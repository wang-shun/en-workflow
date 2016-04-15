package com.chinacreator.c2.flow.listener;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.ManagementService;
import org.activiti.engine.delegate.event.ActivitiEntityEvent;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.HistoricTaskInstanceEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;

import com.chinacreator.c2.flow.cmd.unitetask.DeleteWfUniteRunTaskCmd;
import com.chinacreator.c2.flow.cmd.unitetask.DeleteWfUniteRunTaskExtCmd;
import com.chinacreator.c2.flow.cmd.unitetask.FindWfUniteHisTaskByTaskIdCmd;
import com.chinacreator.c2.flow.cmd.unitetask.FindWfUniteRunTaskByTaskIdCmd;
import com.chinacreator.c2.flow.cmd.unitetask.FindWfUniteRunTaskExtByIdCmd;
import com.chinacreator.c2.flow.cmd.unitetask.InsertWfUniteHisTaskCmd;
import com.chinacreator.c2.flow.cmd.unitetask.InsertWfUniteHisTaskExtCmd;
import com.chinacreator.c2.flow.cmd.unitetask.UpdateWfUniteHisTaskCmd;
import com.chinacreator.c2.flow.cmd.unitetask.UpdateWfUniteRunTaskCmd;
import com.chinacreator.c2.flow.detail.WfBusinessData;
import com.chinacreator.c2.flow.detail.WfConstants;
import com.chinacreator.c2.flow.persistence.entity.WfUniteHisTaskEntity;
import com.chinacreator.c2.flow.persistence.entity.WfUniteHisTaskExtendEntity;
import com.chinacreator.c2.flow.persistence.entity.WfUniteRunTaskEntity;
import com.chinacreator.c2.flow.persistence.entity.WfUniteRunTaskExtendEntity;

public class ExtendEntityEventListener implements ActivitiEventListener {

	protected boolean failOnException = false;
	protected Set<Class<?>> entityClasses;

	private ManagementService managementService;

	@Override
	public void onEvent(ActivitiEvent event) {
		if (isValidEvent(event)) {
			managementService = event.getEngineServices()
					.getManagementService();
			// Check if this event
			if (event.getType() == ActivitiEventType.ENTITY_CREATED) {
				onCreate(event);
			} else if (event.getType() == ActivitiEventType.ENTITY_INITIALIZED) {
				onInitialized(event);
			} else if (event.getType() == ActivitiEventType.ENTITY_DELETED) {
				onDelete(event);
			} else if (event.getType() == ActivitiEventType.ENTITY_UPDATED) {
				onUpdate(event);
			} else if (event.getType() == ActivitiEventType.ENTITY_SUSPENDED) {
				onSuspend(event);
			} else if (event.getType() == ActivitiEventType.ENTITY_ACTIVATED) {
				onActivated(event);
			} else {
				// Entity-specific event
				onEntityEvent(event);
			}
		}
	}

	@Override
	public boolean isFailOnException() {
		return failOnException;
	}

	/**
	 * @return true, if the event is an {@link ActivitiEntityEvent} and (if
	 *         needed) the entityClass set in this instance, is assignable from
	 *         the entity class in the event.
	 */
	protected boolean isValidEvent(ActivitiEvent event) {
		boolean valid = false;
		if (event instanceof ActivitiEntityEvent) {
			if (entityClasses == null) {
				valid = true;
			} else {
				for (Class<?> entityClass : entityClasses) {
					valid = entityClass
							.isAssignableFrom(((ActivitiEntityEvent) event)
									.getEntity().getClass());
					if (valid) {
						break;
					}
				}
			}
		}
		return valid;
	}

