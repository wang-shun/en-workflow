package com.chinacreator.c2.flow.cmd.workdate;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.detail.WfWorkDate;

public class UpdateWorkDateCmd implements Command<Void> {
	
	private WfWorkDate wfWorkDate;
	
	public UpdateWorkDateCmd(WfWorkDate wfWorkDate){
		this.wfWorkDate = wfWorkDate;
	}

	@Override
	public Void execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		dbSqlSession.update("updateWorkDateByParam", wfWorkDate);
		return null;
	}

}
