package com.chinacreator.c2.flow.detail;

import java.io.Serializable;
import java.util.Date;

/**
 * 流程部署对象
 * @author minghua.guo
 *
 */
public class WfDeployment implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 部署id
	 */
	private String id;
	/**
	 * 部署名称
	 */
	private String name;
	/**
	 * 类别
	 */
	private String category;
	/**
	 * 租户id
	 */
	private String tenantId = "";
	/**
	 * 部署时间
	 */
	private Date deploymentTime;
	public Date getDeploymentTime() {
		return deploymentTime;
	}
	public void setDeploymentTime(Date deploymentTime) {
		this.deploymentTime = deploymentTime;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	@Override
	public String toString() {
		return "WfDeployment [id=" + id + ", name=" + name + ", category="
				+ category + ", tenantId=" + tenantId + ", deploymentTime="
				+ deploymentTime + "]";
	}
}
