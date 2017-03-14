package com.chinacreator.c2.flow.api;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.chinacreator.c2.flow.detail.ChooseGroup;
import com.chinacreator.c2.flow.detail.WfActivity;
import com.chinacreator.c2.flow.detail.WfAttachment;
import com.chinacreator.c2.flow.detail.WfComment;
import com.chinacreator.c2.flow.detail.WfIdentityLink;
import com.chinacreator.c2.flow.detail.WfOperator;
import com.chinacreator.c2.flow.detail.WfPageList;
import com.chinacreator.c2.flow.detail.WfProcessInstance;
import com.chinacreator.c2.flow.detail.WfProcessInstanceParam;
import com.chinacreator.c2.flow.detail.WfResult;
import com.chinacreator.c2.flow.detail.WfTask;
import com.chinacreator.c2.flow.detail.WfTaskAction;
import com.chinacreator.c2.flow.detail.WfTaskParam;
import com.chinacreator.c2.flow.detail.WfUniteTaskResult;
import com.chinacreator.c2.flow.detail.WfVariable;
import com.chinacreator.c2.flow.detail.WfVariable.VariableScope;
import com.chinacreator.c2.flow.util.WfUtils.OrderDirection;

/**
 * 
 * |工作流运行时服务接口，包括流程的启动、任务处理、挂起、激活、驳回、自由流等接口<br>
 * |可被本地和远程调用。<br>
 * |远程调用spring bean id=wfRuntimeServiceRemote，注意区分大小写 <br>
 * |本地调用spring bean id=wfRuntimeServiceLocal <br>
 * |统一返回值：200-操作成功、300-参数不正确、400-操作失败、404-操作对象不存在
 * 
 * @author minghua.guo
 * 
 */
public interface WfRuntimeService {

	/**
	 * 根据流程实例id获取流程实例
	 * 
	 * @param processInstanceId
	 *            流程实例id,必须参数
	 * @return 流程实例对象
	 * @throws Exception
	 */
	public WfProcessInstance getProcessInstanceById(String processInstanceId)
			throws Exception;

	/**
	 * 根据流程实例id删除流程实例
	 * 
	 * @param wfOperator
	 *            操作者信息，必须参数
	 * @param deleteReason
	 *            删除原因，可为空
	 * @param processInstanceId
	 *            流程实例id,必须参数
	 * @return 200-操作成功、300-参数不正确、400-操作失败、404-操作对象不存在
	 * @throws Exception
	 */
	public String deleteProcessInstancesById(WfOperator wfOperator,
			String deleteReason, String processInstanceId) throws Exception;

	/**
	 * 启动指定流程版本的流程实例
	 * 
	 * @param wfOperator
	 *            操作者信息，必须参数,其中businessData是统一任务所需业务数据参数，
	 *            如果要使用外围配置moduleId必须要传
	 * @param bussinessId
	 *            业务ID,确保实例和业务的关联关系
	 * @param processDefinitionId
	 *            流程定义ID，必须参数
	 * @param variables
	 *            流程参数， 可以为null
	 * @return 流程操作结果对象
	 * @throws Exception
	 */
	public WfResult startProcessInstanceById(WfOperator wfOperator,
			String bussinessId, String processDefinitionId,
			Map<String, Object> variables) throws Exception;

	/**
	 * 启动最新版本的流程实例
	 * 
	 * @param wfOperator
	 *            操作者信息，必须参数,其中businessData是统一任务所需业务数据参数，
	 *            如果要使用外围配置moduleId必须要传
	 * @param bussinessId
	 *            业务ID,确保实例和业务的关联关系
	 * @param processDefinitionKey
	 *            流程定义Key,必须参数
	 * @param variables
	 *            流程参数， 可以为null
	 * @return 流程操作结果对象
	 * @throws Exception
	 */
	public WfResult startProcessInstanceByKey(WfOperator wfOperator,
			String bussinessId, String processDefinitionKey,
			Map<String, Object> variables) throws Exception;

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
	 * 挂起流程实例
	 * 
	 * @param wfOperator
	 *            操作者信息，必须参数
	 * @param processInstanceId
	 *            流程实例id,必须参数
	 * @return 流程操作结果对象
	 * @throws Exception
	 */
	public WfResult suspendProcessInstance(WfOperator wfOperator,
			String processInstanceId) throws Exception;

