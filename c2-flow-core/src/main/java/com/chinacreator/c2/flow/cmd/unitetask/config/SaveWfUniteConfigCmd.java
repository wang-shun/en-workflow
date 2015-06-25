package com.chinacreator.c2.flow.cmd.unitetask.config;

import java.util.List;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

import com.chinacreator.c2.flow.persistence.entity.WfUniteConfigEntity;

public class SaveWfUniteConfigCmd implements Command<Object> {
	private WfUniteConfigEntity data;

	public SaveWfUniteConfigCmd(WfUniteConfigEntity data) {
		this.data = data;
	}

	@Override
	public Object execute(CommandContext commandContext) {
		DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
		if (data != null && data.getAppId() != null
				&& data.getEngineName() != null && data.getTaskList() != null
				&& data.getTenantId() != null) {
			List<?> tmp = dbSqlSession.selectListWithRawParameter(
					"selectWfUniteConfigByC", data, 0, Integer.MAX_VALUE);
			if (tmp != null && tmp.size() > 0) {
				WfUniteConfigEntity data1 = (WfUniteConfigEntity)tmp.get(0);
				data1.setAppId(data.getAppId());
				data1.setEngineName(data.getEngineName());
				data1.setOnlyTitle(data.getOnlyTitle());
				data1.setTaskList(data.getTaskList());
				data1.setTenantId(data.getTenantId());
				dbSqlSession.update(data1);
			} else {
				dbSqlSession.insert(data);
			}
		} else {
			dbSqlSession.insert(data);
		}
		return data.getId();
	}

}
