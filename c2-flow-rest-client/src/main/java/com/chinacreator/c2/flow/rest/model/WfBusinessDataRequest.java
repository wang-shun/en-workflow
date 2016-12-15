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
 * 业务数据对象(如果流程和某个业务或事项有关联则必须参数)
 */
@ApiModel(description = "业务数据对象(如果流程和某个业务或事项有关联则必须参数)")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-12-15T15:13:20.386+08:00")
public class WfBusinessDataRequest   {
  @JsonProperty("businessKey")
  private String businessKey = null;

  @JsonProperty("appId")
  private String appId = null;

  @JsonProperty("moduleId")
  private String moduleId = null;

  @JsonProperty("moduleName")
  private String moduleName = null;

  @JsonProperty("businessExtend")
  private Object businessExtend = null;

  @JsonProperty("remark1")
  private String remark1 = null;

  @JsonProperty("remark2")
  private String remark2 = null;

  @JsonProperty("remark3")
  private String remark3 = null;

  @JsonProperty("remark4")
  private String remark4 = null;

  @JsonProperty("remark5")
  private String remark5 = null;

  @JsonProperty("remark6")
  private String remark6 = null;

  @JsonProperty("remark7")
  private String remark7 = null;

  @JsonProperty("remark8")
  private String remark8 = null;

  @JsonProperty("remark9")
  private String remark9 = null;

  @JsonProperty("remark10")
  private String remark10 = null;

  @JsonProperty("stringValue")
  private String stringValue = null;

  public WfBusinessDataRequest businessKey(String businessKey) {
    this.businessKey = businessKey;
    return this;
  }

   /**
   * 业务主键id(用于关联业务和流程实例，建议传，否则只能将流程实例id记录到业务中)
   * @return businessKey
  **/
  @ApiModelProperty(example = "null", value = "业务主键id(用于关联业务和流程实例，建议传，否则只能将流程实例id记录到业务中)")
  public String getBusinessKey() {
    return businessKey;
  }

  public void setBusinessKey(String businessKey) {
    this.businessKey = businessKey;
  }

  public WfBusinessDataRequest appId(String appId) {
    this.appId = appId;
    return this;
  }

   /**
   * 应用id
   * @return appId
  **/
  @ApiModelProperty(example = "null", value = "应用id")
  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public WfBusinessDataRequest moduleId(String moduleId) {
    this.moduleId = moduleId;
    return this;
  }

   /**
   * 业务模块id(如果有流程配置启动时必须传)
   * @return moduleId
  **/
  @ApiModelProperty(example = "null", value = "业务模块id(如果有流程配置启动时必须传)")
  public String getModuleId() {
    return moduleId;
  }

  public void setModuleId(String moduleId) {
    this.moduleId = moduleId;
  }

  public WfBusinessDataRequest moduleName(String moduleName) {
    this.moduleName = moduleName;
    return this;
  }

   /**
   * 业务模块名称
   * @return moduleName
  **/
  @ApiModelProperty(example = "null", value = "业务模块名称")
  public String getModuleName() {
    return moduleName;
  }

  public void setModuleName(String moduleName) {
    this.moduleName = moduleName;
  }

  public WfBusinessDataRequest businessExtend(Object businessExtend) {
    this.businessExtend = businessExtend;
    return this;
  }

   /**
   * 业务模块扩展对象
   * @return businessExtend
  **/
  @ApiModelProperty(example = "null", value = "业务模块扩展对象")
  public Object getBusinessExtend() {
    return businessExtend;
  }

  public void setBusinessExtend(Object businessExtend) {
    this.businessExtend = businessExtend;
  }

  public WfBusinessDataRequest remark1(String remark1) {
    this.remark1 = remark1;
    return this;
  }

   /**
   * 备用字段
   * @return remark1
  **/
  @ApiModelProperty(example = "null", value = "备用字段")
  public String getRemark1() {
    return remark1;
  }

  public void setRemark1(String remark1) {
    this.remark1 = remark1;
  }

  public WfBusinessDataRequest remark2(String remark2) {
    this.remark2 = remark2;
    return this;
  }

   /**
   * 备用字段
   * @return remark2
  **/
  @ApiModelProperty(example = "null", value = "备用字段")
  public String getRemark2() {
    return remark2;
  }

  public void setRemark2(String remark2) {
    this.remark2 = remark2;
  }

  public WfBusinessDataRequest remark3(String remark3) {
    this.remark3 = remark3;
    return this;
  }

   /**
   * 备用字段
   * @return remark3
  **/
  @ApiModelProperty(example = "null", value = "备用字段")
  public String getRemark3() {
    return remark3;
  }

  public void setRemark3(String remark3) {
    this.remark3 = remark3;
  }

  public WfBusinessDataRequest remark4(String remark4) {
    this.remark4 = remark4;
    return this;
  }

   /**
   * 备用字段
   * @return remark4
  **/
  @ApiModelProperty(example = "null", value = "备用字段")
  public String getRemark4() {
    return remark4;
  }

  public void setRemark4(String remark4) {
    this.remark4 = remark4;
  }

  public WfBusinessDataRequest remark5(String remark5) {
    this.remark5 = remark5;
    return this;
  }

   /**
   * 备用字段
   * @return remark5
  **/
  @ApiModelProperty(example = "null", value = "备用字段")
  public String getRemark5() {
    return remark5;
  }

