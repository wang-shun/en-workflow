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
import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinacreator.c2.flow.WfApiFactory;

/**
 * @author Tijs Rademakers
 */
public class ModelSaveRestResource extends ServerResource implements
		ModelDataJsonConstants {

	protected static final Logger LOGGER = LoggerFactory
			.getLogger(ModelSaveRestResource.class);

	@Put
	public void saveModel(Form modelForm) {
		String modelId = (String) getRequest().getAttributes().get("modelId");
		String tenantId = (String) getRequest().getAttributes().get("tenantId");
		
		try {
			
			//如果开启了分布式，将分布式应用名称作为tenantId
			if(null==tenantId||"".equals(tenantId)) tenantId=WfApiFactory.getWfTenant();
			
			String name = modelForm.getFirstValue("name");
			String description = modelForm.getFirstValue("description");
			String json_xml = modelForm.getFirstValue("json_xml");
			String svg_xml = modelForm.getFirstValue("svg_xml");
			WfApiFactory.getWfManagerService().saveModel(modelId, tenantId,
					name, description, json_xml, svg_xml);
		} catch (Exception e) {
			LOGGER.error("Error saving model", e);
			setStatus(Status.SERVER_ERROR_INTERNAL);
		}
	}

}