	/**
	 * 激活流程实例
	 * 
	 * @param wfOperator
	 *            操作者信息，必须参数
	 * @param processInstanceId
	 *            流程实例id,必须参数
	 * @return 流程操作结果对象
	 * @throws Exception
	 */
	public WfResult activateProcessInstance(WfOperator wfOperator,
			String processInstanceId) throws Exception;

	/**
	 * 查询流程实例列表
	 * 
	 * @param wfProcessInstanceParam
	 *            流程实例查询参数,必须参数
	 * @return
	 * @throws Exception
	 */
	public WfPageList<WfProcessInstance, WfProcessInstanceParam> queryProcessInstances(
			WfProcessInstanceParam wfProcessInstanceParam) throws Exception;

	/**
	 * 获得流程实例的流程图
	 * 
	 * @param processInstanceId
	 *            流程实例id,必须参数
	 * @return
	 * @throws Exception
	 */
	public InputStream getProcessInstanceDiagram(String processInstanceId)
			throws Exception;

	/**
	 * 获取流程实例的参与者
	 * 
	 * @param processInstanceId
	 *            流程实例id,必须参数
	 * @return
	 * @throws Exception
	 */
	public List<WfIdentityLink> getProcessInstanceCandidates(
			String processInstanceId) throws Exception;

	/**
	 * 为流程实例添加一个参与者
	 * 
	 * @param wfOperator
	 *            操作者信息,必须参数
	 * @param processInstanceId
	 *            流程实例id,必须参数
	 * @param identityLinkType
	 *            候选者类型：WfConstants.WF_IDENTITYLINKTYPE_USERS 或
	 *            WfConstants.WF_IDENTITYLINKTYPE_GROUPS，依赖IdentityLink的类型,必须参数。
	 * @param identityId
	 *            候选创建者的身份的userId 或 groupId,必须参数。
	 * @return 200-操作成功、300-参数不正确、400-操作失败、404-操作对象不存在
	 * @throws Exception
	 */
	public String addProcessInstanceCandidate(WfOperator wfOperator,
			String processInstanceId, String identityLinkType, String identityId)
			throws Exception;

	/**
	 * 删除一个流程实例的参与者
	 * 
	 * @param wfOperator
	 *            操作者信息,必须参数
	 * @param processInstanceId
	 *            流程实例id,必须参数
	 * @param identityLinkType
	 *            候选者类型：WfConstants.WF_IDENTITYLINKTYPE_USERS 或
	 *            WfConstants.WF_IDENTITYLINKTYPE_GROUPS，依赖IdentityLink的类型,必须参数。
	 * @param identityId
	 *            候选创建者的身份的userId 或 groupId,必须参数。
	 * @return 200表示找到了流程实例，并删除了IdentityLink；<br>
	 *         400标识找不到流程实例，删除IdentityLink失败
	 * @throws Exception
	 */
	public String deleteProcessInstanceCandidate(WfOperator wfOperator,
			String processInstanceId, String identityLinkType, String identityId)
			throws Exception;

	/**
	 * 列出流程实例的变量
	 * 
	 * @param processInstanceId
	 *            流程实例id,必须参数
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getProcessInstanceVariables(
			String processInstanceId) throws Exception;

	/**
	 * 获得流程实例的一个变量
	 * 
	 * @param processInstanceId
	 *            流程实例id,必须参数
	 * @param variableName
	 *            流程变量名,必须参数
	 * @return
	 * @throws Exception
	 */
	public WfVariable getProcessInstanceVariableByName(
			String processInstanceId, String variableName) throws Exception;

	/**
	 * 创建流程实例变量
	 * 
	 * @param wfOperator
	 *            操作者信息,必须参数
	 * @param processInstanceId
	 *            流程实例id,必须参数
	 * @param wfVariable
	 *            流程变量,必须参数
	 * @return 200 表示找到了流程实例，并创建了变量。<br>
	 *         300 表示请求体不完整，或包含非法值。 <br>
	 *         404表示找不到请求的流程实例 。 <br>
	 * @throws Exception
	 */
	public String addVariableForProcessInstance(WfOperator wfOperator,
			String processInstanceId, WfVariable wfVariable) throws Exception;

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
	 * 查询任务列表
	 * 
	 * @param params
	 *            任务查询参数,必须参数
	 * @return
	 * @throws Exception
	 */
	public WfPageList<WfTask, WfTaskParam> queryTasks(WfTaskParam params)
			throws Exception;

