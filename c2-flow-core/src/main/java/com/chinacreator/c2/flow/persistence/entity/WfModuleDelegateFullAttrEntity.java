package com.chinacreator.c2.flow.persistence.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.db.PersistentObject;

public class WfModuleDelegateFullAttrEntity extends WfModuleDelegateEntity implements PersistentObject, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String mouduleId;
	
	private String mouduleName;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	public Object getPersistentState() {
		 Map<String, Object> persistentState = new HashMap<String, Object>();
		
		 persistentState.put("id", id);
		 persistentState.put("mouduleId", mouduleId);
		 persistentState.put("mouduleName", mouduleName);
		 
		 persistentState.put("designatorId", designatorId);
		 
		 persistentState.put("designatorName", designatorName);
		 persistentState.put("designeeId", designeeId);
		 persistentState.put("designeeName", designeeName);
		 
		 persistentState.put("delegateType", delegateType);
		 persistentState.put("delegateStartTime", delegateStartTime);
		 persistentState.put("delegateEndTime", delegateEndTime);
		 
		 persistentState.put("delegateStat", delegateStat);
		 persistentState.put("delegateRange", delegateRange);
		 persistentState.put("confirmTime", confirmTime);
		 
		 persistentState.put("appId", appId);
		 persistentState.put("tenantId", tenantId);
		 return persistentState;
		 
	}

}
