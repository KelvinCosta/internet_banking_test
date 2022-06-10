package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Cliente;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.request.ClienteRequest;

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

}
