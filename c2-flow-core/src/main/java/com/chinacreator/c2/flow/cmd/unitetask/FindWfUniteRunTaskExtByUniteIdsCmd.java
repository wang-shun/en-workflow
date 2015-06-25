package com.chinacreator.c2.flow.cmd.unitetask;

import java.util.List;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfUniteRunTaskExtendEntity;

public class FindWfUniteRunTaskExtByUniteIdsCmd implements
		Command<List<WfUniteRunTaskExtendEntity>> {

	private List<String> uniteTaskIds;

	public FindWfUniteRunTaskExtByUniteIdsCmd(List<String> uniteTaskIds){
		this.uniteTaskIds = uniteTaskIds;
	}

	@Override
	public List<WfUniteRunTaskExtendEntity> execute(
			CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		return dbSqlSession.getSqlSession().selectList(
				"selectWfUniteRunTaskExtendByUniteIds", uniteTaskIds);
	}

}
