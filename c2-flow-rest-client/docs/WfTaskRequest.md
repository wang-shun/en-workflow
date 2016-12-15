
# WfTaskRequest

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**currentLoginUserId** | **String** | 模拟当前登陆用户，需要后台启用开关(当流程独立部署且未集成sso环境时调试使用) |  [optional]
**owner** | **String** | 任务所有者 |  [optional]
**assignee** | **String** | 任务处理人 |  [optional]
**delegationState** | [**DelegationStateEnum**](#DelegationStateEnum) | 任务委托状态 |  [optional]
**name** | **String** | 任务名称 |  [optional]
**description** | **String** | 任务描述 |  [optional]
**dueDate** | [**Date**](Date.md) | 任务过期时间 |  [optional]
**priority** | **Integer** | 任务优先级 |  [optional]
**parentTaskId** | **String** | 父任务id |  [optional]
**category** | **String** | 任务类别 |  [optional]


<a name="DelegationStateEnum"></a>
## Enum: DelegationStateEnum
Name | Value
---- | -----
PENDING | &quot;PENDING&quot;
RESOLVED | &quot;RESOLVED&quot;



