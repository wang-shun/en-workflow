
# WfProcessInstanceQueryRequest

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**start** | **Integer** | 开始行 |  [optional]
**size** | **Integer** | 请求大小 |  [optional]
**sort** | **String** | 排序字段 |  [optional]
**order** | **String** | 排序方式asc/desc |  [optional]
**processInstanceId** | **String** | 流程实例id |  [optional]
**processBusinessKey** | **String** | 业务主键 |  [optional]
**processDefinitionId** | **String** | 流程定义id(每次流程部署后会产生一个带版本号新的id) |  [optional]
**processDefinitionKey** | **String** | 流程定义key(流程设计时产生) |  [optional]
**superProcessInstanceId** | **String** | 父流程实例id |  [optional]
**subProcessInstanceId** | **String** | 子流程实例id |  [optional]
**excludeSubprocesses** | **Boolean** | 是否排除子流程 |  [optional]
**involvedUser** | **String** | 流程参与者(处理人、候选人、所有者) |  [optional]
**suspended** | **Boolean** | 是否挂起 |  [optional]
**includeProcessVariables** | **Boolean** | 是否查询流程变量 |  [optional]
**variables** | [**List&lt;WfQueryVariable&gt;**](WfQueryVariable.md) | 根据流程变量过滤 |  [optional]
**tenantId** | **String** | 租户 |  [optional]
**tenantIdLike** | **String** | 租户模糊 |  [optional]
**withoutTenantId** | **Boolean** | 是否禁用租户过滤 |  [optional]



