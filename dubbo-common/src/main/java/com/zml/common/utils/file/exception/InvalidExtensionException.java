package com.zml.common.utils.file.exception;
import java.util.Arrays;

import org.apache.commons.fileupload.FileUploadException;

/**
 * 文件类型不匹配（mime类型）
 * @author ZML
 *
 */

public class InvalidExtensionException extends FileUploadException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -371507233589077329L;
	private String[] allowedExtension;
    private String extension;
    private String filename;

    public InvalidExtensionException(String[] allowedExtension, String extension, String filename) {
        super("filename : [" + filename + "], extension : [" + extension + "], allowed extension : [" + Arrays.toString(allowedExtension) + "]");
        this.allowedExtension = allowedExtension;
        this.extension = extension;
        this.filename = filename;
    }

    public String[] getAllowedExtension() {
        return allowedExtension;
    }

    public String getExtension() {
        return extension;
    }

    public String getFilename() {
        return filename;
    }

    public static class InvalidImageExtensionException extends InvalidExtensionException {
        /**
		 * 
		 */
		private static final long serialVersionUID = -4496888209342477227L;

		public InvalidImageExtensionException(String[] allowedExtension, String extension, String filename) {
            super(allowedExtension, extension, filename);
        }
    }

    public static class InvalidFlashExtensionException extends InvalidExtensionException {
        /**
		 * 
		 */
		private static final long serialVersionUID = 7648999604719698733L;

		public InvalidFlashExtensionException(String[] allowedExtension, String extension, String filename) {
            super(allowedExtension, extension, filename);
        }
    }

    public static class InvalidMediaExtensionException extends InvalidExtensionException {
        /**
		 * 
		 */
		private static final long serialVersionUID = 286322742240780105L;

		public InvalidMediaExtensionException(String[] allowedExtension, String extension, String filename) {
            super(allowedExtension, extension, filename);
        }
    }

}