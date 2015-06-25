package com.chinacreator.c2.flow.detail;

import java.io.Serializable;

/**
 * 流程事项
 * @author Administrator
 *
 */
public class WfModuleBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 事项ID
	 */
	private String id;

	/**
	 * 事项名称
	 */
	private String name;

	/**
	 * 父事项ID
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

	
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String toString(){
		return "WfModuleBean [id="+id+",name="+name+",parentId="+parentId+"]";
	}

}
