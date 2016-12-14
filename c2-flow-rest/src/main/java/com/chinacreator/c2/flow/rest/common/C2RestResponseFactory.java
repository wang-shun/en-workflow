/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.chinacreator.c2.flow.rest.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.task.Comment;
import org.activiti.rest.service.api.engine.variable.BooleanRestVariableConverter;
import org.activiti.rest.service.api.engine.variable.DateRestVariableConverter;
import org.activiti.rest.service.api.engine.variable.DoubleRestVariableConverter;
import org.activiti.rest.service.api.engine.variable.IntegerRestVariableConverter;
import org.activiti.rest.service.api.engine.variable.LongRestVariableConverter;
import org.activiti.rest.service.api.engine.variable.QueryVariable;
import org.activiti.rest.service.api.engine.variable.RestVariable;
import org.activiti.rest.service.api.engine.variable.RestVariable.RestVariableScope;
import org.activiti.rest.service.api.engine.variable.RestVariableConverter;
import org.activiti.rest.service.api.engine.variable.ShortRestVariableConverter;
import org.activiti.rest.service.api.engine.variable.StringRestVariableConverter;

import com.chinacreator.c2.flow.rest.common.vo.WfCommentResponse;
import com.chinacreator.c2.flow.rest.common.vo.WfQueryVariable;
import com.chinacreator.c2.flow.rest.common.vo.WfRestVariable;

/**
 * Default implementation of a {@link C2RestResponseFactory}.
 * 
 * @author Frederik Heremans
 */
public class C2RestResponseFactory {

	public static final int VARIABLE_TASK = 1;
	public static final int VARIABLE_EXECUTION = 2;
	public static final int VARIABLE_PROCESS = 3;
	public static final int VARIABLE_HISTORY_TASK = 4;
	public static final int VARIABLE_HISTORY_PROCESS = 5;
	public static final int VARIABLE_HISTORY_VARINSTANCE = 6;
	public static final int VARIABLE_HISTORY_DETAIL = 7;

	public static final String BYTE_ARRAY_VARIABLE_TYPE = "binary";
	public static final String SERIALIZABLE_VARIABLE_TYPE = "serializable";

	protected List<RestVariableConverter> variableConverters = new ArrayList<RestVariableConverter>();

	public C2RestResponseFactory() {
		initializeVariableConverters();
	}
	
	public List<WfRestVariable> createRestVariables(Map<String, Object> variables, String id, int variableType,RestVariableScope scope) {
		List<WfRestVariable> result = new ArrayList<WfRestVariable>();

		for (Entry<String, Object> pair : variables.entrySet()) {
			result.add(createRestVariable(pair.getKey(), pair.getValue(),scope, id, variableType, false));
		}
		return result;
	}

	public WfRestVariable createRestVariable(String name, Object value,
			RestVariableScope scope, String id, int variableType,
			boolean includeBinaryValue) {

		RestVariableConverter converter = null;
		RestVariable restVar = new RestVariable();
		restVar.setVariableScope(scope);
		restVar.setName(name);

		if (value != null) {
			// Try converting the value
			for (RestVariableConverter c : variableConverters) {
				if (c.getVariableType().isAssignableFrom(value.getClass())) {
					converter = c;
					break;
				}
			}

			if (converter != null) {
				converter.convertVariableValue(value, restVar);
				restVar.setType(converter.getRestTypeName());
			} else {
				// Revert to default conversion, which is the
				// serializable/byte-array form
				if (value instanceof Byte[] || value instanceof byte[]) {
					restVar.setType(BYTE_ARRAY_VARIABLE_TYPE);
				} else {
					restVar.setType(SERIALIZABLE_VARIABLE_TYPE);
				}

				if (includeBinaryValue) {
					restVar.setValue(value);
				}
			}
		}
		
		WfRestVariable wfRestVariable=new WfRestVariable();
		wfRestVariable.setName(restVar.getName());
		wfRestVariable.setType(restVar.getType());
		wfRestVariable.setValue(restVar.getValue());
		wfRestVariable.setVariableScope(restVar.getVariableScope());
		return wfRestVariable;
	}
	
