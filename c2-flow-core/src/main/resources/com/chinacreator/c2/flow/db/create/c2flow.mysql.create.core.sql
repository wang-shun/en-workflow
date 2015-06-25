
create table WF_MOD_PROC_CONFIG
(
   ID                   varchar(64) not null comment '主键',
   PROC_DEF_ID          varchar(64) comment '流程定义ID',
   PROC_DEF_KEY         varchar(255) comment '流程定义KEY',
   PROC_DEF_NAME        varchar(255) comment '流程定义名称',
   MODULE_ID            varchar(255) comment '事项ID',
   MODULE_NAME          varchar(255) comment '事项名称',
   IS_LATEST            char(1) default '1' comment '是否最新：(1:是 0:否)',
   APP_ID               varchar(255) comment '应用ID',
   IS_HASPROPERTY       char(1) default '0' comment '是否需要外围配置（1:是 0:否）',
   primary key (ID)
);

alter table WF_MOD_PROC_CONFIG comment '事项流程配置表';

create table WF_MOD_PROC_PROP
(
   ID                   varchar(50) not null comment '主键',
   CONFIG_ID            varchar(64) comment '事项绑定ID',
   TASK_DEF_KEY         varchar(255) comment '任务定义ID',
   ALIAS                varchar(50) comment '别名',
   DURATION             numeric(8,0) comment '期限数字',
   DURATION_UNIT        varchar(50) comment '流程期限单位（年:Y,月:M,日:D,小时:h,分钟:m,秒:s）',
   PERFORMER            varchar(2000) comment '执行人（逗号分隔）',
   BIND_FORM            varchar(255) comment '流程绑定表单',
   GROUP_PERFORMER 		varchar(2000) comment '执行用户组（逗号分隔）',
   primary key (ID)
);

alter table WF_MOD_PROC_PROP comment '环节外围属性配置表';

create table WF_MOD_PROC_PROP_EXT
(
   ID                   varchar(50) not null comment '主键',
   CONFIG_ID            varchar(64) comment '事项配置ID',
   PROC_DEF_ID          varchar(64) comment '流程定义ID',
   TASK_DEF_KEY         varchar(255) comment '任务定义ID',
   PROPERTY_KEY         varchar(50) comment '属性键',
   PROPERTY_VALUE       varchar(2000) comment '属性值',
   PARENT_PROC_DEF_KEY  varchar(255) comment '父流程定义KEY',
   primary key (ID)
);

alter table WF_MOD_PROC_PROP_EXT comment '环节外围属性配置表(业务扩展参数)';

create table WF_OPERATE_LOG
(
   ID                   varchar(64) not null comment '日志ID',
   CLASS_NAME           varchar(500) comment '服务类',
   METHOD_NAME          varchar(255) comment '方法名',
   METHOD_ALIAS         varchar(255) comment '方法别名',
   RESULT               varchar(10) comment '操作结果',
   CALL_TIME            timestamp comment '调用时间',
   USE_TIME             numeric(8,0) comment '耗时',
   APP_ID               varchar(255) comment '应用ID',
   TENANT_ID            varchar(255) comment '租户ID',
   ENGINE_NAME          varchar(255) comment '引擎名称',
   USER_ID              varchar(255) comment '操作用户',
   IP                   varchar(255) comment '操作IP',
   primary key (ID)
);

alter table WF_OPERATE_LOG comment '流程日志表';

create table WF_OPERATE_LOGDATA
(
   LOG_ID               varchar(64),
   ARGS_OBJECT          longblob comment '参数对象',
   ARGS_VALUE           text comment '参数值',
   RETURN_OBJECT        longblob comment '返回对象',
   RETURN_VALUE         text comment '返回值',
   EXCEPTION            text comment '异常'
);

alter table WF_OPERATE_LOGDATA comment '流程日志数据表';

alter table WF_OPERATE_LOGDATA add constraint FK_Reference_3 foreign key (LOG_ID)
      references WF_OPERATE_LOG (ID) on delete restrict on update restrict;

create table WF_UNITE_COLUNMS
(
   ID                   varchar(64) not null,
   CONFIG_ID            varchar(64),
   COLUMN_ID            varchar(255) comment '字段标识',
   COLUMN_NAME          varchar(255) comment '字段名',
   IS_SHOW              int comment '是否展示',
   IS_TITLE             int comment '是否标题',
   REVISION             int,
   SN             		int comment '排序号',
   primary key (ID)
);

alter table WF_UNITE_COLUNMS comment '统一任务字段配置表';

create table WF_UNITE_CONFIG
(
   ID                   varchar(64) not null,
   APP_ID               varchar(255) comment '应用标识',
   TENANT_ID            varchar(255) comment '租户标识',
   ENGINE_NAME          varchar(255) comment '流程引擎',
   TASK_LIST            varchar(255) comment '任务列表',
   ONLY_TITLE           int comment '是否只显示标题(1-是)',
   REVISION             int,
   primary key (ID)
);

