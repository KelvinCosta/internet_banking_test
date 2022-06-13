package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Cliente;
import com.example.demo.exception.SacarException;
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

	public Cliente sacarValorConta(MovimentarContaRequest request) {
		try {
			Cliente cliente = clienteRepository.findByNumeroConta(request.getNumeroConta()).get();
			BigDecimal novoSaldo = request.getSaqueDeposito() ? cliente.getSaldo().subtract(request.getValor()) : cliente.getSaldo().add(request.getValor());
			Cliente clienteNovoSaldo = new Cliente(cliente, novoSaldo);
			return clienteRepository.save(clienteNovoSaldo);
		} catch (NoSuchElementException ex) {
			throw new SacarException(HttpStatus.NO_CONTENT, "Numero conta: " + request.getNumeroConta() + " nao encontrado");
		}
	}

}
