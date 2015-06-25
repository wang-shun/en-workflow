package com.chinacreator.c2.flow.cmd.delegate;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

public class DeleteModuleDelegateInfoByDelegateIdCmd implements
Command<Void>{

	private String delegateId;
	
	public DeleteModuleDelegateInfoByDelegateIdCmd(String delegateId){
		this.delegateId = delegateId;
	}
	
	@Override
	public Void execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("delegateId", delegateId);
		dbSqlSession.delete("deleteModuleDelegateInfoByDelegateId", parameter);
		return null;
	}

}
