package com.chinacreator.c2.flow.detail;

import java.io.Serializable;
import java.util.Date;

/**
 * 历史活动实例
 * @author minghua.guo
 *
 */
public class WfHistoricActivityInstance implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 活动实例id
	 */
	private String id;
	/**
	 * 活动id
	 */
	private String activityId;
	/**
	 * 活动名称
	 */
	private String activityName;
	/**
	 * 活动类型
	 */
	private String activityType;
	/**
	 * 流程定义id
	 */
	private String processDefinitionId;
	/**
	 * 活动实例id
	 */
	private String processInstanceId;
	/**
	 * 分支id
	 */
	private String executionId;
	/**
	 * 任务id
	 */
	private String taskId;
	/**
	 * 被调用流程实例id
	 */
	private String calledProcessInstanceId;
	/**
	 * 执行人
	 */
	private String assignee;
	/**
	 * 开始时间
	 */
	private Date startTime;
	/**
	 * 结束时间
	 */
	private Date endTime;
	/**
	 * 期限
	 */
	private Long durationInMillis;
	/**
	 * 租户id
	 */
	private String tenantId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
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
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}
	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
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
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getCalledProcessInstanceId() {
		return calledProcessInstanceId;
	}
	public void setCalledProcessInstanceId(String calledProcessInstanceId) {
		this.calledProcessInstanceId = calledProcessInstanceId;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Long getDurationInMillis() {
		return durationInMillis;
	}
	public void setDurationInMillis(Long durationInMillis) {
		this.durationInMillis = durationInMillis;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	@Override
	public String toString() {
		return "WfHistoricActivityInstance [id=" + id + ", activityId="
				+ activityId + ", activityName=" + activityName
				+ ", activityType=" + activityType + ", processDefinitionId="
				+ processDefinitionId + ", processInstanceId="
				+ processInstanceId + ", executionId=" + executionId
				+ ", taskId=" + taskId + ", calledProcessInstanceId="
				+ calledProcessInstanceId + ", assignee=" + assignee
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", durationInMillis=" + durationInMillis + ", tenantId="
				+ tenantId + "]";
	}
}
