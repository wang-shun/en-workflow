package com.chinacreator.c2.flow.detail;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 任务对象
 * @author Administrator
 *
 */
public class WfTask implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 流程定义id
	 */
	private String processDefinitionId;
	/**
	 * 流程实例id
	 */
	private String processInstanceId;
	/**
	 * 业务id
	 */
	private String businessId;
	/**
	 * 任务处理人
	 */
	private String assignee;
	/**
	 * 任务实例id
	 */
	private String taskId;
	/**
	 * 任务定义id
	 */
	private String taskDefinitionId;
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
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 到期时间
	 */
	private Date dueDate;
	/**
	 * 任务变量
	 */
	private Map<String, Object> taskLocalVariables;

	/**
	 * 租户id
	 */
	private String tenantId;

	/**
	 * 拥有者
	 */
	private String ower;
	/**
	 * 分类
	 */
	private String category;
	/**
	 * 优先级
	 */
	private int priority = 0;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 上级任务id
	 */
	private String parentTaskId;

	/**
	 * 流程变量
	 */
	private Map<String, Object> processVariables;

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

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getTaskDefinitionId() {
		return taskDefinitionId;
	}

	public void setTaskDefinitionId(String taskDefinitionId) {
		this.taskDefinitionId = taskDefinitionId;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Map<String, Object> getTaskLocalVariables() {
		return taskLocalVariables;
	}

	public void setTaskLocalVariables(Map<String, Object> taskLocalVariables) {
		this.taskLocalVariables = taskLocalVariables;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getOwer() {
		return ower;
	}

	public void setOwer(String ower) {
		this.ower = ower;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentTaskId() {
		return parentTaskId;
	}

	public void setParentTaskId(String parentTaskId) {
		this.parentTaskId = parentTaskId;
	}

	public Map<String, Object> getProcessVariables() {
		return processVariables;
	}

	public void setProcessVariables(Map<String, Object> processVariables) {
		this.processVariables = processVariables;
	}

	@Override
	public String toString() {
		return "WfTask [processDefinitionId=" + processDefinitionId
				+ ", processInstanceId=" + processInstanceId + ", businessId="
				+ businessId + ", assignee=" + assignee + ", taskId=" + taskId
				+ ", taskDefinitionId=" + taskDefinitionId + ", suspended="
				+ suspended + ", executionId=" + executionId + ", description="
				+ description + ", createTime=" + createTime + ", dueDate="
				+ dueDate + ", taskLocalVariables=" + taskLocalVariables
				+ ", tenantId=" + tenantId + ", ower=" + ower + ", category="
				+ category + ", priority=" + priority + ", name=" + name
				+ ", parentTaskId=" + parentTaskId + ", processVariables="
				+ processVariables + "]";
	}

}
