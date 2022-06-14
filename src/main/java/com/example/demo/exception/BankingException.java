package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class BankingException extends HttpClientErrorException {

	private static final long serialVersionUID = -1945288925948484513L;

	public BankingException(String entity, String field, String key, String description) {
		super((entity + "." + field + key).toLowerCase(), HttpStatus.BAD_REQUEST, entity + " " + field + description, null, null, null);
	}
}
