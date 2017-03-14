
# WfJumpRequest

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**currentLoginUserId** | **String** | 模拟当前登陆用户，需要后台启用开关(当流程独立部署且未集成sso环境时调试使用) |  [optional]
**processInstanceId** | **String** | 流程实例id |  [optional]
**destTaskDefinitionKey** | **String** | 目标环节key |  [optional]
**variables** | [**List&lt;WfRestVariable&gt;**](WfRestVariable.md) | 流程变量 |  [optional]



