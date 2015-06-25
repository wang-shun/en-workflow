package com.chinacreator.c2.flow.api;

import java.util.List;
import java.util.Map;

import com.chinacreator.c2.flow.detail.WfComment;
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
import com.chinacreator.c2.flow.detail.WfVariable;
import com.chinacreator.c2.flow.detail.WfVariable.VariableScope;
import com.chinacreator.c2.flow.detail.WfVariableParam;

/**
 * |工作流历史数据接口，包括流程实例、任务、变量等操作与查询接口<br>
 * |可被本地和远程调用。<br>
 * |远程调用spring bean id=wfHistoryServiceRemote，注意区分大小写 <br>
 * |本地调用spring bean id=wfHistoryServiceLocal <br>
 * |统一返回值：200-操作成功、300-参数不正确、400-操作失败、404-操作对象不存在
 * 
 * @author minghua.guo
 * 
 */
public interface WfHistoryService {
	/**
	 * 获取一个历史流程实例对象
	 * 
	 * @param processInstanceId
	 *            流程实例id，必须参数
	 * @return
	 * @throws Exception
	 */
	public WfHistoricProcessInstance getHistoricProcessInstanceById(
			String processInstanceId) throws Exception;

	/**
	 * 查询历史流程实例列表
	 * 
	 * @param params
	 *            查询条件，必须参数
	 * @return
	 * @throws Exception
	 */
	public WfPageList<WfHistoricProcessInstance, WfHistoricProcessInstanceParam> queryHistoricProcessInstances(
			WfHistoricProcessInstanceParam params) throws Exception;

	/**
	 * 删除历史流程实例
	 * 
	 * @param processInstanceId
	 *            流程实例id，必须参数
	 * @return 200 表示成功删除了历史流程实例。<br>
	 *         404 表示找不到历史流程实例。
	 * @throws Exception
	 */
	public String deleteHistoricProcessInstance(String processInstanceId)
			throws Exception;

	/**
	 * 获取历史流程实例的参与者
	 * 
	 * @param processInstanceId
	 *            流程实例id
	 * @return List<{@link WfIdentityLink}>
	 * @throws Exception
	 */
	public List<WfIdentityLink> getHistoricProcessInstanceCandidates(
			String processInstanceId) throws Exception;

	/**
	 * 列出历史流程实例的变量
	 * 
	 * @param processInstanceId
	 *            流程实例id
	 * @return List<{@link WfVariable}>
	 * @throws Exception
	 */
	public List<WfHistoricVariableInstance> getHistoricProcessInstanceVariables(
			String processInstanceId) throws Exception;

	/**
	 * 为历史流程实例创建一个评论
	 * 
	 * @param wfOperator
	 *            操作者信息
	 * @param processInstanceId
	 *            流程实例id
	 * @param wfComment
	 *            评论
	 * @return 
	 * @throws Exception
	 */
	public String addHistoricProcessInstanceComment(WfOperator wfOperator,
			String processInstanceId, WfComment wfComment) throws Exception;

	/**
	 * 获取历史流程实例的所有评论
	 * 
	 * @param processInstanceId
	 *            流程实例id
	 * @return 
	 * @throws Exception
	 */
	public List<WfComment> getAllHistoricProcessInstanceComments(
			String processInstanceId) throws Exception;

	/**
	 * 获取历史流程实例的一条评论
	 * 
	 * @param processInstanceId
	 *            流程实例id
	 * @param commentId
	 *            评论id
	 * @return 
	 * @throws Exception
	 */
	public WfComment getHistoricProcessInstanceComment(
			String processInstanceId, String commentId) throws Exception;

	/**
	 * 删除历史流程实例的一条评论
	 * 
	 * @param wfOperator
	 *            操作者信息
	 * @param processInstanceId
	 *            流程实例id
	 * @param commentId
	 *            评论id
	 * @return 204 表示找到了历史流程实例，并删除了评论。<br>
	 *         404 表示找不到请求的历史流程实例，或历史流程实例不包含指定id的评论。
	 * @throws Exception
	 */
	public String deleteHistoricProcessInstanceComment(WfOperator wfOperator,
			String processInstanceId, String commentId) throws Exception;

	/**
	 * 获取一个历史任务
	 * 
	 * @param taskId
	 *            任务id
	 * @return 
	 * @throws Exception
	 */
	public WfHistoricTask getHistoricTaskById(String taskId) throws Exception;

	/**
	 * 查询历史任务实例列表
	 * 
	 * @param params
	 *            历史任务实例参数
	 * @return 
	 * @throws Exception
	 */
	public WfPageList<WfHistoricTask, WfHistoricTaskParam> queryHistoricTasks(
			WfHistoricTaskParam params) throws Exception;

	/**
	 * 删除一条历史任务
	 * 
	 * @param wfOperator
	 *            操作者
	 * @param taskId
	 *            任务id
	 * @return 200 表示删除了历史任务实例。<br>
	 *         404 表示找不到历史任务实例。
	 * @throws Exception
	 */
	public String deleteHistoricTask(WfOperator wfOperator, String taskId)
			throws Exception;

	/**
	 * 获得历史任务实例的IdentityLink
	 * 
	 * @param taskId
	 *            任务id
	 * @return 
	 * @throws Exception
	 */
	public List<WfIdentityLink> getHistoricTaskCandidates(String taskId)
			throws Exception;

	/**
	 * 获取历史任务的变量
	 * 
	 * @param taskId
	 *            变量对应的任务id。
	 * @param scope
	 *            返回的变量作用域。如果为LOCAL，只返回任务本身的变量。如果为
	 *            GLOBAL，只返回任务上级分支的变量。如果不指定这个变量，会返回所有局部和全局的变量。
	 * @return 
	 * @throws Exception
	 */
	public Map<String, Object> getHistoricTaskVariables(String taskId,
			VariableScope scope) throws Exception;

	/**
	 * 获取历史任务的某个变量
	 * 
	 * @param taskId
	 *            获取变量对应的任务id。
	 * @param variableName
	 *            获取变量对应的名称。
	 * @param scope返回的变量作用域
	 *            。如果为LOCAL，只返回任务本身的变量。如果为
	 *            GLOBAL，只返回任务上级分支的变量。如果不指定这个变量，会返回所有局部和全局的变量。
	 * @return 
	 * @throws Exception
	 */
	public Object getHistoricTaskVariableByName(String taskId,
			String variableName, VariableScope scope) throws Exception;

	/**
	 * 查询历史变量实例
	 * 
	 * @param params
	 *            查询参数
	 * @return 
	 * @throws Exception
	 */
	public WfPageList<Map<String, Object>, WfVariableParam> queryHistoricVariables(
			WfVariableParam params) throws Exception;

	/**
	 * 查询历史细节
	 * 
	 * @param params
	 *            查询条件
	 * @return 
	 * @throws Exception
	 */
	public WfPageList<WfDetail, WfDetailParam> queryHistoricDetails(
			WfDetailParam params) throws Exception;
}
