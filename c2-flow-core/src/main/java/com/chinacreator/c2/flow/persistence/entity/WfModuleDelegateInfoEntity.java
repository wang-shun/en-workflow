package com.chinacreator.c2.flow.persistence.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.db.PersistentObject;

public class WfModuleDelegateInfoEntity implements PersistentObject, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String id;
	

	private String delegateId;
	
	private String mouduleId;
	
	private String mouduleName;
	
	public String getDelegateId() {
		return delegateId;
	}

	public void setDelegateId(String delegateId) {
		this.delegateId = delegateId;
	}

	public String getMouduleId() {
		return mouduleId;
	}

	public void setMouduleId(String mouduleId) {
		this.mouduleId = mouduleId;
	}

	public String getMouduleName() {
		return mouduleName;
	}

	public void setMouduleName(String mouduleName) {
		this.mouduleName = mouduleName;
	}

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
		 persistentState.put("delegateId", this.delegateId);
		 
		 persistentState.put("mouduleId", this.mouduleId);
		 persistentState.put("mouduleName", this.mouduleName);
		 
		 return persistentState;
	}

}
