package com.chinacreator.c2.flow.rest.history.web.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricIdentityLink;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.rest.service.api.engine.variable.RestVariable.RestVariableScope;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.chinacreator.c2.flow.rest.common.C2RestResponseFactory;
import com.chinacreator.c2.flow.rest.common.vo.WfHistoricIdentityLinkResponse;
import com.chinacreator.c2.flow.rest.common.vo.WfHistoricTaskInstanceQueryRequest;
import com.chinacreator.c2.flow.rest.common.vo.WfHistoricTaskInstanceResponse;
import com.chinacreator.c2.flow.rest.common.vo.WfPageListResponse;
import com.chinacreator.c2.flow.rest.history.HistoricTaskInstanceBaseResource;
import com.chinacreator.c2.web.exception.InvalidRestParamException;
import com.chinacreator.c2.web.exception.ResourceNotFoundException;
import com.chinacreator.c2.web.exception.UnkownException;

/**
 * 历史流程实例rest接口
 * @author hushow
 */
@Service
@Path("v1/flow/history/tasks")
@Api
public class WfHistoryTaskResource extends HistoricTaskInstanceBaseResource{

	@Autowired
	ProcessEngineFactoryBean processEngine;
	
	@Autowired
	HistoryService historyService;
	
	@Autowired
	TaskService taskService;
	
