# c2工作流升级日志

## v4.1.6(2016-05-31)

1. 新 功 能：  分布式下引擎jar启动改为web方式启动，流程管理支持分布式。
2. 新 功 能：  工作流所有api调用时的当前操作人可以是非流程引擎中的用户。
3. 新 功 能：  工作流任务处理列表和流程跟踪中的处理人和组显示为中文。
4. 新 功 能：  任务处理人为多组或多用户签收时，多条待办合并显示，多候选人逗号分隔显示【项目自定义的待办sql需要注意此场景】。
5. 新 功 能：  待办和已办查询接口增加排序参数。
6. 新 功 能：  工作流外围配置选择参与者树，支持机构用户和角色，支持自定义配置扩展。
7. 新 功 能：  工作流通用入口控制器支持非Show-In-UI-View表单打开【url中不带#号方式打开】。
8. 新 功 有：  流程跟踪列表和环节点击浮动跟踪信息的处理人和候选人分开显示，便于跟踪。
9. 修复BUG：驳回接口当有多个历史来源时无法哪里来的驳回哪去，而是随机驳回。
10. 修复BUG：流程外围配置优化和配置错误时友好提示。
11. 修复BUG：工作流已办任务列表，已完成流程，点击操作报空指针错误。
12. 修复BUG：当历史环节未配置处理人时，退回到这一步报错。
13. 修复BUG：创建流程定义key为5656时报is not a valid value for 'NCName'. 错误。
14. 修复BUG：流程事项配置不选择节点保存时，报英文异常。
15. 修复BUG：分布式和本地场景候选组的待办查不到bug处理。
16. 修复BUG：处理人为多组或多用户情况，某用户无法签收问题。
17. 修复BUG：分支合并复杂的流程定义里流程跟踪图上当前步骤红色框不见了。


# 升级操作指南

## v4.1.5.1->v4.1.6

如果项目使用的是带角色版本c2系统管理则需要添加以下配置，将工作流默认组配置为角色，详细过程：

