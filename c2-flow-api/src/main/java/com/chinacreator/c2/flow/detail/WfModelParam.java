package com.chinacreator.c2.flow.detail;

/**
 * 流程定义模型参数
 * 
 * @author Administrator
 * 
 */
public class WfModelParam extends WfPageParam {
	private static final long serialVersionUID = 1L;
	/**
	 * 只返回指定id的模型。
	 */
	private String id;
	/**
	 * 只返回指定分类的模型。
	 */
	private String category;
	/**
	 * 只返回与给定分类匹配的模型。使用%作为通配符。
	 */
	private String categoryLike;
	/**
	 * 只返回非指定分类的模型。
	 */
	private String categoryNotEquals;
	/**
	 * 只返回指定名称的模型。
	 */
	private String name;
	/**
	 * 只返回与指定名称匹配的模型。使用%作为通配符。
	 */
	private String nameLike;
	/**
	 * 只返回指定key的模型。
	 */
	private String key;
	/**
	 * 只返回包含在指定部署包中的模型。
	 */
	private String deploymentId;
	/**
	 * 只返回指定版本的模型。
	 */
	private Integer version;
	/**
	 * 如果为 true，只返回最新版本的模型。最好与key一起使用。如果为 false，就会返回所有版本。
	 */
	private Boolean latestVersion = false;
	/**
	 * 如果为 true，只返回已部署的模型。如果为 false，只返回未部署的模型（deploymentId为null）。
	 */
	private Boolean deployed;
	/**
	 * 只返回指定tenantId的模型。
	 */
	private String tenantId;
	/**
	 * 只返回与指定tenantId匹配的模型。
	 */
	private String tenantIdLike;
	/**
	 * 如果为 true，只返回没有设置tenantId的模型。如果为 false，会忽略 withoutTenantId 参数。
	 */
	private Boolean withoutTenantId = false;

	/**
	 * 根据创建时间排序，需要和order一起使用
	 */
	private Boolean orderByCreateTime = false;
	/**
	 * 根据最后更新时间排序，需要和order一起使用
	 */
	private Boolean orderByLastUpdateTime = false;
	/**
	 * 根据模型类型排序，需要和order一起使用
	 */
	private Boolean orderByModelCategory = false;
	/**
	 * 根据模型id排序，需要和order一起使用
	 */
	private Boolean orderByModelId = false;
	/**
	 * 根据模型key排序，需要和order一起使用
	 */
	private Boolean orderByModelKey = false;
	/**
	 * 根据模型名称排序，需要和order一起使用
	 */
	private Boolean orderByModelName = false;
	/**
	 * 根据模型版本排序，需要和order一起使用
	 */
	private Boolean orderByModelVersion = false;
	/**
	 * 根据租户id排序，需要和order一起使用
	 */
	private Boolean orderByTenantId = false;

	public WfModelParam() {
		super();
	}

	public WfModelParam(boolean paged, String order, long start, long size,
			long total) {
		super(paged, order, start, size, total);
	}

