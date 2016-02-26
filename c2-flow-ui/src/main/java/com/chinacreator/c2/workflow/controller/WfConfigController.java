package com.chinacreator.c2.workflow.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.chinacreator.c2.flow.WfApiFactory;
import com.chinacreator.c2.flow.api.WfFormService;
import com.chinacreator.c2.flow.api.WfHistoryService;
import com.chinacreator.c2.flow.api.WfManagerService;
import com.chinacreator.c2.flow.api.WfRepositoryService;
import com.chinacreator.c2.flow.api.WfRuntimeService;
import com.chinacreator.c2.flow.detail.WfActivity;
import com.chinacreator.c2.flow.detail.WfConstants;
import com.chinacreator.c2.flow.detail.WfGroup;
import com.chinacreator.c2.flow.detail.WfHistoricTask;
import com.chinacreator.c2.flow.detail.WfModuleBean;
import com.chinacreator.c2.flow.detail.WfProcessConfigProperty;
import com.chinacreator.c2.flow.detail.WfProcessDefinition;
import com.chinacreator.c2.flow.detail.WfTask;
import com.chinacreator.c2.flow.detail.WfUser;
import com.chinacreator.c2.flow.util.CommonUtil;
import com.chinacreator.c2.ioc.ApplicationContextManager;
import com.chinacreator.c2.web.controller.ResponseFactory;
import com.chinacreator.c2.workflow.api.WfExtendService;

/**
 * 流程外围配置控制器
 * 
 * @author yicheng.yang
 */
@Controller
@RequestMapping(value = "/workflow/config")
public class WfConfigController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	private WfManagerService wfManagerService = WfApiFactory.getWfManagerService();
	private WfExtendService wfExtendService = (WfExtendService) ApplicationContextManager
			.getContext().getBean(WfExtendService.class);
	private WfRuntimeService wfRuntimeService = WfApiFactory.getWfRuntimeService();
	private WfHistoryService wfHistoryService = WfApiFactory.getWfHistoryService();
	
	private WfRepositoryService wfRepositoryService = WfApiFactory.getWfRepositoryService();

	private WfFormService wfFormService=WfApiFactory.getWfFormService();

	@RequestMapping(value = "/findProcessConfigProperty", method = RequestMethod.POST)
	public Object findProcessConfigProperty(
			@RequestParam(value = "processDefinitionId", required = true) String processDefinitionId,
			@RequestParam(value = "moduleId", required = true) String moduleId,
			@RequestParam(value = "taskDefKey", required = true) String taskDefKey)
			throws Exception {
		WfProcessConfigProperty wfProcessConfigProperty = wfManagerService
				.findProcessConfigProperty(processDefinitionId, moduleId,
						taskDefKey);
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != wfProcessConfigProperty) {
			map.put("result", "success");
			map.put("wfProcessConfigProperty", wfProcessConfigProperty);

			String userIds = wfProcessConfigProperty.getPerformer();
			if (null != userIds && !"".equals(userIds)) {
				String userNames = "";
				String[] userArr = userIds.split(",");
				for (String uId : userArr) {
					WfUser wfUser = wfManagerService.getUserById(uId);
					userNames += wfUser.getLastName() + ",";
				}
				if (!"".equals(userNames)) {
					map.put("userNames",
							userNames.substring(0, userNames.length() - 1));
				}

			}

			String groupPerformer = wfProcessConfigProperty.getGroupPerformer();
			if (null != groupPerformer && !"".equals(groupPerformer)) {
				String groupNames = "";
				String[] groupArr = groupPerformer.split(",");
				for (String gId : groupArr) {
					WfGroup wfGroup = wfManagerService.getGroupById(gId);
					groupNames += wfGroup.getName() + ",";
				}
				if (!"".equals(groupNames)) {
					map.put("groupNames",
							groupNames.substring(0, groupNames.length() - 1));
				}

			}
		} else {
			map.put("result", "empty");
		}
		return new ResponseFactory().createResponseBodyJSONObject(map);

	}

	@RequestMapping(value = "/findProcessTaskConfigByTaskId", method = RequestMethod.POST)
	public Object findProcessTaskConfigByTaskId(
			@RequestBody Map<String, String> body) {
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
						String bindFormInDefinition = wfFormService
								.getTaskFormKey(processDefinitionId,
										taskDefinitionId);
						wfProcessConfigProperty
								.setBindForm(bindFormInDefinition);
					}
				} else {
					wfProcessConfigProperty = new WfProcessConfigProperty();
					// 以流程定义中的表单为准
					String bindFormInDefinition = wfFormService.getTaskFormKey(
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
				return new ResponseFactory().createResponseBodyJSONObject(map);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		map.put("result", "empty");
		return new ResponseFactory().createResponseBodyJSONObject(map);
	}

	@RequestMapping(value = "/findProcessStartConfigByMenuCode", method = RequestMethod.POST)
	public Object findProcessStartConfigByMenuCode(
			@RequestBody Map<String, String> body) {
		String menuCode = body.get("menuCode");
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != menuCode && !"".equals(menuCode.trim())) {
			WfModuleBean wfModuleBean = wfExtendService
					.queryByMenuCode(menuCode);
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
				return new ResponseFactory().createResponseBodyJSONObject(map);
			}
		}
		map.put("result", "empty");
		return new ResponseFactory().createResponseBodyJSONObject(map);

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
				
				List<WfActivity> wfActivityList=wfRepositoryService.getActivitiesByDefinition(processDefinitionId);

				if (null != wfActivityList && !wfActivityList.isEmpty()) {
					for (WfActivity ai : wfActivityList) {
						if ("startEvent".equals(ai.getProperties().get("type"))) {
							wfProcessConfigProperty = wfManagerService
									.findProcessConfigProperty(
											processDefinitionId, moduleId,
											ai.getId());
						}
					}
				}
				// 如果流程定义图中配置了表单，如果外围配置没配置表单，可以用流程定义中的表单，但是外围配置表单优先级>流程定义的表单
				if (wfProcessConfigProperty != null) {
					if (wfProcessConfigProperty.getBindForm() != null
							&& !"".equals(wfProcessConfigProperty.getBindForm()
									.trim())) {
						// 以外围配置中的为准，不做处理
					} else {
						// 以流程定义中的表单为准
						String bindFormInDefinition = wfFormService
								.getStartFormKey(processDefinitionId);
						wfProcessConfigProperty
								.setBindForm(bindFormInDefinition);
					}
				} else {
					wfProcessConfigProperty = new WfProcessConfigProperty();
					// 以流程定义中的表单为准
					String bindFormInDefinition = wfFormService
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
