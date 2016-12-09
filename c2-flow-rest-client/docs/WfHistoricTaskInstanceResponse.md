
# WfHistoricTaskInstanceResponse

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | 任务id |  [optional]
**processDefinitionId** | **String** | 任务所在流程定义id |  [optional]
**processInstanceId** | **String** | 任务所在流程实例id |  [optional]
**executionId** | **String** | 任务执行id |  [optional]
**name** | **String** | 任务名称 |  [optional]
**description** | **String** | 任务描述 |  [optional]
**deleteReason** | **String** | 任务删除原因 |  [optional]
**owner** | **String** | 任务所有者 |  [optional]
**assignee** | **String** | 任务处理人 |  [optional]
**startTime** | [**DateTime**](DateTime.md) | 任务开始时间 |  [optional]
**endTime** | [**DateTime**](DateTime.md) | 任务结束时间 |  [optional]
**durationInMillis** | **Long** | 任务花费毫秒数 |  [optional]
**workTimeInMillis** | **Long** | 任务花费毫秒数 |  [optional]
**claimTime** | [**DateTime**](DateTime.md) | 任务签收时间 |  [optional]
**taskDefinitionKey** | **String** | 任务所在环节定义key |  [optional]
**formKey** | **String** | 任务表单key |  [optional]
**priority** | **Integer** | 任务优先级 |  [optional]
**dueDate** | [**DateTime**](DateTime.md) | 任务过期时间 |  [optional]
**parentTaskId** | **String** | 父任务id |  [optional]
**variables** | [**List&lt;WfRestVariable&gt;**](WfRestVariable.md) | 任务自定义变量集合 |  [optional]
**tenantId** | **String** | 租户 |  [optional]
**category** | **String** | 业务类别 |  [optional]



