# RepositoryDeploymentApi

All URIs are relative to *http://127.0.0.1:83/rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteDeployment**](RepositoryDeploymentApi.md#deleteDeployment) | **DELETE** /v1/flow/repository/deploy/{deploymentId} | 删除流程部署
[**getDeployment**](RepositoryDeploymentApi.md#getDeployment) | **GET** /v1/flow/repository/deploy/{deploymentId} | 获取部署信息)
[**getDeploymentResource**](RepositoryDeploymentApi.md#getDeploymentResource) | **GET** /v1/flow/repository/deploy/{deploymentId}/resources/{resourceId} | 获取部署下的某个资源信息)
[**getDeploymentResourceData**](RepositoryDeploymentApi.md#getDeploymentResourceData) | **GET** /v1/flow/repository/deploy/{deploymentId}/resources/{resourceId}/resourcedata | 下载部署下的资源数据)
[**getDeploymentResources**](RepositoryDeploymentApi.md#getDeploymentResources) | **GET** /v1/flow/repository/deploy/{deploymentId}/resources | 获取部署下的资源列表)
[**getDeployments**](RepositoryDeploymentApi.md#getDeployments) | **GET** /v1/flow/repository/deploy | 获取流程定义部署列表
[**uploadDeployment**](RepositoryDeploymentApi.md#uploadDeployment) | **POST** /v1/flow/repository/deploy | 部署流程定义资源(*bpmn20.xml)


<a name="deleteDeployment"></a>
# **deleteDeployment**
> deleteDeployment(deploymentId)

删除流程部署



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RepositoryDeploymentApi;


RepositoryDeploymentApi apiInstance = new RepositoryDeploymentApi();
String deploymentId = "deploymentId_example"; // String | 部署id
try {
    apiInstance.deleteDeployment(deploymentId);
} catch (ApiException e) {
    System.err.println("Exception when calling RepositoryDeploymentApi#deleteDeployment");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **deploymentId** | **String**| 部署id |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getDeployment"></a>
# **getDeployment**
> WfDeploymentResponse getDeployment(deploymentId)

获取部署信息)



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RepositoryDeploymentApi;


RepositoryDeploymentApi apiInstance = new RepositoryDeploymentApi();
String deploymentId = "deploymentId_example"; // String | 部署id
try {
    WfDeploymentResponse result = apiInstance.getDeployment(deploymentId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RepositoryDeploymentApi#getDeployment");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **deploymentId** | **String**| 部署id |

### Return type

[**WfDeploymentResponse**](WfDeploymentResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getDeploymentResource"></a>
# **getDeploymentResource**
> WfDeploymentResourceResponse getDeploymentResource(deploymentId, resourceId)

获取部署下的某个资源信息)



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RepositoryDeploymentApi;


RepositoryDeploymentApi apiInstance = new RepositoryDeploymentApi();
String deploymentId = "deploymentId_example"; // String | 部署id
String resourceId = "resourceId_example"; // String | 资源id
try {
    WfDeploymentResourceResponse result = apiInstance.getDeploymentResource(deploymentId, resourceId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RepositoryDeploymentApi#getDeploymentResource");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **deploymentId** | **String**| 部署id |
 **resourceId** | **String**| 资源id |

### Return type

[**WfDeploymentResourceResponse**](WfDeploymentResourceResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getDeploymentResourceData"></a>
# **getDeploymentResourceData**
> byte[] getDeploymentResourceData(deploymentId, resourceId)

下载部署下的资源数据)



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RepositoryDeploymentApi;


RepositoryDeploymentApi apiInstance = new RepositoryDeploymentApi();
String deploymentId = "deploymentId_example"; // String | 部署id
String resourceId = "resourceId_example"; // String | 资源id
try {
    byte[] result = apiInstance.getDeploymentResourceData(deploymentId, resourceId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RepositoryDeploymentApi#getDeploymentResourceData");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **deploymentId** | **String**| 部署id |
 **resourceId** | **String**| 资源id |

### Return type

**byte[]**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/octet-stream

<a name="getDeploymentResources"></a>
# **getDeploymentResources**
> List&lt;WfDeploymentResourceResponse&gt; getDeploymentResources(deploymentId)

获取部署下的资源列表)



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RepositoryDeploymentApi;


RepositoryDeploymentApi apiInstance = new RepositoryDeploymentApi();
String deploymentId = "deploymentId_example"; // String | 部署id
try {
    List<WfDeploymentResourceResponse> result = apiInstance.getDeploymentResources(deploymentId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RepositoryDeploymentApi#getDeploymentResources");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **deploymentId** | **String**| 部署id |

### Return type

[**List&lt;WfDeploymentResourceResponse&gt;**](WfDeploymentResourceResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getDeployments"></a>
# **getDeployments**
> PageListResponseWfDeploymentResponse getDeployments(name, nameLike, category, categoryNotEquals, tenantId, tenantIdLike, withoutTenantId)

获取流程定义部署列表



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RepositoryDeploymentApi;


RepositoryDeploymentApi apiInstance = new RepositoryDeploymentApi();
String name = "name_example"; // String | 部署名称
String nameLike = "nameLike_example"; // String | 部署名称模糊
String category = "category_example"; // String | 类别
String categoryNotEquals = "categoryNotEquals_example"; // String | 类别不等于
String tenantId = "tenantId_example"; // String | 租户
String tenantIdLike = "tenantIdLike_example"; // String | 租户模糊
Boolean withoutTenantId = true; // Boolean | 省略租户
try {
    PageListResponseWfDeploymentResponse result = apiInstance.getDeployments(name, nameLike, category, categoryNotEquals, tenantId, tenantIdLike, withoutTenantId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RepositoryDeploymentApi#getDeployments");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **name** | **String**| 部署名称 | [optional]
 **nameLike** | **String**| 部署名称模糊 | [optional]
 **category** | **String**| 类别 | [optional]
 **categoryNotEquals** | **String**| 类别不等于 | [optional]
 **tenantId** | **String**| 租户 | [optional]
 **tenantIdLike** | **String**| 租户模糊 | [optional]
 **withoutTenantId** | **Boolean**| 省略租户 | [optional]

### Return type

[**PageListResponseWfDeploymentResponse**](PageListResponseWfDeploymentResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="uploadDeployment"></a>
# **uploadDeployment**
> WfDeploymentResponse uploadDeployment(body)

部署流程定义资源(*bpmn20.xml)



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RepositoryDeploymentApi;


RepositoryDeploymentApi apiInstance = new RepositoryDeploymentApi();
WfDeploymentRequest body = new WfDeploymentRequest(); // WfDeploymentRequest | 
try {
    WfDeploymentResponse result = apiInstance.uploadDeployment(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RepositoryDeploymentApi#uploadDeployment");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**WfDeploymentRequest**](WfDeploymentRequest.md)|  | [optional]

### Return type

[**WfDeploymentResponse**](WfDeploymentResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

