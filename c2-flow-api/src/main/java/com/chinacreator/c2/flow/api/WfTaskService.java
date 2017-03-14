package com.chinacreator.c2.flow.api;

import java.util.Map;

import com.chinacreator.c2.flow.detail.WfOperator;
import com.chinacreator.c2.flow.detail.WfResult;
import com.chinacreator.c2.flow.detail.WfTask;
import com.chinacreator.c2.flow.detail.WfTaskAction;

/**
 * 
 * @author hushow
 * 
 */
public interface WfTaskService {
	
	/**
	 * 操作任务
	 * 
	 * @param wfOperator
	 *            操作者信息，必须参数
	 * @param taskId
	 *            任务id,必须参数
	 * @param action
	 *            操作动作,必须参数
	 * @param userToDelegateTo
	 *            action=WfTaskAction.DELEGATE时有用，用于指定代理用户id,必须参数
	 * @param variables
	 *            操作参数，可以为null
	 * @return 流程操作结果对象
	 * @throws Exception
	 */
	public WfResult operateTask(WfOperator wfOperator, String taskId,
			WfTaskAction action, String userToDelegateTo,
			Map<String, Object> variables) throws Exception;
	
	
	
	/**
	 * 根据任务id获取任务实例
	 * 
	 * @param taskId
	 *            任务id,必须参数
	 * @return
	 * @throws Exception
	 */
	public WfTask getTaskById(String taskId) throws Exception;
	
	
	/**
	 * 获取流程实例正在运行的任务id
	 * 
	 * @param processInstanceId
	 *            流程实例id,必须参数
	 * @return 任务id，多个逗号分隔
	 * @throws Exception
	 */
	public String getCurrentActiveTaskIds(String processInstanceId)
			throws Exception;
	
	
	/**
	 * 回退流程活动，注意:如果被驳回活动有多个来源活动,且没有制定目标驳回活动的时候,随机驳回
	 * @param wfOperator
	 *            操作者信息，必须参数
	 * @param wfBusinessData
	 * 			  流程业务数据信息，必须参数
	 * @param currenTaskId
	 *            当前任务id, 必须参数
	 * @param rejectMessage
	 *            驳回信息
	 * @param variables
	 *            流程参数， 可以为null
	 * @return 流程操作结果对象
	 * @throws Exception
	 */
	public WfResult reject(WfOperator wfOperator,String taskId,
			String rejectMessage, Map<String, Object> variables)throws Exception;
	
}
