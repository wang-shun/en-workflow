package com.chinacreator.c2.flow.rest.history;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.rest.service.api.RestResponseFactory;
import org.activiti.rest.service.api.engine.variable.RestVariable.RestVariableScope;

import com.chinacreator.c2.flow.rest.common.C2RestResponseFactory;
import com.chinacreator.c2.flow.rest.common.WfAbstractPaginateList;
import com.chinacreator.c2.flow.rest.common.vo.WfHistoricTaskInstanceResponse;

/**
 * @author hushowly
 */
public class WfHistoricTaskInstancePaginateList extends WfAbstractPaginateList<WfHistoricTaskInstanceResponse> {

  
  @SuppressWarnings("rawtypes")
  @Override
  protected List<WfHistoricTaskInstanceResponse> processList(List list) {
    List<WfHistoricTaskInstanceResponse> responseList = new ArrayList<WfHistoricTaskInstanceResponse>();
    C2RestResponseFactory restResponseFactory = new C2RestResponseFactory();
    for (Object instance : list) {
    	HistoricTaskInstance  taskInstance=(HistoricTaskInstance)instance;
    	WfHistoricTaskInstanceResponse result = new WfHistoricTaskInstanceResponse();
        result.setAssignee(taskInstance.getAssignee());
        result.setClaimTime(taskInstance.getClaimTime());
        result.setDeleteReason(taskInstance.getDeleteReason());
        result.setDescription(taskInstance.getDescription());
        result.setDueDate(taskInstance.getDueDate());
        result.setDurationInMillis(taskInstance.getDurationInMillis());
        result.setEndTime(taskInstance.getEndTime());
        result.setExecutionId(taskInstance.getExecutionId());
        result.setFormKey(taskInstance.getFormKey());
        result.setId(taskInstance.getId());
        result.setName(taskInstance.getName());
        result.setOwner(taskInstance.getOwner());
        result.setParentTaskId(taskInstance.getParentTaskId());
        result.setPriority(taskInstance.getPriority());
        result.setProcessDefinitionId(taskInstance.getProcessDefinitionId());
        result.setTenantId(taskInstance.getTenantId());
        result.setCategory(taskInstance.getCategory());
        result.setProcessInstanceId(taskInstance.getProcessInstanceId());
        result.setStartTime(taskInstance.getStartTime());
        result.setTaskDefinitionKey(taskInstance.getTaskDefinitionKey());
        result.setWorkTimeInMillis(taskInstance.getWorkTimeInMillis());
        if (taskInstance.getProcessVariables() != null) {
          Map<String, Object> variableMap = taskInstance.getProcessVariables();
          for (String name : variableMap.keySet()) {
            result.addVariable(restResponseFactory.createRestVariable(name, variableMap.get(name), 
                RestVariableScope.GLOBAL, taskInstance.getId(), RestResponseFactory.VARIABLE_HISTORY_TASK, false));
          }
        }
        if (taskInstance.getTaskLocalVariables() != null) {
          Map<String, Object> variableMap = taskInstance.getTaskLocalVariables();
          for (String name : variableMap.keySet()) {
            result.addVariable(restResponseFactory.createRestVariable(name, variableMap.get(name), 
                RestVariableScope.LOCAL, taskInstance.getId(), RestResponseFactory.VARIABLE_HISTORY_TASK, false));
          }
        }
      responseList.add(result);
    }
    return responseList;
  }
}
