# HistoryInstanceApi

All URIs are relative to *http://127.0.0.1:83/rest/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getHistoricInstanceIdentityLinks**](HistoryInstanceApi.md#getHistoricInstanceIdentityLinks) | **GET** /v1/flow/history/instances/{processInstanceId}/identitylinks | 获取历史流程实例候选人和组信息
[**getHistoricProcessInstances**](HistoryInstanceApi.md#getHistoricProcessInstances) | **GET** /v1/flow/history/instances | 获取历史工作流实例列表
[**getHistoryInstanceComments**](HistoryInstanceApi.md#getHistoryInstanceComments) | **GET** /v1/flow/history/instances/{processInstanceId}/comments | 获取历史流程实例评论意见信息
[**getHistoryProcessInstance**](HistoryInstanceApi.md#getHistoryProcessInstance) | **GET** /v1/flow/history/instances/{processInstanceId} | 获取历史流程实例信息
[**getHistoryProcessInstanceDiagram**](HistoryInstanceApi.md#getHistoryProcessInstanceDiagram) | **GET** /v1/flow/history/instances/{processInstanceId}/diagram | 获取流程实例图


<a name="getHistoricInstanceIdentityLinks"></a>
# **getHistoricInstanceIdentityLinks**
> List&lt;WfHistoricIdentityLinkResponse&gt; getHistoricInstanceIdentityLinks(processInstanceId)

获取历史流程实例候选人和组信息



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.HistoryInstanceApi;


HistoryInstanceApi apiInstance = new HistoryInstanceApi();
String processInstanceId = "processInstanceId_example"; // String | 流程实例id
try {
    List<WfHistoricIdentityLinkResponse> result = apiInstance.getHistoricInstanceIdentityLinks(processInstanceId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling HistoryInstanceApi#getHistoricInstanceIdentityLinks");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **processInstanceId** | **String**| 流程实例id |

### Return type

[**List&lt;WfHistoricIdentityLinkResponse&gt;**](WfHistoricIdentityLinkResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="getHistoricProcessInstances"></a>
# **getHistoricProcessInstances**
> PageListResponseWfHistoricProcessInstanceResponse getHistoricProcessInstances(processInstanceId, processDefinitionKey, processDefinitionId, businessKey, involvedUser, finished, superProcessInstanceId, excludeSubprocesses, finishedBefore, finishedAfter, startedBefore, startedAfter, startedBy, includeProcessVariables, tenantId, tenantIdLike, withoutTenantId)

获取历史工作流实例列表



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.HistoryInstanceApi;


HistoryInstanceApi apiInstance = new HistoryInstanceApi();
String processInstanceId = "processInstanceId_example"; // String | 流程实例id
String processDefinitionKey = "processDefinitionKey_example"; // String | 流程定义key
String processDefinitionId = "processDefinitionId_example"; // String | 流程定义id
String businessKey = "businessKey_example"; // String | 业务主键
String involvedUser = "involvedUser_example"; // String | 流程参与者(处理人、候选人、所有者)
Boolean finished = true; // Boolean | 是否完成
String superProcessInstanceId = "superProcessInstanceId_example"; // String | 父流程实例id
Boolean excludeSubprocesses = true; // Boolean | 排除拥有子流的
DateTime finishedBefore = new DateTime(); // DateTime | 完成时间下区间
DateTime finishedAfter = new DateTime(); // DateTime | 完成时间上区间
DateTime startedBefore = new DateTime(); // DateTime | 开始时间下区间
DateTime startedAfter = new DateTime(); // DateTime | 开始时间上区间
String startedBy = "startedBy_example"; // String | 流程起动者
Boolean includeProcessVariables = true; // Boolean | 是否包含流程变量
String tenantId = "tenantId_example"; // String | 租户
String tenantIdLike = "tenantIdLike_example"; // String | 租户模糊匹配，例:%abc%
Boolean withoutTenantId = true; // Boolean | 是否启用租户过滤
try {
    PageListResponseWfHistoricProcessInstanceResponse result = apiInstance.getHistoricProcessInstances(processInstanceId, processDefinitionKey, processDefinitionId, businessKey, involvedUser, finished, superProcessInstanceId, excludeSubprocesses, finishedBefore, finishedAfter, startedBefore, startedAfter, startedBy, includeProcessVariables, tenantId, tenantIdLike, withoutTenantId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling HistoryInstanceApi#getHistoricProcessInstances");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **processInstanceId** | **String**| 流程实例id | [optional]
 **processDefinitionKey** | **String**| 流程定义key | [optional]
 **processDefinitionId** | **String**| 流程定义id | [optional]
 **businessKey** | **String**| 业务主键 | [optional]
 **involvedUser** | **String**| 流程参与者(处理人、候选人、所有者) | [optional]
 **finished** | **Boolean**| 是否完成 | [optional]
 **superProcessInstanceId** | **String**| 父流程实例id | [optional]
 **excludeSubprocesses** | **Boolean**| 排除拥有子流的 | [optional]
 **finishedBefore** | **DateTime**| 完成时间下区间 | [optional]
 **finishedAfter** | **DateTime**| 完成时间上区间 | [optional]
 **startedBefore** | **DateTime**| 开始时间下区间 | [optional]
 **startedAfter** | **DateTime**| 开始时间上区间 | [optional]
 **startedBy** | **String**| 流程起动者 | [optional]
 **includeProcessVariables** | **Boolean**| 是否包含流程变量 | [optional]
 **tenantId** | **String**| 租户 | [optional]
 **tenantIdLike** | **String**| 租户模糊匹配，例:%abc% | [optional]
 **withoutTenantId** | **Boolean**| 是否启用租户过滤 | [optional]

### Return type

[**PageListResponseWfHistoricProcessInstanceResponse**](PageListResponseWfHistoricProcessInstanceResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getHistoryInstanceComments"></a>
# **getHistoryInstanceComments**
> List&lt;WfCommentResponse&gt; getHistoryInstanceComments(processInstanceId)

获取历史流程实例评论意见信息



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.HistoryInstanceApi;


HistoryInstanceApi apiInstance = new HistoryInstanceApi();
String processInstanceId = "processInstanceId_example"; // String | 流程实例id
try {
    List<WfCommentResponse> result = apiInstance.getHistoryInstanceComments(processInstanceId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling HistoryInstanceApi#getHistoryInstanceComments");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **processInstanceId** | **String**| 流程实例id |

### Return type

[**List&lt;WfCommentResponse&gt;**](WfCommentResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="getHistoryProcessInstance"></a>
# **getHistoryProcessInstance**
> WfHistoricProcessInstanceResponse getHistoryProcessInstance(processInstanceId)

获取历史流程实例信息



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.HistoryInstanceApi;


HistoryInstanceApi apiInstance = new HistoryInstanceApi();
String processInstanceId = "processInstanceId_example"; // String | 流程实例id
try {
    WfHistoricProcessInstanceResponse result = apiInstance.getHistoryProcessInstance(processInstanceId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling HistoryInstanceApi#getHistoryProcessInstance");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **processInstanceId** | **String**| 流程实例id |

### Return type

[**WfHistoricProcessInstanceResponse**](WfHistoricProcessInstanceResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="getHistoryProcessInstanceDiagram"></a>
# **getHistoryProcessInstanceDiagram**
> getHistoryProcessInstanceDiagram(processInstanceId)

获取流程实例图



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.HistoryInstanceApi;


HistoryInstanceApi apiInstance = new HistoryInstanceApi();
String processInstanceId = "processInstanceId_example"; // String | 流程实例id
try {
    apiInstance.getHistoryProcessInstanceDiagram(processInstanceId);
} catch (ApiException e) {
    System.err.println("Exception when calling HistoryInstanceApi#getHistoryProcessInstanceDiagram");
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

