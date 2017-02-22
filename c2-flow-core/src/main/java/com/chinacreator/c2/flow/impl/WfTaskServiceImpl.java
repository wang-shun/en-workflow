package com.chinacreator.c2.flow.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.HistoricTaskInstanceQueryImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.task.Task;

import com.chinacreator.c2.flow.api.WfTaskService;
import com.chinacreator.c2.flow.cmd.JumpActivityCmd;
import com.chinacreator.c2.flow.detail.WfConstants;
import com.chinacreator.c2.flow.detail.WfOperator;
import com.chinacreator.c2.flow.detail.WfResult;
import com.chinacreator.c2.flow.detail.WfTask;
import com.chinacreator.c2.flow.detail.WfTaskAction;
import com.chinacreator.c2.flow.util.LoggerManager;
import com.chinacreator.c2.flow.util.LoggerManager.LoggerType;

public class WfTaskServiceImpl implements WfTaskService{
	
	private IdentityService identityService;
	private TaskService taskService;
	private RepositoryService repositoryService;
	private HistoryService    historyService;
	private ManagementService managementService;
	
	
	
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
//			if (variables != null){
//				variables.put(WfConstants.WF_BUSINESS_DATA_KEY,
//						wfOperator.getBusinessData());
//				for(String key : variables.keySet()){
//					if("".equals(variables.get(key))){
//						variables.put(key, null);
//					}
//				}
//			}
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
				result.setNextTaskId(this.getCurrentActiveTaskIds(processInstanceId));
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
			throw e;
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
	public WfResult reject(WfOperator wfOperator,String taskId,
			String rejectMessage, Map<String, Object> variables)throws Exception {
		
		WfResult result = new WfResult();
		String userId = wfOperator.getUserId();
		try {
			// 用来设置操作流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
			identityService.setAuthenticatedUserId(userId);
			if(variables == null){
				variables = new HashMap<String, Object>();
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
			//if(!taskEntity.getExecutionId().equals(taskEntity.getProcessInstanceId())) historyQuery.executionId(taskEntity.getExecutionId());
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
			throw e;
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



	public IdentityService getIdentityService() {
		return identityService;
	}



	public void setIdentityService(IdentityService identityService) {
		this.identityService = identityService;
	}



	public TaskService getTaskService() {
		return taskService;
	}



	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}



	public RepositoryService getRepositoryService() {
		return repositoryService;
	}



	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}



	public HistoryService getHistoryService() {
		return historyService;
	}



	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}



	public ManagementService getManagementService() {
		return managementService;
	}



	public void setManagementService(ManagementService managementService) {
		this.managementService = managementService;
	}
	
	
}
