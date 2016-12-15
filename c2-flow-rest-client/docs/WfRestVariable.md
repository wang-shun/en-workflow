
# WfRestVariable

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**name** | **String** | 变量名 |  [optional]
**type** | [**TypeEnum**](#TypeEnum) | 变量类型 |  [optional]
**variableScope** | [**VariableScopeEnum**](#VariableScopeEnum) | 变量作用域 |  [optional]
**value** | **Object** | 变量值 |  [optional]


<a name="TypeEnum"></a>
## Enum: TypeEnum
Name | Value
---- | -----
STRING | &quot;string&quot;
BOOLEAN | &quot;boolean&quot;
DATE | &quot;date&quot;
DOUBLE | &quot;double&quot;
INTEGER | &quot;integer&quot;
LONG | &quot;long&quot;
SHORT | &quot;short&quot;
JAVA_UTIL_LIST | &quot;java.util.List&quot;
JAVA_UTIL_MAP | &quot;java.util.Map&quot;


<a name="VariableScopeEnum"></a>
## Enum: VariableScopeEnum
Name | Value
---- | -----
LOCAL | &quot;LOCAL&quot;
GLOBAL | &quot;GLOBAL&quot;



