package com.chinacreator.c2.flow.rest.runtime.web.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.diagram.ProcessDiagramGenerator;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.rest.service.api.engine.variable.RestVariable.RestVariableScope;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.flow.api.WfRuntimeService;
import com.chinacreator.c2.flow.detail.WfBusinessData;
import com.chinacreator.c2.flow.detail.WfOperator;
import com.chinacreator.c2.flow.detail.WfProcessInstance;
import com.chinacreator.c2.flow.detail.WfResult;
import com.chinacreator.c2.flow.rest.common.C2RestResponseFactory;
import com.chinacreator.c2.flow.rest.common.vo.WfJumpRequest;
import com.chinacreator.c2.flow.rest.common.vo.WfActionResult;
import com.chinacreator.c2.flow.rest.common.vo.WfPageListResponse;
import com.chinacreator.c2.flow.rest.common.vo.WfProcessInstanceActionRequest;
import com.chinacreator.c2.flow.rest.common.vo.WfProcessInstanceCreateRequest;
import com.chinacreator.c2.flow.rest.common.vo.WfProcessInstanceQueryRequest;
import com.chinacreator.c2.flow.rest.common.vo.WfProcessInstanceResponse;
import com.chinacreator.c2.flow.rest.common.vo.WfRestVariable;
import com.chinacreator.c2.flow.rest.runtime.BaseProcessInstanceResource;
import com.chinacreator.c2.flow.util.LoggerManager;
import com.chinacreator.c2.flow.util.LoggerManager.LoggerType;
import com.chinacreator.c2.sysmgr.Subject;
import com.chinacreator.c2.web.exception.InvalidRestParamException;
import com.chinacreator.c2.web.exception.ResourceNotFoundException;
import com.chinacreator.c2.web.exception.UnkownException;

/**
 * 流程实例rest接口
 * @author hushow
 */
@Service
@Path("v1/flow/runtime/instances")
@Api
public class WfProcessInstanceResource extends BaseProcessInstanceResource{
	
  @Autowired
  private WfRuntimeService wfRuntimeService;
  
  @Autowired
  private RuntimeService runtimeService;
  
  
  @Autowired
  private RepositoryService repositoryService;
  
  
  @Autowired
  ProcessEngineFactoryBean processEngine;
  
  	
  
