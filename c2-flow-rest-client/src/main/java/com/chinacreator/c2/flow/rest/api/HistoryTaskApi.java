package com.chinacreator.c2.flow.rest.api;

import com.chinacreator.c2.flow.rest.ApiException;
import com.chinacreator.c2.flow.rest.ApiClient;
import com.chinacreator.c2.flow.rest.Configuration;
import com.chinacreator.c2.flow.rest.Pair;

import javax.ws.rs.core.GenericType;

import java.util.Date;
import com.chinacreator.c2.flow.rest.model.PageListResponseWfHistoricTaskInstanceResponse;
import com.chinacreator.c2.flow.rest.model.WfHistoricIdentityLinkResponse;
import com.chinacreator.c2.flow.rest.model.WfHistoricTaskInstanceResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-12-27T09:47:23.486+08:00")
public class HistoryTaskApi {
  private ApiClient apiClient;

  public HistoryTaskApi() {
    this(Configuration.getDefaultApiClient());
  }

  public HistoryTaskApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * 获取历史任务列表
   * 
   * @param taskId 任务id (optional)
   * @param processInstanceId 流程实例 (optional)
   * @param processBusinessKey 业务主键 (optional)
   * @param processDefinitionKey 流程定key (optional)
   * @param processDefinitionId 流程定义id (optional)
   * @param processDefinitionName 流程定义名称 (optional)
   * @param executionId 任务执行id (optional)
   * @param taskName 任务名称 (optional)
   * @param taskNameLike 任务名称模糊匹配 (optional)
   * @param taskDescription 任务描述 (optional)
   * @param taskDescriptionLike 任务描述模糊匹配 (optional)
   * @param taskDefinitionKey 任务key (optional)
   * @param taskDeleteReason 任务删除原因 (optional)
   * @param taskDeleteReasonLike 任务删除原因模糊匹配 (optional)
   * @param taskAssignee 处理人 (optional)
   * @param taskAssigneeLike 处理人模糊匹配 (optional)
   * @param taskOwner 任务所有者 (optional)
   * @param taskOwnerLike 任务所有者模糊匹配 (optional)
   * @param taskInvolvedUser 任务参与者(处理人、候选人、所有者) (optional)
   * @param taskPriority 任务优先级 (optional)
   * @param finished 是否完成 (optional)
   * @param processFinished 任务所在流程实例是否完成 (optional)
   * @param parentTaskId 父任务 (optional)
   * @param dueDate 任务过期时间 (optional)
   * @param dueDateBefore 过期时间下区间 (optional)
   * @param dueDateAfter 过期时间上区间 (optional)
   * @param taskCreatedOn 任务创建时间 (optional)
   * @param taskCreatedBefore 创建时间下区间 (optional)
   * @param taskCreatedAfter 创建时间上区间 (optional)
   * @param taskCompletedOn 任务完成时间 (optional)
   * @param taskCompletedBefore 完成时间下区间 (optional)
   * @param taskCompletedAfter 完成时间上区间 (optional)
   * @param includeTaskLocalVariables 是否只查询包含流程环节变量任务 (optional)
   * @param includeProcessVariables 是否只查询包含流程环节变量和全局变量任务 (optional)
   * @param tenantId 租户 (optional)
   * @param tenantIdLike 租户模糊匹配 (optional)
   * @param withoutTenantId 忽略租户条件 (optional)
   * @return PageListResponseWfHistoricTaskInstanceResponse
   * @throws ApiException if fails to make API call
   */
  public PageListResponseWfHistoricTaskInstanceResponse getHistoricTaskProcessInstances(String taskId, String processInstanceId, String processBusinessKey, String processDefinitionKey, String processDefinitionId, String processDefinitionName, String executionId, String taskName, String taskNameLike, String taskDescription, String taskDescriptionLike, String taskDefinitionKey, String taskDeleteReason, String taskDeleteReasonLike, String taskAssignee, String taskAssigneeLike, String taskOwner, String taskOwnerLike, String taskInvolvedUser, Integer taskPriority, Boolean finished, Boolean processFinished, String parentTaskId, Date dueDate, Date dueDateBefore, Date dueDateAfter, Date taskCreatedOn, Date taskCreatedBefore, Date taskCreatedAfter, Date taskCompletedOn, Date taskCompletedBefore, Date taskCompletedAfter, Boolean includeTaskLocalVariables, Boolean includeProcessVariables, String tenantId, String tenantIdLike, Boolean withoutTenantId) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/v1/flow/history/tasks".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "taskId", taskId));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "processInstanceId", processInstanceId));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "processBusinessKey", processBusinessKey));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "processDefinitionKey", processDefinitionKey));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "processDefinitionId", processDefinitionId));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "processDefinitionName", processDefinitionName));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "executionId", executionId));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "taskName", taskName));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "taskNameLike", taskNameLike));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "taskDescription", taskDescription));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "taskDescriptionLike", taskDescriptionLike));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "taskDefinitionKey", taskDefinitionKey));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "taskDeleteReason", taskDeleteReason));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "taskDeleteReasonLike", taskDeleteReasonLike));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "taskAssignee", taskAssignee));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "taskAssigneeLike", taskAssigneeLike));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "taskOwner", taskOwner));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "taskOwnerLike", taskOwnerLike));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "taskInvolvedUser", taskInvolvedUser));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "taskPriority", taskPriority));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "finished", finished));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "processFinished", processFinished));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "parentTaskId", parentTaskId));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "dueDate", dueDate));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "dueDateBefore", dueDateBefore));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "dueDateAfter", dueDateAfter));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "taskCreatedOn", taskCreatedOn));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "taskCreatedBefore", taskCreatedBefore));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "taskCreatedAfter", taskCreatedAfter));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "taskCompletedOn", taskCompletedOn));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "taskCompletedBefore", taskCompletedBefore));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "taskCompletedAfter", taskCompletedAfter));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "includeTaskLocalVariables", includeTaskLocalVariables));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "includeProcessVariables", includeProcessVariables));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "tenantId", tenantId));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "tenantIdLike", tenantIdLike));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "withoutTenantId", withoutTenantId));

    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<PageListResponseWfHistoricTaskInstanceResponse> localVarReturnType = new GenericType<PageListResponseWfHistoricTaskInstanceResponse>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 获取历史任务候选人或组信息
   * 
   * @param taskId 任务id (required)
   * @return List<WfHistoricIdentityLinkResponse>
   * @throws ApiException if fails to make API call
   */
  public List<WfHistoricIdentityLinkResponse> getHistoryTaskIdentityLinks(String taskId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'taskId' is set
    if (taskId == null) {
      throw new ApiException(400, "Missing the required parameter 'taskId' when calling getHistoryTaskIdentityLinks");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/history/tasks/{taskId}/identitylinks".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "taskId" + "\\}", apiClient.escapeString(taskId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<List<WfHistoricIdentityLinkResponse>> localVarReturnType = new GenericType<List<WfHistoricIdentityLinkResponse>>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 获取历史任务信息
   * 
   * @param taskId 任务id (required)
   * @param includeTaskLocalVariables 是否包含local变量 (optional)
   * @param includeProcessVariables 是否包含global变量 (optional)
   * @return WfHistoricTaskInstanceResponse
   * @throws ApiException if fails to make API call
   */
  public WfHistoricTaskInstanceResponse getTaskInstance(String taskId, Boolean includeTaskLocalVariables, Boolean includeProcessVariables) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'taskId' is set
    if (taskId == null) {
      throw new ApiException(400, "Missing the required parameter 'taskId' when calling getTaskInstance");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/history/tasks/{taskId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "taskId" + "\\}", apiClient.escapeString(taskId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "includeTaskLocalVariables", includeTaskLocalVariables));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "includeProcessVariables", includeProcessVariables));

    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<WfHistoricTaskInstanceResponse> localVarReturnType = new GenericType<WfHistoricTaskInstanceResponse>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}