	/**
	 * Called when an entity create event is received.
	 */
	protected void onCreate(ActivitiEvent event) {
		ActivitiEntityEvent entityEvent = (ActivitiEntityEvent) event;
		String entityEventClassName = entityEvent.getEntity().getClass()
				.getName();

		// if (WfConstants.EVENT_ENTITY_TASKENTITY.equals(entityEventClassName))
		// {
		// TaskEntity taskEntity = (TaskEntity) entityEvent.getEntity();
		// taskEntity = Context.getCommandContext().getTaskEntityManager()
		// .findTaskById(taskEntity.getId());
		// WfUniteRunTaskEntity wfUniteRunTask = coventWfUniteRunTask(
		// taskEntity, "insert");
		// wfUniteTaskManager.insertWfUniteRunTask(wfUniteRunTask);
		// }

		// 历史任务实体创建时
		if (WfConstants.EVENT_ENTITY_HISTORICTASKINSTANCEENTITY
				.equals(entityEventClassName)) {
			HistoricTaskInstanceEntity hisTaskEntity = (HistoricTaskInstanceEntity) entityEvent
					.getEntity();
			String taskId = hisTaskEntity.getId();
			// 获取当前任务详情更新到统一任务表
			hisTaskEntity = Context.getCommandContext()
					.getHistoricTaskInstanceEntityManager()
					.findHistoricTaskInstanceById(taskId);
			WfUniteHisTaskEntity wfUniteHisTask = coventWfUniteHisTask(
					hisTaskEntity, "update");
			managementService.executeCommand(new UpdateWfUniteHisTaskCmd(
					wfUniteHisTask));
		}
	}

	/**
	 * Called when an entity initialized event is received.
	 */
	protected void onInitialized(ActivitiEvent event) {
		// onUpdate(event);
	}

	/**
	 * Called when an entity delete event is received.
	 */
	protected void onDelete(ActivitiEvent event) {
		ActivitiEntityEvent entityEvent = (ActivitiEntityEvent) event;
		String entityEventClassName = entityEvent.getEntity().getClass()
				.getName();
		// 运行时任务删除时
		if (WfConstants.EVENT_ENTITY_TASKENTITY.equals(entityEventClassName)) {
			TaskEntity taskEntity = (TaskEntity) entityEvent.getEntity();
			String taskId = taskEntity.getId();
			// 查询出所有运行时任务的统一待办数据
			List<WfUniteRunTaskEntity> wfUniteRunTaskEntitys = managementService
					.executeCommand(new FindWfUniteRunTaskByTaskIdCmd(taskId));

			if (wfUniteRunTaskEntitys != null) {
				for (WfUniteRunTaskEntity wfUniteRunTaskEntity : wfUniteRunTaskEntitys) {
					// 查询出所有运行时任务的统一待办数据扩展数据
					List<WfUniteRunTaskExtendEntity> extList = managementService.executeCommand(new FindWfUniteRunTaskExtByIdCmd(wfUniteRunTaskEntity.getId()));
					if(extList!=null && !extList.isEmpty()){
						//  删除运行时任务的统一待办数据扩展数据
						managementService.executeCommand(new DeleteWfUniteRunTaskExtCmd(wfUniteRunTaskEntity.getId()));
					}
					
					// 将运行时任务的统一待办数据转换成历史任务统一待办数据
					WfUniteHisTaskEntity wfUniteHisTaskEntity = coventWfUniteHisTask(wfUniteRunTaskEntity);
					// 历史的记录主键采用与运行时相同的
					wfUniteHisTaskEntity.setId(wfUniteRunTaskEntity.getId());
					String assignee = taskEntity.getAssignee();
					if(assignee != null){
						wfUniteHisTaskEntity.setAssignee(assignee);
					}
					// 将转换好的历史任务数据插入到统一任务历史表
					managementService
							.executeCommand(new InsertWfUniteHisTaskCmd(
									wfUniteHisTaskEntity));
					
					if(extList!=null && !extList.isEmpty()){
						//  将扩展数据移到历史拓展数据，执行插入
						for(WfUniteRunTaskExtendEntity wfUniteRunTaskExtendEntity : extList){
							WfUniteHisTaskExtendEntity hisExt = new WfUniteHisTaskExtendEntity();
							hisExt.setId(wfUniteRunTaskExtendEntity.getId());
							hisExt.setUniteTaskHisId(wfUniteRunTaskExtendEntity.getUniteTaskId());
							hisExt.setExtFieldKey(wfUniteRunTaskExtendEntity.getExtFieldKey());
							hisExt.setExtFieldValue(wfUniteRunTaskExtendEntity.getExtFieldValue());
							hisExt.setExtFieldType(wfUniteRunTaskExtendEntity.getExtFieldType());
							hisExt.setUniteTaskHisId(wfUniteRunTaskExtendEntity.getUniteTaskId());
							managementService.executeCommand(new InsertWfUniteHisTaskExtCmd(hisExt));
						}
					}
					
				}
			}
			
			// 删除运行时任务的统一待办表的记录
			managementService.executeCommand(new DeleteWfUniteRunTaskCmd(taskId));
		}
		// 历史任务删除时
		if (WfConstants.EVENT_ENTITY_HISTORICTASKINSTANCEENTITY
				.equals(entityEventClassName)) {
			// 查出历史的统一待办数据，删除即可
			HistoricTaskInstanceEntity hisTaskEntity = (HistoricTaskInstanceEntity) entityEvent
					.getEntity();
			String taskId = hisTaskEntity.getId();
			managementService
					.executeCommand(new DeleteWfUniteRunTaskCmd(taskId));
		}
	}

