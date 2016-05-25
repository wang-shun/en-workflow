package com.chinacreator.c2.flow.api;


/**
 * 用户组定义接口，用户组名称的格式${prefix}[:{detail}]
 * 例如：$org:1 组织类型组    $role:b 角色类型组
 * @author hushow
 */
public abstract class WfGroupType implements GroupType {
	
	private String groupTypeDisplayName;
	
	/**
	 * 设置分组的显示名
	 * @param groupTypeDisplayName
	 */
	public void setGroupTypeDisplayName(String groupTypeDisplayName) {
		this.groupTypeDisplayName = groupTypeDisplayName;
	}
	

	/**
	 * 获取分组的显示名称
	 * @param detail 详情表达式，可解析可不解析，随意
	 * @return 分组显示名称
	 */
	public String getGroupTypeDisplayName(){
		return this.groupTypeDisplayName;
	}
	
	

}
