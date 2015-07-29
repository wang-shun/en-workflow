
create table WF_MOD_PROC_CONFIG 
(
   ID                   VARCHAR2(64)         not null,
   PROC_DEF_ID          VARCHAR2(64),
   PROC_DEF_KEY         VARCHAR2(255),
   PROC_DEF_NAME        VARCHAR2(255),
   MODULE_ID            VARCHAR2(255),
   MODULE_NAME          VARCHAR2(255),
   IS_LATEST            CHAR(1)              default '1',
   APP_ID               VARCHAR2(255),
   IS_HASPROPERTY       CHAR(1)              default '0',
   constraint PK_WF_MOD_PROC_CONFIG primary key (ID)
);

comment on table WF_MOD_PROC_CONFIG is
'事项流程配置表';

comment on column WF_MOD_PROC_CONFIG.ID is
'主键';

comment on column WF_MOD_PROC_CONFIG.PROC_DEF_ID is
'流程定义ID';

comment on column WF_MOD_PROC_CONFIG.PROC_DEF_KEY is
'流程定义KEY';

comment on column WF_MOD_PROC_CONFIG.PROC_DEF_NAME is
'流程定义名称';

comment on column WF_MOD_PROC_CONFIG.MODULE_ID is
'事项ID';

comment on column WF_MOD_PROC_CONFIG.MODULE_NAME is
'事项名称';

comment on column WF_MOD_PROC_CONFIG.IS_LATEST is
'是否最新：(1:是 0:否)';

comment on column WF_MOD_PROC_CONFIG.APP_ID is
'应用ID';

comment on column WF_MOD_PROC_CONFIG.IS_HASPROPERTY is
'是否需要外围配置（1:是 0:否）';

create table WF_MOD_PROC_PROP 
(
   ID                   VARCHAR2(50)         not null,
   CONFIG_ID            VARCHAR2(64),
   TASK_DEF_KEY         VARCHAR2(255),
   ALIAS                VARCHAR2(50),
   DURATION             NUMBER(8,0),
   DURATION_UNIT        VARCHAR2(50),
   PERFORMER            VARCHAR2(2000),
   BIND_FORM            VARCHAR2(255),
   GROUP_PERFORMER 		VARCHAR2(2000),
   constraint PK_WF_MOD_PROC_PROP primary key (ID)
);

comment on table WF_MOD_PROC_PROP is
'环节外围属性配置表';

comment on column WF_MOD_PROC_PROP.ID is
'主键';

comment on column WF_MOD_PROC_PROP.CONFIG_ID is
'事项绑定ID';

comment on column WF_MOD_PROC_PROP.TASK_DEF_KEY is
'任务定义ID';

comment on column WF_MOD_PROC_PROP.ALIAS is
'别名';

comment on column WF_MOD_PROC_PROP.DURATION is
'期限数字';

comment on column WF_MOD_PROC_PROP.DURATION_UNIT is
'流程期限单位（年:Y,月:M,日:D,小时:h,分钟:m,秒:s）';

comment on column WF_MOD_PROC_PROP.PERFORMER is
'执行人（逗号分隔）';

comment on column WF_MOD_PROC_PROP.GROUP_PERFORMER
  is '执行用户组（逗号分隔）';

comment on column WF_MOD_PROC_PROP.BIND_FORM is
'流程绑定表单';

create table WF_MOD_PROC_PROP_EXT 
(
   ID                   VARCHAR2(50)         not null,
   CONFIG_ID            VARCHAR2(64),
   PROC_DEF_ID          VARCHAR2(64),
   TASK_DEF_KEY         VARCHAR2(255),
   PROPERTY_KEY         VARCHAR2(50),
   PROPERTY_VALUE       VARCHAR2(2000),
   PARENT_PROC_DEF_KEY  VARCHAR2(255),
   constraint PK_WF_MOD_PROC_PROP_EXT primary key (ID)
);

comment on table WF_MOD_PROC_PROP_EXT is
'环节外围属性配置表(业务扩展参数)';

comment on column WF_MOD_PROC_PROP_EXT.ID is
'主键';

comment on column WF_MOD_PROC_PROP_EXT.CONFIG_ID is
'事项配置ID';