	/**
	 * Called when an entity update event is received.
	 */
	protected void onSuspend(ActivitiEvent event) {
		ActivitiEntityEvent entityEvent = (ActivitiEntityEvent) event;
		String entityEventClassName = entityEvent.getEntity().getClass()
				.getName();
		if (WfConstants.EVENT_ENTITY_TASKENTITY.equals(entityEventClassName)) {
			TaskEntity taskEntity = (TaskEntity) entityEvent.getEntity();
			List<WfUniteRunTaskEntity> wfUniteRunTaskEntitys = managementService
					.executeCommand(new FindWfUniteRunTaskByTaskIdCmd(
							taskEntity.getId()));
			if (wfUniteRunTaskEntitys != null) {
				for (WfUniteRunTaskEntity wfUniteRunTaskEntity : wfUniteRunTaskEntitys) {
					wfUniteRunTaskEntity
							.setTaskState(WfConstants.WF_UNITE_TASK_TYPE_SUSPEND);
					wfUniteRunTaskEntity.setOwner(taskEntity.getOwner());
					managementService
							.executeCommand(new UpdateWfUniteRunTaskCmd(
									wfUniteRunTaskEntity));
				}
			}
		}
	}

	protected void onActivated(ActivitiEvent event) {
		ActivitiEntityEvent entityEvent = (ActivitiEntityEvent) event;
		String entityEventClassName = entityEvent.getEntity().getClass()
				.getName();
		if (WfConstants.EVENT_ENTITY_TASKENTITY.equals(entityEventClassName)) {
			TaskEntity taskEntity = (TaskEntity) entityEvent.getEntity();
			List<WfUniteRunTaskEntity> wfUniteRunTaskEntitys = managementService
					.executeCommand(new FindWfUniteRunTaskByTaskIdCmd(
							taskEntity.getId()));
			if (wfUniteRunTaskEntitys != null) {
				for (WfUniteRunTaskEntity wfUniteRunTaskEntity : wfUniteRunTaskEntitys) {
					wfUniteRunTaskEntity
							.setTaskState(WfConstants.WF_UNITE_TASK_TYPE_TODO);
					managementService.executeCommand(new UpdateWfUniteRunTaskCmd(
							wfUniteRunTaskEntity));
				}
			}
		}
	}

