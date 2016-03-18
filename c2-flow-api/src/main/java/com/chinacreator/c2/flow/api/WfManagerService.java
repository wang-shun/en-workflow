package com.chinacreator.c2.flow.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.chinacreator.c2.flow.detail.WfGroup;
import com.chinacreator.c2.flow.detail.WfGroupParam;
import com.chinacreator.c2.flow.detail.WfHoliday;
import com.chinacreator.c2.flow.detail.WfModuleDelegate;
import com.chinacreator.c2.flow.detail.WfModuleDelegateInfo;
import com.chinacreator.c2.flow.detail.WfModuleDelegateParam;
import com.chinacreator.c2.flow.detail.WfOperateLogBean;
import com.chinacreator.c2.flow.detail.WfPageList;
import com.chinacreator.c2.flow.detail.WfPageParam;
import com.chinacreator.c2.flow.detail.WfProcessConfigProperty;
import com.chinacreator.c2.flow.detail.WfProcessDefinition;
import com.chinacreator.c2.flow.detail.WfProcessDefinitionParam;
import com.chinacreator.c2.flow.detail.WfUniteColumn;
import com.chinacreator.c2.flow.detail.WfUniteConfig;
import com.chinacreator.c2.flow.detail.WfUser;
import com.chinacreator.c2.flow.detail.WfUserParam;
import com.chinacreator.c2.flow.detail.WfWorkDate;
import com.chinacreator.c2.flow.detail.WfWorkDateParam;

/**
 * |工作流管理接口，包括流程引擎信息、用户、用户组、扩展等接口<br>
 * |可被本地和远程调用。<br>
 * |远程调用spring bean id=wfManagerServiceRemote，注意区分大小写 <br>
 * |本地调用spring bean id=wfManagerServiceLocal <br>
 * |统一返回值：200-操作成功、300-参数不正确、400-操作失败、404-操作对象不存在
 * 
 * @author minghua.guo
 * 
 */
public interface WfManagerService {
	/**
	 * 获取引擎属性
	 * 
	 * @return Map 如：{ "next.dbid":"101", "schema.history":"create(5.14)",
	 *         "schema.version":"5.14" }
	 * @throws Exception
	 */
	public Map<String, String> getEngineProperties() throws Exception;

	/**
	 * 获取引擎信息
	 * 
	 * @return Map 如：{ "name":"default", "version":"5.14",
	 *         "resourceUrl":"file://activiti/activiti.cfg.xml",
	 *         "exception":null }
	 * @throws Exception
	 */
	public Map<String, String> getEngineInfo() throws Exception;

	/**
	 * 获取一个用户
	 * 
	 * @param userId
	 *            用户id
	 * @return
	 * @throws Exception
	 */
	public WfUser getUserById(String userId) throws Exception;

	/**
	 * 查询工作流用户列表
	 * 
	 * @param params
	 *            用户查询参数
	 * @return
	 * @throws Exception
	 */
	public WfPageList<WfUser, WfUserParam> queryUsers(WfUserParam params)
			throws Exception;

	/**
	 * 创建用户
	 * 
	 * @param user
	 *            用户对象
	 * @return 200 表示成功创建了用户。<br>
	 * @throws Exception
	 */
	public String addUser(WfUser user) throws Exception;

	/**
	 * 更新用户
	 * 
	 * @param user
	 *            用户对象
	 * @return 200 表示成功更新了用户。<br>
	 *         404 表示找不到用户。
	 * @throws Exception
	 */
	public String updateUser(WfUser user) throws Exception;

	/**
	 * 删除用户
	 * 
	 * @param userId
	 *            用户id
	 * @return 200 表示成功删除了用户。<br>
	 *         404 表示找不到用户。
	 * @throws Exception
	 */
	public String deleteUserById(String userId) throws Exception;

	/**
	 * 获取一个群组
	 * 
	 * @param groupId
	 *            群组id
	 * @return
	 * @throws Exception
	 */
	public WfGroup getGroupById(String groupId) throws Exception;

