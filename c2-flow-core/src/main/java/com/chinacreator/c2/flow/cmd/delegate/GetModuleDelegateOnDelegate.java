package com.chinacreator.c2.flow.cmd.delegate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfModuleDelegateFullAttrEntity;

public class GetModuleDelegateOnDelegate implements Command<List<WfModuleDelegateFullAttrEntity>>{
	
	private String moduleId;
	
	private String designatorId;
	
	private String designeeId;
	
	private String delegateRange;
	
	private String delegateStat;
	
	private Date currentTime;

	public GetModuleDelegateOnDelegate(String moduleId, String designatorId, String designeeId, String delegateRange, String delegateStat, Date currentTime){
		this.moduleId = moduleId;
		this.designatorId = designatorId;
		this.designeeId = designeeId;
		this.delegateRange = delegateRange;
		this.delegateStat = delegateStat;
		this.currentTime = currentTime;
	}
	
	@Override
	public List<WfModuleDelegateFullAttrEntity> execute(
			CommandContext commandContext) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("moduleId", moduleId);
		parameter.put("designatorId", designatorId);
		parameter.put("designeeId", designeeId);
		parameter.put("delegateRange", delegateRange);
		parameter.put("delegateStat", delegateStat);
		parameter.put("currentTime", currentTime);
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		List<WfModuleDelegateFullAttrEntity> list = dbSqlSession.getSqlSession().selectList("getModuleDelegateOnDelegate", parameter);
		return list;
		
	}

}
