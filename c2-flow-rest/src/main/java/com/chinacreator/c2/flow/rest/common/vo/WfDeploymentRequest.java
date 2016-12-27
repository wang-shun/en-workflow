package com.chinacreator.c2.flow.rest.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.File;

import javax.ws.rs.FormParam;

@ApiModel(value="WfDeploymentRequest",description="流程定义部署信息")
public class WfDeploymentRequest  extends WfBaseRequest{
	
	@ApiModelProperty(value="部署名称(要以bpmn20.xml或bpmn结尾)",required=true)
	@FormParam("deployName")
	private String deployName;
	
	@ApiModelProperty(value="部署类别",required=false)
	@FormParam("category")
	private String category;

	@ApiModelProperty(value="要部署的流程定义文件",required=true)
	@FormParam("deployResource")
	private File deployResource;

	@ApiModelProperty(value="租户(多应用共享流程中心时使用)",required=false)
	@FormParam("tenantId")
	private String tenantId;

	public String getDeployName() {
		return deployName;
	}

	public void setDeployName(String deployName) {
		this.deployName = deployName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}


	

	public File getDeployResource() {
		return deployResource;
	}

	public void setDeployResource(File deployResource) {
		this.deployResource = deployResource;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	
	
	
	
	
}
