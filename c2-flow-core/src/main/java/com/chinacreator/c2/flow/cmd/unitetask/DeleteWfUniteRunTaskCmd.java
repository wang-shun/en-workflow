package com.chinacreator.c2.flow.cmd.unitetask;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

public class DeleteWfUniteRunTaskCmd implements Command<Void> {
	private String taskId;

	public DeleteWfUniteRunTaskCmd(String taskId) {
		this.taskId = taskId;
	}

	@Override
	public Void execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
//		WfUniteRunTaskEntity WfUniteTask = dbSqlSession.selectById(
//				WfUniteRunTaskEntity.class, taskId);
		dbSqlSession.getSqlSession().delete("deleteWfUniteRunTaskByTaskId", taskId);
		return null;
	}

}
