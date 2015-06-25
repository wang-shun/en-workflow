package com.chinacreator.c2.flow.detail;

import java.io.Serializable;

/**
 * 表单属性
 * 
 * @author minghua.guo
 * 
 */
public class WfFormProperty implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 属性id
	 */
	private String id;

	/**
	 * 属性名称
	 */
	private String name;

	/**
	 * 类型
	 */
	private String type;

	/**
	 * 属性值
	 */
	private String value;

	/**
	 * 是否只读
	 */
	private boolean isReadable;

	/**
	 * 是否可写
	 */
	private boolean isWritable;

	/**
	 * 是否必须
	 */
	private boolean isRequired;

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isReadable() {
		return isReadable;
	}

	public void setReadable(boolean isReadable) {
		this.isReadable = isReadable;
	}

	public boolean isWritable() {
		return isWritable;
	}

	public void setWritable(boolean isWritable) {
		this.isWritable = isWritable;
	}

	public boolean isRequired() {
		return isRequired;
	}

	public void setRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}
}
