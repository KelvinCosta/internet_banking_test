package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class SacarException extends HttpClientErrorException {

	private static final long serialVersionUID = -1945288925948484513L;

	public SacarException(HttpStatus statusCode, String statusText) {
		super(statusCode, statusText);
	}
}
