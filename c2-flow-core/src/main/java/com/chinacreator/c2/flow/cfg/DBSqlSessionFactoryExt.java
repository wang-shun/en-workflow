package com.chinacreator.c2.flow.cfg;

import org.activiti.engine.impl.db.DbSqlSessionFactory;

public class DBSqlSessionFactoryExt extends DbSqlSessionFactory{
	
	public static void initExt(){
		//databaseSpecificStatements.
		String defaultOrderBy = " order by ${orderBy} ";
		databaseSpecificLimitBeforeStatements.put("dmdbms", "select * from ( select a.*, ROWNUM rnum from (");
		databaseSpecificLimitAfterStatements.put("dmdbms", "  ) a where ROWNUM < #{lastRow}) where rnum  >= #{firstRow}");
		databaseSpecificLimitBetweenStatements.put("dmdbms", "");
		databaseOuterJoinLimitBetweenStatements.put("dmdbms", "");
		databaseSpecificOrderByStatements.put("dmdbms", defaultOrderBy);
		addDatabaseSpecificStatement("dmdbms", "selectExclusiveJobsToExecute", "selectExclusiveJobsToExecute_integerBoolean");
	}
}
