package com.chinacreator.c2.flow.rest.common;

import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.rest.service.api.engine.variable.RestVariable;
import org.activiti.rest.service.api.engine.variable.RestVariableConverter;

import com.alibaba.fastjson.JSONArray;


public class MapRestVariableConverter implements RestVariableConverter{
	  @Override
	  public String getRestTypeName() {
	    return "java.util.Map";
	  }

	  @Override
	  public Class< ? > getVariableType() {
	    return List.class;
	  }
	  
	  @Override
	  public Object getVariableValue(RestVariable result) {
	    if(result.getValue() != null) {
	      if(!(result.getValue() instanceof String)) {
	        throw new ActivitiIllegalArgumentException("Converter can only convert string to java.util.Map");
	      }
	      
	      try {
	    	  return JSONArray.parseArray((String)result.getValue());
	      } catch (Exception e) {
	    	  e.printStackTrace();
	          throw new ActivitiIllegalArgumentException("The given variable value is not a List json str: '" + result.getValue() + "'", e);
	      }
	    }
	    return null;
	  }

	  @Override
	  public void convertVariableValue(Object variableValue, RestVariable result) {
	    if(variableValue != null) {
	      if(!(variableValue instanceof Map)) {
	        throw new ActivitiIllegalArgumentException("Converter can only convert java.util.Map");
	      }
	      
	      try {
	    	  result.setValue(JSONArray.toJSONString(variableValue));
	      } catch (Exception e) {
	          throw new ActivitiIllegalArgumentException("convert java.util.Map to json error : '" + result.getValue() + "'", e);
	      }
	     
	    } else {
	      result.setValue(null);
	    }
	  }
}
