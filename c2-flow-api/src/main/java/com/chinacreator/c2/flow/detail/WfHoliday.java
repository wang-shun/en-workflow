package com.chinacreator.c2.flow.detail;

import java.io.Serializable;

/**
 * 节假日对象
 * @author yicheng.yang
 *
 */
public class WfHoliday implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String holiday;
	
	private String yHoliday;
	
	private String mHoliday;
	
	public String getMHoliday() {
		return mHoliday;
	}

	public void setMHoliday(String mHoliday) {
		this.mHoliday = mHoliday;
	}

	private String tenantId;
	
	public String getHoliday() {
		return holiday;
	}

	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}

	public String getYHoliday() {
		return yHoliday;
	}

	public void setYHoliday(String yHoliday) {
		this.yHoliday = yHoliday;
	}


	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	@Override
	public String toString(){
		return "WfHoliday [holiday=" + holiday
				+ ", yHoliday=" + yHoliday + ", mHoliday="
				+ mHoliday + ", tenantId=" + tenantId + "]";
	}

}
