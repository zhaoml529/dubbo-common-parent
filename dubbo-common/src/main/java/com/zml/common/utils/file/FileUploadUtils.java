package com.zml.common.utils.file;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.zml.common.utils.file.exception.FileNameLengthLimitExceededException;
import com.zml.common.utils.file.exception.InvalidExtensionException;

/**
 * 文件上传工具类
 * @author ZML
 *
 */
public class FileUploadUtils {

	//默认大小 50M
    public static final long DEFAULT_MAX_SIZE = 52428800;

    //默认的文件名最大长度
    public static final int DEFAULT_FILE_NAME_LENGTH = 200;

    public static final String[] IMAGE_EXTENSION = {
            "bmp", "gif", "jpg", "jpeg", "png"
    };

    public static final String[] FLASH_EXTENSION = {
            "swf", "flv"
    };

    public static final String[] MEDIA_EXTENSION = {
            "swf", "flv", "mp3", "wav", "wma", "wmv", "mid", "avi", "mpg", "asf", "rm", "rmvb"
    };

    public static final String[] DEFAULT_ALLOWED_EXTENSION = {
            //图片
            "bmp", "gif", "jpg", "jpeg", "png",
            //word excel powerpoint
            "doc", "docx", "xls", "xlsx", "ppt", "pptx",
            "html", "htm", "txt",
            //压缩文件
            "rar", "zip", "gz", "bz2",
            //pdf
            "pdf"
    };

    
    /**
     * 以默认配置进行文件上传
     *
     * @param request 	当前请求
     * @param file    	上传的文件
     * @param filePath	上传路径
     * @return
     * @throws Exception 
     * @throws IOException 
     * @throws InvalidExtensionException 
     */
    public static final String upload(HttpServletRequest request, MultipartFile file, String filePath) throws InvalidExtensionException, IOException, Exception {
        return upload(request, file, filePath, DEFAULT_ALLOWED_EXTENSION);
    }


    /**
     * 以默认配置进行文件上传
     *
     * @param request          当前请求
     * @param file             上传的文件
     * @param filePath			上传路径
     * @param allowedExtension 允许上传的文件类型
     * @return
     */
    public static final String upload(HttpServletRequest request, MultipartFile file, String filePath, String[] allowedExtension) throws InvalidExtensionException, IOException, Exception {
    	return upload(request, filePath, file, allowedExtension, DEFAULT_MAX_SIZE);
    }

    /**
     * 文件上传
     *
     * @param request          当前请求 从请求中提取 应用上下文根
     * @param baseDir          相对应用的基目录
     * @param file             上传的文件
     * @param allowedExtension 允许的文件类型 null 表示允许所有
     * @param maxSize          最大上传的大小 -1 表示不限制
     * @param needDatePathAndRandomName 是否需要日期目录和随机文件名前缀
     * @return 返回上传成功的文件名
     * @throws InvalidExtensionException      如果MIME类型不允许
     * @throws FileSizeLimitExceededException 如果超出最大大小
     * @throws FileNameLengthLimitExceededException
     *                                        文件名太长
     * @throws IOException                    比如读写文件出错时
     */
    public static final String upload(
            HttpServletRequest request, String baseDir, MultipartFile file,
            String[] allowedExtension, long maxSize)
            throws InvalidExtensionException, FileSizeLimitExceededException, IOException, FileNameLengthLimitExceededException {

        int fileNamelength = file.getOriginalFilename().length();
        if (fileNamelength > FileUploadUtils.DEFAULT_FILE_NAME_LENGTH) {
            throw new FileNameLengthLimitExceededException(file.getOriginalFilename(), fileNamelength, FileUploadUtils.DEFAULT_FILE_NAME_LENGTH);
        }

        assertAllowed(file, allowedExtension, maxSize);
        //提取文件名
        String filename = extractFilename(file, baseDir);

        File desc = getAbsoluteFile(extractUploadDir(request), filename);

        //上传
        file.transferTo(desc);
        return filename;
    }

    private static final File getAbsoluteFile(String uploadDir, String filename) throws IOException {

        uploadDir = FilenameUtils.normalizeNoEndSeparator(uploadDir);

        File desc = new File(uploadDir + filename);

        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        if (!desc.exists()) {
            desc.createNewFile();
        }
        return desc;
    }


    public static final String extractFilename(MultipartFile file, String baseDir)
            throws UnsupportedEncodingException {
        String filename = file.getOriginalFilename();
        int slashIndex = filename.indexOf("/");
        if (slashIndex >= 0) {
//            filename = filename.substring(slashIndex + 1);
        	filename.replace("/", "-");
        }
        filename = baseDir + File.separator + filename;
        return filename;
    }

    /**
     * 是否允许文件上传
     *
     * @param file             上传的文件
     * @param allowedExtension 文件类型  null 表示允许所有
     * @param maxSize          最大大小 字节为单位 -1表示不限制
     * @return
     * @throws InvalidExtensionException      如果MIME类型不允许
     * @throws FileSizeLimitExceededException 如果超出最大大小
     */
    public static final void assertAllowed(MultipartFile file, String[] allowedExtension, long maxSize)
            throws InvalidExtensionException, FileSizeLimitExceededException {

        String filename = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        if (allowedExtension != null && !isAllowedExtension(extension, allowedExtension)) {
            if (allowedExtension == IMAGE_EXTENSION) {
                throw new InvalidExtensionException.InvalidImageExtensionException(allowedExtension, extension, filename);
            } else if (allowedExtension == FLASH_EXTENSION) {
                throw new InvalidExtensionException.InvalidFlashExtensionException(allowedExtension, extension, filename);
            } else if (allowedExtension == MEDIA_EXTENSION) {
                throw new InvalidExtensionException.InvalidMediaExtensionException(allowedExtension, extension, filename);
            } else {
                throw new InvalidExtensionException(allowedExtension, extension, filename);
            }
        }

        long size = file.getSize();
        if (maxSize != -1 && size > maxSize) {
            throw new FileSizeLimitExceededException("The file size exceeds limit！", size, maxSize);
        }
    }

    /**
     * 判断MIME类型是否是允许的MIME类型
     *
     * @param extension
     * @param allowedExtension
     * @return
     */
    public static final boolean isAllowedExtension(String extension, String[] allowedExtension) {
        for (String str : allowedExtension) {
            if (str.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 提取上传的根目录 默认是应用的根
     *
     * @param request
     * @return
     */
    public static final String extractUploadDir(HttpServletRequest request) {
    	//return request.getServletContext().getRealPath("/");	//上传到项目所在目录，重新部署后文件会消失
		return "";	//不指定具体路径，文件保存在项目所在盘符下。
    }

    /**
     * 删除文件
     * @param request
     * @param filename
     * @throws IOException
     */
    public static final void delete(HttpServletRequest request, String filename) throws IOException {
        if (StringUtils.isEmpty(filename)) {
            return;
        }
        
        File desc = getAbsoluteFile(extractUploadDir(request), filename);
        if (desc.exists()) {
            desc.delete();
        }
    }
    
}