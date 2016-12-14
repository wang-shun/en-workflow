package com.chinacreator.c2.flow.rest.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;


/**
 * @author hushowly
 */
@ApiModel(value="WfTaskQueryRequest",description="任务查询对象")
public class WfTaskQueryRequest extends WfPaginateRequest {

  @ApiModelProperty("任务名称")
  private String name;
  @ApiModelProperty("任务名称模糊条件")
  private String nameLike;
  @ApiModelProperty("任务描述")
  private String description;
  @ApiModelProperty("任务描述模糊条件")
  private String descriptionLike;
  @ApiModelProperty("任务优先级")
  private Integer priority;
  @ApiModelProperty("任务优先级下区间")
  private Integer minimumPriority;
  @ApiModelProperty("任务优先级上区间")
  private Integer maximumPriority;
  @ApiModelProperty("处理人")
  private String assignee;
  @ApiModelProperty("处理人模糊条件")
  private String assigneeLike;
  @ApiModelProperty("任务所有者")
  private String owner;
  @ApiModelProperty("任务所有者模糊条件")
  private String ownerLike;
  @ApiModelProperty("任务是否未分配")
  private Boolean unassigned;
  @ApiModelProperty("代理状态")
  private String delegationState;
  @ApiModelProperty("候选人")
  private String candidateUser;
  @ApiModelProperty("候选组集合)")
  private List<String> candidateGroupIn;
  @ApiModelProperty("候选人或处理人)")
  private String candidateOrAssigned;
  @ApiModelProperty("流程参与者(处理人、候选人、所有者)")
  private String involvedUser;
  @ApiModelProperty("任务所在流程实例id")
  private String processInstanceId;
  @ApiModelProperty("任务的业务主键")
  private String processInstanceBusinessKey;
  @ApiModelProperty("任务的业务主键模糊条件")
  private String processInstanceBusinessKeyLike;
  @ApiModelProperty("任务所在流程定义key")
  private String processDefinitionKey;
  @ApiModelProperty("任务所在流程定义名称")
  private String processDefinitionName;
  @ApiModelProperty("任务所在流程定义key模糊条件")
  private String processDefinitionKeyLike;
  @ApiModelProperty("任务所在流程定义名称模糊条件")
  private String processDefinitionNameLike;
  @ApiModelProperty("任务执行id")
  private String executionId;
  @ApiModelProperty("任务创建时间")
  private Date createdOn;
  @ApiModelProperty("任务创建时间下区间条件")
  private Date createdBefore;
  @ApiModelProperty("任务创建时间上区间条件")
  private Date createdAfter;
  @ApiModelProperty("是否排除有子任务")
  private Boolean excludeSubTasks;
  @ApiModelProperty("任务所在环节key")
  private String taskDefinitionKey;
  @ApiModelProperty("任务所在环节key模糊条件")
  private String taskDefinitionKeyLike;
  @ApiModelProperty("任务过期时间")
  private Date dueDate;
  @ApiModelProperty("过期时间范围下区间")
  private Date dueBefore;
  @ApiModelProperty("过期时间范围上区间")
  private Date dueAfter;
  @ApiModelProperty("忽略过期时间条件")
  private Boolean withoutDueDate;
  @ApiModelProperty(value="是否活动")
  private Boolean active;
  @ApiModelProperty("是否只查询包含流程环节变量任务")
  private Boolean includeTaskLocalVariables;
  @ApiModelProperty("是否只查询包含流程环节变量和全局变量任务")
  private Boolean includeProcessVariables;
  @ApiModelProperty("租户")
  private String tenantId;
  @ApiModelProperty("租户模糊条件")
  private String tenantIdLike;
  @ApiModelProperty("忽略租户条件")
  private Boolean withoutTenantId;
  
  @ApiModelProperty("任务变量条件(LOCAL)")
  private List<WfQueryVariable> taskVariables;
  @ApiModelProperty("任务所在流程变量条件(GLOBAL)")
  private List<WfQueryVariable> processInstanceVariables;
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getNameLike() {
    return nameLike;
  }
  
  public void setNameLike(String nameLike) {
    this.nameLike = nameLike;
  }
  
