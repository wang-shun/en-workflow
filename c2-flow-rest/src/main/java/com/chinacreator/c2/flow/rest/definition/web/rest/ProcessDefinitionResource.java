/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.chinacreator.c2.flow.rest.definition.web.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.ProcessDefinitionQueryProperty;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.query.QueryProperty;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.chinacreator.c2.flow.api.WfManagerService;
import com.chinacreator.c2.flow.rest.common.vo.WfPageListResponse;
import com.chinacreator.c2.flow.rest.common.vo.WfProcessDefinitionActionRequest;
import com.chinacreator.c2.flow.rest.common.vo.WfProcessDefinitionResponse;
import com.chinacreator.c2.flow.rest.definition.BaseProcessDefinitionResource;
import com.chinacreator.c2.flow.rest.definition.WfProcessDefinitionsPaginateList;
import com.chinacreator.c2.web.exception.InvalidRestParamException;
import com.chinacreator.c2.web.exception.ResourceNotFoundException;
import com.chinacreator.c2.web.exception.UnkownException;

/**
 * 流程定义
 * 
 * @author hushow
 * 
 */
@Controller
@Path("v1/flow/repository/definitions")
@Api
@ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),@ApiResponse(code = 404, message = "操作失败，请求资源未找到"),@ApiResponse(code = 500, message = "系统内部错误") })
@SwaggerDefinition(tags = { @Tag(name = "repositoryDefinition", description = "流程定义相关操作") })
public class ProcessDefinitionResource extends BaseProcessDefinitionResource {
	
	private static final Map<String, QueryProperty> properties = new HashMap<String, QueryProperty>();
	
    static {
	    properties.put("id", ProcessDefinitionQueryProperty.PROCESS_DEFINITION_ID);
	    properties.put("key", ProcessDefinitionQueryProperty.PROCESS_DEFINITION_KEY);
	    properties.put("category", ProcessDefinitionQueryProperty.PROCESS_DEFINITION_CATEGORY);
	    properties.put("name", ProcessDefinitionQueryProperty.PROCESS_DEFINITION_NAME);
	    properties.put("version", ProcessDefinitionQueryProperty.PROCESS_DEFINITION_VERSION);
	    properties.put("deploymentId", ProcessDefinitionQueryProperty.DEPLOYMENT_ID);
	}

	@Autowired
	RepositoryService repositoryService;
	
	@Autowired
	RuntimeService runtimeService;
	
	@Autowired
	WfManagerService wfManagerService;

