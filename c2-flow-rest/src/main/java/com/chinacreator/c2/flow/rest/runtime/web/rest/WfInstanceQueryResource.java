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
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.chinacreator.c2.flow.rest.common.vo.WfPageListResponse;
import com.chinacreator.c2.flow.rest.common.vo.WfProcessInstanceQueryRequest;
import com.chinacreator.c2.flow.rest.common.vo.WfProcessInstanceResponse;
import com.chinacreator.c2.flow.rest.runtime.BaseProcessInstanceResource;
import com.chinacreator.c2.flow.rest.runtime.WfProcessInstancePaginateList;
import com.chinacreator.c2.web.exception.InvalidRestParamException;
import com.chinacreator.c2.web.exception.ResourceNotFoundException;
import com.chinacreator.c2.web.exception.UnkownException;



/**
 * @author hushowly
 */
/**
 * 任务查询接口
 * @author hushow
 */
@Controller
@Path("v1/flow/query/runtime")
@Api
public class WfInstanceQueryResource extends BaseProcessInstanceResource{

	@Autowired
	RuntimeService runtimeService;
	
	
	@ApiOperation(value = "查询流程实例列表", tags = "query")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),@ApiResponse(code = 404, message = "操作失败，请求资源未找到"),@ApiResponse(code = 500, message = "系统内部错误")  })
	@POST
	@Path("/instances")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public WfPageListResponse<WfProcessInstanceResponse> queryProcessInstances(@ApiParam(value = "查询过滤条件",required = true) WfProcessInstanceQueryRequest queryRequest) throws Exception {
		try{
			 ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery();

			    // Populate query based on request
			    if (queryRequest.getProcessInstanceId() != null) {
			      query.processInstanceId(queryRequest.getProcessInstanceId());
			    }
			    if (queryRequest.getProcessDefinitionKey() != null) {
			      query.processDefinitionKey(queryRequest.getProcessDefinitionKey());
			    }
			    if (queryRequest.getProcessDefinitionId() != null) {
			      query.processDefinitionId(queryRequest.getProcessDefinitionId());
			    }
			    if (queryRequest.getProcessBusinessKey() != null) {
			      query.processInstanceBusinessKey(queryRequest.getProcessBusinessKey());
			    }
			    if (queryRequest.getInvolvedUser() != null) {
			      query.involvedUser(queryRequest.getInvolvedUser());
			    }
			    if (queryRequest.getSuspended() != null) {
			      if (queryRequest.getSuspended()) {
			        query.suspended();
			      } else {
			        query.active();
			      }
			    }
			    if (queryRequest.getSubProcessInstanceId() != null) {
			      query.subProcessInstanceId(queryRequest.getSubProcessInstanceId());
			    }
			    if (queryRequest.getSuperProcessInstanceId() != null) {
			      query.superProcessInstanceId(queryRequest.getSuperProcessInstanceId());
			    }
			    if (queryRequest.getExcludeSubprocesses() != null) {
			      query.excludeSubprocesses(queryRequest.getExcludeSubprocesses());
			    }
			    if (queryRequest.getIncludeProcessVariables() != null) {
			      if (queryRequest.getIncludeProcessVariables()) {
			        query.includeProcessVariables();
			      }
			    }
			    if (queryRequest.getVariables() != null) {
			      addVariables(query, queryRequest.getVariables());
			    }
			    
			    if(queryRequest.getTenantId() != null) {
			    	query.processInstanceTenantId(queryRequest.getTenantId());
			    }
			    
			    if(queryRequest.getTenantIdLike() != null) {
			    	query.processInstanceTenantIdLike(queryRequest.getTenantIdLike());
			    }
			    
			    if(Boolean.TRUE.equals(queryRequest.getWithoutTenantId())) {
			    	query.processInstanceWithoutTenantId();
			    }

			    return new WfProcessInstancePaginateList().paginateList(queryRequest, query, "id", allowedSortProperties);
		} catch (ActivitiIllegalArgumentException e) {
			throw new InvalidRestParamException(e.getMessage());
		} catch (ActivitiObjectNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		} catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		}
	}

}
