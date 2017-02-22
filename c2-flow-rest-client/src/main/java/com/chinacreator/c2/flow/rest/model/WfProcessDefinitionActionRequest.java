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

import java.io.Serializable;
/**
 * WfProcessDefinitionActionRequest
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-12-28T16:50:41.068+08:00")
public class WfProcessDefinitionActionRequest  implements Serializable {
  @JsonProperty("includeProcessInstances")
  private Boolean includeProcessInstances = null;

  @JsonProperty("date")
  private Date date = null;

  @JsonProperty("category")
  private String category = null;

  /**
   * 流程状态类型
   */
  public enum ActionEnum {
    SUSPEND("SUSPEND"),
    
    ACTIVATE("ACTIVATE");

    private String value;

    ActionEnum(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ActionEnum fromValue(String text) {
      for (ActionEnum b : ActionEnum.values()) {
          if (String.valueOf(b.value).equals(text)) {
              return b;
          }
      }
      return null;
    }
  }

  @JsonProperty("action")
  private ActionEnum action = null;

  public WfProcessDefinitionActionRequest includeProcessInstances(Boolean includeProcessInstances) {
    this.includeProcessInstances = includeProcessInstances;
    return this;
  }

   /**
   * 是否包含子流程实例
   * @return includeProcessInstances
  **/
  @ApiModelProperty(example = "null", value = "是否包含子流程实例")
  public Boolean getIncludeProcessInstances() {
    return includeProcessInstances;
  }

  public void setIncludeProcessInstances(Boolean includeProcessInstances) {
    this.includeProcessInstances = includeProcessInstances;
  }

  public WfProcessDefinitionActionRequest date(Date date) {
    this.date = date;
    return this;
  }

   /**
   * 流程定义日期
   * @return date
  **/
  @ApiModelProperty(example = "null", value = "流程定义日期")
  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public WfProcessDefinitionActionRequest category(String category) {
    this.category = category;
    return this;
  }

   /**
   * 流程定义类别
   * @return category
  **/
  @ApiModelProperty(example = "null", value = "流程定义类别")
  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public WfProcessDefinitionActionRequest action(ActionEnum action) {
    this.action = action;
    return this;
  }

   /**
   * 流程状态类型
   * @return action
  **/
  @ApiModelProperty(example = "null", value = "流程状态类型")
  public ActionEnum getAction() {
    return action;
  }

  public void setAction(ActionEnum action) {
    this.action = action;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WfProcessDefinitionActionRequest wfProcessDefinitionActionRequest = (WfProcessDefinitionActionRequest) o;
    return Objects.equals(this.includeProcessInstances, wfProcessDefinitionActionRequest.includeProcessInstances) &&
        Objects.equals(this.date, wfProcessDefinitionActionRequest.date) &&
        Objects.equals(this.category, wfProcessDefinitionActionRequest.category) &&
        Objects.equals(this.action, wfProcessDefinitionActionRequest.action);
  }

  @Override
  public int hashCode() {
    return Objects.hash(includeProcessInstances, date, category, action);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WfProcessDefinitionActionRequest {\n");
    
    sb.append("    includeProcessInstances: ").append(toIndentedString(includeProcessInstances)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    action: ").append(toIndentedString(action)).append("\n");
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

