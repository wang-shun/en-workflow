package com.chinacreator.c2.flow.util;

import java.util.Map;

import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.task.TaskDefinition;
import org.springframework.context.ApplicationContext;

import com.chinacreator.c2.flow.listener.ExtendTaskListener;

public class AddTaskListenerUtil {

	public static void addTaskListener(RepositoryService repositoryService,
			ManagementService managementService, ApplicationContext applicationContext, String processDefinitionKey) {
		ProcessDefinitionEntity pde = (ProcessDefinitionEntity) repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionKey(processDefinitionKey).latestVersion()
				.singleResult();
		// if (pde.getVersion() == 1) {//修改流程部署后没有监听器了
		pde = (ProcessDefinitionEntity) repositoryService
				.getProcessDefinition(pde.getId());
		Map<String, TaskDefinition> taskDefinitions = pde.getTaskDefinitions();
		if (taskDefinitions != null) {
			for (String key : taskDefinitions.keySet()) {
				TaskDefinition taskDefinition = taskDefinitions.get(key);
				taskDefinition.addTaskListener(
						TaskListener.EVENTNAME_ALL_EVENTS,
						new ExtendTaskListener(managementService,applicationContext));
			}
		}
		// }
	}
}
