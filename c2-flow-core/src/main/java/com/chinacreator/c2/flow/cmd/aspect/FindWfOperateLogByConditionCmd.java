package com.chinacreator.c2.flow.cmd.aspect;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.apache.ibatis.session.RowBounds;

import com.chinacreator.c2.flow.detail.WfPageList;
import com.chinacreator.c2.flow.detail.WfPageParam;
import com.chinacreator.c2.flow.persistence.entity.WfOperateLogEntity;

public class FindWfOperateLogByConditionCmd implements
		Command<WfPageList<WfOperateLogEntity, WfPageParam>> {
	private Map<String, Object> parameters;
	private int firstResult = 0;
	private int maxResults = 10;

	public FindWfOperateLogByConditionCmd(Map<String, Object> parameters,
			int firstResult, int maxResults) {
		this.parameters = parameters;
		this.firstResult = firstResult;
		this.maxResults = maxResults;
	}

	@Override
	public WfPageList<WfOperateLogEntity, WfPageParam> execute(
			CommandContext commandContext) {
		WfPageList<WfOperateLogEntity, WfPageParam> result = new WfPageList<WfOperateLogEntity, WfPageParam>();
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		parameters.put("firstResult", firstResult);
		parameters.put("maxResults", maxResults);
		Long callTimeB = (Long) parameters.get("callTimeB");
		Long callTimeE = (Long) parameters.get("callTimeE");

		if (callTimeB != null) {
			Date callTimeBDate = new Date(callTimeB);
			parameters.put("callTimeB", callTimeBDate);
		} else {
			parameters.put("callTimeB", null);
		}
		if (callTimeE != null) {
			Date callTimeEDate = new Date(callTimeE);
			parameters.put("callTimeE", callTimeEDate);
		} else {
			parameters.put("callTimeE", null);
		}

		RowBounds rowBounds = new RowBounds(firstResult, maxResults);
		List<?> list = dbSqlSession.getSqlSession().selectList(
				"selectWfOperateLogByC", parameters, rowBounds);
		if (list != null && list.size() > 0) {
			for (Object obj : list) {
				result.add((WfOperateLogEntity) obj);
			}
			Object o = dbSqlSession.selectOne("selectWfOperateLogCountByC",
					parameters);
			WfPageParam page = new WfPageParam();
			page.setStart(firstResult);
			page.setSize(list.size());
			page.setTotal((Integer) o);
			result.setWfQuery(page);
		}
		return result;
	}

}
