package com.chinacreator.c2.flow.cmd.unitetask;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

public class DeleteWfUniteRunTaskExtCmd implements Command<Void> {
	private String uniteTaskId;

	public DeleteWfUniteRunTaskExtCmd(String uniteTaskId) {
		this.uniteTaskId = uniteTaskId;
	}

	@Override
	public Void execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		dbSqlSession.getSqlSession().delete("deleteWfUniteRunTaskExtendByUniteTaskId",
				uniteTaskId);
		return null;
	}

}
