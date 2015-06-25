package com.chinacreator.c2.flow.cmd.moduleconfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.detail.WfProcessConfigProperty;

public class FindProcessConfigProperty implements
Command<List<WfProcessConfigProperty>>{
	
	private String processDefinitionId;
	
	private String moduleId;
	
	private String taskDefKey;
	
	public FindProcessConfigProperty(String processDefinitionId, String moduleId,String taskDefKey){
		this.processDefinitionId=processDefinitionId;
		this.moduleId = moduleId;
		this.taskDefKey=taskDefKey;
	}
	

	@Override
	public List<WfProcessConfigProperty> execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		Map<String, Object> parameter = new HashMap<String, Object>();
		
		parameter.put("processDefinitionId", processDefinitionId);
		parameter.put("moduleId", moduleId);
		parameter.put("taskDefKey", taskDefKey);
		
		@SuppressWarnings("unchecked")
		List<WfProcessConfigProperty> result = dbSqlSession.selectListWithRawParameter("findProcessConfigProperty", parameter, 0, Integer.MAX_VALUE);
		return result;
		
	}

}
