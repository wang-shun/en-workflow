package com.chinacreator.c2.flow.detail;

import java.io.Serializable;
import java.util.Date;

/**
 * 历史变量实例
 * 
 * @author Administrator
 * 
 */
public class WfHistoricVariableInstance implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 变量实例id
	 */
	private String id;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 最后更新时间
	 */
	private Date lastUpdatedTime;

	/**
	 * 流程实例id
	 */
	private String processInstanceId;

	/**
	 * 任务id
	 */
	private String taskId;

	/**
	 * 创建时间
	 */
	private Date time;

	/**
	 * 变量值
	 */
	private Object value;

	/**
	 * 变量名称
	 */
	private String variableName;

	/**
	 * 变量类型名称
	 */
	private String variableTypeName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public String getVariableTypeName() {
		return variableTypeName;
	}

	public void setVariableTypeName(String variableTypeName) {
		this.variableTypeName = variableTypeName;
	}

}
