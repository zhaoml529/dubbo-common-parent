package com.zml.common.constant;

public class SystemConstant {

	/***************** session key *****************/
	public static final String CURRENT_USER_ID = "userId";
    public static final String CURRENT_USER_NAME = "userName";
    
    /***************** service key *****************/
    public static final String DATA_PERMISSION = "dataPermission";					// 是否支持数据权限
    public static final String DATA_PERMISSION_USER_ID = "dataPermissionUserId";	// mbs拦截器获取userId关联查询数据权限
    public static final String DATA_PERMISSION_TYPE = "dataPermissionType";			// 数据权限类型
    
	/***************** activiti *****************/
	public static final String ASSIGNEE = "assignee";
	public static final String CANDIDATE_USER = "candidateUser";
	public static final String CANDIDATE_GROUP = "candidateGroup";
	
}
