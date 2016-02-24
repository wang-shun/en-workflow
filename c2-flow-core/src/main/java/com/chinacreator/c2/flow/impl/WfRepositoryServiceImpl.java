package com.chinacreator.c2.flow.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.persistence.entity.ModelEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.query.Query;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.task.IdentityLink;

import com.chinacreator.c2.flow.api.WfRepositoryService;
import com.chinacreator.c2.flow.detail.WfActivity;
import com.chinacreator.c2.flow.detail.WfConstants;
import com.chinacreator.c2.flow.detail.WfDeployment;
import com.chinacreator.c2.flow.detail.WfDeploymentParam;
import com.chinacreator.c2.flow.detail.WfIdentityLink;
import com.chinacreator.c2.flow.detail.WfModel;
import com.chinacreator.c2.flow.detail.WfModelParam;
import com.chinacreator.c2.flow.detail.WfOperator;
import com.chinacreator.c2.flow.detail.WfPageList;
import com.chinacreator.c2.flow.detail.WfPageParam;
import com.chinacreator.c2.flow.detail.WfProcessDefinition;
import com.chinacreator.c2.flow.detail.WfProcessDefinitionParam;
import com.chinacreator.c2.flow.detail.WfResource;
import com.chinacreator.c2.flow.util.CommonUtil;
import com.chinacreator.c2.flow.util.LoggerManager;
import com.chinacreator.c2.flow.util.LoggerManager.LoggerType;

public class WfRepositoryServiceImpl implements WfRepositoryService {
	private RepositoryService repositoryService;

	public RepositoryService getRepositoryService() {
		return repositoryService;
	}

	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	@Override
	public WfPageList<WfDeployment, WfDeploymentParam> queryDeployments(
			WfDeploymentParam wfDeploymentParam) throws Exception {
		WfPageList<WfDeployment, WfDeploymentParam> results = new WfPageList<WfDeployment, WfDeploymentParam>();
		DeploymentQuery deploymentQuery = repositoryService
				.createDeploymentQuery();
		List<Deployment> deployments = new ArrayList<Deployment>();
		if (wfDeploymentParam != null) {
			String name = wfDeploymentParam.getName();
			String nameLike = wfDeploymentParam.getNameLike();
			String category = wfDeploymentParam.getCategory();
			String categoryNotEquals = wfDeploymentParam.getCategoryNotEquals();
			String tenantId = wfDeploymentParam.getTenantId();
			boolean withoutTenantId = wfDeploymentParam.getWithoutTenantId();
			String processDefinitionKey = wfDeploymentParam.getProcessDefinitionKey();
			String processDefinitionKeyLike = wfDeploymentParam.getProcessDefinitionKeyLike();
			boolean orderByDeploymentId = wfDeploymentParam
					.getOrderByDeploymentId();
			boolean orderByDeploymenTime = wfDeploymentParam
					.getOrderByDeploymenTime();
			boolean orderByDeploymentName = wfDeploymentParam
					.getOrderByDeploymentName();
			boolean orderByTenantId = wfDeploymentParam.getOrderByTenantId();
			if (name != null) {
				deploymentQuery.deploymentName(name);
			}
			if (nameLike != null) {
				deploymentQuery.deploymentNameLike(nameLike);
			}
			if (category != null) {
				deploymentQuery.deploymentCategory(category);
			}
			if (categoryNotEquals != null) {
				deploymentQuery.deploymentCategoryNotEquals(categoryNotEquals);
			}
			if (tenantId != null) {
				deploymentQuery.deploymentTenantId(tenantId);
			}
			if (withoutTenantId) {
				deploymentQuery.deploymentWithoutTenantId();
			}
			if (processDefinitionKey != null) {
				deploymentQuery.processDefinitionKey(processDefinitionKey);
			}
			if (processDefinitionKeyLike != null) {
				deploymentQuery.processDefinitionKeyLike(processDefinitionKeyLike);
			}
			if (orderByDeploymentId) {
				deploymentQuery.orderByDeploymentId();
				setOrder(deploymentQuery, wfDeploymentParam);
			}
			if (orderByDeploymenTime) {
				deploymentQuery.orderByDeploymenTime();
				setOrder(deploymentQuery, wfDeploymentParam);
			}
			if (orderByDeploymentName) {
				deploymentQuery.orderByDeploymentName();
				setOrder(deploymentQuery, wfDeploymentParam);
			}
			if (orderByTenantId) {
				deploymentQuery.orderByTenantId();
				setOrder(deploymentQuery, wfDeploymentParam);
			}
			boolean paged = wfDeploymentParam.isPaged();
			if (paged) {
				deployments = deploymentQuery.listPage(
						(int) wfDeploymentParam.getStart(),
						(int) wfDeploymentParam.getSize());
				long total = deploymentQuery.count();
				wfDeploymentParam.setTotal(total);
			} else {
				deployments = deploymentQuery.list();
			}
		} else {
			deployments = deploymentQuery.list();
		}
		results.setWfQuery(wfDeploymentParam);
		for (Deployment deployment : deployments) {
			WfDeployment wfd = new WfDeployment();
			wfd.setCategory(deployment.getCategory());
			wfd.setDeploymentTime(deployment.getDeploymentTime());
			wfd.setId(deployment.getId());
			wfd.setName(deployment.getName());
			wfd.setTenantId(deployment.getTenantId());
			results.add(wfd);
		}

		return results;
	}

