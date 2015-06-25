package com.chinacreator.c2.flow.detail;

import java.io.Serializable;

/**
 * 流程用户对象
 * @author Administrator
 *
 */
public class WfUser implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	private String id;
	/**
	 * 用户名称
	 */
	private String firstName;
	/**
	 * 用户中文名
	 */
	private String lastName;
	/**
	 * 邮箱地址
	 */
	private String email;
	/**
	 * 密码
	 */
	private String password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
