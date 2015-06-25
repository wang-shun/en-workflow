drop table if exists WF_MOD_PROC_PROP_EXT;

drop table if exists WF_MOD_PROC_PROP;

drop table if exists WF_MOD_PROC_CONFIG;

drop table if exists WF_OPERATE_LOGDATA;

drop table if exists WF_OPERATE_LOG;

drop table if exists WF_UNITE_COLUNMS;

drop table if exists WF_UNITE_CONFIG;

drop index INDEX_HIS_PROC_DEF_ID on WF_UNITE_HIS_TASK;

drop index INDEX_HIS_PROC_INST_ID on WF_UNITE_HIS_TASK;

drop index INDEX_HIS_TASK_ID on WF_UNITE_HIS_TASK;

drop table if exists WF_UNITE_HIS_TASK;

drop index INDEX_PROC_DEF_ID2 on WF_UNITE_RUN_TASK;

drop index INDEX_PROC_INST_ID2 on WF_UNITE_RUN_TASK;

drop index INDEX_TASK_ID2 on WF_UNITE_RUN_TASK;

drop table if exists WF_UNITE_RUN_TASK;

drop table if exists WF_MOD_DELEGATE_INFO;

drop table if exists WF_MOD_DELEGATE;

drop view if exists V_WF_TODOLIST;
drop view if exists V_WF_DONELIST;
drop view if exists V_WF_SUSPENDLIST;