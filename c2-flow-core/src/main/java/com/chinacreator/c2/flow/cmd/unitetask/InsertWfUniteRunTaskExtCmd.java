package com.chinacreator.c2.flow.cmd.unitetask;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfUniteRunTaskExtendEntity;

public class InsertWfUniteRunTaskExtCmd implements Command<WfUniteRunTaskExtendEntity>{

	private WfUniteRunTaskExtendEntity WfUniteTaskExtend;
	
	public InsertWfUniteRunTaskExtCmd(WfUniteRunTaskExtendEntity WfUniteTaskExtend) {
		this.WfUniteTaskExtend = WfUniteTaskExtend;
	}
	
	@Override
	public WfUniteRunTaskExtendEntity execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		dbSqlSession.insert(WfUniteTaskExtend);
		return WfUniteTaskExtend;
	}

}
