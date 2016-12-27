package com.chinacreator.c2.flow.rest.api;

import com.chinacreator.c2.flow.rest.ApiException;
import com.chinacreator.c2.flow.rest.ApiClient;
import com.chinacreator.c2.flow.rest.Configuration;
import com.chinacreator.c2.flow.rest.Pair;

import javax.ws.rs.core.GenericType;

import com.chinacreator.c2.flow.rest.model.WfDeploymentResponse;
import com.chinacreator.c2.flow.rest.model.WfDeploymentResourceResponse;
import com.chinacreator.c2.flow.rest.model.PageListResponseWfDeploymentResponse;
import com.chinacreator.c2.flow.rest.model.WfDeploymentRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-12-27T09:47:23.486+08:00")
public class RepositoryDeploymentApi {
  private ApiClient apiClient;

  public RepositoryDeploymentApi() {
    this(Configuration.getDefaultApiClient());
  }

  public RepositoryDeploymentApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * 删除流程部署
   * 
   * @param deploymentId 部署id (required)
   * @throws ApiException if fails to make API call
   */
  public void deleteDeployment(String deploymentId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'deploymentId' is set
    if (deploymentId == null) {
      throw new ApiException(400, "Missing the required parameter 'deploymentId' when calling deleteDeployment");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/repository/deploy/{deploymentId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "deploymentId" + "\\}", apiClient.escapeString(deploymentId.toString()));

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
   * 获取部署信息)
   * 
   * @param deploymentId 部署id (required)
   * @return WfDeploymentResponse
   * @throws ApiException if fails to make API call
   */
  public WfDeploymentResponse getDeployment(String deploymentId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'deploymentId' is set
    if (deploymentId == null) {
      throw new ApiException(400, "Missing the required parameter 'deploymentId' when calling getDeployment");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/repository/deploy/{deploymentId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "deploymentId" + "\\}", apiClient.escapeString(deploymentId.toString()));

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

    GenericType<WfDeploymentResponse> localVarReturnType = new GenericType<WfDeploymentResponse>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 获取部署下的某个资源信息)
   * 
   * @param deploymentId 部署id (required)
   * @param resourceId 资源id (required)
   * @return WfDeploymentResourceResponse
   * @throws ApiException if fails to make API call
   */
  public WfDeploymentResourceResponse getDeploymentResource(String deploymentId, String resourceId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'deploymentId' is set
    if (deploymentId == null) {
      throw new ApiException(400, "Missing the required parameter 'deploymentId' when calling getDeploymentResource");
    }
    
    // verify the required parameter 'resourceId' is set
    if (resourceId == null) {
      throw new ApiException(400, "Missing the required parameter 'resourceId' when calling getDeploymentResource");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/repository/deploy/{deploymentId}/resources/{resourceId}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "deploymentId" + "\\}", apiClient.escapeString(deploymentId.toString()))
      .replaceAll("\\{" + "resourceId" + "\\}", apiClient.escapeString(resourceId.toString()));

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

    GenericType<WfDeploymentResourceResponse> localVarReturnType = new GenericType<WfDeploymentResourceResponse>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 下载部署下的资源数据)
   * 
   * @param deploymentId 部署id (required)
   * @param resourceId 资源id (required)
   * @return byte[]
   * @throws ApiException if fails to make API call
   */
  public byte[] getDeploymentResourceData(String deploymentId, String resourceId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'deploymentId' is set
    if (deploymentId == null) {
      throw new ApiException(400, "Missing the required parameter 'deploymentId' when calling getDeploymentResourceData");
    }
    
    // verify the required parameter 'resourceId' is set
    if (resourceId == null) {
      throw new ApiException(400, "Missing the required parameter 'resourceId' when calling getDeploymentResourceData");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/repository/deploy/{deploymentId}/resources/{resourceId}/resourcedata".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "deploymentId" + "\\}", apiClient.escapeString(deploymentId.toString()))
      .replaceAll("\\{" + "resourceId" + "\\}", apiClient.escapeString(resourceId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    final String[] localVarAccepts = {
      "application/octet-stream"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<byte[]> localVarReturnType = new GenericType<byte[]>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 获取部署下的资源列表)
   * 
   * @param deploymentId 部署id (required)
   * @return List<WfDeploymentResourceResponse>
   * @throws ApiException if fails to make API call
   */
  public List<WfDeploymentResourceResponse> getDeploymentResources(String deploymentId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'deploymentId' is set
    if (deploymentId == null) {
      throw new ApiException(400, "Missing the required parameter 'deploymentId' when calling getDeploymentResources");
    }
    
    // create path and map variables
    String localVarPath = "/v1/flow/repository/deploy/{deploymentId}/resources".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "deploymentId" + "\\}", apiClient.escapeString(deploymentId.toString()));

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

    GenericType<List<WfDeploymentResourceResponse>> localVarReturnType = new GenericType<List<WfDeploymentResourceResponse>>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 获取流程定义部署列表
   * 
   * @param name 部署名称 (optional)
   * @param nameLike 部署名称模糊 (optional)
   * @param category 类别 (optional)
   * @param categoryNotEquals 类别不等于 (optional)
   * @param tenantId 租户 (optional)
   * @param tenantIdLike 租户模糊 (optional)
   * @param withoutTenantId 省略租户 (optional)
   * @return PageListResponseWfDeploymentResponse
   * @throws ApiException if fails to make API call
   */
  public PageListResponseWfDeploymentResponse getDeployments(String name, String nameLike, String category, String categoryNotEquals, String tenantId, String tenantIdLike, Boolean withoutTenantId) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/v1/flow/repository/deploy".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "name", name));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "nameLike", nameLike));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "category", category));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "categoryNotEquals", categoryNotEquals));
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

    GenericType<PageListResponseWfDeploymentResponse> localVarReturnType = new GenericType<PageListResponseWfDeploymentResponse>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * 部署流程定义资源(*bpmn20.xml)
   * 
   * @param body  (optional)
   * @return WfDeploymentResponse
   * @throws ApiException if fails to make API call
   */
  public WfDeploymentResponse uploadDeployment(WfDeploymentRequest body) throws ApiException {
    Object localVarPostBody = body;
    
    // create path and map variables
    String localVarPath = "/v1/flow/repository/deploy".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    if(null!=body.getDeployResource()){
    	localVarFormParams.put("deployResource",body.getDeployResource());
    }
    
    if(null!=body.getDeployName()){
    	localVarFormParams.put("deployName",body.getDeployName());
    }
    if(null!=body.getCategory()){
    	localVarFormParams.put("category",body.getCategory());
    }
    if(null!=body.getCurrentLoginUserId()){
    	localVarFormParams.put("currentLoginUserId",body.getCurrentLoginUserId());
    }
    if(null!=body.getTenantId()){
    	localVarFormParams.put("tenantId",body.getTenantId());
    }
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "multipart/form-data"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<WfDeploymentResponse> localVarReturnType = new GenericType<WfDeploymentResponse>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}
