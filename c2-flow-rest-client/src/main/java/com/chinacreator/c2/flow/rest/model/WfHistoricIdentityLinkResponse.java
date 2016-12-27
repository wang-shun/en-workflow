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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 历史候选人或候选组信息
 */
@ApiModel(description = "历史候选人或候选组信息")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-12-27T09:47:23.486+08:00")
public class WfHistoricIdentityLinkResponse   {
  @JsonProperty("type")
  private String type = null;

  @JsonProperty("userId")
  private String userId = null;

  @JsonProperty("groupId")
  private String groupId = null;

  @JsonProperty("taskId")
  private String taskId = null;

  @JsonProperty("processInstanceId")
  private String processInstanceId = null;

  public WfHistoricIdentityLinkResponse type(String type) {
    this.type = type;
    return this;
  }

   /**
   * 候选人类型
   * @return type
  **/
  @ApiModelProperty(example = "null", value = "候选人类型")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public WfHistoricIdentityLinkResponse userId(String userId) {
    this.userId = userId;
    return this;
  }

   /**
   * 候选人
   * @return userId
  **/
  @ApiModelProperty(example = "null", value = "候选人")
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public WfHistoricIdentityLinkResponse groupId(String groupId) {
    this.groupId = groupId;
    return this;
  }

   /**
   * 候选组
   * @return groupId
  **/
  @ApiModelProperty(example = "null", value = "候选组")
  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  public WfHistoricIdentityLinkResponse taskId(String taskId) {
    this.taskId = taskId;
    return this;
  }

   /**
   * 任务id
   * @return taskId
  **/
  @ApiModelProperty(example = "null", value = "任务id")
  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

  public WfHistoricIdentityLinkResponse processInstanceId(String processInstanceId) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WfHistoricIdentityLinkResponse wfHistoricIdentityLinkResponse = (WfHistoricIdentityLinkResponse) o;
    return Objects.equals(this.type, wfHistoricIdentityLinkResponse.type) &&
        Objects.equals(this.userId, wfHistoricIdentityLinkResponse.userId) &&
        Objects.equals(this.groupId, wfHistoricIdentityLinkResponse.groupId) &&
        Objects.equals(this.taskId, wfHistoricIdentityLinkResponse.taskId) &&
        Objects.equals(this.processInstanceId, wfHistoricIdentityLinkResponse.processInstanceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, userId, groupId, taskId, processInstanceId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WfHistoricIdentityLinkResponse {\n");
    
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
    sb.append("    taskId: ").append(toIndentedString(taskId)).append("\n");
    sb.append("    processInstanceId: ").append(toIndentedString(processInstanceId)).append("\n");
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

