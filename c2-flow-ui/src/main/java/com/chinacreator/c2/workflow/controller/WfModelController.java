package com.chinacreator.c2.workflow.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chinacreator.c2.flow.WfApiFactory;
import com.chinacreator.c2.flow.api.WfManagerService;
import com.chinacreator.c2.flow.api.WfRepositoryService;
import com.chinacreator.c2.flow.detail.WfPageList;
import com.chinacreator.c2.flow.detail.WfProcessDefinition;
import com.chinacreator.c2.flow.detail.WfProcessDefinitionParam;
import com.chinacreator.c2.web.controller.ResponseFactory;

/**
 * 流程模型控制器
 * 
 * @author minghua.guo
 */
@Controller
@RequestMapping(value = "/workflow/model")
public class WfModelController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	RepositoryService repositoryService;

	private WfManagerService wfManagerService = WfApiFactory.getWfManagerService();

	private WfRepositoryService wfRepositoryService = WfApiFactory.getWfRepositoryService();

	/**
	 * 模型列表
	 */
	@RequestMapping(value = "list")
	public ModelAndView modelList() {
		ModelAndView mav = new ModelAndView("workflow/model-list");
		List<Model> list = repositoryService.createModelQuery().list();
		mav.addObject("list", list);
		return mav;
	}

	/**
	 * 创建模型
	 */
	@RequestMapping(value = "create")
	public void create(@RequestParam("name") String name,
			@RequestParam("key") String key,
			@RequestParam("description") String description,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			ObjectMapper objectMapper = new ObjectMapper();
			ObjectNode editorNode = objectMapper.createObjectNode();
			editorNode.put("id", "canvas");
			editorNode.put("resourceId", "canvas");
			ObjectNode stencilSetNode = objectMapper.createObjectNode();
			stencilSetNode.put("namespace",
					"http://b3mn.org/stencilset/bpmn2.0#");
			editorNode.put("stencilset", stencilSetNode);
			ObjectNode propertiesNode = objectMapper.createObjectNode();
			propertiesNode.put("name", name);
			propertiesNode.put("process_id", StringUtils.defaultString(key));
			propertiesNode.put("documentation", description);
			propertiesNode.put("description", description);
			editorNode.put("properties", propertiesNode);
			Model modelData = repositoryService.newModel();

			ObjectNode modelObjectNode = objectMapper.createObjectNode();
			modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
			modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
			description = StringUtils.defaultString(description);
			modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION,
					description);
			modelData.setMetaInfo(modelObjectNode.toString());
			modelData.setName(name);
			modelData.setKey(StringUtils.defaultString(key));

			repositoryService.saveModel(modelData);
			repositoryService.addModelEditorSource(modelData.getId(),
					editorNode.toString().getBytes("utf-8"));

			response.sendRedirect(request.getContextPath()
					+ "/workflow/service/editor?id=" + modelData.getId());
		} catch (Exception e) {
			logger.error("创建模型失败：", e);
		}
	}

	/**
	 * 根据Model部署流程
	 */
	@RequestMapping(value = "deploy/{modelId}")
	public String deploy(@PathVariable("modelId") String modelId,
			RedirectAttributes redirectAttributes) {
		try {
			Model modelData = repositoryService.getModel(modelId);
			ObjectNode modelNode = (ObjectNode) new ObjectMapper()
					.readTree(repositoryService.getModelEditorSource(modelData
							.getId()));
			byte[] bpmnBytes = null;

			BpmnModel model = new BpmnJsonConverter()
					.convertToBpmnModel(modelNode);
			bpmnBytes = new BpmnXMLConverter().convertToXML(model);

			String processName = modelData.getName() + ".bpmn20.xml";
			Deployment deployment = repositoryService.createDeployment()
					.name(modelData.getName())
					.addString(processName, new String(bpmnBytes)).deploy();
			redirectAttributes.addFlashAttribute("message", "部署成功，部署ID="
					+ deployment.getId());
		} catch (Exception e) {
			logger.error("根据模型部署流程失败：modelId={}", modelId, e);
		}
		return "redirect:/workflow/model/list";
	}

	/**
	 * 导出model的xml文件
	 */
	@RequestMapping(value = "export/{modelId}")
	public void export(@PathVariable("modelId") String modelId,
			HttpServletResponse response) {
		try {
			Model modelData = repositoryService.getModel(modelId);
			BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
			JsonNode editorNode = new ObjectMapper().readTree(repositoryService
					.getModelEditorSource(modelData.getId()));
			BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);
			BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
			byte[] bpmnBytes = xmlConverter.convertToXML(bpmnModel);

			ByteArrayInputStream in = new ByteArrayInputStream(bpmnBytes);
			IOUtils.copy(in, response.getOutputStream());
			String filename = bpmnModel.getMainProcess().getId()
					+ ".bpmn20.xml";
			response.setHeader("Content-Disposition", "attachment; filename="
					+ filename);
			response.flushBuffer();
		} catch (Exception e) {
			logger.error("导出model的xml文件失败：modelId={}", modelId, e);
		}
	}

	/**
	 * 导出model的xml文件,含转换为模型
	 */
	@RequestMapping(value = "exportBpmnFile/{processDefinitionId}")
	public void exportBpmnFile(
			@PathVariable("processDefinitionId") String processDefinitionId,
			HttpServletResponse response) throws UnsupportedEncodingException,
			XMLStreamException {

		ProcessDefinition processDefinition = repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).singleResult();
		InputStream bpmnStream = repositoryService.getResourceAsStream(
				processDefinition.getDeploymentId(),
				processDefinition.getResourceName());
		XMLInputFactory xif = XMLInputFactory.newInstance();
		InputStreamReader in = new InputStreamReader(bpmnStream, "UTF-8");
		XMLStreamReader xtr = xif.createXMLStreamReader(in);
		BpmnModel bpmnModel = new BpmnXMLConverter().convertToBpmnModel(xtr);

		BpmnJsonConverter converter = new BpmnJsonConverter();
		ObjectNode modelNode = converter.convertToJson(bpmnModel);
		Model modelData = repositoryService.newModel();
		modelData.setKey(processDefinition.getKey());
		modelData.setName(processDefinition.getResourceName());
		modelData.setCategory(processDefinition.getDeploymentId());

		ObjectNode modelObjectNode = new ObjectMapper().createObjectNode();
		modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME,
				processDefinition.getName());
		modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
		modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION,
				processDefinition.getDescription());
		modelData.setMetaInfo(modelObjectNode.toString());

		repositoryService.saveModel(modelData);

		repositoryService.addModelEditorSource(modelData.getId(), modelNode
				.toString().getBytes("utf-8"));

		String modelId = modelData.getId();
		try {
			Model mData = repositoryService.getModel(modelId);
			BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
			JsonNode editorNode = new ObjectMapper().readTree(repositoryService
					.getModelEditorSource(mData.getId()));
			BpmnModel bModel = jsonConverter.convertToBpmnModel(editorNode);
			BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
			byte[] bpmnBytes = xmlConverter.convertToXML(bModel);

			ByteArrayInputStream ins = new ByteArrayInputStream(bpmnBytes);
			IOUtils.copy(ins, response.getOutputStream());
			String filename = bModel.getMainProcess().getId() + ".bpmn20.xml";
			response.setHeader("Content-Disposition", "attachment; filename="
					+ filename);
			response.flushBuffer();
		} catch (Exception e) {
			logger.error("导出model的xml文件失败：modelId={}", modelId, e);
		}

	}

	@RequestMapping(value = "editProcessByConvert/{processDefinitionId}")
	public void editProcessByConvert(
			@PathVariable("processDefinitionId") String processDefinitionId,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, XMLStreamException {
		// 转换为model
		ProcessDefinition processDefinition = repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).singleResult();
		InputStream bpmnStream = repositoryService.getResourceAsStream(
				processDefinition.getDeploymentId(),
				processDefinition.getResourceName());
		XMLInputFactory xif = XMLInputFactory.newInstance();
		InputStreamReader in = new InputStreamReader(bpmnStream, "UTF-8");
		XMLStreamReader xtr = xif.createXMLStreamReader(in);
		BpmnModel bpmnModel = new BpmnXMLConverter().convertToBpmnModel(xtr);

		BpmnJsonConverter converter = new BpmnJsonConverter();
		ObjectNode modelNode = converter.convertToJson(bpmnModel);
		Model modelData = repositoryService.newModel();
		modelData.setKey(processDefinition.getKey());
		modelData.setName(processDefinition.getResourceName());
		modelData.setCategory(processDefinition.getDeploymentId());

		ObjectNode modelObjectNode = new ObjectMapper().createObjectNode();
		modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME,
				processDefinition.getName());
		modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
		modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION,
				processDefinition.getDescription());
		modelData.setMetaInfo(modelObjectNode.toString());

		repositoryService.saveModel(modelData);

		repositoryService.addModelEditorSource(modelData.getId(), modelNode
				.toString().getBytes("utf-8"));

		// 获取modelId
		String modelId = modelData.getId();

		// 打开流程编辑器修改model
		response.sendRedirect(request.getContextPath()
				+ "/workflow/service/editor?id=" + modelId);
	}

	@RequestMapping(value = "/republishToLatest", method = RequestMethod.POST)
	public Object republishToLatest(@RequestBody Map<String, String> body)
			throws IOException, XMLStreamException {
		// 转换为model
		String processDefinitionId = body.get("processDefinitionId");
		ProcessDefinition processDefinition = repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).singleResult();
		InputStream bpmnStream = repositoryService.getResourceAsStream(
				processDefinition.getDeploymentId(),
				processDefinition.getResourceName());
		XMLInputFactory xif = XMLInputFactory.newInstance();
		InputStreamReader in = new InputStreamReader(bpmnStream, "UTF-8");
		XMLStreamReader xtr = xif.createXMLStreamReader(in);
		BpmnModel bpmnModel = new BpmnXMLConverter().convertToBpmnModel(xtr);

		BpmnJsonConverter converter = new BpmnJsonConverter();
		ObjectNode modelNode = converter.convertToJson(bpmnModel);
		Model modelData = repositoryService.newModel();
		modelData.setKey(processDefinition.getKey());
		modelData.setName(processDefinition.getResourceName());
		modelData.setCategory(processDefinition.getDeploymentId());

		ObjectNode modelObjectNode = new ObjectMapper().createObjectNode();
		modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME,
				processDefinition.getName());
		modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
		modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION,
				processDefinition.getDescription());
		modelData.setMetaInfo(modelObjectNode.toString());

		repositoryService.saveModel(modelData);

		repositoryService.addModelEditorSource(modelData.getId(), modelNode
				.toString().getBytes("utf-8"));

		// 获取modelId
		String modelId = modelData.getId();

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Model modelDataNew = repositoryService.getModel(modelId);
			ObjectNode modelNodeNew = (ObjectNode) new ObjectMapper()
					.readTree(repositoryService
							.getModelEditorSource(modelDataNew.getId()));
			byte[] bpmnBytes = null;

			BpmnModel model = new BpmnJsonConverter()
					.convertToBpmnModel(modelNodeNew);
			bpmnBytes = new BpmnXMLConverter().convertToXML(model);

			String processName = modelData.getName() + ".bpmn20.xml";
			Deployment deployment = repositoryService.createDeployment()
					.name(modelData.getName())
					.addString(processName, new String(bpmnBytes, "UTF-8"))
					.deploy();
			map.put("result", "success");
			map.put("deployId", deployment.getId());

			// 部署成功后需要更新绑定关系为当前的最新版本的流程
			reBindModuleAndProcess(deployment.getId());
		} catch (Exception e) {
			logger.error("根据模型部署流程失败：modelId={}", modelId, e);
		}
		return new ResponseFactory().createResponseBodyJSONObject(map);

	}

	private void reBindModuleAndProcess(String deployId) throws Exception {
		if (null != deployId && !"".equals(deployId.trim())) {
			// 根据部署id查询流程定义
			WfProcessDefinitionParam wfProcessDefinitionParam = new WfProcessDefinitionParam();
			wfProcessDefinitionParam.setPaged(false);
			wfProcessDefinitionParam.setDeploymentId(deployId);
			WfPageList<WfProcessDefinition, WfProcessDefinitionParam> wPageList = wfRepositoryService
					.queryProcessDefinitions(wfProcessDefinitionParam);
			if (null != wPageList && wPageList.getDatas().size() > 0) {
				WfProcessDefinition pd = wPageList.getDatas().get(0);
				// 查询流程绑定的事项，如果有绑定事项，则将所有与事项的绑定关系更新为此最新的流程定义
				List<String> moduleIds = wfManagerService
						.getBindModuleIdsByProcessDefKey(pd.getKey());
				if (null != moduleIds && !moduleIds.isEmpty()) {
					for (String module : moduleIds) {
						/*wfManagerService.unBindProcessFromModule(module, pd);

						wfManagerService.bindProcessToModule(module, pd);*/
						wfManagerService.reBindProcessToModule(module, pd);
					}
				}
			}

		}
	}
}
