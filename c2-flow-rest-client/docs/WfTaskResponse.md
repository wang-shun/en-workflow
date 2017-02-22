
# WfTaskResponse

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | 任务id |  [optional]
**owner** | **String** | 任务所有者 |  [optional]
**assignee** | **String** | 任务处理人 |  [optional]
**delegationState** | **String** | 任务代理状态 |  [optional]
**name** | **String** | 任务名称 |  [optional]
**description** | **String** | 任务描述 |  [optional]
**createTime** | [**Date**](Date.md) | 任务创建时间 |  [optional]
**dueDate** | [**Date**](Date.md) | 任务过期时间 |  [optional]
**priority** | **Integer** | 任务优先级 |  [optional]
**suspended** | **Boolean** | 任务是否挂起 |  [optional]
**taskDefinitionKey** | **String** | 任务环节定义key |  [optional]
**tenantId** | **String** | 租户 |  [optional]
**category** | **String** | 类别 |  [optional]
**parentTaskId** | **String** | 父任务id |  [optional]
**executionId** | **String** | 任务执行id |  [optional]
**processInstanceId** | **String** | 任务所在流程实例id |  [optional]
**processDefinitionId** | **String** | 任务所在流程定义id |  [optional]
**variables** | [**List&lt;WfRestVariable&gt;**](WfRestVariable.md) | 当前任务的自定义变量集合 |  [optional]



