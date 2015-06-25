package com.chinacreator.c2.flow.cmd.holiday;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

public class DeleteHolidayCmd implements Command<Void> {

	private String holiday;
	
	private String yHoliday;
	
	private String mHoliday;
	
	private String tenantId;
	
	public DeleteHolidayCmd(String holiday, String yHoliday, String mHoliday, String tenantId){
		
		this.holiday = holiday;
		this.yHoliday = yHoliday;
		this.mHoliday = mHoliday;
		this.tenantId = tenantId;
		
	}
	
	@Override
	public Void execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("holiday", holiday);
		parameter.put("yHoliday", yHoliday);
		parameter.put("mHoliday", mHoliday);
		parameter.put("tenantId", tenantId);
		dbSqlSession.delete("deleteHolidayByParam", parameter);
		return null;
	}

}
