package com.chinacreator.c2.flow.rest.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import com.chinacreator.c2.flow.detail.WfResult;

/**
 * 流程操作结果
 * @author hushowly
 * 
 */
@ApiModel(value="WfActionResult",description="流程操作结果")
public class WfActionResult implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public WfActionResult() {
		// TODO Auto-generated constructor stub
	}
	
	
	public WfActionResult(WfResult wfResult) {
		this.nextTaskId=wfResult.getNextTaskId();
		this.processInstanceId=wfResult.getProcessInstanceId();
	}

	/**
	 * 流程实例ID
	 */
	@ApiModelProperty("当前操作流程实例")
	private String processInstanceId;

	/**
	 * 下一步任务ID，多个以逗号分隔
	 */
	@ApiModelProperty("下一步任务id")
	private String nextTaskId;

	
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

	@Override
	public String toString() {
		return "WfStartResult [processInstanceId=" + processInstanceId
				+ ", nextTaskId=" + nextTaskId + "]";
	}
}
