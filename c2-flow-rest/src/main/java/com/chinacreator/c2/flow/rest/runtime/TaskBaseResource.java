

package com.chinacreator.c2.flow.rest.runtime;

import java.util.HashMap;
import java.util.List;

import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.impl.TaskQueryProperty;
import org.activiti.engine.query.QueryProperty;
import org.activiti.engine.task.DelegationState;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.rest.service.api.engine.variable.QueryVariable.QueryVariableOperation;
import org.activiti.rest.service.api.runtime.task.TaskRequest;
import org.springframework.util.StringUtils;

import com.chinacreator.c2.flow.rest.common.C2RestResponseFactory;
import com.chinacreator.c2.flow.rest.common.vo.WfPageListResponse;
import com.chinacreator.c2.flow.rest.common.vo.WfQueryVariable;
import com.chinacreator.c2.flow.rest.common.vo.WfTaskQueryRequest;
import com.chinacreator.c2.flow.rest.common.vo.WfTaskRequest;
import com.chinacreator.c2.flow.rest.common.vo.WfTaskResponse;


/**
 * @author hushowly
 */
public class TaskBaseResource {
  
  private static HashMap<String, QueryProperty> properties = new HashMap<String, QueryProperty>();
  
  static {
    properties.put("id", TaskQueryProperty.TASK_ID);
    properties.put("name", TaskQueryProperty.NAME);
    properties.put("description", TaskQueryProperty.DESCRIPTION);
    properties.put("dueDate", TaskQueryProperty.DUE_DATE);
    properties.put("createTime", TaskQueryProperty.CREATE_TIME);
    properties.put("priority", TaskQueryProperty.PRIORITY);
    properties.put("executionId", TaskQueryProperty.EXECUTION_ID);
    properties.put("processInstanceId", TaskQueryProperty.PROCESS_INSTANCE_ID);
    properties.put("tenantId", TaskQueryProperty.TENANT_ID);
  }

  protected DelegationState getDelegationState(String delegationState) {
    DelegationState state = null;
    if(delegationState != null) {
      if(DelegationState.RESOLVED.name().toLowerCase().equals(delegationState)) {
        return DelegationState.RESOLVED;
      } else if(DelegationState.PENDING.name().toLowerCase().equals(delegationState)) {
        return DelegationState.PENDING;
      } else {
        throw new ActivitiIllegalArgumentException("Illegal value for delegationState: " + delegationState);
      }
    }
    return state;
  }
  
  /**
   * Populate the task based on the values that are present in the given {@link TaskRequest}.
   */
  protected void populateTaskFromRequest(Task task, WfTaskRequest taskRequest) {
    if(StringUtils.hasText(taskRequest.getName())) {
      task.setName(taskRequest.getName());
    }
    if(StringUtils.hasText(taskRequest.getAssignee())) {
      task.setAssignee(taskRequest.getAssignee());
    }
    if(StringUtils.hasText(taskRequest.getDescription())) {
      task.setDescription(taskRequest.getDescription());
    }
    
    if(null!=taskRequest.getDueDate()) {
      task.setDueDate(taskRequest.getDueDate());
    }
    if(StringUtils.hasText(taskRequest.getOwner())) {
      task.setOwner(taskRequest.getOwner());
    }
    if(StringUtils.hasText(taskRequest.getParentTaskId())) {
      task.setParentTaskId(taskRequest.getParentTaskId());
    }
    if(null!=taskRequest.getPriority()) {
      task.setPriority(taskRequest.getPriority());
    }
    if(StringUtils.hasText(taskRequest.getCategory())) {
    	task.setCategory(taskRequest.getCategory());
    }

    if(null!=taskRequest.getDelegationState()) {
      //DelegationState delegationState = getDelegationState(taskRequest.getDelegationState());
      task.setDelegationState(taskRequest.getDelegationState());
    }
  }
  
