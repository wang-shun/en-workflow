package com.chinacreator.c2.workflow.workflowManage.ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chinacreator.asp.comp.sys.advanced.job.dto.JobDTO;
import com.chinacreator.asp.comp.sys.advanced.job.service.JobService;
import com.chinacreator.asp.comp.sys.advanced.user.service.UserService;
import com.chinacreator.asp.comp.sys.common.CommonPropertiesUtil;
import com.chinacreator.asp.comp.sys.core.user.dto.UserDTO;
import com.chinacreator.c2.flow.detail.WfUserParam;
import com.chinacreator.c2.ioc.ApplicationContextManager;
import com.chinacreator.c2.web.ds.TreeContentProvider;
import com.chinacreator.c2.web.ds.TreeContext;
import com.chinacreator.c2.web.ds.TreeNode;
import com.chinacreator.c2.web.ds.impl.DefaultTreeNode;

public class CandidateChooseTreeContentProvider implements TreeContentProvider{
	
	@Override
	public TreeNode[] getElements(TreeContext context) {
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		if (null != context) {
			Map<String, Object> map = context.getConditions();
			String menuId = (String) map.get("id");
			String selectedUserIds = (String) map.get("selectedUserIds");
			String selectedGroupIds = (String) map.get("selectedGroupIds");
			List<String> selectedUserIdList = new ArrayList<String>();
			List<String> selectedGroupIdList = new ArrayList<String>();
			if(selectedUserIds != null){
				String[] userIds = selectedUserIds.split(",");
				for(String userId : userIds){
					selectedUserIdList.add(userId);
				}
			}
			if(selectedGroupIds != null){
				String[] groupIds = selectedGroupIds.split(",");
				for(String groupId : groupIds){
					selectedGroupIdList.add(groupId);
				}
			}
			
			JobService jobService = ApplicationContextManager.getContext().getBean(JobService.class);
			
			if(null == menuId || menuId.trim().equals("")){
				//查询所有的角色
				try {
					
					//考虑到分布式环境组织机构可能不统一情况，外围配置处理人从本地获取(流程管理所在应用)
					List<JobDTO> allJobList=jobService.queryAll();
					
					if(null!=allJobList && !allJobList.isEmpty()){
						TreeNode tn = new DefaultTreeNode(null, "orgTree", "参与者树", true);
						((DefaultTreeNode)tn).setChkDisabled(true);
						nodes.add(tn);
						for(JobDTO jobDTO : allJobList){
							TreeNode tnRole = new DefaultTreeNode("orgTree", jobDTO.getJobId(),jobDTO.getJobName(),true);
							if(selectedGroupIdList.contains(jobDTO.getJobId()))
								((DefaultTreeNode)tnRole).setChecked(true);
							nodes.add(tnRole);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				
				//查询角色下的用户
				// menuId传过来是角色ID，查询角色下的用户即可
				WfUserParam params = new WfUserParam();
				params.setMemberOfGroup(menuId);
				params.setOrderByUserFirstName(true);
				params.setOrder(WfUserParam.SORT_ASC);
				try {
					
					UserService userService = ApplicationContextManager.getContext().getBean(UserService.class);
					List<UserDTO> userDtoList =null;
					
					//获取普通用户岗位
					if(menuId.equals(CommonPropertiesUtil.getRoleofeveryoneJobId())){
						userDtoList=userService.queryAll();
					}else{
						userDtoList = jobService.queryUsers(menuId);
					}
					
					if(null!=userDtoList && !userDtoList.isEmpty()){
						for(UserDTO userDTO : userDtoList){
							TreeNode tn = new DefaultTreeNode(null, userDTO.getUserId(), userDTO.getUserRealname(),false);
							if(selectedUserIdList.contains(userDTO.getUserId()))
								((DefaultTreeNode)tn).setChecked(true);
							nodes.add(tn);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		return nodes.toArray(new TreeNode[nodes.size()]);
	}

}
