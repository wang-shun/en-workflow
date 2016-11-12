package com.chinacreator.c2.workflow.workflowManage.ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.flow.WfApiFactory;
import com.chinacreator.c2.flow.Exception.C2FlowRuntimeException;
import com.chinacreator.c2.flow.api.WfManagerService;
import com.chinacreator.c2.flow.detail.WfPageList;
import com.chinacreator.c2.flow.detail.WfProcessDefinition;
import com.chinacreator.c2.flow.detail.WfProcessDefinitionParam;
import com.chinacreator.c2.flow.util.CommonUtil;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;

@Service("processdeflist")
public class ProcessDefListContentProvider implements ArrayContentProvider{

	
	
	@Override
	public Page<WfProcessDefinition> getElements(ArrayContext context) {
		WfManagerService wfManagerService = WfApiFactory.getWfManagerService();
		Page<WfProcessDefinition> page = new Page<WfProcessDefinition>(new Pageable(), new ArrayList<WfProcessDefinition>());

		try {
			
			WfProcessDefinitionParam param = new WfProcessDefinitionParam();
			param.setSize(context.getPageable().getPageSize());
			param.setStart(context.getPageable().getOffset());
			Map<String, Object> map = context.getCondition();
			if(null!=map && !map.isEmpty()){
				String keyLike = (String)map.get("keyLike");
				if(null!=keyLike && !"".equals(keyLike.trim())){
					param.setKeyLike("%"+keyLike+"%");
				}
				String nameLike = (String)map.get("nameLike");
				if(null!=nameLike && !"".equals(nameLike.trim())){
					param.setNameLike("%"+nameLike+"%");
				}
				String key = (String)map.get("key");
				if(null!=key && !"".equals(key.trim())){
					param.setKey(key);
				}
				String id = (String)map.get("id");
				if(null!=id && !"".equals(id.trim())){
					param.setId(id);
				}
				
				//处理租户完全隔离
				String tenantId=WfApiFactory.getWfTenant();
				if(!CommonUtil.stringNullOrEmpty(tenantId)){
					param.setTenantId(tenantId);
				}else{
					param.setWithoutTenantId(true);
				}
				
				/*boolean isLatest = map.get("isLatest")==null?true:(Boolean)map.get("isLatest");
				if(isLatest){
					param.setLatest(true);
				}*/
			}
			
			WfPageList<WfProcessDefinition, WfProcessDefinitionParam> wfPageList = wfManagerService.queryProcessDefinitionsAndDeployInfoList(param);
			int pageindex = context.getPageable().getPageIndex();
			int size = context.getPageable().getPageSize();
			if(wfPageList != null){
				long total = wfPageList.getWfQuery().getTotal();
				List<WfProcessDefinition> list = wfPageList.getDatas(); 
				
	//			Iterator<WfProcessDefinition> itr = wfPageList.getDatas().iterator();
	//			while(itr.hasNext()){
	//				WfProcessDefinition wpd = itr.next();
	//				list.add(wpd);
	//			}
				page = new Page<WfProcessDefinition>(pageindex, size, new Long(total).intValue(), list);
			}else{
				page = new Page<WfProcessDefinition>(pageindex, size, new Long(0).intValue(), new ArrayList<WfProcessDefinition>());
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new C2FlowRuntimeException("获取流程定义列表数据异常，请检查服务是否可用！",e);
		}
		return page;
	}

}
