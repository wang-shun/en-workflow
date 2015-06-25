package org.activiti.engine.impl.db;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Statement;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.db.DbSqlSession;
import org.activiti.engine.impl.db.DbSqlSessionFactory;
import org.activiti.engine.impl.db.upgrade.DbUpgradeStep;
import org.activiti.engine.impl.util.IoUtil;
import org.activiti.engine.impl.util.ReflectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 解决自定义sql脚本中文乱码问题。将DbSqlSession中的executeSchemaResource方法重写，关键代码71行
 * 
 * @author minghua.guo 2014-09-05
 * 
 */
public class SchemaResourceUtil {

	Logger log = LoggerFactory.getLogger(SchemaResourceUtil.class);
	private DbSqlSession dbSqlSession;
	private DbSqlSessionFactory dbSqlSessionFactory;
	String sys_prefix = "";

	public SchemaResourceUtil(DbSqlSession dbSqlSession,
			DbSqlSessionFactory dbSqlSessionFactory, String sys_prefix) {
		this.dbSqlSession = dbSqlSession;
		this.dbSqlSessionFactory = dbSqlSessionFactory;
		if (sys_prefix != null)
			this.sys_prefix = sys_prefix;
	}

	public void executeSchemaResource(String operation, String component,
			String resourceName, boolean isOptional) throws Exception {
		InputStream inputStream = null;
		try {
			inputStream = ReflectUtil.getResourceAsStream(resourceName);
			if (inputStream == null) {
				if (isOptional) {
					log.info("no schema resource {} for {}", resourceName,
							operation);
				} else {
					throw new ActivitiException("resource '" + resourceName
							+ "' is not available");
				}
			} else {
				executeSchemaResource(operation, component, resourceName,
						inputStream);
			}

		} finally {
			IoUtil.closeSilently(inputStream);
		}
	}

	private void executeSchemaResource(String operation, String component,
			String resourceName, InputStream inputStream) throws Exception {
		log.info("performing {} on {} with resource {}", operation, component,
				resourceName);
		String sqlStatement = null;
		String exceptionSqlStatement = null;
		Connection connection = dbSqlSession.getSqlSession().getConnection();
		Statement jdbcStatement = connection.createStatement();
		try {
			connection.setAutoCommit(false);
			Exception exception = null;
			byte[] bytes = IoUtil.readInputStream(inputStream, resourceName);
			String ddlStatements = new String(bytes, Charset.forName("UTF-8"));
			if (sys_prefix != null && !"".equals(sys_prefix.trim())) {
				sys_prefix += ".";
			}
			ddlStatements = ddlStatements.replaceAll("\\$\\{sys_prefix\\}",
					sys_prefix);

			String databaseType = dbSqlSessionFactory.getDatabaseType();
			if (databaseType.equals("mysql")) {
				DatabaseMetaData databaseMetaData = connection.getMetaData();
				int majorVersion = databaseMetaData.getDatabaseMajorVersion();
				int minorVersion = databaseMetaData.getDatabaseMinorVersion();
				log.info("Found MySQL: majorVersion=" + majorVersion
						+ " minorVersion=" + minorVersion);

				// Special care for MySQL < 5.6
				if (majorVersion <= 5 && minorVersion < 6) {
					ddlStatements = dbSqlSession
							.updateDdlForMySqlVersionLowerThan56(ddlStatements);
				}
			}

			BufferedReader reader = new BufferedReader(new StringReader(
					ddlStatements));
			String line = dbSqlSession.readNextTrimmedLine(reader);
			while (line != null) {
				if (line.startsWith("# ")) {
					log.debug(line.substring(2));

				} else if (line.startsWith("-- ")) {
					log.debug(line.substring(3));

				} else if (line.startsWith("execute java ")) {
					String upgradestepClassName = line.substring(13).trim();
					DbUpgradeStep dbUpgradeStep = null;
					try {
						dbUpgradeStep = (DbUpgradeStep) ReflectUtil
								.instantiate(upgradestepClassName);
					} catch (ActivitiException e) {
						throw new ActivitiException(
								"database update java class '"
										+ upgradestepClassName
										+ "' can't be instantiated: "
										+ e.getMessage(), e);
					}
					try {
						log.debug("executing upgrade step java class {}",
								upgradestepClassName);
						dbUpgradeStep.execute(dbSqlSession);
					} catch (Exception e) {
						throw new ActivitiException(
								"error while executing database update java class '"
										+ upgradestepClassName + "': "
										+ e.getMessage(), e);
					}

				} else if (line.length() > 0) {

					if (line.endsWith(";")) {
						sqlStatement = dbSqlSession.addSqlStatementPiece(
								sqlStatement,
								line.substring(0, line.length() - 1));
						try {
							// no logging needed as the connection will log it
							log.debug("SQL: {}", sqlStatement);
							jdbcStatement.execute(sqlStatement);
//							jdbcStatement.close();
						} catch (Exception e) {
							if (exception == null) {
								exception = e;
								exceptionSqlStatement = sqlStatement;
							}
							log.error("problem during schema {}, statement {}",
									operation, sqlStatement, e);
							throw e;
						} finally {
							sqlStatement = null;
						}
					} else {
						sqlStatement = dbSqlSession.addSqlStatementPiece(
								sqlStatement, line);
					}
				}

				line = dbSqlSession.readNextTrimmedLine(reader);
			}

//			if (exception != null) {
//				throw exception;
//			}

			log.debug("activiti db schema {} for component {} successful",
					operation, component);
			connection.commit();
			jdbcStatement.close();
		} catch (Exception e) {
			connection.rollback();
			throw new ActivitiException("couldn't " + operation
					+ " db schema: " + exceptionSqlStatement, e);
		}
	}

	public DbSqlSession getDbSqlSession() {
		return dbSqlSession;
	}

	public void setDbSqlSession(DbSqlSession dbSqlSession) {
		this.dbSqlSession = dbSqlSession;
	}

	public DbSqlSessionFactory getDbSqlSessionFactory() {
		return dbSqlSessionFactory;
	}

	public void setDbSqlSessionFactory(DbSqlSessionFactory dbSqlSessionFactory) {
		this.dbSqlSessionFactory = dbSqlSessionFactory;
	}
}
