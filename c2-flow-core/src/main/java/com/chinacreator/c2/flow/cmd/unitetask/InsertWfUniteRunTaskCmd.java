package com.chinacreator.c2.flow.cmd.unitetask;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfUniteRunTaskEntity;

public class InsertWfUniteRunTaskCmd implements Command<WfUniteRunTaskEntity> {
	private WfUniteRunTaskEntity WfUniteTask;

	public InsertWfUniteRunTaskCmd(WfUniteRunTaskEntity WfUniteTask) {
		this.WfUniteTask = WfUniteTask;
	}

	@Override
	public WfUniteRunTaskEntity execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		dbSqlSession.getSqlSession().insert("insertWfUniteRunTask", WfUniteTask);
		return WfUniteTask;
	}

}
