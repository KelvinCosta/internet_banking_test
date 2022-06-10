package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Cliente;
import com.example.demo.request.ClienteRequest;

@Service
public class BankingService {

	public Cliente save(ClienteRequest request) {
		return new Cliente(request);
	}

}
