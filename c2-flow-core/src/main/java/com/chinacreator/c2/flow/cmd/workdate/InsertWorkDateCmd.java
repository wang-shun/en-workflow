package com.chinacreator.c2.flow.cmd.workdate;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfWorkDateEntity;

public class InsertWorkDateCmd implements Command<Void> {
	
	private WfWorkDateEntity wfWorkDate;
	
	public InsertWorkDateCmd(WfWorkDateEntity wfWorkDate){
		this.wfWorkDate = wfWorkDate;
	}
	
	@Override
	public Void execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		dbSqlSession.insert(wfWorkDate);
		return null;
	}

}
