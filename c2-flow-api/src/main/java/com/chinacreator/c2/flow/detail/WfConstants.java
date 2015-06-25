package com.chinacreator.c2.flow.detail;

/**
 * 流程常量定义类
 * 
 * @author yicheng.yang
 * @version 1.0
 */
public class WfConstants {
	public static final String IDENTITYLINKTYPE_ASSIGNEE = "assignee";

	public static final String IDENTITYLINKTYPE_CANDIDATE = "candidate";

	public static final String IDENTITYLINKTYPE_OWNER = "owner";

	public static final String IDENTITYLINKTYPE_STARTER = "starter";

	public static final String IDENTITYLINKTYPE_PARTICIPANT = "participant";

	/**
	 * 根据classpath路径来部署服务器上的流程文件
	 */
	public static final String DEPLOYTYPE_CLASSPATH = "classpath";
	/**
	 * 根据文件内容来部署流程文件
	 */
	public static final String DEPLOYTYPE_TEXT = "text";
	
	/**
	 * 根据流来部署流程文件
	 */
	public static final String DEPLOYTYPE_INPUTSTREAM = "inputstream";

	/**
	 * 动态指定执行人map中key值
	 */
	public static final String WF_ASSIGNEE = "wfAssignee";
	
	/**
	 * 动态指定下个环节的截止时间map中key值
	 */
	public static final String WF_DUEDATE = "wfDueDate";

	/**
	 * 是否驳回（回退）标识
	 */
	public static final String WF_CONTROL_IS_REJECTED = "WF_CONTROL_IS_REJECTED";
	/**
	 * 是否自由流标识
	 */
	public static final String WF_CONTROL_IS_GOANYWHERE = "WF_CONTROL_IS_GOANYWHERE";
	/*
	 * ************************************************
	 * 流程控制接口调用的返回对象中result属性（执行是否成功）的定义*
	 * ************************************************
	 */
	/**
	 * 流程控制操作执行成功
	 */
	public static final String WF_CONTROL_EXE_SUCCESS = "200";
	/**
	 * 流程控制操作执行失败，参数不全
	 */
	public static final String WF_CONTROL_EXE_ERRORPARAM = "300";
	
	/**
	 * 流程操作失败
	 */
	public static final String WF_CONTROL_EXE_FAIL = "400";
	/**
	 * 流程控制操作对象不存在
	 */
	public static final String WF_CONTROL_EXE_NOOBJECT = "404";

	public static final String WF_CHARSET_UTF_8 = "UTF-8";

	public static final String WF_IDENTITYLINKTYPE_USERS = "users";

	public static final String WF_IDENTITYLINKTYPE_GROUPS = "groups";
	
	public static final String ACTIVITY_PROPERTY_KEY_TYPE = "type";
	public static final String ACTIVITY_TYPE_USERTASK = "userTask";
	
	/**
	 * 事件监听实体类型ExecutionEntity
	 */
	public static final String EVENT_ENTITY_EXECUTIONENTITY = "org.activiti.engine.impl.persistence.entity.ExecutionEntity";
	/**
	 * 事件监听实体类型TaskEntity
	 */
	public static final String EVENT_ENTITY_TASKENTITY = "org.activiti.engine.impl.persistence.entity.TaskEntity";
	/**
	 * 事件监听实体类型HistoricTaskInstanceEntity
	 */
	public static final String EVENT_ENTITY_HISTORICTASKINSTANCEENTITY = "org.activiti.engine.impl.persistence.entity.HistoricTaskInstanceEntity";
	/**
	 * 事件监听实体类型IdentityLinkEntity
	 */
	public static final String EVENT_ENTITY_IDENTITYLINKENTITY = "org.activiti.engine.impl.persistence.entity.IdentityLinkEntity";
	
	/**
	 * 统一任务状态信息，引擎挂起状态值
	 */
	public static final int WF_SUSPEND_INT = 2;
	/**
	 * 统一任务状态信息，待办
	 */
	public static final String WF_UNITE_TASK_TYPE_TODO = "todo";
	/**
	 * 统一任务状态信息，挂起
	 */
	public static final String WF_UNITE_TASK_TYPE_SUSPEND = "suspend";
	/**
	 * 统一任务状态信息，已办
	 */
	public static final String WF_UNITE_TASK_TYPE_DONE = "done";

	/**
	 * 统一任务业务数据Key，可以从任务的本地变量Map里面获取到
	 */
	public static final String WF_BUSINESS_DATA_KEY = "wf_business_data_key";
	
	public static final String JUMPREASON_REJECT = "reject";
	public static final String JUMPREASON_GOANYWHERE = "goAnywhere";
	
	/**
	 * 委托类型-上级委托
	 */
	public static final String WF_DELEGATE_TYPE_UP = "1";
	/**
	 * 委托类型-委托别人
	 */
	public static final String WF_DELEGATE_TYPE_OTHERS = "2";
	
	/**
	 * 委托范围-全部事项
	 */
	public static final String WF_DELEGATE_RANGE_ALL = "0";
	/**
	 * 委托范围-个别事项
	 */
	public static final String WF_DELEGATE_RANGE_SOME = "1";

	/**
	 * 委托状态：1、等待确认；2、委托中；3、委托结束；4、拒绝委托
	 */
	public static final String WF_DELEGATE_STAT_WAITING = "1";
	public static final String WF_DELEGATE_STAT_DELEGATEING = "2";
	public static final String WF_DELEGATE_STAT_DELEGATE_FINISH = "3";
	public static final String WF_DELEGATE_STAT_DELEGATE_RESOLVED = "4";
	
	/**
	 * 期限单位：Y：年      M:月     D：日     h：时     m：分    s：秒
	 */
	public static final String WF_DURATION_UNIT_YEAR="Y";
	public static final String WF_DURATION_UNIT_MONTH="M";
	public static final String WF_DURATION_UNIT_DAY="D";
	public static final String WF_DURATION_UNIT_HOUR="h";
	public static final String WF_DURATION_UNIT_MINUTE="m";
	public static final String WF_DURATION_UNIT_SECOND="s";
	
	
}