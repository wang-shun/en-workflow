package com.chinacreator.c2.workflow.holidayworkday.ds;

import java.util.ArrayList;
import java.util.List;

import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.flow.WfApiFactory;
import com.chinacreator.c2.flow.api.WfManagerService;
import com.chinacreator.c2.flow.detail.WfPageList;
import com.chinacreator.c2.flow.detail.WfWorkDate;
import com.chinacreator.c2.flow.detail.WfWorkDateParam;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;

public class WorkDayListContentProvider implements ArrayContentProvider {

	@Override
	public Page<WfWorkDate> getElements(ArrayContext context) {
		WfManagerService wfManagerService = WfApiFactory.getWfManagerService();
		WfWorkDateParam wfWorkDateParam = new WfWorkDateParam();
		Page<WfWorkDate> page = new Page<WfWorkDate>(new Pageable(),
				new ArrayList<WfWorkDate>());
		wfWorkDateParam.setStart(context.getPageable().getOffset());
		wfWorkDateParam.setPaged(true);
		wfWorkDateParam.setSize(context.getPageable().getPageSize());
		try {
			WfPageList<WfWorkDate, WfWorkDateParam> wfPageList = wfManagerService
					.getWorkDateList(wfWorkDateParam);
			List<WfWorkDate> wfWorkDateList = null;
			if (wfPageList.getWfQuery().getTotal() > 0) {
				wfWorkDateList = wfPageList.getDatas();
			}
			page = new Page<WfWorkDate>(context.getPageable().getPageIndex(),
					context.getPageable().getPageSize(), (int) wfPageList
							.getWfQuery().getTotal(), wfWorkDateList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return page;
	}

}
