package com.chinacreator.c2.workflow.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.chinacreator.asp.comp.sys.advanced.role.service.RoleService;
import com.chinacreator.asp.comp.sys.advanced.user.service.UserService;
import com.chinacreator.asp.comp.sys.core.role.dto.RoleDTO;
import com.chinacreator.asp.comp.sys.core.user.dto.UserDTO;
import com.chinacreator.c2.flow.api.WfGroupType;
import com.chinacreator.c2.flow.detail.ChooseGroup;
import com.chinacreator.c2.flow.detail.ChooseUser;
import com.chinacreator.c2.ioc.ApplicationContextManager;


/**
 * 岗位组实现
 * @author hushow
 *
 */

public class RoleGroupType extends WfGroupType {

	@Override
	public String getPrefix() {
		return "$role";
	}

	@Override
	public List<ChooseUser> parseUsers(String groupId) {
		
		List<ChooseUser> candidateUserList=new ArrayList<ChooseUser>();
		
		if(null==groupId||"".equals(groupId)) return candidateUserList;
		
		List<UserDTO> userDtoList =null;
		
		//获取普通用户岗位
		RoleService roleService = ApplicationContextManager.getContext().getBean(RoleService.class);
		userDtoList = roleService.queryUsers(groupId);
		
		//转换
		for (UserDTO userDTO : userDtoList) {
			ChooseUser candidateUser=new ChooseUser();
			candidateUser.setId(userDTO.getUserId());
			candidateUser.setName(userDTO.getUserName());
			candidateUser.setDisplayName(userDTO.getUserRealname());
			candidateUserList.add(candidateUser);
		}
		
		return candidateUserList;
	}

	
	@Override
	public List<ChooseGroup> getChildGroups(String parentId) {
		if(null==parentId||this.getPrefix().equals(parentId)){
			return this.getGroups();
		}else{
			return Collections.emptyList();
		}
	}
	
	@Override
	public boolean hasChildGroup(String groupId) {
		return false;
	}
	
	
	@Override
	public List<ChooseGroup> getGroups() {
		
		List<ChooseGroup> candidateGroupList=new ArrayList<ChooseGroup>();
		
		RoleService roleService = ApplicationContextManager.getContext().getBean(RoleService.class);
		List<RoleDTO> allRoleList=roleService.queryAll();
		
		for (RoleDTO roleDTO : allRoleList) {
			ChooseGroup candidateGroup=new ChooseGroup();
			candidateGroup.setId(roleDTO.getRoleId());
			candidateGroup.setName(roleDTO.getRoleName());
			candidateGroup.setDisplayName(roleDTO.getRoleName());
			candidateGroup.setType(this.getPrefix());
			candidateGroupList.add(candidateGroup);
		}
		return candidateGroupList;
	}

	@Override
	public ChooseGroup getGroup(String groupId) {
		RoleService roleService = ApplicationContextManager.getContext().getBean(RoleService.class);
		RoleDTO roleDTO=roleService.queryByPK(groupId);
		if(null==roleDTO) return null;
		ChooseGroup candidateGroup=new ChooseGroup();
		candidateGroup.setId(roleDTO.getRoleId());
		candidateGroup.setName(roleDTO.getRoleName());
		candidateGroup.setDisplayName(roleDTO.getRoleName());
		candidateGroup.setType(this.getPrefix());
		return candidateGroup;
	}
	
	@Override
	public List<ChooseGroup> getGroupsByUserKey(String userId) {
		
		List<ChooseGroup> candidateGroupList=new ArrayList<ChooseGroup>();
		//获取普通用户岗位
		UserService userService = ApplicationContextManager.getContext().getBean(UserService.class);
		List<RoleDTO> roleList = userService.queryRoles(userId);
		for (RoleDTO roleDTO : roleList) {
			ChooseGroup candidateGroup=new ChooseGroup();
			candidateGroup.setId(roleDTO.getRoleId());
			candidateGroup.setName(roleDTO.getRoleName());
			candidateGroup.setDisplayName(roleDTO.getRoleName());
			candidateGroup.setType(this.getPrefix());
			candidateGroupList.add(candidateGroup);
		}
		return candidateGroupList;
	}
}
