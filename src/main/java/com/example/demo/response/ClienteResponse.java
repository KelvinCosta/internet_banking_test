package com.example.demo.response;

public class ClienteResponse {

	private String nome;
	private String planoExclusive;
	private String saldo;
	private String numeroConta;
	private String dataNascimento;

	public ClienteResponse(String nome, String planoExclusive, String saldo, String numeroConta,
			String dataNascimento) {
		this.nome = nome;
		this.planoExclusive = planoExclusive;
		this.saldo = saldo;
		this.numeroConta = numeroConta;
		this.dataNascimento = dataNascimento;
	}

}
