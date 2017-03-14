package com.chinacreator.c2.flow.rest.common.exception;

import javax.ws.rs.core.Response.Status;

import com.chinacreator.c2.web.exception.RestException;

public class FlowUnauthorizedException extends RestException{

	private static final long serialVersionUID = 3977361367198212953L;

	public FlowUnauthorizedException(String message) {
		super(message);
	}
	
	@Override
	public String getErrorNum() {
		return FlowRestErrorNumbers.FLOW_UNAUTHORIZED;
	}

	@Override
	public Status getHttpStatus() {
		return Status.UNAUTHORIZED;
	}

}
