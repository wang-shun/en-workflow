package com.chinacreator.c2.flow.detail;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 历史流程实例对象
 * 
 * @author minghua.guo
 * 
 */
public class WfHistoricProcessInstance implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 流程实例id
	 */
	private String id;
	/**
	 * 业务id
	 */
	private String businessKey;
	/**
	 * 流程定义id
	 */
	private String processDefinitionId;
	/**
	 * 开始时间
	 */
	private Date startTime;
	/**
	 * 结束时间
	 */
	private Date endTime;
	/**
	 * 持续时间
	 */
	private Long durationInMillis;
	/**
	 * 删除原因
	 */
	private String deleteReason;
	/**
	 * 上级流程实例id
	 */
	private String superProcessInstanceId;
	/**
	 * 启动用户id
	 */
	private String startUserId;
	/**
	 * 启动活动id
	 */
	private String startActivityId;
	/**
	 * 流程变量
	 */
	private Map<String, Object> processVariables;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBusinessKey() {
		return businessKey;
	}
	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}
	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
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
	public Long getDurationInMillis() {
		return durationInMillis;
	}
	public void setDurationInMillis(Long durationInMillis) {
		this.durationInMillis = durationInMillis;
	}
	public String getDeleteReason() {
		return deleteReason;
	}
	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}
	public String getSuperProcessInstanceId() {
		return superProcessInstanceId;
	}
	public void setSuperProcessInstanceId(String superProcessInstanceId) {
		this.superProcessInstanceId = superProcessInstanceId;
	}
	public String getStartUserId() {
		return startUserId;
	}
	public void setStartUserId(String startUserId) {
		this.startUserId = startUserId;
	}
	public String getStartActivityId() {
		return startActivityId;
	}
	public void setStartActivityId(String startActivityId) {
		this.startActivityId = startActivityId;
	}
	public Map<String, Object> getProcessVariables() {
		return processVariables;
	}
	public void setProcessVariables(Map<String, Object> processVariables) {
		this.processVariables = processVariables;
	}
	@Override
	public String toString() {
		return "WfHistoricProcessInstance [id=" + id + ", businessKey="
				+ businessKey + ", processDefinitionId=" + processDefinitionId
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", durationInMillis=" + durationInMillis + ", deleteReason="
				+ deleteReason + ", superProcessInstanceId="
				+ superProcessInstanceId + ", startUserId=" + startUserId
				+ ", startActivityId=" + startActivityId
				+ ", processVariables=" + processVariables + "]";
	}
}
