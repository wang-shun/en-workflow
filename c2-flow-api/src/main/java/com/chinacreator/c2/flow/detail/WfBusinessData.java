package com.chinacreator.c2.flow.detail;

import java.io.Serializable;

/**
 * 业务数据对象，用户调用流程操作时将业务参数传递到统一任务表
 * 
 * @author minghua.guo
 * 
 */
public class WfBusinessData implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 业务id
	 */
	private String businessKey;
	/**
	 * 应用id
	 */
	private String appId = "module";
	/**
	 * 事项id
	 */
	private String moduleId;
	/**
	 * 事项名称
	 */
	private String moduleName;
	
	/**
	 * 业务数据扩展对象
	 */
	private Object businessExtend;
	/**
	 * 备用字段1
	 */
	private String remark1;
	/**
	 * 备用字段2
	 */
	private String remark2;
	/**
	 * 备用字段3
	 */
	private String remark3;
	/**
	 * 备用字段4
	 */
	private String remark4;
	/**
	 * 备用字段5
	 */
	private String remark5;
	/**
	 * 备用字段6
	 */
	private String remark6;
	/**
	 * 备用字段7
	 */
	private String remark7;
	/**
	 * 备用字段8
	 */
	private String remark8;
	/**
	 * 备用字段9
	 */
	private String remark9;
	/**
	 * 备用字段10
	 */
	private String remark10;

	public WfBusinessData() {

	}

	public WfBusinessData(String businessKey, String appId, String moduleId,
			String moduleName, String remark1, String remark2, String remark3,
			String remark4, String remark5, String remark6, String remark7,
			String remark8, String remark9, String remark10) {
		super();
		this.businessKey = businessKey;
		this.appId = appId;
		this.moduleId = moduleId;
		this.moduleName = moduleName;
		this.remark1 = remark1;
		this.remark2 = remark2;
		this.remark3 = remark3;
		this.remark4 = remark4;
		this.remark5 = remark5;
		this.remark6 = remark6;
		this.remark7 = remark7;
		this.remark8 = remark8;
		this.remark9 = remark9;
		this.remark10 = remark10;
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
	public String toString() {
		return "WfBusinessData [appId=" + appId + ", moduleId=" + moduleId
				+ ", moduleName=" + moduleName + ", remark1=" + remark1
				+ ", remark2=" + remark2 + ", remark3=" + remark3
				+ ", remark4=" + remark4 + ", remark5=" + remark5
				+ ", remark6=" + remark6 + ", remark7=" + remark7
				+ ", remark8=" + remark8 + ", remark9=" + remark9
				+ ", remark10=" + remark10 + "]";
	}

	public String getStringValue() {
		return appId + "," + moduleId + "," + moduleName + "," + remark1 + ","
				+ remark2 + "," + remark3 + "," + remark4 + "," + remark5 + ","
				+ remark6 + "," + remark7 + "," + remark8 + "," + remark9 + ","
				+ remark10;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public Object getBusinessExtend() {
		return businessExtend;
	}

	public void setBusinessExtend(Object businessExtend) {
		this.businessExtend = businessExtend;
	}
}