  	@ApiOperation(value = "获取历史任务列表",tags = "historyTask")
  	@ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),@ApiResponse(code = 404, message = "操作失败，请求资源未找到"),@ApiResponse(code = 500, message = "系统内部错误")  })
  	@GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Consumes({ MediaType.APPLICATION_JSON })
    public WfPageListResponse<WfHistoricTaskInstanceResponse> getHistoricTaskProcessInstances(@ApiParam(value = "任务id") @QueryParam("taskId") String taskId,
    												@ApiParam(value = "流程实例") @QueryParam("processInstanceId") String processInstanceId,
    												@ApiParam(value = "业务主键") @QueryParam("processBusinessKey") String processBusinessKey,
    												@ApiParam(value = "流程定key") @QueryParam("processDefinitionKey") String processDefinitionKey,
    												@ApiParam(value = "流程定义id") @QueryParam("processDefinitionId") String processDefinitionId,
    												@ApiParam(value = "流程定义名称") @QueryParam("processDefinitionName") String processDefinitionName,
    												@ApiParam(value = "任务执行id") @QueryParam("executionId") String executionId,
    												@ApiParam(value = "任务名称") @QueryParam("taskName") String taskName,
    												@ApiParam(value = "任务名称模糊匹配") @QueryParam("taskNameLike") String taskNameLike,
    												@ApiParam(value = "任务描述") @QueryParam("taskDescription") String taskDescription,
    												@ApiParam(value = "任务描述模糊匹配") @QueryParam("taskDescriptionLike") String taskDescriptionLike,
    												@ApiParam(value = "任务key") @QueryParam("taskDefinitionKey") String taskDefinitionKey,
    												@ApiParam(value = "任务删除原因") @QueryParam("taskDeleteReason") String taskDeleteReason,
    												@ApiParam(value = "任务删除原因模糊匹配") @QueryParam("taskDeleteReasonLike") String taskDeleteReasonLike,
    												@ApiParam(value = "处理人") @QueryParam("taskAssignee") String taskAssignee,
    												@ApiParam(value = "处理人模糊匹配") @QueryParam("taskAssigneeLike") String taskAssigneeLike,
    												@ApiParam(value = "任务所有者") @QueryParam("taskOwner") String taskOwner,
    												@ApiParam(value = "任务所有者模糊匹配") @QueryParam("taskOwnerLike") String taskOwnerLike,
    												@ApiParam(value = "任务参与者(处理人、候选人、所有者)") @QueryParam("taskInvolvedUser") String taskInvolvedUser,
    												@ApiParam(value = "任务优先级") @QueryParam("taskPriority") Integer taskPriority,
    												@ApiParam(value = "是否完成") @QueryParam("finished") Boolean finished,
    												@ApiParam(value = "任务所在流程实例是否完成") @QueryParam("processFinished") Boolean processFinished,
    												@ApiParam(value = "父任务") @QueryParam("parentTaskId") String parentTaskId,
    												@ApiParam(value = "任务过期时间") @QueryParam("dueDate") Date dueDate,
    												@ApiParam(value = "过期时间下区间") @QueryParam("dueDateBefore") Date dueDateBefore,
    												@ApiParam(value = "过期时间上区间") @QueryParam("dueDateAfter") Date dueDateAfter,
    												@ApiParam(value = "任务创建时间") @QueryParam("taskCreatedOn") Date taskCreatedOn,
    												@ApiParam(value = "创建时间下区间") @QueryParam("taskCreatedBefore") Date taskCreatedBefore,
    												@ApiParam(value = "创建时间上区间") @QueryParam("taskCreatedAfter") Date taskCreatedAfter,
    												@ApiParam(value = "任务完成时间") @QueryParam("taskCompletedOn") Date taskCompletedOn,
    												@ApiParam(value = "完成时间下区间") @QueryParam("taskCompletedBefore") Date taskCompletedBefore,
    												@ApiParam(value = "完成时间上区间") @QueryParam("taskCompletedAfter") Date taskCompletedAfter,
    												@ApiParam(value = "是否只查询包含流程环节变量任务") @QueryParam("includeTaskLocalVariables") Boolean includeTaskLocalVariables,
    												@ApiParam(value = "是否只查询包含流程环节变量和全局变量任务") @QueryParam("includeProcessVariables") Boolean includeProcessVariables,
    												@ApiParam(value = "租户") @QueryParam("tenantId") String tenantId,
    												@ApiParam(value = "租户模糊匹配") @QueryParam("tenantIdLike") String tenantIdLike,
    												@ApiParam(value = "忽略租户条件") @QueryParam("withoutTenantId") Boolean withoutTenantId) throws Exception{
  	  
  		try{
	  			 // Populate query based on request
  			WfHistoricTaskInstanceQueryRequest queryRequest = new WfHistoricTaskInstanceQueryRequest();
	  	      
	  	      if(StringUtils.hasText(taskId)) {
	  	        queryRequest.setTaskId(taskId);
	  	      }
	  	      
	  	      if(StringUtils.hasText(processInstanceId)) {
	  	        queryRequest.setProcessInstanceId(processInstanceId);
	  	      }
	  	      
	  	      if(StringUtils.hasText(processBusinessKey)) {
	  	        queryRequest.setProcessBusinessKey(processBusinessKey);
	  	      }
	  	      
	  	      if(StringUtils.hasText(processDefinitionKey)) {
	  	        queryRequest.setProcessDefinitionKey(processDefinitionKey);
	  	      }
	  	      
	  	      if(StringUtils.hasText(processDefinitionId)) {
	  	        queryRequest.setProcessDefinitionId(processDefinitionId);
	  	      }
	  	      
	  	      if(StringUtils.hasText(processDefinitionName)) {
	  	        queryRequest.setProcessDefinitionName(processDefinitionName);
	  	      }
	  	      
	  	      if(StringUtils.hasText(executionId)) {
	  	        queryRequest.setExecutionId(executionId);
	  	      }
	  	      
	  	      if(StringUtils.hasText(taskName)){
	  	        queryRequest.setTaskName(taskName);
	  	      }
	  	      
	  	      if(StringUtils.hasText(taskNameLike)) {
	  	        queryRequest.setTaskNameLike(taskNameLike);
	  	      }
	  	      
	  	      if(StringUtils.hasText(taskDescription)) {
	  	        queryRequest.setTaskDescription(taskDescription);
	  	      }
	  	      
	  	      if(StringUtils.hasText(taskDescriptionLike)) {
	  	        queryRequest.setTaskDescriptionLike(taskDescriptionLike);
	  	      }
	  	      
	  	      if(StringUtils.hasText(taskDefinitionKey)) {
	  	        queryRequest.setTaskDefinitionKey(taskDefinitionKey);
	  	      }
	  	      
	  	      if(StringUtils.hasText(taskDeleteReason)) {
	  	        queryRequest.setTaskDeleteReason(taskDeleteReason);
	  	      }
	  	      
	  	      if(StringUtils.hasText(taskDeleteReasonLike)) {
	  	        queryRequest.setTaskDeleteReasonLike(taskDeleteReasonLike);
	  	      }
	  	      
	  	      if(StringUtils.hasText(taskAssignee)) {
	  	        queryRequest.setTaskAssignee(taskAssignee);
	  	      }
	  	      
	  	      if(StringUtils.hasText(taskAssigneeLike)) {
	  	        queryRequest.setTaskAssigneeLike(taskAssigneeLike);
	  	      }
	  	      
	  	      if(StringUtils.hasText(taskOwner)) {
	  	        queryRequest.setTaskOwner(taskOwner);
	  	      }
	  	      
	  	      if(StringUtils.hasText(taskOwnerLike)) {
	  	        queryRequest.setTaskOwnerLike(taskOwnerLike);
	  	      }
	  	      
	  	      if(StringUtils.hasText(taskInvolvedUser)) {
	  	        queryRequest.setTaskInvolvedUser(taskInvolvedUser);
	  	      }
	  	      
	  	      if(null!=taskPriority) {
	  	        queryRequest.setTaskPriority(taskPriority);
	  	      }
	  	      
	  	      if(null!=finished) {
	  	        queryRequest.setFinished(finished);
	  	      }
	  	      
	  	      if(null!=processFinished) {
	  	        queryRequest.setProcessFinished(processFinished);
	  	      }
	  	      
	  	      if(StringUtils.hasText(parentTaskId)) {
	  	        queryRequest.setParentTaskId(parentTaskId);
	  	      }
	  	      
	  	      if(null!=dueDate) {
	  	        queryRequest.setDueDate(dueDate);
	  	      }
	  	      
	  	      if(null!=dueDateAfter) {
	  	        queryRequest.setDueDateAfter(dueDateAfter);
	  	      }
	  	      
	  	      if(null!=dueDateBefore) {
	  	        queryRequest.setDueDateBefore(dueDateBefore);
	  	      }
	  	      
	  	      if(null!=taskCreatedOn) {
	  	        queryRequest.setTaskCreatedOn(taskCreatedOn);
	  	      }
	  	      
	  	      if(null!=taskCreatedBefore) {
	  	      	queryRequest.setTaskCreatedBefore(taskCreatedBefore);
	  	      }
	  	      
	  	      if(null!=taskCreatedAfter) {
	  	      	queryRequest.setTaskCreatedAfter(taskCreatedAfter);
	  	      }
	  	      
	  	      if(null!=taskCompletedOn) {
	  	      	queryRequest.setTaskCompletedOn(taskCompletedOn);
	  	      }
	  	      
	  	      if(null!=taskCompletedBefore) {
	  	      	queryRequest.setTaskCompletedBefore(taskCompletedBefore);
	  	      }
	  	      
	  	      if(null!=taskCompletedAfter) {
	  	      	queryRequest.setTaskCompletedAfter(taskCompletedAfter);
	  	      }
	  	      
	  	      if(null!=includeTaskLocalVariables) {
	  	        queryRequest.setIncludeTaskLocalVariables(includeTaskLocalVariables);
	  	      }
	  	      
	  	      if(null!=includeProcessVariables) {
	  	        queryRequest.setIncludeProcessVariables(includeProcessVariables);
	  	      }
	  	      
	  	      if(StringUtils.hasText(tenantId)) {
	  	        queryRequest.setTenantId(tenantId);
	  	      }
	  	      
	  	      if(StringUtils.hasText(tenantIdLike)) {
	  	      	queryRequest.setTenantIdLike(tenantIdLike);
	  	      }
	  	      
	  	      if(null!=withoutTenantId) {
	  	      	queryRequest.setWithoutTenantId(withoutTenantId);
	  	      }
	  	      
	  	      return getQueryResponse(historyService.createHistoricTaskInstanceQuery(),queryRequest);
  		}catch (ActivitiIllegalArgumentException e1) {
			throw new InvalidRestParamException(e1.getMessage());
		} catch (ActivitiObjectNotFoundException e2) {
			throw new ResourceNotFoundException(e2.getMessage());
		} catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		}
     
    }

  	
	@ApiOperation(value = "获取历史任务信息", tags = "historyTask")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),@ApiResponse(code = 404, message = "操作失败，请求资源未找到"),@ApiResponse(code = 500, message = "系统内部错误")  })
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/{taskId}")
	public WfHistoricTaskInstanceResponse getTaskInstance(@ApiParam(value = "任务id", required = true) @PathParam("taskId") String taskId,
														@ApiParam(value="是否包含local变量",required=false) @QueryParam("includeTaskLocalVariables") boolean includeTaskLocalVariables,
														@ApiParam(value="是否包含global变量",required=false) @QueryParam("includeProcessVariables") boolean includeProcessVariables) throws Exception {

		try {
			
			if (taskId == null) {
				throw new ActivitiIllegalArgumentException(
						"The taskId cannot be null");
			}

			HistoricTaskInstanceQuery historicTaskInstanceQuery=historyService.createHistoricTaskInstanceQuery().taskId(taskId);
			if(includeTaskLocalVariables) historicTaskInstanceQuery.includeTaskLocalVariables();
			if(includeProcessVariables) historicTaskInstanceQuery.includeProcessVariables();
			HistoricTaskInstance taskInstance =historicTaskInstanceQuery.singleResult();
			if (taskInstance == null) {
				throw new ActivitiObjectNotFoundException(
						"Could not find a task instance with id '" + taskId
								+ "'.", HistoricTaskInstance.class);
			}

			WfHistoricTaskInstanceResponse result = new WfHistoricTaskInstanceResponse();
			result.setAssignee(taskInstance.getAssignee());
			result.setClaimTime(taskInstance.getClaimTime());
			result.setDeleteReason(taskInstance.getDeleteReason());
			result.setDescription(taskInstance.getDescription());
			result.setDueDate(taskInstance.getDueDate());
			result.setDurationInMillis(taskInstance.getDurationInMillis());
			result.setEndTime(taskInstance.getEndTime());
			result.setExecutionId(taskInstance.getExecutionId());
			result.setFormKey(taskInstance.getFormKey());
			result.setId(taskInstance.getId());
			result.setName(taskInstance.getName());
			result.setOwner(taskInstance.getOwner());
			result.setParentTaskId(taskInstance.getParentTaskId());
			result.setPriority(taskInstance.getPriority());
			result.setProcessDefinitionId(taskInstance.getProcessDefinitionId());
			result.setTenantId(taskInstance.getTenantId());
			result.setCategory(taskInstance.getCategory());

			result.setProcessInstanceId(taskInstance.getProcessInstanceId());

			result.setStartTime(taskInstance.getStartTime());
			result.setTaskDefinitionKey(taskInstance.getTaskDefinitionKey());
			result.setWorkTimeInMillis(taskInstance.getWorkTimeInMillis());

			C2RestResponseFactory responseFactory = new C2RestResponseFactory();
			
			if (taskInstance.getProcessVariables() != null) {
				Map<String, Object> variableMap = taskInstance
						.getProcessVariables();
				for (String name : variableMap.keySet()) {
					result.addVariable(responseFactory.createRestVariable(
							name, variableMap.get(name),
							RestVariableScope.GLOBAL, taskInstance.getId(),
							C2RestResponseFactory.VARIABLE_HISTORY_TASK, false));
				}
			}
			if (taskInstance.getTaskLocalVariables() != null) {
				Map<String, Object> variableMap = taskInstance
						.getTaskLocalVariables();
				for (String name : variableMap.keySet()) {
					result.addVariable(responseFactory.createRestVariable(
							name, variableMap.get(name),
							RestVariableScope.LOCAL, taskInstance.getId(),
							C2RestResponseFactory.VARIABLE_HISTORY_TASK, false));
				}
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
	
	@ApiOperation(value = "获取历史任务候选人或组信息", tags = "historyTask")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),@ApiResponse(code = 404, message = "操作失败，请求资源未找到"),@ApiResponse(code = 500, message = "系统内部错误")  })
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/{taskId}/identitylinks")
	public List<WfHistoricIdentityLinkResponse> getHistoryTaskIdentityLinks(@ApiParam(value = "任务id", required = true)  @PathParam("taskId") String taskId) throws Exception{
		
		try{
			if (taskId == null) {
				throw new ActivitiIllegalArgumentException(
						"The taskId cannot be null");
			}

			List<HistoricIdentityLink> identityLinks = historyService.getHistoricIdentityLinksForTask(taskId);
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
		} catch (ActivitiIllegalArgumentException e1) {
			throw new InvalidRestParamException(e1.getMessage());
		} catch (ActivitiObjectNotFoundException e2) {
			throw new ResourceNotFoundException(e2.getMessage());
		} catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		}

	}
}
