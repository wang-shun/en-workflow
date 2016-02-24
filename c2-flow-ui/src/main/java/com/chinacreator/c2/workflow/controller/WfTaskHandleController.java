package com.chinacreator.c2.workflow.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.chinacreator.c2.flow.WfApiFactory;
import com.chinacreator.c2.flow.api.WfHistoryService;
import com.chinacreator.c2.flow.api.WfManagerService;
import com.chinacreator.c2.flow.api.WfRepositoryService;
import com.chinacreator.c2.flow.api.WfRuntimeService;
import com.chinacreator.c2.flow.detail.WfActivity;
import com.chinacreator.c2.flow.detail.WfConstants;
import com.chinacreator.c2.flow.detail.WfHistoricTask;
import com.chinacreator.c2.flow.detail.WfModuleBean;
import com.chinacreator.c2.flow.detail.WfProcessConfigProperty;
import com.chinacreator.c2.flow.detail.WfProcessDefinition;
import com.chinacreator.c2.flow.detail.WfTask;
import com.chinacreator.c2.flow.util.CommonUtil;
import com.chinacreator.c2.ioc.ApplicationContextManager;
import com.chinacreator.c2.web.controller.ResponseFactory;
import com.chinacreator.c2.workflow.api.WfExtendService;

/**
 * 任务处理控制器，动态跳转表单，流程参数传递
 * 
 * @author 郭明华
 * 
 */
@Controller
@RequestMapping(value = "/wf")
public class WfTaskHandleController {

	private WfManagerService wfManagerService = WfApiFactory
			.getWfManagerService();
	private WfRepositoryService wfRepositoryService = WfApiFactory
			.getWfRepositoryService();
	private WfExtendService wfExtendService = (WfExtendService) ApplicationContextManager
			.getContext().getBean(WfExtendService.class);
	private WfRuntimeService wfRuntimeService = WfApiFactory
			.getWfRuntimeService();
	private WfHistoryService wfHistoryService = WfApiFactory
			.getWfHistoryService();

	@Autowired
	protected FormService formService;

