package com.chinacreator.c2.flow.demo.leave.services;import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiTaskAlreadyClaimedException;
import org.springframework.util.Assert;

import com.chinacreator.asp.comp.sys.advanced.user.service.UserService;
import com.chinacreator.asp.comp.sys.basic.org.dto.OrgDTO;
import com.chinacreator.asp.comp.sys.core.user.dto.UserDTO;
import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.dao.Dao;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.flow.WfApiFactory;
import com.chinacreator.c2.flow.api.WfHistoryService;
import com.chinacreator.c2.flow.api.WfManagerService;
import com.chinacreator.c2.flow.api.WfRuntimeService;
import com.chinacreator.c2.flow.demo.leave.AskLeave;
import com.chinacreator.c2.flow.detail.WfBusinessData;
import com.chinacreator.c2.flow.detail.WfHistoricProcessInstance;
import com.chinacreator.c2.flow.detail.WfHistoricProcessInstanceParam;
import com.chinacreator.c2.flow.detail.WfModuleBean;
import com.chinacreator.c2.flow.detail.WfOperator;
import com.chinacreator.c2.flow.detail.WfPageList;
import com.chinacreator.c2.flow.detail.WfProcessDefinition;
import com.chinacreator.c2.flow.detail.WfProcessInstance;
import com.chinacreator.c2.flow.detail.WfResult;
import com.chinacreator.c2.flow.detail.WfTask;
import com.chinacreator.c2.flow.detail.WfTaskAction;
import com.chinacreator.c2.ioc.ApplicationContextManager;
import com.chinacreator.c2.workflow.api.WfExtendService;

public class AskLeaveService {
	
	/**
	 * 获取当前登陆用户信息
	 * @return
	 */
	public Map<String,Object> getCurrentUserInfo(){
		Map<String,Object> userMap=new HashMap<String, Object>();
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		UserService userService = ApplicationContextManager.getContext().getBean(UserService.class);
		UserDTO userDto=userService.queryByPK(context.getUser().getId());
		userMap.put("userName",userDto.getUserName());
		userMap.put("userRealName",userDto.getUserRealname());
		List<OrgDTO> orgDtoList=userService.queryOrgs(context.getUser().getId());
		
		if(null!=orgDtoList&&orgDtoList.size()>0){
			userMap.put("orgName",orgDtoList.get(0).getOrgName());
		}else{
			userMap.put("orgName","");
		}
		
		return userMap;
	}
	
