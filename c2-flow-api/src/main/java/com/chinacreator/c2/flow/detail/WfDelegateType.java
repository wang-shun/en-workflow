package com.chinacreator.c2.flow.detail;

import java.io.Serializable;

public enum WfDelegateType implements Serializable{


	/**
	 * 上级委托
	 */
	FROM_UP("1"),
	/**
	 * 委托别人
	 */
	TO_OTHER("2");

	private String action;

	private WfDelegateType(String action_) {
		this.action = action_;
	}

	@Override
	public String toString() {
		return String.valueOf(this.action);
	}

}
