package com.chinacreator.c2.flow.rest.runtime.web.rest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinacreator.c2.flow.query.WfTaskQueryImpl;
import com.chinacreator.c2.flow.rest.common.vo.WfPageListResponse;
import com.chinacreator.c2.flow.rest.common.vo.WfTaskQueryRequest;
import com.chinacreator.c2.flow.rest.common.vo.WfTaskResponse;
import com.chinacreator.c2.flow.rest.runtime.TaskBaseResource;
import com.chinacreator.c2.web.exception.InvalidRestParamException;
import com.chinacreator.c2.web.exception.ResourceNotFoundException;
import com.chinacreator.c2.web.exception.UnkownException;

/**
 * 任务查询接口
 * @author hushow
 */
@Service
@Path("v1/flow/query/runtime")
@Api
public class WfTaskQueryResource extends TaskBaseResource{

	@Autowired
	TaskService taskService;
	
	@ApiOperation(value = "查询待办列表", tags = "query")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),@ApiResponse(code = 404, message = "操作失败，请求资源未找到"),@ApiResponse(code = 500, message = "系统内部错误")  })
	@POST
	@Path("/tasks")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public WfPageListResponse<WfTaskResponse> getQueryResult(@ApiParam(value = "查询过滤条件",required = true) WfTaskQueryRequest request) throws Exception{
		
		try{

			TaskServiceImpl taskServiceImpl=(TaskServiceImpl)taskService;
			WfTaskQueryImpl c2TaskQueryImpl=new WfTaskQueryImpl(taskServiceImpl.getCommandExecutor());
		    return getTasksFromQueryRequest(c2TaskQueryImpl,request);
		    
		} catch (ActivitiIllegalArgumentException e) {
			throw new InvalidRestParamException(e.getMessage());
		} catch (ActivitiObjectNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		} catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		}
	}
}
