package com.chinacreator.c2.flow.detail;

import java.io.Serializable;

/**
 * 流程、任务变量
 * @author Administrator
 *
 */
public class WfVariable implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 变量类型枚举
	 * @author Administrator
	 *
	 */
	public enum VariableType {
		STRING, INTEGER, BOOLEAN, SERIALIZABLE, NULL
	};

	/**
	 * 作用域枚举
	 * @author Administrator
	 *
	 */
	public enum VariableScope {
		LOCAL, GLOBAL
	};


	/**
	 * 变量名称
	 */
	private String name;
	/**
	 * 变量类型
	 */
	private VariableType variableType;
	/**
	 * 变量值
	 */
	private Object value;
	/**
	 * 作用域
	 */
	private VariableScope variableScope;


	public String getType() {
		String type = null;
		if (variableType != null) {
			type = variableType.name().toLowerCase();
		}
		return type;
	}

	public void setType(String type) {
		setVariableScope(getScopeFromString(type));
	}

	public static VariableScope getTypeFromString(String type) {
		if (type != null) {
			for (VariableScope s : VariableScope.values()) {
				if (s.name().equalsIgnoreCase(type)) {
					return s;
				}
			}
		} 
		return null;
	}
	
	public String getScope() {
		String scope = null;
		if (variableScope != null) {
			scope = variableScope.name().toLowerCase();
		}
		return scope;
	}

	public void setScope(String scope) {
		setVariableScope(getScopeFromString(scope));
	}

	public static VariableScope getScopeFromString(String scope) {
		if (scope != null) {
			for (VariableScope s : VariableScope.values()) {
				if (s.name().equalsIgnoreCase(scope)) {
					return s;
				}
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public VariableType getVariableType() {
		return variableType;
	}

	public void setVariableType(VariableType variableType) {
		this.variableType = variableType;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public VariableScope getVariableScope() {
		return variableScope;
	}

	public void setVariableScope(VariableScope variableScope) {
		this.variableScope = variableScope;
	}

	@Override
	public String toString() {
		return "WfVariable [name=" + name + ", variableType=" + variableType + ", value="
				+ value + ", variableScope=" + variableScope + "]";
	}
}
