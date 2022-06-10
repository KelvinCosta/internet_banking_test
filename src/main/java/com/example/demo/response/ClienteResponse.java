package com.example.demo.response;

import com.example.demo.entity.Cliente;
import com.example.demo.utils.DateUtils;

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

	public ClienteResponse(Cliente cliente) {
		this.nome = cliente.getNome();
		this.planoExclusive = cliente.getPlanoExclusive().toString();
		this.saldo = cliente.getSaldo().toString();
		this.numeroConta = cliente.getNumeroConta();
		this.dataNascimento = DateUtils.convertDateToString("dd/MM/yyyy", cliente.getDataNascimento());
	}

	public String getNome() {
		return nome;
	}

	public String getPlanoExclusive() {
		return planoExclusive;
	}

	public String getSaldo() {
		return saldo;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

}