comment on column WF_MOD_PROC_PROP_EXT.PROC_DEF_ID is
'流程定义ID';

comment on column WF_MOD_PROC_PROP_EXT.TASK_DEF_KEY is
'任务定义ID';

comment on column WF_MOD_PROC_PROP_EXT.PROPERTY_KEY is
'属性键';

comment on column WF_MOD_PROC_PROP_EXT.PROPERTY_VALUE is
'属性值';

comment on column WF_MOD_PROC_PROP_EXT.PARENT_PROC_DEF_KEY is
'父流程定义KEY';

create table WF_OPERATE_LOG 
(
   ID                   VARCHAR2(64)         not null,
   CLASS_NAME           VARCHAR2(500),
   METHOD_NAME          VARCHAR2(255),
   METHOD_ALIAS         VARCHAR2(255),
   RESULT               VARCHAR2(10),
   CALL_TIME            TIMESTAMP,
   USE_TIME             NUMBER(8,0),
   APP_ID               VARCHAR2(255),
   TENANT_ID            VARCHAR2(255),
   ENGINE_NAME          VARCHAR2(255),
   USER_ID              VARCHAR2(255),
   IP                   VARCHAR2(255),
   constraint PK_WF_OPERATE_LOG primary key (ID)
);

comment on table WF_OPERATE_LOG is
'流程日志表';

comment on column WF_OPERATE_LOG.ID is
'日志ID';

comment on column WF_OPERATE_LOG.CLASS_NAME is
'服务类';

comment on column WF_OPERATE_LOG.METHOD_NAME is
'方法名';

comment on column WF_OPERATE_LOG.METHOD_ALIAS is
'方法别名';

comment on column WF_OPERATE_LOG.RESULT is
'操作结果';

comment on column WF_OPERATE_LOG.CALL_TIME is
'调用时间';

comment on column WF_OPERATE_LOG.USE_TIME is
'耗时';

comment on column WF_OPERATE_LOG.APP_ID is
'应用ID';

comment on column WF_OPERATE_LOG.TENANT_ID is
'租户ID';

comment on column WF_OPERATE_LOG.ENGINE_NAME is
'引擎名称';

comment on column WF_OPERATE_LOG.USER_ID is
'操作用户';

comment on column WF_OPERATE_LOG.IP is
'操作IP';


create table WF_OPERATE_LOGDATA 
(
   LOG_ID               VARCHAR2(64),
   ARGS_OBJECT          BLOB,
   ARGS_VALUE           CLOB,
   RETURN_OBJECT        BLOB,
   RETURN_VALUE         CLOB,
   EXCEPTION            CLOB
);

comment on table WF_OPERATE_LOGDATA is
'流程日志数据表';

comment on column WF_OPERATE_LOGDATA.ARGS_OBJECT is
'参数对象';

comment on column WF_OPERATE_LOGDATA.ARGS_VALUE is
'参数值';

comment on column WF_OPERATE_LOGDATA.RETURN_OBJECT is
'返回对象';

comment on column WF_OPERATE_LOGDATA.RETURN_VALUE is
'返回值';

comment on column WF_OPERATE_LOGDATA.EXCEPTION is
'异常';

alter table WF_OPERATE_LOGDATA
   add constraint FK_WF_OPERA_REFERENCE_WF_OPERA foreign key (LOG_ID)
      references WF_OPERATE_LOG (ID);

create table WF_UNITE_COLUNMS 
(
   ID                   VARCHAR2(64)         not null,
   CONFIG_ID            VARCHAR2(64),
   COLUMN_ID            VARCHAR2(255),
   COLUMN_NAME          VARCHAR2(255),
   IS_SHOW              INTEGER,
   IS_TITLE             INTEGER,
   REVISION             INTEGER,
   SN             		INTEGER,
   constraint PK_WF_UNITE_COLUNMS primary key (ID)
);

comment on table WF_UNITE_COLUNMS is
'统一任务字段配置表';

comment on column WF_UNITE_COLUNMS.COLUMN_ID is
'字段标识';

comment on column WF_UNITE_COLUNMS.COLUMN_NAME is
'字段名';

