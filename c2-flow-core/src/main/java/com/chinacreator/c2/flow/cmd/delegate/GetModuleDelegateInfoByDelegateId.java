package com.chinacreator.c2.flow.cmd.delegate;

import java.util.List;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfModuleDelegateInfoEntity;

public class GetModuleDelegateInfoByDelegateId implements Command<List<WfModuleDelegateInfoEntity>> {

	private String delegateId;
	
	public GetModuleDelegateInfoByDelegateId(String delegateId){
		this.delegateId = delegateId;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<WfModuleDelegateInfoEntity> execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		List<WfModuleDelegateInfoEntity> list = dbSqlSession.selectList("getModuleDelegateInfoByDelegateId", delegateId);
		return list;
	}

}
