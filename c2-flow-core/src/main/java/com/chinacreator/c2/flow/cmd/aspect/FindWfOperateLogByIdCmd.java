package com.chinacreator.c2.flow.cmd.aspect;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfOperateLogEntity;

public class FindWfOperateLogByIdCmd implements Command<WfOperateLogEntity> {
	private String id;
	
	public FindWfOperateLogByIdCmd(String id){
		this.id = id;
	}
	
	@Override
	public WfOperateLogEntity execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		return (WfOperateLogEntity) dbSqlSession.selectById(
				WfOperateLogEntity.class, id);
	}

}