	/**
	 * 更新任务
	 * 
	 * @param wfTask
	 *            任务实例,必须参数
	 * @return 200 表示成功更新了任务。<br>
	 *         404 表示找不到任务。<br>
	 * 
	 * @throws Exception
	 */
	public String updateTask(WfTask wfTask) throws Exception;

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
	 * 删除任务
	 * 
	 * @param wfOperator
	 *            操作者,必须参数
	 * @param taskId
	 *            任务id,必须参数
	 * @param cascadeHistory
	 *            删除任务时是否删除对应的任务历史（如果存在）,可以为null。
	 * @param deleteReason
	 *            删除任务的原因。cascadeHistory为true时，忽略此参数。
	 * @return 200 表示找到任务，并成功删除。<br>
	 *         400 表示任务删除失败。 <br>
	 *         404 表示找不到任务。
	 * 
	 * @throws Exception
	 */
	public String deleteTask(WfOperator wfOperator, String taskId,
			Boolean cascadeHistory, String deleteReason) throws Exception;

	/**
	 * 获取任务对应域下的变量
	 * 
	 * @param taskId
	 *            变量对应的任务id,必须参数。
	 * @param scope
	 *            返回的变量作用域。如果为VariableScope.LOCAL，只返回任务本身的变量。<br>
	 *            如果为 VariableScope.GLOBAL，只返回任务上级分支的变量。<br>
	 *            如果不指定这个变量(null)，会返回所有局部和全局的变量。
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getTaskVariables(String taskId,
			VariableScope scope) throws Exception;

	/**
	 * 根据变量名获取任务对应域下的变量
	 * 
	 * @param taskId
	 *            获取变量对应的任务id,必须参数。
	 * @param variableName
	 *            获取变量对应的名称,必须参数。
	 * @param scope返回的变量作用域
	 *            。如果为VariableScope.LOCAL，只返回任务本身的变量。如果为
	 *            VariableScope.GLOBAL，只返回任务上级分支的变量。如果不指定这个变量，会返回所有局部和全局的变量,
	 *            必须参数。
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getTaskVariableByName(String taskId,
			String variableName, VariableScope scope) throws Exception;

	/**
	 * 创建任务变量
	 * 
	 * @param wfOperator
	 *            操作者信息，必须参数
	 * @param taskId
	 *            任务id, 必须参数
	 * @param wfVariable
	 *            任务变量, 必须参数
	 * @return 200 表示找到了任务和变量，并更新了变量。<br>
	 *         404表示找不到请求的任务，或找不到给定名称的任务变量。
	 * @throws Exception
	 */
	public String addTaskVariable(WfOperator wfOperator, String taskId,
			WfVariable wfVariable) throws Exception;

	/**
	 * 更新一个任务变量
	 * 
	 * @param wfOperator
	 *            操作者信息，必须参数
	 * @param taskId
	 *            任务id, 必须参数
	 * @param wfVariable
	 *            变量, 必须参数
	 * @return 200 表示找到了任务和变量，并更新了变量。<br>
	 *         404表示找不到请求的任务，或找不到给定名称的任务变量。
	 * 
	 * @throws Exception
	 */
	public String updateTaskVariable(WfOperator wfOperator, String taskId,
			WfVariable wfVariable) throws Exception;

	/**
	 * 删除任务变量
	 * 
	 * @param wfOperator
	 *            操作者信息，必须参数
	 * @param taskId
	 *            任务id, 必须参数
	 * @param variableName
	 *            变量名, 必须参数
	 * @param scope
	 *            作用域，默认是VariableScope.GLOBAL
	 * @return 200 表示找到了任务变量，并成功删除。<br>
	 *         404 表示找不到任务，或任务不包含指定名称的变量。
	 * 
	 * @throws Exception
	 */
	public String deleteTaskVariable(WfOperator wfOperator, String taskId,
			String variableName, VariableScope scope) throws Exception;

