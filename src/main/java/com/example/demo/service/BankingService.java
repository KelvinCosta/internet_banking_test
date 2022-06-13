package com.example.demo.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.Transacao;
import com.example.demo.exception.HistoricoTransacaoException;
import com.example.demo.exception.MovimentarException;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.request.ClienteRequest;
import com.example.demo.request.MovimentarContaRequest;

@Service
public class BankingService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente save(ClienteRequest request) {
		return clienteRepository.save(request.toEntity());
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente movimentarConta(MovimentarContaRequest request) {
		try {
			Cliente cliente = clienteRepository.findByNumeroConta(request.getNumeroConta()).get();
			BigDecimal taxaAdministrativa = request.getSaqueDeposito() && !cliente.getPlanoExclusive()
					? calcularTaxaAdministrativa(request.getValor())
					: new BigDecimal(0);
			BigDecimal novoSaldo = request.getSaqueDeposito()
					? cliente.getSaldo().subtract(request.getValor().add(taxaAdministrativa))
					: cliente.getSaldo().add(request.getValor());
			Transacao transacao = new Transacao(new Date(), request.getValor(), request.getSaqueDeposito() ? "SAQUE" : "DEPOSITO");
			Cliente clienteNovoSaldo = new Cliente(cliente, novoSaldo, transacao);
			return clienteRepository.save(clienteNovoSaldo);
		} catch (NoSuchElementException ex) {
			throw new MovimentarException(HttpStatus.NO_CONTENT,
					"Numero conta: " + request.getNumeroConta() + " nao encontrado");
		}
	}

	private BigDecimal calcularTaxaAdministrativa(BigDecimal valor) {
		BigDecimal min = new BigDecimal(100);
		BigDecimal max = new BigDecimal(300);

		if (valor.compareTo(min) > 0 && valor.compareTo(max) <= 0)
			return valor.multiply(new BigDecimal(0.004));
		if (valor.compareTo(max) > 0)
			return valor.multiply(new BigDecimal(0.01));
		return new BigDecimal(0);
	}

	public Set<Transacao> buscarHistoricoTransacoes(String numeroConta) {
		try{
			Cliente cliente = clienteRepository.findByNumeroConta(numeroConta).get();	
			return cliente.getTransacoes();
		} catch (NoSuchElementException ex) {
			throw new HistoricoTransacaoException(HttpStatus.NO_CONTENT, "Numero conta: " + numeroConta + " nao encontrado");
		}
		
	}

}
