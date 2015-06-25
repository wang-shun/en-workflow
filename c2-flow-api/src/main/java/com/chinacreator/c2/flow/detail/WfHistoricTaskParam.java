package com.chinacreator.c2.flow.detail;

import java.util.Date;

public class WfHistoricTaskParam extends WfPageParam {

	private static final long serialVersionUID = 1L;

	/**
	 * 只返回指定的名称。
	 */
	private String name;
	/**
	 * 只返回与指定名称匹配的任务
	 */
	private String nameLike;
	/**
	 * 只返回指定描述的任务。
	 */
	private String description;
	/**
	 * 只返回指定优先级的任务。
	 */
	private Integer priority;
	/**
	 * 只返回比指定优先级大的任务。
	 */
	private Integer minimumPriority;
	/**
	 * 只返回比指定优先级小的任务。
	 */
	private Integer maximumPriority;
	/**
	 * 只返回分配给指定用户的任务。
	 */
	private String assignee;
	/**
	 * 只返回负责人与指定值匹配的任务。
	 */
	private String assigneeLike;
	/**
	 * 只返回原拥有人为指定用户的任务。
	 */
	private String owner;
	/**
	 * 只返回原拥有人与指定值匹配的任务。
	 */
	private String ownerLike;
	/**
	 * 只返回可以被指定用户领取的任务。这包含将用户设置为直接候选人和用户作为候选群组一员的情况。
	 */
	private String candidateUser;
	/**
	 * 只返回可以被指定群组中用户领取的任务。
	 */
	private String candidateGroup;
	/**
	 * 只返回指定用户参与过的任务。
	 */
	private String involvedUser;
	/**
	 * 只返回指定任务定义id的任务。
	 */
	private String taskDefinitionKey;
	/**
	 * 只返回任务定义id与指定值匹配的任务。
	 */
	private String taskDefinitionKeyLike;
	/**
	 * 只返回作为指定id的流程实例的一部分的任务。
	 */
	private String processInstanceId;
	/**
	 * 只返回作为指定业务key的流程实例的一部分的任务。
	 */
	private String processInstanceBusinessKey;
	/**
	 * 只返回业务key与指定值匹配的流程实例的一部分的任务。
	 */
	private String processInstanceBusinessKeyLike;
	/**
	 * 只返回作为指定流程定义key的流程实例的一部分的任务。
	 */
	private String processDefinitionKey;
	/**
	 * 只返回指定流程定义key与指定值匹配的流程实例的一部分的任务。
	 */
	private String processDefinitionKeyLike;
	/**
	 * 只返回作为指定流程定义名称的流程实例的一部分的任务。
	 */
	private String processDefinitionName;
	/**
	 * 只返回流程定义名称与指定值匹配的流程实例的一部分的任务。
	 */
	private String processDefinitionNameLike;
	/**
	 * 只返回作为指定id分支的一部分的任务。
	 */
	private String executionId;
	/**
	 * 只返回指定创建时间的任务。
	 */
	private Date createdOn;
	/**
	 * 只返回在指定时间之前创建的任务。
	 */
	private Date createdBefore;
	/**
	 * 只返回在指定时间之后创建的任务。
	 */
	private Date createdAfter;
	/**
	 * 只返回指定持续时间的任务。
	 */
	private Date dueOn;
	/**
	 * 只返回持续时间在指定时间之前的任务。
	 */
	private Date dueBefore;
	/**
	 * 只返回持续时间在指定时间之后的任务。
	 */
	private Date dueAfter;
	/**
	 * 只返回没有设置持续时间的任务。如果值为false就会忽略这个属性。
	 */
	private Boolean withoutDueDate = false;
	/**
	 * 如果为 true，只返回未挂起的任务（作为未挂起流程的一部分，或者不属于任何流程）。如果为false，只返回作为挂起流程一部分的任务。
	 */
	private Boolean finished;
	/**
	 * 表示在结果中包含本地变量
	 */
	private Boolean includeTaskLocalVariables = false;
	/**
	 * 表示在结果中包含流程变量。
	 */
	private Boolean includeProcessVariables = false;
	/**
	 * 只返回指定tenantId的任务。
	 */
	private String tenantId;
	/**
	 * 只返回与指定tenantId匹配的任务。
	 */
	private String tenantIdLike;
	/**
	 * 如果为 true，只返回未设置tenantId的任务。如果为 false，会忽略 withoutTenantId 参数
	 */
	private Boolean withoutTenantId = false;

