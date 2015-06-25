package com.chinacreator.c2.flow.persistence.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.db.PersistentObject;

public class WfUniteRunTaskExtendEntity implements PersistentObject,
		Serializable {

	private String id;
	private String uniteTaskId;
	private String extFieldKey;
	private String extFieldValue;
	private String extFieldType;

	private static final long serialVersionUID = 1L;

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
		persistentState.put("unitetaskid", this.uniteTaskId);
		persistentState.put("extfieldkey", this.extFieldKey);
		persistentState.put("extfieldvalue", this.extFieldValue);
		persistentState.put("extfieldtype", this.extFieldType);

		return persistentState;
	}

	public String getUniteTaskId() {
		return uniteTaskId;
	}

	public void setUniteTaskId(String uniteTaskId) {
		this.uniteTaskId = uniteTaskId;
	}

	public String getExtFieldKey() {
		return extFieldKey;
	}

	public void setExtFieldKey(String extFieldKey) {
		this.extFieldKey = extFieldKey;
	}

	public String getExtFieldValue() {
		return extFieldValue;
	}

	public void setExtFieldValue(String extFieldValue) {
		this.extFieldValue = extFieldValue;
	}

	public String getExtFieldType() {
		return extFieldType;
	}

	public void setExtFieldType(String extFieldType) {
		this.extFieldType = extFieldType;
	}

}
