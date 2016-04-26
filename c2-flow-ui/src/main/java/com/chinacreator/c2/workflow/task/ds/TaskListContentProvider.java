package com.chinacreator.c2.workflow.task.ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.chinacreator.asp.comp.sys.advanced.user.service.UserService;
import com.chinacreator.asp.comp.sys.core.user.dto.UserDTO;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.flow.WfApiFactory;
import com.chinacreator.c2.flow.api.WfRuntimeService;
import com.chinacreator.c2.flow.detail.ChooseGroup;
import com.chinacreator.c2.flow.detail.WfUniteTaskResult;
import com.chinacreator.c2.flow.util.CommonUtil;
import com.chinacreator.c2.ioc.ApplicationContextManager;
import com.chinacreator.c2.sysmgr.AuthenticationProvider;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;
import com.chinacreator.c2.workflow.util.WorkflowUtils;

public class TaskListContentProvider implements ArrayContentProvider {
	private AuthenticationProvider authenticationProvider = (AuthenticationProvider)ApplicationContextManager.getContext().getBean(AuthenticationProvider.class);

	@Override
	public Page<Map<String,Object>> getElements(ArrayContext context) {
		
		WfRuntimeService wfRuntimeService = WfApiFactory.getWfRuntimeService();
		Page<Map<String,Object>> page = new Page<Map<String,Object>>(new Pageable(),
				new ArrayList<Map<String,Object>>());
		Map<String, Object> map = context.getCondition();
		String appId = (String)map.get("appId");
		appId = appId == null ? "default" : appId;
		String engineName = (String)map.get("engineName");
		engineName = engineName == null ? "default" : engineName;
		String taskType = (String)map.get("taskType");
		taskType = taskType == null ? "todo" : taskType;
		//用户ID
		String userId = authenticationProvider.getSubject().getId();
		map.put("taskType", taskType);
		
		//处理租户待办完全隔离
		String tenantId=WfApiFactory.getWfTenant();
		if(!CommonUtil.stringNullOrEmpty(tenantId)){
			map.put("tenantId",tenantId);
		}else{
			map.put("withoutTenantId",true);
		}
		
		//获取当前用户工作流所有组
		List<ChooseGroup> candidateGroupList=WorkflowUtils.getGroupsByUserId(userId);
		
		try {
			int offset = context.getPageable().getOffset();
			int pageIndex = context.getPageable().getPageIndex();
			int pageSize = context.getPageable().getPageSize();
			WfUniteTaskResult result = null;
			if("done".equals(taskType)){
				result = wfRuntimeService.queryWfUniteHisTask(userId,candidateGroupList,map, offset, pageSize);
			}else{
				result = wfRuntimeService.queryWfUniteRunTask(userId,candidateGroupList,map, offset, pageSize);
			}
			if(result != null){
				
				//将引擎待办中的用户和组id转换成当前应用内的中文名[考虑引擎独立布署机构不统一情况]
				List<Map<String,Object>> dataList=result.getDatas();
				for (Object object : dataList) {
					
					@SuppressWarnings("unchecked")
					Map<String,Object> rowObj=(Map<String, Object>)object;
					@SuppressWarnings("unchecked")
					List<String> candidateList=(List<String> )rowObj.get("candidate");
					String category=(String)rowObj.get("category");
					String assignee=(String)rowObj.get("assignee");
					
					//转换处理人
					if(StringUtils.isNotEmpty(assignee)){
						UserService userService = ApplicationContextManager.getContext().getBean(UserService.class);
						UserDTO userDto=userService.queryByPK(assignee);
						if(null!=userDto&&StringUtils.isNotEmpty(userDto.getUserRealname())){
							rowObj.put("assignee",userDto.getUserRealname());
						}
					}
					
					
					if(null==candidateList) continue;
					
					
					//候选人转换处理
					List<String> candidateLabels=new ArrayList<String>();
					
					if("groups".equals(category)){
						for (String str : candidateList) {
							String groupDisplay=WorkflowUtils.parseToGroupDisplayName(str);
							if(!StringUtils.isEmpty(groupDisplay)){
								candidateLabels.add(groupDisplay);
							}else{
								candidateLabels.add(str);
							}
						}
						
					}else if("users".equals(category)){
						for (String userIdStr : candidateList) {
							UserService userService = ApplicationContextManager.getContext().getBean(UserService.class);
							UserDTO userDto=userService.queryByPK(userIdStr);
							if(null==userDto||StringUtils.isEmpty(userDto.getUserRealname())){
								candidateLabels.add(userIdStr);
							}else{
								candidateLabels.add(userDto.getUserRealname());
							}
						}
						
					}else{
						candidateLabels=candidateList;
					}
					
					rowObj.put("candidate", candidateLabels);
				}
				page = new Page<Map<String,Object>>(pageIndex, pageSize, result.getTotalResults(), result.getDatas());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

}
