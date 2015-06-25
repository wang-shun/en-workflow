package com.chinacreator.c2.flow.detail;

/**
 * 流程实例参数
 * @author Administrator
 *
 */
public class WfProcessInstanceParam extends WfPageParam {

	private static final long serialVersionUID = 1L;
	/**
	 * 只返回指定id的流程实例
	 */
	private String id;
	/**
	 * 只返回指定流程定义key的流程实例。
	 */
	private String processDefinitionKey;
	/**
	 * 只返回指定流程定义id的流程实例。
	 */
	private String processDefinitionId;
	/**
	 * 只返回指定businessKey的流程实例。
	 */
	private String businessKey;
	/**
	 * 只返回指定用户参与过的流程实例。
	 */
	private String involvedUser;
	/**
	 * 如果为 true，只返回挂起的流程实例。如果为 false，只返回未挂起（激活）的流程实例。
	 */
	private Boolean suspended;
	/**
	 * 只返回指定上级流程实例id的流程实例（对应call-activity）。
	 */
	private String superProcessInstanceId;
	/**
	 * 只返回指定子流程id的流程实例（对方call-activity）。
	 */
	private String subProcessInstanceId;
	/**
	 * 只返回非子流程的流程实例。
	 */
	private Boolean excludeSubprocesses = false;
	/**
	 * 表示结果中包含流程变量。
	 */
	private Boolean includeProcessVariables = false;
	/**
	 * 只返回指定tenantId的流程实例。
	 */
	private String tenantId;
	/**
	 * 只返回与指定tenantId匹配的流程实例。
	 */
	private String tenantIdLike;
	/**
	 * 如果为 true，只返回未设置tenantId的流程实例。如果为 false，会忽略 withoutTenantId 参数。
	 */
	private Boolean withoutTenantId = false;
	/**
	 * 是否根据processDefinitionId排序
	 */
	private Boolean orderByProcessDefinitionId = false;
	/**
	 * 是否根据processDefinitionKey排序
	 */
	private Boolean orderByProcessDefinitionKey = false;
	/**
	 * 是否根据processInstanceId排序
	 */
	private Boolean orderByProcessInstanceId = false;
	/**
	 * 是否根据tenantId排序
	 */
	private Boolean orderByTenantId = false;

	public WfProcessInstanceParam() {
		super();
	}

	public WfProcessInstanceParam(boolean paged, String order, long start,
			long size, long total) {
		super(paged, order, start, size, total);
	}

	public WfProcessInstanceParam(String id, String processDefinitionKey,
			String processDefinitionId, String businessKey,
			String involvedUser, Boolean suspended,
			String superProcessInstanceId, String subProcessInstanceId,
			Boolean excludeSubprocesses, Boolean includeProcessVariables,
			String tenantId, String tenantIdLike, Boolean withoutTenantId,
			Boolean orderByProcessDefinitionId,
			Boolean orderByProcessDefinitionKey,
			Boolean orderByProcessInstanceId, Boolean orderByTenantId,
			boolean paged, String order, long start, long size, long total) {
		super(paged, order, start, size, total);
		this.id = id;
		this.processDefinitionKey = processDefinitionKey;
		this.processDefinitionId = processDefinitionId;
		this.businessKey = businessKey;
		this.involvedUser = involvedUser;
		this.suspended = suspended;
		this.superProcessInstanceId = superProcessInstanceId;
		this.subProcessInstanceId = subProcessInstanceId;
		this.excludeSubprocesses = excludeSubprocesses;
		this.includeProcessVariables = includeProcessVariables;
		this.tenantId = tenantId;
		this.tenantIdLike = tenantIdLike;
		this.withoutTenantId = withoutTenantId;
		this.orderByProcessDefinitionId = orderByProcessDefinitionId;
		this.orderByProcessDefinitionKey = orderByProcessDefinitionKey;
		this.orderByProcessInstanceId = orderByProcessInstanceId;
		this.orderByTenantId = orderByTenantId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Boolean getSuspended() {
		return suspended;
	}

	public void setSuspended(Boolean suspended) {
		this.suspended = suspended;
	}

	public String getSuperProcessInstanceId() {
		return superProcessInstanceId;
	}

	public void setSuperProcessInstanceId(String superProcessInstanceId) {
		this.superProcessInstanceId = superProcessInstanceId;
	}

	public String getSubProcessInstanceId() {
		return subProcessInstanceId;
	}

	public void setSubProcessInstanceId(String subProcessInstanceId) {
		this.subProcessInstanceId = subProcessInstanceId;
	}

	public Boolean getExcludeSubprocesses() {
		return excludeSubprocesses;
	}

	public void setExcludeSubprocesses(Boolean excludeSubprocesses) {
		this.excludeSubprocesses = excludeSubprocesses;
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

	public Boolean getOrderByProcessDefinitionKey() {
		return orderByProcessDefinitionKey;
	}

	public void setOrderByProcessDefinitionKey(
			Boolean orderByProcessDefinitionKey) {
		this.orderByProcessDefinitionKey = orderByProcessDefinitionKey;
	}

	public Boolean getOrderByProcessInstanceId() {
		return orderByProcessInstanceId;
	}

	public void setOrderByProcessInstanceId(Boolean orderByProcessInstanceId) {
		this.orderByProcessInstanceId = orderByProcessInstanceId;
	}

	public Boolean getOrderByTenantId() {
		return orderByTenantId;
	}

	public void setOrderByTenantId(Boolean orderByTenantId) {
		this.orderByTenantId = orderByTenantId;
	}

	@Override
	public String toString() {
		return "WfProcessInstanceParam [id=" + id + ", processDefinitionKey="
				+ processDefinitionKey + ", processDefinitionId="
				+ processDefinitionId + ", businessKey=" + businessKey
				+ ", involvedUser=" + involvedUser + ", suspended=" + suspended
				+ ", superProcessInstanceId=" + superProcessInstanceId
				+ ", subProcessInstanceId=" + subProcessInstanceId
				+ ", excludeSubprocesses=" + excludeSubprocesses
				+ ", includeProcessVariables=" + includeProcessVariables
				+ ", tenantId=" + tenantId + ", tenantIdLike=" + tenantIdLike
				+ ", withoutTenantId=" + withoutTenantId
				+ ", orderByProcessDefinitionId=" + orderByProcessDefinitionId
				+ ", orderByProcessDefinitionKey="
				+ orderByProcessDefinitionKey + ", orderByProcessInstanceId="
				+ orderByProcessInstanceId + ", orderByTenantId="
				+ orderByTenantId + ", toString()=" + super.toString() + "]";
	}

}
