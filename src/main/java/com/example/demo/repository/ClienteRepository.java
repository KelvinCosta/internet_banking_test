package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Cliente;

@Repository
public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Integer>{

	List<Cliente> findAll();

	Optional<Cliente> findByNumeroConta(String numeroConta);
	
}