	@Override
	public WfDeployment getDeploymentById(String deploymentId) throws Exception {
		WfDeployment wfDeployment = null;
		if (null != deploymentId && !"".equals(deploymentId.trim())) {
			Deployment deplogment = repositoryService.createDeploymentQuery()
					.deploymentId(deploymentId).singleResult();
			wfDeployment = new WfDeployment();
			wfDeployment.setId(deploymentId);
			wfDeployment.setName(deplogment.getName());
			wfDeployment.setTenantId(deplogment.getTenantId());
			wfDeployment.setCategory(deplogment.getCategory());
			wfDeployment.setDeploymentTime(deplogment.getDeploymentTime());
		}
		return wfDeployment;
	}

	@Override
	public List<WfResource> queryResourcesByDeploymentId(String deploymentId)
			throws Exception {
		List<WfResource> ret = new ArrayList<WfResource>();
		List<String> resourceNames = repositoryService
				.getDeploymentResourceNames(deploymentId);
		for (String resourceName : resourceNames) {
			ret.add(getDeploymentResourceById(deploymentId, resourceName));
		}
		return ret;
	}

	@Override
	public WfResource getDeploymentResourceById(String deploymentId,
			String resourceId) throws Exception {
		WfResource wfResource = new WfResource();
		InputStream inputStream = repositoryService.getResourceAsStream(
				deploymentId, resourceId);
		if (inputStream != null) {
			try {
				byte[] bytes = CommonUtil.inputStreamToByte(inputStream);
				wfResource.setBytes(bytes);
			} finally {
				inputStream.close();
			}
		}
		wfResource.setDeploymentId(deploymentId);
		wfResource.setId(resourceId);
		wfResource.setName(resourceId);
		return wfResource;
	}

