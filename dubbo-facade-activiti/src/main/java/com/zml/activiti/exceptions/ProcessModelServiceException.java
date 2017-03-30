package com.zml.activiti.exceptions;

import com.zml.common.exceptions.ServiceException;

public class ProcessModelServiceException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 625926348913704151L;
	
	public static final ProcessModelServiceException ADD_PROCESS_MODEL_FAIL = new ProcessModelServiceException(10080001, "添加流程模型失败!"); 
	
	public static final ProcessModelServiceException UPDATE_PROCESS_MODEL_FAIL = new ProcessModelServiceException(10080002, "更新流程模型失败!");
	
	public static final ProcessModelServiceException DELETE_PROCESS_MODEL_FAIL = new ProcessModelServiceException(10080003, "删除流程模型失败!"); 
	
	public static final ProcessModelServiceException ALREAD_EXIST = new ProcessModelServiceException(10080004, "此流程模型已经存在!");
	
	public static final ProcessModelServiceException NOT_EXIST = new ProcessModelServiceException(10080005, "未找到流程信息!");
	
	public ProcessModelServiceException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
	}
	
	public ProcessModelServiceException(int code, String msg) {
		super(code, msg);
	}
	
	public static ProcessModelServiceException create(int code, String msg) {
		return new ProcessModelServiceException(code, msg);
	}

}
