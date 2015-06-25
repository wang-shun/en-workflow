package com.chinacreator.c2.flow.cmd.unitetask;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfUniteHisTaskEntity;

public class DeleteWfUniteHisTaskCmd implements Command<Void> {
	private String taskId;

	public DeleteWfUniteHisTaskCmd(String taskId) {
		this.taskId = taskId;
	}

	@Override
	public Void execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		WfUniteHisTaskEntity WfUniteTask = dbSqlSession.selectById(
				WfUniteHisTaskEntity.class, taskId);
		dbSqlSession.delete(WfUniteTask);
		return null;
	}

}
