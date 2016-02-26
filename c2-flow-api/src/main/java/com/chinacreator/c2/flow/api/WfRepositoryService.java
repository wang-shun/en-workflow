package com.chinacreator.c2.flow.api;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.chinacreator.c2.flow.detail.WfActivity;
import com.chinacreator.c2.flow.detail.WfDeployment;
import com.chinacreator.c2.flow.detail.WfDeploymentParam;
import com.chinacreator.c2.flow.detail.WfIdentityLink;
import com.chinacreator.c2.flow.detail.WfModel;
import com.chinacreator.c2.flow.detail.WfModelParam;
import com.chinacreator.c2.flow.detail.WfOperator;
import com.chinacreator.c2.flow.detail.WfPageList;
import com.chinacreator.c2.flow.detail.WfProcessDefinition;
import com.chinacreator.c2.flow.detail.WfProcessDefinitionParam;
import com.chinacreator.c2.flow.detail.WfResource;

/**
 * |流程定义、流程模型处理与查询接口，包括流程定义的部署。可被本地和远程调用。<br>
 * |远程调用spring bean id=wfRepositoryServiceRemote，注意区分大小写 <br>
 * |本地调用spring bean id=wfRepositoryServiceLocal <br>
 * 
 * 统一返回值：200-操作成功、300-参数不正确、400-操作失败、404-操作对象不存在
 * 
 * @author minghua.guo
 * @version 1.0
 */
public interface WfRepositoryService {
	/**
	 * 查询流程部署
	 * 
	 * @param WfDeploymentParam
	 *            查询部署参数，必须参数
	 * @return
	 * @throws Exception
	 */
	public WfPageList<WfDeployment, WfDeploymentParam> queryDeployments(
			WfDeploymentParam wfDeploymentParam) throws Exception;

	/**
	 * 获得一个部署
	 * 
	 * @param deploymentId
	 *            部署id，必须参数
	 * @return
	 * @throws Exception
	 */
	public WfDeployment getDeploymentById(String deploymentId) throws Exception;

	/**
	 * 部署流程引擎classpath路径下的流程，只适用于本地部署
	 * 
	 * @param wfOperator
	 *            当前操作者信息，必须参数
	 * @param name
	 *            部署名称,可以为空
	 * @param category
	 *            部署分类,可以为空
	 * @param resourceClassPath
	 *            部署流程文件相对classpath路径，必须参数
	 * @return 200-操作成功<br>
	 *         400-操作失败
	 * @throws Exception
	 */
	public String deployDiagramClassPath(WfOperator wfOperator, String name,
			String category, String resourceClassPath) throws Exception;

	/**
	 * 部署流程文件内容
	 * 
	 * @param wfOperator
	 *            当前操作者信息，必须参数
	 * @param name
	 *            部署名称,可以为空
	 * @param category
	 *            部署分类,可以为空
	 * @param resourceName
	 *            部署流程资源名称，必须参数
	 * @param resourceContent
	 *            部署流程内容，读取的bpmn文件的xml内容，必须参数
	 * @return 200-操作成功<br>
	 *         400-操作失败
	 * @throws Exception
	 */
	public String deployDiagramContent(WfOperator wfOperator, String name,
			String category, String resourceName, String resourceContent)
			throws Exception;

	/**
	 * 部署流程包（zip）
	 * 
	 * @param wfOperator
	 *            当前操作者信息，必须参数
	 * @param name
	 *            部署名称,可以为空
	 * @param category
	 *            部署分类,可以为空
	 * @param inputStream
	 *            zip文件输入流，必须参数
	 * @return 200-操作成功<br>
	 *         400-操作失败
	 * @throws Exception
	 */
	public String deployDiagramZip(WfOperator wfOperator, String name,
			String category, InputStream inputStream) throws Exception;

