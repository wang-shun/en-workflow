
# WfProcessInstanceCreateRequest

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**currentLoginUserId** | **String** | 模拟当前登陆用户，需要后台启用开关(当流程独立部署且未集成sso环境时调试使用) |  [optional]
**businessData** | [**WfBusinessDataRequest**](WfBusinessDataRequest.md) |  |  [optional]
**processDefinitionId** | **String** | 根据流程定义id来启动流程，此时不再传processDefinitionKey |  [optional]
**processDefinitionKey** | **String** | 根据流程定义key来启动流程(自动使用最新版本)，此时不再传processDefinitionId |  [optional]
**variables** | [**List&lt;WfRestVariable&gt;**](WfRestVariable.md) | 启动流程需要的自定义变量 |  [optional]
**tenantId** | **String** | 租户 |  [optional]



