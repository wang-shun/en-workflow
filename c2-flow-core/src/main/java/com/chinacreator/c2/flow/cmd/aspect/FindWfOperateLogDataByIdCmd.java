package com.chinacreator.c2.flow.cmd.aspect;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfOperateLogDataEntity;

public class FindWfOperateLogDataByIdCmd implements
		Command<WfOperateLogDataEntity> {
	private String logId;

	public FindWfOperateLogDataByIdCmd(String logId) {
		this.logId = logId;
	}

	@Override
	public WfOperateLogDataEntity execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		return (WfOperateLogDataEntity) dbSqlSession.selectById(
				WfOperateLogDataEntity.class, logId);
	}

}