	/**
	 * 根据部署id删除部署
	 * 
	 * @param wfOperator
	 *            当前操作者信息，必须参数
	 * @param casecade
	 *            是否级联删除，必须参数
	 * @param deploymentId
	 *            部署ID，必须参数
	 * @return 200 表示找到了部署，并删除成功；<br>
	 *         404 表示找不到请求的部署
	 * @throws Exception
	 */
	public String deleteDeploymentsById(WfOperator wfOperator,
			boolean casecade, String deploymentId) throws Exception;

	/**
	 * 根据流程部署id查询相关资源
	 * 
	 * @param deploymentId
	 *            部署id，必须参数
	 * @return
	 * @throws Exception
	 */
	public List<WfResource> queryResourcesByDeploymentId(String deploymentId)
			throws Exception;

	/**
	 * 获取部署资源
	 * 
	 * @param deploymentId
	 *            部署id，必须参数
	 * @param resourceId
	 *            资源id，必须参数
	 * @return
	 * @throws Exception
	 */
	public WfResource getDeploymentResourceById(String deploymentId,
			String resourceId) throws Exception;

	/**
	 * 查询流程定义列表
	 * 
	 * @param wfProcessDefinitionParam
	 *            查询参数，必须参数
	 * @return
	 * @throws Exception
	 */
	public WfPageList<WfProcessDefinition, WfProcessDefinitionParam> queryProcessDefinitions(
			WfProcessDefinitionParam wfProcessDefinitionParam) throws Exception;

	/**
	 * 根据流程定义id获取流程定义对象
	 * 
	 * @param processDefinitionId
	 *            流程定义id，必须参数
	 * @return
	 * @throws Exception
	 */
	public WfProcessDefinition getProcessDefinition(String processDefinitionId)
			throws Exception;

	/**
	 * 更新流程定义的分类
	 * 
	 * @param wfOperator
	 *            操作者信息，必须参数
	 * @param processDefinitionId
	 *            流程定义id，必须参数
	 * @param category
	 *            分类，必须参数
	 * @return 200 表示找到了对象并操作成功；<br>
	 *         404 表示找不到请求的对象
	 * @throws Exception
	 */
	public String updateProcessDefinitionCategory(WfOperator wfOperator,
			String processDefinitionId, String category) throws Exception;

	/**
	 * 根据流程定义id获取流程定义bpmn内容
	 * 
	 * @param processDefinitionId
	 *            流程定义id，必须参数
	 * @return String bpmn内容
	 * @throws Exception
	 */
	public String getProcessDefinitionBPMN(String processDefinitionId)
			throws Exception;

	/**
	 * 挂起流程定义，流程定义被挂起后不能被启动
	 * 
	 * @param wfOperator
	 *            操作者，必须参数
	 * @param processDefinitionId
	 *            流程定义id，必须参数
	 * @return 200-挂起成功
	 * @throws Exception
	 */
	public String suspendProcessDefinition(WfOperator wfOperator,
			String processDefinitionId) throws Exception;

	/**
	 * 激活流程定义，将被挂起的流程定义恢复使用
	 * 
	 * @param wfOperator
	 *            操作者，必须参数
	 * @param processDefinitionId
	 *            流程定义id，必须参数
	 * @return 200-激活成功
	 * @throws Exception
	 */
	public String activateProcessDefinition(WfOperator wfOperator,
			String processDefinitionId) throws Exception;

	/**
	 * 获得流程定义的所有候选启动者
	 * 
	 * @param processDefinitionId
	 *            流程定义id，必须参数
	 * @return
	 * @throws Exception
	 */
	public List<WfIdentityLink> getProcessDefinitionCandidates(
			String processDefinitionId) throws Exception;

	/**
	 * 为流程定义添加一个候选启动者
	 * 
	 * @param wfOperator
	 *            操作者，必须参数
	 * @param processDefinitionId
	 *            流程定义ID，必须参数
	 * @param identityLinkType
	 *            候选者类型：users 或 groups，依赖IdentityLink的类型，必须参数。
	 * @param identityId
	 *            候选创建者的身份的userId 或 groupId，必须参数。
	 * @return
	 * @throws Exception
	 */
	public WfIdentityLink addProcessDefinitionCandidate(WfOperator wfOperator,
			String processDefinitionId, String identityLinkType,
			String identityId) throws Exception;

