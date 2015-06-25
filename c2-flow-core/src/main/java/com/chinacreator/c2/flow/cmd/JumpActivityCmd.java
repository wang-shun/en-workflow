package com.chinacreator.c2.flow.cmd;

/**
 * @author: Henry Yan
 */

import java.util.Map;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;

public class JumpActivityCmd implements Command<String> {
	private String processDefinitionId;
	private String bussinessKey;
	private String jumpToActivityId;
	private String processInstanceId;
	private String jumpReason;
	private Map<String, Object> variables;

	public JumpActivityCmd(String processDefinitionId,String bussinessKey,
			String processInstanceId, String jumpToActivityId,
			Map<String, Object> variables) {
		this(processDefinitionId, bussinessKey, processInstanceId, jumpToActivityId, "jump",
				variables);
	}

	public JumpActivityCmd(String processDefinitionId,String bussinessKey,
			String processInstanceId, String jumpToActivityId,
			String jumpReason, Map<String, Object> variables) {
		this.processDefinitionId = processDefinitionId;
		this.bussinessKey = bussinessKey;
		this.jumpToActivityId = jumpToActivityId;
		this.processInstanceId = processInstanceId;
		this.jumpReason = jumpReason;
		this.variables = variables;
	}

	public String execute(CommandContext commandContext) {
		ExecutionEntity executionEntity = null;
		if (processDefinitionId != null) {

			// 获得当前流程的定义模型
			ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity)commandContext.getProcessEngineConfiguration().getRepositoryService().getProcessDefinition(processDefinitionId);
					
			ActivityImpl activityImpl = processDefinition
					.findActivity(jumpToActivityId);
			executionEntity = processDefinition
					.createProcessInstance(bussinessKey, activityImpl);
			if (variables != null)
				executionEntity.setVariables(variables);
			executionEntity.start();
		} else if (processInstanceId != null) {
			executionEntity = commandContext
					.getExecutionEntityManager().findExecutionById(
							processInstanceId);

			executionEntity.destroyScope(jumpReason);

			ProcessDefinitionImpl processDefinition = executionEntity
					.getProcessDefinition();
			ActivityImpl activity = processDefinition
					.findActivity(jumpToActivityId);

			if (variables != null) {
				executionEntity.setVariables(variables);
			}
			executionEntity.executeActivity(activity);
		}


		return executionEntity.getProcessInstanceId();
	}
}
