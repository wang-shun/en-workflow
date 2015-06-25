package com.chinacreator.c2.flow.cmd.delegate;

import java.util.Date;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfModuleDelegateEntity;

public class UpdateModuleDelegateStateCmd implements Command<Void>{
	
	private String delegateId;
	private String delegateState;
	private Date confirmTime;
	
	public UpdateModuleDelegateStateCmd(String delegateId, String delegateState, Date confirmTime){
		this.delegateId = delegateId;
		this.delegateState = delegateState;
		this.confirmTime = confirmTime;
	}

	@Override
	public Void execute(CommandContext commandContext) {
		WfModuleDelegateEntity object = new WfModuleDelegateEntity();
		object.setDelegateId(delegateId);
		object.setDelegateStat(delegateState);
		object.setConfirmTime(confirmTime);
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		dbSqlSession.update("updateModuleDelegateState", object);
		return null;
	}

}
