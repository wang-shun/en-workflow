package com.chinacreator.c2.flow.persistence.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.db.HasRevision;
import org.activiti.engine.impl.db.PersistentObject;

public class WfUniteConfigEntity implements HasRevision, PersistentObject,
		Serializable {

	private static final long serialVersionUID = 1L;

	protected String id;
	protected int revision = 1;
	protected String appId;
	protected String tenantId; 
	protected String engineName; 
	protected String taskList; 
	protected int onlyTitle;
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public Object getPersistentState() {
	    Map<String, Object> persistentState = new HashMap<String, Object>();
	    persistentState.put("appId", this.appId);
	    persistentState.put("tenantId", tenantId);
	    persistentState.put("engineName", this.engineName);
	    persistentState.put("taskList", this.taskList);
	    persistentState.put("onlyTitle", onlyTitle);
	    return persistentState;
	}

	@Override
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
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getEngineName() {
		return engineName;
	}

	public void setEngineName(String engineName) {
		this.engineName = engineName;
	}

	public String getTaskList() {
		return taskList;
	}

	public void setTaskList(String taskList) {
		this.taskList = taskList;
	}

	public int getOnlyTitle() {
		return onlyTitle;
	}

	public void setOnlyTitle(int onlyTitle) {
		this.onlyTitle = onlyTitle;
	}

}
