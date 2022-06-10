package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.request.ClienteRequest;
import com.example.demo.response.ClienteResponse;
import com.example.demo.response.ErrorResponseDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Banking")
@RestController
@RequestMapping("/banking")
public class BankingController {
	
	@ApiOperation(value = "Cadastro de cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Novo Cliente Cadastrado"),
			@ApiResponse(code = 400, message = "Erro na requisicao", response = ErrorResponseDTO.class),
			@ApiResponse(code = 500, message = "Erro no servico", response = ErrorResponseDTO.class)
	})
	@PostMapping("/cadastrarCliente")
	@ResponseBody
	public ResponseEntity<ClienteResponse> cadastrarCliente(@RequestBody ClienteRequest request){
		return new ResponseEntity<>(null, HttpStatus.CREATED);
	}
}
