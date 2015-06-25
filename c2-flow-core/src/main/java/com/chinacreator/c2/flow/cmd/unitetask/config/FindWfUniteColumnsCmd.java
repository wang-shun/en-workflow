package com.chinacreator.c2.flow.cmd.unitetask.config;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.interceptor.CommandExecutor;

import com.chinacreator.c2.flow.persistence.entity.WfUniteColumnsEntity;
import com.chinacreator.c2.flow.persistence.entity.WfUniteConfigEntity;

public class FindWfUniteColumnsCmd implements
		Command<List<WfUniteColumnsEntity>> {
	private String appId;
	private String tenantId;
	private String engineName;
	private String taskList;

	public FindWfUniteColumnsCmd(String appId, String tenantId,
			String engineName, String taskList) {
		this.appId = appId;
		this.tenantId = tenantId;
		this.engineName = engineName;
		this.taskList = taskList;
	}

	@Override
	public List<WfUniteColumnsEntity> execute(CommandContext commandContext) {
		List<WfUniteColumnsEntity> results = new ArrayList<WfUniteColumnsEntity>();
		try {
			CommandExecutor commandExecutor = commandContext
					.getProcessEngineConfiguration().getCommandExecutor();
			DbSqlSession dbSqlSession = commandContext.getDbSqlSession();
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("appId", appId);
			parameter.put("tenantId", tenantId);
			parameter.put("engineName", engineName);
			parameter.put("taskList", taskList);
			List<?> list = dbSqlSession.selectListWithRawParameter(
					"selectWfUnitColumnsByConfig", parameter, 0, Integer.MAX_VALUE);
			if (list == null || list.size() == 0) {
				WfUniteConfigEntity wfUniteConfig = new WfUniteConfigEntity();
				wfUniteConfig.setAppId(appId);
				wfUniteConfig.setEngineName(engineName);
				wfUniteConfig.setOnlyTitle(0);
				wfUniteConfig.setTaskList(taskList);
				wfUniteConfig.setTenantId(tenantId);
				Object configId = commandExecutor
						.execute(new SaveWfUniteConfigCmd(wfUniteConfig));
				
				DatabaseMetaData metaData = commandContext.getDbSqlSession()
						.getSqlSession().getConnection().getMetaData();
				ResultSet resultSet = metaData.getColumns(null, metaData.getUserName(),
						"WF_UNITE_RUN_TASK", null);
				while (resultSet != null && resultSet.next()) {
					WfUniteColumnsEntity wce = new WfUniteColumnsEntity();
					String columnName = resultSet.getString("COLUMN_NAME")
							.toUpperCase();
					String remarks = resultSet.getString("REMARKS");
					wce.setColumnId(columnName);
					wce.setColumnName(remarks);
					wce.setConfigId((String)configId);
					commandExecutor.execute(new SaveWfUniteColumnCmd(wce));
					results.add(wce);
				}
			} else {
				for (Object obj : list) {
					results.add((WfUniteColumnsEntity) obj);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}
}
