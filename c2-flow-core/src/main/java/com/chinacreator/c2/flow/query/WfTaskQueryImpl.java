package com.chinacreator.c2.flow.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.AbstractVariableQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.QueryVariableValue;
import org.activiti.engine.impl.TaskQueryProperty;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.interceptor.CommandExecutor;
import org.activiti.engine.impl.persistence.entity.SuspensionState;
import org.activiti.engine.impl.variable.VariableTypes;
import org.activiti.engine.task.DelegationState;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;

/**
 *  重写TaskQueryImpl支持待办or查询，一条sql查询所有待办(处理人、候选人、候选组）
 */
public class WfTaskQueryImpl extends AbstractVariableQueryImpl<TaskQuery, Task> implements TaskQuery {
  
  private static final long serialVersionUID = 1L;
  protected String taskId;
  protected String name;
  protected String nameLike;
  protected String description;
  protected String descriptionLike;
  protected Integer priority;
  protected Integer minPriority;
  protected Integer maxPriority;
  protected String assignee;
  protected String assigneeLike;
  protected String involvedUser;
  protected String owner;
  protected String ownerLike;
  protected boolean unassigned = false;
  protected boolean noDelegationState = false;
  protected DelegationState delegationState;
  protected String candidateUser;
  protected String candidateGroup;
  protected List<String> candidateGroups;
  protected String tenantId;
  protected String tenantIdLike;
  protected boolean withoutTenantId;
  protected String processInstanceId;
  protected String executionId;
  protected Date createTime;
  protected Date createTimeBefore;
  protected Date createTimeAfter;
  protected String category;
  protected String key;
  protected String keyLike;
  protected String processDefinitionKey;
  protected String processDefinitionKeyLike;
  protected String processDefinitionId;
  protected String processDefinitionName;
  protected String processDefinitionNameLike;
  protected String deploymentId;
  protected String processInstanceBusinessKey;
  protected String processInstanceBusinessKeyLike;
  protected Date dueDate;
  protected Date dueBefore;
  protected Date dueAfter;
  protected boolean withoutDueDate = false;
  protected SuspensionState suspensionState;
  protected boolean excludeSubtasks = false;
  protected boolean includeTaskLocalVariables = false;
  protected boolean includeProcessVariables = false;
  protected String userIdForCandidateAndAssignee;
  protected boolean bothCandidateAndAssigned = false;
  protected boolean orActive;
  protected WfTaskQueryImpl orQueryObject;

  public WfTaskQueryImpl() {
  }
  
  public WfTaskQueryImpl(CommandContext commandContext) {
    super(commandContext);
  }
  
  public WfTaskQueryImpl(CommandExecutor commandExecutor) {
    super(commandExecutor);
  }

  public WfTaskQueryImpl taskId(String taskId) {
    if (taskId == null) {
      throw new ActivitiIllegalArgumentException("Task id is null");
    }
    
    if(orActive) {
      orQueryObject.taskId = taskId;
    } else {
      this.taskId = taskId;
    }
    return this;
  }
  
  public WfTaskQueryImpl taskName(String name) {
    if (name == null) {
      throw new ActivitiIllegalArgumentException("Task name is null");
    }
    
    if(orActive) {
      orQueryObject.name = name;
    } else {
      this.name = name;
    }
    return this;
  }
  
  public WfTaskQueryImpl taskNameLike(String nameLike) {
    if (nameLike == null) {
      throw new ActivitiIllegalArgumentException("Task namelike is null");
    }
    
    if(orActive) {
      orQueryObject.nameLike = nameLike;
    } else {
      this.nameLike = nameLike;
    }
    return this;
  }
  
  public WfTaskQueryImpl taskDescription(String description) {
    if (description == null) {
      throw new ActivitiIllegalArgumentException("Description is null");
    }
    
    if(orActive) {
      orQueryObject.description = description;
    } else {
      this.description = description;
    }
    return this;
  }
  
  public TaskQuery taskDescriptionLike(String descriptionLike) {
    if (descriptionLike == null) {
      throw new ActivitiIllegalArgumentException("Task descriptionlike is null");
    }
    if(orActive) {
      orQueryObject.descriptionLike = descriptionLike;
    } else {
      this.descriptionLike = descriptionLike;
    }
    return this;
  }
  
