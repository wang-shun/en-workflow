package com.chinacreator.c2.workflow.workflowManage.ds;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.flow.WfApiFactory;
import com.chinacreator.c2.flow.api.WfRepositoryService;
import com.chinacreator.c2.flow.detail.WfDeployment;
import com.chinacreator.c2.flow.detail.WfPageList;
import com.chinacreator.c2.flow.detail.WfPageParam;
import com.chinacreator.c2.flow.detail.WfProcessDefinition;
import com.chinacreator.c2.flow.detail.WfProcessDefinitionParam;
import com.chinacreator.c2.flow.util.DateUtil;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;

public class ProcessHistoryListContentProvider implements ArrayContentProvider{
	
	private WfRepositoryService wfRepositoryService = WfApiFactory.getWfRepositoryService();

	@Override
	public Page<WfProcessDefinition> getElements(ArrayContext context) {
		Page<WfProcessDefinition> page = new Page<WfProcessDefinition>(new Pageable(), new ArrayList<WfProcessDefinition>());
		WfProcessDefinitionParam param = new WfProcessDefinitionParam();
		param.setPaged(true);
		param.setSize(context.getPageable().getPageSize());
		param.setStart((context.getPageable().getPageIndex()-1)*context.getPageable().getPageSize());
		param.setOrderByVersion(true);
		param.setOrder(WfPageParam.SORT_DESC);
		Map<String, Object> map = context.getCondition();
		if(null!=map && !map.isEmpty()){
			String key = (String)map.get("key");
			if(null!=key && !"".equals(key.trim())){
				param.setKey(key);
			}
		}
		try {
			WfPageList<WfProcessDefinition, WfProcessDefinitionParam> wfPageList = wfRepositoryService.queryProcessDefinitions(param);
			long size = wfPageList.getWfQuery().getSize();
			long total = wfPageList.getWfQuery().getTotal();
			long pageindex = context.getPageable().getPageIndex();
			Pageable pageable = new Pageable(new Long(pageindex).intValue(), new Long(size).intValue());
			List<WfProcessDefinition> list = new ArrayList<WfProcessDefinition>(); 
			
			Iterator<WfProcessDefinition> itr = wfPageList.getDatas().iterator();
			while(itr.hasNext()){
				WfProcessDefinition wpd = itr.next();
				Date deployTime = null;
				if(null!=wpd.getDeploymentId()&&!"".equals(wpd.getDeploymentId().trim())){
					WfDeployment wfd = wfRepositoryService.getDeploymentById(wpd.getDeploymentId());
					deployTime = wfd.getDeploymentTime();
					wpd.setDeployTime(DateUtil.longToDateString(deployTime, DateUtil.YYYY_MM_DD_HH_MM_SS));
				}
				
				list.add(wpd);
			}
			page = new Page<WfProcessDefinition>(pageable.getPageIndex(), pageable.getPageSize(), new Long(total).intValue(), list);//new Page<WfProcessDefinition>(pageable, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

}
