package com.chinacreator.c2.flow.detail;

import java.io.Serializable;

public enum WfDelegateRange implements Serializable {


	/**
	 * 所有事项
	 */
	ALL_MODULE("0"),
	/**
	 * 个别事项
	 */
	SOME_MODULE("1");

	private String action;

	private WfDelegateRange(String action_) {
		this.action = action_;
	}

	@Override
	public String toString() {
		return String.valueOf(this.action);
	}

}