	/**
	 * 删除任务的所有局部变量
	 * 
	 * @param wfOperator
	 *            操作者信息，必须参数
	 * @param taskId
	 *            任务id, 必须参数
	 * @return 200 表示找到了任务变量，并成功删除。<br>
	 *         404 表示找不到任务，或任务不包含指定名称的变量。
	 * @throws Exception
	 */
	public String deleteLocalTaskVariable(WfOperator wfOperator, String taskId)
			throws Exception;

	/**
	 * 获取任务的参与者
	 * 
	 * @param taskId
	 *            任务id, 必须参数
	 * @return
	 * @throws Exception
	 */
	public List<WfIdentityLink> getTaskCandidates(String taskId)
			throws Exception;

	/**
	 * 根据类型获取任务的参与者
	 * 
	 * @param taskId
	 *            任务id, 必须参数
	 * @param type
	 *            任务类型，有WfConstants.WF_IDENTITYLINKTYPE_GROUPS-用户组和WfConstants.
	 *            WF_IDENTITYLINKTYPE_USERS-用户两种类型, 必须参数
	 * @return
	 * @throws Exception
	 */
	public List<WfIdentityLink> getTaskCandidatesByType(String taskId,
			String identityLinkType) throws Exception;

	/**
	 * 为任务添加一个参与者，这里任务委托不能生效
	 * 
	 * @param wfOperator
	 *            操作者信息，必须参数
	 * @param taskId
	 *            任务id, 必须参数
	 * @param identityLinkType
	 *            候选者类型：WfConstants.WF_IDENTITYLINKTYPE_USERS 或
	 *            WfConstants.WF_IDENTITYLINKTYPE_GROUPS，依赖IdentityLink的类型,
	 *            必须参数。
	 * @param identityId
	 *            候选创建者的身份的userId 或 groupId, 必须参数。
	 * @return 200 表示添加候选人成功。<br>
	 *         404 表示找不到任务。
	 * @throws Exception
	 */
	public String addTaskCandidate(WfOperator wfOperator, String taskId,
			String identityLinkType, String identityId) throws Exception;

	/**
	 * 删除一个任务的参与者
	 * 
	 * @param wfOperator
	 *            操作者信息，必须参数
	 * @param taskId
	 *            任务id, 必须参数
	 * @param identityLinkType
	 *            候选者类型：WfConstants.WF_IDENTITYLINKTYPE_USERS 或
	 *            WfConstants.WF_IDENTITYLINKTYPE_GROUPS，依赖IdentityLink的类型,
	 *            必须参数。
	 * @param identityId
	 *            候选创建者的身份的userId 或 groupId, 必须参数。
	 * @return 200表示成功删除了IdentityLink；<br>
	 *         404标识找不到任务，删除IdentityLink失败
	 * @throws Exception
	 */
	public String deleteTaskCandidate(WfOperator wfOperator, String taskId,
			String identityLinkType, String identityId) throws Exception;

	/**
	 * 为任务创建一个评论
	 * 
	 * @param wfOperator
	 *            操作者信息，必须参数
	 * @param wfComment
	 *            评论, 必须参数，其中taskId、processInstanceId、message必填
	 * @return 200表示成功删除了IdentityLink；<br>
	 *         404标识找不到任务，删除IdentityLink失败
	 * @throws Exception
	 */
	public String addTaskComment(WfOperator wfOperator, WfComment wfComment)
			throws Exception;

	/**
	 * 获得任务的所有评论
	 * 
	 * @param taskId
	 *            任务id, 必须参数
	 * @return
	 * @throws Exception
	 */
	public List<WfComment> getTaskComments(String taskId) throws Exception;

	/**
	 * 获得任务的一个评论
	 * 
	 * @param commentId
	 *            评论id, 必须参数
	 * @return
	 * @throws Exception
	 */
	public WfComment getTaskCommentById(String commentId) throws Exception;

	/**
	 * 删除任务的一个评论
	 * 
	 * @param wfOperator
	 *            操作者信息，必须参数
	 * @param commentId
	 *            评论id, 必须参数
	 * @return 200 表示找到了任务和评论，并删除了评论。<br>
	 *         404 表示找不到任务，或任务不包含id的评论。
	 * 
	 * @throws Exception
	 */
	public String deleteTaskComment(WfOperator wfOperator, String commentId)
			throws Exception;