alter table WF_UNITE_CONFIG comment '统一任务配置表';

create table WF_UNITE_HIS_TASK
(
   ID                   varchar(64) not null,
   TASK_ID              varchar(64) comment '任务ID',
   BUSINESS_KEY         varchar(255) comment '业务标识',
   EXECUTION_ID         varchar(64) comment '分支ID',
   PROC_INST_ID         varchar(64) comment '流程实例ID',
   PROC_DEF_ID          varchar(64) comment '流程定义ID',
   NAME                 varchar(255) comment '任务名称',
   PARENT_TASK_ID       varchar(64) comment '上级任务名称',
   DESCRIPTION          varchar(2000) comment '描述',
   TASK_DEF_KEY         varchar(255) comment '任务定义标识',
   OWNER                varchar(255) comment '拥有者',
   ASSIGNEE             varchar(255) comment '处理人',
   CANDIDATE            varchar(4000) comment '候选人',
   DELEGATION           varchar(64) comment '委派状态',
   PRIORITY             int comment '优先级',
   CREATE_TIME          datetime comment '创建时间',
   END_TIME             datetime comment '结束时间',
   DUE_DATE             datetime comment '期限',
   CATEGORY             varchar(255) comment '类别',
   TASK_STATE           varchar(64) comment '挂起状态',
   TENANT_ID            varchar(255) comment '租户标识',
   APP_ID               varchar(255) comment '应用标识',
   MODULE_ID            varchar(255) comment '事项标识',
   MODULE_NAME          varchar(255) comment '事项名称',
   DELETE_REASON        varchar(2000) comment '删除原因',
   FORM_KEY             varchar(255) comment '表单标识',
   REVISION             int,
   REMARK1              varchar(255) comment '备用字段1',
   REMARK2              varchar(255) comment '备用字段2',
   REMARK3              varchar(255) comment '备用字段3',
   REMARK4              varchar(255) comment '备用字段4',
   REMARK5              varchar(255) comment '备用字段5',
   REMARK6              varchar(255) comment '备用字段6',
   REMARK7              varchar(255) comment '备用字段7',
   REMARK8              varchar(255) comment '备用字段8',
   REMARK9              varchar(255) comment '备用字段9',
   REMARK10             varchar(255) comment '备用字段10',
   primary key (ID)
);

alter table WF_UNITE_HIS_TASK comment '统一任务历史表';

create index INDEX_HIS_TASK_ID on WF_UNITE_HIS_TASK
(
   TASK_ID
);

create index INDEX_HIS_PROC_INST_ID on WF_UNITE_HIS_TASK
(
   PROC_INST_ID
);

create index INDEX_HIS_PROC_DEF_ID on WF_UNITE_HIS_TASK
(
   PROC_DEF_ID
);

create table WF_UNITE_RUN_TASK
(
   ID                   varchar(64) not null,
   TASK_ID              varchar(64) comment '任务ID',
   BUSINESS_KEY         varchar(255) comment '业务标识',
   EXECUTION_ID         varchar(64) comment '分支ID',
   PROC_INST_ID         varchar(64) comment '流程实例ID',
   PROC_DEF_ID          varchar(64) comment '流程定义ID',
   NAME                 varchar(255) comment '任务名称',
   PARENT_TASK_ID       varchar(64) comment '上级任务名称',
   DESCRIPTION          varchar(2000) comment '描述',
   TASK_DEF_KEY         varchar(255) comment '任务定义标识',
   OWNER                varchar(255) comment '拥有者',
   ASSIGNEE             varchar(255) comment '处理人',
   CANDIDATE            varchar(4000) comment '候选人',
   DELEGATION           varchar(64) comment '委派状态',
   PRIORITY             int comment '优先级',
   CREATE_TIME          datetime comment '创建时间',
   END_TIME             datetime comment '结束时间',
   DUE_DATE             datetime comment '期限',
   CATEGORY             varchar(255) comment '类别',
   TASK_STATE           varchar(64) comment '任务状态',
   TENANT_ID            varchar(255) comment '租户标识',
   APP_ID               varchar(255) comment '应用标识',
   MODULE_ID            varchar(255) comment '事项标识',
   MODULE_NAME          varchar(255) comment '事项名称',
   DELETE_REASON        varchar(2000) comment '删除原因',
   FORM_KEY             varchar(255) comment '表单标识',
   REVISION             int,
   REMARK1              varchar(255) comment '备用字段1',
   REMARK2              varchar(255) comment '备用字段2',
   REMARK3              varchar(255) comment '备用字段3',
   REMARK4              varchar(255) comment '备用字段4',
   REMARK5              varchar(255) comment '备用字段5',
   REMARK6              varchar(255) comment '备用字段6',
   REMARK7              varchar(255) comment '备用字段7',
   REMARK8              varchar(255) comment '备用字段8',
   REMARK9              varchar(255) comment '备用字段9',
   REMARK10             varchar(255) comment '备用字段10',
   primary key (ID)
);

