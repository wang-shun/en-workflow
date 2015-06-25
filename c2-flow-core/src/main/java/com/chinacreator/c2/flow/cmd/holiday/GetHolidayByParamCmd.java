package com.chinacreator.c2.flow.cmd.holiday;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.detail.WfHoliday;

public class GetHolidayByParamCmd  implements Command<List<WfHoliday>>{

	private WfHoliday wfHoliday;
	
	public GetHolidayByParamCmd(WfHoliday wfHoliday){
		this.wfHoliday = wfHoliday;
	}
	
	@Override
	public List<WfHoliday> execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("wfHoliday", wfHoliday);
		
		@SuppressWarnings("unchecked")
		List<WfHoliday> result = dbSqlSession.selectListWithRawParameter("getHolidayByParam", parameter, 0, Integer.MAX_VALUE);
		return result;
	}
	
}
