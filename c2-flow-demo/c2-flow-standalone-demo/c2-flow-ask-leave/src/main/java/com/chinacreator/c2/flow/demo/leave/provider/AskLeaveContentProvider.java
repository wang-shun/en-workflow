package com.chinacreator.c2.flow.demo.leave.provider;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.enhance.Order;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.dao.mybatis.enhance.RowBounds4Page;
import com.chinacreator.c2.dao.mybatis.enhance.Sortable;
import com.chinacreator.c2.flow.WfApiFactory;
import com.chinacreator.c2.flow.api.WfRuntimeService;
import com.chinacreator.c2.flow.demo.leave.AskLeave;
import com.chinacreator.c2.flow.detail.ChooseGroup;
import com.chinacreator.c2.flow.detail.WfModuleBean;
import com.chinacreator.c2.flow.detail.WfUniteTaskResult;
import com.chinacreator.c2.flow.util.CommonUtil;
import com.chinacreator.c2.flow.util.WfUtils;
import com.chinacreator.c2.ioc.ApplicationContextManager;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;
import com.chinacreator.c2.workflow.api.WfExtendService;

public class AskLeaveContentProvider implements
		ArrayContentProvider{
	
	
	@Override
	public Page<Object> getElements(ArrayContext arg0){
		
		String moduleKey=(String)arg0.getCondition().get("moduleKey");
	
		 Pageable pageable=arg0.getPageable();
		 
		try{
			
			if(CommonUtil.stringNullOrEmpty(moduleKey)) throw new RuntimeException("流程启动失败，流程菜单事项moduleKey不能为空！");
			
			WfExtendService wfExtendService = (WfExtendService) ApplicationContextManager.getContext().getBean(WfExtendService.class);
			WfModuleBean wfModuleBean = wfExtendService.queryByMenuCode(moduleKey);
			if(null==wfModuleBean)  throw new RuntimeException("流程启动失败，请配置流程菜单事项！");
			
	       
	        Dao<AskLeave> dao=DaoFactory.create(AskLeave.class);
	        Sortable sortable=new Sortable(new Order("createTime",Order.DIRECTION_DESC));
	        
	        RowBounds4Page rowBounds = new RowBounds4Page(((pageable == null ? 1 : pageable.getPageIndex()) - 1) * (pageable == null ? 15 : pageable.getPageSize()),(pageable == null ? 15 : pageable.getPageSize()),sortable,null,true);
	        List<Object> askLeaveList=dao.getSession().selectList("selectAskLeave",arg0.getCondition(),rowBounds);
	        
	        /**
	         * 关联出流程当前处理人
	         */
	        WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
	        WfRuntimeService wfRuntimeService= WfApiFactory.getWfRuntimeService();
	        
	        //获取当前用户工作流所有组,用于去引擎获取属于当前用户的组的待办
	        List<ChooseGroup> candidateGroupList=WfUtils.getGroupsByUserId(context.getUser().getId());
	        
	        //获取当前业务待办信息
	        for (Object object : askLeaveList) {
				Map<String,Object> askLeaveMap=(Map<String,Object>)object;
				String businesskey=(String)askLeaveMap.get("id");
		        Map<String, Object> params=new HashMap<String, Object>();
		        params.put("moduleid",wfModuleBean.getId());
		        params.put("businesskey", businesskey);
		        params.put("taskType", "todo");
		        
		        /**
		         * 调用接口查工作流待办
		         * 建议在for循环外层一次查出再关联，效率会更好
		         * 当然，如果项目永远不考虑引擎独立部署，那你可以使用业务表和工作流待办进行关联sql查询更灵活
		         */
		        WfUniteTaskResult wfUniteTaskResult = wfRuntimeService.queryWfUniteRunTask(context.getUser().getId(),candidateGroupList,params,0,1,null);
		        if(null!=wfUniteTaskResult&&wfUniteTaskResult.getDatas().size()>0){
		        	askLeaveMap.put("todo",wfUniteTaskResult.getDatas().get(0));
		        }
			}
	        return new Page<Object>(pageable.getPageIndex(), pageable.getPageSize(), rowBounds.getTotalSize(), askLeaveList);
	        
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return new Page<Object>(pageable.getPageIndex(), pageable.getPageSize(),0,Collections.emptyList());
	}
}
