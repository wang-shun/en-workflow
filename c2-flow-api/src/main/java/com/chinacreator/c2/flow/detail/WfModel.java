package com.chinacreator.c2.flow.detail;

import java.io.Serializable;
import java.util.Date;

/**
 * 流程定义模型
 * @author minghua.guo
 *
 */
public class WfModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 模型id
	 */
	private String id;
	/**
	 * 模型名称
	 */
	private String name;
	/**
	 * 模型key
	 */
	private String key;
	/**
	 * 模型类型
	 */
	private String category;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 最后更新时间
	 */
	private Date lastUpdateTime;
	/**
	 * 模型版本
	 */
	private Integer version = 1;
	/**
	 * 元数据信息
	 */
	private String metaInfo;
	/**
	 * 模型部署id
	 */
	private String deploymentId;
	/**
	 * 模型编辑源码
	 */
	private String editorSourceValueId;
	/**
	 * 模型扩展编辑源码
	 */
	private String editorSourceExtraValueId;
	/**
	 * 租户id
	 */
	private String tenantId;
	
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getMetaInfo() {
		return metaInfo;
	}
	public void setMetaInfo(String metaInfo) {
		this.metaInfo = metaInfo;
	}
	public String getDeploymentId() {
		return deploymentId;
	}
	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}
	public String getEditorSourceValueId() {
		return editorSourceValueId;
	}
	public void setEditorSourceValueId(String editorSourceValueId) {
		this.editorSourceValueId = editorSourceValueId;
	}
	public String getEditorSourceExtraValueId() {
		return editorSourceExtraValueId;
	}
	public void setEditorSourceExtraValueId(String editorSourceExtraValueId) {
		this.editorSourceExtraValueId = editorSourceExtraValueId;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	@Override
	public String toString() {
		return "WfModel [id=" + id + ", name="
				+ name + ", key=" + key + ", category=" + category
				+ ", createTime=" + createTime + ", lastUpdateTime="
				+ lastUpdateTime + ", version=" + version + ", metaInfo="
				+ metaInfo + ", deploymentId=" + deploymentId
				+ ", editorSourceValueId=" + editorSourceValueId
				+ ", editorSourceExtraValueId=" + editorSourceExtraValueId
				+ ", tenantId=" + tenantId + "]";
	}
}
