/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

/**
 * @author Tijs Rademakers
 */
public class ModelEditorJsonRestResource extends ServerResource implements
		ModelDataJsonConstants {

	protected static final Logger LOGGER = LoggerFactory
			.getLogger(ModelEditorJsonRestResource.class);
	private ObjectMapper objectMapper = new ObjectMapper();

	@Get
	public ObjectNode getEditorJson() {
		ObjectNode modelNode = null;
		String modelId = (String) getRequest().getAttributes().get("modelId");

		try {
			WfRepositoryService wfRepositoryService = WfApiFactory
					.getWfRepositoryService();
			WfModel model = wfRepositoryService.getModelById(modelId);
			// RepositoryService repositoryService =
			// ProcessEngines.getDefaultProcessEngine().getRepositoryService();
			// Model model = repositoryService.getModel(modelId);

			if (model != null) {
				try {
					if (StringUtils.isNotEmpty(model.getMetaInfo())) {
						modelNode = (ObjectNode) objectMapper.readTree(model
								.getMetaInfo());
					} else {
						modelNode = objectMapper.createObjectNode();
						modelNode.put(MODEL_NAME, model.getName());
					}
					modelNode.put(MODEL_ID, model.getId());
					String modelEditorSource = wfRepositoryService
							.getModelEditorSource(model.getId());
					ObjectNode editorJsonNode = (ObjectNode) objectMapper
							.readTree(modelEditorSource);
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
