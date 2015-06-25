package com.chinacreator.c2.flow.cmd.unitetask;

import java.util.List;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfUniteHisTaskExtendEntity;

public class FindWfUniteHisTaskExtByUniteIdsCmd implements
		Command<List<WfUniteHisTaskExtendEntity>> {

	private List<String> uniteTaskIds;

	public FindWfUniteHisTaskExtByUniteIdsCmd(List<String> uniteTaskIds){
		this.uniteTaskIds = uniteTaskIds;
	}
	
	@Override
	public List<WfUniteHisTaskExtendEntity> execute(
			CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		return dbSqlSession.getSqlSession().selectList(
				"selectWfUniteHisTaskExtendByUniteIds", uniteTaskIds);
	}

}
