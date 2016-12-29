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

import java.io.Serializable;
/**
 * 流程操作结果
 */
@ApiModel(description = "流程操作结果")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-12-28T16:50:41.068+08:00")
public class WfActionResult  implements Serializable {
  @JsonProperty("processInstanceId")
  private String processInstanceId = null;

  @JsonProperty("nextTaskId")
  private String nextTaskId = null;

  public WfActionResult processInstanceId(String processInstanceId) {
    this.processInstanceId = processInstanceId;
    return this;
  }

   /**
   * 当前操作流程实例
   * @return processInstanceId
  **/
  @ApiModelProperty(example = "null", value = "当前操作流程实例")
  public String getProcessInstanceId() {
    return processInstanceId;
  }

  public void setProcessInstanceId(String processInstanceId) {
    this.processInstanceId = processInstanceId;
  }

  public WfActionResult nextTaskId(String nextTaskId) {
    this.nextTaskId = nextTaskId;
    return this;
  }

   /**
   * 下一步任务id
   * @return nextTaskId
  **/
  @ApiModelProperty(example = "null", value = "下一步任务id")
  public String getNextTaskId() {
    return nextTaskId;
  }

  public void setNextTaskId(String nextTaskId) {
    this.nextTaskId = nextTaskId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WfActionResult wfActionResult = (WfActionResult) o;
    return Objects.equals(this.processInstanceId, wfActionResult.processInstanceId) &&
        Objects.equals(this.nextTaskId, wfActionResult.nextTaskId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(processInstanceId, nextTaskId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WfActionResult {\n");
    
    sb.append("    processInstanceId: ").append(toIndentedString(processInstanceId)).append("\n");
    sb.append("    nextTaskId: ").append(toIndentedString(nextTaskId)).append("\n");
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

