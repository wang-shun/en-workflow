package com.chinacreator.c2.flow.rest.deploy.web.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.DeploymentQueryProperty;
import org.activiti.engine.impl.bpmn.deployer.BpmnDeployer;
import org.activiti.engine.query.QueryProperty;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.rest.common.application.DefaultMediaTypeResolver;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.flow.api.WfManagerService;
import com.chinacreator.c2.flow.api.WfRepositoryService;
import com.chinacreator.c2.flow.detail.WfDeployment;
import com.chinacreator.c2.flow.detail.WfOperator;
import com.chinacreator.c2.flow.detail.WfPageList;
import com.chinacreator.c2.flow.detail.WfProcessDefinition;
import com.chinacreator.c2.flow.detail.WfProcessDefinitionParam;
import com.chinacreator.c2.flow.rest.common.vo.WfDeploymentRequest;
import com.chinacreator.c2.flow.rest.common.vo.WfDeploymentResourceResponse;
import com.chinacreator.c2.flow.rest.common.vo.WfDeploymentResourceResponse.DeploymentResourceType;
import com.chinacreator.c2.flow.rest.common.vo.WfDeploymentResponse;
import com.chinacreator.c2.flow.rest.common.vo.WfPageListResponse;
import com.chinacreator.c2.flow.rest.deploy.DeploymentsPaginateList;
import com.chinacreator.c2.sysmgr.Subject;
import com.chinacreator.c2.web.exception.InvalidRestParamException;
import com.chinacreator.c2.web.exception.ResourceNotFoundException;
import com.chinacreator.c2.web.exception.UnkownException;

/**
 * 流程定义部署管理
 * 
 * @author hushow
 * 
 */
@Service
@Path("v1/flow/repository/deploy")
@Api
@SwaggerDefinition(tags = { @Tag(name = "repositoryDeployment", description = "流程部署相关操作") })
@ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),
		@ApiResponse(code = 404, message = "操作失败，请求资源未找到"),
		@ApiResponse(code = 500, message = "系统内部错误") })
public class DeploymentResource {

	protected static final String DEPRECATED_API_DEPLOYMENT_SEGMENT = "deployment";

	private static Map<String, QueryProperty> allowedSortProperties = new HashMap<String, QueryProperty>();

	static {
		allowedSortProperties.put("id", DeploymentQueryProperty.DEPLOYMENT_ID);
		allowedSortProperties.put("name",
				DeploymentQueryProperty.DEPLOYMENT_NAME);
		allowedSortProperties.put("deployTime",
				DeploymentQueryProperty.DEPLOY_TIME);
		allowedSortProperties.put("tenantId",
				DeploymentQueryProperty.DEPLOYMENT_TENANT_ID);
	}

	@Autowired
	RepositoryService repositoryService;

	@Autowired
	WfRepositoryService wfRepositoryService;

	@Autowired
	private WfManagerService wfManagerService;

	// @Autowired
	// MultipartResolver multipartResolver;

