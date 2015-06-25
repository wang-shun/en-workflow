package com.chinacreator.c2.flow.detail;

import java.io.Serializable;

/**
 * 流程操作结果
 * 
 * @author minghua.guo
 * 
 */
public class WfResult implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 流程实例ID
	 */
	private String processInstanceId;

	/**
	 * 下一步任务ID，多个以逗号分隔
	 */
	private String nextTaskId;

	/**
	 * 操作结果："200"--成功, "300"--参数不正确, "400"--操作失败, "404"--操作对象不存在
	 */
	private String result;

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getNextTaskId() {
		return nextTaskId;
	}

	public void setNextTaskId(String nextTaskId) {
		this.nextTaskId = nextTaskId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "WfStartResult [processInstanceId=" + processInstanceId
				+ ", nextTaskId=" + nextTaskId + ", result=" + result + "]";
	}
}
