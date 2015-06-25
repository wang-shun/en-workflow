package com.chinacreator.c2.flow.cmd;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.db.DbSqlSessionFactory;
import org.activiti.engine.impl.db.SchemaResourceUtil;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

public class WfExtendSchemaBuildCmd implements Command<Object> {
	private DbSqlSession dbSqlSession;
	private DbSqlSessionFactory dbSqlSessionFactory;
	private boolean dbIdentityUsed = true;
	private String sys_prefix = "";

	public WfExtendSchemaBuildCmd(String sys_prefix) {
		this.sys_prefix = sys_prefix;
	}

	@Override
	public Object execute(CommandContext commandContext) {
		dbSqlSession = commandContext.getDbSqlSession();
		dbSqlSessionFactory = dbSqlSession.getDbSqlSessionFactory();
		try {
			SchemaResourceUtil schemaResourceUtil = new SchemaResourceUtil(
					dbSqlSession, dbSqlSessionFactory, sys_prefix);

			// 初始化基础扩展表
			boolean isCoreTablePresent = dbSqlSession
					.isTablePresent("WF_UNITE_RUN_TASK");
			if (!isCoreTablePresent) {
				schemaResourceUtil.executeSchemaResource("create", "core",
						getResourceForDbOperation("create", "create", "core"),
						true);
				// 初始化基础数据,如菜单
				schemaResourceUtil.executeSchemaResource("create", "data",
						getResourceForDbOperation("create", "create", "data"),
						true);
			}

			// 初始化机构用户视图
			dbIdentityUsed = dbSqlSessionFactory.isDbIdentityUsed();
			boolean isIdentityTablePresent = isTablePresent("ACT_ID_USER");
			if (!dbIdentityUsed && !isIdentityTablePresent) {
				schemaResourceUtil.executeSchemaResource(
						"create",
						"identity",
						getResourceForDbOperation("create", "create",
								"identity"), true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			commandContext.exception(e);
		}
		return null;
	}

	//自定义判断，加入视图判断
	public static String[] JDBC_METADATA_TABLE_TYPES = { "TABLE", "VIEW"};
	public boolean isTablePresent(String tableName) {
		// ACT-1610: in case the prefix IS the schema itself, we don't add the
		// prefix, since the
		// check is already aware of the schema
		if (!dbSqlSessionFactory.isTablePrefixIsSchema()) {
			tableName = prependDatabaseTablePrefix(tableName);
		}

		Connection connection = null;
		try {
			connection = dbSqlSession.getSqlSession().getConnection();
			DatabaseMetaData databaseMetaData = connection.getMetaData();
			ResultSet tables = null;

			String schema = "";
			if (dbSqlSessionFactory.getDatabaseSchema() != null) {
				schema = dbSqlSessionFactory.getDatabaseSchema();
			}

			String databaseType = dbSqlSessionFactory.getDatabaseType();

			if ("postgres".equals(databaseType)) {
				tableName = tableName.toLowerCase();
			}

			try {
				tables = databaseMetaData.getTables(
						null, schema,
						tableName, JDBC_METADATA_TABLE_TYPES);
				return tables.next();
			} finally {
				try {
					tables.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			throw new ActivitiException(
					"couldn't check if tables are already present using metadata: "
							+ e.getMessage(), e);
		}
	}

	protected String prependDatabaseTablePrefix(String tableName) {
		return dbSqlSessionFactory.getDatabaseTablePrefix() + tableName;
	}

	private String getResourceForDbOperation(String directory,
			String operation, String component) {
		String databaseType = dbSqlSessionFactory.getDatabaseType();
		return "com/chinacreator/c2/flow/db/" + directory + "/c2flow."
				+ databaseType + "." + operation + "." + component + ".sql";
	}
}
