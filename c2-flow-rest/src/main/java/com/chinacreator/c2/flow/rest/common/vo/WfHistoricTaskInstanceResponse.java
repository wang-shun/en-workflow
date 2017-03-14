package com.chinacreator.c2.flow.rest.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author hushowly
 */
@ApiModel(value="WfHistoricTaskInstanceResponse",description="历史任务信息")
public class WfHistoricTaskInstanceResponse {
  @ApiModelProperty("任务id")
  protected String id;
  @ApiModelProperty("任务所在流程定义id")
  protected String processDefinitionId;
  @ApiModelProperty("任务所在流程实例id")
  protected String processInstanceId;
  @ApiModelProperty("任务执行id")
  protected String executionId;
  @ApiModelProperty("任务名称")
  protected String name;
  @ApiModelProperty("任务描述")
  protected String description;
  @ApiModelProperty("任务删除原因")
  protected String deleteReason;
  @ApiModelProperty("任务所有者")
  protected String owner;
  @ApiModelProperty("任务处理人")
  protected String assignee;
  @ApiModelProperty("任务开始时间")
  protected Date startTime;
  @ApiModelProperty("任务结束时间")
  protected Date endTime;
  @ApiModelProperty("任务花费毫秒数")
  protected Long durationInMillis;
  @ApiModelProperty("任务花费毫秒数")
  protected Long workTimeInMillis;
  @ApiModelProperty("任务签收时间")
  protected Date claimTime;
  @ApiModelProperty("任务所在环节定义key")
  protected String taskDefinitionKey;
  @ApiModelProperty("任务表单key")
  protected String formKey;
  @ApiModelProperty("任务优先级")
  protected Integer priority;
  @ApiModelProperty("任务过期时间")
  protected Date dueDate;
  @ApiModelProperty("父任务id")
  protected String parentTaskId;
  @ApiModelProperty("任务自定义变量集合")
  protected List<WfRestVariable> variables = new ArrayList<WfRestVariable>();
  @ApiModelProperty("租户")
  protected String tenantId;
  @ApiModelProperty("业务类别")
  protected String category; 
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
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
  public String getExecutionId() {
    return executionId;
  }
  public void setExecutionId(String executionId) {
    this.executionId = executionId;
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
  public String getDeleteReason() {
    return deleteReason;
  }
  public void setDeleteReason(String deleteReason) {
    this.deleteReason = deleteReason;
  }
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
  public Long getWorkTimeInMillis() {
    return workTimeInMillis;
  }
  public void setWorkTimeInMillis(Long workTimeInMillis) {
    this.workTimeInMillis = workTimeInMillis;
  }
  public Date getClaimTime() {
    return claimTime;
  }
  public void setClaimTime(Date claimTime) {
    this.claimTime = claimTime;
  }
  public String getTaskDefinitionKey() {
    return taskDefinitionKey;
  }
  public void setTaskDefinitionKey(String taskDefinitionKey) {
    this.taskDefinitionKey = taskDefinitionKey;
  }
  public String getFormKey() {
    return formKey;
  }
  public void setFormKey(String formKey) {
    this.formKey = formKey;
  }
  public Integer getPriority() {
    return priority;
  }
  public void setPriority(Integer priority) {
    this.priority = priority;
  }
  public Date getDueDate() {
    return dueDate;
  }
  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }
  public String getParentTaskId() {
    return parentTaskId;
  }
  public void setParentTaskId(String parentTaskId) {
    this.parentTaskId = parentTaskId;
  }
  public List<WfRestVariable> getVariables() {
    return variables;
  }
  public void setVariables(List<WfRestVariable> variables) {
    this.variables = variables;
  }
  public void addVariable(WfRestVariable variable) {
    variables.add(variable);
  }
  public void setTenantId(String tenantId) {
	  this.tenantId = tenantId;
  }
  public String getTenantId() {
	  return tenantId;
  }
  public void setCategory(String category) {
	  this.category = category;
  }
  public String getCategory() {
	  return category;
  }
}
