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
@ApiModel(value="WfHistoricTaskInstanceQueryRequest",description="历史任务查询参数111111")
public class WfHistoricTaskInstanceQueryRequest extends WfPaginateRequest {

  @ApiModelProperty("任务id")
  private String taskId;
  @ApiModelProperty("流程实例id")
  private String processInstanceId;
  @ApiModelProperty("业务主键")
  private String processBusinessKey;
  @ApiModelProperty("业务主键模糊")
  private String processBusinessKeyLike;
  @ApiModelProperty("流程定义id")
  private String processDefinitionId;
  @ApiModelProperty("流程定义key")
  private String processDefinitionKey;
  @ApiModelProperty("流程定义key模糊")
  private String processDefinitionKeyLike;
  @ApiModelProperty("流程定义名称")
  private String processDefinitionName;
  @ApiModelProperty("流程定义名称模糊")
  private String processDefinitionNameLike;
  @ApiModelProperty("任务执行id")
  private String executionId;
  @ApiModelProperty("任务名称")
  private String taskName;
  @ApiModelProperty("任务名称模糊")
  private String taskNameLike;
  @ApiModelProperty("任务描述")
  private String taskDescription;
  @ApiModelProperty("任务描述模糊")
  private String taskDescriptionLike;
  @ApiModelProperty("任务环节key")
  private String taskDefinitionKey;
  @ApiModelProperty("任务环节key模糊")
  private String taskDefinitionKeyLike;
  @ApiModelProperty("任务删除原因")
  private String taskDeleteReason;
  @ApiModelProperty("任务删除原因模糊")
  private String taskDeleteReasonLike;
  @ApiModelProperty("任务处理人")
  private String taskAssignee;
  @ApiModelProperty("任务处理人模糊")
  private String taskAssigneeLike;
  @ApiModelProperty("任务所有者")
  private String taskOwner;
  @ApiModelProperty("任务所有者模糊")
  private String taskOwnerLike;
  @ApiModelProperty("任务参与者(处理人、候选人、所有者)")
  private String taskInvolvedUser;
  @ApiModelProperty("任务优先级")
  private Integer taskPriority;
  @ApiModelProperty("任务优先级下区间")
  private Integer taskMinPriority;
  @ApiModelProperty("任务优先级上区间")
  private Integer taskMaxPriority;
  @ApiModelProperty(value="任务是否完成")
  private Boolean finished=null;
  @ApiModelProperty("流程是否完成")
  private Boolean processFinished=null;
  @ApiModelProperty("父任务id")
  private String parentTaskId;
  @ApiModelProperty("任务过期时间")
  private Date dueDate;
  @ApiModelProperty("任务过期时间晚于")
  private Date dueDateAfter;
  @ApiModelProperty("任务过期时间早于")
  private Date dueDateBefore;
  @ApiModelProperty("放弃过期时间过滤")
  private Boolean withoutDueDate=null;
  @ApiModelProperty("任务创建时间")
  private Date taskCreatedOn;
  @ApiModelProperty("任务创建时间早于")
  private Date taskCreatedBefore;
  @ApiModelProperty("任务创建时间晚于")
  private Date taskCreatedAfter;
  @ApiModelProperty("任务完成时间")
  private Date taskCompletedOn;
  @ApiModelProperty("任务完成时间早于")
  private Date taskCompletedBefore;
  @ApiModelProperty("任务完成时间晚于")
  private Date taskCompletedAfter;
  @ApiModelProperty("是否只查询包含流程环节变量任务")
  private Boolean includeTaskLocalVariables=null;
  @ApiModelProperty("是否只查询包含流程环节变量和全局变量任务")
  private Boolean includeProcessVariables=null;
  @ApiModelProperty("任务变量条件(LOCAL)")
  private List<WfQueryVariable> taskVariables;
  @ApiModelProperty("任务所在流程变量条件(GLOBAL)")
  private List<WfQueryVariable> processVariables;
  @ApiModelProperty("租户")
  private String tenantId;
  @ApiModelProperty("租户模糊")
  private String tenantIdLike;
  @ApiModelProperty("忽略租户条件")
  private Boolean withoutTenantId=null;

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

  public String getProcessBusinessKey() {
    return processBusinessKey;
  }
  
  public String getProcessBusinessKeyLike() {
	  return processBusinessKeyLike;
  }
  
  public void setProcessBusinessKeyLike(String processBusinessKeyLike) {
	  this.processBusinessKeyLike = processBusinessKeyLike;
  }

  public void setProcessBusinessKey(String processBusinessKey) {
    this.processBusinessKey = processBusinessKey;
  }

  public String getProcessDefinitionId() {
    return processDefinitionId;
  }

  public void setProcessDefinitionId(String processDefinitionId) {
    this.processDefinitionId = processDefinitionId;
  }

  public String getProcessDefinitionKey() {
    return processDefinitionKey;
  }

  public void setProcessDefinitionKey(String processDefinitionKey) {
    this.processDefinitionKey = processDefinitionKey;
  }
  
  public String getProcessDefinitionKeyLike() {
	  return processDefinitionKeyLike;
  }
  
  public void setProcessDefinitionKeyLike(String processDefinitionKeyLike) {
	  this.processDefinitionKeyLike = processDefinitionKeyLike;
  }

  public String getProcessDefinitionName() {
    return processDefinitionName;
  }

  public void setProcessDefinitionName(String processDefinitionName) {
    this.processDefinitionName = processDefinitionName;
  }

  public String getProcessDefinitionNameLike() {
	  return processDefinitionNameLike;
  }
  
  public String getExecutionId() {
    return executionId;
  }
  
  public void setProcessDefinitionNameLike(String processDefinitionNameLike) {
	  this.processDefinitionNameLike = processDefinitionNameLike;
  }