	@RequestMapping(value = "/taskHandle")
	public ModelAndView taskHandle(@RequestParam(value = "moduleId", required = false) String moduleId,
			String taskId, String businessKey, String taskType,String menuCode,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String formUrl = "";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		if (taskId != null && !"".equals(taskId)) {// 完成任务
			Map<String, String> body = new HashMap<String, String>();
			body.put("taskId", taskId);
			body.put("moduleId", moduleId);
			body.put("taskType", taskType);
			Map<String, Object> data = findProcessTaskConfigByTaskId(body);

			if (data != null && "success".equals(data.get("result"))) {
				// console.log(data);
				String moduleId1 = (String) data.get("moduleId");
				String processDefinitionId = (String) data
						.get("processDefinitionId");
				String processInstanceId = (String) data
						.get("processInstanceId");

				// 待办任务的外围配置
				WfProcessConfigProperty wfProcessConfigProperty = (WfProcessConfigProperty) data
						.get("wfProcessConfigProperty");

				String alias = wfProcessConfigProperty.getAlias();
				String bindForm = wfProcessConfigProperty.getBindForm();
				String configId = wfProcessConfigProperty.getConfigId();
				Integer duration = wfProcessConfigProperty.getDuration();
				String durationUnit = wfProcessConfigProperty.getDurationUnit();
				String performer = wfProcessConfigProperty.getPerformer();
				String taskDefKey = wfProcessConfigProperty.getTaskDefKey();

				String hrefUrlPre = "";
				hrefUrlPre += bindForm;


				if (!StringUtils.isEmpty(moduleId1)) {
					paramMap.put("moduleId", moduleId1);
				}
				if (!StringUtils.isEmpty(processDefinitionId)) {
					paramMap.put("processDefinitionId", processDefinitionId);
				}
				if (!StringUtils.isEmpty(processInstanceId)) {
					paramMap.put("processInstanceId", processInstanceId);
				}
				if (!StringUtils.isEmpty(taskId)) {
					paramMap.put("taskId", taskId);
				}

				if (!StringUtils.isEmpty(alias)) {
					paramMap.put("alias", alias);
				}
				if (!StringUtils.isEmpty(bindForm)) {
//					hrefParamArr.add("bindForm=" + bindForm);
				}
				if (!StringUtils.isEmpty(configId)) {
					paramMap.put("configId", configId);
				}
				if (!StringUtils.isEmpty(duration)) {
					paramMap.put("duration", duration);
				}
				if (!StringUtils.isEmpty(durationUnit)) {
					paramMap.put("durationUnit", durationUnit);
				}
				if (!StringUtils.isEmpty(performer)) {
					paramMap.put("performer", performer);
				}
				if (!StringUtils.isEmpty(taskDefKey)) {
					paramMap.put("taskDefKey", taskDefKey);
				}
				if (!StringUtils.isEmpty(businessKey)) {
					paramMap.put("businessKey", businessKey);
				}

				formUrl = hrefUrlPre;// + "?" + hrefParam;
				
			}
		} else {// 启动流程
			Map<String, String> body = new HashMap<String, String>();
			body.put("moduleCode", menuCode);
			Map<String, Object> data = findProcessStartConfigByMenuCode(body);
			if (data != null && "success".equals(data.get("result"))) {
				WfProcessConfigProperty wfProcessConfigProperty = (WfProcessConfigProperty) data
						.get("wfProcessConfigProperty");
				String processDefinitionId = (String) data
						.get("processDefinitionId");
				moduleId = (String) data.get("moduleId");

				String hrefUrlPre = "";

				String alias = wfProcessConfigProperty.getAlias();
				String bindForm = wfProcessConfigProperty.getBindForm();
				String configId = wfProcessConfigProperty.getConfigId();
				Integer duration = wfProcessConfigProperty.getDuration();
				String durationUnit = wfProcessConfigProperty.getDurationUnit();
				String performer = wfProcessConfigProperty.getPerformer();
				String taskDefKey = wfProcessConfigProperty.getTaskDefKey();
				String groupPerformer = wfProcessConfigProperty
						.getGroupPerformer();

				hrefUrlPre += bindForm;
				

				if (!StringUtils.isEmpty(taskDefKey)) {
					paramMap.put("taskDefKey", taskDefKey);
				}
				if (!StringUtils.isEmpty(groupPerformer)) {
					paramMap.put("groupPerformer", groupPerformer);
				}
				if (!StringUtils.isEmpty(configId)) {
					paramMap.put("configId", configId);
				}
				if (!StringUtils.isEmpty(alias)) {
					paramMap.put("alias", alias);
				}
				if (!StringUtils.isEmpty(duration)) {
					paramMap.put("duration", duration);
				}

				if (!StringUtils.isEmpty(durationUnit)) {
					paramMap.put("durationUnit", durationUnit);
				}

				if (!StringUtils.isEmpty(performer)) {
					paramMap.put("performer", performer);
				}

				if (!StringUtils.isEmpty(processDefinitionId)) {
					paramMap.put("processDefinitionId", processDefinitionId);
				}

				if (!StringUtils.isEmpty(moduleId)) {
					paramMap.put("moduleId", moduleId);
				}
				
				formUrl = hrefUrlPre;
			}
		}
		/*String path = request.getContextPath();
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/";
		if(!CommonUtil.stringNullOrEmpty(formUrl)){
			response.sendRedirect(basePath+formUrl);
		}*/
		
		//forward的第二个参数当采用UI的tab样式时会进行tab名称的设置，这里将环节别名设置为名称，环节别名没配置，查出环节在流程定义中的名称作为名称
		String aliasStr = paramMap.get("alias")==null?"": paramMap.get("alias").toString();
		String processDefinitionIdStr = paramMap.get("processDefinitionId")==null?"":paramMap.get("processDefinitionId").toString();
		String tabName = "";
		if(CommonUtil.stringNullOrEmpty(aliasStr)){
			//别名为空，需要查询任务的名称
			if(taskId != null && !"".equals(taskId)){
				WfTask wft = wfRuntimeService.getTaskById(taskId);
				tabName = wft.getName();
			}else{
				WfProcessDefinition wfProcessDefinition = wfRepositoryService.getProcessDefinition(processDefinitionIdStr);
				String wfProcessName = wfProcessDefinition.getName();
				tabName = wfProcessName;
			}
		}else{
			tabName = aliasStr;
		}
		
		
		return new ResponseFactory().forward(formUrl, tabName, paramMap);
		
	}