  public TaskQuery taskPriority(Integer priority) {
    if (priority == null) {
      throw new ActivitiIllegalArgumentException("Priority is null");
    }
    if(orActive) {
      orQueryObject.priority = priority;
    } else {
      this.priority = priority;
    }
    return this;
  }

  public TaskQuery taskMinPriority(Integer minPriority) {
    if (minPriority == null) {
      throw new ActivitiIllegalArgumentException("Min Priority is null");
    }
    if(orActive) {
      orQueryObject.minPriority = minPriority;
    } else {
      this.minPriority = minPriority;
    }
    return this;
  }

  public TaskQuery taskMaxPriority(Integer maxPriority) {
    if (maxPriority == null) {
      throw new ActivitiIllegalArgumentException("Max Priority is null");
    }
    if(orActive) {
      orQueryObject.maxPriority = maxPriority;
    } else {
      this.maxPriority = maxPriority;
    }
    return this;
  }

  public WfTaskQueryImpl taskAssignee(String assignee) {
    if (assignee == null) {
      throw new ActivitiIllegalArgumentException("Assignee is null");
    }
    if(orActive) {
      orQueryObject.assignee = assignee;
    } else {
      this.assignee = assignee;
    }
    return this;
  }
  
  public WfTaskQueryImpl taskAssigneeLike(String assigneeLike) {
    if (assigneeLike == null) {
      throw new ActivitiIllegalArgumentException("Assignee is null");
    }
    if(orActive) {
      orQueryObject.assigneeLike = assignee;
    } else {
      this.assigneeLike = assigneeLike;
    }
    return this;
  }
  
  public WfTaskQueryImpl taskOwner(String owner) {
    if (owner == null) {
      throw new ActivitiIllegalArgumentException("Owner is null");
    }
    if(orActive) {
      orQueryObject.owner = owner;
    } else {
      this.owner = owner;
    }
    return this;
  }
  
  public WfTaskQueryImpl taskOwnerLike(String ownerLike) {
    if (ownerLike == null) {
      throw new ActivitiIllegalArgumentException("Owner is null");
    }
    if(orActive) {
      orQueryObject.ownerLike = ownerLike;
    } else {
      this.ownerLike = ownerLike;
    }
    return this;
  }
  
  /** @see {@link #taskUnassigned} */
  @Deprecated
  public TaskQuery taskUnnassigned() {
    return taskUnassigned();
  }

  public TaskQuery taskUnassigned() {
    if(orActive) {
      orQueryObject.unassigned = true;
    } else {
      this.unassigned = true;
    }
    return this;
  }

  public TaskQuery taskDelegationState(DelegationState delegationState) {
    if(orActive) {
      if (delegationState == null) {
        orQueryObject.noDelegationState = true;
      } else {
        orQueryObject.delegationState = delegationState;
      }
    } else {
      if (delegationState == null) {
        this.noDelegationState = true;
      } else {
        this.delegationState = delegationState;
      }
    }
    return this;
  }

  public WfTaskQueryImpl taskCandidateUser(String candidateUser) {
    if (candidateUser == null) {
      throw new ActivitiIllegalArgumentException("Candidate user is null");
    }
    
//    if (candidateGroup != null) {
//      throw new ActivitiIllegalArgumentException("Invalid query usage: cannot set both candidateUser and candidateGroup");
//    }
//    if (candidateGroups != null) {
//      throw new ActivitiIllegalArgumentException("Invalid query usage: cannot set both candidateUser and candidateGroupIn");
//    }
    
    if(orActive) {
      orQueryObject.candidateUser = candidateUser;
    } else {
      this.candidateUser = candidateUser;
    }
    
    return this;
  }
  
  public WfTaskQueryImpl taskInvolvedUser(String involvedUser) {
    if (involvedUser == null) {
      throw new ActivitiIllegalArgumentException("Involved user is null");
    }
    if(orActive) {
      orQueryObject.involvedUser = involvedUser;
    } else {
      this.involvedUser = involvedUser;
    }
    return this;
  }
  
