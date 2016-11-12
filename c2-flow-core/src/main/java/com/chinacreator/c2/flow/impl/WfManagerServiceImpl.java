package com.chinacreator.c2.flow.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineInfo;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.impl.ProcessEngineImpl;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.BoundaryEventActivityBehavior;
import org.activiti.engine.impl.bpmn.behavior.CallActivityBehavior;
import org.activiti.engine.impl.bpmn.parser.BpmnParse;
import org.activiti.engine.impl.bpmn.parser.ErrorEventDefinition;
import org.activiti.engine.impl.bpmn.parser.EventSubscriptionDeclaration;
import org.activiti.engine.impl.jobexecutor.TimerDeclarationImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.Lane;
import org.activiti.engine.impl.pvm.process.LaneSet;
import org.activiti.engine.impl.pvm.process.ParticipantProcess;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.query.Query;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.rest.common.api.ActivitiUtil;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.c2.flow.api.WfManagerService;
import com.chinacreator.c2.flow.api.WfRepositoryService;
import com.chinacreator.c2.flow.cmd.aspect.FindWfOperateLogByConditionCmd;
import com.chinacreator.c2.flow.cmd.aspect.FindWfOperateLogByIdCmd;
import com.chinacreator.c2.flow.cmd.aspect.FindWfOperateLogDataByIdCmd;
import com.chinacreator.c2.flow.cmd.delegate.DeleteModuleDelegateByIdCmd;
import com.chinacreator.c2.flow.cmd.delegate.DeleteModuleDelegateInfoByDelegateIdCmd;
import com.chinacreator.c2.flow.cmd.delegate.GetModuleDelegateByParam;
import com.chinacreator.c2.flow.cmd.delegate.GetModuleDelegateInfoByDelegateId;
import com.chinacreator.c2.flow.cmd.delegate.GetWfDelegateSomeRangeCountCmd;
import com.chinacreator.c2.flow.cmd.delegate.InsertWfModuleDelegateCmd;
import com.chinacreator.c2.flow.cmd.delegate.InsertWfModuleDelegateInfoCmd;
import com.chinacreator.c2.flow.cmd.delegate.UpdateModuleDelegateStateCmd;
import com.chinacreator.c2.flow.cmd.delegate.UpdateWfModuleDelegateCmd;
import com.chinacreator.c2.flow.cmd.holiday.DeleteHolidayCmd;
import com.chinacreator.c2.flow.cmd.holiday.GetHolidayByParamCmd;
import com.chinacreator.c2.flow.cmd.holiday.GetHolidaysCmd;
import com.chinacreator.c2.flow.cmd.holiday.InsertHolidayCmd;
import com.chinacreator.c2.flow.cmd.moduleconfig.DeleteProcessConfigPropertyByConfigIdAndTaskDefIdCommand;
import com.chinacreator.c2.flow.cmd.moduleconfig.FindProcessConfigProperty;
import com.chinacreator.c2.flow.cmd.moduleconfig.GetBindModuleIdsByProcessDefKeyAndTenantCmd;
import com.chinacreator.c2.flow.cmd.moduleconfig.GetBindModuleIdsByProcessDefKeyCmd;
import com.chinacreator.c2.flow.cmd.moduleconfig.GetProcessInfoByModuleIdCmd;
import com.chinacreator.c2.flow.cmd.moduleconfig.InsertModuleConfigCmd;
import com.chinacreator.c2.flow.cmd.moduleconfig.InsertProcessConfigPropertyCmd;
import com.chinacreator.c2.flow.cmd.moduleconfig.SelectWfModuleConfigByParamCmd;
import com.chinacreator.c2.flow.cmd.moduleconfig.UpdateIsLatestByModuleIdCmd;
import com.chinacreator.c2.flow.cmd.monit.GetHistoricActivityInstanceByProcessInstanceId;
import com.chinacreator.c2.flow.cmd.processdefine.GetProcessDefinitionAndDeployInfoListCmd;
import com.chinacreator.c2.flow.cmd.unitetask.config.FindWfUniteColumnsCmd;
import com.chinacreator.c2.flow.cmd.unitetask.config.FindWfUniteConfigCmd;
import com.chinacreator.c2.flow.cmd.unitetask.config.SaveWfUniteColumnCmd;
import com.chinacreator.c2.flow.cmd.unitetask.config.SaveWfUniteConfigCmd;
import com.chinacreator.c2.flow.cmd.workdate.DeleteWorkDateCmd;
import com.chinacreator.c2.flow.cmd.workdate.GetWorkDateByParamCmd;
import com.chinacreator.c2.flow.cmd.workdate.GetWorkDateOfDayCmd;
import com.chinacreator.c2.flow.cmd.workdate.InsertWorkDateCmd;
import com.chinacreator.c2.flow.cmd.workdate.UpdateWorkDateCmd;
import com.chinacreator.c2.flow.detail.WfConstants;
import com.chinacreator.c2.flow.detail.WfDelegateRange;
import com.chinacreator.c2.flow.detail.WfDelegateStatus;
import com.chinacreator.c2.flow.detail.WfDelegateType;
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
import com.chinacreator.c2.flow.listener.ExtendTaskListener;
import com.chinacreator.c2.flow.persistence.entity.WfHolidayEntity;
import com.chinacreator.c2.flow.persistence.entity.WfModuleConfigEntity;
import com.chinacreator.c2.flow.persistence.entity.WfModuleDelegateEntity;
import com.chinacreator.c2.flow.persistence.entity.WfModuleDelegateInfoEntity;
import com.chinacreator.c2.flow.persistence.entity.WfOperateLogDataEntity;
import com.chinacreator.c2.flow.persistence.entity.WfOperateLogEntity;
import com.chinacreator.c2.flow.persistence.entity.WfProcessConfigPropertyEntity;
import com.chinacreator.c2.flow.persistence.entity.WfProcessDefinitionAndDeployInfoEntity;
import com.chinacreator.c2.flow.persistence.entity.WfUniteColumnsEntity;
import com.chinacreator.c2.flow.persistence.entity.WfUniteConfigEntity;
import com.chinacreator.c2.flow.persistence.entity.WfWorkDateEntity;
import com.chinacreator.c2.flow.util.CommonUtil;
import com.chinacreator.c2.flow.util.DateUtil;
import com.chinacreator.c2.flow.util.PKGenerator;

public class WfManagerServiceImpl implements WfManagerService {
	private static final String ACTTRANS_LINK_TOKEN = "_-_";

	private RuntimeService runtimeService;
	private ManagementService managementService;

	private RepositoryService repositoryService;

	private ProcessEngine processEngine;

	private IdentityService identityService;

	private HistoryService historyService;

