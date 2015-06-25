package com.chinacreator.c2.flow;

import java.io.IOException;
import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.ContextLoader;

import com.chinacreator.c2.flow.api.WfFormService;
import com.chinacreator.c2.flow.api.WfHistoryService;
import com.chinacreator.c2.flow.api.WfManagerService;
import com.chinacreator.c2.flow.api.WfRepositoryService;
import com.chinacreator.c2.flow.api.WfRuntimeService;

public class WfApiFactory {
	static ApplicationContext factoryRemote = null;
	static ApplicationContext factoryLocal = null;

	private static WfRuntimeService wfRuntimeService;
	private static WfRepositoryService wfRepositoryService;
	private static WfHistoryService wfHistoryService;
	private static WfManagerService wfManagerService;
	private static WfFormService wfFormService;
	
	private static String remoted = "false";
	private static String holidayWorkDayFlag = "false";
	
	static {
		Properties p = new Properties();
		try {
			p.load(new ClassPathResource("custom/c2-flow-api-application.properties").getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		remoted = (String)p.get("c2.flow.api.remoted");
		holidayWorkDayFlag = (String)p.get("c2.flow.api.holidayworkday.flag");
//		remoted = (String) WfPropertyPlaceholderConfigurer
//				.getContextProperty("c2.flow.api.remoted");
		if ("true".equals(remoted)) {
			factoryRemote = new ClassPathXmlApplicationContext(
					"classpath:custom/c2-flow-api-application.xml");
		}else{
			factoryLocal = ContextLoader.getCurrentWebApplicationContext();
		}
	}

	public static WfRuntimeService getWfRuntimeService() {
		if ("true".equals(remoted)) {
			wfRuntimeService = (WfRuntimeService) factoryRemote
					.getBean("wfRuntimeServiceRemote");
		} else {
			wfRuntimeService = (WfRuntimeService) factoryLocal.getBean("wfRuntimeServiceLocal");
		}
		return wfRuntimeService;
	}

	public static WfRepositoryService getWfRepositoryService() {
		if ("true".equals(remoted)) {
			wfRepositoryService = (WfRepositoryService) factoryRemote
					.getBean("wfRepositoryServiceRemote");
		} else {
			wfRepositoryService = (WfRepositoryService) factoryLocal.getBean("wfRepositoryServiceLocal");
		}
		return wfRepositoryService;
	}

	public static WfHistoryService getWfHistoryService() {
		if ("true".equals(remoted)) {
			wfHistoryService = (WfHistoryService) factoryRemote
					.getBean("wfHistoryServiceRemote");
		} else {
			wfHistoryService = (WfHistoryService) factoryLocal.getBean("wfHistoryServiceLocal");
		}
		return wfHistoryService;
	}

	public static WfManagerService getWfManagerService() {
		if ("true".equals(remoted)) {
			wfManagerService = (WfManagerService) factoryRemote
					.getBean("wfManagerServiceRemote");
		} else {
			wfManagerService = (WfManagerService) factoryLocal.getBean("wfManagerServiceLocal");
		}
		return wfManagerService;
	}

	public static WfFormService getWfFormService() {
		if ("true".equals(remoted)) {
			wfFormService = (WfFormService) factoryRemote
					.getBean("wfFormServiceRemote");
		} else {
			wfFormService = (WfFormService) factoryLocal.getBean("wfFormServiceLocal");
		}
		return wfFormService;
	}
	
	public static String getHolidayWorkdayFlag(){
		return holidayWorkDayFlag;
	}
}
