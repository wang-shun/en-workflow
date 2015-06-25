package com.chinacreator.c2.flow.cmd.aspect;

import org.activiti.engine.identity.User;
import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfOperateLogEntity;

public class InsertWfOperateLogCmd implements Command<Void> {
	private WfOperateLogEntity data;

	public InsertWfOperateLogCmd(WfOperateLogEntity data) {
		this.data = data;
	}

	@Override
	public Void execute(CommandContext commandContext) {
		if(data != null){
			String userId = data.getUserId();
			User u = commandContext.getUserIdentityManager().findUserById(userId);
			String userCName = u.getLastName();
			String userName = u.getFirstName();
			userCName = userCName == null ? userName : userCName + "("+userName+")";
			data.setUserId(userCName);
		}
		data.setEngineName(commandContext.getProcessEngineConfiguration().getProcessEngineName());
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		dbSqlSession.insert(data);
		return null;
	}

}