  protected WfPageListResponse<WfTaskResponse> getTasksFromQueryRequest(TaskQuery taskQuery,WfTaskQueryRequest request) {
    
    // Populate filter-parameters
    if(request.getName() != null) {
      taskQuery.taskName(request.getName());
    }
    if(request.getNameLike() != null) {
      taskQuery.taskNameLike(request.getNameLike());
    }
    if(request.getDescription() != null) {
      taskQuery.taskDescription(request.getDescription());
    }
    if(request.getDescriptionLike() != null) {
      taskQuery.taskDescriptionLike(request.getDescriptionLike());
    }
    if(request.getPriority() != null) {
      taskQuery.taskPriority(request.getPriority());
    }
    if(request.getMinimumPriority() != null) {
      taskQuery.taskMinPriority(request.getMinimumPriority());
    }
    if(request.getMaximumPriority() != null) {
      taskQuery.taskMaxPriority(request.getMaximumPriority());
    }
    if(request.getAssignee() != null) {
      taskQuery.taskAssignee(request.getAssignee());
    }
    if(request.getAssigneeLike() != null) {
      taskQuery.taskAssigneeLike(request.getAssigneeLike());
    }
    if(request.getOwner() != null) {
      taskQuery.taskOwner(request.getOwner());
    }
    if(request.getOwnerLike() != null) {
      taskQuery.taskOwnerLike(request.getOwnerLike());
    }
    if(request.getUnassigned() != null) {
      taskQuery.taskUnassigned();
    }
    if(request.getDelegationState() != null) {
      DelegationState state = getDelegationState(request.getDelegationState());
      if(state != null) {
        taskQuery.taskDelegationState(state);
      }
    }
    
	if (request.getCandidateUser() != null) {
		taskQuery.taskCandidateUser(request.getCandidateUser());
	}
	
	if (request.getCandidateGroupIn() != null) {
		taskQuery.taskCandidateGroupIn(request.getCandidateGroupIn());
	}
	
	if (request.getInvolvedUser() != null) {
		taskQuery.taskInvolvedUser(request.getInvolvedUser());
	}
	
    if(request.getProcessInstanceId() != null) {
      taskQuery.processInstanceId(request.getProcessInstanceId());
    }
    if(request.getProcessInstanceBusinessKey() != null) {
      taskQuery.processInstanceBusinessKey(request.getProcessInstanceBusinessKey());
    }
    if(request.getExecutionId() != null) {
      taskQuery.executionId(request.getExecutionId());
    }
    if(request.getCreatedOn() != null) {
      taskQuery.taskCreatedOn(request.getCreatedOn());
    }
    if(request.getCreatedBefore() != null) {
      taskQuery.taskCreatedBefore(request.getCreatedBefore());
    }
    if(request.getCreatedAfter() != null) {
      taskQuery.taskCreatedAfter(request.getCreatedAfter());
    }
    if(request.getExcludeSubTasks() != null) {
      if(request.getExcludeSubTasks().booleanValue()) {
        taskQuery.excludeSubtasks();
      }
    }

    if(request.getTaskDefinitionKey() != null) {
      taskQuery.taskDefinitionKey(request.getTaskDefinitionKey());
    }
    taskQuery.taskDefinitionKeyLike(request.getTaskDefinitionKeyLike());
    if(request.getDueDate() != null) {
      taskQuery.dueDate(request.getDueDate());
    }
    if(request.getDueBefore() != null) {
      taskQuery.dueBefore(request.getDueBefore());
    }
    if(request.getDueAfter() != null) {
      taskQuery.dueAfter(request.getDueAfter());
    }
    if(request.getWithoutDueDate() != null && request.getWithoutDueDate()) {
    	taskQuery.withoutDueDate();
    }
    
    if(request.getActive() != null) {
      if(request.getActive().booleanValue()) {
        taskQuery.active();
      } else {
        taskQuery.suspended();
      }
    }
    
    if (request.getIncludeTaskLocalVariables() != null) {
      if (request.getIncludeTaskLocalVariables()) {
        taskQuery.includeTaskLocalVariables();
      }
    }
    if (request.getIncludeProcessVariables() != null) {
      if (request.getIncludeProcessVariables()) {
        taskQuery.includeProcessVariables();
      }
    }
    
    if(request.getProcessInstanceBusinessKeyLike() != null) {
    	taskQuery.processInstanceBusinessKeyLike(request.getProcessInstanceBusinessKeyLike());
    }
    
    if(request.getProcessDefinitionKey() != null) {
    	taskQuery.processDefinitionKey(request.getProcessDefinitionKey());
    }
    
    if(request.getProcessDefinitionKeyLike() != null) {
    	taskQuery.processDefinitionKeyLike(request.getProcessDefinitionKeyLike());
    }
    
    if(request.getProcessDefinitionName() != null) {
    	taskQuery.processDefinitionName(request.getProcessDefinitionName());
    }
    
    if(request.getProcessDefinitionNameLike() != null) {
    	taskQuery.processDefinitionNameLike(request.getProcessDefinitionNameLike());
    }
    
    if(request.getTaskVariables() != null) {
      addTaskvariables(taskQuery, request.getTaskVariables());
    }
    
    if(request.getProcessInstanceVariables() != null) {
      addProcessvariables(taskQuery, request.getProcessInstanceVariables());
    }
    
    if(request.getTenantId() != null) {
    	taskQuery.taskTenantId(request.getTenantId());
    }
    
    if(request.getTenantIdLike() != null) {
    	taskQuery.taskTenantIdLike(request.getTenantIdLike());
    }
    
    if(Boolean.TRUE.equals(request.getWithoutTenantId())) {
    	taskQuery.taskWithoutTenantId();
    }
    
	if(request.getCandidateOrAssigned()!=null){
		taskQuery.taskCandidateOrAssigned(request.getCandidateOrAssigned());
	}
    
    return new WfTaskPaginateList().paginateList(request, taskQuery, "id", properties);
  }
  
