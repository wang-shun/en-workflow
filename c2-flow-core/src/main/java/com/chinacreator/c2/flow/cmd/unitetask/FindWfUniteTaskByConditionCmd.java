package com.chinacreator.c2.flow.cmd.unitetask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.identity.User;
import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;

import com.alibaba.fastjson.JSON;
import com.chinacreator.c2.flow.cmd.unitetask.config.FindWfUniteConfigColumnsCmd;
import com.chinacreator.c2.flow.detail.WfUniteTaskResult;
import com.chinacreator.c2.flow.persistence.entity.WfUniteRunTaskExtendEntity;
import com.chinacreator.c2.flow.util.WfUtils;
import com.chinacreator.c2.flow.util.WfUtils.OrderDirection;

public class FindWfUniteTaskByConditionCmd implements
		Command<WfUniteTaskResult> {
	private Map<String, Object> parameters;
	private int firstResult = 0;
	private int maxResults = 10;
	private Map<String,OrderDirection> orderBys;

	public FindWfUniteTaskByConditionCmd(Map<String, Object> parameters,
			int firstResult, int maxResults) {
		this.parameters = parameters;
		this.firstResult = firstResult;
		this.maxResults = maxResults;
	}
	
	
	public FindWfUniteTaskByConditionCmd(Map<String, Object> parameters,
			int firstResult, int maxResults,Map<String,OrderDirection> orderBys) {
		this.parameters = parameters;
		this.firstResult = firstResult;
		this.maxResults = maxResults;
		this.orderBys=orderBys;
	}


	
	@SuppressWarnings("unchecked")
	@Override
	public WfUniteTaskResult execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		WfUniteTaskResult result = new WfUniteTaskResult();
		parameters.put("firstResult", firstResult);
		parameters.put("maxResults", maxResults);
		RowBounds rowBounds = new RowBounds(firstResult, maxResults);
		
		//组装排序sql
		List<String> orderByList=new ArrayList<String>();
		if(null!=orderBys&&orderBys.size()>0){
			for (Map.Entry<String,OrderDirection> entry : orderBys.entrySet()) {
				String key=entry.getKey();
				OrderDirection value=entry.getValue();
				if(StringUtils.isNotEmpty(key)&&null!=value){
					orderByList.add("b."+key+" "+value.name());
				}
			}
		}
		
		if(orderByList.size()>0){
			parameters.put("orderByStr",StringUtils.join(orderByList,","));
		}
		
		List<?> list = dbSqlSession.getSqlSession().selectList("selectWfUniteRunTaskByC",parameters,rowBounds);
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
				Map<String, Object> data = WfUtils.getPersistentState((Map<String, Object>) obj);
				data.put("taskTitle", WfUtils.getTaskTitle(data, userCName, wfUniteTaskResult));
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
