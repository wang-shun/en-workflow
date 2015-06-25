package com.chinacreator.c2.flow.cmd.unitetask;

import java.util.List;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfUniteRunTaskEntity;

public class FindWfUniteRunTaskByTaskIdCmd implements Command<List<WfUniteRunTaskEntity>> {
	private String taskId;

	public FindWfUniteRunTaskByTaskIdCmd(String taskId) {
		this.taskId = taskId;
	}

	@Override
	public List<WfUniteRunTaskEntity> execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		return dbSqlSession.getSqlSession().selectList("selectWfUniteRunTask", taskId);
		//.selectById(WfUniteRunTaskEntity.class, taskId);
	}

}
