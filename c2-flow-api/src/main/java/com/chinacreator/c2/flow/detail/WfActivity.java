package com.chinacreator.c2.flow.detail;

import java.io.Serializable;
import java.util.Map;

/**
 * 活动对象，与任务对象有区别，比如可以是网关节点
 * @author minghua.guo
 *
 */
public class WfActivity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 任务定义id
	 */
	private String id;

	/**
	 * 变量
	 */
	private Map<String, Object> variables;

	/**
	 * 属性
	 */
	private Map<String, Object> properties;
	/**
	 * X坐标
	 */
	private int x = -1;
	/**
	 * Y坐标
	 */
	private int y = -1;
	/**
	 * 宽度
	 */
	private int width = -1;
	/**
	 * 高度
	 */
	private int height = -1;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, Object> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}
}
