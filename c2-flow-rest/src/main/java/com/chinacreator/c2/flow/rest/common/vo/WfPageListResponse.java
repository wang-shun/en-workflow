package com.chinacreator.c2.flow.rest.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author hushowly
 */
@ApiModel(value="PageListResponse", description="数据集响应模型")
public class WfPageListResponse<T> {
  
  @ApiModelProperty(value = "当前页数据集合")
  List<T> data;
  @ApiModelProperty(value = "总记录数")
  long total;
  @ApiModelProperty(value = "请求起始行")
  int start;
  @ApiModelProperty(value = "排序字段")
  String sort;
  @ApiModelProperty(value = "排序方式(asc/desc)")
  String order;
  @ApiModelProperty(value = "请求大小")
  int size;
  
public List<T> getData() {
	return data;
}
public void setData(List<T> data) {
	this.data = data;
}

public long getTotal() {
	return total;
}
public void setTotal(long total) {
	this.total = total;
}
public int getStart() {
	return start;
}
public void setStart(int start) {
	this.start = start;
}
public String getSort() {
	return sort;
}
public void setSort(String sort) {
	this.sort = sort;
}
public String getOrder() {
	return order;
}
public void setOrder(String order) {
	this.order = order;
}
public int getSize() {
	return size;
}
public void setSize(int size) {
	this.size = size;
}
  
  
 
}
