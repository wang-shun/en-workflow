delete from wf_unite_run_task;
delete from wf_unite_his_task;
delete from act_hi_actinst;
delete from act_hi_taskinst;
delete from act_hi_procinst;
delete from act_ru_task;
delete from act_ru_execution;
commit;

insert into wf_unite_run_task
  select t.id,
         replace(t.actinsid, 'reportToDispatcher', 'RTD') as task_id,
         t.businessid as business_key,
         replace(t.procinsid, 'reportToDispatcher', 'RTD') as execution_id,
         replace(t.procinsid, 'reportToDispatcher', 'RTD') as proc_inst_id,
         '&流程定义id' as proc_def_id,
         t.actname as name,
         '' as parent_task_id,
         '' as description,
         replace(t.actdefid, 'reportToDispatcher', 'RTD') as task_def_key,
         '' as owner,
         '' as assignee,
         t.username as candidate,
         '' as delegation,
         0 as priority,
         (to_date('1970-01-01 08:00:00', 'yyyy-mm-dd hh24:mi:ss') +
         t.createtime / 24 / 60 / 60 / 1000) as create_time,
         (to_date('1970-01-01 08:00:00', 'yyyy-mm-dd hh24:mi:ss') +
         t.laststatetime / 24 / 60 / 60 / 1000) as end_time,
         (to_date('1970-01-01 08:00:00', 'yyyy-mm-dd hh24:mi:ss') +
         a.limittime / 24 / 60 / 60 / 1000) as due_date,
         '' as category,
         t.type as task_state,
         '' as tenant_id,
         app_id,
         module_id,
         t.module_id module_name,
         '' as delete_reason,
         '' as form_key,
         1 as revision,
         remark1,
         remark2,
         remark3,
         remark4,
         remark5,
         remark6,
         remark7,
         remark8,
         remark9,
         remark10
    from lsgl.td_proc_uniform_agenda t, lsgl.activities a
   where (t.type <> 'done')
     and t.actinsid = a.id(+)
     and t.procdefid = 'reportToDispatcher_wp1';
     
insert into wf_unite_his_task
  select t.id,
         replace(t.actinsid, 'reportToDispatcher', 'RTD') as task_id,
         t.businessid as business_key,
         replace(t.procinsid, 'reportToDispatcher', 'RTD') as execution_id,
         replace(t.procinsid, 'reportToDispatcher', 'RTD') as proc_inst_id,
         '&流程定义id' as proc_def_id,
         t.actname as name,
         '' as parent_task_id,
         '' as description,
         replace(t.actdefid, 'reportToDispatcher', 'RTD') as task_def_key,
         '' as owner,
         '' as assignee,
         t.username as candidate,
         '' as delegation,
         0 as priority,
         (to_date('1970-01-01 08:00:00', 'yyyy-mm-dd hh24:mi:ss') +
         t.createtime / 24 / 60 / 60 / 1000) as create_time,
         (to_date('1970-01-01 08:00:00', 'yyyy-mm-dd hh24:mi:ss') +
         t.laststatetime / 24 / 60 / 60 / 1000) as end_time,
         (to_date('1970-01-01 08:00:00', 'yyyy-mm-dd hh24:mi:ss') +
         a.limittime / 24 / 60 / 60 / 1000) as due_date,
         '' as category,
         t.type as task_state,
         '' as tenant_id,
         app_id,
         module_id,
         t.module_id module_name,
         '' as delete_reason,
         '' as form_key,
         1 as revision,
         remark1,
         remark2,
         remark3,
         remark4,
         remark5,
         remark6,
         remark7,
         remark8,
         remark9,
         remark10
    from lsgl.td_proc_uniform_agenda t, lsgl.activities a
   where t.type = 'done'
     and t.actinsid = a.id(+)
     and t.procdefid = 'reportToDispatcher_wp1';
commit;
     
insert into act_hi_procinst 
select replace(t.id, 'reportToDispatcher', 'RTD') id_,
       replace(t.id, 'reportToDispatcher', 'RTD') as proc_inst_id_,
       t1.businessid as business_key_,
       '&流程定义id' as proc_def_id_,
       (to_date('1970-01-01 08:00:00', 'yyyy-mm-dd hh24:mi:ss') +
       t.started / 24 / 60 / 60 / 1000) as start_time_,
       (case t2.keyvalue
         when 'closed.completed' then
          (to_date('1970-01-01 08:00:00', 'yyyy-mm-dd hh24:mi:ss') +
          t.laststatetime / 24 / 60 / 60 / 1000)
         else
          null
       end) as end_time_,
       '' as duration_,
       t.resourcerequesterid as start_user_id_,
       replace(t1.activityid, 'reportToDispatcher', 'RTD') as start_act_id_,
       '' as end_act_id_,
       replace(t.activityrequesterprocessid, 'reportToDispatcher', 'RTD') as super_process_instance_id_,
       t.description delete_reason_,
       '' as tenant_id_
  from lsgl.processes                t,
       lsgl.td_proc_form_instancemap t1,
       lsgl.processstates            t2
 where t.id = t1.procinstanceid(+)
   and t.state = t2.oid
   and t.name = 'reportToDispatcher_wp1';