	public Map<String, Object> findProcessTaskConfigByTaskId(
			Map<String, String> body) {
		String taskId = body.get("taskId");
		String moduleId = body.get("moduleId");
		String taskType = body.get("taskType");

		Map<String, Object> map = new HashMap<String, Object>();
		if (!CommonUtil.stringNullOrEmpty(taskId)
				&& !CommonUtil.stringNullOrEmpty(moduleId)) {

			try {
				WfTask wfTask = null;
				WfHistoricTask wfHistoricTask = null;
				// 通过任务ID找到任务信息
				if (WfConstants.WF_UNITE_TASK_TYPE_DONE.equals(taskType)) {
					wfHistoricTask = wfHistoryService
							.getHistoricTaskById(taskId);
				} else {
					wfTask = wfRuntimeService.getTaskById(taskId);
				}

				String businessId = "";
				String processDefinitionId = "";
				String processInstanceId = "";
				String taskDefinitionId = "";

				if (WfConstants.WF_UNITE_TASK_TYPE_DONE.equals(taskType)) {
					processDefinitionId = wfHistoricTask
							.getProcessDefinitionId();
					processInstanceId = wfHistoricTask.getProcessInstanceId();
					taskDefinitionId = wfHistoricTask.getTaskDefinitionKey();
				} else {
					businessId = wfTask.getBusinessId();
					processDefinitionId = wfTask.getProcessDefinitionId();
					processInstanceId = wfTask.getProcessInstanceId();
					taskDefinitionId = wfTask.getTaskDefinitionId();
				}

				// 通过流程定义和事项、环节定义查到外围配置信息
				WfProcessConfigProperty wfProcessConfigProperty = wfManagerService
						.findProcessConfigProperty(processDefinitionId,
								moduleId, taskDefinitionId);

				if (wfProcessConfigProperty != null) {
					if (wfProcessConfigProperty.getBindForm() != null
							&& !"".equals(wfProcessConfigProperty.getBindForm()
									.trim())) {
						// 以外围配置中的为准，不做处理
					} else {
						// 以流程定义中的表单为准
						String bindFormInDefinition = formService
								.getTaskFormKey(processDefinitionId,
										taskDefinitionId);
						wfProcessConfigProperty
								.setBindForm(bindFormInDefinition);
					}
				} else {
					wfProcessConfigProperty = new WfProcessConfigProperty();
					// 以流程定义中的表单为准
					String bindFormInDefinition = formService.getTaskFormKey(
							processDefinitionId, taskDefinitionId);
					wfProcessConfigProperty.setBindForm(bindFormInDefinition);
				}
				map.put("result", "success");
				map.put("moduleId", moduleId);
				map.put("taskId", taskId);
				map.put("businessId", businessId);
				map.put("processInstanceId", processInstanceId);
				map.put("wfProcessConfigProperty", wfProcessConfigProperty);
				map.put("processDefinitionId", processDefinitionId);
				return map;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		map.put("result", "empty");
		return map;
	}

	public Map<String, Object> findProcessStartConfigByMenuCode(
			Map<String, String> body) {
		String moduleCode = body.get("moduleCode");
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(moduleCode)) {
			WfModuleBean wfModuleBean = wfExtendService
					.queryByMenuCode(moduleCode);
			if (null != wfModuleBean) {
				// 事项ID，即菜单ID
				String menuId = wfModuleBean.getId();
				Map<String, Object> result = getStartEventConfigByModuleId(menuId);
				map.put("result", "success");
				map.put("moduleId", menuId);
				map.put("processDefinitionId",
						result.get("processDefinitionId"));
				map.put("wfProcessConfigProperty",
						result.get("wfProcessConfigProperty"));
				return map;
			}
		}
		map.put("result", "empty");
		return map;
	}

	private Map<String, Object> getStartEventConfigByModuleId(String moduleId) {
		Map<String, Object> result = new HashMap<String, Object>();
		WfProcessConfigProperty wfProcessConfigProperty = null;
		if (moduleId != null && !"".equals(moduleId.trim())) {
			// 通过事项找到流程定义
			try {
				WfProcessDefinition wfProcessDefinition = wfManagerService
						.getBindProcessByModuleId(moduleId);
				
				// 通过流程定义查到开始环节的定义key
				String processDefinitionId = wfProcessDefinition.getId();
				result.put("processDefinitionId", processDefinitionId);
				
				List<WfActivity> wfActivityList = wfRepositoryService.getActivitiesByDefinition(processDefinitionId);
				for (WfActivity wfActivity : wfActivityList) {
					if ("startEvent".equals(wfActivity.getProperties().get("type"))){
						wfProcessConfigProperty = wfManagerService.findProcessConfigProperty(processDefinitionId, moduleId,wfActivity.getId());
					}
				}
				
				
//				ProcessDefinitionEntity processDefEntity = (ProcessDefinitionEntity) repositoryService
//						.getProcessDefinition(processDefinitionId);
//				List<ActivityImpl> ActivityImplList = processDefEntity
//						.getActivities();
//				if (null != ActivityImplList && !ActivityImplList.isEmpty()) {
//					for (ActivityImpl ai : ActivityImplList) {
//						if ("startEvent".equals(ai.getProperty("type"))) {
//							wfProcessConfigProperty = wfManagerService
//									.findProcessConfigProperty(
//											processDefinitionId, moduleId,
//											ai.getId());
//						}
//					}
//				}
				// 如果流程定义图中配置了表单，如果外围配置没配置表单，可以用流程定义中的表单，但是外围配置表单优先级>流程定义的表单
				if (wfProcessConfigProperty != null) {
					if (wfProcessConfigProperty.getBindForm() != null
							&& !"".equals(wfProcessConfigProperty.getBindForm()
									.trim())) {
						// 以外围配置中的为准，不做处理
					} else {
						// 以流程定义中的表单为准
						String bindFormInDefinition = formService
								.getStartFormKey(processDefinitionId);
						wfProcessConfigProperty
								.setBindForm(bindFormInDefinition);
					}
				} else {
					wfProcessConfigProperty = new WfProcessConfigProperty();
					// 以流程定义中的表单为准
					String bindFormInDefinition = formService
							.getStartFormKey(processDefinitionId);
					wfProcessConfigProperty.setBindForm(bindFormInDefinition);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		result.put("wfProcessConfigProperty", wfProcessConfigProperty);
		return result;
	}
}
