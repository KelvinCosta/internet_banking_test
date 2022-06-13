package com.example.demo.request;

import java.math.BigDecimal;

public class MovimentarContaRequest {

	private String numeroConta;
	private BigDecimal valor;
	private boolean saqueDeposito;

	public MovimentarContaRequest(String numeroConta, BigDecimal valor, boolean saqueDeposito) {
		this.numeroConta = numeroConta;
		this.valor = valor;
		this.saqueDeposito = saqueDeposito;
	}
	
	public String getNumeroConta() {
		return numeroConta;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	
	public boolean getSaqueDeposito() {
		return saqueDeposito;
	}
	
}
