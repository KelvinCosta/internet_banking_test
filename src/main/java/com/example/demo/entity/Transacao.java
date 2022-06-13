package com.example.demo.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.example.demo.response.TransacaoResponse;

@Entity
@Table(name = "TRANSACAO")
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSACAO_SEQ")
	@SequenceGenerator(name = "TRANSACAO_SEQ", sequenceName = "TRANSACAO_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private Integer id;

	private Date data;
	private BigDecimal valor;
	private String tipo;

	@ManyToOne
	@JoinColumn(name = "CLIENTE_ID", nullable = false, updatable = false)
	private Cliente cliente;

	public Transacao() {
	}

	public Transacao(Date data, BigDecimal valor, String tipo) {
		this.data = data;
		this.valor = valor;
		this.tipo = tipo;
	}

	public Integer getId() {
		return id;
	}

	public Date getData() {
		return data;
	}

	public BigDecimal getValor() {
		return valor.setScale(2, RoundingMode.CEILING);
	}

	public String getTipo() {
		return tipo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public TransacaoResponse toResponse() {
		return new TransacaoResponse(this);
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