	private WfRepositoryService wfRepositoryServiceLocal;
	final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public HistoryService getHistoryService() {
		return historyService;
	}

	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}

	public ProcessEngine getProcessEngine() {
		return processEngine;
	}

	public void setProcessEngine(ProcessEngine processEngine) {
		this.processEngine = processEngine;
	}

	public IdentityService getIdentityService() {
		return identityService;
	}

	public void setIdentityService(IdentityService identityService) {
		this.identityService = identityService;
	}

	public ManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(ManagementService managementService) {
		this.managementService = managementService;
	}

	public WfRepositoryService getWfRepositoryServiceLocal() {
		return wfRepositoryServiceLocal;
	}

	public void setWfRepositoryServiceLocal(
			WfRepositoryService wfRepositoryServiceLocal) {
		this.wfRepositoryServiceLocal = wfRepositoryServiceLocal;
	}

	@Override
	public Map<String, String> getEngineProperties() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		if (null != managementService) {
			map = managementService.getProperties();
		}
		return map;
	}

	@Override
	public Map<String, String> getEngineInfo() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		ProcessEngineInfo engineInfo = ProcessEngines
				.getProcessEngineInfo(processEngine.getName());
		if (engineInfo != null) {
			map.put("name", engineInfo.getName());
			map.put("resourceUrl", engineInfo.getResourceUrl());
			map.put("version", ProcessEngine.VERSION);
			map.put("exception", null);
		} else {
			map.put("exception", "ProcessEngine Info is null");
		}
		return map;
	}

	@Override
	public WfUser getUserById(String userId) throws Exception {
		User user = identityService.createUserQuery().userId(userId)
				.singleResult();
		WfUser wfUser = null;
		if (null != user) {
			wfUser = new WfUser();
			wfUser.setId(user.getId());
			wfUser.setEmail(user.getEmail());
			wfUser.setFirstName(user.getFirstName());
			wfUser.setLastName(user.getLastName());
			wfUser.setPassword(user.getPassword());
		}
		return wfUser;
	}

	@Override
	public WfPageList<WfUser, WfUserParam> queryUsers(WfUserParam params)
			throws Exception {
		WfPageList<WfUser, WfUserParam> wfUserPageList = new WfPageList<WfUser, WfUserParam>();
		if (null != params) {
			UserQuery userQuery = identityService.createUserQuery();
			String email = params.getEmail();
			String emailLike = params.getEmailLike();
			String firstName = params.getFirstName();
			String firstNameLike = params.getFirstNameLike();
			String id = params.getId();
			String lastName = params.getLastName();
			String lastNameLike = params.getLastNameLike();
			String memberOfGroup = params.getMemberOfGroup();
			String potentialStarter = params.getPotentialStarter();

			boolean orderByUserEmail = params.isOrderByUserEmail();

			boolean orderByUserFirstName = params.isOrderByUserFirstName();

			boolean orderByUserId = params.isOrderByUserId();

			boolean orderByUserLastName = params.isOrderByUserLastName();

			// 是否分页
			boolean isPaged = params.isPaged();

			if (!CommonUtil.stringNullOrEmpty(email)) {
				userQuery.userEmail(email);
			}
			if (!CommonUtil.stringNullOrEmpty(emailLike)) {
				userQuery.userEmailLike(emailLike);
			}
			if (!CommonUtil.stringNullOrEmpty(firstName)) {
				userQuery.userFirstName(firstName);
			}
			if (!CommonUtil.stringNullOrEmpty(firstNameLike)) {
				userQuery.userFirstNameLike(firstNameLike);
			}
			if (!CommonUtil.stringNullOrEmpty(id)) {
				userQuery.userId(id);
			}
			if (!CommonUtil.stringNullOrEmpty(lastName)) {
				userQuery.userLastName(lastName);
			}
			if (!CommonUtil.stringNullOrEmpty(lastNameLike)) {
				userQuery.userLastNameLike(lastNameLike);
			}
			if (!CommonUtil.stringNullOrEmpty(memberOfGroup)) {
				userQuery.memberOfGroup(memberOfGroup);
			}
			if (!CommonUtil.stringNullOrEmpty(potentialStarter)) {
				userQuery.potentialStarter(potentialStarter);
			}

			if (orderByUserEmail) {
				userQuery.orderByUserEmail();
				setOrder(userQuery, params);
			}
			if (orderByUserFirstName) {
				userQuery.orderByUserFirstName();
				setOrder(userQuery, params);
			}
			if (orderByUserId) {
				userQuery.orderByUserId();
				setOrder(userQuery, params);
			}
			if (orderByUserLastName) {
				userQuery.orderByUserLastName();
				setOrder(userQuery, params);
			}

			List<User> userList = new ArrayList<User>();
			if (isPaged) {
				userList = userQuery.listPage((int) params.getStart(),
						(int) params.getSize());
				long total = userQuery.count();
				params.setTotal(total);
			} else {
				userList = userQuery.list();
			}
			wfUserPageList.setWfQuery(params);
			if (!userList.isEmpty()) {
				for (User u : userList) {
					WfUser wfUser = new WfUser();
					wfUser.setId(u.getId());
					wfUser.setEmail(u.getEmail());
					wfUser.setFirstName(u.getFirstName());
					wfUser.setLastName(u.getLastName());
					wfUser.setPassword(u.getPassword());
					wfUserPageList.add(wfUser);
				}
			}
		}
		return wfUserPageList;
	}

	@Override
	@Transactional
	public String addUser(WfUser user) throws Exception {

		if (null != user) {
			String userId = new PKGenerator().getNextId();
			User activitiUser = identityService.newUser(userId);
			activitiUser.setFirstName(user.getFirstName());
			activitiUser.setEmail(user.getEmail());
			activitiUser.setLastName(user.getLastName());
			activitiUser.setPassword(user.getPassword());
			identityService.saveUser(activitiUser);
			return WfConstants.WF_CONTROL_EXE_SUCCESS;
		}
		return WfConstants.WF_CONTROL_EXE_FAIL;
	}

	@Override
	public String updateUser(WfUser user) throws Exception {
		if (null != user && !CommonUtil.stringNullOrEmpty(user.getId())) {
			User activitiUser = identityService.createUserQuery()
					.userId(user.getId()).singleResult();
			if (null != activitiUser) {
				activitiUser.setEmail(user.getEmail());
				activitiUser.setFirstName(user.getFirstName());
				activitiUser.setLastName(user.getLastName());
				activitiUser.setPassword(user.getPassword());

			} else {
				return WfConstants.WF_CONTROL_EXE_NOOBJECT;
			}

			identityService.saveUser(activitiUser);
			return WfConstants.WF_CONTROL_EXE_SUCCESS;
		}
		return WfConstants.WF_CONTROL_EXE_FAIL;
	}

	@Override
	public String deleteUserById(String userId) throws Exception {
		if (!CommonUtil.stringNullOrEmpty(userId)) {
			identityService.deleteUser(userId);
		}
		return WfConstants.WF_CONTROL_EXE_FAIL;
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

	@Override
	public WfProcessDefinition getBindProcessByModuleId(String moduleId)
			throws Exception {
		if (CommonUtil.stringNullOrEmpty(moduleId)) {
			throw new NullPointerException("moduleId不能为空！");
		}
		// WfModuleConfigEntity eo =
		// manageDao.getProcessInfoByModuleId(moduleId);
		WfModuleConfigEntity eo = managementService
				.executeCommand(new GetProcessInfoByModuleIdCmd(moduleId));

		WfProcessDefinition wpd = null;
		if (eo != null) {
			wpd=new WfProcessDefinition();
			wpd.setId(eo.getProcDefId());
			wpd.setKey(eo.getProcDefKey());
			wpd.setName(eo.getProcDefName());
		}
		return wpd;
	}

	@Override
	@Transactional
	public void bindProcessToModule(String moduleId, WfProcessDefinition wpd)
			throws Exception {
		WfModuleConfigEntity eo = new WfModuleConfigEntity();
		if (CommonUtil.stringNullOrEmpty(moduleId) || wpd == null) {
			throw new NullPointerException("moduleId不能为空且流程定义对象不能为空！");
		}

		String key = wpd.getKey();

		// 查询模块配置的所有流程，可不为islatest的。
		List<WfModuleConfigEntity> wmceList = managementService
				.executeCommand(new SelectWfModuleConfigByParamCmd(null,
						moduleId, null, key, null));

		List<WfModuleConfigEntity> matchList = new ArrayList<WfModuleConfigEntity>();
		if (wmceList != null && !wmceList.isEmpty()) {
			for (WfModuleConfigEntity wmce : wmceList) {
				if (wmce != null && wmce.getProcDefKey().equals(wpd.getKey())) {
					matchList.add(wmce);
				}
			}
		}
		// 从来没有绑定过该流程定义，创建新的绑定关系
		if (matchList.isEmpty()) {
			// 创建新的绑定关系
			eo.setId(new PKGenerator().getNextId());
			eo.setModuleId(moduleId);
			eo.setProcDefId(wpd.getId());
			eo.setProcDefKey(wpd.getKey());
			eo.setProcDefName(wpd.getName());
			eo.setIsLatest(true);

			managementService.executeCommand(new InsertModuleConfigCmd(eo));
		} else {
			// 是否有绑定当前传进来的流程的标识
			boolean isCurrentFlag = false;
			// 绑定过流程
			for (WfModuleConfigEntity match : matchList) {
				// 流程定义ID都相同,更新流程定义的状态即可
				if (wpd.getId().equals(match.getProcDefId())) {
					// 连流程定义ID都相等，只要更新islatest状态即可
					managementService
							.executeCommand(new UpdateIsLatestByModuleIdCmd(
									moduleId, wpd.getId(), true));
					isCurrentFlag = true;
					break;
				}
			}
			if (!isCurrentFlag) {
				// 绑定的是历史流程版本
				// 取版本最大的历史版本，获取所有的流程绑定关系和流程外围配置属性，进行拷贝
				int version = -1;
				String deploymentId = "";
				for (WfModuleConfigEntity match : matchList) {
					ProcessDefinition pDefine = repositoryService
							.createProcessDefinitionQuery()
							.processDefinitionId(match.getProcDefId())
							.singleResult();
					if (pDefine.getVersion() > version) {
						version = pDefine.getVersion();
						deploymentId = pDefine.getDeploymentId();
					}
				}
				if (version > 0) {
					ProcessDefinition pdf = repositoryService
							.createProcessDefinitionQuery()
							.processDefinitionKey(wpd.getKey())
							.processDefinitionVersion(version)
							.deploymentId(deploymentId).singleResult();
					String processDefId = pdf.getId();

					// 创建新的绑定关系
					String uuId = new PKGenerator().getNextId();
					eo.setId(uuId);
					eo.setModuleId(moduleId);
					eo.setProcDefId(wpd.getId());
					eo.setProcDefKey(wpd.getKey());
					eo.setProcDefName(wpd.getName());
					eo.setIsLatest(true);
					managementService.executeCommand(new InsertModuleConfigCmd(
							eo));

					List<WfProcessConfigProperty> wfProcessConfigPropertyList = managementService
							.executeCommand(new FindProcessConfigProperty(
									processDefId, moduleId, null));
					if (wfProcessConfigPropertyList != null
							&& !wfProcessConfigPropertyList.isEmpty()) {
						for (WfProcessConfigProperty wpcp : wfProcessConfigPropertyList) {
							wpcp.setConfigId(uuId);

							wpcp.setId(new PKGenerator().getNextId());

							// 再插入配置信息
							WfProcessConfigPropertyEntity wfProcessConfigPropertyEntity = new WfProcessConfigPropertyEntity();
							wfProcessConfigPropertyEntity.setAlias(wpcp
									.getAlias());
							wfProcessConfigPropertyEntity.setBindForm(wpcp
									.getBindForm());
							wfProcessConfigPropertyEntity.setConfigId(wpcp
									.getConfigId());
							wfProcessConfigPropertyEntity.setDuration(wpcp
									.getDuration());

							wfProcessConfigPropertyEntity.setDurationUnit(wpcp
									.getDurationUnit());
							wfProcessConfigPropertyEntity.setId(wpcp.getId());
							wfProcessConfigPropertyEntity.setPerformer(wpcp
									.getPerformer());
							wfProcessConfigPropertyEntity.setTaskDefKey(wpcp
									.getTaskDefKey());
							wfProcessConfigPropertyEntity
									.setGroupPerformer(wpcp.getGroupPerformer());

							managementService
									.executeCommand(new InsertProcessConfigPropertyCmd(
											wfProcessConfigPropertyEntity));
						}
					}

				}
			}
		}
	}

	@Override
	@Transactional
	public void reBindProcessToModule(String moduleId, WfProcessDefinition wpd)
			throws Exception {
		WfModuleConfigEntity eo = new WfModuleConfigEntity();
		if (CommonUtil.stringNullOrEmpty(moduleId) || wpd == null) {
			throw new NullPointerException("moduleId不能为空且流程定义对象不能为空！");
		}
		// 更新老的事项绑定关系isLatest为false
		managementService.executeCommand(new UpdateIsLatestByModuleIdCmd(
				moduleId, null, false));

		String key = wpd.getKey();
		// 查询模块配置的所有流程，可不为islatest的。
		List<WfModuleConfigEntity> wmceList = managementService
				.executeCommand(new SelectWfModuleConfigByParamCmd(null,
						moduleId, null, key, null));

		List<WfModuleConfigEntity> matchList = new ArrayList<WfModuleConfigEntity>();
		if (wmceList != null && !wmceList.isEmpty()) {
			for (WfModuleConfigEntity wmce : wmceList) {
				if (wmce != null && wmce.getProcDefKey().equals(wpd.getKey())) {
					matchList.add(wmce);
				}
			}
		}

		if (!matchList.isEmpty()) {
			// 是否有绑定当前传进来的流程的标识
			boolean isCurrentFlag = false;
			// 绑定过流程
			for (WfModuleConfigEntity match : matchList) {
				// 流程定义ID都相同,更新流程定义的状态即可
				if (wpd.getId().equals(match.getProcDefId())) {
					// 连流程定义ID都相等，只要更新islatest状态即可
					managementService
							.executeCommand(new UpdateIsLatestByModuleIdCmd(
									moduleId, wpd.getId(), true));
					isCurrentFlag = true;
					break;
				}
			}

			if (!isCurrentFlag) {
				// 绑定的是历史流程版本
				// 取版本最大的历史版本，获取所有的流程绑定关系和流程外围配置属性，进行拷贝
				int version = -1;
				String deploymentId = "";
				for (WfModuleConfigEntity match : matchList) {
					ProcessDefinition pDefine = repositoryService
							.createProcessDefinitionQuery()
							.processDefinitionId(match.getProcDefId())
							.singleResult();
					if (pDefine.getVersion() > version) {
						version = pDefine.getVersion();
						deploymentId = pDefine.getDeploymentId();
					}
				}
				if (version > 0) {
					ProcessDefinition pdf = repositoryService
							.createProcessDefinitionQuery()
							.processDefinitionKey(wpd.getKey())
							.processDefinitionVersion(version)
							.deploymentId(deploymentId).singleResult();
					String processDefId = pdf.getId();

					// 创建新的绑定关系
					String uuId = new PKGenerator().getNextId();
					eo.setId(uuId);
					eo.setModuleId(moduleId);
					eo.setProcDefId(wpd.getId());
					eo.setProcDefKey(wpd.getKey());
					eo.setProcDefName(wpd.getName());
					eo.setIsLatest(true);
					managementService.executeCommand(new InsertModuleConfigCmd(
							eo));

					List<WfProcessConfigProperty> wfProcessConfigPropertyList = managementService
							.executeCommand(new FindProcessConfigProperty(
									processDefId, moduleId, null));
					if (wfProcessConfigPropertyList != null
							&& !wfProcessConfigPropertyList.isEmpty()) {
						for (WfProcessConfigProperty wpcp : wfProcessConfigPropertyList) {
							wpcp.setConfigId(uuId);

							wpcp.setId(new PKGenerator().getNextId());

							// 再插入配置信息
							WfProcessConfigPropertyEntity wfProcessConfigPropertyEntity = new WfProcessConfigPropertyEntity();
							wfProcessConfigPropertyEntity.setAlias(wpcp
									.getAlias());
							wfProcessConfigPropertyEntity.setBindForm(wpcp
									.getBindForm());
							wfProcessConfigPropertyEntity.setConfigId(wpcp
									.getConfigId());
							wfProcessConfigPropertyEntity.setDuration(wpcp
									.getDuration());

							wfProcessConfigPropertyEntity.setDurationUnit(wpcp
									.getDurationUnit());
							wfProcessConfigPropertyEntity.setId(wpcp.getId());
							wfProcessConfigPropertyEntity.setPerformer(wpcp
									.getPerformer());
							wfProcessConfigPropertyEntity.setTaskDefKey(wpcp
									.getTaskDefKey());
							wfProcessConfigPropertyEntity
									.setGroupPerformer(wpcp.getGroupPerformer());

							managementService
									.executeCommand(new InsertProcessConfigPropertyCmd(
											wfProcessConfigPropertyEntity));
						}
					}

				}
			}
		}

		/*
		 * // 更新老的事项绑定关系isLatest为false managementService.executeCommand(new
		 * UpdateIsLatestByModuleIdCmd( moduleId, null, false));
		 * 
		 * // 创建新的绑定关系 eo.setId(new PKGenerator().getNextId());
		 * eo.setModuleId(moduleId); eo.setProcDefId(wpd.getId());
		 * eo.setProcDefKey(wpd.getKey()); eo.setProcDefName(wpd.getName());
		 * eo.setIsLatest(true); managementService.executeCommand(new
		 * InsertModuleConfigCmd(eo));
		 */
	}

	@Override
	public void unBindProcessFromModule(String moduleId, WfProcessDefinition wpd)
			throws Exception {
		if (CommonUtil.stringNullOrEmpty(moduleId) || wpd == null) {
			throw new NullPointerException("moduleId不能为空且流程定义对象不能为空！");
		}
		managementService.executeCommand(new UpdateIsLatestByModuleIdCmd(
				moduleId, null, false));
	}

	@Override
	public List<String> getBindModuleIdsByProcessDefKey(
			String processDefinitionKey) throws Exception {
		if (CommonUtil.stringNullOrEmpty(processDefinitionKey)) {
			throw new NullPointerException("processDefinitionKey不能为空！");
		}
		// return
		// manageDao.getBindModuleIdsByProcessDefKey(processDefinitionKey);
		return managementService
				.executeCommand(new GetBindModuleIdsByProcessDefKeyCmd(
						processDefinitionKey));
	}

	@Override
	public List<String> getBindModuleIdsByProcessDefKeyAndTenant(String processDefinitionKey,String tenantId) throws Exception {
		if (CommonUtil.stringNullOrEmpty(processDefinitionKey)) {
			throw new NullPointerException("processDefinitionKey不能为空！");
		}

		return managementService
				.executeCommand(new GetBindModuleIdsByProcessDefKeyAndTenantCmd(processDefinitionKey,tenantId));
	}
	
	
	@Override
	public List<WfUniteColumn> findWfUniteColumns(String appId,
			String tenantId, String engineName, String taskType)
			throws Exception {
		List<WfUniteColumn> result = new ArrayList<WfUniteColumn>();
		List<WfUniteColumnsEntity> list = managementService
				.executeCommand(new FindWfUniteColumnsCmd(appId, tenantId,
						engineName, taskType));
		if (list != null) {
			for (WfUniteColumnsEntity wce : list) {
				WfUniteColumn wc = new WfUniteColumn();
				wc.setColumnId(wce.getColumnId());
				wc.setColumnName(wce.getColumnName());
				wc.setId(wce.getId());
				wc.setConfigId(wce.getConfigId());
				wc.setIsShow(wce.getIsShow());
				wc.setIsTitle(wce.getIsTitle());
				wc.setRevision(wce.getRevision());
				wc.setSn(wce.getSn());
				result.add(wc);
			}
		}
		return result;
	}

	@Override
	public void saveWfUniteConfig(WfUniteConfig data) throws Exception {
		WfUniteConfigEntity dataEntity = new WfUniteConfigEntity();
		dataEntity.setAppId(data.getAppId());
		dataEntity.setEngineName(data.getEngineName());
		dataEntity.setId(data.getId());
		dataEntity.setOnlyTitle(data.getOnlyTitle());
		dataEntity.setRevision(data.getRevision());
		dataEntity.setTaskList(data.getTaskList());
		dataEntity.setTenantId(data.getTenantId());
		managementService.executeCommand(new SaveWfUniteConfigCmd(dataEntity));
	}

	@Override
	public void saveWfUniteColumn(WfUniteColumn data) throws Exception {
		WfUniteColumnsEntity dataEntity = new WfUniteColumnsEntity();
		dataEntity.setColumnId(data.getColumnId());
		dataEntity.setColumnName(data.getColumnName());
		dataEntity.setConfigId(data.getConfigId());
		dataEntity.setId(data.getId());
		dataEntity.setIsShow(data.getIsShow());
		dataEntity.setIsTitle(data.getIsTitle());
		dataEntity.setRevision(data.getRevision());
		dataEntity.setSn(data.getSn());
		managementService.executeCommand(new SaveWfUniteColumnCmd(dataEntity));
	}

	@Override
	public List<WfUniteConfig> findWfUniteConfig(String appId, String tenantId,
			String engineName, String taskType) throws Exception {
		List<WfUniteConfig> result = new ArrayList<WfUniteConfig>();
		List<WfUniteConfigEntity> list = managementService
				.executeCommand(new FindWfUniteConfigCmd(appId, tenantId,
						engineName, taskType));
		if (list != null) {
			for (WfUniteConfigEntity data : list) {
				WfUniteConfig wc = new WfUniteConfig();
				wc.setAppId(data.getAppId());
				wc.setEngineName(data.getEngineName());
				wc.setId(data.getId());
				wc.setOnlyTitle(data.getOnlyTitle());
				wc.setRevision(data.getRevision());
				wc.setTaskList(data.getTaskList());
				wc.setTenantId(data.getTenantId());
				result.add(wc);
			}
		}
		return result;
	}

	@Override
	public WfProcessConfigProperty findProcessConfigProperty(
			String processDefinitionId, String moduleId, String taskDefKey)
			throws Exception {
		if (CommonUtil.stringNullOrEmpty(moduleId)) {
			throw new NullPointerException("moduleId不能为空！");
		}
		if (CommonUtil.stringNullOrEmpty(taskDefKey)) {
			throw new NullPointerException("taskDefKey不能为空！");
		}
		List<WfProcessConfigProperty> wfProcessConfigPropertyList = managementService
				.executeCommand(new FindProcessConfigProperty(
						processDefinitionId, moduleId, taskDefKey));
		if (wfProcessConfigPropertyList != null
				&& !wfProcessConfigPropertyList.isEmpty()) {
			return wfProcessConfigPropertyList.get(0);
		}
		return null;
	}

	@Override
	@Transactional
	public void saveProcessConfigProperty(String processDefinitionId,
			String taskDefinitionId, String moduleId,
			WfProcessConfigProperty wpcp) {
		if (CommonUtil.stringNullOrEmpty(processDefinitionId)) {
			throw new NullPointerException("processDefinitionId不能为空！");
		}
		if (CommonUtil.stringNullOrEmpty(moduleId)) {
			throw new NullPointerException("moduleId不能为空！");
		}
		if (CommonUtil.stringNullOrEmpty(taskDefinitionId)) {
			throw new NullPointerException("taskDefinitionId不能为空！");
		}
		// 主键
		String id = new PKGenerator().getNextId();
		if (wpcp == null) {
			wpcp = new WfProcessConfigProperty();
		}
		wpcp.setId(id);
		wpcp.setTaskDefKey(taskDefinitionId);
		// selectWfModuleConfig
		// GetProcessInfoByModuleIdCmd
		WfModuleConfigEntity wfModuleConfigEntity = managementService
				.executeCommand(new GetProcessInfoByModuleIdCmd(moduleId));
		if (wfModuleConfigEntity != null) {
			String configId = wfModuleConfigEntity.getId();
			wpcp.setConfigId(configId);
			// 先删除配置信息delete by configId and taskDefKey
			managementService
					.executeCommand(new DeleteProcessConfigPropertyByConfigIdAndTaskDefIdCommand(
							configId, taskDefinitionId));

			// 再插入配置信息
			WfProcessConfigPropertyEntity wfProcessConfigPropertyEntity = new WfProcessConfigPropertyEntity();
			wfProcessConfigPropertyEntity.setAlias(wpcp.getAlias());
			wfProcessConfigPropertyEntity.setBindForm(wpcp.getBindForm());
			wfProcessConfigPropertyEntity.setConfigId(wpcp.getConfigId());
			wfProcessConfigPropertyEntity.setDuration(wpcp.getDuration());

			wfProcessConfigPropertyEntity.setDurationUnit(wpcp
					.getDurationUnit());
			wfProcessConfigPropertyEntity.setId(wpcp.getId());
			wfProcessConfigPropertyEntity.setPerformer(wpcp.getPerformer());
			wfProcessConfigPropertyEntity.setTaskDefKey(wpcp.getTaskDefKey());
			wfProcessConfigPropertyEntity.setGroupPerformer(wpcp
					.getGroupPerformer());

			managementService
					.executeCommand(new InsertProcessConfigPropertyCmd(
							wfProcessConfigPropertyEntity));
		}

	}

	@Override
	public WfPageList<WfGroup, WfGroupParam> queryGroups(WfGroupParam params)
			throws Exception {
		WfPageList<WfGroup, WfGroupParam> wfGroupPageList = new WfPageList<WfGroup, WfGroupParam>();

		if (null != params) {
			GroupQuery query = identityService.createGroupQuery();

			boolean isPaged = params.isPaged();

			if (!CommonUtil.stringNullOrEmpty(params.getId())) {
				query.groupId(params.getId());
			}
			if (!CommonUtil.stringNullOrEmpty(params.getMember())) {
				query.groupMember(params.getMember());
			}
			if (!CommonUtil.stringNullOrEmpty(params.getName())) {
				query.groupName(params.getName());
			}
			if (!CommonUtil.stringNullOrEmpty(params.getNameLike())) {
				query.groupNameLike(params.getNameLike());
			}
			if (!CommonUtil.stringNullOrEmpty(params.getPotentialStarter())) {
				query.potentialStarter(params.getPotentialStarter());
			}
			if (!CommonUtil.stringNullOrEmpty(params.getType())) {
				query.groupType(params.getType());
			}

			boolean orderById = params.getOrderById();
			boolean orderByName = params.getOrderByName();
			boolean orderByType = params.getOrderByType();
			if (orderById) {
				query.orderByGroupId();
				setOrder(query, params);
			}
			if (orderByName) {
				query.orderByGroupName();
				setOrder(query, params);
			}
			if (orderByType) {
				query.orderByGroupType();
				setOrder(query, params);
			}

			List<Group> groupList = new ArrayList<Group>();
			if (isPaged) {
				groupList = query.listPage((int) params.getStart(),
						(int) params.getSize());
				long total = query.count();
				params.setTotal(total);
			} else {
				groupList = query.list();
			}

			wfGroupPageList.setWfQuery(params);
			if (null != groupList && !groupList.isEmpty()) {
				for (Group group : groupList) {
					WfGroup wfGroup = new WfGroup();
					wfGroup.setId(group.getId());
					wfGroup.setName(group.getName());
					wfGroup.setType(group.getType());
					wfGroupPageList.add(wfGroup);
				}
			}

		}
		return wfGroupPageList;
	}

	@Override
	public WfGroup getGroupById(String groupId) throws Exception {
		if (CommonUtil.stringNullOrEmpty(groupId)) {
			throw new NullPointerException("群组ID为空！");
		}
		Group group = identityService.createGroupQuery().groupId(groupId)
				.singleResult();
		WfGroup wfGroup = null;
		if (null != group) {
			wfGroup = new WfGroup();
			wfGroup.setId(group.getId());
			wfGroup.setName(group.getName());
			wfGroup.setType(group.getType());
		}
		return wfGroup;
	}

	@Override
	public WfPageList<WfOperateLogBean, WfPageParam> findWfOperateLog(
			Map<String, Object> parameters, int firstResult, int maxResults)
			throws Exception {
		WfPageList<WfOperateLogBean, WfPageParam> result = new WfPageList<WfOperateLogBean, WfPageParam>();
		WfPageList<WfOperateLogEntity, WfPageParam> wfoles = managementService
				.executeCommand(new FindWfOperateLogByConditionCmd(parameters,
						firstResult, maxResults));
		if (wfoles != null) {
			result.setWfQuery(wfoles.getWfQuery());
			for (WfOperateLogEntity wfole : wfoles.getDatas()) {
				WfOperateLogBean bean = new WfOperateLogBean();
				bean.setAppId(wfole.getAppId());
				bean.setCallTime(wfole.getCallTime());
				bean.setClassName(wfole.getClassName());
				bean.setEngineName(wfole.getEngineName());
				bean.setId(wfole.getId());
				bean.setIp(wfole.getIp());
				bean.setMethodAlias(wfole.getMethodAlias());
				bean.setMethodName(wfole.getMethodName());
				String res = wfole.getResult();
				bean.setResult(res);
				bean.setTenantId(wfole.getTenantId());
				String userId = wfole.getUserId();
				bean.setUserId(userId);
				bean.setUseTime(wfole.getUseTime());
				result.add(bean);
			}
		}
		return result;
	}

	@Override
	public WfOperateLogBean getWfOperateLogById(String id) throws Exception {
		WfOperateLogBean bean = new WfOperateLogBean();
		WfOperateLogEntity wfole = managementService
				.executeCommand(new FindWfOperateLogByIdCmd(id));
		WfOperateLogDataEntity wold = managementService
				.executeCommand(new FindWfOperateLogDataByIdCmd(id));
		if (wfole != null) {
			bean.setAppId(wfole.getAppId());
			bean.setCallTime(wfole.getCallTime());
			bean.setClassName(wfole.getClassName());
			bean.setEngineName(wfole.getEngineName());
			bean.setId(wfole.getId());
			bean.setIp(wfole.getIp());
			bean.setMethodAlias(wfole.getMethodAlias());
			bean.setMethodName(wfole.getMethodName());
			bean.setResult(wfole.getResult());
			bean.setTenantId(wfole.getTenantId());
			bean.setUserId(wfole.getUserId());
			bean.setUseTime(wfole.getUseTime());
		}
		if (wold != null) {
			bean.setArgsObject(wold.getArgsObject());
			bean.setArgsValue(wold.getArgsValue());
			bean.setException(wold.getException());
			bean.setReturnObject(wold.getReturnObject());
			bean.setReturnValue(wold.getReturnValue());
		}
		return bean;
	}

	@Override
	public WfPageList<WfModuleDelegate, WfModuleDelegateParam> getModuleDelegateByParam(
			WfModuleDelegateParam wfModuleDelegateParam) {
		WfPageList<WfModuleDelegate, WfModuleDelegateParam> wfModuleDelegatePageList = new WfPageList<WfModuleDelegate, WfModuleDelegateParam>();
		WfPageList<WfModuleDelegateEntity, WfModuleDelegateParam> wfPageList = managementService
				.executeCommand(new GetModuleDelegateByParam(
						wfModuleDelegateParam));
		if (null != wfPageList && !wfPageList.getDatas().isEmpty()) {
			for (WfModuleDelegateEntity entity : wfPageList.getDatas()) {
				WfModuleDelegate wfModuleDelegate = new WfModuleDelegate();
				wfModuleDelegate.setAppId(entity.getAppId());
				wfModuleDelegate.setConfirmTime(entity.getConfirmTime());
				wfModuleDelegate
						.setDelegateEndTime(entity.getDelegateEndTime());
				wfModuleDelegate.setDelegateId(entity.getDelegateId());

				String delegateRangeShow = "";
				if (entity.getDelegateRange().equals(
						WfDelegateRange.ALL_MODULE.toString())) {
					delegateRangeShow = "全部事项";
				} else if (entity.getDelegateRange().equals(
						WfDelegateRange.SOME_MODULE.toString())) {
					delegateRangeShow = "个别事项";
				}
				wfModuleDelegate.setDelegateRange(delegateRangeShow);

				wfModuleDelegate.setDelegateStartTime(entity
						.getDelegateStartTime());

				String delegateStatShow = "";
				if (entity.getDelegateStat().equals(
						WfDelegateStatus.DELEGATE_FINISH.toString())) {
					delegateStatShow = "委托结束";
				} else if (entity.getDelegateStat().equals(
						WfDelegateStatus.DELEGATING.toString())) {
					if (new Date().after(entity.getDelegateEndTime())) {
						delegateStatShow = "委托结束";
					} else {
						delegateStatShow = "委托中";
					}
				} else if (entity.getDelegateStat().equals(
						WfDelegateStatus.RESOLVED.toString())) {
					delegateStatShow = "拒绝委托";
				} else if (entity.getDelegateStat().equals(
						WfDelegateStatus.WAITING.toString())) {
					delegateStatShow = "等待确认";
				}

				wfModuleDelegate.setDelegateStat(delegateStatShow);

				String delegateTypeShow = "";
				if (entity.getDelegateType().equals(
						WfDelegateType.FROM_UP.toString())) {
					delegateTypeShow = "上级委托";
				} else if (entity.getDelegateType().equals(
						WfDelegateType.TO_OTHER.toString())) {
					delegateTypeShow = "委托别人";
				}
				wfModuleDelegate.setDelegateType(delegateTypeShow);

				wfModuleDelegate.setDesignatorId(entity.getDesignatorId());
				wfModuleDelegate.setDesignatorName(entity.getDesignatorName());
				wfModuleDelegate.setDesigneeId(entity.getDesigneeId());
				wfModuleDelegate.setDesigneeName(entity.getDesigneeName());
				wfModuleDelegate.setTenantId(entity.getTenantId());

				// 查询事项名称与事项ID
				List<WfModuleDelegateInfoEntity> wfModuleDelegateInfoEntitys = managementService
						.executeCommand(new GetModuleDelegateInfoByDelegateId(
								entity.getDelegateId()));
				List<WfModuleDelegateInfo> wfModuleDelegateInfos = null;
				if (null != wfModuleDelegateInfoEntitys
						&& !wfModuleDelegateInfoEntitys.isEmpty()) {
					wfModuleDelegateInfos = new ArrayList<WfModuleDelegateInfo>();
					for (WfModuleDelegateInfoEntity enti : wfModuleDelegateInfoEntitys) {
						if (enti != null) {
							WfModuleDelegateInfo info = new WfModuleDelegateInfo();
							info.setDelegateId(enti.getDelegateId());
							info.setId(enti.getId());
							info.setMouduleId(enti.getMouduleId());
							info.setMouduleName(enti.getMouduleName());
							wfModuleDelegateInfos.add(info);
						}
					}
				}
				wfModuleDelegate
						.setWfModuleDelegateInfos(wfModuleDelegateInfos);
				wfModuleDelegatePageList.add(wfModuleDelegate);
			}

			wfModuleDelegatePageList.setWfQuery(wfPageList.getWfQuery());
		}
		return wfModuleDelegatePageList;
	}

	@Override
	@Transactional
	public Map<String, String> saveModuleDelegate(
			WfModuleDelegate wfModuleDelegate,
			List<WfModuleDelegateInfo> wfModuleDelegateInfoList)
			throws Exception {
		// InsertWfModuleDelegateCmd
		// insertWfModuleDelegate
		Map<String, String> resultMap = validateAddDelegate(wfModuleDelegate,
				wfModuleDelegateInfoList);
		if (!"success".equals(resultMap.get("status"))) {
			return resultMap;
		}
		String pk = new PKGenerator().getNextId();
		WfModuleDelegateEntity entity = new WfModuleDelegateEntity();
		entity.setAppId(wfModuleDelegate.getAppId());
		entity.setConfirmTime(wfModuleDelegate.getConfirmTime());
		entity.setDelegateEndTime(wfModuleDelegate.getDelegateEndTime());
		entity.setDelegateId(pk);
		entity.setDelegateRange(wfModuleDelegate.getDelegateRange());
		entity.setDelegateStartTime(wfModuleDelegate.getDelegateStartTime());
		entity.setDelegateStat(wfModuleDelegate.getDelegateStat());
		entity.setDelegateType(wfModuleDelegate.getDelegateType());
		entity.setDesignatorId(wfModuleDelegate.getDesignatorId());
		entity.setDesignatorName(wfModuleDelegate.getDesignatorName());
		entity.setDesigneeId(wfModuleDelegate.getDesigneeId());
		entity.setDesigneeName(wfModuleDelegate.getDesigneeName());
		entity.setId(pk);
		entity.setTenantId(wfModuleDelegate.getTenantId());

		managementService.executeCommand(new InsertWfModuleDelegateCmd(entity));

		// pk作为INFO表的外键插入
		List<WfModuleDelegateInfoEntity> entityList = null;
		if (null != wfModuleDelegateInfoList
				&& !wfModuleDelegateInfoList.isEmpty()) {
			entityList = new ArrayList<WfModuleDelegateInfoEntity>();
			for (WfModuleDelegateInfo info : wfModuleDelegateInfoList) {
				if (null != info) {
					String infoPk = new PKGenerator().getNextId();
					WfModuleDelegateInfoEntity e = new WfModuleDelegateInfoEntity();
					e.setDelegateId(pk);
					e.setId(infoPk);
					e.setMouduleId(info.getMouduleId());
					e.setMouduleName(info.getMouduleName());
					entityList.add(e);
				}
			}
		}
		if (entityList != null) {
			for (WfModuleDelegateInfoEntity infoEntity : entityList) {
				managementService
						.executeCommand(new InsertWfModuleDelegateInfoCmd(
								infoEntity));
			}
		}
		return resultMap;

	}

	/**
	 * 校验新增委托，不允许存在重复的时间将重复的事项委托给他人
	 * 
	 * @param wfModuleDelegate
	 * @param wfModuleDelegateInfoList
	 * @return
	 */
	private Map<String, String> validateAddDelegate(
			WfModuleDelegate wfModuleDelegate,
			List<WfModuleDelegateInfo> wfModuleDelegateInfoList) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("status", "success");
		map.put("message", "委托成功");

		Date start = wfModuleDelegate.getDelegateStartTime();// 委托开始时间
		Date end = wfModuleDelegate.getDelegateEndTime();// 委托结束时间
		String designeeId = wfModuleDelegate.getDesigneeId();// 被委托人ID
		String designatorId = wfModuleDelegate.getDesignatorId();// 委托人ID
		if (designeeId == null || "".equals(designeeId.trim())) {
			map.put("status", "existDesigneeIsDesignator");
			map.put("message", "被委托人不能为空");
			return map;
		}
		if (designatorId == null || "".equals(designatorId.trim())) {
			map.put("status", "existDesigneeIsDesignator");
			map.put("message", "委托人不能为空");
			return map;
		}
		if (start == null) {
			map.put("status", "existDesigneeIsDesignator");
			map.put("message", "委托开始时间不能为空");
			return map;
		}
		if (end == null) {
			map.put("status", "existDesigneeIsDesignator");
			map.put("message", "委托结束时间不能为空");
			return map;
		}
		if (start.after(end) || start.equals(end)) {
			map.put("status", "existDesigneeIsDesignator");
			map.put("message", "委托开始时间不能大于或等于委托结束时间");
			return map;
		}

		// 委托校验：根据当前委托的委托人和被委托人进行查询、如果在委托时间段已经有全部委托或对应的事项委托、并且状态为(委托状态：1、等待确认
		// 2、委托中 3)，则校验不通过
		// 事项ID
		String moduleIds = "";
		if (wfModuleDelegateInfoList != null
				&& !wfModuleDelegateInfoList.isEmpty()) {
			for (WfModuleDelegateInfo wmdi : wfModuleDelegateInfoList) {
				String moduleId = wmdi.getMouduleId();
				if (moduleId != null && !"".equals(moduleId.trim())) {
					moduleIds += "'" + moduleId + "',";
				}
			}
			if (!"".equals(moduleIds.trim())) {
				moduleIds = moduleIds.substring(0, moduleIds.length() - 1);
			} else {
				moduleIds = "''";
			}
		} else {
			moduleIds = "''";
		}
		int countAssignee = managementService
				.executeCommand(new GetWfDelegateSomeRangeCountCmd(
						wfModuleDelegate.getDelegateId(), moduleIds,
						designatorId, designeeId, wfModuleDelegate
								.getDelegateRange(), start, end));

		if (countAssignee > 0) {
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			// 已存在委托
			map.put("status", "existDesigneeIsDesignator");
			map.put("message", "委托人[" + wfModuleDelegate.getDesignatorName()
					+ "]或被委托人[" + wfModuleDelegate.getDesigneeName() + "]在时间段["
					+ sdf.format(start) + "-" + sdf.format(end)
					+ "]已有事项委托，这种情况不允许再给他设置委托，请选择其他人员或时间段");
			return map;
		}

		/*
		 * //委托的是个别事项
		 * if(WfConstants.WF_DELEGATE_RANGE_SOME.equals(wfModuleDelegate
		 * .getDelegateRange())){ // 事项ID if(wfModuleDelegateInfoList!=null &&
		 * !wfModuleDelegateInfoList.isEmpty()){ for(WfModuleDelegateInfo wmdi :
		 * wfModuleDelegateInfoList){ if(wmdi!=null){
		 * //对每个待委托的事项遍历，如果没有重复的委托，可以进行 Date start =
		 * wfModuleDelegate.getDelegateStartTime();//委托开始时间 Date end =
		 * wfModuleDelegate.getDelegateEndTime();//委托结束时间 String moduleId =
		 * wmdi.getMouduleId();//模块事项ID String designeeId =
		 * wfModuleDelegate.getDesigneeId();//被委托人ID String designatorId =
		 * wfModuleDelegate.getDesignatorId();
		 *//******
		 * 个别委托与全部委托是否存在冲突？ begine
		 ******/
		/*
		 * int countAll = managementService.executeCommand(new
		 * GetWfDelegateSomeRangeCountCmd(null, designatorId, null,
		 * WfConstants.WF_DELEGATE_RANGE_ALL, start, end)); if(countAll>0){
		 * //已存在委托 map.put("status", "existAllDelegate"); map.put("message",
		 * "已存在全部委托"); return map; }
		 *//******
		 * 个别委托与全部委托是否存在冲突？ end
		 ******/
		/*
						
						*//******
		 * 个别委托与个别委托是否存在冲突？ begine
		 ******/
		/*
		 * int count = managementService.executeCommand(new
		 * GetWfDelegateSomeRangeCountCmd(moduleId, null, null,
		 * WfConstants.WF_DELEGATE_RANGE_SOME, start, end)); if(count>0){
		 * //已存在委托 map.put("status", "existSome"); map.put("message",
		 * "已存在个别委托"); return map; }
		 * //如果不存在重复的时间事项有委托，那就需要遍历已有的委托中，执行人是否存在已经把这个事项委托出去，如果有，也是非法的，不让新增委托
		 * int countOut = managementService.executeCommand(new
		 * GetWfDelegateSomeRangeCountCmd(moduleId, designeeId, null,
		 * WfConstants.WF_DELEGATE_RANGE_SOME, start, end)); if(countOut>0){
		 * //已存在委托 map.put("status", "existDesigneeIsDesignator");
		 * map.put("message", "被委托人已经将该事项委托出去，这种情况不允许，请选择其他人员"); return map; }
		 * int countOut2 = managementService.executeCommand(new
		 * GetWfDelegateSomeRangeCountCmd(null, designeeId, null,
		 * WfConstants.WF_DELEGATE_RANGE_ALL, start, end)); if(countOut2>0){
		 * //已存在委托 map.put("status", "existDesigneeIsDesignator");
		 * map.put("message", "被委托人已经将该事项全部委托出去，这种情况不允许，请选择其他人员"); return map; }
		 *//******
		 * 个别委托与个别委托是否存在冲突？ end
		 ******/
		/*
		 * 
		 * }
		 * 
		 * }
		 * 
		 * } } else {// 新增的是全部委托 if (wfModuleDelegate != null) { //
		 * 对每个待委托的事项遍历，如果没有重复的委托，可以进行 Date start =
		 * wfModuleDelegate.getDelegateStartTime();// 委托开始时间 Date end =
		 * wfModuleDelegate.getDelegateEndTime();// 委托结束时间 String designeeId =
		 * wfModuleDelegate.getDesigneeId();// 被委托人ID String designatorId =
		 * wfModuleDelegate.getDesignatorId();
		 *//******
		 * 全部委托与个别委托是否存在冲突？ begine
		 ******/
		/*
		 * int count = managementService .executeCommand(new
		 * GetWfDelegateSomeRangeCountCmd( null, designatorId, null,
		 * WfConstants.WF_DELEGATE_RANGE_SOME, start, end)); if (count > 0) { //
		 * 已存在委托 map.put("status", "existSome"); map.put("message", "已存在个别委托");
		 * return map; } int countAll = managementService .executeCommand(new
		 * GetWfDelegateSomeRangeCountCmd( null, designatorId, null,
		 * WfConstants.WF_DELEGATE_RANGE_ALL, start, end)); if (countAll > 0) {
		 * // 已存在委托 map.put("status", "existAllDelegate"); map.put("message",
		 * "已存在全部委托"); return map; } // 被委托人在同一个时间段内不允许有其他委托任务 int countAssignee
		 * = managementService .executeCommand(new
		 * GetWfDelegateSomeRangeCountCmd( null, designeeId, null,
		 * WfConstants.WF_DELEGATE_RANGE_ALL, start, end)); if (countAssignee >
		 * 0) { // 已存在委托 map.put("status", "existDesigneeIsDesignator");
		 * map.put("message", "被委托人已有事项委托出去，这种情况不允许再给他设置全部委托，请选择其他人员"); return
		 * map; }
		 * 
		 * } }
		 */
		return map;
	}

	@Override
	public Map<String, String> saveModuleDelegateArr(
			WfModuleDelegate wfModuleDelegate,
			WfModuleDelegateInfo[] wfModuleDelegateInfoArr) throws Exception {
		List<WfModuleDelegateInfo> wfModuleDelegateInfoList = null;
		if (wfModuleDelegateInfoArr != null
				&& wfModuleDelegateInfoArr.length > 0) {
			wfModuleDelegateInfoList = new ArrayList<WfModuleDelegateInfo>();
			for (WfModuleDelegateInfo info : wfModuleDelegateInfoArr) {
				if (info != null) {
					wfModuleDelegateInfoList.add(info);
				}
			}
		}
		return saveModuleDelegate(wfModuleDelegate, wfModuleDelegateInfoList);

	}

	@Override
	public void updateModuleDelegateState(String delegateId,
			String delegateState, boolean acceptFlag) throws Exception {
		if (!CommonUtil.stringNullOrEmpty(delegateId)
				&& !CommonUtil.stringNullOrEmpty(delegateState)) {
			if (acceptFlag) {
				managementService
						.executeCommand(new UpdateModuleDelegateStateCmd(
								delegateId, delegateState, new Date()));
			} else {
				managementService
						.executeCommand(new UpdateModuleDelegateStateCmd(
								delegateId, delegateState, null));
			}
		} else {
			throw new NullPointerException("参数非法！");
		}
	}

	@Override
	@Transactional
	public void deleteModuleDelegate(String delegateId) throws Exception {
		if (!CommonUtil.stringNullOrEmpty(delegateId)) {
			managementService
					.executeCommand(new DeleteModuleDelegateInfoByDelegateIdCmd(
							delegateId));
			managementService.executeCommand(new DeleteModuleDelegateByIdCmd(
					delegateId));
		} else {
			throw new NullPointerException("参数非法！");
		}
	}

	@Override
	@Transactional
	public Map<String, String> modifyModuleDelegate(
			WfModuleDelegate wfModuleDelegate,
			List<WfModuleDelegateInfo> wfModuleDelegateInfoList)
			throws Exception {

		Map<String, String> resultMap = validateAddDelegate(wfModuleDelegate,
				wfModuleDelegateInfoList);
		if (!"success".equals(resultMap.get("status"))) {
			return resultMap;
		}

		WfModuleDelegateEntity entity = new WfModuleDelegateEntity();
		entity.setAppId(wfModuleDelegate.getAppId());
		entity.setConfirmTime(wfModuleDelegate.getConfirmTime());
		entity.setDelegateEndTime(wfModuleDelegate.getDelegateEndTime());
		entity.setDelegateId(wfModuleDelegate.getDelegateId());
		entity.setDelegateRange(wfModuleDelegate.getDelegateRange());
		entity.setDelegateStartTime(wfModuleDelegate.getDelegateStartTime());
		entity.setDelegateStat(wfModuleDelegate.getDelegateStat());
		entity.setDelegateType(wfModuleDelegate.getDelegateType());
		entity.setDesignatorId(wfModuleDelegate.getDesignatorId());
		entity.setDesignatorName(wfModuleDelegate.getDesignatorName());
		entity.setDesigneeId(wfModuleDelegate.getDesigneeId());
		entity.setDesigneeName(wfModuleDelegate.getDesigneeName());
		entity.setId(wfModuleDelegate.getDelegateId());
		entity.setTenantId(wfModuleDelegate.getTenantId());
		// 修改委托表
		managementService.executeCommand(new UpdateWfModuleDelegateCmd(entity));

		// 删除委托ID对应的所有委托信息表记录
		managementService
				.executeCommand(new DeleteModuleDelegateInfoByDelegateIdCmd(
						wfModuleDelegate.getDelegateId()));

		// 新增新的委托信息
		List<WfModuleDelegateInfoEntity> entityList = null;
		if (null != wfModuleDelegateInfoList
				&& !wfModuleDelegateInfoList.isEmpty()) {
			entityList = new ArrayList<WfModuleDelegateInfoEntity>();
			for (WfModuleDelegateInfo info : wfModuleDelegateInfoList) {
				if (null != info) {
					String infoPk = new PKGenerator().getNextId();
					WfModuleDelegateInfoEntity e = new WfModuleDelegateInfoEntity();
					e.setDelegateId(wfModuleDelegate.getDelegateId());
					e.setId(infoPk);
					e.setMouduleId(info.getMouduleId());
					e.setMouduleName(info.getMouduleName());
					entityList.add(e);
				}
			}
		}
		if (entityList != null) {
			for (WfModuleDelegateInfoEntity infoEntity : entityList) {
				managementService
						.executeCommand(new InsertWfModuleDelegateInfoCmd(
								infoEntity));
			}
		}
		return resultMap;
	}

	@Override
	@Transactional
	public Map<String, String> modifyModuleDelegateArr(
			WfModuleDelegate wfModuleDelegate,
			WfModuleDelegateInfo[] wfModuleDelegateInfoArr) throws Exception {
		List<WfModuleDelegateInfo> wfModuleDelegateInfoList = null;
		if (wfModuleDelegateInfoArr != null
				&& wfModuleDelegateInfoArr.length > 0) {
			wfModuleDelegateInfoList = new ArrayList<WfModuleDelegateInfo>();
			for (WfModuleDelegateInfo info : wfModuleDelegateInfoArr) {
				if (info != null) {
					wfModuleDelegateInfoList.add(info);
				}
			}
		}
		return modifyModuleDelegate(wfModuleDelegate, wfModuleDelegateInfoList);
	}

	@Override
	public WfPageList<WfProcessDefinition, WfProcessDefinitionParam> queryProcessDefinitionsAndDeployInfoList(
			WfProcessDefinitionParam wfProcessDefinitionParam) throws Exception {
		
		WfPageList<WfProcessDefinition, WfProcessDefinitionParam> resultPageList = null;
		WfPageList<WfProcessDefinitionAndDeployInfoEntity, WfProcessDefinitionParam> wfPageList = managementService
				.executeCommand(new GetProcessDefinitionAndDeployInfoListCmd(
						wfProcessDefinitionParam));
		if (null != wfPageList && wfPageList.getDatas() != null
				&& !wfPageList.getDatas().isEmpty()) {
			resultPageList = new WfPageList<WfProcessDefinition, WfProcessDefinitionParam>();
			resultPageList.setWfQuery(wfPageList.getWfQuery());
			for (WfProcessDefinitionAndDeployInfoEntity entity : wfPageList
					.getDatas()) {
				WfProcessDefinition wpd = new WfProcessDefinition();
				wpd.setId(entity.getId());
				wpd.setDeploymentId(entity.getDeploymentId());
				wpd.setDeployTime(DateUtil.dateToStr(entity.getDeployTime(),
						DateUtil.YYYY_MM_DD_HH_MM_SS));
				wpd.setDescription(entity.getDescription());
				wpd.setDiagramResourceName(entity.getDgrmResourceName());
				wpd.setKey(entity.getKey());
				wpd.setName(entity.getName());
				wpd.setResourceName(entity.getResourceName());
				wpd.setStartFormKey(entity.getHasStartFormKey());
				wpd.setSuspended(entity.getSuspensionState());
				wpd.setTenantId(entity.getTenantId());
				wpd.setVersion(entity.getVersion());
				resultPageList.add(wpd);
			}
		}
		return resultPageList;
	}

	@Override
	public void saveModel(String modelId, String tenantId, String name,
			String description, String json_xml, String svg_xml)
			throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		Model model = repositoryService.getModel(modelId);

		ObjectNode modelJson = (ObjectNode) objectMapper.readTree(model
				.getMetaInfo());

		modelJson.put(ModelDataJsonConstants.MODEL_NAME, name);
		modelJson.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
		model.setMetaInfo(modelJson.toString());
		model.setName(name);

		repositoryService.saveModel(model);

		repositoryService.addModelEditorSource(model.getId(),
				json_xml.getBytes("utf-8"));

