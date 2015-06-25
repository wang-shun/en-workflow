package com.chinacreator.c2.flow.cmd.unitetask.config;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfUniteConfigEntity;

public class FindWfUniteConfigCmd implements Command<List<WfUniteConfigEntity>> {
	private String appId;
	private String tenantId;
	private String engineName;
	private String taskList;

	public FindWfUniteConfigCmd(String appId, String tenantId,
			String engineName, String taskList) {
		this.appId = appId;
		this.tenantId = tenantId;
		this.engineName = engineName;
		this.taskList = taskList;
	}
	
	@Override
	public List<WfUniteConfigEntity> execute(CommandContext commandContext) {
		List<WfUniteConfigEntity> result = new ArrayList<WfUniteConfigEntity>();
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		WfUniteConfigEntity parameter = new WfUniteConfigEntity();
		parameter.setAppId(appId);
		parameter.setTenantId(tenantId);
		parameter.setEngineName(engineName);
		parameter.setTaskList(taskList);
		List<?> list = dbSqlSession.selectListWithRawParameter(
				"selectWfUniteConfigByC", parameter, 0, Integer.MAX_VALUE);
		if(list != null){
			for(Object obj : list){
				result.add((WfUniteConfigEntity) obj);
			}
		}
		return result;
	}

}
