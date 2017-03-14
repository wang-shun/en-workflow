package com.chinacreator.c2.workflow.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.chinacreator.asp.comp.sys.advanced.job.service.JobService;
import com.chinacreator.asp.comp.sys.advanced.user.service.UserService;
import com.chinacreator.asp.comp.sys.core.user.dto.UserDTO;
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
import com.chinacreator.c2.web.controller.ResponseFactory;
import com.chinacreator.c2.workflow.service.OrgGroupType;

/**
 * 流程监控控制器
 * 
 * @author minghua.guo
 */
@Controller
@RequestMapping(value = "/workflow/monitor")
public class WfMonitorController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	private WfHistoryService wfHistoryService = WfApiFactory.getWfHistoryService();
	private WfRuntimeService wfRuntimeService = WfApiFactory.getWfRuntimeService();

	@RequestMapping(value = "/wfmonitor", method = RequestMethod.POST)
	public Object userProcessInstanceMonitor(
			@RequestParam(value = "processInstanceId", required = true) String processInstanceId,
			@RequestParam(value = "taskDefinitionKey", required = false) String taskDefinitionKey)
			throws Exception {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		if (!CommonUtil.stringNullOrEmpty(processInstanceId)) {
			WfHistoricTaskParam params = new WfHistoricTaskParam();

			params.setProcessInstanceId(processInstanceId);
			if (!CommonUtil.stringNullOrEmpty(taskDefinitionKey)) {
				params.setTaskDefinitionKey(taskDefinitionKey);
			}
			params.setOrderByHistoricTaskInstanceStartTime(true);
			params.setOrder(WfHistoricTaskParam.SORT_ASC);
			params.setPaged(false);
			WfPageList<WfHistoricTask, WfHistoricTaskParam> wfHistoricTaskPageList = wfHistoryService
					.queryHistoricTasks(params);
			mapList = formList(wfHistoricTaskPageList);
		}
		return new ResponseFactory().createResponseBodyJSONObject(mapList);
	}

	private List<Map<String, Object>> formList(
			WfPageList<WfHistoricTask, WfHistoricTaskParam> wfHistoricTaskPageList)
			throws Exception {
		
		JobService jobService = ApplicationContextManager.getContext().getBean(JobService.class);
		UserService userService = ApplicationContextManager.getContext().getBean(UserService.class);
		
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		if (null != wfHistoricTaskPageList && !wfHistoricTaskPageList.getDatas().isEmpty()) {
			for (WfHistoricTask wfHistoricTask : wfHistoricTaskPageList.getDatas()) {
				if (wfHistoricTask != null) {
					Map<String, Object> mapItem = new HashMap<String, Object>();
					mapItem.put("id", wfHistoricTask.getId());
					mapItem.put("taskDefinitionKey",
							wfHistoricTask.getTaskDefinitionKey());
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
							&& new Date()
									.compareTo(wfHistoricTask.getDueDate()) >= 0) {
						mapItem.put("overDue", "<font color='red'>已超期</font>");
					}

					String description = wfHistoricTask.getDescription();
					String deleteReason=wfHistoricTask.getDeleteReason();
					
					if (wfHistoricTask.getEndTime() != null) {
						mapItem.put("status", "已完成");
					}else{
						mapItem.put("status", "待处理");
						boolean isSuspend = wfRuntimeService.getTaskById(wfHistoricTask.getId()).isSuspended();
						if (isSuspend) description = description == null ? "挂起": description;
					}
					
					//签收人
					String assignee = wfHistoricTask.getAssignee();
					if(StringUtils.isEmpty(assignee)){
						assignee="";
					}else{
						UserDTO userDto=userService.queryByPK(assignee);
						if(null!=userDto&&!CommonUtil.stringNullOrEmpty(userDto.getUserRealname())){
							assignee = userDto.getUserRealname();
						}
					}
					
					//候选人
					String candidates="";
					List<WfIdentityLink> idLinks = wfHistoryService.getHistoricTaskCandidates(wfHistoricTask.getId());
					if (idLinks != null) {
						
						Set<String> userSet = new HashSet<String>();
						Set<String> groupSet = new HashSet<String>();
						String assigneeUserItem = "";
						String assigneeGroupItem = "";
						
						String assigneeInfo = "";
						for (WfIdentityLink idLink : idLinks) {
							
							if(idLink.getType()=="assignee") continue;
							
							if(!CommonUtil.stringNullOrEmpty(idLink.getUserId())){
								userSet.add(idLink.getUserId());
							}
							if(!CommonUtil.stringNullOrEmpty(idLink.getGroupId())){
								groupSet.add(idLink.getGroupId());
							}
						}
						
						for (String userId : userSet) {
							UserDTO userDto=userService.queryByPK(userId);
							if(null!=userDto&&!CommonUtil.stringNullOrEmpty(userDto.getUserRealname())){
								assigneeUserItem += userDto.getUserRealname() + ",";
							}else{
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
								if(null==groupType) groupType=WfUtils.getDefaultGroupType();  //获取系统默认组类型
								ChooseGroup candidateGroup=groupType.getGroup(gid);
								//依赖流程引擎待办，groupId 没有grouptype信息前缀  默认为岗位组 查不出来查 机构组
								if(candidateGroup == null){
									groupType = new OrgGroupType();
									candidateGroup = groupType.getGroup(gid);
								}
								assigneeGroupItem += candidateGroup.getDisplayName()+ ",";
							} catch (Exception e) {
								assigneeGroupItem += groupId + ",";
							}
						}
						if (assigneeGroupItem.length() > 0){
							assigneeGroupItem = assigneeGroupItem.substring(0, assigneeGroupItem.length() - 1);
							assigneeInfo += "用户组【"+assigneeGroupItem+"】";
						}
						
						candidates = assigneeInfo;
						
					}
					
					mapItem.put("candidate", candidates);
					mapItem.put("assignee", assignee);
					mapItem.put("description", description);
					mapItem.put("deleteReason", deleteReason);
					mapList.add(mapItem);
				}
			}
		}
		return mapList;
	}

}
