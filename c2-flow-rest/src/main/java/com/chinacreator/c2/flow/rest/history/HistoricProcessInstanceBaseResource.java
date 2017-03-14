
package com.chinacreator.c2.flow.rest.history;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.impl.HistoricProcessInstanceQueryProperty;
import org.activiti.engine.query.QueryProperty;
import org.activiti.rest.service.api.engine.variable.QueryVariable.QueryVariableOperation;

import com.chinacreator.c2.flow.rest.common.C2RestResponseFactory;
import com.chinacreator.c2.flow.rest.common.vo.WfHistoricProcessInstanceQueryRequest;
import com.chinacreator.c2.flow.rest.common.vo.WfHistoricProcessInstanceResponse;
import com.chinacreator.c2.flow.rest.common.vo.WfPageListResponse;
import com.chinacreator.c2.flow.rest.common.vo.WfQueryVariable;

/**
 * @author hushowly
 */
public class HistoricProcessInstanceBaseResource {

  private static Map<String, QueryProperty> allowedSortProperties = new HashMap<String, QueryProperty>();

  static {
	  
    allowedSortProperties.put("processInstanceId", HistoricProcessInstanceQueryProperty.PROCESS_INSTANCE_ID_);
    allowedSortProperties.put("processDefinitionId", HistoricProcessInstanceQueryProperty.PROCESS_DEFINITION_ID);
    allowedSortProperties.put("businessKey", HistoricProcessInstanceQueryProperty.BUSINESS_KEY);
    allowedSortProperties.put("startTime", HistoricProcessInstanceQueryProperty.START_TIME);
    allowedSortProperties.put("endTime", HistoricProcessInstanceQueryProperty.END_TIME);
    allowedSortProperties.put("duration", HistoricProcessInstanceQueryProperty.DURATION);
    allowedSortProperties.put("tenantId", HistoricProcessInstanceQueryProperty.TENANT_ID);
  }
  
  
  protected WfPageListResponse<WfHistoricProcessInstanceResponse> getQueryResponse(HistoricProcessInstanceQuery query,WfHistoricProcessInstanceQueryRequest queryRequest) {
    
    // Populate query based on request
    if (queryRequest.getProcessInstanceId() != null) {
      query.processInstanceId(queryRequest.getProcessInstanceId());
    }
    if (queryRequest.getProcessInstanceIds() != null && queryRequest.getProcessInstanceIds().size() > 0) {
      query.processInstanceIds(new HashSet<String>(queryRequest.getProcessInstanceIds()));
    }
    if (queryRequest.getProcessDefinitionKey() != null) {
      query.processDefinitionKey(queryRequest.getProcessDefinitionKey());
    }
    if (queryRequest.getProcessDefinitionId() != null) {
      query.processDefinitionId(queryRequest.getProcessDefinitionId());
    }
    if (queryRequest.getProcessBusinessKey() != null) {
      query.processInstanceBusinessKey(queryRequest.getProcessBusinessKey());
    }
    if (queryRequest.getInvolvedUser() != null) {
      query.involvedUser(queryRequest.getInvolvedUser());
    }
    if (queryRequest.getSuperProcessInstanceId() != null) {
      query.superProcessInstanceId(queryRequest.getSuperProcessInstanceId());
    }
    if (queryRequest.getExcludeSubprocesses() != null) {
      query.excludeSubprocesses(queryRequest.getExcludeSubprocesses());
    }
    if (queryRequest.getFinishedAfter() != null) {
      query.finishedAfter(queryRequest.getFinishedAfter());
    }
    if (queryRequest.getFinishedBefore() != null) {
      query.finishedBefore(queryRequest.getFinishedBefore());
    }
    if (queryRequest.getStartedAfter() != null) {
      query.startedAfter(queryRequest.getStartedAfter());
    }
    if (queryRequest.getStartedBefore() != null) {
      query.startedBefore(queryRequest.getStartedBefore());
    }
    if (queryRequest.getStartedBy() != null) {
      query.startedBy(queryRequest.getStartedBy());
    }
    if (queryRequest.getFinished() != null) {
      if (queryRequest.getFinished()) {
        query.finished();
      } else {
        query.unfinished();
      }
    }
    if (queryRequest.getIncludeProcessVariables() != null) {
      if (queryRequest.getIncludeProcessVariables()) {
        query.includeProcessVariables();
      }
    }
    if (queryRequest.getVariables() != null) {
      addVariables(query, queryRequest.getVariables());
    }
    
    if(queryRequest.getTenantId() != null) {
    	query.processInstanceTenantId(queryRequest.getTenantId());
    }
    
    if(queryRequest.getTenantIdLike() != null) {
    	query.processInstanceTenantIdLike(queryRequest.getTenantIdLike());
    }
    
    if(Boolean.TRUE.equals(queryRequest.getWithoutTenantId())) {
    	query.processInstanceWithoutTenantId();
    }

    return new WfHistoricProcessInstancePaginateList().paginateList(queryRequest, query, "processInstanceId", allowedSortProperties);
  }