	public WfModelParam(String id, String category, String categoryLike,
			String categoryNotEquals, String name, String nameLike, String key,
			String deploymentId, Integer version, Boolean latestVersion,
			Boolean deployed, String tenantId, String tenantIdLike,
			Boolean withoutTenantId, Boolean orderByCreateTime,
			Boolean orderByLastUpdateTime, Boolean orderByModelCategory,
			Boolean orderByModelId, Boolean orderByModelKey,
			Boolean orderByModelName, Boolean orderByModelVersion,
			Boolean orderByTenantId, boolean paged, String order, long start,
			long size, long total) {
		super(paged, order, start, size, total);
		this.id = id;
		this.category = category;
		this.categoryLike = categoryLike;
		this.categoryNotEquals = categoryNotEquals;
		this.name = name;
		this.nameLike = nameLike;
		this.key = key;
		this.deploymentId = deploymentId;
		this.version = version;
		this.latestVersion = latestVersion;
		this.deployed = deployed;
		this.tenantId = tenantId;
		this.tenantIdLike = tenantIdLike;
		this.withoutTenantId = withoutTenantId;
		this.orderByCreateTime = orderByCreateTime;
		this.orderByLastUpdateTime = orderByLastUpdateTime;
		this.orderByModelCategory = orderByModelCategory;
		this.orderByModelId = orderByModelId;
		this.orderByModelKey = orderByModelKey;
		this.orderByModelName = orderByModelName;
		this.orderByModelVersion = orderByModelVersion;
		this.orderByTenantId = orderByTenantId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategoryLike() {
		return categoryLike;
	}

	public void setCategoryLike(String categoryLike) {
		this.categoryLike = categoryLike;
	}

	public String getCategoryNotEquals() {
		return categoryNotEquals;
	}

	public void setCategoryNotEquals(String categoryNotEquals) {
		this.categoryNotEquals = categoryNotEquals;
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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Boolean getLatestVersion() {
		return latestVersion;
	}

	public void setLatestVersion(Boolean latestVersion) {
		this.latestVersion = latestVersion;
	}

	public Boolean getDeployed() {
		return deployed;
	}

	public void setDeployed(Boolean deployed) {
		this.deployed = deployed;
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

	public Boolean getOrderByCreateTime() {
		return orderByCreateTime;
	}

	public void setOrderByCreateTime(Boolean orderByCreateTime) {
		this.orderByCreateTime = orderByCreateTime;
	}

	public Boolean getOrderByLastUpdateTime() {
		return orderByLastUpdateTime;
	}

	public void setOrderByLastUpdateTime(Boolean orderByLastUpdateTime) {
		this.orderByLastUpdateTime = orderByLastUpdateTime;
	}

	public Boolean getOrderByModelCategory() {
		return orderByModelCategory;
	}

	public void setOrderByModelCategory(Boolean orderByModelCategory) {
		this.orderByModelCategory = orderByModelCategory;
	}

	public Boolean getOrderByModelId() {
		return orderByModelId;
	}

	public void setOrderByModelId(Boolean orderByModelId) {
		this.orderByModelId = orderByModelId;
	}

	public Boolean getOrderByModelKey() {
		return orderByModelKey;
	}

	public void setOrderByModelKey(Boolean orderByModelKey) {
		this.orderByModelKey = orderByModelKey;
	}

	public Boolean getOrderByModelName() {
		return orderByModelName;
	}

	public void setOrderByModelName(Boolean orderByModelName) {
		this.orderByModelName = orderByModelName;
	}

	public Boolean getOrderByModelVersion() {
		return orderByModelVersion;
	}

	public void setOrderByModelVersion(Boolean orderByModelVersion) {
		this.orderByModelVersion = orderByModelVersion;
	}

	public Boolean getOrderByTenantId() {
		return orderByTenantId;
	}

	public void setOrderByTenantId(Boolean orderByTenantId) {
		this.orderByTenantId = orderByTenantId;
	}

	@Override
	public String toString() {
		return "WfModelParam [id=" + id + ", category=" + category
				+ ", categoryLike=" + categoryLike + ", categoryNotEquals="
				+ categoryNotEquals + ", name=" + name + ", nameLike="
				+ nameLike + ", key=" + key + ", deploymentId=" + deploymentId
				+ ", version=" + version + ", latestVersion=" + latestVersion
				+ ", deployed=" + deployed + ", tenantId=" + tenantId
				+ ", tenantIdLike=" + tenantIdLike + ", withoutTenantId="
				+ withoutTenantId + ", orderByCreateTime=" + orderByCreateTime
				+ ", orderByLastUpdateTime=" + orderByLastUpdateTime
				+ ", orderByModelCategory=" + orderByModelCategory
				+ ", orderByModelId=" + orderByModelId + ", orderByModelKey="
				+ orderByModelKey + ", orderByModelName=" + orderByModelName
				+ ", orderByModelVersion=" + orderByModelVersion
				+ ", orderByTenantId=" + orderByTenantId + ", toString()="
				+ super.toString() + "]";
	}
}