	/**
	 * 获得任务的所有事件
	 * 
	 * @param taskId
	 *            任务id, 必须参数
	 * @return
	 * @throws Exception
	 */
	public List<WfComment> getTaskEvents(String taskId) throws Exception;

	/**
	 * 获得任务的一个事件
	 * 
	 * @param eventId
	 *            事件id, 必须参数
	 * @return
	 * @throws Exception
	 */
	public WfComment getTaskEventById(String eventId) throws Exception;

	/**
	 * 为任务创建一个附件
	 * 
	 * @param wfOperator
	 *            操作者信息，必须参数
	 * @param attachment
	 *            附件, 必须参数
	 * @return 200 表示添加任务附件成功。<br>
	 *         400表示添加任务附件失败。
	 * @throws Exception
	 */
	public String addTaskAttachment(WfOperator wfOperator,
			WfAttachment attachment) throws Exception;

	/**
	 * 获得任务的所有附件
	 * 
	 * @param taskId
	 *            任务id, 必须参数
	 * @return
	 * @throws Exception
	 */
	public List<WfAttachment> getTaskAttachments(String taskId)
			throws Exception;

	/**
	 * 获取任务的一个附件
	 * 
	 * @param attachmentId
	 *            附件id, 必须参数
	 * @return
	 * @throws Exception
	 */
	public WfAttachment getTaskAttachmentById(String attachmentId)
			throws Exception;

	/**
	 * 获取当前任务的直接后续用户任务定义
	 * 
	 * @param taskId
	 *            任务id（注意是实例不是定义）
	 * @return
	 * @throws Exception
	 */
	public List<WfActivity> getNextUserTaskDefByTaskId(String taskId)
			throws Exception;

	/**
	 * 删除任务的一个附件
	 * 
	 * @param wfOperator
	 *            操作者信息，必须参数
	 * @param attachmentId
	 *            附件id, 必须参数
	 * @return 200 表示找到了任务和附件，并删除了附件。 <br>
	 *         404 表示找不到任务，或任务不包含对应id的附件。
	 * 
	 * @throws Exception
	 */
	public String deleteTaskAttachmentById(WfOperator wfOperator,
			String attachmentId) throws Exception;

	/**
	 * 回退流程活动，注意:如果被驳回活动有多个来源活动,且没有制定目标驳回活动的时候,随机驳回
	 * 
	 * @param wfOperator
	 *            操作者信息，必须参数
	 * @param currenTaskId
	 *            当前任务id, 必须参数
	 * @param rejectMessage
	 *            驳回信息
	 * @param variables
	 *            流程参数， 可以为null
	 * @return 流程操作结果对象
	 * @throws Exception
	 */
	public WfResult reject(WfOperator wfOperator, String currenTaskId,
			String rejectMessage, Map<String, Object> variables)
			throws Exception;

	/**
	 * 自由流控制
	 * 
	 * @param wfOperator
	 *            操作者信息，必须参数
	 * @param isStart
	 *            是否启动流程, 必须参数
	 * @param bussinessKey
	 *            业务id，启动流程时需要
	 * @param processDefinitionId
	 *            流程定义id，启动流程时需要
	 * @param currenTaskId
	 *            当前任务id
	 * @param destTaskDefinitionKey
	 *            目标任务定义id, 必须参数
	 * @param useHisAssignee
	 *            是否使用历史负责人作为自由流向的任务执行人
	 * @param variables
	 *            流程参数， 可以为null
	 * @return 流程操作结果对象
	 * @throws Exception
	 */
	public WfResult goAnyWhere(WfOperator wfOperator, boolean isStart,
			String bussinessKey, String processDefinitionId,
			String currenTaskId, String destTaskDefinitionKey,
			boolean useHisAssignee, Map<String, Object> variables)
			throws Exception;
	
	
	/**
	 * 自由流控制
	 * 
	 * @param wfOperator
	 *            操作者信息，必须参数
	 * @param processInstanceId
	 *            流程实例id
	 * @param destTaskDefinitionKey
	 *            目标任务定义id, 必须参数
	 * @param variables
	 *            流程参数， 可以为null
	 * @return 流程操作结果对象
	 * @throws Exception
	 */
	public WfResult goAnyWhere(WfOperator wfOperator,String processInstanceId,
						String destTaskDefinitionKey,Map<String,Object> variables) throws Exception;

