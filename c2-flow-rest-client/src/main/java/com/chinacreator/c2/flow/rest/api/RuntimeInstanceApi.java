package com.chinacreator.c2.flow.rest.api;

import com.chinacreator.c2.flow.rest.ApiException;
import com.chinacreator.c2.flow.rest.ApiClient;
import com.chinacreator.c2.flow.rest.Configuration;
import com.chinacreator.c2.flow.rest.Pair;

import javax.ws.rs.core.GenericType;

import com.chinacreator.c2.flow.rest.model.WfProcessInstanceResponse;
import com.chinacreator.c2.flow.rest.model.WfProcessInstanceCreateRequest;
import com.chinacreator.c2.flow.rest.model.PageListResponseWfProcessInstanceResponse;
import com.chinacreator.c2.flow.rest.model.WfJumpRequest;
import com.chinacreator.c2.flow.rest.model.WfActionResult;
import com.chinacreator.c2.flow.rest.model.WfProcessInstanceActionRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-12-28T16:50:41.068+08:00")
public class RuntimeInstanceApi {
  private ApiClient apiClient;

  public RuntimeInstanceApi() {
    this(Configuration.getDefaultApiClient());
  }

  public RuntimeInstanceApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * 启动工作流实例
   * 
   * @param body 创建流程实例参数 (required)
   * @return WfProcessInstanceResponse
   * @throws ApiException if fails to make API call
   */
  public WfProcessInstanceResponse createProcessInstance(WfProcessInstanceCreateRequest body) throws ApiException {
    Object localVarPostBody = body;
    
    // verify the required parameter 'body' is set
    if (body == null) {
      throw new ApiException(400, "Missing the required parameter 'body' when calling createProcessInstance");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/runtime/instances".replaceAll("\\{format\\}","json");

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

    GenericType<WfProcessInstanceResponse> localVarReturnType = new GenericType<WfProcessInstanceResponse>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 删除工作流实例信息
   * 
   * @param processInstanceId 流程实例id (required)
   * @param deleteReason 删除原因 (optional)
   * @return Object
   * @throws ApiException if fails to make API call
   */
  public Object deleteProcessInstance(String processInstanceId, String deleteReason) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'processInstanceId' is set
    if (processInstanceId == null) {
      throw new ApiException(400, "Missing the required parameter 'processInstanceId' when calling deleteProcessInstance");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/runtime/instances/{processInstanceId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "processInstanceId" + "\\}", apiClient.escapeString(processInstanceId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

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

    GenericType<Object> localVarReturnType = new GenericType<Object>() {};
    return apiClient.invokeAPI(localVarPath, "DELETE", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 获取工作流实例信息
   * 
   * @param processInstanceId 流程实例id (required)
   * @return WfProcessInstanceResponse
   * @throws ApiException if fails to make API call
   */
  public WfProcessInstanceResponse getProcessInstance(String processInstanceId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'processInstanceId' is set
    if (processInstanceId == null) {
      throw new ApiException(400, "Missing the required parameter 'processInstanceId' when calling getProcessInstance");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/runtime/instances/{processInstanceId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "processInstanceId" + "\\}", apiClient.escapeString(processInstanceId.toString()));

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

    GenericType<WfProcessInstanceResponse> localVarReturnType = new GenericType<WfProcessInstanceResponse>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 获取流程实例图
   * 获取流程实例图片，红色框标记为当前环节
   * @param processInstanceId 流程实例id (required)
   * @throws ApiException if fails to make API call
   */
  public void getProcessInstanceDiagram(String processInstanceId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'processInstanceId' is set
    if (processInstanceId == null) {
      throw new ApiException(400, "Missing the required parameter 'processInstanceId' when calling getProcessInstanceDiagram");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/runtime/instances/{processInstanceId}/diagram".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "processInstanceId" + "\\}", apiClient.escapeString(processInstanceId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    final String[] localVarAccepts = {
      "image/png"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };


    apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, null);
  }
  /**
   * 获取流程实例的流程定义布局信息
   * 
   * @param processInstanceId 流程实例id (required)
   * @return String
   * @throws ApiException if fails to make API call
   */
  public String getProcessInstanceDiagramLayout(String processInstanceId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'processInstanceId' is set
    if (processInstanceId == null) {
      throw new ApiException(400, "Missing the required parameter 'processInstanceId' when calling getProcessInstanceDiagramLayout");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/runtime/instances/{processInstanceId}/diagramLayout".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "processInstanceId" + "\\}", apiClient.escapeString(processInstanceId.toString()));

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

    GenericType<String> localVarReturnType = new GenericType<String>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 获取流程实例高亮信息
   * 
   * @param processInstanceId 流程实例id (required)
   * @return String
   * @throws ApiException if fails to make API call
   */
  public String getProcessInstanceHighlighted(String processInstanceId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'processInstanceId' is set
    if (processInstanceId == null) {
      throw new ApiException(400, "Missing the required parameter 'processInstanceId' when calling getProcessInstanceHighlighted");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/runtime/instances/{processInstanceId}/highlighted".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "processInstanceId" + "\\}", apiClient.escapeString(processInstanceId.toString()));

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

    GenericType<String> localVarReturnType = new GenericType<String>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 工作流实例列表
   * 
   * @param id 流程实例id (optional)
   * @param processDefinitionKey 流程定义key (optional)
   * @param processDefinitionId 流程定义id (optional)
   * @param businessKey 业务记录主键 (optional)
   * @param involvedUser 流程参与者(处理人、候选人、所有者) (optional)
   * @param suspended 是否挂起 (optional)
   * @param superProcessInstanceId 挂起的流程实例id (optional)
   * @param subProcessInstanceId 子流程实例id (optional)
   * @param excludeSubprocesses 是否排除子流程 (optional)
   * @param includeProcessVariables 是否查询流程变量 (optional)
   * @param tenantId 租户Id (optional)
   * @param tenantIdLike  (optional)
   * @param withoutTenantId 是否禁用租户过滤 (optional)
   * @param order asc/desc排序 (optional)
   * @param sort 排序字段 (optional)
   * @param start 请求开始行 (optional)
   * @param size 请求记录大小 (optional)
   * @return PageListResponseWfProcessInstanceResponse
   * @throws ApiException if fails to make API call
   */
  public PageListResponseWfProcessInstanceResponse getProcessInstances(String id, String processDefinitionKey, String processDefinitionId, String businessKey, String involvedUser, Boolean suspended, String superProcessInstanceId, String subProcessInstanceId, Boolean excludeSubprocesses, Boolean includeProcessVariables, String tenantId, String tenantIdLike, Boolean withoutTenantId, String order, Integer sort, Integer start, Integer size) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/v1/flow/runtime/instances".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "id", id));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "processDefinitionKey", processDefinitionKey));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "processDefinitionId", processDefinitionId));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "businessKey", businessKey));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "involvedUser", involvedUser));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "suspended", suspended));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "superProcessInstanceId", superProcessInstanceId));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "subProcessInstanceId", subProcessInstanceId));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "excludeSubprocesses", excludeSubprocesses));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "includeProcessVariables", includeProcessVariables));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "tenantId", tenantId));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "tenantIdLike", tenantIdLike));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "withoutTenantId", withoutTenantId));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "order", order));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "sort", sort));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "start", start));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "size", size));

    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<PageListResponseWfProcessInstanceResponse> localVarReturnType = new GenericType<PageListResponseWfProcessInstanceResponse>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 自由流
   * 
   * @param processInstanceId 流程实例id (required)
   * @param body 自由流参数 (required)
   * @return WfActionResult
   * @throws ApiException if fails to make API call
   */
  public WfActionResult goAnyWhere(String processInstanceId, WfJumpRequest body) throws ApiException {
    Object localVarPostBody = body;
    
    // verify the required parameter 'processInstanceId' is set
    if (processInstanceId == null) {
      throw new ApiException(400, "Missing the required parameter 'processInstanceId' when calling goAnyWhere");
    }
    
    // verify the required parameter 'body' is set
    if (body == null) {
      throw new ApiException(400, "Missing the required parameter 'body' when calling goAnyWhere");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/runtime/instances/{processInstanceId}/goAnyWhere".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "processInstanceId" + "\\}", apiClient.escapeString(processInstanceId.toString()));

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
   * 激活或挂起工作流实例
   * 
   * @param processInstanceId 流程实例id (required)
   * @param body suspend或activate实例 (required)
   * @return WfProcessInstanceResponse
   * @throws ApiException if fails to make API call
   */
  public WfProcessInstanceResponse performProcessInstanceAction(String processInstanceId, WfProcessInstanceActionRequest body) throws ApiException {
    Object localVarPostBody = body;
    
    // verify the required parameter 'processInstanceId' is set
    if (processInstanceId == null) {
      throw new ApiException(400, "Missing the required parameter 'processInstanceId' when calling performProcessInstanceAction");
    }
    
    // verify the required parameter 'body' is set
    if (body == null) {
      throw new ApiException(400, "Missing the required parameter 'body' when calling performProcessInstanceAction");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/runtime/instances/{processInstanceId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "processInstanceId" + "\\}", apiClient.escapeString(processInstanceId.toString()));

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

    GenericType<WfProcessInstanceResponse> localVarReturnType = new GenericType<WfProcessInstanceResponse>() {};
    return apiClient.invokeAPI(localVarPath, "PUT", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}
