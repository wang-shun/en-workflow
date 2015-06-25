package com.chinacreator.c2.flow.detail;

import java.io.Serializable;

public enum WfDelegateStatus implements Serializable{


	/**
	 * 等待确认
	 */
	WAITING("1"),
	/**
	 * 委托中
	 */
	DELEGATING("2"),
	/**
	 * 委托结束
	 */
	DELEGATE_FINISH("3"),
	/**
	 * 拒绝委托
	 */
	RESOLVED("4");
	private String action;

	private WfDelegateStatus(String action_) {
		this.action = action_;
	}

	@Override
	public String toString() {
		return String.valueOf(this.action);
	}

}