  public void setRemark5(String remark5) {
    this.remark5 = remark5;
  }

  public WfBusinessDataRequest remark6(String remark6) {
    this.remark6 = remark6;
    return this;
  }

   /**
   * 备用字段
   * @return remark6
  **/
  @ApiModelProperty(example = "null", value = "备用字段")
  public String getRemark6() {
    return remark6;
  }

  public void setRemark6(String remark6) {
    this.remark6 = remark6;
  }

  public WfBusinessDataRequest remark7(String remark7) {
    this.remark7 = remark7;
    return this;
  }

   /**
   * 备用字段
   * @return remark7
  **/
  @ApiModelProperty(example = "null", value = "备用字段")
  public String getRemark7() {
    return remark7;
  }

  public void setRemark7(String remark7) {
    this.remark7 = remark7;
  }

  public WfBusinessDataRequest remark8(String remark8) {
    this.remark8 = remark8;
    return this;
  }

   /**
   * 备用字段
   * @return remark8
  **/
  @ApiModelProperty(example = "null", value = "备用字段")
  public String getRemark8() {
    return remark8;
  }

  public void setRemark8(String remark8) {
    this.remark8 = remark8;
  }

  public WfBusinessDataRequest remark9(String remark9) {
    this.remark9 = remark9;
    return this;
  }

   /**
   * 备用字段
   * @return remark9
  **/
  @ApiModelProperty(example = "null", value = "备用字段")
  public String getRemark9() {
    return remark9;
  }

  public void setRemark9(String remark9) {
    this.remark9 = remark9;
  }

  public WfBusinessDataRequest remark10(String remark10) {
    this.remark10 = remark10;
    return this;
  }

   /**
   * 备用字段
   * @return remark10
  **/
  @ApiModelProperty(example = "null", value = "备用字段")
  public String getRemark10() {
    return remark10;
  }

  public void setRemark10(String remark10) {
    this.remark10 = remark10;
  }

  public WfBusinessDataRequest stringValue(String stringValue) {
    this.stringValue = stringValue;
    return this;
  }

   /**
   * Get stringValue
   * @return stringValue
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getStringValue() {
    return stringValue;
  }

  public void setStringValue(String stringValue) {
    this.stringValue = stringValue;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WfBusinessDataRequest wfBusinessDataRequest = (WfBusinessDataRequest) o;
    return Objects.equals(this.businessKey, wfBusinessDataRequest.businessKey) &&
        Objects.equals(this.appId, wfBusinessDataRequest.appId) &&
        Objects.equals(this.moduleId, wfBusinessDataRequest.moduleId) &&
        Objects.equals(this.moduleName, wfBusinessDataRequest.moduleName) &&
        Objects.equals(this.businessExtend, wfBusinessDataRequest.businessExtend) &&
        Objects.equals(this.remark1, wfBusinessDataRequest.remark1) &&
        Objects.equals(this.remark2, wfBusinessDataRequest.remark2) &&
        Objects.equals(this.remark3, wfBusinessDataRequest.remark3) &&
        Objects.equals(this.remark4, wfBusinessDataRequest.remark4) &&
        Objects.equals(this.remark5, wfBusinessDataRequest.remark5) &&
        Objects.equals(this.remark6, wfBusinessDataRequest.remark6) &&
        Objects.equals(this.remark7, wfBusinessDataRequest.remark7) &&
        Objects.equals(this.remark8, wfBusinessDataRequest.remark8) &&
        Objects.equals(this.remark9, wfBusinessDataRequest.remark9) &&
        Objects.equals(this.remark10, wfBusinessDataRequest.remark10) &&
        Objects.equals(this.stringValue, wfBusinessDataRequest.stringValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(businessKey, appId, moduleId, moduleName, businessExtend, remark1, remark2, remark3, remark4, remark5, remark6, remark7, remark8, remark9, remark10, stringValue);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WfBusinessDataRequest {\n");
    
    sb.append("    businessKey: ").append(toIndentedString(businessKey)).append("\n");
    sb.append("    appId: ").append(toIndentedString(appId)).append("\n");
    sb.append("    moduleId: ").append(toIndentedString(moduleId)).append("\n");
    sb.append("    moduleName: ").append(toIndentedString(moduleName)).append("\n");
    sb.append("    businessExtend: ").append(toIndentedString(businessExtend)).append("\n");
    sb.append("    remark1: ").append(toIndentedString(remark1)).append("\n");
    sb.append("    remark2: ").append(toIndentedString(remark2)).append("\n");
    sb.append("    remark3: ").append(toIndentedString(remark3)).append("\n");
    sb.append("    remark4: ").append(toIndentedString(remark4)).append("\n");
    sb.append("    remark5: ").append(toIndentedString(remark5)).append("\n");
    sb.append("    remark6: ").append(toIndentedString(remark6)).append("\n");
    sb.append("    remark7: ").append(toIndentedString(remark7)).append("\n");
    sb.append("    remark8: ").append(toIndentedString(remark8)).append("\n");
    sb.append("    remark9: ").append(toIndentedString(remark9)).append("\n");
    sb.append("    remark10: ").append(toIndentedString(remark10)).append("\n");
    sb.append("    stringValue: ").append(toIndentedString(stringValue)).append("\n");
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

