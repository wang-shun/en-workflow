package com.chinacreator.c2.flow.rest.history;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.rest.service.api.engine.variable.RestVariable.RestVariableScope;

import com.chinacreator.c2.flow.rest.common.C2RestResponseFactory;
import com.chinacreator.c2.flow.rest.common.WfAbstractPaginateList;
import com.chinacreator.c2.flow.rest.common.vo.WfHistoricProcessInstanceResponse;

/**
 * @author hushowly
 */
public class WfHistoricProcessInstancePaginateList extends WfAbstractPaginateList<WfHistoricProcessInstanceResponse>{

	@SuppressWarnings("rawtypes")
	@Override
	protected List<WfHistoricProcessInstanceResponse> processList(List list) {
		List<WfHistoricProcessInstanceResponse> responseList = new ArrayList<WfHistoricProcessInstanceResponse>();
		C2RestResponseFactory restResponseFactory = new C2RestResponseFactory();
		for (Object obj : list) {
			HistoricProcessInstance processInstance=(HistoricProcessInstance)obj;
			WfHistoricProcessInstanceResponse result = new WfHistoricProcessInstanceResponse();
			result.setBusinessKey(processInstance.getBusinessKey());
			result.setDeleteReason(processInstance.getDeleteReason());
			result.setDurationInMillis(processInstance.getDurationInMillis());
			result.setEndActivityId(processInstance.getEndActivityId());
			result.setEndTime(processInstance.getEndTime());
			result.setId(processInstance.getId());
			result.setProcessDefinitionId(processInstance.getProcessDefinitionId());
			result.setStartActivityId(processInstance.getStartActivityId());
			result.setStartTime(processInstance.getStartTime());
			result.setStartUserId(processInstance.getStartUserId());
			result.setSuperProcessInstanceId(processInstance.getSuperProcessInstanceId());
			if (processInstance.getProcessVariables() != null) {
				Map<String, Object> variableMap = processInstance.getProcessVariables();
				for (String name : variableMap.keySet()) {
					result.addVariable(restResponseFactory.createRestVariable(name, variableMap.get(name),
							RestVariableScope.LOCAL, processInstance.getId(),
							C2RestResponseFactory.VARIABLE_HISTORY_PROCESS, false));
				}
			}
			result.setTenantId(processInstance.getTenantId());

			responseList.add(result);
		}
		return responseList;
	}
}
