package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class ClienteException extends HttpClientErrorException {

	public ClienteException(HttpStatus statusCode, String statusText) {
		super(statusCode, statusText);
	}

	private static final long serialVersionUID = -7406764300358373966L;

}
