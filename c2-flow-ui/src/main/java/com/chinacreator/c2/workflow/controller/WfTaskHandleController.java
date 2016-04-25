package com.chinacreator.c2.workflow.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chinacreator.c2.flow.WfApiFactory;
import com.chinacreator.c2.flow.Exception.C2FlowRuntimeException;
import com.chinacreator.c2.flow.api.WfFormService;
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
	private WfFormService wfFormService=WfApiFactory.getWfFormService();
	
	
	@RequestMapping(value = "/taskHandle")
	public Object taskHandle(@RequestParam(value = "moduleId", required = false) String moduleId,
			String taskId, String businessKey, String taskType,String menuCode,
			HttpServletRequest request, HttpServletResponse response){
		
		String formUrl = "";
		String tabName = "";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		try{
			
			// 完成任务
			if (!StringUtils.isEmpty(taskId)) {

				String taskName="";
				WfTask wfTask = null;
				WfHistoricTask wfHistoricTask = null;
				// 通过任务ID找到任务信息
				if (WfConstants.WF_UNITE_TASK_TYPE_DONE.equals(taskType)) {
					wfHistoricTask = wfHistoryService.getHistoricTaskById(taskId);
					if(null==wfHistoricTask) throw new C2FlowRuntimeException("历史列表中不存在此任务！");
				} else {
					wfTask = wfRuntimeService.getTaskById(taskId);
					if(null==wfTask) throw new C2FlowRuntimeException("当前任务已完成或不存在，请重新打开任务页面！");
				}

				String processDefinitionId = "";
				String processInstanceId = "";
				String taskDefinitionId = "";

				if (WfConstants.WF_UNITE_TASK_TYPE_DONE.equals(taskType)){
					processDefinitionId = wfHistoricTask.getProcessDefinitionId();
					processInstanceId = wfHistoricTask.getProcessInstanceId();
					taskDefinitionId = wfHistoricTask.getTaskDefinitionKey();
					taskName=wfHistoricTask.getName();
				} else {
					processDefinitionId = wfTask.getProcessDefinitionId();
					processInstanceId = wfTask.getProcessInstanceId();
					taskDefinitionId = wfTask.getTaskDefinitionId();
					taskName=wfTask.getName();
				}
				
				// 通过流程定义和事项、环节定义查到外围配置信息
				WfProcessConfigProperty wfProcessConfigProperty = wfManagerService.findProcessConfigProperty(processDefinitionId,moduleId,taskDefinitionId);
				
				if(null==wfProcessConfigProperty){
					wfProcessConfigProperty=new WfProcessConfigProperty();
				}
				
				//外围配置为空以流程定义中的表单为准
				if (wfProcessConfigProperty.getBindForm() == null|| "".equals(wfProcessConfigProperty.getBindForm().trim())) {
					String bindFormInDefinition = wfFormService.getTaskFormKey(processDefinitionId,taskDefinitionId);
					wfProcessConfigProperty.setBindForm(bindFormInDefinition);
				}
				
				if (wfProcessConfigProperty.getBindForm() == null|| "".equals(wfProcessConfigProperty.getBindForm().trim())){
					throw new C2FlowRuntimeException("["+taskName+"]环节表单配置空，无法自动进入表单！");
				}

				String alias = wfProcessConfigProperty.getAlias();
				String bindForm = wfProcessConfigProperty.getBindForm();
				String configId = wfProcessConfigProperty.getConfigId();
				Integer duration = wfProcessConfigProperty.getDuration();
				String durationUnit = wfProcessConfigProperty.getDurationUnit();
				String performer = wfProcessConfigProperty.getPerformer();
				String taskDefKey = wfProcessConfigProperty.getTaskDefKey();

				String hrefUrlPre = "";
				hrefUrlPre = bindForm;

				if (!StringUtils.isEmpty(moduleId)) {
					paramMap.put("moduleId", moduleId);
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
//						hrefParamArr.add("bindForm=" + bindForm);
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
	
			} else {
				
				// 启动流程
				Map<String, String> body = new HashMap<String, String>();
				body.put("moduleCode",menuCode);
				
				WfModuleBean wfModuleBean = wfExtendService.queryByMenuCode(menuCode);
				if(null==wfModuleBean)  throw new C2FlowRuntimeException("不存在流程事项Code["+menuCode+"]");
				
				moduleId =wfModuleBean.getId(); 
				
				WfProcessDefinition wfProcessDefinition = wfManagerService.getBindProcessByModuleId(moduleId);
				if(null==wfProcessDefinition) throw new C2FlowRuntimeException("事项Code["+menuCode+"]还未和任何流程定义进行绑定！");
				String processDefinitionId = wfProcessDefinition.getId();
				
				WfProcessConfigProperty wfProcessConfigProperty=findProcessStartConfig(moduleId, processDefinitionId);
				if(null==wfProcessConfigProperty||StringUtils.isEmpty(wfProcessConfigProperty.getBindForm())) throw new C2FlowRuntimeException("流程开始环节表单配置空，无法自动进入启动表单！");
				
				String hrefUrlPre = "";
				String alias = wfProcessConfigProperty.getAlias();
				String bindForm = wfProcessConfigProperty.getBindForm();
				String configId = wfProcessConfigProperty.getConfigId();
				Integer duration = wfProcessConfigProperty.getDuration();
				String durationUnit = wfProcessConfigProperty.getDurationUnit();
				String performer = wfProcessConfigProperty.getPerformer();
				String taskDefKey = wfProcessConfigProperty.getTaskDefKey();
				String groupPerformer = wfProcessConfigProperty.getGroupPerformer();

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
			
			if(CommonUtil.stringNullOrEmpty(aliasStr)){
				//别名为空，需要查询任务的名称
				if(taskId != null && !"".equals(taskId)){
					WfHistoricTask wfHistoricTask = wfHistoryService.getHistoricTaskById(taskId);
					tabName = wfHistoricTask.getName();
				}else{
					WfProcessDefinition wfProcessDefinition = wfRepositoryService.getProcessDefinition(processDefinitionIdStr);
					String wfProcessName = wfProcessDefinition.getName();
					tabName = wfProcessName;
				}
			}else{
				tabName = aliasStr;
			}
			
			
			return new ResponseFactory().forward(formUrl, tabName, paramMap);
			
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseFactory().createResponseBodyException(e);
		}
		
	}

	
	/**
	 * 获取开始节点流程配置
	 * @param moduleId
	 * @param processDefinitionId
	 * @return
	 * @throws Exception
	 */
	public WfProcessConfigProperty findProcessStartConfig(String moduleId,String processDefinitionId) throws Exception {
		
		WfProcessConfigProperty wfProcessConfigProperty = null;
		List<WfActivity> wfActivityList = wfRepositoryService.getActivitiesByDefinition(processDefinitionId);
		for (WfActivity wfActivity : wfActivityList) {
			if ("startEvent".equals(wfActivity.getProperties().get("type"))){
				wfProcessConfigProperty = wfManagerService.findProcessConfigProperty(processDefinitionId, moduleId,wfActivity.getId());
			}
		}
		
		// 如果流程定义图中配置了表单，如果外围配置没配置表单，可以用流程定义中的表单，但是外围配置表单优先级>流程定义的表单
		if (null==wfProcessConfigProperty){
			wfProcessConfigProperty = new WfProcessConfigProperty();
		}
		
		if(StringUtils.isEmpty(wfProcessConfigProperty.getBindForm())){
			// 以流程定义中的表单为准
			String bindFormInDefinition = wfFormService.getStartFormKey(processDefinitionId);
			wfProcessConfigProperty.setBindForm(bindFormInDefinition);
		}
		return wfProcessConfigProperty;
	}
}
