package com.chinacreator.c2.flow.rest.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

import org.activiti.rest.service.api.engine.variable.QueryVariable;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;

/**
 * @author hushowly
 */
@ApiModel(value="WfHistoricProcessInstanceQueryRequest",description="历史流程实例查询参数")
public class WfHistoricProcessInstanceQueryRequest extends WfPaginateRequest {

  @ApiModelProperty("流程实例d")
  private String processInstanceId;
  @ApiModelProperty("流程实例集合")
  private List<String> processInstanceIds;
  @ApiModelProperty("业务主键")
  private String processBusinessKey;
  @ApiModelProperty("流程定义id")
  private String processDefinitionId;
  @ApiModelProperty("流程定义key")
  private String processDefinitionKey;
  @ApiModelProperty("父流程id")
  private String superProcessInstanceId;
  @ApiModelProperty("排除拥有子流的")
  private Boolean excludeSubprocesses;
  @ApiModelProperty("是否完成")
  private Boolean finished;
  @ApiModelProperty("流程参与者(处理人、候选人、所有者) ")
  private String involvedUser;
  @ApiModelProperty("完成时间晚于")
  private Date finishedAfter;
  @ApiModelProperty("完成时间早于")
  private Date finishedBefore;
  @ApiModelProperty("开始时间晚于")
  private Date startedAfter;
  @ApiModelProperty("开始时间早于")
  private Date startedBefore;
  @ApiModelProperty("流程启动者")
  private String startedBy;
  @ApiModelProperty("是否包含流程变量")
  private Boolean includeProcessVariables;
  @ApiModelProperty("流程自定义变量条件")  
  private List<WfQueryVariable> variables;
  @ApiModelProperty("租户")
  private String tenantId;
  @ApiModelProperty("租户模糊匹配")
  private String tenantIdLike;
  @ApiModelProperty("是否启用租户过滤")
  private Boolean withoutTenantId;
  
  public String getProcessInstanceId() {
    return processInstanceId;
  }
  
  public void setProcessInstanceId(String processInstanceId) {
    this.processInstanceId = processInstanceId;
  }
  
  public List<String> getProcessInstanceIds() {
    return processInstanceIds;
  }

  public void setProcessInstanceIds(List<String> processInstanceIds) {
    this.processInstanceIds = processInstanceIds;
  }

  public String getProcessBusinessKey() {
    return processBusinessKey;
  }
  
  public void setProcessBusinessKey(String processBusinessKey) {
    this.processBusinessKey = processBusinessKey;
  }
  
  public String getProcessDefinitionId() {
    return processDefinitionId;
  }
  
  public void setProcessDefinitionId(String processDefinitionId) {
    this.processDefinitionId = processDefinitionId;
  }
  
  public String getProcessDefinitionKey() {
    return processDefinitionKey;
  }
  
  public void setProcessDefinitionKey(String processDefinitionKey) {
    this.processDefinitionKey = processDefinitionKey;
  }
  
  public String getSuperProcessInstanceId() {
    return superProcessInstanceId;
  }
  
  public void setSuperProcessInstanceId(String superProcessInstanceId) {
    this.superProcessInstanceId = superProcessInstanceId;
  }

  public Boolean getExcludeSubprocesses() {
    return excludeSubprocesses;
  }

  public void setExcludeSubprocesses(Boolean excludeSubprocesses) {
    this.excludeSubprocesses = excludeSubprocesses;
  }

  public Boolean getFinished() {
    return finished;
  }

  public void setFinished(Boolean finished) {
    this.finished = finished;
  }

  public String getInvolvedUser() {
    return involvedUser;
  }
  
  public void setInvolvedUser(String involvedUser) {
    this.involvedUser = involvedUser;
  }
  
  public Date getFinishedAfter() {
    return finishedAfter;
  }

  public void setFinishedAfter(Date finishedAfter) {
    this.finishedAfter = finishedAfter;
  }

  public Date getFinishedBefore() {
    return finishedBefore;
  }

  public void setFinishedBefore(Date finishedBefore) {
    this.finishedBefore = finishedBefore;
  }

  public Date getStartedAfter() {
    return startedAfter;
  }

  public void setStartedAfter(Date startedAfter) {
    this.startedAfter = startedAfter;
  }

  public Date getStartedBefore() {
    return startedBefore;
  }

  public void setStartedBefore(Date startedBefore) {
    this.startedBefore = startedBefore;
  }

  public String getStartedBy() {
    return startedBy;
  }

  public void setStartedBy(String startedBy) {
    this.startedBy = startedBy;
  }

  public Boolean getIncludeProcessVariables() {
    return includeProcessVariables;
  }

  public void setIncludeProcessVariables(Boolean includeProcessVariables) {
    this.includeProcessVariables = includeProcessVariables;
  }

  @JsonTypeInfo(use=Id.CLASS, defaultImpl=WfQueryVariable.class)  
  public List<WfQueryVariable> getVariables() {
    return variables;
  }
  
  public void setVariables(List<WfQueryVariable> variables) {
    this.variables = variables;
  }

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantIdLike() {
		return tenantIdLike;
	}

	public void setTenantIdLike(String tenantIdLike) {
		this.tenantIdLike = tenantIdLike;
	}

	public Boolean getWithoutTenantId() {
		return withoutTenantId;
	}

	public void setWithoutTenantId(Boolean withoutTenantId) {
		this.withoutTenantId = withoutTenantId;
	}
   
}
