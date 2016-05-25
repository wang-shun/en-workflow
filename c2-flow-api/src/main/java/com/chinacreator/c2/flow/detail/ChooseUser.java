package com.chinacreator.c2.flow.detail;

import java.io.Serializable;

/**
 * 候选人对象
 * @author hushowly
 *
 */
public class ChooseUser implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	private String id;
	
	/**
	 * 用户名
	 */
	private String name;
	
	
	/**
	 * 用户显示名
	 */
	private String displayName;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDisplayName() {
		return displayName;
	}


	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}



	
	

	
	
}