	@ApiOperation(value = "流程定义列表", tags = "repositoryDefinition")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public WfPageListResponse<WfProcessDefinitionResponse> getProcessDefinitions(@ApiParam(value = "类别") @QueryParam("category") String category,
																				@ApiParam(value = "类别模糊过滤") @QueryParam("categoryLike") String categoryLike,
																				@ApiParam(value = "类别不等于") @QueryParam("categoryNotEquals") String categoryNotEquals,
																				@ApiParam(value = "流程标识(定义key)") @QueryParam("key") String key,
																				@ApiParam(value = "流程标识模糊") @QueryParam("keyLike") String keyLike,
																				@ApiParam(value = "定义名称") @QueryParam("name") String name,
																				@ApiParam(value = "定义名称模湖") @QueryParam("nameLike") String nameLike,
																				@ApiParam(value = "资源名称") @QueryParam("resourceName") String resourceName,
																				@ApiParam(value = "资源名称模湖") @QueryParam("resourceNameLike") String resourceNameLike,
																				@ApiParam(value = "版本") @QueryParam("version") Integer version,
																				@ApiParam(value = "是否暂停的") @QueryParam("suspended") Boolean suspended,
																				@ApiParam(value = "是否最新版本") @QueryParam("latest") Boolean latest,
																				@ApiParam(value = "部署id") @QueryParam("deploymentId") String deploymentId) throws Exception{

		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();

		if (StringUtils.hasText(category)) {
			processDefinitionQuery.processDefinitionCategory(category);
		}
		if (StringUtils.hasText(categoryLike)) {
			processDefinitionQuery
					.processDefinitionCategoryLike(categoryLike);
		}
		if (StringUtils.hasText(categoryNotEquals)) {
			processDefinitionQuery.processDefinitionCategoryNotEquals(categoryNotEquals);
		}
		if (StringUtils.hasText(key)) {
			processDefinitionQuery.processDefinitionKey(key);
		}
		if (StringUtils.hasText(keyLike)) {
			processDefinitionQuery.processDefinitionKeyLike(keyLike);
		}
		if (StringUtils.hasText(name)) {
			processDefinitionQuery.processDefinitionName(name);
		}
		if (StringUtils.hasText(nameLike)) {
			processDefinitionQuery.processDefinitionNameLike(nameLike);
		}
		if (StringUtils.hasText(resourceName)) {
			processDefinitionQuery.processDefinitionResourceName(resourceName);
		}
		if (StringUtils.hasText(resourceNameLike)) {
			processDefinitionQuery.processDefinitionResourceNameLike(resourceNameLike);
		}
		if (null!=version) {
			processDefinitionQuery.processDefinitionVersion(version);
		}
		if (null!=suspended) {
			if (suspended) {
				processDefinitionQuery.suspended();
			} else {
				processDefinitionQuery.active();
			}
		}
		if (null!=latest) {
			if(latest) {
				processDefinitionQuery.latestVersion();
			}
		}
		if (StringUtils.hasText(deploymentId)) {
			processDefinitionQuery.deploymentId(deploymentId);
		}
//		if (StringUtils.hasText(startableByUser)) {
//			processDefinitionQuery.startableByUser(startableByUser);
//		}

		return new WfProcessDefinitionsPaginateList().paginateList(processDefinitionQuery, "name", properties);

	}
	
	
	@ApiOperation(value = "获取单个流程信息", tags = "repositoryDefinition")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/{processDefinitionId}")
	public WfProcessDefinitionResponse getProcessDefinition(@ApiParam(value="流程定义id") @PathParam("processDefinitionId") String processDefinitionId) throws Exception{

		
		try {
			
			ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
			
			ProcessDefinition processDefinition=this.getProcessDefinitionFromRequest(processDefinitionQuery, processDefinitionId);
			
			WfProcessDefinitionResponse wfProcessDefinition=new WfProcessDefinitionResponse(processDefinition);
			wfProcessDefinition.setGraphicalNotationDefined(((ProcessDefinitionEntity) processDefinition).isGraphicalNotationDefined());
			return wfProcessDefinition;
		} catch (ActivitiIllegalArgumentException e1) {
			throw new InvalidRestParamException(e1.getMessage());
		} catch (ActivitiObjectNotFoundException e2) {
			throw new ResourceNotFoundException(e2.getMessage());
		} catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		}
	}
	
	
	@ApiOperation(value = "修改流程某些信息", tags = "repositoryDefinition")
	@PUT
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/{processDefinitionId}")
	public WfProcessDefinitionResponse executeProcessDefinitionAction(@ApiParam(value="流程定义id") @PathParam("processDefinitionId") String processDefinitionId,
			WfProcessDefinitionActionRequest actionRequest) throws Exception{

		
		try {
			
			if (actionRequest == null) {
				throw new ActivitiIllegalArgumentException("No action found in request body.");
			}
			
			ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
			ProcessDefinition processDefinition=this.getProcessDefinitionFromRequest(processDefinitionQuery, processDefinitionId);
			
			WfProcessDefinitionResponse wfProcessDefinitionResponse=new WfProcessDefinitionResponse(processDefinition);
			wfProcessDefinitionResponse.setGraphicalNotationDefined(((ProcessDefinitionEntity) processDefinition).isGraphicalNotationDefined());
			
			if (actionRequest.getCategory() != null) {
				// Update of category required
				repositoryService.setProcessDefinitionCategory(processDefinition.getId(), actionRequest.getCategory());
				wfProcessDefinitionResponse.setCategory(actionRequest.getCategory());
				return wfProcessDefinitionResponse;
			} else {
				// Actual action
				if (actionRequest.getAction() != null) {
					if (WfProcessDefinitionActionRequest.ProcessDefinitionAction.SUSPEND.equals(actionRequest.getAction())) {
						
					    if(processDefinition.isSuspended()) {
						      throw new ActivitiIllegalArgumentException("Process definition with id '" + processDefinition.getId() + " ' is already suspended");
						}
						repositoryService.suspendProcessDefinitionById(processDefinition.getId(),actionRequest.isIncludeProcessInstances(),actionRequest.getDate());
					    // No need to re-fetch the ProcessDefinition, just alter the suspended state of the result-object
						wfProcessDefinitionResponse.setSuspended(true);
						return wfProcessDefinitionResponse;

					} else if (WfProcessDefinitionActionRequest.ProcessDefinitionAction.ACTIVATE.equals(actionRequest.getAction())) {
						
					    if(!processDefinition.isSuspended()) {
					        throw new ActivitiIllegalArgumentException("Process definition with id '" + processDefinition.getId() + " ' is already active");
					      }
					      repositoryService.activateProcessDefinitionById(processDefinition.getId(), actionRequest.isIncludeProcessInstances(), actionRequest.getDate());
					      // No need to re-fetch the ProcessDefinition, just alter the suspended state of the result-object
					      wfProcessDefinitionResponse.setSuspended(false);
						  return wfProcessDefinitionResponse;
					}
				}

				throw new ActivitiIllegalArgumentException("Invalid action: '"+ actionRequest.getAction() + "'.");
			}
			
		} catch (ActivitiIllegalArgumentException e1) {
			throw new InvalidRestParamException(e1.getMessage());
		} catch (ActivitiObjectNotFoundException e2) {
			throw new ResourceNotFoundException(e2.getMessage());
		} catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		}
	}
	
	
	@ApiOperation(value = "下载流程定义文件", tags = "repositoryDefinition",response=Byte.class)
	@GET
	@Produces({ MediaType.APPLICATION_OCTET_STREAM })
	@Path("/{processDefinitionId}/resourcedata")
	public Response getProcessDefinitionResourceData(@ApiParam(value = "流程定义id") @PathParam("processDefinitionId") String processDefinitionId)  throws Exception{
		try {

			ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
			ProcessDefinition processDefinition=this.getProcessDefinitionFromRequest(processDefinitionQuery, processDefinitionId);
			Deployment deployment = repositoryService.createDeploymentQuery()
					.deploymentId(processDefinition.getDeploymentId())
					.singleResult();
			if (deployment == null) {
				throw new ActivitiObjectNotFoundException(
						"Could not find a deployment with id '"
								+ processDefinition.getDeploymentId() + "'.",
						Deployment.class);
			}
			List<String> resourceList = repositoryService.getDeploymentResourceNames(processDefinition.getDeploymentId());
			if (resourceList.contains(processDefinition.getResourceName())) {
				final InputStream resourceStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), processDefinition.getResourceName());
				 ResponseBuilder response = Response.ok((Object) resourceStream);
				 response.header("Content-Disposition","attachment; filename="+processDefinition.getResourceName());
				 response.header("Content-type","application/octet-stream");
				 return response.build();
			} else {
				// Resource not found in deployment
				throw new ActivitiObjectNotFoundException(
						"Could not find a resource with id '" + processDefinition.getResourceName()
								+ "' in deployment '" + processDefinition.getDeploymentId() + "'.",
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
	
	
	@ApiOperation(value = "获取流程定义模型", tags = "repositoryDefinition")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{processDefinitionId}/model")
	  public Object  getModelResource(@ApiParam(value = "流程定义id") @PathParam("processDefinitionId") String processDefinitionId) throws Exception{
		try{
			ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
		    ProcessDefinition processDefinition = this.getProcessDefinitionFromRequest(processDefinitionQuery, processDefinitionId);
		    BpmnModel bpmnModel=repositoryService.getBpmnModel(processDefinition.getId());
		    return bpmnModel;
		} catch (ActivitiIllegalArgumentException e) {
			throw new InvalidRestParamException(e.getMessage());
		} catch (ActivitiObjectNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		} catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		}

	}
	
	
	@ApiOperation(value = "获取流程定义布局图", tags = "repositoryDefinition")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{processDefinitionId}/diagramLayout")
	  public String  getDiagramLayout(@ApiParam(value = "流程定义id") @PathParam("processDefinitionId") String processDefinitionId) throws Exception{
		try{
			String responseJSONStr = wfManagerService.getDiagram(null,processDefinitionId);
			return responseJSONStr;
		} catch (ActivitiIllegalArgumentException e) {
			throw new InvalidRestParamException(e.getMessage());
		} catch (ActivitiObjectNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		} catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		}

	}
	
}
