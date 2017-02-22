package com.chinacreator.c2.flow.rest.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author hushowly
 */
@ApiModel(value="WfPaginateRequest",description="分页请求信息")
public class WfPaginateRequest{

	@ApiModelProperty("开始行")
	protected Integer start;
	@ApiModelProperty("请求大小")
	protected Integer size;
	@ApiModelProperty("排序字段")
	protected String sort;
	@ApiModelProperty("排序方式asc/desc")
	protected String order;

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
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
}
