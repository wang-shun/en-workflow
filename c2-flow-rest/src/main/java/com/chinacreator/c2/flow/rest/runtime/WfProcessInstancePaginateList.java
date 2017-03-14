package com.chinacreator.c2.flow.rest.runtime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.rest.service.api.engine.variable.RestVariable.RestVariableScope;

import com.chinacreator.c2.flow.rest.common.C2RestResponseFactory;
import com.chinacreator.c2.flow.rest.common.WfAbstractPaginateList;
import com.chinacreator.c2.flow.rest.common.vo.WfProcessInstanceResponse;

/**
 * @author hushowly
 */
public class WfProcessInstancePaginateList extends WfAbstractPaginateList<WfProcessInstanceResponse> {

  @SuppressWarnings("rawtypes")
  @Override
  protected List<WfProcessInstanceResponse> processList(List list) {
	  
    List<WfProcessInstanceResponse> responseList = new ArrayList<WfProcessInstanceResponse>();
    
    for (Object instance : list) {
    	ProcessInstance processInstance =(ProcessInstance) instance;
        WfProcessInstanceResponse result = new WfProcessInstanceResponse();
        result.setActivityId(processInstance.getActivityId());
        result.setBusinessKey(processInstance.getBusinessKey());
        result.setId(processInstance.getId());
        result.setProcessDefinitionId(processInstance.getProcessDefinitionId());
        result.setSuspended(processInstance.isSuspended());
        result.setTenantId(processInstance.getTenantId());
        if (processInstance.getProcessVariables() != null) {
          Map<String, Object> variableMap = processInstance.getProcessVariables();
          C2RestResponseFactory c2RestResponseFactory=new C2RestResponseFactory();
          for (String name : variableMap.keySet()) {
            result.addVariable(c2RestResponseFactory.createRestVariable(name, variableMap.get(name), 
                RestVariableScope.LOCAL, processInstance.getId(),C2RestResponseFactory.VARIABLE_PROCESS, false));
          }
        }
      responseList.add(result);
    }
    return responseList;
  }
}
