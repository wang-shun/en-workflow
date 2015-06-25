package com.chinacreator.c2.flow.cmd.unitetask.config;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfUniteColumnsEntity;

public class SaveWfUniteColumnCmd implements Command<Object> {
	private WfUniteColumnsEntity data;

	public SaveWfUniteColumnCmd(WfUniteColumnsEntity data) {
		this.data = data;
	}

	@Override
	public Object execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		if (data != null && data.getId() != null) {
			WfUniteColumnsEntity tmp = dbSqlSession.selectById(
					WfUniteColumnsEntity.class, data.getId());
			if(tmp == null){
				dbSqlSession.insert(data);
			}else{
				tmp.setColumnName(data.getColumnName());
				tmp.setIsShow(data.getIsShow());
				tmp.setIsTitle(data.getIsTitle());
				tmp.setSn(data.getSn());
				dbSqlSession.update(tmp);
			}
		}else{
			dbSqlSession.insert(data);
		}
		return null;
	}

}
