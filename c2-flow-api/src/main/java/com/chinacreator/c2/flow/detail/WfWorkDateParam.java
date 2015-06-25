package com.chinacreator.c2.flow.detail;

import java.util.Date;

public class WfWorkDateParam extends WfPageParam{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String beginDate;

	private String endDate;

	private String amEndTime;
	private String amBeginTime;

	private String pmEndTime;
	private String pmBeginTime;

	private String workId;
	private String remark;
	private Date lastModifyTime;
	private String tenantId;
	
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getAmEndTime() {
		return amEndTime;
	}
	public void setAmEndTime(String amEndTime) {
		this.amEndTime = amEndTime;
	}
	public String getAmBeginTime() {
		return amBeginTime;
	}
	public void setAmBeginTime(String amBeginTime) {
		this.amBeginTime = amBeginTime;
	}
	public String getPmEndTime() {
		return pmEndTime;
	}
	public void setPmEndTime(String pmEndTime) {
		this.pmEndTime = pmEndTime;
	}
	public String getPmBeginTime() {
		return pmBeginTime;
	}
	public void setPmBeginTime(String pmBeginTime) {
		this.pmBeginTime = pmBeginTime;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	@Override
	public String toString(){
		return "WfWorkDateParam [workId=" + workId+ ", beginDate=" + beginDate 
				+ ", endDate=" + endDate 
				+ ", amBeginTime=" + amBeginTime 
				+ ", amEndTime=" + amEndTime 
				+ ", pmBeginTime=" + pmBeginTime 
				+ ", pmEndTime=" + pmEndTime 
				+ ", remark=" + remark 
				+ ", lastModifyTime=" + lastModifyTime 
				+ ", tenantId=" + tenantId 
				+ "]";
	}

}
