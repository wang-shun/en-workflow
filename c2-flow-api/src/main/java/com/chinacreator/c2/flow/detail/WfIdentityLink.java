package com.chinacreator.c2.flow.detail;

import java.io.Serializable;

/**
 * 流程参与者
 * @author minghua.guo
 *
 */
public class WfIdentityLink implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 用户组id
	 */
	private String groupId;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 任务id，有值说明是任务参与者
	 */
	private String taskId;
	/**
	 * 流程定义id
	 */
	private String processDefinitionId;
	/**
	 * 流程实例id，有值说明是流程参与者
	 */
	private String processInstanceId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
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
	@Override
	public String toString() {
		return "WfIdentityLink [userId=" + userId + ", groupId=" + groupId
				+ ", type=" + type + ", taskId=" + taskId
				+ ", processDefinitionId=" + processDefinitionId
				+ ", processInstanceId=" + processInstanceId + "]";
	}
	
}
