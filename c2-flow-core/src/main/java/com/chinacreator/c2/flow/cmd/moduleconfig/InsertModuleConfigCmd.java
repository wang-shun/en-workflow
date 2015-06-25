package com.chinacreator.c2.flow.cmd.moduleconfig;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfModuleConfigEntity;

public class InsertModuleConfigCmd implements Command<Void> {
	private WfModuleConfigEntity procModuleConfig;

	public InsertModuleConfigCmd(WfModuleConfigEntity procModuleConfig){
		this.procModuleConfig = procModuleConfig;
	}
	
	@Override
	public Void execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		dbSqlSession.insert(procModuleConfig);
		return null;
	}
}