/*		InputStream svgStream = new ByteArrayInputStream(
				svg_xml.getBytes("utf-8"));
		TranscoderInput input = new TranscoderInput(svgStream);

		PNGTranscoder transcoder = new PNGTranscoder();
		// Setup output
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		TranscoderOutput output = new TranscoderOutput(outStream);

		// Do the transformation
		transcoder.transcode(input, output);
		final byte[] result = outStream.toByteArray();
		repositoryService.addModelEditorSourceExtra(model.getId(), result);
		outStream.close();*/

		// yicheng.yang add begine
//		Model modelData = repositoryService.getModel(model.getId());
//		ObjectNode modelNode = (ObjectNode) new ObjectMapper()
//				.readTree(repositoryService.getModelEditorSource(modelData
//						.getId()));
//
//		byte[] bpmnBytes = null;
//		BpmnModel bpmnModel = new BpmnJsonConverter()
//				.convertToBpmnModel(modelNode);
//		bpmnBytes = new BpmnXMLConverter().convertToXML(bpmnModel);

		String processName = model.getName() + ".bpmn20.xml";
		Deployment deployment = repositoryService.createDeployment()
				.name(model.getName()).tenantId(tenantId)
				.addString(processName, json_xml)
				.deploy();
		
		reBindModuleAndProcess(deployment.getId(),tenantId);
		
