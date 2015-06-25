package com.chinacreator.c2.flow.cmd.processdefine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.apache.ibatis.session.RowBounds;

import com.chinacreator.c2.flow.detail.WfPageList;
import com.chinacreator.c2.flow.detail.WfProcessDefinitionParam;
import com.chinacreator.c2.flow.persistence.entity.WfProcessDefinitionAndDeployInfoEntity;

public class GetProcessDefinitionAndDeployInfoListCmd implements
Command<WfPageList<WfProcessDefinitionAndDeployInfoEntity, WfProcessDefinitionParam>>{
	
	private WfProcessDefinitionParam wfProcessDefinitionParam;
	
	public GetProcessDefinitionAndDeployInfoListCmd(WfProcessDefinitionParam wfProcessDefinitionParam){
		this.wfProcessDefinitionParam = wfProcessDefinitionParam;
	}

	@Override
	public WfPageList<WfProcessDefinitionAndDeployInfoEntity,WfProcessDefinitionParam> execute(
			CommandContext commandContext) {
		WfPageList<WfProcessDefinitionAndDeployInfoEntity,WfProcessDefinitionParam> wfPageList = new WfPageList<WfProcessDefinitionAndDeployInfoEntity, WfProcessDefinitionParam>();
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("wfProcessDefinitionParam", wfProcessDefinitionParam);
		
		int cnt = (Integer)dbSqlSession.selectOne("getProcessDefinitionAndDeployInfoListC", parameters);
		if(cnt > 0){
			@SuppressWarnings("unchecked")
			List<WfProcessDefinitionAndDeployInfoEntity> result = dbSqlSession
					.getSqlSession().selectList(
							"getProcessDefinitionAndDeployInfoList",
							parameters,
							new RowBounds((int) wfProcessDefinitionParam.getStart(), (int) wfProcessDefinitionParam.getSize()));
			wfProcessDefinitionParam.setTotal(cnt);
			
			wfPageList.setWfQuery(wfProcessDefinitionParam);
			wfPageList.addAll(result);
		}
		return wfPageList;
		
	}

}
