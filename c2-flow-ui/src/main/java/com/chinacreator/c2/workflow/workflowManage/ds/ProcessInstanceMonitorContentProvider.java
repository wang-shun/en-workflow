package com.chinacreator.c2.workflow.workflowManage.ds;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.chinacreator.asp.comp.sys.advanced.job.dto.JobDTO;
import com.chinacreator.asp.comp.sys.advanced.job.service.JobService;
import com.chinacreator.asp.comp.sys.advanced.user.service.UserService;
import com.chinacreator.asp.comp.sys.core.user.dto.UserDTO;
import com.chinacreator.c2.dao.mybatis.enhance.Page;
import com.chinacreator.c2.dao.mybatis.enhance.Pageable;
import com.chinacreator.c2.flow.WfApiFactory;
import com.chinacreator.c2.flow.api.GroupType;
import com.chinacreator.c2.flow.api.WfHistoryService;
import com.chinacreator.c2.flow.api.WfRuntimeService;
import com.chinacreator.c2.flow.detail.ChooseGroup;
import com.chinacreator.c2.flow.detail.WfHistoricTask;
import com.chinacreator.c2.flow.detail.WfHistoricTaskParam;
import com.chinacreator.c2.flow.detail.WfIdentityLink;
import com.chinacreator.c2.flow.detail.WfPageList;
import com.chinacreator.c2.flow.util.CommonUtil;
import com.chinacreator.c2.flow.util.DateUtil;
import com.chinacreator.c2.flow.util.WfUtils;
import com.chinacreator.c2.ioc.ApplicationContextManager;
import com.chinacreator.c2.web.ds.ArrayContentProvider;
import com.chinacreator.c2.web.ds.ArrayContext;

public class ProcessInstanceMonitorContentProvider implements ArrayContentProvider{

	private WfHistoryService wfHistoryService = WfApiFactory.getWfHistoryService();
	private WfRuntimeService wfRuntimeService = WfApiFactory.getWfRuntimeService();
	
	@Override
	public Page<Map<String, Object>> getElements(ArrayContext context) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(new Pageable(), new ArrayList<Map<String, Object>>());
		
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>(); 
		Map<String, Object> map = context.getCondition();
		if(null!=map && !map.isEmpty()){
			String processInstanceId = (String)map.get("processInstanceId");
			String taskDefinitionKey = (String)map.get("taskDefinitionKey");
			
			if(!CommonUtil.stringNullOrEmpty(processInstanceId)){
				WfHistoricTaskParam params = new WfHistoricTaskParam();
				int firstResult = context.getPageable().getOffset();
				int maxResults = context.getPageable().getPageSize();
				int pageIndex = context.getPageable().getPageIndex();
				
				params.setProcessInstanceId(processInstanceId);
				if(!CommonUtil.stringNullOrEmpty(taskDefinitionKey)){
					params.setTaskDefinitionKey(taskDefinitionKey);
				}
				params.setOrderByHistoricTaskInstanceStartTime(true);
				params.setOrder(WfHistoricTaskParam.SORT_ASC);
				params.setStart(firstResult);
				params.setSize(maxResults);
				params.setPaged(true);
				int total = 0;
				try {
					WfPageList<WfHistoricTask, WfHistoricTaskParam> wfHistoricTaskPageList = wfHistoryService.queryHistoricTasks(params);
					total = (int)wfHistoricTaskPageList.getWfQuery().getTotal();
					mapList = formList(wfHistoricTaskPageList);
				} catch (Exception e) {
					e.printStackTrace();
				}
				page = new Page<Map<String,Object>>(pageIndex, maxResults, total, mapList);
			}
			
		}
		
