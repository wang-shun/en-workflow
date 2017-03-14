package com.chinacreator.c2.flow.rest.common.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import com.chinacreator.c2.flow.rest.common.vo.WfProcessInstanceActionRequest.InstanceAction;


/**
 * 流程定义请求对象
 * @author hushow
 *
 */
public class WfProcessDefinitionActionRequest {
  
  @ApiModelProperty(value="是否包含子流程实例",required=false)
  private boolean includeProcessInstances = false;
  @ApiModelProperty(value="流程定义日期",required=false)
  private Date date;
  @ApiModelProperty(value="流程定义类别",required=false)
  private String category;
  @ApiModelProperty(value="流程状态类型",required=false)
  private ProcessDefinitionAction action;
  
  public enum ProcessDefinitionAction {
	  SUSPEND,
	  ACTIVATE;
  }
  
  public void setIncludeProcessInstances(boolean includeProcessInstances) {
    this.includeProcessInstances = includeProcessInstances;
  }
  public boolean isIncludeProcessInstances() {
    return includeProcessInstances;
  }
  public void setDate(Date date) {
    this.date = date;
  }
  public Date getDate() {
    return date;
  }
  public void setCategory(String category) {
    this.category = category;
  }
  public String getCategory() {
    return category;
  }
public ProcessDefinitionAction getAction() {
	return action;
}
public void setAction(ProcessDefinitionAction action) {
	this.action = action;
}

  
  
}
