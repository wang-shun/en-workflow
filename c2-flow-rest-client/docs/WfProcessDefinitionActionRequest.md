
# WfProcessDefinitionActionRequest

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**includeProcessInstances** | **Boolean** | 是否包含子流程实例 |  [optional]
**date** | [**Date**](Date.md) | 流程定义日期 |  [optional]
**category** | **String** | 流程定义类别 |  [optional]
**action** | [**ActionEnum**](#ActionEnum) | 流程状态类型 |  [optional]


<a name="ActionEnum"></a>
## Enum: ActionEnum
Name | Value
---- | -----
SUSPEND | &quot;SUSPEND&quot;
ACTIVATE | &quot;ACTIVATE&quot;



