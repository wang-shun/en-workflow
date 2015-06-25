package com.chinacreator.c2.flow.detail;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 流程实例对象
 * @author minghua.guo
 *
 */
public class WfProcessInstance implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 流程定义id
	 */
	private String processDefinitionId;

	/**
	 * 流程实例id
	 */
	private String processInstanceId;
	/**
	 * 业务id
	 */
	private String businessKey;

	/**
	 * 启动时间
	 */
	private Date startTime;

	/**
	 * 结束时间
	 */
	private Date endTime;
	/**
	 * 活动id
	 */
	private String activityId;
	/**
	 * 是否挂起
	 */
	private boolean suspended = false;
	/**
	 * 是否结束
	 */
	private boolean ended = false;

	/**
	 * 流程参数
	 */
	private Map<String, Object> processVariables = new HashMap<String, Object>();

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public boolean isSuspended() {
		return suspended;
	}

	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}

	public boolean isEnded() {
		return ended;
	}

	public void setEnded(boolean ended) {
		this.ended = ended;
	}

	public Map<String, Object> getProcessVariables() {
		return processVariables;
	}

	public void setProcessVariables(Map<String, Object> processVariables) {
		this.processVariables = processVariables;
	}
}
