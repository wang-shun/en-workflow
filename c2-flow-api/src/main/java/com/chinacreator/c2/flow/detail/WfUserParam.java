package com.chinacreator.c2.flow.detail;

/**
 * 用户查询参数
 * @author Administrator
 *
 */
public class WfUserParam extends WfPageParam {

	private static final long serialVersionUID = 1L;

	/**
	 * 	只返回指定id的用户。
	 */
	private String id;
	/**
	 * 只返回指定firstname的用户。
	 */
	private String firstName;
	/**
	 * 只返回指定lastname的用户。
	 */
	private String lastName;
	/**
	 * 只返回指定email的用户。
	 */
	private String email;
	/**
	 * 只返回firstname与指定值匹配的用户。使用%通配符。
	 */
	private String firstNameLike;
	/**
	 * 只返回lastname与指定值匹配的用户。使用%通配符。
	 */
	private String lastNameLike;
	/**
	 * 只返回email与指定值匹配的用户。使用%通配符。
	 */
	private String emailLike;
	/**
	 * 只返回指定组成员的用户。
	 */
	private String memberOfGroup;
	/**
	 * 只返回指定流程定义id的默认启动人。
	 */
	private String potentialStarter;
	
	/**
	 * 按照用户邮箱排序
	 */
	private boolean orderByUserEmail;
	/**
	 * 按照用户firstname排序
	 */
	private boolean orderByUserFirstName;
	/**
	 * 按照用户ID排序
	 */
	private boolean orderByUserId;
	
	/**
	 * 按照用户lastname排序
	 */
	private boolean orderByUserLastName;
	
	public boolean isOrderByUserEmail() {
		return orderByUserEmail;
	}
	public void setOrderByUserEmail(boolean orderByUserEmail) {
		this.orderByUserEmail = orderByUserEmail;
	}
	public boolean isOrderByUserFirstName() {
		return orderByUserFirstName;
	}
	public void setOrderByUserFirstName(boolean orderByUserFirstName) {
		this.orderByUserFirstName = orderByUserFirstName;
	}
	public boolean isOrderByUserId() {
		return orderByUserId;
	}
	public void setOrderByUserId(boolean orderByUserId) {
		this.orderByUserId = orderByUserId;
	}
	public boolean isOrderByUserLastName() {
		return orderByUserLastName;
	}
	public void setOrderByUserLastName(boolean orderByUserLastName) {
		this.orderByUserLastName = orderByUserLastName;
	}
	
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
	public String getFirstNameLike() {
		return firstNameLike;
	}
	public void setFirstNameLike(String firstNameLike) {
		this.firstNameLike = firstNameLike;
	}
	public String getLastNameLike() {
		return lastNameLike;
	}
	public void setLastNameLike(String lastNameLike) {
		this.lastNameLike = lastNameLike;
	}
	public String getEmailLike() {
		return emailLike;
	}
	public void setEmailLike(String emailLike) {
		this.emailLike = emailLike;
	}
	public String getMemberOfGroup() {
		return memberOfGroup;
	}
	public void setMemberOfGroup(String memberOfGroup) {
		this.memberOfGroup = memberOfGroup;
	}
	public String getPotentialStarter() {
		return potentialStarter;
	}
	public void setPotentialStarter(String potentialStarter) {
		this.potentialStarter = potentialStarter;
	}
}
