package com.zml.activiti.processTask.taskListener;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zml.activiti.service.IProcessDefineService;
import com.zml.activiti.service.IProcessModelService;

/**
 * 动态设置审批人，不用重新部署流程。
 * @author ZML
 */
@Component("userTaskListener")
public class UserTaskListener  implements TaskListener {
	
	private static final Logger logger = LoggerFactory.getLogger(UserTaskListener.class);
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 2469173747518657343L;
	
	@Autowired
    private RepositoryService repositoryService;
	
	@Autowired
	private IProcessModelService processModelService;
	
	@Autowired
	private IProcessDefineService processDefineService;
	
/*	@Autowired
	@Qualifier("httpClient")
	private Client httpClient; */
	
	@Override
	public void notify(DelegateTask delegateTask) {
		/*String processDefinitionId = delegateTask.getProcessDefinitionId();	//processKey4:1:2512
		ProcessDefinition processDefinition = this.repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
		String processDefinitionKey = processDefinition.getKey();			//processKey4
		String taskDefinitionKey = delegateTask.getTaskDefinitionKey();		//userTask1
		String applyStaffId = delegateTask.getVariable("startStaffId").toString();	// 申请人编制id
		logger.info("进入任务监听器！");
		ProcessModel processModel = this.processModelService.findByKey(processDefinitionKey);
		if(processModel != null) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("modelId", processModel.getId());
			List<ProcessDefine> defineList = this.processDefineService.findAllDefine(params);
			for(ProcessDefine pd : defineList) {
				// 设置审批人员
				String taskId = pd.getTaskId();
				Integer candidateType = pd.getCandidateType();
				// 编制id或岗位id或部门id
				String selectId = pd.getCandidateIds();		
				if(candidateType == 4) {	// 发起人处理
					continue;
				}
				if(taskDefinitionKey.equals(taskId)){
					switch (candidateType) {
						case 1:				// 自定义	(现在用于项目分析报告)
							JSONObject jsonObject1 = this.getUserStaffId(applyStaffId, null);	// applyStaffId提报人的编制
							Long applyDeptId = jsonObject1.getLong("deptId");					// 提报人的部门
							JSONObject jsonObject2 = this.getUserStaffId(null, selectId);		// selectPostId所选的岗位id
							Long selectDeptId = jsonObject2.getLong("deptId");					// 所选岗位的部门
							logger.info("提报人的编制id:"+applyStaffId+" 提报人的部门id:"+applyDeptId);
							logger.info("审批人的岗位id:"+selectId+" 审批人的部门id:"+selectDeptId);
							if(applyDeptId == selectDeptId) {									// judge.如果提报人的部门和所选部门相等，不是跨部门审批。
								Long leaderPostId = jsonObject1.getLong("leaderPostId");		// 提报人上级领导的岗位
								logger.info("提报人上级领导的岗位id:"+leaderPostId);
								if(leaderPostId == Long.valueOf(selectId)) {					// judge.如果提报人上级领导的岗位和所选岗位相等，则直接由上级领导审批。
									logger.info("直接由上级领导审批！");
									Long leaderStaffId = jsonObject1.getLong("leaderStaffId");	// 提报人上级领导的编制
									if(leaderStaffId != null) {
										delegateTask.setAssignee(leaderStaffId.toString());
									} else {
										logger.error("提报人的上级领导为空！！！");
									}
								} else {														// judge.不是直接上级领导审批，则所选岗位下的所有编制都可以查看此待办任务。
									logger.info("由所选岗位下的所有编制签收任务！");
									delegateTask.addCandidateGroup(selectId);
								}
							} else {															// judge.是跨部门审批，则所选岗位下的所有编制都可以查看此待办任务。
								logger.info("跨部门审批，由所选岗位下的所有编制签收任务！");
								delegateTask.addCandidateGroup(selectId);
							}
							break;
						case 2:		// 处理人(编制)
							delegateTask.setAssignee(selectId);			// 所选编制id
							break;
						case 3:		// 候选组(岗位)
							delegateTask.addCandidateGroup(selectId);	// 所选岗位id
							break;
						case 5:		// 候选人(多个编制)
							String[] userIds = selectId.split(",");
							List<String> users = new ArrayList<String>();
							for(int i=0; i<userIds.length;i++){
								users.add(userIds[i]);
							}
							delegateTask.addCandidateUsers(users);		// 所选编制id(多个)
							break;
						default:
							break;
					}
				}
			}
		}*/
	}
	
	/**
	 * 查询用户编制
	 * @param staffId	编制id
	 * @param postId	岗位id
	 * @return
	 */
	/*private JSONObject getUserStaffId(String staffId, String postId){
		JSONObject jo = null;
		Map<String,Object> conditionMap=new HashMap<String,Object>();
		conditionMap.put("type", "findAll");
		conditionMap.put("transCode", "M0008");
		if(staffId != null) {
			conditionMap.put("staffId", staffId);
		} 
		if(postId != null) {
			conditionMap.put("postId", postId);
		} 
		String userResult = this.httpClient.callTrans("M0008", conditionMap);
		JSONObject obj = JSON.parseObject(userResult);
		if(StringUtils.notNull(obj.getString("list"))) {
			String list = obj.getString("list");
			JSONArray jsonArray = JSON.parseArray(list);
			if(jsonArray != null && jsonArray.size() > 0){
				jo = jsonArray.getJSONObject(0);
			} else {
				jo = null;
			}
		} else {
			jo = null;
		}
		return jo;
	}*/

}
