package com.chinacreator.c2.flow.rest.common.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;

/**
 * @author hushowly
 */
@ApiModel(value="WfTaskActionRequest",description="处理任务请求参数")
public class WfTaskActionRequest{
  
  public enum actionType{
	  @ApiModelProperty("签收")
	  CLAIM,
	  @ApiModelProperty("完成")
	  COMPLETE,
	  @ApiModelProperty("签收并完成")
	  CLAIM_COMPLETE,
	  @ApiModelProperty("委托代理")
	  DELEGATE,
	  @ApiModelProperty("拒绝委托代理")
	  RESOLVE,
	  @ApiModelProperty("退回")
	  REJECT
  }
  
  @ApiModelProperty(value="处理任务动作类型",required=true)
  private actionType action;
  
  @ApiModelProperty("处理人(DELEGATE操作类型时必传)")
  private String assignee;
  
  @ApiModelProperty("操作任务所需流程变量")
  private List<WfRestVariable> variables;
  
  public actionType getAction() {
	return action;
}
public void setAction(actionType action) {
	this.action = action;
}
public void setAssignee(String assignee) {
    this.assignee = assignee;
  }
  public String getAssignee() {
    return assignee;
  }
  public void setVariables(List<WfRestVariable> variables) {
    this.variables = variables;
  }
  @JsonTypeInfo(use=Id.CLASS, defaultImpl=WfRestVariable.class)
  public List<WfRestVariable> getVariables() {
    return variables;
  }
}
