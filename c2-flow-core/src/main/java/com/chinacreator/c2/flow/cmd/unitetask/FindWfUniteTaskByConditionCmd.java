package com.chinacreator.c2.flow.cmd.unitetask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.identity.User;
import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.apache.ibatis.session.RowBounds;

import com.alibaba.fastjson.JSON;
import com.chinacreator.c2.flow.cmd.unitetask.config.FindWfUniteConfigColumnsCmd;
import com.chinacreator.c2.flow.detail.WfUniteColumn;
import com.chinacreator.c2.flow.detail.WfUniteTaskResult;
import com.chinacreator.c2.flow.persistence.entity.WfUniteRunTaskExtendEntity;

public class FindWfUniteTaskByConditionCmd implements
		Command<WfUniteTaskResult> {
	private Map<String, Object> parameters;
	private int firstResult = 0;
	private int maxResults = 10;

	public FindWfUniteTaskByConditionCmd(Map<String, Object> parameters,
			int firstResult, int maxResults) {
		this.parameters = parameters;
		this.firstResult = firstResult;
		this.maxResults = maxResults;
	}
	

	public Map<String,Object> getPersistentState(Map<String,Object> data) {
		Map<String, Object> persistentState = new HashMap<String, Object>();
		persistentState.put("id",data.get("id"));
		persistentState.put("appid", data.get("appId"));
		persistentState.put("assignee",data.get("assignee"));
		persistentState.put("businesskey",data.get("businessKey"));
		persistentState.put("candidate", data.get("candidate"));
		persistentState.put("category",data.get("category"));
		persistentState.put("createtime",data.get("createTime"));
		persistentState.put("endtime",data.get("endTime"));
		persistentState.put("delegation",data.get("delegation"));
		persistentState.put("deletereason",data.get("deleteReason"));
		persistentState.put("description",data.get("description"));
		persistentState.put("duedate",data.get("dueDate"));
		persistentState.put("executionid",data.get("executionId"));
		persistentState.put("formkey",data.get("formKey"));
		persistentState.put("moduleid",data.get("moduleId"));
		persistentState.put("modulename",data.get("moduleName"));
		persistentState.put("name",data.get("name"));
		persistentState.put("owner",data.get("owner"));
		persistentState.put("parenttaskid",data.get("parentTaskId"));
		persistentState.put("priority",data.get("priority"));
		persistentState.put("procdefid",data.get("procDefId"));
		persistentState.put("procinstid",data.get("procInstId"));
		persistentState.put("revision",data.get("revision"));
		persistentState.put("taskdefkey",data.get("taskDefKey"));
		persistentState.put("taskid",data.get("taskId"));
		persistentState.put("taskstate",data.get("taskState"));
		persistentState.put("tenantId",data.get("tenantId"));
		persistentState.put("remark1", data.get("remark1"));
		persistentState.put("remark2", data.get("remark2"));
		persistentState.put("remark3", data.get("remark3"));
		persistentState.put("remark4", data.get("remark4"));
		persistentState.put("remark5", data.get("remark5"));
		persistentState.put("remark6", data.get("remark6"));
		persistentState.put("remark7", data.get("remark7"));
		persistentState.put("remark8", data.get("remark8"));
		persistentState.put("remark9", data.get("remark9"));
		persistentState.put("remark10",data.get("remark10"));
		return persistentState;
	}
	
	
	private String getTaskTitle(Map<String, Object> parameters, String userCName, 
			WfUniteTaskResult wfUniteTaskResult) {
		String appId = (String) parameters.get("appId");
		appId = appId == null ? "default" : appId;
		String tenantId = (String) parameters.get("tenantId");
		tenantId = tenantId == null ? "default" : tenantId;
		String engineName = (String) parameters.get("engineName");
		engineName = engineName == null ? "default" : engineName;
		String taskTitle = "";
		try {
			String taskState =(String)parameters.get("taskState");
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
					taskTitle = "用户【" + userCName + "】的" + taskType1 + "任务："+parameters.get("name");
					if (titleColumns != null) {
						for (WfUniteColumn wfuc : titleColumns) {
							int isTilte = wfuc.getIsTitle();
							if (isTilte == 1) {
								String columnId = wfuc.getColumnId();
								String columnName = wfuc.getColumnName();
								columnName = columnName == null ? wfuc
										.getColumnId() : columnName;
								taskDetail.add(columnName + ":"
										+ parameters.get(columnId.toLowerCase()));
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
	
	@SuppressWarnings("unchecked")
	@Override
	public WfUniteTaskResult execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		WfUniteTaskResult result = new WfUniteTaskResult();
		parameters.put("firstResult", firstResult);
		parameters.put("maxResults", maxResults);
		RowBounds rowBounds = new RowBounds(firstResult, maxResults);
		List<?> list = dbSqlSession.getSqlSession().selectList(
				"selectWfUniteRunTaskByC", parameters, rowBounds);
		result.setFirstResult(firstResult);
		result.setMaxResults(maxResults);
		if (list != null && list.size() > 0) {
			List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
			String appId = (String) parameters.get("appId");
			appId = appId == null ? "default" : appId;
			String tenantId = (String) parameters.get("tenantId");
			tenantId = tenantId == null ? "default" : tenantId;
			String engineName = (String) parameters.get("engineName");
			engineName = engineName == null ? "default" : engineName;
			String listType = (String) parameters.get("taskType");
			String userId = (String) parameters.get("userId");

			User user = commandContext.getUserIdentityManager().findUserById(
					userId);
			String userCName = userId;
			if (user != null) {
				userCName = user.getLastName();
			}
			FindWfUniteConfigColumnsCmd findWfUniteConfigColumnsCmd = new FindWfUniteConfigColumnsCmd(
					appId, tenantId, engineName, listType);
			WfUniteTaskResult wfUniteTaskResult = commandContext
					.getProcessEngineConfiguration().getCommandExecutor()
					.execute(findWfUniteConfigColumnsCmd);
			
			for (Object obj : list) {
				Map<String, Object> data = this.getPersistentState((Map<String, Object>) obj);
				data.put("taskTitle", this.getTaskTitle(data, userCName, wfUniteTaskResult));
				datas.add(data);
			}
			
			// yyc add begine 查询冗余数据
			List<String> uniteTaskIds = new ArrayList<String>();
			if(!datas.isEmpty()){
				for(Map<String, Object> dataItem : datas){
					if(dataItem!=null){
						uniteTaskIds.add(dataItem.get("id").toString());
					}
				}
			}
			List<WfUniteRunTaskExtendEntity> extendDatas = commandContext.getProcessEngineConfiguration().getCommandExecutor()
					.execute(new FindWfUniteRunTaskExtByUniteIdsCmd(uniteTaskIds));

			if(extendDatas!=null && !extendDatas.isEmpty()){
				for(Map<String, Object> dataItem : datas){
					Map<String, Object> extendDataItem = new HashMap<String, Object>();
					for(WfUniteRunTaskExtendEntity entity : extendDatas){
						String uId = dataItem.get("id").toString();
						String uniteTaskId = entity.getUniteTaskId();
						if(uId.equals(uniteTaskId)){
							String key = entity.getExtFieldKey();
							String type = entity.getExtFieldType();
							String value = entity.getExtFieldValue();
							
							if("int".equals(type)||Integer.class.getName().equals(type)){
								extendDataItem.put(key, Integer.parseInt(value));
							}else if("short".equals(type)||Short.class.getName().equals(type)){
								extendDataItem.put(key, Short.parseShort(value));
							}else if("char".equals(type)||Character.class.getName().equals(type)){
								//此处只接受一个字符
								char[] chars=value.toCharArray();
								extendDataItem.put(key, chars[0]);
							}else if("long".equals(type)|| Long.class.getName().equals(type)){
								extendDataItem.put(key, Long.parseLong(value));
							}else if("float".equals(type)||Float.class.getName().equals(type)){
								extendDataItem.put(key, Float.parseFloat(value));
							}else if("double".equals(type)||Double.class.getName().equals(type)){
								extendDataItem.put(key, Double.parseDouble(value));
							}else if(String.class.getName().equals(type)){
								extendDataItem.put(key, value);
							}else if("boolean".equals(type)||Boolean.class.getName().equals(type)){
								extendDataItem.put(key, Boolean.parseBoolean(value));
							}else{
								try {
									extendDataItem.put(key, JSON.parseObject(value, Class.forName(type)));
								} catch (ClassNotFoundException e) {
									e.printStackTrace();
								}
							}
							dataItem.put("businessExtend", extendDataItem);
						}
					}
				}
			}
			// yyc add end 查询冗余数据
			
			result.setDatas(datas);
			Object o = dbSqlSession.selectOne("selectWfUniteRunTaskCountByC",
					parameters);
			result.setTotalResults((Integer) o);
		} else {
			result.setTotalResults(0);
		}
		return result;
	}

}
