package com.example.demo;

import java.math.BigDecimal;
import java.util.Date;

public class ClienteRequest {

	private String nome;
	private Boolean planoExclusive;
	private BigDecimal saldo;
	private String numeroConta;
	private Date dataNascimento;

	public ClienteRequest(String nome, Boolean planoExclusive, BigDecimal saldo, String numeroConta,
			Date dataNascimento) {
		this.nome = nome;
		this.planoExclusive = planoExclusive;
		this.saldo = saldo;
		this.numeroConta = numeroConta;
		this.dataNascimento = dataNascimento;
	}

	public String getNome() {
		return this.nome;
	}

	public Boolean getPlanoExclusive() {
		return this.planoExclusive;
	}

	public BigDecimal getSaldo() {
		return this.saldo;
	}

	public String getNumeroConta() {
		return this.numeroConta;
	}

	public Date getDataNascimento() {
		return this.dataNascimento;
	}

}
