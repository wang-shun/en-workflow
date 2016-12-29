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
import java.util.Date;
import java.util.List;

import java.io.Serializable;
/**
 * 历史流程实例查询参数
 */
@ApiModel(description = "历史流程实例查询参数")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-12-28T16:50:41.068+08:00")
public class WfHistoricProcessInstanceQueryRequest  implements Serializable {
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

  @JsonProperty("processInstanceIds")
  private List<String> processInstanceIds = new ArrayList<String>();

  @JsonProperty("processBusinessKey")
  private String processBusinessKey = null;

  @JsonProperty("processDefinitionId")
  private String processDefinitionId = null;

  @JsonProperty("processDefinitionKey")
  private String processDefinitionKey = null;

  @JsonProperty("superProcessInstanceId")
  private String superProcessInstanceId = null;

  @JsonProperty("excludeSubprocesses")
  private Boolean excludeSubprocesses = null;

  @JsonProperty("finished")
  private Boolean finished = null;

  @JsonProperty("involvedUser")
  private String involvedUser = null;

  @JsonProperty("finishedAfter")
  private Date finishedAfter = null;

  @JsonProperty("finishedBefore")
  private Date finishedBefore = null;

  @JsonProperty("startedAfter")
  private Date startedAfter = null;

  @JsonProperty("startedBefore")
  private Date startedBefore = null;

  @JsonProperty("startedBy")
  private String startedBy = null;

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

