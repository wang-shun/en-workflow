package com.chinacreator.c2.flow.detail;

public class WfGroupParam extends WfPageParam{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 只返回指定id的群组。
	 */
	private String id;
	
	/**
	 * 只返回指定名称的群组。
	 */
	private String name;
	
	/**
	 * 只返回指定类型的群组。
	 */
	private String type;
	
	/**
	 * 只返回名称与指定值匹配的群组使用%作为通配符。
	 */
	private String nameLike;
	
	/**
	 * 只返回成员与指定用户相同的群组。
	 */
	private String member;
	
	/**
	 * 只返回成员作为指定id流程定义的潜在启动者的劝阻
	 */
	private String potentialStarter;

	/**
	 * 按组ID排序
	 */
	private Boolean orderById = false;
	
	/**
	 * 按组名称排序
	 */
	private Boolean orderByName = false;
	
	/**
	 * 按组类型排序
	 */
	private Boolean orderByType = false;
	
	public Boolean getOrderById() {
		return orderById;
	}

	public void setOrderById(Boolean orderById) {
		this.orderById = orderById;
	}

	public Boolean getOrderByName() {
		return orderByName;
	}

	public void setOrderByName(Boolean orderByName) {
		this.orderByName = orderByName;
	}

	public Boolean getOrderByType() {
		return orderByType;
	}

	public void setOrderByType(Boolean orderByType) {
		this.orderByType = orderByType;
	}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNameLike() {
		return nameLike;
	}

	public void setNameLike(String nameLike) {
		this.nameLike = nameLike;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public String getPotentialStarter() {
		return potentialStarter;
	}

	public void setPotentialStarter(String potentialStarter) {
		this.potentialStarter = potentialStarter;
	}
	
	
}