  public WfTaskQueryImpl taskCandidateGroup(String candidateGroup) {
    if (candidateGroup == null) {
      throw new ActivitiIllegalArgumentException("Candidate group is null");
    }
//    if (candidateUser != null) {
//      throw new ActivitiIllegalArgumentException("Invalid query usage: cannot set both candidateGroup and candidateUser");
//    }
    if (candidateGroups != null) {
      throw new ActivitiIllegalArgumentException("Invalid query usage: cannot set both candidateGroup and candidateGroupIn");
    }
    if(orActive) {
      orQueryObject.candidateGroup = candidateGroup;
    } else {
      this.candidateGroup = candidateGroup;
    }
    return this;
  }

  @Override
  public TaskQuery taskCandidateOrAssigned(String userIdForCandidateAndAssignee) {
    if (candidateGroup != null) {
      throw new ActivitiIllegalArgumentException("Invalid query usage: cannot set candidateGroup");
    }
    if (candidateUser != null) {
      throw new ActivitiIllegalArgumentException("Invalid query usage: cannot set both userIdForCandidateAndAssignee and candidateUser");
    }
    
    if (assignee != null) {
        throw new ActivitiIllegalArgumentException("Invalid query usage: cannot set both userIdForCandidateAndAssignee and assignee");
    }
    
    if(orActive) {
      orQueryObject.bothCandidateAndAssigned = true;
      orQueryObject.userIdForCandidateAndAssignee = userIdForCandidateAndAssignee;
    } else {
      this.bothCandidateAndAssigned = true;
      this.userIdForCandidateAndAssignee = userIdForCandidateAndAssignee;
    }
    
    return this;
  }

  public TaskQuery taskCandidateGroupIn(List<String> candidateGroups) {
    if(candidateGroups == null) {
      throw new ActivitiIllegalArgumentException("Candidate group list is null");
    }
    if(candidateGroups.size()== 0) {
      throw new ActivitiIllegalArgumentException("Candidate group list is empty");
    }
    
//    if (candidateUser != null) {
//      throw new ActivitiIllegalArgumentException("Invalid query usage: cannot set both candidateGroupIn and candidateUser");
//    }
    if (candidateGroup != null) {
      throw new ActivitiIllegalArgumentException("Invalid query usage: cannot set both candidateGroupIn and candidateGroup");
    }
    
    if(orActive) {
      orQueryObject.candidateGroups = candidateGroups;
    } else {
      this.candidateGroups = candidateGroups;
    }
    return this;
  }
  
  public TaskQuery taskTenantId(String tenantId) {
  	if (tenantId == null) {
  		throw new ActivitiIllegalArgumentException("task tenant id is null");
  	}
  	 if(orActive) {
       orQueryObject.tenantId = tenantId;
     } else {
       this.tenantId = tenantId;
     }
  	return this;
  }
  
  public TaskQuery taskTenantIdLike(String tenantIdLike) {
  	if (tenantIdLike == null) {
  		throw new ActivitiIllegalArgumentException("task tenant id is null");
  	}
  	if(orActive) {
      orQueryObject.tenantIdLike = tenantIdLike;
    } else {
      this.tenantIdLike = tenantIdLike;
    }
  	return this;
  }
  
  public TaskQuery taskWithoutTenantId() {
    if(orActive) {
      orQueryObject.withoutTenantId = true;
    } else {
      this.withoutTenantId = true;
    }
  	return this;
  }
  
  public WfTaskQueryImpl processInstanceId(String processInstanceId) {
    if(orActive) {
      orQueryObject.processInstanceId = processInstanceId;
    } else {
      this.processInstanceId = processInstanceId;
    }
    return this;
  }
  
  public WfTaskQueryImpl processInstanceBusinessKey(String processInstanceBusinessKey) {
    if(orActive) {
      orQueryObject.processInstanceBusinessKey = processInstanceBusinessKey;
    } else {
      this.processInstanceBusinessKey = processInstanceBusinessKey;
    }
    return this;
  }
  
