package com.chinacreator.c2.flow.rest.api;

import com.chinacreator.c2.flow.rest.ApiException;
import com.chinacreator.c2.flow.rest.ApiClient;
import com.chinacreator.c2.flow.rest.Configuration;
import com.chinacreator.c2.flow.rest.Pair;

import javax.ws.rs.core.GenericType;

import com.chinacreator.c2.flow.rest.model.PageListResponseWfHistoricProcessInstanceResponse;
import com.chinacreator.c2.flow.rest.model.WfHistoricProcessInstanceQueryRequest;
import com.chinacreator.c2.flow.rest.model.PageListResponseWfHistoricTaskInstanceResponse;
import com.chinacreator.c2.flow.rest.model.WfHistoricTaskInstanceQueryRequest;
import com.chinacreator.c2.flow.rest.model.PageListResponseWfProcessInstanceResponse;
import com.chinacreator.c2.flow.rest.model.WfProcessInstanceQueryRequest;
import com.chinacreator.c2.flow.rest.model.PageListResponseWfTaskResponse;
import com.chinacreator.c2.flow.rest.model.WfTaskQueryRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-12-28T16:50:41.068+08:00")
public class QueryApi {
  private ApiClient apiClient;

  public QueryApi() {
    this(Configuration.getDefaultApiClient());
  }

  public QueryApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * 查询历史流程实例列表
   * 
   * @param body 查询过滤条件 (required)
   * @return PageListResponseWfHistoricProcessInstanceResponse
   * @throws ApiException if fails to make API call
   */
  public PageListResponseWfHistoricProcessInstanceResponse queryHistoricProcessInstances(WfHistoricProcessInstanceQueryRequest body) throws ApiException {
    Object localVarPostBody = body;
    
    // verify the required parameter 'body' is set
    if (body == null) {
      throw new ApiException(400, "Missing the required parameter 'body' when calling queryHistoricProcessInstances");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/query/history/instances".replaceAll("\\{format\\}","json");

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

    GenericType<PageListResponseWfHistoricProcessInstanceResponse> localVarReturnType = new GenericType<PageListResponseWfHistoricProcessInstanceResponse>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 查询历史任务列表
   * 
   * @param body 查询过滤条件 (required)
   * @return PageListResponseWfHistoricTaskInstanceResponse
   * @throws ApiException if fails to make API call
   */
  public PageListResponseWfHistoricTaskInstanceResponse queryHistoricTaskInstances(WfHistoricTaskInstanceQueryRequest body) throws ApiException {
    Object localVarPostBody = body;
    
    // verify the required parameter 'body' is set
    if (body == null) {
      throw new ApiException(400, "Missing the required parameter 'body' when calling queryHistoricTaskInstances");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/query/history/tasks".replaceAll("\\{format\\}","json");

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

    GenericType<PageListResponseWfHistoricTaskInstanceResponse> localVarReturnType = new GenericType<PageListResponseWfHistoricTaskInstanceResponse>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 查询流程实例列表
   * 
   * @param body 查询过滤条件 (required)
   * @return PageListResponseWfProcessInstanceResponse
   * @throws ApiException if fails to make API call
   */
  public PageListResponseWfProcessInstanceResponse queryProcessInstances(WfProcessInstanceQueryRequest body) throws ApiException {
    Object localVarPostBody = body;
    
    // verify the required parameter 'body' is set
    if (body == null) {
      throw new ApiException(400, "Missing the required parameter 'body' when calling queryProcessInstances");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/query/runtime/instances".replaceAll("\\{format\\}","json");

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

    GenericType<PageListResponseWfProcessInstanceResponse> localVarReturnType = new GenericType<PageListResponseWfProcessInstanceResponse>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 查询待办列表
   * 
   * @param body 查询过滤条件 (required)
   * @return PageListResponseWfTaskResponse
   * @throws ApiException if fails to make API call
   */
  public PageListResponseWfTaskResponse queryTasks(WfTaskQueryRequest body) throws ApiException {
    Object localVarPostBody = body;
    
    // verify the required parameter 'body' is set
    if (body == null) {
      throw new ApiException(400, "Missing the required parameter 'body' when calling queryTasks");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/query/runtime/tasks".replaceAll("\\{format\\}","json");

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

    GenericType<PageListResponseWfTaskResponse> localVarReturnType = new GenericType<PageListResponseWfTaskResponse>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}
