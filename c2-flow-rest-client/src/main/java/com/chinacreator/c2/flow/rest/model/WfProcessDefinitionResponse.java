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
 * 流程定义信息
 */
@ApiModel(description = "流程定义信息")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-12-28T13:50:28.213+08:00")
public class WfProcessDefinitionResponse   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("key")
  private String key = null;

  @JsonProperty("version")
  private Integer version = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("deploymentId")
  private String deploymentId = null;

  @JsonProperty("resourceName")
  private String resourceName = null;

  @JsonProperty("diagramResourceName")
  private String diagramResourceName = null;

  @JsonProperty("category")
  private String category = null;

  @JsonProperty("graphicalNotationDefined")
  private Boolean graphicalNotationDefined = null;

  @JsonProperty("suspended")
  private Boolean suspended = null;

  @JsonProperty("startFormDefined")
  private Boolean startFormDefined = null;

  public WfProcessDefinitionResponse id(String id) {
    this.id = id;
    return this;
  }

   /**
   * 流程定义id
   * @return id
  **/
  @ApiModelProperty(example = "null", value = "流程定义id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public WfProcessDefinitionResponse key(String key) {
    this.key = key;
    return this;
  }

   /**
   * 流程标识(流程定义key)
   * @return key
  **/
  @ApiModelProperty(example = "null", value = "流程标识(流程定义key)")
  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public WfProcessDefinitionResponse version(Integer version) {
    this.version = version;
    return this;
  }

   /**
   * 流程定义版本
   * @return version
  **/
  @ApiModelProperty(example = "null", value = "流程定义版本")
  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public WfProcessDefinitionResponse name(String name) {
    this.name = name;
    return this;
  }

   /**
   * 流程定名称
   * @return name
  **/
  @ApiModelProperty(example = "null", value = "流程定名称")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public WfProcessDefinitionResponse description(String description) {
    this.description = description;
    return this;
  }

   /**
   * 流程定描述
   * @return description
  **/
  @ApiModelProperty(example = "null", value = "流程定描述")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public WfProcessDefinitionResponse deploymentId(String deploymentId) {
    this.deploymentId = deploymentId;
    return this;
  }

   /**
   * 流程定义部署id
   * @return deploymentId
  **/
  @ApiModelProperty(example = "null", value = "流程定义部署id")
  public String getDeploymentId() {
    return deploymentId;
  }

  public void setDeploymentId(String deploymentId) {
    this.deploymentId = deploymentId;
  }

  public WfProcessDefinitionResponse resourceName(String resourceName) {
    this.resourceName = resourceName;
    return this;
  }

   /**
   * 流程定义xml资源名称
   * @return resourceName
  **/
  @ApiModelProperty(example = "null", value = "流程定义xml资源名称")
  public String getResourceName() {
    return resourceName;
  }

  public void setResourceName(String resourceName) {
    this.resourceName = resourceName;
  }

  public WfProcessDefinitionResponse diagramResourceName(String diagramResourceName) {
    this.diagramResourceName = diagramResourceName;
    return this;
  }

   /**
   * 流程图资源名称
   * @return diagramResourceName
  **/
  @ApiModelProperty(example = "null", value = "流程图资源名称")
  public String getDiagramResourceName() {
    return diagramResourceName;
  }

  public void setDiagramResourceName(String diagramResourceName) {
    this.diagramResourceName = diagramResourceName;
  }

  public WfProcessDefinitionResponse category(String category) {
    this.category = category;
    return this;
  }

   /**
   * 类别
   * @return category
  **/
  @ApiModelProperty(example = "null", value = "类别")
  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public WfProcessDefinitionResponse graphicalNotationDefined(Boolean graphicalNotationDefined) {
    this.graphicalNotationDefined = graphicalNotationDefined;
    return this;
  }

   /**
   * Get graphicalNotationDefined
   * @return graphicalNotationDefined
  **/
  @ApiModelProperty(example = "null", value = "")
  public Boolean getGraphicalNotationDefined() {
    return graphicalNotationDefined;
  }

  public void setGraphicalNotationDefined(Boolean graphicalNotationDefined) {
    this.graphicalNotationDefined = graphicalNotationDefined;
  }

  public WfProcessDefinitionResponse suspended(Boolean suspended) {
    this.suspended = suspended;
    return this;
  }

   /**
   * 是否暂停
   * @return suspended
  **/
  @ApiModelProperty(example = "null", value = "是否暂停")
  public Boolean getSuspended() {
    return suspended;
  }

  public void setSuspended(Boolean suspended) {
    this.suspended = suspended;
  }

  public WfProcessDefinitionResponse startFormDefined(Boolean startFormDefined) {
    this.startFormDefined = startFormDefined;
    return this;
  }

   /**
   * Get startFormDefined
   * @return startFormDefined
  **/
  @ApiModelProperty(example = "null", value = "")
  public Boolean getStartFormDefined() {
    return startFormDefined;
  }

  public void setStartFormDefined(Boolean startFormDefined) {
    this.startFormDefined = startFormDefined;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WfProcessDefinitionResponse wfProcessDefinitionResponse = (WfProcessDefinitionResponse) o;
    return Objects.equals(this.id, wfProcessDefinitionResponse.id) &&
        Objects.equals(this.key, wfProcessDefinitionResponse.key) &&
        Objects.equals(this.version, wfProcessDefinitionResponse.version) &&
        Objects.equals(this.name, wfProcessDefinitionResponse.name) &&
        Objects.equals(this.description, wfProcessDefinitionResponse.description) &&
        Objects.equals(this.deploymentId, wfProcessDefinitionResponse.deploymentId) &&
        Objects.equals(this.resourceName, wfProcessDefinitionResponse.resourceName) &&
        Objects.equals(this.diagramResourceName, wfProcessDefinitionResponse.diagramResourceName) &&
        Objects.equals(this.category, wfProcessDefinitionResponse.category) &&
        Objects.equals(this.graphicalNotationDefined, wfProcessDefinitionResponse.graphicalNotationDefined) &&
        Objects.equals(this.suspended, wfProcessDefinitionResponse.suspended) &&
        Objects.equals(this.startFormDefined, wfProcessDefinitionResponse.startFormDefined);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, key, version, name, description, deploymentId, resourceName, diagramResourceName, category, graphicalNotationDefined, suspended, startFormDefined);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WfProcessDefinitionResponse {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    key: ").append(toIndentedString(key)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    deploymentId: ").append(toIndentedString(deploymentId)).append("\n");
    sb.append("    resourceName: ").append(toIndentedString(resourceName)).append("\n");
    sb.append("    diagramResourceName: ").append(toIndentedString(diagramResourceName)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    graphicalNotationDefined: ").append(toIndentedString(graphicalNotationDefined)).append("\n");
    sb.append("    suspended: ").append(toIndentedString(suspended)).append("\n");
    sb.append("    startFormDefined: ").append(toIndentedString(startFormDefined)).append("\n");
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

