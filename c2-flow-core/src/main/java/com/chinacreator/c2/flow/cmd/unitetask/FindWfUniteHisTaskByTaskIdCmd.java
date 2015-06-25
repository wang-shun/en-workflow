package com.chinacreator.c2.flow.cmd.unitetask;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfUniteHisTaskEntity;

public class FindWfUniteHisTaskByTaskIdCmd implements Command<WfUniteHisTaskEntity> {
	private String taskId;

	public FindWfUniteHisTaskByTaskIdCmd(String taskId) {
		this.taskId = taskId;
	}

	@Override
	public WfUniteHisTaskEntity execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		return (WfUniteHisTaskEntity) dbSqlSession.selectById(
				WfUniteHisTaskEntity.class, taskId);
	}

}
