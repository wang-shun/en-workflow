package com.chinacreator.c2.flow.listener;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.ManagementService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.task.IdentityLink;
import org.springframework.context.ApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinacreator.c2.flow.api.WfManagerService;
import com.chinacreator.c2.flow.cmd.delegate.GetModuleDelegateByParam;
import com.chinacreator.c2.flow.cmd.delegate.GetModuleDelegateOnDelegate;
import com.chinacreator.c2.flow.cmd.moduleconfig.FindProcessConfigProperty;
import com.chinacreator.c2.flow.cmd.unitetask.FindWfUniteRunTaskByTaskIdCmd;
import com.chinacreator.c2.flow.cmd.unitetask.InsertWfUniteRunTaskCmd;
import com.chinacreator.c2.flow.cmd.unitetask.InsertWfUniteRunTaskExtCmd;
import com.chinacreator.c2.flow.detail.WfBusinessData;
import com.chinacreator.c2.flow.detail.WfConstants;
import com.chinacreator.c2.flow.detail.WfModuleDelegateParam;
import com.chinacreator.c2.flow.detail.WfPageList;
import com.chinacreator.c2.flow.detail.WfProcessConfigProperty;
import com.chinacreator.c2.flow.persistence.entity.WfModuleDelegateEntity;
import com.chinacreator.c2.flow.persistence.entity.WfModuleDelegateFullAttrEntity;
import com.chinacreator.c2.flow.persistence.entity.WfUniteRunTaskEntity;
import com.chinacreator.c2.flow.persistence.entity.WfUniteRunTaskExtendEntity;
import com.chinacreator.c2.flow.util.CommonUtil;

/**
 * 任务监听器，用于创建任务到统一任务表，事件监听器获取不到任务实体的属性，所以新增放到这里实现。
 * 
 * @author minghua.guo
 * 
 */
public class ExtendTaskListener implements TaskListener {
	private static final long serialVersionUID = 1L;

	private ManagementService managementService;
	private ApplicationContext context;
	


	public ExtendTaskListener(ManagementService managementService, ApplicationContext context){
		this.managementService = managementService;
		this.context = context;
	}
	
	@Override
	public void notify(DelegateTask delegateTask) {
		String eventName = delegateTask.getEventName();
		TaskEntity taskEntity = (TaskEntity) delegateTask;
		if (EVENTNAME_CREATE.equals(eventName)) {
			onCreate(taskEntity);
		} /*
		 * else if (EVENTNAME_ASSIGNMENT.equals(eventName)) {
		 * onUpdate(taskEntity, wfUniteTaskManager); } else if
		 * (EVENTNAME_COMPLETE.equals(eventName)) {
		 * 
		 * } else if (EVENTNAME_DELETE.equals(eventName)) { onDelete(taskEntity,
		 * wfUniteTaskManager); }
		 */
	}