	protected void onUpdate(ActivitiEvent event) {
		ActivitiEntityEvent entityEvent = (ActivitiEntityEvent) event;
		String entityEventClassName = entityEvent.getEntity().getClass()
				.getName();
		if (WfConstants.EVENT_ENTITY_TASKENTITY.equals(entityEventClassName)) {
			TaskEntity taskEntity = (TaskEntity) entityEvent.getEntity();
			List<WfUniteRunTaskEntity> wfUniteRunTasks = coventWfUniteRunTask(
					taskEntity, "update");
			if(wfUniteRunTasks != null){
				for(WfUniteRunTaskEntity wfUniteRunTask : wfUniteRunTasks){
					managementService.executeCommand(new UpdateWfUniteRunTaskCmd(
							wfUniteRunTask));
				}
			}
		}
	}

	/**
	 * Called when an event is received, which is not a create, an update or
	 * delete.
	 */
	protected void onEntityEvent(ActivitiEvent event) {
		// Default implementation is a NO-OP
	}

	private List<WfUniteRunTaskEntity> coventWfUniteRunTask(TaskEntity taskEntity,
			String opType) {
		List<WfUniteRunTaskEntity> wfUnitRunTasks = new ArrayList<WfUniteRunTaskEntity>();
		String assignee = taskEntity.getAssignee();
		String category = taskEntity.getCategory();
//		Date createTime = taskEntity.getCreateTime();
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
		}
		if ("insert".equals(opType)) {
			if (wfBusinessData != null) {
				businessKey = wfBusinessData.getBusinessKey();
			}
		} else {
			businessKey = taskEntity.getProcessInstance().getBusinessKey();
			wfUnitRunTasks = managementService
					.executeCommand(new FindWfUniteRunTaskByTaskIdCmd(taskId));
		}

