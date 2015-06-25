package com.chinacreator.c2.flow.detail;

import java.util.Date;

public class WfModuleDelegateParam extends WfPageParam{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String designatorId;
	
	private String designatorName;
	
	private String designeeId;
	
	private String designeeName;
	
	private String delegateType;
	
	private Date delegateStartTimeBegine;
	
	private Date delegateStartTimeEnd;
	
	private Date delegateEndTimeBegine;
	
	private Date delegateEndTimeEnd;
	
	private String delegateStat;
	
	private String delegateRange;
	
	private Date confirmTime;
	
	private String appId;

	private String delegateId;
	
	private String tenantId;

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
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


	public Date getDelegateStartTimeBegine() {
		return delegateStartTimeBegine;
	}

	public void setDelegateStartTimeBegine(Date delegateStartTimeBegine) {
		this.delegateStartTimeBegine = delegateStartTimeBegine;
	}

	public Date getDelegateStartTimeEnd() {
		return delegateStartTimeEnd;
	}

	public void setDelegateStartTimeEnd(Date delegateStartTimeEnd) {
		this.delegateStartTimeEnd = delegateStartTimeEnd;
	}

	public Date getDelegateEndTimeBegine() {
		return delegateEndTimeBegine;
	}

	public void setDelegateEndTimeBegine(Date delegateEndTimeBegine) {
		this.delegateEndTimeBegine = delegateEndTimeBegine;
	}

	public Date getDelegateEndTimeEnd() {
		return delegateEndTimeEnd;
	}

	public void setDelegateEndTimeEnd(Date delegateEndTimeEnd) {
		this.delegateEndTimeEnd = delegateEndTimeEnd;
	}

	

}
