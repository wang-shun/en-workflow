package com.chinacreator.c2.flow.detail;

/**
 * 历史细节查询参数
 * 
 * @author minghua.guo
 * 
 */
public class WfDetailParam extends WfPageParam {

	private static final long serialVersionUID = 1L;

	/**
	 * 历史细节的id。
	 */
	private String id;
	/**
	 * 历史细节的流程实例id。
	 */
	private String processInstanceId;
	/**
	 * 历史细节的分支id。
	 */
	private String executionId;
	/**
	 * 历史细节的活动实例id。
	 */
	private String activityInstanceId;
	/**
	 * 历史细节的任务id。
	 */
	private String taskId;
	/**
	 * 表示结果中只返回FormProperties。
	 */
	private Boolean selectOnlyFormProperties;
	/**
	 * 表示结果中只返回变量更新信息。
	 */
	private Boolean selectOnlyVariableUpdates;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public String getActivityInstanceId() {
		return activityInstanceId;
	}

	public void setActivityInstanceId(String activityInstanceId) {
		this.activityInstanceId = activityInstanceId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Boolean getSelectOnlyFormProperties() {
		return selectOnlyFormProperties;
	}

	public void setSelectOnlyFormProperties(Boolean selectOnlyFormProperties) {
		this.selectOnlyFormProperties = selectOnlyFormProperties;
	}

	public Boolean getSelectOnlyVariableUpdates() {
		return selectOnlyVariableUpdates;
	}

	public void setSelectOnlyVariableUpdates(Boolean selectOnlyVariableUpdates) {
		this.selectOnlyVariableUpdates = selectOnlyVariableUpdates;
	}
}
