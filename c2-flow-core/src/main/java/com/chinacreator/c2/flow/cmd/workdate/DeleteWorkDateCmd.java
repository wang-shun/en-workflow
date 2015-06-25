package com.chinacreator.c2.flow.cmd.workdate;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

public class DeleteWorkDateCmd implements Command<Void>{

	private String tenantId;
	
	private String workId;
	
	public DeleteWorkDateCmd(String workId, String tenantId){
		this.tenantId = tenantId;
		this.workId = workId;
	}
	
	@Override
	public Void execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("tenantId", tenantId);
		parameter.put("workId", workId);
		dbSqlSession.delete("deleteWorkDate", parameter);
		return null;
	}

}
