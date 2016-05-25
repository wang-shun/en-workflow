package com.chinacreator.c2.flow.api;

import java.util.List;

import com.chinacreator.c2.flow.detail.ChooseGroup;
import com.chinacreator.c2.flow.detail.ChooseUser;

/**
 * 用户组定义接口，用户组名称的格式${prefix}[:{detail}]
 * 例如：$org:1 组织类型组    $role:b 角色类型组
 * @author hushow
 */
public interface GroupType {
	
	/**
	 * 用户组名称前缀，不要带$和:
	 * @return 用户组前缀
	 */
	public String getPrefix();
	
	
	/**
	 * 获取分组的显示名称
	 * @param detail 详情表达式，可解析可不解析，随意
	 * @return 分组显示名称
	 */
	public String getGroupTypeDisplayName();
	
	
	/**
	 * 获取该当前类型组下所有用户
	 * @param detail 用户组详情表达式，只有Definition知道具体的意义
	 * @return  组内所有用户的id
	 */
	public  List<ChooseUser> parseUsers(String groupId);
	

	/**
	 * 获取当前类型的所有组[有层级结构]
	 * @return
	 */
	public List<ChooseGroup> getChildGroups(String groupId);
	
	
	/**
	 * 判断是否有下级组
	 * @param groupId
	 * @return
	 */
	public boolean hasChildGroup(String groupId);

	
	
	/**
	 * 获取当前类型下的所有组
	 * @return
	 */
	public List<ChooseGroup> getGroups();
	
	
	/**
	 * 获取组信息
	 * @param groupId
	 * @return
	 */
	public ChooseGroup getGroup(String groupId);
	
	/**
	 * 获取当前类型下用户所属组
	 * @return
	 */
	public List<ChooseGroup> getGroupsByUserKey(String userId);
}