	/**
	 * 查询统一任务配置信息
	 * 
	 * @param appId
	 *            应用id
	 * @param tenantId
	 *            租户id
	 * @param engineName
	 *            引擎名称
	 * @param taskList
	 *            任务列表todolist-待办；donelist-已办；suspendlist-挂起
	 * @return
	 * @throws Exception
	 */
	public WfUniteTaskResult queryWfUniteConfig(String appId, String tenantId,
			String engineName, String taskList) throws Exception;

	/**
	 * 查询统一待办任务列表
	 * 
	 * @param parameters
	 *            查询条件
	 * @param firstResult
	 *            分页：起始记录数
	 * @param maxResults
	 *            分页：每页最大记录数
	 * @return
	 * @throws Exception
	 */
	public WfUniteTaskResult queryWfUniteRunTask(
			Map<String, Object> parameters, int firstResult, int maxResults)
			throws Exception;
	

	/**
	 * 查询统一已办任务列表
	 * 
	 * @param parameters
	 *            查询条件
	 * @param firstResult
	 *            分页：起始记录数
	 * @param maxResults
	 *            分页：每页最大记录数
	 * @return
	 * @throws Exception
	 */
	public WfUniteTaskResult queryWfUniteHisTask(
			Map<String, Object> parameters, int firstResult, int maxResults)
			throws Exception;
	
	/**
	 * 处理任务（为了C2封装的临时接口，解决枚举不支持作为参数的问题，以后要删掉）
	 * 
	 * @param wfOperator
	 *            操作者信息，必须参数
	 * @param taskId
	 *            任务id,必须参数
	 * @param action
	 *            操作动作,必须参数（String类型）
	 * @param userToDelegateTo
	 *            action=WfTaskAction.DELEGATE时有用，用于指定代理用户id,必须参数
	 * @param variables
	 *            操作参数，可以为null
	 * @return 流程操作结果对象
	 * @throws Exception
	 */
	public WfResult operateTaskTmp(WfOperator wfOperator, String taskId,
			String action, String userToDelegateTo,
			Map<String, Object> variables) throws Exception;
	
	
	/**
	 * 获取executionId的活动集合
	 * @param executionId
	 *          执行ID，不能为空
	 * @return 活动ID集合
	 * @throws Exception
	 */
	public List<String> getActiveActivityIds(String executionId) throws Exception;
	
	
	/**
	 * 查询某用户统一待办任务列表
	 * 注：查询所属组待办需要传chooseGroupList参数
	 * @param userId
	 *            用户id 
	 * @param chooseGroupList
	 *            用户所属组 
	 *            示例: List<ChooseGroup> chooseGroupList=WorkflowUtils.getGroupsByUserId(userId);
	 * @param parameters
	 *            其它查询条件
	 * @param firstResult
	 *            分页：起始记录数
	 * @param maxResults
	 *            分页：每页最大记录数
	 * @param orderBys
	 * 			  排序参数，为空默认CREATE_TIME desc
	 * @return
	 * @throws Exception
	 */
	public WfUniteTaskResult queryWfUniteRunTask(String userId,List<ChooseGroup> chooseGroupList,Map<String, Object> parameters,
			int firstResult, int maxResults,Map<String,OrderDirection> orderBys)
			throws Exception;
	
	
	
	/**
	 * 查询统一已办任务列表
	 * 注：查询所属组待办需要传chooseGroupList参数
	 * @param userId
	 *            用户id 
	 * @param chooseGroupList
	 *            用户所属组 
	 *            示例: List<ChooseGroup> chooseGroupList=WorkflowUtils.getGroupsByUserId(userId);
	 * @param parameters
	 *            查询条件
	 * @param firstResult
	 *            分页：起始记录数
	 * @param maxResults
	 *            分页：每页最大记录数
	 * @param orderBys
	 * 			  排序参数，为空默认CREATE_TIME desc
	 * @return
	 * @throws Exception
	 */
	public WfUniteTaskResult queryWfUniteHisTask(String userId,List<ChooseGroup> chooseGroupList, Map<String, Object> parameters,
			int firstResult, int maxResults,Map<String,OrderDirection> orderBys) throws Exception ;
}
