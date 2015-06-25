package com.chinacreator.c2.flow.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricDetailQuery;
import org.activiti.engine.history.HistoricIdentityLink;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.persistence.entity.HistoricTaskInstanceEntity;
import org.activiti.engine.impl.persistence.entity.VariableInstanceEntity;
import org.activiti.engine.query.Query;
import org.activiti.engine.task.Comment;

import com.chinacreator.c2.flow.api.WfHistoryService;
import com.chinacreator.c2.flow.detail.WfComment;
import com.chinacreator.c2.flow.detail.WfConstants;
import com.chinacreator.c2.flow.detail.WfDetail;
import com.chinacreator.c2.flow.detail.WfDetailParam;
import com.chinacreator.c2.flow.detail.WfHistoricProcessInstance;
import com.chinacreator.c2.flow.detail.WfHistoricProcessInstanceParam;
import com.chinacreator.c2.flow.detail.WfHistoricTask;
import com.chinacreator.c2.flow.detail.WfHistoricTaskParam;
import com.chinacreator.c2.flow.detail.WfHistoricVariableInstance;
import com.chinacreator.c2.flow.detail.WfIdentityLink;
import com.chinacreator.c2.flow.detail.WfOperator;
import com.chinacreator.c2.flow.detail.WfPageList;
import com.chinacreator.c2.flow.detail.WfPageParam;
import com.chinacreator.c2.flow.detail.WfProcessDefinitionParam;
import com.chinacreator.c2.flow.detail.WfVariable.VariableScope;
import com.chinacreator.c2.flow.detail.WfVariableParam;
import com.chinacreator.c2.flow.util.CommonUtil;
import com.chinacreator.c2.flow.util.LoggerManager;
import com.chinacreator.c2.flow.util.LoggerManager.LoggerType;

public class WfHistoryServiceImpl implements WfHistoryService {
	
	private HistoryService historyService;
	
