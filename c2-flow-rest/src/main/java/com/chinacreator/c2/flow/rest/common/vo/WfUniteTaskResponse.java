package com.chinacreator.c2.flow.rest.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 * 统一任务结果集，包括需要显示的字段信息，分页信息，查询结果等
 * 
 * @author hushowly
 * 
 */
@ApiModel(value="WfUniteTaskResponse",description="c2扩展的统一任务信息")
public class WfUniteTaskResponse implements Serializable {
	
	
	private static final long serialVersionUID = 5910253186909490545L;
	
	@ApiModelProperty("统一待办特有id")
	private String id;
	@ApiModelProperty("任务id")
	private String taskId;
	@ApiModelProperty("业务主键")
	private String businessKey;
	@ApiModelProperty("任务执行id")
	private String executionId;
	@ApiModelProperty("任务所在流程实例id")
	private String procInstId;
	@ApiModelProperty("任务所在流程定义id")
	private String procDefId;
	@ApiModelProperty("任务名称")
	private String name;
	@ApiModelProperty("父任务id")
	private String parentTaskId;
	@ApiModelProperty("任务描述")
	private String description;
	@ApiModelProperty("任务所在环节定义key")
	private String taskdefKey;
	@ApiModelProperty("任务所有者")
	private String owner;
	@ApiModelProperty("处理人")
	private String assignee;
	@ApiModelProperty("候选人")
	private List<String> candidate;
	@ApiModelProperty("委托人")
	private String delegation;
	@ApiModelProperty("任务优先级")
	private Integer priority;
	@ApiModelProperty("任务创建时间")
	private Timestamp createTime;
	@ApiModelProperty("任务结束时间")
	private Timestamp endTime;
	@ApiModelProperty("任务过期时间")
	private Timestamp dueDate;
	@ApiModelProperty("任务类别")
	private String category;
	@ApiModelProperty("任务状态")
	private String taskState;
	@ApiModelProperty("租户")
	private String tenantId;
	@ApiModelProperty("应用id")
	private String appId;
	@ApiModelProperty("模块id")
	private String moduleId;
	@ApiModelProperty("模块名称")
	private String moduleName;
	@ApiModelProperty("删除原因")
	private String deleteReason;
	@ApiModelProperty("表单key")
	private String formKey;
	@ApiModelProperty("版本")
	private Integer revision;
	@ApiModelProperty("备用字段1")
	private String remark1;
	@ApiModelProperty("备用字段2")
	private String remark2;
	@ApiModelProperty("备用字段3")
	private String remark3;
	@ApiModelProperty("备用字段4")
	private String remark4;
	@ApiModelProperty("备用字段5")
	private String remark5;
	@ApiModelProperty("备用字段6")
	private String remark6;
	@ApiModelProperty("备用字段7")
	private String remark7;
	@ApiModelProperty("备用字段9")
	private String remark8;
	@ApiModelProperty("备用字段9")
	private String remark9;
	@ApiModelProperty("备用字段10")
	private String remark10;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
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
	public String getTaskdefKey() {
		return taskdefKey;
	}
	public void setTaskdefKey(String taskdefKey) {
		this.taskdefKey = taskdefKey;
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
	public List<String> getCandidate() {
		return candidate;
	}
	public void setCandidate(List<String> candidate) {
		this.candidate = candidate;
	}
	public String getDelegation() {
		return delegation;
	}
	public void setDelegation(String delegation) {
		this.delegation = delegation;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public Timestamp getDueDate() {
		return dueDate;
	}
	public void setDueDate(Timestamp dueDate) {
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
	public Integer getRevision() {
		return revision;
	}
	public void setRevision(Integer revision) {
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

	
	
	
	
}