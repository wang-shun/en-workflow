package com.chinacreator.c2.flow.detail;

import java.io.Serializable;

/**
 * 流程环节外围配置信息（wf_mod_proc_prop）
 * @author yicheng.yang
 *
 */
public class WfProcessConfigProperty implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 事项绑定ID
	 */
	private String configId;
	
	/**
	 * 任务定义ID
	 */
	private String taskDefKey;
	
	/**
	 * 别名
	 */
	private String alias;
	
	/**
	 * 期限数字
	 */
	private Integer duration;
	
	/**
	 * 流程期限单位（年:Y;月:M;日:D;小时:h;分钟:m;秒:s）
	 */
	private String durationUnit;
	
	/**
	 * 执行人（逗号分隔）
	 */
	private String performer;
	
	/**
	 * 执行用户组（逗号分隔）
	 */
	private String groupPerformer;
	
	/**
	 * 流程绑定表单
	 */
	private String bindForm;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConfigId() {
		return configId;
	}

	public void setConfigId(String configId) {
		this.configId = configId;
	}

	public String getTaskDefKey() {
		return taskDefKey;
	}

	public void setTaskDefKey(String taskDefKey) {
		this.taskDefKey = taskDefKey;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getDurationUnit() {
		return durationUnit;
	}

	public void setDurationUnit(String durationUnit) {
		this.durationUnit = durationUnit;
	}

	public String getPerformer() {
		return performer;
	}

	public void setPerformer(String performer) {
		this.performer = performer;
	}

	public String getBindForm() {
		return bindForm;
	}

	public void setBindForm(String bindForm) {
		this.bindForm = bindForm;
	}
	
	public String getGroupPerformer() {
		return groupPerformer;
	}

	public void setGroupPerformer(String groupPerformer) {
		this.groupPerformer = groupPerformer;
	}

	@Override
	public String toString() {
		return "WfProcessConfigProperty [id=" + id + ", configId=" + configId + ", groupPerformer=" + groupPerformer
				+ ", taskDefKey=" + taskDefKey + ", alias=" + alias
				+ ", duration=" + duration + ", durationUnit=" + durationUnit
				+ ", performer=" + performer + ", bindForm=" + bindForm + "]";
	}
}
