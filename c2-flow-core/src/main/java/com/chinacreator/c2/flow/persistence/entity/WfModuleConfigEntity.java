package com.chinacreator.c2.flow.persistence.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.db.PersistentObject;

/**
 * 事项流程配置表访问对象类
 * 
 * @author 杨祎程
 * 
 */
public class WfModuleConfigEntity implements PersistentObject, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private String id;
//	private int revision = 1;

	/**
	 * 流程定义ID
	 */
	private String procDefId;

	/**
	 * 流程定义KEY
	 */
	private String procDefKey;

	/**
	 * 流程定义名称
	 */
	private String procDefName;

	/**
	 * 事项ID
	 */
	private String moduleId;

	/**
	 * 事项名称
	 */
	private String moduleName;

	/**
	 * 是否最新
	 */
	private Boolean isLatest;

	/**
	 * 应用ID
	 */
	private String appId;

	/**
	 * 是否需要外围配置
	 */
	private String isHasproperty;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProcDefId() {
		return procDefId;
	}

	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}

	public String getProcDefKey() {
		return procDefKey;
	}

	public void setProcDefKey(String procDefKey) {
		this.procDefKey = procDefKey;
	}

	public String getProcDefName() {
		return procDefName;
	}

	public void setProcDefName(String procDefName) {
		this.procDefName = procDefName;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Boolean getIsLatest() {
		return isLatest;
	}

	public void setIsLatest(Boolean isLatest) {
		this.isLatest = isLatest;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getIsHasproperty() {
		return isHasproperty;
	}

	public void setIsHasproperty(String isHasproperty) {
		this.isHasproperty = isHasproperty;
	}

	@Override
	public Object getPersistentState() {
		Map<String, Object> persistentState = new HashMap<String, Object>();
		persistentState.put("id", this.id);
		persistentState.put("appId", this.appId);
		persistentState.put("isHasproperty", this.isHasproperty);
		persistentState.put("isLatest", this.isLatest);
		persistentState.put("moduleId", this.moduleId);
		persistentState.put("moduleName", this.moduleName);
		persistentState.put("procDefId", this.procDefId);
		persistentState.put("procDefKey", this.procDefKey);
		persistentState.put("procDefName", this.procDefName);
		return persistentState;
	}

	/*@Override
	public void setRevision(int revision) {
		this.revision = revision;
	}

	@Override
	public int getRevision() {
		return revision;
	}

	@Override
	public int getRevisionNext() {
		return revision + 1;
	}*/

}