comment on column WF_UNITE_COLUNMS.IS_SHOW is
'是否展示';

comment on column WF_UNITE_COLUNMS.IS_TITLE is
'是否标题';

comment on column WF_UNITE_COLUNMS.SN is
'排序号';

create table WF_UNITE_CONFIG 
(
   ID                   VARCHAR2(64)         not null,
   APP_ID               VARCHAR2(255),
   TENANT_ID            VARCHAR2(255),
   ENGINE_NAME          VARCHAR2(255),
   TASK_LIST            VARCHAR2(255),
   ONLY_TITLE           INTEGER,
   REVISION             INTEGER,
   constraint PK_WF_UNITE_CONFIG primary key (ID)
);

comment on table WF_UNITE_CONFIG is
'统一任务配置表';

comment on column WF_UNITE_CONFIG.APP_ID is
'应用标识';

comment on column WF_UNITE_CONFIG.TENANT_ID is
'租户标识';

comment on column WF_UNITE_CONFIG.ENGINE_NAME is
'流程引擎';

comment on column WF_UNITE_CONFIG.TASK_LIST is
'任务列表';

comment on column WF_UNITE_CONFIG.ONLY_TITLE is
'是否只显示标题(1-是)';

create table WF_UNITE_HIS_TASK 
(
   ID                   VARCHAR2(64)         not null,
   TASK_ID              VARCHAR2(64),
   BUSINESS_KEY         VARCHAR2(255),
   EXECUTION_ID         VARCHAR2(64),
   PROC_INST_ID         VARCHAR2(64),
   PROC_DEF_ID          VARCHAR2(64),
   NAME                 VARCHAR2(255),
   PARENT_TASK_ID       VARCHAR2(64),
   DESCRIPTION          VARCHAR2(2000),
   TASK_DEF_KEY         VARCHAR2(255),
   OWNER                VARCHAR2(255),
   ASSIGNEE             VARCHAR2(255),
   CANDIDATE            VARCHAR2(4000),
   DELEGATION           VARCHAR2(64),
   PRIORITY             INTEGER,
   CREATE_TIME          TIMESTAMP,
   END_TIME             TIMESTAMP,
   DUE_DATE             TIMESTAMP,
   CATEGORY             VARCHAR2(255),
   TASK_STATE           VARCHAR2(64),
   TENANT_ID            VARCHAR2(255),
   APP_ID               VARCHAR2(255),
   MODULE_ID            VARCHAR2(255),
   MODULE_NAME          VARCHAR2(255),
   DELETE_REASON        VARCHAR2(2000),
   FORM_KEY             VARCHAR2(255),
   REVISION             INTEGER,
   REMARK1              VARCHAR2(255),
   REMARK2              VARCHAR2(255),
   REMARK3              VARCHAR2(255),
   REMARK4              VARCHAR2(255),
   REMARK5              VARCHAR2(255),
   REMARK6              VARCHAR2(255),
   REMARK7              VARCHAR2(255),
   REMARK8              VARCHAR2(255),
   REMARK9              VARCHAR2(255),
   REMARK10             VARCHAR2(255),
   constraint PK_WF_UNITE_HIS_TASK primary key (ID)
);

comment on table WF_UNITE_HIS_TASK is
'统一任务历史表';

comment on column WF_UNITE_HIS_TASK.TASK_ID is
'任务ID';

comment on column WF_UNITE_HIS_TASK.BUSINESS_KEY is
'业务标识';

comment on column WF_UNITE_HIS_TASK.EXECUTION_ID is
'分支ID';

comment on column WF_UNITE_HIS_TASK.PROC_INST_ID is
'流程实例ID';

comment on column WF_UNITE_HIS_TASK.PROC_DEF_ID is
'流程定义ID';

comment on column WF_UNITE_HIS_TASK.NAME is
'任务名称';

comment on column WF_UNITE_HIS_TASK.PARENT_TASK_ID is
'上级任务名称';

comment on column WF_UNITE_HIS_TASK.DESCRIPTION is
'描述';

comment on column WF_UNITE_HIS_TASK.TASK_DEF_KEY is
'任务定义标识';

comment on column WF_UNITE_HIS_TASK.OWNER is
'拥有者';

