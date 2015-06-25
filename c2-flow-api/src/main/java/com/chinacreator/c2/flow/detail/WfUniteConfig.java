package com.chinacreator.c2.flow.detail;

import java.io.Serializable;

public class WfUniteConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	protected String id;
	protected int revision = 1;
	protected String appId;
	protected String tenantId;
	protected String engineName;
	protected String taskList;
	protected int onlyTitle = 0;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRevision() {
		return revision;
	}

	public void setRevision(int revision) {
		this.revision = revision;
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
