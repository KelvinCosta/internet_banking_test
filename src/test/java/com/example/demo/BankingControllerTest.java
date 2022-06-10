package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.utils.JsonUtils;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BankingControllerTest {

	private static final String BASE_URI = "/banking";

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private JsonUtils jsonUtils;
	
	protected ResultActions save(Object request) throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(BASE_URI + "/save").contentType(MediaType.APPLICATION_JSON)
				.content(jsonUtils.getRequestJSON(request));
		return mockMvc.perform(builder.contentType(MediaType.APPLICATION_JSON));
	}
	
	private ResultActions findAll() throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(BASE_URI);
		return mockMvc.perform(builder.contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	void canary() {
		assertTrue(true);
	}

	@Test
	void testCadastrarCliente() {
		String nome = "Teste da Silva";
		Boolean planoExclusive = true;
		Long saldo = 0L;
		String numeroConta = "CC102938";
		Date dataNascimento = new Date("25031988");
		ClienteRequest request = new ClienteRequest(nome, planoExclusive, saldo, numeroConta, dataNascimento);
		save(request).andExpect(status().isCreated());
	}

	@Test
	void testRetornarTodosClientes() throws Exception {
		findAll().andExpect(status().isOk());
	}

	@Test
	void testSacarValorMenorQue100() {
		fail("Not yet implemented");
	}

	@Test
	void testSacarValorEntre100E300() {
		fail("Not yet implemented");
	}

	@Test
	void testSacarValorMaiorQue300() {
		fail("Not yet implemented");
	}

	@Test
	void testSacarValorPlanoExclusive() {
		fail("Not yet implemented");
	}

	@Test
	void testDepositar() {
		fail("Not yet implemented");
	}

	@Test
	void testConsultarHistoricoTransacoes() {
		fail("Not yet implemented");
	}

}
