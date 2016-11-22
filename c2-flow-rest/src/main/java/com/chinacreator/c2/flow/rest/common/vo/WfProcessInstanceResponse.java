package com.chinacreator.c2.flow.rest.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hushowly
 */
@ApiModel(value="WfProcessInstanceResponse", description="流程实例信息")
public class WfProcessInstanceResponse {
  @ApiModelProperty(value = "流程实例id")
  protected String id;
  @ApiModelProperty(value = "业务主键")
  protected String businessKey;
  @ApiModelProperty(value = "流程实例是否挂起")
  protected boolean suspended;
  @ApiModelProperty(value = "流程定义id")
  protected String processDefinitionId;
  @ApiModelProperty(value = "活动id")
  protected String activityId;
  @ApiModelProperty(value = "流程实例的自定义变量集合")
  protected List<WfRestVariable> variables = new ArrayList<WfRestVariable>();
  @ApiModelProperty(value = "租户")
  protected String tenantId;
  
  public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  
  public String getBusinessKey() {
    return businessKey;
  }
  
  public void setBusinessKey(String businessKey) {
    this.businessKey = businessKey;
  }
  
  public boolean isSuspended() {
    return suspended;
  }
  
  public void setSuspended(boolean suspended) {
    this.suspended = suspended;
  }
  
  public String getProcessDefinitionId() {
    return processDefinitionId;
  }

  public void setProcessDefinitionId(String processDefinitionId) {
    this.processDefinitionId = processDefinitionId;
  }

  
  public String getActivityId() {
    return activityId;
  }
  
  public void setActivityId(String activityId) {
    this.activityId = activityId;
  }
  
  public List<WfRestVariable> getVariables() {
    return variables;
  }
  
  public void setVariables(List<WfRestVariable> variables) {
    this.variables = variables;
  }
  
  public void addVariable(WfRestVariable variable) {
    variables.add(variable);
  }
  
  public void setTenantId(String tenantId) {
	  this.tenantId = tenantId;
  }
  
  public String getTenantId() {
	  return tenantId;
  }
}
