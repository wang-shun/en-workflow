package com.chinacreator.c2.workflow.workflowManage.ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinacreator.c2.flow.detail.WfModuleBean;
import com.chinacreator.c2.web.ds.TreeContentProvider;
import com.chinacreator.c2.web.ds.TreeContext;
import com.chinacreator.c2.web.ds.TreeNode;
import com.chinacreator.c2.web.ds.impl.DefaultTreeNode;
import com.chinacreator.c2.workflow.api.WfExtendService;

@Service("moduletreecontent")
public class ProcessModuleBindTreeContentProvider implements
		TreeContentProvider {
	@Autowired
	private WfExtendService wfExtendService;
//	private WfExtendService wfExtendService = (WfExtendService) ApplicationContextManager
//			.getContext().getBean(WfExtendService.class);

	@Override
	public TreeNode[] getElements(TreeContext context) {
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		if (null != context) {
			Map<String, Object> map = context.getConditions();
			String menuId = (String) map.get("id");

			if (null == menuId || menuId.trim().equals("")) {
//				WfModuleBean wfModuleBean = wfExtendService
//						.queryByMenuCode(WfExtendServiceImpl.MODULE_MENU_CODE);
//				TreeNode tn = new DefaultTreeNode(null, wfModuleBean.getId(),
//						wfModuleBean.getName(), true);
//				nodes.add(tn);
//				menuId = wfModuleBean.getId();
				menuId = "0";
			}

			// 查询子菜单
			List<WfModuleBean> wfModuleBeans = wfExtendService.queryChildren(
					menuId, false);
			if (null != wfModuleBeans && !wfModuleBeans.isEmpty()) {
				for (WfModuleBean menu : wfModuleBeans) {
					boolean hasWfChildren = wfExtendService.existsWfChildren(menu.getId());
					if(hasWfChildren){
						boolean isParent = wfExtendService.existsChildren(menu.getId());
						TreeNode tn = new DefaultTreeNode(menu.getParentId(),
								menu.getId(), menu.getName(),isParent);
						if(isParent){
							((DefaultTreeNode)tn).setChkDisabled(true);
						}
						nodes.add(tn);
					}
				}
			}
		}

		return nodes.toArray(new TreeNode[nodes.size()]);
	}

}
