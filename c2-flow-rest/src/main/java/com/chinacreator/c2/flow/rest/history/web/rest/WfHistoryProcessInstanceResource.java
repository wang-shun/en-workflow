package com.chinacreator.c2.flow.rest.history.web.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricIdentityLink;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.diagram.ProcessDiagramGenerator;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.task.Comment;
import org.activiti.rest.service.api.engine.variable.RestVariable.RestVariableScope;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.chinacreator.c2.flow.rest.common.C2RestResponseFactory;
import com.chinacreator.c2.flow.rest.common.vo.WfCommentResponse;
import com.chinacreator.c2.flow.rest.common.vo.WfHistoricIdentityLinkResponse;
import com.chinacreator.c2.flow.rest.common.vo.WfHistoricProcessInstanceQueryRequest;
import com.chinacreator.c2.flow.rest.common.vo.WfHistoricProcessInstanceResponse;
import com.chinacreator.c2.flow.rest.common.vo.WfPageListResponse;
import com.chinacreator.c2.flow.rest.history.HistoricProcessInstanceBaseResource;
import com.chinacreator.c2.web.exception.InvalidRestParamException;
import com.chinacreator.c2.web.exception.ResourceNotFoundException;
import com.chinacreator.c2.web.exception.UnkownException;

/**
 * 历史流程实例rest接口
 * @author hushow
 */
@Service
@Path("v1/flow/history/instances")
@Api
public class WfHistoryProcessInstanceResource extends HistoricProcessInstanceBaseResource{
	
	@Autowired
	ProcessEngineFactoryBean processEngine;
	
	@Autowired
	HistoryService historyService;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	RepositoryService repositoryService;
	
	@Autowired
	RuntimeService runtimeService;
  
