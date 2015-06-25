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
import com.chinacreator.c2.flow.detail.WfUniteTaskResult;
import com.chinacreator.c2.flow.persistence.entity.WfUniteHisTaskExtendEntity;
import com.chinacreator.c2.flow.persistence.entity.WfUniteRunTaskEntity;

public class FindWfUniteHisTaskByConditionCmd implements Command<WfUniteTaskResult> {
	private Map<String, Object> parameters;
	private int firstResult = 0;
	private int maxResults = 10;

	public FindWfUniteHisTaskByConditionCmd(Map<String, Object> parameters,
			int firstResult, int maxResults) {
		this.parameters = parameters;
		this.firstResult = firstResult;
		this.maxResults = maxResults;
	}

	@SuppressWarnings("unchecked")
	@Override
	public WfUniteTaskResult execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		WfUniteTaskResult result = new WfUniteTaskResult();
		parameters.put("firstResult", firstResult);
		parameters.put("maxResults", maxResults);
		RowBounds rowBounds = new RowBounds(firstResult, maxResults);
		// yyc begine
		/*if(parameters.get("businessExtend")!=null){
			Map<String, Object> businessExtendParam = (Map<String, Object>)parameters.get("businessExtend");
			Set<String> keySet = businessExtendParam.keySet();
			if(!keySet.isEmpty()){
				List<Map<String, String>> businessExtendParams = new ArrayList<Map<String, String>>();
				for(String key:keySet){
					Map<String, String> paramMap = new HashMap<String, String>();
					paramMap.put("businessExtendParamKey", key);
					Object valueObj = businessExtendParam.get(key);
					if (valueObj instanceof Integer) {
						paramMap.put("businessExtendParamValue", valueObj + "");
					}else if(valueObj instanceof Long){
						paramMap.put("businessExtendParamValue", valueObj + "");
					}else if(valueObj instanceof Float){
						paramMap.put("businessExtendParamValue", valueObj + "");
					}else if(valueObj instanceof Double){
						paramMap.put("businessExtendParamValue", valueObj + "");
					}else if(valueObj instanceof String){
						paramMap.put("businessExtendParamValue", valueObj + "");
					}else if(valueObj instanceof Boolean){
						paramMap.put("businessExtendParamValue", valueObj + "");
					}else if(valueObj instanceof Short){
						paramMap.put("businessExtendParamValue", valueObj + "");
					}else if(valueObj instanceof Character){
						paramMap.put("businessExtendParamValue", valueObj + "");
					}else{
						//stringValue = JSON.toJSONString(o);
						//其他对象类型不查询
					}
					businessExtendParams.add(paramMap);
				}
				parameters.put("businessExtendParams", businessExtendParams);
			}
		}*/
		//yyc end
		List<?> list = dbSqlSession.getSqlSession().selectList("selectWfUniteHisTaskByC", parameters, rowBounds);
				//.selectListWithRawParameter("selectWfUniteRunTaskByC", parameters, firstResult, maxResults);
		result.setFirstResult(firstResult);
		result.setMaxResults(maxResults);
		if(list != null && list.size() > 0){

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
			
			List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
			for(Object obj : list){
				Map<String, Object> data = (Map<String, Object>) ((WfUniteRunTaskEntity) obj)
						.getPersistentState();
				data.put("taskTitle", ((WfUniteRunTaskEntity) obj)
						.getTaskTitle(data, userCName, wfUniteTaskResult));
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
			List<WfUniteHisTaskExtendEntity> extendDatas = commandContext.getProcessEngineConfiguration().getCommandExecutor()
					.execute(new FindWfUniteHisTaskExtByUniteIdsCmd(uniteTaskIds));

			if(extendDatas!=null && !extendDatas.isEmpty()){
				for(Map<String, Object> dataItem : datas){
					Map<String, Object> extendDataItem = new HashMap<String, Object>();
					for(WfUniteHisTaskExtendEntity entity : extendDatas){
						String uId = dataItem.get("id").toString();
						String uniteTaskId = entity.getUniteTaskHisId();
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
							// 将业务对象放到map中，key为WfBusinessData的businessExtend属性
							dataItem.put("businessExtend", extendDataItem);
						}
					}
				}
			}
			// yyc add end 查询冗余数据
						
			result.setDatas(datas);
			Object o = dbSqlSession.selectOne("selectWfUniteHisTaskCountByC", parameters);
			result.setTotalResults((Integer)o);
		}else{
			result.setTotalResults(0);
		}
		return result;
	}

}
