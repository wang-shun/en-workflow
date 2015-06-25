package com.chinacreator.c2.flow.persistence.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.db.PersistentObject;

public class WfHolidayEntity implements PersistentObject, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String holiday;
	
	private String yHoliday;
	
	private String mHoliday;
	
	private String tenantId;
	
	public String getHoliday() {
		return holiday;
	}

	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}

	public String getyHoliday() {
		return yHoliday;
	}

	public void setyHoliday(String yHoliday) {
		this.yHoliday = yHoliday;
	}

	public String getmHoliday() {
		return mHoliday;
	}

	public void setmHoliday(String mHoliday) {
		this.mHoliday = mHoliday;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	@Override
	public String getId() {
		return holiday + yHoliday + mHoliday + tenantId;
	}

	@Override
	public void setId(String id) {
		id = holiday + yHoliday + mHoliday + tenantId;
	}

	@Override
	public Object getPersistentState() {
		Map<String, Object> persistentState = new HashMap<String, Object>();
		
		 persistentState.put("id", holiday + yHoliday + mHoliday + tenantId);
		 persistentState.put("holiday", holiday);
		 
		 persistentState.put("yHoliday", yHoliday);
		 persistentState.put("mHoliday", mHoliday);
		 persistentState.put("tenantId", tenantId);
		 
		 return persistentState;
	}

}
