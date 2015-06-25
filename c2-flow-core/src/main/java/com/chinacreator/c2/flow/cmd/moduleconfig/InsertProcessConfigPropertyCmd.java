package com.chinacreator.c2.flow.cmd.moduleconfig;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfProcessConfigPropertyEntity;

public class InsertProcessConfigPropertyCmd  implements Command<Void>{
	
	private WfProcessConfigPropertyEntity wfProcessConfigPropertyEntity;
	
	public InsertProcessConfigPropertyCmd(WfProcessConfigPropertyEntity wfProcessConfigPropertyEntity){
		this.wfProcessConfigPropertyEntity=wfProcessConfigPropertyEntity;
	}

	@Override
	public Void execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		dbSqlSession.insert(wfProcessConfigPropertyEntity);
		return null;
	}
	
}
