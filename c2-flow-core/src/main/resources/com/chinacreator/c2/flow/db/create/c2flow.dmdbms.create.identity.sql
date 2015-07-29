create or replace view act_id_group as
select t.job_id as ID_, 1 as REV_, t.JOB_NAME as NAME_, '' as TYPE_
from ${sys_prefix}TD_SM_JOB t where t.JOB_SCOPE='0';

create or replace view act_id_user as
select t.user_id       as ID_,
         1               as REV_,
         t1.user_name     as FIRST_,
         t1.user_realname as LAST_,
         t1.user_email    as EMAIL_,
         t1.user_password as PWD_,
         t1.user_id       as PICTURE_ID_
    from ${sys_prefix}TD_SM_USERINSTANCE t inner join ${sys_prefix}TD_SM_USER t1 on t.user_id=t1.user_id where (t.scope_type='2' or t.scope_type='0') and  t.is_enabled='1';

create or replace view act_id_info as
select t.user_id as id_,
1 as rev_,
t1.user_id as user_id_,
t1.user_type type_,
'' as key_,
'' as value_,
'' as password_,
'' as parent_id_
from ${sys_prefix}TD_SM_USERINSTANCE t inner join ${sys_prefix}TD_SM_USER t1 on t.user_id=t1.user_id where (t.scope_type='2' or t.scope_type='0') and t.is_enabled='1';

  
create or replace view act_id_membership as
select t.USER_ID as USER_ID_, t.scope_id as GROUP_ID_
from  ${sys_prefix}TD_SM_USERINSTANCE t where (t.scope_type='2' or t.scope_type='0')  and t.is_enabled='1';