	/**
	 * 根据删除原因排序，结合order一起使用。
	 */
	private Boolean orderByDeleteReason = false;
	/**
	 * 根据分支id排序，结合order一起使用。
	 */
	private Boolean orderByExecutionId = false;
	/**
	 * 根据历史活动实例id排序，结合order一起使用。
	 */
	private Boolean orderByHistoricActivityInstanceId = false;
	/**
	 * 根据历史任务实例期限排序，结合order一起使用。
	 */
	private Boolean orderByHistoricTaskInstanceDuration = false;
	/**
	 * 根据历史人物结束时间排序，结合order一起使用。
	 */
	private Boolean orderByHistoricTaskInstanceEndTime = false;
	/**
	 * 根据历史人物启动时间排序，结合order一起使用。
	 */
	private Boolean orderByHistoricTaskInstanceStartTime = false;
	/**
	 * 根据流程定义id排序，结合order一起使用。
	 */
	private Boolean orderByProcessDefinitionId = false;
	/**
	 * 根据流程实例id排序，结合order一起使用。
	 */
	private Boolean orderByProcessInstanceId = false;
	/**
	 * 根据任务执行人排序，结合order一起使用。
	 */
	private Boolean orderByTaskAssignee = false;
	/**
	 * 根据任务定义Key排序，结合order一起使用。
	 */
	private Boolean orderByTaskDefinitionKey = false;
	/**
	 * 根据任务描述排序，结合order一起使用。
	 */
	private Boolean orderByTaskDescription = false;
	/**
	 * 根据任务期限排序，结合order一起使用。
	 */
	private Boolean orderByTaskDueDate = false;
	/**
	 * 根据任务id排序，结合order一起使用。
	 */
	private Boolean orderByTaskId = false;
	/**
	 * 根据任务名称排序，结合order一起使用。
	 */
	private Boolean orderByTaskName = false;
	/**
	 * 根据任务拥有者排序，结合order一起使用。
	 */
	private Boolean orderByTaskOwner = false;
	/**
	 * 根据任务优先级排序，结合order一起使用。
	 */
	private Boolean orderByTaskPriority = false;
	/**
	 * 根据租户id排序，结合order一起使用。
	 */
	private Boolean orderByTenantId = false;

	public Boolean getOrderByDeleteReason() {
		return orderByDeleteReason;
	}

	public void setOrderByDeleteReason(Boolean orderByDeleteReason) {
		this.orderByDeleteReason = orderByDeleteReason;
	}

	public Boolean getOrderByExecutionId() {
		return orderByExecutionId;
	}

	public void setOrderByExecutionId(Boolean orderByExecutionId) {
		this.orderByExecutionId = orderByExecutionId;
	}

	public Boolean getOrderByHistoricActivityInstanceId() {
		return orderByHistoricActivityInstanceId;
	}

	public void setOrderByHistoricActivityInstanceId(
			Boolean orderByHistoricActivityInstanceId) {
		this.orderByHistoricActivityInstanceId = orderByHistoricActivityInstanceId;
	}

	public Boolean getOrderByHistoricTaskInstanceDuration() {
		return orderByHistoricTaskInstanceDuration;
	}

	public void setOrderByHistoricTaskInstanceDuration(
			Boolean orderByHistoricTaskInstanceDuration) {
		this.orderByHistoricTaskInstanceDuration = orderByHistoricTaskInstanceDuration;
	}

