package com.chinacreator.c2.flow.persistence.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.db.PersistentObject;

/**
 * 用户流程实例监控对象
 * 
 * @author 杨祎程
 * 
 */
public class WfProcessInstanceMonitorEntity implements 
		PersistentObject, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;

	/**
	 * 事项名称(目前结果集中不做展示，因为一个流程定义可对应多个事项，期待新的扩展设计)预留
	 */
	private String moduleName;

	/**
	 * 事项ID(目前结果集中不做展示，因为一个流程定义可对应多个事项，期待新的扩展设计)预留
	 */
	private String moduleId;

	/**
	 * 业务标识
	 */
	private String businessKey;

	/**
	 * 流程实例ID
	 */
	private String processInstanceId;

	/**
	 * 流程定义标识
	 */
	private String processDefinitionKey;

	/**
	 * 流程定义ID
	 */
	private String processDefinitionId;

	/**
	 * 任务key
	 */
	private String actKey;

	/**
	 * 任务名称
	 */
	private String actName;

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getActKey() {
		return actKey;
	}

	public void setActKey(String actKey) {
		this.actKey = actKey;
	}

	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public Object getPersistentState() {
		Map<String, Object> persistentState = new HashMap<String, Object>();
		persistentState.put("id", this.id);
		persistentState.put("actKey", this.actKey);
		persistentState.put("actName", this.actName);
		persistentState.put("businessKey", this.businessKey);
		persistentState.put("moduleId", this.moduleId);
		persistentState.put("moduleName", this.moduleName);
		persistentState.put("processDefinitionKey", this.processDefinitionKey);
		persistentState.put("processDefinitionKey", this.processDefinitionKey);
		persistentState.put("processInstanceId", this.processInstanceId);
		return persistentState;
	}

}
