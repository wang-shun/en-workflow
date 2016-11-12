package com.chinacreator.c2.flow;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.chinacreator.c2.flow.detail.ChooseGroup;
import com.chinacreator.c2.flow.detail.WfActivity;
import com.chinacreator.c2.flow.detail.WfAttachment;
import com.chinacreator.c2.flow.detail.WfComment;
import com.chinacreator.c2.flow.detail.WfDeployment;
import com.chinacreator.c2.flow.detail.WfDeploymentParam;
import com.chinacreator.c2.flow.detail.WfDetail;
import com.chinacreator.c2.flow.detail.WfDetailParam;
import com.chinacreator.c2.flow.detail.WfFormData;
import com.chinacreator.c2.flow.detail.WfGroup;
import com.chinacreator.c2.flow.detail.WfGroupParam;
import com.chinacreator.c2.flow.detail.WfHistoricProcessInstance;
import com.chinacreator.c2.flow.detail.WfHistoricProcessInstanceParam;
import com.chinacreator.c2.flow.detail.WfHistoricTask;
import com.chinacreator.c2.flow.detail.WfHistoricTaskParam;
import com.chinacreator.c2.flow.detail.WfHistoricVariableInstance;
import com.chinacreator.c2.flow.detail.WfHoliday;
import com.chinacreator.c2.flow.detail.WfIdentityLink;
import com.chinacreator.c2.flow.detail.WfModel;
import com.chinacreator.c2.flow.detail.WfModelParam;
import com.chinacreator.c2.flow.detail.WfModuleDelegate;
import com.chinacreator.c2.flow.detail.WfModuleDelegateInfo;
import com.chinacreator.c2.flow.detail.WfModuleDelegateParam;
import com.chinacreator.c2.flow.detail.WfOperateLogBean;
import com.chinacreator.c2.flow.detail.WfOperator;
import com.chinacreator.c2.flow.detail.WfPageList;
import com.chinacreator.c2.flow.detail.WfPageParam;
import com.chinacreator.c2.flow.detail.WfProcessConfigProperty;
import com.chinacreator.c2.flow.detail.WfProcessDefinition;
import com.chinacreator.c2.flow.detail.WfProcessDefinitionParam;
import com.chinacreator.c2.flow.detail.WfProcessInstance;
import com.chinacreator.c2.flow.detail.WfProcessInstanceParam;
import com.chinacreator.c2.flow.detail.WfResource;
import com.chinacreator.c2.flow.detail.WfResult;
import com.chinacreator.c2.flow.detail.WfTask;
import com.chinacreator.c2.flow.detail.WfTaskAction;
import com.chinacreator.c2.flow.detail.WfTaskParam;
import com.chinacreator.c2.flow.detail.WfUniteColumn;
import com.chinacreator.c2.flow.detail.WfUniteConfig;
import com.chinacreator.c2.flow.detail.WfUniteTaskResult;
import com.chinacreator.c2.flow.detail.WfUser;
import com.chinacreator.c2.flow.detail.WfUserParam;
import com.chinacreator.c2.flow.detail.WfVariable;
import com.chinacreator.c2.flow.detail.WfVariable.VariableScope;
import com.chinacreator.c2.flow.detail.WfVariableParam;
import com.chinacreator.c2.flow.detail.WfWorkDate;
import com.chinacreator.c2.flow.detail.WfWorkDateParam;
import com.chinacreator.c2.flow.util.WfUtils.OrderDirection;

/**
 * c2平台规则服务类，注意：所有XXX.rule文件都通过该类调用工作流接口，而不要直接调用WfRepositoryService,
 * WfHistoryService, WfManagerService, WfFormService
 * 
 * @author minghua.guo
 * 
 */
@RestController
@RequestMapping("workflowrule")
public class C2FlowRuleService {

