package com.chinacreator.c2.flow.rest.definition;

import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;


/**
 * 流程定义
 * @author hushow
 *
 */
public class BaseProcessDefinitionResource {

	
	protected ProcessDefinition getProcessDefinitionFromRequest(ProcessDefinitionQuery processDefinitionQuery,String processDefinitionId) {
		if (processDefinitionId == null) {
			throw new ActivitiIllegalArgumentException(
					"The processDefinitionId cannot be null");
		}

		
		ProcessDefinition processDefinition = processDefinitionQuery.processDefinitionId(processDefinitionId).singleResult();
		
		if (processDefinition == null) {
			throw new ActivitiObjectNotFoundException(
					"Could not find a process definition with id '"
							+ processDefinitionId + "'.",
					ProcessDefinition.class);
		}

		return processDefinition;
	}
	  
}
