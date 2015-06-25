package com.chinacreator.c2.flow.detail;

import java.io.Serializable;

/**
 * 用户组对象
 * @author minghua.guo
 *
 */
public class WfGroup implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户组id
	 */
	private String id;
	/**
	 * 用户组名称
	 */
	private String name;
	/**
	 * 类型
	 */
	private String type;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
