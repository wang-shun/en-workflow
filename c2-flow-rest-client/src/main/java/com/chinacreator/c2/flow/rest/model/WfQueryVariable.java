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
 * 变量查询参数
 */
@ApiModel(description = "变量查询参数")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-12-09T14:24:45.873+08:00")
public class WfQueryVariable   {
  @JsonProperty("name")
  private String name = null;

  /**
   * 变量值匹配操作
   */
  public enum VariableOperationEnum {
    EQUALS("EQUALS"),
    
    NOT_EQUALS("NOT_EQUALS"),
    
    EQUALS_IGNORE_CASE("EQUALS_IGNORE_CASE"),
    
    NOT_EQUALS_IGNORE_CASE("NOT_EQUALS_IGNORE_CASE"),
    
    LIKE("LIKE"),
    
    GREATER_THAN("GREATER_THAN"),
    
    GREATER_THAN_OR_EQUALS("GREATER_THAN_OR_EQUALS"),
    
    LESS_THAN("LESS_THAN"),
    
    LESS_THAN_OR_EQUALS("LESS_THAN_OR_EQUALS");

    private String value;

    VariableOperationEnum(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static VariableOperationEnum fromValue(String text) {
      for (VariableOperationEnum b : VariableOperationEnum.values()) {
          if (String.valueOf(b.value).equals(text)) {
              return b;
          }
      }
      return null;
    }
  }

  @JsonProperty("variableOperation")
  private VariableOperationEnum variableOperation = null;

  @JsonProperty("value")
  private Object value = null;

  @JsonProperty("type")
  private String type = null;

  public WfQueryVariable name(String name) {
    this.name = name;
    return this;
  }

   /**
   * 变量名称
   * @return name
  **/
  @ApiModelProperty(example = "null", value = "变量名称")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public WfQueryVariable variableOperation(VariableOperationEnum variableOperation) {
    this.variableOperation = variableOperation;
    return this;
  }

   /**
   * 变量值匹配操作
   * @return variableOperation
  **/
  @ApiModelProperty(example = "null", value = "变量值匹配操作")
  public VariableOperationEnum getVariableOperation() {
    return variableOperation;
  }

  public void setVariableOperation(VariableOperationEnum variableOperation) {
    this.variableOperation = variableOperation;
  }

  public WfQueryVariable value(Object value) {
    this.value = value;
    return this;
  }

   /**
   * 变量值
   * @return value
  **/
  @ApiModelProperty(example = "null", value = "变量值")
  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  public WfQueryVariable type(String type) {
    this.type = type;
    return this;
  }

   /**
   * 变量类型
   * @return type
  **/
  @ApiModelProperty(example = "null", value = "变量类型")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WfQueryVariable wfQueryVariable = (WfQueryVariable) o;
    return Objects.equals(this.name, wfQueryVariable.name) &&
        Objects.equals(this.variableOperation, wfQueryVariable.variableOperation) &&
        Objects.equals(this.value, wfQueryVariable.value) &&
        Objects.equals(this.type, wfQueryVariable.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, variableOperation, value, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WfQueryVariable {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    variableOperation: ").append(toIndentedString(variableOperation)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

