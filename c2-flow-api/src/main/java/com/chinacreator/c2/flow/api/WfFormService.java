package com.chinacreator.c2.flow.api;

import com.chinacreator.c2.flow.detail.WfFormData;
import com.chinacreator.c2.flow.detail.WfOperator;
import com.chinacreator.c2.flow.detail.WfProcessInstance;

/**
 * |工作流表单数据接口，主要是表单数据的查询与保存接口<br>
 * |可被本地和远程调用。<br>
 * |远程调用spring bean id=wfFormServiceRemote，注意区分大小写 <br>
 * |本地调用spring bean id=wfFormServiceLocal <br>
 * |统一返回值：200-操作成功、300-参数不正确、400-操作失败、404-操作对象不存在
 * 
 * @author minghua.guo
 * 
 */
public interface WfFormService {
	/**
	 * 获取启动流程的表单数据
	 * 
	 * @param processDefinitionId
	 *            ，必须参数 流程定义id
	 * @return
	 * @throws Exception
	 */
	public WfFormData getStartFormData(String processDefinitionId)
			throws Exception;

	/**
	 * 获取任务的表单数据
	 * 
	 * @param taskId
	 *            任务id，必须参数
	 * @return
	 * @throws Exception
	 */
	public WfFormData getTaskFormData(String taskId) throws Exception;

	/**
	 * 提交启动流程表单数据
	 * 
	 * @param wfOperator
	 *            操作者信息，必须参数
	 * @param data
	 *            表单数据 ，必须参数
	 * @param businessKey
	 *            业务ID
	 * @return WfProcessInstance 流程实例信息
	 * 
	 * @throws Exception
	 */
	public WfProcessInstance saveStartFormData(WfOperator wfOperator, WfFormData data, String businessKey)
			throws Exception;
	
	/**
	 * 提交任务表单数据
	 * 
	 * @param wfOperator
	 *            操作者信息，必须参数
	 * @param data
	 *            表单数据 ，必须参数
	 * @return 200 表示请求成功，并提交了表单数据。<br>
	 * 
	 * @throws Exception
	 */
	public String saveTaskFormData(WfOperator wfOperator, WfFormData data)
			throws Exception;
}
