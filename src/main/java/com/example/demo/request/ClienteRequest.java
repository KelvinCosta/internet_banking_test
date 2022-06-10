package com.example.demo.request;

import com.example.demo.entity.Cliente;

public class ClienteRequest {

	private String nome;
	private String planoExclusive;
	private String saldo;
	private String numeroConta;
	private String dataNascimento;

	public ClienteRequest(String nome, String planoExclusive, String saldo, String numeroConta,
			String dataNascimento) {
		this.nome = nome;
		this.planoExclusive = planoExclusive;
		this.saldo = saldo;
		this.numeroConta = numeroConta;
		this.dataNascimento = dataNascimento;
	}

	public String getNome() {
		return this.nome;
	}

	public String getPlanoExclusive() {
		return this.planoExclusive;
	}

	public String getSaldo() {
		return this.saldo;
	}

	public String getNumeroConta() {
		return this.numeroConta;
	}

	public String getDataNascimento() {
		return this.dataNascimento;
	}

	public Cliente toEntity() {
		return new Cliente(this);
	}

}
