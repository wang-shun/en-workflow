package com.chinacreator.c2.flow.cfg;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.impl.util.ReflectUtil;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.session.Configuration;

import com.chinacreator.c2.flow.cmd.WfExtendSchemaBuildCmd;
import com.chinacreator.c2.flow.listener.ExtendTaskListener;

public class ExtendSpringProcessEngineConfiguration extends
		SpringProcessEngineConfiguration {
	private String sys_prefix = "";
	
	public static final String EXTEND_MYBATIS_MAPPING_FILE = "com/chinacreator/c2/flow/persistence/mapping/mappings.xml";

 	String customMappers;
	@Override
	public ProcessEngine buildProcessEngine() {
		ProcessEngine processEngine = super.buildProcessEngine();
		if (DB_SCHEMA_UPDATE_TRUE.equals(databaseSchemaUpdate)) {
			// 执行扩展库表初始化
			commandExecutor.execute(this.getSchemaCommandConfig(),
					new WfExtendSchemaBuildCmd(sys_prefix));
		}
		initCustomMappers();
		/**
		 * 初始化所有流程定义任务监听器,用于统一任务
		 */
		initTaskListeners();
		return processEngine;
	}
	
 	protected void initCustomMappers() {
 		if(customMappers!=null){
	
 			String[] list = customMappers.split(",");
 			for (String s : list) {
 				Configuration configuration = this.sqlSessionFactory
 						.getConfiguration();
 				InputStream inputStream = ReflectUtil.getResourceAsStream(s);
 				new XMLMapperBuilder(inputStream, configuration, s,
 						configuration.getSqlFragments()).parse();
 			}			
 		}
 
 	}
	@Override
	protected InputStream getMyBatisXmlConfigurationSteam() {
		return ReflectUtil.getResourceAsStream(EXTEND_MYBATIS_MAPPING_FILE);
	}


	protected void initTaskListeners() {
		List<ProcessDefinition> processDefinitions = repositoryService
				.createProcessDefinitionQuery().list();
		if (processDefinitions != null) {
			for (ProcessDefinition pd : processDefinitions) {
				ProcessDefinitionEntity pde = (ProcessDefinitionEntity) repositoryService
						.getProcessDefinition(pd.getId());
				Map<String, TaskDefinition> taskDefinitions = pde
						.getTaskDefinitions();
				if (taskDefinitions != null) {
					for (String key : taskDefinitions.keySet()) {
						TaskDefinition taskDefinition = taskDefinitions
								.get(key);
						taskDefinition.addTaskListener(
								TaskListener.EVENTNAME_ALL_EVENTS,
								new ExtendTaskListener(managementService, this.getApplicationContext()));
					}
				}
			}
		}
	}

	/**
	 * 重写覆盖databaseSchema配置，方便兼容pt数据源引入后对databaseSchema的配置
	 */
	@Override
	protected void initSessionFactories() {
		try {
			if (databaseSchema == null) {
				this.databaseSchema = getDataSource().getConnection()
						.getMetaData().getUserName();
				this.databaseSchema = this.databaseSchema.toUpperCase();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		super.initSessionFactories();
	}

	public String getSys_prefix() {
		return sys_prefix;
	}

	public void setSys_prefix(String sys_prefix) {
		this.sys_prefix = sys_prefix;
	}
	
 	public String getCustomMappers() {
 		return customMappers;
 	}
 
 	public void setCustomMappers(String customMappers) {
 		this.customMappers = customMappers;
 	}
}
