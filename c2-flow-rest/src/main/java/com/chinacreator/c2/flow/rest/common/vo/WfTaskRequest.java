package com.chinacreator.c2.flow.rest.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import org.activiti.engine.task.DelegationState;

/**
 * @author hushowly
 */
@ApiModel(value = "WfTaskRequest", description = "任务信息请求参数")
public class WfTaskRequest {

	@ApiModelProperty("任务所有者")
	private String owner;
	@ApiModelProperty("任务处理人")
	private String assignee;
	@ApiModelProperty("任务委托状态")
	private DelegationState delegationState;
	@ApiModelProperty("任务名称")
	private String name;
	@ApiModelProperty("任务描述")
	private String description;
	@ApiModelProperty("任务过期时间")
	private Date dueDate;
	@ApiModelProperty("任务优先级")
	private Integer priority;
	@ApiModelProperty("父任务id")
	private String parentTaskId;
	@ApiModelProperty("任务类别")
	private String category;

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public DelegationState getDelegationState() {
		return delegationState;
	}

	public void setDelegationState(DelegationState delegationState) {
		this.delegationState = delegationState;
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

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getParentTaskId() {
		return parentTaskId;
	}

	public void setParentTaskId(String parentTaskId) {
		this.parentTaskId = parentTaskId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
