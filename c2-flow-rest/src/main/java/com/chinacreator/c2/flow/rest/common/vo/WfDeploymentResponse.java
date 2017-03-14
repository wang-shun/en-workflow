package com.chinacreator.c2.flow.rest.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import org.activiti.engine.repository.Deployment;

import com.chinacreator.c2.flow.detail.WfDeployment;

/**
 * 部署
 * @author hushow
 *
 */
@ApiModel(value="WfDeploymentResponse",description="部署信息")
public class WfDeploymentResponse {

  @ApiModelProperty(value="部署id")
  String id;
  @ApiModelProperty(value="部署名称")
  String name;
  @ApiModelProperty(value="部署时间")
  Date deploymentTime;
  @ApiModelProperty(value="类别")
  String category;
  @ApiModelProperty(value="租户")
  String tenantId;
  
  public WfDeploymentResponse() {
	// TODO Auto-generated constructor stub
}
  
  public WfDeploymentResponse(WfDeployment deployment) {
    setId(deployment.getId());
    setName(deployment.getName());
    setDeploymentTime(deployment.getDeploymentTime());
    setCategory(deployment.getCategory());
    setTenantId(deployment.getTenantId());
  }
  
  public WfDeploymentResponse(Deployment deployment) {
	    setId(deployment.getId());
	    setName(deployment.getName());
	    setDeploymentTime(deployment.getDeploymentTime());
	    setCategory(deployment.getCategory());
	    setTenantId(deployment.getTenantId());
	  }
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public  Date getDeploymentTime() {
    return deploymentTime;
  }
  public void setDeploymentTime( Date deploymentTime) {
    this.deploymentTime = deploymentTime;
  }
  public String getCategory() {
    return category;
  }
  public void setCategory(String category) {
    this.category = category;
  }
  public void setTenantId(String tenantId) {
	  this.tenantId = tenantId;
  }
  public String getTenantId() {
	  return tenantId;
  }
}
