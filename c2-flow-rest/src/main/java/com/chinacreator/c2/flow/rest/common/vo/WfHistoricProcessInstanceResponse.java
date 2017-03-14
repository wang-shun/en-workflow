package com.chinacreator.c2.flow.rest.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author hushowly
 */
@ApiModel(value="WfHistoricProcessInstanceResponse",description="历史流程实例信息")
public class WfHistoricProcessInstanceResponse {
  @ApiModelProperty(value="流程实例id")
  protected String id;
  @ApiModelProperty(value="业务主键")
  protected String businessKey;
  @ApiModelProperty(value="流程定义id")
  protected String processDefinitionId;
  @ApiModelProperty(value="流程实例开始时间")
  protected Date startTime;
  @ApiModelProperty(value="流程实例结束时间")
  protected Date endTime;
  @ApiModelProperty(value="流程实例总共花费毫秒数")
  protected Long durationInMillis;
  @ApiModelProperty(value="流程实例启动用户")
  protected String startUserId;
  @ApiModelProperty(value="流程实例开始环节id")
  protected String startActivityId;
  @ApiModelProperty(value="流程实例结束环节id")
  protected String endActivityId;
  @ApiModelProperty(value="流程实例删除原因")
  protected String deleteReason;
  @ApiModelProperty(value="父流程实例id")
  protected String superProcessInstanceId;
  @ApiModelProperty(value="流程实例自定义变量集合")
  protected List<WfRestVariable> variables = new ArrayList<WfRestVariable>();
  @ApiModelProperty(value="租户")
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
  public String getProcessDefinitionId() {
    return processDefinitionId;
  }
  public void setProcessDefinitionId(String processDefinitionId) {
    this.processDefinitionId = processDefinitionId;
  }
  public Date getStartTime() {
    return startTime;
  }
  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }
  public Date getEndTime() {
    return endTime;
  }
  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }
  public Long getDurationInMillis() {
    return durationInMillis;
  }
  public void setDurationInMillis(Long durationInMillis) {
    this.durationInMillis = durationInMillis;
  }
  public String getStartUserId() {
    return startUserId;
  }
  public void setStartUserId(String startUserId) {
    this.startUserId = startUserId;
  }
  public String getStartActivityId() {
    return startActivityId;
  }
  public void setStartActivityId(String startActivityId) {
    this.startActivityId = startActivityId;
  }
  public String getEndActivityId() {
    return endActivityId;
  }
  public void setEndActivityId(String endActivityId) {
    this.endActivityId = endActivityId;
  }
  public String getDeleteReason() {
    return deleteReason;
  }
  public void setDeleteReason(String deleteReason) {
    this.deleteReason = deleteReason;
  }
  public String getSuperProcessInstanceId() {
    return superProcessInstanceId;
  }
  public void setSuperProcessInstanceId(String superProcessInstanceId) {
    this.superProcessInstanceId = superProcessInstanceId;
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