		if(wfUnitRunTasks != null){
			Iterator<WfUniteRunTaskEntity> itr = wfUnitRunTasks.iterator();
			while(itr.hasNext()){
				WfUniteRunTaskEntity wfUnitRunTask = itr.next();
				//签收时忽略非签收人的同一任务
				//if(assignee != null && !assignee.equals(wfUnitRunTask.getCandidate())){
//					managementService.executeCommand(new DeleteWfUniteRunTaskByPkCmd(wfUnitRunTask));
					//itr.remove();
					//continue;
				//}

				if (appId != null)
					wfUnitRunTask.setAppId(appId);
				wfUnitRunTask.setAssignee(assignee);
				if (businessKey != null)
					wfUnitRunTask.setBusinessKey(businessKey);
				wfUnitRunTask.setTaskId(taskId);
				wfUnitRunTask.setTaskState(type);
				wfUnitRunTask.setTaskDefKey(taskDefinitionKey);
				wfUnitRunTask.setTenantId(tenantId);
				if (category != null)
					wfUnitRunTask.setCategory(category);
				//wfUnitRunTask.setCreateTime(createTime);
				wfUnitRunTask.setDelegation(delegationState);
				//wfUnitRunTask.setDeleteReason("");
				wfUnitRunTask.setDescription(description);
				wfUnitRunTask.setDueDate(dueDate);
				wfUnitRunTask.setExecutionId(executionId);
				wfUnitRunTask.setFormKey("");
				if (moduleId != null)
					wfUnitRunTask.setModuleId(moduleId);
				if (moduleName != null)
					wfUnitRunTask.setModuleName(moduleName);
				if (name != null)
				wfUnitRunTask.setName(name);
				if (owner != null)
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
			}
		}
		return wfUnitRunTasks;
	}

	private WfUniteHisTaskEntity coventWfUniteHisTask(
			WfUniteRunTaskEntity taskEntity) {
		WfUniteHisTaskEntity wfUnitRunTask = new WfUniteHisTaskEntity();
		String assignee = taskEntity.getAssignee();
		String category = taskEntity.getCategory();
		Date createTime = taskEntity.getCreateTime();
		String description = taskEntity.getDescription();
		Date dueDate = taskEntity.getDueDate();
		String executionId = taskEntity.getExecutionId();
		String taskId = taskEntity.getTaskId();
		String name = taskEntity.getName();
		String owner = taskEntity.getOwner();
		String parentTaskId = taskEntity.getParentTaskId();
		int priority = taskEntity.getPriority();
		String processDefinitionId = taskEntity.getProcDefId();
		String processInstanceId = taskEntity.getProcInstId();
		String type = WfConstants.WF_UNITE_TASK_TYPE_DONE;
		String taskDefinitionKey = taskEntity.getTaskDefKey();
		String tenantId = taskEntity.getTenantId();
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
		String delegationState = taskEntity.getDelegation();
		appId = taskEntity.getAppId();
		moduleId = taskEntity.getModuleId();
		moduleName = taskEntity.getModuleName();
		remark1 = taskEntity.getRemark1();
		remark2 = taskEntity.getRemark2();
		remark3 = taskEntity.getRemark3();
		remark4 = taskEntity.getRemark4();
		remark5 = taskEntity.getRemark5();
		remark6 = taskEntity.getRemark6();
		remark7 = taskEntity.getRemark7();
		remark8 = taskEntity.getRemark8();
		remark9 = taskEntity.getRemark9();
		remark10 = taskEntity.getRemark10();
		businessKey = taskEntity.getBusinessKey();
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

	private WfUniteHisTaskEntity coventWfUniteHisTask(
			HistoricTaskInstanceEntity taskEntity, String opType) {
		WfUniteHisTaskEntity wfUnitRunTask = new WfUniteHisTaskEntity();
		String assignee = taskEntity.getAssignee();
		String category = taskEntity.getCategory();
		Date createTime = taskEntity.getEndTime();
		String deleteReason = taskEntity.getDeleteReason();
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
		String type = WfConstants.WF_UNITE_TASK_TYPE_DONE;
		String taskDefinitionKey = taskEntity.getTaskDefinitionKey();
		String tenantId = taskEntity.getTenantId();

		if ("insert".equals(opType)) {
		} else {
			managementService.executeCommand(new FindWfUniteHisTaskByTaskIdCmd(
					taskId));
		}

		if (taskId != null)
			wfUnitRunTask.setTaskId(taskId);
		wfUnitRunTask.setTaskState(type);
		if (assignee != null)
			wfUnitRunTask.setAssignee(assignee);
		if (taskDefinitionKey != null)
			wfUnitRunTask.setTaskDefKey(taskDefinitionKey);
		if (tenantId != null)
			wfUnitRunTask.setTenantId(tenantId);
		if (category != null)
			wfUnitRunTask.setCategory(category);
		if (createTime != null)
			wfUnitRunTask.setCreateTime(createTime);
		if (deleteReason != null)
			wfUnitRunTask.setDeleteReason(deleteReason);
		if (description != null)
			wfUnitRunTask.setDescription(description);
		if (dueDate != null)
			wfUnitRunTask.setDueDate(dueDate);
		if (executionId != null)
			wfUnitRunTask.setExecutionId(executionId);
		wfUnitRunTask.setFormKey("");
		if (name != null)
			wfUnitRunTask.setName(name);
		if (owner != null)
			wfUnitRunTask.setOwner(owner);
		if (parentTaskId != null)
			wfUnitRunTask.setParentTaskId(parentTaskId);
		wfUnitRunTask.setPriority(priority);
		if (processDefinitionId != null)
			wfUnitRunTask.setProcDefId(processDefinitionId);
		if (processInstanceId != null)
			wfUnitRunTask.setProcInstId(processInstanceId);
		return wfUnitRunTask;
	}

	public Set<Class<?>> getEntityClasses() {
		return entityClasses;
	}

	public void setEntityClasses(Set<Class<?>> entityClasses) {
		this.entityClasses = entityClasses;
	}

	public void setFailOnException(boolean failOnException) {
		this.failOnException = failOnException;
	}

	public ManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(ManagementService managementService) {
		this.managementService = managementService;
	}
}