	public Boolean getOrderByHistoricTaskInstanceEndTime() {
		return orderByHistoricTaskInstanceEndTime;
	}

	public void setOrderByHistoricTaskInstanceEndTime(
			Boolean orderByHistoricTaskInstanceEndTime) {
		this.orderByHistoricTaskInstanceEndTime = orderByHistoricTaskInstanceEndTime;
	}

	public Boolean getOrderByHistoricTaskInstanceStartTime() {
		return orderByHistoricTaskInstanceStartTime;
	}

	public void setOrderByHistoricTaskInstanceStartTime(
			Boolean orderByHistoricTaskInstanceStartTime) {
		this.orderByHistoricTaskInstanceStartTime = orderByHistoricTaskInstanceStartTime;
	}

	public Boolean getOrderByProcessDefinitionId() {
		return orderByProcessDefinitionId;
	}

	public void setOrderByProcessDefinitionId(Boolean orderByProcessDefinitionId) {
		this.orderByProcessDefinitionId = orderByProcessDefinitionId;
	}

	public Boolean getOrderByProcessInstanceId() {
		return orderByProcessInstanceId;
	}

	public void setOrderByProcessInstanceId(Boolean orderByProcessInstanceId) {
		this.orderByProcessInstanceId = orderByProcessInstanceId;
	}

	public Boolean getOrderByTaskAssignee() {
		return orderByTaskAssignee;
	}

	public void setOrderByTaskAssignee(Boolean orderByTaskAssignee) {
		this.orderByTaskAssignee = orderByTaskAssignee;
	}

	public Boolean getOrderByTaskDefinitionKey() {
		return orderByTaskDefinitionKey;
	}

	public void setOrderByTaskDefinitionKey(Boolean orderByTaskDefinitionKey) {
		this.orderByTaskDefinitionKey = orderByTaskDefinitionKey;
	}

	public Boolean getOrderByTaskDescription() {
		return orderByTaskDescription;
	}

	public void setOrderByTaskDescription(Boolean orderByTaskDescription) {
		this.orderByTaskDescription = orderByTaskDescription;
	}

	public Boolean getOrderByTaskDueDate() {
		return orderByTaskDueDate;
	}

	public void setOrderByTaskDueDate(Boolean orderByTaskDueDate) {
		this.orderByTaskDueDate = orderByTaskDueDate;
	}

	public Boolean getOrderByTaskId() {
		return orderByTaskId;
	}

	public void setOrderByTaskId(Boolean orderByTaskId) {
		this.orderByTaskId = orderByTaskId;
	}

	public Boolean getOrderByTaskName() {
		return orderByTaskName;
	}

	public void setOrderByTaskName(Boolean orderByTaskName) {
		this.orderByTaskName = orderByTaskName;
	}

	public Boolean getOrderByTaskOwner() {
		return orderByTaskOwner;
	}

	public void setOrderByTaskOwner(Boolean orderByTaskOwner) {
		this.orderByTaskOwner = orderByTaskOwner;
	}

	public Boolean getOrderByTaskPriority() {
		return orderByTaskPriority;
	}

	public void setOrderByTaskPriority(Boolean orderByTaskPriority) {
		this.orderByTaskPriority = orderByTaskPriority;
	}

	public Boolean getOrderByTenantId() {
		return orderByTenantId;
	}

