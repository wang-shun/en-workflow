package com.chinacreator.c2.flow.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import org.activiti.engine.ManagementService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chinacreator.c2.flow.cmd.aspect.InsertWfOperateLogCmd;
import com.chinacreator.c2.flow.cmd.aspect.InsertWfOperateLogDataCmd;
import com.chinacreator.c2.flow.detail.WfOperator;
import com.chinacreator.c2.flow.persistence.entity.WfOperateLogDataEntity;
import com.chinacreator.c2.flow.persistence.entity.WfOperateLogEntity;
import com.chinacreator.c2.flow.util.PKGenerator;

/**
 * 工作流服务切面
 * 
 * @author minghua.guo
 * @date 2014-8-27
 */
// 声明这是一个组件
@Component
// 声明这是一个切面Bean
@Aspect
public class WfServiceAspect {
	@Autowired
	ManagementService managementService;

	// 配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
	@Pointcut("execution(* com.chinacreator.c2.flow.impl.*.*(com.chinacreator.c2.flow.detail.WfOperator,..))")
	public void aspect() {
	}

	/*
	 * 配置前置通知,使用在方法aspect()上注册的切入点 同时接受JoinPoint切入点对象,可以没有该参数
	 */

	@Before("aspect()")
	public void before(JoinPoint joinPoint) {
	}

	// 配置后置通知,使用在方法aspect()上注册的切入点

	@After("aspect()")
	public void after(JoinPoint joinPoint) {
	}

	// 配置环绕通知,使用在方法aspect()上注册的切入点
	@Around("aspect()")
	public Object around(JoinPoint joinPoint) throws Throwable {
		long requestTime = 0;
		String className = "";
		String methodName = "";
		String methodAlias = "";
		String argStr = "";
		Object[] args = null;
		Object returnObj = null;
		String returnStr = "";
		Throwable exception = null;
		final WfOperateLogEntity data = new WfOperateLogEntity();
		final WfOperateLogDataEntity data1 = new WfOperateLogDataEntity();
		final long start = System.currentTimeMillis();
		try {
			className = joinPoint.getSignature().getDeclaringTypeName();
			methodName = joinPoint.getSignature().getName();
			Method[] methods = Class.forName(className).getDeclaredMethods();
			Class<?>[] parameterTypers = null;
			for(Method method : methods){
				if(methodName.equals(method.getName())){
					parameterTypers = method.getParameterTypes();
					break;
				}
			}
			args = joinPoint.getArgs();
			if(args != null){
				int i = 0;
				for(Object arg : args){
					if(arg != null){
						argStr += parameterTypers[i].getName()+ "=" + arg.toString() + ";\n";
					}
					i++;
				}
			}
			returnObj = ((ProceedingJoinPoint) joinPoint).proceed();
			long end = System.currentTimeMillis();
			requestTime = end - start;
			returnStr = returnObj.toString();
			data.setResult("成功");
		} catch (Throwable e) {
			long end = System.currentTimeMillis();
			requestTime = end - start;
			exception = e;
			data.setResult("失败");

		}

		final long requestTime1 = requestTime;
		final String className1 = className;
		final String methodName1 = methodName;
		final String methodAlias1 = methodAlias;
		final String argStr1 = argStr;
		final Object[] args1 = args;
		final Object returnObj1 = returnObj;
		final String returnStr1 = returnStr;
		final Throwable exception1 = exception;
		// 在新线程中调用日志记录
		new Thread() {
			public void run() {
				try {
					if (args1 != null && args1.length > 0) {
						WfOperator wfOperator = (WfOperator) args1[0];
						data.setUserId(wfOperator.getUserId());
						data.setIp(wfOperator.getIp());
						data.setTenantId(wfOperator.getTenantId());
						data.setAppId(wfOperator.getBusinessData().getAppId());
					}
				} catch (Exception e) {

				}
				String logId = new PKGenerator().getNextId();
				data.setId(logId);
				data.setCallTime(new Date(start));
				data.setClassName(className1);
				data.setMethodName(methodName1);
				data.setMethodAlias(methodAlias1);
				data.setUseTime(requestTime1);
				InsertWfOperateLogCmd insertWfOperateLogCmd = new InsertWfOperateLogCmd(
						data);
				managementService.executeCommand(insertWfOperateLogCmd);
				data1.setArgsObject(args1);
				data1.setArgsValue(argStr1);
				if (exception1 != null)
					data1.setException(exception1.toString());
				data1.setId(logId);
				data1.setReturnObject(returnObj1);
				data1.setReturnValue(returnStr1);
				InsertWfOperateLogDataCmd insertWfOperateLogDataCmd = new InsertWfOperateLogDataCmd(
						data1);
				managementService.executeCommand(insertWfOperateLogDataCmd);
			}
		}.start();
		if (exception != null)
			throw exception;
		return returnObj;
	}

	// 配置后置返回通知,使用在方法aspect()上注册的切入点

	@AfterReturning(pointcut = "aspect()", returning = "returnObj")
	public void afterReturn(JoinPoint joinPoint, Object returnObj) {
	}

	// 配置抛出异常后通知,使用在方法aspect()上注册的切入点

	@AfterThrowing(pointcut = "aspect()", throwing = "ex")
	public void afterThrow(JoinPoint joinPoint, Exception ex) {
	}
	 

}