package com.chinacreator.c2.workflow.workflowManage.ds;

import java.util.ArrayList;
import java.util.Map;

import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.flow.WfApiFactory;
import com.chinacreator.c2.flow.api.WfRepositoryService;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;

public class ActivityVariablesListContentProvider implements ArrayContentProvider{

	private WfRepositoryService wfRepositoryService = WfApiFactory.getWfRepositoryService();
	
	@Override
	public Page<Map<String, Object>> getElements(ArrayContext context) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(new Pageable(), new ArrayList<Map<String, Object>>());
		
		Map<String, Object> map = context.getCondition();
		String processDefinitionId = (String)map.get("processDefinitionId");
		String taskDefKey = (String)map.get("taskDefKey");
		if((null!=processDefinitionId && !"".equals(processDefinitionId.trim())) && (null!=taskDefKey && !"".equals(taskDefKey.trim()))){
			//查询流程定义中环节绑定的属性
			try {
				//获取当前活动的下一步活动ID
				String nextTaskDefKey = wfRepositoryService.getNextActivityId(processDefinitionId, taskDefKey);
						
				//获取下一步活动的参数
				Map<String, Object> variableMap = wfRepositoryService.queryVariablesOfActivityInDefinition(processDefinitionId, nextTaskDefKey);
				System.out.println(variableMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return page;
	}

}
