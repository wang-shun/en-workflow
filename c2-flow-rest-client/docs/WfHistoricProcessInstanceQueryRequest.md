
# WfHistoricProcessInstanceQueryRequest

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**start** | **Integer** | 开始行 |  [optional]
**size** | **Integer** | 请求大小 |  [optional]
**sort** | **String** | 排序字段 |  [optional]
**order** | **String** | 排序方式asc/desc |  [optional]
**processInstanceId** | **String** | 流程实例d |  [optional]
**processInstanceIds** | **List&lt;String&gt;** | 流程实例集合 |  [optional]
**processBusinessKey** | **String** | 业务主键 |  [optional]
**processDefinitionId** | **String** | 流程定义id |  [optional]
**processDefinitionKey** | **String** | 流程定义key |  [optional]
**superProcessInstanceId** | **String** | 父流程id |  [optional]
**excludeSubprocesses** | **Boolean** | 排除拥有子流的 |  [optional]
**finished** | **Boolean** | 是否完成 |  [optional]
**involvedUser** | **String** | 流程参与者(处理人、候选人、所有者)  |  [optional]
**finishedAfter** | [**DateTime**](DateTime.md) | 完成时间晚于 |  [optional]
**finishedBefore** | [**DateTime**](DateTime.md) | 完成时间早于 |  [optional]
**startedAfter** | [**DateTime**](DateTime.md) | 开始时间晚于 |  [optional]
**startedBefore** | [**DateTime**](DateTime.md) | 开始时间早于 |  [optional]
**startedBy** | **String** | 流程启动者 |  [optional]
**includeProcessVariables** | **Boolean** | 是否包含流程变量 |  [optional]
**variables** | [**List&lt;WfQueryVariable&gt;**](WfQueryVariable.md) | 流程自定义变量条件 |  [optional]
**tenantId** | **String** | 租户 |  [optional]
**tenantIdLike** | **String** | 租户模糊匹配 |  [optional]
**withoutTenantId** | **Boolean** | 是否启用租户过滤 |  [optional]



