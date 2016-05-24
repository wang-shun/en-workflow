package com.chinacreator.c2.flow.ws;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import com.chinacreator.c2.flow.Exception.C2FlowRuntimeException;

public class WsDelegate implements JavaDelegate {
	
	  private Expression wsdl;
	  private Expression operation; 
	  private Expression parameters;  
	  private Expression returnValue;

	  public void execute(DelegateExecution execution) throws Exception {
		  
		  try{
			  
			    String wsdlString = (String)wsdl.getValue(execution);
			    JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
			    Client client = dcf.createClient(wsdlString);

			    List<Object> paramStrings = new ArrayList<Object>();
			    if (parameters!=null) {     
			      StringTokenizer st = new StringTokenizer( (String)parameters.getValue(execution), ",");
			      while (st.hasMoreTokens()) {
			        paramStrings.add(st.nextToken().trim());
			      }
			    }
			    
			    Object response = client.invoke((String)operation.getValue(execution), paramStrings.toArray());    
			    if (returnValue!=null) {
			      String returnVariableName = (String) returnValue.getValue(execution);
			      execution.setVariable(returnVariableName, response);
			    }
			    
		  }catch(Exception e){
			  throw new C2FlowRuntimeException("流程webservice服务节点["+execution.getCurrentActivityName()+"]执行失败！",e);
		  }
		  
		//ExecutionEntity e=(ExecutionEntity)execution;
		//ExpressionManager expressionManager = Context.getProcessEngineConfiguration().getExpressionManager();
		//Expression a1=expressionManager.createExpression("${a1+1}");
		//Expression a1=expressionManager.createExpression("${wf_business_data_key.moduleId}");
//		System.out.println(a1.getValue(execution));
		

	    

	  }
	}