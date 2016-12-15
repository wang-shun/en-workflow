# c2-flow-rest-client

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn deploy
```

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>com.chinacreator.c2</groupId>
    <artifactId>c2-flow-rest-client</artifactId>
    <version>4.1.6.1-SNAPSHOT</version>
    <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "com.chinacreator.c2:c2-flow-rest-client:4.1.6.1-SNAPSHOT"
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

* target/c2-flow-rest-client-4.1.6.1-SNAPSHOT.jar
* target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

import com.chinacreator.c2.flow.rest.*;
import com.chinacreator.c2.flow.rest.auth.*;
import com.chinacreator.c2.flow.rest.model.*;
import com.chinacreator.c2.flow.rest.api.HistoryInstanceApi;

import java.io.File;
import java.util.*;

public class HistoryInstanceApiExample {

    public static void main(String[] args) {
        
        HistoryInstanceApi apiInstance = new HistoryInstanceApi();
        String processInstanceId = "processInstanceId_example"; // String | 流程实例id
        try {
            List<WfHistoricIdentityLinkResponse> result = apiInstance.getHistoricInstanceIdentityLinks(processInstanceId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling HistoryInstanceApi#getHistoricInstanceIdentityLinks");
            e.printStackTrace();
        }
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *http://127.0.0.1:83/rest*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*HistoryInstanceApi* | [**getHistoricInstanceIdentityLinks**](docs/HistoryInstanceApi.md#getHistoricInstanceIdentityLinks) | **GET** /v1/flow/history/instances/{processInstanceId}/identitylinks | 获取历史流程实例候选人和组信息
*HistoryInstanceApi* | [**getHistoricProcessInstances**](docs/HistoryInstanceApi.md#getHistoricProcessInstances) | **GET** /v1/flow/history/instances | 获取历史工作流实例列表
*HistoryInstanceApi* | [**getHistoryInstanceComments**](docs/HistoryInstanceApi.md#getHistoryInstanceComments) | **GET** /v1/flow/history/instances/{processInstanceId}/comments | 获取历史流程实例评论意见信息
*HistoryInstanceApi* | [**getHistoryProcessInstance**](docs/HistoryInstanceApi.md#getHistoryProcessInstance) | **GET** /v1/flow/history/instances/{processInstanceId} | 获取历史流程实例信息
*HistoryInstanceApi* | [**getHistoryProcessInstanceDiagram**](docs/HistoryInstanceApi.md#getHistoryProcessInstanceDiagram) | **GET** /v1/flow/history/instances/{processInstanceId}/diagram | 获取流程实例图
*HistoryTaskApi* | [**getHistoricTaskProcessInstances**](docs/HistoryTaskApi.md#getHistoricTaskProcessInstances) | **GET** /v1/flow/history/tasks | 获取历史任务列表
*HistoryTaskApi* | [**getHistoryTaskIdentityLinks**](docs/HistoryTaskApi.md#getHistoryTaskIdentityLinks) | **GET** /v1/flow/history/tasks/{taskId}/identitylinks | 获取历史任务候选人或组信息
*HistoryTaskApi* | [**getTaskInstance**](docs/HistoryTaskApi.md#getTaskInstance) | **GET** /v1/flow/history/tasks/{taskId} | 获取历史任务信息
*QueryApi* | [**queryHistoricProcessInstances**](docs/QueryApi.md#queryHistoricProcessInstances) | **POST** /v1/flow/query/history/instances | 查询历史流程实例列表
*QueryApi* | [**queryHistoricTaskInstances**](docs/QueryApi.md#queryHistoricTaskInstances) | **POST** /v1/flow/query/history/tasks | 查询历史任务列表
*QueryApi* | [**queryProcessInstances**](docs/QueryApi.md#queryProcessInstances) | **POST** /v1/flow/query/runtime/instances | 查询流程实例列表
*QueryApi* | [**queryTasks**](docs/QueryApi.md#queryTasks) | **POST** /v1/flow/query/runtime/tasks | 查询待办列表
*RuntimeInstanceApi* | [**createProcessInstance**](docs/RuntimeInstanceApi.md#createProcessInstance) | **POST** /v1/flow/runtime/instances | 启动工作流实例
*RuntimeInstanceApi* | [**deleteProcessInstance**](docs/RuntimeInstanceApi.md#deleteProcessInstance) | **DELETE** /v1/flow/runtime/instances/{processInstanceId} | 删除工作流实例信息
*RuntimeInstanceApi* | [**getProcessInstance**](docs/RuntimeInstanceApi.md#getProcessInstance) | **GET** /v1/flow/runtime/instances/{processInstanceId} | 获取工作流实例信息
*RuntimeInstanceApi* | [**getProcessInstanceDiagram**](docs/RuntimeInstanceApi.md#getProcessInstanceDiagram) | **GET** /v1/flow/runtime/instances/{processInstanceId}/diagram | 获取流程实例图
*RuntimeInstanceApi* | [**getProcessInstances**](docs/RuntimeInstanceApi.md#getProcessInstances) | **GET** /v1/flow/runtime/instances | 工作流实例列表
*RuntimeInstanceApi* | [**goAnyWhere**](docs/RuntimeInstanceApi.md#goAnyWhere) | **POST** /v1/flow/runtime/instances/{processInstanceId}/goAnyWhere | 自由流
*RuntimeInstanceApi* | [**performProcessInstanceAction**](docs/RuntimeInstanceApi.md#performProcessInstanceAction) | **PUT** /v1/flow/runtime/instances/{processInstanceId} | 激活或挂起工作流实例
*RuntimeTaskApi* | [**createComment**](docs/RuntimeTaskApi.md#createComment) | **POST** /v1/flow/runtime/tasks/{taskId}/comments | 添加任务意见
*RuntimeTaskApi* | [**createTaskVariable**](docs/RuntimeTaskApi.md#createTaskVariable) | **POST** /v1/flow/runtime/tasks/{taskId}/variables | 批量添加任务流程变量
*RuntimeTaskApi* | [**deleteAllLocalTaskVariables**](docs/RuntimeTaskApi.md#deleteAllLocalTaskVariables) | **DELETE** /v1/flow/runtime/tasks/{taskId}/variables | 批量删除任务流程变量
*RuntimeTaskApi* | [**deleteComment**](docs/RuntimeTaskApi.md#deleteComment) | **DELETE** /v1/flow/runtime/tasks/{taskId}/comments/{commentId} | 删了某任务下某条意见
*RuntimeTaskApi* | [**deleteTask**](docs/RuntimeTaskApi.md#deleteTask) | **DELETE** /v1/flow/runtime/tasks/{taskId} | 删除任务
*RuntimeTaskApi* | [**executeTaskAction**](docs/RuntimeTaskApi.md#executeTaskAction) | **POST** /v1/flow/runtime/tasks/{taskId} | 处理任务
*RuntimeTaskApi* | [**getComment**](docs/RuntimeTaskApi.md#getComment) | **GET** /v1/flow/runtime/tasks/{taskId}/comments/{commentId} | 获取某任务下某条意见详细
*RuntimeTaskApi* | [**getComments**](docs/RuntimeTaskApi.md#getComments) | **GET** /v1/flow/runtime/tasks/{taskId}/comments | 获取某任务意见列表
*RuntimeTaskApi* | [**getTask**](docs/RuntimeTaskApi.md#getTask) | **GET** /v1/flow/runtime/tasks/{taskId} | 获取任务信息
*RuntimeTaskApi* | [**getTasks**](docs/RuntimeTaskApi.md#getTasks) | **GET** /v1/flow/runtime/tasks | 待办列表
*RuntimeTaskApi* | [**getVariable**](docs/RuntimeTaskApi.md#getVariable) | **GET** /v1/flow/runtime/tasks/{taskId}/variables/{variableName} | 获取某任务某流程变量
*RuntimeTaskApi* | [**getVariables**](docs/RuntimeTaskApi.md#getVariables) | **GET** /v1/flow/runtime/tasks/{taskId}/variables | 获取某任务流程变量集合
*RuntimeTaskApi* | [**updateTask**](docs/RuntimeTaskApi.md#updateTask) | **PUT** /v1/flow/runtime/tasks/{taskId} | 修改任务


## Documentation for Models

 - [PageListResponseWfHistoricProcessInstanceResponse](docs/PageListResponseWfHistoricProcessInstanceResponse.md)
 - [PageListResponseWfHistoricTaskInstanceResponse](docs/PageListResponseWfHistoricTaskInstanceResponse.md)
 - [PageListResponseWfProcessInstanceResponse](docs/PageListResponseWfProcessInstanceResponse.md)
 - [PageListResponseWfTaskResponse](docs/PageListResponseWfTaskResponse.md)
 - [WfActionResult](docs/WfActionResult.md)
 - [WfBusinessDataRequest](docs/WfBusinessDataRequest.md)
 - [WfCommentResponse](docs/WfCommentResponse.md)
 - [WfHistoricIdentityLinkResponse](docs/WfHistoricIdentityLinkResponse.md)
 - [WfHistoricProcessInstanceQueryRequest](docs/WfHistoricProcessInstanceQueryRequest.md)
 - [WfHistoricProcessInstanceResponse](docs/WfHistoricProcessInstanceResponse.md)
 - [WfHistoricTaskInstanceQueryRequest](docs/WfHistoricTaskInstanceQueryRequest.md)
 - [WfHistoricTaskInstanceResponse](docs/WfHistoricTaskInstanceResponse.md)
 - [WfJumpRequest](docs/WfJumpRequest.md)
 - [WfProcessInstanceActionRequest](docs/WfProcessInstanceActionRequest.md)
 - [WfProcessInstanceCreateRequest](docs/WfProcessInstanceCreateRequest.md)
 - [WfProcessInstanceQueryRequest](docs/WfProcessInstanceQueryRequest.md)
 - [WfProcessInstanceResponse](docs/WfProcessInstanceResponse.md)
 - [WfQueryVariable](docs/WfQueryVariable.md)
 - [WfRestVariable](docs/WfRestVariable.md)
 - [WfTaskActionRequest](docs/WfTaskActionRequest.md)
 - [WfTaskQueryRequest](docs/WfTaskQueryRequest.md)
 - [WfTaskRequest](docs/WfTaskRequest.md)
 - [WfTaskResponse](docs/WfTaskResponse.md)


## Documentation for Authorization

All endpoints do not require authorization.
Authentication schemes defined for the API:

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issue.

## Author



