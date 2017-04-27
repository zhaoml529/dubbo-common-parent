package com.dili.deliver.exceptions;

import com.zml.common.exceptions.ServiceException;

/**
 * @author fuping
 *
 */
public class ComPetitorServiceException extends ServiceException{
	private static final long serialVersionUID = -8240308476595458974L;

	public static final ComPetitorServiceException ADD_TB_COMPETITOR_ERR = new  ComPetitorServiceException(10030001, "添加失败！");
	
	public static final ComPetitorServiceException UPDATE_TB_COMPETITOR_ERR = new  ComPetitorServiceException(10030002, "修改失败！");
	
	public static final ComPetitorServiceException DELETE_TB_COMPETITOR_ERR = new  ComPetitorServiceException(10030003, "删除失败！");
	
	public ComPetitorServiceException(int code,String msg){
		super(code,msg);
	}
}
