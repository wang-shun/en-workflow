package com.chinacreator.c2.flow.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.HistoricTaskInstanceQueryImpl;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.diagram.ProcessDiagramGenerator;
import org.activiti.engine.impl.persistence.entity.AttachmentEntity;
import org.activiti.engine.impl.persistence.entity.ByteArrayEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.query.Query;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Attachment;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.DelegationState;
import org.activiti.engine.task.Event;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.IdentityLinkType;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.rest.common.api.ActivitiUtil;

import com.chinacreator.c2.flow.api.WfRuntimeService;
import com.chinacreator.c2.flow.cmd.JumpActivityCmd;
import com.chinacreator.c2.flow.cmd.unitetask.FindWfUniteHisTaskByConditionCmd;
import com.chinacreator.c2.flow.cmd.unitetask.FindWfUniteTaskByConditionCmd;
import com.chinacreator.c2.flow.cmd.unitetask.config.FindWfUniteConfigColumnsCmd;
import com.chinacreator.c2.flow.detail.ChooseGroup;
import com.chinacreator.c2.flow.detail.WfActivity;
import com.chinacreator.c2.flow.detail.WfAttachment;
import com.chinacreator.c2.flow.detail.WfComment;
import com.chinacreator.c2.flow.detail.WfConstants;
import com.chinacreator.c2.flow.detail.WfIdentityLink;
import com.chinacreator.c2.flow.detail.WfOperator;
import com.chinacreator.c2.flow.detail.WfPageList;
import com.chinacreator.c2.flow.detail.WfPageParam;
import com.chinacreator.c2.flow.detail.WfProcessDefinitionParam;
import com.chinacreator.c2.flow.detail.WfProcessInstance;
import com.chinacreator.c2.flow.detail.WfProcessInstanceParam;
import com.chinacreator.c2.flow.detail.WfResult;
import com.chinacreator.c2.flow.detail.WfTask;
import com.chinacreator.c2.flow.detail.WfTaskAction;
import com.chinacreator.c2.flow.detail.WfTaskParam;
import com.chinacreator.c2.flow.detail.WfUniteTaskResult;
import com.chinacreator.c2.flow.detail.WfVariable;
import com.chinacreator.c2.flow.detail.WfVariable.VariableScope;
import com.chinacreator.c2.flow.util.CommonUtil;
import com.chinacreator.c2.flow.util.LoggerManager;
import com.chinacreator.c2.flow.util.LoggerManager.LoggerType;
import com.chinacreator.c2.flow.util.WfUtils.OrderDirection;

public class WfRuntimeServiceImpl implements WfRuntimeService {
	private RepositoryService repositoryService;
	private RuntimeService runtimeService;
	private TaskService taskService;
	private IdentityService identityService;
	private HistoryService historyService;
	private ManagementService managementService;

	@Override
	public WfProcessInstance getProcessInstanceById(String processInstanceId)
			throws Exception {
		ProcessInstance pi = runtimeService.createProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();
		if (pi != null) {
			WfProcessInstance wpi = new WfProcessInstance();
			wpi.setActivityId(pi.getActivityId());
			wpi.setBusinessKey(pi.getBusinessKey());
			wpi.setEnded(pi.isEnded());
			wpi.setProcessDefinitionId(pi.getProcessDefinitionId());
			wpi.setProcessInstanceId(pi.getProcessInstanceId());
			wpi.setProcessVariables(pi.getProcessVariables());
			wpi.setSuspended(pi.isSuspended());
			return wpi;
		}
		return null;
	}

