package com.chinacreator.c2.workflow.service;

import java.util.ArrayList;
import java.util.List;

import com.chinacreator.asp.comp.sys.advanced.org.service.OrgService;
import com.chinacreator.asp.comp.sys.advanced.user.service.UserService;
import com.chinacreator.asp.comp.sys.basic.org.dto.OrgDTO;
import com.chinacreator.asp.comp.sys.core.user.dto.UserDTO;
import com.chinacreator.c2.flow.api.WfGroupType;
import com.chinacreator.c2.flow.detail.ChooseGroup;
import com.chinacreator.c2.flow.detail.ChooseUser;
import com.chinacreator.c2.ioc.ApplicationContextManager;


/**
 * 机构组实现
 * @author hushow
 *
 */
public class OrgGroupType extends WfGroupType {

	@Override
	public String getPrefix() {
		return "$org";
	}

	@Override
	public List<ChooseUser> parseUsers(String groupId) {
		
		List<ChooseUser> candidateUserList=new ArrayList<ChooseUser>();
		
		if(null==groupId||"".equals(groupId)) return candidateUserList;

		OrgService orgService = ApplicationContextManager.getContext().getBean(OrgService.class);
		
		//获取部门下用户
		List<UserDTO> userDtoList = orgService.queryUsers(groupId);
		
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
		
		List<ChooseGroup> candidateUserList=new ArrayList<ChooseGroup>();
		
		String orgId=parentId;
		
		if(null==parentId||this.getPrefix().equals(parentId)){
			orgId="0";
		}
		
		OrgService orgService = ApplicationContextManager.getContext().getBean(OrgService.class);
		
		List<OrgDTO> orgDTOs = orgService.queryChildOrgs(orgId,false);
		for (OrgDTO orgDTO : orgDTOs) {
			ChooseGroup chooseGroup=new ChooseGroup();
			chooseGroup.setId(orgDTO.getOrgId());
			chooseGroup.setName(orgDTO.getOrgName());
			chooseGroup.setDisplayName(orgDTO.getOrgShowName());
			chooseGroup.setType(this.getPrefix());
			candidateUserList.add(chooseGroup);
		}
		
		return candidateUserList;
	}
	
	
	@Override
	public boolean hasChildGroup(String groupId){
		
		OrgService orgService = ApplicationContextManager.getContext().getBean(OrgService.class);
		
		return orgService.existsChildOrgs(groupId);
	}
	
	
	@Override
	public List<ChooseGroup> getGroups() {
		
		List<ChooseGroup> candidateGroupList=new ArrayList<ChooseGroup>();
		
		OrgService orgService = ApplicationContextManager.getContext().getBean(OrgService.class);
		
		List<OrgDTO> allOrgList=orgService.queryAll();
		
		for (OrgDTO orgDTO : allOrgList) {
			ChooseGroup candidateGroup=new ChooseGroup();
			candidateGroup.setId(orgDTO.getOrgId());
			candidateGroup.setName(orgDTO.getOrgName());
			candidateGroup.setDisplayName(orgDTO.getOrgShowName());
			candidateGroup.setType(this.getPrefix());
			candidateGroupList.add(candidateGroup);
		}
		return candidateGroupList;
	}

	
	@Override
	public ChooseGroup getGroup(String groupId) {
		
		OrgService orgService = ApplicationContextManager.getContext().getBean(OrgService.class);
		
		OrgDTO orgDTO=orgService.queryByPK(groupId);
		if(null==orgDTO) return null;
		ChooseGroup candidateGroup=new ChooseGroup();
		candidateGroup.setId(orgDTO.getOrgId());
		candidateGroup.setName(orgDTO.getOrgName());
		candidateGroup.setDisplayName(orgDTO.getOrgShowName());
		candidateGroup.setType(this.getPrefix());
		return candidateGroup;
	}
	
	@Override
	public List<ChooseGroup> getGroupsByUserKey(String userId) {
		
		List<ChooseGroup> candidateGroupList=new ArrayList<ChooseGroup>();
		
		UserService userService = ApplicationContextManager.getContext().getBean(UserService.class);
		
		List<OrgDTO> orgDTOList=userService.queryOrgs(userId);
		for (OrgDTO orgDTO : orgDTOList) {
			ChooseGroup candidateGroup=new ChooseGroup();
			candidateGroup.setId(orgDTO.getOrgId());
			candidateGroup.setName(orgDTO.getOrgName());
			candidateGroup.setDisplayName(orgDTO.getOrgShowName());
			candidateGroup.setType(this.getPrefix());
			candidateGroupList.add(candidateGroup);
		}
		return candidateGroupList;
	}
}
