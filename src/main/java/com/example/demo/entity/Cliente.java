package com.example.demo.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.example.demo.exception.BankingException;
import com.example.demo.request.ClienteRequest;
import com.example.demo.response.ClienteResponse;
import com.example.demo.utils.DateUtils;

@Entity
@Table(name = "CLIENTE")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENTE_SEQ")
	@SequenceGenerator(name = "CLIENTE_SEQ", sequenceName = "CLIENTE_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private Integer id;
	
	private String nome;
	private Boolean planoExclusive;
	private BigDecimal saldo;
	private String numeroConta;
	private Date dataNascimento;
	
	@OneToMany(mappedBy = "cliente")
	private Set<Transacao> transacoes = new HashSet<Transacao>();

	private void validador(String str, String field) {
		if (str.isBlank()) {
			throw new BankingException("Cliente", field, ".vazio", " nao pode ser vazio");
		}
	}

	public Cliente() {
	}

	public Cliente(ClienteRequest request) {
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

	/**
	 * 
	 * @param cliente
	 * @param novoSaldo
	 * @return new cliente object with changed saldo value
	 */
	public Cliente(Cliente cliente, BigDecimal novoSaldo, Transacao novaTransacao) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.planoExclusive = cliente.getPlanoExclusive();
		this.saldo = novoSaldo;
		this.numeroConta = cliente.getNumeroConta();
		this.dataNascimento = cliente.getDataNascimento();
		this.transacoes = cliente.getTransacoes();
		transacoes.add(novaTransacao);
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Boolean getPlanoExclusive() {
		return planoExclusive;
	}

	public BigDecimal getSaldo() {
		return saldo.setScale(2, RoundingMode.CEILING);
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
	public Set<Transacao> getTransacoes() {
		return transacoes;
	}

	public ClienteResponse toResponse() {
		return new ClienteResponse(this);
	}

}
