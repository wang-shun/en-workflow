package com.chinacreator.c2.flow.detail;

import com.chinacreator.c2.flow.detail.WfVariable.VariableScope;

/**
 * 历史变量查询条件
 * @author minghua.guo
 *
 */
public class WfVariableParam extends WfPageParam {
	private static final long serialVersionUID = 1L;

	/**
	 * 历史变量实例的流程实例id。
	 */
	private String processInstanceId;
	/**
	 * 历史变量实例的任务id。
	 */
	private String taskId;
	/**
	 * 历史变量实例的变量名称。
	 */
	private String variableName;
	/**
	 * 对变量名称使用'like'操作历史变量实例。
	 */
	private String variableNameLike;
	
	/**
	 * 参数范围 {@link VariableScope}
	 */
	private VariableScope scope;
	
	public VariableScope getScope() {
		return scope;
	}
	
	public void setScope(VariableScope scope) {
		this.scope = scope;
	}
	
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getVariableName() {
		return variableName;
	}
	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	public String getVariableNameLike() {
		return variableNameLike;
	}
	public void setVariableNameLike(String variableNameLike) {
		this.variableNameLike = variableNameLike;
	}
}