	/**
	 * 删除流程定义的候选启动者
	 * 
	 * @param wfOperator
	 *            操作者，必须参数
	 * @param processDefinitionId
	 *            流程定义ID，必须参数
	 * @param identityLinkType
	 *            候选者类型：users 或 groups，依赖IdentityLink的类型，必须参数。
	 * @param identityId
	 *            候选创建者的身份的userId 或 groupId，必须参数。
	 * @return 200表示找到了流程定义，并删除了IdentityLink；<br>
	 *         404标识找不到流程定义，删除IdentityLink失败
	 * @throws Exception
	 */
	public String deleteProcessDefinitionCandidate(WfOperator wfOperator,
			String processDefinitionId, String identityLinkType,
			String identityId) throws Exception;

	/**
	 * 查询流程模型
	 * 
	 * @param WfModelParam
	 *            查询流程模型参数 ，必须参数
	 * @return
	 * @throws Exception
	 */
	public WfPageList<WfModel, WfModelParam> queryModels(
			WfModelParam wfModelParam) throws Exception;

	/**
	 * 根据模型id获取一个模型
	 * 
	 * @param modelId
	 *            模型id，必须参数
	 * @return
	 * @throws Exception
	 */
	public WfModel getModelById(String modelId) throws Exception;

	/**
	 * 创建一个新的模型
	 * 
	 * @param wfOperator
	 *            操作者信息，必须参数
	 * @param wfModel
	 *            模型对象，必须参数
	 * @return 200-表示创建成功
	 * @throws Exception
	 */
	public String addModel(WfOperator wfOperator, WfModel wfModel)
			throws Exception;

	/**
	 * 更新一个模型
	 * 
	 * @param wfOperator
	 *            操作者信息，必须参数
	 * @param modelId
	 *            模型id，必须参数
	 * @param wfModel
	 *            模型对象，必须参数
	 * @return 200-表示更新成功；<br>
	 *         400-表示更新失败
	 * @throws Exception
	 */
	public String updateModel(WfOperator wfOperator, String modelId,
			WfModel wfModel) throws Exception;

	/**
	 * 删除模型
	 * 
	 * @param wfOperator
	 *            操作者信息，必须参数
	 * @param modelId
	 *            模型id，必须参数
	 * @return 200-表示删除成功；<br>
	 *         400-表示删除失败
	 * @throws Exception
	 */
	public String deleteModelsById(WfOperator wfOperator, String modelId)
			throws Exception;

	/**
	 * 获得模型的可编辑源码
	 * 
	 * @param modelId
	 *            模型id，必须参数
	 * @return 模型源码
	 * @throws Exception
	 */
	public String getModelEditorSource(String modelId) throws Exception;

	/**
	 * 设置模型的可编辑源码
	 * 
	 * @param wfOperator
	 *            操作者，必须参数
	 * @param modelId
	 *            模型id，必须参数
	 * @param modelSource
	 *            模型源码，必须参数
	 * @return 200-表示设置成功；<br>
	 *         400-表示设置失败
	 * @throws Exception
	 */
	public String saveModelEditorSource(WfOperator wfOperator, String modelId,
			String modelSource) throws Exception;

	/**
	 * 获得模型的附加可编辑源码
	 * 
	 * @param modelId
	 *            模型id，必须参数
	 * @return 模型附加源码
	 * @throws Exception
	 */
	public String getModelEditorSourceExtra(String modelId) throws Exception;

	/**
	 * 设置模型的附加可编辑源码
	 * 
	 * @param wfOperator
	 *            操作者，必须参数
	 * @param modelId
	 *            模型id，必须参数
	 * @param modelSourceExtra
	 *            模型附加源码，必须参数
	 * @return 200-表示设置成功；<br>
	 *         400-表示设置失败
	 * @throws Exception
	 */
	public String saveModelEditorSourceExtra(WfOperator wfOperator,
			String modelId, String modelSource) throws Exception;
	