comment on column WF_UNITE_HIS_TASK.ASSIGNEE is
'处理人';

comment on column WF_UNITE_HIS_TASK.CANDIDATE is
'候选人';

comment on column WF_UNITE_HIS_TASK.DELEGATION is
'委派状态';

comment on column WF_UNITE_HIS_TASK.PRIORITY is
'优先级';

comment on column WF_UNITE_HIS_TASK.CREATE_TIME is
'创建时间';

comment on column WF_UNITE_HIS_TASK.END_TIME is
'结束时间';

comment on column WF_UNITE_HIS_TASK.DUE_DATE is
'期限';

comment on column WF_UNITE_HIS_TASK.CATEGORY is
'类别';

comment on column WF_UNITE_HIS_TASK.TASK_STATE is
'挂起状态';

comment on column WF_UNITE_HIS_TASK.TENANT_ID is
'租户标识';

comment on column WF_UNITE_HIS_TASK.APP_ID is
'应用标识';

comment on column WF_UNITE_HIS_TASK.MODULE_ID is
'事项标识';

comment on column WF_UNITE_HIS_TASK.MODULE_NAME is
'事项名称';

comment on column WF_UNITE_HIS_TASK.DELETE_REASON is
'删除原因';

comment on column WF_UNITE_HIS_TASK.FORM_KEY is
'表单标识';

comment on column WF_UNITE_HIS_TASK.REMARK1 is
'备用字段1';

comment on column WF_UNITE_HIS_TASK.REMARK2 is
'备用字段2';

comment on column WF_UNITE_HIS_TASK.REMARK3 is
'备用字段3';

comment on column WF_UNITE_HIS_TASK.REMARK4 is
'备用字段4';

comment on column WF_UNITE_HIS_TASK.REMARK5 is
'备用字段5';

comment on column WF_UNITE_HIS_TASK.REMARK6 is
'备用字段6';

comment on column WF_UNITE_HIS_TASK.REMARK7 is
'备用字段7';

comment on column WF_UNITE_HIS_TASK.REMARK8 is
'备用字段8';

comment on column WF_UNITE_HIS_TASK.REMARK9 is
'备用字段9';

comment on column WF_UNITE_HIS_TASK.REMARK10 is
'备用字段10';

create index INDEX_HIS_TASK_ID on WF_UNITE_HIS_TASK (
   TASK_ID ASC
);

create index INDEX_HIS_PROC_INST_ID on WF_UNITE_HIS_TASK (
   PROC_INST_ID ASC
);

create index INDEX_HIS_PROC_DEF_ID on WF_UNITE_HIS_TASK (
   PROC_DEF_ID ASC
);

create table WF_UNITE_RUN_TASK 
(
   ID                   VARCHAR2(64)         not null,
   TASK_ID              VARCHAR2(64),
   BUSINESS_KEY         VARCHAR2(255),
   EXECUTION_ID         VARCHAR2(64),
   PROC_INST_ID         VARCHAR2(64),
   PROC_DEF_ID          VARCHAR2(64),
   NAME                 VARCHAR2(255),
   PARENT_TASK_ID       VARCHAR2(64),
   DESCRIPTION          VARCHAR2(2000),
   TASK_DEF_KEY         VARCHAR2(255),
   OWNER                VARCHAR2(255),
   ASSIGNEE             VARCHAR2(255),
   CANDIDATE            VARCHAR2(4000),
   DELEGATION           VARCHAR2(64),
   PRIORITY             INTEGER,
   CREATE_TIME          TIMESTAMP,
   END_TIME             TIMESTAMP,
   DUE_DATE             TIMESTAMP,
   CATEGORY             VARCHAR2(255),
   TASK_STATE           VARCHAR2(64),
   TENANT_ID            VARCHAR2(255),
   APP_ID               VARCHAR2(255),
   MODULE_ID            VARCHAR2(255),
   MODULE_NAME          VARCHAR2(255),
   DELETE_REASON        VARCHAR2(2000),
   FORM_KEY             VARCHAR2(255),
   REVISION             INTEGER,
   REMARK1              VARCHAR2(255),
   REMARK2              VARCHAR2(255),
   REMARK3              VARCHAR2(255),
   REMARK4              VARCHAR2(255),
   REMARK5              VARCHAR2(255),
   REMARK6              VARCHAR2(255),
   REMARK7              VARCHAR2(255),
   REMARK8              VARCHAR2(255),
   REMARK9              VARCHAR2(255),
   REMARK10             VARCHAR2(255),
   constraint PK_WF_UNITE_RUN_TASK primary key (ID)
);