  protected void addVariables(HistoricProcessInstanceQuery processInstanceQuery, List<WfQueryVariable> variables) {
    C2RestResponseFactory responseFactory =new C2RestResponseFactory();
    
    for (WfQueryVariable variable : variables) {
      if (variable.getVariableOperation() == null) {
        throw new ActivitiIllegalArgumentException("Variable operation is missing for variable: " + variable.getName());
      }
      if (variable.getValue() == null) {
        throw new ActivitiIllegalArgumentException("Variable value is missing for variable: " + variable.getName());
      }

      boolean nameLess = variable.getName() == null;

      Object actualValue = responseFactory.getVariableValue(variable);

      // A value-only query is only possible using equals-operator
      if (nameLess && variable.getVariableOperation() != QueryVariableOperation.EQUALS) {
        throw new ActivitiIllegalArgumentException("Value-only query (without a variable-name) is only supported when using 'equals' operation.");
      }

      switch (variable.getVariableOperation()) {

      case EQUALS:
        if (nameLess) {
          processInstanceQuery.variableValueEquals(actualValue);
        } else {
          processInstanceQuery.variableValueEquals(variable.getName(), actualValue);
        }
        break;

      case EQUALS_IGNORE_CASE:
        if (actualValue instanceof String) {
          processInstanceQuery.variableValueEqualsIgnoreCase(variable.getName(), (String) actualValue);
        } else {
          throw new ActivitiIllegalArgumentException("Only string variable values are supported when ignoring casing, but was: "
                  + actualValue.getClass().getName());
        }
        break;

      case NOT_EQUALS:
        processInstanceQuery.variableValueNotEquals(variable.getName(), actualValue);
        break;
        
      case LIKE:
        if (actualValue instanceof String) {
          processInstanceQuery.variableValueLike(variable.getName(), (String) actualValue);
        } else {
          throw new ActivitiIllegalArgumentException("Only string variable values are supported for like, but was: "
                  + actualValue.getClass().getName());
        }
        break;
        
      case GREATER_THAN:
        processInstanceQuery.variableValueGreaterThan(variable.getName(), actualValue);
        break;
        
      case GREATER_THAN_OR_EQUALS:
        processInstanceQuery.variableValueGreaterThanOrEqual(variable.getName(), actualValue);
        break;

      case LESS_THAN:
        processInstanceQuery.variableValueLessThan(variable.getName(), actualValue);
        break;
        
      case LESS_THAN_OR_EQUALS:
        processInstanceQuery.variableValueLessThanOrEqual(variable.getName(), actualValue);
        break;

      default:
        throw new ActivitiIllegalArgumentException("Unsupported variable query operation: " + variable.getVariableOperation());
      }
    }
  }
}
