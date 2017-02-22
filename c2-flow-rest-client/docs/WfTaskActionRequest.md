
# WfTaskActionRequest

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**currentLoginUserId** | **String** | 模拟当前登陆用户，需要后台启用开关(当流程独立部署且未集成sso环境时调试使用) |  [optional]
**action** | [**ActionEnum**](#ActionEnum) | 处理任务动作类型 | 
**assignee** | **String** | 处理人(DELEGATE操作类型时必传) |  [optional]
**variables** | [**List&lt;WfRestVariable&gt;**](WfRestVariable.md) | 操作任务所需流程变量 |  [optional]


<a name="ActionEnum"></a>
## Enum: ActionEnum
Name | Value
---- | -----
CLAIM | &quot;CLAIM&quot;
COMPLETE | &quot;COMPLETE&quot;
CLAIM_COMPLETE | &quot;CLAIM_COMPLETE&quot;
DELEGATE | &quot;DELEGATE&quot;
RESOLVE | &quot;RESOLVE&quot;
REJECT | &quot;REJECT&quot;



