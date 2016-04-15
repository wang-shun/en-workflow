package com.chinacreator.c2.flow.detail;

import java.io.Serializable;

/**
 * 候选组对象
 * @author hushowly
 *
 */
public class ChooseGroup implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户组id
	 */
	private String id;
	
	/**
	 * 用户组名
	 */
	private String name;
	
	
	/**
	 * 用户组显示名
	 */
	private String displayName;
	
	
	/**
	 * 类型
	 */
	private String type;
	
	/**
	 * 父节点
	 */
	private String parentId;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	

	
}
