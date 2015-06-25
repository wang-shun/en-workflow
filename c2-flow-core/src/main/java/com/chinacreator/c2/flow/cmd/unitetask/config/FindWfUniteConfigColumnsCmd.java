package com.chinacreator.c2.flow.cmd.unitetask.config;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.interceptor.CommandExecutor;

import com.chinacreator.c2.flow.detail.WfUniteColumn;
import com.chinacreator.c2.flow.detail.WfUniteTaskResult;
import com.chinacreator.c2.flow.persistence.entity.WfUniteColumnsEntity;
import com.chinacreator.c2.flow.persistence.entity.WfUniteConfigEntity;

public class FindWfUniteConfigColumnsCmd implements Command<WfUniteTaskResult> {
	private String appId;
	private String tenantId;
	private String engineName;
	private String taskList;

	public FindWfUniteConfigColumnsCmd(String appId, String tenantId,
			String engineName, String taskList) {
		this.appId = appId;
		this.tenantId = tenantId;
		this.engineName = engineName;
		this.taskList = taskList;
	}

	@Override
	public WfUniteTaskResult execute(CommandContext commandContext) {
		CommandExecutor commandExecutor = commandContext
				.getProcessEngineConfiguration().getCommandExecutor();
		WfUniteTaskResult result = new WfUniteTaskResult();
		List<WfUniteConfigEntity> list1 = commandExecutor
				.execute(new FindWfUniteConfigCmd(appId, tenantId, engineName,
						taskList));
		if (list1 != null && list1.size() > 0) {
			int tmp = list1.get(0).getOnlyTitle();
			result.setOnlyTitle(tmp == 1 ? true : false);
		}
		List<WfUniteColumnsEntity> list2 = commandExecutor
				.execute(new FindWfUniteColumnsCmd(appId, tenantId, engineName,
						taskList));
		if(list2 != null){
			List<WfUniteColumn> columns = new ArrayList<WfUniteColumn>();
			for(WfUniteColumnsEntity wuce : list2){
				WfUniteColumn column = new WfUniteColumn();
				column.setColumnId(wuce.getColumnId().replaceAll("_", "").toLowerCase());
				column.setColumnName(wuce.getColumnName());
				column.setId(wuce.getId());
				column.setConfigId(wuce.getConfigId());
				column.setIsShow(wuce.getIsShow());
				column.setIsTitle(wuce.getIsTitle());
				column.setSn(wuce.getSn());
				column.setRevision(wuce.getRevision());
				columns.add(column);
			}
			result.setColumns(columns);
		}
		return result;
	}

}
