package com.chinacreator.c2.flow.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.db.PersistentObject;

public class WfModuleDelegateEntity implements PersistentObject, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected String delegateId;

	protected String designatorId;
	
	protected String designatorName;
	
	protected String designeeId;
	
	protected String designeeName;
	
	protected String delegateType;
	
	protected Date delegateStartTime;
	
	protected Date delegateEndTime;
	
	protected String delegateStat;
	
	protected String delegateRange;
	
	protected Date confirmTime;
	
	protected String appId;
	
	protected String tenantId;
	
	public String getDelegateId() {
		return delegateId;
	}

	public void setDelegateId(String delegateId) {
		this.delegateId = delegateId;
	}

	public String getDesignatorId() {
		return designatorId;
	}

	public void setDesignatorId(String designatorId) {
		this.designatorId = designatorId;
	}

	public String getDesignatorName() {
		return designatorName;
	}

	public void setDesignatorName(String designatorName) {
		this.designatorName = designatorName;
	}

	public String getDesigneeId() {
		return designeeId;
	}

	public void setDesigneeId(String designeeId) {
		this.designeeId = designeeId;
	}

	public String getDesigneeName() {
		return designeeName;
	}

	public void setDesigneeName(String designeeName) {
		this.designeeName = designeeName;
	}

	public String getDelegateType() {
		return delegateType;
	}

	public void setDelegateType(String delegateType) {
		this.delegateType = delegateType;
	}

	public Date getDelegateStartTime() {
		return delegateStartTime;
	}

	public void setDelegateStartTime(Date delegateStartTime) {
		this.delegateStartTime = delegateStartTime;
	}

	public Date getDelegateEndTime() {
		return delegateEndTime;
	}

	public void setDelegateEndTime(Date delegateEndTime) {
		this.delegateEndTime = delegateEndTime;
	}

	public String getDelegateStat() {
		return delegateStat;
	}

	public void setDelegateStat(String delegateStat) {
		this.delegateStat = delegateStat;
	}

	public String getDelegateRange() {
		return delegateRange;
	}

	public void setDelegateRange(String delegateRange) {
		this.delegateRange = delegateRange;
	}

	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
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

	@Override
	public String getId() {
		return delegateId;
	}

	@Override
	public void setId(String id) {
		this.delegateId = id;
	}

	@Override
	public Object getPersistentState() {
		 Map<String, Object> persistentState = new HashMap<String, Object>();
		
		 persistentState.put("id", this.delegateId);
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
