package com.zml.user.exceptions;

import com.zml.common.exceptions.ServiceException;

/**
 * 数据权限错误基础类
 * @author zhaomingliang
 * @date 2017年4月10日
 */
public class DataPermissionServiceException extends ServiceException {

	/**
	 * 
	 */
	public static final long serialVersionUID = 8477415367381213608L;
	
	public static final DataPermissionServiceException ADD_DATA_PERMISSION_ERR = new DataPermissionServiceException(10050001, "添加数据权限失败！");
	
	public static final DataPermissionServiceException UPDATE_DATA_PERMISSION_ERR = new DataPermissionServiceException(10050002, "更新数据权限失败！");
	
	public static final DataPermissionServiceException DELETE_DATA_PERMISSION_ERR = new DataPermissionServiceException(10050003, "删除数据权限失败！");
	
	public static final DataPermissionServiceException DATA_PERMISSION_IS_EXIST = new DataPermissionServiceException(10050004, "此权限组名称已经存在！");

	public DataPermissionServiceException() {
		
	}
	
	public DataPermissionServiceException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}
	
	public DataPermissionServiceException(int code, String msg) {
		super(code, msg);
	}
	
	public static DataPermissionServiceException create(int code, String msg) {
		return new DataPermissionServiceException(code, msg);
	}
}
