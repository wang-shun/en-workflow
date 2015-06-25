package com.chinacreator.c2.flow.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.db.PersistentObject;

public class WfOperateLogEntity implements PersistentObject, Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String className;
	private String methodName;
	private String methodAlias;
	private String result;
	private Date callTime;
	private long useTime;
	private String appId;
	private String tenantId; 
	private String engineName; 
	private String userId;
	private String ip;
	
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
		persistentState.put("className", className);
		persistentState.put("methodName", this.methodName);
		persistentState.put("methodAlias", this.methodAlias);
		persistentState.put("result", result);
		persistentState.put("callTime", callTime);
		persistentState.put("useTime", useTime);
		persistentState.put("appId", appId);
		persistentState.put("tenantId", tenantId);
		persistentState.put("engineName", engineName);
		persistentState.put("userId", userId);
		persistentState.put("ip", ip);
		return persistentState;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodAlias() {
		return methodAlias;
	}

	public void setMethodAlias(String methodAlias) {
		this.methodAlias = methodAlias;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getCallTime() {
		return callTime;
	}

	public void setCallTime(Date callTime) {
		this.callTime = callTime;
	}

	public long getUseTime() {
		return useTime;
	}

	public void setUseTime(long useTime) {
		this.useTime = useTime;
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

	public String getEngineName() {
		return engineName;
	}

	public void setEngineName(String engineName) {
		this.engineName = engineName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
