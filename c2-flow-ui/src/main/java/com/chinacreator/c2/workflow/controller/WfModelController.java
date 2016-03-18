package com.chinacreator.c2.workflow.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.flow.WfApiFactory;
import com.chinacreator.c2.flow.Exception.C2FlowRuntimeException;
import com.chinacreator.c2.flow.api.WfManagerService;
import com.chinacreator.c2.flow.api.WfRepositoryService;
import com.chinacreator.c2.flow.detail.WfConstants;
import com.chinacreator.c2.flow.detail.WfDeployment;
import com.chinacreator.c2.flow.detail.WfModel;
import com.chinacreator.c2.flow.detail.WfModelParam;
import com.chinacreator.c2.flow.detail.WfOperator;
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

	private WfManagerService wfManagerService = WfApiFactory.getWfManagerService();

	private WfRepositoryService wfRepositoryService = WfApiFactory.getWfRepositoryService();

	/**
	 * 模型列表
	 */
	@RequestMapping(value = "list")
	public ModelAndView modelList() {
		ModelAndView mav = new ModelAndView("workflow/model-list");
		WfModelParam wfModelParam=new WfModelParam();
		
		WfPageList<WfModel, WfModelParam> wfPageList=new WfPageList<WfModel, WfModelParam>();
		try{
			wfPageList=wfRepositoryService.queryModels(wfModelParam);
		}catch(Exception e){
			e.printStackTrace();
			throw new C2FlowRuntimeException("获取工作流模型异常",e);
		}
		
		//List<Model> list = wfRepositoryService.createModelQuery().list();
		mav.addObject("list", wfPageList.getDatas());
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
			
			WfModel modelData=new WfModel();
			//Model modelData = repositoryService.newModel();

			ObjectNode modelObjectNode = objectMapper.createObjectNode();
			modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
			modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
			description = StringUtils.defaultString(description);
			modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION,
					description);
			modelData.setMetaInfo(modelObjectNode.toString());
			modelData.setName(name);
			modelData.setKey(StringUtils.defaultString(key));
			
			WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
			WfOperator wfOperator = new WfOperator(context.getUser().getId(),context.getUser().getName(),context.getUser().getName(),context.getRequest().getRemoteHost(),WfApiFactory.getWfTenant());
			
			modelData=wfRepositoryService.insertModel(wfOperator,modelData);

			//repositoryService.saveModel(modelData);
			
			wfRepositoryService.saveModelEditorSource(wfOperator,modelData.getId(),editorNode.toString());
			//repositoryService.addModelEditorSource(modelData.getId(),editorNode.toString().getBytes("utf-8"));

			response.sendRedirect(request.getContextPath()+ "/workflow/service/editor?id=" + modelData.getId());
		} catch (Exception e) {
			logger.error("创建模型失败：", e);
			throw new C2FlowRuntimeException("创建模型失败,请检查服务是否可!",e);
		}
	}

	/**
	 * 根据Model部署流程
	 */
	@RequestMapping(value = "deploy/{modelId}")
	public String deploy(@PathVariable("modelId") String modelId,
			RedirectAttributes redirectAttributes) {
		try {
			
			WfModel modelData=wfRepositoryService.getModelById(modelId);
			
			ObjectNode modelNode = (ObjectNode) new ObjectMapper()
					.readTree(wfRepositoryService.getModelEditorSource(modelData.getId()));
			byte[] bpmnBytes = null;

			BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
			bpmnBytes = new BpmnXMLConverter().convertToXML(model);

			String processName = modelData.getName() + ".bpmn20.xml";
			
			//如果开启了分布式，将分布式应用名称作为tenantId
			String tenantId=WfApiFactory.getWfTenant();
			
			WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
			WfOperator wfOperator = new WfOperator(context.getUser().getId(),context.getUser().getName(),context.getUser().getName(),context.getRequest().getRemoteHost(),tenantId);
			
			WfDeployment wfDeployment=wfRepositoryService.deployContent(wfOperator,modelData.getName(),null,processName,new String(bpmnBytes));

			redirectAttributes.addFlashAttribute("message", "部署成功，部署ID="+ wfDeployment.getId());
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
			WfModel modelData = wfRepositoryService.getModelById(modelId);
			BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
			JsonNode editorNode = new ObjectMapper().readTree(wfRepositoryService.getModelEditorSource(modelData.getId()));
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
	 * @throws Exception 
	 */
	@RequestMapping(value = "exportBpmnFile/{processDefinitionId}")
	public void exportBpmnFile(
			@PathVariable("processDefinitionId") String processDefinitionId,
			HttpServletResponse response) throws Exception {

		//转换为model
		WfProcessDefinition wfProcessDefinition=null;
		String bpmnStr=null;
		try {
			wfProcessDefinition = wfRepositoryService.getProcessDefinition(processDefinitionId);
			bpmnStr=wfRepositoryService.getProcessDefinitionBPMN(processDefinitionId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new C2FlowRuntimeException("获取流程定义异常",e);
		}

		InputStream bpmnStream=new ByteArrayInputStream(bpmnStr.getBytes(WfConstants.WF_CHARSET_UTF_8));
		
		XMLInputFactory xif = XMLInputFactory.newInstance();
		InputStreamReader in = new InputStreamReader(bpmnStream, "UTF-8");
		XMLStreamReader xtr = xif.createXMLStreamReader(in);
		BpmnModel bpmnModel = new BpmnXMLConverter().convertToBpmnModel(xtr);

		BpmnJsonConverter converter = new BpmnJsonConverter();
		ObjectNode modelNode = converter.convertToJson(bpmnModel);
		WfModel modelData = new WfModel();
		modelData.setKey(wfProcessDefinition.getKey());
		modelData.setName(wfProcessDefinition.getResourceName());
		modelData.setCategory(wfProcessDefinition.getDeploymentId());

		ObjectNode modelObjectNode = new ObjectMapper().createObjectNode();
		modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME,
				wfProcessDefinition.getName());
		modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
		modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION,
				wfProcessDefinition.getDescription());
		modelData.setMetaInfo(modelObjectNode.toString());

		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		WfOperator wfOperator = new WfOperator(context.getUser().getId(),context.getUser().getName(),context.getUser().getName(),context.getRequest().getRemoteHost(),WfApiFactory.getWfTenant());
		
		modelData=wfRepositoryService.insertModel(wfOperator,modelData);
		//repositoryService.saveModel(modelData);

		wfRepositoryService.saveModelEditorSource(wfOperator,modelData.getId(),modelNode.toString());
		//repositoryService.addModelEditorSource(modelData.getId(), modelNode.toString().getBytes("utf-8"));

		String modelId = modelData.getId();
		try {
			
			WfModel wfModel = wfRepositoryService.getModelById(modelId);
			BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
			JsonNode editorNode = new ObjectMapper().readTree(wfRepositoryService
					.getModelEditorSource(wfModel.getId()));
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
			throw new C2FlowRuntimeException("导出model的xml文件失败：modelId="+modelId,e);
		}

	}

	@RequestMapping(value = "editProcessByConvert/{processDefinitionId}")
	public void editProcessByConvert(
			@PathVariable("processDefinitionId") String processDefinitionId,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//转换为model
		WfProcessDefinition wfProcessDefinition=null;
		String bpmnStr=null;
		try {
			wfProcessDefinition = wfRepositoryService.getProcessDefinition(processDefinitionId);
			bpmnStr=wfRepositoryService.getProcessDefinitionBPMN(processDefinitionId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new C2FlowRuntimeException("获取流程定义异常",e);
		}
		
//		ProcessDefinition processDefinition = repositoryService
//				.createProcessDefinitionQuery()
//				.processDefinitionId(processDefinitionId).singleResult();
		
//		InputStream bpmnStream = repositoryService.getResourceAsStream(
//				wfProcessDefinition.getDeploymentId(),
//				wfProcessDefinition.getResourceName());
		
		InputStream bpmnStream=new ByteArrayInputStream(bpmnStr.getBytes(WfConstants.WF_CHARSET_UTF_8));
		
		XMLInputFactory xif = XMLInputFactory.newInstance();
		InputStreamReader in = new InputStreamReader(bpmnStream, "UTF-8");
		XMLStreamReader xtr = xif.createXMLStreamReader(in);
		BpmnModel bpmnModel = new BpmnXMLConverter().convertToBpmnModel(xtr);

		BpmnJsonConverter converter = new BpmnJsonConverter();
		ObjectNode modelNode = converter.convertToJson(bpmnModel);
		
		WfModel modelData=new WfModel();
		//Model modelData = repositoryService.newModel();
		modelData.setKey(wfProcessDefinition.getKey());
		modelData.setName(wfProcessDefinition.getResourceName());
		modelData.setCategory(wfProcessDefinition.getDeploymentId());

		ObjectNode modelObjectNode = new ObjectMapper().createObjectNode();
		modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME,
				wfProcessDefinition.getName());
		modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
		modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION,
				wfProcessDefinition.getDescription());
		modelData.setMetaInfo(modelObjectNode.toString());

		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		WfOperator wfOperator = new WfOperator(context.getUser().getId(),context.getUser().getName(),context.getUser().getName(),context.getRequest().getRemoteHost(),WfApiFactory.getWfTenant());
		
		modelData=wfRepositoryService.insertModel(wfOperator,modelData);
		
		//repositoryService.saveModel(modelData);
		
		//repositoryService.addModelEditorSource(modelData.getId(), modelNode.toString().getBytes("utf-8"));
		
		wfRepositoryService.saveModelEditorSource(wfOperator,modelData.getId(),modelNode.toString());

		// 获取modelId
		String modelId = modelData.getId();

		// 打开流程编辑器修改model
		response.sendRedirect(request.getContextPath()
				+ "/workflow/service/editor?id=" + modelId);
	}

	@RequestMapping(value = "/republishToLatest", method = RequestMethod.POST)
	public Object republishToLatest(@RequestBody Map<String, String> body)
			throws IOException, XMLStreamException {
		
		String processDefinitionId = body.get("processDefinitionId");
		
		try {
			
			WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
			WfOperator wfOperator = new WfOperator(context.getUser().getId(),context.getUser().getName(),context.getUser().getName(),context.getRequest().getRemoteHost(),WfApiFactory.getWfTenant());
			
			//转换为model
			WfProcessDefinition wfProcessDefinition=null;
			String bpmnStr=null;
			wfProcessDefinition = wfRepositoryService.getProcessDefinition(processDefinitionId);
			bpmnStr=wfRepositoryService.getProcessDefinitionBPMN(processDefinitionId);
			InputStream bpmnStream=new ByteArrayInputStream(bpmnStr.getBytes(WfConstants.WF_CHARSET_UTF_8));
			
			XMLInputFactory xif = XMLInputFactory.newInstance();
			InputStreamReader in = new InputStreamReader(bpmnStream, "UTF-8");
			XMLStreamReader xtr = xif.createXMLStreamReader(in);
			BpmnModel bpmnModel = new BpmnXMLConverter().convertToBpmnModel(xtr);

			BpmnJsonConverter converter = new BpmnJsonConverter();
			ObjectNode modelNode = converter.convertToJson(bpmnModel);
			WfModel modelData = new WfModel();
			modelData.setKey(wfProcessDefinition.getKey());
			modelData.setName(wfProcessDefinition.getResourceName());
			modelData.setCategory(wfProcessDefinition.getDeploymentId());
			
			ObjectNode modelObjectNode = new ObjectMapper().createObjectNode();
			modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME,wfProcessDefinition.getName());
			modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
			modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION,
					wfProcessDefinition.getDescription());
			modelData.setMetaInfo(modelObjectNode.toString());

			modelData=wfRepositoryService.insertModel(wfOperator,modelData);
			//repositoryService.saveModel(modelData);

			wfRepositoryService.saveModelEditorSource(wfOperator,modelData.getId(),modelNode.toString());
			//repositoryService.addModelEditorSource(modelData.getId(), modelNode.toString().getBytes("utf-8"));

			// 获取modelId
			String modelId = modelData.getId();

			Map<String, Object> map = new HashMap<String, Object>();
			try {
				WfModel modelDataNew = wfRepositoryService.getModelById(modelId);
				ObjectNode modelNodeNew = (ObjectNode) new ObjectMapper()
						.readTree(wfRepositoryService.getModelEditorSource(modelDataNew.getId()));
				byte[] bpmnBytes = null;

				BpmnModel model = new BpmnJsonConverter()
						.convertToBpmnModel(modelNodeNew);
				bpmnBytes = new BpmnXMLConverter().convertToXML(model);

				String processName = modelData.getName() + ".bpmn20.xml";
				
				WfDeployment wfDeployment=wfRepositoryService.deployContent(wfOperator,modelData.getName(),null,processName,new String(bpmnBytes, "UTF-8"));
				
				map.put("result", "success");
				map.put("deployId", wfDeployment.getId());

				// 部署成功后需要更新绑定关系为当前的最新版本的流程
				reBindModuleAndProcess(wfDeployment.getId());
			} catch (Exception e) {
				logger.error("根据模型部署流程失败：modelId={}", modelId, e);
				throw new C2FlowRuntimeException("根据模型部署流程失败：modelId="+modelId,e);
			}
			
			return new ResponseFactory().createResponseBodyJSONObject(map);
			
		} catch (Exception e) {
			logger.error("发布工作流模型异常：processDefinitionId={}", processDefinitionId, e);
			throw new C2FlowRuntimeException("发布工作流模型异常：processDefinitionId="+processDefinitionId,e);
		}

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
