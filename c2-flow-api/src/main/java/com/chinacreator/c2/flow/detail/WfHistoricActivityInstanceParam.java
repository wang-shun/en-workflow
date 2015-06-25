package com.chinacreator.c2.flow.detail;

/**
 * 历史活动实例请求参数
 * @author minghua.guo
 *
 */
public class WfHistoricActivityInstanceParam extends WfPageParam {

	private static final long serialVersionUID = 1L;

	/**
	 * 活动实例id
	 */
	private String activityId;
	/**
	 * 历史活动实例id。
	 */
	private String activityInstanceId;
	/**
	 * 历史活动实例的名称。
	 */
	private String activityName;
	/**
	 * 历史活动实例的元素类型。
	 */
	private String activityType;
	/**
	 * 历史活动实例的分支id。
	 */
	private String executionId;
	/**
	 * 表示历史活动实例是否完成。
	 */
	private Boolean finished;
	/**
	 * 历史活动实例的负责人。
	 */
	private String taskAssignee;
	/**
	 * 历史活动实例的流程实例id。
	 */
	private String processInstanceId;
	/**
	 * 历史活动实例的流程定义id。
	 */
	private String processDefinitionId;
	/**
	 * 只返回指定tenantId的实例。
	 */
	private String tenantId;
	/**
	 * 只返回与指定tenantId匹配的实例
	 */
	private String tenantIdLike;
	/**
	 * 如果为 true，只返回未设置tenantId的历史。如果为 false，会忽略 withoutTenantId 参数。
	 */
	private Boolean withoutTenantId;
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getActivityInstanceId() {
		return activityInstanceId;
	}
	public void setActivityInstanceId(String activityInstanceId) {
		this.activityInstanceId = activityInstanceId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	public String getExecutionId() {
		return executionId;
	}
	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}
	public Boolean getFinished() {
		return finished;
	}
	public void setFinished(Boolean finished) {
		this.finished = finished;
	}
	public String getTaskAssignee() {
		return taskAssignee;
	}
	public void setTaskAssignee(String taskAssignee) {
		this.taskAssignee = taskAssignee;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}
	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getTenantIdLike() {
		return tenantIdLike;
	}
	public void setTenantIdLike(String tenantIdLike) {
		this.tenantIdLike = tenantIdLike;
	}
	public Boolean getWithoutTenantId() {
		return withoutTenantId;
	}
	public void setWithoutTenantId(Boolean withoutTenantId) {
		this.withoutTenantId = withoutTenantId;
	}
}
