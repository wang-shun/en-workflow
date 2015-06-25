package com.chinacreator.c2.flow.cmd.delegate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.apache.ibatis.session.RowBounds;

import com.chinacreator.c2.flow.detail.WfModuleDelegateParam;
import com.chinacreator.c2.flow.detail.WfPageList;
import com.chinacreator.c2.flow.persistence.entity.WfModuleDelegateEntity;

public class GetModuleDelegateByParam implements Command<WfPageList<WfModuleDelegateEntity, WfModuleDelegateParam>> {
	
	private WfModuleDelegateParam wfModuleDelegate;
	
	public GetModuleDelegateByParam(WfModuleDelegateParam wfModuleDelegate){
		this.wfModuleDelegate = wfModuleDelegate;
	}
	

	@Override
	public WfPageList<WfModuleDelegateEntity,WfModuleDelegateParam> execute(CommandContext commandContext) {
		WfPageList<WfModuleDelegateEntity,WfModuleDelegateParam> wfPageList = new WfPageList<WfModuleDelegateEntity, WfModuleDelegateParam>();
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("wfModuleDelegate", wfModuleDelegate);
		
		int cnt = (Integer)dbSqlSession.selectOne("getModuleDelegateByParamC", parameter);
		
		if(cnt > 0){
			RowBounds rb = new RowBounds((int)wfModuleDelegate.getStart(), (int)wfModuleDelegate.getSize());
			parameter.put("firstResult", wfModuleDelegate.getStart());
			parameter.put("maxResults", wfModuleDelegate.getSize());
			
			List<WfModuleDelegateEntity> result =  dbSqlSession.getSqlSession().selectList("getModuleDelegateByParam", parameter, rb);
			//List<WfModuleDelegateEntity> result = dbSqlSession.selectListWithRawParameter("getModuleDelegateByParam", parameter, (int)wfModuleDelegate.getStart(), (int)wfModuleDelegate.getSize());
			
			
			wfModuleDelegate.setTotal(cnt);
			
			wfPageList.setWfQuery(wfModuleDelegate);
			wfPageList.addAll(result);
		}
		
		return wfPageList;
		
	}

}
