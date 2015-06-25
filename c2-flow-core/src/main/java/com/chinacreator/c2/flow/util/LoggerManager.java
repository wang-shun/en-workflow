package com.chinacreator.c2.flow.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinacreator.c2.flow.detail.WfOperator;

public class LoggerManager {
	public enum LoggerType {
		DEBUG, INFO, WARN, ERROR;
	}

	public static void log(final Class<?> t, final LoggerType logType,
			final WfOperator wfOperator, final Exception e, final String message,
			final Object... params) {
		// 在新线程中调用日志记录
		new Thread() {
			public void run() {
				Logger logger = LoggerFactory.getLogger(t);
				Object[] objs = new Object[params.length + 1];
				objs[0] = wfOperator;
				for (int i = 1; i < objs.length; i++) {
					objs[i] = params[i - 1];
				}

				switch (logType) {
				case DEBUG:
					logger.debug("操作者信息={}；" + message, objs);
					break;
				case WARN:
					logger.warn("操作者信息={}；" + message, objs);
					break;
				case ERROR:
					logger.error("操作者信息={}；" + message + "[异常信息: " + e + "]", objs);
					break;
				default:
					logger.info("操作者信息={}；" + message, objs);
					break;
				}
			}
		}.start();
	}

}
