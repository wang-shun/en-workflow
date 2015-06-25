package com.chinacreator.c2.flow.cmd.moduleconfig;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfModuleConfigEntity;

public class GetProcessInfoByModuleIdCmd implements Command<WfModuleConfigEntity> {
	private String moduleId;
	
	public GetProcessInfoByModuleIdCmd(String moduleId){
		this.moduleId = moduleId;
	}

	@Override
	public WfModuleConfigEntity execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		return (WfModuleConfigEntity) dbSqlSession.selectById(
				WfModuleConfigEntity.class, moduleId);
	}

}
