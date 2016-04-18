package com.chinacreator.c2.workflow.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.chinacreator.asp.comp.sys.basic.menu.dto.MenuDTO;
import com.chinacreator.asp.comp.sys.basic.menu.service.MenuService;
import com.chinacreator.c2.flow.WfApiFactory;
import com.chinacreator.c2.flow.api.WfManagerService;
import com.chinacreator.c2.flow.detail.WfModuleBean;
import com.chinacreator.c2.flow.detail.WfProcessDefinition;
import com.chinacreator.c2.workflow.api.WfExtendService;

public class WfExtendServiceImpl implements WfExtendService {

	public static final String MODULE_MENU_CODE = "moduleConfig";

	@Autowired
	private MenuService menuService;
	
	private WfManagerService wfManagerService = WfApiFactory.getWfManagerService();

	@Override
	public List<WfModuleBean> queryChildren(String moduleId, boolean isRecursive) {
		List<WfModuleBean> list = new ArrayList<WfModuleBean>();

		MenuDTO menuDto = null;

		String menuId = "";

		if (null == moduleId || "".equals(moduleId.trim())) {
			menuDto = menuService.queryByMenuCode(MODULE_MENU_CODE);
			menuId = menuDto.getMenuId();
		} else {
			menuId = moduleId;
		}
		
		List<MenuDTO> menuDTOList = menuService.queryChildren(menuId, false);

		if (null != menuDTOList && !menuDTOList.isEmpty()) {
			for (MenuDTO menuDTO : menuDTOList) {
				if (null != menuDTO) {
					String type = menuDTO.getType();
					String href = menuDTO.getHref();
					WfModuleBean wmBean = new WfModuleBean();
					wmBean.setId(menuDTO.getMenuId());
					wmBean.setName(menuDTO.getMenuName());
					wmBean.setParentId(menuDTO.getParentId());
					if(("4".equals(type) && href == null) || ("4".equals(type) && href != null && href.contains("wf/taskHandle"))){
						list.add(wmBean);
					}
				}
			}
		}

		return list;
	}

	@Override
	public boolean existsWfChildren(String moduleId) {
		MenuDTO mdto = menuService.queryByPK(moduleId);
		String href0 = mdto.getHref();
		if("4".equals(mdto.getType()) && href0 != null && href0.contains("wf/taskHandle")){
			return true;
		}
		List<MenuDTO> menuDTOList = menuService.queryChildren(moduleId, false);

		if (null != menuDTOList && !menuDTOList.isEmpty()) {
			for (MenuDTO menuDTO : menuDTOList) {
				if (null != menuDTO) {
					String type = menuDTO.getType();
					String href = menuDTO.getHref();
					WfModuleBean wmBean = new WfModuleBean();
					String mId = menuDTO.getMenuId();
					wmBean.setId(mId);
					wmBean.setName(menuDTO.getMenuName());
					wmBean.setParentId(menuDTO.getParentId());
					if(("4".equals(type) && href != null && href.contains("wf/taskHandle"))){
						return true;
					}else if(("4".equals(type) && href == null)){
						boolean ret = existsWfChildren(mId);
						if(ret){
							return ret;
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public WfModuleBean queryByMenuCode(String menuCode) {
		MenuDTO menuDTO = menuService.queryByMenuCode(menuCode);
		WfModuleBean vmb = null;
		if (null != menuDTO) {
			vmb = new WfModuleBean();
			vmb.setId(menuDTO.getMenuId());
			vmb.setName(menuDTO.getMenuName());
			vmb.setParentId(menuDTO.getParentId());
		}
		return vmb;
	}
	
	
	@Override
	public WfProcessDefinition getProcessDefinitionByMenuCode(String menuCode) throws Exception{
		WfModuleBean wfModuleBean=this.queryByMenuCode(menuCode);
		if(null==wfModuleBean) return null;
		return wfManagerService.getBindProcessByModuleId(wfModuleBean.getId());
	}
	
	
	@Override
	public boolean existsChildren(String moduleId) {
		return menuService.existsChildMenus(moduleId);
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

}
