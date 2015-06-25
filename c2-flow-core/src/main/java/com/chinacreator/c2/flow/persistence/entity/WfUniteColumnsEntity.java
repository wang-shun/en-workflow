package com.chinacreator.c2.flow.persistence.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.db.HasRevision;
import org.activiti.engine.impl.db.PersistentObject;

public class WfUniteColumnsEntity implements HasRevision, PersistentObject,
		Serializable {

	private static final long serialVersionUID = 1L;

	protected String id;
	protected int revision = 1;
	protected String configId; 
	protected String columnId; 
	protected String columnName; 
	protected int isShow = 0; 
	protected int isTitle = 0;
	protected int sn = 100;
	
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
	    persistentState.put("id", this.id);
	    persistentState.put("configId", this.configId);
	    persistentState.put("columnId", columnId);
	    persistentState.put("columnName", this.columnName);
	    persistentState.put("isShow", this.isShow);
	    persistentState.put("isTitle", isTitle);
	    persistentState.put("sn", this.sn);
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
