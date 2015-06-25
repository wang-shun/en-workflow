package com.chinacreator.c2.flow.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.db.HasRevision;
import org.activiti.engine.impl.db.PersistentObject;

public class WfUniteHisTaskEntity implements HasRevision, PersistentObject,
		Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String taskId;
	private String businessKey;
	private String executionId;
	private String procInstId;
	private String procDefId;
	private String name;
	private String parentTaskId;
	private String description;
	private String taskDefKey;
	private String owner;
	private String assignee;
	private String candidate;
	private String delegation;
	private int priority = 1;
	private Date createTime;
	private Date endTime;
	private Date dueDate;
	private String category;
	private String taskState;
	private String tenantId;
	private String appId;
	private String moduleId;
	private String moduleName;
	private String deleteReason;
	private String formKey;
	private int revision = 1;
	private String remark1;
	private String remark2;
	private String remark3;
	private String remark4;
	private String remark5;
	private String remark6;
	private String remark7;
	private String remark8;
	private String remark9;
	private String remark10;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public String getProcInstId() {
		return procInstId;
	}

	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}

	public String getProcDefId() {
		return procDefId;
	}

	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentTaskId() {
		return parentTaskId;
	}

	public void setParentTaskId(String parentTaskId) {
		this.parentTaskId = parentTaskId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTaskDefKey() {
		return taskDefKey;
	}

	public void setTaskDefKey(String taskDefKey) {
		this.taskDefKey = taskDefKey;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getCandidate() {
		return candidate;
	}

	public void setCandidate(String candidate) {
		this.candidate = candidate;
	}

	public String getDelegation() {
		return delegation;
	}

	public void setDelegation(String delegation) {
		this.delegation = delegation;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTaskState() {
		return taskState;
	}

	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getDeleteReason() {
		return deleteReason;
	}

	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}

	public String getFormKey() {
		return formKey;
	}

	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}

	public int getRevision() {
		return revision;
	}

	public void setRevision(int revision) {
		this.revision = revision;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getRemark4() {
		return remark4;
	}

	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}

	public String getRemark5() {
		return remark5;
	}

	public void setRemark5(String remark5) {
		this.remark5 = remark5;
	}

	public String getRemark6() {
		return remark6;
	}

	public void setRemark6(String remark6) {
		this.remark6 = remark6;
	}

	public String getRemark7() {
		return remark7;
	}

	public void setRemark7(String remark7) {
		this.remark7 = remark7;
	}

	public String getRemark8() {
		return remark8;
	}

	public void setRemark8(String remark8) {
		this.remark8 = remark8;
	}

	public String getRemark9() {
		return remark9;
	}

	public void setRemark9(String remark9) {
		this.remark9 = remark9;
	}

	public String getRemark10() {
		return remark10;
	}

	public void setRemark10(String remark10) {
		this.remark10 = remark10;
	}

	@Override
	public Object getPersistentState() {
		Map<String, Object> persistentState = new HashMap<String, Object>();
		persistentState.put("id", this.id);
		persistentState.put("appId", this.appId);
		persistentState.put("assignee", this.assignee);
		persistentState.put("businessKey", this.businessKey);
		persistentState.put("candidate", this.candidate);
		persistentState.put("category", this.category);
		persistentState.put("createTime", this.createTime);
		persistentState.put("endTime", this.endTime);
		persistentState.put("delegation", this.delegation);
		persistentState.put("deleteReason", this.deleteReason);
		persistentState.put("description", this.description);
		persistentState.put("dueDate", this.dueDate);
		persistentState.put("executionId", this.executionId);
		persistentState.put("formKey", this.formKey);
		persistentState.put("moduleId", this.moduleId);
		persistentState.put("moduleName", this.moduleName);
		persistentState.put("name", this.name);
		persistentState.put("owner", this.owner);
		persistentState.put("parentTaskId", this.parentTaskId);
		persistentState.put("priority", this.priority);
		persistentState.put("procDefId", this.procDefId);
		persistentState.put("procInstId", this.procInstId);
		persistentState.put("revision", this.revision);
		persistentState.put("taskDefKey", this.taskDefKey);
		persistentState.put("taskId", this.taskId);
		persistentState.put("taskState", this.taskState);
		persistentState.put("tenantId", this.tenantId);
		persistentState.put("remark1", this.remark1);
		persistentState.put("remark2", this.remark2);
		persistentState.put("remark3", this.remark3);
		persistentState.put("remark4", this.remark4);
		persistentState.put("remark5", this.remark5);
		persistentState.put("remark6", this.remark6);
		persistentState.put("remark7", this.remark7);
		persistentState.put("remark8", this.remark8);
		persistentState.put("remark9", this.remark9);
		persistentState.put("remark10", this.remark10);
		return persistentState;
	}

	@Override
	public int getRevisionNext() {
		return revision + 1;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
