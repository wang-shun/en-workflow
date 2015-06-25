package com.chinacreator.c2.flow.modeler.services;

import org.activiti.rest.common.api.SecuredResource;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.restlet.resource.Get;

import com.chinacreator.c2.flow.WfApiFactory;

public class ProcessDefinitionDiagramLayoutResource extends SecuredResource {

	private String processInstanceId = null;
	private String processDefinitionId = null;

	@Get
	public ObjectNode getDiagram() {
		// TODO: do it all with Map and convert at the end to JSON
		processDefinitionId = (String) getRequest().getAttributes().get(
				"processDefinitionId");
		processInstanceId = (String) getRequest().getAttributes().get(
				"processInstanceId");
		ObjectNode responseJSON = new ObjectMapper().createObjectNode();
		try {
			String responseJSONStr = WfApiFactory.getWfManagerService().getDiagram(
					processInstanceId, processDefinitionId);
			responseJSON = new ObjectMapper().readValue(responseJSONStr, ObjectNode.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseJSON;
	}
}
