package com.chinacreator.c2.flow.cmd.unitetask;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfUniteRunTaskEntity;

public class DeleteWfUniteRunTaskByIdCmd implements Command<Void> {
	private String unitTaskId;

	public DeleteWfUniteRunTaskByIdCmd(String unitTaskId) {
		this.unitTaskId = unitTaskId;
	}

	@Override
	public Void execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
//		WfUniteRunTaskEntity WfUniteTask = dbSqlSession.selectById(
//				WfUniteRunTaskEntity.class, taskId);
		WfUniteRunTaskEntity u=new WfUniteRunTaskEntity();
		u.setId(unitTaskId);
		dbSqlSession.delete(u);
		return null;
	}

}
