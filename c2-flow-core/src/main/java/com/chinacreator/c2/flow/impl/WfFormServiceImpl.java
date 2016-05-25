package com.chinacreator.c2.flow.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.FormService;
import org.activiti.engine.form.FormData;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.runtime.ProcessInstance;

import com.chinacreator.c2.flow.api.WfFormService;
import com.chinacreator.c2.flow.detail.WfConstants;
import com.chinacreator.c2.flow.detail.WfFormData;
import com.chinacreator.c2.flow.detail.WfFormProperty;
import com.chinacreator.c2.flow.detail.WfOperator;
import com.chinacreator.c2.flow.detail.WfProcessInstance;
import com.chinacreator.c2.flow.util.CommonUtil;

public class WfFormServiceImpl implements WfFormService {
	
	private FormService formService;
	
	public FormService getFormService() {
		return formService;
	}

	public void setFormService(FormService formService) {
		this.formService = formService;
	}

	@Override
	public WfFormData getStartFormData(String processDefinitionId)
			throws Exception {
		if (CommonUtil.stringNullOrEmpty(processDefinitionId)) {
			throw new ActivitiIllegalArgumentException(
					"The processDefinitionId parameter has to be provided");
		}

		FormData formData = null;
		if (processDefinitionId != null) {
			formData = formService.getStartFormData(processDefinitionId);
		}

		if (formData == null) {
			throw new ActivitiObjectNotFoundException(
					"Could not find a form data with processDefinitionId '" + processDefinitionId + "'.",
					FormData.class);
		}
		
		WfFormData wfFormData = getTransedWfFormData(formData);
		wfFormData.setProcessDefinitionId(processDefinitionId);
		return wfFormData;
	}

	@Override
	public WfFormData getTaskFormData(String taskId) throws Exception {
		if (CommonUtil.stringNullOrEmpty(taskId)) {
			throw new ActivitiIllegalArgumentException(
					"The taskId parameter has to be provided");
		}

		FormData formData = null;
		if (taskId != null) {
			formData = formService.getTaskFormData(taskId);
		}

		if (formData == null) {
			throw new ActivitiObjectNotFoundException(
					"Could not find a form data with taskId '" + taskId + "'.",
					FormData.class);
		}
		
		WfFormData wfFormData = getTransedWfFormData(formData);
		wfFormData.setTaskId(taskId);
		return wfFormData;

	}
	
	private WfFormData getTransedWfFormData(FormData formData){
		if(null!=formData){
			WfFormData wfFormData = new WfFormData();
			wfFormData.setDeploymentId(formData.getDeploymentId());
			wfFormData.setFormKey(formData.getFormKey());
			
			List<FormProperty> formPropertyList = formData.getFormProperties();
			List<WfFormProperty> wfFormPropertyList = new ArrayList<WfFormProperty>();
			if(formPropertyList!=null && !formPropertyList.isEmpty()){
				for(FormProperty fp : formPropertyList){
					if(null!=fp){
						WfFormProperty wfp = new WfFormProperty();
						wfp.setId(fp.getId());
						wfp.setName(fp.getName());
						wfp.setReadable(fp.isReadable());
						wfp.setRequired(fp.isRequired());
						wfp.setType(fp.getType().getName());
						wfp.setValue(fp.getValue());
						wfp.setWritable(fp.isWritable());
						wfFormPropertyList.add(wfp);
					}
				}
			}
			wfFormData.setFormProperties(wfFormPropertyList);
			return wfFormData;
		}
		return null;
	}

	@Override
	public String saveTaskFormData(WfOperator wfOperator, WfFormData data)
			throws Exception {
		if(data==null){
			throw new NullPointerException("WfFormData is null!");
		}
		if(CommonUtil.stringNullOrEmpty(data.getTaskId())){
			throw new NullPointerException("WfFormData.taskId is null!");
		}
		String taskId = data.getTaskId();
		Map<String, String> propertyMap = new HashMap<String, String>();
		if(data.getFormProperties()!=null && !data.getFormProperties().isEmpty()){
			List<WfFormProperty> wfFormPropertyList = data.getFormProperties();
			for(WfFormProperty wfp:wfFormPropertyList){
				propertyMap.put(wfp.getId(), wfp.getValue());
			}
		}
		formService.submitTaskFormData(taskId, propertyMap);
		return WfConstants.WF_CONTROL_EXE_SUCCESS;
	}
	
	@Override
	public WfProcessInstance saveStartFormData(WfOperator wfOperator,
			WfFormData data, String businessKey) throws Exception {
		if(data==null){
			throw new NullPointerException("WfFormData is null!");
		}
		if(CommonUtil.stringNullOrEmpty(data.getProcessDefinitionId())){
			throw new NullPointerException("WfFormData.processDefinitionId is null!");
		}
		String processDefinitionId = data.getProcessDefinitionId();
		Map<String, String> propertyMap = new HashMap<String, String>();
		if(data.getFormProperties()!=null && !data.getFormProperties().isEmpty()){
			List<WfFormProperty> wfFormPropertyList = data.getFormProperties();
			for(WfFormProperty wfp:wfFormPropertyList){
				propertyMap.put(wfp.getId(), wfp.getValue());
			}
		}
		 ProcessInstance processInstance = null;
		if(CommonUtil.stringNullOrEmpty(businessKey)){
			processInstance = formService.submitStartFormData(processDefinitionId, propertyMap);
		}else{
			processInstance = formService.submitStartFormData(processDefinitionId, businessKey, propertyMap);
		}
		
		WfProcessInstance wfProcessInstance = getTransedWfProcessInstance(processInstance);
		return wfProcessInstance;
	}

	private WfProcessInstance getTransedWfProcessInstance(
			ProcessInstance processInstance) {
		if(processInstance!=null){
			WfProcessInstance wfProcessInstance = new WfProcessInstance();
			wfProcessInstance.setActivityId(processInstance.getActivityId());
			wfProcessInstance.setBusinessKey(processInstance.getBusinessKey());
			wfProcessInstance.setEnded(processInstance.isEnded());
			wfProcessInstance.setProcessDefinitionId(processInstance.getProcessDefinitionId());
			wfProcessInstance.setProcessInstanceId(processInstance.getProcessInstanceId());
			wfProcessInstance.setProcessVariables(processInstance.getProcessVariables());
//			wfProcessInstance.setStartTime(processInstance.)
//			wfProcessInstance.setEndTime(processInstance.)
			wfProcessInstance.setSuspended(processInstance.isSuspended());
//			wfProcessInstance.seti
		}
		return null;
	}
	
	
	@Override
	public String getTaskFormKey(String processDefinitionId,
			String taskDefinitionKey) {
		return formService.getTaskFormKey(processDefinitionId, taskDefinitionKey);
	}
	
	
	@Override
	public String getStartFormKey(String processDefinitionId) {
		return formService.getStartFormKey(processDefinitionId);
	}

}
