# RepositoryDefinitionApi

All URIs are relative to *http://127.0.0.1:83/rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**executeProcessDefinitionAction**](RepositoryDefinitionApi.md#executeProcessDefinitionAction) | **PUT** /v1/flow/repository/definitions/{processDefinitionId} | 修改流程某些信息
[**getDiagramLayout**](RepositoryDefinitionApi.md#getDiagramLayout) | **GET** /v1/flow/repository/definitions/{processDefinitionId}/diagramLayout | 获取流程定义布局图
[**getModelResource**](RepositoryDefinitionApi.md#getModelResource) | **GET** /v1/flow/repository/definitions/{processDefinitionId}/model | 获取流程定义模型
[**getProcessDefinition**](RepositoryDefinitionApi.md#getProcessDefinition) | **GET** /v1/flow/repository/definitions/{processDefinitionId} | 获取单个流程信息
[**getProcessDefinitionResourceData**](RepositoryDefinitionApi.md#getProcessDefinitionResourceData) | **GET** /v1/flow/repository/definitions/{processDefinitionId}/resourcedata | 下载流程定义文件
[**getProcessDefinitions**](RepositoryDefinitionApi.md#getProcessDefinitions) | **GET** /v1/flow/repository/definitions | 流程定义列表


<a name="executeProcessDefinitionAction"></a>
# **executeProcessDefinitionAction**
> WfProcessDefinitionResponse executeProcessDefinitionAction(processDefinitionId, body)

修改流程某些信息



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RepositoryDefinitionApi;


RepositoryDefinitionApi apiInstance = new RepositoryDefinitionApi();
String processDefinitionId = "processDefinitionId_example"; // String | 流程定义id
WfProcessDefinitionActionRequest body = new WfProcessDefinitionActionRequest(); // WfProcessDefinitionActionRequest | 
try {
    WfProcessDefinitionResponse result = apiInstance.executeProcessDefinitionAction(processDefinitionId, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RepositoryDefinitionApi#executeProcessDefinitionAction");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **processDefinitionId** | **String**| 流程定义id |
 **body** | [**WfProcessDefinitionActionRequest**](WfProcessDefinitionActionRequest.md)|  | [optional]

### Return type

[**WfProcessDefinitionResponse**](WfProcessDefinitionResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getDiagramLayout"></a>
# **getDiagramLayout**
> String getDiagramLayout(processDefinitionId)

获取流程定义布局图



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RepositoryDefinitionApi;


RepositoryDefinitionApi apiInstance = new RepositoryDefinitionApi();
String processDefinitionId = "processDefinitionId_example"; // String | 流程定义id
try {
    String result = apiInstance.getDiagramLayout(processDefinitionId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RepositoryDefinitionApi#getDiagramLayout");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **processDefinitionId** | **String**| 流程定义id |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getModelResource"></a>
# **getModelResource**
> Object getModelResource(processDefinitionId)

获取流程定义模型



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RepositoryDefinitionApi;


RepositoryDefinitionApi apiInstance = new RepositoryDefinitionApi();
String processDefinitionId = "processDefinitionId_example"; // String | 流程定义id
try {
    Object result = apiInstance.getModelResource(processDefinitionId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RepositoryDefinitionApi#getModelResource");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **processDefinitionId** | **String**| 流程定义id |

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getProcessDefinition"></a>
# **getProcessDefinition**
> WfProcessDefinitionResponse getProcessDefinition(processDefinitionId)

获取单个流程信息



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RepositoryDefinitionApi;


RepositoryDefinitionApi apiInstance = new RepositoryDefinitionApi();
String processDefinitionId = "processDefinitionId_example"; // String | 流程定义id
try {
    WfProcessDefinitionResponse result = apiInstance.getProcessDefinition(processDefinitionId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RepositoryDefinitionApi#getProcessDefinition");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **processDefinitionId** | **String**| 流程定义id |

### Return type

[**WfProcessDefinitionResponse**](WfProcessDefinitionResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getProcessDefinitionResourceData"></a>
# **getProcessDefinitionResourceData**
> byte[] getProcessDefinitionResourceData(processDefinitionId)

下载流程定义文件



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RepositoryDefinitionApi;


RepositoryDefinitionApi apiInstance = new RepositoryDefinitionApi();
String processDefinitionId = "processDefinitionId_example"; // String | 流程定义id
try {
    byte[] result = apiInstance.getProcessDefinitionResourceData(processDefinitionId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RepositoryDefinitionApi#getProcessDefinitionResourceData");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **processDefinitionId** | **String**| 流程定义id |

### Return type

**byte[]**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/octet-stream

<a name="getProcessDefinitions"></a>
# **getProcessDefinitions**
> PageListResponseWfProcessDefinitionResponse getProcessDefinitions(category, categoryLike, categoryNotEquals, key, keyLike, name, nameLike, resourceName, resourceNameLike, version, suspended, latest, deploymentId)

流程定义列表



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RepositoryDefinitionApi;


RepositoryDefinitionApi apiInstance = new RepositoryDefinitionApi();
String category = "category_example"; // String | 类别
String categoryLike = "categoryLike_example"; // String | 类别模糊过滤
String categoryNotEquals = "categoryNotEquals_example"; // String | 类别不等于
String key = "key_example"; // String | 流程标识(定义key)
String keyLike = "keyLike_example"; // String | 流程标识模糊
String name = "name_example"; // String | 定义名称
String nameLike = "nameLike_example"; // String | 定义名称模湖
String resourceName = "resourceName_example"; // String | 资源名称
String resourceNameLike = "resourceNameLike_example"; // String | 资源名称模湖
Integer version = 56; // Integer | 版本
Boolean suspended = true; // Boolean | 是否暂停的
Boolean latest = true; // Boolean | 是否最新版本
String deploymentId = "deploymentId_example"; // String | 部署id
try {
    PageListResponseWfProcessDefinitionResponse result = apiInstance.getProcessDefinitions(category, categoryLike, categoryNotEquals, key, keyLike, name, nameLike, resourceName, resourceNameLike, version, suspended, latest, deploymentId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RepositoryDefinitionApi#getProcessDefinitions");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **category** | **String**| 类别 | [optional]
 **categoryLike** | **String**| 类别模糊过滤 | [optional]
 **categoryNotEquals** | **String**| 类别不等于 | [optional]
 **key** | **String**| 流程标识(定义key) | [optional]
 **keyLike** | **String**| 流程标识模糊 | [optional]
 **name** | **String**| 定义名称 | [optional]
 **nameLike** | **String**| 定义名称模湖 | [optional]
 **resourceName** | **String**| 资源名称 | [optional]
 **resourceNameLike** | **String**| 资源名称模湖 | [optional]
 **version** | **Integer**| 版本 | [optional]
 **suspended** | **Boolean**| 是否暂停的 | [optional]
 **latest** | **Boolean**| 是否最新版本 | [optional]
 **deploymentId** | **String**| 部署id | [optional]

### Return type

[**PageListResponseWfProcessDefinitionResponse**](PageListResponseWfProcessDefinitionResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

