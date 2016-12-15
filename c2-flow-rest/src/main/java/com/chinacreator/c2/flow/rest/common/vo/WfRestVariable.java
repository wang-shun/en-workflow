package com.chinacreator.c2.flow.rest.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import org.activiti.rest.service.api.engine.variable.RestVariable.RestVariableScope;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;


/**
 * @author hushowly
 */
@ApiModel(value="WfRestVariable",description="流程变量")
public class WfRestVariable{

  @ApiModelProperty("变量名")
  private String name;
  @ApiModelProperty(value="变量类型",allowableValues="string,boolean,date,double,integer,long,short,java.util.List,java.util.Map")
  private String type;
  @ApiModelProperty(value="变量作用域")
  private RestVariableScope variableScope;
  @ApiModelProperty("变量值")
  private Object value;
  
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  @JsonSerialize(include=Inclusion.NON_NULL)
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }

  public Object getValue() {
    return value;
  }
  public void setValue(Object value) {
    this.value = value;
  }
  public RestVariableScope getVariableScope() {
	return variableScope;
  }
  public void setVariableScope(RestVariableScope variableScope) {
	this.variableScope = variableScope;
  }
  
}
