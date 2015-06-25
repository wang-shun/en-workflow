package com.chinacreator.c2.flow.detail;

import java.io.InputStream;
import java.io.Serializable;

/**
 * 流程附件对象
 * 
 * @author minghua.guo
 * 
 */
public class WfAttachment implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 附件id
	 */
	private String id;
	/**
	 * 附件名称
	 */
	private String name;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 附件类型
	 */
	private String type;
	/**
	 * 任务id
	 */
	private String taskId;
	/**
	 * 流程实例id
	 */
	private String processInstanceId;
	/**
	 * 附件url
	 */
	private String url;
	/**
	 * 附件内容id
	 */
	private String contentId;
	/**
	 * 附件内容
	 */
	private InputStream content;
	/**
	 * 用户id
	 */
	private String userId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public InputStream getContent() {
		return content;
	}

	public void setContent(InputStream content) {
		this.content = content;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "WfAttachment [id=" + id + ", name=" + name + ", description="
				+ description + ", type=" + type + ", taskId=" + taskId
				+ ", processInstanceId=" + processInstanceId + ", url=" + url
				+ ", contentId=" + contentId + ", content=" + content
				+ ", userId=" + userId + "]";
	}

}
