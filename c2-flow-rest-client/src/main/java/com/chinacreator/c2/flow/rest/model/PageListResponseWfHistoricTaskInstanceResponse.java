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
import com.chinacreator.c2.flow.rest.model.WfHistoricTaskInstanceResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;
/**
 * 数据集响应模型
 */
@ApiModel(description = "数据集响应模型")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-12-28T16:50:41.068+08:00")
public class PageListResponseWfHistoricTaskInstanceResponse  implements Serializable {
  @JsonProperty("data")
  private List<WfHistoricTaskInstanceResponse> data = new ArrayList<WfHistoricTaskInstanceResponse>();

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

  public PageListResponseWfHistoricTaskInstanceResponse data(List<WfHistoricTaskInstanceResponse> data) {
    this.data = data;
    return this;
  }

  public PageListResponseWfHistoricTaskInstanceResponse addDataItem(WfHistoricTaskInstanceResponse dataItem) {
    this.data.add(dataItem);
    return this;
  }

   /**
   * 当前页数据集合
   * @return data
  **/
  @ApiModelProperty(example = "null", value = "当前页数据集合")
  public List<WfHistoricTaskInstanceResponse> getData() {
    return data;
  }

  public void setData(List<WfHistoricTaskInstanceResponse> data) {
    this.data = data;
  }

  public PageListResponseWfHistoricTaskInstanceResponse total(Long total) {
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

  public PageListResponseWfHistoricTaskInstanceResponse start(Integer start) {
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

  public PageListResponseWfHistoricTaskInstanceResponse sort(String sort) {
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

  public PageListResponseWfHistoricTaskInstanceResponse order(String order) {
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

  public PageListResponseWfHistoricTaskInstanceResponse size(Integer size) {
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
    PageListResponseWfHistoricTaskInstanceResponse pageListResponseWfHistoricTaskInstanceResponse = (PageListResponseWfHistoricTaskInstanceResponse) o;
    return Objects.equals(this.data, pageListResponseWfHistoricTaskInstanceResponse.data) &&
        Objects.equals(this.total, pageListResponseWfHistoricTaskInstanceResponse.total) &&
        Objects.equals(this.start, pageListResponseWfHistoricTaskInstanceResponse.start) &&
        Objects.equals(this.sort, pageListResponseWfHistoricTaskInstanceResponse.sort) &&
        Objects.equals(this.order, pageListResponseWfHistoricTaskInstanceResponse.order) &&
        Objects.equals(this.size, pageListResponseWfHistoricTaskInstanceResponse.size);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, total, start, sort, order, size);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PageListResponseWfHistoricTaskInstanceResponse {\n");
    
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

