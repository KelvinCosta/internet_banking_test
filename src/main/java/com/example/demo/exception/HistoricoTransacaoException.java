package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class HistoricoTransacaoException extends HttpClientErrorException {

	private static final long serialVersionUID = -528309452326975261L;

	public HistoricoTransacaoException(HttpStatus statusCode, String statusText) {
		super(statusCode, statusText);
	}
}
