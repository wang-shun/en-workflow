package com.chinacreator.c2.flow.rest.common;

import org.springframework.util.StringUtils;

import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.c2.context.OperationContextHolder;
import com.chinacreator.c2.context.WebOperationContext;
import com.chinacreator.c2.flow.detail.WfOperator;
import com.chinacreator.c2.flow.rest.common.exception.FlowUnauthorizedException;
import com.chinacreator.c2.flow.rest.common.vo.WfBaseRequest;
import com.chinacreator.c2.sysmgr.Subject;

public class FowRestHelper {
	
	   /**
	    * 获取当前操作用户信息
	    * @return
	    */
		public static WfOperator getWfOperator(WfBaseRequest actionRequest) throws Exception{
			
			boolean isDebug="true".equals(ConfigManager.getInstance().getConfig("c2.flow.rest.debug"))?true:false;
			WfOperator wfOperator=new WfOperator();
		    WebOperationContext context = (WebOperationContext)OperationContextHolder.getContext();
		    Subject subject=context.getUser();
		    
		    //如果开启了调试模式从参数中取当前用户
		    if(StringUtils.hasText(actionRequest.getCurrentLoginUserId())&&isDebug){
		    	wfOperator.setUserId(actionRequest.getCurrentLoginUserId());
		    }else if(null!=subject&&StringUtils.hasText(subject.getId())){
		    	wfOperator.setUserId(subject.getId());
		    }
		    
		    if(!StringUtils.hasText(wfOperator.getUserId())){
		    	throw new FlowUnauthorizedException("Not logged in without permission to operate.");
		    }
		    
		    return wfOperator;
		}
}
