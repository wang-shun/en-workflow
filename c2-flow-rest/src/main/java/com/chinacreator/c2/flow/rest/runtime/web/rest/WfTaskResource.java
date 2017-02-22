package com.chinacreator.c2.flow.rest.runtime.web.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.javax.el.PropertyNotFoundException;
import org.activiti.engine.impl.persistence.entity.VariableInstanceEntity;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.rest.service.api.RestResponseFactory;
import org.activiti.rest.service.api.engine.variable.RestVariable;
import org.activiti.rest.service.api.engine.variable.RestVariable.RestVariableScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.chinacreator.c2.flow.api.WfTaskService;
import com.chinacreator.c2.flow.detail.WfOperator;
import com.chinacreator.c2.flow.detail.WfResult;
import com.chinacreator.c2.flow.detail.WfTaskAction;
import com.chinacreator.c2.flow.rest.common.C2RestResponseFactory;
import com.chinacreator.c2.flow.rest.common.FowRestHelper;
import com.chinacreator.c2.flow.rest.common.exception.FlowResourceAlreadyExistsException;
import com.chinacreator.c2.flow.rest.common.exception.FlowUnauthorizedException;
import com.chinacreator.c2.flow.rest.common.vo.WfActionResult;
import com.chinacreator.c2.flow.rest.common.vo.WfCommentRequest;
import com.chinacreator.c2.flow.rest.common.vo.WfCommentResponse;
import com.chinacreator.c2.flow.rest.common.vo.WfPageListResponse;
import com.chinacreator.c2.flow.rest.common.vo.WfRestVariable;
import com.chinacreator.c2.flow.rest.common.vo.WfTaskActionRequest;
import com.chinacreator.c2.flow.rest.common.vo.WfTaskQueryRequest;
import com.chinacreator.c2.flow.rest.common.vo.WfTaskRequest;
import com.chinacreator.c2.flow.rest.common.vo.WfTaskResponse;
import com.chinacreator.c2.flow.rest.runtime.TaskBaseResource;
import com.chinacreator.c2.web.exception.InvalidRestParamException;
import com.chinacreator.c2.web.exception.ResourceNotFoundException;
import com.chinacreator.c2.web.exception.UnkownException;

/**
 * 流程实例rest接口
 * 
 * @author hushow
 */
@Controller
@Path("v1/flow/runtime/tasks")
@Api
@SwaggerDefinition(tags={@Tag(name = "runtimeTask",description="运行时任务相关操作")})
public class WfTaskResource extends TaskBaseResource {
	
	@Autowired
	private WfTaskService wfTaskService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	TaskService taskService;
	
	@Autowired
	HistoryService historyService;
	
	@Autowired
	IdentityService identityService;

