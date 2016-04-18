package com.chinacreator.c2.workflow.api;


import java.util.List;

import com.chinacreator.c2.flow.detail.WfModuleBean;
import com.chinacreator.c2.flow.detail.WfProcessDefinition;

public interface WfExtendService {
	
	/**
	 * 获取事项菜单
	 * @param moduleId 
	 *               事项ID
	 * @param isRecursive
	 *               是否递归
	 * @return List<WfModuleBean>子事项树列表
	 */
	public List<WfModuleBean> queryChildren(String moduleId, boolean isRecursive);
	
	/**
	 * 事项是否存在子菜单
	 * @param moduleId 
	 *               事项ID
	 * @return 存在：true 不存在：false
	 */
	public boolean existsChildren(String moduleId);
	
	/**
	 * 事项是否存在流程子菜单
	 * @param moduleId 
	 *               事项ID
	 * @return 存在：true 不存在：false
	 */
	public boolean existsWfChildren(String moduleId);

	/**
	 * 通过事项菜单编码查询菜单对象
	 * @param menuCode 事项菜单编码
	 * @return 事项菜单对象
	 */
	public WfModuleBean queryByMenuCode(String menuCode);
	
	
	/**
	 * 通过事项菜单获取最新的工作流定义
	 * @param menuCode
	 * @return 流程定义
	 */
	public WfProcessDefinition getProcessDefinitionByMenuCode(String menuCode) throws Exception;

}