  public WfTaskQueryImpl processInstanceBusinessKeyLike(String processInstanceBusinessKeyLike) {
    if(orActive) {
      orQueryObject.processInstanceBusinessKeyLike = processInstanceBusinessKeyLike;
    } else {
      this.processInstanceBusinessKeyLike = processInstanceBusinessKeyLike;
    }
    return this;
  }
  
  public WfTaskQueryImpl executionId(String executionId) {
    if(orActive) {
      orQueryObject.executionId = executionId;
    } else {
      this.executionId = executionId;
    }
    return this;
  }
  
  public WfTaskQueryImpl taskCreatedOn(Date createTime) {
    if(orActive) {
      orQueryObject.createTime = createTime;
    } else {
      this.createTime = createTime;
    }
    return this;
  }
  
  public TaskQuery taskCreatedBefore(Date before) {
    if(orActive) {
      orQueryObject.createTimeBefore = before;
    } else {
      this.createTimeBefore = before;
    }
    return this;
  }
  
  public TaskQuery taskCreatedAfter(Date after) {
    if(orActive) {
      orQueryObject.createTimeAfter = after;
    } else {
      this.createTimeAfter = after;
    }
    return this;
  }
  
  public TaskQuery taskCategory(String category) {
    if(orActive) {
      orQueryObject.category = category;
    } else {
      this.category = category;
    }
  	return this;
  }
  
  public TaskQuery taskDefinitionKey(String key) {
    if(orActive) {
      orQueryObject.key = key;
    } else {
      this.key = key;
    }
    return this;
  }
  
  public TaskQuery taskDefinitionKeyLike(String keyLike) {
    if(orActive) {
      orQueryObject.keyLike = keyLike;
    } else {
      this.keyLike = keyLike;
    }
    return this;
  }
  
  public TaskQuery taskVariableValueEquals(String variableName, Object variableValue) {
    if(orActive) {
      orQueryObject.variableValueEquals(variableName, variableValue);
    } else {
      this.variableValueEquals(variableName, variableValue);
    }
    return this;
  }
  
  public TaskQuery taskVariableValueEquals(Object variableValue) {
    if(orActive) {
      orQueryObject.variableValueEquals(variableValue);
    } else {
      this.variableValueEquals(variableValue);
    }
    return this;
  }
  
  public TaskQuery taskVariableValueEqualsIgnoreCase(String name, String value) {
    if(orActive) {
      orQueryObject.variableValueEqualsIgnoreCase(name, value);
    } else {
      this.variableValueEqualsIgnoreCase(name, value);
    }
    return this;
  }
  
  public TaskQuery taskVariableValueNotEqualsIgnoreCase(String name, String value) {
    if(orActive) {
      orQueryObject.variableValueNotEqualsIgnoreCase(name, value);
    } else {
      this.variableValueNotEqualsIgnoreCase(name, value);
    }
    return this;
  }

  public TaskQuery taskVariableValueNotEquals(String variableName, Object variableValue) {
    if(orActive) {
      orQueryObject.variableValueNotEquals(variableName, variableValue);
    } else {
      this.variableValueNotEquals(variableName, variableValue);
    }
    return this;
  }
  
  public TaskQuery taskVariableValueGreaterThan(String name, Object value) {
    if(orActive) {
      orQueryObject.variableValueGreaterThan(name, value);
    } else {
      this.variableValueGreaterThan(name, value);
    }
    return this;
  }

  public TaskQuery taskVariableValueGreaterThanOrEqual(String name, Object value) {
    if(orActive) {
      orQueryObject.variableValueGreaterThanOrEqual(name, value);
    } else {
      this.variableValueGreaterThanOrEqual(name, value);
    }
    return this;
  }

  public TaskQuery taskVariableValueLessThan(String name, Object value) {
    if(orActive) {
      orQueryObject.variableValueLessThan(name, value);
    } else {
      this.variableValueLessThan(name, value);
    }
    return this;
  }

  public TaskQuery taskVariableValueLessThanOrEqual(String name, Object value) {
    if(orActive) {
      orQueryObject.variableValueLessThanOrEqual(name, value);
    } else {
      this.variableValueLessThanOrEqual(name, value);
    }
    return this;
  }

