/**
 * 工作流接口文档
 * 工作流接口文档
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.chinacreator.c2.flow.rest.model;

import java.util.Objects;
import com.chinacreator.c2.flow.rest.model.WfRestVariable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 历史流程实例信息
 */
@ApiModel(description = "历史流程实例信息")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-12-28T13:50:28.213+08:00")
public class WfHistoricProcessInstanceResponse   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("businessKey")
  private String businessKey = null;

  @JsonProperty("processDefinitionId")
  private String processDefinitionId = null;

  @JsonProperty("startTime")
  private Date startTime = null;

  @JsonProperty("endTime")
  private Date endTime = null;

  @JsonProperty("durationInMillis")
  private Long durationInMillis = null;

  @JsonProperty("startUserId")
  private String startUserId = null;

  @JsonProperty("startActivityId")
  private String startActivityId = null;

  @JsonProperty("endActivityId")
  private String endActivityId = null;

  @JsonProperty("deleteReason")
  private String deleteReason = null;

  @JsonProperty("superProcessInstanceId")
  private String superProcessInstanceId = null;

  @JsonProperty("variables")
  private List<WfRestVariable> variables = new ArrayList<WfRestVariable>();

  @JsonProperty("tenantId")
  private String tenantId = null;

  public WfHistoricProcessInstanceResponse id(String id) {
    this.id = id;
    return this;
  }

   /**
   * 流程实例id
   * @return id
  **/
  @ApiModelProperty(example = "null", value = "流程实例id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public WfHistoricProcessInstanceResponse businessKey(String businessKey) {
    this.businessKey = businessKey;
    return this;
  }

   /**
   * 业务主键
   * @return businessKey
  **/
  @ApiModelProperty(example = "null", value = "业务主键")
  public String getBusinessKey() {
    return businessKey;
  }

  public void setBusinessKey(String businessKey) {
    this.businessKey = businessKey;
  }

  public WfHistoricProcessInstanceResponse processDefinitionId(String processDefinitionId) {
    this.processDefinitionId = processDefinitionId;
    return this;
  }

   /**
   * 流程定义id
   * @return processDefinitionId
  **/
  @ApiModelProperty(example = "null", value = "流程定义id")
  public String getProcessDefinitionId() {
    return processDefinitionId;
  }

  public void setProcessDefinitionId(String processDefinitionId) {
    this.processDefinitionId = processDefinitionId;
  }

  public WfHistoricProcessInstanceResponse startTime(Date startTime) {
    this.startTime = startTime;
    return this;
  }

   /**
   * 流程实例开始时间
   * @return startTime
  **/
  @ApiModelProperty(example = "null", value = "流程实例开始时间")
  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public WfHistoricProcessInstanceResponse endTime(Date endTime) {
    this.endTime = endTime;
    return this;
  }

   /**
   * 流程实例结束时间
   * @return endTime
  **/
  @ApiModelProperty(example = "null", value = "流程实例结束时间")
  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public WfHistoricProcessInstanceResponse durationInMillis(Long durationInMillis) {
    this.durationInMillis = durationInMillis;
    return this;
  }

   /**
   * 流程实例总共花费毫秒数
   * @return durationInMillis
  **/
  @ApiModelProperty(example = "null", value = "流程实例总共花费毫秒数")
  public Long getDurationInMillis() {
    return durationInMillis;
  }

  public void setDurationInMillis(Long durationInMillis) {
    this.durationInMillis = durationInMillis;
  }

  public WfHistoricProcessInstanceResponse startUserId(String startUserId) {
    this.startUserId = startUserId;
    return this;
  }

   /**
   * 流程实例启动用户
   * @return startUserId
  **/
  @ApiModelProperty(example = "null", value = "流程实例启动用户")
  public String getStartUserId() {
    return startUserId;
  }

  public void setStartUserId(String startUserId) {
    this.startUserId = startUserId;
  }

  public WfHistoricProcessInstanceResponse startActivityId(String startActivityId) {
    this.startActivityId = startActivityId;
    return this;
  }

   /**
   * 流程实例开始环节id
   * @return startActivityId
  **/
  @ApiModelProperty(example = "null", value = "流程实例开始环节id")
  public String getStartActivityId() {
    return startActivityId;
  }

  public void setStartActivityId(String startActivityId) {
    this.startActivityId = startActivityId;
  }

  public WfHistoricProcessInstanceResponse endActivityId(String endActivityId) {
    this.endActivityId = endActivityId;
    return this;
  }

   /**
   * 流程实例结束环节id
   * @return endActivityId
  **/
  @ApiModelProperty(example = "null", value = "流程实例结束环节id")
  public String getEndActivityId() {
    return endActivityId;
  }

  public void setEndActivityId(String endActivityId) {
    this.endActivityId = endActivityId;
  }

  public WfHistoricProcessInstanceResponse deleteReason(String deleteReason) {
    this.deleteReason = deleteReason;
    return this;
  }

   /**
   * 流程实例删除原因
   * @return deleteReason
  **/
  @ApiModelProperty(example = "null", value = "流程实例删除原因")
  public String getDeleteReason() {
    return deleteReason;
  }

  public void setDeleteReason(String deleteReason) {
    this.deleteReason = deleteReason;
  }

  public WfHistoricProcessInstanceResponse superProcessInstanceId(String superProcessInstanceId) {
    this.superProcessInstanceId = superProcessInstanceId;
    return this;
  }

   /**
   * 父流程实例id
   * @return superProcessInstanceId
  **/
  @ApiModelProperty(example = "null", value = "父流程实例id")
  public String getSuperProcessInstanceId() {
    return superProcessInstanceId;
  }

  public void setSuperProcessInstanceId(String superProcessInstanceId) {
    this.superProcessInstanceId = superProcessInstanceId;
  }

  public WfHistoricProcessInstanceResponse variables(List<WfRestVariable> variables) {
    this.variables = variables;
    return this;
  }

  public WfHistoricProcessInstanceResponse addVariablesItem(WfRestVariable variablesItem) {
    this.variables.add(variablesItem);
    return this;
  }

   /**
   * 流程实例自定义变量集合
   * @return variables
  **/
  @ApiModelProperty(example = "null", value = "流程实例自定义变量集合")
  public List<WfRestVariable> getVariables() {
    return variables;
  }

  public void setVariables(List<WfRestVariable> variables) {
    this.variables = variables;
  }

  public WfHistoricProcessInstanceResponse tenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

   /**
   * 租户
   * @return tenantId
  **/
  @ApiModelProperty(example = "null", value = "租户")
  public String getTenantId() {
    return tenantId;
  }

  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WfHistoricProcessInstanceResponse wfHistoricProcessInstanceResponse = (WfHistoricProcessInstanceResponse) o;
    return Objects.equals(this.id, wfHistoricProcessInstanceResponse.id) &&
        Objects.equals(this.businessKey, wfHistoricProcessInstanceResponse.businessKey) &&
        Objects.equals(this.processDefinitionId, wfHistoricProcessInstanceResponse.processDefinitionId) &&
        Objects.equals(this.startTime, wfHistoricProcessInstanceResponse.startTime) &&
        Objects.equals(this.endTime, wfHistoricProcessInstanceResponse.endTime) &&
        Objects.equals(this.durationInMillis, wfHistoricProcessInstanceResponse.durationInMillis) &&
        Objects.equals(this.startUserId, wfHistoricProcessInstanceResponse.startUserId) &&
        Objects.equals(this.startActivityId, wfHistoricProcessInstanceResponse.startActivityId) &&
        Objects.equals(this.endActivityId, wfHistoricProcessInstanceResponse.endActivityId) &&
        Objects.equals(this.deleteReason, wfHistoricProcessInstanceResponse.deleteReason) &&
        Objects.equals(this.superProcessInstanceId, wfHistoricProcessInstanceResponse.superProcessInstanceId) &&
        Objects.equals(this.variables, wfHistoricProcessInstanceResponse.variables) &&
        Objects.equals(this.tenantId, wfHistoricProcessInstanceResponse.tenantId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, businessKey, processDefinitionId, startTime, endTime, durationInMillis, startUserId, startActivityId, endActivityId, deleteReason, superProcessInstanceId, variables, tenantId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WfHistoricProcessInstanceResponse {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    businessKey: ").append(toIndentedString(businessKey)).append("\n");
    sb.append("    processDefinitionId: ").append(toIndentedString(processDefinitionId)).append("\n");
    sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
    sb.append("    endTime: ").append(toIndentedString(endTime)).append("\n");
    sb.append("    durationInMillis: ").append(toIndentedString(durationInMillis)).append("\n");
    sb.append("    startUserId: ").append(toIndentedString(startUserId)).append("\n");
    sb.append("    startActivityId: ").append(toIndentedString(startActivityId)).append("\n");
    sb.append("    endActivityId: ").append(toIndentedString(endActivityId)).append("\n");
    sb.append("    deleteReason: ").append(toIndentedString(deleteReason)).append("\n");
    sb.append("    superProcessInstanceId: ").append(toIndentedString(superProcessInstanceId)).append("\n");
    sb.append("    variables: ").append(toIndentedString(variables)).append("\n");
    sb.append("    tenantId: ").append(toIndentedString(tenantId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

