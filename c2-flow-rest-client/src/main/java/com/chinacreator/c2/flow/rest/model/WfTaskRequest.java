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
import java.util.Date;


/**
 * 任务信息请求参数
 */
@ApiModel(description = "任务信息请求参数")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-12-27T09:47:23.486+08:00")
public class WfTaskRequest   {
  @JsonProperty("currentLoginUserId")
  private String currentLoginUserId = null;

  @JsonProperty("owner")
  private String owner = null;

  @JsonProperty("assignee")
  private String assignee = null;

  /**
   * 任务委托状态
   */
  public enum DelegationStateEnum {
    PENDING("PENDING"),
    
    RESOLVED("RESOLVED");

    private String value;

    DelegationStateEnum(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static DelegationStateEnum fromValue(String text) {
      for (DelegationStateEnum b : DelegationStateEnum.values()) {
          if (String.valueOf(b.value).equals(text)) {
              return b;
          }
      }
      return null;
    }
  }

  @JsonProperty("delegationState")
  private DelegationStateEnum delegationState = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("dueDate")
  private Date dueDate = null;

  @JsonProperty("priority")
  private Integer priority = null;

  @JsonProperty("parentTaskId")
  private String parentTaskId = null;

  @JsonProperty("category")
  private String category = null;

  public WfTaskRequest currentLoginUserId(String currentLoginUserId) {
    this.currentLoginUserId = currentLoginUserId;
    return this;
  }

   /**
   * 模拟当前登陆用户，需要后台启用开关(当流程独立部署且未集成sso环境时调试使用)
   * @return currentLoginUserId
  **/
  @ApiModelProperty(example = "null", value = "模拟当前登陆用户，需要后台启用开关(当流程独立部署且未集成sso环境时调试使用)")
  public String getCurrentLoginUserId() {
    return currentLoginUserId;
  }

  public void setCurrentLoginUserId(String currentLoginUserId) {
    this.currentLoginUserId = currentLoginUserId;
  }

  public WfTaskRequest owner(String owner) {
    this.owner = owner;
    return this;
  }

   /**
   * 任务所有者
   * @return owner
  **/
  @ApiModelProperty(example = "null", value = "任务所有者")
  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public WfTaskRequest assignee(String assignee) {
    this.assignee = assignee;
    return this;
  }

   /**
   * 任务处理人
   * @return assignee
  **/
  @ApiModelProperty(example = "null", value = "任务处理人")
  public String getAssignee() {
    return assignee;
  }

  public void setAssignee(String assignee) {
    this.assignee = assignee;
  }

  public WfTaskRequest delegationState(DelegationStateEnum delegationState) {
    this.delegationState = delegationState;
    return this;
  }

   /**
   * 任务委托状态
   * @return delegationState
  **/
  @ApiModelProperty(example = "null", value = "任务委托状态")
  public DelegationStateEnum getDelegationState() {
    return delegationState;
  }

  public void setDelegationState(DelegationStateEnum delegationState) {
    this.delegationState = delegationState;
  }

  public WfTaskRequest name(String name) {
    this.name = name;
    return this;
  }

   /**
   * 任务名称
   * @return name
  **/
  @ApiModelProperty(example = "null", value = "任务名称")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public WfTaskRequest description(String description) {
    this.description = description;
    return this;
  }

   /**
   * 任务描述
   * @return description
  **/
  @ApiModelProperty(example = "null", value = "任务描述")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public WfTaskRequest dueDate(Date dueDate) {
    this.dueDate = dueDate;
    return this;
  }

   /**
   * 任务过期时间
   * @return dueDate
  **/
  @ApiModelProperty(example = "null", value = "任务过期时间")
  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public WfTaskRequest priority(Integer priority) {
    this.priority = priority;
    return this;
  }

   /**
   * 任务优先级
   * @return priority
  **/
  @ApiModelProperty(example = "null", value = "任务优先级")
  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  public WfTaskRequest parentTaskId(String parentTaskId) {
    this.parentTaskId = parentTaskId;
    return this;
  }

   /**
   * 父任务id
   * @return parentTaskId
  **/
  @ApiModelProperty(example = "null", value = "父任务id")
  public String getParentTaskId() {
    return parentTaskId;
  }

  public void setParentTaskId(String parentTaskId) {
    this.parentTaskId = parentTaskId;
  }

  public WfTaskRequest category(String category) {
    this.category = category;
    return this;
  }

   /**
   * 任务类别
   * @return category
  **/
  @ApiModelProperty(example = "null", value = "任务类别")
  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WfTaskRequest wfTaskRequest = (WfTaskRequest) o;
    return Objects.equals(this.currentLoginUserId, wfTaskRequest.currentLoginUserId) &&
        Objects.equals(this.owner, wfTaskRequest.owner) &&
        Objects.equals(this.assignee, wfTaskRequest.assignee) &&
        Objects.equals(this.delegationState, wfTaskRequest.delegationState) &&
        Objects.equals(this.name, wfTaskRequest.name) &&
        Objects.equals(this.description, wfTaskRequest.description) &&
        Objects.equals(this.dueDate, wfTaskRequest.dueDate) &&
        Objects.equals(this.priority, wfTaskRequest.priority) &&
        Objects.equals(this.parentTaskId, wfTaskRequest.parentTaskId) &&
        Objects.equals(this.category, wfTaskRequest.category);
  }

  @Override
  public int hashCode() {
    return Objects.hash(currentLoginUserId, owner, assignee, delegationState, name, description, dueDate, priority, parentTaskId, category);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WfTaskRequest {\n");
    
    sb.append("    currentLoginUserId: ").append(toIndentedString(currentLoginUserId)).append("\n");
    sb.append("    owner: ").append(toIndentedString(owner)).append("\n");
    sb.append("    assignee: ").append(toIndentedString(assignee)).append("\n");
    sb.append("    delegationState: ").append(toIndentedString(delegationState)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    dueDate: ").append(toIndentedString(dueDate)).append("\n");
    sb.append("    priority: ").append(toIndentedString(priority)).append("\n");
    sb.append("    parentTaskId: ").append(toIndentedString(parentTaskId)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
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

