package com.chinacreator.c2.flow.rest.client.proxy.web.rest;

import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.c2.flow.rest.ApiClient;
import com.chinacreator.c2.flow.rest.ApiException;
import com.chinacreator.c2.flow.rest.api.HistoryInstanceApi;
import com.chinacreator.c2.flow.rest.api.HistoryTaskApi;
import com.chinacreator.c2.flow.rest.api.QueryApi;
import com.chinacreator.c2.flow.rest.model.PageListResponseWfHistoricTaskInstanceResponse;
import com.chinacreator.c2.flow.rest.model.WfHistoricIdentityLinkResponse;
import com.chinacreator.c2.flow.rest.model.WfHistoricTaskInstanceQueryRequest;
import com.chinacreator.c2.flow.rest.model.WfHistoricTaskInstanceResponse;
import com.chinacreator.c2.web.exception.InvalidRestParamException;
import com.chinacreator.c2.web.exception.ResourceNotFoundException;
import com.chinacreator.c2.web.exception.UnkownException;

/**
 * 流程定义
 * 
 * @author hushow
 * 
 */
@Service
@Path("workflow/service/process-instance")
public class ProcessInstanceProxyResource {

	@GET
	@Produces({"text/javascript"})
	@Path("{processInstanceId}/diagram-layout")
	  public String  getDiagramLayout(@ApiParam(value = "流程实例id") @PathParam("processInstanceId") String processInstanceId,@QueryParam("callback") String callback) throws Exception{
		try{
			String bathPath=ConfigManager.getInstance().getConfig("c2.flow.console.basePath");
			ApiClient apiClient=new ApiClient();
			apiClient.setBasePath(bathPath);
			HistoryInstanceApi historyInstanceApi=new HistoryInstanceApi(apiClient);
			return ClientUtils.filterSuccessJsonp(callback,historyInstanceApi.getHistoryProcessInstanceDiagramLayout(processInstanceId));
		} catch (ApiException e) {
			if(404==e.getCode()){
				return ClientUtils.filterErrorJsonp(callback,e.getCode(),"找不到流程实例："+processInstanceId);
			}else{
				e.printStackTrace();
				return ClientUtils.filterErrorJsonp(callback,e.getCode(),"远程获取流程实例信息失败，请联系管理员");
			}
		}catch (Exception e) {
			e.printStackTrace();
			return ClientUtils.filterErrorJsonp(callback,500,"获取流程实例信息异常，请联系管理员");
		}

	}
	
	
	@GET
	@Produces({"text/javascript"})
	@Path("{processInstanceId}/highlights")
	  public String  getHighlights(@ApiParam(value = "流程实例id") @PathParam("processInstanceId") String processInstanceId,@QueryParam("callback") String callback) throws Exception{
		try{
			String bathPath=ConfigManager.getInstance().getConfig("c2.flow.console.basePath");
			ApiClient apiClient=new ApiClient();
			apiClient.setBasePath(bathPath);
			HistoryInstanceApi historyInstanceApi=new HistoryInstanceApi(apiClient);
			return ClientUtils.filterSuccessJsonp(callback,historyInstanceApi.getHistoryProcessInstanceHighlighted(processInstanceId));
		} catch (ApiException e) {
			if(404==e.getCode()){
				return ClientUtils.filterErrorJsonp(callback,e.getCode(),"找不到流程实例："+processInstanceId);
			}else{
				e.printStackTrace();
				return ClientUtils.filterErrorJsonp(callback,e.getCode(),"远程获取流程实例信息失败，请联系管理员");
			}
		}catch (Exception e) {
			e.printStackTrace();
			return ClientUtils.filterErrorJsonp(callback,500,"获取流程实例信息异常，请联系管理员");
		}

	}
	
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("{processInstanceId}/taskDefinition/{taskDefinitionKey}/monitoring")
	  public List<Object>  getTaskMonitor(@ApiParam(value = "流程实例id") @PathParam("processInstanceId") String processInstanceId,@ApiParam(value = "环节定义key") @PathParam("taskDefinitionKey") String taskDefinitionKey) throws Exception{
		try{
			
			List<Object> reList=new ArrayList<Object>();
			
			if(!StringUtils.hasText(processInstanceId)){
				throw new InvalidRestParamException("The processInstanceId cannot be null");
			}
			
			if(!StringUtils.hasText(taskDefinitionKey)){
				throw new InvalidRestParamException("The taskDefinitionKey cannot be null");
			}
			
			String bathPath=ConfigManager.getInstance().getConfig("c2.flow.console.basePath");
			ApiClient apiClient=new ApiClient();
			apiClient.setBasePath(bathPath);
			
			QueryApi QueryApi=new QueryApi(apiClient);
			
			HistoryTaskApi historyTaskApi=new HistoryTaskApi(apiClient);
			
			WfHistoricTaskInstanceQueryRequest wfHistoricTaskInstanceQueryRequest=new WfHistoricTaskInstanceQueryRequest();
			wfHistoricTaskInstanceQueryRequest.setProcessInstanceId(processInstanceId);
			wfHistoricTaskInstanceQueryRequest.setTaskDefinitionKey(taskDefinitionKey);
			wfHistoricTaskInstanceQueryRequest.start(0);
			wfHistoricTaskInstanceQueryRequest.size(10000);
			PageListResponseWfHistoricTaskInstanceResponse pageList=QueryApi.queryHistoricTaskInstances(wfHistoricTaskInstanceQueryRequest);
			for (WfHistoricTaskInstanceResponse task : pageList.getData()) {
				Map<String,Object> row=new HashMap<String, Object>();
				row.put("id", task.getId());
				row.put("name", task.getName());
				row.put("assignee", task.getAssignee());
				row.put("claimTime", task.getClaimTime());
				row.put("dueDate", task.getDueDate());
				row.put("durationInMillis", task.getDurationInMillis());
				row.put("startTime", task.getStartTime());
				row.put("endTime", task.getEndTime());
				row.put("workTimeInMillis", task.getWorkTimeInMillis());
				row.put("description", task.getDescription());
				row.put("category", task.getCategory());
				row.put("priority", task.getPriority());
				
				
				//候选人暂时单独再次查询，以后待办接口支持直接返回集合
				String userStr="";
				String groupStr="";
				List<WfHistoricIdentityLinkResponse> userList=historyTaskApi.getHistoryTaskIdentityLinks(task.getId());
				for (WfHistoricIdentityLinkResponse wlr : userList) {
					if(StringUtils.hasText(wlr.getUserId())){
						userStr+=","+wlr.getUserId();
					}else if(StringUtils.hasText(wlr.getGroupId())){
						groupStr+=","+wlr.getUserId();
					}
				}
				userStr=userStr.replaceFirst(",","");
				groupStr=groupStr.replaceFirst(",","");
				
				String candidateStr="";
				if(!"".equals(userStr)) candidateStr+="用户["+userStr+"]";
				if(!"".equals(groupStr)) candidateStr+="组["+groupStr+"]";
				row.put("candidate",candidateStr);
				reList.add(row);
			}
			return reList;
			
		} catch (ApiException e) {
			if(404==e.getCode()){
				throw new ResourceNotFoundException(e.getMessage());
			}else{
				e.printStackTrace();
				throw new UnkownException("获取环节监控信息异常："+e.getMessage());
			}
		}catch (Exception e) {
			throw new UnkownException(e.getMessage(), e);
		}
	}
	
}
