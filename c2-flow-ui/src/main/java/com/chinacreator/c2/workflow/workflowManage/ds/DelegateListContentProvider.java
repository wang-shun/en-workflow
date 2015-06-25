package com.chinacreator.c2.workflow.workflowManage.ds;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.flow.WfApiFactory;
import com.chinacreator.c2.flow.api.WfManagerService;
import com.chinacreator.c2.flow.detail.WfModuleDelegate;
import com.chinacreator.c2.flow.detail.WfModuleDelegateInfo;
import com.chinacreator.c2.flow.detail.WfModuleDelegateParam;
import com.chinacreator.c2.flow.detail.WfPageList;
import com.chinacreator.c2.ioc.ApplicationContextManager;
import com.chinacreator.c2.sysmgr.AuthenticationProvider;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;

public class DelegateListContentProvider implements ArrayContentProvider{

	private WfManagerService wfManagerService = WfApiFactory.getWfManagerService();
	
	private AuthenticationProvider authenticationProvider = (AuthenticationProvider) ApplicationContextManager
			.getContext().getBean(AuthenticationProvider.class);
	
	@Override
	public Page<WfModuleDelegate> getElements(ArrayContext context) {
		Page<WfModuleDelegate> page = new Page<WfModuleDelegate>(new Pageable(), new ArrayList<WfModuleDelegate>());
		if(null!=context){
			Map<String, Object> map = context.getCondition();
			if(null!=map){
				WfModuleDelegateParam param = new WfModuleDelegateParam();
				// 通过标识区分是查询我委托出去的还是委托给我的  delegateFlag=1标识我委托出去的 delegateFlag=0标识委托给我的 
				String delegateFlag = (String)map.get("delegateFlag");
				// session中用户ID
				String userIdInSession = authenticationProvider.getSubject().getId();
				
				String designatorId = (String)map.get("designatorId");
				if("1".equals(delegateFlag)){
					designatorId = userIdInSession;
				}
				
				String designeeId = (String)map.get("designeeId"); 
				if("0".equals(delegateFlag)){
					designeeId = userIdInSession;
				}
				
				String designatorName = (String)map.get("designatorName");
				String designeeName = (String)map.get("designeeName");
				
				Date delegateStartTimeBegine = null;
				Date delegateStartTimeEnd = null;
				Date delegateEndTimeBegine = null;
				Date delegateEndTimeEnd = null;
				try {
					delegateStartTimeBegine = map.get("delegateStartTimeBegine")==null?null:new Date((Long)map.get("delegateStartTimeBegine"));//DateUtil.StrToDate((String)map.get("delegateStartTimeBegine"), DateUtil.YYYY_MM_DD_HH_MM_SS);
					
					delegateStartTimeEnd =  map.get("delegateStartTimeEnd")==null?null:new Date((Long)map.get("delegateStartTimeEnd"));//DateUtil.StrToDate((String)map.get("delegateStartTimeEnd"),DateUtil.YYYY_MM_DD_HH_MM_SS);
					delegateEndTimeBegine = map.get("delegateEndTimeBegine")==null?null:new Date((Long)map.get("delegateEndTimeBegine"));//DateUtil.StrToDate((String)map.get("delegateEndTimeBegine"),DateUtil.YYYY_MM_DD_HH_MM_SS);
					delegateEndTimeEnd = map.get("delegateEndTimeEnd")==null?null:new Date((Long)map.get("delegateEndTimeEnd"));//DateUtil.StrToDate((String)map.get("delegateEndTimeEnd"),DateUtil.YYYY_MM_DD_HH_MM_SS);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				param.setDesignatorId(designatorId);
				param.setDesignatorName(designatorName);
				param.setDesigneeId(designeeId);
				param.setDesigneeName(designeeName);
				
				param.setDelegateStartTimeBegine(delegateStartTimeBegine);
				param.setDelegateStartTimeEnd(delegateStartTimeEnd);
				param.setDelegateEndTimeBegine(delegateEndTimeBegine);
				param.setDelegateEndTimeEnd(delegateEndTimeEnd);
				WfPageList<WfModuleDelegate, WfModuleDelegateParam> pageList = null;
				try {
					pageList = wfManagerService.getModuleDelegateByParam(param);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Pageable pageable = context.getPageable();
				List<WfModuleDelegate> list = getList(pageList);
				// 查询委托到的事项信息表，为每行记录扩展事项信息
				int total = 0;
				if(null!=pageList && !pageList.getDatas().isEmpty()){
					total = (int)pageList.getWfQuery().getTotal();
				}
				page = new Page<WfModuleDelegate>(pageable.getPageIndex(), pageable.getPageSize(), total, list);
			}
		}
		
		return page;
	}

	private List<WfModuleDelegate> getList(
			WfPageList<WfModuleDelegate, WfModuleDelegateParam> pageList) {
		List<WfModuleDelegate> list = new ArrayList<WfModuleDelegate>();
		if(null!=pageList && !pageList.getDatas().isEmpty()){
			for(WfModuleDelegate wfModuleDelegate : pageList.getDatas()){
				if(null!=wfModuleDelegate){
					if(null!=wfModuleDelegate.getWfModuleDelegateInfos() && !wfModuleDelegate.getWfModuleDelegateInfos().isEmpty()){
//						wfModuleDelegate
						String moduleIds = "";
						String moduleNames = "";
						for(WfModuleDelegateInfo info : wfModuleDelegate.getWfModuleDelegateInfos()){
							moduleIds += info.getMouduleId()+",";
							moduleNames += info.getMouduleName()+",";
						}
						if(!"".equals(moduleIds)){
							moduleIds = moduleIds.substring(0, moduleIds.length()-1);
							moduleNames = moduleNames.substring(0, moduleNames.length()-1);
						}
						wfModuleDelegate.setModuleIds(moduleIds);
						wfModuleDelegate.setModuleNames(moduleNames);
					}
					list.add(wfModuleDelegate);
				}
			}
		}
		return list;
	}

}