alter table WF_UNITE_RUN_TASK comment '统一任务表';

create index INDEX_TASK_ID2 on WF_UNITE_RUN_TASK
(
   TASK_ID
);

create index INDEX_PROC_INST_ID2 on WF_UNITE_RUN_TASK
(
   PROC_INST_ID
);

create index INDEX_PROC_DEF_ID2 on WF_UNITE_RUN_TASK
(
   PROC_DEF_ID
);

create table WF_MOD_DELEGATE
(
   DELEGATE_ID          varchar(64) not null comment '主键',
   DESIGNATOR_ID        varchar(200) comment '委托人ID',
   DESIGNATOR_NAME      varchar(200) comment '委托人',
   DESIGNEE_ID          varchar(200) comment '被委托人ID',
   DESIGNEE_NAME        varchar(200) comment '被委托人',
   DELEGATE_TYPE        char(1) comment '委托类型：1、上级委托；2、委托别人',
   DELEGATE_START_TIME  datetime comment '委托开始时间',
   DELEGATE_END_TIME    datetime comment '委托结束时间',
   DELEGATE_STAT        char(1) comment '委托状态：1、等待确认 2、委托中 3、委托结束  4、拒绝委托',
   DELEGATE_RANGE       char(1) comment '委托范围的类型，0：全部事项 1：个别事项 ',
   CONFIRM_TIME         datetime comment '确认时间',
   APP_ID               varchar(20) comment '应用ID',
   TENANT_ID            varchar(255) comment '租户ID',
   primary key (DELEGATE_ID)
);

alter table WF_MOD_DELEGATE comment '事项委托表';

create table WF_MOD_DELEGATE_INFO
(
   ID                   varchar(64) not null comment '主键',
   DELEGATE_ID          varchar(64) comment '委托ID外键',
   MOUDULE_ID           varchar(64) comment '事项ID',
   MOUDULE_NAME         varchar(200) comment '事项名称',
   primary key (ID)
);

alter table WF_MOD_DELEGATE_INFO comment '委托事项信息关联表';

alter table WF_MOD_DELEGATE_INFO add constraint FK_Reference_4 foreign key (DELEGATE_ID)
      references WF_MOD_DELEGATE (DELEGATE_ID) on delete restrict on update restrict;

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
  
create or replace view v_wf_todolist as
select a.id_ task_id_,
       a.proc_inst_id_,
       a.name_,
       a.proc_def_id_,
       a.rev_,
       a.execution_id_,
       a.parent_task_id_,
       a.description_,
       a.task_def_key_,
       a.owner_,
       a.assignee_,
       a.delegation_,
       a.priority_,
       a.create_time_,
       a.due_date_,
       a.category_,
       a.suspension_state_,
       a.tenant_id_,
       b.user_id_,
      (select c.business_key_ from  ACT_RU_EXECUTION c where a.proc_inst_id_ = c.proc_inst_id_) business_key_,
      (select d.name_ from act_re_procdef d where d.id_ = a.proc_def_id_) procdef_name
  from act_ru_task a, act_ru_identitylink b
 where a.suspension_state_ = 1 and a.id_=b.task_id_ and b.type_='candidate'
;

create or replace view v_wf_donelist as
select a.id_,
       a.proc_def_id_,
       a.task_def_key_,
       a.proc_inst_id_,
       a.execution_id_,
       a.parent_task_id_,
       a.name_,
       a.description_,
       a.owner_,
       a.assignee_,
       a.start_time_,
       a.claim_time_,
       a.end_time_,
       a.duration_,
       a.delete_reason_,
       a.priority_,
       a.due_date_,
       a.form_key_,
       a.category_,
       a.tenant_id_,
       b.user_id_,
       c.business_key_,
       d.name_ procdef_name
  from ACT_HI_TASKINST     a,
       ACT_HI_IDENTITYLINK b,
       ACT_HI_PROCINST     c,
       act_re_procdef      d
 where b.task_id_ = a.id_
   and b.type_ = 'candidate'
   and c.proc_inst_id_ = a.proc_inst_id_
   and d.id_ = a.proc_def_id_
   and a.end_time_ is not null;

CREATE OR REPLACE VIEW v_wf_suspendlist AS 
select a.*, b.user_id_,c.business_key_
  from ACT_RU_TASK a, ACT_RU_IDENTITYLINK b, ACT_RU_EXECUTION c
 where a.id_ = b.task_id_
   and a.proc_inst_id_=c.proc_inst_id_
   and b.type_ = 'candidate'
   and a.suspension_state_ = 2;