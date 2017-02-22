package com.chinacreator.c2.flow.rest.common.exception;

import javax.ws.rs.core.Response.Status;

import com.chinacreator.c2.web.exception.RestException;

public class FlowResourceAlreadyExistsException extends RestException{

	private static final long serialVersionUID = 3977361367198212953L;

	public FlowResourceAlreadyExistsException(String message) {
		super(message);
	}
	
	@Override
	public String getErrorNum() {
		return FlowRestErrorNumbers.FLOW_RESOURCE_ALREADY_EXIST;
	}

	@Override
	public Status getHttpStatus() {
		return Status.CONFLICT;
	}

}
