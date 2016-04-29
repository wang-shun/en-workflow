CREATE TABLE `wf_demo_ask_leave` (
  `id` varchar(128) NOT NULL,
  `ask_user_id` varchar(128) NOT NULL,
  `create_time` datetime NOT NULL,
  `ask_start_time` datetime NOT NULL,
  `ask_end_time` datetime NOT NULL,
  `ask_type` varchar(1024) NOT NULL,
  `assign_to` varchar(128) NOT NULL,
  `ask_days` int(11) NOT NULL,
  `ask_reasons` varchar(2000) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;