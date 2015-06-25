package com.chinacreator.c2.flow.cmd.unitetask;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfUniteHisTaskExtendEntity;

public class InsertWfUniteHisTaskExtCmd implements Command<WfUniteHisTaskExtendEntity>{

	private WfUniteHisTaskExtendEntity wfUniteHisTaskExtend;
	
	public InsertWfUniteHisTaskExtCmd(WfUniteHisTaskExtendEntity wfUniteHisTaskExtend){
		this.wfUniteHisTaskExtend = wfUniteHisTaskExtend;
	}
	@Override
	public WfUniteHisTaskExtendEntity execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		dbSqlSession.insert(wfUniteHisTaskExtend);
		return wfUniteHisTaskExtend;
	}

}
