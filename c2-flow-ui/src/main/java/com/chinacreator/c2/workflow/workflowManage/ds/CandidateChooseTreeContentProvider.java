package com.chinacreator.c2.workflow.workflowManage.ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chinacreator.c2.flow.WfApiFactory;
import com.chinacreator.c2.flow.api.WfManagerService;
import com.chinacreator.c2.flow.detail.WfGroup;
import com.chinacreator.c2.flow.detail.WfGroupParam;
import com.chinacreator.c2.flow.detail.WfPageList;
import com.chinacreator.c2.flow.detail.WfUser;
import com.chinacreator.c2.flow.detail.WfUserParam;
import com.chinacreator.c2.web.ds.TreeContentProvider;
import com.chinacreator.c2.web.ds.TreeContext;
import com.chinacreator.c2.web.ds.TreeNode;
import com.chinacreator.c2.web.ds.impl.DefaultTreeNode;

public class CandidateChooseTreeContentProvider implements TreeContentProvider{

	private WfManagerService wfManagerService = WfApiFactory.getWfManagerService();
	
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
			
			if(null == menuId || menuId.trim().equals("")){
				//查询所有的角色
				try {
					WfGroupParam params = new WfGroupParam();
					params.setPaged(false);
					params.setOrderByName(true);
					params.setOrder(WfGroupParam.SORT_ASC);
					WfPageList<WfGroup, WfGroupParam> list = wfManagerService.queryGroups(params);
					if(null!=list && !list.getDatas().isEmpty()){
						TreeNode tn = new DefaultTreeNode(null, "orgTree", "参与者树", true);
						nodes.add(tn);
						for(WfGroup wfGroup : list.getDatas()){
							TreeNode tnRole = new DefaultTreeNode("orgTree", wfGroup.getId(), wfGroup.getName(), true);
							if(selectedGroupIdList.contains(wfGroup.getId()))
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
					WfPageList<WfUser, WfUserParam> wfUserPageList = wfManagerService.queryUsers(params);
					if(null!=wfUserPageList && !wfUserPageList.getDatas().isEmpty()){
						for(WfUser wfUser : wfUserPageList.getDatas()){
							TreeNode tn = new DefaultTreeNode(null, wfUser.getId(), wfUser.getLastName(), false);
							if(selectedUserIdList.contains(wfUser.getId()))
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
