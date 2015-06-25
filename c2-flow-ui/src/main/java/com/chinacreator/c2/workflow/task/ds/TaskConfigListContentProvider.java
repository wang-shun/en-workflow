package com.chinacreator.c2.workflow.task.ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.flow.WfApiFactory;
import com.chinacreator.c2.flow.api.WfManagerService;
import com.chinacreator.c2.flow.detail.WfUniteColumn;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;

public class TaskConfigListContentProvider implements ArrayContentProvider {

	@Override
	public Page<WfUniteColumn> getElements(ArrayContext context) {
		WfManagerService wfManagerService = WfApiFactory.getWfManagerService();
		Page<WfUniteColumn> page = new Page<WfUniteColumn>(new Pageable(),
				new ArrayList<WfUniteColumn>());
		Map<String, Object> map = context.getCondition();
		String appId = (String)map.get("appId");
		appId = appId == null ? "default" : appId;
		String tenantId = (String)map.get("tenantId");
		tenantId = tenantId == null ? "default" : tenantId;
		String engineName = (String)map.get("engineName");
		engineName = engineName == null ? "default" : engineName;
		String taskType = (String)map.get("taskType");
		taskType = taskType == null ? "todolist" : taskType;
		try {
			List<WfUniteColumn> list = wfManagerService.findWfUniteColumns(appId, tenantId, engineName,
					taskType);
			int pageIndex = context.getPageable().getPageIndex();
			int pageSize = context.getPageable().getPageSize();
			if(list != null){
				page = new Page<WfUniteColumn>(pageIndex, pageSize, list.size(), list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

}