	public void setOrderByTenantId(Boolean orderByTenantId) {
		this.orderByTenantId = orderByTenantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameLike() {
		return nameLike;
	}

	public void setNameLike(String nameLike) {
		this.nameLike = nameLike;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getMinimumPriority() {
		return minimumPriority;
	}

	public void setMinimumPriority(Integer minimumPriority) {
		this.minimumPriority = minimumPriority;
	}

	public Integer getMaximumPriority() {
		return maximumPriority;
	}

	public void setMaximumPriority(Integer maximumPriority) {
		this.maximumPriority = maximumPriority;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getAssigneeLike() {
		return assigneeLike;
	}

	public void setAssigneeLike(String assigneeLike) {
		this.assigneeLike = assigneeLike;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOwnerLike() {
		return ownerLike;
	}

	public void setOwnerLike(String ownerLike) {
		this.ownerLike = ownerLike;
	}

	public String getCandidateUser() {
		return candidateUser;
	}

	public void setCandidateUser(String candidateUser) {
		this.candidateUser = candidateUser;
	}

	public String getCandidateGroup() {
		return candidateGroup;
	}

	public void setCandidateGroup(String candidateGroup) {
		this.candidateGroup = candidateGroup;
	}

	public String getInvolvedUser() {
		return involvedUser;
	}

	public void setInvolvedUser(String involvedUser) {
		this.involvedUser = involvedUser;
	}

	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}

	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}

	public String getTaskDefinitionKeyLike() {
		return taskDefinitionKeyLike;
	}

	public void setTaskDefinitionKeyLike(String taskDefinitionKeyLike) {
		this.taskDefinitionKeyLike = taskDefinitionKeyLike;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getProcessInstanceBusinessKey() {
		return processInstanceBusinessKey;
	}

	public void setProcessInstanceBusinessKey(String processInstanceBusinessKey) {
		this.processInstanceBusinessKey = processInstanceBusinessKey;
	}

	public String getProcessInstanceBusinessKeyLike() {
		return processInstanceBusinessKeyLike;
	}

	public void setProcessInstanceBusinessKeyLike(
			String processInstanceBusinessKeyLike) {
		this.processInstanceBusinessKeyLike = processInstanceBusinessKeyLike;
	}

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public String getProcessDefinitionKeyLike() {
		return processDefinitionKeyLike;
	}

	public void setProcessDefinitionKeyLike(String processDefinitionKeyLike) {
		this.processDefinitionKeyLike = processDefinitionKeyLike;
	}

	public String getProcessDefinitionName() {
		return processDefinitionName;
	}

	public void setProcessDefinitionName(String processDefinitionName) {
		this.processDefinitionName = processDefinitionName;
	}

	public String getProcessDefinitionNameLike() {
		return processDefinitionNameLike;
	}

	public void setProcessDefinitionNameLike(String processDefinitionNameLike) {
		this.processDefinitionNameLike = processDefinitionNameLike;
	}

	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getCreatedBefore() {
		return createdBefore;
	}

	public void setCreatedBefore(Date createdBefore) {
		this.createdBefore = createdBefore;
	}

	public Date getCreatedAfter() {
		return createdAfter;
	}

	public void setCreatedAfter(Date createdAfter) {
		this.createdAfter = createdAfter;
	}

	public Date getDueOn() {
		return dueOn;
	}

	public void setDueOn(Date dueOn) {
		this.dueOn = dueOn;
	}

	public Date getDueBefore() {
		return dueBefore;
	}

	public void setDueBefore(Date dueBefore) {
		this.dueBefore = dueBefore;
	}

	public Date getDueAfter() {
		return dueAfter;
	}

	public void setDueAfter(Date dueAfter) {
		this.dueAfter = dueAfter;
	}

	public Boolean getWithoutDueDate() {
		return withoutDueDate;
	}

	public void setWithoutDueDate(Boolean withoutDueDate) {
		this.withoutDueDate = withoutDueDate;
	}

	public Boolean getFinished() {
		return finished;
	}

	public void setFinished(Boolean finished) {
		this.finished = finished;
	}

	public Boolean getIncludeTaskLocalVariables() {
		return includeTaskLocalVariables;
	}

	public void setIncludeTaskLocalVariables(Boolean includeTaskLocalVariables) {
		this.includeTaskLocalVariables = includeTaskLocalVariables;
	}

	public Boolean getIncludeProcessVariables() {
		return includeProcessVariables;
	}

	public void setIncludeProcessVariables(Boolean includeProcessVariables) {
		this.includeProcessVariables = includeProcessVariables;
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