	public WfFormData getStartFormData(String processDefinitionId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfFormData getTaskFormData(String taskId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfProcessInstance saveStartFormData(WfOperator wfOperator, WfFormData data, String businessKey)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String saveTaskFormData(WfOperator wfOperator, WfFormData data) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, String> getEngineProperties() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, String> getEngineInfo() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfUser getUserById(String userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfPageList<WfUser, WfUserParam> queryUsers(WfUserParam params) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String addUser(WfUser user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String updateUser(WfUser user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteUserById(String userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfGroup getGroupById(String groupId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfPageList<WfGroup, WfGroupParam> queryGroups(WfGroupParam params) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping(value = "getbindprocess", method = RequestMethod.GET)
	public WfProcessDefinition getBindProcessByModuleId(@RequestParam() String moduleId) throws Exception {
		return WfApiFactory.getWfManagerService().getBindProcessByModuleId(moduleId);
	}

	@RequestMapping(value = "bindprocesstomodule", method = RequestMethod.POST)
	public void bindProcessToModule(@RequestParam() String moduleId, @RequestBody() WfProcessDefinition wpd)
			throws Exception {
		WfApiFactory.getWfManagerService().bindProcessToModule(moduleId, wpd);
	}

	public void unBindProcessFromModule(String moduleId, WfProcessDefinition wpd) throws Exception {
		WfApiFactory.getWfManagerService().unBindProcessFromModule(moduleId, wpd);
	}

	public List<String> getBindModuleIdsByProcessDefKey(String processDefinitionKey) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WfUniteColumn> findWfUniteColumns(String appId, String tenantId, String engineName, String taskType)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WfUniteConfig> findWfUniteConfig(String appId, String tenantId, String engineName, String taskType)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveWfUniteConfig(WfUniteConfig data) throws Exception {
		// TODO Auto-generated method stub

	}

	public void saveWfUniteColumn(WfUniteColumn data) throws Exception {
		// TODO Auto-generated method stub

	}
	@RequestMapping(value="getprocessconfig",method=RequestMethod.GET)
	public WfProcessConfigProperty findProcessConfigProperty(@RequestParam() String processDefinitionId,
			@RequestParam() String moduleId,
			@RequestParam() String taskDefKey) throws Exception {
		return WfApiFactory.getWfManagerService().findProcessConfigProperty(processDefinitionId, moduleId, taskDefKey);
	}

	@RequestMapping(value="saveprocessconfig",method=RequestMethod.POST)
	public void saveProcessConfigProperty(@RequestBody() JSONObject jsonObject) throws Exception {
		String processDefinitionId = jsonObject.getString("processDefinitionId");
		String taskDefinitionId = jsonObject.getString("taskDefinitionId"); 
		String moduleId = jsonObject.getString("moduleId"); 
		WfProcessConfigProperty wpcp = jsonObject.getObject("wpcp", WfProcessConfigProperty.class);
		WfApiFactory.getWfManagerService().saveProcessConfigProperty(				
				processDefinitionId, taskDefinitionId, moduleId, wpcp);
	}

	public WfPageList<WfModuleDelegate, WfModuleDelegateParam> getModuleDelegateByParam(
			WfModuleDelegateParam wfModuleDelegateParam) throws Exception {
		// TODO Auto-generated method stub
		// return null;
		return WfApiFactory.getWfManagerService().getModuleDelegateByParam(wfModuleDelegateParam);
	}

	public WfPageList<WfOperateLogBean, WfPageParam> findWfOperateLog(Map<String, Object> parameters, int firstResult,
			int maxResults) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfOperateLogBean getWfOperateLogById(String id) throws Exception {
		return WfApiFactory.getWfManagerService().getWfOperateLogById(id);
	}

	public Map<String, String> saveModuleDelegate(WfModuleDelegate wfModuleDelegate,
			List<WfModuleDelegateInfo> wfModuleDelegateInfoList) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, String> saveModuleDelegateArr(WfModuleDelegate wfModuleDelegate,
			WfModuleDelegateInfo[] wfModuleDelegateInfoArr) throws Exception {
		return WfApiFactory.getWfManagerService().saveModuleDelegateArr(wfModuleDelegate, wfModuleDelegateInfoArr);
	}

	public void deleteModuleDelegate(String delegateId) throws Exception {
		WfApiFactory.getWfManagerService().deleteModuleDelegate(delegateId);
	}

	public WfHistoricProcessInstance getHistoricProcessInstanceById(String processInstanceId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfPageList<WfHistoricProcessInstance, WfHistoricProcessInstanceParam> queryHistoricProcessInstances(
			WfHistoricProcessInstanceParam params) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteHistoricProcessInstance(String processInstanceId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WfIdentityLink> getHistoricProcessInstanceCandidates(String processInstanceId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WfHistoricVariableInstance> getHistoricProcessInstanceVariables(String processInstanceId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String addHistoricProcessInstanceComment(WfOperator wfOperator, String processInstanceId,
			WfComment wfComment) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WfComment> getAllHistoricProcessInstanceComments(String processInstanceId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfComment getHistoricProcessInstanceComment(String processInstanceId, String commentId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteHistoricProcessInstanceComment(WfOperator wfOperator, String processInstanceId,
			String commentId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfHistoricTask getHistoricTaskById(String taskId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfPageList<WfHistoricTask, WfHistoricTaskParam> queryHistoricTasks(WfHistoricTaskParam params)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteHistoricTask(WfOperator wfOperator, String taskId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WfIdentityLink> getHistoricTaskCandidates(String taskId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Object> getHistoricTaskVariables(String taskId, VariableScope scope) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getHistoricTaskVariableByName(String taskId, String variableName, VariableScope scope)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfPageList<Map<String, Object>, WfVariableParam> queryHistoricVariables(WfVariableParam params)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfPageList<WfDetail, WfDetailParam> queryHistoricDetails(WfDetailParam params) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfPageList<WfDeployment, WfDeploymentParam> queryDeployments(WfDeploymentParam wfDeploymentParam)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfDeployment getDeploymentById(String deploymentId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String deployDiagramClassPath(WfOperator wfOperator, String name, String category, String resourceClassPath)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String deployDiagramContent(WfOperator wfOperator, String name, String category, String resourceName,
			String resourceContent) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String deployDiagramZip(WfOperator wfOperator, String name, String category, InputStream inputStream)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteDeploymentsById(WfOperator wfOperator, boolean casecade, String deploymentId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WfResource> queryResourcesByDeploymentId(String deploymentId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfResource getDeploymentResourceById(String deploymentId, String resourceId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfPageList<WfProcessDefinition, WfProcessDefinitionParam> queryProcessDefinitions(
			WfProcessDefinitionParam wfProcessDefinitionParam) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfProcessDefinition getProcessDefinition(String processDefinitionId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String updateProcessDefinitionCategory(WfOperator wfOperator, String processDefinitionId, String category)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getProcessDefinitionBPMN(String processDefinitionId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String suspendProcessDefinition(WfOperator wfOperator, String processDefinitionId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String activateProcessDefinition(WfOperator wfOperator, String processDefinitionId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WfIdentityLink> getProcessDefinitionCandidates(String processDefinitionId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfIdentityLink addProcessDefinitionCandidate(WfOperator wfOperator, String processDefinitionId,
			String identityLinkType, String identityId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteProcessDefinitionCandidate(WfOperator wfOperator, String processDefinitionId,
			String identityLinkType, String identityId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfPageList<WfModel, WfModelParam> queryModels(WfModelParam wfModelParam) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfModel getModelById(String modelId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String addModel(WfOperator wfOperator, WfModel wfModel) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String updateModel(WfOperator wfOperator, String modelId, WfModel wfModel) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteModelsById(WfOperator wfOperator, String modelId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getModelEditorSource(String modelId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String saveModelEditorSource(WfOperator wfOperator, String modelId, String modelSource) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getModelEditorSourceExtra(String modelId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String saveModelEditorSourceExtra(WfOperator wfOperator, String modelId, String modelSource)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNextActivityId(String processDefinitionId, String currenTaskDefId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Object> queryVariablesOfActivityInDefinition(String processDefinitionId, String taskDefId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfProcessInstance getProcessInstanceById(String processInstanceId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteProcessInstancesById(WfOperator wfOperator, String deleteReason, String processInstanceId)
			throws Exception {
		return WfApiFactory.getWfRuntimeService().deleteProcessInstancesById(wfOperator, deleteReason,
				processInstanceId);
	}

	public WfResult startProcessInstanceById(WfOperator wfOperator, String bussinessId, String processDefinitionId,
			Map<String, Object> variables) throws Exception {
		return WfApiFactory.getWfRuntimeService().startProcessInstanceById(wfOperator, bussinessId, processDefinitionId,
				variables);
	}

	public WfResult startProcessInstanceByKey(WfOperator wfOperator, String bussinessId, String processDefinitionKey,
			Map<String, Object> variables) throws Exception {
		return WfApiFactory.getWfRuntimeService().startProcessInstanceByKey(wfOperator, bussinessId,
				processDefinitionKey, variables);
	}

	public String getCurrentActiveTaskIds(String processInstanceId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfResult suspendProcessInstance(WfOperator wfOperator, String processInstanceId) throws Exception {
		return WfApiFactory.getWfRuntimeService().suspendProcessInstance(wfOperator, processInstanceId);
	}

	public WfResult activateProcessInstance(WfOperator wfOperator, String processInstanceId) throws Exception {
		return WfApiFactory.getWfRuntimeService().activateProcessInstance(wfOperator, processInstanceId);
	}

	public WfPageList<WfProcessInstance, WfProcessInstanceParam> queryProcessInstances(
			WfProcessInstanceParam wfProcessInstanceParam) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public InputStream getProcessInstanceDiagram(String processInstanceId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WfIdentityLink> getProcessInstanceCandidates(String processInstanceId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String addProcessInstanceCandidate(WfOperator wfOperator, String processInstanceId, String identityLinkType,
			String identityId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteProcessInstanceCandidate(WfOperator wfOperator, String processInstanceId,
			String identityLinkType, String identityId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Object> getProcessInstanceVariables(String processInstanceId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfVariable getProcessInstanceVariableByName(String processInstanceId, String variableName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String addVariableForProcessInstance(WfOperator wfOperator, String processInstanceId, WfVariable wfVariable)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfTask getTaskById(String taskId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfPageList<WfTask, WfTaskParam> queryTasks(WfTaskParam params) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String updateTask(WfTask wfTask) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfResult operateTask(WfOperator wfOperator, String taskId, WfTaskAction action, String userToDelegateTo,
			Map<String, Object> variables) throws Exception {
		return WfApiFactory.getWfRuntimeService().operateTask(wfOperator, taskId, action, userToDelegateTo, variables);
	}

	public String deleteTask(WfOperator wfOperator, String taskId, Boolean cascadeHistory, String deleteReason)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Object> getTaskVariables(String taskId, VariableScope scope) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Object> getTaskVariableByName(String taskId, String variableName, VariableScope scope)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String addTaskVariable(WfOperator wfOperator, String taskId, WfVariable wfVariable) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String updateTaskVariable(WfOperator wfOperator, String taskId, WfVariable wfVariable) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteTaskVariable(WfOperator wfOperator, String taskId, String variableName, VariableScope scope)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteLocalTaskVariable(WfOperator wfOperator, String taskId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WfIdentityLink> getTaskCandidates(String taskId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WfIdentityLink> getTaskCandidatesByType(String taskId, String identityLinkType) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String addTaskCandidate(WfOperator wfOperator, String taskId, String identityLinkType, String identityId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteTaskCandidate(WfOperator wfOperator, String taskId, String identityLinkType, String identityId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String addTaskComment(WfOperator wfOperator, WfComment wfComment) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WfComment> getTaskComments(String taskId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfComment getTaskCommentById(String commentId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteTaskComment(WfOperator wfOperator, String commentId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WfComment> getTaskEvents(String taskId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfComment getTaskEventById(String eventId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String addTaskAttachment(WfOperator wfOperator, WfAttachment attachment) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WfAttachment> getTaskAttachments(String taskId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfAttachment getTaskAttachmentById(String attachmentId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WfActivity> getNextUserTaskDefByTaskId(String taskId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String deleteTaskAttachmentById(WfOperator wfOperator, String attachmentId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfResult reject(WfOperator wfOperator, String currenTaskId, String rejectMessage,
			Map<String, Object> variables) throws Exception {
		return WfApiFactory.getWfRuntimeService().reject(wfOperator, currenTaskId, rejectMessage, variables);
	}

	public WfResult goAnyWhere(WfOperator wfOperator, boolean isStart, String bussinessKey, String processDefinitionId,
			String currenTaskId, String destTaskDefinitionKey, boolean useHisAssignee, Map<String, Object> variables)
			throws Exception {
		return WfApiFactory.getWfRuntimeService().goAnyWhere(wfOperator, isStart, bussinessKey, processDefinitionId,
				currenTaskId, destTaskDefinitionKey, useHisAssignee, variables);
	}

	public WfUniteTaskResult queryWfUniteConfig(String appId, String tenantId, String engineName, String taskList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfUniteTaskResult queryWfUniteRunTask(Map<String, Object> parameters, int firstResult, int maxResults)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfUniteTaskResult queryWfUniteHisTask(Map<String, Object> parameters, int firstResult, int maxResults)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfResult operateTaskTmp(WfOperator wfOperator, String taskId, String action, String userToDelegateTo,
			Map<String, Object> variables) throws Exception {
		return WfApiFactory.getWfRuntimeService().operateTaskTmp(wfOperator, taskId, action, userToDelegateTo,
				variables);
	}

	public Map<String, String> modifyModuleDelegate(WfModuleDelegate wfModuleDelegate,
			List<WfModuleDelegateInfo> wfModuleDelegateInfoList) throws Exception {
		return WfApiFactory.getWfManagerService().modifyModuleDelegate(wfModuleDelegate, wfModuleDelegateInfoList);
	}

	public Map<String, String> modifyModuleDelegateArr(WfModuleDelegate wfModuleDelegate,
			WfModuleDelegateInfo[] wfModuleDelegateInfoArr) throws Exception {
		return WfApiFactory.getWfManagerService().modifyModuleDelegateArr(wfModuleDelegate, wfModuleDelegateInfoArr);
	}

	public void updateModuleDelegateState(String delegateId, String delegateState, boolean acceptFlag)
			throws Exception {
		WfApiFactory.getWfManagerService().updateModuleDelegateState(delegateId, delegateState, acceptFlag);
	}

	public WfPageList<WfProcessDefinition, WfProcessDefinitionParam> queryProcessDefinitionsAndDeployInfoList(
			WfProcessDefinitionParam wfProcessDefinitionParam) throws Exception {
		return null;
	}

	public void saveModel(String modelId, String tenantId, String name, String description, String json_xml,
			String svg_xml) throws Exception {
		// TODO Auto-generated method stub

	}

	public String getDiagram(String processInstanceId, String processDefinitionId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getHighlighted(String processInstanceId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void addTaskListener(String processDefinitionKey) throws Exception {
		// TODO Auto-generated method stub

	}

	public void reBindProcessToModule(String moduleId, WfProcessDefinition wpd) throws Exception {
		WfApiFactory.getWfManagerService().reBindProcessToModule(moduleId, wpd);
	}

	public void addHoliday(WfHoliday wfHoliday) throws Exception {
		WfApiFactory.getWfManagerService().addHoliday(wfHoliday);

	}

	public void deleteHoliday(WfHoliday wfHoliday) throws Exception {
		WfApiFactory.getWfManagerService().deleteHoliday(wfHoliday);

	}

	public void setWorkDate(WfWorkDate wfWorkDate) throws Exception {
		WfApiFactory.getWfManagerService().setWorkDate(wfWorkDate);

	}

	public void addWorkDate(WfWorkDate wfWorkDate) throws Exception {
		WfApiFactory.getWfManagerService().addWorkDate(wfWorkDate);

	}

	public void deleteWorkDate(WfWorkDate wfWorkDate) throws Exception {
		WfApiFactory.getWfManagerService().deleteWorkDate(wfWorkDate);

	}

	public List<WfHoliday> getHolidayList(WfHoliday wfHoliday) throws Exception {

		return WfApiFactory.getWfManagerService().getHolidayList(wfHoliday);
	}

	public WfPageList<WfWorkDate, WfWorkDateParam> getWorkDateList(WfWorkDateParam wfWorkDateParam) throws Exception {
		return WfApiFactory.getWfManagerService().getWorkDateList(wfWorkDateParam);
	}

	public Date getDueDateAfterExecute(Date begineDate, Integer duration, String durationUnit, String tenantId)
			throws Exception {
		return WfApiFactory.getWfManagerService().getDueDateAfterExecute(begineDate, duration, durationUnit, tenantId);
	}

	public void deleteWorkDates(String[] workIds) throws Exception {
		WfApiFactory.getWfManagerService().deleteWorkDates(workIds);

	}

	public boolean setHoliday(WfHoliday wfHoliday) throws Exception {
		return WfApiFactory.getWfManagerService().setHoliday(wfHoliday);
	}

	public void setDefaultYearHoliday(String yHoliday) throws Exception {
		WfApiFactory.getWfManagerService().setDefaultYearHoliday(yHoliday);
	}

	public List<WfActivity> getActivitiesByDefinition(String processDefinitionId) throws Exception {
		return WfApiFactory.getWfRepositoryService().getActivitiesByDefinition(processDefinitionId);
	}

	public WfModel insertModel(WfOperator wfOperator, WfModel wfModel) throws Exception {
		return WfApiFactory.getWfRepositoryService().insertModel(wfOperator, wfModel);
	}

	public WfDeployment deployClassPath(WfOperator wfOperator, String name, String category, String resourceClassPath)
			throws Exception {
		return WfApiFactory.getWfRepositoryService().deployClassPath(wfOperator, name, category, resourceClassPath);
	}

	public WfDeployment deployContent(WfOperator wfOperator, String name, String category, String resourceName,
			String resourceContent) throws Exception {
		return WfApiFactory.getWfRepositoryService().deployContent(wfOperator, name, category, resourceName,
				resourceContent);
	}

	public WfDeployment deployZip(WfOperator wfOperator, String name, String category, InputStream inputStream)
			throws Exception {
		return WfApiFactory.getWfRepositoryService().deployZip(wfOperator, name, category, inputStream);
	}

	public WfDeployment deployByte(WfOperator wfOperator, byte[] bytes, String name, String category) throws Exception {
		return WfApiFactory.getWfRepositoryService().deployByte(wfOperator, bytes, name, category);
	}

	public List<String> getActiveActivityIds(String executionId) throws Exception {
		return WfApiFactory.getWfRuntimeService().getActiveActivityIds(executionId);
	}

	public String getStartFormKey(String processDefinitionId) {
		return WfApiFactory.getWfFormService().getStartFormKey(processDefinitionId);
	}

	public String getTaskFormKey(String processDefinitionId, String taskDefinitionKey) {
		return WfApiFactory.getWfFormService().getTaskFormKey(processDefinitionId, taskDefinitionKey);
	}

	public void addTaskListener(String processDefinitionKey, String tenantId) throws Exception {
		WfApiFactory.getWfManagerService().addTaskListener(processDefinitionKey, tenantId);

	}

	public List<String> getBindModuleIdsByProcessDefKeyAndTenant(String processDefinitionKey, String tenantId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfUniteTaskResult queryWfUniteHisTask(String userId, List<ChooseGroup> chooseGroupList,
			Map<String, Object> parameters, int firstResult, int maxResults, Map<String, OrderDirection> orderBys)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public WfUniteTaskResult queryWfUniteRunTask(String userId, List<ChooseGroup> chooseGroupList,
			Map<String, Object> parameters, int firstResult, int maxResults, Map<String, OrderDirection> orderBys)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
