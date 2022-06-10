package com.example.demo.request;

import java.math.BigDecimal;

public class DepositarRequest {
	
	private String numeroConta;
	private BigDecimal valorDeposito;
	
	public DepositarRequest(String numeroConta, BigDecimal valorDeposito) {
		this.numeroConta = numeroConta;
		this.valorDeposito = valorDeposito;
	}

	public String getNumeroConta() {
		return this.numeroConta;
	}

	public BigDecimal getValorDeposito() {
		return this.valorDeposito;
	}

	

}
