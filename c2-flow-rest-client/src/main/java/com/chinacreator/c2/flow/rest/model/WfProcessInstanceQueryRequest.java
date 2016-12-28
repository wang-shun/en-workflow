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
import com.chinacreator.c2.flow.rest.model.WfQueryVariable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;


/**
 * 流程实例查询参数
 */
@ApiModel(description = "流程实例查询参数")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-12-28T13:50:28.213+08:00")
public class WfProcessInstanceQueryRequest   {
  @JsonProperty("start")
  private Integer start = null;

  @JsonProperty("size")
  private Integer size = null;

  @JsonProperty("sort")
  private String sort = null;

  @JsonProperty("order")
  private String order = null;

  @JsonProperty("processInstanceId")
  private String processInstanceId = null;

  @JsonProperty("processBusinessKey")
  private String processBusinessKey = null;

  @JsonProperty("processDefinitionId")
  private String processDefinitionId = null;

  @JsonProperty("processDefinitionKey")
  private String processDefinitionKey = null;

  @JsonProperty("superProcessInstanceId")
  private String superProcessInstanceId = null;

  @JsonProperty("subProcessInstanceId")
  private String subProcessInstanceId = null;

  @JsonProperty("excludeSubprocesses")
  private Boolean excludeSubprocesses = null;

  @JsonProperty("involvedUser")
  private String involvedUser = null;

  @JsonProperty("suspended")
  private Boolean suspended = null;

  @JsonProperty("includeProcessVariables")
  private Boolean includeProcessVariables = null;

  @JsonProperty("variables")
  private List<WfQueryVariable> variables = new ArrayList<WfQueryVariable>();

  @JsonProperty("tenantId")
  private String tenantId = null;

  @JsonProperty("tenantIdLike")
  private String tenantIdLike = null;

  @JsonProperty("withoutTenantId")
  private Boolean withoutTenantId = null;

  public WfProcessInstanceQueryRequest start(Integer start) {
    this.start = start;
    return this;
  }

   /**
   * 开始行
   * @return start
  **/
  @ApiModelProperty(example = "null", value = "开始行")
  public Integer getStart() {
    return start;
  }

  public void setStart(Integer start) {
    this.start = start;
  }

  public WfProcessInstanceQueryRequest size(Integer size) {
    this.size = size;
    return this;
  }

   /**
   * 请求大小
   * @return size
  **/
  @ApiModelProperty(example = "null", value = "请求大小")
  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public WfProcessInstanceQueryRequest sort(String sort) {
    this.sort = sort;
    return this;
  }

   /**
   * 排序字段
   * @return sort
  **/
  @ApiModelProperty(example = "null", value = "排序字段")
  public String getSort() {
    return sort;
  }

  public void setSort(String sort) {
    this.sort = sort;
  }

  public WfProcessInstanceQueryRequest order(String order) {
    this.order = order;
    return this;
  }

   /**
   * 排序方式asc/desc
   * @return order
  **/
  @ApiModelProperty(example = "null", value = "排序方式asc/desc")
  public String getOrder() {
    return order;
  }

  public void setOrder(String order) {
    this.order = order;
  }

  public WfProcessInstanceQueryRequest processInstanceId(String processInstanceId) {
    this.processInstanceId = processInstanceId;
    return this;
  }

   /**
   * 流程实例id
   * @return processInstanceId
  **/
  @ApiModelProperty(example = "null", value = "流程实例id")
  public String getProcessInstanceId() {
    return processInstanceId;
  }

  public void setProcessInstanceId(String processInstanceId) {
    this.processInstanceId = processInstanceId;
  }

  public WfProcessInstanceQueryRequest processBusinessKey(String processBusinessKey) {
    this.processBusinessKey = processBusinessKey;
    return this;
  }

   /**
   * 业务主键
   * @return processBusinessKey
  **/
  @ApiModelProperty(example = "null", value = "业务主键")
  public String getProcessBusinessKey() {
    return processBusinessKey;
  }

  public void setProcessBusinessKey(String processBusinessKey) {
    this.processBusinessKey = processBusinessKey;
  }

  public WfProcessInstanceQueryRequest processDefinitionId(String processDefinitionId) {
    this.processDefinitionId = processDefinitionId;
    return this;
  }

   /**
   * 流程定义id(每次流程部署后会产生一个带版本号新的id)
   * @return processDefinitionId
  **/
  @ApiModelProperty(example = "null", value = "流程定义id(每次流程部署后会产生一个带版本号新的id)")
  public String getProcessDefinitionId() {
    return processDefinitionId;
  }