  public TaskQuery taskVariableValueLike(String name, String value) {
    if(orActive) {
      orQueryObject.variableValueLike(name, value);
    } else {
      this.variableValueLike(name, value);
    }
    return this;
  }

  public TaskQuery processVariableValueEquals(String variableName, Object variableValue) {
    if(orActive) {
      orQueryObject.variableValueEquals(variableName, variableValue, false);
    } else {
      this.variableValueEquals(variableName, variableValue, false);
    }
    return this;
  }

  public TaskQuery processVariableValueNotEquals(String variableName, Object variableValue) {
    if(orActive) {
      orQueryObject.variableValueNotEquals(variableName, variableValue, false);
    } else {
      this.variableValueNotEquals(variableName, variableValue, false);
    }
    return this;
  }
  
  public TaskQuery processVariableValueEquals(Object variableValue) {
    if(orActive) {
      orQueryObject.variableValueEquals(variableValue, false);
    } else {
      this.variableValueEquals(variableValue, false);
    }
    return this;
  }
  
  public TaskQuery processVariableValueEqualsIgnoreCase(String name, String value) {
    if(orActive) {
      orQueryObject.variableValueEqualsIgnoreCase(name, value, false);
    } else {
      this.variableValueEqualsIgnoreCase(name, value, false);
    }
    return this;
  }
  
  public TaskQuery processVariableValueNotEqualsIgnoreCase(String name, String value) {
    if(orActive) {
      orQueryObject.variableValueNotEqualsIgnoreCase(name, value, false);
    } else {
      this.variableValueNotEqualsIgnoreCase(name, value, false);
    }
    return this;
  }
  
  public TaskQuery processVariableValueGreaterThan(String name, Object value) {
    if(orActive) {
      orQueryObject.variableValueGreaterThan(name, value, false);
    } else {
      this.variableValueGreaterThan(name, value, false);
    }
    return this;
  }

  public TaskQuery processVariableValueGreaterThanOrEqual(String name, Object value) {
    if(orActive) {
      orQueryObject.variableValueGreaterThanOrEqual(name, value, false);
    } else {
      this.variableValueGreaterThanOrEqual(name, value, false);
    }
    return this;
  }

  public TaskQuery processVariableValueLessThan(String name, Object value) {
    if(orActive) {
      orQueryObject.variableValueLessThan(name, value, false);
    } else {
      this.variableValueLessThan(name, value, false);
    }
    return this;
  }

  public TaskQuery processVariableValueLessThanOrEqual(String name, Object value) {
    if(orActive) {
      orQueryObject.variableValueLessThanOrEqual(name, value, false);
    } else {
      this.variableValueLessThanOrEqual(name, value, false);
    }
    return this;
  }

  public TaskQuery processVariableValueLike(String name, String value) {
    if(orActive) {
      orQueryObject.variableValueLike(name, value, false);
    } else {
      this.variableValueLike(name, value, false);
    }
    return this;
  }

  public TaskQuery processDefinitionKey(String processDefinitionKey) {
    if(orActive) {
      orQueryObject.processDefinitionKey = processDefinitionKey;
    } else {
      this.processDefinitionKey = processDefinitionKey;
    }
    return this;
  }
  
  public TaskQuery processDefinitionKeyLike(String processDefinitionKeyLike) {
    if(orActive) {
      orQueryObject.processDefinitionKeyLike = processDefinitionKeyLike;
    } else {
      this.processDefinitionKeyLike = processDefinitionKeyLike;
    }
    return this;
  }

  public TaskQuery processDefinitionId(String processDefinitionId) {
    if(orActive) {
      orQueryObject.processDefinitionId = processDefinitionId;
    } else {
      this.processDefinitionId = processDefinitionId;
    }
    return this;
  }
  
  public TaskQuery processDefinitionName(String processDefinitionName) {
    if(orActive) {
      orQueryObject.processDefinitionName = processDefinitionName;
    } else {
      this.processDefinitionName = processDefinitionName;
    }
    return this;
  }
  
