package com.chinacreator.c2.flow.cmd.moduleconfig;

import java.util.List;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

public class GetBindModuleIdsByProcessDefKeyCmd implements
		Command<List<String>> {
	private String processDefinitionKey;

	public GetBindModuleIdsByProcessDefKeyCmd(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		return dbSqlSession.selectList("getBindModuleIdsByProcessDefKey",
				processDefinitionKey);
	}

}
