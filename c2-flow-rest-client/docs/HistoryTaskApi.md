# HistoryTaskApi

All URIs are relative to *http://127.0.0.1:83/rest/*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getHistoricTaskProcessInstances**](HistoryTaskApi.md#getHistoricTaskProcessInstances) | **GET** /v1/flow/history/tasks | 获取历史任务列表
[**getHistoryTaskIdentityLinks**](HistoryTaskApi.md#getHistoryTaskIdentityLinks) | **GET** /v1/flow/history/tasks/{taskId}/identitylinks | 获取历史任务候选人或组信息
[**getTaskInstance**](HistoryTaskApi.md#getTaskInstance) | **GET** /v1/flow/history/tasks/{taskId} | 获取历史任务信息


<a name="getHistoricTaskProcessInstances"></a>
# **getHistoricTaskProcessInstances**
> PageListResponseWfHistoricTaskInstanceResponse getHistoricTaskProcessInstances(taskId, processInstanceId, processBusinessKey, processDefinitionKey, processDefinitionId, processDefinitionName, executionId, taskName, taskNameLike, taskDescription, taskDescriptionLike, taskDefinitionKey, taskDeleteReason, taskDeleteReasonLike, taskAssignee, taskAssigneeLike, taskOwner, taskOwnerLike, taskInvolvedUser, taskPriority, finished, processFinished, parentTaskId, dueDate, dueDateBefore, dueDateAfter, taskCreatedOn, taskCreatedBefore, taskCreatedAfter, taskCompletedOn, taskCompletedBefore, taskCompletedAfter, includeTaskLocalVariables, includeProcessVariables, tenantId, tenantIdLike, withoutTenantId)

获取历史任务列表



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.HistoryTaskApi;


HistoryTaskApi apiInstance = new HistoryTaskApi();
String taskId = "taskId_example"; // String | 任务id
String processInstanceId = "processInstanceId_example"; // String | 流程实例
String processBusinessKey = "processBusinessKey_example"; // String | 业务主键
String processDefinitionKey = "processDefinitionKey_example"; // String | 流程定key
String processDefinitionId = "processDefinitionId_example"; // String | 流程定义id
String processDefinitionName = "processDefinitionName_example"; // String | 流程定义名称
String executionId = "executionId_example"; // String | 任务执行id
String taskName = "taskName_example"; // String | 任务名称
String taskNameLike = "taskNameLike_example"; // String | 任务名称模糊匹配
String taskDescription = "taskDescription_example"; // String | 任务描述
String taskDescriptionLike = "taskDescriptionLike_example"; // String | 任务描述模糊匹配
String taskDefinitionKey = "taskDefinitionKey_example"; // String | 任务key
String taskDeleteReason = "taskDeleteReason_example"; // String | 任务删除原因
String taskDeleteReasonLike = "taskDeleteReasonLike_example"; // String | 任务删除原因模糊匹配
String taskAssignee = "taskAssignee_example"; // String | 处理人
String taskAssigneeLike = "taskAssigneeLike_example"; // String | 处理人模糊匹配
String taskOwner = "taskOwner_example"; // String | 任务所有者
String taskOwnerLike = "taskOwnerLike_example"; // String | 任务所有者模糊匹配
String taskInvolvedUser = "taskInvolvedUser_example"; // String | 任务参与者(处理人、候选人、所有者)
Integer taskPriority = 56; // Integer | 任务优先级
Boolean finished = true; // Boolean | 是否完成
Boolean processFinished = true; // Boolean | 任务所在流程实例是否完成
String parentTaskId = "parentTaskId_example"; // String | 父任务
DateTime dueDate = new DateTime(); // DateTime | 任务过期时间
DateTime dueDateBefore = new DateTime(); // DateTime | 过期时间下区间
DateTime dueDateAfter = new DateTime(); // DateTime | 过期时间上区间
DateTime taskCreatedOn = new DateTime(); // DateTime | 任务创建时间
DateTime taskCreatedBefore = new DateTime(); // DateTime | 创建时间下区间
DateTime taskCreatedAfter = new DateTime(); // DateTime | 创建时间上区间
DateTime taskCompletedOn = new DateTime(); // DateTime | 任务完成时间
DateTime taskCompletedBefore = new DateTime(); // DateTime | 完成时间下区间
DateTime taskCompletedAfter = new DateTime(); // DateTime | 完成时间上区间
Boolean includeTaskLocalVariables = true; // Boolean | 是否只查询包含流程环节变量任务
Boolean includeProcessVariables = true; // Boolean | 是否只查询包含流程环节变量和全局变量任务
String tenantId = "tenantId_example"; // String | 租户
String tenantIdLike = "tenantIdLike_example"; // String | 租户模糊匹配
Boolean withoutTenantId = true; // Boolean | 忽略租户条件
try {
    PageListResponseWfHistoricTaskInstanceResponse result = apiInstance.getHistoricTaskProcessInstances(taskId, processInstanceId, processBusinessKey, processDefinitionKey, processDefinitionId, processDefinitionName, executionId, taskName, taskNameLike, taskDescription, taskDescriptionLike, taskDefinitionKey, taskDeleteReason, taskDeleteReasonLike, taskAssignee, taskAssigneeLike, taskOwner, taskOwnerLike, taskInvolvedUser, taskPriority, finished, processFinished, parentTaskId, dueDate, dueDateBefore, dueDateAfter, taskCreatedOn, taskCreatedBefore, taskCreatedAfter, taskCompletedOn, taskCompletedBefore, taskCompletedAfter, includeTaskLocalVariables, includeProcessVariables, tenantId, tenantIdLike, withoutTenantId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling HistoryTaskApi#getHistoricTaskProcessInstances");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **taskId** | **String**| 任务id | [optional]
 **processInstanceId** | **String**| 流程实例 | [optional]
 **processBusinessKey** | **String**| 业务主键 | [optional]
 **processDefinitionKey** | **String**| 流程定key | [optional]
 **processDefinitionId** | **String**| 流程定义id | [optional]
 **processDefinitionName** | **String**| 流程定义名称 | [optional]
 **executionId** | **String**| 任务执行id | [optional]
 **taskName** | **String**| 任务名称 | [optional]
 **taskNameLike** | **String**| 任务名称模糊匹配 | [optional]
 **taskDescription** | **String**| 任务描述 | [optional]
 **taskDescriptionLike** | **String**| 任务描述模糊匹配 | [optional]
 **taskDefinitionKey** | **String**| 任务key | [optional]
 **taskDeleteReason** | **String**| 任务删除原因 | [optional]
 **taskDeleteReasonLike** | **String**| 任务删除原因模糊匹配 | [optional]
 **taskAssignee** | **String**| 处理人 | [optional]
 **taskAssigneeLike** | **String**| 处理人模糊匹配 | [optional]
 **taskOwner** | **String**| 任务所有者 | [optional]
 **taskOwnerLike** | **String**| 任务所有者模糊匹配 | [optional]
 **taskInvolvedUser** | **String**| 任务参与者(处理人、候选人、所有者) | [optional]
 **taskPriority** | **Integer**| 任务优先级 | [optional]
 **finished** | **Boolean**| 是否完成 | [optional]
 **processFinished** | **Boolean**| 任务所在流程实例是否完成 | [optional]
 **parentTaskId** | **String**| 父任务 | [optional]
 **dueDate** | **DateTime**| 任务过期时间 | [optional]
 **dueDateBefore** | **DateTime**| 过期时间下区间 | [optional]
 **dueDateAfter** | **DateTime**| 过期时间上区间 | [optional]
 **taskCreatedOn** | **DateTime**| 任务创建时间 | [optional]
 **taskCreatedBefore** | **DateTime**| 创建时间下区间 | [optional]
 **taskCreatedAfter** | **DateTime**| 创建时间上区间 | [optional]
 **taskCompletedOn** | **DateTime**| 任务完成时间 | [optional]
 **taskCompletedBefore** | **DateTime**| 完成时间下区间 | [optional]
 **taskCompletedAfter** | **DateTime**| 完成时间上区间 | [optional]
 **includeTaskLocalVariables** | **Boolean**| 是否只查询包含流程环节变量任务 | [optional]
 **includeProcessVariables** | **Boolean**| 是否只查询包含流程环节变量和全局变量任务 | [optional]
 **tenantId** | **String**| 租户 | [optional]
 **tenantIdLike** | **String**| 租户模糊匹配 | [optional]
 **withoutTenantId** | **Boolean**| 忽略租户条件 | [optional]

### Return type

[**PageListResponseWfHistoricTaskInstanceResponse**](PageListResponseWfHistoricTaskInstanceResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getHistoryTaskIdentityLinks"></a>
# **getHistoryTaskIdentityLinks**
> List&lt;WfHistoricIdentityLinkResponse&gt; getHistoryTaskIdentityLinks(taskId)

获取历史任务候选人或组信息



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.HistoryTaskApi;


HistoryTaskApi apiInstance = new HistoryTaskApi();
String taskId = "taskId_example"; // String | 任务id
try {
    List<WfHistoricIdentityLinkResponse> result = apiInstance.getHistoryTaskIdentityLinks(taskId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling HistoryTaskApi#getHistoryTaskIdentityLinks");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **taskId** | **String**| 任务id |

### Return type

[**List&lt;WfHistoricIdentityLinkResponse&gt;**](WfHistoricIdentityLinkResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getTaskInstance"></a>
# **getTaskInstance**
> WfHistoricTaskInstanceResponse getTaskInstance(taskId, includeTaskLocalVariables, includeProcessVariables)

获取历史任务信息



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.HistoryTaskApi;


HistoryTaskApi apiInstance = new HistoryTaskApi();
String taskId = "taskId_example"; // String | 任务id
Boolean includeTaskLocalVariables = true; // Boolean | 是否包含local变量
Boolean includeProcessVariables = true; // Boolean | 是否包含global变量
try {
    WfHistoricTaskInstanceResponse result = apiInstance.getTaskInstance(taskId, includeTaskLocalVariables, includeProcessVariables);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling HistoryTaskApi#getTaskInstance");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **taskId** | **String**| 任务id |
 **includeTaskLocalVariables** | **Boolean**| 是否包含local变量 | [optional]
 **includeProcessVariables** | **Boolean**| 是否包含global变量 | [optional]

### Return type

[**WfHistoricTaskInstanceResponse**](WfHistoricTaskInstanceResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

