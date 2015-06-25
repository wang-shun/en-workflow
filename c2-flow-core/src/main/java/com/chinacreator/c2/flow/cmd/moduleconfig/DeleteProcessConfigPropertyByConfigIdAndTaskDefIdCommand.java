package com.chinacreator.c2.flow.cmd.moduleconfig;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfUniteHisTaskEntity;

public class DeleteProcessConfigPropertyByConfigIdAndTaskDefIdCommand implements
		Command<Void> {
	
	private String configId;
	
	private String taskDefinitionId;
	
	public DeleteProcessConfigPropertyByConfigIdAndTaskDefIdCommand(String configId, String taskDefinitionId){
		this.configId = configId;
		this.taskDefinitionId=taskDefinitionId;
	}

	@Override
	public Void execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("configId", configId);
		parameter.put("taskDefinitionId", taskDefinitionId);
		dbSqlSession.delete("deleteProcessConfigPropertyByConfigIdAndTaskDefId", parameter);
		return null;
		
	}

}