commit;
   
insert into act_hi_actinst
  select replace(t.id, 'reportToDispatcher', 'RTD') as id_,
       replace(t.processid, 'reportToDispatcher', 'RTD') as execution_id_,
       replace(t.processid, 'reportToDispatcher', 'RTD') as proc_inst_id_,
       '&流程定义id' as proc_def_id_,
       replace(t.activitydefinitionid, 'reportToDispatcher', 'RTD') as act_id_,
       replace(t.id, 'reportToDispatcher', 'RTD') as task_id_,
       '' as call_proc_inst_id_,
       t.name as act_name_,
       'userTask' as act_type_,
       t.resourceid as assignee_,
       (to_date('1970-01-01 08:00:00', 'yyyy-mm-dd hh24:mi:ss') +
       t.activated / 24 / 60 / 60 / 1000) as start_time_,
       (case t1.keyvalue
         when 'closed.completed' then
          (to_date('1970-01-01 08:00:00', 'yyyy-mm-dd hh24:mi:ss') +
          t.laststatetime / 24 / 60 / 60 / 1000)
         else
          null
       end) as end_time_,
       '' as duration_,
       '' as tenant_id_
  from lsgl.activities t, lsgl.activitystates t1
 where t.state = t1.oid
   and t.pdefname like '%reportToDispatcher_wp1%';
commit;  
   
insert into act_hi_actinst
  select sys_guid() as id_,
         replace(t.id, 'reportToDispatcher', 'RTD') as execution_id_,
         replace(t.id, 'reportToDispatcher', 'RTD') as proc_inst_id_,
         '&流程定义id' as proc_def_id_,
         'Start' as act_name_,
         '' as task_id_,
         '' as call_proc_inst_id_,
         'Start' as act_id_,
         'startEvent' as act_type_,
         '' as assignee_,
         (to_date('1970-01-01 08:00:00', 'yyyy-mm-dd hh24:mi:ss') +
         t.created / 24 / 60 / 60 / 1000) as start_time_,
         (to_date('1970-01-01 08:00:00', 'yyyy-mm-dd hh24:mi:ss') +
         t.laststatetime / 24 / 60 / 60 / 1000) as end_time_,
         '' as duration_,
         '' as tenant_id_
    from lsgl.processes t
   where t.pdefname like '%reportToDispatcher_wp1%';
commit;  
   
insert into act_hi_actinst 
select sys_guid() as id_,
         replace(t.id, 'reportToDispatcher', 'RTD') as execution_id_,
         replace(t.id, 'reportToDispatcher', 'RTD') as proc_inst_id_,
         '&流程定义id' as proc_def_id_,
         'exclusivegateway1' as act_name_,
         '' as task_id_,
         '' as call_proc_inst_id_,
         'exclusivegateway1' as act_id_,
         'exclusiveGateway' as act_type_,
         '' as assignee_,
         (to_date('1970-01-01 08:00:00', 'yyyy-mm-dd hh24:mi:ss') +
         t.created / 24 / 60 / 60 / 1000) as start_time_,
         (to_date('1970-01-01 08:00:00', 'yyyy-mm-dd hh24:mi:ss') +
         t.laststatetime / 24 / 60 / 60 / 1000) as end_time_,
         '' as duration_,
         '' as tenant_id_
    from lsgl.processes t
   where t.pdefname like '%reportToDispatcher_wp1%';
commit;  
   
insert into act_hi_actinst 
select sys_guid() as id_,
         replace(t.id, 'reportToDispatcher', 'RTD') as execution_id_,
         replace(t.id, 'reportToDispatcher', 'RTD') as proc_inst_id_,
         '&流程定义id' as proc_def_id_,
         'exclusivegateway2' as act_name_,
         '' as task_id_,
         '' as call_proc_inst_id_,
         'exclusivegateway2' as act_id_,
         'exclusiveGateway' as act_type_,
         '' as assignee_,
         (to_date('1970-01-01 08:00:00', 'yyyy-mm-dd hh24:mi:ss') +
         t.created / 24 / 60 / 60 / 1000) as start_time_,
         (to_date('1970-01-01 08:00:00', 'yyyy-mm-dd hh24:mi:ss') +
         t.laststatetime / 24 / 60 / 60 / 1000) as end_time_,
         '' as duration_,
         '' as tenant_id_
    from lsgl.processes t
   where t.pdefname like '%reportToDispatcher_wp1%';
