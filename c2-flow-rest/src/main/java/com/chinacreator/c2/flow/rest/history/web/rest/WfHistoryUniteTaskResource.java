package com.chinacreator.c2.flow.rest.history.web.rest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.chinacreator.c2.flow.api.WfRuntimeService;
import com.chinacreator.c2.flow.detail.WfUniteTaskResult;
import com.chinacreator.c2.flow.rest.common.vo.WfPageListResponse;
import com.chinacreator.c2.flow.rest.common.vo.WfUniteTaskResponse;
import com.chinacreator.c2.web.exception.InvalidRestParamException;
import com.chinacreator.c2.web.exception.ResourceNotFoundException;
import com.chinacreator.c2.web.exception.UnkownException;

/**
 * 历史流程实例rest接口
 * @author hushow
 */
@Service
@Path("v1/flow/history/unite/tasks")
public class WfHistoryUniteTaskResource{

	@Autowired
	WfRuntimeService wfRuntimeService;
	
	@ApiOperation(value = "统一待办列表", tags = "history_unite_task",notes="注：只能用于查询单个用户历史统一待办表中的数据")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "错误的请求参数"),@ApiResponse(code = 404, message = "操作失败，请求资源未找到"),@ApiResponse(code = 500, message = "系统内部错误")  })
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public WfPageListResponse<WfUniteTaskResponse> getHistoryUniteTasks(
									  @ApiParam(value = "用户id,必传，查询此用户待办信息",required=true) @QueryParam("userId") String userId,
									  @ApiParam(value = "当前用户所属组") @QueryParam("groups") String []groups,
									  @ApiParam(value = "任务id") @QueryParam("taskId") String taskId,
									  @ApiParam(value = "业务主键") @QueryParam("businessKey") String businessKey,
									  @ApiParam(value = "执行id") @QueryParam("executionId") String executionId,
									  @ApiParam(value = "流程实例id") @QueryParam("procInstId") String procInstId,
									  @ApiParam(value = "流程定义id") @QueryParam("procDefId") String procDefId,
									  @ApiParam(value = "任务名称") @QueryParam("name") String name,
									  @ApiParam(value = "父任务id") @QueryParam("parentTaskId") String parentTaskId,
									  @ApiParam(value = "任务描述") @QueryParam("description") String description,
									  @ApiParam(value = "任务定义key") @QueryParam("taskDefKey") String taskDefKey,
									  @ApiParam(value = "任务所有者") @QueryParam("owner") String owner,
									  @ApiParam(value = "处理人") @QueryParam("assignee") String assignee,
									  @ApiParam(value = "候选人") @QueryParam("candidate") String candidate,
									  @ApiParam(value = "委托人") @QueryParam("delegation") String delegation,
									  @ApiParam(value = "任务优先级") @QueryParam("priority") String priority,
									  @ApiParam(value = "创建时间") @QueryParam("createTime") java.sql.Date createTime,
									  @ApiParam(value = "创建时间下区间") @QueryParam("createTimeAfter") java.sql.Date createTimeAfter,
									  @ApiParam(value = "创建时间上区间") @QueryParam("createTimeBefore") java.sql.Date createTimeBefore,
									  @ApiParam(value = "结束时间") @QueryParam("endTime") java.sql.Date endTime,
									  @ApiParam(value = "结束时间下区间") @QueryParam("endTimeAfter") java.sql.Date endTimeAfter,
									  @ApiParam(value = "结束时间上区间") @QueryParam("endTimeBefore") java.sql.Date endTimeBefore,
									  @ApiParam(value = "过期时间") @QueryParam("dueDate") java.sql.Date dueDate,
									  @ApiParam(value = "过期时间下区间") @QueryParam("dueDateAfter") String dueDateAfter,
									  @ApiParam(value = "过期时间上区间") @QueryParam("dueDateBefore") String dueDateBefore,
									  @ApiParam(value = "处理人类型") @QueryParam("category") String category,
									  @ApiParam(value = "任务状态") @QueryParam("taskState") String taskState,
									  @ApiParam(value = "租户") @QueryParam("tenantId") String tenantId,
									  @ApiParam(value = "租户模糊匹配") @QueryParam("tenantIdLike") String tenantIdLike,
									  @ApiParam(value = "是否查询非租户任务") @QueryParam("withoutTenantId") Boolean withoutTenantId,
									  @ApiParam(value = "应用id") @QueryParam("appId") String appId,
									  @ApiParam(value = "模块id") @QueryParam("moduleId") String moduleId,
									  @ApiParam(value = "模块名称") @QueryParam("moduleName") String moduleName,
									  @ApiParam(value = "删除原因") @QueryParam("deleteReason") String deleteReason,
									  @ApiParam(value = "表单key") @QueryParam("formKey") String formKey,
									  @ApiParam(value = "版本") @QueryParam("revision") String revision,
									  @ApiParam(value = "备用字段") @QueryParam("remark1") String remark1,
									  @ApiParam(value = "备用字段") @QueryParam("remark2") String remark2,
									  @ApiParam(value = "备用字段") @QueryParam("remark3") String remark3,
									  @ApiParam(value = "备用字段") @QueryParam("remark4") String remark4,
									  @ApiParam(value = "备用字段") @QueryParam("remark5") String remark5,
									  @ApiParam(value = "备用字段") @QueryParam("remark6") String remark6,
									  @ApiParam(value = "备用字段") @QueryParam("remark7") String remark7,
									  @ApiParam(value = "备用字段") @QueryParam("remark8") String remark8,
									  @ApiParam(value = "备用字段") @QueryParam("remark9") String remark9,
									  @ApiParam(value = "备用字段") @QueryParam("remark10") String remark10,
									  @ApiParam(value = "排序字段") @QueryParam("orderByStr") String orderByStr,
									  @ApiParam(value = "分页开始行") @QueryParam("start") Integer start,
									  @ApiParam(value = "分页页大小") @QueryParam("size") Integer size
									  ) throws Exception{

		try{
			
			Map<String,Object> parameters=new HashMap<String, Object>();
			parameters.put("taskType","done");
			
			if(null==start) start=0;
			if(null==size) size=10;
			
			if(StringUtils.hasText(userId)) parameters.put("userId",userId);
			if(null!=groups&&groups.length>0) parameters.put("groups",groups);
			if(StringUtils.hasText(taskId)) parameters.put("taskid",taskId);
			if(StringUtils.hasText(businessKey)) parameters.put("businesskey",businessKey);
			if(StringUtils.hasText(executionId)) parameters.put("executionid",executionId);
			if(StringUtils.hasText(procInstId)) parameters.put("procinstid",procInstId);
			if(StringUtils.hasText(procDefId)) parameters.put("procdefid",procDefId);
			if(StringUtils.hasText(name)) parameters.put("name",name);
			if(StringUtils.hasText(parentTaskId)) parameters.put("parenttaskid",parentTaskId);
			if(StringUtils.hasText(description)) parameters.put("description",description);
			if(StringUtils.hasText(taskDefKey)) parameters.put("taskdefkey",taskDefKey);
			if(StringUtils.hasText(owner)) parameters.put("owner",owner);
			if(StringUtils.hasText(assignee)) parameters.put("assignee",assignee);
			if(StringUtils.hasText(candidate)) parameters.put("candidate",candidate);
			if(StringUtils.hasText(delegation)) parameters.put("delegation",delegation);
			if(StringUtils.hasText(priority)) parameters.put("priority",priority);
			if(null!=createTime) parameters.put("createtime",createTime);
			if(null!=createTimeAfter) parameters.put("createtimeb",createTimeAfter);
			if(null!=createTimeBefore) parameters.put("createtimee",createTimeBefore);
			if(null!=endTime) parameters.put("endtime",endTime);
			if(null!=endTimeAfter) parameters.put("endtimeb",endTimeAfter);
			if(null!=endTimeBefore) parameters.put("endtimee",endTimeBefore);
			if(null!=dueDate) parameters.put("duedate",dueDate);
			if(null!=dueDateAfter) parameters.put("duedateb",dueDateAfter);
			if(null!=dueDateBefore) parameters.put("duedatee",dueDateBefore);
			if(StringUtils.hasText(category)) parameters.put("category",category);
			if(StringUtils.hasText(taskState)) parameters.put("taskstate",taskState);
			if(StringUtils.hasText(tenantId)) parameters.put("tenantId",tenantId);
			if(StringUtils.hasText(tenantIdLike)) parameters.put("tenantIdLike",tenantIdLike);
			if(null!=withoutTenantId) parameters.put("withoutTenantId",withoutTenantId);
			if(StringUtils.hasText(appId)) parameters.put("appid",appId);
			if(StringUtils.hasText(moduleId)) parameters.put("moduleid",moduleId);
			if(StringUtils.hasText(moduleName)) parameters.put("modulename",moduleName);
			if(StringUtils.hasText(deleteReason)) parameters.put("deletereason",deleteReason);
			if(StringUtils.hasText(formKey)) parameters.put("formkey",formKey);
			if(StringUtils.hasText(revision)) parameters.put("revision",revision);
			if(StringUtils.hasText(remark1)) parameters.put("remark1",remark1);
			if(StringUtils.hasText(remark2)) parameters.put("remark2",remark2);
			if(StringUtils.hasText(remark3)) parameters.put("remark3",remark3);
			if(StringUtils.hasText(remark4)) parameters.put("remark4",remark4);
			if(StringUtils.hasText(remark5)) parameters.put("remark5",remark5);
			if(StringUtils.hasText(remark6)) parameters.put("remark6",remark6);
			if(StringUtils.hasText(remark7)) parameters.put("remark7",remark7);
			if(StringUtils.hasText(remark8)) parameters.put("remark8",remark8);
			if(StringUtils.hasText(remark9)) parameters.put("remark9",remark9);
			if(StringUtils.hasText(remark10)) parameters.put("remark10",remark10);
			if(StringUtils.hasText(orderByStr)) parameters.put("orderByStr",orderByStr);
			

			WfUniteTaskResult wfUniteTaskResult=wfRuntimeService.queryWfUniteHisTask(parameters, start.intValue(), size.intValue());
			
			WfPageListResponse<WfUniteTaskResponse> response = new WfPageListResponse<WfUniteTaskResponse>();
		    response.setStart(start);
		    response.setSize(wfUniteTaskResult.getDatas().size()); 
		    response.setOrder(orderByStr);
		    response.setTotal(wfUniteTaskResult.getTotalResults());

		    List<Map<String, Object>>  datas=wfUniteTaskResult.getDatas();
		    List<WfUniteTaskResponse> wUniteTaskResponseList=new ArrayList<WfUniteTaskResponse>();
		    for (Map<String, Object> map : datas) {
		    	
		    	WfUniteTaskResponse wfUniteTaskResponse=new WfUniteTaskResponse();
		    	
		        //BeanCopier copier = BeanCopier.create(map.getClass(),wfUniteTaskResponse.getClass(), false);
		       // copier.copy(map, wfUniteTaskResponse,null);
		    	wfUniteTaskResponse.setTenantId((String)map.get("tenantId"));
		    	wfUniteTaskResponse.setEndTime((Timestamp)map.get("endtime"));
		    	wfUniteTaskResponse.setTaskId((String)map.get("taskid"));
		    	wfUniteTaskResponse.setAppId((String)map.get("appid"));
		    	wfUniteTaskResponse.setCandidate((List<String>)map.get("candidate"));
		    	wfUniteTaskResponse.setCreateTime((Timestamp)map.get("createtime"));
		    	wfUniteTaskResponse.setId((String)map.get("id"));
		    	wfUniteTaskResponse.setDelegation((String)map.get("delegation"));
		    	wfUniteTaskResponse.setModuleName((String)map.get("modulename"));
		    	wfUniteTaskResponse.setRevision((Integer)map.get("revision"));
		    	wfUniteTaskResponse.setDescription((String)map.get("description"));
		    	wfUniteTaskResponse.setFormKey((String)map.get("formkey"));
		    	wfUniteTaskResponse.setPriority((Integer)map.get("priority"));
		    	wfUniteTaskResponse.setExecutionId((String)map.get("executionid"));
		    	wfUniteTaskResponse.setName((String)map.get("name"));
		    	wfUniteTaskResponse.setDueDate((Timestamp)map.get("duedate"));
		    	wfUniteTaskResponse.setModuleId((String)map.get("moduleid"));
		    	wfUniteTaskResponse.setDeleteReason((String)map.get("deletereason"));
		    	wfUniteTaskResponse.setParentTaskId((String)map.get("parenttaskid"));
		    	wfUniteTaskResponse.setProcInstId((String)map.get("procinstid"));
		    	wfUniteTaskResponse.setAssignee((String)map.get("assignee"));
		    	wfUniteTaskResponse.setTaskdefKey((String)map.get("taskdefkey"));
		    	wfUniteTaskResponse.setBusinessKey((String)map.get("businesskey"));
		    	wfUniteTaskResponse.setCategory((String)map.get("category"));
		    	wfUniteTaskResponse.setTaskState((String)map.get("taskstate"));
		    	wfUniteTaskResponse.setProcDefId((String)map.get("procdefid"));
		    	wfUniteTaskResponse.setOwner((String)map.get("owner"));

		    	wUniteTaskResponseList.add(wfUniteTaskResponse);
			}
		    response.setData(wUniteTaskResponseList);
		    
		    return response;
			
		} catch (ActivitiIllegalArgumentException e1) {
			throw new InvalidRestParamException(e1.getMessage());
		} catch (ActivitiObjectNotFoundException e2) {
			throw new ResourceNotFoundException(e2.getMessage());
		} catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		}
		
	}
	
}