  protected void addTaskvariables(TaskQuery taskQuery, List<WfQueryVariable> variables) {
	  
	C2RestResponseFactory responseFactory = new C2RestResponseFactory();
	  
    for(WfQueryVariable variable : variables) {
      if(variable.getVariableOperation() == null) {
        throw new ActivitiIllegalArgumentException("Variable operation is missing for variable: " + variable.getName());
      }
      if(variable.getValue() == null) {
        throw new ActivitiIllegalArgumentException("Variable value is missing for variable: " + variable.getName());
      }
      
      boolean nameLess = variable.getName() == null;
      
      Object actualValue = responseFactory.getVariableValue(variable);
      
      // A value-only query is only possible using equals-operator
      if(nameLess && variable.getVariableOperation() != QueryVariableOperation.EQUALS) {
        throw new ActivitiIllegalArgumentException("Value-only query (without a variable-name) is only supported when using 'equals' operation.");
      }
      
      switch(variable.getVariableOperation()) {
      
      case EQUALS:
        if(nameLess) {
          taskQuery.taskVariableValueEquals(actualValue);
        } else {
          taskQuery.taskVariableValueEquals(variable.getName(), actualValue);
        }
        break;
        
      case EQUALS_IGNORE_CASE:
        if(actualValue instanceof String) {
          taskQuery.taskVariableValueEqualsIgnoreCase(variable.getName(), (String)actualValue);
        } else {
          throw new ActivitiIllegalArgumentException("Only string variable values are supported when ignoring casing, but was: " + actualValue.getClass().getName());
        }
        break;
        
      case NOT_EQUALS:
        taskQuery.taskVariableValueNotEquals(variable.getName(), actualValue);
        break;
        
      case NOT_EQUALS_IGNORE_CASE:
        if(actualValue instanceof String) {
          taskQuery.taskVariableValueNotEqualsIgnoreCase(variable.getName(), (String)actualValue);
        } else {
          throw new ActivitiIllegalArgumentException("Only string variable values are supported when ignoring casing, but was: " + actualValue.getClass().getName());
        }
        break;
        
      case GREATER_THAN:
      	taskQuery.taskVariableValueGreaterThan(variable.getName(), actualValue);
      	break;
      	
      case GREATER_THAN_OR_EQUALS:
      	taskQuery.taskVariableValueGreaterThanOrEqual(variable.getName(), actualValue);
      	break;
      	
      case LESS_THAN:
      	taskQuery.taskVariableValueLessThan(variable.getName(), actualValue);
      	break;
      	
      case LESS_THAN_OR_EQUALS:
      	taskQuery.taskVariableValueLessThanOrEqual(variable.getName(), actualValue);
      	break;
      	
      case LIKE:
      	if(actualValue instanceof String) {
      		taskQuery.taskVariableValueLike(variable.getName(), (String) actualValue);
      	} else {
      		throw new ActivitiIllegalArgumentException("Only string variable values are supported using like, but was: " + actualValue.getClass().getName());
      	}
      	break;
      default:
        throw new ActivitiIllegalArgumentException("Unsupported variable query operation: " + variable.getVariableOperation());
      }
    }
  }
  
