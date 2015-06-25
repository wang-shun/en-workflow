package com.chinacreator.c2.flow.detail;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class WfModuleDelegate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String delegateId;

	private String designatorId;
	
	private String designatorName;
	
	private String designeeId;
	
	private String designeeName;
	
	private String delegateType;
	
	private Date delegateStartTime;
	
	private Date delegateEndTime;
	
	private String delegateStat;
	
	private String delegateRange;
	
	private Date confirmTime;
	
	private String appId;
	
	private String tenantId;
	
	private List<WfModuleDelegateInfo> wfModuleDelegateInfos = null;

	private String moduleIds;
	
	private String moduleNames;
	
	public String getModuleIds() {
		return moduleIds;
	}

	public void setModuleIds(String moduleIds) {
		this.moduleIds = moduleIds;
	}

	public String getModuleNames() {
		return moduleNames;
	}

	public void setModuleNames(String moduleNames) {
		this.moduleNames = moduleNames;
	}


	public List<WfModuleDelegateInfo> getWfModuleDelegateInfos() {
		return wfModuleDelegateInfos;
	}

	public void setWfModuleDelegateInfos(
			List<WfModuleDelegateInfo> wfModuleDelegateInfos) {
		this.wfModuleDelegateInfos = wfModuleDelegateInfos;
	}

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

}
