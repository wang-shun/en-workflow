package com.chinacreator.c2.flow.cmd.workdate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.db.DbSqlSessionFactory;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfWorkDateEntity;

public class GetWorkDateOfDayCmd implements Command<List<WfWorkDateEntity>> {

	private String curDate;

	public GetWorkDateOfDayCmd(String curDate) {
		this.curDate = curDate;
	}

	@Override
	public List<WfWorkDateEntity> execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		DbSqlSessionFactory ss = dbSqlSession.getDbSqlSessionFactory();
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("curDate", curDate);
		//oracle支持
		if ("oracle".equals(ss.getDatabaseType())) {

			@SuppressWarnings("unchecked")
			List<WfWorkDateEntity> result = dbSqlSession.getSqlSession()
					.selectList("getWorkDateOfDay_oracle", parameter);
			return result;
		//oracle支持
		}else if("mysql".equals(ss.getDatabaseType())){
			@SuppressWarnings("unchecked")
			List<WfWorkDateEntity> result = dbSqlSession.getSqlSession()
					.selectList("getWorkDateOfDay_mysql", parameter);
			return result;
		}
		return null;

	}

}