	/**
	 * Called when an entity create event is received.
	 */
	protected void onCreate(TaskEntity taskEntity) {
		taskEntity = Context.getCommandContext().getTaskEntityManager()
				.findTaskById(taskEntity.getId());
		
		if(null != taskEntity){

			// 进行外围配置参数的设置
			setByOuterConfig(taskEntity);
			
			// 进行任务委托
			setDelegate(taskEntity);
			
			Set<IdentityLink> identityLinks = taskEntity.getCandidates();
			
			WfUniteRunTaskEntity wfUniteRunTask = coventWfUniteRunTask(taskEntity,
					"insert");
			if(identityLinks != null && identityLinks.size() > 0){
				for(IdentityLink identityLinkEntity : identityLinks){
					String type = identityLinkEntity.getType();
					if(WfConstants.IDENTITYLINKTYPE_CANDIDATE.equals(type)){
						String userId = identityLinkEntity.getUserId();
						String groupId = identityLinkEntity.getGroupId();
						if(userId != null && !"".equals(userId)){
							wfUniteRunTask.setCategory(WfConstants.WF_IDENTITYLINKTYPE_USERS);
							wfUniteRunTask.setCandidate(userId);
						}else if(groupId != null && !"".equals(groupId)){
							wfUniteRunTask.setCategory(WfConstants.WF_IDENTITYLINKTYPE_GROUPS);
							wfUniteRunTask.setCandidate(groupId);
						}
						wfUniteRunTask.setId(Context.getCommandContext().getProcessEngineConfiguration().getIdGenerator().getNextId());
						WfUniteRunTaskEntity wfUniteRunTaskEntity = managementService.executeCommand(new InsertWfUniteRunTaskCmd(wfUniteRunTask));
						// 拿到统一任务的ID
						String uniteTaskId = wfUniteRunTaskEntity.getId();
						
						// 插入业务扩展数据到统一任务扩展表
						/*WfUniteRunTaskExtendEntity wfUniteRunTaskExtendEntity = new WfUniteRunTaskExtendEntity();
						wfUniteRunTaskExtendEntity.setUniteTaskId(uniteTaskId);*/
						
						// 遍历对象的属性
						Map<String, Object> variables = taskEntity.getVariablesLocal();
						WfBusinessData wfBusinessData = (WfBusinessData) variables
								.get(WfConstants.WF_BUSINESS_DATA_KEY);
						if (wfBusinessData == null) {
							variables = taskEntity.getVariables();
							wfBusinessData = (WfBusinessData) variables
									.get(WfConstants.WF_BUSINESS_DATA_KEY);
						}
						if(wfBusinessData!=null && wfBusinessData.getBusinessExtend()!=null){
							
							Object businessExtendObject = wfBusinessData.getBusinessExtend();
							
							//businessExtendObject.getClass().getMethod(null, null).getReturnType().getName();
							// 筛选出非基本类型和非String类型的属性，创建一个返回类名和属性key的映射
							//businessExtendObject.getClass().getFields()
							Map<String, String> attrReturnTypeMap = new HashMap<String, String>();
							Method[] ms = businessExtendObject.getClass().getMethods();
							if(ms!=null && ms.length>0){
								for(Method method : ms){
									String returnTypeName = method.getReturnType().getName();
									if(returnTypeName.equals(Integer.class.getName()) || returnTypeName.equals(Long.class.getName())
											|| returnTypeName.equals(Float.class.getName()) || returnTypeName.equals(Double.class.getName())
											|| returnTypeName.equals(String.class.getName())|| returnTypeName.equals(Boolean.class.getName())
											|| returnTypeName.equals(Short.class.getName())|| returnTypeName.equals(Character.class.getName())
											){
										continue;
									}else{
										String methodName = method.getName();
										if(methodName.startsWith("get")){
											String fieldName = methodName.substring(3, methodName.length());
											String pre = fieldName.substring(0, 1).toLowerCase();
											String tail = fieldName.substring(1, fieldName.length());
											fieldName = pre+tail;
											attrReturnTypeMap.put(fieldName, returnTypeName);
										}
										
									}
								}
							}
							
							JSONObject jsonObj = (JSONObject) JSON.toJSON(businessExtendObject);
							Set<String> keySet = jsonObj.keySet();
							if(!keySet.isEmpty()){
								for (String key : keySet) {
									WfUniteRunTaskExtendEntity wfUniteRunTaskExtendEntity = new WfUniteRunTaskExtendEntity();
									wfUniteRunTaskExtendEntity.setId(Context.getCommandContext().getProcessEngineConfiguration()
											.getIdGenerator().getNextId());
									wfUniteRunTaskExtendEntity.setUniteTaskId(uniteTaskId);
									wfUniteRunTaskExtendEntity.setExtFieldKey(key);
									Object o = jsonObj.get(key);
									String className = o.getClass().getName();
									String stringValue = null;
									if (o instanceof Integer) {
										stringValue = o + "";
									}else if(o instanceof Long){
										stringValue = o + "";
									}else if(o instanceof Float){
										stringValue = o + "";
									}else if(o instanceof Double){
										stringValue = o + "";
									}else if(o instanceof String){
										stringValue = o + "";
									}else if(o instanceof Boolean){
										stringValue = o + "";
									}else if(o instanceof Short){
										stringValue = o + "";
									}else if(o instanceof Character){
										stringValue = o + "";
									}else{
										stringValue = JSON.toJSONString(o);
									}
									if(attrReturnTypeMap.containsKey(key)){
										wfUniteRunTaskExtendEntity.setExtFieldType(attrReturnTypeMap.get(key));
									}else{
										wfUniteRunTaskExtendEntity.setExtFieldType(className);
									}
									wfUniteRunTaskExtendEntity
											.setExtFieldValue(stringValue);
									
									 managementService.executeCommand(new InsertWfUniteRunTaskExtCmd(wfUniteRunTaskExtendEntity));
								}
							}
							
						}
						
					}
				}
			}else{
				wfUniteRunTask.setId(Context.getCommandContext().getProcessEngineConfiguration().getIdGenerator().getNextId());
				managementService.executeCommand(new InsertWfUniteRunTaskCmd(wfUniteRunTask));
			}
		}
	}