	/**
	 * 获取用户群组列表
	 * 
	 * @param params
	 *            用户组查询参数
	 * @return
	 * @throws Exception
	 */
	public WfPageList<WfGroup, WfGroupParam> queryGroups(WfGroupParam params)
			throws Exception;

	/**
	 * 通过事项ID获取流程实例
	 * 
	 * @param moduleId
	 *            事项ID return 流程定义对象
	 * @throws Exception
	 */
	public WfProcessDefinition getBindProcessByModuleId(String moduleId)
			throws Exception;

	/**
	 * 新增流程事项配置
	 * 
	 * @param wpd
	 *            流程信息
	 * @param moduleId
	 *            事项模块ID
	 * @throws Exception
	 */
	public void bindProcessToModule(String moduleId, WfProcessDefinition wpd)
			throws Exception;
	
	/**
	 * 重新绑定事项配置
	 * @param moduleId
	 * @param wpd
	 * @throws Exception
	 */
	public void reBindProcessToModule(String moduleId, WfProcessDefinition wpd) throws Exception;

	/**
	 * 解除流程与事项的绑定
	 * 
	 * @param moduleId
	 *            事项ID
	 * @param wpd
	 *            流程信息
	 * @throws Exception
	 */
	public void unBindProcessFromModule(String moduleId, WfProcessDefinition wpd)
			throws Exception;

	/**
	 * 查询流程标识对应的所有已绑定事项
	 * 
	 * @param processDefinitionKey
	 *            流程定义key
	 * @return List<String> 事项ID集合 throws Exception
	 */
	public List<String> getBindModuleIdsByProcessDefKey(
			String processDefinitionKey) throws Exception;

	/**
	 * 查询任务字段配置
	 * 
	 * @param appId
	 *            应用id
	 * @param tenantId
	 *            租户id
	 * @param engineName
	 *            引擎名称
	 * @param taskType
	 *            任务类型：todolist-待办、donelist-已办、suspendlist-挂起等
	 * @return
	 * @throws Exception
	 */
	public List<WfUniteColumn> findWfUniteColumns(String appId,
			String tenantId, String engineName, String taskType)
			throws Exception;

	/**
	 * 查询任务配置
	 * 
	 * @param appId
	 *            应用id
	 * @param tenantId
	 *            租户id
	 * @param engineName
	 *            引擎名称
	 * @param taskType
	 *            任务类型：todolist-待办、donelist-已办、suspendlist-挂起等
	 * @return
	 * @throws Exception
	 */
	public List<WfUniteConfig> findWfUniteConfig(String appId, String tenantId,
			String engineName, String taskType) throws Exception;

	/**
	 * 保存任务配置数据
	 * 
	 * @param data
	 *            任务配置数据
	 * @throws Exception
	 */
	public void saveWfUniteConfig(WfUniteConfig data) throws Exception;

	/**
	 * 保存任务字段配置数据
	 * 
	 * @param data
	 *            字段配置数据
	 * @throws Exception
	 */
	public void saveWfUniteColumn(WfUniteColumn data) throws Exception;

	/**
	 * 获取事项下某个环节的外围配置信息
	 * 
	 * @param processDefinitionId
	 *            流程定义ID(可为空)
	 * @param moduleId
	 *            事项ID(必传)
	 * @param taskDefKey
	 *            任务定义ID(必传)
	 */
	public WfProcessConfigProperty findProcessConfigProperty(
			String processDefinitionId, String moduleId, String taskDefKey)
			throws Exception;

	/**
	 * 保存环节外围配置信息
	 * 
	 * @param processDefinitionId
	 * @param taskDefinitionId
	 * @param moduleId
	 * @param wpcp
	 */
	public void saveProcessConfigProperty(String processDefinitionId,
			String taskDefinitionId, String moduleId,
			WfProcessConfigProperty wpcp) throws Exception;

	/**
	 * 根据查询条件查询事项委托列表
	 * 
	 * @param wfModuleDelegateParam
	 *            事项委托查询条件
	 * @return WfPageList分页后的事项委托列表
	 */
	public WfPageList<WfModuleDelegate, WfModuleDelegateParam> getModuleDelegateByParam(
			WfModuleDelegateParam wfModuleDelegateParam) throws Exception;

