package com.chinacreator.c2.flow.cmd.moduleconfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfModuleConfigEntity;

public class SelectWfModuleConfigByParamCmd implements
		Command<List<WfModuleConfigEntity>> {

	private Boolean isLatest;

	private String moduleId;

	private String id;

	private String procDefId;

	private String procDefKey;

	public SelectWfModuleConfigByParamCmd(String id, String moduleId,
			String procDefId, String procDefKey, Boolean isLatest) {
		this.id = id;
		this.moduleId = moduleId;
		this.procDefId = procDefId;
		this.procDefKey = procDefKey;
		this.isLatest = isLatest;
	}

	@Override
	public List<WfModuleConfigEntity> execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		Map<String, Object> parameter = new HashMap<String, Object>();
		
		parameter.put("id", id);
		parameter.put("moduleId", moduleId);
		parameter.put("procDefId", procDefId);
		parameter.put("procDefKey", procDefKey);
		parameter.put("isLatest", isLatest);
		
		@SuppressWarnings("unchecked")
		List<WfModuleConfigEntity> result = dbSqlSession.selectListWithRawParameter("selectWfModuleConfigByParam", parameter, 0, Integer.MAX_VALUE);
		return result;
	}

}