  public TaskQuery processDefinitionNameLike(String processDefinitionNameLike) {
    if(orActive) {
      orQueryObject.processDefinitionNameLike = processDefinitionNameLike;
    } else {
      this.processDefinitionNameLike = processDefinitionNameLike;
    }
    return this;
  }
  
  public TaskQuery deploymentId(String deploymentId) {
    if(orActive) {
      orQueryObject.deploymentId = deploymentId;
    } else {
      this.deploymentId = deploymentId;
    }
    return this;
  }
  
  public TaskQuery dueDate(Date dueDate) {
    if(orActive) {
      orQueryObject.dueDate = dueDate;
      orQueryObject.withoutDueDate = false;
    } else {
      this.dueDate = dueDate;
      this.withoutDueDate = false;
    }
    return this;
  }
  
  public TaskQuery dueBefore(Date dueBefore) {
    if(orActive) {
      orQueryObject.dueBefore = dueBefore;
      orQueryObject.withoutDueDate = false;
    } else {
      this.dueBefore = dueBefore;
      this.withoutDueDate = false;
    }
    return this;
  }
  
  public TaskQuery dueAfter(Date dueAfter) {
    if(orActive) {
      orQueryObject.dueAfter = dueAfter;
      orQueryObject.withoutDueDate = false;
    } else {
      this.dueAfter = dueAfter;
      this.withoutDueDate = false;
    }
    return this;
  }
  
  public TaskQuery withoutDueDate() {
    if(orActive) {
      orQueryObject.withoutDueDate = true;
    } else {
      this.withoutDueDate = true;
    }
    return this;
  }

  public TaskQuery excludeSubtasks() {
    if(orActive) {
      orQueryObject.excludeSubtasks = true;
    } else {
      this.excludeSubtasks = true;
    }
    return this;
  }
  
  public TaskQuery suspended() {
    if(orActive) {
      orQueryObject.suspensionState = SuspensionState.SUSPENDED;
    } else {
      this.suspensionState = SuspensionState.SUSPENDED;
    }
    return this;
  }

  public TaskQuery active() {
    if(orActive) {
      orQueryObject.suspensionState = SuspensionState.ACTIVE;
    } else {
      this.suspensionState = SuspensionState.ACTIVE;
    }
    return this;
  }
  
  public TaskQuery includeTaskLocalVariables() {
    this.includeTaskLocalVariables = true;
    return this;
  }
  
  public TaskQuery includeProcessVariables() {
    this.includeProcessVariables = true;
    return this;
  }

  public List<String> getCandidateGroups() {
    if (candidateGroup!=null) {
      List<String> candidateGroupList = new java.util.ArrayList<String>(1);
      candidateGroupList.add(candidateGroup);
      return candidateGroupList;
    } else if (candidateUser != null) {
      return getGroupsForCandidateUser(candidateUser);
    } else if(candidateGroups != null) {
      return candidateGroups;
    }
    return null;
  }
  
  protected List<String> getGroupsForCandidateUser(String candidateUser) {
    // TODO: Discuss about removing this feature? Or document it properly and maybe recommend to not use it
    // and explain alternatives
    List<Group> groups = Context
      .getCommandContext()
      .getGroupIdentityManager()
      .findGroupsByUser(candidateUser);
    List<String> groupIds = new ArrayList<String>();
    for (Group group : groups) {
      groupIds.add(group.getId());
    }
    return groupIds;
  }
  
  protected void ensureVariablesInitialized() {    
    VariableTypes types = Context.getProcessEngineConfiguration().getVariableTypes();
    for (QueryVariableValue var : queryVariableValues) {
      var.initialize(types);
    }
    
    if(orQueryObject != null) {
      orQueryObject.ensureVariablesInitialized();
    }
  }
  
  //or query ////////////////////////////////////////////////////////////////
  

  public TaskQuery or() {
    if(orActive || orQueryObject != null) {
        throw new ActivitiException("or() can only be called once");
    }
    
    // Create instance of the orQuery
    orActive = true;
    orQueryObject = new WfTaskQueryImpl();
    return this;
  }
  