	@ApiOperation(value = "获取流程定义部署列表", tags = "repositoryDeployment")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public WfPageListResponse<WfDeploymentResponse> getDeployments(
			@ApiParam(value = "部署名称") @QueryParam("name") String name,
			@ApiParam(value = "部署名称模糊") @QueryParam("nameLike") String nameLike,
			@ApiParam(value = "类别") @QueryParam("category") String category,
			@ApiParam(value = "类别不等于") @QueryParam("categoryNotEquals") String categoryNotEquals,
			@ApiParam(value = "租户") @QueryParam("tenantId") String tenantId,
			@ApiParam(value = "租户模糊") @QueryParam("tenantIdLike") String tenantIdLike,
			@ApiParam(value = "省略租户") @QueryParam("withoutTenantId") Boolean withoutTenantId)
			throws Exception {

		try {

			DeploymentQuery deploymentQuery = repositoryService
					.createDeploymentQuery();

			// Apply filters
			if (StringUtils.hasText(name)) {
				deploymentQuery.deploymentName(name);
			}
			if (StringUtils.hasText(nameLike)) {
				deploymentQuery.deploymentNameLike(nameLike);
			}
			if (StringUtils.hasText(category)) {
				deploymentQuery.deploymentCategory(category);
			}
			if (StringUtils.hasText(categoryNotEquals)) {
				deploymentQuery.deploymentCategoryNotEquals(categoryNotEquals);
			}
			if (StringUtils.hasText(tenantId)) {
				deploymentQuery.deploymentTenantId(tenantId);
			}
			if (StringUtils.hasText(tenantIdLike)) {
				deploymentQuery.deploymentTenantIdLike(tenantIdLike);
			}
			if (null != withoutTenantId) {
				if (Boolean.TRUE == withoutTenantId) {
					deploymentQuery.deploymentWithoutTenantId();
				}
			}

			return new DeploymentsPaginateList().paginateList(deploymentQuery,
					"id", allowedSortProperties);

		} catch (ActivitiIllegalArgumentException e) {
			throw new InvalidRestParamException(e.getMessage());
		} catch (ActivitiObjectNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		} catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		}

	}

	@ApiOperation(value = "部署流程定义资源(*bpmn20.xml)", tags = "repositoryDeployment")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public WfDeploymentResponse uploadDeployment(
			@MultipartForm WfDeploymentRequest wfDeploymentRequest)
			throws Exception {

		try {

			if (StringUtils.isEmpty(wfDeploymentRequest.getDeployName())) {
				throw new ActivitiIllegalArgumentException(
						"The deployName cannot be null");
			}

			if (!this.isBpmnResource(wfDeploymentRequest.getDeployName())) {
				throw new ActivitiIllegalArgumentException(
						"The deployName need end with bpmn20.xml or bpmn");
			}

			if (null == wfDeploymentRequest.getDeployResource()) {
				throw new ActivitiIllegalArgumentException(
						"The deployResource cannot be null");
			}

			//允许匿名部署
			WfOperator wfOperator = new WfOperator();
			WebOperationContext context = (WebOperationContext) OperationContextHolder.getContext();
			Subject subject = context.getUser();
			if(null!=subject){
				wfOperator.setUserId(subject.getName());
			}
			
			File file=wfDeploymentRequest.getDeployResource();
			WfDeployment WfDeployment = wfRepositoryService.deployByte(
					wfOperator,FileCopyUtils.copyToByteArray(file), wfDeploymentRequest.getDeployName(),
					wfDeploymentRequest.getCategory());

			// 部署成功后需要更新绑定关系为当前的最新版本的流程
			this.reBindModuleAndProcess(WfDeployment.getId());

			// 给新部署的流程定义添加全局待办监听
			WfProcessDefinitionParam wfProcessDefinitionParam = new WfProcessDefinitionParam();
			wfProcessDefinitionParam.setDeploymentId(WfDeployment.getId());
			WfPageList<WfProcessDefinition, WfProcessDefinitionParam> wfList = wfRepositoryService
					.queryProcessDefinitions(wfProcessDefinitionParam);
			WfProcessDefinition pdf = wfList.getDatas().get(0);
			if (null == wfDeploymentRequest.getTenantId()) {
				wfManagerService.addTaskListener(pdf.getKey());
			} else {
				wfManagerService.addTaskListener(pdf.getKey(),
						wfDeploymentRequest.getTenantId());
			}
			WfDeploymentResponse wr = new WfDeploymentResponse(WfDeployment);
			return wr;
		} catch (ActivitiIllegalArgumentException e) {
			throw new InvalidRestParamException(e.getMessage());
		} catch (ActivitiObjectNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		} catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		}

	}

	@ApiOperation(value = "获取部署信息)", tags = "repositoryDeployment")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/{deploymentId}")
	public WfDeploymentResponse getDeployment(
			@ApiParam(value = "部署id") @PathParam("deploymentId") String deploymentId)
			throws Exception {
		try {
			if (deploymentId == null) {
				throw new ActivitiIllegalArgumentException(
						"The deploymentId cannot be null");
			}

			Deployment deployment = repositoryService.createDeploymentQuery()
					.deploymentId(deploymentId).singleResult();

			if (deployment == null) {
				throw new ActivitiObjectNotFoundException(
						"Could not find a deployment with id '" + deploymentId
								+ "'.", Deployment.class);
			}

			return new WfDeploymentResponse(deployment);
		} catch (ActivitiIllegalArgumentException e) {
			throw new InvalidRestParamException(e.getMessage());
		} catch (ActivitiObjectNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		} catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		}

	}

	@ApiOperation(value = "获取部署下的资源列表)", tags = "repositoryDeployment")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/{deploymentId}/resources")
	public List<WfDeploymentResourceResponse> getDeploymentResources(
			@ApiParam(value = "部署id") @PathParam("deploymentId") String deploymentId)
			throws Exception {

		try {
			if (deploymentId == null) {
				throw new ActivitiIllegalArgumentException(
						"No deployment id provided");
			}

			// Check if deployment exists
			Deployment deployment = repositoryService.createDeploymentQuery()
					.deploymentId(deploymentId).singleResult();
			if (deployment == null) {
				throw new ActivitiObjectNotFoundException(
						"Could not find a deployment with id '" + deploymentId
								+ "'.", Deployment.class);
			}

			List<String> resourceList = repositoryService
					.getDeploymentResourceNames(deploymentId);

			// Add additional metadata to the artifact-strings before returning
			List<WfDeploymentResourceResponse> resposeList = new ArrayList<WfDeploymentResourceResponse>();
			for (String resourceId : resourceList) {

				// Fetch media-type
				DefaultMediaTypeResolver mediaTypeResolver = new DefaultMediaTypeResolver();
				org.restlet.data.MediaType mediaType = mediaTypeResolver
						.resolveMediaType(resourceId);
				String mediaTypeString = (mediaType != null) ? mediaType
						.toString() : null;

				DeploymentResourceType type = DeploymentResourceType.RESOURCE;
				for (String suffix : BpmnDeployer.BPMN_RESOURCE_SUFFIXES) {
					if (resourceId.endsWith(suffix)) {
						type = DeploymentResourceType.PROCESS_DEFINITION;
						break;
					}
				}
				WfDeploymentResourceResponse wfDeploymentResourceResponse = new WfDeploymentResourceResponse(
						resourceId, mediaTypeString, type);
				resposeList.add(wfDeploymentResourceResponse);
			}
			return resposeList;
		} catch (ActivitiIllegalArgumentException e) {
			throw new InvalidRestParamException(e.getMessage());
		} catch (ActivitiObjectNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		} catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		}

	}

	@ApiOperation(value = "获取部署下的某个资源信息)", tags = "repositoryDeployment")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/{deploymentId}/resources/{resourceId}")
	public WfDeploymentResourceResponse getDeploymentResource(
			@ApiParam(value = "部署id") @PathParam("deploymentId") String deploymentId,
			@ApiParam(value = "资源id") @PathParam("resourceId") String resourceId)
			throws Exception {
		try {
			if (deploymentId == null) {
				throw new ActivitiIllegalArgumentException(
						"No deployment id provided");
			}
			if (resourceId == null) {
				throw new ActivitiIllegalArgumentException(
						"No resource id provided");
			}

			// Check if deployment exists
			Deployment deployment = repositoryService.createDeploymentQuery()
					.deploymentId(deploymentId).singleResult();
			if (deployment == null) {
				throw new ActivitiObjectNotFoundException(
						"Could not find a deployment with id '" + deploymentId
								+ "'.", Deployment.class);
			}

			List<String> resourceList = repositoryService
					.getDeploymentResourceNames(deploymentId);

			if (resourceList.contains(resourceId)) {
				// Build resource representation
				// Fetch media-type
				DefaultMediaTypeResolver mediaTypeResolver = new DefaultMediaTypeResolver();
				org.restlet.data.MediaType mediaType = mediaTypeResolver
						.resolveMediaType(resourceId);
				String mediaTypeString = (mediaType != null) ? mediaType
						.toString() : null;

				DeploymentResourceType type = DeploymentResourceType.RESOURCE;
				for (String suffix : BpmnDeployer.BPMN_RESOURCE_SUFFIXES) {
					if (resourceId.endsWith(suffix)) {
						type = DeploymentResourceType.PROCESS_DEFINITION;
						break;
					}
				}
				return new WfDeploymentResourceResponse(resourceId,
						mediaTypeString, type);
			} else {
				// Resource not found in deployment
				throw new ActivitiObjectNotFoundException(
						"Could not find a resource with id '" + resourceId
								+ "' in deployment '" + deploymentId + "'.",
						String.class);
			}
		} catch (ActivitiIllegalArgumentException e) {
			throw new InvalidRestParamException(e.getMessage());
		} catch (ActivitiObjectNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		} catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		}

	}
	
	
	@ApiOperation(value = "删除流程部署", tags = "repositoryDeployment")
	@DELETE
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/{deploymentId}")
	public void deleteDeployment(@ApiParam(value = "部署id") @PathParam("deploymentId") String deploymentId) throws Exception{
		
		try {
			WfOperator wfOperator = new WfOperator();
			WebOperationContext context = (WebOperationContext) OperationContextHolder.getContext();
			Subject subject = context.getUser();
			if(null!=subject){
				wfOperator.setUserId(subject.getName());
			}
			wfRepositoryService.deleteDeploymentsById(wfOperator,false,deploymentId);
			
		} catch (ActivitiIllegalArgumentException e1) {
			throw new InvalidRestParamException(e1.getMessage());
		} catch (ActivitiObjectNotFoundException e2) {
			throw new ResourceNotFoundException(e2.getMessage());
		} catch (Exception e) {
			throw new UnkownException("删除部署失败，检查是否有运行数据");
		}
	}
	
	
	@ApiOperation(value = "下载部署下的资源数据)", tags = "repositoryDeployment",response=Byte.class)
	@GET
	@Produces({ MediaType.APPLICATION_OCTET_STREAM})
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/{deploymentId}/resources/{resourceId}/resourcedata")
	public Response getDeploymentResourceData(
			@ApiParam(value = "部署id") @PathParam("deploymentId") String deploymentId,
			@ApiParam(value = "资源id") @PathParam("resourceId") String resourceId)
			throws Exception {
		try {
			if (deploymentId == null) {
				throw new ActivitiIllegalArgumentException(
						"No deployment id provided");
			}
			if (resourceId == null) {
				throw new ActivitiIllegalArgumentException(
						"No resource id provided");
			}

			// Check if deployment exists
			Deployment deployment = repositoryService.createDeploymentQuery()
					.deploymentId(deploymentId).singleResult();
			if (deployment == null) {
				throw new ActivitiObjectNotFoundException(
						"Could not find a deployment with id '" + deploymentId
								+ "'.", Deployment.class);
			}

			List<String> resourceList = repositoryService.getDeploymentResourceNames(deploymentId);
			if (resourceList.contains(resourceId)) {
				DefaultMediaTypeResolver mediaTypeResolver = new DefaultMediaTypeResolver();
				org.restlet.data.MediaType mediaType = mediaTypeResolver
						.resolveMediaType(resourceId);
				
				InputStream resourceStream = repositoryService.getResourceAsStream(deploymentId,resourceId);
				ResponseBuilder response = Response.ok((Object) resourceStream);
				response.type(mediaType.getName());
				//response.header("Content-Disposition","attachment; filename="+resourceId);
				//response.header("Content-type","application/octet-stream");
				return response.build();
			} else {
				// Resource not found in deployment
				throw new ActivitiObjectNotFoundException(
						"Could not find a resource with id '" + resourceId
								+ "' in deployment '" + deploymentId + "'.",
						String.class);
			}
		} catch (ActivitiIllegalArgumentException e1) {
			return Response.status(Response.Status.BAD_REQUEST).build();
			// throw new InvalidRestParamException(e1.getMessage());
		} catch (ActivitiObjectNotFoundException e2) {
			return Response.status(Response.Status.NOT_FOUND).build();
			// throw e2;
		} catch (Exception e) {
			// throw new UnkownException(e.getMessage(),e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.build();
		}

	}

	private void reBindModuleAndProcess(String deployId) throws Exception {
		if (null != deployId && !"".equals(deployId.trim())) {
			// 根据部署id查询流程定义
			WfProcessDefinitionParam wfProcessDefinitionParam = new WfProcessDefinitionParam();
			wfProcessDefinitionParam.setPaged(false);
			wfProcessDefinitionParam.setDeploymentId(deployId);
			WfPageList<WfProcessDefinition, WfProcessDefinitionParam> wPageList = wfRepositoryService
					.queryProcessDefinitions(wfProcessDefinitionParam);
			if (null != wPageList && wPageList.getDatas().size() > 0) {
				WfProcessDefinition pd = wPageList.getDatas().get(0);
				// 查询流程绑定的事项，如果有绑定事项，则将所有与事项的绑定关系更新为此最新的流程定义
				List<String> moduleIds = wfManagerService
						.getBindModuleIdsByProcessDefKey(pd.getKey());
				if (null != moduleIds && !moduleIds.isEmpty()) {
					for (String module : moduleIds) {
						/*
						 * wfManagerService.unBindProcessFromModule(module, pd);
						 * 
						 * wfManagerService.bindProcessToModule(module, pd);
						 */
						wfManagerService.reBindProcessToModule(module, pd);
					}
				}
			} else {
				// 通过部署ID找不到流程定义，说明流程部署的时候是非法的。
				throw new Exception("文件发布失败！");
			}

		}
	}

	protected boolean isBpmnResource(String resourceName) {
		for (String suffix : BpmnDeployer.BPMN_RESOURCE_SUFFIXES) {
			if (resourceName.endsWith(suffix)) {
				return true;
			}
		}
		return false;
	}

}
