package com.chinacreator.c2.flow.detail;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论对象
 * 
 * @author minghua.guo
 * 
 */
public class WfComment implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String TYPE_EVENT = "event";
	public static final String TYPE_COMMENT = "comment";

	/**
	 * 评论id
	 */
	private String id;
	/**
	 * 评论类型，为event-表示事件
	 */
	private String type;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 评论时间
	 */
	private Date time;
	/**
	 * 任务id
	 */
	private String taskId;
	/**
	 * 流程实例id
	 */
	private String processInstanceId;
	/**
	 * 评论内容
	 */
	private String message;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "WfComment [id=" + id + ", type=" + type + ", userId=" + userId
				+ ", time=" + time + ", taskId=" + taskId
				+ ", processInstanceId=" + processInstanceId + ", message="
				+ message + "]";
	}
}