  public TaskQuery endOr() {
    if(!orActive || orQueryObject == null) {
      throw new ActivitiException("endOr() can only be called after calling or()");
    }
    orActive = false;
    return this;
  }
  

  //ordering ////////////////////////////////////////////////////////////////
  
  public TaskQuery orderByTaskId() {
    return orderBy(TaskQueryProperty.TASK_ID);
  }
  
  public TaskQuery orderByTaskName() {
    return orderBy(TaskQueryProperty.NAME);
  }
  
  public TaskQuery orderByTaskDescription() {
    return orderBy(TaskQueryProperty.DESCRIPTION);
  }
  
  public TaskQuery orderByTaskPriority() {
    return orderBy(TaskQueryProperty.PRIORITY);
  }
  
  public TaskQuery orderByProcessInstanceId() {
    return orderBy(TaskQueryProperty.PROCESS_INSTANCE_ID);
  }
  
  public TaskQuery orderByExecutionId() {
    return orderBy(TaskQueryProperty.EXECUTION_ID);
  }
  
  public TaskQuery orderByTaskAssignee() {
    return orderBy(TaskQueryProperty.ASSIGNEE);
  }
  
  public TaskQuery orderByTaskCreateTime() {
    return orderBy(TaskQueryProperty.CREATE_TIME);
  }
  
  public TaskQuery orderByDueDate() {
    return orderBy(TaskQueryProperty.DUE_DATE);
  }
  
  @Override
  public TaskQuery orderByTenantId() {
  	return orderBy(TaskQueryProperty.TENANT_ID);
  }
  
  public String getMssqlOrDB2OrderBy() {
    String specialOrderBy = super.getOrderBy();
    if (specialOrderBy != null && specialOrderBy.length() > 0) {
      specialOrderBy = specialOrderBy.replace("RES.", "TEMPRES_");
    }
    return specialOrderBy;
  }
  
  //results ////////////////////////////////////////////////////////////////

  public List<Task> executeList(CommandContext commandContext, Page page) {
    ensureVariablesInitialized();
    checkQueryOk();
    if (includeTaskLocalVariables || includeProcessVariables) {
    	
      return findTasksAndVariablesByQueryCriteria(commandContext.getDbSqlSession(),this);
    } else {
      return findTasksByQueryCriteria(commandContext.getDbSqlSession(),this);
    }
  }
  
  
  public long findTaskCountByQueryCriteria(DbSqlSession dbSqlSession,WfTaskQueryImpl taskQuery) {
	    return (Long) dbSqlSession.selectOne("selectTaskCountByQueryCriteria_wfQuery", taskQuery);
	    
}
  
  
  @SuppressWarnings("unchecked")
  public List<Task> findTasksByQueryCriteria(DbSqlSession dbSqlSession,WfTaskQueryImpl taskQuery) {
    final String query = "selectTaskByQueryCriteria_wfQuery";
    return dbSqlSession.selectList(query, taskQuery);
  }
  
  
  @SuppressWarnings("unchecked")
  public List<Task> findTasksAndVariablesByQueryCriteria(DbSqlSession dbSqlSession,WfTaskQueryImpl taskQuery) {
    final String query = "selectTaskWithVariablesByQueryCriteria_wfQuery";
    // paging doesn't work for combining task instances and variables due to an outer join, so doing it in-memory
    if (taskQuery.getFirstResult() < 0 || taskQuery.getMaxResults() <= 0) {
      return Collections.EMPTY_LIST;
    }
    
    int firstResult = taskQuery.getFirstResult();
    int maxResults = taskQuery.getMaxResults();
    
    // setting max results, limit to 20000 results for performance reasons
    taskQuery.setMaxResults(20000);
    taskQuery.setFirstResult(0);
    
    List<Task> instanceList = dbSqlSession.selectListWithRawParameterWithoutFilter(query, taskQuery, taskQuery.getFirstResult(), taskQuery.getMaxResults());
    
    if (instanceList != null && instanceList.size() > 0) {
      if (firstResult > 0) {
        if (firstResult <= instanceList.size()) {
          int toIndex = firstResult + Math.min(maxResults, instanceList.size() - firstResult);
          return instanceList.subList(firstResult, toIndex);
        } else {
          return Collections.EMPTY_LIST;
        }
      } else {
        int toIndex = Math.min(maxResults, instanceList.size());
        return instanceList.subList(0, toIndex);
      }
    }
    return Collections.EMPTY_LIST;
  }
  
  
  public long executeCount(CommandContext commandContext) {
    ensureVariablesInitialized();
    checkQueryOk();
    return findTaskCountByQueryCriteria(commandContext.getDbSqlSession(),this);
  }
  

	  
  