	public Object getVariableValue(WfRestVariable restVariable) {
		Object value = null;

		if (restVariable.getType() != null) {
			// Try locating a converter if the type has been specified
			RestVariableConverter converter = null;
			for (RestVariableConverter conv : variableConverters) {
				if (conv.getRestTypeName().equals(restVariable.getType())) {
					converter = conv;
					break;
				}
			}
			if (converter == null) {
				throw new ActivitiIllegalArgumentException("Variable '"
						+ restVariable.getName() + "' has unsupported type: '"
						+ restVariable.getType() + "'.");
			}
			
			RestVariable rv=new RestVariable();
			rv.setName(restVariable.getName());
			rv.setVariableScope(restVariable.getVariableScope());
			rv.setType(restVariable.getType());
			rv.setValue(restVariable.getValue());
			value = converter.getVariableValue(rv);

		} else {
			// Revert to type determined by REST-to-Java mapping when no
			// explicit type has been provided
			value = restVariable.getValue();
		}
		return value;
	}

	public Object getVariableValue(RestVariable restVariable) {
		Object value = null;

		if (restVariable.getType() != null) {
			// Try locating a converter if the type has been specified
			RestVariableConverter converter = null;
			for (RestVariableConverter conv : variableConverters) {
				if (conv.getRestTypeName().equals(restVariable.getType())) {
					converter = conv;
					break;
				}
			}
			if (converter == null) {
				throw new ActivitiIllegalArgumentException("Variable '"
						+ restVariable.getName() + "' has unsupported type: '"
						+ restVariable.getType() + "'.");
			}
			
			value = converter.getVariableValue(restVariable);

		} else {
			// Revert to type determined by REST-to-Java mapping when no
			// explicit type has been provided
			value = restVariable.getValue();
		}
		return value;
	}

	public Object getVariableValue(QueryVariable restVariable) {
		Object value = null;

		if (restVariable.getType() != null) {
			// Try locating a converter if the type has been specified
			RestVariableConverter converter = null;
			for (RestVariableConverter conv : variableConverters) {
				if (conv.getRestTypeName().equals(restVariable.getType())) {
					converter = conv;
					break;
				}
			}
			if (converter == null) {
				throw new ActivitiIllegalArgumentException("Variable '"
						+ restVariable.getName() + "' has unsupported type: '"
						+ restVariable.getType() + "'.");
			}

			RestVariable temp = new RestVariable();
			temp.setValue(restVariable.getValue());
			temp.setType(restVariable.getType());
			temp.setName(restVariable.getName());
			value = converter.getVariableValue(temp);

		} else {
			// Revert to type determined by REST-to-Java mapping when no
			// explicit type has been provided
			value = restVariable.getValue();
		}
		return value;
	}
	
	public Object getVariableValue(WfQueryVariable restVariable) {
		Object value = null;

		if (restVariable.getType() != null) {
			// Try locating a converter if the type has been specified
			RestVariableConverter converter = null;
			for (RestVariableConverter conv : variableConverters) {
				if (conv.getRestTypeName().equals(restVariable.getType())) {
					converter = conv;
					break;
				}
			}
			if (converter == null) {
				throw new ActivitiIllegalArgumentException("Variable '"
						+ restVariable.getName() + "' has unsupported type: '"
						+ restVariable.getType() + "'.");
			}

			RestVariable temp = new RestVariable();
			temp.setValue(restVariable.getValue());
			temp.setType(restVariable.getType());
			temp.setName(restVariable.getName());
			value = converter.getVariableValue(temp);

		} else {
			// Revert to type determined by REST-to-Java mapping when no
			// explicit type has been provided
			value = restVariable.getValue();
		}
		return value;
	}

	public WfCommentResponse createRestComment(Comment comment) {
		return createCommentResponse(comment.getTaskId(),
				comment.getProcessInstanceId(), comment.getUserId(),
				comment.getFullMessage(), comment.getId());
	}

	public WfCommentResponse createCommentResponse(String taskId,
			String processInstanceId, String author, String message,
			String commentId) {
		WfCommentResponse result = new WfCommentResponse();
		result.setAuthor(author);
		result.setMessage(message);
		result.setId(commentId);
		return result;
	}

	/**
	 * Called once when the converters need to be initialized. Override of
	 * custom conversion needs to be done between java and rest.
	 */
	protected void initializeVariableConverters() {
		variableConverters.add(new StringRestVariableConverter());
		variableConverters.add(new IntegerRestVariableConverter());
		variableConverters.add(new LongRestVariableConverter());
		variableConverters.add(new ShortRestVariableConverter());
		variableConverters.add(new DoubleRestVariableConverter());
		variableConverters.add(new BooleanRestVariableConverter());
		variableConverters.add(new DateRestVariableConverter());
		variableConverters.add(new ListRestVariableConverter());
	}

}
