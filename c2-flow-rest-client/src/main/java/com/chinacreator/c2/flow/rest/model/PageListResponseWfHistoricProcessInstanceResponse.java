/**
 * 工作流接口文档
 * 工作流接口文档
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.chinacreator.c2.flow.rest.model;

import java.util.Objects;
import com.chinacreator.c2.flow.rest.model.WfHistoricProcessInstanceResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;


/**
 * 数据集响应模型
 */
@ApiModel(description = "数据集响应模型")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-12-28T13:50:28.213+08:00")
public class PageListResponseWfHistoricProcessInstanceResponse   {
  @JsonProperty("data")
  private List<WfHistoricProcessInstanceResponse> data = new ArrayList<WfHistoricProcessInstanceResponse>();

  @JsonProperty("total")
  private Long total = null;

  @JsonProperty("start")
  private Integer start = null;

  @JsonProperty("sort")
  private String sort = null;

  @JsonProperty("order")
  private String order = null;

  @JsonProperty("size")
  private Integer size = null;

  public PageListResponseWfHistoricProcessInstanceResponse data(List<WfHistoricProcessInstanceResponse> data) {
    this.data = data;
    return this;
  }

  public PageListResponseWfHistoricProcessInstanceResponse addDataItem(WfHistoricProcessInstanceResponse dataItem) {
    this.data.add(dataItem);
    return this;
  }

   /**
   * 当前页数据集合
   * @return data
  **/
  @ApiModelProperty(example = "null", value = "当前页数据集合")
  public List<WfHistoricProcessInstanceResponse> getData() {
    return data;
  }

  public void setData(List<WfHistoricProcessInstanceResponse> data) {
    this.data = data;
  }

  public PageListResponseWfHistoricProcessInstanceResponse total(Long total) {
    this.total = total;
    return this;
  }

   /**
   * 总记录数
   * @return total
  **/
  @ApiModelProperty(example = "null", value = "总记录数")
  public Long getTotal() {
    return total;
  }

  public void setTotal(Long total) {
    this.total = total;
  }

  public PageListResponseWfHistoricProcessInstanceResponse start(Integer start) {
    this.start = start;
    return this;
  }

   /**
   * 请求起始行
   * @return start
  **/
  @ApiModelProperty(example = "null", value = "请求起始行")
  public Integer getStart() {
    return start;
  }

  public void setStart(Integer start) {
    this.start = start;
  }

  public PageListResponseWfHistoricProcessInstanceResponse sort(String sort) {
    this.sort = sort;
    return this;
  }

   /**
   * 排序字段
   * @return sort
  **/
  @ApiModelProperty(example = "null", value = "排序字段")
  public String getSort() {
    return sort;
  }

  public void setSort(String sort) {
    this.sort = sort;
  }

  public PageListResponseWfHistoricProcessInstanceResponse order(String order) {
    this.order = order;
    return this;
  }

   /**
   * 排序方式(asc/desc)
   * @return order
  **/
  @ApiModelProperty(example = "null", value = "排序方式(asc/desc)")
  public String getOrder() {
    return order;
  }

  public void setOrder(String order) {
    this.order = order;
  }

  public PageListResponseWfHistoricProcessInstanceResponse size(Integer size) {
    this.size = size;
    return this;
  }

   /**
   * 请求大小
   * @return size
  **/
  @ApiModelProperty(example = "null", value = "请求大小")
  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PageListResponseWfHistoricProcessInstanceResponse pageListResponseWfHistoricProcessInstanceResponse = (PageListResponseWfHistoricProcessInstanceResponse) o;
    return Objects.equals(this.data, pageListResponseWfHistoricProcessInstanceResponse.data) &&
        Objects.equals(this.total, pageListResponseWfHistoricProcessInstanceResponse.total) &&
        Objects.equals(this.start, pageListResponseWfHistoricProcessInstanceResponse.start) &&
        Objects.equals(this.sort, pageListResponseWfHistoricProcessInstanceResponse.sort) &&
        Objects.equals(this.order, pageListResponseWfHistoricProcessInstanceResponse.order) &&
        Objects.equals(this.size, pageListResponseWfHistoricProcessInstanceResponse.size);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, total, start, sort, order, size);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PageListResponseWfHistoricProcessInstanceResponse {\n");
    
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    start: ").append(toIndentedString(start)).append("\n");
    sb.append("    sort: ").append(toIndentedString(sort)).append("\n");
    sb.append("    order: ").append(toIndentedString(order)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

