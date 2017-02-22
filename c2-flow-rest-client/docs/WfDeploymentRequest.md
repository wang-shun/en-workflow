
# WfDeploymentRequest

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**currentLoginUserId** | **String** | 模拟当前登陆用户，需要后台启用开关(当流程独立部署且未集成sso环境时调试使用) |  [optional]
**deployName** | **String** | 部署名称(要以bpmn20.xml或bpmn结尾) | 
**category** | **String** | 部署类别 |  [optional]
**deployResource** | [**File**](File.md) | 要部署的流程定义文件 | 
**tenantId** | **String** | 租户(多应用共享流程中心时使用) |  [optional]



