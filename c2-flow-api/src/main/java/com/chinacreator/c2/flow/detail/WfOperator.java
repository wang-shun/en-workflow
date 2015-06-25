package com.chinacreator.c2.flow.detail;

import java.io.Serializable;

/**
 * 流程操作者信息，主要用于工作流引擎登录、记录日志、传递租户信息、统一任务业务数据等
 * 
 * @author minghua.guo
 * 
 */
public class WfOperator implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 用户id，必须参数
	 */
	private String userId;
	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 用户中文名
	 */
	private String userCName;
	/**
	 * 租户id
	 */
	private String tenantId;
	/**
	 * 用户ip地址
	 */
	private String ip;
	
	/**
	 * 统一任务业务数据
	 */
	private WfBusinessData businessData;
	
	public WfOperator(){
		
	}

	public WfOperator(String userId, String userName, String userCName,
			String ip, String tenantId) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userCName = userCName;
		this.tenantId = tenantId;
		this.ip = ip;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCName() {
		return userCName;
	}

	public void setUserCName(String userCName) {
		this.userCName = userCName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	
	@Override
	public String toString() {
		return "WfOperator [userId=" + userId + ", userName=" + userName
				+ ", userCName=" + userCName + ", tenantId=" + tenantId
				+ ", ip=" + ip + "]";
	}

	public String toFullString() {
		return "WfOperator ["+toString()+ ":" + businessData.toString() + "]";
	}

	public WfBusinessData getBusinessData() {
		if(businessData == null){
			businessData = new WfBusinessData();
		}
		return businessData;
	}

	public void setBusinessData(WfBusinessData businessData) {
		this.businessData = businessData;
	}
}