1. 在项目的resources/custom/*-content.xml spring文件中加下如下配置
```
<bean id="wfConfig" class="com.chinacreator.c2.flow.util.WfConfig">
	<property name="groupTypes">
		<list>
			<ref bean="jobGroupType"/>
			<ref bean="orgGroupType"/>
			<ref bean="roleGroupType"/>
		</list>
	</property>
	
	<!--默认组类型为岗位，如果act_id_group视图关联的是角色请覆盖配置为roleGroupType-->
	<property name="defaultGroupType" ref="roleGroupType"/>
</bean>
``` 

## v4.1.3->v4.1.5.1

v4.1.3以上版本增加了表，需要手动创建如下数据厍表

1. 在mysql下
```
	create table WF_UNITE_RUN_TASK_EXT
	(
	  ID              VARCHAR(64) not null,
	  UNITE_TASK_ID   VARCHAR(64),
	  EXT_FIELD_KEY   VARCHAR(64),
	  EXT_FIELD_VALUE VARCHAR(255),
	  EXT_FIELD_TYPE  VARCHAR(64),
	  primary key (ID)
	);
	
	alter table WF_UNITE_RUN_TASK_EXT
	  add constraint FK_TASK_EXT_REFERENCE_WF_TASK foreign key (UNITE_TASK_ID)
	  references WF_UNITE_RUN_TASK (ID) on delete restrict on update restrict;
	  
	create table WF_UNITE_HIS_TASK_EXT
	(
	  ID                VARCHAR(64) not null,
	  UNITE_TASK_HIS_ID VARCHAR(64),
	  EXT_FIELD_KEY     VARCHAR(64),
	  EXT_FIELD_VALUE   VARCHAR(255),
	  EXT_FIELD_TYPE    VARCHAR(64),
	  primary key (ID)
	);
	  
	alter table WF_UNITE_HIS_TASK_EXT
	  add constraint FK_WF_HISTASK_REFERENCE_WF_EXT foreign key (UNITE_TASK_HIS_ID)
	  references WF_UNITE_HIS_TASK (ID) on delete restrict on update restrict;
```


2. 在oracle下
```
	create table WF_UNITE_RUN_TASK_EXT
	(
	  ID              VARCHAR2(64) not null,
	  UNITE_TASK_ID   VARCHAR2(64),
	  EXT_FIELD_KEY   VARCHAR2(64),
	  EXT_FIELD_VALUE VARCHAR2(255),
	  EXT_FIELD_TYPE  VARCHAR2(64),
	  constraint WF_UNITE_RUN_TASK_EXT primary key (ID)
	);
	
	alter table WF_UNITE_RUN_TASK_EXT
	  add constraint FK_TASK_EXT_REFERENCE_WF_TASK foreign key (UNITE_TASK_ID)
	  references WF_UNITE_RUN_TASK (ID);
	  
	create table WF_UNITE_HIS_TASK_EXT
	(
	  ID                VARCHAR2(64) not null,
	  UNITE_TASK_HIS_ID VARCHAR2(64),
	  EXT_FIELD_KEY     VARCHAR2(64),
	  EXT_FIELD_VALUE   VARCHAR2(255),
	  EXT_FIELD_TYPE    VARCHAR2(64),
	  constraint WF_UNITE_HIS_TASK_EXT primary key (ID)
	);
	  
	alter table WF_UNITE_HIS_TASK_EXT
	  add constraint FK_WF_HISTASK_REFERENCE_WF_EXT foreign key (UNITE_TASK_HIS_ID)
	  references WF_UNITE_HIS_TASK (ID);
```


## v4.1.0->v4.1.3

此区间的版本工作流如果想升级高版本，需要手动创建如下数据厍表

1. 在mysql下
```
	create table WF_HOLIDAY
	(
	  HOLIDAY   VARCHAR(10) not null comment '保存假日日期',
	  YHOLIDAY  VARCHAR(4) not null comment '保存假日所属的年分，保存4位',
	  MHOLIDAY  VARCHAR(2) not null comment '保存节假日所属的月份，保存2位',
	  TENANT_ID VARCHAR(50) comment '租户ID'
	);
	
	alter table WF_HOLIDAY comment '节假日设置表';
	  
	alter table WF_HOLIDAY
	  add constraint PK_TD_SP_HOLIDAY unique (HOLIDAY, MHOLIDAY, YHOLIDAY);
	  
	create table WF_WORKDATE
	(
	  BEGIN_DATE       VARCHAR(20) comment '起始日期',
	  END_DATE         VARCHAR(20) comment '结束日期',
	  AM_END_TIME      VARCHAR(10) comment '上午班结束时间',
	  WORK_ID          VARCHAR(32) not null comment '主键ID',
	  PM_END_TIME      VARCHAR(10) comment '下午班结束时间',
	  AM_BEGIN_TIME    VARCHAR(10) comment '上午班开始时间',
	  PM_BEGIN_TIME    VARCHAR(10) comment '下午班开始时间',
	  REMARK           VARCHAR(1000) comment '备注说明',
	  LAST_MODIFY_TIME TIMESTAMP DEFAULT NOW()  comment '最后修改时间' ,
	  TENANT_ID        VARCHAR(50) comment '租户ID'
	);
	alter table WF_WORKDATE comment '作息时间表';
	
	alter table WF_WORKDATE
	  add constraint PK_WORKDATE primary key (WORK_ID);
```


2. 在oracle下
```
	create table WF_HOLIDAY
	(
	  HOLIDAY   VARCHAR2(10) not null,
	  YHOLIDAY  VARCHAR2(4) not null,
	  MHOLIDAY  VARCHAR2(2) not null,
	  TENANT_ID VARCHAR2(50)
	);
	
	comment on table WF_HOLIDAY
	  is '节假日设置表';
	comment on column WF_HOLIDAY.HOLIDAY
	  is '保存假日日期';
	comment on column WF_HOLIDAY.YHOLIDAY
	  is '保存假日所属的年分，保存4位';
	comment on column WF_HOLIDAY.MHOLIDAY
	  is '保存节假日所属的月份，保存2位';
	comment on column WF_HOLIDAY.TENANT_ID
	  is '租户ID';
	  
	alter table WF_HOLIDAY
	  add constraint PK_TD_SP_HOLIDAY unique (HOLIDAY, MHOLIDAY, YHOLIDAY);
	
	create table WF_WORKDATE
	(
	  BEGIN_DATE       VARCHAR2(20),
	  END_DATE         VARCHAR2(20),
	  AM_END_TIME      VARCHAR2(10),
	  WORK_ID          VARCHAR2(32) not null,
	  PM_END_TIME      VARCHAR2(10),
	  AM_BEGIN_TIME    VARCHAR2(10),
	  PM_BEGIN_TIME    VARCHAR2(10),
	  REMARK           VARCHAR2(1000),
	  LAST_MODIFY_TIME DATE default sysdate,
	  TENANT_ID        VARCHAR2(50)
	);
	
	
	comment on table WF_WORKDATE
	  is '作息时间表';
	comment on column WF_WORKDATE.BEGIN_DATE
	  is '起始日期';
	comment on column WF_WORKDATE.END_DATE
	  is '结束日期';
	comment on column WF_WORKDATE.AM_END_TIME
	  is '上午班结束时间';
	comment on column WF_WORKDATE.WORK_ID
	  is '主键ID';
	comment on column WF_WORKDATE.PM_END_TIME
	  is '下午班结束时间';
	comment on column WF_WORKDATE.AM_BEGIN_TIME
	  is '上午班开始时间';
	comment on column WF_WORKDATE.PM_BEGIN_TIME
	  is '下午班开始时间';
	comment on column WF_WORKDATE.REMARK
	  is '备注说明';
	comment on column WF_WORKDATE.LAST_MODIFY_TIME
	  is '最后修改时间';
	comment on column WF_WORKDATE.TENANT_ID
	  is '租户ID';
	  
	alter table WF_WORKDATE
	  add constraint PK_WORKDATE primary key (WORK_ID);
```