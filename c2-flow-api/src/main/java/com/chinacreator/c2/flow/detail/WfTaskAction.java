package com.chinacreator.c2.flow.detail;

import java.io.Serializable;

/**
 * 任务操作类型
 * @author Administrator
 *
 */
public enum WfTaskAction implements Serializable {
	/**
	 * 领取任务
	 */
	CLAIM("claim"),
	/**
	 * 完成任务
	 */
	COMPLETE("complete"),
	/**
	 * 领取并完成任务
	 */
	CLAIM_COMPLETE("claim_complete"),
	/**
	 * 制定用户代理任务
	 */
	DELEGATE("delegate"),
	/**
	 * 回绝代理任务
	 */
	RESOLVE("resolve");
	private String action;

	private WfTaskAction(String action_) {
		this.action = action_;
	}

	@Override
	public String toString() {
		return String.valueOf(this.action);
	}
}
