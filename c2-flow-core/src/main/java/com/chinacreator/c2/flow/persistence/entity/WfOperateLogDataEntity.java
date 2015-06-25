package com.chinacreator.c2.flow.persistence.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.db.PersistentObject;

public class WfOperateLogDataEntity implements PersistentObject, Serializable {
	private static final long serialVersionUID = 1L;
	private String logId;
	private Object[] argsObject;
	private String argsValue;
	private Object returnObject;
	private String returnValue;
	private String exception;

	@Override
	public String getId() {
		return logId;
	}

	@Override
	public void setId(String id) {
		this.logId = id;
	}

	@Override
	public Object getPersistentState() {
		Map<String, Object> persistentState = new HashMap<String, Object>();
		persistentState.put("id", this.logId);
		persistentState.put("argsObject", argsObject);
		persistentState.put("argsValue", this.argsValue);
		persistentState.put("returnObject", this.returnObject);
		persistentState.put("returnValue", returnValue);
		persistentState.put("exception", exception);
		return persistentState;
	}

	public Object[] getArgsObject() {
		return argsObject;
	}

	public void setArgsObject(Object[] argsObject) {
		this.argsObject = argsObject;
	}

	public String getArgsValue() {
		return argsValue;
	}

	public void setArgsValue(String argsValue) {
		this.argsValue = argsValue;
	}

	public Object getReturnObject() {
		return returnObject;
	}

	public void setReturnObject(Object returnObject) {
		this.returnObject = returnObject;
	}

	public String getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

}