  @ApiOperation(value = "工作流实例列表",tags = "runtime_instance")
  @ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),@ApiResponse(code = 404, message = "操作失败，请求资源未找到"),@ApiResponse(code = 500, message = "系统内部错误")  })
  @GET
  @Produces({ MediaType.APPLICATION_JSON })
  @Consumes({ MediaType.APPLICATION_JSON })
  public WfPageListResponse<WfProcessInstanceResponse> getProcessInstances(	@ApiParam(value = "流程实例id", required = false) @QueryParam("id") String id,
		  									@ApiParam(value = "流程定义key", required = false) @QueryParam("processDefinitionKey") String processDefinitionKey,
		  									@ApiParam(value = "流程定义id", required = false) @QueryParam("processDefinitionId") String processDefinitionId,
		  									@ApiParam(value = "业务记录主键", required = false) @QueryParam("businessKey") String businessKey,
		  									@ApiParam(value = "流程参与者(处理人、候选人、所有者)", required = false) @QueryParam("involvedUser") String involvedUser,
		  									@ApiParam(value = "是否挂起", required = false) @QueryParam("suspended") Boolean suspended,
		  									@ApiParam(value = "挂起的流程实例id", required = false) @QueryParam("superProcessInstanceId") String superProcessInstanceId,
		  									@ApiParam(value = "子流程实例id", required = false) @QueryParam("subProcessInstanceId") String subProcessInstanceId,
		  									@ApiParam(value = "是否排除子流程", required = false) @QueryParam("excludeSubprocesses") Boolean excludeSubprocesses,
		  									@ApiParam(value = "是否查询流程变量", required = false) @QueryParam("includeProcessVariables") Boolean includeProcessVariables,
		  									@ApiParam(value = "租户Id", required = false) @QueryParam("tenantId") String tenantId,@QueryParam("tenantIdLike") String tenantIdLike,
		  									@ApiParam(value = "是否禁用租户过滤", required = false) @QueryParam("withoutTenantId") boolean withoutTenantId,
		  									@ApiParam(value = "asc/desc排序", required = false) @QueryParam("order") String order,
		  									@ApiParam(value = "排序字段", required = false) @QueryParam("sort") Integer sort,
		  									@ApiParam(value = "请求开始行", required = false) @QueryParam("start") Integer start,
		  									@ApiParam(value = "请求记录大小", required = false) @QueryParam("size") Integer size)  throws Exception{
	
	    try{
	    	
	    	WfProcessInstanceQueryRequest queryRequest = new WfProcessInstanceQueryRequest();
		    
	 	   
		    if(StringUtils.hasText(id)) {
		      queryRequest.setProcessInstanceId(id);
		    }
		    
		    if(StringUtils.hasText(processDefinitionKey)) {
		      queryRequest.setProcessDefinitionKey(processDefinitionKey);
		    }
		    
		    if(StringUtils.hasText(processDefinitionId)) {
		      queryRequest.setProcessDefinitionId(processDefinitionId);
		    }
		    
		    if(StringUtils.hasText(businessKey)) {
		      queryRequest.setProcessBusinessKey(businessKey);
		    }
		    
		    if(StringUtils.hasText(involvedUser)) {
		      queryRequest.setInvolvedUser(involvedUser);
		    }
		  
		    if(null!=suspended) {
		      queryRequest.setSuspended(suspended);
		    }

			if(StringUtils.hasText(superProcessInstanceId)) {
			  queryRequest.setSuperProcessInstanceId(superProcessInstanceId);
			}
			
			
			if(StringUtils.hasText(subProcessInstanceId)) {
			  queryRequest.setSubProcessInstanceId(subProcessInstanceId);
			}
			
			if(null==excludeSubprocesses) {
			  queryRequest.setExcludeSubprocesses(excludeSubprocesses);
			}
			
			if(null==includeProcessVariables) {
			  queryRequest.setIncludeProcessVariables(includeProcessVariables);
			}
			
			if(StringUtils.hasText(tenantId)) {
			  queryRequest.setTenantId(tenantId);
			}
			
			if(StringUtils.hasText(tenantIdLike)) {
			  queryRequest.setTenantIdLike(tenantIdLike);
			}
			
			if(withoutTenantId) {
			  queryRequest.setWithoutTenantId(Boolean.TRUE);
			}
			
			if(StringUtils.hasText(order)) {
			    queryRequest.setOrder(order);
			}
			
			if(!StringUtils.isEmpty(start)) {
			    queryRequest.setStart(start);
			}
			
			if(!StringUtils.isEmpty(size)) {
			    queryRequest.setSize(size);
			}
			
			return getQueryResponse(runtimeService.createProcessInstanceQuery(),queryRequest);
	    }catch(Exception e){
	    	throw new UnkownException(e.getMessage(),e);
	    }
  }
  
  
  @ApiOperation(value = "启动工作流实例",tags = "runtime_instance")
  @ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),
		  				  @ApiResponse(code = 404, message = "操作失败，请求资源未找到"),
		  				  @ApiResponse(code = 401, message = "操作失败，未经认证请求"),
		  				  @ApiResponse(code = 500, message = "系统内部错误")  })
  @POST
  @Produces({MediaType.APPLICATION_JSON})
  @Consumes({MediaType.APPLICATION_JSON})
  public WfProcessInstanceResponse createProcessInstance(@ApiParam(value = "创建流程实例参数", required = true) WfProcessInstanceCreateRequest requestVo) throws Exception{
	  
	    WfOperator wfOperator=new WfOperator();
	    WfResult wfResult=null;
	    
	  	try{
	  		
		    WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		    Subject subject=context.getUser();
		    //暂时开放未登陆可以启动工作流，流程不一定都是用户环节
//		    if(null==subject||!StringUtils.hasText(subject.getId())){
//		    	throw new FlowUnauthorizedException("Not logged in without permission to operate.");
//		    }
		    
		    if(null!=subject){
			    wfOperator.setUserId(subject.getId());
			    wfOperator.setUserName(subject.getName());
			    wfOperator.setUserCName(subject.getName());
		    }
		   
	  		if(null==requestVo){
		  		throw new ActivitiIllegalArgumentException("Either processDefinitionId, processDefinitionKey or message is required.");
		  	}
	  		
	  		
//	  		if(null==requestVo.getBusinessData()||null==requestVo.getBusinessData().getModuleId()){
//	  			throw new ActivitiIllegalArgumentException("businessData.moduleId is required.");
//	  		}
	  		
		    if(requestVo.getProcessDefinitionId() == null && requestVo.getProcessDefinitionKey() == null) {
		      throw new ActivitiIllegalArgumentException("Either processDefinitionId, processDefinitionKey is required.");
		    }
		    
		    int paramsSet = ((requestVo.getProcessDefinitionId() != null) ? 1 : 0)+ ((requestVo.getProcessDefinitionKey() != null) ? 1 : 0);
		    
		    if(paramsSet > 1) {
		      throw new ActivitiIllegalArgumentException("Only one of processDefinitionId, processDefinitionKey or message should be set.");
		    }
		    
		    if(StringUtils.hasText(requestVo.getTenantId())) {
		    	// Tenant-id can only be used with either key
		    	if(requestVo.getProcessDefinitionId() != null) {
		    		throw new ActivitiIllegalArgumentException("TenantId can only be used with either processDefinitionKey.");
		    	}
		    }
		    
		    C2RestResponseFactory factory = new C2RestResponseFactory();
		    
		    Map<String, Object> startVariables = null;
		    if(requestVo.getVariables() != null) {
		      startVariables = new HashMap<String, Object>();
		      for(WfRestVariable variable : requestVo.getVariables()) {
		        if(variable.getName() == null) {
		          throw new ActivitiIllegalArgumentException("Variable name is required.");
		        }
		        startVariables.put(variable.getName(), factory.getVariableValue(variable));
		      }
		    }
		    
		    // Actually start the instance based on key or id
		    try {
		    
		      String bussinessKey=null;
		      if(null!=requestVo.getBusinessData()){
		    	  
			      WfBusinessData wfBusinessData=new WfBusinessData();
			      wfBusinessData.setBusinessKey(requestVo.getBusinessData().getBusinessKey());
			      wfBusinessData.setAppId(requestVo.getBusinessData().getAppId());
			      wfBusinessData.setModuleId(requestVo.getBusinessData().getModuleId());
			      wfBusinessData.setModuleName(requestVo.getBusinessData().getModuleName());
			      wfBusinessData.setBusinessExtend(requestVo.getBusinessData().getBusinessExtend());
			      wfBusinessData.setRemark1(requestVo.getBusinessData().getRemark1());
			      wfBusinessData.setRemark2(requestVo.getBusinessData().getRemark2());
			      wfBusinessData.setRemark3(requestVo.getBusinessData().getRemark3());
			      wfBusinessData.setRemark4(requestVo.getBusinessData().getRemark4());
			      wfBusinessData.setRemark5(requestVo.getBusinessData().getRemark5());
			      wfBusinessData.setRemark6(requestVo.getBusinessData().getRemark6());
			      wfBusinessData.setRemark7(requestVo.getBusinessData().getRemark7());
			      wfBusinessData.setRemark8(requestVo.getBusinessData().getRemark8());
			      wfBusinessData.setRemark9(requestVo.getBusinessData().getRemark9());
			      wfBusinessData.setRemark10(requestVo.getBusinessData().getRemark10());
			      wfOperator.setBusinessData(wfBusinessData);
		      }
		     
		      if(requestVo.getProcessDefinitionId() != null) {
		    	  wfResult = wfRuntimeService.startProcessInstanceById(wfOperator,requestVo.getProcessDefinitionId(),bussinessKey, startVariables);
		      } else{
		      	if(StringUtils.hasText(requestVo.getTenantId())) {
		      		wfOperator.setTenantId(requestVo.getTenantId());
		      	}
		      	wfResult = wfRuntimeService.startProcessInstanceByKey(wfOperator,bussinessKey,requestVo.getProcessDefinitionKey(),startVariables);
		      }
		      
		      //再查询一次流程实例详细，以后启动接口要直接返回实例详情信息
		      WfProcessInstance wfProcessInstance=wfRuntimeService.getProcessInstanceById(wfResult.getProcessInstanceId());
		      
		      WfProcessInstanceResponse result = new WfProcessInstanceResponse();
		      result.setActivityId(wfProcessInstance.getActivityId());
		      result.setBusinessKey(wfProcessInstance.getBusinessKey());
		      result.setId(wfProcessInstance.getProcessInstanceId());
		      result.setProcessDefinitionId(wfProcessInstance.getProcessDefinitionId());
		      //result.setProcessDefinitionUrl(securedResource.createFullResourceUrl(RestUrls.URL_PROCESS_DEFINITION, processInstance.getProcessDefinitionId()));
		      result.setSuspended(wfProcessInstance.isSuspended());
		      //result.setUrl(securedResource.createFullResourceUrl(RestUrls.URL_PROCESS_INSTANCE, processInstance.getId()));
		      result.setTenantId(requestVo.getTenantId());
//		      if (processInstance.getProcessVariables() != null) {
//		        Map<String, Object> variableMap = processInstance.getProcessVariables();
//		        for (String name : variableMap.keySet()) {
//		          result.addVariable(createRestVariable(securedResource, name, variableMap.get(name), 
//		              RestVariableScope.LOCAL, processInstance.getId(), VARIABLE_PROCESS, false));
//		        }
//		      }
		      
				LoggerManager
				.log(getClass(),
						LoggerType.DEBUG,
						wfOperator,
						null,
						"启动流程成功： processDefinitionId={},processDefinitionKey={}, businessId={}, processInstanceId={}, nextTaskIds={}, variables={}",
						requestVo.getProcessDefinitionId(),requestVo.getProcessDefinitionKey(), bussinessKey,
						wfResult.getProcessInstanceId(),"", startVariables);
				
				//return new WfActionResult(wfResult);
		        return result;
		    } catch(ActivitiObjectNotFoundException aonfe) {
		    	throw new ActivitiIllegalArgumentException(aonfe.getMessage(), aonfe);
		    }
		    
	  	}catch(ActivitiIllegalArgumentException e){
			LoggerManager
			.log(getClass(),
					LoggerType.ERROR,
					wfOperator,
					e,
					"启动流程失败： processDefinitionId={},processDefinitionKey={}, variables={}",
					requestVo.getProcessDefinitionId(), requestVo.getProcessDefinitionKey(),requestVo.getVariables());
	  		throw new InvalidRestParamException(e.getMessage());
	  	} catch (ActivitiObjectNotFoundException e) {
			LoggerManager
			.log(getClass(),
					LoggerType.ERROR,
					wfOperator,
					e,
					"启动流程失败： processDefinitionId={},processDefinitionKey={}, variables={}",
					requestVo.getProcessDefinitionId(), requestVo.getProcessDefinitionKey(),requestVo.getVariables());
			throw new ResourceNotFoundException(e.getMessage());
		}catch(Exception e){
			LoggerManager
			.log(getClass(),
					LoggerType.ERROR,
					wfOperator,
					e,
					"启动流程失败： processDefinitionId={},processDefinitionKey={}, businessId={}, variables={}",
					requestVo.getProcessDefinitionId(), requestVo.getProcessDefinitionKey(),requestVo.getVariables());
	  		throw new UnkownException(e.getMessage(),e);
	  	}
  }
  
  
  @ApiOperation(value = "获取工作流实例信息",tags = "runtime_instance")
  @ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),@ApiResponse(code = 404, message = "操作失败，请求资源未找到"),@ApiResponse(code = 500, message = "系统内部错误")  })
  @GET
  @Produces({MediaType.APPLICATION_JSON})
  @Consumes({MediaType.APPLICATION_JSON})
  @Path("/{processInstanceId}")
  public WfProcessInstanceResponse getProcessInstance(@ApiParam(value = "流程实例id", required = true) @PathParam("processInstanceId") String processInstanceId)  throws Exception{
	
	   try{
		   ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		   if (processInstance == null) {
			   throw new ActivitiObjectNotFoundException("Could not find a process instance with id '" + processInstanceId + "'.",ProcessInstance.class);
		   }
		   
		   WfProcessInstanceResponse result = new WfProcessInstanceResponse();
		   result.setActivityId(processInstance.getActivityId());
		   result.setBusinessKey(processInstance.getBusinessKey());
		   result.setId(processInstance.getId());
		   result.setProcessDefinitionId(processInstance.getProcessDefinitionId());
		   result.setSuspended(processInstance.isSuspended());
		   result.setTenantId(processInstance.getTenantId());
		   if (processInstance.getProcessVariables() != null) {
			 C2RestResponseFactory c2RestResponseFactory=new C2RestResponseFactory();
		     Map<String, Object> variableMap = processInstance.getProcessVariables();
		     for (String name : variableMap.keySet()) {
		       result.addVariable(c2RestResponseFactory.createRestVariable( name, variableMap.get(name), 
		           RestVariableScope.LOCAL, processInstance.getId(), C2RestResponseFactory.VARIABLE_PROCESS, false));
		     }
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
  
  
  @ApiOperation(value = "删除工作流实例信息",tags = "runtime_instance")
  @ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),@ApiResponse(code = 404, message = "操作失败，请求资源未找到"),@ApiResponse(code = 500, message = "系统内部错误")  })
  @DELETE
  @Produces({MediaType.APPLICATION_JSON})
  @Consumes({MediaType.APPLICATION_JSON})
  @Path("/{processInstanceId}")
  public Object deleteProcessInstance(@ApiParam(value = "流程实例id", required = true) @PathParam("processInstanceId") String processInstanceId,@ApiParam(value = "删除原因", required = false) @QueryParam("deleteReason") String deleteReason)  throws Exception{

	  try{
		  
		  WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		  Subject subject=context.getUser();
		  WfOperator wfOperator=new WfOperator();
//		  if(null==subject||!StringUtils.hasText(subject.getId())){
//			  return Response.status(Response.Status.UNAUTHORIZED).entity("Not logged in without permission to operate.").build();
//		  }
		  if(null!=subject){
			  wfOperator.setUserId(subject.getId());
			  wfOperator.setUserName(subject.getName());
			  wfOperator.setUserCName(subject.getName());
		  }
		  
		  WfProcessInstance wfProcessInstance=wfRuntimeService.getProcessInstanceById(processInstanceId);
		  if(null==wfProcessInstance) throw new ResourceNotFoundException("Could not find a process instance with id '" + processInstanceId + "'.");
	      wfRuntimeService.deleteProcessInstancesById(wfOperator,deleteReason,processInstanceId);
	      return null;
	  }catch (ActivitiIllegalArgumentException e) {
			throw new InvalidRestParamException(e.getMessage());
		} catch (ActivitiObjectNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		} catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		}
  }
  
  
  @ApiOperation(value = "激活或挂起工作流实例",tags = "runtime_instance",response=WfProcessInstanceResponse.class)
  @ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),@ApiResponse(code = 404, message = "操作失败，请求资源未找到"),@ApiResponse(code = 500, message = "系统内部错误")  })
  @PUT
  @Produces({MediaType.APPLICATION_JSON})
  @Consumes({MediaType.APPLICATION_JSON})
  @Path("/{processInstanceId}")
  public Object performProcessInstanceAction(@ApiParam(value = "流程实例id", required = true) @PathParam("processInstanceId") String processInstanceId,@ApiParam(value = "suspend或activate实例", required = true) WfProcessInstanceActionRequest actionRequest) throws Exception{
    
	  try{
		  
		  WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		  Subject subject=context.getUser();
		  WfOperator wfOperator=new WfOperator();
//		  if(null==subject||!StringUtils.hasText(subject.getId())){
//			  return Response.status(Response.Status.UNAUTHORIZED).entity("Not logged in without permission to operate.").build();
//		  }
		  if(null!=subject){
			  wfOperator.setUserId(subject.getId());
			  wfOperator.setUserName(subject.getName());
			  wfOperator.setUserCName(subject.getName());
		  }
		  
		  if(null==actionRequest){
		  		throw new ActivitiIllegalArgumentException("action is required.");
		  }
		  
		  ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		  if(null==processInstance) throw new ActivitiObjectNotFoundException("Could not find a process instance with id '" + processInstanceId + "'.",ProcessInstance.class);
		  
		  
		  if(WfProcessInstanceActionRequest.InstanceAction.ACTIVATE.equals(actionRequest.getAction())) {
			  wfRuntimeService.activateProcessInstance(wfOperator, processInstanceId);
		  } else if(WfProcessInstanceActionRequest.InstanceAction.SUSPEND.equals(actionRequest.getAction())) {
			  wfRuntimeService.suspendProcessInstance(wfOperator, processInstanceId);
		  }else{
			  throw new ActivitiIllegalArgumentException("Invalid action: '" + actionRequest.getAction() + "'.");
		  }
		  
		  processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();

		  WfProcessInstanceResponse result = new WfProcessInstanceResponse();
		  result.setActivityId(processInstance.getActivityId());
		  result.setBusinessKey(processInstance.getBusinessKey());
		  result.setId(processInstance.getId());
		  result.setProcessDefinitionId(processInstance.getProcessDefinitionId());
		  result.setSuspended(processInstance.isSuspended());
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
  
  
  @ApiOperation(value = "获取流程实例图",tags = "runtime_instance",notes="获取流程实例图片，红色框标记为当前环节",produces="image/png")
  @ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),@ApiResponse(code = 404, message = "操作失败，请求资源未找到"),@ApiResponse(code = 500, message = "系统内部错误")  })
  @GET
  @Produces({"image/png"})
  @Path("/{processInstanceId}/diagram")
  public Response  getProcessInstanceDiagram(@ApiParam(value = "流程实例id", required = true) @PathParam("processInstanceId") String processInstanceId) throws Exception{
	 
	  try{
		  
		  ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		  if(null==processInstance) throw new ResourceNotFoundException("Could not find a process instance with id '" + processInstanceId + "'.");
		  
		  ProcessDefinitionEntity pde = (ProcessDefinitionEntity) ((RepositoryServiceImpl) 
		    		repositoryService).getDeployedProcessDefinition(processInstance.getProcessDefinitionId());
		  
		  if (pde != null && pde.isGraphicalNotationDefined()) {
			  BpmnModel bpmnModel = repositoryService.getBpmnModel(pde.getId());
			  
			  Context.setProcessEngineConfiguration(processEngine.getProcessEngineConfiguration());
			  
			  InputStream resource = ProcessDiagramGenerator.generateDiagram(bpmnModel, "png", runtimeService.getActiveActivityIds(processInstance.getId()));
			  
			  ResponseBuilder response = Response.ok((Object) resource);
			  //response.header("Content-Disposition","attachment; filename="+processInstanceId+".png");
			  response.header("Content-type","image/png");
			  return response.build();
			  //InputRepresentation output = new InputRepresentation(resource, MediaType.IMAGE_PNG);
			  //return resource;
			  
		  } else {
			  throw new ActivitiIllegalArgumentException("Process instance with id '" + processInstance.getId() + "' has no graphical notation defined.");
		  }
		    
	  }catch(ActivitiIllegalArgumentException e){
		  return Response.status(Response.Status.BAD_REQUEST).build();
		  //throw new InvalidRestParamException(e1.getMessage());
	  }catch(ActivitiObjectNotFoundException e){
		  return Response.status(Response.Status.NOT_FOUND).build();
		  //throw e2;
	  }catch(Exception e){
		  //throw new UnkownException(e.getMessage(),e);
		  return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	  }
  }
  
	@ApiOperation(value = "自由流", tags = "runtime_instance")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),@ApiResponse(code = 404, message = "操作失败，请求资源未找到"),@ApiResponse(code = 500, message = "系统内部错误")  })
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/{processInstanceId}/goAnyWhere")
	public WfActionResult goAnyWhere(
			@ApiParam(value = "流程实例id", required = true) @PathParam("processInstanceId") String processInstanceId,
			@ApiParam(value = "自由流参数", required = true) WfJumpRequest jumpRequest)
			throws Exception {

		try {
			
			if(!StringUtils.hasText(jumpRequest.getDestTaskDefinitionKey())){
				throw new ActivitiIllegalArgumentException("destTaskDefinitionKey is required.");
			}
			
			WebOperationContext context = (WebOperationContext) OperationContextHolder.getContext();
			Subject subject = context.getUser();
			WfOperator wfOperator = new WfOperator();
//			if (null == subject || !StringUtils.hasText(subject.getId())) {
//				return Response.status(Response.Status.UNAUTHORIZED)
//						.entity("Not logged in without permission to operate.")
//						.build();
//			}
			if(null!=subject){
				wfOperator.setUserId(subject.getId());
				wfOperator.setUserName(subject.getName());
				wfOperator.setUserCName(subject.getName());
			}
			
		    C2RestResponseFactory factory = new C2RestResponseFactory();
		    
		    Map<String, Object> startVariables = null;
		    if(jumpRequest.getVariables() != null) {
		      startVariables = new HashMap<String, Object>();
		      for(WfRestVariable variable : jumpRequest.getVariables()) {
		        if(variable.getName() == null) {
		          throw new ActivitiIllegalArgumentException("Variable name is required.");
		        }
		        startVariables.put(variable.getName(), factory.getVariableValue(variable));
		      }
		    }
		    
		    WfResult wfResult= wfRuntimeService.goAnyWhere(wfOperator, processInstanceId, jumpRequest.getDestTaskDefinitionKey(), startVariables);
		    return new WfActionResult(wfResult);
			
		} catch (ActivitiIllegalArgumentException e1) {
			throw new InvalidRestParamException(e1.getMessage());
		} catch (ActivitiObjectNotFoundException e2) {
			throw new ResourceNotFoundException(e2.getMessage());
		} catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		}
	}
 
  protected Boolean queryParameterAsBoolean(String name,String stringValue) {
	    if(stringValue != null) {
	      if(Boolean.TRUE.toString().equals(stringValue.toLowerCase())) {
	        return Boolean.TRUE;
	      } else if(Boolean.FALSE.toString().equals(stringValue.toLowerCase())) {
	        return Boolean.FALSE;
	      } else {
	        throw new ActivitiIllegalArgumentException("The given value for query-parameter '" + name + "' should be one fo 'true' or 'false', instead of: " + stringValue);
	      }
	    }
	    return null;
 }
}