	/**
	 * 添加请假条
	 * @param askLeave
	 */
	public void addAskLeave(AskLeave askLeave){
		Assert.hasText(askLeave.getAskReasons(),"请假原因不能为空！");
		Assert.notNull(askLeave.getAskStartTime(),"请假开始时间不能为空！");
		Assert.notNull(askLeave.getAskEndTime(),"请假结束时间不能为空！");
		Assert.hasText(askLeave.getAskType(),"请假类型不能为空！");
		Assert.notNull(askLeave.getAskDays(),"请假天数不能为空！");
		
		askLeave.setCreateTime(new Timestamp(System.currentTimeMillis()));
		WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		askLeave.setAskUserId(context.getUser().getId());
		askLeave.setStatus(0);
		Dao<AskLeave> dao=DaoFactory.create(AskLeave.class);
		dao.insert(askLeave);
	}
	
	
	/**
	 * 添加请假条启动工作流
	 * @param askLeave             请假信息
	 * @param moduleKey            事项key
	 * @return
	 */
	public AskLeave addAskLeaveAndStartFlow(AskLeave askLeave,String moduleKey){
		
		Assert.hasText(askLeave.getAskReasons(),"请假原因不能为空！");
		Assert.notNull(askLeave.getAskStartTime(),"请假开始时间不能为空！");
		Assert.notNull(askLeave.getAskEndTime(),"请假结束时间不能为空！");
		Assert.hasText(askLeave.getAskType(),"请假类型不能为空！");
		Assert.notNull(askLeave.getAskDays(),"请假天数不能为空！");
		Assert.hasText(moduleKey,"moduleKey参数为空，无法启动工作流！");
		
		try{
			
			WfRuntimeService wfRuntimeService = WfApiFactory.getWfRuntimeService();
			
			askLeave.setCreateTime(new Timestamp(System.currentTimeMillis()));
			WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
			askLeave.setAskUserId(context.getUser().getId());
			askLeave.setStatus(0);
			
			//获取流程事项菜单信息
			WfExtendService wfExtendService = (WfExtendService) ApplicationContextManager.getContext().getBean(WfExtendService.class);
			WfModuleBean wfModuleBean = wfExtendService.queryByMenuCode(moduleKey);
			if(null==wfModuleBean)  throw new RuntimeException("流程启动失败，请配置流程事项菜单！");
			
			WfManagerService wfManagerService=WfApiFactory.getWfManagerService();
			//获取当前事项绑定的最新流程定义
			WfProcessDefinition  wfProcessDefinition=wfManagerService.getBindProcessByModuleId(wfModuleBean.getId());
			if(null==wfProcessDefinition)  throw new RuntimeException("流程启动失败，请关联流程事项菜单和流程定义及相关配置！");
			
			//保存请假条
			Dao<AskLeave> dao=DaoFactory.create(AskLeave.class);
			dao.insert(askLeave);
			
			WfOperator wf=new WfOperator(context.getUser().getId(),context.getUser().getName(),context.getUser().getName(),null,null);
			wf.setTenantId(WfApiFactory.getWfTenant());//支持分布式必填参数
			
			//流程和业务关联数据，会插入到待办表中，建议填写完整
			WfBusinessData bd=new WfBusinessData();
			bd.setModuleId(wfModuleBean.getId());     //必须填，否则待办关联不到表单配置
			bd.setModuleName(wfModuleBean.getName()); //建议填写，待办能看到模块名称
			bd.setBusinessKey(askLeave.getId());      //建议填写,通过待办方便关联业务数据
			wf.setBusinessData(bd);
			
			Map<String,Object> variables=new HashMap<String, Object>();
			variables.put("assignTo", context.getUser().getId());
			wfRuntimeService.startProcessInstanceById(wf,askLeave.getId(),wfProcessDefinition.getId(),variables);
			
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("请假申批出现异常",e);
		}
		
		return askLeave;
		
	}
	
	
	/**
	 * 提交审批
	 * @param businessKey
	 * @param taskId     任务id
	 * @param moduleKey  事项key,必传，否则找不到相应的模块
	 */
	public void askLeaveFlowSubmit(String businessKey,String taskId,String moduleKey){
		
		try{
			
			WfRuntimeService wfRuntimeService = WfApiFactory.getWfRuntimeService();
			
			WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
			WfOperator wf=new WfOperator(context.getUser().getId(),context.getUser().getName(),context.getUser().getName(),null,null);
			wf.setTenantId(WfApiFactory.getWfTenant());
			
			//获取流程启动的必填参数“事项ID”
			WfExtendService wfExtendService = (WfExtendService) ApplicationContextManager.getContext().getBean(WfExtendService.class);
			WfModuleBean wfModuleBean = wfExtendService.queryByMenuCode(moduleKey);
			if(null==wfModuleBean)  throw new RuntimeException("流程操作失败，请配置流程菜单事项！");
			
			//流程和业务关联数据，会插入到待办表中，建议填写完整
			WfBusinessData bd=new WfBusinessData();
			bd.setModuleId(wfModuleBean.getId());      //必须填，否则待办关联不到表单配置
			bd.setModuleName(wfModuleBean.getName());  //建议填写，待办能看到模块名称
			bd.setBusinessKey(businessKey);            //建议填写,通过待办方便关联业务数据
			wf.setBusinessData(bd);
			
			Dao<AskLeave> dao=DaoFactory.create(AskLeave.class);
			AskLeave askLeave=dao.selectByID(businessKey);
			
			Map<String,Object> variables=new HashMap<String, Object>();
			variables.put("assignTo",askLeave.getAssignTo());
			
			variables.put("askLeave",askLeave);
			wfRuntimeService.operateTask(wf,taskId, WfTaskAction.CLAIM_COMPLETE,null, variables);
			
			//修改审批中状态
			askLeave.setStatus(1);
			dao.update(askLeave);
			
		}catch(ActivitiTaskAlreadyClaimedException fe){
			throw new RuntimeException("任务已签收，需要签收人才能处理！",fe);
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("流程任务处理异常",e);
		}

	}
	
	
	/**
	 * 删除请假条
	 * @param id
	 */
	public void deleteAskLeave(String id){
		Dao<AskLeave> dao=DaoFactory.create(AskLeave.class);
		AskLeave ask=new AskLeave();
		ask.setId(id);
		dao.delete(ask);
	}
	
	
	/**
	 * 修改请假条
	 * @param id
	 */
	public void updateAskLeave(AskLeave askLeave){
		Assert.hasText(askLeave.getId(),"id参数不能为空！");
		Dao<AskLeave> dao=DaoFactory.create(AskLeave.class);
		AskLeave dbAsk=dao.selectByID(askLeave.getId());
		dbAsk.setAskStartTime(askLeave.getAskStartTime());
		dbAsk.setAskEndTime(askLeave.getAskEndTime());
		dbAsk.setAskType(askLeave.getAskType());
		dbAsk.setAskReasons(askLeave.getAskReasons());
		dao.update(dbAsk);
	}
	
	
	/**
	 * 获取请假条详细
	 * @param id
	 * @return
	 */
	public Map<String,Object> getAskLeave(String id){
		Map<String,Object> leaveMap=new HashMap<String, Object>();
		Dao<AskLeave> dao=DaoFactory.create(AskLeave.class);
		AskLeave askLeave=dao.selectByID(id);
		leaveMap.put("askDays",askLeave.getAskDays());
		leaveMap.put("askStartTime",askLeave.getAskStartTime());
		leaveMap.put("askEndTime",askLeave.getAskEndTime());
		leaveMap.put("askReasons",askLeave.getAskReasons());
		leaveMap.put("askType",askLeave.getAskType());
		
		UserService userService = ApplicationContextManager.getContext().getBean(UserService.class);
		UserDTO userDto=userService.queryByPK(askLeave.getAskUserId());
		leaveMap.put("askUserName",userDto.getUserRealname());
		
		UserDTO userAssginTo=userService.queryByPK(askLeave.getAssignTo());
		leaveMap.put("assignToUserName",userAssginTo.getUserRealname());
		
		//获取流程相关信息
		if(askLeave.getStatus()>=0){
			WfHistoricProcessInstance wfProcessInstance=this.getFlowInstanceByBussinessKey(id);
			if(null!=wfProcessInstance){
				leaveMap.put("processInstanceId", wfProcessInstance.getId());
				leaveMap.put("processDefinitionId", wfProcessInstance.getProcessDefinitionId());
			}
		}
		return leaveMap;
	}
	
	
	/**
	 * 申请申批，启动流程
	 * @param askLeaveId
	 * @param moduleKey
	 */
	public void applyAskLeave(String askLeaveId,String moduleKey){
		
		try{
			
			//获取流程启动的必填参数“事项ID”
			WfExtendService wfExtendService = (WfExtendService) ApplicationContextManager.getContext().getBean(WfExtendService.class);
			WfModuleBean wfModuleBean = wfExtendService.queryByMenuCode(moduleKey);
			
			if(null==wfModuleBean)  throw new RuntimeException("流程启动失败，请配置流程菜单事项！");
			
			WfManagerService wfManagerService=WfApiFactory.getWfManagerService();
			WfProcessDefinition  wfProcessDefinition=wfManagerService.getBindProcessByModuleId(wfModuleBean.getId());
			if(null==wfProcessDefinition)  throw new RuntimeException("流程启动失败，请绑定流程菜单事项和流程定义及相关配置！");
			
			Dao<AskLeave> dao=DaoFactory.create(AskLeave.class);
			AskLeave askLeave=dao.selectByID(askLeaveId);
			
			WfRuntimeService wfRuntimeService = WfApiFactory.getWfRuntimeService();
			WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
			WfOperator wf=new WfOperator(context.getUser().getId(),context.getUser().getName(),context.getUser().getName(),null,null);
			wf.setTenantId(WfApiFactory.getWfTenant());
			
			//流程和业务关联数据
			WfBusinessData bd=new WfBusinessData();
			bd.setModuleId(wfModuleBean.getId());
			bd.setModuleName(wfModuleBean.getName());
			bd.setBusinessKey(askLeaveId);
			wf.setBusinessData(bd);
			
			Map<String,Object> variables=new HashMap<String, Object>();
			
			WfResult wfResult=wfRuntimeService.startProcessInstanceByKey(wf,askLeaveId,wfProcessDefinition.getKey(),variables);
			
			wfResult=wfRuntimeService.operateTask(wf,wfResult.getNextTaskId(),WfTaskAction.CLAIM_COMPLETE,null,variables);

			variables.put("assignTo",askLeave.getAssignTo());
			wfResult=wfRuntimeService.operateTask(wf,wfResult.getNextTaskId(),WfTaskAction.CLAIM_COMPLETE,null,variables);
			
			askLeave.setStatus(1);
			dao.update(askLeave);
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("请假申批出现异常",e);
		}

	}
	
	
	/**
	 * 根据业务ID获取关联的流程实现
	 * @param askLeaveId
	 * @return
	 */
	public WfHistoricProcessInstance getFlowInstanceByBussinessKey(String askLeaveId){
		
		try{
			
			WfHistoryService wfHistoryService = WfApiFactory.getWfHistoryService();
			WfHistoricProcessInstanceParam wfProcessInstanceParam=new WfHistoricProcessInstanceParam();
			wfProcessInstanceParam.setBusinessKey(askLeaveId);
			wfProcessInstanceParam.setTenantId(WfApiFactory.getWfTenant());
			if(null!=WfApiFactory.getWfTenant()){
				wfProcessInstanceParam.setWithoutTenantId(false);
			}
			
			WfPageList<WfHistoricProcessInstance, WfHistoricProcessInstanceParam> wfList=wfHistoryService.queryHistoricProcessInstances(wfProcessInstanceParam);
			if(null==wfList||wfList.getDatas().size()<=0) throw new RuntimeException("找不到业务数据["+askLeaveId+"]关联的流程实例");
			return wfList.getDatas().get(0);
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("获取流程实现信息异常",e);
		}

	}
	
	
	/**
	 * 通用审批处理
	 * @param businessKey 业务id
	 * @param taskId      当前任务id
	 * @param businessKey 表单业务id
	 * @param isPass      是否审批通过
	 * @param comment     退回原因
	 */
	public void commonReply(String businessKey,String taskId,String moduleId,Boolean isPass,String comment){
		
		try{
			
			WfRuntimeService wfRuntimeService = WfApiFactory.getWfRuntimeService();
			
			WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
			WfOperator wf=new WfOperator(context.getUser().getId(),context.getUser().getName(),context.getUser().getName(),null,null);
			wf.setTenantId(WfApiFactory.getWfTenant());
			
			//流程和业务关联数据
			WfBusinessData bd=new WfBusinessData();
			bd.setModuleId(moduleId);
			wf.setBusinessData(bd);
			
			Map<String,Object> variables=new HashMap<String, Object>();
			
			Dao<AskLeave> dao=DaoFactory.create(AskLeave.class);
			AskLeave askLeave=dao.selectByID(businessKey);
			variables.put("askLeave",askLeave);
			WfResult wfResult=null;
			if(isPass){
				wfResult=wfRuntimeService.operateTask(wf,taskId, WfTaskAction.CLAIM_COMPLETE,null, variables);
				
				WfProcessInstance wfProcessInstance=wfRuntimeService.getProcessInstanceById(wfResult.getProcessInstanceId());
				//流程结束
				if(null==wfProcessInstance){
					askLeave.setStatus(100);
					dao.update(askLeave);
				}
			}else{
				wfResult=wfRuntimeService.reject(wf, taskId, comment,variables);
				
				//流程不通过
				askLeave.setStatus(101);
				dao.update(askLeave);
			}
			
//			WfHistoryService wfHistoryService=WfApiFactory.getWfHistoryService();
//			WfRepositoryService  repositoryService=WfApiFactory.getWfRepositoryService();
			
//			WfHistoricTask wfHistoricTask=wfHistoryService.getHistoricTaskById(wfResult.getNextTaskId());
//			List<WfActivity> wfActivityList=repositoryService.getActivitiesByDefinition(wfHistoricTask.getProcessDefinitionId());
//			for (WfActivity wfActivity : wfActivityList) {
//				if(wfHistoricTask.getTaskDefinitionKey().equals(wfActivity.getId())){
//					String type = (String) wfActivity.getProperties().get(WfConstants.ACTIVITY_PROPERTY_KEY_TYPE);
//					//流程结束了
//					if ("endEvent".equals(type)) {
//						
//					}
//				}
//			}

			
		}catch(ActivitiTaskAlreadyClaimedException fe){
			throw new RuntimeException("任务已签收，需要签收人才能处理！",fe);
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("流程任务处理异常",e);
		}

	}
	
	
	
	
	/**
	 * 最后环节审批处理
	 * @param taskId      当前任务id
	 * @param businessKey 表单业务id
	 * @param isPass      是否审批通过
	 * @param comment     退回原因
	 */
	public void doneReply(String taskId,String moduleId,Boolean isPass,String comment){
		
		try{
			
			WfRuntimeService wfRuntimeService = WfApiFactory.getWfRuntimeService();
			
			WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
			WfOperator wf=new WfOperator(context.getUser().getId(),context.getUser().getName(),context.getUser().getName(),null,null);
			wf.setTenantId(WfApiFactory.getWfTenant());
			
			//流程和业务关联数据
			WfBusinessData bd=new WfBusinessData();
			bd.setModuleId(moduleId);
			wf.setBusinessData(bd);
			
			Map<String,Object> variables=new HashMap<String, Object>();
			
			if(isPass){
			
				//根据taskId找业务id，这里其实可以从前台传过来
			    WfTask wfTask=wfRuntimeService.getTaskById(taskId);
			    WfProcessInstance wfProcessInstance=wfRuntimeService.getProcessInstanceById(wfTask.getProcessInstanceId());
			    
			    wfRuntimeService.operateTask(wf,taskId, WfTaskAction.CLAIM_COMPLETE,null, variables);
			    
			    //改变业务状态
				Dao<AskLeave> dao=DaoFactory.create(AskLeave.class);
				AskLeave askLeave=dao.selectByID(wfProcessInstance.getBusinessKey());
				askLeave.setStatus(100);
				dao.update(askLeave);
			}else{
				wfRuntimeService.reject(wf, taskId, comment,variables);
			}
		}catch(ActivitiTaskAlreadyClaimedException fe){
			throw new RuntimeException("任务已签收，需要签收人才能处理！",fe);
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("流程任务处理异常",e);
		}

	}
	
	
}
