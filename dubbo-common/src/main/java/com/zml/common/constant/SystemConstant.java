package com.zml.common.constant;

public class SystemConstant {

	/***************** session key *****************/
    public static final String CURRENT_USER = "user";
    //public static final String GROUP_ID = "groupId";
    public static final String SESSION_FORCE_LOGOUT_KEY = "session.force.logout";
    
	/***************** activiti *****************/
	public static final String ASSIGNEE = "assignee";
	public static final String CANDIDATE_USER = "candidateUser";
	public static final String CANDIDATE_GROUP = "candidateGroup";
	
	/***************** shiro *******************/
	public static final Integer PASSWORD_RETRY_COUNT = 5;	//登录次数超过此数时锁定
	public static final Integer PASSWORD_SHOW_JCAPTCHA = 3;	//登录次超过此数时数显示验证码
}
