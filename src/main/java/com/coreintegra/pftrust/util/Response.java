package com.coreintegra.pftrust.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class Response {
	public static final String responseType = "responseType";
	public static final String response = "response";
	public static final String SomethingWentWrong = "Something went wrong...";

	public static ResponseEntity<Object> success(Object responseData) {
		Map<String,Object> result = new HashMap<>();
		result.put(responseType, "200");
		result.put(response, responseData);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	public static ResponseEntity<Object> success(Object responseData, Map<String,Object> additional) {
		Map<String,Object> result = new HashMap<>();
		result.put(responseType, "200");
		result.put(response, responseData);
		
		Object[] keys = additional.keySet().toArray();
		for(int i=0;i<keys.length;i++) {
			result.put(keys[i].toString(), additional.get(keys[i].toString()));
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	public static ResponseEntity<Object> failure() {
		return Response.failure(null, null);
	}
	
	public static ResponseEntity<Object> failure(String message) {
		return Response.failure(null, message);
	}
	
	public static ResponseEntity<Object> failureWithStatus(String status) {
		return Response.failure(status, null);
	}
	
	public static ResponseEntity<Object> failure(String status, String message) {
		Map<String,Object> result = new HashMap<>();
		result.put(responseType, status != null ? status : "300");
		result.put(response, message != null ? message : SomethingWentWrong);
		result.put("error", message);
		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}
	
	public static ResponseEntity<Object> failureSpl(String message) {
		Map<String,Object> result = new HashMap<>();
		result.put(responseType, "300");
		result.put(response, SomethingWentWrong);
		result.put("error", message);
		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}
	
	public static ResponseEntity<Object> authFailure(String message) {
		Map<String,Object> result = new HashMap<>();
		result.put(responseType, "401");
		result.put(response, message);
		result.put("error", message);
		return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
	}
}
