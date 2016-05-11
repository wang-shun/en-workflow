package com.chinacreator.c2.workflow.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.chinacreator.asp.comp.sys.advanced.job.dto.JobDTO;
import com.chinacreator.asp.comp.sys.advanced.job.service.JobService;
import com.chinacreator.asp.comp.sys.advanced.user.service.UserService;
import com.chinacreator.asp.comp.sys.common.CommonPropertiesUtil;
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
public class JobGroupType extends WfGroupType {

	@Override
	public String getPrefix() {
		return "$job";
	}

	@Override
	public List<ChooseUser> parseUsers(String groupId) {
		
		List<ChooseUser> candidateUserList=new ArrayList<ChooseUser>();
		
		if(null==groupId||"".equals(groupId)) return candidateUserList;
		
		
		List<UserDTO> userDtoList =null;
		
		//获取普通用户岗位
		if(groupId.equals(CommonPropertiesUtil.getRoleofeveryoneJobId())){
			UserService userService = ApplicationContextManager.getContext().getBean(UserService.class);
			userDtoList=userService.queryAll();
		}else{
			JobService jobService = ApplicationContextManager.getContext().getBean(JobService.class);
			userDtoList = jobService.queryUsers(groupId);
		}
		
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
		
		JobService jobService = ApplicationContextManager.getContext().getBean(JobService.class);
		List<JobDTO> allJobList=jobService.queryAll();
		
		for (JobDTO jobDTO : allJobList) {
			ChooseGroup candidateGroup=new ChooseGroup();
			candidateGroup.setId(jobDTO.getJobId());
			candidateGroup.setName(jobDTO.getJobName());
			candidateGroup.setDisplayName(jobDTO.getJobName());
			candidateGroup.setType(this.getPrefix());
			candidateGroupList.add(candidateGroup);
		}
		return candidateGroupList;
	}

	@Override
	public ChooseGroup getGroup(String groupId) {
		JobService jobService = ApplicationContextManager.getContext().getBean(JobService.class);
		JobDTO jobDto=jobService.queryByPK(groupId);
		if(null==jobDto) return null;
		ChooseGroup candidateGroup=new ChooseGroup();
		candidateGroup.setId(jobDto.getJobId());
		candidateGroup.setName(jobDto.getJobName());
		candidateGroup.setDisplayName(jobDto.getJobName());
		candidateGroup.setType(this.getPrefix());
		return candidateGroup;
	}
	
	
	@Override
	public List<ChooseGroup> getGroupsByUserKey(String userId) {
		List<ChooseGroup> candidateGroupList=new ArrayList<ChooseGroup>();
		
		JobService jobService = ApplicationContextManager.getContext().getBean(JobService.class);
		List<JobDTO> jobDtoList=jobService.queryByUser(null,userId);
		for (JobDTO jobDTO : jobDtoList) {
			ChooseGroup candidateGroup=new ChooseGroup();
			candidateGroup.setId(jobDTO.getJobId());
			candidateGroup.setName(jobDTO.getJobName());
			candidateGroup.setDisplayName(jobDTO.getJobName());
			candidateGroup.setType(this.getPrefix());
			candidateGroupList.add(candidateGroup);
		}
		return candidateGroupList;
	}
}
