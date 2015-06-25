package com.chinacreator.c2.workflow.log.ds;

import java.util.ArrayList;
import java.util.Map;

import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.flow.WfApiFactory;
import com.chinacreator.c2.flow.api.WfManagerService;
import com.chinacreator.c2.flow.detail.WfOperateLogBean;
import com.chinacreator.c2.flow.detail.WfPageList;
import com.chinacreator.c2.flow.detail.WfPageParam;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;

public class LogListContentProvider implements ArrayContentProvider {

	@Override
	public Page<WfOperateLogBean> getElements(ArrayContext context) {
		Page<WfOperateLogBean> result = null;
		WfManagerService wfManagerService = WfApiFactory.getWfManagerService();
		Map<String, Object> parameters = context.getCondition();
		int firstResult = context.getPageable().getOffset();
		int maxResults = context.getPageable().getPageSize();
		int pageIndex = context.getPageable().getPageIndex();
		try {
			WfPageList<WfOperateLogBean, WfPageParam> wfOperateLogs = wfManagerService
					.findWfOperateLog(parameters, firstResult, maxResults);
			if (wfOperateLogs != null && wfOperateLogs.getDatas().size() > 0) {
				result = new Page<WfOperateLogBean>(pageIndex, maxResults,
						(int) wfOperateLogs.getWfQuery().getTotal(),
						wfOperateLogs.getDatas()); 
			}else{
				result = new Page<WfOperateLogBean>(pageIndex, maxResults,
						0,
						new ArrayList<WfOperateLogBean>()); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