comment on table WF_UNITE_RUN_TASK is
'统一任务表';

comment on column WF_UNITE_RUN_TASK.TASK_ID is
'任务ID';

comment on column WF_UNITE_RUN_TASK.BUSINESS_KEY is
'业务标识';

comment on column WF_UNITE_RUN_TASK.EXECUTION_ID is
'分支ID';

comment on column WF_UNITE_RUN_TASK.PROC_INST_ID is
'流程实例ID';

comment on column WF_UNITE_RUN_TASK.PROC_DEF_ID is
'流程定义ID';

comment on column WF_UNITE_RUN_TASK.NAME is
'任务名称';

comment on column WF_UNITE_RUN_TASK.PARENT_TASK_ID is
'上级任务名称';

comment on column WF_UNITE_RUN_TASK.DESCRIPTION is
'描述';

comment on column WF_UNITE_RUN_TASK.TASK_DEF_KEY is
'任务定义标识';

comment on column WF_UNITE_RUN_TASK.OWNER is
'拥有者';

comment on column WF_UNITE_RUN_TASK.ASSIGNEE is
'处理人';

comment on column WF_UNITE_RUN_TASK.CANDIDATE is
'候选人';

comment on column WF_UNITE_RUN_TASK.DELEGATION is
'委派状态';

comment on column WF_UNITE_RUN_TASK.PRIORITY is
'优先级';

comment on column WF_UNITE_RUN_TASK.CREATE_TIME is
'创建时间';

comment on column WF_UNITE_RUN_TASK.END_TIME is
'结束时间';

comment on column WF_UNITE_RUN_TASK.DUE_DATE is
'期限';

comment on column WF_UNITE_RUN_TASK.CATEGORY is
'类别';

comment on column WF_UNITE_RUN_TASK.TASK_STATE is
'任务状态';

comment on column WF_UNITE_RUN_TASK.TENANT_ID is
'租户标识';

comment on column WF_UNITE_RUN_TASK.APP_ID is
'应用标识';

comment on column WF_UNITE_RUN_TASK.MODULE_ID is
'事项标识';

comment on column WF_UNITE_RUN_TASK.MODULE_NAME is
'事项名称';

comment on column WF_UNITE_RUN_TASK.DELETE_REASON is
'删除原因';

comment on column WF_UNITE_RUN_TASK.FORM_KEY is
'表单标识';

comment on column WF_UNITE_RUN_TASK.REMARK1 is
'备用字段1';

comment on column WF_UNITE_RUN_TASK.REMARK2 is
'备用字段2';

comment on column WF_UNITE_RUN_TASK.REMARK3 is
'备用字段3';

comment on column WF_UNITE_RUN_TASK.REMARK4 is
'备用字段4';

comment on column WF_UNITE_RUN_TASK.REMARK5 is
'备用字段5';

comment on column WF_UNITE_RUN_TASK.REMARK6 is
'备用字段6';

comment on column WF_UNITE_RUN_TASK.REMARK7 is
'备用字段7';

comment on column WF_UNITE_RUN_TASK.REMARK8 is
'备用字段8';

comment on column WF_UNITE_RUN_TASK.REMARK9 is
'备用字段9';

comment on column WF_UNITE_RUN_TASK.REMARK10 is
'备用字段10';

create index INDEX_TASK_ID2 on WF_UNITE_RUN_TASK (
   TASK_ID ASC
);

create index INDEX_PROC_INST_ID2 on WF_UNITE_RUN_TASK (
   PROC_INST_ID ASC
);

create index INDEX_PROC_DEF_ID2 on WF_UNITE_RUN_TASK (
   PROC_DEF_ID ASC
);