commit;  
   
insert into act_hi_actinst 
select sys_guid() as id_,
         replace(t.id, 'reportToDispatcher', 'RTD') as execution_id_,
         replace(t.id, 'reportToDispatcher', 'RTD') as proc_inst_id_,
         '&流程定义id' as proc_def_id_,
         'exclusivegateway3' as act_name_,
         '' as task_id_,
         '' as call_proc_inst_id_,
         'exclusivegateway3' as act_id_,
         'exclusiveGateway' as act_type_,
         '' as assignee_,
         (to_date('1970-01-01 08:00:00', 'yyyy-mm-dd hh24:mi:ss') +
         t.created / 24 / 60 / 60 / 1000) as start_time_,
         (to_date('1970-01-01 08:00:00', 'yyyy-mm-dd hh24:mi:ss') +
         t.laststatetime / 24 / 60 / 60 / 1000) as end_time_,
         '' as duration_,
         '' as tenant_id_
    from lsgl.processes t
   where t.pdefname like '%reportToDispatcher_wp1%';
commit;  
   
insert into act_hi_actinst 
select sys_guid() as id_,
         replace(t.id, 'reportToDispatcher', 'RTD') as execution_id_,
         replace(t.id, 'reportToDispatcher', 'RTD') as proc_inst_id_,
         '&流程定义id' as proc_def_id_,
         'exclusivegateway4' as act_name_,
         '' as task_id_,
         '' as call_proc_inst_id_,
         'exclusivegateway4' as act_id_,
         'exclusiveGateway' as act_type_,
         '' as assignee_,
         (to_date('1970-01-01 08:00:00', 'yyyy-mm-dd hh24:mi:ss') +
         t.created / 24 / 60 / 60 / 1000) as start_time_,
         (to_date('1970-01-01 08:00:00', 'yyyy-mm-dd hh24:mi:ss') +
         t.laststatetime / 24 / 60 / 60 / 1000) as end_time_,
         '' as duration_,
         '' as tenant_id_
    from lsgl.processes t
   where t.pdefname like '%reportToDispatcher_wp1%';
commit;  
   
insert into act_hi_actinst 
select sys_guid() as id_,
         replace(t.id, 'reportToDispatcher', 'RTD') as execution_id_,
         replace(t.id, 'reportToDispatcher', 'RTD') as proc_inst_id_,
         '&流程定义id' as proc_def_id_,
         'exclusivegateway5' as act_name_,
         '' as task_id_,
         '' as call_proc_inst_id_,
         'exclusivegateway5' as act_id_,
         'exclusiveGateway' as act_type_,
         '' as assignee_,
         (to_date('1970-01-01 08:00:00', 'yyyy-mm-dd hh24:mi:ss') +
         t.created / 24 / 60 / 60 / 1000) as start_time_,
         (to_date('1970-01-01 08:00:00', 'yyyy-mm-dd hh24:mi:ss') +
         t.laststatetime / 24 / 60 / 60 / 1000) as end_time_,
         '' as duration_,
         '' as tenant_id_
    from lsgl.processes t
   where t.pdefname like '%reportToDispatcher_wp1%';
commit;  

insert into act_hi_taskinst
  select replace(t.id, 'reportToDispatcher', 'RTD') id_,
        '&流程定义id' as proc_def_id_,
        replace(t.activitydefinitionid, 'reportToDispatcher', 'RTD') as task_def_key_,
        replace(t.processid, 'reportToDispatcher', 'RTD') as proc_inst_id_,
        replace(t.processid, 'reportToDispatcher', 'RTD') as execution_id_,
        '' as parent_task_id_,
        t.name as name_,
        t.description as description_,
        '' as owner_,
        t.resourceid as assignee_,
        (to_date('1970-01-01 08:00:00', 'yyyy-mm-dd hh24:mi:ss') +
        t.activated / 24 / 60 / 60 / 1000) as start_time_,
        (case to_char(t.accepted)
          when '4611686018427387903' then
           null
          else
           (to_date('1970-01-01 08:00:00', 'yyyy-mm-dd hh24:mi:ss') +
           t.accepted / 24 / 60 / 60 / 1000)
        end) as claim_time_,
        (case t1.keyvalue
          when 'closed.completed' then
           (to_date('1970-01-01 08:00:00', 'yyyy-mm-dd hh24:mi:ss') +
           t.laststatetime / 24 / 60 / 60 / 1000)
          else
           null
        end) as end_time_,
        '' as duration_,
        '' as delete_reason_,
        t.priority as priority_,
        (case t.limittime
          when 253402271999000 then
           null
          else
           (to_date('1970-01-01 08:00:00', 'yyyy-mm-dd hh24:mi:ss') +
           t.limittime / 24 / 60 / 60 / 1000)
        end) as due_date_,
        '' as form_key_,
        '' as category_,
        '' as tenant_id_
   from lsgl.activities t, lsgl.activitystates t1
  where t.state = t1.oid
    and t.pdefname like '%reportToDispatcher_wp1%';
