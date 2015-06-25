package com.chinacreator.c2.flow.cmd.delegate;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfModuleDelegateInfoEntity;

public class InsertWfModuleDelegateInfoCmd implements Command<Void>{

	private WfModuleDelegateInfoEntity wfModuleDelegateInfo;
	
	public InsertWfModuleDelegateInfoCmd(WfModuleDelegateInfoEntity wfModuleDelegateInfo){
		this.wfModuleDelegateInfo = wfModuleDelegateInfo;
	}

	@Override
	public Void execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		dbSqlSession.insert(wfModuleDelegateInfo);
		return null;
	}
	
	
}
