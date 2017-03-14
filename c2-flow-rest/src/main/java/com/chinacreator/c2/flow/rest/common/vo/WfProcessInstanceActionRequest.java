package com.chinacreator.c2.flow.rest.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * @author hushowly
 */
@ApiModel(value="WfProcessInstanceActionRequest",description="流程实例操作参数")
public class WfProcessInstanceActionRequest extends WfBaseRequest{

  @ApiModelProperty(value="流程实例操作",required=true)
  private InstanceAction action;
  
  public enum InstanceAction {
	  SUSPEND,
	  ACTIVATE;
  }
	
	public InstanceAction getAction() {
		return action;
	}
	
	public void setAction(InstanceAction action) {
		this.action = action;
	}
  
  
  
}