  public void setProcessDefinitionId(String processDefinitionId) {
    this.processDefinitionId = processDefinitionId;
  }

  public WfProcessInstanceQueryRequest processDefinitionKey(String processDefinitionKey) {
    this.processDefinitionKey = processDefinitionKey;
    return this;
  }

   /**
   * 流程定义key(流程设计时产生)
   * @return processDefinitionKey
  **/
  @ApiModelProperty(example = "null", value = "流程定义key(流程设计时产生)")
  public String getProcessDefinitionKey() {
    return processDefinitionKey;
  }

  public void setProcessDefinitionKey(String processDefinitionKey) {
    this.processDefinitionKey = processDefinitionKey;
  }

  public WfProcessInstanceQueryRequest superProcessInstanceId(String superProcessInstanceId) {
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

  public WfProcessInstanceQueryRequest subProcessInstanceId(String subProcessInstanceId) {
    this.subProcessInstanceId = subProcessInstanceId;
    return this;
  }

   /**
   * 子流程实例id
   * @return subProcessInstanceId
  **/
  @ApiModelProperty(example = "null", value = "子流程实例id")
  public String getSubProcessInstanceId() {
    return subProcessInstanceId;
  }

  public void setSubProcessInstanceId(String subProcessInstanceId) {
    this.subProcessInstanceId = subProcessInstanceId;
  }

  public WfProcessInstanceQueryRequest excludeSubprocesses(Boolean excludeSubprocesses) {
    this.excludeSubprocesses = excludeSubprocesses;
    return this;
  }

   /**
   * 是否排除子流程
   * @return excludeSubprocesses
  **/
  @ApiModelProperty(example = "null", value = "是否排除子流程")
  public Boolean getExcludeSubprocesses() {
    return excludeSubprocesses;
  }

  public void setExcludeSubprocesses(Boolean excludeSubprocesses) {
    this.excludeSubprocesses = excludeSubprocesses;
  }

  public WfProcessInstanceQueryRequest involvedUser(String involvedUser) {
    this.involvedUser = involvedUser;
    return this;
  }

   /**
   * 流程参与者(处理人、候选人、所有者)
   * @return involvedUser
  **/
  @ApiModelProperty(example = "null", value = "流程参与者(处理人、候选人、所有者)")
  public String getInvolvedUser() {
    return involvedUser;
  }

  public void setInvolvedUser(String involvedUser) {
    this.involvedUser = involvedUser;
  }

  public WfProcessInstanceQueryRequest suspended(Boolean suspended) {
    this.suspended = suspended;
    return this;
  }

   /**
   * 是否挂起
   * @return suspended
  **/
  @ApiModelProperty(example = "null", value = "是否挂起")
  public Boolean getSuspended() {
    return suspended;
  }

  public void setSuspended(Boolean suspended) {
    this.suspended = suspended;
  }

  public WfProcessInstanceQueryRequest includeProcessVariables(Boolean includeProcessVariables) {
    this.includeProcessVariables = includeProcessVariables;
    return this;
  }

   /**
   * 是否查询流程变量
   * @return includeProcessVariables
  **/
  @ApiModelProperty(example = "null", value = "是否查询流程变量")
  public Boolean getIncludeProcessVariables() {
    return includeProcessVariables;
  }

  public void setIncludeProcessVariables(Boolean includeProcessVariables) {
    this.includeProcessVariables = includeProcessVariables;
  }

  public WfProcessInstanceQueryRequest variables(List<WfQueryVariable> variables) {
    this.variables = variables;
    return this;
  }

  public WfProcessInstanceQueryRequest addVariablesItem(WfQueryVariable variablesItem) {
    this.variables.add(variablesItem);
    return this;
  }

   /**
   * 根据流程变量过滤
   * @return variables
  **/
  @ApiModelProperty(example = "null", value = "根据流程变量过滤")
  public List<WfQueryVariable> getVariables() {
    return variables;
  }

  public void setVariables(List<WfQueryVariable> variables) {
    this.variables = variables;
  }

  public WfProcessInstanceQueryRequest tenantId(String tenantId) {
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

  public WfProcessInstanceQueryRequest tenantIdLike(String tenantIdLike) {
    this.tenantIdLike = tenantIdLike;
    return this;
  }

   /**
   * 租户模糊
   * @return tenantIdLike
  **/
  @ApiModelProperty(example = "null", value = "租户模糊")
  public String getTenantIdLike() {
    return tenantIdLike;
  }

  public void setTenantIdLike(String tenantIdLike) {
    this.tenantIdLike = tenantIdLike;
  }

  public WfProcessInstanceQueryRequest withoutTenantId(Boolean withoutTenantId) {
    this.withoutTenantId = withoutTenantId;
    return this;
  }

   /**
   * 是否禁用租户过滤
   * @return withoutTenantId
  **/
  @ApiModelProperty(example = "null", value = "是否禁用租户过滤")
  public Boolean getWithoutTenantId() {
    return withoutTenantId;
  }

  public void setWithoutTenantId(Boolean withoutTenantId) {
    this.withoutTenantId = withoutTenantId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WfProcessInstanceQueryRequest wfProcessInstanceQueryRequest = (WfProcessInstanceQueryRequest) o;
    return Objects.equals(this.start, wfProcessInstanceQueryRequest.start) &&
        Objects.equals(this.size, wfProcessInstanceQueryRequest.size) &&
        Objects.equals(this.sort, wfProcessInstanceQueryRequest.sort) &&
        Objects.equals(this.order, wfProcessInstanceQueryRequest.order) &&
        Objects.equals(this.processInstanceId, wfProcessInstanceQueryRequest.processInstanceId) &&
        Objects.equals(this.processBusinessKey, wfProcessInstanceQueryRequest.processBusinessKey) &&
        Objects.equals(this.processDefinitionId, wfProcessInstanceQueryRequest.processDefinitionId) &&
        Objects.equals(this.processDefinitionKey, wfProcessInstanceQueryRequest.processDefinitionKey) &&
        Objects.equals(this.superProcessInstanceId, wfProcessInstanceQueryRequest.superProcessInstanceId) &&
        Objects.equals(this.subProcessInstanceId, wfProcessInstanceQueryRequest.subProcessInstanceId) &&
        Objects.equals(this.excludeSubprocesses, wfProcessInstanceQueryRequest.excludeSubprocesses) &&
        Objects.equals(this.involvedUser, wfProcessInstanceQueryRequest.involvedUser) &&
        Objects.equals(this.suspended, wfProcessInstanceQueryRequest.suspended) &&
        Objects.equals(this.includeProcessVariables, wfProcessInstanceQueryRequest.includeProcessVariables) &&
        Objects.equals(this.variables, wfProcessInstanceQueryRequest.variables) &&
        Objects.equals(this.tenantId, wfProcessInstanceQueryRequest.tenantId) &&
        Objects.equals(this.tenantIdLike, wfProcessInstanceQueryRequest.tenantIdLike) &&
        Objects.equals(this.withoutTenantId, wfProcessInstanceQueryRequest.withoutTenantId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(start, size, sort, order, processInstanceId, processBusinessKey, processDefinitionId, processDefinitionKey, superProcessInstanceId, subProcessInstanceId, excludeSubprocesses, involvedUser, suspended, includeProcessVariables, variables, tenantId, tenantIdLike, withoutTenantId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WfProcessInstanceQueryRequest {\n");
    
    sb.append("    start: ").append(toIndentedString(start)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("    sort: ").append(toIndentedString(sort)).append("\n");
    sb.append("    order: ").append(toIndentedString(order)).append("\n");
    sb.append("    processInstanceId: ").append(toIndentedString(processInstanceId)).append("\n");
    sb.append("    processBusinessKey: ").append(toIndentedString(processBusinessKey)).append("\n");
    sb.append("    processDefinitionId: ").append(toIndentedString(processDefinitionId)).append("\n");
    sb.append("    processDefinitionKey: ").append(toIndentedString(processDefinitionKey)).append("\n");
    sb.append("    superProcessInstanceId: ").append(toIndentedString(superProcessInstanceId)).append("\n");
    sb.append("    subProcessInstanceId: ").append(toIndentedString(subProcessInstanceId)).append("\n");
    sb.append("    excludeSubprocesses: ").append(toIndentedString(excludeSubprocesses)).append("\n");
    sb.append("    involvedUser: ").append(toIndentedString(involvedUser)).append("\n");
    sb.append("    suspended: ").append(toIndentedString(suspended)).append("\n");
    sb.append("    includeProcessVariables: ").append(toIndentedString(includeProcessVariables)).append("\n");
    sb.append("    variables: ").append(toIndentedString(variables)).append("\n");
    sb.append("    tenantId: ").append(toIndentedString(tenantId)).append("\n");
    sb.append("    tenantIdLike: ").append(toIndentedString(tenantIdLike)).append("\n");
    sb.append("    withoutTenantId: ").append(toIndentedString(withoutTenantId)).append("\n");
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