	@Override
	public WfPageList<WfProcessDefinition, WfProcessDefinitionParam> queryProcessDefinitions(
			WfProcessDefinitionParam wfProcessDefinitionParam) throws Exception {
		WfPageList<WfProcessDefinition, WfProcessDefinitionParam> results = new WfPageList<WfProcessDefinition, WfProcessDefinitionParam>();
		ProcessDefinitionQuery processDefinitionQuery = repositoryService
				.createProcessDefinitionQuery();
		String id = wfProcessDefinitionParam.getId();
		Integer version = wfProcessDefinitionParam.getVersion();
		String name = wfProcessDefinitionParam.getName();
		String nameLike = wfProcessDefinitionParam.getNameLike();
		String key = wfProcessDefinitionParam.getKey();
		String keyLike = wfProcessDefinitionParam.getKeyLike();
		String resourceName = wfProcessDefinitionParam.getResourceName();
		String resourceNameLike = wfProcessDefinitionParam
				.getResourceNameLike();
		String category = wfProcessDefinitionParam.getCategory();
		String categoryLike = wfProcessDefinitionParam.getCategoryLike();
		String categoryNotEquals = wfProcessDefinitionParam
				.getCategoryNotEquals();
		String deploymentId = wfProcessDefinitionParam.getDeploymentId();
		String startableByUser = wfProcessDefinitionParam.getStartableByUser();
		Boolean latest = wfProcessDefinitionParam.getLatest();
		Boolean suspended = wfProcessDefinitionParam.getSuspended();
		Boolean orderByCategory = wfProcessDefinitionParam.getOrderByCategory();
		Boolean orderByDeploymentId = wfProcessDefinitionParam
				.getOrderByDeploymentId();
		Boolean orderByKey = wfProcessDefinitionParam.getOrderByKey();
		Boolean orderByName = wfProcessDefinitionParam.getOrderByName();
		Boolean orderByProcessDefinitionId = wfProcessDefinitionParam
				.getOrderByProcessDefinitionId();
		Boolean orderByTenantId = wfProcessDefinitionParam.getOrderByTenantId();
		Boolean orderByVersion = wfProcessDefinitionParam.getOrderByVersion();
		if (id != null) {
			processDefinitionQuery.processDefinitionId(id);
		}
		if (version != null) {
			processDefinitionQuery.processDefinitionVersion(version);
		}
		if (name != null) {
			processDefinitionQuery.processDefinitionName(name);
		}
		if (nameLike != null) {
			processDefinitionQuery.processDefinitionNameLike(nameLike);
		}
		if (key != null) {
			processDefinitionQuery.processDefinitionKey(key);
		}
		if (keyLike != null) {
			processDefinitionQuery.processDefinitionKeyLike(keyLike);
		}
		if (resourceName != null) {
			processDefinitionQuery.processDefinitionResourceName(resourceName);
		}
		if (resourceNameLike != null) {
			processDefinitionQuery
					.processDefinitionResourceNameLike(resourceNameLike);
		}
		if (category != null) {
			processDefinitionQuery.processDefinitionCategory(category);
		}
		if (categoryLike != null) {
			processDefinitionQuery.processDefinitionCategoryLike(categoryLike);
		}
		if (categoryNotEquals != null) {
			processDefinitionQuery
					.processDefinitionCategoryNotEquals(categoryNotEquals);
		}
		if (deploymentId != null) {
			processDefinitionQuery.deploymentId(deploymentId);
		}
		if (startableByUser != null) {
			processDefinitionQuery.startableByUser(startableByUser);
		}
		if (latest) {
			processDefinitionQuery.latestVersion();
		}
		if (suspended) {
			processDefinitionQuery.suspended();
		}

		if (orderByDeploymentId) {
			processDefinitionQuery.orderByDeploymentId();
			setOrder(processDefinitionQuery, wfProcessDefinitionParam);
		}
		if (orderByCategory) {
			processDefinitionQuery.orderByProcessDefinitionCategory();
			setOrder(processDefinitionQuery, wfProcessDefinitionParam);
		}
		if (orderByProcessDefinitionId) {
			processDefinitionQuery.orderByProcessDefinitionId();
			setOrder(processDefinitionQuery, wfProcessDefinitionParam);
		}
		if (orderByKey) {
			processDefinitionQuery.orderByProcessDefinitionKey();
			setOrder(processDefinitionQuery, wfProcessDefinitionParam);
		}
		if (orderByName) {
			processDefinitionQuery.orderByProcessDefinitionName();
			setOrder(processDefinitionQuery, wfProcessDefinitionParam);
		}
		if (orderByVersion) {
			processDefinitionQuery.orderByProcessDefinitionVersion();
			setOrder(processDefinitionQuery, wfProcessDefinitionParam);
		}

		if (orderByTenantId) {
			processDefinitionQuery.orderByTenantId();
			setOrder(processDefinitionQuery, wfProcessDefinitionParam);
		}
		boolean paged = wfProcessDefinitionParam.isPaged();
		List<ProcessDefinition> processDefinitions = new ArrayList<ProcessDefinition>();
		if (paged) {
			processDefinitions = processDefinitionQuery.listPage(
					(int) wfProcessDefinitionParam.getStart(),
					(int) wfProcessDefinitionParam.getSize());
			long total = processDefinitionQuery.count();
			wfProcessDefinitionParam.setTotal(total);
		} else {
			processDefinitions = processDefinitionQuery.list();
		}
		results.setWfQuery(wfProcessDefinitionParam);

		for (ProcessDefinition pd : processDefinitions) {
			WfProcessDefinition wpd = new WfProcessDefinition();
			wpd.setDeploymentId(pd.getDeploymentId());
			wpd.setDescription(pd.getDescription());
			wpd.setDiagramResourceName(pd.getDiagramResourceName());
			wpd.setId(pd.getId());
			wpd.setKey(pd.getKey());
			wpd.setName(pd.getName());
			wpd.setResourceName(pd.getResourceName());
			wpd.setStartFormKey(pd.hasStartFormKey());
			wpd.setSuspended(pd.isSuspended());
			wpd.setTenantId(pd.getTenantId());
			wpd.setVersion(pd.getVersion());
			results.add(wpd);
		}
		return results;
	}

	@Override
	public WfProcessDefinition getProcessDefinition(String processDefinitionId)
			throws Exception {
		ProcessDefinition pd = repositoryService
				.getProcessDefinition(processDefinitionId);
		WfProcessDefinition wpd = new WfProcessDefinition();
		if (pd != null) {
			wpd.setDeploymentId(pd.getDeploymentId());
			wpd.setDescription(pd.getDescription());
			wpd.setDiagramResourceName(pd.getDiagramResourceName());
			wpd.setId(pd.getId());
			wpd.setKey(pd.getKey());
			wpd.setName(pd.getName());
			wpd.setResourceName(pd.getResourceName());
			wpd.setStartFormKey(pd.hasStartFormKey());
			wpd.setSuspended(pd.isSuspended());
			wpd.setTenantId(pd.getTenantId());
			wpd.setVersion(pd.getVersion());
			return wpd;
		} else {
			return null;
		}
	}

	@Override
	public String getProcessDefinitionBPMN(String processDefinitionId)
			throws Exception {
		BpmnModel bpmnModel = repositoryService
				.getBpmnModel(processDefinitionId);
		if (bpmnModel != null) {
			BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
			byte[] bts = bpmnXMLConverter.convertToXML(bpmnModel);
			return new String(bts, WfConstants.WF_CHARSET_UTF_8);
		}
		return null;
	}