  public WfHistoricProcessInstanceQueryRequest start(Integer start) {
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

  public WfHistoricProcessInstanceQueryRequest size(Integer size) {
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

  public WfHistoricProcessInstanceQueryRequest sort(String sort) {
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

  public WfHistoricProcessInstanceQueryRequest order(String order) {
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

  public WfHistoricProcessInstanceQueryRequest processInstanceId(String processInstanceId) {
    this.processInstanceId = processInstanceId;
    return this;
  }

   /**
   * 流程实例d
   * @return processInstanceId
  **/
  @ApiModelProperty(example = "null", value = "流程实例d")
  public String getProcessInstanceId() {
    return processInstanceId;
  }

  public void setProcessInstanceId(String processInstanceId) {
    this.processInstanceId = processInstanceId;
  }

  public WfHistoricProcessInstanceQueryRequest processInstanceIds(List<String> processInstanceIds) {
    this.processInstanceIds = processInstanceIds;
    return this;
  }

  public WfHistoricProcessInstanceQueryRequest addProcessInstanceIdsItem(String processInstanceIdsItem) {
    this.processInstanceIds.add(processInstanceIdsItem);
    return this;
  }

   /**
   * 流程实例集合
   * @return processInstanceIds
  **/
  @ApiModelProperty(example = "null", value = "流程实例集合")
  public List<String> getProcessInstanceIds() {
    return processInstanceIds;
  }

  public void setProcessInstanceIds(List<String> processInstanceIds) {
    this.processInstanceIds = processInstanceIds;
  }

  public WfHistoricProcessInstanceQueryRequest processBusinessKey(String processBusinessKey) {
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

  public WfHistoricProcessInstanceQueryRequest processDefinitionId(String processDefinitionId) {
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

  public WfHistoricProcessInstanceQueryRequest processDefinitionKey(String processDefinitionKey) {
    this.processDefinitionKey = processDefinitionKey;
    return this;
  }

   /**
   * 流程定义key
   * @return processDefinitionKey
  **/
  @ApiModelProperty(example = "null", value = "流程定义key")
  public String getProcessDefinitionKey() {
    return processDefinitionKey;
  }

  public void setProcessDefinitionKey(String processDefinitionKey) {
    this.processDefinitionKey = processDefinitionKey;
  }

  public WfHistoricProcessInstanceQueryRequest superProcessInstanceId(String superProcessInstanceId) {
    this.superProcessInstanceId = superProcessInstanceId;
    return this;
  }

   /**
   * 父流程id
   * @return superProcessInstanceId
  **/
  @ApiModelProperty(example = "null", value = "父流程id")
  public String getSuperProcessInstanceId() {
    return superProcessInstanceId;
  }

  public void setSuperProcessInstanceId(String superProcessInstanceId) {
    this.superProcessInstanceId = superProcessInstanceId;
  }

  public WfHistoricProcessInstanceQueryRequest excludeSubprocesses(Boolean excludeSubprocesses) {
    this.excludeSubprocesses = excludeSubprocesses;
    return this;
  }

   /**
   * 排除拥有子流的
   * @return excludeSubprocesses
  **/
  @ApiModelProperty(example = "null", value = "排除拥有子流的")
  public Boolean getExcludeSubprocesses() {
    return excludeSubprocesses;
  }

  public void setExcludeSubprocesses(Boolean excludeSubprocesses) {
    this.excludeSubprocesses = excludeSubprocesses;
  }

  public WfHistoricProcessInstanceQueryRequest finished(Boolean finished) {
    this.finished = finished;
    return this;
  }

   /**
   * 是否完成
   * @return finished
  **/
  @ApiModelProperty(example = "null", value = "是否完成")
  public Boolean getFinished() {
    return finished;
  }

  public void setFinished(Boolean finished) {
    this.finished = finished;
  }

  public WfHistoricProcessInstanceQueryRequest involvedUser(String involvedUser) {
    this.involvedUser = involvedUser;
    return this;
  }

   /**
   * 流程参与者(处理人、候选人、所有者) 
   * @return involvedUser
  **/
  @ApiModelProperty(example = "null", value = "流程参与者(处理人、候选人、所有者) ")
  public String getInvolvedUser() {
    return involvedUser;
  }

  public void setInvolvedUser(String involvedUser) {
    this.involvedUser = involvedUser;
  }

  public WfHistoricProcessInstanceQueryRequest finishedAfter(Date finishedAfter) {
    this.finishedAfter = finishedAfter;
    return this;
  }

   /**
   * 完成时间晚于
   * @return finishedAfter
  **/
  @ApiModelProperty(example = "null", value = "完成时间晚于")
  public Date getFinishedAfter() {
    return finishedAfter;
  }

  public void setFinishedAfter(Date finishedAfter) {
    this.finishedAfter = finishedAfter;
  }

  public WfHistoricProcessInstanceQueryRequest finishedBefore(Date finishedBefore) {
    this.finishedBefore = finishedBefore;
    return this;
  }

   /**
   * 完成时间早于
   * @return finishedBefore
  **/
  @ApiModelProperty(example = "null", value = "完成时间早于")
  public Date getFinishedBefore() {
    return finishedBefore;
  }

  public void setFinishedBefore(Date finishedBefore) {
    this.finishedBefore = finishedBefore;
  }

  public WfHistoricProcessInstanceQueryRequest startedAfter(Date startedAfter) {
    this.startedAfter = startedAfter;
    return this;
  }

   /**
   * 开始时间晚于
   * @return startedAfter
  **/
  @ApiModelProperty(example = "null", value = "开始时间晚于")
  public Date getStartedAfter() {
    return startedAfter;
  }

  public void setStartedAfter(Date startedAfter) {
    this.startedAfter = startedAfter;
  }

  public WfHistoricProcessInstanceQueryRequest startedBefore(Date startedBefore) {
    this.startedBefore = startedBefore;
    return this;
  }

   /**
   * 开始时间早于
   * @return startedBefore
  **/
  @ApiModelProperty(example = "null", value = "开始时间早于")
  public Date getStartedBefore() {
    return startedBefore;
  }

  public void setStartedBefore(Date startedBefore) {
    this.startedBefore = startedBefore;
  }

  public WfHistoricProcessInstanceQueryRequest startedBy(String startedBy) {
    this.startedBy = startedBy;
    return this;
  }

   /**
   * 流程启动者
   * @return startedBy
  **/
  @ApiModelProperty(example = "null", value = "流程启动者")
  public String getStartedBy() {
    return startedBy;
  }

  public void setStartedBy(String startedBy) {
    this.startedBy = startedBy;
  }

  public WfHistoricProcessInstanceQueryRequest includeProcessVariables(Boolean includeProcessVariables) {
    this.includeProcessVariables = includeProcessVariables;
    return this;
  }

   /**
   * 是否包含流程变量
   * @return includeProcessVariables
  **/
  @ApiModelProperty(example = "null", value = "是否包含流程变量")
  public Boolean getIncludeProcessVariables() {
    return includeProcessVariables;
  }

  public void setIncludeProcessVariables(Boolean includeProcessVariables) {
    this.includeProcessVariables = includeProcessVariables;
  }

  public WfHistoricProcessInstanceQueryRequest variables(List<WfQueryVariable> variables) {
    this.variables = variables;
    return this;
  }

  public WfHistoricProcessInstanceQueryRequest addVariablesItem(WfQueryVariable variablesItem) {
    this.variables.add(variablesItem);
    return this;
  }

   /**
   * 流程自定义变量条件
   * @return variables
  **/
  @ApiModelProperty(example = "null", value = "流程自定义变量条件")
  public List<WfQueryVariable> getVariables() {
    return variables;
  }

  public void setVariables(List<WfQueryVariable> variables) {
    this.variables = variables;
  }

  public WfHistoricProcessInstanceQueryRequest tenantId(String tenantId) {
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

  public WfHistoricProcessInstanceQueryRequest tenantIdLike(String tenantIdLike) {
    this.tenantIdLike = tenantIdLike;
    return this;
  }

   /**
   * 租户模糊匹配
   * @return tenantIdLike
  **/
  @ApiModelProperty(example = "null", value = "租户模糊匹配")
  public String getTenantIdLike() {
    return tenantIdLike;
  }

  public void setTenantIdLike(String tenantIdLike) {
    this.tenantIdLike = tenantIdLike;
  }

  public WfHistoricProcessInstanceQueryRequest withoutTenantId(Boolean withoutTenantId) {
    this.withoutTenantId = withoutTenantId;
    return this;
  }

   /**
   * 是否启用租户过滤
   * @return withoutTenantId
  **/
  @ApiModelProperty(example = "null", value = "是否启用租户过滤")
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
    WfHistoricProcessInstanceQueryRequest wfHistoricProcessInstanceQueryRequest = (WfHistoricProcessInstanceQueryRequest) o;
    return Objects.equals(this.start, wfHistoricProcessInstanceQueryRequest.start) &&
        Objects.equals(this.size, wfHistoricProcessInstanceQueryRequest.size) &&
        Objects.equals(this.sort, wfHistoricProcessInstanceQueryRequest.sort) &&
        Objects.equals(this.order, wfHistoricProcessInstanceQueryRequest.order) &&
        Objects.equals(this.processInstanceId, wfHistoricProcessInstanceQueryRequest.processInstanceId) &&
        Objects.equals(this.processInstanceIds, wfHistoricProcessInstanceQueryRequest.processInstanceIds) &&
        Objects.equals(this.processBusinessKey, wfHistoricProcessInstanceQueryRequest.processBusinessKey) &&
        Objects.equals(this.processDefinitionId, wfHistoricProcessInstanceQueryRequest.processDefinitionId) &&
        Objects.equals(this.processDefinitionKey, wfHistoricProcessInstanceQueryRequest.processDefinitionKey) &&
        Objects.equals(this.superProcessInstanceId, wfHistoricProcessInstanceQueryRequest.superProcessInstanceId) &&
        Objects.equals(this.excludeSubprocesses, wfHistoricProcessInstanceQueryRequest.excludeSubprocesses) &&
        Objects.equals(this.finished, wfHistoricProcessInstanceQueryRequest.finished) &&
        Objects.equals(this.involvedUser, wfHistoricProcessInstanceQueryRequest.involvedUser) &&
        Objects.equals(this.finishedAfter, wfHistoricProcessInstanceQueryRequest.finishedAfter) &&
        Objects.equals(this.finishedBefore, wfHistoricProcessInstanceQueryRequest.finishedBefore) &&
        Objects.equals(this.startedAfter, wfHistoricProcessInstanceQueryRequest.startedAfter) &&
        Objects.equals(this.startedBefore, wfHistoricProcessInstanceQueryRequest.startedBefore) &&
        Objects.equals(this.startedBy, wfHistoricProcessInstanceQueryRequest.startedBy) &&
        Objects.equals(this.includeProcessVariables, wfHistoricProcessInstanceQueryRequest.includeProcessVariables) &&
        Objects.equals(this.variables, wfHistoricProcessInstanceQueryRequest.variables) &&
        Objects.equals(this.tenantId, wfHistoricProcessInstanceQueryRequest.tenantId) &&
        Objects.equals(this.tenantIdLike, wfHistoricProcessInstanceQueryRequest.tenantIdLike) &&
        Objects.equals(this.withoutTenantId, wfHistoricProcessInstanceQueryRequest.withoutTenantId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(start, size, sort, order, processInstanceId, processInstanceIds, processBusinessKey, processDefinitionId, processDefinitionKey, superProcessInstanceId, excludeSubprocesses, finished, involvedUser, finishedAfter, finishedBefore, startedAfter, startedBefore, startedBy, includeProcessVariables, variables, tenantId, tenantIdLike, withoutTenantId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WfHistoricProcessInstanceQueryRequest {\n");
    
    sb.append("    start: ").append(toIndentedString(start)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("    sort: ").append(toIndentedString(sort)).append("\n");
    sb.append("    order: ").append(toIndentedString(order)).append("\n");
    sb.append("    processInstanceId: ").append(toIndentedString(processInstanceId)).append("\n");
    sb.append("    processInstanceIds: ").append(toIndentedString(processInstanceIds)).append("\n");
    sb.append("    processBusinessKey: ").append(toIndentedString(processBusinessKey)).append("\n");
    sb.append("    processDefinitionId: ").append(toIndentedString(processDefinitionId)).append("\n");
    sb.append("    processDefinitionKey: ").append(toIndentedString(processDefinitionKey)).append("\n");
    sb.append("    superProcessInstanceId: ").append(toIndentedString(superProcessInstanceId)).append("\n");
    sb.append("    excludeSubprocesses: ").append(toIndentedString(excludeSubprocesses)).append("\n");
    sb.append("    finished: ").append(toIndentedString(finished)).append("\n");
    sb.append("    involvedUser: ").append(toIndentedString(involvedUser)).append("\n");
    sb.append("    finishedAfter: ").append(toIndentedString(finishedAfter)).append("\n");
    sb.append("    finishedBefore: ").append(toIndentedString(finishedBefore)).append("\n");
    sb.append("    startedAfter: ").append(toIndentedString(startedAfter)).append("\n");
    sb.append("    startedBefore: ").append(toIndentedString(startedBefore)).append("\n");
    sb.append("    startedBy: ").append(toIndentedString(startedBy)).append("\n");
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

