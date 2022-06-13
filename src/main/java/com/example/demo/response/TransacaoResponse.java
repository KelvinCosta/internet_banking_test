package com.example.demo.response;

import com.example.demo.entity.Transacao;
import com.example.demo.utils.DateUtils;

public class TransacaoResponse {

	private String data;
	private String valor;
	private String tipo;

	public TransacaoResponse(Transacao transacao) {
		this.data = DateUtils.convertDateToString("dd/MM/yyyy", transacao.getData());
		this.valor = transacao.getValor().toString();
		this.tipo = transacao.getTipo();
	}

	public String getData() {
		return data;
	}

	public String getValor() {
		return valor;
	}

	public String getTipo() {
		return tipo;
	}

}
