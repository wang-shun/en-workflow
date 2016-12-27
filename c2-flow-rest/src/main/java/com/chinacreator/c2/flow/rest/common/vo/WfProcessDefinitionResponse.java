package com.chinacreator.c2.flow.rest.common.vo;

import org.activiti.engine.repository.ProcessDefinition;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="WfProcessDefinitionResponse",description="流程定义信息")
public class WfProcessDefinitionResponse {

  @ApiModelProperty("流程定义id")
  private String id;
  @ApiModelProperty("流程标识(流程定义key)")
  private String key;
  @ApiModelProperty("流程定义版本")
  private int version;
  @ApiModelProperty("流程定名称")
  private String name;
  @ApiModelProperty("流程定描述")
  private String description;
  @ApiModelProperty("流程定义部署id")
  private String deploymentId;
  @ApiModelProperty("流程定义xml资源名称")
  private String resourceName;
  @ApiModelProperty("流程图资源名称")
  private String diagramResourceName;
  @ApiModelProperty("类别")
  private String category;
  private boolean graphicalNotationDefined = false;
  @ApiModelProperty("是否暂停")
  private boolean suspended = false;
  private boolean startFormDefined = false;
  
  public WfProcessDefinitionResponse() {
	// TODO Auto-generated constructor stub
  }
  
  public WfProcessDefinitionResponse(ProcessDefinition processDefinition) {
	  this.id=processDefinition.getId();
	  this.key=processDefinition.getKey();
	  this.version=processDefinition.getVersion();
	  this.name=processDefinition.getName();
	  this.description=processDefinition.getDescription();
	  this.deploymentId=processDefinition.getDeploymentId();
	  this.resourceName=processDefinition.getResourceName();
	  this.diagramResourceName=processDefinition.getDiagramResourceName();
	  this.category=processDefinition.getCategory();
	  this.suspended=processDefinition.isSuspended();
	  this.startFormDefined=processDefinition.hasStartFormKey();
  }
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getKey() {
    return key;
  }
  public void setKey(String key) {
    this.key = key;
  }
  public int getVersion() {
    return version;
  }
  public void setVersion(int version) {
    this.version = version;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getDeploymentId() {
    return deploymentId;
  }
  public void setDeploymentId(String deploymentId) {
    this.deploymentId = deploymentId;
  }
  public String getCategory() {
    return category;
  }
  public void setCategory(String category) {
    this.category = category;
  }

  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  public String getResourceName() {
	return resourceName;
}
public void setResourceName(String resourceName) {
	this.resourceName = resourceName;
}
public String getDiagramResourceName() {
	return diagramResourceName;
}
public void setDiagramResourceName(String diagramResourceName) {
	this.diagramResourceName = diagramResourceName;
}
public void setGraphicalNotationDefined(boolean graphicalNotationDefined) {
    this.graphicalNotationDefined = graphicalNotationDefined;
  }
  public boolean isGraphicalNotationDefined() {
    return graphicalNotationDefined;
  }
  public void setSuspended(boolean suspended) {
    this.suspended = suspended;
  }
  public boolean isSuspended() {
    return suspended;
  }
  public void setStartFormDefined(boolean startFormDefined) {
    this.startFormDefined = startFormDefined;
  }
  public boolean isStartFormDefined() {
    return startFormDefined;
  }
}