//		// yicheng.yang add end
//		ApplicationContext context = ((SpringProcessEngineConfiguration) ((ProcessEngineImpl) processEngine)
//				.getProcessEngineConfiguration()).getApplicationContext();
//		// add by minghua.guo 新增时部署自动添加任务监听器，判断新增依据，版本号为1
//		AddTaskListenerUtil.addTaskListener(repositoryService,
//				managementService, context, modelData.getKey());
		
		//新增时部署自动添加任务监听器，判断新增依据，版本号为1
		if(null==tenantId){
			this.addTaskListener(model.getKey());         
		}else{
			this.addTaskListener(model.getKey(),tenantId);
		}
	}

	private void reBindModuleAndProcess(String deployId,String tenantId) throws Exception {

		if (null != deployId && !"".equals(deployId.trim())) {
			// 根据部署id查询流程定义
			WfProcessDefinitionParam wfProcessDefinitionParam = new WfProcessDefinitionParam();
			wfProcessDefinitionParam.setPaged(false);
			wfProcessDefinitionParam.setDeploymentId(deployId);
			WfPageList<WfProcessDefinition, WfProcessDefinitionParam> wPageList = wfRepositoryServiceLocal
					.queryProcessDefinitions(wfProcessDefinitionParam);
			if (null != wPageList && wPageList.getDatas().size() > 0) {
				WfProcessDefinition pd = wPageList.getDatas().get(0);
				// 查询流程绑定的事项，如果有绑定事项，则将所有与事项的绑定关系更新为此最新的流程定义
				List<String> moduleIds =getBindModuleIdsByProcessDefKeyAndTenant(pd.getKey(),tenantId);
				if (null != moduleIds && !moduleIds.isEmpty()) {
					for (String module : moduleIds) {
						/*
						 * unBindProcessFromModule(module, pd);
						 * 
						 * bindProcessToModule(module, pd);
						 */
						reBindProcessToModule(module, pd);
					}
				}
			}

		}
	}

	public RepositoryService getRepositoryService() {
		return repositoryService;
	}

	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	@Override
	public String getDiagram(String processInstanceId,
			String processDefinitionId) throws Exception {
		ProcessInstance processInstance = null;
		ProcessDefinitionEntity processDefinition = null;

		List<String> highLightedFlows = Collections.<String> emptyList();
		List<String> highLightedActivities = Collections.<String> emptyList();

		Map<String, ObjectNode> subProcessInstanceMap = new HashMap<String, ObjectNode>();
		if (processInstanceId != null) {
			processInstance = runtimeService.createProcessInstanceQuery()
					.processInstanceId(processInstanceId).singleResult();
			if (processInstance == null) {
				return null;
			}
			processDefinitionId = processInstance.getProcessDefinitionId();

			List<ProcessInstance> subProcessInstances = runtimeService
					.createProcessInstanceQuery()
					.superProcessInstanceId(processInstanceId).list();
			for (ProcessInstance subProcessInstance : subProcessInstances) {
				String subDefId = subProcessInstance.getProcessDefinitionId();

				String superExecutionId = ((ExecutionEntity) subProcessInstance)
						.getSuperExecutionId();
				ProcessDefinitionEntity subDef = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
						.getDeployedProcessDefinition(subDefId);

				ObjectNode processInstanceJSON = new ObjectMapper()
						.createObjectNode();
				processInstanceJSON.put("processInstanceId",
						subProcessInstance.getId());
				processInstanceJSON.put("superExecutionId", superExecutionId);
				processInstanceJSON.put("processDefinitionId", subDef.getId());
				processInstanceJSON
						.put("processDefinitionKey", subDef.getKey());
				processInstanceJSON.put("processDefinitionName",
						subDef.getName());

				subProcessInstanceMap
						.put(superExecutionId, processInstanceJSON);
			}
		}

		if (processDefinitionId == null) {
			throw new ActivitiException("No process definition id provided");
		}

		processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(processDefinitionId);

		if (processDefinition == null) {
			throw new ActivitiException("Process definition "
					+ processDefinitionId + " could not be found");
		}

		ObjectNode responseJSON = new ObjectMapper().createObjectNode();

		// Process definition
		JsonNode pdrJSON = getProcessDefinitionResponse(processDefinition);

		if (pdrJSON != null) {
			responseJSON.put("processDefinition", pdrJSON);
		}

		// Highlighted activities
		if (processInstance != null) {
			ArrayNode activityArray = new ObjectMapper().createArrayNode();
			ArrayNode flowsArray = new ObjectMapper().createArrayNode();

			highLightedActivities = runtimeService
					.getActiveActivityIds(processInstanceId);
			highLightedFlows = getHighLightedFlows(processInstanceId,
					processDefinition);

			for (String activityName : highLightedActivities) {
				activityArray.add(activityName);
			}

			for (String flow : highLightedFlows)
				flowsArray.add(flow);

			responseJSON.put("highLightedActivities", activityArray);
			responseJSON.put("highLightedFlows", flowsArray);
		}

		// Pool shape, if process is participant in collaboration
		if (processDefinition.getParticipantProcess() != null) {
			ParticipantProcess pProc = processDefinition
					.getParticipantProcess();

			ObjectNode participantProcessJSON = new ObjectMapper()
					.createObjectNode();
			participantProcessJSON.put("id", pProc.getId());
			if (StringUtils.isNotEmpty(pProc.getName())) {
				participantProcessJSON.put("name", pProc.getName());
			} else {
				participantProcessJSON.put("name", "");
			}
			participantProcessJSON.put("x", pProc.getX());
			participantProcessJSON.put("y", pProc.getY());
			participantProcessJSON.put("width", pProc.getWidth());
			participantProcessJSON.put("height", pProc.getHeight());

			responseJSON.put("participantProcess", participantProcessJSON);
		}

		// Draw lanes

		if (processDefinition.getLaneSets() != null
				&& processDefinition.getLaneSets().size() > 0) {
			ArrayNode laneSetArray = new ObjectMapper().createArrayNode();
			for (LaneSet laneSet : processDefinition.getLaneSets()) {
				ArrayNode laneArray = new ObjectMapper().createArrayNode();
				if (laneSet.getLanes() != null && laneSet.getLanes().size() > 0) {
					for (Lane lane : laneSet.getLanes()) {
						ObjectNode laneJSON = new ObjectMapper()
								.createObjectNode();
						laneJSON.put("id", lane.getId());
						if (StringUtils.isNotEmpty(lane.getName())) {
							laneJSON.put("name", lane.getName());
						} else {
							laneJSON.put("name", "");
						}
						laneJSON.put("x", lane.getX());
						laneJSON.put("y", lane.getY());
						laneJSON.put("width", lane.getWidth());
						laneJSON.put("height", lane.getHeight());

						List<String> flowNodeIds = lane.getFlowNodeIds();
						ArrayNode flowNodeIdsArray = new ObjectMapper()
								.createArrayNode();
						for (String flowNodeId : flowNodeIds) {
							flowNodeIdsArray.add(flowNodeId);
						}
						laneJSON.put("flowNodeIds", flowNodeIdsArray);

						laneArray.add(laneJSON);
					}
				}
				ObjectNode laneSetJSON = new ObjectMapper().createObjectNode();
				laneSetJSON.put("id", laneSet.getId());
				if (StringUtils.isNotEmpty(laneSet.getName())) {
					laneSetJSON.put("name", laneSet.getName());
				} else {
					laneSetJSON.put("name", "");
				}
				laneSetJSON.put("lanes", laneArray);

				laneSetArray.add(laneSetJSON);
			}

			if (laneSetArray.size() > 0)
				responseJSON.put("laneSets", laneSetArray);
		}

		ArrayNode sequenceFlowArray = new ObjectMapper().createArrayNode();
		ArrayNode activityArray = new ObjectMapper().createArrayNode();

		// Activities and their sequence-flows

		for (ActivityImpl activity : processDefinition.getActivities()) {
			getActivity(activity, activityArray, sequenceFlowArray,
					processInstanceId, highLightedFlows, subProcessInstanceMap,
					processInstance);
		}

		responseJSON.put("activities", activityArray);
		responseJSON.put("sequenceFlows", sequenceFlowArray);
		return responseJSON.toString();
	}

	private List<String> getHighLightedFlows(String processInstanceId,
			ProcessDefinitionEntity processDefinition) {
		HistoryService historyService = ActivitiUtil.getHistoryService();

		List<String> highLightedFlows = new ArrayList<String>();
		List<HistoricActivityInstance> historicActivityInstances = historyService
				.createHistoricActivityInstanceQuery()
				.processInstanceId(processInstanceId)
				.orderByHistoricActivityInstanceStartTime().asc().list();

		List<String> historicActivityInstanceList = new ArrayList<String>();
		for (HistoricActivityInstance hai : historicActivityInstances) {
			historicActivityInstanceList.add(hai.getActivityId());
		}

		// add current activities to list
		List<String> highLightedActivities = runtimeService
				.getActiveActivityIds(processInstanceId);
		historicActivityInstanceList.addAll(highLightedActivities);

		// activities and their sequence-flows
		for (ActivityImpl activity : processDefinition.getActivities()) {
			int index = historicActivityInstanceList.indexOf(activity.getId());

			if (index >= 0 && index + 1 < historicActivityInstanceList.size()) {
				List<PvmTransition> pvmTransitionList = activity
						.getOutgoingTransitions();
				for (PvmTransition pvmTransition : pvmTransitionList) {
					String destinationFlowId = pvmTransition.getDestination()
							.getId();
					if (destinationFlowId.equals(historicActivityInstanceList
							.get(index + 1))) {
						highLightedFlows.add(pvmTransition.getId());
					}
				}
			}
		}
		return highLightedFlows;
	}

	@SuppressWarnings("unchecked")
	private void getActivity(ActivityImpl activity, ArrayNode activityArray,
			ArrayNode sequenceFlowArray, String processInstanceId,
			List<String> highLightedFlows,
			Map<String, ObjectNode> subProcessInstanceMap,
			ProcessInstance processInstance) {

		ObjectNode activityJSON = new ObjectMapper().createObjectNode();

		// Gather info on the multi instance marker
		String multiInstance = (String) activity.getProperty("multiInstance");
		if (multiInstance != null) {
			if (!"sequential".equals(multiInstance)) {
				multiInstance = "parallel";
			}
		}

		ActivityBehavior activityBehavior = activity.getActivityBehavior();
		// Gather info on the collapsed marker
		Boolean collapsed = (activityBehavior instanceof CallActivityBehavior);
		Boolean expanded = (Boolean) activity
				.getProperty(BpmnParse.PROPERTYNAME_ISEXPANDED);
		if (expanded != null) {
			collapsed = !expanded;
		}

		Boolean isInterrupting = null;
		if (activityBehavior instanceof BoundaryEventActivityBehavior) {
			isInterrupting = ((BoundaryEventActivityBehavior) activityBehavior)
					.isInterrupting();
		}

		// Outgoing transitions of activity
		for (PvmTransition sequenceFlow : activity.getOutgoingTransitions()) {
			String flowName = (String) sequenceFlow.getProperty("name");
			boolean isHighLighted = (highLightedFlows.contains(sequenceFlow
					.getId()));
			boolean isConditional = sequenceFlow
					.getProperty(BpmnParse.PROPERTYNAME_CONDITION) != null
					&& !((String) activity.getProperty("type")).toLowerCase()
							.contains("gateway");
			boolean isDefault = sequenceFlow.getId().equals(
					activity.getProperty("default"))
					&& ((String) activity.getProperty("type")).toLowerCase()
							.contains("gateway");

			List<Integer> waypoints = ((TransitionImpl) sequenceFlow)
					.getWaypoints();
			ArrayNode xPointArray = new ObjectMapper().createArrayNode();
			ArrayNode yPointArray = new ObjectMapper().createArrayNode();
			for (int i = 0; i < waypoints.size(); i += 2) { // waypoints.size()
															// minimally 4: x1,
															// y1,
															// x2, y2
				xPointArray.add(waypoints.get(i));
				yPointArray.add(waypoints.get(i + 1));
			}

			ObjectNode flowJSON = new ObjectMapper().createObjectNode();
			flowJSON.put("id", sequenceFlow.getId());
			flowJSON.put("name", flowName);
			flowJSON.put("flow", "(" + sequenceFlow.getSource().getId() + ")--"
					+ sequenceFlow.getId() + "-->("
					+ sequenceFlow.getDestination().getId() + ")");

			if (isConditional)
				flowJSON.put("isConditional", isConditional);
			if (isDefault)
				flowJSON.put("isDefault", isDefault);
			if (isHighLighted)
				flowJSON.put("isHighLighted", isHighLighted);

			flowJSON.put("xPointArray", xPointArray);
			flowJSON.put("yPointArray", yPointArray);

			sequenceFlowArray.add(flowJSON);
		}

		// Nested activities (boundary events)
		ArrayNode nestedActivityArray = new ObjectMapper().createArrayNode();
		for (ActivityImpl nestedActivity : activity.getActivities()) {
			nestedActivityArray.add(nestedActivity.getId());
		}

		Map<String, Object> properties = activity.getProperties();
		ObjectNode propertiesJSON = new ObjectMapper().createObjectNode();
		for (String key : properties.keySet()) {
			Object prop = properties.get(key);
			if (prop instanceof String)
				propertiesJSON.put(key, (String) properties.get(key));
			else if (prop instanceof Integer)
				propertiesJSON.put(key, (Integer) properties.get(key));
			else if (prop instanceof Boolean)
				propertiesJSON.put(key, (Boolean) properties.get(key));
			else if ("initial".equals(key)) {
				ActivityImpl act = (ActivityImpl) properties.get(key);
				propertiesJSON.put(key, act.getId());
			} else if ("timerDeclarations".equals(key)) {
				ArrayList<TimerDeclarationImpl> timerDeclarations = (ArrayList<TimerDeclarationImpl>) properties
						.get(key);
				ArrayNode timerDeclarationArray = new ObjectMapper()
						.createArrayNode();

				if (timerDeclarations != null)
					for (TimerDeclarationImpl timerDeclaration : timerDeclarations) {
						ObjectNode timerDeclarationJSON = new ObjectMapper()
								.createObjectNode();

						timerDeclarationJSON.put("isExclusive",
								timerDeclaration.isExclusive());
						if (timerDeclaration.getRepeat() != null)
							timerDeclarationJSON.put("repeat",
									timerDeclaration.getRepeat());

						timerDeclarationJSON.put("retries",
								String.valueOf(timerDeclaration.getRetries()));
						timerDeclarationJSON.put("type",
								timerDeclaration.getJobHandlerType());
						timerDeclarationJSON.put("configuration",
								timerDeclaration.getJobHandlerConfiguration());
						// timerDeclarationJSON.put("expression",
						// timerDeclaration.getDescription());

						timerDeclarationArray.add(timerDeclarationJSON);
					}
				if (timerDeclarationArray.size() > 0)
					propertiesJSON.put(key, timerDeclarationArray);
				// TODO: implement getting description
			} else if ("eventDefinitions".equals(key)) {
				ArrayList<EventSubscriptionDeclaration> eventDefinitions = (ArrayList<EventSubscriptionDeclaration>) properties
						.get(key);
				ArrayNode eventDefinitionsArray = new ObjectMapper()
						.createArrayNode();

				if (eventDefinitions != null) {
					for (EventSubscriptionDeclaration eventDefinition : eventDefinitions) {
						ObjectNode eventDefinitionJSON = new ObjectMapper()
								.createObjectNode();

						if (eventDefinition.getActivityId() != null)
							eventDefinitionJSON.put("activityId",
									eventDefinition.getActivityId());

						eventDefinitionJSON.put("eventName",
								eventDefinition.getEventName());
						eventDefinitionJSON.put("eventType",
								eventDefinition.getEventType());
						eventDefinitionJSON.put("isAsync",
								eventDefinition.isAsync());
						eventDefinitionJSON.put("isStartEvent",
								eventDefinition.isStartEvent());
						eventDefinitionsArray.add(eventDefinitionJSON);
					}
				}

				if (eventDefinitionsArray.size() > 0)
					propertiesJSON.put(key, eventDefinitionsArray);
				// TODO: implement it
			} else if ("errorEventDefinitions".equals(key)) {
				ArrayList<ErrorEventDefinition> errorEventDefinitions = (ArrayList<ErrorEventDefinition>) properties
						.get(key);
				ArrayNode errorEventDefinitionsArray = new ObjectMapper()
						.createArrayNode();

				if (errorEventDefinitions != null) {
					for (ErrorEventDefinition errorEventDefinition : errorEventDefinitions) {
						ObjectNode errorEventDefinitionJSON = new ObjectMapper()
								.createObjectNode();

						if (errorEventDefinition.getErrorCode() != null)
							errorEventDefinitionJSON.put("errorCode",
									errorEventDefinition.getErrorCode());
						else
							errorEventDefinitionJSON.putNull("errorCode");

						errorEventDefinitionJSON.put("handlerActivityId",
								errorEventDefinition.getHandlerActivityId());

						errorEventDefinitionsArray
								.add(errorEventDefinitionJSON);
					}
				}

				if (errorEventDefinitionsArray.size() > 0)
					propertiesJSON.put(key, errorEventDefinitionsArray);
			}

		}

		if ("callActivity".equals(properties.get("type"))) {
			CallActivityBehavior callActivityBehavior = null;

			if (activityBehavior instanceof CallActivityBehavior) {
				callActivityBehavior = (CallActivityBehavior) activityBehavior;
			}
			
			if (callActivityBehavior != null) {
				propertiesJSON.put("processDefinitonKey",
						callActivityBehavior.getProcessDefinitonKey());

				// get processDefinitonId from execution or get last
				// processDefinitonId
				// by key
				ArrayNode processInstanceArray = new ObjectMapper()
						.createArrayNode();
				if (processInstance != null) {
					List<Execution> executionList = runtimeService
							.createExecutionQuery()
							.processInstanceId(processInstanceId)
							.activityId(activity.getId()).list();
					if (executionList.size() > 0) {
						for (Execution execution : executionList) {
							ObjectNode processInstanceJSON = subProcessInstanceMap
									.get(execution.getId());
							processInstanceArray.add(processInstanceJSON);
						}
					}
				}

				// If active activities nas no instance of this callActivity
				// then add
				// last definition
				if (processInstanceArray.size() == 0) {
					// Get last definition by key
					ProcessDefinition lastProcessDefinition = repositoryService
							.createProcessDefinitionQuery()
							.processDefinitionKey(
									callActivityBehavior
											.getProcessDefinitonKey())
							.latestVersion().singleResult();

					// TODO: unuseful fields there are processDefinitionName,
					// processDefinitionKey
					ObjectNode processInstanceJSON = new ObjectMapper()
							.createObjectNode();
					processInstanceJSON.put("processDefinitionId",
							lastProcessDefinition.getId());
					processInstanceJSON.put("processDefinitionKey",
							lastProcessDefinition.getKey());
					processInstanceJSON.put("processDefinitionName",
							lastProcessDefinition.getName());
					processInstanceArray.add(processInstanceJSON);
				}

				if (processInstanceArray.size() > 0) {
					propertiesJSON.put("processDefinitons",
							processInstanceArray);
				}
			}
		}

		activityJSON.put("activityId", activity.getId());
		activityJSON.put("properties", propertiesJSON);
		if (multiInstance != null)
			activityJSON.put("multiInstance", multiInstance);
		if (collapsed)
			activityJSON.put("collapsed", collapsed);
		if (nestedActivityArray.size() > 0)
			activityJSON.put("nestedActivities", nestedActivityArray);
		if (isInterrupting != null)
			activityJSON.put("isInterrupting", isInterrupting);

		activityJSON.put("x", activity.getX());
		activityJSON.put("y", activity.getY());
		activityJSON.put("width", activity.getWidth());
		activityJSON.put("height", activity.getHeight());

		activityArray.add(activityJSON);

		// Nested activities (boundary events)
		for (ActivityImpl nestedActivity : activity.getActivities()) {
			getActivity(nestedActivity, activityArray, sequenceFlowArray,
					processInstanceId, highLightedFlows, subProcessInstanceMap,
					processInstance);
		}
	}

	private JsonNode getProcessDefinitionResponse(
			ProcessDefinitionEntity processDefinition) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode pdrJSON = mapper.createObjectNode();
		pdrJSON.put("id", processDefinition.getId());
		pdrJSON.put("name", processDefinition.getName());
		pdrJSON.put("key", processDefinition.getKey());
		pdrJSON.put("version", processDefinition.getVersion());
		pdrJSON.put("deploymentId", processDefinition.getDeploymentId());
		pdrJSON.put("isGraphicNotationDefined",
				isGraphicNotationDefined(processDefinition));
		return pdrJSON;
	}

	private boolean isGraphicNotationDefined(
			ProcessDefinitionEntity processDefinition) {
		return ((ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(processDefinition.getId()))
				.isGraphicalNotationDefined();
	}

	public RuntimeService getRuntimeService() {
		return runtimeService;
	}

	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}

	@Override
	public String getHighlighted(String processInstanceId) throws Exception {
		ObjectNode responseJSON = new ObjectMapper().createObjectNode();

		responseJSON.put("processInstanceId", processInstanceId);

		ArrayNode activitiesArray = new ObjectMapper().createArrayNode();
		ArrayNode activitiesExecutedArray = new ObjectMapper()
				.createArrayNode();
		ArrayNode flowsArray = new ObjectMapper().createArrayNode();
		ProcessInstance processInstance = null;
		HistoricProcessInstance historicProcessInstance = null;
		ProcessDefinitionEntity processDefinition = null;

		List<String> highLightedFlows = new ArrayList<String>();
		try {
			processInstance = runtimeService.createProcessInstanceQuery()
					.processInstanceId(processInstanceId).singleResult();
			if (processInstance == null) {
				historicProcessInstance = historyService
						.createHistoricProcessInstanceQuery()
						.processInstanceId(processInstanceId).singleResult();// .createProcessInstanceQuery().processInstanceId(processInstanceId).
				// processInstance = new ProcessInstance();
			}
			if (processInstance != null) {
				processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
						.getDeployedProcessDefinition(processInstance
								.getProcessDefinitionId());
				responseJSON.put("processDefinitionId",
						processInstance.getProcessDefinitionId());
			} else {
				processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
						.getDeployedProcessDefinition(((HistoricProcessInstanceEntity) historicProcessInstance)
								.getProcessDefinitionId());
				responseJSON
						.put("processDefinitionId",
								((HistoricProcessInstanceEntity) historicProcessInstance)
										.getProcessDefinitionId());
			}

			if (processInstance != null) {
				List<String> highLightedActivities = runtimeService
						.getActiveActivityIds(processInstanceId);
				Map<String, Object> highLightedActivitiesMap = new HashMap<String, Object>();

				for (String activityId : highLightedActivities) {
					activitiesArray.add(activityId);
					highLightedActivitiesMap.put(activityId, activityId);
				}
			}

			highLightedFlows = getHighLightedFlows(processDefinition,
					processInstanceId);
			for (String flow : highLightedFlows) {
				flowsArray.add(flow);
			}

			// 执行过的流程环节定义需要高亮显示 add by yicheng.yang begine
			HistoricActivityInstanceQuery htiQuery = historyService
					.createHistoricActivityInstanceQuery();// .createHistoricTaskInstanceQuery();
			List<HistoricActivityInstance> htiList = htiQuery
					.processInstanceId(processInstanceId).list();
			if (null != htiList && !htiList.isEmpty()) {
				for (HistoricActivityInstance historicTaskInstance : htiList) {
					if (null != historicTaskInstance) {
						activitiesExecutedArray.add(historicTaskInstance
								.getActivityId());
					}
				}
				// 正在运行的环节，需要标红，不能作为已办理的环节
				/*
				 * if(null!=highLightedActivities &&
				 * !highLightedActivities.isEmpty()){ Iterator<JsonNode> jsonItr
				 * = activitiesExecutedArray.iterator();
				 * while(jsonItr.hasNext()){ JsonNode o = jsonItr.next(); String
				 * str = o.asText();
				 * if(highLightedActivitiesMap.get(str)!=null){
				 * jsonItr.remove(); } }
				 * 
				 * }
				 */
			}

			// 执行过的流程环节定义需要高亮显示 add by yicheng.yang end

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 增加执行过的流程环节定义的高亮显示 add by yicheng.yang 20140805
		responseJSON.put("activitiesExecuted", activitiesExecutedArray);

		responseJSON.put("activities", activitiesArray);
		responseJSON.put("flows", flowsArray);

		return responseJSON.toString();
	}

	/**
	 * getHighLightedFlows
	 * 
	 * @param processDefinition
	 * @param processInstanceId
	 * @return
	 */
	private List<String> getHighLightedFlows(
			ProcessDefinitionEntity processDefinition, String processInstanceId) {

		List<String> highLightedFlows = new ArrayList<String>();

//		List<HistoricActivityInstance> historicActivityInstances = historyService
//				.createHistoricActivityInstanceQuery()
//				.processInstanceId(processInstanceId).orderByHistoricActivityInstanceEndTime().asc()
//				.orderByHistoricActivityInstanceStartTime().asc()
				// order by startime asc is not correct. use default order is
				// correct.
				// .orderByHistoricActivityInstanceStartTime().asc()/*.orderByActivityId().asc()*/
//				.list();

		GetHistoricActivityInstanceByProcessInstanceId cmd = new GetHistoricActivityInstanceByProcessInstanceId(
				processInstanceId);
		List<HistoricActivityInstance> historicActivityInstances = managementService
				.executeCommand(cmd);
		
		Map<String, String> actInsKeys = new HashMap<String, String>();
		setActInsTransKeys(historicActivityInstances, actInsKeys);

		// LinkedList<HistoricActivityInstance> hisActInstList = new
		// LinkedList<HistoricActivityInstance>();
		// hisActInstList.addAll(historicActivityInstances);

		// getHighlightedFlows(processDefinition.getActivities(),
		// hisActInstList,
		// highLightedFlows);
		Set<PvmTransition> pvmTrans = new HashSet<PvmTransition>();
		setAllTrans(processDefinition.getActivities(), pvmTrans);
		for (PvmTransition pvmTran : pvmTrans) {
			ActivityImpl source = (ActivityImpl)pvmTran.getSource();
			String sourceActDefId = source.getId();
			ActivityImpl dest = (ActivityImpl)pvmTran.getDestination();
			String destActDefId = dest.getId();
			if (actInsKeys.containsKey(sourceActDefId + ACTTRANS_LINK_TOKEN
					+ destActDefId)) {
				highLightedFlows.add(pvmTran.getId());
			}
		}
		return highLightedFlows;
	}

	/**
	 * 获取历史活动实例的所有可能轨迹
	 * 
	 * @param historicActivityInstances
	 *            历史活动实例列表，按开始时间递增排序
	 * @param actInsKeys
	 *            fromActId-toActId key
	 */
	private void setActInsTransKeys(
			List<HistoricActivityInstance> historicActivityInstances,
			Map<String, String> actInsKeys) {
		if (historicActivityInstances != null) {
			int i = 0, size = historicActivityInstances.size();
			for (; i < size - 1; i++) {
				HistoricActivityInstance fromActIns = historicActivityInstances
						.get(i);
				HistoricActivityInstance toActIns = historicActivityInstances
						.get(i + 1);
				String actInsKey = fromActIns.getActivityId()
						+ ACTTRANS_LINK_TOKEN + toActIns.getActivityId();
				actInsKeys.put(actInsKey, actInsKey);
			}
		}
	}

	/**
	 * 获取所有的流程定义轨迹
	 * @param activitys 活动定义列表
	 * @param pvmTrans 返回的规则定义列表
	 */
	private void setAllTrans(List<ActivityImpl> activitys,
			Set<PvmTransition> pvmTrans) {
		if (activitys != null) {
			for (ActivityImpl activity : activitys) {
				if ("subProcess".equals(activity.getProperty("type"))) {
					setAllTrans(activity.getActivities(), pvmTrans);
				} else {
					List<PvmTransition> outList = activity
							.getOutgoingTransitions();
					List<PvmTransition> inList = activity.getIncomingTransitions();
					if (outList != null) {
						pvmTrans.addAll(outList);
					}
					if (inList != null) {
						pvmTrans.addAll(inList);
					}
				}
			}
		}
	}

	/**
	 * getHighlightedFlows
	 * 
	 * code logic: 1. Loop all activities by id asc order; 2. Check each
	 * activity's outgoing transitions and eventBoundery outgoing transitions,
	 * if outgoing transitions's destination.id is in other executed
	 * activityIds, add this transition to highLightedFlows List; 3. But if
	 * activity is not a parallelGateway or inclusiveGateway, only choose the
	 * earliest flow.
	 * 
	 * @param activityList
	 * @param hisActInstList
	 * @param highLightedFlows
	 */
	/*private void getHighlightedFlows(List<ActivityImpl> activityList,
			LinkedList<HistoricActivityInstance> hisActInstList,
			List<String> highLightedFlows) {

		// check out startEvents in activityList
		List<ActivityImpl> startEventActList = new ArrayList<ActivityImpl>();
		Map<String, ActivityImpl> activityMap = new HashMap<String, ActivityImpl>(
				activityList.size());
		for (ActivityImpl activity : activityList) {

			activityMap.put(activity.getId(), activity);

			String actType = (String) activity.getProperty("type");
			if (actType != null
					&& actType.toLowerCase().indexOf("startevent") >= 0) {
				startEventActList.add(activity);
			}
		}

		// These codes is used to avoid a bug:
		// ACT-1728 If the process instance was started by a callActivity, it
		// will be not have the startEvent activity in ACT_HI_ACTINST table
		// Code logic:
		// Check the first activity if it is a startEvent, if not check out the
		// startEvent's highlight outgoing flow.
		HistoricActivityInstance firstHistActInst = hisActInstList.getFirst();
		String firstActType = (String) firstHistActInst.getActivityType();
		if (firstActType != null
				&& firstActType.toLowerCase().indexOf("startevent") < 0) {
			PvmTransition startTrans = getStartTransaction(startEventActList,
					firstHistActInst);
			if (startTrans != null) {
				highLightedFlows.add(startTrans.getId());
			}
		}

		while (hisActInstList.size() > 0) {
			HistoricActivityInstance histActInst = hisActInstList.removeFirst();
			ActivityImpl activity = activityMap
					.get(histActInst.getActivityId());
			if (activity != null) {
				boolean isParallel = false;
				String type = histActInst.getActivityType();
				if ("parallelGateway".equals(type)
						|| "inclusiveGateway".equals(type)) {
					isParallel = true;
				} else if ("subProcess".equals(histActInst.getActivityType())) {
					getHighlightedFlows(activity.getActivities(),
							hisActInstList, highLightedFlows);
				}

				List<PvmTransition> allOutgoingTrans = new ArrayList<PvmTransition>();
				allOutgoingTrans.addAll(activity.getOutgoingTransitions());
				allOutgoingTrans
						.addAll(getBoundaryEventOutgoingTransitions(activity));
				List<String> activityHighLightedFlowIds = getHighlightedFlows(
						allOutgoingTrans, hisActInstList, isParallel);
				highLightedFlows.addAll(activityHighLightedFlowIds);
			}
		}
	}*/

	/**
	 * Check out the outgoing transition connected to firstActInst from
	 * startEventActList
	 * 
	 * @param startEventActList
	 * @param firstActInst
	 * @return
	 */
	/*private PvmTransition getStartTransaction(
			List<ActivityImpl> startEventActList,
			HistoricActivityInstance firstActInst) {
		for (ActivityImpl startEventAct : startEventActList) {
			for (PvmTransition trans : startEventAct.getOutgoingTransitions()) {
				if (trans.getDestination().getId()
						.equals(firstActInst.getActivityId())) {
					return trans;
				}
			}
		}
		return null;
	}*/

	/**
	 * getBoundaryEventOutgoingTransitions
	 * 
	 * @param activity
	 * @return
	 */
	/*private List<PvmTransition> getBoundaryEventOutgoingTransitions(
			ActivityImpl activity) {
		List<PvmTransition> boundaryTrans = new ArrayList<PvmTransition>();
		for (ActivityImpl subActivity : activity.getActivities()) {
			String type = (String) subActivity.getProperty("type");
			if (type != null && type.toLowerCase().indexOf("boundary") >= 0) {
				boundaryTrans.addAll(subActivity.getOutgoingTransitions());
			}
		}
		return boundaryTrans;
	}*/

	/**
	 * find out single activity's highlighted flowIds
	 * 
	 * @param activity
	 * @param hisActInstList
	 * @param isExclusive
	 *            if true only return one flowId(Such as exclusiveGateway,
	 *            BoundaryEvent On Task)
	 * @return
	 */
	/*private List<String> getHighlightedFlows(
			List<PvmTransition> pvmTransitionList,
			LinkedList<HistoricActivityInstance> hisActInstList,
			boolean isParallel) {

		List<String> highLightedFlowIds = new ArrayList<String>();

		PvmTransition earliestTrans = null;
		HistoricActivityInstance earliestHisActInst = null;

		for (PvmTransition pvmTransition : pvmTransitionList) {

			String destActId = pvmTransition.getDestination().getId();
			HistoricActivityInstance destHisActInst = findHisActInst(
					hisActInstList, destActId);
			if (destHisActInst != null) {
				if (isParallel) {
					highLightedFlowIds.add(pvmTransition.getId());
				} else if (earliestHisActInst == null
						|| (earliestHisActInst.getId().compareTo(
								destHisActInst.getId()) > 0)) {
					earliestTrans = pvmTransition;
					earliestHisActInst = destHisActInst;
				}
			}
		}

		if ((!isParallel) && earliestTrans != null) {
			highLightedFlowIds.add(earliestTrans.getId());
		}

		return highLightedFlowIds;
	}*/

	/*private HistoricActivityInstance findHisActInst(
			LinkedList<HistoricActivityInstance> hisActInstList, String actId) {
		for (HistoricActivityInstance hisActInst : hisActInstList) {
			if (hisActInst.getActivityId().equals(actId)) {
				return hisActInst;
			}
		}
		return null;
	}*/

	@Override
	public void addTaskListener(String processDefinitionKey) throws Exception {
		ProcessDefinitionEntity pde = (ProcessDefinitionEntity) repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionKey(processDefinitionKey).processDefinitionWithoutTenantId().latestVersion()
				.singleResult();
		// if (pde.getVersion() == 1) {//修改流程部署后没有监听器了
		pde = (ProcessDefinitionEntity) repositoryService
				.getProcessDefinition(pde.getId());
		Map<String, TaskDefinition> taskDefinitions = pde.getTaskDefinitions();
		ApplicationContext context = ((SpringProcessEngineConfiguration) ((ProcessEngineImpl) processEngine)
				.getProcessEngineConfiguration()).getApplicationContext();
		if (taskDefinitions != null) {
			for (String key : taskDefinitions.keySet()) {
				TaskDefinition taskDefinition = taskDefinitions.get(key);
				taskDefinition.addTaskListener(
						TaskListener.EVENTNAME_ALL_EVENTS,
						new ExtendTaskListener(managementService, context));
			}
		}
	}
	
	@Override
	public void addTaskListener(String processDefinitionKey,String tenantId) throws Exception {
		ProcessDefinitionEntity pde = (ProcessDefinitionEntity) repositoryService
				.createProcessDefinitionQuery().processDefinitionTenantId(tenantId)
				.processDefinitionKey(processDefinitionKey).latestVersion()
				.singleResult();
		// if (pde.getVersion() == 1) {//修改流程部署后没有监听器了
		pde = (ProcessDefinitionEntity) repositoryService
				.getProcessDefinition(pde.getId());
		Map<String, TaskDefinition> taskDefinitions = pde.getTaskDefinitions();
		ApplicationContext context = ((SpringProcessEngineConfiguration) ((ProcessEngineImpl) processEngine)
				.getProcessEngineConfiguration()).getApplicationContext();
		if (taskDefinitions != null) {
			for (String key : taskDefinitions.keySet()) {
				TaskDefinition taskDefinition = taskDefinitions.get(key);
				taskDefinition.addTaskListener(
						TaskListener.EVENTNAME_ALL_EVENTS,
						new ExtendTaskListener(managementService, context));
			}
		}
	}

	@Override
	public void addHoliday(WfHoliday wfHoliday) throws Exception {
		if (wfHoliday != null) {
			WfHolidayEntity entity = new WfHolidayEntity();
			entity.setHoliday(wfHoliday.getHoliday());
			entity.setmHoliday(wfHoliday.getMHoliday());
			entity.setTenantId(wfHoliday.getTenantId());
			entity.setyHoliday(wfHoliday.getYHoliday());
			managementService.executeCommand(new InsertHolidayCmd(entity));
		} else {
			throw new NullPointerException("节假日对象为空");
		}
	}

	@Override
	public void deleteHoliday(WfHoliday wfHoliday) throws Exception {
		if (wfHoliday != null) {
			managementService.executeCommand(new DeleteHolidayCmd(wfHoliday
					.getHoliday(), wfHoliday.getYHoliday(), wfHoliday
					.getMHoliday(), wfHoliday.getTenantId()));
		} else {
			throw new NullPointerException("节假日对象为空");
		}
	}

	public boolean setHoliday(WfHoliday wfHoliday) throws Exception {
		boolean b = false;
		List<WfHoliday> list = getHolidayList(wfHoliday);
		if (list != null && !list.isEmpty()) {
			deleteHoliday(wfHoliday);
			b = false;
		} else {
			addHoliday(wfHoliday);
			b = true;
		}
		return b;
	}

	@Override
	public void setWorkDate(WfWorkDate wfWorkDate) throws Exception {
		if (wfWorkDate != null) {
			managementService.executeCommand(new UpdateWorkDateCmd(wfWorkDate));
		} else {
			throw new NullPointerException("作息时间对象为空");
		}
	}

	@Override
	public void addWorkDate(WfWorkDate wfWorkDate) throws Exception {
		if (wfWorkDate != null) {
			WfWorkDateEntity entity = new WfWorkDateEntity();

			entity.setAmBeginTime(wfWorkDate.getAmBeginTime());
			entity.setAmEndTime(wfWorkDate.getAmEndTime());
			entity.setBeginDate(wfWorkDate.getBeginDate());
			entity.setEndDate(wfWorkDate.getEndDate());
			entity.setLastModifyTime(wfWorkDate.getLastModifyTime());

			entity.setPmBeginTime(wfWorkDate.getPmBeginTime());
			entity.setPmEndTime(wfWorkDate.getPmEndTime());
			entity.setRemark(wfWorkDate.getRemark());
			entity.setTenantId(wfWorkDate.getTenantId());
			entity.setWorkId(new PKGenerator().getNextId());

			managementService.executeCommand(new InsertWorkDateCmd(entity));
		} else {
			throw new NullPointerException("作息时间对象为空");
		}
	}

	@Override
	public void deleteWorkDate(WfWorkDate wfWorkDate) throws Exception {
		if (wfWorkDate != null) {
			String workId = wfWorkDate.getWorkId();
			String tenantId = wfWorkDate.getTenantId();
			if (CommonUtil.stringNullOrEmpty(workId)) {
				throw new NullPointerException("作息时间ID为空");
			}
			managementService.executeCommand(new DeleteWorkDateCmd(workId,
					tenantId));
		} else {
			throw new NullPointerException("作息时间对象为空");
		}
	}

	@Override
	public List<WfHoliday> getHolidayList(WfHoliday wfHoliday) throws Exception {
		List<WfHoliday> wfHolidayResult = null;
		if (wfHoliday != null) {
			wfHolidayResult = managementService
					.executeCommand(new GetHolidayByParamCmd(wfHoliday));
		} else {
			throw new NullPointerException("节假日对象为空");
		}
		return wfHolidayResult;
	}

	@Override
	public WfPageList<WfWorkDate, WfWorkDateParam> getWorkDateList(
			WfWorkDateParam wfWorkDateParam) throws Exception {
		WfPageList<WfWorkDate, WfWorkDateParam> wfPageList = new WfPageList<WfWorkDate, WfWorkDateParam>();
		if (wfWorkDateParam != null) {
			WfPageList<WfWorkDateEntity, WfWorkDateParam> wfEntityPageList = managementService
					.executeCommand(new GetWorkDateByParamCmd(wfWorkDateParam));
			List<WfWorkDate> list = null;
			if (wfEntityPageList != null && wfEntityPageList.getDatas() != null
					&& !wfEntityPageList.getDatas().isEmpty()) {
				list = new ArrayList<WfWorkDate>();
				for (WfWorkDateEntity en : wfEntityPageList.getDatas()) {
					WfWorkDate wfWorkDate = new WfWorkDate();

					wfWorkDate.setAmBeginTime(en.getAmBeginTime());
					wfWorkDate.setAmEndTime(en.getAmEndTime());
					wfWorkDate.setBeginDate(en.getBeginDate());
					wfWorkDate.setEndDate(en.getEndDate());
					wfWorkDate.setLastModifyTime(en.getLastModifyTime());

					wfWorkDate.setPmBeginTime(en.getPmBeginTime());
					wfWorkDate.setPmEndTime(en.getPmEndTime());
					wfWorkDate.setRemark(en.getRemark());
					wfWorkDate.setTenantId(en.getTenantId());
					wfWorkDate.setWorkId(en.getWorkId());

					list.add(wfWorkDate);
				}
				wfPageList.setDatas(list);
				wfPageList.setWfQuery(wfEntityPageList.getWfQuery());
			}
		} else {
			throw new NullPointerException("作息时间对象为空");
		}
		return wfPageList;
	}

	public int getRealdays(Date begineDate, Integer duration) throws Exception {
		return this.getHolidays(begineDate, duration).size() + duration;
	}

	/**
	 * 得到从某个时间开始到第i个工作日内的假日数组
	 * 
	 * @param beginDate
	 * @param workdaysCount
	 * @return
	 * @throws ManagerException
	 */
	public List<Date> getHolidays(Date beginDate, int workdaysCount) throws Exception {
		List<Date> holidaysList = new ArrayList<Date>();
		Date endDate = new Date();
		endDate = getEndDate(beginDate, workdaysCount);
		holidaysList = getHolidays(beginDate, endDate);
		return holidaysList;
	}

	/**
	 * 得到两个日期内的假日数组
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @throws ManagerException
	 */
	@SuppressWarnings({ "unused", "deprecation" })
	public List<Date> getHolidays(Date beginDate, Date endDate) throws Exception {
		List<Date> list = new ArrayList<Date>();
		String beginDateStr = DateUtil.dateToStr(beginDate, "yyyy-M-d");
		String endDateStr = DateUtil.dateToStr(endDate, "yyyy-M-d");
		if (endDate.before(beginDate)) {
			beginDateStr = DateUtil.dateToStr(endDate, "yyyy-M-d");
			endDateStr = DateUtil.dateToStr(beginDate, "yyyy-M-d");
		}
		int bm = 0;
		int em = 0;
		int bd = 0;
		int ed = 0;
		int count = 0;
		bm = Integer.parseInt(beginDateStr.substring(5,
				beginDateStr.indexOf('-', 5)));
		em = Integer.parseInt(endDateStr.substring(5,
				endDateStr.indexOf('-', 5)));
		bd = Integer.parseInt(beginDateStr.substring(beginDateStr.indexOf('-',
				5) + 1));
		ed = Integer
				.parseInt(endDateStr.substring(endDateStr.indexOf('-', 5) + 1));
		// modify by minghua.guo[2010-12-29]解决跨年问题
		String sql = "";
		if (beginDateStr.substring(0, 4).equals(endDateStr.substring(0, 4))) {
			int i;
			sql += " yholiday = '" + beginDateStr.substring(0, 4) + "'";
			for (i = bm; i <= em; i++) {
				if (i == bm) {
					sql += " and mholiday = " + i + "" + "";
				} else {
					sql += " or mholiday = " + i + "" + "";
				}
			}
		} else {
			int m;
			for (int i = Integer.parseInt(beginDateStr.substring(0, 4)); i <= Integer
					.parseInt(endDateStr.substring(0, 4)); i++) {
				if (i == Integer.parseInt(beginDateStr.substring(0, 4))) {
					// modify by minghua.guo[2010-12-29]解决跨年问题
					sql += " yholiday = '" + i + "" + "'";
				} else {
					sql += " or yholiday = '" + i + "" + "'";
				}
			}
		}
		try {
			/*
			 * List<Holiday> list1 = executor.queryListBean(Holiday.class,
			 * "getHolidays", map);
			 */
			List<WfHoliday> list1 = managementService
					.executeCommand(new GetHolidaysCmd(sql));
			for (int i = 0; i < list1.size(); i++) {
				String currDateStr = list1.get(i).getHoliday();
				if (compareDateStr(beginDateStr, currDateStr) >= 0
						&& compareDateStr(currDateStr, endDateStr) >= 0) {
					Date holiday = new Date();
					holiday.setYear(Integer
							.parseInt(list1.get(i).getYHoliday()) - 1900);
					holiday.setMonth(Integer.parseInt(list1.get(i)
							.getMHoliday()) - 1);
					holiday.setDate(Integer.parseInt(currDateStr
							.substring(currDateStr.indexOf('-', 5) + 1)));
					holiday.setHours(0);
					holiday.setMinutes(0);
					holiday.setSeconds(0);
					list.add(holiday);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 比较两个日期的大小，如果endDateStr小于beginDateStr则返回-1，大于则放回1，等于则返回0
	 * 
	 * @param beginDateStr
	 * @param endDateStr
	 * @return
	 */
	public int compareDateStr(String beginDateStr, String endDateStr)
			throws Exception {
		int by = 0;
		int ey = 0;
		int bm = 0;
		int em = 0;
		int bd = 0;
		int ed = 0;

		by = Integer.parseInt(beginDateStr.substring(0, 4));
		ey = Integer.parseInt(endDateStr.substring(0, 4));
		// 取得年整数型
		bm = Integer.parseInt(beginDateStr.substring(5,
				beginDateStr.indexOf('-', 5)));
		em = Integer.parseInt(endDateStr.substring(5,
				endDateStr.indexOf('-', 5)));
		// 取得月的整数型
		bd = Integer.parseInt(beginDateStr.substring(beginDateStr.indexOf('-',
				5) + 1));
		ed = Integer
				.parseInt(endDateStr.substring(endDateStr.indexOf('-', 5) + 1));
		// 取得日的整数型
		if (ey < by) {
			return -1;
		}
		if (ey > by) {
			return 1;
		}
		if (em < bm) {
			return -1;
		}
		if (em > bm) {
			return 1;
		}
		if (ed < bd) {
			return -1;
		}
		if (ed > bd) {
			return 1;
		}
		return 0;
	}

	/**
	 * 得到从某天开始到第几个工作日的日期。 包括开始的日期和得到的日期。
	 */
	@SuppressWarnings("deprecation")
	public Date getEndDate(Date beginDate, int workdays) throws Exception {
		Date endDate = (Date) beginDate.clone();
		endDate.setDate(beginDate.getDate() + workdays);
		int holidays = workdays - getWorkdayCounts(beginDate, endDate);
		while (holidays > 0) {
			endDate.setDate(endDate.getDate() + holidays);
			holidays = workdays - getWorkdayCounts(beginDate, endDate);
		}
		endDate.setDate(endDate.getDate() + holidays);
		return endDate;
	}

	/**
	 * 得到两个日期之间的工作日天数，替换老的方法
	 * 
	 * @author minghua.guo[2011-1-11]
	 */
	public int getWorkdayCounts(Date beginDate, Date endDate) {
		int days = 0;
		int workdays = 0;
		try {
			long l = endDate.getTime() - beginDate.getTime();
			days += l / 60 / 60 / 1000 / 24;
			Calendar d = Calendar.getInstance();
			d.setTime((Date) beginDate.clone());
			for (int i = 0; i < days; i++) {
				d.add(Calendar.DATE, 1);
				if (isWorkDays(d.getTime())) {
					workdays++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return workdays;
	}

	/**
	 * 判断是否是工作日
	 * 
	 * @param curDate
	 * @return
	 * @throws Exception
	 */
	public boolean isWorkDays(Date curDate) throws Exception {
		boolean flag = true;
		try {
			String date = new SimpleDateFormat("yyyy-MM-dd").format(curDate);
			WfHoliday wfHoliday = new WfHoliday();
			wfHoliday.setHoliday(date);
			List<WfHoliday> list = getHolidayList(wfHoliday);
			if (list.size() > 0) {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public Date getOverTimes(Date begineDate, Integer duration)
			throws Exception {

		Date curDate = begineDate;
		try {
			if (duration > 0) {
				while (true) {
					String curTime = new SimpleDateFormat("HH:mm:ss")
							.format(curDate);
					String curDateStr = new SimpleDateFormat("yyyy-MM-dd")
							.format(curDate);
					SimpleDateFormat ymdHms = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					long curTimel = ymdHms.parse(
							curDateStr + " " + curTime + ":00").getTime();
					long amBeginl = ymdHms
							.parse(curDateStr + " " + this.getAmBegin(curDate)
									+ ":00").getTime();
					long amendl = ymdHms.parse(
							curDateStr + " " + this.getAmEnd(curDate) + ":00")
							.getTime();
					long pmBeginl = ymdHms
							.parse(curDateStr + " " + this.getPmBegin(curDate)
									+ ":00").getTime();
					long pmendl = ymdHms.parse(
							curDateStr + " " + this.getPmEnd(curDate) + ":00")
							.getTime();

					if (this.isWorkDays(curDate)) {
						// 上班之前时间段
						if (curTimel <= amBeginl) {
							return this.getBeforeAM(curDate, duration);
						}
						// 上午上班时间段
						if (curTimel > amBeginl && curTimel <= amendl) {
							return this.getAM(curDate, duration);
						}
						// 中午时间段
						if (curTimel > amendl && curTimel < pmBeginl) {
							return this.getNoonDate(curDate, duration);
						}
						// 下午上班时间段
						if (curTimel >= pmBeginl && curTimel < pmendl) {
							return this.getAfternoonDate(curDate, duration);
						}
						// 下班以后时间段
						if (curTimel >= pmendl) {
							return this.getAfterPMDate(curDate, duration);
						}
					} else {
						curDate = this.getNextDate(curDate, 1);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return curDate;
	}

	public Date getAfterPMDate(Date curDate, double workHour) throws Exception {
		Date hur = null;
		try {
			DateFormat df = new SimpleDateFormat("HH:mm:ss");
			String curTime = new SimpleDateFormat("HH:mm:ss").format(curDate);
			Date forCurTime = df.parse(curTime);
			Date curPM = df.parse(this.getPmEnd(curDate));
			if (forCurTime.getTime() > curPM.getTime()) {
				if (workHour > 0) {
					Date date = this.getNextDate(curDate, 1);
					hur = this.getBeforeAM(date, workHour);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hur;
	}

	public Date getAM(Date curDate, double workHour) throws Exception {
		Date hur = null;
		try {
			DateFormat df = new SimpleDateFormat("HH:mm:ss");
			DateFormat ff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar c = Calendar.getInstance();
			String cda = new SimpleDateFormat("yyyy-MM-dd").format(curDate);
			String curTime = new SimpleDateFormat("HH:mm:ss").format(curDate);
			Date formatTime = df.parse(curTime);
//			Date curAMTime = ff.parse(new SimpleDateFormat(
//					"yyyy-MM-dd HH:mm:ss").format(curDate));
			c.setTime(curDate);
			int ho = c.get(Calendar.HOUR_OF_DAY) + (int) workHour;
			int mu = c.get(Calendar.MINUTE);
			int second = c.get(Calendar.SECOND);

			double mt = workHour % 1;
			int min = (int) (mu + mt * 60);

			double s = (mu + mt * 60) % 1;
			int second1 = (int) Math.round(second + s * 60);
			second = second1;
			if (second >= 60) {
				second = second1 - 60;
				min += 1;
			}
			if (min < 60) {
				mu = min;
			}
			if (min >= 60) {
				mu = min - 60;
				ho = ho + 1;
			}

			Date amtem = df.parse(ho + ":" + mu + ":" + second);
			Date curAMOve = df.parse(this.getAmEnd(curDate));
			Date curAM = df.parse(this.getAmEnd(curDate));
//			Date curPMOve = df.parse(this.getPmEnd(curDate));
			Date curPMBeg = df.parse(this.getPmBegin(curDate));
			double surp = (double) (curAM.getTime() - formatTime.getTime())
					/ (double) (1000 * 60 * 60);
			if (amtem.getTime() <= curAMOve.getTime()) {
				hur = ff.parse(cda + " " + ho + ":" + mu + ":" + second);
			} else if (amtem.getTime() > curAMOve.getTime()
					&& workHour <= (surp + this.getPMWorkHour(curDate))) {
				Calendar c1 = Calendar.getInstance();
				c1.setTime(curPMBeg);
				int h = c1.get(Calendar.HOUR_OF_DAY) + (int) (workHour - surp);
				int m = c1.get(Calendar.MINUTE);
				int second2 = c1.get(Calendar.SECOND);

				double w1 = (workHour - surp);
				double mt2 = w1 % 1;
				int min2 = (int) (m + mt2 * 60);

				double s1 = (mu + mt2 * 60) % 1;
				int second3 = (int) Math.round(second2 + s1 * 60);
				second = second3;
				if (second >= 60) {
					second = second3 - 60;
					min2 += 1;
				}
				if (min2 < 60 && min2 > 0) {
					m = min2;
				}
				if (min2 >= 60) {
					m = min2 - 60;
					h = h + 1;
				}

				hur = ff.parse(cda + " " + h + ":" + m + ":" + second);
			} else {
				double surplus = (double) ((curAMOve.getTime() - formatTime
						.getTime()) / (double) (1000 * 60 * 60) + this
						.getPMWorkHour(curDate));
				double surWorkHour = workHour - surplus;
				if (surWorkHour > 0) {
					Date surDate = this.getNextDate(curDate, 1);
					hur = this.getBeforeAM(surDate, surWorkHour);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hur;
	}

	public Date getNoonDate(Date curDate, double workHour) throws Exception {
		Date hur = null;
		try {
			DateFormat df = new SimpleDateFormat("HH:mm:ss");
			DateFormat ff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar c = Calendar.getInstance();
			String cda = new SimpleDateFormat("yyyy-MM-dd").format(curDate);
			double pm = this.getPMWorkHour(curDate);
			if (pm >= workHour) {
				Date curPM = df.parse(this.getPmBegin(curDate));
				c.setTime(curPM);
				int ho = c.get(Calendar.HOUR_OF_DAY) + (int) workHour;
				int mu = c.get(Calendar.MINUTE);
				int second = c.get(Calendar.SECOND);

				double mt = workHour % 1;
				int min = (int) (mu + mt * 60); // 此时取整,不能四舍五入.

				double s = (mu + mt * 60) % 1;
				int second1 = (int) Math.round(second + s * 60);
				second = second1;
				if (second >= 60) {
					second = second1 - 60;
					min += 1;
				}
				if (min < 60 && min > 0) {
					mu = min;
				}
				if (min >= 60) {
					mu = min - 60;
					ho = ho + 1;
				}

				hur = ff.parse(cda + " " + ho + ":" + mu + ":" + second);
			} else {
				double workNum = workHour - pm;
				if (workNum > 0) {
					Date date = this.getNextDate(curDate, 1);
					hur = this.getBeforeAM(date, workNum);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hur;
	}

	public Date getAfternoonDate(Date curDate, double workHour)
			throws Exception {
		Date hur = null;
		try {
			DateFormat df = new SimpleDateFormat("HH:mm:ss");
			DateFormat ff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar c = Calendar.getInstance();
			String cda = new SimpleDateFormat("yyyy-MM-dd").format(curDate);
			String curTime = new SimpleDateFormat("HH:mm:ss").format(curDate);
			Date forCurTime = df.parse(curTime);
			c.setTime(curDate);
			int ho = c.get(Calendar.HOUR_OF_DAY) + (int) workHour;
			int mu = c.get(Calendar.MINUTE);
			int second = c.get(Calendar.SECOND);

			double mt = workHour % 1;
			int min = (int) (mu + mt * 60);

			double s = (mu + mt * 60) % 1;
			int second1 = (int) Math.round(second + s * 60);
			second = second1;
			if (second >= 60) {
				second = second1 - 60;
				min += 1;
			}
			if (min < 60 && min > 0) {
				mu = min;
			}
			if (min >= 60) {
				mu = min - 60;
				ho = ho + 1;
			}

			Date newPM = df.parse(ho + ":" + mu + ":" + second);
			Date curPMEnd = df.parse(this.getPmEnd(curDate));
			if (newPM.getTime() <= curPMEnd.getTime()) {
				hur = ff.parse((cda + " " + ho + ":" + mu + ":" + second));
			} else {
				double surplus = (double) (curPMEnd.getTime() - forCurTime
						.getTime()) / (double) (1000 * 60 * 60);
				double workNum = workHour - surplus;

				if (workNum > 0) {
					Date date = this.getNextDate(curDate, 1);
					hur = this.getBeforeAM(date, workNum);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hur;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Date getBeforeAM(Date curDate, double workHour) throws Exception {
		Date date = null;
		try {
			Map map = new HashMap();
			map.put("date", curDate);
			map.put("workhour", workHour);
//			Object key = null;
//			Object value = null;
			while (true) {
				double hour = Double
						.parseDouble(map.get("workhour").toString());
				date = (Date) map.get("date");
				if (hour > 0) {
					map = getBeforeAMResult(date, hour);
				} else {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getBeforeAMResult(Date curDate, double workHour)
			throws Exception {
		Date hur = null;
		Map map = new HashMap();
		try {
			DateFormat df = new SimpleDateFormat("HH:mm:ss");
			DateFormat ff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar c = Calendar.getInstance();
			String cda = new SimpleDateFormat("yyyy-MM-dd").format(curDate);
			double am = this.getAMWorkHour(curDate);
			double pm = this.getPMWorkHour(curDate);

			// 当天没有设置工作时间的情况 add by minghua.guo 2010-03-08
			if (am == 0 && pm == 0) {
				throw new Exception("日期：【" + cda + "】没有设置作息时间，请设置.");
			}
			// 只设置下午工作时间而没有设置上午工作时间的情况
			if (am == 0 && pm > 0) {
				throw new Exception("日期：【" + cda + "】没有设置上午作息时间，请设置.");
			}
			// end by minghua.guo 2010-03-08

			if (am >= workHour) {
				Date curAM = df.parse(this.getAmBegin(curDate));
				c.setTime(curAM);
				int ho = c.get(Calendar.HOUR_OF_DAY) + (int) workHour;
				int mu = c.get(Calendar.MINUTE);
				int second = c.get(Calendar.SECOND);

				double mt = workHour % 1;
				int min = (int) (mu + mt * 60); // 此时取整,不能四舍五入.

				double s = (mu + mt * 60) % 1;
				int second1 = (int) Math.round(second + s * 60);

				if (min < 60) {
					mu = min;
				}
				if (min >= 60) {
					mu = min - 60;
					ho = ho + 1;
				}

				hur = ff.parse(cda + " " + ho + ":" + mu + ":" + second1);
			} else if (am < workHour && (am + pm) >= workHour) {
				Date curPM = df.parse(this.getPmBegin(curDate));
				c.setTime(curPM);
				int ho = c.get(Calendar.HOUR_OF_DAY) + (int) (workHour - am);
				int mu = c.get(Calendar.MINUTE);
				int second = c.get(Calendar.SECOND);

				double w = workHour - am;
				double mt = w % 1;
				int min = (int) (mu + mt * 60); // 此时取整,不能四舍五入.

				double s = (mu + mt * 60) % 1;
				int second1 = (int) Math.round(second + s * 60);
				second = second1;
				if (second >= 60) {
					second = second1 - 60;
					min += 1;
				}
				if (min < 60) {
					mu = min;
				}
				if (min >= 60) {
					mu = min - 60;
					ho = ho + 1;
				}

				hur = ff.parse(cda + " " + ho + ":" + mu + ":" + second);
			} else {
				hur = this.getNextDate(curDate, 1);
			}
			double workNum = workHour - (am + pm);
			map.put("date", hur);
			map.put("workhour", workNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 获取当前日期的上午工作小时数
	 * 
	 * @param curDate
	 * @return
	 * @throws Exception
	 */
	public double getAMWorkHour(Date date) throws Exception {
		double workhour = 0;
		try {
			String curDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
			/*
			 * List<Daily> list = executor.queryList(Daily.class,
			 * "getAMWorkHour", curDate, curDate);
			 */
			// DONE
			// List<WfWorkDate> list = new ArrayList<WfWorkDate>();
			List<WfWorkDateEntity> list = managementService
					.executeCommand(new GetWorkDateOfDayCmd(curDate));

			DateFormat df = new SimpleDateFormat("HH:mm:ss");
			for (int i = 0; i < list.size(); i++) {
				String amBegin = list.get(i).getAmBeginTime() + ":" + "00";
				String amEnd = list.get(i).getAmEndTime() + ":" + "00";
				Date ambegin = df.parse(amBegin);
				Date amend = df.parse(amEnd);
				double am = (double) (amend.getTime() - ambegin.getTime())
						/ (double) (1000 * 60 * 60);
				workhour = am;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return workhour;
	}

	/**
	 * 获取当前日期的下午工作小时数
	 * 
	 * @param curDate
	 * @return
	 * @throws Exception
	 */
	public double getPMWorkHour(Date date) throws Exception {
		double workhour = 0;
		try {
			String curDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
			/*
			 * List<Daily> list = executor.queryList(Daily.class,
			 * "getPMWorkHour", curDate, curDate);
			 */
			// DONE
			List<WfWorkDateEntity> list = managementService
					.executeCommand(new GetWorkDateOfDayCmd(curDate));
			DateFormat df = new SimpleDateFormat("HH:mm:ss");
			for (int i = 0; i < list.size(); i++) {
				String pmBegin = list.get(i).getPmBeginTime() + ":" + "00";
				String pmEnd = list.get(i).getPmEndTime() + ":" + "00";
				Date pmbegin = df.parse(pmBegin);
				Date pmend = df.parse(pmEnd);
				double pm = (double) (pmend.getTime() - pmbegin.getTime())
						/ (double) (1000 * 60 * 60);
				workhour = pm;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return workhour;
	}

	/*
	 * 查询下午班开始时间
	 */
	public String getPmBegin(Date date) throws Exception {
		String msg = "";
		try {
			String curDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
			/*
			 * List<Daily> list = executor.queryList(Daily.class, "getPmBegin",
			 * curDate, curDate);
			 */
			// DONE
			List<WfWorkDateEntity> list = managementService
					.executeCommand(new GetWorkDateOfDayCmd(curDate));// new
																		// ArrayList<WfWorkDate>();
			for (int i = 0; i < list.size(); i++) {
				msg = list.get(i).getPmBeginTime() + ":" + "00";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	/*
	 * 查询上午班开始时间
	 */
	public String getAmBegin(Date date) throws Exception {
		String msg = "";
		try {
			String curDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
			/*
			 * List<Daily> list = executor.queryList(Daily.class, "getAmBegin",
			 * curDate, curDate);
			 */
			// DONE
			List<WfWorkDateEntity> list = managementService
					.executeCommand(new GetWorkDateOfDayCmd(curDate));// new
																		// ArrayList<WfWorkDate>();

			for (int i = 0; i < list.size(); i++) {
				msg = list.get(i).getAmBeginTime() + ":" + "00";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	/*
	 * 查询下午班结束时间
	 */
	public String getPmEnd(Date date) throws Exception {
		String msg = "";
		try {
			String curDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
			/*
			 * List<Daily> list = executor.queryList(Daily.class, "getPmEnd",
			 * curDate, curDate);
			 */
			// DONE
			List<WfWorkDateEntity> list = managementService
					.executeCommand(new GetWorkDateOfDayCmd(curDate));// new
																		// ArrayList<WfWorkDate>();

			for (int i = 0; i < list.size(); i++) {
				msg = list.get(i).getPmEndTime() + ":" + "00";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	/*
	 * 查询上午班结束时间
	 */
	public String getAmEnd(Date date) throws Exception {
		String msg = "";
		try {
			String curDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
			/*
			 * List<Daily> list = executor.queryList(Daily.class, "getAmEnd",
			 * curDate, curDate);
			 */
			// DONE
			List<WfWorkDateEntity> list = managementService
					.executeCommand(new GetWorkDateOfDayCmd(curDate));// new
																		// ArrayList<WfWorkDate>();

			for (int i = 0; i < list.size(); i++) {
				msg = list.get(i).getAmEndTime() + ":" + "00";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	/**
	 * 获取下一个工作日期
	 * 
	 * @param curDate
	 * @param num
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Date getNextDate(Date curDate, int date) throws Exception {
		Date dt = null;
		try {
			Object key = null;
			Date value = null;
			Map map = new HashMap();
			map.put("0", curDate);
			while (true) {
				for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
					Map.Entry entry = (Map.Entry) it.next();
					key = entry.getKey();
					value = (Date) entry.getValue();
					dt = value;
				}
				if (key.equals("0")) {
					map = getDateResult(value, date);
				} else {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dt;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map getDateResult(Date curDate, int date) throws Exception {
		Map map = new HashMap();
//		boolean flg = false;
		Date dt = null;
		try {
			Calendar d = Calendar.getInstance();
			d.setTime(curDate);
			int year = d.get(Calendar.YEAR);
			int month = d.get(Calendar.MONTH) + 1;
			int da = d.get(Calendar.DATE) + date;
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dt = df.parse(year + "-" + month + "-" + da + " 00:00:00");
			if (this.isWorkDays(dt)) {
				map.put("1", dt);
			} else {
				map.put("0", dt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public Date getDueDateAfterExecute(Date begineDate, Integer duration,
			String durationUnit, String tenantId) throws Exception {
		if (begineDate == null) {
			throw new NullPointerException("开始时间为空");
		}
		if (duration == null || CommonUtil.stringNullOrEmpty(durationUnit)) {
			throw new NullPointerException("期限数值或期限单位为空");
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(begineDate);
		
		String holidayWorkDayFlag = ConfigManager.getInstance().getConfig("c2.flow.core.holidayworkday.flag");
		if (WfConstants.WF_DURATION_UNIT_DAY.equals(durationUnit)
				&& "true".equals(holidayWorkDayFlag)) {
			// 通过作息时间查询真实的天数，通过真实的天数计算期限
			int realDays = getRealdays(begineDate, duration);
			cal.add(Calendar.DAY_OF_MONTH, realDays);
		} else if (WfConstants.WF_DURATION_UNIT_HOUR.equals(durationUnit)
				&& "true".equals(holidayWorkDayFlag)) {

			Date date1 = getOverTimes(begineDate, duration);
			cal.setTime(date1);
		} else {
			return getDueDateNotAfterExecute(begineDate, duration, durationUnit);
		}
		return cal.getTime();
	}

	private Date getDueDateNotAfterExecute(Date date, Integer duration,
			String durationUnit) throws Exception {
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(date);

		if (WfConstants.WF_DURATION_UNIT_YEAR.equals(durationUnit)) {
			rightNow.add(Calendar.YEAR, duration);// 当时日期减1年
		} else if (WfConstants.WF_DURATION_UNIT_MONTH.equals(durationUnit)) {
			rightNow.add(Calendar.MONTH, duration);
		} else if (WfConstants.WF_DURATION_UNIT_DAY.equals(durationUnit)) {
			rightNow.add(Calendar.DAY_OF_MONTH, duration);
		} else if (WfConstants.WF_DURATION_UNIT_HOUR.equals(durationUnit)) {
			rightNow.add(Calendar.HOUR_OF_DAY, duration);
		} else if (WfConstants.WF_DURATION_UNIT_MINUTE.equals(durationUnit)) {
			rightNow.add(Calendar.MINUTE, duration);
		} else if (WfConstants.WF_DURATION_UNIT_SECOND.equals(durationUnit)) {
			rightNow.add(Calendar.SECOND, duration);
		}
		return rightNow.getTime();
	}

	@Override
	public void deleteWorkDates(String[] workIds) throws Exception {
		if (workIds == null || workIds.length == 0) {
			throw new NullPointerException("作息时间ID为空或非法");
		}
		for (String workId : workIds) {
			if (!CommonUtil.stringNullOrEmpty(workId)) {
				WfWorkDate wfWorkDate = new WfWorkDate();
				wfWorkDate.setWorkId(workId);
				deleteWorkDate(wfWorkDate);
			}
		}
	}

	@Override
	public void setDefaultYearHoliday(String yHoliday) throws Exception {
		WfHoliday wfHoliday = new WfHoliday();
		wfHoliday.setYHoliday(yHoliday);
		deleteHoliday(wfHoliday);

		List<Date> weekEnds = DateUtil.getChinaWeekends(Integer
				.parseInt(yHoliday));
		if (weekEnds != null && !weekEnds.isEmpty()) {
			for (Date date : weekEnds) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				int year = cal.get(Calendar.YEAR);// 得到年
				int month = cal.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
//				int day = cal.get(Calendar.DAY_OF_MONTH);// 得到天
				WfHoliday wfh = new WfHoliday();
				wfh.setYHoliday("" + year);
				if (month < 10) {
					wfh.setMHoliday("0" + month);
				} else {
					wfh.setMHoliday(month + "");
				}
				wfh.setHoliday(sdf.format(date));
				addHoliday(wfh);
			}
		}
	}
}
