package com.chinacreator.c2.flow.rest.common;

import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.impl.AbstractQuery;
import org.activiti.engine.query.Query;
import org.activiti.engine.query.QueryProperty;

import com.chinacreator.c2.flow.rest.common.vo.WfPageListResponse;
import com.chinacreator.c2.flow.rest.common.vo.WfPaginateRequest;

/**
 * @author hushowly
 */
public abstract class WfAbstractPaginateList<T> {

	/**
   * uses the pagination parameters form the request and makes sure to order the result and set all pagination
   * attributes for the response to render
   *
   * @param req The request containing the pagination parameters
   * @param query The query to get the paged list from
   * @param listName The name model attribute name to use for the result list
   * @param model The model to put the list and the pagination attributes in
   * @param defaultSort THe default sort column (the rest attribute) that later will be mapped to an internal engine name
   */
  @SuppressWarnings("rawtypes")
  public WfPageListResponse<T> paginateList(WfPaginateRequest paginateRequest, Query query,
      String defaultSort, Map<String, QueryProperty> properties) {
  	
  	if(paginateRequest == null) {
  		paginateRequest = new WfPaginateRequest();
  	}
  	
  	// Use defaults for paging, if not set in the PaginationRequest, nor in the URL
  	Integer start = paginateRequest.getStart();
  	if(start == null || start < 0) {
  		start = 0;
  	}
  	
    Integer size = paginateRequest.getSize();
    if(size == null || size < 0) {
    	size = 10;
    }
    
    String sort = paginateRequest.getSort();
    if(sort == null) {
      sort = defaultSort;
    }
    String order = paginateRequest.getOrder();
    if(order == null) {
      order = "asc";
    }

    // Sort order
    if (sort != null && properties.size() > 0) {
      QueryProperty qp = properties.get(sort);
      if (qp == null) {
        throw new ActivitiIllegalArgumentException("Value for param 'sort' is not valid, '" + sort + "' is not a valid property");
      }
      ((AbstractQuery) query).orderBy(qp);
      if (order.equals("asc")) {
        query.asc();
      }
      else if (order.equals("desc")) {
        query.desc();
      }
      else {
        throw new ActivitiIllegalArgumentException("Value for param 'order' is not valid : '" + order + "', must be 'asc' or 'desc'");
      }
    }

    // Get result and set pagination parameters
    List<T> list = processList(query.listPage(start, size));
    WfPageListResponse<T> response = new WfPageListResponse<T>();
    response.setStart(start);
    response.setSize(list.size()); 
    response.setSort(sort);
    response.setOrder(order);
    response.setTotal(query.count());
    response.setData(list);
    return response;
  }
  
  
  /**
   * uses the pagination parameters from the request and makes sure to order the result and set all pagination
   * attributes for the response to render
   *
   * @param req The request containing the pagination parameters
   * @param query The query to get the paged list from
   * @param listName The name model attribute name to use for the result list
   * @param model The model to put the list and the pagination attributes in
   * @param defaultSort THe default sort column (the rest attribute) that later will be mapped to an internal engine name
   */
  @SuppressWarnings("rawtypes")
  public WfPageListResponse<T> paginateList(Query query,
      String defaultSort, Map<String, QueryProperty> properties) {
  	return paginateList(null, query, defaultSort, properties);
  }
  
  @SuppressWarnings("rawtypes")
  protected abstract List<T> processList(List list);
}