	/**
	 * 获取流程定义中某活动的下一步活动定义KEY
	 * @param processDefinitionId
	 *             流程定义ID
	 * @param currenTaskDefId
	 *             当前活动定义KEY
	 * @return 下一步活动的定义KEY
	 * @throws Exception
	 */
	public String getNextActivityId(String processDefinitionId, String currenTaskDefId) throws Exception;
	
	/**
	 * 查询流程活动定义中设置的变量
	 * @param processDefinitionId
	 *            流程定义ID
	 * @param taskDefId
	 *            活动定义KEY
	 * @return
	 * @throws Exception
	 * 
	 */
	public Map<String, Object> queryVariablesOfActivityInDefinition(
			String processDefinitionId, String taskDefId) throws Exception;
	
	
	/**
	 * 查询流程定义模型的所有活动
	 * @param processDefinitionId 
	 * 			流程定义ID
	 * @return
	 * @throws Exception
	 */
	public List<WfActivity> getActivitiesByDefinition(String processDefinitionId) throws Exception;
	
	
	/**
	 * 创建一个新的模型
	 * 
	 * @param wfOperator
	 *            操作者信息，必须参数
	 * @param wfModel
	 *            模型对象，必须参数
	 * @return 返回最新对象，返回主键
	 * @throws Exception
	 */
	public WfModel insertModel(WfOperator wfOperator, WfModel wfModel) throws Exception;
	
	
	/**
	 * 部署流程文件内容
	 * 
	 * @param wfOperator
	 *            当前操作者信息，必须参数
	 * @param name
	 *            部署名称,可以为空
	 * @param category
	 *            部署分类,可以为空
	 * @param resourceName
	 *            部署流程资源名称，必须参数
	 * @param resourceContent
	 *            部署流程内容，读取的bpmn文件的xml内容，必须参数
	 * @return 最新部署实体，携带ID
	 * @throws Exception 失败则抛出异常
	 */
	public WfDeployment deployContent(WfOperator wfOperator, String name,
			String category, String resourceName, String resourceContent) throws Exception;
	
	
	/**
	 * 部署流程包（zip）
	 * 
	 * @param wfOperator
	 *            当前操作者信息，必须参数
	 * @param name
	 *            部署名称,可以为空
	 * @param category
	 *            部署分类,可以为空
	 * @param inputStream
	 *            zip文件输入流，必须参数
	 * @return 最新部署实体，携带ID
	 * @throws Exception 失败则抛出异常
	 */
	public WfDeployment deployZip(WfOperator wfOperator, String name,
			String category, InputStream inputStream) throws Exception;
	
	
	/**
	 * 部署流程引擎classpath路径下的流程，只适用于本地部署
	 * 
	 * @param wfOperator
	 *            当前操作者信息，必须参数
	 * @param name
	 *            部署名称,可以为空
	 * @param category
	 *            部署分类,可以为空
	 * @param resourceClassPath
	 *            部署流程文件相对classpath路径，必须参数
	 * @return 最新部署实体，携带ID
	 * @throws Exception 失败则抛出异常
	 */
	public WfDeployment deployClassPath(WfOperator wfOperator, String name,
			String category, String resourceClassPath) throws Exception;
	
	
	/**
	 * 部署流程(字节)
	 * 
	 * @param wfOperator
	 *            当前操作者信息，必须参数
	 * @param bytes
	 *            部署流程文件节字数组，必须参数
	 * @param name
	 *            部署名称,可以为空
	 * @param category
	 *            部署分类,可以为空
	 * @return 最新部署实体，携带ID
	 * @throws Exception 失败则抛出异常
	 */
	public WfDeployment deployByte(WfOperator wfOperator,byte[] bytes,String name,
			String category) throws Exception;
	
}
