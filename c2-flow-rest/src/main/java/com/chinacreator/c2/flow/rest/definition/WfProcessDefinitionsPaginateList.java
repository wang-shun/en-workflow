package com.chinacreator.c2.flow.rest.definition;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.repository.ProcessDefinition;

import com.chinacreator.c2.flow.rest.common.WfAbstractPaginateList;
import com.chinacreator.c2.flow.rest.common.vo.WfProcessDefinitionResponse;


public class WfProcessDefinitionsPaginateList extends WfAbstractPaginateList<WfProcessDefinitionResponse> {
  
  @SuppressWarnings("rawtypes")
  @Override
  protected List<WfProcessDefinitionResponse> processList(List list) {
    List<WfProcessDefinitionResponse> responseList = new ArrayList<WfProcessDefinitionResponse>();
    for (Object obj : list) {
    	ProcessDefinition processDefinition=(ProcessDefinition)obj;
        WfProcessDefinitionResponse response = new WfProcessDefinitionResponse();
        response.setId(processDefinition.getId());
        response.setKey(processDefinition.getKey());
        response.setVersion(processDefinition.getVersion());
        response.setCategory(processDefinition.getCategory());
        response.setName(processDefinition.getName());
        response.setDescription(processDefinition.getDescription());
        response.setSuspended(processDefinition.isSuspended());
        response.setStartFormDefined(processDefinition.hasStartFormKey());
        
        response.setDeploymentId(processDefinition.getDeploymentId());
        response.setResourceName(processDefinition.getResourceName());
        if(processDefinition.getDiagramResourceName() != null) {
          response.setDiagramResourceName( processDefinition.getDiagramResourceName());
        }
        
        responseList.add(response);
    }
    return responseList;
  }
}
