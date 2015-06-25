package com.chinacreator.c2.flow.detail;

import java.io.Serializable;
import java.util.Date;
/**
 * 流程日志明细数据
 * @author minghua.guo
 *
 */
public class WfOperateLogBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	/**
	 * 服务类名称
	 */
	private String className;
	/**
	 * 方法名称
	 */
	private String methodName;
	/**
	 * 方法描述
	 */
	private String methodAlias;
	/**
	 * 操作结果
	 */
	private String result;
	/**
	 * 调用时间
	 */
	private Date callTime;
	/**
	 * 消耗时间
	 */
	private long useTime;
	/**
	 * 应用id
	 */
	private String appId;
	/**
	 * 租户id
	 */
	private String tenantId; 
	/**
	 * 引擎名称
	 */
	private String engineName; 
	/**
	 * 操作用户id
	 */
	private String userId;
	/**
	 * 操作ip地址
	 */
	private String ip;

	/**
	 * 参数对象
	 */
	private Object[] argsObject;
	/**
	 * 参数值toString
	 */
	private String argsValue;
	/**
	 * 返回对象
	 */
	private Object returnObject;
	/**
	 * 返回值
	 */
	private String returnValue;
	/**
	 * 异常信息
	 */
	private String exception;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodAlias() {
		return methodAlias;
	}

	public void setMethodAlias(String methodAlias) {
		this.methodAlias = methodAlias;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getCallTime() {
		return callTime;
	}

	public void setCallTime(Date callTime) {
		this.callTime = callTime;
	}

	public long getUseTime() {
		return useTime;
	}

	public void setUseTime(long useTime) {
		this.useTime = useTime;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getEngineName() {
		return engineName;
	}

	public void setEngineName(String engineName) {
		this.engineName = engineName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Object[] getArgsObject() {
		return argsObject;
	}

	public void setArgsObject(Object[] argsObject) {
		this.argsObject = argsObject;
	}

	public String getArgsValue() {
		return argsValue;
	}

	public void setArgsValue(String argsValue) {
		this.argsValue = argsValue;
	}

	public Object getReturnObject() {
		return returnObject;
	}

	public void setReturnObject(Object returnObject) {
		this.returnObject = returnObject;
	}

	public String getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}
}
