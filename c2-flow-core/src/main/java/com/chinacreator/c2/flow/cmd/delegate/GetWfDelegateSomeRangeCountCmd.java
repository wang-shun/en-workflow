package com.chinacreator.c2.flow.cmd.delegate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

public class GetWfDelegateSomeRangeCountCmd implements Command<Integer>{

	private String delegateId;
	
	private Date startTime;
	
	private Date endTime;
	
	private String moduleId;
	
	private String designatorId;
	
	private String designeeId;
	
	private String delegateRange;
	
	public GetWfDelegateSomeRangeCountCmd(String delegateId, String moduleId, String designatorId, String designeeId, String delegateRange, Date startTime, Date endTime){
		this.delegateId = delegateId;
		this.moduleId = moduleId;
		this.designatorId = designatorId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.delegateRange = delegateRange;
		this.designeeId = designeeId;
	}
	
	@Override
	public Integer execute(CommandContext commandContext) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("delegateId", delegateId);
		parameter.put("moduleId", moduleId);
		parameter.put("designatorId", designatorId);
		parameter.put("designeeId", designeeId);
		parameter.put("startTime", startTime);
		parameter.put("endTime", endTime);
		parameter.put("delegateRange", delegateRange);
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		return (Integer)dbSqlSession.selectOne("getWfDelegateSomeRangeCount", parameter);
	}

}
