package com.chinacreator.c2.flow.detail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 表单数据
 * 
 * @author minghua.guo
 * 
 */
public class WfFormData implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 表单key
	 */
	private String formKey;
	/**
	 * 部署id
	 */
	private String deploymentId;
	/**
	 * 任务id
	 */
	private String taskId;
	/**
	 * 流程定义id
	 */
	private String processDefinitionId;
	/**
	 * 表单属性
	 */
	private List<WfFormProperty> formProperties = new ArrayList<WfFormProperty>();

	public String getFormKey() {
		return formKey;
	}

	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public List<WfFormProperty> getFormProperties() {
		return formProperties;
	}

	public void setFormProperties(List<WfFormProperty> formProperties) {
		this.formProperties = formProperties;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}
}
