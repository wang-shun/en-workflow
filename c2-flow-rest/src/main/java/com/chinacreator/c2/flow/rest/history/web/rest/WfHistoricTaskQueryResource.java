package com.chinacreator.c2.flow.rest.history.web.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinacreator.c2.flow.rest.common.vo.WfHistoricTaskInstanceQueryRequest;
import com.chinacreator.c2.flow.rest.common.vo.WfHistoricTaskInstanceResponse;
import com.chinacreator.c2.flow.rest.common.vo.WfPageListResponse;
import com.chinacreator.c2.flow.rest.history.HistoricTaskInstanceBaseResource;
import com.chinacreator.c2.web.exception.InvalidRestParamException;
import com.chinacreator.c2.web.exception.ResourceNotFoundException;
import com.chinacreator.c2.web.exception.UnkownException;



/**
 * 历史任务查询接口
 * @author hushow
 */
@Service
@Path("v1/flow/query/history/tasks")
@Api
public class WfHistoricTaskQueryResource extends HistoricTaskInstanceBaseResource {

  @Autowired
  HistoryService historyService;
  
  @ApiOperation(value = "查询历史任务列表", tags = "query")
  @ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),@ApiResponse(code = 404, message = "操作失败，请求资源未找到"),@ApiResponse(code = 500, message = "系统内部错误")  })
  @POST
  public WfPageListResponse<WfHistoricTaskInstanceResponse> queryHistoricTaskInstances(@ApiParam(value = "查询过滤条件",required = true)  WfHistoricTaskInstanceQueryRequest queryRequest) throws Exception{
  	try{
  		return getQueryResponse(historyService.createHistoricTaskInstanceQuery(),queryRequest);
  	}catch (ActivitiIllegalArgumentException e) {
		throw new InvalidRestParamException(e.getMessage());
	} catch (ActivitiObjectNotFoundException e) {
		throw new ResourceNotFoundException(e.getMessage());
	} catch (Exception e) {
		throw new UnkownException(e.getMessage(), e);
	}
    
  }
}
