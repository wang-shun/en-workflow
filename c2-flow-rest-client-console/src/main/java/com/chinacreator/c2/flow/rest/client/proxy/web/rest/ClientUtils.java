package com.chinacreator.c2.flow.rest.client.proxy.web.rest;

public class ClientUtils {
	
	public static String filterSuccessJsonp(String callback,String content){
		StringBuilder stringBuilder = new StringBuilder();
		if(null!=callback){				
			stringBuilder.append(callback);
		    stringBuilder.append("(");
		    stringBuilder.append(content);
		    stringBuilder.append(");");
		    return stringBuilder.toString();
		}else{
			return content;
		}
	}
	
	
	public static String filterErrorJsonp(String callback,int errorCode,String errorDesc){
		StringBuilder stringBuilder = new StringBuilder();
		if(null!=callback){
			stringBuilder.append(callback);
			stringBuilder.append("(");
			stringBuilder.append("{code:");
	        stringBuilder.append(errorCode);
	        stringBuilder.append(",msg:'");
	        stringBuilder.append(errorDesc.replace("'", "\\'"));
	        stringBuilder.append("'}");
	        stringBuilder.append(");");
		}else{
			stringBuilder.append("{code:");
	        stringBuilder.append(errorCode);
	        stringBuilder.append(",msg:'");
	        stringBuilder.append(errorDesc.replace("'", "\\'"));
	        stringBuilder.append("'}");
		}
		return stringBuilder.toString();
	}
}
