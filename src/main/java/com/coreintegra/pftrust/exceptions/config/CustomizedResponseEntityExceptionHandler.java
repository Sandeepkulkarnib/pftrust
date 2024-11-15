package com.coreintegra.pftrust.exceptions.config;

import com.coreintegra.pftrust.exceptions.AuthException;
import com.coreintegra.pftrust.exceptions.CodeException;
import com.coreintegra.pftrust.exceptions.InputException;
import com.coreintegra.pftrust.exceptions.JPAException;
import com.coreintegra.pftrust.util.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(SQLException.class)
	public final ResponseEntity<Object> handleSQLException(SQLException ex,
                                                           WebRequest request) {
		ex.printStackTrace();
		return Response.failureSpl(ex.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAll(Exception ex,
                                                  WebRequest request) {
		ex.printStackTrace();
		return Response.failureSpl(ex.getMessage());
	}
	
	@ExceptionHandler(JPAException.class)
	public final ResponseEntity<Object> handleJPAException(Exception ex,
                                                           WebRequest request) {
		ex.printStackTrace();
		return Response.failure(ex.getMessage());
	}
	
	@ExceptionHandler(InputException.class)
	public final ResponseEntity<Object> handleInputException(Exception ex,
                                                             WebRequest request) {
		return Response.failure("100",ex.getMessage());
	}
	
	@ExceptionHandler(CodeException.class)
	public final ResponseEntity<Object> handlCodeException(Exception ex,
                                                           WebRequest request) {
		ex.printStackTrace();

		return Response.failure(ex.getMessage());
	}
	
	@ExceptionHandler(AuthException.class)
	public final ResponseEntity<Object> handlAuthException(Exception ex,
                                                           WebRequest request) {
		ex.printStackTrace();
		return Response.authFailure(ex.getMessage());
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return Response.failure(ex.getMessage());
	}
}
