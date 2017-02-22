package com.chinacreator.c2.flow.rest.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import org.activiti.rest.service.api.engine.variable.RestVariable;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;


/**
 * @author hushow
 */
@ApiModel(value="WfProcessInstanceCreateRequest",description="创建流程实例入参")
public class WfProcessInstanceCreateRequest extends WfBaseRequest{
 
  /**
  * 统一任务业务数据，必须参数
  */
  @ApiModelProperty
  private WfBusinessDataRequest businessData;
  
  @ApiModelProperty("根据流程定义id来启动流程，此时不再传processDefinitionKey")
  private String processDefinitionId;
  @ApiModelProperty("根据流程定义key来启动流程(自动使用最新版本)，此时不再传processDefinitionId")
  private String processDefinitionKey;
  @ApiModelProperty("启动流程需要的自定义变量")
  private List<WfRestVariable> variables;
  @ApiModelProperty("租户")
  protected String tenantId;
  

public String getProcessDefinitionId() {
    return processDefinitionId;
  }
  
  public void setProcessDefinitionId(String processDefinitionId) {
    this.processDefinitionId = processDefinitionId;
  }
  
  public String getProcessDefinitionKey() {
    return processDefinitionKey;
  }
  
  public void setProcessDefinitionKey(String processDefinitionKey) {
    this.processDefinitionKey = processDefinitionKey;
  }
  
  public void setTenantId(String tenantId) {
	  this.tenantId = tenantId;
  }
  
  public String getTenantId() {
	  return tenantId;
  }
  
  @JsonTypeInfo(use=Id.CLASS, defaultImpl=RestVariable.class)  
  public List<WfRestVariable> getVariables() {
    return variables;
  }
  
  public void setVariables(List<WfRestVariable> variables) {
    this.variables = variables;
  }

public WfBusinessDataRequest getBusinessData() {
	return businessData;
}

public void setBusinessData(WfBusinessDataRequest businessData) {
	this.businessData = businessData;
}
  
  
  
}
