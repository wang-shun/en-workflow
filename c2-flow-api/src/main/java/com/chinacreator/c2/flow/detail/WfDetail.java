package com.chinacreator.c2.flow.detail;

import java.io.Serializable;
import java.util.Date;

/**
 * 明细对象
 * 
 * @author Administrator
 * 
 */
public class WfDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 明细id
	 */
	private String id;
	/**
	 * 流程实例id
	 */
	private String processInstanceId;
	/**
	 * 活动实例id
	 */
	private String activityInstanceId;
	/**
	 * 任务id
	 */
	private String taskId;
	/**
	 * 分支id
	 */
	private String executionId;
	/**
	 * 时间
	 */
	private Date time;
	/**
	 * 明细类型
	 */
	private String detailType;
	/**
	 * 属性id
	 */
	private String propertyId;
	/**
	 * 属性值
	 */
	private String propertyValue;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getActivityInstanceId() {
		return activityInstanceId;
	}

	public void setActivityInstanceId(String activityInstanceId) {
		this.activityInstanceId = activityInstanceId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "WfHistoricDetail [id=" + id + ", processInstanceId="
				+ processInstanceId + ", activityInstanceId="
				+ activityInstanceId + ", taskId=" + taskId + ", executionId="
				+ executionId + ", time=" + time + "]";
	}

	public String getDetailType() {
		return detailType;
	}

	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}

	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}
}
