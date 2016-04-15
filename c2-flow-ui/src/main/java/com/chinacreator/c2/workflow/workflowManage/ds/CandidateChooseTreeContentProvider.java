package com.chinacreator.c2.workflow.workflowManage.ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chinacreator.c2.flow.api.GroupType;
import com.chinacreator.c2.flow.detail.ChooseGroup;
import com.chinacreator.c2.flow.detail.ChooseUser;
import com.chinacreator.c2.flow.util.CommonUtil;
import com.chinacreator.c2.ioc.ApplicationContextManager;
import com.chinacreator.c2.web.ds.TreeContentProvider;
import com.chinacreator.c2.web.ds.TreeContext;
import com.chinacreator.c2.web.ds.TreeNode;
import com.chinacreator.c2.web.ds.impl.DefaultTreeNode;
import com.chinacreator.c2.workflow.util.WorkflowUtils;

public class CandidateChooseTreeContentProvider implements TreeContentProvider{
	
	@Override
	public TreeNode[] getElements(TreeContext context) {
		
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		if (null != context) {
			Map<String, Object> map = context.getConditions();
			
			String id=(String) map.get("id");
			String gid = WorkflowUtils.parseToGroupId(id);
			String gType = WorkflowUtils.parseToGroupTypePrex(id);
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
			
			Map<String, GroupType> groupTypes = ApplicationContextManager.getContext().getBeansOfType(GroupType.class);
			
			//为空，查询所有组类型
			if(CommonUtil.stringNullOrEmpty(gType)&&CommonUtil.stringNullOrEmpty(gid)){
				try {
					
					TreeNode tnRoot = new DefaultTreeNode(null, "orgTree", "参与者树",true);
					((DefaultTreeNode)tnRoot).setChkDisabled(true);
					nodes.add(tnRoot);
					
					for(GroupType groupType:groupTypes.values()){
						TreeNode tnGroupType = new DefaultTreeNode("orgTree",groupType.getPrefix(),groupType.getGroupTypeDisplayName(),true);
						((DefaultTreeNode)tnGroupType).setChkDisabled(true);
						nodes.add(tnGroupType);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				
				GroupType groupType=WorkflowUtils.getGroupTypeByPrex(gType);
				if(null==groupType) throw new RuntimeException("找不到组类型["+gType+"]");
				
				if("".equals(gid)) gid=null;
				
				//查询当前组下的子组
				List<ChooseGroup>  groupList=groupType.getChildGroups(gid);
				for (ChooseGroup candidateGroup : groupList) {
					TreeNode tn = new DefaultTreeNode(null,groupType.getPrefix()+":"+candidateGroup.getId(),candidateGroup.getDisplayName(),true);
					if(selectedGroupIdList.contains(groupType.getPrefix()+":"+candidateGroup.getId())) ((DefaultTreeNode)tn).setChecked(true);
					nodes.add(tn);
				}
				
				//查询当前组下用户
				List<ChooseUser>  userList=groupType.parseUsers(gid);
				for (ChooseUser candidateUser : userList) {
					TreeNode tnUser = new DefaultTreeNode(null,candidateUser.getId(),candidateUser.getDisplayName(),false);
					if(selectedUserIdList.contains(candidateUser.getId())) ((DefaultTreeNode)tnUser).setChecked(true);
					nodes.add(tnUser);
				}
			}
			
		}
		return nodes.toArray(new TreeNode[nodes.size()]);
	}
	

}
