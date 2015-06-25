package com.chinacreator.c2.flow.modeler.services;

import org.activiti.engine.ActivitiException;
import org.activiti.rest.common.api.SecuredResource;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.restlet.resource.Get;

import com.chinacreator.c2.flow.WfApiFactory;

public class ProcessInstanceHighlightsResource extends SecuredResource {

	@Get
	public ObjectNode getHighlighted() {
		String processInstanceId = (String) getRequest().getAttributes().get(
				"processInstanceId");

		if (processInstanceId == null) {
			throw new ActivitiException("No process instance id provided");
		}

		ObjectNode responseJSON = new ObjectMapper().createObjectNode();

		try {
			String responseJSONStr = WfApiFactory.getWfManagerService()
					.getHighlighted(processInstanceId);
			responseJSON = new ObjectMapper().readValue(responseJSONStr,
					ObjectNode.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseJSON;
	}
}
