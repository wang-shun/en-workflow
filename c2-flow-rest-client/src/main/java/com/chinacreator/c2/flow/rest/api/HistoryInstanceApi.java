package com.chinacreator.c2.flow.rest.api;

import com.chinacreator.c2.flow.rest.ApiException;
import com.chinacreator.c2.flow.rest.ApiClient;
import com.chinacreator.c2.flow.rest.Configuration;
import com.chinacreator.c2.flow.rest.Pair;

import javax.ws.rs.core.GenericType;

import com.chinacreator.c2.flow.rest.model.WfHistoricIdentityLinkResponse;
import com.chinacreator.c2.flow.rest.model.PageListResponseWfHistoricProcessInstanceResponse;
import java.util.Date;
import com.chinacreator.c2.flow.rest.model.WfCommentResponse;
import com.chinacreator.c2.flow.rest.model.WfHistoricProcessInstanceResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-12-28T13:50:28.213+08:00")
public class HistoryInstanceApi {
  private ApiClient apiClient;

  public HistoryInstanceApi() {
    this(Configuration.getDefaultApiClient());
  }

  public HistoryInstanceApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * 获取历史流程实例候选人和组信息
   * 
   * @param processInstanceId 流程实例id (required)
   * @return List<WfHistoricIdentityLinkResponse>
   * @throws ApiException if fails to make API call
   */
  public List<WfHistoricIdentityLinkResponse> getHistoricInstanceIdentityLinks(String processInstanceId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'processInstanceId' is set
    if (processInstanceId == null) {
      throw new ApiException(400, "Missing the required parameter 'processInstanceId' when calling getHistoricInstanceIdentityLinks");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/history/instances/{processInstanceId}/identitylinks".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "processInstanceId" + "\\}", apiClient.escapeString(processInstanceId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    final String[] localVarAccepts = {
      
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<List<WfHistoricIdentityLinkResponse>> localVarReturnType = new GenericType<List<WfHistoricIdentityLinkResponse>>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 获取历史工作流实例列表
   * 
   * @param processInstanceId 流程实例id (optional)
   * @param processDefinitionKey 流程定义key (optional)
   * @param processDefinitionId 流程定义id (optional)
   * @param businessKey 业务主键 (optional)
   * @param involvedUser 流程参与者(处理人、候选人、所有者) (optional)
   * @param finished 是否完成 (optional)
   * @param superProcessInstanceId 父流程实例id (optional)
   * @param excludeSubprocesses 排除拥有子流的 (optional)
   * @param finishedBefore 完成时间下区间 (optional)
   * @param finishedAfter 完成时间上区间 (optional)
   * @param startedBefore 开始时间下区间 (optional)
   * @param startedAfter 开始时间上区间 (optional)
   * @param startedBy 流程起动者 (optional)
   * @param includeProcessVariables 是否包含流程变量 (optional)
   * @param tenantId 租户 (optional)
   * @param tenantIdLike 租户模糊匹配，例:%abc% (optional)
   * @param withoutTenantId 是否启用租户过滤 (optional)
   * @return PageListResponseWfHistoricProcessInstanceResponse
   * @throws ApiException if fails to make API call
   */
  public PageListResponseWfHistoricProcessInstanceResponse getHistoricProcessInstances(String processInstanceId, String processDefinitionKey, String processDefinitionId, String businessKey, String involvedUser, Boolean finished, String superProcessInstanceId, Boolean excludeSubprocesses, Date finishedBefore, Date finishedAfter, Date startedBefore, Date startedAfter, String startedBy, Boolean includeProcessVariables, String tenantId, String tenantIdLike, Boolean withoutTenantId) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/v1/flow/history/instances".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "processInstanceId", processInstanceId));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "processDefinitionKey", processDefinitionKey));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "processDefinitionId", processDefinitionId));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "businessKey", businessKey));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "involvedUser", involvedUser));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "finished", finished));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "superProcessInstanceId", superProcessInstanceId));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "excludeSubprocesses", excludeSubprocesses));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "finishedBefore", finishedBefore));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "finishedAfter", finishedAfter));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "startedBefore", startedBefore));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "startedAfter", startedAfter));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "startedBy", startedBy));
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

    GenericType<PageListResponseWfHistoricProcessInstanceResponse> localVarReturnType = new GenericType<PageListResponseWfHistoricProcessInstanceResponse>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 获取历史流程实例评论意见信息
   * 
   * @param processInstanceId 流程实例id (required)
   * @return List<WfCommentResponse>
   * @throws ApiException if fails to make API call
   */
  public List<WfCommentResponse> getHistoryInstanceComments(String processInstanceId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'processInstanceId' is set
    if (processInstanceId == null) {
      throw new ApiException(400, "Missing the required parameter 'processInstanceId' when calling getHistoryInstanceComments");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/history/instances/{processInstanceId}/comments".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "processInstanceId" + "\\}", apiClient.escapeString(processInstanceId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    final String[] localVarAccepts = {
      
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<List<WfCommentResponse>> localVarReturnType = new GenericType<List<WfCommentResponse>>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 获取历史流程实例信息
   * 
   * @param processInstanceId 流程实例id (required)
   * @return WfHistoricProcessInstanceResponse
   * @throws ApiException if fails to make API call
   */
  public WfHistoricProcessInstanceResponse getHistoryProcessInstance(String processInstanceId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'processInstanceId' is set
    if (processInstanceId == null) {
      throw new ApiException(400, "Missing the required parameter 'processInstanceId' when calling getHistoryProcessInstance");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/history/instances/{processInstanceId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "processInstanceId" + "\\}", apiClient.escapeString(processInstanceId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    final String[] localVarAccepts = {
      
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<WfHistoricProcessInstanceResponse> localVarReturnType = new GenericType<WfHistoricProcessInstanceResponse>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 获取流程实例图
   * 
   * @param processInstanceId 流程实例id (required)
   * @throws ApiException if fails to make API call
   */
  public void getHistoryProcessInstanceDiagram(String processInstanceId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'processInstanceId' is set
    if (processInstanceId == null) {
      throw new ApiException(400, "Missing the required parameter 'processInstanceId' when calling getHistoryProcessInstanceDiagram");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/history/instances/{processInstanceId}/diagram".replaceAll("\\{format\\}","json")
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
}
