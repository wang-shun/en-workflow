insert into tb_sm_privilege (id, code, name, parent_id, type, perm_expr, creator, creator_time, sn)
values ('9306A948074D40D6913C4C63EB77FDA3', 'lcdygl', '流程定义管理', 'A63C219293DC408196986183890E3E96', '4', 'lcdygl', '1', current_timestamp, 1);

insert into tb_sm_privilege (id, code, name, parent_id, type, perm_expr, creator, creator_time, sn)
values ('00856F04A92A4AA4B441C818732CDAB8', 'lcpz', '流程配置', 'A63C219293DC408196986183890E3E96', '4', 'lcpz', '1', current_timestamp, 2);

insert into tb_sm_privilege (id, code, name, parent_id, type, perm_expr, creator, creator_time, sn)
values ('715A4E69C8E3472FB8BC1692A416B6F4', 'processInstanceMonitor', '流程实例监控', 'A63C219293DC408196986183890E3E96', '4', 'processInstanceMonitor', '1', current_timestamp, 5);

insert into tb_sm_privilege (id, code, name, parent_id, type, perm_expr, creator, creator_time, sn)
values ('BBB73676CF47440FA2CF1C4BA3EEE067', 'processLog', '流程日志', 'A63C219293DC408196986183890E3E96', '4', 'processLog', '1', current_timestamp, 9);

insert into tb_sm_privilege (id, code, name, parent_id, type, perm_expr, creator, creator_time, sn)
values ('453BCA26C4A64E7FB0689D05697CAC80', 'taskConfig', '任务配置', 'A63C219293DC408196986183890E3E96', '4', 'taskConfig', '1', current_timestamp, 3);

insert into tb_sm_privilege (id, code, name, parent_id, type, perm_expr, creator, creator_time, sn)
values ('97A08662A8E64761A5F6BE760CA9018F', 'taskHandle', '任务处理', 'A63C219293DC408196986183890E3E96', '4', 'taskHandle', '1', current_timestamp, 4);

insert into tb_sm_privilege (id, code, name, parent_id, type, perm_expr, creator, creator_time, sn)
values ('704245AD9534477D9BD74E9364936B61', 'userProcessInstanceMonitor', '用户流程实例监控', 'A63C219293DC408196986183890E3E96', '4', 'userProcessInstanceMonitor', '1', current_timestamp, 6);

insert into tb_sm_privilege (id, code, name, parent_id, type, perm_expr, creator, creator_time, sn)
values ('A63C219293DC408196986183890E3E96', 'workflowManage', '工作流管理', '0', '4', 'workflowManage', '1', current_timestamp, 2);

insert into tb_sm_privilege (id, code, name, parent_id, type, perm_expr, creator, creator_time, sn)
values ('CC5822F1CBCC46A98648FEA70B690792', 'taskDelegate', '事项委托', 'A63C219293DC408196986183890E3E96', '4', 'taskDelegate', '1', current_timestamp, 7);

insert into tb_sm_privilege (id, code, name, parent_id, type, perm_expr, creator, creator_time, sn)
values ('F7DB51DEB5204576A413D4D6A83690FD', 'myReceivedTask', '委托给我的', 'CC5822F1CBCC46A98648FEA70B690792', '4', 'myReceivedTask', '1', current_timestamp, 2);

insert into tb_sm_privilege (id, code, name, parent_id, type, perm_expr, creator, creator_time, sn)
values ('56ED0AA0B428409F852F95F833EED6A9', 'myDelegate', '我委托的事项', 'CC5822F1CBCC46A98648FEA70B690792', '4', 'myDelegate', '1', current_timestamp, 1);

insert into tb_sm_menu (menu_id, is_enabled, href, icon, display_mode)
values ('00856F04A92A4AA4B441C818732CDAB8', '1', '#/f/moduleBind', 'fa-gears', '0');

insert into tb_sm_menu (menu_id, is_enabled, href, icon, display_mode)
values ('715A4E69C8E3472FB8BC1692A416B6F4', '1', '#/f/processInstanceMonitor', 'fa-share-alt', '0');

insert into tb_sm_menu (menu_id, is_enabled, href, icon, display_mode)
values ('BBB73676CF47440FA2CF1C4BA3EEE067', '1', '#/f/processLog', 'fa-calendar', '0');

insert into tb_sm_menu (menu_id, is_enabled, href, icon, display_mode)
values ('9306A948074D40D6913C4C63EB77FDA3', '1', '#/f/procTypeManage', 'fa-code-fork', '0');

insert into tb_sm_menu (menu_id, is_enabled, href, icon, display_mode)
values ('453BCA26C4A64E7FB0689D05697CAC80', '1', '#/f/taskConfig', 'fa-cog', '0');

insert into tb_sm_menu (menu_id, is_enabled, href, icon, display_mode)
values ('97A08662A8E64761A5F6BE760CA9018F', '1', '#/f/taskList', 'fa-retweet', '0');

insert into tb_sm_menu (menu_id, is_enabled, href, icon, display_mode)
values ('704245AD9534477D9BD74E9364936B61', '1', '#/f/userProcessInstanceMonitor', 'fa-share-alt', '0');

insert into tb_sm_menu (menu_id, is_enabled, href, icon, display_mode)
values ('A63C219293DC408196986183890E3E96', '1', null, 'fa-sitemap', '0');

insert into tb_sm_menu (menu_id, is_enabled, href, icon, display_mode)
values ('F7DB51DEB5204576A413D4D6A83690FD', '1', '#/f/myReceivedTask', 'fa-arrow-circle-down', '0');

insert into tb_sm_menu (menu_id, is_enabled, href, icon, display_mode)
values ('56ED0AA0B428409F852F95F833EED6A9', '1', '#/f/myDelegate', 'fa-external-link-square', '0');

insert into tb_sm_menu (menu_id, is_enabled, href, icon, display_mode)
values ('CC5822F1CBCC46A98648FEA70B690792', '1', null, 'fa-arrows-h', '0');
