package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class MovimentarException extends HttpClientErrorException {

	private static final long serialVersionUID = -1945288925948484513L;

	public MovimentarException(HttpStatus statusCode, String statusText) {
		super(statusCode, statusText);
	}
}
