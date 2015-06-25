package com.chinacreator.c2.flow.persistence.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.db.PersistentObject;

public class WfUniteHisTaskExtendEntity implements PersistentObject,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String uniteTaskHisId;
	private String extFieldKey;
	private String extFieldValue;
	private String extFieldType;
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}


	public String getUniteTaskHisId() {
		return uniteTaskHisId;
	}

	public void setUniteTaskHisId(String uniteTaskHisId) {
		this.uniteTaskHisId = uniteTaskHisId;
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

	
	@Override
	public Object getPersistentState() {
		Map<String, Object> persistentState = new HashMap<String, Object>();

		persistentState.put("id", this.id);
		persistentState.put("unitetaskhisid", this.uniteTaskHisId);
		persistentState.put("extfieldkey", this.extFieldKey);
		persistentState.put("extfieldvalue", this.extFieldValue);
		persistentState.put("extfieldtype", this.extFieldType);

		return persistentState;
	}

}