  	@ApiOperation(value = "获取历史工作流实例列表",tags = "history_instance")
  	@ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),@ApiResponse(code = 404, message = "操作失败，请求资源未找到"),@ApiResponse(code = 500, message = "系统内部错误")  })
  	@GET
	public WfPageListResponse<WfHistoricProcessInstanceResponse> getHistoricProcessInstances(@ApiParam(value = "流程实例id", required = false) @QueryParam("processInstanceId") String processInstanceId,
													@ApiParam(value = "流程定义key", required = false) @QueryParam("processDefinitionKey")  String processDefinitionKey,
													@ApiParam(value = "流程定义id", required = false) @QueryParam("processDefinitionId") String processDefinitionId,
													@ApiParam(value = "业务主键", required = false) @QueryParam("businessKey") String businessKey,
													@ApiParam(value = "流程参与者(处理人、候选人、所有者)", required = false) @QueryParam("involvedUser") String involvedUser,
													@ApiParam(value = "是否完成", required = false) @QueryParam("finished") Boolean finished,
													@ApiParam(value = "父流程实例id", required = false) @QueryParam("superProcessInstanceId") String superProcessInstanceId,
													@ApiParam(value = "排除拥有子流的", required = false) @QueryParam("excludeSubprocesses") Boolean excludeSubprocesses,
													@ApiParam(value = "完成时间下区间", required = false) @QueryParam("finishedBefore") Date finishedBefore,
													@ApiParam(value = "完成时间上区间", required = false) @QueryParam("finishedAfter") Date finishedAfter,
													@ApiParam(value = "开始时间下区间", required = false) @QueryParam("startedBefore") Date startedBefore,
													@ApiParam(value = "开始时间上区间", required = false) @QueryParam("startedAfter") Date startedAfter,
													@ApiParam(value = "流程起动者", required = false) @QueryParam("startedBy") String startedBy,
													@ApiParam(value = "是否包含流程变量", required = false) @QueryParam("includeProcessVariables") Boolean includeProcessVariables,
													@ApiParam(value = "租户", required = false) @QueryParam("tenantId") String tenantId,
													@ApiParam(value = "租户模糊匹配，例:%abc%", required = false) @QueryParam("tenantIdLike") String tenantIdLike,
													@ApiParam(value = "是否启用租户过滤", required = false) @QueryParam("withoutTenantId") Boolean withoutTenantId) throws Exception{
  		
		
  		try{
  			
  			// Populate query based on request
  			WfHistoricProcessInstanceQueryRequest queryRequest = new WfHistoricProcessInstanceQueryRequest();

  			if (StringUtils.hasText(processInstanceId)) {
  				queryRequest.setProcessInstanceId(processInstanceId);
  			}

  			if (StringUtils.hasText(processDefinitionKey)) {
  				queryRequest.setProcessDefinitionKey(processDefinitionKey);
  			}

  			if (StringUtils.hasText(processDefinitionId)) {
  				queryRequest.setProcessDefinitionId(processDefinitionId);
  			}

  			if (StringUtils.hasText(businessKey)) {
  				queryRequest.setProcessBusinessKey(businessKey);
  			}

  			if (StringUtils.hasText(involvedUser)) {
  				queryRequest.setInvolvedUser(involvedUser);
  			}

  			if (null!=finished) {
  				queryRequest.setFinished(finished);
  			}
  			
  			if (StringUtils.hasText(superProcessInstanceId)) {
  				queryRequest.setSuperProcessInstanceId(superProcessInstanceId);
  			}

  			if (null!=excludeSubprocesses) {
  				queryRequest.setExcludeSubprocesses(excludeSubprocesses);
  			}

  			if (null!=finishedAfter) {
  				queryRequest.setFinishedAfter(finishedAfter);
  			}

  			if (null != finishedBefore) {
  				queryRequest.setFinishedBefore(finishedBefore);
  			}

  			if (null!=startedAfter) {
  				queryRequest.setStartedAfter(startedAfter);
  			}

  			if (null!=startedBefore) {
  				queryRequest.setStartedBefore(startedBefore);
  			}

  			if (StringUtils.hasText(startedBy)) {
  				queryRequest.setStartedBy(startedBy);
  			}

  			if (null!=includeProcessVariables) {
  				queryRequest.setIncludeProcessVariables(includeProcessVariables);
  			}

  			if (StringUtils.hasText(tenantId)) {
  				queryRequest.setTenantId(tenantId);
  			}

  			if (StringUtils.hasText(tenantIdLike)) {
  				queryRequest.setTenantIdLike(tenantIdLike);
  			}

  			if (null!=withoutTenantId) {
  				queryRequest.setWithoutTenantId(withoutTenantId);
  			}

  			return getQueryResponse(historyService.createHistoricProcessInstanceQuery(),queryRequest);
  		}catch (ActivitiIllegalArgumentException e) {
			throw new InvalidRestParamException(e.getMessage());
		} catch (ActivitiObjectNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		} catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		}
		
	}
  
  	
	@ApiOperation(value = "获取历史流程实例信息", tags = "history_instance")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),@ApiResponse(code = 404, message = "操作失败，请求资源未找到"),@ApiResponse(code = 500, message = "系统内部错误")  })
	@GET
	@Path("/{processInstanceId}")
	public WfHistoricProcessInstanceResponse getHistoryProcessInstance(@ApiParam(value = "流程实例id", required = true)  @PathParam("processInstanceId") String processInstanceId)  throws Exception{
		
		
	    try{
	    	if (processInstanceId == null) {
	  	      throw new ActivitiIllegalArgumentException("The processInstanceId cannot be null");
	  	    }
	  	    
	  	    HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
	  	    if (processInstance == null) {
	  	      throw new ActivitiObjectNotFoundException("Could not find a process instance with id '" + processInstanceId + "'.", HistoricProcessInstance.class);
	  	    }
	  	    
	  	    
	  		WfHistoricProcessInstanceResponse result = new WfHistoricProcessInstanceResponse();
	  		result.setBusinessKey(processInstance.getBusinessKey());
	  		result.setDeleteReason(processInstance.getDeleteReason());
	  		result.setDurationInMillis(processInstance.getDurationInMillis());
	  		result.setEndActivityId(processInstance.getEndActivityId());
	  		result.setEndTime(processInstance.getEndTime());
	  		result.setId(processInstance.getId());
	  		result.setProcessDefinitionId(processInstance.getProcessDefinitionId());
	  		result.setStartActivityId(processInstance.getStartActivityId());
	  		result.setStartTime(processInstance.getStartTime());
	  		result.setStartUserId(processInstance.getStartUserId());
	  		result.setSuperProcessInstanceId(processInstance.getSuperProcessInstanceId());
	  		
	  		
	  		 C2RestResponseFactory responseFactory =new C2RestResponseFactory();
	  		 
	  		if (processInstance.getProcessVariables() != null) {
	  			Map<String, Object> variableMap = processInstance
	  					.getProcessVariables();
	  			for (String name : variableMap.keySet()) {
	  				result.addVariable(responseFactory.createRestVariable(name,
	  						variableMap.get(name), RestVariableScope.LOCAL,
	  						processInstance.getId(),C2RestResponseFactory.VARIABLE_HISTORY_PROCESS,
	  						false));
	  			}
	  		}
	  		result.setTenantId(processInstance.getTenantId());
	  		return result;
	  		
	    } catch (ActivitiIllegalArgumentException e) {
			throw new InvalidRestParamException(e.getMessage());
		} catch (ActivitiObjectNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		} catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		}
		

	}
	
	
	@ApiOperation(value = "获取历史流程实例候选人和组信息", tags = "history_instance")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),@ApiResponse(code = 404, message = "操作失败，请求资源未找到"),@ApiResponse(code = 500, message = "系统内部错误")  })
	@GET
	@Path("/{processInstanceId}/identitylinks")
	public List<WfHistoricIdentityLinkResponse> getHistoricInstanceIdentityLinks(
			@ApiParam(value = "流程实例id", required = true) @PathParam("processInstanceId") String processInstanceId)
			throws Exception {

		try {

			if (processInstanceId == null) {
				throw new ActivitiIllegalArgumentException(
						"The processInstanceId cannot be null");
			}

			List<HistoricIdentityLink> identityLinks = historyService.getHistoricIdentityLinksForProcessInstance(processInstanceId);

			List<WfHistoricIdentityLinkResponse> responseList = new ArrayList<WfHistoricIdentityLinkResponse>();
			if (identityLinks != null) {
				for (HistoricIdentityLink identityLink : identityLinks) {
					WfHistoricIdentityLinkResponse result = new WfHistoricIdentityLinkResponse();
					result.setType(identityLink.getType());
					result.setUserId(identityLink.getUserId());
					result.setGroupId(identityLink.getGroupId());
					result.setTaskId(identityLink.getTaskId());
					result.setProcessInstanceId(identityLink.getProcessInstanceId());
					responseList.add(result);
				}
			}

			return responseList;
		} catch (ActivitiIllegalArgumentException e) {
			throw new InvalidRestParamException(e.getMessage());
		} catch (ActivitiObjectNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		} catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		}

	}
	
	
	@ApiOperation(value = "获取历史流程实例评论意见信息", tags = "history_instance")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),@ApiResponse(code = 404, message = "操作失败，请求资源未找到"),@ApiResponse(code = 500, message = "系统内部错误")  })
	@GET
	@Path("/{processInstanceId}/comments")
	public List<WfCommentResponse> getHistoryInstanceComments(
			@ApiParam(value = "流程实例id", required = true) @PathParam("processInstanceId") String processInstanceId)
			throws Exception {

		try {

			if (processInstanceId == null) {
				throw new ActivitiIllegalArgumentException("The processInstanceId cannot be null");
			}

			HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery()
					.processInstanceId(processInstanceId).singleResult();
			if (processInstance == null) {
				throw new ActivitiObjectNotFoundException(
						"Could not find a process instance with id '"
								+ processInstanceId + "'.",
						HistoricProcessInstance.class);
			}

			List<WfCommentResponse> result = new ArrayList<WfCommentResponse>();
			C2RestResponseFactory responseFactory = new C2RestResponseFactory();

			for (Comment comment : taskService.getProcessInstanceComments(processInstance.getId())) {
				result.add(responseFactory.createRestComment(comment));
			}

			return result;

		} catch (ActivitiIllegalArgumentException e) {
			throw new InvalidRestParamException(e.getMessage());
		} catch (ActivitiObjectNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		} catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		}

	}
	

	  @ApiOperation(value = "获取流程实例图",tags = "history_instance")
	  @ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),@ApiResponse(code = 404, message = "操作失败，请求资源未找到"),@ApiResponse(code = 500, message = "系统内部错误")  })
	  @GET
	  @Produces({"image/png"})
	  @Path("/{processInstanceId}/diagram")
	  public Response  getHistoryProcessInstanceDiagram(@ApiParam(value = "流程实例id", required = true) @PathParam("processInstanceId") String processInstanceId) throws Exception{
		 
		  try{
			  
			  HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
			  
			  if(null==processInstance) throw new ResourceNotFoundException("Could not find a process instance with id '" + processInstanceId + "'.");
			  
			  ProcessDefinitionEntity pde = (ProcessDefinitionEntity) ((RepositoryServiceImpl) 
			    		repositoryService).getDeployedProcessDefinition(processInstance.getProcessDefinitionId());
			  
			  if (pde != null && pde.isGraphicalNotationDefined()) {
				  BpmnModel bpmnModel = repositoryService.getBpmnModel(pde.getId());
				  
				  Context.setProcessEngineConfiguration(processEngine.getProcessEngineConfiguration());
				  
				  List<String> activitys=new ArrayList<String>();
				  if(null!=processInstance.getEndTime()){
					  activitys.add(processInstance.getEndActivityId());
				  }else{
					  activitys=runtimeService.getActiveActivityIds(processInstance.getId());
				  }
				  
				  InputStream resource = ProcessDiagramGenerator.generateDiagram(bpmnModel, "png",activitys);
				  
				  ResponseBuilder response = Response.ok((Object) resource);
				  //response.header("Content-Disposition","attachment; filename="+processInstanceId+".png");
				  response.header("Content-type","image/png");
				  return response.build();
				  //InputRepresentation output = new InputRepresentation(resource, MediaType.IMAGE_PNG);
				  //return resource;
				  
			  } else {
				  throw new ActivitiIllegalArgumentException("Process instance with id '" + processInstance.getId() + "' has no graphical notation defined.");
			  }
			    
		  }catch(ActivitiIllegalArgumentException e1){
			  return Response.status(Response.Status.BAD_REQUEST).build();
			  //throw new InvalidRestParamException(e1.getMessage());
		  }catch(ResourceNotFoundException e2){
			  return Response.status(Response.Status.NOT_FOUND).build();
			  //throw e2;
		  }catch(Exception e){
			  //throw new UnkownException(e.getMessage(),e);
			  return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		  }
	  }

}
