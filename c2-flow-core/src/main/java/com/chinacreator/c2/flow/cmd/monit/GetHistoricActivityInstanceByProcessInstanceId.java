package com.chinacreator.c2.flow.cmd.monit;

import java.util.List;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

public class GetHistoricActivityInstanceByProcessInstanceId implements
		Command<List<HistoricActivityInstance>> {
	private String processInstanceId;
	
	public GetHistoricActivityInstanceByProcessInstanceId(String processInstanceId){
		this.processInstanceId = processInstanceId;
	}

	@Override
	public List<HistoricActivityInstance> execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		List<HistoricActivityInstance> list = dbSqlSession.getSqlSession().selectList("selectHistoricActivityInstanceByProcessInstanceId", processInstanceId);
		return list;
	}

}
