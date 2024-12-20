package com.coreintegra.pftrust.exceptions;

import org.springframework.security.core.AuthenticationException;

public class AuthException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public AuthException() {
		super("Unauthorised User");
	}

	public AuthException(String msg) {
		super(msg);
	}

}
