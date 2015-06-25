package com.chinacreator.c2.flow.detail;

/**
 * 流程部署查询参数
 * 
 * @author minghua.guo
 * 
 */
public class WfDeploymentParam extends WfPageParam {

	private static final long serialVersionUID = 1L;
	/**
	 * 只返回指定流程定义key的部署，可以为空
	 */
	private String processDefinitionKey;
	/**
	 * 只返回指定流程定义key相似的部署，可以为空
	 */
	private String processDefinitionKeyLike;
	/**
	 * 只返回指定名称的部署，可以为空
	 */
	private String name;
	/**
	 * 只返回名称与指定值相似的部署，可以为空
	 */
	private String nameLike;
	/**
	 * 只返回指定分类的部署，可以为空
	 */
	private String category;
	/**
	 * 只返回与指定分类不同的部署，可以为空
	 */
	private String categoryNotEquals;
	/**
	 * 只返回指定tenantId的部署，可以为空
	 */
	private String tenantId;
	/**
	 * 只返回与指定tenantId匹配的部署，可以为空
	 */
	private Boolean withoutTenantId = false;
	/**
	 * 根据部署id排序
	 */
	private Boolean orderByDeploymentId = false;
	/**
	 * 根据部署时间排序
	 */
	private Boolean orderByDeploymenTime = false;
	/**
	 * 根据部署名称排序
	 */
	private Boolean orderByDeploymentName = false;
	/**
	 * 根据租户ID排序
	 */
	private Boolean orderByTenantId = false;

	public WfDeploymentParam() {
		super();
	}

	public WfDeploymentParam(boolean paged, String order, long start,
			long size, long total) {
		super(paged, order, start, size, total);
	}

	public WfDeploymentParam(String name, String nameLike, String category,
			String categoryNotEquals, String tenantId, Boolean withoutTenantId,
			Boolean orderByDeploymentId, Boolean orderByDeploymenTime,
			Boolean orderByDeploymentName, Boolean orderByTenantId,
			String processDefinitionKey, String processDefinitionKeyLike,
			boolean paged, String order, long start, long size, long total) {
		super(paged, order, start, size, total);
		this.name = name;
		this.nameLike = nameLike;
		this.category = category;
		this.categoryNotEquals = categoryNotEquals;
		this.tenantId = tenantId;
		this.withoutTenantId = withoutTenantId;
		this.orderByDeploymentId = orderByDeploymentId;
		this.orderByDeploymenTime = orderByDeploymenTime;
		this.orderByDeploymentName = orderByDeploymentName;
		this.orderByTenantId = orderByTenantId;
		this.processDefinitionKey = processDefinitionKey;
		this.processDefinitionKeyLike = processDefinitionKeyLike;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategoryNotEquals() {
		return categoryNotEquals;
	}

	public void setCategoryNotEquals(String categoryNotEquals) {
		this.categoryNotEquals = categoryNotEquals;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public Boolean getWithoutTenantId() {
		return withoutTenantId;
	}

	public void setWithoutTenantId(Boolean withoutTenantId) {
		this.withoutTenantId = withoutTenantId;
	}

	public Boolean getOrderByDeploymentId() {
		return orderByDeploymentId;
	}

	public void setOrderByDeploymentId(Boolean orderByDeploymentId) {
		this.orderByDeploymentId = orderByDeploymentId;
	}

	public Boolean getOrderByDeploymenTime() {
		return orderByDeploymenTime;
	}

	public void setOrderByDeploymenTime(Boolean orderByDeploymenTime) {
		this.orderByDeploymenTime = orderByDeploymenTime;
	}

	public Boolean getOrderByDeploymentName() {
		return orderByDeploymentName;
	}

	public void setOrderByDeploymentName(Boolean orderByDeploymentName) {
		this.orderByDeploymentName = orderByDeploymentName;
	}

	public Boolean getOrderByTenantId() {
		return orderByTenantId;
	}

	public void setOrderByTenantId(Boolean orderByTenantId) {
		this.orderByTenantId = orderByTenantId;
	}

	@Override
	public String toString() {
		return "WfDeploymentParam [name=" + name + ", nameLike=" + nameLike
				+ ", category=" + category + ", categoryNotEquals="
				+ categoryNotEquals + ", tenantId=" + tenantId
				+ ", withoutTenantId=" + withoutTenantId
				+ ", orderByDeploymentId=" + orderByDeploymentId
				+ ", orderByDeploymenTime=" + orderByDeploymenTime
				+ ", orderByDeploymentName=" + orderByDeploymentName
				+ ", orderByTenantId=" + orderByTenantId + ", toString()="
				+ super.toString() + "]";
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
}
