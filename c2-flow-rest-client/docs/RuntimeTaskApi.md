# RuntimeTaskApi

All URIs are relative to *http://127.0.0.1:83/rest*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createComment**](RuntimeTaskApi.md#createComment) | **POST** /v1/flow/runtime/tasks/{taskId}/comments | 添加任务意见
[**createTaskVariable**](RuntimeTaskApi.md#createTaskVariable) | **POST** /v1/flow/runtime/tasks/{taskId}/variables | 批量添加任务流程变量
[**deleteAllLocalTaskVariables**](RuntimeTaskApi.md#deleteAllLocalTaskVariables) | **DELETE** /v1/flow/runtime/tasks/{taskId}/variables | 批量删除任务流程变量
[**deleteComment**](RuntimeTaskApi.md#deleteComment) | **DELETE** /v1/flow/runtime/tasks/{taskId}/comments/{commentId} | 删了某任务下某条意见
[**deleteTask**](RuntimeTaskApi.md#deleteTask) | **DELETE** /v1/flow/runtime/tasks/{taskId} | 删除任务
[**executeTaskAction**](RuntimeTaskApi.md#executeTaskAction) | **POST** /v1/flow/runtime/tasks/{taskId} | 处理任务
[**getComment**](RuntimeTaskApi.md#getComment) | **GET** /v1/flow/runtime/tasks/{taskId}/comments/{commentId} | 获取某任务下某条意见详细
[**getComments**](RuntimeTaskApi.md#getComments) | **GET** /v1/flow/runtime/tasks/{taskId}/comments | 获取某任务意见列表
[**getTask**](RuntimeTaskApi.md#getTask) | **GET** /v1/flow/runtime/tasks/{taskId} | 获取任务信息
[**getTasks**](RuntimeTaskApi.md#getTasks) | **GET** /v1/flow/runtime/tasks | 待办列表
[**getVariable**](RuntimeTaskApi.md#getVariable) | **GET** /v1/flow/runtime/tasks/{taskId}/variables/{variableName} | 获取某任务某流程变量
[**getVariables**](RuntimeTaskApi.md#getVariables) | **GET** /v1/flow/runtime/tasks/{taskId}/variables | 获取某任务流程变量集合
[**updateTask**](RuntimeTaskApi.md#updateTask) | **PUT** /v1/flow/runtime/tasks/{taskId} | 修改任务


<a name="createComment"></a>
# **createComment**
> WfCommentResponse createComment(taskId, body)

添加任务意见



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RuntimeTaskApi;


RuntimeTaskApi apiInstance = new RuntimeTaskApi();
String taskId = "taskId_example"; // String | 任务实例id
WfCommentResponse body = new WfCommentResponse(); // WfCommentResponse | 竟见数据
try {
    WfCommentResponse result = apiInstance.createComment(taskId, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RuntimeTaskApi#createComment");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **taskId** | **String**| 任务实例id |
 **body** | [**WfCommentResponse**](WfCommentResponse.md)| 竟见数据 |

### Return type

[**WfCommentResponse**](WfCommentResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="createTaskVariable"></a>
# **createTaskVariable**
> List&lt;WfRestVariable&gt; createTaskVariable(taskId, body)

批量添加任务流程变量



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RuntimeTaskApi;


RuntimeTaskApi apiInstance = new RuntimeTaskApi();
String taskId = "taskId_example"; // String | 任务实例id
List<WfRestVariable> body = Arrays.asList(new WfRestVariable()); // List<WfRestVariable> | 变量集合
try {
    List<WfRestVariable> result = apiInstance.createTaskVariable(taskId, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RuntimeTaskApi#createTaskVariable");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **taskId** | **String**| 任务实例id |
 **body** | [**List&lt;WfRestVariable&gt;**](WfRestVariable.md)| 变量集合 |

### Return type

[**List&lt;WfRestVariable&gt;**](WfRestVariable.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="deleteAllLocalTaskVariables"></a>
# **deleteAllLocalTaskVariables**
> deleteAllLocalTaskVariables(taskId)

批量删除任务流程变量



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RuntimeTaskApi;


RuntimeTaskApi apiInstance = new RuntimeTaskApi();
String taskId = "taskId_example"; // String | 任务实例id
try {
    apiInstance.deleteAllLocalTaskVariables(taskId);
} catch (ApiException e) {
    System.err.println("Exception when calling RuntimeTaskApi#deleteAllLocalTaskVariables");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **taskId** | **String**| 任务实例id |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="deleteComment"></a>
# **deleteComment**
> deleteComment(taskId, commentId)

删了某任务下某条意见



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RuntimeTaskApi;


RuntimeTaskApi apiInstance = new RuntimeTaskApi();
String taskId = "taskId_example"; // String | 任务实例id
String commentId = "commentId_example"; // String | 意见id
try {
    apiInstance.deleteComment(taskId, commentId);
} catch (ApiException e) {
    System.err.println("Exception when calling RuntimeTaskApi#deleteComment");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **taskId** | **String**| 任务实例id |
 **commentId** | **String**| 意见id |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="deleteTask"></a>
# **deleteTask**
> deleteTask(taskId, cascadeHistory, deleteReason)

删除任务



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RuntimeTaskApi;


RuntimeTaskApi apiInstance = new RuntimeTaskApi();
String taskId = "taskId_example"; // String | 任务实例id
Boolean cascadeHistory = true; // Boolean | 是否删除历史任务实例
String deleteReason = "deleteReason_example"; // String | 删除原因
try {
    apiInstance.deleteTask(taskId, cascadeHistory, deleteReason);
} catch (ApiException e) {
    System.err.println("Exception when calling RuntimeTaskApi#deleteTask");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **taskId** | **String**| 任务实例id |
 **cascadeHistory** | **Boolean**| 是否删除历史任务实例 | [optional]
 **deleteReason** | **String**| 删除原因 | [optional]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="executeTaskAction"></a>
# **executeTaskAction**
> WfActionResult executeTaskAction(taskId, body)

处理任务

处理任务：签收(CLAIM)、签收并完成(CLAIM_COMPLETE)、完成(COMPLETE)、委托代理(DELEGATE)、回绝委托代理(RESOLVE)、退回任务(REJECT)

### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RuntimeTaskApi;


RuntimeTaskApi apiInstance = new RuntimeTaskApi();
String taskId = "taskId_example"; // String | 任务id
WfTaskActionRequest body = new WfTaskActionRequest(); // WfTaskActionRequest | 操作入参
try {
    WfActionResult result = apiInstance.executeTaskAction(taskId, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RuntimeTaskApi#executeTaskAction");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **taskId** | **String**| 任务id |
 **body** | [**WfTaskActionRequest**](WfTaskActionRequest.md)| 操作入参 |

### Return type

[**WfActionResult**](WfActionResult.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getComment"></a>
# **getComment**
> WfCommentResponse getComment(taskId, commentId)

获取某任务下某条意见详细



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RuntimeTaskApi;


RuntimeTaskApi apiInstance = new RuntimeTaskApi();
String taskId = "taskId_example"; // String | 任务实例id
String commentId = "commentId_example"; // String | 意见id
try {
    WfCommentResponse result = apiInstance.getComment(taskId, commentId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RuntimeTaskApi#getComment");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **taskId** | **String**| 任务实例id |
 **commentId** | **String**| 意见id |

### Return type

[**WfCommentResponse**](WfCommentResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getComments"></a>
# **getComments**
> List&lt;WfCommentResponse&gt; getComments(taskId)

获取某任务意见列表



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RuntimeTaskApi;


RuntimeTaskApi apiInstance = new RuntimeTaskApi();
String taskId = "taskId_example"; // String | 任务实例id
try {
    List<WfCommentResponse> result = apiInstance.getComments(taskId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RuntimeTaskApi#getComments");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **taskId** | **String**| 任务实例id |

### Return type

[**List&lt;WfCommentResponse&gt;**](WfCommentResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getTask"></a>
# **getTask**
> WfTaskResponse getTask(taskId, includeTaskLocalVariables, includeProcessVariables)

获取任务信息



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RuntimeTaskApi;


RuntimeTaskApi apiInstance = new RuntimeTaskApi();
String taskId = "taskId_example"; // String | 任务id
Boolean includeTaskLocalVariables = true; // Boolean | 是否包含local变量
Boolean includeProcessVariables = true; // Boolean | 是否包含global变量
try {
    WfTaskResponse result = apiInstance.getTask(taskId, includeTaskLocalVariables, includeProcessVariables);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RuntimeTaskApi#getTask");
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

[**WfTaskResponse**](WfTaskResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getTasks"></a>
# **getTasks**
> PageListResponseWfTaskResponse getTasks(name, nameLike, description, descriptionLike, priority, minimumPriority, maximumPriority, assignee, owner, unassigned, delegationState, candidateUser, involvedUser, candidateGroup, processDefinitionKey, processDefinitionKeyLike, processDefinitionName, processDefinitionNameLike, processInstanceId, processInstanceBusinessKey, executionId, createdOn, createdBefore, createdAfter, excludeSubTasks, taskDefinitionKey, taskDefinitionKeyLike, dueDate, dueBefore, dueAfter, active, includeTaskLocalVariables, includeProcessVariables, tenantId, tenantIdLike, withoutTenantId)

待办列表



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RuntimeTaskApi;


RuntimeTaskApi apiInstance = new RuntimeTaskApi();
String name = "name_example"; // String | 任务名称
String nameLike = "nameLike_example"; // String | 任务名称模糊匹配
String description = "description_example"; // String | 任务描述
String descriptionLike = "descriptionLike_example"; // String | 任务描述模糊匹配
Integer priority = 56; // Integer | 任务优先级
Integer minimumPriority = 56; // Integer | 任务最小优先级
Integer maximumPriority = 56; // Integer | 任务最大优先级
String assignee = "assignee_example"; // String | 任务处理人
String owner = "owner_example"; // String | 任务所有者
Boolean unassigned = true; // Boolean | 是否未分配
String delegationState = "delegationState_example"; // String | 代理状态
String candidateUser = "candidateUser_example"; // String | 任务候选人
String involvedUser = "involvedUser_example"; // String | 流程参与者(处理人、候选人、所有者)
String candidateGroup = "candidateGroup_example"; // String | 任务候选组
String processDefinitionKey = "processDefinitionKey_example"; // String | 任务所属流程定义key
String processDefinitionKeyLike = "processDefinitionKeyLike_example"; // String | 流程定义key模糊匹配
String processDefinitionName = "processDefinitionName_example"; // String | 任务所属流程定义名称
String processDefinitionNameLike = "processDefinitionNameLike_example"; // String | 流程定义名称模糊匹配
String processInstanceId = "processInstanceId_example"; // String | 任务所属流程实例id
String processInstanceBusinessKey = "processInstanceBusinessKey_example"; // String | 任务所属业务id
String executionId = "executionId_example"; // String | 任务执行id
Date createdOn = new Date(); // Date | 任务创建时间
Date createdBefore = new Date(); // Date | 创建时间下区间
Date createdAfter = new Date(); // Date | 创建时间上区间
Boolean excludeSubTasks = true; // Boolean | 是否排除有子任务
String taskDefinitionKey = "taskDefinitionKey_example"; // String | 任务key
String taskDefinitionKeyLike = "taskDefinitionKeyLike_example"; // String | 任务key模糊匹配
Date dueDate = new Date(); // Date | 任务处理时间
Date dueBefore = new Date(); // Date | 任务处理时间下区间
Date dueAfter = new Date(); // Date | 任务处理时间上区间
Boolean active = true; // Boolean | 任务是否活动的
Boolean includeTaskLocalVariables = true; // Boolean | 是否只查询包含流程环节变量任务
Boolean includeProcessVariables = true; // Boolean | 是否只查询包含流程环节变量和全局变量任务
String tenantId = "tenantId_example"; // String | 租户
String tenantIdLike = "tenantIdLike_example"; // String | 租户模糊匹配
Boolean withoutTenantId = true; // Boolean | 忽略租户条件
try {
    PageListResponseWfTaskResponse result = apiInstance.getTasks(name, nameLike, description, descriptionLike, priority, minimumPriority, maximumPriority, assignee, owner, unassigned, delegationState, candidateUser, involvedUser, candidateGroup, processDefinitionKey, processDefinitionKeyLike, processDefinitionName, processDefinitionNameLike, processInstanceId, processInstanceBusinessKey, executionId, createdOn, createdBefore, createdAfter, excludeSubTasks, taskDefinitionKey, taskDefinitionKeyLike, dueDate, dueBefore, dueAfter, active, includeTaskLocalVariables, includeProcessVariables, tenantId, tenantIdLike, withoutTenantId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RuntimeTaskApi#getTasks");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **name** | **String**| 任务名称 | [optional]
 **nameLike** | **String**| 任务名称模糊匹配 | [optional]
 **description** | **String**| 任务描述 | [optional]
 **descriptionLike** | **String**| 任务描述模糊匹配 | [optional]
 **priority** | **Integer**| 任务优先级 | [optional]
 **minimumPriority** | **Integer**| 任务最小优先级 | [optional]
 **maximumPriority** | **Integer**| 任务最大优先级 | [optional]
 **assignee** | **String**| 任务处理人 | [optional]
 **owner** | **String**| 任务所有者 | [optional]
 **unassigned** | **Boolean**| 是否未分配 | [optional]
 **delegationState** | **String**| 代理状态 | [optional]
 **candidateUser** | **String**| 任务候选人 | [optional]
 **involvedUser** | **String**| 流程参与者(处理人、候选人、所有者) | [optional]
 **candidateGroup** | **String**| 任务候选组 | [optional]
 **processDefinitionKey** | **String**| 任务所属流程定义key | [optional]
 **processDefinitionKeyLike** | **String**| 流程定义key模糊匹配 | [optional]
 **processDefinitionName** | **String**| 任务所属流程定义名称 | [optional]
 **processDefinitionNameLike** | **String**| 流程定义名称模糊匹配 | [optional]
 **processInstanceId** | **String**| 任务所属流程实例id | [optional]
 **processInstanceBusinessKey** | **String**| 任务所属业务id | [optional]
 **executionId** | **String**| 任务执行id | [optional]
 **createdOn** | **Date**| 任务创建时间 | [optional]
 **createdBefore** | **Date**| 创建时间下区间 | [optional]
 **createdAfter** | **Date**| 创建时间上区间 | [optional]
 **excludeSubTasks** | **Boolean**| 是否排除有子任务 | [optional]
 **taskDefinitionKey** | **String**| 任务key | [optional]
 **taskDefinitionKeyLike** | **String**| 任务key模糊匹配 | [optional]
 **dueDate** | **Date**| 任务处理时间 | [optional]
 **dueBefore** | **Date**| 任务处理时间下区间 | [optional]
 **dueAfter** | **Date**| 任务处理时间上区间 | [optional]
 **active** | **Boolean**| 任务是否活动的 | [optional]
 **includeTaskLocalVariables** | **Boolean**| 是否只查询包含流程环节变量任务 | [optional]
 **includeProcessVariables** | **Boolean**| 是否只查询包含流程环节变量和全局变量任务 | [optional]
 **tenantId** | **String**| 租户 | [optional]
 **tenantIdLike** | **String**| 租户模糊匹配 | [optional]
 **withoutTenantId** | **Boolean**| 忽略租户条件 | [optional]

### Return type

[**PageListResponseWfTaskResponse**](PageListResponseWfTaskResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getVariable"></a>
# **getVariable**
> WfRestVariable getVariable(taskId, variableName, scope)

获取某任务某流程变量



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RuntimeTaskApi;


RuntimeTaskApi apiInstance = new RuntimeTaskApi();
String taskId = "taskId_example"; // String | 任务实例id
String variableName = "variableName_example"; // String | 流程变量名
String scope = "scope_example"; // String | 作用域(local/global)
try {
    WfRestVariable result = apiInstance.getVariable(taskId, variableName, scope);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RuntimeTaskApi#getVariable");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **taskId** | **String**| 任务实例id |
 **variableName** | **String**| 流程变量名 |
 **scope** | **String**| 作用域(local/global) |

### Return type

[**WfRestVariable**](WfRestVariable.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getVariables"></a>
# **getVariables**
> List&lt;WfRestVariable&gt; getVariables(taskId, scope)

获取某任务流程变量集合



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RuntimeTaskApi;


RuntimeTaskApi apiInstance = new RuntimeTaskApi();
String taskId = "taskId_example"; // String | 任务实例id
String scope = "scope_example"; // String | 作用域(local/global)
try {
    List<WfRestVariable> result = apiInstance.getVariables(taskId, scope);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RuntimeTaskApi#getVariables");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **taskId** | **String**| 任务实例id |
 **scope** | **String**| 作用域(local/global) |

### Return type

[**List&lt;WfRestVariable&gt;**](WfRestVariable.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="updateTask"></a>
# **updateTask**
> WfTaskResponse updateTask(taskId, body)

修改任务



### Example
```java
// Import classes:
//import com.chinacreator.c2.flow.rest.ApiException;
//import com.chinacreator.c2.flow.rest.api.RuntimeTaskApi;


RuntimeTaskApi apiInstance = new RuntimeTaskApi();
String taskId = "taskId_example"; // String | 
WfTaskRequest body = new WfTaskRequest(); // WfTaskRequest | 
try {
    WfTaskResponse result = apiInstance.updateTask(taskId, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RuntimeTaskApi#updateTask");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **taskId** | **String**|  |
 **body** | [**WfTaskRequest**](WfTaskRequest.md)|  | [optional]

### Return type

[**WfTaskResponse**](WfTaskResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

