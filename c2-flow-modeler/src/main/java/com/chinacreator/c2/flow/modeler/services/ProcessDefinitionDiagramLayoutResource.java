package com.chinacreator.c2.flow.modeler.services;

import java.io.InputStream;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.rest.common.api.SecuredResource;
import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.restlet.resource.Get;

import com.chinacreator.c2.ioc.ApplicationContextManager;

public class ProcessDefinitionDiagramLayoutResource extends SecuredResource {

	private String processInstanceId = null;
	private String processDefinitionId = null;

	@Get
	public String getDiagram() {
		RepositoryService repositoryService = ApplicationContextManager.getContext().getBean(RepositoryService.class);
		
		// TODO: do it all with Map and convert at the end to JSON
		processDefinitionId = (String) getRequest().getAttributes().get(
				"processDefinitionId");
//		processInstanceId = (String) getRequest().getAttributes().get(
//				"processInstanceId");
//		ObjectNode responseJSON = new ObjectMapper().createObjectNode();
		String bpmnXml = null;
		try {
			InputStream in = repositoryService.getProcessModel(processDefinitionId);
			bpmnXml = IOUtils.toString(in, "UTF-8");
//			ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
//					.getDeployedProcessDefinition(processDefinitionId);

			
//			String responseJSONStr = WfApiFactory.getWfManagerService().getDiagram(
//					processInstanceId, processDefinitionId);
//			responseJSON = new ObjectMapper().readValue(responseJSONStr, ObjectNode.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

//		return responseJSON;
		return bpmnXml;
	}
}
