package com.chinacreator.c2.flow.rest.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value="WfJumpRequest", description="流程跳转入参")
public class WfJumpRequest {
	
	@ApiModelProperty(value = "流程实例id")
	private String processInstanceId;
	@ApiModelProperty(value = "目标环节key")
	private String destTaskDefinitionKey;
	@ApiModelProperty(value = "流程变量")
	private List<WfRestVariable> variables;
	
	
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
	public String getDestTaskDefinitionKey() {
		return destTaskDefinitionKey;
	}
	public void setDestTaskDefinitionKey(String destTaskDefinitionKey) {
		this.destTaskDefinitionKey = destTaskDefinitionKey;
	}
	
	
	public List<WfRestVariable> getVariables() {
		return variables;
	}
	public void setVariables(List<WfRestVariable> variables) {
		this.variables = variables;
	}
	
	
	
}
