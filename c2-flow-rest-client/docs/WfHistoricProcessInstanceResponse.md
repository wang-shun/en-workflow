
# WfHistoricProcessInstanceResponse

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **String** | 流程实例id |  [optional]
**businessKey** | **String** | 业务主键 |  [optional]
**processDefinitionId** | **String** | 流程定义id |  [optional]
**startTime** | [**Date**](Date.md) | 流程实例开始时间 |  [optional]
**endTime** | [**Date**](Date.md) | 流程实例结束时间 |  [optional]
**durationInMillis** | **Long** | 流程实例总共花费毫秒数 |  [optional]
**startUserId** | **String** | 流程实例启动用户 |  [optional]
**startActivityId** | **String** | 流程实例开始环节id |  [optional]
**endActivityId** | **String** | 流程实例结束环节id |  [optional]
**deleteReason** | **String** | 流程实例删除原因 |  [optional]
**superProcessInstanceId** | **String** | 父流程实例id |  [optional]
**variables** | [**List&lt;WfRestVariable&gt;**](WfRestVariable.md) | 流程实例自定义变量集合 |  [optional]
**tenantId** | **String** | 租户 |  [optional]



