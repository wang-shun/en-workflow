package com.chinacreator.c2.flow.rest.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import org.activiti.rest.service.api.engine.variable.QueryVariable;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;


/**
 * @author hushow
 */
@ApiModel(value="WfProcessInstanceQueryRequest",description="流程实例查询参数")
public class WfProcessInstanceQueryRequest extends WfPaginateRequest {

  @ApiModelProperty("流程实例id")
  private String processInstanceId;
  @ApiModelProperty("业务主键")
  private String processBusinessKey;
  @ApiModelProperty("流程定义id(每次流程部署后会产生一个带版本号新的id)")
  private String processDefinitionId;
  @ApiModelProperty("流程定义key(流程设计时产生)")
  private String processDefinitionKey;
  @ApiModelProperty("父流程实例id")
  private String superProcessInstanceId;
  @ApiModelProperty("子流程实例id")
  private String subProcessInstanceId;
  @ApiModelProperty("是否排除子流程")
  private Boolean excludeSubprocesses;
  @ApiModelProperty("流程参与者(处理人、候选人、所有者)")
  private String involvedUser;
  @ApiModelProperty("是否挂起")
  private Boolean suspended;
  @ApiModelProperty("是否查询流程变量")
  private Boolean includeProcessVariables;
  @ApiModelProperty("根据流程变量过滤")
  private List<WfQueryVariable> variables;
  @ApiModelProperty("租户")
  private String tenantId;
  @ApiModelProperty("租户模糊")
  private String tenantIdLike;
  @ApiModelProperty("是否禁用租户过滤")
  private Boolean withoutTenantId;
  
  public String getProcessInstanceId() {
    return processInstanceId;
  }
  
  public void setProcessInstanceId(String processInstanceId) {
    this.processInstanceId = processInstanceId;
  }
  
  public String getProcessBusinessKey() {
    return processBusinessKey;
  }
  
  public void setProcessBusinessKey(String processBusinessKey) {
    this.processBusinessKey = processBusinessKey;
  }
  
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
  
  public String getSuperProcessInstanceId() {
    return superProcessInstanceId;
  }
  
  public void setSuperProcessInstanceId(String superProcessInstanceId) {
    this.superProcessInstanceId = superProcessInstanceId;
  }
  
  public String getSubProcessInstanceId() {
    return subProcessInstanceId;
  }
  
  public void setSubProcessInstanceId(String subProcessInstanceId) {
    this.subProcessInstanceId = subProcessInstanceId;
  }
  
  public Boolean getExcludeSubprocesses() {
    return excludeSubprocesses;
  }

  public void setExcludeSubprocesses(Boolean excludeSubprocesses) {
    this.excludeSubprocesses = excludeSubprocesses;
  }

  public String getInvolvedUser() {
    return involvedUser;
  }
  
  public void setInvolvedUser(String involvedUser) {
    this.involvedUser = involvedUser;
  }
  
  public Boolean getSuspended() {
    return suspended;
  }
  
  public void setSuspended(Boolean suspended) {
    this.suspended = suspended;
  }
  
  public Boolean getIncludeProcessVariables() {
    return includeProcessVariables;
  }

  public void setIncludeProcessVariables(Boolean includeProcessVariables) {
    this.includeProcessVariables = includeProcessVariables;
  }

  @JsonTypeInfo(use=Id.CLASS, defaultImpl=WfQueryVariable.class)  
  public List<WfQueryVariable> getVariables() {
    return variables;
  }
  
  public void setVariables(List<WfQueryVariable> variables) {
    this.variables = variables;
  }
  
  public void setTenantId(String tenantId) {
	  this.tenantId = tenantId;
  }
  
  public String getTenantId() {
	  return tenantId;
  }
  
  public void setWithoutTenantId(Boolean withoutTenantId) {
	  this.withoutTenantId = withoutTenantId;
  }
  
  public Boolean getWithoutTenantId() {
	  return withoutTenantId;
  }
  
  public String getTenantIdLike() {
	  return tenantIdLike;
  }
  
  public void setTenantIdLike(String tenantIdLike) {
	  this.tenantIdLike = tenantIdLike;
  }
}