	private void setDelegate(TaskEntity taskEntity) {
		Map<String,Object> variableMap = taskEntity.getVariables();
		WfBusinessData wfBusinessData = (WfBusinessData) variableMap.get(WfConstants.WF_BUSINESS_DATA_KEY);
		String moduleId = wfBusinessData.getModuleId();
		// 查询当前处理用户是不是事项的委托人，带委托时间，委托状态
		// 目前只考虑执行人，不考虑参与者和参与组的情况
		if(!CommonUtil.stringNullOrEmpty(moduleId)){
			    String assignee = taskEntity.getAssignee();//执行人
			    if(!CommonUtil.stringNullOrEmpty(assignee)){
			    	List<WfModuleDelegateFullAttrEntity> list = managementService.executeCommand(new GetModuleDelegateOnDelegate(moduleId, assignee, null, WfConstants.WF_DELEGATE_RANGE_SOME, WfConstants.WF_DELEGATE_STAT_DELEGATEING, new Date()));
			    	if(list!=null && !list.isEmpty()){
						WfModuleDelegateFullAttrEntity entity = list.get(0);
						try{
							delegate(taskEntity, assignee, entity.getDesigneeId(), "0");
						} catch (Exception e) {
							e.printStackTrace();
						}
			    	}
			    	
			    }
			    
			    Set<IdentityLink> identityInlnkSet = taskEntity.getCandidates();//参与者
			    if(identityInlnkSet!=null && !identityInlnkSet.isEmpty()){
			    	for(IdentityLink identityLink : identityInlnkSet){
			    		if(!CommonUtil.stringNullOrEmpty(identityLink.getUserId())){
			    			// 先查询是否存在个别事项委托的情况
							List<WfModuleDelegateFullAttrEntity> list = managementService.executeCommand(new GetModuleDelegateOnDelegate(moduleId, identityLink.getUserId(), null, WfConstants.WF_DELEGATE_RANGE_SOME, WfConstants.WF_DELEGATE_STAT_DELEGATEING, new Date()));
							if(list!=null && !list.isEmpty()){
								WfModuleDelegateFullAttrEntity entity = list.get(0);
								try {
									//1： 参与者    0：执行人
									delegate(taskEntity, identityLink.getUserId(), entity.getDesigneeId(), "1");
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
			    		}
			    	}
			    }
			    
				
			    if(!CommonUtil.stringNullOrEmpty(assignee)){
			    	// 如果不存在个别事项委托，查询当前执行人是否存在全局委托
					WfModuleDelegateParam wfModuleDelegateParam = new WfModuleDelegateParam();
					wfModuleDelegateParam.setDesignatorId(assignee);
					wfModuleDelegateParam.setDelegateRange(WfConstants.WF_DELEGATE_RANGE_ALL);
					wfModuleDelegateParam.setDelegateStartTimeEnd(new Date());
					wfModuleDelegateParam.setDelegateEndTimeBegine(new Date());
					wfModuleDelegateParam.setDelegateStat(WfConstants.WF_DELEGATE_STAT_DELEGATEING);
					WfPageList<WfModuleDelegateEntity, WfModuleDelegateParam> wfPageList = managementService.executeCommand(new GetModuleDelegateByParam(wfModuleDelegateParam));
					if((wfPageList!=null && wfPageList.getDatas()!=null && !wfPageList.getDatas().isEmpty())){
						WfModuleDelegateEntity entity = wfPageList.getDatas().get(0);
						try {
							delegate(taskEntity, assignee, entity.getDesigneeId(), "0");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
			    }
			    if(identityInlnkSet!=null && !identityInlnkSet.isEmpty()){
			    	for(IdentityLink identityLink : identityInlnkSet){
			    		if(!CommonUtil.stringNullOrEmpty(identityLink.getUserId())){
			    			WfModuleDelegateParam wfModuleDelegateParam = new WfModuleDelegateParam();
							wfModuleDelegateParam.setDesignatorId(identityLink.getUserId());
							wfModuleDelegateParam.setDelegateRange(WfConstants.WF_DELEGATE_RANGE_ALL);
							wfModuleDelegateParam.setDelegateStat(WfConstants.WF_DELEGATE_STAT_DELEGATEING);
							wfModuleDelegateParam.setDelegateStartTimeEnd(new Date());
							wfModuleDelegateParam.setDelegateEndTimeBegine(new Date());
							WfPageList<WfModuleDelegateEntity, WfModuleDelegateParam> wfPageList = managementService.executeCommand(new GetModuleDelegateByParam(wfModuleDelegateParam));
							if(wfPageList!=null && wfPageList.getDatas()!=null && !wfPageList.getDatas().isEmpty()){
								WfModuleDelegateEntity entity = wfPageList.getDatas().get(0);
								try {
									delegate(taskEntity, identityLink.getUserId(), entity.getDesigneeId(), "1");
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
			    		}
			    	}
			    }
				
		}
		
	}

	private void setByOuterConfig(TaskEntity taskEntity) {
		
		String taskDefKey = taskEntity.getTaskDefinitionKey();
		Map<String,Object> variableMap = taskEntity.getVariables();
		WfBusinessData wfBusinessData = (WfBusinessData) variableMap.get(WfConstants.WF_BUSINESS_DATA_KEY);
		String moduleId = wfBusinessData.getModuleId();
		String procDefId = taskEntity.getProcessDefinitionId();

		boolean hasPerformer = true;
		
		Set<IdentityLink> identityInlnkSet = taskEntity.getCandidates();
		String assignee = taskEntity.getAssignee();
		//如果任务签收人为空字符串(一般不会这样)，会导致任务不能签收，设置为null
		if(assignee != null && "".equals(assignee.trim())){
			taskEntity.setAssignee(null);
		}
		//当参与者是非空时，也要判断是用户参与者非空还是群组参与者非空，对参与者为空的，读取外围配置进行参与者的指定
		if((null==identityInlnkSet || identityInlnkSet.isEmpty()) && CommonUtil.stringNullOrEmpty(assignee)){
			hasPerformer = false;
		}
		List<WfProcessConfigProperty> wfProcessConfigPropertyList = managementService.executeCommand(new FindProcessConfigProperty(procDefId, moduleId, taskDefKey));
		if(wfProcessConfigPropertyList!=null && !wfProcessConfigPropertyList.isEmpty()){
			WfProcessConfigProperty wpcp = wfProcessConfigPropertyList.get(0);
			//当参与者是非空时，也要判断是用户参与者非空还是群组参与者非空，对参与者为空的，读取外围配置进行参与者的指定
			if((null==identityInlnkSet || identityInlnkSet.isEmpty()) && CommonUtil.stringNullOrEmpty(assignee)){
				hasPerformer = false;
				String groupPerformer = wpcp.getGroupPerformer();
				if(!CommonUtil.stringNullOrEmpty(groupPerformer)){
					String[] groupPerformers = groupPerformer.split(",");
					List<String> pArr = new ArrayList<String>();
					for(String p : groupPerformers){
						pArr.add(p);
					}
					taskEntity.addCandidateGroups(pArr);
					hasPerformer = true;
				}
				
				String performer = wpcp.getPerformer();
				if(!CommonUtil.stringNullOrEmpty(performer)){
					String[] userPerformers = performer.split(",");
					List<String> pArr = new ArrayList<String>();
					for(String p : userPerformers){
						pArr.add(p);
					}
					taskEntity.addCandidateUsers(pArr);
					hasPerformer = true;
				}
			}
			
			if(null==taskEntity.getDueDate()){
				Integer duration = wpcp.getDuration();
				String durationUnit = wpcp.getDurationUnit();
				Date dueDate = null;
				// 采用服务器时间做当前时间，务必保证应用服务器和数据库服务器时钟同步
				if(duration!=null && duration>0 && durationUnit!=null && !"".equals(durationUnit.trim())){
					try {
						dueDate = calculateDueDate(taskEntity.getCreateTime(), duration, durationUnit);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				taskEntity.setDueDate(dueDate);
			}
			
			if(!CommonUtil.stringNullOrEmpty(wpcp.getAlias())){
				// 配置了环节别名，则采用环节别名
				taskEntity.setName(wpcp.getAlias());
			}
		}
		//如果任务不存在任何候选人（组）或执行人，默认设置任务执行人为流程发起人
		if(!hasPerformer){
			String processInstanceId = taskEntity.getProcessInstanceId();
			HistoricProcessInstanceEntity historicProcessInstanceEntity = Context
					.getCommandContext()
					.getHistoricProcessInstanceEntityManager()
					.findHistoricProcessInstance(processInstanceId);
			taskEntity.addCandidateUser(historicProcessInstanceEntity.getStartUserId());
		}
	}

	private WfUniteRunTaskEntity coventWfUniteRunTask(TaskEntity taskEntity,
			String opType) {
		WfUniteRunTaskEntity wfUnitRunTask = new WfUniteRunTaskEntity();
		String assignee = taskEntity.getAssignee();
		String category = taskEntity.getCategory();
		Date createTime = taskEntity.getCreateTime();
		String delegationState = taskEntity.getDelegationStateString();
		String description = taskEntity.getDescription();
		Date dueDate = taskEntity.getDueDate();
		String executionId = taskEntity.getExecutionId();
		String taskId = taskEntity.getId();
		String name = taskEntity.getName();
		String owner = taskEntity.getOwner();
		String parentTaskId = taskEntity.getParentTaskId();
		int priority = taskEntity.getPriority();
		String processDefinitionId = taskEntity.getProcessDefinitionId();
		String processInstanceId = taskEntity.getProcessInstanceId();
		int suspensionState = taskEntity.getSuspensionState();
		String type = WfConstants.WF_UNITE_TASK_TYPE_TODO;
		if (suspensionState == WfConstants.WF_SUSPEND_INT) {
			type = WfConstants.WF_UNITE_TASK_TYPE_SUSPEND;
		}
		String taskDefinitionKey = taskEntity.getTaskDefinitionKey();
		String tenantId = taskEntity.getTenantId();
		Map<String, Object> variables = taskEntity.getVariablesLocal();
		WfBusinessData wfBusinessData = (WfBusinessData) variables
				.get(WfConstants.WF_BUSINESS_DATA_KEY);
		if (wfBusinessData == null) {
			variables = taskEntity.getVariables();
			wfBusinessData = (WfBusinessData) variables
					.get(WfConstants.WF_BUSINESS_DATA_KEY);
		}
		String businessKey = "";
		String appId = "";
		String moduleId = "";
		String moduleName = "";
		String remark1 = "";
		String remark2 = "";
		String remark3 = "";
		String remark4 = "";
		String remark5 = "";
		String remark6 = "";
		String remark7 = "";
		String remark8 = "";
		String remark9 = "";
		String remark10 = "";
		if (wfBusinessData != null) {
			appId = wfBusinessData.getAppId();
			moduleId = wfBusinessData.getModuleId();
			moduleName = wfBusinessData.getModuleName();
			remark1 = wfBusinessData.getRemark1();
			remark2 = wfBusinessData.getRemark2();
			remark3 = wfBusinessData.getRemark3();
			remark4 = wfBusinessData.getRemark4();
			remark5 = wfBusinessData.getRemark5();
			remark6 = wfBusinessData.getRemark6();
			remark7 = wfBusinessData.getRemark7();
			remark8 = wfBusinessData.getRemark8();
			remark9 = wfBusinessData.getRemark9();
			remark10 = wfBusinessData.getRemark10();
			businessKey = wfBusinessData.getBusinessKey();
		}
		if ("insert".equals(opType)) {
		} else {
			managementService.executeCommand(new FindWfUniteRunTaskByTaskIdCmd(taskId));
		}
		ExecutionEntity ee = taskEntity.getProcessInstance();
		if (ee != null) {
			String tmp = businessKey;
			if (tmp == null || "".equals(tmp))
				businessKey = ee.getBusinessKey();
		}

		if (appId != null)
			wfUnitRunTask.setAppId(appId);
		wfUnitRunTask.setAssignee(assignee);
		if (businessKey != null)
			wfUnitRunTask.setBusinessKey(businessKey);
		wfUnitRunTask.setTaskId(taskId);
		wfUnitRunTask.setTaskState(type);
		wfUnitRunTask.setTaskDefKey(taskDefinitionKey);
		wfUnitRunTask.setTenantId(tenantId);
		wfUnitRunTask.setCategory(category);
		wfUnitRunTask.setCreateTime(createTime);
		wfUnitRunTask.setDelegation(delegationState);
		wfUnitRunTask.setDeleteReason("");
		wfUnitRunTask.setDescription(description);
		wfUnitRunTask.setDueDate(dueDate);
		wfUnitRunTask.setExecutionId(executionId);
		wfUnitRunTask.setFormKey("");
		if (moduleId != null)
			wfUnitRunTask.setModuleId(moduleId);
		if (moduleName != null)
			wfUnitRunTask.setModuleName(moduleName);
		wfUnitRunTask.setName(name);
		wfUnitRunTask.setOwner(owner);
		wfUnitRunTask.setParentTaskId(parentTaskId);
		wfUnitRunTask.setPriority(priority);
		wfUnitRunTask.setProcDefId(processDefinitionId);
		wfUnitRunTask.setProcInstId(processInstanceId);
		if (remark1 != null)
			wfUnitRunTask.setRemark1(remark1);
		if (remark2 != null)
			wfUnitRunTask.setRemark2(remark2);
		if (remark3 != null)
			wfUnitRunTask.setRemark3(remark3);
		if (remark4 != null)
			wfUnitRunTask.setRemark4(remark4);
		if (remark5 != null)
			wfUnitRunTask.setRemark5(remark5);
		if (remark6 != null)
			wfUnitRunTask.setRemark6(remark6);
		if (remark7 != null)
			wfUnitRunTask.setRemark7(remark7);
		if (remark8 != null)
			wfUnitRunTask.setRemark8(remark8);
		if (remark9 != null)
			wfUnitRunTask.setRemark9(remark9);
		if (remark10 != null)
			wfUnitRunTask.setRemark10(remark10);
		return wfUnitRunTask;
	}

	public ManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(ManagementService managementService) {
		this.managementService = managementService;
	}
	
	/**
	 * 计算截止日期
	 * @param date 当前时间
	 * @param duration 间隔量
	 * @param durationUnit 间隔单位（年:Y;月:M;日:D;小时:h;分钟:m;秒:s）
	 */
	private Date calculateDueDate(Date date, Integer duration,
			String durationUnit) throws Exception{
		return context.getBean(WfManagerService.class).getDueDateAfterExecute(date, duration, durationUnit, null);
	}
	
	private void delegate(TaskEntity taskEntity, String userFrom, String userTo, String type) throws Exception {
		if("1".equals(type)){
			taskEntity.deleteCandidateUser(userFrom);
			taskEntity.addCandidateUser(userTo);
		}
		if("0".equals(type)){
			taskEntity.setAssignee(userTo);
		}
	}

	public ApplicationContext getContext() {
		return context;
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}
}
