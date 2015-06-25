package com.chinacreator.c2.flow.cmd.holiday;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfHolidayEntity;

public class InsertHolidayCmd implements Command<Void>{

	private WfHolidayEntity wfHoliday;
	
	public InsertHolidayCmd(WfHolidayEntity wfHoliday){
		this.wfHoliday = wfHoliday;
	}
	
	@Override
	public Void execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		dbSqlSession.insert(wfHoliday);
		return null;
	}

}
