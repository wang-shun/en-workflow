# RuntimeInstanceApi

All URIs are relative to *http://127.0.0.1:83/rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createProcessInstance**](RuntimeInstanceApi.md#createProcessInstance) | **POST** /v1/flow/runtime/instances | 启动工作流实例
[**deleteProcessInstance**](RuntimeInstanceApi.md#deleteProcessInstance) | **DELETE** /v1/flow/runtime/instances/{processInstanceId} | 删除工作流实例信息
[**getProcessInstance**](RuntimeInstanceApi.md#getProcessInstance) | **GET** /v1/flow/runtime/instances/{processInstanceId} | 获取工作流实例信息
[**getProcessInstanceDiagram**](RuntimeInstanceApi.md#getProcessInstanceDiagram) | **GET** /v1/flow/runtime/instances/{processInstanceId}/diagram | 获取流程实例图
[**getProcessInstanceDiagramLayout**](RuntimeInstanceApi.md#getProcessInstanceDiagramLayout) | **GET** /v1/flow/runtime/instances/{processInstanceId}/diagramLayout | 获取流程实例的流程定义布局信息
[**getProcessInstanceHighlighted**](RuntimeInstanceApi.md#getProcessInstanceHighlighted) | **GET** /v1/flow/runtime/instances/{processInstanceId}/highlighted | 获取流程实例高亮信息
[**getProcessInstances**](RuntimeInstanceApi.md#getProcessInstances) | **GET** /v1/flow/runtime/instances | 工作流实例列表
[**goAnyWhere**](RuntimeInstanceApi.md#goAnyWhere) | **POST** /v1/flow/runtime/instances/{processInstanceId}/goAnyWhere | 自由流
[**performProcessInstanceAction**](RuntimeInstanceApi.md#performProcessInstanceAction) | **PUT** /v1/flow/runtime/instances/{processInstanceId} | 激活或挂起工作流实例


<a name="createProcessInstance"></a>
# **createProcessInstance**
> WfProcessInstanceResponse createProcessInstance(body)

启动工作流实例



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RuntimeInstanceApi;


RuntimeInstanceApi apiInstance = new RuntimeInstanceApi();
WfProcessInstanceCreateRequest body = new WfProcessInstanceCreateRequest(); // WfProcessInstanceCreateRequest | 创建流程实例参数
try {
    WfProcessInstanceResponse result = apiInstance.createProcessInstance(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RuntimeInstanceApi#createProcessInstance");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**WfProcessInstanceCreateRequest**](WfProcessInstanceCreateRequest.md)| 创建流程实例参数 |

### Return type

[**WfProcessInstanceResponse**](WfProcessInstanceResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="deleteProcessInstance"></a>
# **deleteProcessInstance**
> Object deleteProcessInstance(processInstanceId, deleteReason)

删除工作流实例信息



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RuntimeInstanceApi;


RuntimeInstanceApi apiInstance = new RuntimeInstanceApi();
String processInstanceId = "processInstanceId_example"; // String | 流程实例id
String deleteReason = "deleteReason_example"; // String | 删除原因
try {
    Object result = apiInstance.deleteProcessInstance(processInstanceId, deleteReason);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RuntimeInstanceApi#deleteProcessInstance");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **processInstanceId** | **String**| 流程实例id |
 **deleteReason** | **String**| 删除原因 | [optional]

### Return type

**Object**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getProcessInstance"></a>
# **getProcessInstance**
> WfProcessInstanceResponse getProcessInstance(processInstanceId)

获取工作流实例信息



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RuntimeInstanceApi;


RuntimeInstanceApi apiInstance = new RuntimeInstanceApi();
String processInstanceId = "processInstanceId_example"; // String | 流程实例id
try {
    WfProcessInstanceResponse result = apiInstance.getProcessInstance(processInstanceId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RuntimeInstanceApi#getProcessInstance");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **processInstanceId** | **String**| 流程实例id |

### Return type

[**WfProcessInstanceResponse**](WfProcessInstanceResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getProcessInstanceDiagram"></a>
# **getProcessInstanceDiagram**
> getProcessInstanceDiagram(processInstanceId)

获取流程实例图

获取流程实例图片，红色框标记为当前环节

### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RuntimeInstanceApi;


RuntimeInstanceApi apiInstance = new RuntimeInstanceApi();
String processInstanceId = "processInstanceId_example"; // String | 流程实例id
try {
    apiInstance.getProcessInstanceDiagram(processInstanceId);
} catch (ApiException e) {
    System.err.println("Exception when calling RuntimeInstanceApi#getProcessInstanceDiagram");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **processInstanceId** | **String**| 流程实例id |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: image/png

<a name="getProcessInstanceDiagramLayout"></a>
# **getProcessInstanceDiagramLayout**
> String getProcessInstanceDiagramLayout(processInstanceId)

获取流程实例的流程定义布局信息



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RuntimeInstanceApi;


RuntimeInstanceApi apiInstance = new RuntimeInstanceApi();
String processInstanceId = "processInstanceId_example"; // String | 流程实例id
try {
    String result = apiInstance.getProcessInstanceDiagramLayout(processInstanceId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RuntimeInstanceApi#getProcessInstanceDiagramLayout");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **processInstanceId** | **String**| 流程实例id |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getProcessInstanceHighlighted"></a>
# **getProcessInstanceHighlighted**
> String getProcessInstanceHighlighted(processInstanceId)

获取流程实例高亮信息



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RuntimeInstanceApi;


RuntimeInstanceApi apiInstance = new RuntimeInstanceApi();
String processInstanceId = "processInstanceId_example"; // String | 流程实例id
try {
    String result = apiInstance.getProcessInstanceHighlighted(processInstanceId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RuntimeInstanceApi#getProcessInstanceHighlighted");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **processInstanceId** | **String**| 流程实例id |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getProcessInstances"></a>
# **getProcessInstances**
> PageListResponseWfProcessInstanceResponse getProcessInstances(id, processDefinitionKey, processDefinitionId, businessKey, involvedUser, suspended, superProcessInstanceId, subProcessInstanceId, excludeSubprocesses, includeProcessVariables, tenantId, tenantIdLike, withoutTenantId, order, sort, start, size)

工作流实例列表



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RuntimeInstanceApi;


RuntimeInstanceApi apiInstance = new RuntimeInstanceApi();
String id = "id_example"; // String | 流程实例id
String processDefinitionKey = "processDefinitionKey_example"; // String | 流程定义key
String processDefinitionId = "processDefinitionId_example"; // String | 流程定义id
String businessKey = "businessKey_example"; // String | 业务记录主键
String involvedUser = "involvedUser_example"; // String | 流程参与者(处理人、候选人、所有者)
Boolean suspended = true; // Boolean | 是否挂起
String superProcessInstanceId = "superProcessInstanceId_example"; // String | 挂起的流程实例id
String subProcessInstanceId = "subProcessInstanceId_example"; // String | 子流程实例id
Boolean excludeSubprocesses = true; // Boolean | 是否排除子流程
Boolean includeProcessVariables = true; // Boolean | 是否查询流程变量
String tenantId = "tenantId_example"; // String | 租户Id
String tenantIdLike = "tenantIdLike_example"; // String | 
Boolean withoutTenantId = true; // Boolean | 是否禁用租户过滤
String order = "order_example"; // String | asc/desc排序
Integer sort = 56; // Integer | 排序字段
Integer start = 56; // Integer | 请求开始行
Integer size = 56; // Integer | 请求记录大小
try {
    PageListResponseWfProcessInstanceResponse result = apiInstance.getProcessInstances(id, processDefinitionKey, processDefinitionId, businessKey, involvedUser, suspended, superProcessInstanceId, subProcessInstanceId, excludeSubprocesses, includeProcessVariables, tenantId, tenantIdLike, withoutTenantId, order, sort, start, size);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RuntimeInstanceApi#getProcessInstances");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| 流程实例id | [optional]
 **processDefinitionKey** | **String**| 流程定义key | [optional]
 **processDefinitionId** | **String**| 流程定义id | [optional]
 **businessKey** | **String**| 业务记录主键 | [optional]
 **involvedUser** | **String**| 流程参与者(处理人、候选人、所有者) | [optional]
 **suspended** | **Boolean**| 是否挂起 | [optional]
 **superProcessInstanceId** | **String**| 挂起的流程实例id | [optional]
 **subProcessInstanceId** | **String**| 子流程实例id | [optional]
 **excludeSubprocesses** | **Boolean**| 是否排除子流程 | [optional]
 **includeProcessVariables** | **Boolean**| 是否查询流程变量 | [optional]
 **tenantId** | **String**| 租户Id | [optional]
 **tenantIdLike** | **String**|  | [optional]
 **withoutTenantId** | **Boolean**| 是否禁用租户过滤 | [optional]
 **order** | **String**| asc/desc排序 | [optional]
 **sort** | **Integer**| 排序字段 | [optional]
 **start** | **Integer**| 请求开始行 | [optional]
 **size** | **Integer**| 请求记录大小 | [optional]

### Return type

[**PageListResponseWfProcessInstanceResponse**](PageListResponseWfProcessInstanceResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="goAnyWhere"></a>
# **goAnyWhere**
> WfActionResult goAnyWhere(processInstanceId, body)

自由流



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RuntimeInstanceApi;


RuntimeInstanceApi apiInstance = new RuntimeInstanceApi();
String processInstanceId = "processInstanceId_example"; // String | 流程实例id
WfJumpRequest body = new WfJumpRequest(); // WfJumpRequest | 自由流参数
try {
    WfActionResult result = apiInstance.goAnyWhere(processInstanceId, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RuntimeInstanceApi#goAnyWhere");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **processInstanceId** | **String**| 流程实例id |
 **body** | [**WfJumpRequest**](WfJumpRequest.md)| 自由流参数 |

### Return type

[**WfActionResult**](WfActionResult.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="performProcessInstanceAction"></a>
# **performProcessInstanceAction**
> WfProcessInstanceResponse performProcessInstanceAction(processInstanceId, body)

激活或挂起工作流实例



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RuntimeInstanceApi;


RuntimeInstanceApi apiInstance = new RuntimeInstanceApi();
String processInstanceId = "processInstanceId_example"; // String | 流程实例id
WfProcessInstanceActionRequest body = new WfProcessInstanceActionRequest(); // WfProcessInstanceActionRequest | suspend或activate实例
try {
    WfProcessInstanceResponse result = apiInstance.performProcessInstanceAction(processInstanceId, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RuntimeInstanceApi#performProcessInstanceAction");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **processInstanceId** | **String**| 流程实例id |
 **body** | [**WfProcessInstanceActionRequest**](WfProcessInstanceActionRequest.md)| suspend或activate实例 |

### Return type

[**WfProcessInstanceResponse**](WfProcessInstanceResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

