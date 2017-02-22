package com.chinacreator.c2.flow.rest.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * @author hushowly
 */
@ApiModel(value="WfHistoricIdentityLinkResponse",description="历史候选人或候选组信息")
public class WfHistoricIdentityLinkResponse {
  @ApiModelProperty("候选人类型")
  protected String type;
  @ApiModelProperty("候选人")
  protected String userId;
  @ApiModelProperty("候选组")
  protected String groupId;
  @ApiModelProperty("任务id")
  protected String taskId;
  @ApiModelProperty("流程实例id")
  protected String processInstanceId;
  
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getUserId() {
    return userId;
  }
  public void setUserId(String userId) {
    this.userId = userId;
  }
  public String getGroupId() {
    return groupId;
  }
  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }
  public String getTaskId() {
    return taskId;
  }
  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

  public String getProcessInstanceId() {
    return processInstanceId;
  }
  public void setProcessInstanceId(String processInstanceId) {
    this.processInstanceId = processInstanceId;
  }
}
