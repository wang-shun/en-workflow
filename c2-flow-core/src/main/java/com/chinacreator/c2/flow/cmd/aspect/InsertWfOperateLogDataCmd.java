package com.chinacreator.c2.flow.cmd.aspect;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfOperateLogDataEntity;

public class InsertWfOperateLogDataCmd implements Command<Void> {
	private WfOperateLogDataEntity data;

	public InsertWfOperateLogDataCmd(WfOperateLogDataEntity data) {
		this.data = data;
	}

	@Override
	public Void execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		dbSqlSession.insert(data);
		return null;
	}
}
