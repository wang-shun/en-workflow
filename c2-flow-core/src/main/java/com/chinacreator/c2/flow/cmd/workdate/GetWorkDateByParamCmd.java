package com.chinacreator.c2.flow.cmd.workdate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.apache.ibatis.session.RowBounds;

import com.chinacreator.c2.flow.detail.WfPageList;
import com.chinacreator.c2.flow.detail.WfWorkDateParam;
import com.chinacreator.c2.flow.persistence.entity.WfWorkDateEntity;

public class GetWorkDateByParamCmd implements Command<WfPageList<WfWorkDateEntity, WfWorkDateParam>>{

	private WfWorkDateParam wfWorkDateParam;
	
	public GetWorkDateByParamCmd(WfWorkDateParam wfWorkDateParam){
		this.wfWorkDateParam = wfWorkDateParam;
	}
	
	@Override
	public WfPageList<WfWorkDateEntity, WfWorkDateParam> execute(
			CommandContext commandContext) {
		WfPageList<WfWorkDateEntity,WfWorkDateParam> wfPageList = new WfPageList<WfWorkDateEntity, WfWorkDateParam>();
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("wfWorkDateParam", wfWorkDateParam);
		
		int cnt = (Integer)dbSqlSession.selectOne("getWorkDateByParamC", parameter);
		
		if(cnt > 0){
			RowBounds rowBounds = new RowBounds((int)wfWorkDateParam.getStart(), (int)wfWorkDateParam.getSize());
			parameter.put("firstResult", wfWorkDateParam.getStart());
			parameter.put("maxResults", wfWorkDateParam.getSize());
			
			List<WfWorkDateEntity> result = dbSqlSession.getSqlSession().selectList("getWorkDateByParam", parameter, rowBounds);//.selectListWithRawParameter("getWorkDateByParam", parameter, (int)wfWorkDateParam.getStart(), (int)wfWorkDateParam.getSize());
			
			wfWorkDateParam.setTotal(cnt);
			
			wfPageList.setWfQuery(wfWorkDateParam);
			wfPageList.addAll(result);
		}
		return wfPageList;
	}

}