  //getters ////////////////////////////////////////////////////////////////

  public String getName() {
    return name;
  }
  public String getNameLike() {
    return nameLike;
  }
  public String getAssignee() {
    return assignee;
  }
  public boolean getUnassigned() {
    return unassigned;
  }
  public DelegationState getDelegationState() {
    return delegationState;
  }
  public boolean getNoDelegationState() {
    return noDelegationState;
  }
  public String getDelegationStateString() {
    return (delegationState!=null ? delegationState.toString() : null);
  }
  public String getCandidateUser() {
    return candidateUser;
  }
  public String getCandidateGroup() {
    return candidateGroup;
  }
  public String getProcessInstanceId() {
    return processInstanceId;
  }
  public String getExecutionId() {
    return executionId;
  }
  public String getTaskId() {
    return taskId;
  }
  public String getDescription() {
    return description;
  }
  public String getDescriptionLike() {
    return descriptionLike;
  }
  public Integer getPriority() {
    return priority;
  }
  public Date getCreateTime() {
    return createTime;
  }
  public Date getCreateTimeBefore() {
    return createTimeBefore;
  }
  public Date getCreateTimeAfter() {
    return createTimeAfter;
  }
  public String getKey() {
    return key;
  }
  public String getKeyLike() {
    return keyLike;
  }
  public String getProcessDefinitionKey() {
    return processDefinitionKey;
  }
  public String getProcessDefinitionId() {
    return processDefinitionId;
  }
  public String getProcessDefinitionName() {
    return processDefinitionName;
  }
  public String getProcessInstanceBusinessKey() {
    return processInstanceBusinessKey;
  }
  public boolean getExcludeSubtasks() {
    return excludeSubtasks;
  }
  public String getTenantId() {
	return tenantId;
  }
  public String getTenantIdLike() {
	return tenantIdLike;
  }
  public boolean isWithoutTenantId() {
	return withoutTenantId;
  }
  public String getUserIdForCandidateAndAssignee() {
    return userIdForCandidateAndAssignee;
  }
  public WfTaskQueryImpl getOrQueryObject() {
    return orQueryObject;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public Integer getMinPriority() {
    return minPriority;
  }

  public Integer getMaxPriority() {
    return maxPriority;
  }

  public String getAssigneeLike() {
    return assigneeLike;
  }

  public String getInvolvedUser() {
    return involvedUser;
  }

  public String getOwner() {
    return owner;
  }

  public String getOwnerLike() {
    return ownerLike;
  }

  public String getCategory() {
    return category;
  }

  public String getProcessDefinitionKeyLike() {
    return processDefinitionKeyLike;
  }

  public String getProcessDefinitionNameLike() {
    return processDefinitionNameLike;
  }

  public String getDeploymentId() {
    return deploymentId;
  }

  public String getProcessInstanceBusinessKeyLike() {
    return processInstanceBusinessKeyLike;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public Date getDueBefore() {
    return dueBefore;
  }

  public Date getDueAfter() {
    return dueAfter;
  }

  public boolean isWithoutDueDate() {
    return withoutDueDate;
  }

  public SuspensionState getSuspensionState() {
    return suspensionState;
  }

  public boolean isIncludeTaskLocalVariables() {
    return includeTaskLocalVariables;
  }

  public boolean isIncludeProcessVariables() {
    return includeProcessVariables;
  }

  public boolean isBothCandidateAndAssigned() {
    return bothCandidateAndAssigned;
  }

  public boolean isOrActive() {
    return orActive;
  }
  
}