	/**
	 * 查询流程日志
	 * 
	 * @param parameters
	 *            查询参数
	 * @param firstResult
	 *            起始记录数
	 * @param maxResults
	 *            每页最大记录数
	 * @return
	 * @throws Exception
	 */
	public WfPageList<WfOperateLogBean, WfPageParam> findWfOperateLog(
			Map<String, Object> parameters, int firstResult, int maxResults)
			throws Exception;

	/**
	 * 根据日志id获取日志明细
	 * 
	 * @param id
	 *            日志id
	 * @return
	 * @throws Exception
	 */
	public WfOperateLogBean getWfOperateLogById(String id) throws Exception;

	/**
	 * 新增事项委托
	 * 
	 * @param wfModuleDelegate
	 *            事项委托基本信息
	 * @param wfModuleDelegateInfoList
	 *            事项委托事项信息列表
	 * @return 委托状态
	 * @throws Exception
	 */
	public Map<String, String> saveModuleDelegate(
			WfModuleDelegate wfModuleDelegate,
			List<WfModuleDelegateInfo> wfModuleDelegateInfoList)
			throws Exception;

	/**
	 * 新增事项委托
	 * 
	 * @param wfModuleDelegate
	 *            事项委托基本信息
	 * @param wfModuleDelegateInfoArr
	 *            事项委托事项信息列表
	 * @return 委托状态
	 * @throws Exception
	 */
	public Map<String, String> saveModuleDelegateArr(
			WfModuleDelegate wfModuleDelegate,
			WfModuleDelegateInfo[] wfModuleDelegateInfoArr) throws Exception;

	/**
	 * 修改事项委托
	 * 
	 * @param wfModuleDelegate
	 *            事项委托基本信息
	 * @param wfModuleDelegateInfoList
	 *            事项委托事项信息列表
	 * @return 委托状态
	 * @throws Exception
	 */
	public Map<String, String> modifyModuleDelegate(
			WfModuleDelegate wfModuleDelegate,
			List<WfModuleDelegateInfo> wfModuleDelegateInfoList)
			throws Exception;

	/**
	 * 修改事项委托
	 * 
	 * @param wfModuleDelegate
	 *            事项委托基本信息
	 * @param wfModuleDelegateInfoArr
	 *            事项委托事项信息列表
	 * @return委托状态
	 * @throws Exception
	 */
	public Map<String, String> modifyModuleDelegateArr(
			WfModuleDelegate wfModuleDelegate,
			WfModuleDelegateInfo[] wfModuleDelegateInfoArr) throws Exception;

	/**
	 * 修改事项委托状态
	 * 
	 * @param delegateId
	 *            委托ID
	 * @param delegateState
	 *            委托状态
	 * @param acceptFlag
	 *            接受委托标志，为true时需要更新确认时间为当前时间
	 * @throws Exception
	 */
	public void updateModuleDelegateState(String delegateId,
			String delegateState, boolean acceptFlag) throws Exception;

	/**
	 * 删除事项委托
	 * 
	 * @param delegateId
	 *            委托ID
	 * @throws Exception
	 */
	public void deleteModuleDelegate(String delegateId) throws Exception;

	/**
	 * 查询流程定义列表(自己写sql且含部署时间，按部署时间排序)
	 * 
	 * @param wfProcessDefinitionParam
	 *            查询参数，必须参数
	 * @return
	 * @throws Exception
	 */
	public WfPageList<WfProcessDefinition, WfProcessDefinitionParam> queryProcessDefinitionsAndDeployInfoList(
			WfProcessDefinitionParam wfProcessDefinitionParam) throws Exception;

	/**
	 * 保存并部署流程，用于在线流程编辑器
	 * 
	 * @param modelId
	 *            模型id
	 * @param tenantId
	 *            租户id
	 * @param name
	 *            名称
	 * @param description
	 *            描述
	 * @param json_xml
	 *            流程json_xml内容
	 * @param svg_xml
	 *            流程svg_xml内容
	 * @throws Exception
	 */
	public void saveModel(String modelId, String tenantId, String name,
			String description, String json_xml, String svg_xml)
			throws Exception;

