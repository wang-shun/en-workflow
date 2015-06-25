package com.chinacreator.c2.flow.cmd.unitetask;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfUniteRunTaskEntity;

public class UpdateWfUniteRunTaskCmd implements Command<Void> {
	private WfUniteRunTaskEntity WfUniteTask;

	public UpdateWfUniteRunTaskCmd(WfUniteRunTaskEntity WfUniteTask) {
		this.WfUniteTask = WfUniteTask;
	}

	@Override
	public Void execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		dbSqlSession.update(WfUniteTask);
		return null;
	}

}
