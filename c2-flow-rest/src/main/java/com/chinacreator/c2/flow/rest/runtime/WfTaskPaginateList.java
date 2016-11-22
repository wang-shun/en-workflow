package com.chinacreator.c2.flow.rest.runtime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.task.Task;
import org.activiti.rest.service.api.engine.variable.RestVariable.RestVariableScope;

import com.chinacreator.c2.flow.rest.common.C2RestResponseFactory;
import com.chinacreator.c2.flow.rest.common.WfAbstractPaginateList;
import com.chinacreator.c2.flow.rest.common.vo.WfTaskResponse;

/**
 * @author hushow
 */
public class WfTaskPaginateList extends WfAbstractPaginateList<WfTaskResponse> {
  
  @SuppressWarnings("rawtypes")
  @Override
  protected List<WfTaskResponse> processList(List list) {
    List<WfTaskResponse> responseList = new ArrayList<WfTaskResponse>();
    for (Object t : list){
    	Task task=(Task)t;
    	WfTaskResponse response = new WfTaskResponse((Task)task);
    	C2RestResponseFactory c2RestResponseFactory=new C2RestResponseFactory();
    	
        if (task.getProcessVariables() != null) {
            Map<String, Object> variableMap = task.getProcessVariables();
            for (String name : variableMap.keySet()) {
              response.addVariable(c2RestResponseFactory.createRestVariable(name, variableMap.get(name), 
                  RestVariableScope.GLOBAL, task.getId(), C2RestResponseFactory.VARIABLE_TASK, false));
            }
          }
          if (task.getTaskLocalVariables() != null) {
            Map<String, Object> variableMap = task.getTaskLocalVariables();
            for (String name : variableMap.keySet()) {
              response.addVariable(c2RestResponseFactory.createRestVariable(name, variableMap.get(name), 
                  RestVariableScope.LOCAL, task.getId(), C2RestResponseFactory.VARIABLE_TASK, false));
            }
          }
          
    	responseList.add(response);
    }
    return responseList;
  }
}