  public void setExecutionId(String executionId) {
    this.executionId = executionId;
  }

  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public String getTaskNameLike() {
    return taskNameLike;
  }

  public void setTaskNameLike(String taskNameLike) {
    this.taskNameLike = taskNameLike;
  }

  public String getTaskDescription() {
    return taskDescription;
  }

  public void setTaskDescription(String taskDescription) {
    this.taskDescription = taskDescription;
  }

  public String getTaskDescriptionLike() {
    return taskDescriptionLike;
  }

  public void setTaskDescriptionLike(String taskDescriptionLike) {
    this.taskDescriptionLike = taskDescriptionLike;
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
  
  public String getTaskDeleteReason() {
    return taskDeleteReason;
  }

  public void setTaskDeleteReason(String taskDeleteReason) {
    this.taskDeleteReason = taskDeleteReason;
  }

  public String getTaskDeleteReasonLike() {
    return taskDeleteReasonLike;
  }

  public void setTaskDeleteReasonLike(String taskDeleteReasonLike) {
    this.taskDeleteReasonLike = taskDeleteReasonLike;
  }

  public String getTaskAssignee() {
    return taskAssignee;
  }

  public void setTaskAssignee(String taskAssignee) {
    this.taskAssignee = taskAssignee;
  }

  public String getTaskAssigneeLike() {
    return taskAssigneeLike;
  }

  public void setTaskAssigneeLike(String taskAssigneeLike) {
    this.taskAssigneeLike = taskAssigneeLike;
  }

  public String getTaskOwner() {
    return taskOwner;
  }

  public void setTaskOwner(String taskOwner) {
    this.taskOwner = taskOwner;
  }

  public String getTaskOwnerLike() {
    return taskOwnerLike;
  }

  public void setTaskOwnerLike(String taskOwnerLike) {
    this.taskOwnerLike = taskOwnerLike;
  }

  public String getTaskInvolvedUser() {
    return taskInvolvedUser;
  }

  public void setTaskInvolvedUser(String taskInvolvedUser) {
    this.taskInvolvedUser = taskInvolvedUser;
  }

  public Integer getTaskPriority() {
    return taskPriority;
  }

  public void setTaskPriority(Integer taskPriority) {
    this.taskPriority = taskPriority;
  }
  
  public Integer getTaskMaxPriority() {
	  return taskMaxPriority;
  }
  
  public void setTaskMaxPriority(Integer taskMaxPriority) {
	  this.taskMaxPriority = taskMaxPriority;
  }
  
  public Integer getTaskMinPriority() {
	  return taskMinPriority;
  }
  
  public void setTaskMinPriority(Integer taskMinPriority) {
	  this.taskMinPriority = taskMinPriority;
  }

  public Boolean getFinished() {
    return finished;
  }

  public void setFinished(Boolean finished) {
    this.finished = finished;
  }

  public Boolean getProcessFinished() {
    return processFinished;
  }

  public void setProcessFinished(Boolean processFinished) {
    this.processFinished = processFinished;
  }

  public String getParentTaskId() {
    return parentTaskId;
  }

  public void setParentTaskId(String parentTaskId) {
    this.parentTaskId = parentTaskId;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public Date getDueDateAfter() {
    return dueDateAfter;
  }

  public void setDueDateAfter(Date dueDateAfter) {
    this.dueDateAfter = dueDateAfter;
  }

  public Date getDueDateBefore() {
    return dueDateBefore;
  }

  public void setDueDateBefore(Date dueDateBefore) {
    this.dueDateBefore = dueDateBefore;
  }
  
  public Boolean getWithoutDueDate() {
	  return withoutDueDate;
  }
  
  public void setWithoutDueDate(Boolean withoutDueDate) {
	  this.withoutDueDate = withoutDueDate;
  }

  public Date getTaskCreatedOn() {
    return taskCreatedOn;
  }

  public void setTaskCreatedOn(Date taskCreatedOn) {
    this.taskCreatedOn = taskCreatedOn;
  }
  
  public void setTaskCreatedAfter(Date taskCreatedAfter) {
	  this.taskCreatedAfter = taskCreatedAfter;
  }
  
  public Date getTaskCompletedAfter() {
	  return taskCompletedAfter;
  }
  
  public void setTaskCompletedAfter(Date taskCompletedAfter) {
	  this.taskCompletedAfter = taskCompletedAfter;
  }
  
  public Date getTaskCompletedBefore() {
	  return taskCompletedBefore;
  }
  
  public void setTaskCompletedBefore(Date taskCompletedBefore) {
	  this.taskCompletedBefore = taskCompletedBefore;
  }
  
  public Date getTaskCompletedOn() {
	  return taskCompletedOn;
  }
  
  public void setTaskCompletedOn(Date taskCompletedOn) {
	  this.taskCompletedOn = taskCompletedOn;
  }
  
  public Date getTaskCreatedAfter() {
	  return taskCreatedAfter;
  }
  
  public void setTaskCreatedBefore(Date taskCreatedBefore) {
	  this.taskCreatedBefore = taskCreatedBefore;
  }
  
  public Date getTaskCreatedBefore() {
	  return taskCreatedBefore;
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
  public List<WfQueryVariable> getProcessVariables() {
    return processVariables;
  }
  
  public void setProcessVariables(List<WfQueryVariable> processVariables) {
    this.processVariables = processVariables;
  }

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantIdLike() {
		return tenantIdLike;
	}

	public void setTenantIdLike(String tenantIdLike) {
		this.tenantIdLike = tenantIdLike;
	}

	public Boolean getWithoutTenantId() {
		return withoutTenantId;
	}

	public void setWithoutTenantId(Boolean withoutTenantId) {
		this.withoutTenantId = withoutTenantId;
	}
}
