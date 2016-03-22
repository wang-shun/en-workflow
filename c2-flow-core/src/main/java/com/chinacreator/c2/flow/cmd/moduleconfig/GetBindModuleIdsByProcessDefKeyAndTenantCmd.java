package com.chinacreator.c2.flow.cmd.moduleconfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

public class GetBindModuleIdsByProcessDefKeyAndTenantCmd implements
		Command<List<String>> {
	private String processDefinitionKey;
	
	private String tenantId;

	public GetBindModuleIdsByProcessDefKeyAndTenantCmd(String processDefinitionKey,String tenantId) {
		this.processDefinitionKey = processDefinitionKey;
		this.tenantId=tenantId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("processDefinitionKey",this.processDefinitionKey);
		params.put("tenantId",this.tenantId);
		return dbSqlSession.selectListWithRawParameter("getBindModuleIdsByProcessDefKeyAndTenant",params,0, Integer.MAX_VALUE);

	}

}