	@ApiOperation(value = "待办列表", tags = "runtimeTask")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),@ApiResponse(code = 404, message = "操作失败，请求资源未找到"),@ApiResponse(code = 500, message = "系统内部错误")  })
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public WfPageListResponse<WfTaskResponse> getTasks(@ApiParam(value = "任务名称") @QueryParam("name") String name,
								 @ApiParam(value = "任务名称模糊匹配") @QueryParam("nameLike") String nameLike,
								 @ApiParam(value = "任务描述") @QueryParam("description") String description,
								 @ApiParam(value = "任务描述模糊匹配") @QueryParam("descriptionLike") String descriptionLike,
								 @ApiParam(value = "任务优先级") @QueryParam("priority") Integer priority,
								 @ApiParam(value = "任务最小优先级") @QueryParam("minimumPriority") Integer minimumPriority, 
								 @ApiParam(value = "任务最大优先级") @QueryParam("maximumPriority") Integer maximumPriority,
								 @ApiParam(value = "任务处理人") @QueryParam("assignee") String assignee,
								 @ApiParam(value = "任务所有者") @QueryParam("owner") String owner,
								 @ApiParam(value = "是否未分配") @QueryParam("unassigned") Boolean unassigned,
								 @ApiParam(value = "代理状态") @QueryParam("delegationState") String delegationState,
								 @ApiParam(value = "任务候选人") @QueryParam("candidateUser") String candidateUser,
								 @ApiParam(value = "流程参与者(处理人、候选人、所有者)") @QueryParam("involvedUser") String involvedUser,
								 @ApiParam(value = "任务候选组") @QueryParam("candidateGroup") String candidateGroup,
								 @ApiParam(value = "任务所属流程定义key") @QueryParam("processDefinitionKey") String processDefinitionKey,
								 @ApiParam(value = "流程定义key模糊匹配") @QueryParam("processDefinitionKeyLike") String processDefinitionKeyLike,
								 @ApiParam(value = "任务所属流程定义名称") @QueryParam("processDefinitionName") String processDefinitionName,
								 @ApiParam(value = "流程定义名称模糊匹配") @QueryParam("processDefinitionNameLike") String processDefinitionNameLike,
								 @ApiParam(value = "任务所属流程实例id") @QueryParam("processInstanceId") String processInstanceId,
								 @ApiParam(value = "任务所属业务id") @QueryParam("processInstanceBusinessKey") String processInstanceBusinessKey,
								 @ApiParam(value = "任务执行id") @QueryParam("executionId") String executionId,
								 @ApiParam(value = "任务创建时间") @QueryParam("createdOn") Date createdOn,
								 @ApiParam(value = "创建时间下区间") @QueryParam("createdBefore") Date createdBefore,
								 @ApiParam(value = "创建时间上区间") @QueryParam("createdAfter") Date createdAfter,
								 @ApiParam(value = "是否排除有子任务") @QueryParam("excludeSubTasks") Boolean excludeSubTasks,
								 @ApiParam(value = "任务key") @QueryParam("taskDefinitionKey") String taskDefinitionKey,
								 @ApiParam(value = "任务key模糊匹配") @QueryParam("taskDefinitionKeyLike") String taskDefinitionKeyLike,
								 @ApiParam(value = "任务处理时间") @QueryParam("dueDate") Date dueDate,
								 @ApiParam(value = "任务处理时间下区间") @QueryParam("dueBefore") Date dueBefore,
								 @ApiParam(value = "任务处理时间上区间") @QueryParam("dueAfter") Date dueAfter,
								 @ApiParam(value = "任务是否活动的") @QueryParam("active") Boolean active,
								 @ApiParam(value = "是否只查询包含流程环节变量任务") @QueryParam("includeTaskLocalVariables") Boolean includeTaskLocalVariables,
								 @ApiParam(value = "是否只查询包含流程环节变量和全局变量任务") @QueryParam("includeProcessVariables") Boolean includeProcessVariables,
								 @ApiParam(value = "租户") @QueryParam("tenantId") String tenantId,
								 @ApiParam(value = "租户模糊匹配") @QueryParam("tenantIdLike") String tenantIdLike,
								 @ApiParam(value = "忽略租户条件") @QueryParam("withoutTenantId") Boolean withoutTenantId) {

		// Create a Task query request
		WfTaskQueryRequest request = new WfTaskQueryRequest();

		// Populate filter-parameters
		if (StringUtils.hasText(name)) {
			request.setName(name);
		}

		if (StringUtils.hasText(nameLike)) {
			request.setNameLike(nameLike);
		}

		if (StringUtils.hasText(description)) {
			request.setDescription(description);
		}

		if (StringUtils.hasText(description)) {
			request.setDescriptionLike(descriptionLike);
		}

		if (null != priority) {
			request.setPriority(priority);
		}

		if (null != minimumPriority) {
			request.setMinimumPriority(minimumPriority);
		}

		if (null != maximumPriority) {
			request.setMaximumPriority(maximumPriority);
		}

		if (StringUtils.hasText(assignee)) {
			request.setAssignee(assignee);
		}

		if (StringUtils.hasText(owner)) {
			request.setOwner(owner);
		}

		if (null != unassigned) {
			request.setUnassigned(unassigned);
		}

		if (StringUtils.hasText(delegationState)) {
			request.setDelegationState(delegationState);
		}

		if (StringUtils.hasText(candidateUser)) {
			request.setCandidateUser(candidateUser);
		}

		if (StringUtils.hasText(involvedUser)) {
			request.setInvolvedUser(involvedUser);
		}
		
		if (StringUtils.hasText(processDefinitionKey)) {
			request.setProcessDefinitionKey(processDefinitionKey);
		}

		if (StringUtils.hasText(processDefinitionKeyLike)) {
			request.setProcessDefinitionKeyLike(processDefinitionKeyLike);
		}

		if (StringUtils.hasText(processDefinitionName)) {
			request.setProcessDefinitionName(processDefinitionName);
		}

		if (StringUtils.hasText(processDefinitionNameLike)) {
			request.setProcessDefinitionNameLike(processDefinitionNameLike);
		}

		if (StringUtils.hasText(processInstanceId)) {
			request.setProcessInstanceId(processInstanceId);
		}

		if (StringUtils.hasText(processInstanceBusinessKey)) {
			request.setProcessInstanceBusinessKey(processInstanceBusinessKey);
		}

		if (StringUtils.hasText(executionId)) {
			request.setExecutionId(executionId);
		}

		if (null != createdOn) {
			request.setCreatedOn(createdOn);
		}

		if (null != createdBefore) {
			request.setCreatedBefore(createdBefore);
		}

		if (null != createdAfter) {
			request.setCreatedAfter(createdAfter);
		}

		if (null != excludeSubTasks) {
			request.setExcludeSubTasks(excludeSubTasks);
		}

		if (StringUtils.hasText(taskDefinitionKey)) {
			request.setTaskDefinitionKey(taskDefinitionKey);
		}

		if (StringUtils.hasText(taskDefinitionKeyLike)) {
			request.setTaskDefinitionKeyLike(taskDefinitionKeyLike);
		}

		if (null != dueDate) {
			request.setDueDate(dueDate);
		}

		if (null != dueBefore) {
			request.setDueBefore(dueBefore);
		}

		if (null != dueAfter) {
			request.setDueAfter(dueAfter);
		}

		if (null != active) {
			request.setActive(active);
		}

		if (null != includeTaskLocalVariables) {
			request.setIncludeTaskLocalVariables(includeTaskLocalVariables);
		}

		if (null != includeProcessVariables) {
			request.setIncludeProcessVariables(includeProcessVariables);
		}

		if (StringUtils.hasText(tenantId)) {
			request.setTenantId(tenantId);
		}

		if (StringUtils.hasText(tenantIdLike)) {
			request.setTenantIdLike(tenantIdLike);
		}

		if (null != withoutTenantId && Boolean.TRUE.equals(withoutTenantId)) {
			request.setWithoutTenantId(Boolean.TRUE);
		}
		
		TaskQuery taskQuery = taskService.createTaskQuery();

		return getTasksFromQueryRequest(taskQuery,request);
	}

	@ApiOperation(value = "获取任务信息", tags = "runtimeTask")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),@ApiResponse(code = 404, message = "操作失败，请求资源未找到"),@ApiResponse(code = 500, message = "系统内部错误")  })
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/{taskId}")
	public WfTaskResponse getTask(@ApiParam(value="任务id",required=false) @PathParam("taskId") String taskId,
								@ApiParam(value="是否包含local变量",required=false) @QueryParam("includeTaskLocalVariables") boolean includeTaskLocalVariables,
								@ApiParam(value="是否包含global变量",required=false) @QueryParam("includeProcessVariables") boolean includeProcessVariables) throws Exception {
		
		try {

			if (taskId == null) {
				throw new ActivitiIllegalArgumentException(
						"The taskId cannot be null");
			}
			
			TaskQuery taskQuery=taskService.createTaskQuery().taskId(taskId);
			if(includeTaskLocalVariables) taskQuery.includeTaskLocalVariables();
			if(includeProcessVariables) taskQuery.includeProcessVariables();
			Task task = taskQuery.singleResult();
			if (task == null) {
				throw new ActivitiObjectNotFoundException(
						"Could not find a task with id '" + taskId + "'.",
						Task.class);
			}
			
			C2RestResponseFactory responseFactory = new C2RestResponseFactory();
			
			WfTaskResponse taskResponse=new WfTaskResponse(task);
		    if (task.getProcessVariables() != null) {
		        Map<String, Object> variableMap = task.getProcessVariables();
		        for (String name : variableMap.keySet()) {
		        	taskResponse.addVariable(responseFactory.createRestVariable(name, variableMap.get(name), 
		              RestVariableScope.GLOBAL, task.getId(), C2RestResponseFactory.VARIABLE_TASK, false));
		        }
		      }
		      if (task.getTaskLocalVariables() != null) {
		        Map<String, Object> variableMap = task.getTaskLocalVariables();
		        for (String name : variableMap.keySet()) {
		        	taskResponse.addVariable(responseFactory.createRestVariable( name, variableMap.get(name), 
		              RestVariableScope.LOCAL, task.getId(), C2RestResponseFactory.VARIABLE_TASK, false));
		        }
		      }
		     
			return taskResponse;
			
		} catch (ActivitiIllegalArgumentException e1) {
			throw new InvalidRestParamException(e1.getMessage());
		} catch (ActivitiObjectNotFoundException e2) {
			throw new ResourceNotFoundException(e2.getMessage());
		} catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		}
	}
	
	
	@ApiOperation(value = "修改任务", tags = "runtimeTask")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),@ApiResponse(code = 404, message = "操作失败，请求资源未找到"),@ApiResponse(code = 500, message = "系统内部错误")  })
	@PUT
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/{taskId}")
	public WfTaskResponse updateTask(@PathParam("taskId") String taskId,WfTaskRequest taskRequest) throws Exception{

		try{
			
			if (taskRequest == null) {
				throw new ActivitiIllegalArgumentException("A request body was expected when updating the task.");
			}

			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			if (task == null) {
				throw new ActivitiObjectNotFoundException("Could not find a task with id '" + taskId + "'.",Task.class);
			}

			// Populate the task properties based on the request
			populateTaskFromRequest(task, taskRequest);

			// Save the task and fetch agian, it's possible that an
			// assignment-listener has updated
			// fields after it was saved so we can't use the in-memory task
			taskService.saveTask(task);
			task = taskService.createTaskQuery().taskId(task.getId()).singleResult();
			
			WfTaskResponse response = new WfTaskResponse(task);
			return response;

		}catch (ActivitiIllegalArgumentException e1) {
			throw new InvalidRestParamException(e1.getMessage());
		}catch (ActivitiObjectNotFoundException e2) {
			throw new ResourceNotFoundException(e2.getMessage());
		}catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		}
	}
	
	
	@ApiOperation(value = "处理任务",tags = "runtimeTask",notes = "处理任务：签收(CLAIM)、签收并完成(CLAIM_COMPLETE)、完成(COMPLETE)、委托代理(DELEGATE)、回绝委托代理(RESOLVE)、退回任务(REJECT)")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),
							@ApiResponse(code = 404, message = "操作失败，请求资源未找到"),
							@ApiResponse(code = 401, message = "操作失败，未经认证请求"),
							@ApiResponse(code = 500, message = "系统内部错误")  })
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/{taskId}")
	public WfActionResult executeTaskAction(@ApiParam(value = "任务id",required = true) @PathParam("taskId") String taskId,@ApiParam(value = "操作入参",required = true) WfTaskActionRequest actionRequest) throws Exception {

		try {
			
			
			WfOperator wfOperator=FowRestHelper.getWfOperator(actionRequest);

			if (actionRequest == null) {
				throw new ActivitiIllegalArgumentException(
						"A request body was expected when executing a task action.");
			}

			if (taskId == null) {
				throw new ActivitiIllegalArgumentException(
						"The taskId cannot be null");
			}

			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			if (task == null) {
				throw new ActivitiObjectNotFoundException("Could not find a task with id '" + taskId + "'.",Task.class);
			}
			
			WfActionResult wfActionResult=null;
			if (WfTaskActionRequest.actionType.COMPLETE.equals(actionRequest.getAction())) {
				wfActionResult= completeTask(wfOperator, task, actionRequest);
			}else if (WfTaskActionRequest.actionType.CLAIM.equals(actionRequest.getAction())) {
				wfActionResult= claimTask(wfOperator, task, actionRequest);
			}else if(WfTaskActionRequest.actionType.CLAIM_COMPLETE.equals(actionRequest.getAction())){
				wfActionResult= claimAndCompleteTask(wfOperator, task, actionRequest);
			}else if (WfTaskActionRequest.actionType.DELEGATE.equals(actionRequest.getAction())) {
				wfActionResult= delegateTask(wfOperator, task, actionRequest);
			}else if (WfTaskActionRequest.actionType.RESOLVE.equals(actionRequest.getAction())) {
				wfActionResult= resolveTask(wfOperator, task, actionRequest);
			}else if(WfTaskActionRequest.actionType.REJECT.equals(actionRequest.getAction())){
				wfActionResult= rejectTask(wfOperator, task, actionRequest);
			}else {
				throw new ActivitiIllegalArgumentException("Invalid action: '"+ actionRequest.getAction() + "'.");
			}
			task = taskService.createTaskQuery().taskId(taskId).singleResult();
			
			return wfActionResult;
			
		} catch (ActivitiIllegalArgumentException e) {
			throw new InvalidRestParamException(e.getMessage());
		}catch (ActivitiObjectNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		} catch(FlowUnauthorizedException e){
			throw e;
		}catch(ActivitiException e){
			if(e.getCause() instanceof PropertyNotFoundException){
				throw new InvalidRestParamException(e.getMessage());
			}else{
				throw new UnkownException(e.getMessage(), e);
			}
		}catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		}
	}
	
	
	@ApiOperation(value = "删除任务", tags = "runtimeTask")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),@ApiResponse(code = 404, message = "操作失败，请求资源未找到"),@ApiResponse(code = 500, message = "系统内部错误")  })
	@DELETE
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/{taskId}")
	public Response deleteTask( @ApiParam(value = "任务实例id", required = true) @PathParam("taskId") String taskId,
								@ApiParam(value = "是否删除历史任务实例", required = false) @QueryParam("cascadeHistory")
								Boolean cascadeHistory,
								@ApiParam(value = "删除原因", required = false) @QueryParam("deleteReason") String deleteReason) throws Exception{
		
		try{
			
		    if (taskId == null) {
		        throw new ActivitiIllegalArgumentException("The taskId cannot be null");
		    }
			
			Task taskToDelete = taskService.createTaskQuery().taskId(taskId).singleResult();
			if (taskToDelete == null) {
				throw new ActivitiObjectNotFoundException("Could not find a task with id '" + taskId + "'.",Task.class);
			}
			if (taskToDelete.getExecutionId() != null) {
				// Can't delete a task that is part of a process instance
				return Response.status(Response.Status.FORBIDDEN).entity("Cannot delete a task that is part of a process-instance.").build();
			}

			if (cascadeHistory != null) {
				// Ignore delete-reason since the task-history (where the reason is
				// recorded) will be deleted anyway
				taskService.deleteTask(taskToDelete.getId(),cascadeHistory);
			} else {
				// Delete with delete-reason
				taskService.deleteTask(taskToDelete.getId(),deleteReason);
			}
			
			return null;
			
		} catch (ActivitiIllegalArgumentException e1) {
			throw new InvalidRestParamException(e1.getMessage());
		} catch (ActivitiObjectNotFoundException e2) {
			throw new ResourceNotFoundException(e2.getMessage());
		} catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		}
	}
	
	
	@ApiOperation(value = "获取某任务流程变量集合", tags = "runtimeTask")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),@ApiResponse(code = 404, message = "操作失败，请求资源未找到"),@ApiResponse(code = 500, message = "系统内部错误")  })
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/{taskId}/variables")
	public List<WfRestVariable> getVariables( @ApiParam(value = "任务实例id", required = true) @PathParam("taskId") String taskId,
											@ApiParam(value = "作用域(local/global)", required = true) @QueryParam("scope") String scope) throws Exception{

		try{
			
		    if (taskId == null) {
		        throw new ActivitiIllegalArgumentException("The taskId cannot be null");
		    }
			
			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			if (task == null) {
				throw new ActivitiObjectNotFoundException("Could not find a task with id '" + taskId + "'.",Task.class);
			}
			
			List<WfRestVariable> result = new ArrayList<WfRestVariable>();
			Map<String, WfRestVariable> variableMap = new HashMap<String, WfRestVariable>();

			RestVariableScope variableScope = RestVariable.getScopeFromString(scope);
			if (variableScope == null) {
				// Use both local and global variables
				addLocalVariables(task, variableMap);
				addGlobalVariables(task, variableMap);
			} else if (variableScope == RestVariableScope.GLOBAL) {
				addGlobalVariables(task, variableMap);
			} else if (variableScope == RestVariableScope.LOCAL) {
				addLocalVariables(task, variableMap);
			}

			// Get unique variables from map
			result.addAll(variableMap.values());
			return result;
		} catch (ActivitiIllegalArgumentException e1) {
			throw new InvalidRestParamException(e1.getMessage());
		} catch (ActivitiObjectNotFoundException e2) {
			throw new ResourceNotFoundException(e2.getMessage());
		} catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		}

	}
	
	
	@ApiOperation(value = "批量添加任务流程变量", tags = "runtimeTask")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),
							@ApiResponse(code = 404, message = "操作失败，请求资源未找到"),
							@ApiResponse(code = 409, message = "存在冲突"),
							@ApiResponse(code = 500, message = "系统内部错误")})
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/{taskId}/variables")
	public List<WfRestVariable> createTaskVariable(
			@ApiParam(value = "任务实例id", required = true) @PathParam("taskId") String taskId,
			@ApiParam(value = "变量集合", required = true) WfRestVariable[] restVariables) throws Exception{

		try{
			
			if (taskId == null) {
				throw new ActivitiIllegalArgumentException(
						"The taskId cannot be null");
			}

			if (restVariables == null || restVariables.length == 0) {
				throw new ActivitiIllegalArgumentException(
						"Request didn't contain a list of variables to create.");
			}

			List<WfRestVariable> variables = new ArrayList<WfRestVariable>();

			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			if (task == null) {
				throw new ActivitiObjectNotFoundException(
						"Could not find a task with id '" + taskId + "'.",
						Task.class);
			}

			RestVariableScope sharedScope = null;
			RestVariableScope varScope = null;
			Map<String, Object> variablesToSet = new HashMap<String, Object>();

			C2RestResponseFactory factory = new C2RestResponseFactory();
			for (WfRestVariable var : restVariables) {
				// Validate if scopes match
				varScope = var.getVariableScope();
				if (var.getName() == null) {
					throw new ActivitiIllegalArgumentException(
							"Variable name is required");
				}

				if (varScope == null) {
					varScope = RestVariableScope.LOCAL;
				}
				if (sharedScope == null) {
					sharedScope = varScope;
				}
				if (varScope != sharedScope) {
					throw new ActivitiIllegalArgumentException(
							"Only allowed to update multiple variables in the same scope.");
				}

				if (hasVariableOnScope(task, var.getName(), varScope)) {
					throw new FlowResourceAlreadyExistsException("Variable '" + var.getName()
							+ "' is already present on task '"
							+ task.getId() + "'.");
				}
				
				Object actualVariableValue = factory.getVariableValue(var);
				variablesToSet.put(var.getName(), actualVariableValue);
				variables.add(factory.createRestVariable(var.getName(),
						actualVariableValue, varScope, task.getId(),
						RestResponseFactory.VARIABLE_TASK, false));
			}

			if (variablesToSet.size() > 0) {
				if (sharedScope == RestVariableScope.LOCAL) {
					taskService.setVariablesLocal(task.getId(), variablesToSet);
				} else {
					if (task.getExecutionId() != null) {
						// Explicitly set on execution, setting non-local variables
						// on task will override local-variables if exists
						runtimeService.setVariables(task.getExecutionId(),
								variablesToSet);
					} else {
						// Standalone task, no global variables possible
						throw new ActivitiIllegalArgumentException(
								"Cannot set global variables on task '"
										+ task.getId()
										+ "', task is not part of process.");
					}
				}
			}
			
			return variables;
		} catch (ActivitiIllegalArgumentException e) {
			throw new InvalidRestParamException(e.getMessage());
		} catch (ActivitiObjectNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		} catch(FlowResourceAlreadyExistsException e){
			throw e;
		}catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		}
		
	}
	
	
	@ApiOperation(value = "批量删除任务流程变量", tags = "runtimeTask")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),@ApiResponse(code = 404, message = "操作失败，请求资源未找到"),@ApiResponse(code = 500, message = "系统内部错误")  })
	@DELETE
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/{taskId}/variables")
	public void deleteAllLocalTaskVariables(@ApiParam(value = "任务实例id", required = true) @PathParam("taskId") String taskId)
			throws Exception {

		try {

			if (taskId == null) {
				throw new ActivitiIllegalArgumentException(
						"The taskId cannot be null");
			}

			Task task = taskService.createTaskQuery().taskId(taskId)
					.singleResult();
			if (task == null) {
				throw new ActivitiObjectNotFoundException(
						"Could not find a task with id '" + taskId + "'.",
						Task.class);
			}

			Collection<String> currentVariables = taskService.getVariablesLocal(task.getId()).keySet();
			taskService.removeVariablesLocal(task.getId(), currentVariables);

		} catch (ActivitiIllegalArgumentException e1) {
			throw new InvalidRestParamException(e1.getMessage());
		} catch (ActivitiObjectNotFoundException e2) {
			throw new ResourceNotFoundException(e2.getMessage());
		} catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		}

	}
	
	
	@ApiOperation(value = "获取某任务某流程变量", tags = "runtimeTask")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),@ApiResponse(code = 404, message = "操作失败，请求资源未找到"),@ApiResponse(code = 500, message = "系统内部错误")  })
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/{taskId}/variables/{variableName}")
	public WfRestVariable getVariable(@ApiParam(value = "任务实例id", required = true) @PathParam("taskId") String taskId,
									@ApiParam(value = "流程变量名", required = true) @PathParam("variableName") String variableName,
									@ApiParam(value = "作用域(local/global)", required = true) @QueryParam("scope") String scope) throws Exception{
		
		
		try{
			
			if (null==taskId) {
				throw new ActivitiIllegalArgumentException(
						"The taskId cannot be null");
			}

			if (null==variableName) {
				throw new ActivitiIllegalArgumentException(
						"The variableName cannot be null");
			}
			
			boolean variableFound = false;
		    Object value = null;
		    RestVariableScope variableScope = RestVariable.getScopeFromString(scope);
		    if(variableScope == null) {
		      // First, check local variables (which have precedence when no scope is supplied)
		      if(taskService.hasVariableLocal(taskId, variableName)) {
		        value = taskService.getVariableLocal(taskId, variableName);
		        variableScope = RestVariableScope.LOCAL;
		        variableFound = true;
		      } else {
		        // Revert to execution-variable when not present local on the task
		        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		        if(task.getExecutionId() != null && runtimeService.hasVariable(task.getExecutionId(), variableName)) {
		          value = runtimeService.getVariable(task.getExecutionId(), variableName);
		          variableScope = RestVariableScope.GLOBAL;
		          variableFound = true;
		        }
		      }
		      
		    } else if(variableScope == RestVariableScope.GLOBAL) {
		      Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		      if(task.getExecutionId() != null && runtimeService.hasVariable(task.getExecutionId(), variableName)) {
		        value = runtimeService.getVariable(task.getExecutionId(), variableName);
		        variableFound = true;
		      }
		      
		    } else if(variableScope == RestVariableScope.LOCAL) {
		      if(taskService.hasVariableLocal(taskId, variableName)) {
		        value = taskService.getVariableLocal(taskId, variableName);
		        variableFound = true;
		      }
		    }
		    
		    if(!variableFound) {
		        throw new ActivitiObjectNotFoundException("Task '" + taskId + "' doesn't have a variable with name: '" + variableName + "'.", VariableInstanceEntity.class);
		    } else {
		      C2RestResponseFactory responseFactory = new C2RestResponseFactory();
		      return responseFactory.createRestVariable(variableName, value, variableScope, taskId, RestResponseFactory.VARIABLE_TASK, false);
		    }
		} catch (ActivitiIllegalArgumentException e1) {
			throw new InvalidRestParamException(e1.getMessage());
		} catch (ActivitiObjectNotFoundException e2) {
			throw new ResourceNotFoundException(e2.getMessage());
		} catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		}
		
	}
	
	
	@ApiOperation(value = "获取某任务意见列表", tags = "runtimeTask")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),@ApiResponse(code = 404, message = "操作失败，请求资源未找到"),@ApiResponse(code = 500, message = "系统内部错误")  })
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/{taskId}/comments")
	public List<WfCommentResponse> getComments(@ApiParam(value = "任务实例id", required = true) @PathParam("taskId") String taskId) throws Exception{

		try {

			if (taskId == null) {
				throw new ActivitiIllegalArgumentException(
						"The taskId cannot be null");
			}

			HistoricTaskInstance task =historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
			if (task == null) {
				throw new ActivitiObjectNotFoundException(
						"Could not find a task with id '" + taskId + "'.",
						Task.class);
			}

			List<WfCommentResponse> result = new ArrayList<WfCommentResponse>();
			C2RestResponseFactory responseFactory = new C2RestResponseFactory();

			for (Comment comment : taskService.getTaskComments(task.getId())) {
				result.add(responseFactory.createRestComment(comment));
			}

			return result;
		} catch (ActivitiIllegalArgumentException e1) {
			throw new InvalidRestParamException(e1.getMessage());
		} catch (ActivitiObjectNotFoundException e2) {
			throw new ResourceNotFoundException(e2.getMessage());
		} catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		}
	}
	
	
	@ApiOperation(value = "添加任务意见", tags = "runtimeTask")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),@ApiResponse(code = 404, message = "操作失败，请求资源未找到"),@ApiResponse(code = 500, message = "系统内部错误")  })
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/{taskId}/comments")
	public WfCommentResponse createComment(@ApiParam(value = "任务实例id", required = true) @PathParam("taskId") String taskId,
			@ApiParam(value = "竟见数据", required = true) WfCommentRequest comment) throws Exception{

		try{
			
			if (taskId == null) {
		        throw new ActivitiIllegalArgumentException("The taskId cannot be null");
		      }
		      
		      Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		      if (task == null) {
		        throw new ActivitiObjectNotFoundException("Could not find a task with id '" + taskId + "'.", Task.class);
		      }

			if (comment.getMessage() == null) {
				throw new ActivitiIllegalArgumentException(
						"Comment text is required.");
			}
			
			identityService.setAuthenticatedUserId(comment.getAuthor());
			Comment createdComment = taskService.addComment(task.getId(), null, comment.getMessage());
			C2RestResponseFactory responseFactory = new C2RestResponseFactory();
			return responseFactory.createRestComment(createdComment);
			
		}catch (ActivitiIllegalArgumentException e1) {
			throw new InvalidRestParamException(e1.getMessage());
		} catch (ActivitiObjectNotFoundException e2) {
			throw new ResourceNotFoundException(e2.getMessage());
		} catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		} finally {
          identityService.setAuthenticatedUserId(null);
        }
		
	    
	}
	
	
	@ApiOperation(value = "获取某任务下某条意见详细", tags = "runtimeTask")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),@ApiResponse(code = 404, message = "操作失败，请求资源未找到"),@ApiResponse(code = 500, message = "系统内部错误")  })
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/{taskId}/comments/{commentId}")
	public WfCommentResponse getComment(
			@ApiParam(value = "任务实例id", required = true) @PathParam("taskId") String taskId,
			@ApiParam(value = "意见id", required = true) @PathParam("commentId") String commentId) throws Exception{

		try{
			
			if (taskId == null) {
				throw new ActivitiIllegalArgumentException(
						"The taskId cannot be null");
			}

			if (commentId == null) {
				throw new ActivitiIllegalArgumentException("CommentId is required.");
			}

			HistoricTaskInstance task = historyService
					.createHistoricTaskInstanceQuery().taskId(taskId)
					.singleResult();
			if (task == null) {
				throw new ActivitiObjectNotFoundException(
						"Could not find a task with id '" + taskId + "'.",
						Task.class);
			}

			Comment comment = taskService.getComment(commentId);
			if (comment == null || !task.getId().equals(comment.getTaskId())) {
				throw new ActivitiObjectNotFoundException("Task '" + task.getId()
						+ "' doesn't have a comment with id '" + commentId + "'.",
						Comment.class);
			}

			C2RestResponseFactory responseFactory = new C2RestResponseFactory();
			return responseFactory.createRestComment(comment);
		}catch (ActivitiIllegalArgumentException e1) {
			throw new InvalidRestParamException(e1.getMessage());
		} catch (ActivitiObjectNotFoundException e2) {
			throw new ResourceNotFoundException(e2.getMessage());
		} catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		}
		
	}
	
	@ApiOperation(value = "删了某任务下某条意见", tags = "runtimeTask")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),@ApiResponse(code = 404, message = "操作失败，请求资源未找到"),@ApiResponse(code = 500, message = "系统内部错误")  })
	@DELETE
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/{taskId}/comments/{commentId}")
	public void deleteComment(
			@ApiParam(value = "任务实例id", required = true) @PathParam("taskId") String taskId,
			@ApiParam(value = "意见id", required = true) @PathParam("commentId") String commentId) throws Exception{

		
		try{
			
			// Check if task exists
			if (taskId == null) {
				throw new ActivitiIllegalArgumentException(
						"The taskId cannot be null");
			}

			if (commentId == null) {
				throw new ActivitiIllegalArgumentException("CommentId is required.");
			}

			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			if (task == null) {
				throw new ActivitiObjectNotFoundException(
						"Could not find a task with id '" + taskId + "'.",
						Task.class);
			}

			Comment comment = taskService.getComment(commentId);
			if (comment == null || comment.getTaskId() == null
					|| !comment.getTaskId().equals(task.getId())) {
				throw new ActivitiObjectNotFoundException("Task '" + task.getId()
						+ "' doesn't have a comment with id '" + commentId + "'.",
						Comment.class);
			}

			taskService.deleteComment(commentId);
		}catch (ActivitiIllegalArgumentException e1) {
			throw new InvalidRestParamException(e1.getMessage());
		} catch (ActivitiObjectNotFoundException e2) {
			throw new ResourceNotFoundException(e2.getMessage());
		} catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		}
	}

	
	protected WfActionResult completeTask(WfOperator wfOperator,Task task, WfTaskActionRequest actionRequest) throws Exception{
		Map<String, Object> variablesToSet=null;
		if (actionRequest.getVariables() != null) {
			variablesToSet = new HashMap<String, Object>();
			for (WfRestVariable var : actionRequest.getVariables()) {
				if (var.getName() == null) {
					throw new ActivitiIllegalArgumentException(
							"Variable name is required");
				}

				C2RestResponseFactory factory = new C2RestResponseFactory();
				Object actualVariableValue = factory.getVariableValue(var);
				variablesToSet.put(var.getName(), actualVariableValue);
			}
		}
		
		WfResult wfResult= wfTaskService.operateTask(wfOperator,task.getId(),WfTaskAction.COMPLETE,null,variablesToSet);
		return new WfActionResult(wfResult);
	}

	
	protected WfActionResult claimTask(WfOperator wfOperator,Task task, WfTaskActionRequest actionRequest) throws Exception{
		Map<String, Object> variablesToSet=null;
		if (actionRequest.getVariables() != null) {
			variablesToSet = new HashMap<String, Object>();
			for (WfRestVariable var : actionRequest.getVariables()) {
				if (var.getName() == null) {
					throw new ActivitiIllegalArgumentException(
							"Variable name is required");
				}

				C2RestResponseFactory factory = new C2RestResponseFactory();
				Object actualVariableValue = factory.getVariableValue(var);
				variablesToSet.put(var.getName(), actualVariableValue);
			}
		}
		WfResult wfResult= wfTaskService.operateTask(wfOperator, task.getId(), WfTaskAction.CLAIM,null,variablesToSet);
		return new WfActionResult(wfResult);
	}
	
	
	protected WfActionResult claimAndCompleteTask(WfOperator wfOperator,Task task, WfTaskActionRequest actionRequest) throws Exception{
		Map<String, Object> variablesToSet=null;
		if (actionRequest.getVariables() != null) {
			variablesToSet = new HashMap<String, Object>();
			for (WfRestVariable var : actionRequest.getVariables()) {
				if (var.getName() == null) {
					throw new ActivitiIllegalArgumentException(
							"Variable name is required");
				}

				C2RestResponseFactory factory = new C2RestResponseFactory();
				Object actualVariableValue = factory.getVariableValue(var);
				variablesToSet.put(var.getName(), actualVariableValue);
			}
		}
		WfResult wfResult= wfTaskService.operateTask(wfOperator, task.getId(), WfTaskAction.CLAIM_COMPLETE,null,variablesToSet);
		return new WfActionResult(wfResult);
	}

	protected WfActionResult delegateTask(WfOperator wfOperator,Task task, WfTaskActionRequest actionRequest) throws Exception{
		
		if (actionRequest.getAssignee() == null) {
			throw new ActivitiIllegalArgumentException(
					"An assignee is required when delegating a task.");
		}
		
		Map<String, Object> variablesToSet=null;
		if (actionRequest.getVariables() != null) {
			variablesToSet = new HashMap<String, Object>();
			for (WfRestVariable var : actionRequest.getVariables()) {
				if (var.getName() == null) {
					throw new ActivitiIllegalArgumentException(
							"Variable name is required");
				}

				C2RestResponseFactory factory = new C2RestResponseFactory();
				Object actualVariableValue = factory.getVariableValue(var);
				variablesToSet.put(var.getName(), actualVariableValue);
			}
		}
		
		WfResult wfResult= wfTaskService.operateTask(wfOperator, task.getId(), WfTaskAction.DELEGATE,actionRequest.getAssignee(),variablesToSet);
		return new WfActionResult(wfResult);
	}

	
	protected WfActionResult resolveTask(WfOperator wfOperator,Task task, WfTaskActionRequest actionRequest) throws Exception{
		
		Map<String, Object> variablesToSet=null;
		if (actionRequest.getVariables() != null) {
			variablesToSet = new HashMap<String, Object>();
			for (WfRestVariable var : actionRequest.getVariables()) {
				if (var.getName() == null) {
					throw new ActivitiIllegalArgumentException(
							"Variable name is required");
				}

				C2RestResponseFactory factory = new C2RestResponseFactory();
				Object actualVariableValue = factory.getVariableValue(var);
				variablesToSet.put(var.getName(), actualVariableValue);
			}
		}
		
		WfResult wfResult= wfTaskService.operateTask(wfOperator, task.getId(), WfTaskAction.RESOLVE,null,variablesToSet);
		return new WfActionResult(wfResult);
	}
	
	
	protected WfActionResult rejectTask(WfOperator wfOperator,Task task,WfTaskActionRequest actionRequest) throws Exception{
		Map<String, Object> variablesToSet=null;
	    if(actionRequest.getVariables() != null) {
	    	variablesToSet= new HashMap<String, Object>(); 
	        for(WfRestVariable var : actionRequest.getVariables()) {
	          if(var.getName() == null) {
	            throw new ActivitiIllegalArgumentException("Variable name is required");
	          }
	          
	          C2RestResponseFactory factory = new C2RestResponseFactory();
	          Object actualVariableValue = factory.getVariableValue(var);
	          variablesToSet.put(var.getName(),actualVariableValue);
	        }
	    }
	    WfResult wfResult= wfTaskService.reject(wfOperator,task.getId(),null,variablesToSet);
		return new WfActionResult(wfResult);
	}
	
	
	protected void addLocalVariables(Task task,Map<String, WfRestVariable> variableMap) {
		Map<String, Object> rawVariables = taskService.getVariablesLocal(task.getId());
		C2RestResponseFactory factory = new C2RestResponseFactory();
		List<WfRestVariable> localVariables = factory.createRestVariables(rawVariables, task.getId(),RestResponseFactory.VARIABLE_TASK,RestVariableScope.LOCAL);

		for (WfRestVariable var : localVariables) {
			variableMap.put(var.getName(), var);
		}
	}
	
	protected void addGlobalVariables(Task task,Map<String, WfRestVariable> variableMap) {
		if (task.getExecutionId() != null) {
			Map<String, Object> rawVariables = runtimeService.getVariables(task.getExecutionId());
			C2RestResponseFactory factory = new C2RestResponseFactory();
			List<WfRestVariable> globalVariables =factory.createRestVariables(
							rawVariables, task.getId(),
							RestResponseFactory.VARIABLE_TASK,
							RestVariableScope.GLOBAL);

			// Overlay global variables over local ones. In case they are
			// present the values are not overridden,
			// since local variables get precedence over global ones at all
			// times.
			for (WfRestVariable var : globalVariables) {
				if (!variableMap.containsKey(var.getName())) {
					variableMap.put(var.getName(), var);
				}
			}
		}
	}
	
  protected boolean hasVariableOnScope(Task task, String variableName, RestVariableScope scope) {
	    boolean variableFound = false;
	      
	    if(scope == RestVariableScope.GLOBAL) {
	      if(task.getExecutionId() != null && runtimeService.hasVariable(task.getExecutionId(), variableName)) {
	        variableFound = true;
	      }
	      
	    } else if(scope == RestVariableScope.LOCAL) {
	      if(taskService.hasVariableLocal(task.getId(), variableName)) {
	        variableFound = true;
	      }
	    }
	    return variableFound;
 }
  
  
  /**
   * 分支测试
   * @return
   */
  public long getNumber(){
	  return System.currentTimeMillis()%2;
  }
}