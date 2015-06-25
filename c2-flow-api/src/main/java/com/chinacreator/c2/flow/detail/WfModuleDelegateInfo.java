package com.chinacreator.c2.flow.detail;

import java.io.Serializable;

public class WfModuleDelegateInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String delegateId;
	
	private String mouduleId;
	
	private String mouduleName;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

}
