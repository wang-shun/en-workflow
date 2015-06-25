package com.chinacreator.c2.flow.detail;

import java.io.Serializable;

/**
 * 流程定义对象
 * @author minghua.guo
 *
 */
public class WfProcessDefinition implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * 流程定义id
	 */
	private String id;

	/**
	 * 流程定义名称
	 */
	private String name;

	/**
	 * 流程定义key
	 */
	private String key;

	/**
	 * 流程定义描述
	 */
	private String description;

	/**
	 * 流程定义版本号
	 */
	private int version;

	/**
	 * 资源名称
	 */
	private String resourceName;

	/**
	 * 部署id，已部署才有值
	 */
	private String deploymentId;

	/**
	 * 流程图资源名称
	 */
	private String diagramResourceName;

	/**
	 * 租户id
	 */
	private String tenantId;

	/**
	 * 是否挂起
	 */
	private boolean suspended;
	
	/**
	 * 是否有启动表单Key
	 */
	private boolean startFormKey;
	
	/**
	 * 部署时间
	 */
	private String deployTime;

	public String getDeployTime() {
		return deployTime;
	}

	public void setDeployTime(String deployTime) {
		this.deployTime = deployTime;
	}

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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public String getDiagramResourceName() {
		return diagramResourceName;
	}

	public void setDiagramResourceName(String diagramResourceName) {
		this.diagramResourceName = diagramResourceName;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public boolean isSuspended() {
		return suspended;
	}

	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}

	public boolean isStartFormKey() {
		return startFormKey;
	}

	public void setStartFormKey(boolean startFormKey) {
		this.startFormKey = startFormKey;
	}

	@Override
	public String toString() {
		return "WfProcessDefinition [id=" + id + ", name=" + name + ", key="
				+ key + ", description=" + description + ", version=" + version
				+ ", resourceName=" + resourceName + ", deploymentId="
				+ deploymentId + ", diagramResourceName=" + diagramResourceName
				+ ", tenantId=" + tenantId + ", suspended=" + suspended + ", deployTime=" + deployTime
				+ ", startFormKey=" + startFormKey + "]";
	}
}