commit;

insert into act_ru_execution
  select replace(t.id, 'reportToDispatcher', 'RTD') as id_,
         1 as rev_,
         replace(t.id, 'reportToDispatcher', 'RTD') as proc_inst_id_,
         t1.businessid as business_key_,
         replace(t.activityrequesterprocessid, 'reportToDispatcher', 'RTD') as parent_id_,
         '&流程定义id' as proc_def_id_,
         null as super_exec_,
         replace(t2.activitydefinitionid, 'reportToDispatcher', 'RTD')  as act_id_,
         1 as is_active_,
         0 as is_concurrent_,
         1 as is_scope_,
         0 as is_event_scope_,
         (case t3.keyvalue
           when 'open.not_running.suspended' then
            2
           else
            1
         end) suspension_state_,
         2 as cached_ent_state_,
         '' as tenant_id_
    from lsgl.processes                t,
         lsgl.td_proc_form_instancemap t1,
         lsgl.activities               t2,
         lsgl.processstates            t3
   where t.name = 'reportToDispatcher_wp1'
     and t.id = t1.procinstanceid
     and t.state = t3.oid
     and t3.keyvalue like '%open%'
     and t.id = t2.processid
     and t2.state in (select a.oid
                        from lsgl.activitystates a
                       where a.keyvalue like '%open%');
commit;
                       
insert into act_ru_task
  select replace(t.id, 'reportToDispatcher', 'RTD') as id_,
         1 as rev_,
         replace(t.processid, 'reportToDispatcher', 'RTD') as execution_id_,
         replace(t.processid, 'reportToDispatcher', 'RTD') as proc_inst_id_,
         '&流程定义id' as proc_def_id_,
         t.name as name_,
         '' as parent_task_id_,
         t.description as description_,
         replace(t.activitydefinitionid, 'reportToDispatcher', 'RTD') as task_def_key_,
         '' as owner_,
         t.resourceid as assignee_,
         '' as delegation_,
         t.priority as priority_,
         (to_date('1970-01-01 08:00:00', 'yyyy-mm-dd hh24:mi:ss') +
         t.activated / 24 / 60 / 60 / 1000) as create_time_,
         (case t.limittime
           when 253402271999000 then
            null
           else
            (to_date('1970-01-01 08:00:00', 'yyyy-mm-dd hh24:mi:ss') +
            t.limittime / 24 / 60 / 60 / 1000)
         end) as due_date_,
         '' as category_,
         (case t1.keyvalue
           when 'open.not_running.suspended' then
            2
           else
            1
         end) suspension_state_,
         '' as tenant_id_
    from lsgl.activities t, lsgl.activitystates t1
   where t.state = t1.oid
     and t1.keyvalue like '%open%'
     and t.pdefname like '%reportToDispatcher_wp1%';
commit;
   
delete from act_hi_actinst t
 where t.act_id_ = 'exclusivegateway2'
   and t.proc_inst_id_ not in
       (select t1.proc_inst_id_
          from act_hi_actinst t1
         where t1.act_id_ = 'RTD_wp1_act2' and t.end_time_ is not null);
delete from act_hi_actinst t
 where t.act_id_ = 'exclusivegateway3'
   and t.proc_inst_id_ not in
       (select t1.proc_inst_id_
          from act_hi_actinst t1
         where t1.act_id_ = 'RTD_wp1_act11' and t.end_time_ is not null);
delete from act_hi_actinst t
 where t.act_id_ = 'exclusivegateway4'
   and t.proc_inst_id_ not in
       (select t1.proc_inst_id_
          from act_hi_actinst t1
         where t1.act_id_ = 'RTD_wp1_act6' and t.end_time_ is not null);
delete from act_hi_actinst t
 where t.act_id_ = 'exclusivegateway5'
   and t.proc_inst_id_ not in
       (select t1.proc_inst_id_
          from act_hi_actinst t1
         where t1.act_id_ = 'RTD_wp1_act7' and t.end_time_ is not null);
commit;