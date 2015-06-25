alter table WF_MOD_PROC_PROP
   drop constraint FK_WF_MOD_P_REFERENCE_WF_MOD_P;

alter table WF_UNITE_COLUNMS
   drop constraint FK_WF_UNITE_REFERENCE_WF_UNITE;

drop table WF_MOD_PROC_PROP_EXT cascade constraints;

drop table WF_MOD_PROC_PROP cascade constraints;

drop table WF_MOD_PROC_CONFIG cascade constraints;

drop table WF_OPERATE_LOGDATA cascade constraints;

drop table WF_OPERATE_LOG cascade constraints;

drop table WF_UNITE_COLUNMS cascade constraints;

drop table WF_UNITE_CONFIG cascade constraints;

drop index INDEX_HIS_PROC_DEF_ID;

drop index INDEX_HIS_PROC_INST_ID;

drop index INDEX_HIS_TASK_ID;

drop table WF_UNITE_HIS_TASK cascade constraints;

drop index INDEX_PROC_DEF_ID2;

drop index INDEX_PROC_INST_ID2;

drop index INDEX_TASK_ID2;

drop table WF_UNITE_RUN_TASK cascade constraints;

alter table WF_MOD_DELEGATE_INFO
   drop constraint FK_WF_MOD_D_REFERENCE_WF_MOD_D;

drop table WF_MOD_DELEGATE_INFO cascade constraints;

drop table WF_MOD_DELEGATE cascade constraints;

drop view V_WF_TODOLIST;
drop view V_WF_DONELIST;
drop view V_WF_SUSPENDLIST;