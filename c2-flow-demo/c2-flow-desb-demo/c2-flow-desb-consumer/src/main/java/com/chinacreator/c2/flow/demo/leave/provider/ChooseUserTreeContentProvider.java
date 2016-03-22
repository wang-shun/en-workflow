package com.chinacreator.c2.flow.demo.leave.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chinacreator.asp.comp.sys.advanced.org.service.OrgService;
import com.chinacreator.asp.comp.sys.basic.org.dto.OrgDTO;
import com.chinacreator.asp.comp.sys.core.user.dto.UserDTO;
import com.chinacreator.asp.sysmgmt.common.CommonTreeNode;
import com.chinacreator.asp.sysmgmt.sysset.jobmenumgt.JobMenuMgtMessages;
import com.chinacreator.c2.ioc.ApplicationContextManager;
import com.chinacreator.c2.web.ds.TreeContentProvider;
import com.chinacreator.c2.web.ds.TreeContext;
import com.chinacreator.c2.web.ds.TreeNode;

public class ChooseUserTreeContentProvider implements TreeContentProvider{
	
	@Override
	public TreeNode[] getElements(TreeContext context) {
		
		List<CommonTreeNode> list = new ArrayList<CommonTreeNode>();
		
		if (null != context) {
			Map<String, Object> map = context.getConditions();
			String orgId = (String) map.get("id");
			
			String selectedUserIds = (String) map.get("selectUserId");
			
			OrgService orgService = ApplicationContextManager.getContext().getBean(OrgService.class);
			
			if (null == orgId || orgId.trim().equals("")) {
				CommonTreeNode rootOrgTreeNode = new CommonTreeNode();
				rootOrgTreeNode.setId("0");
				rootOrgTreeNode.setName(JobMenuMgtMessages.getString("JOBMENUMGT.ORG_ROOT_TREENAME"));
				rootOrgTreeNode.setPid(null);
				rootOrgTreeNode.setNocheck(true);
				rootOrgTreeNode.setChkDisabled(true);
				rootOrgTreeNode.setParent(true);
				list.add(rootOrgTreeNode);
				
				List<OrgDTO> orgDTOs = orgService.queryChildOrgs("0",false);
				for (OrgDTO orgDTO : orgDTOs) {
					CommonTreeNode orgTreeNode = new CommonTreeNode();
					orgTreeNode.setId(orgDTO.getOrgId());
					orgTreeNode.setName(orgDTO.getOrgShowName());
					orgTreeNode.setPid(orgDTO.getParentId());
					orgTreeNode.setNocheck(true);
					orgTreeNode.setChkDisabled(true);
					orgTreeNode.setParent(true);
					list.add(orgTreeNode);
				} 
				
			}else{ 
				
				//查询机构下所有用户
				List<UserDTO> userDtoList = orgService.queryUsers(orgId);
				for (UserDTO userDTO : userDtoList) {
					CommonTreeNode orgTreeNode = new CommonTreeNode();
					orgTreeNode.setId(userDTO.getUserId());
					orgTreeNode.setName(userDTO.getUserRealname());
					orgTreeNode.setPid(orgId);
					orgTreeNode.setParent(false);
					if(userDTO.getUserId().equals(selectedUserIds))  orgTreeNode.setChecked(true);
					list.add(orgTreeNode);
				}
				
				
				//查询子机构
				List<OrgDTO> orgDTOs = orgService.queryChildOrgs(orgId,false);
				for (OrgDTO orgDTO : orgDTOs) {
					CommonTreeNode orgTreeNode = new CommonTreeNode();
					orgTreeNode.setId(orgDTO.getOrgId());
					orgTreeNode.setName(orgDTO.getOrgShowName());
					orgTreeNode.setPid(orgDTO.getParentId());
					orgTreeNode.setNocheck(true);
					orgTreeNode.setParent(true);
					list.add(orgTreeNode);
				} 

			}

		}
		
		return list.toArray(new CommonTreeNode[list.size()]);
	}

}
