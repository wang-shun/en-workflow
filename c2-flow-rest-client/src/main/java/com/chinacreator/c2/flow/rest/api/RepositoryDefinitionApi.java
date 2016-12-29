package com.chinacreator.c2.flow.rest.api;

import com.chinacreator.c2.flow.rest.ApiException;
import com.chinacreator.c2.flow.rest.ApiClient;
import com.chinacreator.c2.flow.rest.Configuration;
import com.chinacreator.c2.flow.rest.Pair;

import javax.ws.rs.core.GenericType;

import com.chinacreator.c2.flow.rest.model.WfProcessDefinitionActionRequest;
import com.chinacreator.c2.flow.rest.model.WfProcessDefinitionResponse;
import com.chinacreator.c2.flow.rest.model.PageListResponseWfProcessDefinitionResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-12-28T16:50:41.068+08:00")
public class RepositoryDefinitionApi {
  private ApiClient apiClient;

  public RepositoryDefinitionApi() {
    this(Configuration.getDefaultApiClient());
  }

  public RepositoryDefinitionApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * 修改流程某些信息
   * 
   * @param processDefinitionId 流程定义id (required)
   * @param body  (optional)
   * @return WfProcessDefinitionResponse
   * @throws ApiException if fails to make API call
   */
  public WfProcessDefinitionResponse executeProcessDefinitionAction(String processDefinitionId, WfProcessDefinitionActionRequest body) throws ApiException {
    Object localVarPostBody = body;
    
    // verify the required parameter 'processDefinitionId' is set
    if (processDefinitionId == null) {
      throw new ApiException(400, "Missing the required parameter 'processDefinitionId' when calling executeProcessDefinitionAction");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/repository/definitions/{processDefinitionId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "processDefinitionId" + "\\}", apiClient.escapeString(processDefinitionId.toString()));

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

    GenericType<WfProcessDefinitionResponse> localVarReturnType = new GenericType<WfProcessDefinitionResponse>() {};
    return apiClient.invokeAPI(localVarPath, "PUT", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 获取流程定义布局图
   * 
   * @param processDefinitionId 流程定义id (required)
   * @return String
   * @throws ApiException if fails to make API call
   */
  public String getDiagramLayout(String processDefinitionId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'processDefinitionId' is set
    if (processDefinitionId == null) {
      throw new ApiException(400, "Missing the required parameter 'processDefinitionId' when calling getDiagramLayout");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/repository/definitions/{processDefinitionId}/diagramLayout".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "processDefinitionId" + "\\}", apiClient.escapeString(processDefinitionId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<String> localVarReturnType = new GenericType<String>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 获取流程定义模型
   * 
   * @param processDefinitionId 流程定义id (required)
   * @return Object
   * @throws ApiException if fails to make API call
   */
  public Object getModelResource(String processDefinitionId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'processDefinitionId' is set
    if (processDefinitionId == null) {
      throw new ApiException(400, "Missing the required parameter 'processDefinitionId' when calling getModelResource");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/repository/definitions/{processDefinitionId}/model".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "processDefinitionId" + "\\}", apiClient.escapeString(processDefinitionId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<Object> localVarReturnType = new GenericType<Object>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 获取单个流程信息
   * 
   * @param processDefinitionId 流程定义id (required)
   * @return WfProcessDefinitionResponse
   * @throws ApiException if fails to make API call
   */
  public WfProcessDefinitionResponse getProcessDefinition(String processDefinitionId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'processDefinitionId' is set
    if (processDefinitionId == null) {
      throw new ApiException(400, "Missing the required parameter 'processDefinitionId' when calling getProcessDefinition");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/repository/definitions/{processDefinitionId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "processDefinitionId" + "\\}", apiClient.escapeString(processDefinitionId.toString()));

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

    GenericType<WfProcessDefinitionResponse> localVarReturnType = new GenericType<WfProcessDefinitionResponse>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 下载流程定义文件
   * 
   * @param processDefinitionId 流程定义id (required)
   * @return byte[]
   * @throws ApiException if fails to make API call
   */
  public byte[] getProcessDefinitionResourceData(String processDefinitionId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'processDefinitionId' is set
    if (processDefinitionId == null) {
      throw new ApiException(400, "Missing the required parameter 'processDefinitionId' when calling getProcessDefinitionResourceData");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/repository/definitions/{processDefinitionId}/resourcedata".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "processDefinitionId" + "\\}", apiClient.escapeString(processDefinitionId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    final String[] localVarAccepts = {
      "application/octet-stream"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<byte[]> localVarReturnType = new GenericType<byte[]>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 流程定义列表
   * 
   * @param category 类别 (optional)
   * @param categoryLike 类别模糊过滤 (optional)
   * @param categoryNotEquals 类别不等于 (optional)
   * @param key 流程标识(定义key) (optional)
   * @param keyLike 流程标识模糊 (optional)
   * @param name 定义名称 (optional)
   * @param nameLike 定义名称模湖 (optional)
   * @param resourceName 资源名称 (optional)
   * @param resourceNameLike 资源名称模湖 (optional)
   * @param version 版本 (optional)
   * @param suspended 是否暂停的 (optional)
   * @param latest 是否最新版本 (optional)
   * @param deploymentId 部署id (optional)
   * @return PageListResponseWfProcessDefinitionResponse
   * @throws ApiException if fails to make API call
   */
  public PageListResponseWfProcessDefinitionResponse getProcessDefinitions(String category, String categoryLike, String categoryNotEquals, String key, String keyLike, String name, String nameLike, String resourceName, String resourceNameLike, Integer version, Boolean suspended, Boolean latest, String deploymentId) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/v1/flow/repository/definitions".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "category", category));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "categoryLike", categoryLike));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "categoryNotEquals", categoryNotEquals));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "key", key));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "keyLike", keyLike));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "name", name));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "nameLike", nameLike));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "resourceName", resourceName));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "resourceNameLike", resourceNameLike));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "version", version));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "suspended", suspended));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "latest", latest));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "deploymentId", deploymentId));

    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<PageListResponseWfProcessDefinitionResponse> localVarReturnType = new GenericType<PageListResponseWfProcessDefinitionResponse>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}
