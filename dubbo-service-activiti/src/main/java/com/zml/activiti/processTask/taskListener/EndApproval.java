package com.zml.activiti.processTask.taskListener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

/**
 * 最后的审批节点
 * @author ZML
 *
 */
@Component("endApproval")
public class EndApproval  implements TaskListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1734610029464458384L;

	@Override
	public void notify(DelegateTask delegateTask) {
		delegateTask.setVariable("endApproval", true);
	}

}
