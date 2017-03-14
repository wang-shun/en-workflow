package com.chinacreator.c2.flow.rest.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.activiti.engine.task.DelegationState;
import org.activiti.engine.task.Task;

/**
 * @author hushowly
 */
@ApiModel(value="WfTaskResponse", description="任务信息")
public class WfTaskResponse {

  @ApiModelProperty(value = "任务id")
  protected String id;
  @ApiModelProperty(value = "任务所有者")
  protected String owner;
  @ApiModelProperty(value = "任务处理人")
  protected String assignee;
  @ApiModelProperty(value = "任务代理状态")
  protected String delegationState;
  @ApiModelProperty(value = "任务名称")
  protected String name;
  @ApiModelProperty(value = "任务描述")
  protected String description;
  @ApiModelProperty(value = "任务创建时间")
  protected Date createTime;
  @ApiModelProperty(value = "任务过期时间")
  protected Date dueDate;
  @ApiModelProperty(value = "任务优先级")
  protected int priority;
  @ApiModelProperty(value = "任务是否挂起")
  protected boolean suspended;
  @ApiModelProperty(value = "任务环节定义key")
  protected String taskDefinitionKey;
  @ApiModelProperty(value = "租户")
  protected String tenantId;
  @ApiModelProperty(value = "类别")
  protected String category;
  
  // References to other resources
  @ApiModelProperty(value = "父任务id")
  protected String parentTaskId;
  @ApiModelProperty(value = "任务执行id")
  protected String executionId;
  @ApiModelProperty(value = "任务所在流程实例id")
  protected String processInstanceId;
  @ApiModelProperty(value = "任务所在流程定义id")
  protected String processDefinitionId;
  
  @ApiModelProperty(value = "当前任务的自定义变量集合")
  protected List<WfRestVariable> variables = new ArrayList<WfRestVariable>();
  
  public WfTaskResponse(Task task) {
    setId(task.getId());
    setOwner(task.getOwner());
    setAssignee(task.getAssignee());
    setDelegationState(getDelegationStateString(task.getDelegationState()));
    setName(task.getName());
    setDescription(task.getDescription());
    setCreateTime(task.getCreateTime());
    setDueDate(task.getDueDate());
    setPriority(task.getPriority());
    setSuspended(task.isSuspended());
    setTaskDefinitionKey(task.getTaskDefinitionKey());
    setParentTaskId(task.getParentTaskId());
    setExecutionId(task.getExecutionId());
    setCategory(task.getCategory());
    setProcessInstanceId(task.getProcessInstanceId());
    setProcessDefinitionId(task.getProcessDefinitionId());
    setTenantId(task.getTenantId());
  }
  
  protected String getDelegationStateString(DelegationState state) {
    String result = null;
    if(state != null) {
      result = state.toString().toLowerCase();
    }
    return result;
  }

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
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
  public String getDelegationState() {
    return delegationState;
  }
  public void setDelegationState(String delegationState) {
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
  public Date getCreateTime() {
    return createTime;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
  public Date getDueDate() {
    return dueDate;
  }
  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }
  public int getPriority() {
    return priority;
  }
  public void setPriority(int priority) {
    this.priority = priority;
  }
  public boolean isSuspended() {
    return suspended;
  }
  public void setSuspended(boolean suspended) {
    this.suspended = suspended;
  }
  public String getTaskDefinitionKey() {
    return taskDefinitionKey;
  }
  public void setTaskDefinitionKey(String taskDefinitionKey) {
    this.taskDefinitionKey = taskDefinitionKey;
  }

  public String getParentTaskId() {
    return parentTaskId;
  }

  public void setParentTaskId(String parentTaskId) {
    this.parentTaskId = parentTaskId;
  }

  public String getExecutionId() {
    return executionId;
  }

  public void setExecutionId(String executionId) {
    this.executionId = executionId;
  }

  public void setCategory(String category) {
	  this.category = category;
  }
  
  public String getCategory() {
	  return category;
  }

  public String getProcessInstanceId() {
    return processInstanceId;
  }

  public void setProcessInstanceId(String processInstanceId) {
    this.processInstanceId = processInstanceId;
  }


  public String getProcessDefinitionId() {
    return processDefinitionId;
  }

  public void setProcessDefinitionId(String processDefinitionId) {
    this.processDefinitionId = processDefinitionId;
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
  
  public String getTenantId() {
	  return tenantId;
  }
  
  public void setTenantId(String tenantId) {
	  this.tenantId = tenantId;
  }
}
