package com.chinacreator.c2.flow.rest.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * @author hushowly
 */
@ApiModel(value="WfCommentResponse",description="评论意见信息")
public class WfCommentResponse {
  @ApiModelProperty("意见id")
  private String id;
  @ApiModelProperty("意见作者")
  private String author;
  @ApiModelProperty("意见内容")
  private String message;
  
  public void setId(String id) {
    this.id = id;
  }
  
  public String getId() {
    return id;
  }
  
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
