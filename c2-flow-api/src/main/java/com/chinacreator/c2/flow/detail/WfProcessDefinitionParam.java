package com.chinacreator.c2.flow.detail;

/**
 * 流程定义参数
 * 
 * @author Administrator
 * 
 */
public class WfProcessDefinitionParam extends WfPageParam {
	private static final long serialVersionUID = 1L;

	/**
	 * 流程定义id
	 */
	private String id;
	/**
	 * 流程定义版本号
	 */
	private Integer version;
	/**
	 * 流程定义名称
	 */
	private String name;
	/**
	 * 流程定义名称模糊查找，需要自带%
	 */
	private String nameLike;
	/**
	 * 流程定义key
	 */
	private String key;
	/**
	 * 流程定义key模糊查找，需要自带%
	 */
	private String keyLike;
	/**
	 * 资源名称
	 */
	private String resourceName;
	/**
	 * 资源名称模糊查找，需要自带%
	 */
	private String resourceNameLike;
	/**
	 * 类别
	 */
	private String category;
	/**
	 * 类别模糊查找，需要自带%
	 */
	private String categoryLike;
	/**
	 * 排除类别查找
	 */
	private String categoryNotEquals;
	/**
	 * 部署id
	 */
	private String deploymentId;
	/**
	 * 是否能被用户启动
	 */
	private String startableByUser;
	/**
	 * 是否最新版本
	 */
	private Boolean latest = false;
	/**
	 * 是否挂起
	 */
	private Boolean suspended = false;

	/**
	 * 根据部署id排序，需要结合order一起使用
	 */
	private Boolean orderByDeploymentId = false;
	/**
	 * 根据类别排序，需要结合order一起使用
	 */
	private Boolean orderByCategory = false;
	/**
	 * 根据流程定义id排序，需要结合order一起使用
	 */
	private Boolean orderByProcessDefinitionId = false;
	/**
	 * 根据流程定义key排序，需要结合order一起使用
	 */
	private Boolean orderByKey = false;
	/**
	 * 根据流程定名称排序，需要结合order一起使用
	 */
	private Boolean orderByName = false;
	/**
	 * 根据版本号排序，需要结合order一起使用
	 */
	private Boolean orderByVersion = false;
	/**
	 * 根据租户id排序，需要结合order一起使用
	 */
	private Boolean orderByTenantId = false;
	
	/**
	 * 租户id
	 */
	private String tenantId;
	
	private String tenantIdLike;
	
	private Boolean withoutTenantId;

	public WfProcessDefinitionParam() {
		super();
	}

	public WfProcessDefinitionParam(boolean paged, String order, long start,
			long size, long total) {
		super(paged, order, start, size, total);
	}

	public WfProcessDefinitionParam(String id, Integer version, String name,
			String nameLike, String key, String keyLike, String resourceName,
			String resourceNameLike, String category, String categoryLike,
			String categoryNotEquals, String deploymentId,
			String startableByUser, Boolean latest, Boolean suspended,
			Boolean orderByDeploymentId, Boolean orderByCategory,
			Boolean orderByProcessDefinitionId, Boolean orderByKey,
			Boolean orderByName, Boolean orderByVersion,
			Boolean orderByTenantId, boolean paged, String order, long start,
			long size, long total) {
		super(paged, order, start, size, total);
		this.id = id;
		this.version = version;
		this.name = name;
		this.nameLike = nameLike;
		this.key = key;
		this.keyLike = keyLike;
		this.resourceName = resourceName;
		this.resourceNameLike = resourceNameLike;
		this.category = category;
		this.categoryLike = categoryLike;
		this.categoryNotEquals = categoryNotEquals;
		this.deploymentId = deploymentId;
		this.startableByUser = startableByUser;
		this.latest = latest;
		this.suspended = suspended;
		this.orderByDeploymentId = orderByDeploymentId;
		this.orderByCategory = orderByCategory;
		this.orderByProcessDefinitionId = orderByProcessDefinitionId;
		this.orderByKey = orderByKey;
		this.orderByName = orderByName;
		this.orderByVersion = orderByVersion;
		this.orderByTenantId = orderByTenantId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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

	public String getKeyLike() {
		return keyLike;
	}

	public void setKeyLike(String keyLike) {
		this.keyLike = keyLike;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceNameLike() {
		return resourceNameLike;
	}

	public void setResourceNameLike(String resourceNameLike) {
		this.resourceNameLike = resourceNameLike;
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

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public String getStartableByUser() {
		return startableByUser;
	}

	public void setStartableByUser(String startableByUser) {
		this.startableByUser = startableByUser;
	}

	public Boolean getLatest() {
		return latest;
	}

	public void setLatest(Boolean latest) {
		this.latest = latest;
	}

	public Boolean getSuspended() {
		return suspended;
	}

	public void setSuspended(Boolean suspended) {
		this.suspended = suspended;
	}

	public Boolean getOrderByDeploymentId() {
		return orderByDeploymentId;
	}

	public void setOrderByDeploymentId(Boolean orderByDeploymentId) {
		this.orderByDeploymentId = orderByDeploymentId;
	}

	public Boolean getOrderByCategory() {
		return orderByCategory;
	}

	public void setOrderByCategory(Boolean orderByCategory) {
		this.orderByCategory = orderByCategory;
	}

	public Boolean getOrderByProcessDefinitionId() {
		return orderByProcessDefinitionId;
	}

	public void setOrderByProcessDefinitionId(Boolean orderByProcessDefinitionId) {
		this.orderByProcessDefinitionId = orderByProcessDefinitionId;
	}

	public Boolean getOrderByKey() {
		return orderByKey;
	}

	public void setOrderByKey(Boolean orderByKey) {
		this.orderByKey = orderByKey;
	}

	public Boolean getOrderByName() {
		return orderByName;
	}

	public void setOrderByName(Boolean orderByName) {
		this.orderByName = orderByName;
	}

	public Boolean getOrderByVersion() {
		return orderByVersion;
	}

	public void setOrderByVersion(Boolean orderByVersion) {
		this.orderByVersion = orderByVersion;
	}

	public Boolean getOrderByTenantId() {
		return orderByTenantId;
	}

	public void setOrderByTenantId(Boolean orderByTenantId) {
		this.orderByTenantId = orderByTenantId;
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

	@Override
	public String toString() {
		return "WfProcessDefinitionQuery [id=" + id + ", version=" + version
				+ ", name=" + name + ", nameLike=" + nameLike + ", key=" + key
				+ ", keyLike=" + keyLike + ", resourceName=" + resourceName
				+ ", resourceNameLike=" + resourceNameLike + ", category="
				+ category + ", categoryLike=" + categoryLike
				+ ", categoryNotEquals=" + categoryNotEquals
				+ ", deploymentId=" + deploymentId + ", startableByUser="
				+ startableByUser + ", latest=" + latest + ", suspended="
				+ suspended + ", orderByDeploymentId=" + orderByDeploymentId
				+ ", orderByCategory=" + orderByCategory
				+ ", orderByProcessDefinitionId=" + orderByProcessDefinitionId
				+ ", orderByKey=" + orderByKey + ", orderByName=" + orderByName
				+ ", tenantId=" + tenantId
				+ ", tenantIdLike=" + tenantIdLike
				+ ", withoutTenantId=" + withoutTenantId
				+ ", orderByVersion=" + orderByVersion + ", orderByTenantId="
				+ orderByTenantId + ", toString()=" + super.toString() + "]";
	}
}
