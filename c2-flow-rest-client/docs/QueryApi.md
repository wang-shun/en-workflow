# QueryApi

All URIs are relative to *http://127.0.0.1:83/rest/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**queryHistoricProcessInstances**](QueryApi.md#queryHistoricProcessInstances) | **POST** /v1/flow/query/history/instances | 查询历史流程实例列表
[**queryHistoricTaskInstances**](QueryApi.md#queryHistoricTaskInstances) | **POST** /v1/flow/query/history/tasks | 查询历史任务列表
[**queryProcessInstances**](QueryApi.md#queryProcessInstances) | **POST** /v1/flow/query/runtime/instances | 查询流程实例列表
[**queryTasks**](QueryApi.md#queryTasks) | **POST** /v1/flow/query/runtime/tasks | 查询待办列表


<a name="queryHistoricProcessInstances"></a>
# **queryHistoricProcessInstances**
> PageListResponseWfHistoricProcessInstanceResponse queryHistoricProcessInstances(body)

查询历史流程实例列表



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.QueryApi;


QueryApi apiInstance = new QueryApi();
WfHistoricProcessInstanceQueryRequest body = new WfHistoricProcessInstanceQueryRequest(); // WfHistoricProcessInstanceQueryRequest | 查询过滤条件
try {
    PageListResponseWfHistoricProcessInstanceResponse result = apiInstance.queryHistoricProcessInstances(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling QueryApi#queryHistoricProcessInstances");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**WfHistoricProcessInstanceQueryRequest**](WfHistoricProcessInstanceQueryRequest.md)| 查询过滤条件 |

### Return type

[**PageListResponseWfHistoricProcessInstanceResponse**](PageListResponseWfHistoricProcessInstanceResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="queryHistoricTaskInstances"></a>
# **queryHistoricTaskInstances**
> PageListResponseWfHistoricTaskInstanceResponse queryHistoricTaskInstances(body)

查询历史任务列表



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.QueryApi;


QueryApi apiInstance = new QueryApi();
WfHistoricTaskInstanceQueryRequest body = new WfHistoricTaskInstanceQueryRequest(); // WfHistoricTaskInstanceQueryRequest | 查询过滤条件
try {
    PageListResponseWfHistoricTaskInstanceResponse result = apiInstance.queryHistoricTaskInstances(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling QueryApi#queryHistoricTaskInstances");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**WfHistoricTaskInstanceQueryRequest**](WfHistoricTaskInstanceQueryRequest.md)| 查询过滤条件 |

### Return type

[**PageListResponseWfHistoricTaskInstanceResponse**](PageListResponseWfHistoricTaskInstanceResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="queryProcessInstances"></a>
# **queryProcessInstances**
> PageListResponseWfProcessInstanceResponse queryProcessInstances(body)

查询流程实例列表



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.QueryApi;


QueryApi apiInstance = new QueryApi();
WfProcessInstanceQueryRequest body = new WfProcessInstanceQueryRequest(); // WfProcessInstanceQueryRequest | 查询过滤条件
try {
    PageListResponseWfProcessInstanceResponse result = apiInstance.queryProcessInstances(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling QueryApi#queryProcessInstances");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**WfProcessInstanceQueryRequest**](WfProcessInstanceQueryRequest.md)| 查询过滤条件 |

### Return type

[**PageListResponseWfProcessInstanceResponse**](PageListResponseWfProcessInstanceResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="queryTasks"></a>
# **queryTasks**
> PageListResponseWfTaskResponse queryTasks(body)

查询待办列表



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.QueryApi;


QueryApi apiInstance = new QueryApi();
WfTaskQueryRequest body = new WfTaskQueryRequest(); // WfTaskQueryRequest | 查询过滤条件
try {
    PageListResponseWfTaskResponse result = apiInstance.queryTasks(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling QueryApi#queryTasks");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**WfTaskQueryRequest**](WfTaskQueryRequest.md)| 查询过滤条件 |

### Return type

[**PageListResponseWfTaskResponse**](PageListResponseWfTaskResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

