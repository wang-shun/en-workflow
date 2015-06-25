package com.chinacreator.c2.flow.cmd.unitetask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfUniteRunTaskExtendEntity;

public class FindWfUniteRunTaskExtByIdCmd implements
		Command<List<WfUniteRunTaskExtendEntity>> {
	private String uniteTaskId;

	public FindWfUniteRunTaskExtByIdCmd(String uniteTaskId) {
		this.uniteTaskId = uniteTaskId;
	}

	@Override
	public List<WfUniteRunTaskExtendEntity> execute(
			CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("uniteTaskId", uniteTaskId);
		return dbSqlSession.getSqlSession().selectList("selectWfUniteRunTaskExtend",parameters);
	}

}
