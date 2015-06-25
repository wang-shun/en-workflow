package com.chinacreator.c2.flow.cmd.moduleconfig;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfModuleConfigEntity;

public class UpdateIsLatestByModuleIdCmd implements Command<Void> {
	private String moduleId;
	private Boolean isLatest;
	private String procDefId;
	
	public UpdateIsLatestByModuleIdCmd(String moduleId, String procDefId, Boolean isLatest){
		this.moduleId = moduleId;
		this.procDefId = procDefId;
		this.isLatest = isLatest;
	}

	@Override
	public Void execute(CommandContext commandContext) {
		WfModuleConfigEntity object = new WfModuleConfigEntity();
		object.setModuleId(moduleId);
		object.setIsLatest(isLatest);
		object.setProcDefId(procDefId);
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		dbSqlSession.update("updateIsLatestByModuleId", object);
		return null;
	}

}