  protected void addProcessvariables(TaskQuery taskQuery, List<WfQueryVariable> variables) {
	  C2RestResponseFactory responseFactory = new C2RestResponseFactory();
    
    for(WfQueryVariable variable : variables) {
      if(variable.getVariableOperation() == null) {
        throw new ActivitiIllegalArgumentException("Variable operation is missing for variable: " + variable.getName());
      }
      if(variable.getValue() == null) {
        throw new ActivitiIllegalArgumentException("Variable value is missing for variable: " + variable.getName());
      }
      
      boolean nameLess = variable.getName() == null;
      
      Object actualValue = responseFactory.getVariableValue(variable);
      
      // A value-only query is only possible using equals-operator
      if(nameLess && variable.getVariableOperation() != QueryVariableOperation.EQUALS) {
        throw new ActivitiIllegalArgumentException("Value-only query (without a variable-name) is only supported when using 'equals' operation.");
      }
      
switch(variable.getVariableOperation()) {
      
      case EQUALS:
        if(nameLess) {
          taskQuery.processVariableValueEquals(actualValue);
        } else {
          taskQuery.processVariableValueEquals(variable.getName(), actualValue);
        }
        break;
        
      case EQUALS_IGNORE_CASE:
        if(actualValue instanceof String) {
          taskQuery.processVariableValueEqualsIgnoreCase(variable.getName(), (String)actualValue);
        } else {
          throw new ActivitiIllegalArgumentException("Only string variable values are supported when ignoring casing, but was: " + actualValue.getClass().getName());
        }
        break;
        
      case NOT_EQUALS:
        taskQuery.processVariableValueNotEquals(variable.getName(), actualValue);
        break;
        
      case NOT_EQUALS_IGNORE_CASE:
        if(actualValue instanceof String) {
          taskQuery.processVariableValueNotEqualsIgnoreCase(variable.getName(), (String)actualValue);
        } else {
          throw new ActivitiIllegalArgumentException("Only string variable values are supported when ignoring casing, but was: " + actualValue.getClass().getName());
        }
        break;
        
      case GREATER_THAN:
      	taskQuery.processVariableValueGreaterThan(variable.getName(), actualValue);
      	break;
      	
      case GREATER_THAN_OR_EQUALS:
      	taskQuery.processVariableValueGreaterThanOrEqual(variable.getName(), actualValue);
      	break;
      	
      case LESS_THAN:
      	taskQuery.processVariableValueLessThan(variable.getName(), actualValue);
      	break;
      	
      case LESS_THAN_OR_EQUALS:
      	taskQuery.processVariableValueLessThanOrEqual(variable.getName(), actualValue);
      	break;
      	
      case LIKE:
      	if(actualValue instanceof String) {
      		taskQuery.processVariableValueLike(variable.getName(), (String) actualValue);
      	} else {
      		throw new ActivitiIllegalArgumentException("Only string variable values are supported using like, but was: " + actualValue.getClass().getName());
      	}
      	break;
      default:
        throw new ActivitiIllegalArgumentException("Unsupported variable query operation: " + variable.getVariableOperation());
      }
    }
  }
 
}
