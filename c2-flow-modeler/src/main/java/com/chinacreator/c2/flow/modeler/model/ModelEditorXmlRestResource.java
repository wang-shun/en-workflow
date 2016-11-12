package com.chinacreator.c2.flow.modeler.model;

import org.activiti.editor.constants.ModelDataJsonConstants;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinacreator.c2.flow.WfApiFactory;
import com.chinacreator.c2.flow.api.WfRepositoryService;
import com.chinacreator.c2.flow.detail.WfModel;

public class ModelEditorXmlRestResource extends ServerResource implements ModelDataJsonConstants {

	protected static final Logger LOGGER = LoggerFactory.getLogger(ModelEditorJsonRestResource.class);
	private ObjectMapper objectMapper = new ObjectMapper();

	@Get
	public ObjectNode getEditorXml() {
		ObjectNode modelNode = null;
		String modelId = (String) getRequest().getAttributes().get("modelId");

		try {
			WfRepositoryService wfRepositoryService = WfApiFactory.getWfRepositoryService();
			WfModel model = wfRepositoryService.getModelById(modelId);
			// RepositoryService repositoryService =
			// ProcessEngines.getDefaultProcessEngine().getRepositoryService();
			// Model model = repositoryService.getModel(modelId);

			if (model != null) {
				try {
					if (StringUtils.isNotEmpty(model.getMetaInfo())) {
						modelNode = (ObjectNode) objectMapper.readTree(model.getMetaInfo());
					} else {
						modelNode = objectMapper.createObjectNode();
						modelNode.put(MODEL_NAME, model.getName());
					}
					modelNode.put(MODEL_ID, model.getId());
					String modelEditorSource = wfRepositoryService.getModelEditorSource(model.getId());
					ObjectNode editorJsonNode = (ObjectNode) objectMapper.readTree(modelEditorSource);
					modelNode.put("model", editorJsonNode);

				} catch (Exception e) {
					LOGGER.error("Error creating model JSON", e);
					setStatus(Status.SERVER_ERROR_INTERNAL);
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return modelNode;
	}
}
