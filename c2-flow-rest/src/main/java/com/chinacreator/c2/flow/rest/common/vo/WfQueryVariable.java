package com.chinacreator.c2.flow.rest.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import org.activiti.rest.service.api.engine.variable.QueryVariable.QueryVariableOperation;

/**
 * @author hushowy
 */
@ApiModel(value="WfQueryVariable",description="变量查询参数")
public class WfQueryVariable {

  @ApiModelProperty("变量名称")
  private String name;
  @ApiModelProperty("变量值匹配操作")
  private QueryVariableOperation variableOperation;
  @ApiModelProperty("变量值")
  private Object value;
  @ApiModelProperty("变量类型")
  private String type;
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public QueryVariableOperation getVariableOperation() {
	return variableOperation;
}

public void setVariableOperation(QueryVariableOperation variableOperation) {
	this.variableOperation = variableOperation;
}

public Object getValue() {
    return value;
  }
  
  public void setValue(Object value) {
    this.value = value;
  }
  
  public String getType() {
    return type;
  }
  
  public void setType(String type) {
    this.type = type;
  }
  
}
