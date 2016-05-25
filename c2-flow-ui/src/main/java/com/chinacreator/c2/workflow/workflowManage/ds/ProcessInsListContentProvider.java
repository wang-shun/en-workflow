package com.chinacreator.c2.workflow.workflowManage.ds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.chinacreator.asp.comp.sys.core.user.service.UserService;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.flow.WfApiFactory;
import com.chinacreator.c2.flow.api.WfHistoryService;
import com.chinacreator.c2.flow.api.WfManagerService;
import com.chinacreator.c2.flow.detail.WfHistoricProcessInstance;
import com.chinacreator.c2.flow.detail.WfHistoricProcessInstanceParam;
import com.chinacreator.c2.flow.detail.WfPageList;
import com.chinacreator.c2.flow.detail.WfProcessDefinition;
import com.chinacreator.c2.flow.util.CommonUtil;
import com.chinacreator.c2.ioc.ApplicationContextManager;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;

public class ProcessInsListContentProvider implements ArrayContentProvider {

	private WfHistoryService wfHistoryService = WfApiFactory.getWfHistoryService();

	private WfManagerService wfManagerService = WfApiFactory.getWfManagerService();

	private UserService userService = (UserService) ApplicationContextManager
			.getContext()
			.getBean(
					"com.chinacreator.asp.comp.sys.basic.user.service.UserServiceImpl");


	@Override
	public Page<WfHistoricProcessInstance> getElements(ArrayContext context) {

		Page<WfHistoricProcessInstance> page = new Page<WfHistoricProcessInstance>(
				new Pageable(), new ArrayList<WfHistoricProcessInstance>());

		// 历史列表
		WfHistoricProcessInstanceParam wfHis = new WfHistoricProcessInstanceParam();
		wfHis.setPaged(true);
		wfHis.setSize(context.getPageable().getPageSize());
		wfHis.setStart(context.getPageable().getOffset());
		// 增加排序条件
		wfHis.setOrderByProcessInstanceStartTime(true);
		wfHis.setOrder(WfHistoricProcessInstanceParam.SORT_DESC);
		
		//处理租户完全隔离
		String tenantId=WfApiFactory.getWfTenant();
		if(!CommonUtil.stringNullOrEmpty(tenantId)){
			wfHis.setTenantId(tenantId);
		}else{
			wfHis.setWithoutTenantId(true);
		}

		Map<String, Object> map = context.getCondition();
		if (null != map && !map.isEmpty()) {
			// 用户ID
			String userId = "";
			
			String userIdInMap = (String) map.get("userId");
			if (!CommonUtil.stringNullOrEmpty(userIdInMap)) {
				com.chinacreator.asp.comp.sys.core.user.dto.UserDTO userDto = userService
						.queryByUserName(userIdInMap);
				userId = userDto.getUserId();
				userId = userId == null ? "null" : userId;
			}
			// 流程标识
			String processDefinitionKey = (String) map
					.get("processDefinitionKey");
			// 业务标识
			String businessKey = (String) map.get("businessKey");
			// 事项ID-下拉数（只选一个）
			String moduleId = (String) map.get("moduleId");

			if (!CommonUtil.stringNullOrEmpty(businessKey)) {
				wfHis.setBusinessKey(businessKey);
			}
			if (!CommonUtil.stringNullOrEmpty(userId)) {
				 wfHis.setInvolvedUser(userId);
			}
			if (!CommonUtil.stringNullOrEmpty(processDefinitionKey)) {
				wfHis.setProcessDefinitionKey(processDefinitionKey);
			}

			if (!CommonUtil.stringNullOrEmpty(moduleId)) {
				String pDefId = "";
				try {
					WfProcessDefinition wpd = wfManagerService
							.getBindProcessByModuleId(moduleId);
					pDefId = wpd.getId();
					// 通过模块事项找不到流程定义
					if (CommonUtil.stringNullOrEmpty(pDefId)) {
						return page;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				wfHis.setProcessDefinitionId(pDefId);
			}
		}
		try {
			WfPageList<WfHistoricProcessInstance, WfHistoricProcessInstanceParam> wfPageList = wfHistoryService
					.queryHistoricProcessInstances(wfHis);
			long size = wfPageList.getWfQuery().getSize();
			long total = wfPageList.getWfQuery().getTotal();
			long pageindex = context.getPageable().getPageIndex();
			Pageable pageable = new Pageable(new Long(pageindex).intValue(),
					new Long(size).intValue());

			List<WfHistoricProcessInstance> list = new ArrayList<WfHistoricProcessInstance>();

			Iterator<WfHistoricProcessInstance> itr = wfPageList.getDatas().iterator();
			while (itr.hasNext()) {
				WfHistoricProcessInstance wpd = itr.next();
				Map<String, Object> variableMap = new HashMap<String, Object>();
				String defId = wpd.getProcessDefinitionId();
				variableMap.put("key", defId.substring(0, defId.indexOf(":")));
				variableMap.put(
						"version",
						defId.substring(defId.indexOf(":") + 1,
								defId.lastIndexOf(":")));

				wpd.setProcessVariables(variableMap);
				list.add(wpd);
			}
			page = new Page<WfHistoricProcessInstance>(pageable.getPageIndex(),
					pageable.getPageSize(), new Long(total).intValue(), list);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
}
