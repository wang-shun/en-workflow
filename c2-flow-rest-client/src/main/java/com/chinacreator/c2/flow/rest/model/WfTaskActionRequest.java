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
import java.util.List;

import java.io.Serializable;
/**
 * 处理任务请求参数
 */
@ApiModel(description = "处理任务请求参数")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-12-28T16:50:41.068+08:00")
public class WfTaskActionRequest  implements Serializable {
  @JsonProperty("currentLoginUserId")
  private String currentLoginUserId = null;

  /**
   * 处理任务动作类型
   */
  public enum ActionEnum {
    CLAIM("CLAIM"),
    
    COMPLETE("COMPLETE"),
    
    CLAIM_COMPLETE("CLAIM_COMPLETE"),
    
    DELEGATE("DELEGATE"),
    
    RESOLVE("RESOLVE"),
    
    REJECT("REJECT");

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

  @JsonProperty("assignee")
  private String assignee = null;

  @JsonProperty("variables")
  private List<WfRestVariable> variables = new ArrayList<WfRestVariable>();

  public WfTaskActionRequest currentLoginUserId(String currentLoginUserId) {
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

  public WfTaskActionRequest action(ActionEnum action) {
    this.action = action;
    return this;
  }

   /**
   * 处理任务动作类型
   * @return action
  **/
  @ApiModelProperty(example = "null", required = true, value = "处理任务动作类型")
  public ActionEnum getAction() {
    return action;
  }

  public void setAction(ActionEnum action) {
    this.action = action;
  }

  public WfTaskActionRequest assignee(String assignee) {
    this.assignee = assignee;
    return this;
  }

   /**
   * 处理人(DELEGATE操作类型时必传)
   * @return assignee
  **/
  @ApiModelProperty(example = "null", value = "处理人(DELEGATE操作类型时必传)")
  public String getAssignee() {
    return assignee;
  }

  public void setAssignee(String assignee) {
    this.assignee = assignee;
  }

  public WfTaskActionRequest variables(List<WfRestVariable> variables) {
    this.variables = variables;
    return this;
  }

  public WfTaskActionRequest addVariablesItem(WfRestVariable variablesItem) {
    this.variables.add(variablesItem);
    return this;
  }

   /**
   * 操作任务所需流程变量
   * @return variables
  **/
  @ApiModelProperty(example = "null", value = "操作任务所需流程变量")
  public List<WfRestVariable> getVariables() {
    return variables;
  }

  public void setVariables(List<WfRestVariable> variables) {
    this.variables = variables;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WfTaskActionRequest wfTaskActionRequest = (WfTaskActionRequest) o;
    return Objects.equals(this.currentLoginUserId, wfTaskActionRequest.currentLoginUserId) &&
        Objects.equals(this.action, wfTaskActionRequest.action) &&
        Objects.equals(this.assignee, wfTaskActionRequest.assignee) &&
        Objects.equals(this.variables, wfTaskActionRequest.variables);
  }

  @Override
  public int hashCode() {
    return Objects.hash(currentLoginUserId, action, assignee, variables);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WfTaskActionRequest {\n");
    
    sb.append("    currentLoginUserId: ").append(toIndentedString(currentLoginUserId)).append("\n");
    sb.append("    action: ").append(toIndentedString(action)).append("\n");
    sb.append("    assignee: ").append(toIndentedString(assignee)).append("\n");
    sb.append("    variables: ").append(toIndentedString(variables)).append("\n");
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