	@Override
	public String deployDiagramClassPath(WfOperator wfOper, String name,
			String category, String resourceClassPath) throws Exception {
		WfDeployment ret = new WfDeployment();
		DeploymentBuilder deploymentBuilder = repositoryService
				.createDeployment();
		Deployment deployment = deploymentBuilder.name(name).category(category)
				.tenantId(wfOper.getTenantId())
				.addClasspathResource(resourceClassPath).deploy();
		if (deployment != null) {// 部署成功
			ret.setId(deployment.getId());
			ret.setCategory(deployment.getCategory());
			ret.setDeploymentTime(deployment.getDeploymentTime());
			ret.setId(deployment.getId());
			ret.setName(deployment.getName());
			ret.setTenantId(deployment.getTenantId());

			LoggerManager.log(getClass(), LoggerType.DEBUG, wfOper, null,
					"部署流程(deployDiagramClassPath)成功:名称={},类别={},资源路径={}",
					name, category, resourceClassPath);
			return WfConstants.WF_CONTROL_EXE_SUCCESS;
		} else {
			LoggerManager.log(getClass(), LoggerType.DEBUG, wfOper, null,
					"部署流程(deployDiagramClassPath)失败:名称={},类别={},资源路径={}",
					name, category, resourceClassPath);
		}
		return WfConstants.WF_CONTROL_EXE_FAIL;
	}

	@Override
	public String deployDiagramContent(WfOperator wfOper, String name,
			String category, String resourceName, String resourceContent)
			throws Exception {
		WfDeployment ret = new WfDeployment();
		String tenantId = wfOper.getTenantId();
		DeploymentBuilder deploymentBuilder = repositoryService
				.createDeployment();
		Deployment deployment = deploymentBuilder.name(name).category(category)
				.tenantId(tenantId).addString(resourceName, resourceContent)
				.deploy();
		if (deployment != null) {// 部署成功
			ret.setId(deployment.getId());
			ret.setCategory(deployment.getCategory());
			ret.setDeploymentTime(deployment.getDeploymentTime());
			ret.setId(deployment.getId());
			ret.setName(deployment.getName());
			ret.setTenantId(deployment.getTenantId());
			LoggerManager.log(getClass(), LoggerType.DEBUG, wfOper, null,
					"部署流程(deployDiagramContent)成功:名称={},类别={},资源名称={}", name,
					category, resourceName);
			return WfConstants.WF_CONTROL_EXE_SUCCESS;
		} else {
			LoggerManager.log(getClass(), LoggerType.DEBUG, wfOper, null,
					"部署流程(deployDiagramContent)失败:名称={},类别={},资源名称={}", name,
					category, resourceName);
		}
		return WfConstants.WF_CONTROL_EXE_FAIL;
	}

	@Override
	public String deployDiagramZip(WfOperator wfOper, String name,
			String category, InputStream inputStream) throws Exception {
		WfDeployment ret = new WfDeployment();
		String tenantId = wfOper.getTenantId();
		DeploymentBuilder deploymentBuilder = repositoryService
				.createDeployment();
		ZipInputStream zis = new ZipInputStream(inputStream);
		Deployment deployment = deploymentBuilder.name(name).category(category)
				.tenantId(tenantId).addZipInputStream(zis).deploy();
		if (deployment != null) {// 部署成功
			ret.setId(deployment.getId());
			ret.setCategory(deployment.getCategory());
			ret.setDeploymentTime(deployment.getDeploymentTime());
			ret.setId(deployment.getId());
			ret.setName(deployment.getName());
			ret.setTenantId(deployment.getTenantId());
			LoggerManager.log(getClass(), LoggerType.DEBUG, wfOper, null,
					"部署流程(deployDiagramZip)成功:名称={},类别={}", name, category);
			return WfConstants.WF_CONTROL_EXE_SUCCESS;
		} else {
			LoggerManager.log(getClass(), LoggerType.DEBUG, wfOper, null,
					"部署流程(deployDiagramZip)失败:名称={},类别={}", name, category);
		}
		return WfConstants.WF_CONTROL_EXE_FAIL;
	}

