package com.chinacreator.c2.flow.Exception;

import com.chinacreator.c2.exception.C2RuntimeException;

public class C2FlowRuntimeException  extends C2RuntimeException {

	private static final long serialVersionUID = -3117031226000623588L;
	
	public C2FlowRuntimeException(String msg){
		super(msg) ;
	}
	
	public C2FlowRuntimeException(String msg,Throwable exception){
		super(msg,exception) ;
	}
	
}