		return page;
	
	}

	private List<Map<String, Object>> formList(
			WfPageList<WfHistoricTask, WfHistoricTaskParam> wfHistoricTaskPageList) throws Exception{
		
		UserService userService = ApplicationContextManager.getContext().getBean(UserService.class);
		JobService jobService = ApplicationContextManager.getContext().getBean(JobService.class);
		
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		if(null!=wfHistoricTaskPageList && !wfHistoricTaskPageList.getDatas().isEmpty()){
			for(WfHistoricTask wfHistoricTask:wfHistoricTaskPageList.getDatas()){
				if(wfHistoricTask!=null){
					Map<String, Object> mapItem = new HashMap<String, Object>();
					mapItem.put("id", wfHistoricTask.getId());
					mapItem.put("taskDefinitionKey", wfHistoricTask.getTaskDefinitionKey());
					mapItem.put("name", wfHistoricTask.getName());
					mapItem.put("startTime", DateUtil.dateToStr(
							wfHistoricTask.getStartTime(),
							DateUtil.YYYY_MM_DD_HH_MM_SS));
					mapItem.put("dueDate", DateUtil.dateToStr(
							wfHistoricTask.getDueDate(),
							DateUtil.YYYY_MM_DD_HH_MM_SS));
					mapItem.put("endTime", DateUtil.dateToStr(
							wfHistoricTask.getEndTime(),
							DateUtil.YYYY_MM_DD_HH_MM_SS));
					if (wfHistoricTask.getDueDate() != null
							&& new Date().compareTo(wfHistoricTask.getDueDate()) >= 0) {
						mapItem.put("overDue", "<font color='red'>已超期</font>");
					}
					
					String description = wfHistoricTask.getDescription();//.getDeleteReason();
					String deleteReason=wfHistoricTask.getDeleteReason();
					String assignee = "";
					if (wfHistoricTask.getEndTime() != null) {
						mapItem.put("status", "已完成");
						assignee = wfHistoricTask.getAssignee();
						if (assignee == null) {
							assignee = "";
							List<WfIdentityLink> idLinks = wfHistoryService
									.getHistoricTaskCandidates(wfHistoricTask
											.getId());
							if (idLinks != null) {
								Set<String> set = new HashSet<String>();
								for (WfIdentityLink idLink : idLinks) {
									set.add(idLink.getUserId());
								}
								for (String userId : set) {
									try {
										UserDTO userDto=userService.queryByPK(userId);
										assignee += userDto.getUserRealname() + ",";
									} catch (Exception e) {
										assignee += userId + ",";
									}
								}
								if (assignee.length() > 0)
									assignee = assignee.substring(0,
											assignee.length() - 1);
							}
						} else {
							try {
								UserDTO userDto=userService.queryByPK(assignee);
								assignee = userDto.getUserRealname();
							} catch (Exception e) {

							}
						}
					} else {
						mapItem.put("status", "待处理");
						List<WfIdentityLink> idLinks = wfRuntimeService.getTaskCandidates(wfHistoricTask.getId());
								
						boolean isSuspend = wfRuntimeService.getTaskById(wfHistoricTask.getId()).isSuspended();
						if (isSuspend)
							description = description == null ? "挂起"
									: description;
						Set<String> userSet = new HashSet<String>();
						Set<String> groupSet = new HashSet<String>();
						String assigneeUserItem = "";
						String assigneeGroupItem = "";
						if (idLinks != null) {
							String assigneeInfo = "";
							for (WfIdentityLink idLink : idLinks) {
								if(!CommonUtil.stringNullOrEmpty(idLink.getUserId())){
									userSet.add(idLink.getUserId());
								}
								if(!CommonUtil.stringNullOrEmpty(idLink.getGroupId())){
									groupSet.add(idLink.getGroupId());
								}
							}
							
							for (String userId : userSet) {
								try {
									UserDTO userDto=userService.queryByPK(userId);
									if(null!=userDto&&!CommonUtil.stringNullOrEmpty(userDto.getUserId())){
										assigneeUserItem += userDto.getUserRealname() + ",";
									}else{
										assigneeUserItem += userId + ",";
									}
								} catch (Exception e) {
									assigneeUserItem += userId + ",";
								}
							}
							if (assigneeUserItem.length() > 0){
								assigneeUserItem = assigneeUserItem.substring(0, assigneeUserItem.length() - 1);
								assigneeInfo += "用户【"+assigneeUserItem+"】";
							}
							
							for (String groupId : groupSet) {
								try {
									String gid=WfUtils.parseToGroupId(groupId);
									String groupPrex=WfUtils.parseToGroupTypePrex(groupId);
									GroupType groupType=WfUtils.getGroupTypeByPrex(groupPrex);
									if(null==groupType){
										JobDTO jobDTO=jobService.queryByPK(gid);
										assigneeGroupItem += jobDTO.getJobName()+ ",";
									}else{
										ChooseGroup candidateGroup=groupType.getGroup(gid);
										assigneeGroupItem += candidateGroup.getDisplayName()+ ",";
									}
								} catch (Exception e) {
									assigneeGroupItem += groupId + ",";
								}
							}
							if (assigneeGroupItem.length() > 0){
								assigneeGroupItem = assigneeGroupItem.substring(0, assigneeGroupItem.length() - 1);
								assigneeInfo += "用户组【"+assigneeGroupItem+"】";
							}
							
							assignee = assigneeInfo;
							
						}
					}
					mapItem.put("assignee", assignee);
					mapItem.put("description", description);
					mapItem.put("deletereason", deleteReason);
					mapList.add(mapItem);
				}
			}
		}
		return mapList;
	}



	
}
