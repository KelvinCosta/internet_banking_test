package com.example.demo.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.http.HttpStatus;

import com.example.demo.exception.ClienteException;
import com.example.demo.request.ClienteRequest;
import com.example.demo.utils.DateUtils;

@Entity
@Table(name = "CLIENTE")
public class Cliente {

	private String nome;
	private Boolean planoExclusive;
	private BigDecimal saldo;
	private String numeroConta;
	private Date dataNascimento;
	
	private void validador(String str, String field) {
		if (str.isBlank()) { 
			throw new ClienteException(HttpStatus.BAD_REQUEST, field + " invalido");
		}
	}
	
	public Cliente(ClienteRequest request)  {
		validador(request.getNome(), "Nome");
		this.nome = request.getNome();
		
		validador(request.getPlanoExclusive(), "Plano Exclusive");
		this.planoExclusive = Boolean.parseBoolean(request.getPlanoExclusive());
		
		validador(request.getSaldo(), "Saldo");
		this.saldo = new BigDecimal(request.getSaldo());
		
		validador(request.getNumeroConta(), "Numero Conta");
		this.numeroConta = request.getNumeroConta();
		
		validador(request.getDataNascimento(), "Data Nascimento");
		this.dataNascimento = DateUtils.convertStringToDate("dd/MM/yyyy", request.getDataNascimento());
		
	}

	public String getNome() {
		return nome;
	}

	public Boolean getPlanoExclusive() {
		return planoExclusive;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}
	
}
