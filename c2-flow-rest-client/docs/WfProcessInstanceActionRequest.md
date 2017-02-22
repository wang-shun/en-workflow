
# WfProcessInstanceActionRequest

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**currentLoginUserId** | **String** | 模拟当前登陆用户，需要后台启用开关(当流程独立部署且未集成sso环境时调试使用) |  [optional]
**action** | [**ActionEnum**](#ActionEnum) | 流程实例操作 | 


<a name="ActionEnum"></a>
## Enum: ActionEnum
Name | Value
---- | -----
SUSPEND | &quot;SUSPEND&quot;
ACTIVATE | &quot;ACTIVATE&quot;