	private TaskService taskService;
	
	
	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public HistoryService getHistoryService() {
		return historyService;
	}

	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}

	@Override
	public WfHistoricProcessInstance getHistoricProcessInstanceById(
			String processInstanceId) throws Exception {
		HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		WfHistoricProcessInstance whpi = getTransedWfHistoricProcessInstance(historicProcessInstance);
		return whpi;
	}

	@Override
	public WfPageList<WfHistoricProcessInstance, WfHistoricProcessInstanceParam> queryHistoricProcessInstances(
			WfHistoricProcessInstanceParam params) throws Exception {
		WfPageList<WfHistoricProcessInstance, WfHistoricProcessInstanceParam> whpiList = new WfPageList<WfHistoricProcessInstance, WfHistoricProcessInstanceParam>();
		if(null!=params){
			HistoricProcessInstanceQuery hpiQuery = historyService.createHistoricProcessInstanceQuery();
			String businessKey = params.getBusinessKey();
			boolean excludeSubprocesses = params.getExcludeSubprocesses();
			boolean finished = params.getFinished()==null?false:params.getFinished();
			Date finishedAfter = params.getFinishedAfter();
			Date finishedBefore = params.getFinishedBefore();
			boolean includeProcessVariables = params.getIncludeProcessVariables();
			String involvedUser = params.getInvolvedUser();
			boolean orderByProcessDefinitionId = params.getOrderByProcessDefinitionId();
			boolean orderByProcessInstanceId = params.getOrderByProcessInstanceId();
			boolean orderByProcessInstanceBusinessKey = params.getOrderByProcessInstanceBusinessKey();
			boolean orderByProcessInstanceDuration = params.getOrderByProcessInstanceDuration();
			boolean orderByProcessInstanceEndTime = params.getOrderByProcessInstanceEndTime();
			boolean orderByProcessInstanceStartTime = params.getOrderByProcessInstanceStartTime();
			boolean orderByTenantId = params.getOrderByTenantId();
			String processDefinitionId = params.getProcessDefinitionId();
			String processDefinitionKey = params.getProcessDefinitionKey();
			String processInstanceId = params.getProcessInstanceId();
			Date startedAfter = params.getStartedAfter();
			Date startedBefore = params.getStartedBefore();
			String startedBy = params.getStartedBy();
			String superProcessInstanceId = params.getSuperProcessInstanceId();
			String tenantId = params.getTenantId();
			String tenantIdLike = params.getTenantIdLike();
			boolean withoutTenantId = params.getWithoutTenantId();
			//是否分页
			boolean isPaged = params.isPaged();
			
			
			//查询条件的处理
			if(!CommonUtil.stringNullOrEmpty(businessKey)){
				hpiQuery.processInstanceBusinessKey(businessKey);
			}
			
			if(excludeSubprocesses){
				hpiQuery.excludeSubprocesses(excludeSubprocesses);
			}
			
			if(finished){
				hpiQuery.finished();
			}
			
			if(null!=finishedAfter){
				hpiQuery.finishedAfter(finishedAfter);
			}
			
			if(null!=finishedBefore){
				hpiQuery.finishedBefore(finishedBefore);
			}
			
			if(includeProcessVariables){
				hpiQuery.includeProcessVariables();
			}
			
			if(!CommonUtil.stringNullOrEmpty(involvedUser)){
				hpiQuery.involvedUser(involvedUser);
			}
			if(!CommonUtil.stringNullOrEmpty(processDefinitionId)){
				hpiQuery.processDefinitionId(processDefinitionId);
			}
			if(!CommonUtil.stringNullOrEmpty(processDefinitionKey)){
				hpiQuery.processDefinitionKey(processDefinitionKey);
			}
			if(!CommonUtil.stringNullOrEmpty(processInstanceId)){
				hpiQuery.processInstanceId(processInstanceId);
			}
			if(null!=startedAfter){
				hpiQuery.startedAfter(startedAfter);
			}
			if(null!=startedBefore){
				hpiQuery.startedBefore(startedBefore);
			}
			if(null!=startedBy){
				hpiQuery.startedBy(startedBy);
			}
			if(!CommonUtil.stringNullOrEmpty(superProcessInstanceId)){
				hpiQuery.superProcessInstanceId(superProcessInstanceId);
			}
			if(!CommonUtil.stringNullOrEmpty(tenantId)){
				hpiQuery.processInstanceTenantId(tenantId);
			}
			if(!CommonUtil.stringNullOrEmpty(tenantIdLike)){
				hpiQuery.processInstanceTenantIdLike(tenantIdLike);
			}
			if(withoutTenantId){
				hpiQuery.processInstanceWithoutTenantId();
			}
			
			//排序的处理
			if(orderByProcessDefinitionId){
				hpiQuery.orderByProcessDefinitionId();
				setOrder(hpiQuery, params);
			}
			if(orderByProcessInstanceId){
				hpiQuery.orderByProcessInstanceId();
				setOrder(hpiQuery, params);
			}
			if(orderByProcessInstanceBusinessKey){
				hpiQuery.orderByProcessInstanceBusinessKey();
				setOrder(hpiQuery, params);
			}
			if(orderByProcessInstanceDuration){
				hpiQuery.orderByProcessInstanceDuration();
				setOrder(hpiQuery, params);
			}
			if(orderByProcessInstanceEndTime){
				hpiQuery.orderByProcessInstanceEndTime();
				setOrder(hpiQuery, params);
			}
			if(orderByProcessInstanceStartTime){
				hpiQuery.orderByProcessInstanceStartTime();
				setOrder(hpiQuery, params);
			}
			if(orderByTenantId){
				hpiQuery.orderByTenantId();
				setOrder(hpiQuery, params);
			}
			
			List<HistoricProcessInstance> hisPorcInsList = new ArrayList<HistoricProcessInstance>();
			//处理分页
			if(isPaged){
				hisPorcInsList = hpiQuery.listPage((int)params.getStart(), (int)params.getSize());
				long total = hpiQuery.count();
				params.setTotal(total);
			}else{
				hisPorcInsList = hpiQuery.list();
			}
			whpiList.setWfQuery(params);
			
			//对象转换
			if(!hisPorcInsList.isEmpty()){
				for(HistoricProcessInstance p: hisPorcInsList){
					WfHistoricProcessInstance whpi = getTransedWfHistoricProcessInstance(p);
					if(null!=whpi){
						whpiList.add(whpi);
					}
				}
			}
			
		}
		return whpiList;
	}

	@Override
	public String deleteHistoricProcessInstance(String processInstanceId)
			throws Exception {
		if(!CommonUtil.stringNullOrEmpty(processInstanceId)){
			historyService.deleteHistoricProcessInstance(processInstanceId);
		}
		return WfConstants.WF_CONTROL_EXE_SUCCESS;
	}

	@Override
	public List<WfIdentityLink> getHistoricProcessInstanceCandidates(
			String processInstanceId) throws Exception {
		List<HistoricIdentityLink> historicIdentityLinkList = new ArrayList<HistoricIdentityLink>(); 
		List<WfIdentityLink> wfIdentityLinkList = new ArrayList<WfIdentityLink>(); 
		if(!CommonUtil.stringNullOrEmpty(processInstanceId)){
			historicIdentityLinkList = historyService.getHistoricIdentityLinksForProcessInstance(processInstanceId);
		}
		if(!historicIdentityLinkList.isEmpty()){
			for(HistoricIdentityLink hil:historicIdentityLinkList){
				WfIdentityLink wfIdentityLink = getTransedWfIdentityLink(hil);
				wfIdentityLinkList.add(wfIdentityLink);
			}
		}
		return wfIdentityLinkList;
		
	}

	@Override
	public List<WfHistoricVariableInstance> getHistoricProcessInstanceVariables(
			String processInstanceId) throws Exception {
		List<WfHistoricVariableInstance> whviList = new ArrayList<WfHistoricVariableInstance>();
		List<HistoricVariableInstance> historicVariableInstanceList = new ArrayList<HistoricVariableInstance>();
		if(!CommonUtil.stringNullOrEmpty(processInstanceId)){
			historicVariableInstanceList = historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).list();
		}
		
		if(!historicVariableInstanceList.isEmpty()){
			for(HistoricVariableInstance hvi:historicVariableInstanceList){
				WfHistoricVariableInstance whvi = getTransedWfHistoricVariableInstance(hvi);
				whviList.add(whvi);
			}
		}
		return whviList;
	}

	@Override
	public String addHistoricProcessInstanceComment(WfOperator wfOperator,
			String processInstanceId, WfComment wfComment) throws Exception {
		if (null != wfComment && !CommonUtil.stringNullOrEmpty(wfComment.getMessage())){
			taskService.addComment(null, processInstanceId, wfComment.getMessage());
			return WfConstants.WF_CONTROL_EXE_SUCCESS;
		}else{
			return WfConstants.WF_CONTROL_EXE_NOOBJECT;
		}
	}

	@Override
	public List<WfComment> getAllHistoricProcessInstanceComments(
			String processInstanceId) throws Exception {
		List<WfComment> wfCommentList = new ArrayList<WfComment>();
		if(!CommonUtil.stringNullOrEmpty(processInstanceId)){
			List<Comment> commentList = taskService.getProcessInstanceComments(processInstanceId);
			if (null != commentList && !commentList.isEmpty()) {
				for(Comment comment : commentList){
					WfComment wfComment = getTransedWfComment(comment);
					wfCommentList.add(wfComment);
				}
			}
		}
		return wfCommentList;
	}

	@Override
	public WfComment getHistoricProcessInstanceComment(
			String processInstanceId, String commentId) throws Exception {
		if(CommonUtil.stringNullOrEmpty(processInstanceId)){
			throw new NullPointerException("Process instance id is null!");
		}
		if(CommonUtil.stringNullOrEmpty(commentId)){
			throw new NullPointerException("comment id is null!");
		}
		Comment comment = taskService.getComment(commentId);
		if(comment == null || comment.getProcessInstanceId() == null || !comment.getProcessInstanceId().equals(processInstanceId)) {
			throw new ActivitiObjectNotFoundException("Process instance '" + processInstanceId +"' doesn't have a comment with id '" + commentId + "'.", Comment.class);
		}
		WfComment wfComment = getTransedWfComment(comment);
		return wfComment;
	}

	@Override
	public String deleteHistoricProcessInstanceComment(WfOperator wfOperator,
			String processInstanceId, String commentId) throws Exception {
		if(CommonUtil.stringNullOrEmpty(processInstanceId)){
			throw new NullPointerException("Process instance id is null!");
		}
		if(CommonUtil.stringNullOrEmpty(commentId)){
			throw new NullPointerException("comment id is null!");
		}
		Comment comment = taskService.getComment(commentId);
		if(comment == null || comment.getProcessInstanceId() == null || !comment.getProcessInstanceId().equals(processInstanceId)) {
			ActivitiObjectNotFoundException e = new ActivitiObjectNotFoundException(Comment.class);
			LoggerManager.log(WfHistoryServiceImpl.class, LoggerType.ERROR, wfOperator, e, "Process instance {} doesn't have a comment with id {}.", processInstanceId, commentId);
			return WfConstants.WF_CONTROL_EXE_FAIL;
		}
		taskService.deleteComment(commentId);
		return WfConstants.WF_CONTROL_EXE_SUCCESS;
	}

	@Override
	public WfHistoricTask getHistoricTaskById(String taskId) throws Exception {
		if(CommonUtil.stringNullOrEmpty(taskId)){
			throw new NullPointerException("taskId is null");
		}
		HistoricTaskInstance taskInstance = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
		if (taskInstance == null) {
			throw new ActivitiObjectNotFoundException("Could not find a task instance with id '" + taskId + "'.",HistoricTaskInstance.class);
		}
		WfHistoricTask wfHistoricTask = getTransedWfHistoricTask(taskInstance);
		return wfHistoricTask;
	}

	@Override
	public WfPageList<WfHistoricTask, WfHistoricTaskParam> queryHistoricTasks(
			WfHistoricTaskParam params) throws Exception {
		 WfPageList<WfHistoricTask, WfHistoricTaskParam> wfHistoricTaskPageList = new WfPageList<WfHistoricTask, WfHistoricTaskParam>();
		 if(null!=params){
			 HistoricTaskInstanceQuery htiQuery = historyService.createHistoricTaskInstanceQuery();
			 boolean finished = params.getFinished()==null?false:params.getFinished();
			 String assignee = params.getAssignee();
			 String assigneeLike = params.getAssigneeLike();
			 String candidateGroup = params.getCandidateGroup();
			 String candidateUser = params.getCandidateUser();
			 Date createdAfter = params.getCreatedAfter();
			 Date createdBefore = params.getCreatedBefore();
			 Date createdOn = params.getCreatedOn();
			 String description = params.getDescription();
			 Date dueAfter = params.getDueAfter();
			 Date dueBefore = params.getDueBefore();
			 Date dueOn = params.getDueOn();
			 String executionId = params.getExecutionId();
			 boolean includeProcessVariables = params.getIncludeProcessVariables()==null?false:params.getIncludeProcessVariables();
			 boolean includeTaskLocalVariables = params.getIncludeTaskLocalVariables()==null?false:params.getIncludeTaskLocalVariables();
			 String involvedUser = params.getInvolvedUser();
			 Integer maximumPriority = params.getMaximumPriority();
			 Integer minimumPriority = params.getMinimumPriority();
			 String name = params.getName();
			 String nameLike = params.getNameLike();
			 
			 String owner = params.getOwner();
			 String ownerLike = params.getOwnerLike();
			 Integer priority = params.getPriority();
			 String processDefinitionKey = params.getProcessDefinitionKey();
			 String processDefinitionKeyLike = params.getProcessDefinitionKeyLike();
			 String processDefinitionName = params.getProcessDefinitionName();
			 String processDefinitionNameLike = params.getProcessDefinitionNameLike();
			 String processInstanceBusinessKey = params.getProcessInstanceBusinessKey();
			 String processInstanceBusinessKeyLike = params.getProcessInstanceBusinessKeyLike();
			 String processInstanceId = params.getProcessInstanceId();
			 
			 String taskDefinitionKey = params.getTaskDefinitionKey();
			 String taskDefinitionKeyLike = params.getTaskDefinitionKeyLike();
			 String tenantId = params.getTenantId();
			 String tenantIdLike = params.getTenantIdLike();
			 boolean withoutDueDate = params.getWithoutDueDate()==null?false:params.getWithoutDueDate();
			 boolean withoutTenantId = params.getWithoutTenantId()==null?false:params.getWithoutTenantId();

			 //查询条件
			 if(finished){
				 htiQuery.unfinished();
			 }
			 if(!CommonUtil.stringNullOrEmpty(assignee)){
				 htiQuery.taskAssignee(assignee);
			 }
			 if(!CommonUtil.stringNullOrEmpty(assigneeLike)){
				 htiQuery.taskAssigneeLike(assigneeLike);
			 }
			 if(!CommonUtil.stringNullOrEmpty(candidateGroup)){
				 htiQuery.taskCandidateGroup(candidateGroup);
			 }
			 if(!CommonUtil.stringNullOrEmpty(candidateUser)){
				 htiQuery.taskCandidateUser(candidateUser);
			 }
			 if(null!=createdAfter){
				 htiQuery.taskCreatedAfter(createdAfter);
			 }
			 if(null!=createdBefore){
				 htiQuery.taskCreatedBefore(createdBefore);
			 }
			 if(null!=createdOn){
				 htiQuery.taskCreatedOn(createdOn);
			 }
			 if(!CommonUtil.stringNullOrEmpty(description)){
				 //始终模糊查询
				 htiQuery.taskDescriptionLike(description);
			 }
			 if(null!=dueAfter){
				 htiQuery.taskDueAfter(dueAfter);
			 }
			 if(null!=dueBefore){
				 htiQuery.taskDueBefore(dueBefore);
			 }
			 if(null!=dueOn){
				 htiQuery.taskDueDate(dueOn);
			 }
			 if(!CommonUtil.stringNullOrEmpty(executionId)){
				 htiQuery.executionId(executionId);
			 }
			 if(includeProcessVariables){
				 htiQuery.includeProcessVariables();
			 }
			 if(includeTaskLocalVariables){
				 htiQuery.includeTaskLocalVariables();
			 }
			 if(!CommonUtil.stringNullOrEmpty(involvedUser)){
				 htiQuery.taskInvolvedUser(involvedUser);
			 }
			 if(null!=maximumPriority){
				 htiQuery.taskMaxPriority(maximumPriority);
			 }
			 if(null!=minimumPriority){
				 htiQuery.taskMinPriority(minimumPriority);
			 }
			 if(!CommonUtil.stringNullOrEmpty(name)){
				 htiQuery.taskName(name);
			 }
			 if(!CommonUtil.stringNullOrEmpty(nameLike)){
				 htiQuery.taskNameLike(nameLike);
			 }
			 if(!CommonUtil.stringNullOrEmpty(owner)){
				 htiQuery.taskOwner(owner);
			 }
			 if(!CommonUtil.stringNullOrEmpty(ownerLike)){
				 htiQuery.taskOwnerLike(ownerLike);
			 }
			 if(null!=priority){
				 htiQuery.taskPriority(priority);
			 }
			 if(!CommonUtil.stringNullOrEmpty(processDefinitionKey)){
				 htiQuery.processDefinitionKey(processDefinitionKey);
			 }
			 if(!CommonUtil.stringNullOrEmpty(processDefinitionKeyLike)){
				 htiQuery.processDefinitionKeyLike(processDefinitionKeyLike);
			 }
			 if(!CommonUtil.stringNullOrEmpty(processDefinitionName)){
				 htiQuery.processDefinitionName(processDefinitionName);
			 }
			 if(!CommonUtil.stringNullOrEmpty(processDefinitionNameLike)){
				 htiQuery.processDefinitionNameLike(processDefinitionNameLike);
			 }
			 if(!CommonUtil.stringNullOrEmpty(processInstanceBusinessKey)){
				 htiQuery.processInstanceBusinessKey(processInstanceBusinessKey);
			 }
			 if(!CommonUtil.stringNullOrEmpty(processInstanceBusinessKeyLike)){
				 htiQuery.processInstanceBusinessKeyLike(processInstanceBusinessKeyLike);
			 }
			 if(!CommonUtil.stringNullOrEmpty(processInstanceId)){
				 htiQuery.processInstanceId(processInstanceId);
			 }
			 if(!CommonUtil.stringNullOrEmpty(taskDefinitionKey)){
				 htiQuery.taskDefinitionKey(taskDefinitionKey);
			 }
			 if(!CommonUtil.stringNullOrEmpty(taskDefinitionKeyLike)){
				 htiQuery.taskDefinitionKeyLike(taskDefinitionKeyLike);
			 }
			 if(!CommonUtil.stringNullOrEmpty(tenantId)){
				 htiQuery.taskTenantId(tenantId);
			 }
			 if(!CommonUtil.stringNullOrEmpty(tenantIdLike)){
				 htiQuery.taskTenantIdLike(tenantIdLike);
			 }
			 if(withoutDueDate){
				 htiQuery.withoutTaskDueDate();
			 }
			 if(withoutTenantId){
				 htiQuery.taskWithoutTenantId();
			 }
			 
			 //排序条件
			 if(params.getOrderByDeleteReason()){
				 htiQuery.orderByDeleteReason();
				 setOrder(htiQuery, params);
			 }
			 if(params.getOrderByExecutionId()){
				 htiQuery.orderByExecutionId();
				 setOrder(htiQuery, params);
			 }
			 if(params.getOrderByHistoricActivityInstanceId()){
				 htiQuery.orderByHistoricActivityInstanceId();
				 setOrder(htiQuery, params);
			 }
			 if(params.getOrderByHistoricTaskInstanceDuration()){
				 htiQuery.orderByHistoricTaskInstanceDuration();
				 setOrder(htiQuery, params);
			 }
			 if(params.getOrderByHistoricTaskInstanceEndTime()){
				 htiQuery.orderByHistoricTaskInstanceEndTime();
				 setOrder(htiQuery, params);
			 }
			 if(params.getOrderByHistoricTaskInstanceStartTime()){
				 htiQuery.orderByHistoricTaskInstanceStartTime();
				 setOrder(htiQuery, params);
			 }
			 if(params.getOrderByProcessDefinitionId()){
				 htiQuery.orderByProcessDefinitionId();
				 setOrder(htiQuery, params);
			 }
			 if(params.getOrderByProcessInstanceId()){
				 htiQuery.orderByProcessInstanceId();
				 setOrder(htiQuery, params);
			 }
			 if(params.getOrderByTaskAssignee()){
				 htiQuery.orderByTaskAssignee();
				 setOrder(htiQuery, params);
			 }
			 if(params.getOrderByTaskDefinitionKey()){
				 htiQuery.orderByTaskDefinitionKey();
				 setOrder(htiQuery, params);
			 }
			 if(params.getOrderByTaskDescription()){
				 htiQuery.orderByTaskDescription();
				 setOrder(htiQuery, params);
			 }
			 if(params.getOrderByTaskDueDate()){
				 htiQuery.orderByTaskDueDate();
				 setOrder(htiQuery, params);
			 }
			 if(params.getOrderByTaskId()){
				 htiQuery.orderByTaskId();
				 setOrder(htiQuery, params);
			 }
			 if(params.getOrderByTaskName()){
				 htiQuery.orderByTaskName();
				 setOrder(htiQuery, params);
			 }
			 if(params.getOrderByTaskOwner()){
				 htiQuery.orderByTaskOwner();
				 setOrder(htiQuery, params);
			 }
			 if(params.getOrderByTaskPriority()){
				 htiQuery.orderByTaskPriority();
				 setOrder(htiQuery, params);
			 }
			 if(params.getOrderByTenantId()){
				 htiQuery.orderByTenantId();
				 setOrder(htiQuery, params);
			 }
			 
			 List<HistoricTaskInstance> historicTaskInstanceList = new ArrayList<HistoricTaskInstance>();
			 boolean isPaged = params.isPaged();
			 if(isPaged){
				 historicTaskInstanceList = htiQuery.listPage((int)params.getStart(), (int)params.getSize());
				 long total = htiQuery.count();
				 params.setTotal(total);
			 }else{
				 historicTaskInstanceList = htiQuery.list();
			 }
			 wfHistoricTaskPageList.setWfQuery(params);
			 
			 if(!historicTaskInstanceList.isEmpty()){
				 for(HistoricTaskInstance hti:historicTaskInstanceList){
					 WfHistoricTask wht = getTransedWfHistoricTask(hti);
					 wfHistoricTaskPageList.add(wht);
				 }
			 }
		 }
		 return wfHistoricTaskPageList;
	}
	

	@Override
	public String deleteHistoricTask(WfOperator wfOperator, String taskId)
			throws Exception {
		if(CommonUtil.stringNullOrEmpty(taskId)){
			throw new NullPointerException("The taskId cannot be null");
		}
		historyService.deleteHistoricTaskInstance(taskId);
		return WfConstants.WF_CONTROL_EXE_SUCCESS;
	}

	@Override
	public List<WfIdentityLink> getHistoricTaskCandidates(String taskId) {
		if (CommonUtil.stringNullOrEmpty(taskId)) {
			throw new NullPointerException("The taskId cannot be null");
		}

		List<HistoricIdentityLink> historicIdentityLinkList = historyService.getHistoricIdentityLinksForTask(taskId);
		List<WfIdentityLink> wfIdentityLinkList = new ArrayList<WfIdentityLink>();
		if(null!=historicIdentityLinkList && !historicIdentityLinkList.isEmpty()){
			for(HistoricIdentityLink historicIdentity : historicIdentityLinkList){
				WfIdentityLink wl = getTransedWfIdentityLink(historicIdentity);
				wfIdentityLinkList.add(wl);
			}
		}
		return wfIdentityLinkList;
	}

	@Override
	public Map<String, Object> getHistoricTaskVariables(String taskId,
			VariableScope scope) throws Exception {
		HistoricTaskInstanceQuery htiQuery = historyService.createHistoricTaskInstanceQuery().taskId(taskId);
		if(null!=scope){
			if(VariableScope.GLOBAL.equals(scope)){
				htiQuery.includeProcessVariables();
			}else{
				htiQuery.includeTaskLocalVariables();
			}
		}else{
			htiQuery.includeTaskLocalVariables().includeProcessVariables();
		}
		
		HistoricTaskInstance taskObject = htiQuery.singleResult();
		Map<String, Object> variableMap = new HashMap<String, Object>();
		if(null!=taskObject){
			variableMap = taskObject.getTaskLocalVariables();
		}
		return variableMap;
	}

	@Override
	public Object getHistoricTaskVariableByName(String taskId,
			String variableName, VariableScope scope) throws Exception {
		HistoricTaskInstanceQuery htiQuery = historyService.createHistoricTaskInstanceQuery().taskId(taskId);
		if(null!=scope){
			if(VariableScope.GLOBAL==scope){
				htiQuery.includeProcessVariables();
			}else{
				htiQuery.includeTaskLocalVariables();
			}
		}else{
			htiQuery.includeTaskLocalVariables().includeProcessVariables();
		}
		
		HistoricTaskInstance taskObject = htiQuery.singleResult();
		
		if (taskObject == null) {
		    throw new ActivitiObjectNotFoundException("Historic task instance '" + taskId + "' couldn't be found.", HistoricTaskInstanceEntity.class);
		}

	    Object value = null;
	    if (scope != null) {
	      if (VariableScope.GLOBAL == scope) {
	        value = taskObject.getProcessVariables().get(variableName);
	      } else {
	        value = taskObject.getTaskLocalVariables().get(variableName);
	      }
	    } else {
	      // look for local task variables first
	      if (taskObject.getTaskLocalVariables().containsKey(variableName)) {
	        value = taskObject.getTaskLocalVariables().get(variableName);
	      } else {
	        value = taskObject.getProcessVariables().get(variableName);
	      }
	    }
	    
	    if (value == null) {
	        throw new ActivitiObjectNotFoundException("Historic task instance '" + taskId + "' variable value for " + variableName + " couldn't be found.", VariableInstanceEntity.class);
	    }
	    return value;
	}

	@Override
	public WfPageList<Map<String, Object>, WfVariableParam> queryHistoricVariables(
			WfVariableParam params) throws Exception {
//		params.
		WfPageList<Map<String, Object>, WfVariableParam> variablePageList = new WfPageList<Map<String,Object>, WfVariableParam>();
		if(null!=params){
			String processInstanceId = params.getProcessInstanceId();
			String taskId = params.getTaskId();
			String variableName = params.getVariableName();
			String variableNameLike = params.getVariableNameLike();
			
			HistoricTaskInstanceQuery taskQuery = historyService.createHistoricTaskInstanceQuery();
			if(!CommonUtil.stringNullOrEmpty(processInstanceId)){
				taskQuery.processInstanceId(processInstanceId);
			}
			if(!CommonUtil.stringNullOrEmpty(taskId)){
				taskQuery.taskId(taskId);
			}
			
			
			VariableScope scope = params.getScope();
			if(null!=scope){
				if(VariableScope.GLOBAL == scope){
					taskQuery.includeProcessVariables();
				}else{
					taskQuery.includeTaskLocalVariables();
				}
			}else{
				taskQuery.includeProcessVariables().includeTaskLocalVariables();
			}
			
			List<HistoricTaskInstance> htiList = new ArrayList<HistoricTaskInstance>();
			boolean isPaged = params.isPaged();
			if(isPaged){
				htiList = taskQuery.listPage((int)params.getStart(), (int)params.getSize());
				long total = taskQuery.count();
				params.setTotal(total);
			}else{
				htiList = taskQuery.list();
			}
			variablePageList.setWfQuery(params);
			
			if(!htiList.isEmpty()){
				if(CommonUtil.stringNullOrEmpty(variableName) && CommonUtil.stringNullOrEmpty(variableNameLike)){
					for(HistoricTaskInstance hti : htiList){
						Map<String, Object> map = null;
						if(null!=scope){
							 if (VariableScope.GLOBAL == scope) {
						        map = hti.getProcessVariables();
						      } else {
						        map = hti.getTaskLocalVariables();
						      }
						}else{
							Map<String, Object> processVariableMap = hti.getProcessVariables();
							Map<String, Object> taskLocalVariableMap = hti.getTaskLocalVariables();
							map = new HashMap<String, Object>();
							map.putAll(processVariableMap);
							map.putAll(taskLocalVariableMap);
						}
						variablePageList.add(map);
					}
				}else{
					for(HistoricTaskInstance hti : htiList){
						Map<String, Object> map = null;
						if(null!=scope){
							 if (VariableScope.GLOBAL == scope) {
								map =new HashMap<String, Object>();
						        if(hti.getProcessVariables().containsKey(variableName)){
						        	map.put(variableName, hti.getProcessVariables().get(variableName));
						        }
						      } else {
						    	  map =new HashMap<String, Object>();
						    	  if(hti.getTaskLocalVariables().containsKey(variableName)){
						    		  map.put(variableName, hti.getTaskLocalVariables().get(variableName));
						    	  }
						      }
						}else{
							 // look for local task variables first
							 map =new HashMap<String, Object>();
						      if (hti.getTaskLocalVariables().containsKey(variableName)) {
						    	  map.put(variableName, hti.getTaskLocalVariables().get(variableName));
						      } else {
						    	  map.put(variableName, hti.getProcessVariables().get(variableName));
						      }
						}
						variablePageList.add(map);
					}
				}
				
			}
		}
		return variablePageList;
	}

	@Override
	public WfPageList<WfDetail, WfDetailParam> queryHistoricDetails(
			WfDetailParam params) throws Exception {
		WfPageList<WfDetail, WfDetailParam> wfDetailPageList = new WfPageList<WfDetail, WfDetailParam>(); 
		if(null!= params){
			String activityInstanceId = params.getActivityInstanceId();
			String executionId = params.getExecutionId();
			String id = params.getId();
			String processInstanceId = params.getProcessInstanceId();
			boolean selectOnlyFormProperties = params.getSelectOnlyFormProperties()==null?false:params.getSelectOnlyFormProperties();
			boolean selectOnlyVariableUpdates = params.getSelectOnlyVariableUpdates()==null?false:params.getSelectOnlyVariableUpdates();
			String taskId = params.getTaskId();
			
			HistoricDetailQuery hdQuery = historyService.createHistoricDetailQuery();
			if(!CommonUtil.stringNullOrEmpty(activityInstanceId)){
				hdQuery.activityInstanceId(activityInstanceId);
			}
			if(!CommonUtil.stringNullOrEmpty(executionId)){
				hdQuery.executionId(executionId);
			}
			if(!CommonUtil.stringNullOrEmpty(id)){
				hdQuery.id(id);
			}
			if(!CommonUtil.stringNullOrEmpty(processInstanceId)){
				hdQuery.processInstanceId(processInstanceId);
			}
			if(!CommonUtil.stringNullOrEmpty(taskId)){
				hdQuery.taskId(taskId);
			}
			if(selectOnlyFormProperties){
				hdQuery.formProperties();
			}
			if(selectOnlyVariableUpdates){
				hdQuery.variableUpdates();
			}
			
			List<HistoricDetail> historicDetailList = new ArrayList<HistoricDetail>();
			boolean isPaged = params.isPaged();
			if(isPaged){
				historicDetailList = hdQuery.listPage((int)params.getStart(), (int)params.getSize());
				long total = hdQuery.count();
				params.setTotal(total);
			}else{
				hdQuery.list();
			}
			wfDetailPageList.setWfQuery(params);
			
			if(!historicDetailList.isEmpty()){
				for(HistoricDetail historicDetail : historicDetailList){
					WfDetail wfDetail = new WfDetail();
					wfDetail.setActivityInstanceId(historicDetail.getActivityInstanceId());
//					wfDetail.setDetailType(historicDetail.)
					wfDetail.setExecutionId(historicDetail.getExecutionId());
					wfDetail.setId(historicDetail.getId());
					wfDetail.setProcessInstanceId(historicDetail.getProcessInstanceId());
//					wfDetail.setPropertyId(historicDetail)
//					wfDetail.setPropertyValue(historicDetail.get)
					wfDetail.setTaskId(historicDetail.getTaskId());
					wfDetail.setTime(historicDetail.getTime());
					wfDetailPageList.add(wfDetail);
				}
				
			}
			
		}
		return wfDetailPageList;
	}
	
	private WfHistoricProcessInstance getTransedWfHistoricProcessInstance(HistoricProcessInstance historicProcessInstance){
		if(historicProcessInstance!=null){
			WfHistoricProcessInstance whpi = new WfHistoricProcessInstance();
			whpi.setProcessDefinitionId(historicProcessInstance.getProcessDefinitionId());
			whpi.setBusinessKey(historicProcessInstance.getBusinessKey());
			whpi.setDeleteReason(historicProcessInstance.getDeleteReason());
			whpi.setDurationInMillis(historicProcessInstance.getDurationInMillis());
			whpi.setEndTime(historicProcessInstance.getEndTime());
			whpi.setId(historicProcessInstance.getId());
			whpi.setProcessVariables(historicProcessInstance.getProcessVariables());
			whpi.setStartActivityId(historicProcessInstance.getStartActivityId());
			whpi.setStartTime(historicProcessInstance.getStartTime());
			whpi.setStartUserId(historicProcessInstance.getStartUserId());
			whpi.setSuperProcessInstanceId(historicProcessInstance.getSuperProcessInstanceId());
			return whpi;
		}
		return null;
	}
	
	private WfIdentityLink getTransedWfIdentityLink(HistoricIdentityLink historicIdentityLink){
		if(historicIdentityLink!=null){
			WfIdentityLink wil = new WfIdentityLink();
			
			wil.setProcessInstanceId(historicIdentityLink.getProcessInstanceId());
			wil.setGroupId(historicIdentityLink.getGroupId());
			wil.setTaskId(historicIdentityLink.getTaskId());
			wil.setType(historicIdentityLink.getType());
			wil.setUserId(historicIdentityLink.getUserId());
			return wil;
		}
		return null;
	}
	
	private WfHistoricVariableInstance getTransedWfHistoricVariableInstance(HistoricVariableInstance historicVariableInstance){
		if(null!=historicVariableInstance){
			WfHistoricVariableInstance wv = new WfHistoricVariableInstance();
			wv.setCreateTime(historicVariableInstance.getCreateTime());
			wv.setId(historicVariableInstance.getId());
			wv.setLastUpdatedTime(historicVariableInstance.getLastUpdatedTime());
			wv.setProcessInstanceId(historicVariableInstance.getProcessInstanceId());
			wv.setTaskId(historicVariableInstance.getTaskId());
			wv.setTime(historicVariableInstance.getTime());
			wv.setValue(historicVariableInstance.getValue());
			wv.setVariableName(historicVariableInstance.getVariableName());
			wv.setVariableTypeName(historicVariableInstance.getVariableTypeName());
			return wv;
		}else{
			return null;
		}
	}
	
	private WfComment getTransedWfComment(Comment comment){
		if(null!=comment){
			WfComment wfComment = new WfComment();
			wfComment.setId(comment.getId());
			wfComment.setMessage(comment.getFullMessage());
			wfComment.setProcessInstanceId(comment.getProcessInstanceId());
			wfComment.setTaskId(comment.getTaskId());
			wfComment.setTime(comment.getTime());
			wfComment.setType(comment.getType());
			wfComment.setUserId(comment.getUserId());
			return wfComment;
		}else{
			return null;
		}
	}
	
	private WfHistoricTask getTransedWfHistoricTask(HistoricTaskInstance taskInstance) {
		if(taskInstance!=null){
			WfHistoricTask wfHistoricTask = new WfHistoricTask();
			wfHistoricTask.setAssignee(taskInstance.getAssignee());
			wfHistoricTask.setCategory(taskInstance.getCategory());
			wfHistoricTask.setClaimTime(taskInstance.getClaimTime());
			wfHistoricTask.setDeleteReason(taskInstance.getDeleteReason());
			wfHistoricTask.setDescription(taskInstance.getDescription());
			wfHistoricTask.setDueDate(taskInstance.getDueDate());
			wfHistoricTask.setDurationInMillis(taskInstance.getDurationInMillis());
			wfHistoricTask.setEndTime(taskInstance.getEndTime());
			wfHistoricTask.setExecutionId(taskInstance.getExecutionId());
			wfHistoricTask.setFormKey(taskInstance.getFormKey());
			wfHistoricTask.setId(taskInstance.getId());
			wfHistoricTask.setName(taskInstance.getName());
			wfHistoricTask.setOwner(taskInstance.getOwner());
			wfHistoricTask.setParentTaskId(taskInstance.getParentTaskId());
			wfHistoricTask.setPriority(taskInstance.getPriority());
			wfHistoricTask.setProcessDefinitionId(taskInstance.getProcessDefinitionId());
			wfHistoricTask.setProcessInstanceId(taskInstance.getProcessInstanceId());
			wfHistoricTask.setProcessVariables(taskInstance.getProcessVariables());
			wfHistoricTask.setStartTime(taskInstance.getStartTime());
//			wfHistoricTask.setSuspended(taskInstance.)
			wfHistoricTask.setTaskDefinitionKey(taskInstance.getTaskDefinitionKey());
			wfHistoricTask.setTaskLocalVariables(taskInstance.getTaskLocalVariables());
			wfHistoricTask.setTenantId(taskInstance.getTenantId());
			wfHistoricTask.setWorkTimeInMillis(taskInstance.getWorkTimeInMillis());
			return wfHistoricTask;
		}else{
			return null;
		}
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

}
