package com.chinacreator.c2.flow.cmd.unitetask;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfUniteHisTaskEntity;

public class InsertWfUniteHisTaskCmd implements Command<Void> {
	private WfUniteHisTaskEntity WfUniteTask;

	public InsertWfUniteHisTaskCmd(WfUniteHisTaskEntity WfUniteTask) {
		this.WfUniteTask = WfUniteTask;
	}

	@Override
	public Void execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		dbSqlSession.insert(WfUniteTask);
		return null;
	}

}