alter table WF_MOD_PROC_PROP
   add constraint FK_WF_MOD_P_REFERENCE_WF_MOD_P foreign key (CONFIG_ID)
      references WF_MOD_PROC_CONFIG (ID);

alter table WF_UNITE_COLUNMS
   add constraint FK_WF_UNITE_REFERENCE_WF_UNITE foreign key (CONFIG_ID)
      references WF_UNITE_CONFIG (ID);
      
create table WF_MOD_DELEGATE 
(
   DELEGATE_ID          VARCHAR2(64)         not null,
   DESIGNATOR_ID        VARCHAR2(200),
   DESIGNATOR_NAME      VARCHAR2(200),
   DESIGNEE_ID          VARCHAR2(200),
   DESIGNEE_NAME        VARCHAR2(200),
   DELEGATE_TYPE        CHAR(1),
   DELEGATE_START_TIME  DATE,
   DELEGATE_END_TIME    DATE,
   DELEGATE_STAT        CHAR(1),
   DELEGATE_RANGE       CHAR(1),
   CONFIRM_TIME         DATE,
   APP_ID               VARCHAR2(20),
   TENANT_ID            VARCHAR2(255),
   constraint PK_WF_MOD_DELEGATE primary key (DELEGATE_ID)
);

comment on table WF_MOD_DELEGATE is
'事项委托表';

comment on column WF_MOD_DELEGATE.DELEGATE_ID is
'主键';

comment on column WF_MOD_DELEGATE.DESIGNATOR_ID is
'委托人ID';

comment on column WF_MOD_DELEGATE.DESIGNATOR_NAME is
'委托人';

comment on column WF_MOD_DELEGATE.DESIGNEE_ID is
'被委托人ID';

comment on column WF_MOD_DELEGATE.DESIGNEE_NAME is
'被委托人';

comment on column WF_MOD_DELEGATE.DELEGATE_TYPE is
'委托类型：1、上级委托；2、委托别人';

comment on column WF_MOD_DELEGATE.DELEGATE_START_TIME is
'委托开始时间';

comment on column WF_MOD_DELEGATE.DELEGATE_END_TIME is
'委托结束时间';

comment on column WF_MOD_DELEGATE.DELEGATE_STAT is
'委托状态：1、等待确认；2、委托中；3、委托结束；4、拒绝委托';

comment on column WF_MOD_DELEGATE.DELEGATE_RANGE is
'委托范围的类型，0：全部事项 1：个别事项 ';

comment on column WF_MOD_DELEGATE.CONFIRM_TIME is
'确认时间';

comment on column WF_MOD_DELEGATE.APP_ID is
'应用ID';

comment on column WF_MOD_DELEGATE.TENANT_ID is
'租户ID';
      
create table WF_MOD_DELEGATE_INFO 
(
   ID                   VARCHAR2(64)         not null,
   DELEGATE_ID          VARCHAR2(64),
   MOUDULE_ID           VARCHAR2(64),
   MOUDULE_NAME         VARCHAR2(200),
   constraint PK_WF_MOD_DELEGATE_INFO primary key (ID)
);

comment on table WF_MOD_DELEGATE_INFO is
'委托事项信息关联表';

comment on column WF_MOD_DELEGATE_INFO.ID is
'主键';

comment on column WF_MOD_DELEGATE_INFO.DELEGATE_ID is
'委托ID外键';

comment on column WF_MOD_DELEGATE_INFO.MOUDULE_ID is
'事项ID';

comment on column WF_MOD_DELEGATE_INFO.MOUDULE_NAME is
'事项名称';

alter table WF_MOD_DELEGATE_INFO
   add constraint FK_WF_MOD_D_REFERENCE_WF_MOD_D foreign key (DELEGATE_ID)
      references WF_MOD_DELEGATE (DELEGATE_ID);
      
      
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

create table WF_UNITE_RUN_TASK_EXT
(
  ID              VARCHAR2(64) not null,
  UNITE_TASK_ID   VARCHAR2(64),
  EXT_FIELD_KEY   VARCHAR2(64),
  EXT_FIELD_VALUE VARCHAR2(255),
  EXT_FIELD_TYPE  VARCHAR2(64),
  constraint WF_UNITE_HIS_TASK_EXT primary key (ID)
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
