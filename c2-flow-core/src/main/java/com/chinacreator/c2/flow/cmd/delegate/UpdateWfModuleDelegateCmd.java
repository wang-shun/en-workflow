package com.chinacreator.c2.flow.cmd.delegate;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfModuleDelegateEntity;

public class UpdateWfModuleDelegateCmd implements Command<Void>{

	private WfModuleDelegateEntity wfModuleDelegateEntity;
	
	public UpdateWfModuleDelegateCmd(WfModuleDelegateEntity wfModuleDelegateEntity){
		this.wfModuleDelegateEntity = wfModuleDelegateEntity;
	}

	@Override
	public Void execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		dbSqlSession.update("updateWfModuleDelegate", wfModuleDelegateEntity);
		return null;
	}
	
	
}