	@Override
	public String deleteDeploymentsById(WfOperator wfOper, boolean casecade,
			String deploymentId) throws Exception {
		try {
			repositoryService.deleteDeployment(deploymentId, casecade);
		} catch (ActivitiObjectNotFoundException e) {
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOper, e,
					"删除流程部署失败:部署id={}", deploymentId);
			e.printStackTrace();
			return WfConstants.WF_CONTROL_EXE_SUCCESS;
		} catch (Exception e) {
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOper, e,
					"删除流程部署失败:部署id={}", deploymentId);
			throw e;
		}
		LoggerManager.log(getClass(), LoggerType.ERROR, wfOper, null,
				"删除流程部署成功:部署id={}", deploymentId);
		return WfConstants.WF_CONTROL_EXE_SUCCESS;
	}

	@Override
	public String updateProcessDefinitionCategory(WfOperator wfOper,
			String processDefinitionId, String category) throws Exception {
		try {
			repositoryService.setProcessDefinitionCategory(processDefinitionId,
					category);
			LoggerManager.log(getClass(), LoggerType.DEBUG, wfOper, null,
					"更新流程分类成功:流程定义id={}, 分类={}", processDefinitionId,
					category);
		} catch (ActivitiObjectNotFoundException e) {
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOper, e,
					"更新流程分类失败:流程定义id={}, 分类={}", processDefinitionId,
					category);
			return WfConstants.WF_CONTROL_EXE_SUCCESS;
		} catch (Exception e) {
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOper, e,
					"更新流程分类失败:流程定义id={}, 分类={}", processDefinitionId,
					category);
			throw e;
		}
		return WfConstants.WF_CONTROL_EXE_SUCCESS;
	}

	@Override
	public String suspendProcessDefinition(WfOperator wfOper,
			String processDefinitionId) throws Exception {

		try {
			repositoryService.suspendProcessDefinitionById(processDefinitionId);
			LoggerManager.log(getClass(), LoggerType.DEBUG, wfOper, null,
					"挂起流程定义成功:流程定义id={}", processDefinitionId);
		} catch (ActivitiObjectNotFoundException e) {
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOper, e,
					"挂起流程定义失败:流程定义id={}", processDefinitionId);
			return WfConstants.WF_CONTROL_EXE_NOOBJECT;
		} catch (Exception e) {
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOper, e,
					"挂起流程定义失败:流程定义id={}", processDefinitionId);
			throw e;
		}
		return WfConstants.WF_CONTROL_EXE_SUCCESS;
	}

	@Override
	public String activateProcessDefinition(WfOperator wfOper,
			String processDefinitionId) throws Exception {
		try {
			repositoryService
					.activateProcessDefinitionById(processDefinitionId);
			LoggerManager.log(getClass(), LoggerType.DEBUG, wfOper, null,
					"恢复流程定义成功:流程定义id={}", processDefinitionId);
		} catch (ActivitiObjectNotFoundException e) {
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOper, e,
					"恢复流程定义失败:流程定义id={}", processDefinitionId);
			return WfConstants.WF_CONTROL_EXE_NOOBJECT;
		} catch (Exception e) {
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOper, e,
					"恢复流程定义失败:流程定义id={}", processDefinitionId);
			throw e;
		}
		return WfConstants.WF_CONTROL_EXE_SUCCESS;
	}

	@Override
	public List<WfIdentityLink> getProcessDefinitionCandidates(
			String processDefinitionId) throws Exception {
		List<WfIdentityLink> ret = new ArrayList<WfIdentityLink>();
		List<IdentityLink> ils = repositoryService
				.getIdentityLinksForProcessDefinition(processDefinitionId);
		if (ils != null) {
			for (IdentityLink il : ils) {
				WfIdentityLink wil = new WfIdentityLink();
				wil.setGroupId(il.getGroupId());
				wil.setProcessDefinitionId(il.getProcessDefinitionId());
				wil.setProcessInstanceId(wil.getProcessInstanceId());
				wil.setTaskId(il.getTaskId());
				wil.setType(il.getType());
				wil.setUserId(il.getUserId());
				ret.add(wil);
			}
			return ret;
		}
		return null;
	}

	@Override
	public String deleteProcessDefinitionCandidate(WfOperator wfOper,
			String processDefinitionId, String identityLinkType,
			String identityId) throws Exception {
		try {
			if (WfConstants.WF_IDENTITYLINKTYPE_USERS.equals(identityLinkType)) {
				repositoryService.deleteCandidateStarterUser(
						processDefinitionId, identityId);
			} else {
				repositoryService.deleteCandidateStarterGroup(
						processDefinitionId, identityId);
			}
			LoggerManager.log(getClass(), LoggerType.DEBUG, wfOper, null,
					"删除流程定义启动者成功:流程定义id={}, 启动者类型={}, identityId={}",
					processDefinitionId, identityLinkType, identityId);
		} catch (Exception e) {
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOper, e,
					"删除流程定义启动者失败:流程定义id={}, 启动者类型={}, identityId={}",
					processDefinitionId, identityLinkType, identityId);
			throw e;
		}
		return WfConstants.WF_CONTROL_EXE_SUCCESS;
	}

	@Override
	public WfPageList<WfModel, WfModelParam> queryModels(
			WfModelParam wfModelParam) throws Exception {
		WfPageList<WfModel, WfModelParam> results = new WfPageList<WfModel, WfModelParam>();
		ModelQuery modelQuery = repositoryService.createModelQuery();
		String modelId = wfModelParam.getId();
		String name = wfModelParam.getName();
		String nameLike = wfModelParam.getNameLike();
		String category = wfModelParam.getCategory();
		String categoryLike = wfModelParam.getCategoryLike();
		String categoryNotEquals = wfModelParam.getCategoryNotEquals();
		Boolean deployed = wfModelParam.getDeployed();
		String deploymentId = wfModelParam.getDeploymentId();
		String key = wfModelParam.getKey();
		boolean latestVersion = wfModelParam.getLatestVersion();
		String tenantId = wfModelParam.getTenantId();
		boolean withoutTenantId = wfModelParam.getWithoutTenantId();
		String tenantIdLike = wfModelParam.getTenantIdLike();
		if (modelId != null) {
			modelQuery.modelId(modelId);
		}
		if (name != null) {
			modelQuery.modelName(name);
		}
		if (nameLike != null) {
			modelQuery.modelNameLike(nameLike);
		}
		if (category != null) {
			modelQuery.modelCategory(category);
		}
		if (categoryLike != null) {
			modelQuery.modelCategoryLike(categoryLike);
		}
		if (categoryNotEquals != null) {
			modelQuery.modelCategoryNotEquals(categoryNotEquals);
		}
		if (deployed != null) {
			if (deployed) {
				modelQuery.deployed();
			} else {
				modelQuery.notDeployed();
			}
		}
		if (deploymentId != null) {
			modelQuery.deploymentId(deploymentId);
		}
		if (key != null) {
			modelQuery.modelKey(key);
		}
		if (latestVersion) {
			modelQuery.latestVersion();
		}
		if (tenantId != null) {
			modelQuery.modelTenantId(tenantId);
		}
		if (tenantIdLike != null) {
			modelQuery.modelTenantIdLike(tenantIdLike);
		}
		if (withoutTenantId) {
			modelQuery.modelWithoutTenantId();
		}
		boolean orderByCreateTime = wfModelParam.getOrderByCreateTime();
		if (orderByCreateTime) {
			modelQuery.orderByCreateTime();
			setOrder(modelQuery, wfModelParam);
		}
		boolean orderByLastUpdateTime = wfModelParam.getOrderByLastUpdateTime();
		if (orderByLastUpdateTime) {
			modelQuery.orderByLastUpdateTime();
			setOrder(modelQuery, wfModelParam);
		}
		boolean orderByModelCategory = wfModelParam.getOrderByModelCategory();
		if (orderByModelCategory) {
			modelQuery.orderByModelCategory();
			setOrder(modelQuery, wfModelParam);
		}
		boolean orderByModelId = wfModelParam.getOrderByModelId();
		if (orderByModelId) {
			modelQuery.orderByModelId();
			setOrder(modelQuery, wfModelParam);
		}
		boolean orderByModelKey = wfModelParam.getOrderByModelKey();
		if (orderByModelKey) {
			modelQuery.orderByModelKey();
			setOrder(modelQuery, wfModelParam);
		}
		boolean orderByModelName = wfModelParam.getOrderByModelName();
		if (orderByModelName) {
			modelQuery.orderByModelName();
			setOrder(modelQuery, wfModelParam);
		}
		boolean orderByModelVersion = wfModelParam.getOrderByModelVersion();
		if (orderByModelVersion) {
			modelQuery.orderByModelVersion();
			setOrder(modelQuery, wfModelParam);
		}
		boolean orderByTenantId = wfModelParam.getOrderByTenantId();
		if (orderByTenantId) {
			modelQuery.orderByTenantId();
			setOrder(modelQuery, wfModelParam);
		}
		boolean paged = wfModelParam.isPaged();
		List<Model> models = new ArrayList<Model>();
		if (paged) {
			models = modelQuery.listPage((int) wfModelParam.getStart(),
					(int) wfModelParam.getSize());
			long total = modelQuery.count();
			wfModelParam.setTotal(total);
		} else {
			models = modelQuery.list();
		}
		results.setWfQuery(wfModelParam);
		for (Model model : models) {
			WfModel wfm = new WfModel();
			wfm.setCategory(model.getCategory());
			wfm.setCreateTime(model.getCreateTime());
			wfm.setDeploymentId(model.getDeploymentId());
			wfm.setId(model.getId());
			wfm.setKey(model.getKey());
			wfm.setLastUpdateTime(model.getLastUpdateTime());
			wfm.setMetaInfo(model.getMetaInfo());
			wfm.setName(model.getName());
			wfm.setVersion(model.getVersion());
			wfm.setTenantId(model.getTenantId());
			results.add(wfm);
		}
		return results;
	}

	private void setOrder(Query<?, ?> query, WfPageParam wfPageParam) {
		String order = wfPageParam.getOrder();
		if (WfPageParam.SORT_ASC.equals(order)) {
			query.asc();
		}
		if (WfPageParam.SORT_DESC.equals(order)) {
			query.desc();
		}
	}

	@Override
	public WfModel getModelById(String modelId) throws Exception {
		Model model = repositoryService.getModel(modelId);
		WfModel wfm = new WfModel();
		wfm.setCategory(model.getCategory());
		wfm.setCreateTime(model.getCreateTime());
		wfm.setDeploymentId(model.getDeploymentId());
		wfm.setId(model.getId());
		wfm.setKey(model.getKey());
		wfm.setLastUpdateTime(model.getLastUpdateTime());
		wfm.setMetaInfo(model.getMetaInfo());
		wfm.setName(model.getName());
		wfm.setVersion(model.getVersion());
		wfm.setTenantId(model.getTenantId());
		return wfm;
	}

	@Override
	public String addModel(WfOperator wfOper, WfModel wfModel) throws Exception {
		try {
			Model model = new ModelEntity();
			model.setCategory(wfModel.getCategory());
			model.setDeploymentId(wfModel.getDeploymentId());
			model.setKey(wfModel.getKey());
			model.setMetaInfo(wfModel.getMetaInfo());
			model.setName(wfModel.getName());
			model.setVersion(wfModel.getVersion());
			model.setTenantId(wfModel.getTenantId());
			repositoryService.saveModel(model);
			LoggerManager.log(getClass(), LoggerType.DEBUG, wfOper, null,
					"添加模型成功:模型={}", wfModel);
		} catch (Exception e) {
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOper, e,
					"添加模型失败:模型={}", wfModel);
			throw e;
		}
		return WfConstants.WF_CONTROL_EXE_SUCCESS;
	}
	
	@Override
	public WfModel insertModel(WfOperator wfOperator, WfModel wfModel) throws Exception {
		
		
		try {
			Model model = new ModelEntity();
			model.setCategory(wfModel.getCategory());
			model.setDeploymentId(wfModel.getDeploymentId());
			model.setKey(wfModel.getKey());
			model.setMetaInfo(wfModel.getMetaInfo());
			model.setName(wfModel.getName());
			model.setVersion(wfModel.getVersion());
			model.setTenantId(wfModel.getTenantId());
			repositoryService.saveModel(model);
			wfModel.setId(model.getId());
			
			LoggerManager.log(getClass(), LoggerType.DEBUG, wfOperator, null,
					"添加模型成功:模型={}", wfModel);
		} catch (Exception e) {
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOperator, e,
					"添加模型失败:模型={}", wfModel);
			throw e;
		}
		return wfModel;
	}

	@Override
	public String updateModel(WfOperator wfOper, String modelId, WfModel wfModel)
			throws Exception {
		try {
			Model model = repositoryService.getModel(modelId);
			model.setCategory(wfModel.getCategory());
			model.setDeploymentId(wfModel.getDeploymentId());
			model.setKey(wfModel.getKey());
			model.setMetaInfo(wfModel.getMetaInfo());
			model.setName(wfModel.getName());
			model.setVersion(wfModel.getVersion());
			model.setTenantId(wfModel.getTenantId());
			repositoryService.saveModel(model);
			LoggerManager.log(getClass(), LoggerType.DEBUG, wfOper, null,
					"更新模型成功:模型id={}, 模型={}", modelId, wfModel);
		} catch (Exception e) {
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOper, e,
					"更新模型失败:模型id={}, 模型={}", modelId, wfModel);
			throw e;
		}
		return WfConstants.WF_CONTROL_EXE_SUCCESS;
	}

	@Override
	public String deleteModelsById(WfOperator wfOper, String modelId)
			throws Exception {
		try {
			repositoryService.deleteModel(modelId);
			LoggerManager.log(getClass(), LoggerType.DEBUG, wfOper, null,
					"删除模型成功:模型id={}", modelId);
		} catch (Exception e) {
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOper, e,
					"删除模型失败:模型id={}", modelId);
			throw e;
		}
		return WfConstants.WF_CONTROL_EXE_SUCCESS;
	}

	@Override
	public String getModelEditorSource(String modelId) throws Exception {
		byte[] modelSource = repositoryService.getModelEditorSource(modelId);
		if (modelSource != null) {
			return new String(modelSource, WfConstants.WF_CHARSET_UTF_8);
		}
		return null;
	}

	@Override
	public String saveModelEditorSource(WfOperator wfOper, String modelId,
			String modelSource) throws Exception {
		try {
			if (modelSource != null) {
				byte[] bytes = modelSource.getBytes("utf-8");
				repositoryService.addModelEditorSource(modelId, bytes);
				LoggerManager.log(getClass(), LoggerType.DEBUG, wfOper, null,
						"保存模型源码成功:模型id={}, 源码={}", modelId, modelSource);
				return WfConstants.WF_CONTROL_EXE_SUCCESS;
			}
		} catch (Exception e) {
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOper, e,
					"保存模型源码失败:模型id={}, 源码={}", modelId, modelSource);
			throw e;
		}
		return WfConstants.WF_CONTROL_EXE_FAIL;
	}

	@Override
	public String getModelEditorSourceExtra(String modelId) throws Exception {
		byte[] modelSourceExt = repositoryService
				.getModelEditorSourceExtra(modelId);
		if (modelSourceExt != null) {
			return new String(modelSourceExt, WfConstants.WF_CHARSET_UTF_8);
		}
		return null;
	}

	@Override
	public String saveModelEditorSourceExtra(WfOperator wfOper, String modelId,
			String modelSource) throws Exception {
		try {
			if (modelSource != null) {
				byte[] bytes = modelSource.getBytes();
				repositoryService.addModelEditorSourceExtra(modelId, bytes);
				LoggerManager.log(getClass(), LoggerType.DEBUG, wfOper, null,
						"保存模型扩展源码成功:模型id={}, 源码={}", modelId, modelSource);
				return WfConstants.WF_CONTROL_EXE_SUCCESS;
			}
		} catch (Exception e) {
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOper, e,
					"保存模型扩展源码失败:模型id={}, 源码={}", modelId, modelSource);
			throw e;
		}
		return WfConstants.WF_CONTROL_EXE_FAIL;
	}

	@Override
	public WfIdentityLink addProcessDefinitionCandidate(WfOperator wfOper,
			String processDefinitionId, String identityLinkType,
			String identityId) throws Exception {
		try {
			if (WfConstants.WF_IDENTITYLINKTYPE_USERS.equals(identityLinkType)) {
				repositoryService.addCandidateStarterUser(processDefinitionId,
						identityId);
			} else {
				repositoryService.addCandidateStarterGroup(processDefinitionId,
						identityId);
			}
			LoggerManager.log(getClass(), LoggerType.DEBUG, wfOper, null,
					"添加流程定义启动者成功:流程定义id={}, 启动者类型={}, identityId={}",
					processDefinitionId, identityLinkType, identityId);
		} catch (Exception e) {
			LoggerManager.log(getClass(), LoggerType.ERROR, wfOper, e,
					"添加流程定义启动者失败:流程定义id={}, 启动者类型={}, identityId={}",
					processDefinitionId, identityLinkType, identityId);
			throw e;
		}
		return null;
	}

	
	@Override
	public String getNextActivityId(String processDefinitionId,
			String currenTaskDefId) throws Exception {
		String nextActivityId = "";
		if(CommonUtil.stringNullOrEmpty(processDefinitionId)){
			throw new NullPointerException("流程定义ID为空！");
		}
		if(CommonUtil.stringNullOrEmpty(currenTaskDefId)){
			throw new NullPointerException("当前活动定义KEY为空！");
		}
		ProcessDefinitionEntity processDefEntity = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(processDefinitionId);
		List<ActivityImpl> ActivityImplList = processDefEntity.getActivities();
		if(null!=ActivityImplList && !ActivityImplList.isEmpty()){
			for(ActivityImpl ai : ActivityImplList){
				if(currenTaskDefId.equals(ai.getId())){
					//已找到当前活动定义对象
					List<PvmTransition> outTransitions = ai.getOutgoingTransitions();
					for (PvmTransition tr : outTransitions) {
						PvmActivity ac = tr.getDestination(); // 获取线路的终点节点
						nextActivityId = ac.getId();
					}
					break;
				}
			}
		}
		return nextActivityId;
	}
	
	@Override
	public Map<String, Object> queryVariablesOfActivityInDefinition(
			String processDefinitionId, String taskDefId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if(CommonUtil.stringNullOrEmpty(processDefinitionId)){
			throw new NullPointerException("流程定义ID为空！");
		}
		if(CommonUtil.stringNullOrEmpty(taskDefId)){
			throw new NullPointerException("活动定义KEY为空！");
		}
//		ProcessDefinitionEntity processDefEntity = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(processDefinitionId);
//		ActivityImpl ai = processDefEntity.findActivity(taskDefId);
//		TaskDefinition tdf = (TaskDefinition)ai.getProperty("taskDefinition");
//		Set<Expression> set = tdf.getCandidateUserIdExpressions();
		//map = ai.getVariables();
//		map.put("candidateUserIdExpressions", set.);
		return map;
	}
	
	
	@Override
	public List<WfActivity> getActivitiesByDefinition(String processDefinitionId)
			throws Exception {
		List<WfActivity> result = new ArrayList<WfActivity>();

		// 获得当前流程的定义模型
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
		
		// 获得当前流程定义模型的所有任务节点
		List<ActivityImpl> activitilist = processDefinition.getActivities();
		for (ActivityImpl activity : activitilist) {
			WfActivity wai = new WfActivity();
			wai.setHeight(activity.getHeight());
			wai.setId(activity.getId());
			wai.setVariables(activity.getVariables());
			wai.setWidth(activity.getWidth());
			wai.setX(activity.getX());
			wai.setY(activity.getY());
			
			//排除非序列化的属性
			Map<String, Object> properties=new HashMap<String, Object>();
			properties.put("type", activity.getProperties().get("type"));
			wai.setProperties(properties);
			
			result.add(wai);
		}
		return result;
	}
	
	
}
