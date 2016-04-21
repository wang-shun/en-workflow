package com.chinacreator.c2.flow.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chinacreator.c2.flow.detail.WfUniteColumn;
import com.chinacreator.c2.flow.detail.WfUniteTaskResult;

public class WfUtils {
	
	public static Map<String,Object> getPersistentState(Map<String,Object> datas) {
		Map<String, Object> persistentState = new HashMap<String, Object>();
		persistentState.put("id",datas.get("id"));
		persistentState.put("appid", datas.get("appId"));
		persistentState.put("assignee",datas.get("assignee"));
		persistentState.put("businesskey",datas.get("businessKey"));
		persistentState.put("candidate", datas.get("candidate"));
		persistentState.put("category",datas.get("category"));
		persistentState.put("createtime",datas.get("createTime"));
		persistentState.put("endtime",datas.get("endTime"));
		persistentState.put("delegation",datas.get("delegation"));
		persistentState.put("deletereason",datas.get("deleteReason"));
		persistentState.put("description",datas.get("description"));
		persistentState.put("duedate",datas.get("dueDate"));
		persistentState.put("executionid",datas.get("executionId"));
		persistentState.put("formkey",datas.get("formKey"));
		persistentState.put("moduleid",datas.get("moduleId"));
		persistentState.put("modulename",datas.get("moduleName"));
		persistentState.put("name",datas.get("name"));
		persistentState.put("owner",datas.get("owner"));
		persistentState.put("parenttaskid",datas.get("parentTaskId"));
		persistentState.put("priority",datas.get("priority"));
		persistentState.put("procdefid",datas.get("procDefId"));
		persistentState.put("procinstid",datas.get("procInstId"));
		persistentState.put("revision",datas.get("revision"));
		persistentState.put("taskdefkey",datas.get("taskDefKey"));
		persistentState.put("taskid",datas.get("taskId"));
		persistentState.put("taskstate",datas.get("taskState"));
		persistentState.put("tenantId",datas.get("tenantId"));
		persistentState.put("remark1", datas.get("remark1"));
		persistentState.put("remark2", datas.get("remark2"));
		persistentState.put("remark3", datas.get("remark3"));
		persistentState.put("remark4", datas.get("remark4"));
		persistentState.put("remark5", datas.get("remark5"));
		persistentState.put("remark6", datas.get("remark6"));
		persistentState.put("remark7", datas.get("remark7"));
		persistentState.put("remark8", datas.get("remark8"));
		persistentState.put("remark9", datas.get("remark9"));
		persistentState.put("remark10",datas.get("remark10"));
		return persistentState;
	}
	
	
	public static String getTaskTitle(Map<String, Object> datas, String userCName, 
			WfUniteTaskResult wfUniteTaskResult) {
		String appId = (String) datas.get("appId");
		appId = appId == null ? "default" : appId;
		String tenantId = (String) datas.get("tenantId");
		tenantId = tenantId == null ? "default" : tenantId;
		String engineName = (String) datas.get("engineName");
		engineName = engineName == null ? "default" : engineName;
		String taskTitle = "";
		try {
			String taskState =(String)datas.get("taskState");
			String taskType1 = "待办";
			if ("todo".equals(taskState)) {
				taskType1 = "待办";
			}
			if ("done".equals(taskState)) {
				taskType1 = "已办";
			}
			if ("suspend".equals(taskState)) {
				taskType1 = "挂起";
			}
			if (wfUniteTaskResult != null) {
				boolean onlyTitle = wfUniteTaskResult.isOnlyTitle();
				if (onlyTitle) {

					List<WfUniteColumn> titleColumns = wfUniteTaskResult
							.getColumns();

					List<String> taskDetail = new ArrayList<String>();
					taskTitle = "用户【" + userCName + "】的" + taskType1 + "任务："+datas.get("name");
					if (titleColumns != null) {
						for (WfUniteColumn wfuc : titleColumns) {
							int isTilte = wfuc.getIsTitle();
							if (isTilte == 1) {
								String columnId = wfuc.getColumnId();
								String columnName = wfuc.getColumnName();
								columnName = columnName == null ? wfuc
										.getColumnId() : columnName;
								taskDetail.add(columnName + ":"
										+ datas.get(columnId.toLowerCase()));
							}
						}
					}
					if (taskDetail.size() > 0) {
						taskTitle += "，任务详情：" + taskDetail + "";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return taskTitle;
	}
}