	/**
	 * 获取流程预览图
	 * 
	 * @param processInstanceId
	 *            流程实例id
	 * @param processDefinitionId
	 *            流程定义id
	 * @return
	 * @throws Exception
	 */
	public String getDiagram(String processInstanceId,
			String processDefinitionId) throws Exception;

	/**
	 * 获取流程监控图高亮显示信息
	 * 
	 * @param processInstanceId
	 *            流程实例id
	 * @return
	 * @throws Exception
	 */
	public String getHighlighted(String processInstanceId) throws Exception;
	
	/**
	 * 根据流程定义key给最新版本的流程添加任务监听器
	 * @param processDefinitionKey 流程定义key
	 * @throws Exception
	 */
	public void addTaskListener(String processDefinitionKey) throws Exception;
	
	/**
	 * 根据流程定义key给最新版本的流程添加任务监听器
	 * @param processDefinitionKey 流程定义key
	 * @param tenantId   租户id
	 * @throws Exception
	 */
	public void addTaskListener(String processDefinitionKey,String tenantId) throws Exception;
	
	/**
	 * 新增节假日（已有的不重复添加，容错）
	 * @param wfHoliday 工作流节假日对象
	 * @throws Exception
	 */
	public void addHoliday(WfHoliday wfHoliday) throws Exception;
	
	/**
	 * 删除节假日
	 * @param wfHoliday 工作流节假日对象
	 * @throws Exception
	 */
	public void deleteHoliday(WfHoliday wfHoliday) throws Exception;
	
	/**
	 * 设置节假日（如果有则删除节假日，否则新增节假日）
	 * @param wfHoliday 工作流节假日对象
	 * @throws Exception
	 */
	public boolean setHoliday(WfHoliday wfHoliday) throws Exception;
	
	/**
	 * 设置当年的节假日为默认的双休日休假
	 * @param yHoliday 年份
	 * @throws Exception
	 */
	public void setDefaultYearHoliday(String yHoliday) throws Exception;
	
	/**
	 * 设置作息时间（修改已有作息时间）
	 * @param wfWorkDate 工作流作息时间对象
	 * @throws Exception
	 */
	public void setWorkDate(WfWorkDate wfWorkDate) throws Exception;
	
	/**
	 * 新增作息时间
	 * @param wfWorkDate 工作流作息时间对象
	 * @throws Exception
	 */
	public void addWorkDate(WfWorkDate wfWorkDate) throws Exception;
	
	/**
	 * 删除作息时间
	 * @param wfWorkDate 工作流作息时间对象
	 * @throws Exception
	 */
	public void deleteWorkDate(WfWorkDate wfWorkDate) throws Exception;
	
	/**
	 * 批量删除作息时间
	 * @param wfWorkDate 工作流作息时间对象
	 * @throws Exception
	 */
	public void deleteWorkDates(String[] workIds) throws Exception;
	
	/**
	 * 获取工作流节假日列表
	 * @param wfHoliday 节假日查询条件
	 * @return 工作流节假日列表
	 * @throws Exception
	 */
	public List<WfHoliday> getHolidayList(WfHoliday wfHoliday) throws Exception;
	
	/**
	 * 获取工作流作息时间列表
	 * @param wfWorkDate 作息时间查询对象
	 * @return 工作流作息时间列表
	 * @throws Exception
	 */
	public WfPageList<WfWorkDate, WfWorkDateParam> getWorkDateList(WfWorkDateParam wfWorkDateParam) throws Exception;

	/**
	 * 计算过期时间
	 * @param begineDate 开始时间
	 * @param duration 期限数值
	 * @param durationUnit 期限单位（年:Y;月:M;日:D;小时:h;分钟:m;秒:s）
	 * @param tenantId 租户ID
	 * @return 过期时间
	 * @throws Exception
	 */
	public Date getDueDateAfterExecute(Date begineDate, Integer duration, String durationUnit, String tenantId) throws Exception;
	
}
