package com.chinacreator.c2.flow.detail;

import java.io.Serializable;

/**
 * 资源对象
 * @author Administrator
 *
 */
public class WfResource implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 资源id
	 */
	protected String id;
	/**
	 * 资源名称
	 */
	protected String name;

	/**
	 * 二进制内容
	 */
	protected byte[] bytes;

	/**
	 * 部署id
	 */
	protected String deploymentId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	@Override
	public String toString() {
		return "WfResource [id=" + id + ", name=" + name + ", deploymentId="
				+ deploymentId + "]";
	}
}
