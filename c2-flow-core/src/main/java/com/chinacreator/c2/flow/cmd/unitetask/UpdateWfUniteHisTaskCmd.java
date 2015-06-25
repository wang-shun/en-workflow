package com.chinacreator.c2.flow.cmd.unitetask;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfUniteHisTaskEntity;

public class UpdateWfUniteHisTaskCmd implements Command<Void> {
	private WfUniteHisTaskEntity WfUniteTask;

	public UpdateWfUniteHisTaskCmd(WfUniteHisTaskEntity WfUniteTask) {
		this.WfUniteTask = WfUniteTask;
	}

	@Override
	public Void execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		dbSqlSession.update(WfUniteTask);
		return null;
	}

}