  public String getDescription() {
    return description;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public String getDescriptionLike() {
    return descriptionLike;
  }
  
  public void setDescriptionLike(String descriptionLike) {
    this.descriptionLike = descriptionLike;
  }
  
  public Integer getPriority() {
    return priority;
  }
  
  public void setPriority(Integer priority) {
    this.priority = priority;
  }
  
  public Integer getMinimumPriority() {
    return minimumPriority;
  }
  
  public void setMinimumPriority(Integer minimumPriority) {
    this.minimumPriority = minimumPriority;
  }
  
  public Integer getMaximumPriority() {
    return maximumPriority;
  }
  
  public void setMaximumPriority(Integer maximumPriority) {
    this.maximumPriority = maximumPriority;
  }
  
  public String getAssignee() {
    return assignee;
  }
  
  public void setAssignee(String assignee) {
    this.assignee = assignee;
  }
  
  public String getAssigneeLike() {
	  return assigneeLike;
  }
  
  public void setAssigneeLike(String assigneeLike) {
	  this.assigneeLike = assigneeLike;
  }
  
  public String getOwner() {
    return owner;
  }
  
  public void setOwner(String owner) {
    this.owner = owner;
  }
  
  public String getOwnerLike() {
	  return ownerLike;
  }
  public void setOwnerLike(String ownerLike) {
	  this.ownerLike = ownerLike;
  }
  
  public Boolean getUnassigned() {
    return unassigned;
  }
  
  public void setUnassigned(Boolean unassigned) {
    this.unassigned = unassigned;
  }
  
  public String getDelegationState() {
    return delegationState;
  }
  
  public void setDelegationState(String delegationState) {
    this.delegationState = delegationState;
  }
  
  public String getCandidateUser() {
    return candidateUser;
  }
  
  public void setCandidateUser(String candidateUser) {
    this.candidateUser = candidateUser;
  }
  
  
  public String getInvolvedUser() {
    return involvedUser;
  }
  
  public void setInvolvedUser(String involvedUser) {
    this.involvedUser = involvedUser;
  }
  
  public String getProcessInstanceId() {
    return processInstanceId;
  }
  
  public void setProcessInstanceId(String processInstanceId) {
    this.processInstanceId = processInstanceId;
  }
  
  public String getProcessInstanceBusinessKey() {
    return processInstanceBusinessKey;
  }
  
  public void setProcessInstanceBusinessKey(String processInstanceBusinessKey) {
    this.processInstanceBusinessKey = processInstanceBusinessKey;
  }
  
  public String getProcessInstanceBusinessKeyLike() {
	  return processInstanceBusinessKeyLike;
  }
  
  public void setProcessInstanceBusinessKeyLike(String processInstanceBusinessKeyLike) {
	  this.processInstanceBusinessKeyLike = processInstanceBusinessKeyLike;
  }
  
  public String getExecutionId() {
    return executionId;
  }
  
  public void setExecutionId(String executionId) {
    this.executionId = executionId;
  }
  
  public Date getCreatedOn() {
    return createdOn;
  }
  
  public void setCreatedOn(Date createdOn) {
    this.createdOn = createdOn;
  }
  
  public Date getCreatedBefore() {
    return createdBefore;
  }
  
  public void setCreatedBefore(Date createdBefore) {
    this.createdBefore = createdBefore;
  }
  
  public Date getCreatedAfter() {
    return createdAfter;
  }
  
  public void setCreatedAfter(Date createdAfter) {
    this.createdAfter = createdAfter;
  }
  
  public Boolean getExcludeSubTasks() {
    return excludeSubTasks;
  }
  
  public void setExcludeSubTasks(Boolean excludeSubTasks) {
    this.excludeSubTasks = excludeSubTasks;
  }
  
  public String getTaskDefinitionKey() {
    return taskDefinitionKey;
  }
  
  public void setTaskDefinitionKey(String taskDefinitionKey) {
    this.taskDefinitionKey = taskDefinitionKey;
  }
  
  public String getTaskDefinitionKeyLike() {
    return taskDefinitionKeyLike;
  }
  
  public void setTaskDefinitionKeyLike(String taskDefinitionKeyLike) {
    this.taskDefinitionKeyLike = taskDefinitionKeyLike;
  }
  
  public Date getDueDate() {
    return dueDate;
  }
  
  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }
  
