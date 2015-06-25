package com.chinacreator.c2.flow.cmd.delegate;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfModuleDelegateEntity;

public class InsertWfModuleDelegateCmd implements Command<Void> {
	
	private WfModuleDelegateEntity wfModuleDelegate;
	
	public InsertWfModuleDelegateCmd(WfModuleDelegateEntity wfModuleDelegate){
		this.wfModuleDelegate = wfModuleDelegate;
	}

	@Override
	public Void execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		dbSqlSession.insert(wfModuleDelegate);
		return null;
	}

}
