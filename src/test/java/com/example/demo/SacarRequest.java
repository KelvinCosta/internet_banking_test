package com.example.demo;

import java.math.BigDecimal;

public class SacarRequest {

	private String numeroConta;
	private BigDecimal valorSaque;

	public SacarRequest(String numeroConta, BigDecimal valorSaque) {
		this.numeroConta = numeroConta;
		this.valorSaque = valorSaque;
	}
	
	public String getNumeroConta() {
		return this.numeroConta;
	}
	
	public BigDecimal getValorSaque() {
		return this.valorSaque;
	}
}
