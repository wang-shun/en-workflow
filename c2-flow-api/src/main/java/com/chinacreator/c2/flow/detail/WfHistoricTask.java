package com.chinacreator.c2.flow.detail;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 历史任务对象
 * 
 * @author minghua.guo
 * 
 */
public class WfHistoricTask implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 任务id
	 */
	private String id;
	/**
	 * 任务名称
	 */
	private String name;
	/**
	 * 删除原因
	 */
	private String deleteReason;
	/**
	 * 流程定义id
	 */
	private String processDefinitionId;
	/**
	 * 流程实例id
	 */
	private String processInstanceId;
	/**
	 * 任务拥有者
	 */
	private String owner;
	/**
	 * 任务处理人
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
	 * 签收时间
	 */
	private Date claimTime;
	/**
	 * 持续时间
	 */
	private Long durationInMillis;
	/**
	 * 工作时间
	 */
	private Long workTimeInMillis;
	/**
	 * 任务定义Key
	 */
	private String taskDefinitionKey;
	/**
	 * 是否挂起
	 */
	private boolean suspended;
	/**
	 * 流程执行id
	 */
	private String executionId;
	/**
	 * 任务描述
	 */
	private String description;
	/**
	 * 表单Key
	 */
	private String formKey;
	/**
	 * 优先级
	 */
	private int priority;
	/**
	 * 到期时间
	 */
	private Date dueDate;
	/**
	 * 类别
	 */
	private String category;
	/**
	 * 上级任务id
	 */
	private String parentTaskId;
	/**
	 * 租户id
	 */
	private String tenantId;
	/**
	 * 任务变量
	 */
	private Map<String, Object> taskLocalVariables;
	/**
	 * 流程变量
	 */
	private Map<String, Object> processVariables;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeleteReason() {
		return deleteReason;
	}

	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
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

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
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

	public Date getClaimTime() {
		return claimTime;
	}

	public void setClaimTime(Date claimTime) {
		this.claimTime = claimTime;
	}

	public Long getDurationInMillis() {
		return durationInMillis;
	}

	public void setDurationInMillis(Long durationInMillis) {
		this.durationInMillis = durationInMillis;
	}

	public Long getWorkTimeInMillis() {
		return workTimeInMillis;
	}

	public void setWorkTimeInMillis(Long workTimeInMillis) {
		this.workTimeInMillis = workTimeInMillis;
	}

	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}

	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}

	public boolean isSuspended() {
		return suspended;
	}

	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}

	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFormKey() {
		return formKey;
	}

	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getParentTaskId() {
		return parentTaskId;
	}

	public void setParentTaskId(String parentTaskId) {
		this.parentTaskId = parentTaskId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public Map<String, Object> getTaskLocalVariables() {
		return taskLocalVariables;
	}

	public void setTaskLocalVariables(Map<String, Object> taskLocalVariables) {
		this.taskLocalVariables = taskLocalVariables;
	}

	public Map<String, Object> getProcessVariables() {
		return processVariables;
	}

	public void setProcessVariables(Map<String, Object> processVariables) {
		this.processVariables = processVariables;
	}

	@Override
	public String toString() {
		return "WfHistoricTask [id=" + id + ", name=" + name
				+ ", deleteReason=" + deleteReason + ", processDefinitionId="
				+ processDefinitionId + ", processInstanceId="
				+ processInstanceId + ", owner=" + owner + ", assignee="
				+ assignee + ", startTime=" + startTime + ", endTime="
				+ endTime + ", claimTime=" + claimTime + ", durationInMillis="
				+ durationInMillis + ", workTimeInMillis=" + workTimeInMillis
				+ ", taskDefinitionKey=" + taskDefinitionKey + ", suspended="
				+ suspended + ", executionId=" + executionId + ", description="
				+ description + ", formKey=" + formKey + ", priority="
				+ priority + ", dueDate=" + dueDate + ", category=" + category
				+ ", parentTaskId=" + parentTaskId + ", tenantId=" + tenantId
				+ ", taskLocalVariables=" + taskLocalVariables
				+ ", processVariables=" + processVariables + "]";
	}

}
