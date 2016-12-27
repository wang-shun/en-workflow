package com.chinacreator.c2.flow.rest.api;

import com.chinacreator.c2.flow.rest.ApiException;
import com.chinacreator.c2.flow.rest.ApiClient;
import com.chinacreator.c2.flow.rest.Configuration;
import com.chinacreator.c2.flow.rest.Pair;

import javax.ws.rs.core.GenericType;

import com.chinacreator.c2.flow.rest.model.WfCommentResponse;
import com.chinacreator.c2.flow.rest.model.WfRestVariable;
import com.chinacreator.c2.flow.rest.model.WfTaskActionRequest;
import com.chinacreator.c2.flow.rest.model.WfActionResult;
import com.chinacreator.c2.flow.rest.model.WfTaskResponse;
import com.chinacreator.c2.flow.rest.model.PageListResponseWfTaskResponse;
import java.util.Date;
import com.chinacreator.c2.flow.rest.model.WfTaskRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-12-27T09:47:23.486+08:00")
public class RuntimeTaskApi {
  private ApiClient apiClient;

  public RuntimeTaskApi() {
    this(Configuration.getDefaultApiClient());
  }

  public RuntimeTaskApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * 添加任务意见
   * 
   * @param taskId 任务实例id (required)
   * @param body 竟见数据 (required)
   * @return WfCommentResponse
   * @throws ApiException if fails to make API call
   */
  public WfCommentResponse createComment(String taskId, WfCommentResponse body) throws ApiException {
    Object localVarPostBody = body;
    
    // verify the required parameter 'taskId' is set
    if (taskId == null) {
      throw new ApiException(400, "Missing the required parameter 'taskId' when calling createComment");
    }
    
    // verify the required parameter 'body' is set
    if (body == null) {
      throw new ApiException(400, "Missing the required parameter 'body' when calling createComment");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/runtime/tasks/{taskId}/comments".replaceAll("\\{format\\}","json")
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

    GenericType<WfCommentResponse> localVarReturnType = new GenericType<WfCommentResponse>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 批量添加任务流程变量
   * 
   * @param taskId 任务实例id (required)
   * @param body 变量集合 (required)
   * @return List<WfRestVariable>
   * @throws ApiException if fails to make API call
   */
  public List<WfRestVariable> createTaskVariable(String taskId, List<WfRestVariable> body) throws ApiException {
    Object localVarPostBody = body;
    
    // verify the required parameter 'taskId' is set
    if (taskId == null) {
      throw new ApiException(400, "Missing the required parameter 'taskId' when calling createTaskVariable");
    }
    
    // verify the required parameter 'body' is set
    if (body == null) {
      throw new ApiException(400, "Missing the required parameter 'body' when calling createTaskVariable");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/runtime/tasks/{taskId}/variables".replaceAll("\\{format\\}","json")
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

    GenericType<List<WfRestVariable>> localVarReturnType = new GenericType<List<WfRestVariable>>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 批量删除任务流程变量
   * 
   * @param taskId 任务实例id (required)
   * @throws ApiException if fails to make API call
   */
  public void deleteAllLocalTaskVariables(String taskId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'taskId' is set
    if (taskId == null) {
      throw new ApiException(400, "Missing the required parameter 'taskId' when calling deleteAllLocalTaskVariables");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/runtime/tasks/{taskId}/variables".replaceAll("\\{format\\}","json")
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


    apiClient.invokeAPI(localVarPath, "DELETE", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, null);
  }
  /**
   * 删了某任务下某条意见
   * 
   * @param taskId 任务实例id (required)
   * @param commentId 意见id (required)
   * @throws ApiException if fails to make API call
   */
  public void deleteComment(String taskId, String commentId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'taskId' is set
    if (taskId == null) {
      throw new ApiException(400, "Missing the required parameter 'taskId' when calling deleteComment");
    }
    
    // verify the required parameter 'commentId' is set
    if (commentId == null) {
      throw new ApiException(400, "Missing the required parameter 'commentId' when calling deleteComment");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/runtime/tasks/{taskId}/comments/{commentId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "taskId" + "\\}", apiClient.escapeString(taskId.toString()))
      .replaceAll("\\{" + "commentId" + "\\}", apiClient.escapeString(commentId.toString()));

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


    apiClient.invokeAPI(localVarPath, "DELETE", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, null);
  }
  /**
   * 删除任务
   * 
   * @param taskId 任务实例id (required)
   * @param cascadeHistory 是否删除历史任务实例 (optional)
   * @param deleteReason 删除原因 (optional)
   * @throws ApiException if fails to make API call
   */
  public void deleteTask(String taskId, Boolean cascadeHistory, String deleteReason) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'taskId' is set
    if (taskId == null) {
      throw new ApiException(400, "Missing the required parameter 'taskId' when calling deleteTask");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/runtime/tasks/{taskId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "taskId" + "\\}", apiClient.escapeString(taskId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "cascadeHistory", cascadeHistory));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "deleteReason", deleteReason));

    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };


    apiClient.invokeAPI(localVarPath, "DELETE", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, null);
  }
  /**
   * 处理任务
   * 处理任务：签收(CLAIM)、签收并完成(CLAIM_COMPLETE)、完成(COMPLETE)、委托代理(DELEGATE)、回绝委托代理(RESOLVE)、退回任务(REJECT)
   * @param taskId 任务id (required)
   * @param body 操作入参 (required)
   * @return WfActionResult
   * @throws ApiException if fails to make API call
   */
  public WfActionResult executeTaskAction(String taskId, WfTaskActionRequest body) throws ApiException {
    Object localVarPostBody = body;
    
    // verify the required parameter 'taskId' is set
    if (taskId == null) {
      throw new ApiException(400, "Missing the required parameter 'taskId' when calling executeTaskAction");
    }
    
    // verify the required parameter 'body' is set
    if (body == null) {
      throw new ApiException(400, "Missing the required parameter 'body' when calling executeTaskAction");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/runtime/tasks/{taskId}".replaceAll("\\{format\\}","json")
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

    GenericType<WfActionResult> localVarReturnType = new GenericType<WfActionResult>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 获取某任务下某条意见详细
   * 
   * @param taskId 任务实例id (required)
   * @param commentId 意见id (required)
   * @return WfCommentResponse
   * @throws ApiException if fails to make API call
   */
  public WfCommentResponse getComment(String taskId, String commentId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'taskId' is set
    if (taskId == null) {
      throw new ApiException(400, "Missing the required parameter 'taskId' when calling getComment");
    }
    
    // verify the required parameter 'commentId' is set
    if (commentId == null) {
      throw new ApiException(400, "Missing the required parameter 'commentId' when calling getComment");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/runtime/tasks/{taskId}/comments/{commentId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "taskId" + "\\}", apiClient.escapeString(taskId.toString()))
      .replaceAll("\\{" + "commentId" + "\\}", apiClient.escapeString(commentId.toString()));

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

    GenericType<WfCommentResponse> localVarReturnType = new GenericType<WfCommentResponse>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 获取某任务意见列表
   * 
   * @param taskId 任务实例id (required)
   * @return List<WfCommentResponse>
   * @throws ApiException if fails to make API call
   */
  public List<WfCommentResponse> getComments(String taskId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'taskId' is set
    if (taskId == null) {
      throw new ApiException(400, "Missing the required parameter 'taskId' when calling getComments");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/runtime/tasks/{taskId}/comments".replaceAll("\\{format\\}","json")
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

    GenericType<List<WfCommentResponse>> localVarReturnType = new GenericType<List<WfCommentResponse>>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 获取任务信息
   * 
   * @param taskId 任务id (required)
   * @param includeTaskLocalVariables 是否包含local变量 (optional)
   * @param includeProcessVariables 是否包含global变量 (optional)
   * @return WfTaskResponse
   * @throws ApiException if fails to make API call
   */
  public WfTaskResponse getTask(String taskId, Boolean includeTaskLocalVariables, Boolean includeProcessVariables) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'taskId' is set
    if (taskId == null) {
      throw new ApiException(400, "Missing the required parameter 'taskId' when calling getTask");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/runtime/tasks/{taskId}".replaceAll("\\{format\\}","json")
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

    GenericType<WfTaskResponse> localVarReturnType = new GenericType<WfTaskResponse>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 待办列表
   * 
   * @param name 任务名称 (optional)
   * @param nameLike 任务名称模糊匹配 (optional)
   * @param description 任务描述 (optional)
   * @param descriptionLike 任务描述模糊匹配 (optional)
   * @param priority 任务优先级 (optional)
   * @param minimumPriority 任务最小优先级 (optional)
   * @param maximumPriority 任务最大优先级 (optional)
   * @param assignee 任务处理人 (optional)
   * @param owner 任务所有者 (optional)
   * @param unassigned 是否未分配 (optional)
   * @param delegationState 代理状态 (optional)
   * @param candidateUser 任务候选人 (optional)
   * @param involvedUser 流程参与者(处理人、候选人、所有者) (optional)
   * @param candidateGroup 任务候选组 (optional)
   * @param processDefinitionKey 任务所属流程定义key (optional)
   * @param processDefinitionKeyLike 流程定义key模糊匹配 (optional)
   * @param processDefinitionName 任务所属流程定义名称 (optional)
   * @param processDefinitionNameLike 流程定义名称模糊匹配 (optional)
   * @param processInstanceId 任务所属流程实例id (optional)
   * @param processInstanceBusinessKey 任务所属业务id (optional)
   * @param executionId 任务执行id (optional)
   * @param createdOn 任务创建时间 (optional)
   * @param createdBefore 创建时间下区间 (optional)
   * @param createdAfter 创建时间上区间 (optional)
   * @param excludeSubTasks 是否排除有子任务 (optional)
   * @param taskDefinitionKey 任务key (optional)
   * @param taskDefinitionKeyLike 任务key模糊匹配 (optional)
   * @param dueDate 任务处理时间 (optional)
   * @param dueBefore 任务处理时间下区间 (optional)
   * @param dueAfter 任务处理时间上区间 (optional)
   * @param active 任务是否活动的 (optional)
   * @param includeTaskLocalVariables 是否只查询包含流程环节变量任务 (optional)
   * @param includeProcessVariables 是否只查询包含流程环节变量和全局变量任务 (optional)
   * @param tenantId 租户 (optional)
   * @param tenantIdLike 租户模糊匹配 (optional)
   * @param withoutTenantId 忽略租户条件 (optional)
   * @return PageListResponseWfTaskResponse
   * @throws ApiException if fails to make API call
   */
  public PageListResponseWfTaskResponse getTasks(String name, String nameLike, String description, String descriptionLike, Integer priority, Integer minimumPriority, Integer maximumPriority, String assignee, String owner, Boolean unassigned, String delegationState, String candidateUser, String involvedUser, String candidateGroup, String processDefinitionKey, String processDefinitionKeyLike, String processDefinitionName, String processDefinitionNameLike, String processInstanceId, String processInstanceBusinessKey, String executionId, Date createdOn, Date createdBefore, Date createdAfter, Boolean excludeSubTasks, String taskDefinitionKey, String taskDefinitionKeyLike, Date dueDate, Date dueBefore, Date dueAfter, Boolean active, Boolean includeTaskLocalVariables, Boolean includeProcessVariables, String tenantId, String tenantIdLike, Boolean withoutTenantId) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/v1/flow/runtime/tasks".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "name", name));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "nameLike", nameLike));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "description", description));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "descriptionLike", descriptionLike));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "priority", priority));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "minimumPriority", minimumPriority));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "maximumPriority", maximumPriority));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "assignee", assignee));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "owner", owner));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "unassigned", unassigned));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "delegationState", delegationState));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "candidateUser", candidateUser));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "involvedUser", involvedUser));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "candidateGroup", candidateGroup));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "processDefinitionKey", processDefinitionKey));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "processDefinitionKeyLike", processDefinitionKeyLike));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "processDefinitionName", processDefinitionName));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "processDefinitionNameLike", processDefinitionNameLike));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "processInstanceId", processInstanceId));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "processInstanceBusinessKey", processInstanceBusinessKey));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "executionId", executionId));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "createdOn", createdOn));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "createdBefore", createdBefore));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "createdAfter", createdAfter));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "excludeSubTasks", excludeSubTasks));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "taskDefinitionKey", taskDefinitionKey));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "taskDefinitionKeyLike", taskDefinitionKeyLike));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "dueDate", dueDate));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "dueBefore", dueBefore));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "dueAfter", dueAfter));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "active", active));
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

    GenericType<PageListResponseWfTaskResponse> localVarReturnType = new GenericType<PageListResponseWfTaskResponse>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 获取某任务某流程变量
   * 
   * @param taskId 任务实例id (required)
   * @param variableName 流程变量名 (required)
   * @param scope 作用域(local/global) (required)
   * @return WfRestVariable
   * @throws ApiException if fails to make API call
   */
  public WfRestVariable getVariable(String taskId, String variableName, String scope) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'taskId' is set
    if (taskId == null) {
      throw new ApiException(400, "Missing the required parameter 'taskId' when calling getVariable");
    }
    
    // verify the required parameter 'variableName' is set
    if (variableName == null) {
      throw new ApiException(400, "Missing the required parameter 'variableName' when calling getVariable");
    }
    
    // verify the required parameter 'scope' is set
    if (scope == null) {
      throw new ApiException(400, "Missing the required parameter 'scope' when calling getVariable");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/runtime/tasks/{taskId}/variables/{variableName}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "taskId" + "\\}", apiClient.escapeString(taskId.toString()))
      .replaceAll("\\{" + "variableName" + "\\}", apiClient.escapeString(variableName.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "scope", scope));

    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<WfRestVariable> localVarReturnType = new GenericType<WfRestVariable>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 获取某任务流程变量集合
   * 
   * @param taskId 任务实例id (required)
   * @param scope 作用域(local/global) (required)
   * @return List<WfRestVariable>
   * @throws ApiException if fails to make API call
   */
  public List<WfRestVariable> getVariables(String taskId, String scope) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'taskId' is set
    if (taskId == null) {
      throw new ApiException(400, "Missing the required parameter 'taskId' when calling getVariables");
    }
    
    // verify the required parameter 'scope' is set
    if (scope == null) {
      throw new ApiException(400, "Missing the required parameter 'scope' when calling getVariables");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/runtime/tasks/{taskId}/variables".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "taskId" + "\\}", apiClient.escapeString(taskId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "scope", scope));

    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<List<WfRestVariable>> localVarReturnType = new GenericType<List<WfRestVariable>>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 修改任务
   * 
   * @param taskId  (required)
   * @param body  (optional)
   * @return WfTaskResponse
   * @throws ApiException if fails to make API call
   */
  public WfTaskResponse updateTask(String taskId, WfTaskRequest body) throws ApiException {
    Object localVarPostBody = body;
    
    // verify the required parameter 'taskId' is set
    if (taskId == null) {
      throw new ApiException(400, "Missing the required parameter 'taskId' when calling updateTask");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/runtime/tasks/{taskId}".replaceAll("\\{format\\}","json")
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

    GenericType<WfTaskResponse> localVarReturnType = new GenericType<WfTaskResponse>() {};
    return apiClient.invokeAPI(localVarPath, "PUT", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}
