package com.chinacreator.c2.workflow.task.ds;

import java.util.ArrayList;
import java.util.Map;

import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.flow.WfApiFactory;
import com.chinacreator.c2.flow.api.WfRuntimeService;
import com.chinacreator.c2.flow.detail.WfUniteTaskResult;
import com.chinacreator.c2.flow.util.CommonUtil;
import com.chinacreator.c2.ioc.ApplicationContextManager;
import com.chinacreator.c2.sysmgr.AuthenticationProvider;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;

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
		map.put("userId", userId);
		map.put("taskType", taskType);
		
		
		//处理租户待办完全隔离
		String tenantId=WfApiFactory.getWfTenant();
		if(!CommonUtil.stringNullOrEmpty(tenantId)){
			map.put("tenantId",tenantId);
		}else{
			map.put("withoutTenantId",true);
		}
		
		try {
			int offset = context.getPageable().getOffset();
			int pageIndex = context.getPageable().getPageIndex();
			int pageSize = context.getPageable().getPageSize();
			WfUniteTaskResult result = null;
			if("done".equals(taskType)){
				result = wfRuntimeService.queryWfUniteHisTask(map, offset, pageSize);
			}else{
				result = wfRuntimeService.queryWfUniteRunTask(map, offset, pageSize);
			}
			if(result != null){
				page = new Page<Map<String,Object>>(pageIndex, pageSize, result.getTotalResults(), result.getDatas());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

}