  public Date getDueBefore() {
    return dueBefore;
  }
  
  public void setDueBefore(Date dueBefore) {
    this.dueBefore = dueBefore;
  }
  
  public Date getDueAfter() {
    return dueAfter;
  }
  
  public void setDueAfter(Date dueAfter) {
    this.dueAfter = dueAfter;
  }
  
  public Boolean getActive() {
    return active;
  }
  
  public void setActive(Boolean active) {
    this.active = active;
  }
  
  public Boolean getIncludeTaskLocalVariables() {
    return includeTaskLocalVariables;
  }

  public void setIncludeTaskLocalVariables(Boolean includeTaskLocalVariables) {
    this.includeTaskLocalVariables = includeTaskLocalVariables;
  }

  public Boolean getIncludeProcessVariables() {
    return includeProcessVariables;
  }

  public void setIncludeProcessVariables(Boolean includeProcessVariables) {
    this.includeProcessVariables = includeProcessVariables;
  }

  @JsonTypeInfo(use=Id.CLASS, defaultImpl=WfQueryVariable.class)
  public List<WfQueryVariable> getTaskVariables() {
    return taskVariables;
  }
  
  public void setTaskVariables(List<WfQueryVariable> taskVariables) {
    this.taskVariables = taskVariables;
  }
  
  @JsonTypeInfo(use=Id.CLASS, defaultImpl=WfQueryVariable.class)
  public List<WfQueryVariable> getProcessInstanceVariables() {
    return processInstanceVariables;
  }
  
  public void setProcessInstanceVariables(List<WfQueryVariable> processInstanceVariables) {
    this.processInstanceVariables = processInstanceVariables;
  }
  
  public void setProcessDefinitionNameLike(String processDefinitionNameLike) {
	  this.processDefinitionNameLike = processDefinitionNameLike;
  }
  
  public String getProcessDefinitionNameLike() {
	  return processDefinitionNameLike;
  }
  
  public String getProcessDefinitionKeyLike() {
	  return processDefinitionKeyLike;
  }
  public void setProcessDefinitionKeyLike(String processDefinitionKeyLike) {
	  this.processDefinitionKeyLike = processDefinitionKeyLike;
  }
  
  public void setWithoutDueDate(Boolean withoutDueDate) {
	  this.withoutDueDate = withoutDueDate;
  }
  
  public Boolean getWithoutDueDate() {
	  return withoutDueDate;
  }
  
  public String getProcessDefinitionKey() {
	  return processDefinitionKey;
  }
  
  public void setProcessDefinitionKey(String processDefinitionKey) {
	  this.processDefinitionKey = processDefinitionKey;
  }
  
  public String getProcessDefinitionName() {
	  return processDefinitionName;
  }
  
  public void setProcessDefinitionName(String processDefinitionName) {
	  this.processDefinitionName = processDefinitionName;
  }
  
  public void setTenantId(String tenantId) {
	  this.tenantId = tenantId;
  }
  
  public String getTenantId() {
	  return tenantId;
  }
  
  public void setTenantIdLike(String tenantIdLike) {
	  this.tenantIdLike = tenantIdLike;
  }
  
  public String getTenantIdLike() {
	  return tenantIdLike;
  }
  
  public void setWithoutTenantId(Boolean withoutTenantId) {
	  this.withoutTenantId = withoutTenantId;
  }
  
  public Boolean getWithoutTenantId() {
	  return withoutTenantId;
  }

public List<String> getCandidateGroupIn() {
	return candidateGroupIn;
}

public void setCandidateGroupIn(List<String> candidateGroupIn) {
	this.candidateGroupIn = candidateGroupIn;
}

public String getCandidateOrAssigned() {
	return candidateOrAssigned;
}

public void setCandidateOrAssigned(String candidateOrAssigned) {
	this.candidateOrAssigned = candidateOrAssigned;
}


  
  
}