	@Override
	public String deleteProcessInstancesById(WfOperator wfOperator,
			String deleteReason, String processInstanceId) throws Exception {
		try {
			runtimeService.deleteProcessInstance(processInstanceId,
					deleteReason);
			LoggerManager.log(getClass(), LoggerType.DEBUG, wfOperator, null,
					"删除流程成功:流程ids={},删除原因={}", processInstanceId, deleteReason);
			return WfConstants.WF_CONTROL_EXE_SUCCESS;
		} catch (ActivitiObjectNotFoundException e) {
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOperator, e,
					"删除流程失败,删除的对象不存在:流程ids={},删除原因={}", processInstanceId,
					deleteReason);
			return WfConstants.WF_CONTROL_EXE_NOOBJECT;
		} catch (Exception e) {
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOperator, e,
					"删除流程失败:流程ids={},删除原因={}", processInstanceId, deleteReason);
			throw e;
		}
	}

	@Override
	public WfResult startProcessInstanceById(WfOperator wfOperator,
			String bussinessKey, String processDefinitionId,
			Map<String, Object> variables) throws Exception {
		WfResult wfResult = new WfResult();

		String userId = wfOperator.getUserId();
		ProcessInstance processInstance = null;
		try {
			// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
			identityService.setAuthenticatedUserId(userId);
			// addTaskListenerByProcessDefinitionId(processDefinitionId);
			if(variables == null){
				variables = new HashMap<String, Object>();
			}
			if (variables != null){
				variables.put(WfConstants.WF_BUSINESS_DATA_KEY,
						wfOperator.getBusinessData());
				for(String key : variables.keySet()){
					if("".equals(variables.get(key))){
						variables.put(key, null);
					}
				}
			}

			// 调用activiti的运行时接口启动流程生成实例
			if (processDefinitionId != null) {
				processInstance = runtimeService.startProcessInstanceById(
						processDefinitionId, bussinessKey, variables);
			}
			String processInstanceId = processInstance.getProcessInstanceId();
			wfResult.setProcessInstanceId(processInstanceId);
			String nextTaskIds = getCurrentActiveTaskIds(processInstanceId);
			wfResult.setNextTaskId(nextTaskIds);
			wfResult.setResult(WfConstants.WF_CONTROL_EXE_SUCCESS);
			LoggerManager
					.log(getClass(),
							LoggerType.DEBUG,
							wfOperator,
							null,
							"启动流程成功： processDefinitionId={}, businessId={}, processInstanceId={}, nextTaskIds={}, variables={}",
							processDefinitionId, bussinessKey,
							processInstanceId, nextTaskIds, variables);
		} catch (Exception e) {
			wfResult.setResult(WfConstants.WF_CONTROL_EXE_FAIL);
			LoggerManager
					.log(getClass(),
							LoggerType.DEBUG,
							wfOperator,
							null,
							"启动流程失败：processDefinitionId={}, businessId={}, variables={}",
							processDefinitionId, bussinessKey, variables);
			throw e;
		} finally {
			identityService.setAuthenticatedUserId(null);
		}
		return wfResult;
	}

	@Override
	public String getCurrentActiveTaskIds(String processInstanceId)
			throws Exception {
		String nextTaskIds = null;
		List<Task> taskList = taskService.createTaskQuery()
				.processInstanceId(processInstanceId).list();
		if (null != taskList && !taskList.isEmpty()) {
			nextTaskIds = "";
			for (Task task : taskList) {
				nextTaskIds += task.getId() + ",";
			}
		}
		if (nextTaskIds != null && nextTaskIds.length() > 0) {
			nextTaskIds = nextTaskIds.substring(0, nextTaskIds.length() - 1);
		}
		return nextTaskIds;
	}

	@Override
	public WfResult startProcessInstanceByKey(WfOperator wfOperator,
			String bussinessKey, String processDefinitionKey,
			Map<String, Object> variables) throws Exception {
		WfResult wfResult = new WfResult();

		String userId = wfOperator.getUserId();
		String tenantId = wfOperator.getTenantId();
		ProcessInstance processInstance = null;
		try {
			// ProcessDefinition processDefinition = repositoryService
			// .createProcessDefinitionQuery()
			// .processDefinitionKey(processDefinitionKey).latestVersion()
			// .singleResult();
			// addTaskListenerByProcessDefinitionId(processDefinition.getId());
			if(variables == null){
				variables = new HashMap<String, Object>();
			}
			if (variables != null){
				variables.put(WfConstants.WF_BUSINESS_DATA_KEY,
						wfOperator.getBusinessData());
				for(String key : variables.keySet()){
					if("".equals(variables.get(key))){
						variables.put(key, null);
					}
				}
			}
			// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
			identityService.setAuthenticatedUserId(userId);

			// 调用activiti的运行时接口启动流程生成实例
			if (processDefinitionKey != null) {
				if (tenantId != null) {
					processInstance = runtimeService
							.startProcessInstanceByKeyAndTenantId(
									processDefinitionKey, bussinessKey,
									variables, tenantId);
				} else {
					processInstance = runtimeService.startProcessInstanceByKey(
							processDefinitionKey, bussinessKey, variables);
				}
			}
			String processInstanceId = processInstance.getProcessInstanceId();
			wfResult.setProcessInstanceId(processInstanceId);
			String nextTaskIds = getCurrentActiveTaskIds(processInstanceId);
			wfResult.setNextTaskId(nextTaskIds);
			wfResult.setResult(WfConstants.WF_CONTROL_EXE_SUCCESS);
			LoggerManager
					.log(getClass(),
							LoggerType.DEBUG,
							wfOperator,
							null,
							"启动流程成功： processDefinitionKey={}, businessId={}, processInstanceId={}, nextTaskIds={}, variables={}",
							processDefinitionKey, bussinessKey,
							processInstanceId, nextTaskIds, variables);
		} catch (Exception e) {
			wfResult.setResult(WfConstants.WF_CONTROL_EXE_FAIL);
			LoggerManager
					.log(getClass(),
							LoggerType.ERROR,
							wfOperator,
							e,
							"启动流程失败： processDefinitionKey={}, businessId={}, variables={}",
							processDefinitionKey, bussinessKey, variables);
			throw e;
		} finally {
			identityService.setAuthenticatedUserId(null);
		}
		return wfResult;
	}

	@Override
	public WfResult suspendProcessInstance(WfOperator wfOperator,
			String processInstanceId) throws Exception {
		WfResult wfResult = new WfResult();
		String userId = wfOperator.getUserId();
		try {
			// 用来设置操作流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
			identityService.setAuthenticatedUserId(userId);
			ProcessInstance processInstance = runtimeService
					.createProcessInstanceQuery()
					.processInstanceId(processInstanceId).singleResult();
			// addTaskListenerByProcessDefinitionId(processInstance
			// .getProcessDefinitionId());
			if (processInstance != null) {
				if (!processInstance.isSuspended()) {
					runtimeService
							.suspendProcessInstanceById(processInstanceId);
					wfResult.setResult(WfConstants.WF_CONTROL_EXE_SUCCESS);
					LoggerManager.log(getClass(), LoggerType.DEBUG, wfOperator,
							null, "挂起流程成功：processInstanceId={}",
							processInstanceId);
				} else {
					wfResult.setResult(WfConstants.WF_CONTROL_EXE_FAIL);
					LoggerManager.log(getClass(), LoggerType.DEBUG, wfOperator,
							null, "挂起流程失败，不能重复挂起：processInstanceId={}",
							processInstanceId);
					wfResult.setResult(WfConstants.WF_CONTROL_EXE_FAIL);
				}
			}
		} catch (Exception e) {
			wfResult.setResult(WfConstants.WF_CONTROL_EXE_FAIL);
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOperator, e,
					"挂起流程 失败：processInstanceId={}", processInstanceId);
			throw e;
		} finally {
			identityService.setAuthenticatedUserId(null);
		}
		return wfResult;
	}

	@Override
	public WfResult activateProcessInstance(WfOperator wfOperator,
			String processInstanceId) throws Exception {
		WfResult wfResult = new WfResult();
		String userId = wfOperator.getUserId();
		try {
			// 用来设置操作流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
			identityService.setAuthenticatedUserId(userId);
			ProcessInstance processInstance = runtimeService
					.createProcessInstanceQuery()
					.processInstanceId(processInstanceId).singleResult();
			boolean suspendFlag = processInstance.isSuspended();
			// addTaskListenerByProcessDefinitionId(processInstance
			// .getProcessDefinitionId());
			if (suspendFlag) {
				runtimeService.activateProcessInstanceById(processInstanceId);
				wfResult.setResult(WfConstants.WF_CONTROL_EXE_SUCCESS);
				LoggerManager
						.log(getClass(), LoggerType.DEBUG, wfOperator, null,
								"激活恢复流程成功：processInstanceId={}",
								processInstanceId);
			} else {
				wfResult.setResult(WfConstants.WF_CONTROL_EXE_FAIL);
				LoggerManager.log(getClass(), LoggerType.ERROR, wfOperator,
						null, "激活恢复流程 失败，不能重复激活：processInstanceId={}",
						processInstanceId);
			}
		} catch (Exception e) {
			wfResult.setResult(WfConstants.WF_CONTROL_EXE_FAIL);
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOperator, e,
					"激活恢复流程失败：processInstanceId={}", processInstanceId);
			throw e;
		} finally {
			identityService.setAuthenticatedUserId(null);
		}
		return wfResult;
	}

	@Override
	public WfPageList<WfProcessInstance, WfProcessInstanceParam> queryProcessInstances(
			WfProcessInstanceParam wfProcessInstanceParam) throws Exception {
		WfPageList<WfProcessInstance, WfProcessInstanceParam> results = new WfPageList<WfProcessInstance, WfProcessInstanceParam>();
		ProcessInstanceQuery processInstanceQuery = runtimeService
				.createProcessInstanceQuery();
		String id = wfProcessInstanceParam
				.getId();
		String processDefinitionId = wfProcessInstanceParam
				.getProcessDefinitionId();
		String businessKey = wfProcessInstanceParam.getBusinessKey();
		String processDefinitionKey = wfProcessInstanceParam
				.getProcessDefinitionKey();
		Boolean excludeSubprocesses = wfProcessInstanceParam
				.getExcludeSubprocesses();
		Boolean includeProcessVariables = wfProcessInstanceParam
				.getIncludeProcessVariables();
		String involvedUser = wfProcessInstanceParam.getInvolvedUser();
		Boolean orderByProcessDefinitionId = wfProcessInstanceParam
				.getOrderByProcessDefinitionId();
		Boolean orderByProcessDefinitionKey = wfProcessInstanceParam
				.getOrderByProcessDefinitionKey();
		Boolean orderByProcessInstanceId = wfProcessInstanceParam
				.getOrderByProcessInstanceId();
		Boolean orderByTenantId = wfProcessInstanceParam.getOrderByTenantId();
		String subProcessInstanceId = wfProcessInstanceParam
				.getSubProcessInstanceId();
		String superProcessInstanceId = wfProcessInstanceParam
				.getSuperProcessInstanceId();
		Boolean suspended = wfProcessInstanceParam.getSuspended() == null ? false
				: wfProcessInstanceParam.getSuspended();
		String tenantId = wfProcessInstanceParam.getTenantId();
		String tenantIdLike = wfProcessInstanceParam.getTenantIdLike();
		Boolean withoutTenantId = wfProcessInstanceParam.getWithoutTenantId();
		if (id != null) {
			processInstanceQuery.processInstanceId(id);
		}
		if (processDefinitionId != null) {
			processInstanceQuery.processDefinitionId(processDefinitionId);
		}
		if (businessKey != null) {
			processInstanceQuery.processInstanceBusinessKey(businessKey);
		}
		if (processDefinitionKey != null) {
			processInstanceQuery.processDefinitionKey(processDefinitionKey);
		}
		if (excludeSubprocesses) {
			processInstanceQuery.excludeSubprocesses(excludeSubprocesses);
		}
		if (includeProcessVariables) {
			processInstanceQuery.includeProcessVariables();
		}
		if (involvedUser != null) {
			processInstanceQuery.involvedUser(involvedUser);
		}
		if (orderByProcessDefinitionId) {
			processInstanceQuery.orderByProcessDefinitionId();
			setOrder(processInstanceQuery, wfProcessInstanceParam);
		}
		if (orderByProcessDefinitionKey) {
			processInstanceQuery.orderByProcessDefinitionKey();
			setOrder(processInstanceQuery, wfProcessInstanceParam);
		}
		if (orderByProcessInstanceId) {
			processInstanceQuery.orderByProcessInstanceId();
			setOrder(processInstanceQuery, wfProcessInstanceParam);
		}
		if (orderByTenantId) {
			processInstanceQuery.orderByTenantId();
			setOrder(processInstanceQuery, wfProcessInstanceParam);
		}
		if (subProcessInstanceId != null) {
			processInstanceQuery.subProcessInstanceId(subProcessInstanceId);
		}
		if (superProcessInstanceId != null) {
			processInstanceQuery.superProcessInstanceId(superProcessInstanceId);
		}
		if (suspended) {
			processInstanceQuery.suspended();
		}
		if (tenantId != null) {
			processInstanceQuery.processInstanceTenantId(tenantId);
		}
		if (tenantIdLike != null) {
			processInstanceQuery.processInstanceTenantIdLike(tenantIdLike);
		}
		if (withoutTenantId) {
			processInstanceQuery.processInstanceWithoutTenantId();
		}
		boolean paged = wfProcessInstanceParam.isPaged();
		List<ProcessInstance> pis = null;
		if (paged) {
			pis = processInstanceQuery.listPage(
					(int) wfProcessInstanceParam.getStart(),
					(int) wfProcessInstanceParam.getSize());
			long total = processInstanceQuery.count();
			wfProcessInstanceParam.setTotal(total);
		} else {
			pis = processInstanceQuery.list();
		}
		if (pis != null) {
			for (ProcessInstance pi : pis) {
				WfProcessInstance wpi = new WfProcessInstance();
				wpi.setActivityId(pi.getActivityId());
				wpi.setBusinessKey(pi.getBusinessKey());
				wpi.setEnded(pi.isEnded());
				wpi.setProcessDefinitionId(pi.getProcessDefinitionId());
				wpi.setProcessInstanceId(pi.getProcessInstanceId());
				wpi.setProcessVariables(pi.getProcessVariables());
				wpi.setSuspended(pi.isSuspended());
				results.add(wpi);
			}
			results.setWfQuery(wfProcessInstanceParam);
		}
		return results;
	}

	private void setOrder(Query<?, ?> query, WfPageParam wfPageParam) {
		String order = wfPageParam.getOrder();
		if (WfProcessDefinitionParam.SORT_ASC.equals(order)) {
			query.asc();
		}
		if (WfProcessDefinitionParam.SORT_DESC.equals(order)) {
			query.desc();
		}
	}

	@Override
	public InputStream getProcessInstanceDiagram(String processInstanceId)
			throws Exception {
		ProcessInstance processInstance = runtimeService
				.createProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();
		ProcessDefinitionEntity pde = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(processInstance
						.getProcessDefinitionId());
		if (pde != null && pde.isGraphicalNotationDefined()) {
			BpmnModel bpmnModel = ActivitiUtil.getRepositoryService()
					.getBpmnModel(pde.getId());
			InputStream resource = ProcessDiagramGenerator.generateDiagram(
					bpmnModel, "png", runtimeService
							.getActiveActivityIds(processInstance.getId()));
			return resource;
		} else {
			throw new Exception("id为 '" + processInstance.getId()
					+ "' 的流程实例没有定义的图形。");
		}
	}

	@Override
	public List<WfIdentityLink> getProcessInstanceCandidates(
			String processInstanceId) throws Exception {
		List<WfIdentityLink> results = null;
		List<IdentityLink> identityLinks = runtimeService
				.getIdentityLinksForProcessInstance(processInstanceId);
		if (identityLinks != null) {
			results = new ArrayList<WfIdentityLink>();
			for (IdentityLink identityLink : identityLinks) {
				results.add(coventWfIdentityLink(identityLink));
			}
		}
		return results;
	}

	@Override
	public String addProcessInstanceCandidate(WfOperator wfOperator,
			String processInstanceId, String identityLinkType, String identityId)
			throws Exception {
		try {
			runtimeService.addUserIdentityLink(processInstanceId, identityId,
					identityLinkType);
			LoggerManager
					.log(getClass(),
							LoggerType.DEBUG,
							wfOperator,
							null,
							"添加流程实例候选人成功：processInstanceId={},identityLinkType={},identityId={}",
							processInstanceId, identityLinkType, identityId);
			return WfConstants.WF_CONTROL_EXE_SUCCESS;
		} catch (ActivitiObjectNotFoundException e) {
			LoggerManager
					.log(getClass(),
							LoggerType.ERROR,
							wfOperator,
							e,
							"添加流程实例候选人失败，流程实例不存在：processInstanceId={},identityLinkType={},identityId={}",
							processInstanceId, identityLinkType, identityId);
			return WfConstants.WF_CONTROL_EXE_NOOBJECT;
		} catch (Exception e) {
			LoggerManager
					.log(getClass(),
							LoggerType.ERROR,
							wfOperator,
							e,
							"添加流程实例候选人失败：processInstanceId={},identityLinkType={},identityId={}",
							processInstanceId, identityLinkType, identityId);
			throw e;
		}
	}

	@Override
	public String deleteProcessInstanceCandidate(WfOperator wfOperator,
			String processInstanceId, String identityLinkType, String identityId)
			throws Exception {
		// 目前没有找到相关api实现
		return WfConstants.WF_CONTROL_EXE_FAIL;
	}

	@Override
	public Map<String, Object> getProcessInstanceVariables(
			String processInstanceId) throws Exception {
		Map<String, Object> results = new HashMap<String, Object>();
		List<Execution> executions = runtimeService.createExecutionQuery()
				.processInstanceId(processInstanceId).list();
		if (executions != null) {
			for (Execution exe : executions) {
				Map<String, Object> variables = runtimeService.getVariables(exe
						.getId());
				if (variables != null) {
					results.putAll(variables);
				}
			}
		}
		return results;
	}

	@Override
	public WfVariable getProcessInstanceVariableByName(
			String processInstanceId, String variableName) throws Exception {
		List<String> variableNames = new ArrayList<String>();
		variableNames.add(variableName);
		List<Execution> executions = runtimeService.createExecutionQuery()
				.processInstanceId(processInstanceId).list();
		if (executions != null) {
			WfVariable wfv = new WfVariable();
			for (Execution exe : executions) {
				Object value = runtimeService.getVariable(exe.getId(),
						variableName);
				wfv.setName(variableName);
				wfv.setValue(value);
				return wfv;
			}
		}
		return null;
	}

	@Override
	public String addVariableForProcessInstance(WfOperator wfOperator,
			String processInstanceId, WfVariable wfVariable) throws Exception {
		try {
//			VariableScope scope = wfVariable.getVariableScope();
			//if (VariableScope.LOCAL.equals(scope)) {
				runtimeService.setVariableLocal(processInstanceId,
						wfVariable.getName(), wfVariable.getValue());
			//}
		} catch (ActivitiObjectNotFoundException e) {
			e.printStackTrace();
			return WfConstants.WF_CONTROL_EXE_NOOBJECT;
		}
		return WfConstants.WF_CONTROL_EXE_SUCCESS;
	}

	@Override
	public WfTask getTaskById(String taskId) throws Exception {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		if (task != null) {
			WfTask wft = coventWfTask(task);
			return wft;
		}
		return null;
	}

	private WfTask coventWfTask(Task task) {
		WfTask wft = new WfTask();
		wft.setAssignee(task.getAssignee());
		wft.setCreateTime(task.getCreateTime());
		wft.setDescription(task.getDescription());
		wft.setDueDate(task.getDueDate());
		wft.setExecutionId(task.getExecutionId());
		wft.setProcessDefinitionId(task.getProcessDefinitionId());
		wft.setProcessInstanceId(task.getProcessInstanceId());
		wft.setSuspended(task.isSuspended());
		wft.setTaskDefinitionId(task.getTaskDefinitionKey());
		wft.setTaskId(task.getId());
		wft.setTaskLocalVariables(task.getTaskLocalVariables());
		wft.setTenantId(task.getTenantId());
		wft.setCategory(task.getCategory());
		wft.setName(task.getName());
		wft.setOwer(task.getOwner());
		wft.setParentTaskId(task.getParentTaskId());
		wft.setPriority(task.getPriority());
		wft.setProcessVariables(task.getProcessVariables());
		return wft;
	}

	@Override
	public WfPageList<WfTask, WfTaskParam> queryTasks(WfTaskParam params)
			throws Exception {
		WfPageList<WfTask, WfTaskParam> result = new WfPageList<WfTask, WfTaskParam>();
		TaskQuery taskQuery = taskService.createTaskQuery();
		Boolean active = params.getActive();
		if (active != null && active) {
			taskQuery.active();
		}
		String assignee = params.getAssignee();
		if (assignee != null) {
			taskQuery.taskAssignee(assignee);
		}
		String assigneeLike = params.getAssigneeLike();
		if (assigneeLike != null) {
			taskQuery.taskAssigneeLike(assigneeLike);
		}
		String candidateGroup = params.getCandidateGroup();
		if (candidateGroup != null) {
			taskQuery.taskCandidateGroup(candidateGroup);
		}
		String candidateUser = params.getCandidateUser();
		if (candidateUser != null) {
			taskQuery.taskCandidateUser(candidateUser);
		}
		Date createdAfter = params.getCreatedAfter();
		if (createdAfter != null) {
			taskQuery.taskCreatedAfter(createdAfter);
		}
		Date createdBefore = params.getCreatedBefore();
		if (createdBefore != null) {
			taskQuery.taskCreatedBefore(createdBefore);
		}
		Date createdOn = params.getCreatedOn();
		if (createdOn != null) {
			taskQuery.taskCreatedOn(createdOn);
		}
		String delegationState = params.getDelegationState();
		if (delegationState != null) {
			DelegationState ds = DelegationState.valueOf(delegationState);
			taskQuery.taskDelegationState(ds);
		}
		String description = params.getDescription();
		if (description != null) {
			taskQuery.taskDescription(description);
		}
		Date dueAfter = params.getDueAfter();
		if (dueAfter != null) {
			taskQuery.dueAfter(dueAfter);
		}
		Date dueBefore = params.getDueBefore();
		if (dueBefore != null) {
			taskQuery.dueBefore(dueBefore);
		}
		Date dueOn = params.getDueOn();
		if (dueOn != null) {
			taskQuery.dueDate(dueOn);
		}
		Boolean excludeSubTasks = params.getExcludeSubTasks();
		if (excludeSubTasks != null && excludeSubTasks) {
			taskQuery.excludeSubtasks();
		}
		String executionId = params.getExecutionId();
		if (executionId != null) {
			taskQuery.executionId(executionId);
		}
		Boolean includeProcessVariables = params.getIncludeProcessVariables();
		if (includeProcessVariables != null && includeProcessVariables) {
			taskQuery.includeProcessVariables();
		}
		Boolean includeTaskLocalVariables = params
				.getIncludeTaskLocalVariables();
		if (includeTaskLocalVariables != null && includeTaskLocalVariables) {
			taskQuery.includeTaskLocalVariables();
		}
		String involvedUser = params.getInvolvedUser();
		if (involvedUser != null) {
			taskQuery.taskInvolvedUser(involvedUser);
		}
		Integer maximumPriority = params.getMaximumPriority();
		if (maximumPriority != null) {
			taskQuery.taskMaxPriority(maximumPriority);
		}
		Integer minimumPriority = params.getMinimumPriority();
		if (minimumPriority != null) {
			taskQuery.taskMinPriority(minimumPriority);
		}
		String name = params.getName();
		if (name != null) {
			taskQuery.taskName(name);
		}
		String nameLike = params.getNameLike();
		if (nameLike != null) {
			taskQuery.taskNameLike(nameLike);
		}
		String owner = params.getOwner();
		if (owner != null) {
			taskQuery.taskOwner(owner);
		}
		String ownerLike = params.getOwnerLike();
		if (ownerLike != null) {
			taskQuery.taskOwnerLike(ownerLike);
		}
		Integer priority = params.getPriority();
		if (priority != null) {
			taskQuery.taskPriority(priority);
		}
		String processDefinitionKey = params.getProcessDefinitionKey();
		if (processDefinitionKey != null) {
			taskQuery.processDefinitionKey(processDefinitionKey);
		}
		String processDefinitionKeyLike = params.getProcessDefinitionKeyLike();
		if (processDefinitionKeyLike != null) {
			taskQuery.processDefinitionKeyLike(processDefinitionKeyLike);
		}
		String processDefinitionName = params.getProcessDefinitionName();
		if (processDefinitionName != null) {
			taskQuery.processDefinitionName(processDefinitionName);
		}
		String processDefinitionNameLike = params
				.getProcessDefinitionNameLike();
		if (processDefinitionNameLike != null) {
			taskQuery.processDefinitionNameLike(processDefinitionNameLike);
		}
		String processInstanceBusinessKey = params
				.getProcessInstanceBusinessKey();
		if (processInstanceBusinessKey != null) {
			taskQuery.processInstanceBusinessKey(processInstanceBusinessKey);
		}
		String processInstanceBusinessKeyLike = params
				.getProcessInstanceBusinessKeyLike();
		if (processInstanceBusinessKeyLike != null) {
			taskQuery
					.processInstanceBusinessKeyLike(processInstanceBusinessKeyLike);
		}
		String processInstanceId = params.getProcessInstanceId();
		if (processInstanceId != null) {
			taskQuery.processInstanceId(processInstanceId);
		}
		String taskDefinitionKey = params.getTaskDefinitionKey();
		if (taskDefinitionKey != null) {
			taskQuery.taskDefinitionKey(taskDefinitionKey);
		}
		String taskDefinitionKeyLike = params.getTaskDefinitionKeyLike();
		if (taskDefinitionKeyLike != null) {
			taskQuery.taskDefinitionKeyLike(taskDefinitionKeyLike);
		}
		String tenantId = params.getTenantId();
		if (tenantId != null) {
			taskQuery.taskTenantId(tenantId);
		}
		String tenantIdLike = params.getTenantIdLike();
		if (tenantIdLike != null) {
			taskQuery.taskTenantIdLike(tenantIdLike);
		}
		Boolean unassigned = params.getUnassigned();
		if (unassigned != null && unassigned) {
			taskQuery.taskUnassigned();
		}
		Boolean withoutDueDate = params.getWithoutDueDate();
		if (withoutDueDate != null && withoutDueDate) {
			taskQuery.withoutDueDate();
		}
		Boolean withoutTenantId = params.getWithoutTenantId();
		if (withoutTenantId != null && withoutTenantId) {
			taskQuery.taskWithoutTenantId();
		}
		if (params.getOrderByDueDate()) {
			taskQuery.orderByDueDate();
			setOrder(taskQuery, params);
		}
		if (params.getOrderByExecutionId()) {
			taskQuery.orderByExecutionId();
			setOrder(taskQuery, params);
		}
		if (params.getOrderByProcessInstanceId()) {
			taskQuery.orderByProcessInstanceId();
			setOrder(taskQuery, params);
		}
		if (params.getOrderByTaskAssignee()) {
			taskQuery.orderByTaskAssignee();
			setOrder(taskQuery, params);
		}
		if (params.getOrderByTaskCreateTime()) {
			taskQuery.orderByTaskCreateTime();
			setOrder(taskQuery, params);
		}
		if (params.getOrderByTaskDescription()) {
			taskQuery.orderByTaskDescription();
			setOrder(taskQuery, params);
		}
		if (params.getOrderByTaskId()) {
			taskQuery.orderByTaskId();
			setOrder(taskQuery, params);
		}
		if (params.getOrderByTaskName()) {
			taskQuery.orderByTaskName();
			setOrder(taskQuery, params);
		}
		if (params.getOrderByTaskPriority()) {
			taskQuery.orderByTaskPriority();
			setOrder(taskQuery, params);
		}
		if (params.getOrderByTenantId()) {
			taskQuery.orderByTenantId();
			setOrder(taskQuery, params);
		}
		boolean paged = params.isPaged();
		List<Task> tasks = null;
		if (paged) {
			tasks = taskQuery.listPage((int) params.getStart(),
					(int) params.getSize());
			long total = taskQuery.count();
			params.setTotal(total);
		} else {
			tasks = taskQuery.list();
		}
		if (tasks != null) {
			for (Task task : tasks) {
				result.add(coventWfTask(task));
			}
			result.setWfQuery(params);
		}
		return result;
	}

	@Override
	public String updateTask(WfTask wfTask) throws Exception {
		if (wfTask.getTaskId() == null) {
			return WfConstants.WF_CONTROL_EXE_ERRORPARAM;
		}
		Task task = taskService.createTaskQuery().taskId(wfTask.getTaskId())
				.singleResult();
		task.setAssignee(wfTask.getAssignee());
		task.setCategory(wfTask.getCategory());
		task.setDescription(wfTask.getDescription());
		task.setDueDate(wfTask.getDueDate());
		task.setName(wfTask.getName());
		task.setOwner(wfTask.getOwer());
		task.setParentTaskId(wfTask.getParentTaskId());
		task.setPriority(wfTask.getPriority());
		taskService.saveTask(task);
		return WfConstants.WF_CONTROL_EXE_SUCCESS;
	}

	@Override
	public WfResult operateTask(WfOperator wfOperator, String taskId,
			WfTaskAction action, String userToDelegateTo,
			Map<String, Object> variables) throws Exception {
		WfResult result = new WfResult();
		String userId = wfOperator.getUserId();
		try {
			if(variables == null){
				variables = new HashMap<String, Object>();
			}
			if (variables != null){
				variables.put(WfConstants.WF_BUSINESS_DATA_KEY,
						wfOperator.getBusinessData());
				for(String key : variables.keySet()){
					if("".equals(variables.get(key))){
						variables.put(key, null);
					}
				}
			}
			identityService.setAuthenticatedUserId(userId);
			// Task task = taskService.createTaskQuery().taskId(taskId)
			// .singleResult();
			// addTaskListenerByProcessDefinitionId(task.getProcessDefinitionId());
			switch (action) {
			case CLAIM:
				taskService.claim(taskId, userId);
				result.setResult(WfConstants.WF_CONTROL_EXE_SUCCESS);
				result.setNextTaskId(taskId);
				LoggerManager.log(getClass(), LoggerType.DEBUG, wfOperator,
						null, "签收任务成功：taskId={},action={},variables={}",
						taskId, action, variables);
				break;
			case COMPLETE:
				WfTask wfTask = this.getTaskById(taskId);
				if(null==wfTask) throw new ActivitiObjectNotFoundException(WfTask.class);
				String processInstanceId = wfTask.getProcessInstanceId();
				result.setProcessInstanceId(processInstanceId);
				taskService.complete(taskId, variables);
				result.setNextTaskId(this
						.getCurrentActiveTaskIds(processInstanceId));
				result.setResult(WfConstants.WF_CONTROL_EXE_SUCCESS);
				LoggerManager.log(getClass(), LoggerType.DEBUG, wfOperator,
						null, "完成任务成功：taskId={},action={},variables={}",
						taskId, action, variables);
				break;
			case CLAIM_COMPLETE:
				
				WfTask wfTask1 = this.getTaskById(taskId);
				if(null==wfTask1) throw new ActivitiObjectNotFoundException(WfTask.class);
				String processInstanceId1 = wfTask1.getProcessInstanceId();
				result.setProcessInstanceId(processInstanceId1);
				taskService.claim(taskId, userId);
				taskService.complete(taskId, variables);
				result.setResult(WfConstants.WF_CONTROL_EXE_SUCCESS);
				result.setNextTaskId(this
						.getCurrentActiveTaskIds(processInstanceId1));
				LoggerManager.log(getClass(), LoggerType.DEBUG, wfOperator,
						null, "签收并完成任务成功：taskId={},action={},variables={}",
						taskId, action, variables);
				break;
			case DELEGATE:
				taskService.claim(taskId, userId);
				result = delegate(wfOperator, taskId, userToDelegateTo);
				break;
			case RESOLVE:
				taskService.resolveTask(taskId, variables);
				result.setResult(WfConstants.WF_CONTROL_EXE_SUCCESS);
				LoggerManager.log(getClass(), LoggerType.DEBUG, wfOperator,
						null, "回绝代理任务成功：taskId={},action={},variables={}",
						taskId, action, variables);
				break;
			default:
				result.setResult(WfConstants.WF_CONTROL_EXE_ERRORPARAM);
				break;
			}
		} catch (ActivitiObjectNotFoundException e) {
			result.setResult(WfConstants.WF_CONTROL_EXE_NOOBJECT);
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOperator, e,
					"操作任务失败，任务不存在：taskId={},action={},variables={}", taskId,
					action, variables);
		} catch (Exception e) {
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOperator, e,
					"操作任务失败，任务不存在：taskId={},action={},variables={}", taskId,
					action, variables);
			throw e;
		} finally {
			identityService.setAuthenticatedUserId(null);
		}
		return result;
	}

	@Override
	public String deleteTask(WfOperator wfOperator, String taskId,
			Boolean cascadeHistory, String deleteReason) throws Exception {
		String userId = wfOperator.getUserId();
		try {
			// Task task = taskService.createTaskQuery().taskId(taskId)
			// .singleResult();
			// addTaskListenerByProcessDefinitionId(task.getProcessDefinitionId());
			identityService.setAuthenticatedUserId(userId);
			if (cascadeHistory != null && cascadeHistory) {
				taskService.deleteTask(taskId, true);
			} else {
				taskService.deleteTask(taskId, deleteReason);
			}
			LoggerManager.log(getClass(), LoggerType.DEBUG, wfOperator, null,
					"删除任务成功：taskId={},cascadeHistory={},deleteReason={}",
					taskId, cascadeHistory, deleteReason);
			return WfConstants.WF_CONTROL_EXE_SUCCESS;
		} catch (Exception e) {
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOperator, null,
					"删除任务失败：taskId={},cascadeHistory={},deleteReason={}",
					taskId, cascadeHistory, deleteReason);
			throw e;
		} finally {
			identityService.setAuthenticatedUserId(userId);
		}
	}

	@Override
	public Map<String, Object> getTaskVariables(String taskId,
			VariableScope scope) throws Exception {
		Map<String, Object> result = null;
		switch (scope) {
		case LOCAL:
			result = taskService.getVariablesLocal(taskId);
			break;
		default:
			result = taskService.getVariables(taskId);
			break;
		}
		return result;
	}

	@Override
	public Map<String, Object> getTaskVariableByName(String taskId,
			String variableName, VariableScope scope) throws Exception {
		Map<String, Object> result = null;
		List<String> variableNames = new ArrayList<String>();
		variableNames.add(variableName);
		switch (scope) {
		case LOCAL:
			result = taskService.getVariablesLocal(taskId, variableNames);
			break;
		case GLOBAL:
			result = taskService.getVariables(taskId, variableNames);
			break;
		default:
			result = taskService.getVariablesLocal(taskId, variableNames);
			Map<String, Object> tmp = taskService.getVariables(taskId,
					variableNames);
			if (result != null) {
				if (tmp != null) {
					result.putAll(tmp);
				}
			} else {
				result = tmp;
			}
			break;
		}
		return result;
	}

	@Override
	public String addTaskVariable(WfOperator wfOperator, String taskId,
			WfVariable wfVariable) throws Exception {
		try {
			VariableScope scope = wfVariable.getVariableScope();
			if(null==scope){
				//如果没传属性值范围，默认使用全局
				scope = VariableScope.GLOBAL;
			}
			switch (scope) {
			case LOCAL:
				taskService.setVariableLocal(taskId, wfVariable.getName(),
						wfVariable.getValue());
				break;
			default:
				taskService.setVariable(taskId, wfVariable.getName(),
						wfVariable.getValue());
				break;
			}
			LoggerManager.log(getClass(), LoggerType.DEBUG, wfOperator, null,
					"添加任务变量成功：taskId={},variableName={},value={}", taskId,
					wfVariable.getName(), wfVariable.getValue());
			return WfConstants.WF_CONTROL_EXE_SUCCESS;
		} catch (Exception e) {
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOperator, e,
					"添加任务变量失败：taskId={},variableName={},value={}", taskId,
					wfVariable.getName(), wfVariable.getValue());
			throw e;
		}
	}

	@Override
	public String updateTaskVariable(WfOperator wfOperator, String taskId,
			WfVariable wfVariable) throws Exception {
		VariableScope scope = wfVariable.getVariableScope();
		Map<String, Object> result = null;
		if(scope==null){
			scope = VariableScope.GLOBAL;
		}
		switch (scope) {
		case LOCAL:
			result = taskService.getVariablesLocal(taskId);
			break;
		default:
			result = taskService.getVariables(taskId);
			break;
		}
		String flag = WfConstants.WF_CONTROL_EXE_NOOBJECT;
		if(result.containsKey(wfVariable.getName())){
			flag = addTaskVariable(wfOperator, taskId, wfVariable);
		}
		
		return flag;
	}

	@Override
	public String deleteTaskVariable(WfOperator wfOperator, String taskId,
			String variableName, VariableScope scope) throws Exception {
		try {
			switch (scope) {
			case LOCAL:
				taskService.removeVariableLocal(taskId, variableName);
				break;
			default:
				taskService.removeVariable(taskId, variableName);
				break;
			}
			LoggerManager.log(getClass(), LoggerType.DEBUG, wfOperator, null,
					"删除任务变量成功：taskId={},variableName={},VariableScope={}",
					taskId, variableName, scope);
			return WfConstants.WF_CONTROL_EXE_SUCCESS;
		} catch (Exception e) {
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOperator, e,
					"删除任务变量失败：taskId={},variableName={},VariableScope={}",
					taskId, variableName, scope);
			throw e;
		}
	}

	@Override
	public String deleteLocalTaskVariable(WfOperator wfOperator, String taskId)
			throws Exception {
		try {
			Map<String, Object> variables = taskService
					.getVariablesLocal(taskId);
			if (variables != null) {
				taskService.removeVariablesLocal(taskId, variables.keySet());
			}
			LoggerManager.log(getClass(), LoggerType.DEBUG, wfOperator, null,
					"删除任务所有本地变量成功：taskId={}", taskId);
			return WfConstants.WF_CONTROL_EXE_SUCCESS;
		} catch (Exception e) {
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOperator, e,
					"删除任务所有本地变量失败：taskId={}", taskId);
			throw e;
		}
	}

	@Override
	public List<WfIdentityLink> getTaskCandidates(String taskId)
			throws Exception {
		List<WfIdentityLink> result = null;
		List<IdentityLink> ils = taskService.getIdentityLinksForTask(taskId);
		if (ils != null) {
			result = new ArrayList<WfIdentityLink>();
			for (IdentityLink il : ils) {
				result.add(coventWfIdentityLink(il));
			}
		}
		return result;
	}

	private WfIdentityLink coventWfIdentityLink(IdentityLink il)
			throws Exception {
		WfIdentityLink wil = new WfIdentityLink();
		wil.setGroupId(il.getGroupId());
		wil.setProcessDefinitionId(il.getProcessDefinitionId());
		wil.setProcessInstanceId(il.getProcessInstanceId());
		wil.setTaskId(il.getTaskId());
		wil.setType(il.getType());
		wil.setUserId(il.getUserId());
		return wil;
	}

	@Override
	public List<WfIdentityLink> getTaskCandidatesByType(String taskId,
			String identityLinkType) throws Exception {
		List<WfIdentityLink> result = null;
		List<IdentityLink> ils = taskService.getIdentityLinksForTask(taskId);
		if (ils != null) {
			result = new ArrayList<WfIdentityLink>();
			for (IdentityLink il : ils) {
				if (WfConstants.WF_IDENTITYLINKTYPE_USERS.equals(identityLinkType)){
					if(IdentityLinkType.CANDIDATE.equals(il.getType()) && !CommonUtil.stringNullOrEmpty(il.getUserId())){
						result.add(coventWfIdentityLink(il));
					}
				}
				if(WfConstants.WF_IDENTITYLINKTYPE_GROUPS.equals(identityLinkType)){
					if(IdentityLinkType.CANDIDATE.equals(il.getType()) && !CommonUtil.stringNullOrEmpty(il.getGroupId())){
						result.add(coventWfIdentityLink(il));
					}
				}
			}
		}
		return result;
	}

	@Override
	public String addTaskCandidate(WfOperator wfOperator, String taskId,
			String identityLinkType, String identityId) throws Exception {
		try {
			if (WfConstants.WF_IDENTITYLINKTYPE_USERS.equals(identityLinkType)) {
				taskService.addCandidateUser(taskId, identityId);
			}
			if (WfConstants.WF_IDENTITYLINKTYPE_GROUPS.equals(identityLinkType)) {
				taskService.addCandidateGroup(taskId, identityId);
			}
			LoggerManager.log(getClass(), LoggerType.DEBUG, wfOperator, null,
					"为任务添加一个参与者成功：taskId={},identityLinkType={},identityId={}",
					taskId, identityLinkType, identityId);
			return WfConstants.WF_CONTROL_EXE_SUCCESS;
		} catch (ActivitiObjectNotFoundException e) {
			LoggerManager
					.log(getClass(),
							LoggerType.ERROR,
							wfOperator,
							e,
							"为任务添加一个参与者失败，任务不存在：taskId={},identityLinkType={},identityId={}",
							taskId, identityLinkType, identityId);
			return WfConstants.WF_CONTROL_EXE_NOOBJECT;
		} catch (Exception e) {
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOperator, e,
					"为任务添加一个参与者失败：taskId={},identityLinkType={},identityId={}",
					taskId, identityLinkType, identityId);
			throw e;
		}
	}

	@Override
	public String deleteTaskCandidate(WfOperator wfOperator, String taskId,
			String identityLinkType, String identityId) throws Exception {
		try {
			if (WfConstants.WF_IDENTITYLINKTYPE_USERS.equals(identityLinkType)) {
				taskService.deleteCandidateUser(taskId, identityId);
			}
			if (WfConstants.WF_IDENTITYLINKTYPE_GROUPS.equals(identityLinkType)) {
				taskService.deleteCandidateGroup(taskId, identityId);
			}
			LoggerManager.log(getClass(), LoggerType.DEBUG, wfOperator, null,
					"删除任务参与者成功：taskId={},identityLinkType={},identityId={}",
					taskId, identityLinkType, identityId);
			return WfConstants.WF_CONTROL_EXE_SUCCESS;
		} catch (ActivitiObjectNotFoundException e) {
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOperator, e,
					"删除任务参与者失败：taskId={},identityLinkType={},identityId={}",
					taskId, identityLinkType, identityId);
			return WfConstants.WF_CONTROL_EXE_NOOBJECT;
		} catch (Exception e) {
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOperator, e,
					"删除任务参与者失败：taskId={},identityLinkType={},identityId={}",
					taskId, identityLinkType, identityId);
			throw e;
		}
	}

	@Override
	public String addTaskComment(WfOperator wfOperator, WfComment wfComment)
			throws Exception {
		String taskId = wfComment.getTaskId();
		String processInstanceId = wfComment.getProcessInstanceId();
		String message = wfComment.getMessage();
		try {
			taskService.addComment(wfComment.getTaskId(),
					wfComment.getProcessInstanceId(), wfComment.getMessage());
			LoggerManager.log(getClass(), LoggerType.DEBUG, wfOperator, null,
					"添加任务评论成功：taskId={},processInstanceId={},message={}",
					taskId, processInstanceId, message);
			return WfConstants.WF_CONTROL_EXE_SUCCESS;
		} catch (Exception e) {
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOperator, e,
					"添加任务评论失败：taskId={},processInstanceId={},message={}",
					taskId, processInstanceId, message);
			throw e;
		}
	}

	@Override
	public List<WfComment> getTaskComments(String taskId) throws Exception {
		List<WfComment> result = null;
		List<Comment> cts = taskService.getTaskComments(taskId);
		if (cts != null) {
			result = new ArrayList<WfComment>();
			for (Comment ct : cts) {
				result.add(coventWfComment(ct));
			}
		}
		return result;
	}

	private WfComment coventWfComment(Comment ct) throws Exception {
		WfComment wct = new WfComment();
		wct.setId(ct.getId());
		wct.setMessage(ct.getFullMessage());
		wct.setProcessInstanceId(ct.getProcessInstanceId());
		wct.setTaskId(ct.getTaskId());
		wct.setTime(ct.getTime());
		wct.setType(ct.getType());
		wct.setUserId(ct.getUserId());
		return wct;
	}

	@Override
	public WfComment getTaskCommentById(String commentId) throws Exception {
		Comment ct = taskService.getComment(commentId);
		if (ct != null) {
			return coventWfComment(ct);
		}
		return null;
	}

	@Override
	public String deleteTaskComment(WfOperator wfOperator, String commentId)
			throws Exception {
		try {
			taskService.deleteComment(commentId);
			LoggerManager.log(getClass(), LoggerType.DEBUG, wfOperator, null,
					"删除任务评论成功：commentId={}", commentId);
			return WfConstants.WF_CONTROL_EXE_SUCCESS;
		} catch (ActivitiObjectNotFoundException e) {
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOperator, null,
					"删除任务评论失败：commentId={}", commentId);
			return WfConstants.WF_CONTROL_EXE_NOOBJECT;
		} catch (Exception e) {
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOperator, e,
					"删除任务评论失败：commentId={}", commentId);
			throw e;
		}
	}

	@Override
	public List<WfComment> getTaskEvents(String taskId) throws Exception {
		List<WfComment> result = null;
		List<Event> ets = taskService.getTaskEvents(taskId);
		if (ets != null) {
			result = new ArrayList<WfComment>();
			for (Event et : ets) {
				result.add(coventWfComment(et));
			}
		}
		return result;
	}

	private WfComment coventWfComment(Event ct) throws Exception {
		WfComment wct = new WfComment();
		wct.setId(ct.getId());
		wct.setMessage(ct.getMessage());
		wct.setProcessInstanceId(ct.getProcessInstanceId());
		wct.setTaskId(ct.getTaskId());
		wct.setTime(ct.getTime());
		wct.setType(WfComment.TYPE_EVENT);
		wct.setUserId(ct.getUserId());
		return wct;
	}

	@Override
	public WfComment getTaskEventById(String eventId) throws Exception {
		Event et = taskService.getEvent(eventId);
		if (et != null) {
			return coventWfComment(et);
		}
		return null;
	}

	@Override
	public String addTaskAttachment(WfOperator wfOperator,
			WfAttachment attachment) throws Exception {
		try {
			taskService.createAttachment(attachment.getType(),
					attachment.getTaskId(), attachment.getProcessInstanceId(),
					attachment.getName(), attachment.getDescription(),
					attachment.getContent());
			LoggerManager.log(getClass(), LoggerType.DEBUG, wfOperator, null,
					"添加任务附件成功：attachment={}", attachment);
			return WfConstants.WF_CONTROL_EXE_SUCCESS;
		} catch (Exception e) {
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOperator, e,
					"添加任务附件失败：attachment={}", attachment);
			throw e;
		}
	}

	@Override
	public List<WfAttachment> getTaskAttachments(String taskId)
			throws Exception {
		List<WfAttachment> result = null;
		List<Attachment> ats = taskService.getTaskAttachments(taskId);
		if (ats != null) {
			result = new ArrayList<WfAttachment>();
			for (Attachment at : ats) {
				result.add(coventWfAttachment(at));
			}
		}
		return result;
	}

	private WfAttachment coventWfAttachment(Attachment at) throws Exception {
		WfAttachment wat = new WfAttachment();
		wat.setDescription(at.getDescription());
		wat.setId(at.getId());
		wat.setName(at.getName());
		wat.setProcessInstanceId(at.getProcessInstanceId());
		wat.setTaskId(at.getTaskId());
		wat.setType(at.getType());
		wat.setUrl(at.getUrl());
		wat.setUserId(at.getUserId());
		ByteArrayEntity bae = ((AttachmentEntity) at).getContent();
		if (bae != null) {
			wat.setContent(CommonUtil.byteToInputStream(bae.getBytes()));
		}
		return wat;
	}

	@Override
	public WfAttachment getTaskAttachmentById(String attachmentId)
			throws Exception {
		Attachment at = taskService.getAttachment(attachmentId);
		if (at != null) {
			return coventWfAttachment(at);
		}
		return null;
	}

	@Override
	public String deleteTaskAttachmentById(WfOperator wfOperator,
			String attachmentId) throws Exception {
		try {
			taskService.deleteAttachment(attachmentId);
			LoggerManager.log(getClass(), LoggerType.DEBUG, wfOperator, null,
					"删除任务附件成功：attachmentId={}", attachmentId);
			return WfConstants.WF_CONTROL_EXE_SUCCESS;
		} catch (Exception e) {
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOperator, e,
					"删除任务附件失败：attachmentId={}", attachmentId);
			throw e;
		}
	}

	@Override
	public WfResult reject(WfOperator wfOperator, String taskId,
			String rejectMessage, Map<String, Object> variables)
			throws Exception {
		WfResult result = new WfResult();
		String userId = wfOperator.getUserId();
		try {
			// 用来设置操作流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
			identityService.setAuthenticatedUserId(userId);
			if(variables == null){
				variables = new HashMap<String, Object>();
			}
			if (variables != null){
				variables.put(WfConstants.WF_BUSINESS_DATA_KEY,
						wfOperator.getBusinessData());
				for(String key : variables.keySet()){
					if("".equals(variables.get(key))){
						variables.put(key, null);
					}
				}
			}
			
			// 获得当前任务的对应实列
			Task taskEntity = taskService.createTaskQuery().taskId(taskId).singleResult();
			if (null==taskEntity) throw new ActivitiException("当前任务["+taskId+"]未找到");
			
			// 当前任务key
			String taskDefKey = taskEntity.getTaskDefinitionKey();
			

			//获得当前流程的定义模型
			ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(taskEntity.getProcessDefinitionId());
	        if (null==processDefinition) throw new ActivitiException("流程定义["+taskEntity.getProcessDefinitionId()+"]未找到");
			
			//获得当前活动节点定义
			ActivityImpl currActivity = ((ProcessDefinitionImpl) processDefinition).findActivity(taskDefKey);
			if(null==currActivity)  throw new ActivitiException("当前环节定义["+taskDefKey+"]未找到");
			
			// 获得当前流程实例的历史任务,按照任务id排序，这样就可以只取上一环节最近的任务实例
			String processInstanceId = taskEntity.getProcessInstanceId();
			
			//获得当前流程实例的已经完成的历史任务,按照任务时间降序排序
			HistoricTaskInstanceQueryImpl historyQuery=(HistoricTaskInstanceQueryImpl)historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).finished();
			if(!taskEntity.getExecutionId().equals(taskEntity.getProcessInstanceId())) historyQuery.executionId(taskEntity.getExecutionId());
			List<HistoricTaskInstance> historicTaskInstances =historyQuery.orderByHistoricTaskInstanceEndTime().desc().list();
	        
			ActivityImpl destActiviti = null;// 驳回目标节点
			HistoricTaskInstance destHistoricTaskInstance=null;
			String destAssignee = null;//驳回目标节点执行人

			List<ActivityImpl> historyUserActList = getPreUserActivitys(currActivity);
			for (ActivityImpl activityImpl : historyUserActList) {
				HistoricTaskInstance historicTaskInstance=this.findNewHistoricUserTask(activityImpl,historicTaskInstances);
				if(null==historicTaskInstance) continue;
				if(null==destActiviti){
					destHistoricTaskInstance=historicTaskInstance;
					destActiviti=activityImpl;
					destAssignee=historicTaskInstance.getAssignee();
				}else{
					if(historicTaskInstance.getEndTime().after(destHistoricTaskInstance.getEndTime())){
						destHistoricTaskInstance=historicTaskInstance;
						destActiviti=activityImpl;
						destAssignee=historicTaskInstance.getAssignee();
					}
				}
			}
			
			if(null==destActiviti) throw new ActivitiException("找不到驳回的目标节点");
			
//			taskEntity.setDescription(rejectMessage);
			String nextTaskIds = taskTransferOutLine(null, null, processInstanceId,
					destActiviti.getId(), rejectMessage == null ? WfConstants.JUMPREASON_REJECT : rejectMessage,
					destAssignee, variables);
			result.setProcessInstanceId(processInstanceId);
			result.setNextTaskId(nextTaskIds);
			result.setResult(WfConstants.WF_CONTROL_EXE_SUCCESS);
			LoggerManager.log(getClass(), LoggerType.DEBUG, wfOperator, null,
					"驳回流程成功：taskId={}, rejectMessage={}, variables={}", taskId,
					rejectMessage, variables);
		} catch (ActivitiObjectNotFoundException e) {
			result.setResult(WfConstants.WF_CONTROL_EXE_NOOBJECT);
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOperator, e,
					"驳回流程失败，操作对象不存在：taskId={}, rejectMessage={}, variables={}",
					taskId, rejectMessage, variables);
			e.printStackTrace();
		} catch (Exception e) {
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOperator, e,
					"驳回流程失败：taskId={}, rejectMessage={}, variables={}", taskId,
					rejectMessage, variables);
			throw e;
		} finally {
			identityService.setAuthenticatedUserId(null);
		}
		return result;
	}

	/**
	 * 当流程定义ID不为空时代表启动流程自由流
	 * @param processDefinitionId 
	 * @param bussinessKey
	 * @param processInstanceId
	 * @param jumpToActivityId
	 * @param jumpReason
	 * @param destAssignee
	 * @param variables
	 * @return
	 * @throws Exception
	 */
	private String taskTransferOutLine(String processDefinitionId,
			String bussinessKey, String processInstanceId,
			String jumpToActivityId, String jumpReason, String destAssignee,
			Map<String, Object> variables) throws Exception {
		String nextTaskIds = null;
		try {
			// jump
			JumpActivityCmd jumpActivityCmd = new JumpActivityCmd(
					processDefinitionId, bussinessKey, processInstanceId,
					jumpToActivityId, jumpReason, variables);
			processInstanceId = managementService
					.executeCommand(jumpActivityCmd);

			nextTaskIds = this.getCurrentActiveTaskIds(processInstanceId);
			if (destAssignee != null && !"".equals(destAssignee)) {
				taskService.setAssignee(nextTaskIds, destAssignee);
			}
		} catch (Exception e) {
			throw e;
		}
		return nextTaskIds;
	}
	
	
	/**
	 * 从历史记录中获取环节最新历史数据
	 * @param activityImpl
	 * @param historyTaskList
	 * @return
	 */
    private HistoricTaskInstance findNewHistoricUserTask(ActivityImpl activityImpl,List<HistoricTaskInstance> historyTaskList) {
       if(null==historyTaskList||historyTaskList.size()<=0) return null;
       HistoricTaskInstance newHistoricTaskInstance=null;
       for(HistoricTaskInstance historicTaskInstance : historyTaskList){
    	   if(historicTaskInstance.getTaskDefinitionKey().equals(activityImpl.getId())){
    		   if(null==newHistoricTaskInstance){
    			   newHistoricTaskInstance=historicTaskInstance;
    		   }else{
    			   if(historicTaskInstance.getEndTime().after(newHistoricTaskInstance.getEndTime())) newHistoricTaskInstance=historicTaskInstance;
    		   }
    	   }
       }
       return newHistoricTaskInstance;
    } 

	
	/**
	 * 根据当前活动寻找其上一个用户活动集合
	 * @param currActiviti 当前活动
	 * @return
	 */
	private List<ActivityImpl> getPreUserActivitys(ActivityImpl currActiviti) {

		List<ActivityImpl> ret = new ArrayList<ActivityImpl>();

		List<PvmTransition> incomings = currActiviti.getIncomingTransitions();

		for (PvmTransition incoming : incomings) {
			PvmActivity activity = incoming.getSource();
			String type = (String) activity.getProperty(WfConstants.ACTIVITY_PROPERTY_KEY_TYPE);
			if (WfConstants.ACTIVITY_TYPE_USERTASK.equals(type)) {
				ret.add((ActivityImpl)activity);
			} else{
				//递归查找上一步用户节点
				ret.addAll(getPreUserActivitys((ActivityImpl)activity));
			}
		}
		return ret;
	}

	private WfResult delegate(WfOperator wfOperator, String taskId,
			String destUserId) throws Exception {
		WfResult result = new WfResult();
		String userId = wfOperator.getUserId();

		try {
			// 用来设置操作流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
			identityService.setAuthenticatedUserId(userId);

			taskService.delegateTask(taskId, destUserId);
			result.setResult(WfConstants.WF_CONTROL_EXE_SUCCESS);
			LoggerManager.log(getClass(), LoggerType.DEBUG, wfOperator, null,
					"委托任务成功：taskId={}, destUserId={} ", taskId, destUserId);
		} catch (ActivitiObjectNotFoundException e) {
			result.setResult(WfConstants.WF_CONTROL_EXE_NOOBJECT);
			LoggerManager
					.log(getClass(), LoggerType.ERROR, wfOperator, e,
							"委托任务失败，对象不存在：taskId={}, destUserId={} ", taskId,
							destUserId);
			e.printStackTrace();
		} catch (Exception e) {
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOperator, e,
					"委托任务失败：taskId={}, destUserId={} ", taskId, destUserId);
			throw e;
		} finally {
			identityService.setAuthenticatedUserId(null);
		}
		return result;
	}

	@Override
	public WfResult goAnyWhere(WfOperator wfOperator, boolean isStart,
			String bussinessKey, String processDefinitionId, String taskId,
			String destTaskDefinitionKey, boolean useHisAssignee,
			Map<String, Object> variables) throws Exception {
		
		WfResult result = new WfResult();
		String userId = wfOperator.getUserId();
		try {
			// 用来设置操作流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
			identityService.setAuthenticatedUserId(userId);

			if(variables == null){
				variables = new HashMap<String, Object>();
			}
			if (variables != null){
				variables.put(WfConstants.WF_BUSINESS_DATA_KEY,
						wfOperator.getBusinessData());
				for(String key : variables.keySet()){
					if("".equals(variables.get(key))){
						variables.put(key, null);
					}
				}
			}
				
			if (isStart) {
				String processInstanceId = null;
				String nextTaskIds = taskTransferOutLine(
						processDefinitionId, bussinessKey, processInstanceId,
						destTaskDefinitionKey,
						WfConstants.JUMPREASON_GOANYWHERE, null, variables);

				result.setResult(WfConstants.WF_CONTROL_EXE_SUCCESS);
				result.setProcessInstanceId(processInstanceId);
				result.setNextTaskId(nextTaskIds);
			} else {
				// 获得当前任务的对应实列
				Task taskEntity = taskService.createTaskQuery().taskId(taskId)
						.singleResult();
				String destAssignee = "";
				if (useHisAssignee) {// 设置历史执行人
					// 获得当前流程实例的历史任务,按照任务id排序，这样就可以只取上一环节最近的任务实例
					String processInstanceId = taskEntity
							.getProcessInstanceId();
					List<HistoricTaskInstance> historicTaskInstances = historyService
							.createHistoricTaskInstanceQuery()
							.processInstanceId(processInstanceId)
							.orderByTaskId().desc().list();

					for (HistoricTaskInstance hti : historicTaskInstances) {
						if (destTaskDefinitionKey.equals(hti
								.getTaskDefinitionKey())) {
							destAssignee = hti.getAssignee();
						}
					}
				}
				String nextTaskIds = taskTransferOutLine(null, null,
						taskEntity.getProcessInstanceId(),
						destTaskDefinitionKey,
						WfConstants.JUMPREASON_GOANYWHERE, destAssignee,
						variables);
				result.setNextTaskId(nextTaskIds);
			}
			result.setResult(WfConstants.WF_CONTROL_EXE_SUCCESS);
			LoggerManager
					.log(getClass(),
							LoggerType.DEBUG,
							wfOperator,
							null,
							"自由流转成功：isStart={}, bussinessKey={}, processDefinitionId={}, taskId={}, destTaskDefinitionKey={}, useHisAssignee={}, variables={}",
							isStart, bussinessKey, processDefinitionId, taskId,
							destTaskDefinitionKey, useHisAssignee, variables);
		} catch (Exception e) {
			LoggerManager
					.log(getClass(),
							LoggerType.ERROR,
							wfOperator,
							e,
							"自由流转失败：isStart={}, bussinessKey={}, processDefinitionId={}, taskId={}, destTaskDefinitionKey={}, useHisAssignee={}, variables={}",
							isStart, bussinessKey, processDefinitionId, taskId,
							destTaskDefinitionKey, useHisAssignee, variables);
			throw e;
		} finally {
			identityService.setAuthenticatedUserId(null);
		}
		return result;
	}

	@Override
	public List<WfActivity> getNextUserTaskDefByTaskId(String taskId)
			throws Exception {
		List<WfActivity> result = new ArrayList<WfActivity>();
		// 获得当前任务的对应实列
		Task taskEntity = taskService.createTaskQuery().taskId(taskId)
				.singleResult();
		// 当前任务key
		String taskDefKey = taskEntity.getTaskDefinitionKey();

		// 获得当前流程的定义模型
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) repositoryService
				.getProcessDefinition(taskEntity.getProcessDefinitionId());
		// 获得当前流程定义模型的所有任务节点
		List<ActivityImpl> activitilist = processDefinition.getActivities();
		for (ActivityImpl activity : activitilist) {
			if (activity.getId().equals(taskDefKey)) {
				List<PvmTransition> pvmTrans = activity
						.getOutgoingTransitions();
				if (pvmTrans != null) {
					for (PvmTransition pvmTran : pvmTrans) {
						List<ActivityImpl> activitys = getNextUserTask(pvmTran);
						for (ActivityImpl ai : activitys) {
							WfActivity wai = new WfActivity();
							wai.setHeight(ai.getHeight());
							wai.setId(ai.getId());
							wai.setVariables(ai.getVariables());
							wai.setWidth(ai.getWidth());
							wai.setX(ai.getX());
							wai.setY(ai.getY());
							wai.setProperties(ai.getProperties());
							result.add(wai);
						}
					}

				}
				break;
			}
		}
		return result;
	}

	private List<ActivityImpl> getNextUserTask(PvmTransition pvmTran) {
		List<ActivityImpl> result = new ArrayList<ActivityImpl>();
		ActivityImpl ai = (ActivityImpl) pvmTran.getDestination();
		String type = (String) ai
				.getProperty(WfConstants.ACTIVITY_PROPERTY_KEY_TYPE);
		if (WfConstants.ACTIVITY_TYPE_USERTASK.equals(type)) {
			result.add(ai);
		} else {
			List<PvmTransition> pvmTrans = ai.getOutgoingTransitions();
			if (pvmTrans != null) {
				for (PvmTransition pvmTran1 : pvmTrans) {
					result.addAll(getNextUserTask(pvmTran1));
				}
			}
		}
		return result;
	}

	public IdentityService getIdentityService() {
		return identityService;
	}

	public void setIdentityService(IdentityService identityService) {
		this.identityService = identityService;
	}

	public HistoryService getHistoryService() {
		return historyService;
	}

	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}

	public RepositoryService getRepositoryService() {
		return repositoryService;
	}

	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	public RuntimeService getRuntimeService() {
		return runtimeService;
	}

	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public ManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(ManagementService managementService) {
		this.managementService = managementService;
	}

	@Override
	public WfUniteTaskResult queryWfUniteConfig(String appId, String tenantId,
			String engineName, String taskList) throws Exception {
		return managementService
				.executeCommand(new FindWfUniteConfigColumnsCmd(appId,
						tenantId, engineName, taskList));
	}

	@Override
	public WfUniteTaskResult queryWfUniteRunTask(
			Map<String, Object> parameters, int firstResult, int maxResults)
			throws Exception {
		return managementService
				.executeCommand(new FindWfUniteTaskByConditionCmd(parameters,
						firstResult, maxResults));
	}

	@Override
	public WfUniteTaskResult queryWfUniteHisTask(
			Map<String, Object> parameters, int firstResult, int maxResults)
			throws Exception {
		/*
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boolean_", true);
		map.put("string_", "string");
		parameters.put("businessExtend", map);
		*/
		return managementService
				.executeCommand(new FindWfUniteHisTaskByConditionCmd(parameters,
						firstResult, maxResults));
	}
	
	public WfResult operateTaskTmp(WfOperator wfOperator, String taskId,
			String action, String userToDelegateTo,
			Map<String, Object> variables) throws Exception {
		if(action.equals(WfTaskAction.CLAIM.toString())){
			return operateTask(wfOperator, taskId,
					WfTaskAction.CLAIM,  userToDelegateTo,
					 variables);
		}else if(action.equals(WfTaskAction.COMPLETE.toString())){
			return operateTask(wfOperator, taskId,
					WfTaskAction.COMPLETE,  userToDelegateTo,
					 variables);
		}else if(action.equals(WfTaskAction.CLAIM_COMPLETE.toString())){
			return operateTask(wfOperator, taskId,
					WfTaskAction.CLAIM_COMPLETE,  userToDelegateTo,
					 variables);
		}else if(action.equals(WfTaskAction.DELEGATE.toString())){
			return operateTask(wfOperator, taskId,
					WfTaskAction.DELEGATE,  userToDelegateTo,
					 variables);
		}else if(action.equals(WfTaskAction.RESOLVE.toString())){
			return operateTask(wfOperator, taskId,
					WfTaskAction.RESOLVE,  userToDelegateTo,
					 variables);
		}
		return null;
	}
	
	
	public List<String> getActiveActivityIds(String executionId) {
		return runtimeService.getActiveActivityIds(executionId);
	}
	
	@Override
	public WfUniteTaskResult queryWfUniteRunTask(String userId,
			List<ChooseGroup> chooseGroupList, Map<String, Object> parameters,
			int firstResult, int maxResults,Map<String,OrderDirection> orderBys) throws Exception {

		if(null==parameters) parameters=new HashMap<String, Object>();
		parameters.put("groups",chooseGroupList);
		parameters.put("userId",userId);
		
		
		return managementService
				.executeCommand(new FindWfUniteTaskByConditionCmd(parameters,
						firstResult, maxResults,orderBys));
	}
	
	
	@Override
	public WfUniteTaskResult queryWfUniteHisTask(String userId,
			List<ChooseGroup> chooseGroupList, Map<String, Object> parameters,
			int firstResult, int maxResults,Map<String,OrderDirection> orderBys) throws Exception {
		if(null==parameters) parameters=new HashMap<String, Object>();
		parameters.put("groups",chooseGroupList);
		parameters.put("userId",userId);
		return managementService
				.executeCommand(new FindWfUniteHisTaskByConditionCmd(parameters,
						firstResult, maxResults,orderBys));
	}
}
