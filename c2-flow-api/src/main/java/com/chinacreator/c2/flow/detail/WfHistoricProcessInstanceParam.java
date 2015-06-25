package com.chinacreator.c2.flow.detail;

import java.util.Date;

/**
 * 历史流程实例查询条件
 * 
 * @author minghua.guo
 * 
 */
public class WfHistoricProcessInstanceParam extends WfPageParam {
	private static final long serialVersionUID = 1L;

	/**
	 * 流程实例id
	 */
	private String processInstanceId;
	/**
	 * 历史流程实例的流程定义key。
	 */
	private String processDefinitionKey;
	/**
	 * 历史流程实例的流程定义id。
	 */
	private String processDefinitionId;
	/**
	 * 历史流程实例的businessKey。
	 */
	private String businessKey;
	/**
	 * 历史流程实例的参与者。
	 */
	private String involvedUser;
	/**
	 * 表示历史流程实例是否结束了。
	 */
	private Boolean finished;
	/**
	 * 历史流程实例的上级流程实例id。
	 */
	private String superProcessInstanceId;
	/**
	 * 只返回非子流程的历史流程实例。
	 */
	private Boolean excludeSubprocesses = true;
	/**
	 * 只返回指定时间之后结束的历史流程实例。
	 */
	private Date finishedAfter;
	/**
	 * 只返回指定时间之前结束的历史流程实例。
	 */
	private Date finishedBefore;
	/**
	 * 只返回指定时间之后开始的历史流程实例。
	 */
	private Date startedAfter;
	/**
	 * 只返回指定时间之前开始的历史流程实例。
	 */
	private Date startedBefore;
	/**
	 * 只返回由指定用户启动的历史流程实例。
	 */
	private String startedBy;
	/**
	 * 表示是否应该返回历史流程实例变量。
	 */
	private Boolean includeProcessVariables = false;
	/**
	 * 只返回指定tenantId的实例。
	 */
	private String tenantId;
	/**
	 * 只返回与指定tenantId匹配的实例。
	 */
	private String tenantIdLike;
	/**
	 * 如果为 true，只返回未设置tenantId的实例。如果为 false，会忽略 withoutTenantId 参数。
	 */
	private Boolean withoutTenantId = true;

	/**
	 * 根据流程定义id排序，结合order一起使用。
	 */
	private Boolean orderByProcessDefinitionId = false;
	/**
	 * 根据流程业务id排序，结合order一起使用。
	 */
	private Boolean orderByProcessInstanceBusinessKey = false;
	/**
	 * 根据流程期限排序，结合order一起使用。
	 */
	private Boolean orderByProcessInstanceDuration = false;
	/**
	 * 根据流程结束时间排序，结合order一起使用。
	 */
	private Boolean orderByProcessInstanceEndTime = false;
	/**
	 * 根据流程实例id排序，结合order一起使用。
	 */
	private Boolean orderByProcessInstanceId = false;
	/**
	 * 根据流程启动时间排序，结合order一起使用。
	 */
	private Boolean orderByProcessInstanceStartTime = false;
	/**
	 * 根据租户id排序，结合order一起使用。
	 */
	private Boolean orderByTenantId = false;

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public String getInvolvedUser() {
		return involvedUser;
	}

	public void setInvolvedUser(String involvedUser) {
		this.involvedUser = involvedUser;
	}

	public Boolean getFinished() {
		return finished;
	}

	public void setFinished(Boolean finished) {
		this.finished = finished;
	}

	public String getSuperProcessInstanceId() {
		return superProcessInstanceId;
	}

	public void setSuperProcessInstanceId(String superProcessInstanceId) {
		this.superProcessInstanceId = superProcessInstanceId;
	}

	public Boolean getExcludeSubprocesses() {
		return excludeSubprocesses;
	}

	public void setExcludeSubprocesses(Boolean excludeSubprocesses) {
		this.excludeSubprocesses = excludeSubprocesses;
	}

	public Date getFinishedAfter() {
		return finishedAfter;
	}

	public void setFinishedAfter(Date finishedAfter) {
		this.finishedAfter = finishedAfter;
	}

	public Date getFinishedBefore() {
		return finishedBefore;
	}

	public void setFinishedBefore(Date finishedBefore) {
		this.finishedBefore = finishedBefore;
	}

	public Date getStartedAfter() {
		return startedAfter;
	}

	public void setStartedAfter(Date startedAfter) {
		this.startedAfter = startedAfter;
	}

	public Date getStartedBefore() {
		return startedBefore;
	}

	public void setStartedBefore(Date startedBefore) {
		this.startedBefore = startedBefore;
	}

	public String getStartedBy() {
		return startedBy;
	}

	public void setStartedBy(String startedBy) {
		this.startedBy = startedBy;
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

	public Boolean getOrderByProcessDefinitionId() {
		return orderByProcessDefinitionId;
	}

	public void setOrderByProcessDefinitionId(Boolean orderByProcessDefinitionId) {
		this.orderByProcessDefinitionId = orderByProcessDefinitionId;
	}

	public Boolean getOrderByProcessInstanceBusinessKey() {
		return orderByProcessInstanceBusinessKey;
	}

	public void setOrderByProcessInstanceBusinessKey(
			Boolean orderByProcessInstanceBusinessKey) {
		this.orderByProcessInstanceBusinessKey = orderByProcessInstanceBusinessKey;
	}

	public Boolean getOrderByProcessInstanceDuration() {
		return orderByProcessInstanceDuration;
	}

	public void setOrderByProcessInstanceDuration(
			Boolean orderByProcessInstanceDuration) {
		this.orderByProcessInstanceDuration = orderByProcessInstanceDuration;
	}

	public Boolean getOrderByProcessInstanceEndTime() {
		return orderByProcessInstanceEndTime;
	}

	public void setOrderByProcessInstanceEndTime(
			Boolean orderByProcessInstanceEndTime) {
		this.orderByProcessInstanceEndTime = orderByProcessInstanceEndTime;
	}

	public Boolean getOrderByProcessInstanceId() {
		return orderByProcessInstanceId;
	}

	public void setOrderByProcessInstanceId(Boolean orderByProcessInstanceId) {
		this.orderByProcessInstanceId = orderByProcessInstanceId;
	}

	public Boolean getOrderByProcessInstanceStartTime() {
		return orderByProcessInstanceStartTime;
	}

	public void setOrderByProcessInstanceStartTime(
			Boolean orderByProcessInstanceStartTime) {
		this.orderByProcessInstanceStartTime = orderByProcessInstanceStartTime;
	}

	public Boolean getOrderByTenantId() {
		return orderByTenantId;
	}

	public void setOrderByTenantId(Boolean orderByTenantId) {
		this.orderByTenantId = orderByTenantId;
	}
}
