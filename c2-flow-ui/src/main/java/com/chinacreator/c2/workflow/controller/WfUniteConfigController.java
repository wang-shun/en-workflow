package com.chinacreator.c2.workflow.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chinacreator.c2.flow.WfApiFactory;
import com.chinacreator.c2.flow.api.WfManagerService;
import com.chinacreator.c2.flow.api.WfRuntimeService;
import com.chinacreator.c2.flow.detail.WfUniteColumn;
import com.chinacreator.c2.flow.detail.WfUniteConfig;
import com.chinacreator.c2.flow.detail.WfUniteTaskResult;
import com.chinacreator.c2.web.controller.ResponseFactory;

@Controller
@RequestMapping(value = "/workflow/uniteconfig")
public class WfUniteConfigController {

	WfManagerService wfManagerService = WfApiFactory.getWfManagerService();
	WfRuntimeService wfRuntimeService = WfApiFactory.getWfRuntimeService();

	@RequestMapping(value = "/loadconfig", method = RequestMethod.GET)
	public Object loadMainConfig(String appId,
			String engineName, String taskType) {
		appId = appId == null ? "default" : appId;
		
		//处理租户隔离
		String tenantId=WfApiFactory.getWfTenant();
		tenantId = tenantId == null ? "default" : tenantId;
		
		engineName = engineName == null ? "default" : engineName;
		taskType = taskType == null ? "todo" : taskType;
		Boolean result = false;
		try {
			List<WfUniteConfig> list = wfManagerService.findWfUniteConfig(
					appId, tenantId, engineName, taskType);
			if (list != null) {
				result = list.get(0).getOnlyTitle() == 0 ? false : true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseFactory().createResponseBodyJSONObject(result);
	}

	@RequestMapping(value = "/loadcloumns", method = RequestMethod.GET)
	public Object loadCloumnsConfig(String appId,String engineName, String taskType) {
		appId = appId == null ? "default" : appId;
		
		//处理租户隔离
		String tenantId=WfApiFactory.getWfTenant();
		tenantId = tenantId == null ? "default" : tenantId;
		engineName = engineName == null ? "default" : engineName;
		taskType = taskType == null ? "todolist" : taskType;
		WfUniteTaskResult result = null;
		try {
			result = wfRuntimeService.queryWfUniteConfig(appId,tenantId,engineName,taskType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Object ret = new ResponseFactory().createResponseBodyJSONObject(result);
		return ret;
	}

	@RequestMapping(value = "/savecolumn", method = RequestMethod.POST)
	public Object saveColumnConfig(String id, String columnName, String isShow,
			String isTitle, String sn) {
		WfUniteColumn data = new WfUniteColumn();
		try {
			data.setId(id);
			data.setColumnName(columnName);
			data.setIsShow("Yes".equals(isShow) ? 1 : 0);
			data.setIsTitle("Yes".equals(isTitle) ? 1 : 0);
			if (sn != null) {
				data.setSn(Integer.parseInt(sn));
			}
			wfManagerService.saveWfUniteColumn(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseFactory().createResponseBodyJS("");
	}

	@RequestMapping(value = "/saveconfig", method = RequestMethod.POST)
	public Object saveMainConfig(@RequestBody Map<String, Object> body) {
		String appId = (String) body.get("appId");
		//String tenantId = (String) body.get("tenantId");
		
		//处理租户隔离
		String tenantId=WfApiFactory.getWfTenant();
		String engineName = (String) body.get("engineName");
		String taskType = (String) body.get("taskType");
		Boolean onlyTitle = (Boolean) body.get("onlyTitle");
		appId = appId == null ? "default" : appId;
		tenantId = tenantId == null ? "default" : tenantId;
		engineName = engineName == null ? "default" : engineName;
		taskType = taskType == null ? "todolist" : taskType;
		WfUniteConfig data = new WfUniteConfig();
		try {
			data.setAppId(appId);
			data.setEngineName(engineName);
			data.setTenantId(tenantId);
			data.setTaskList(taskType);
			data.setOnlyTitle((onlyTitle == null || !onlyTitle) ? 0 : 1);
			wfManagerService.saveWfUniteConfig(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseFactory().createResponseBodyJS("");
	}
}
