CREATE OR REPLACE VIEW ACT_ID_GROUP AS
	SELECT
	t.job_id AS ID_,
	1 AS REV_,
	t.job_name AS NAME_,
	'' AS TYPE_
FROM
	${sys_prefix}td_sm_job t
WHERE
	(t.job_scope = '0');

CREATE OR REPLACE VIEW ACT_ID_USER AS
SELECT
	t.user_id AS ID_,
	1 AS REV_,
	t1.user_name AS FIRST_,
	t1.user_realname AS LAST_,
	t1.user_email AS EMAIL_,
	t1.user_password AS PWD_,
	t1.user_id AS PICTURE_ID_
FROM
	(
		${sys_prefix}td_sm_userinstance t
		JOIN ${sys_prefix}td_sm_user t1 ON (
			(
				(
					t.user_id = t1.user_id
				)
				AND (
					(t.scope_type =  '0')
					OR (t.scope_type =  '2')
				)
			)
		)
	)
WHERE
	(t.is_enabled =  '1');
    
CREATE OR REPLACE VIEW ACT_ID_INFO AS
SELECT
	t.user_id AS id_,
	1 AS rev_,
	t1.user_id AS user_id_,
	t1.user_type AS type_,
	'' AS key_,
	'' AS value_,
	'' AS password_,
	'' AS parent_id_
FROM
	(
		${sys_prefix}td_sm_userinstance t
		JOIN ${sys_prefix}td_sm_user t1 ON (
			(
				t.user_id = t1.user_id
			)
		)
	)
WHERE
	(
		(
			(t.scope_type = '0')
			OR (t.scope_type = '2')
		)
		AND (t.is_enabled = '1')
	) ;
  
CREATE OR REPLACE VIEW ACT_ID_MEMBERSHIP AS
SELECT
	t.user_id AS USER_ID_,
	t.scope_id AS GROUP_ID_
FROM
	${sys_prefix}td_sm_userinstance t
WHERE
	(
		(
			(t.scope_type = '2')
			OR (t.scope_type = '0')
		)
		AND (t.is_enabled = '1')
	);