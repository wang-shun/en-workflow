package com.chinacreator.c2.flow.rest.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * @author hushowly
 */
@ApiModel(value="WfCommentRequest",description="评论意见信息")
public class WfCommentRequest {
  @ApiModelProperty("意见作者")
  private String author;
  @ApiModelProperty("意见内容")
  private String message;
  
  public String getAuthor() {
    return author;
  }
  
  public void setAuthor(String author) {
    this.author = author;
  }
  
  public String getMessage() {
    return message;
  }
  
  public void setMessage(String message) {
    this.message = message;
  }
}
