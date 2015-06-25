package com.chinacreator.c2.flow.detail;

import java.io.Serializable;

public class WfUniteColumn implements Serializable {
	private static final long serialVersionUID = 1L;

	protected String id;
	protected int revision = 1;
	protected String configId; 
	protected String columnId; 
	protected String columnName; 
	protected int isShow; 
	protected int isTitle;
	protected int sn;
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
	public String getConfigId() {
		return configId;
	}
	public void setConfigId(String configId) {
		this.configId = configId;
	}
	public String getColumnId() {
		return columnId;
	}
	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public int getIsShow() {
		return isShow;
	}
	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}
	public int getIsTitle() {
		return isTitle;
	}
	public void setIsTitle(int isTitle) {
		this.isTitle = isTitle;
	}
	public int getSn() {
		return sn;
	}
	public void setSn(int sn) {
		this.sn = sn;
	}
}
