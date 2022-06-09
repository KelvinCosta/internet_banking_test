package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BankingControllerTest {

	private static final String BASE_URI = "/banking";

	@Autowired
	private MockMvc mockMvc;

	@Test
	void canary() {
		assertTrue(true);
	}

	@Test
	void testCadastrarCliente() {
		fail("Not yet implemented");
	}

	@Test
	void testRetornarTodosClientes() {
		fail("Not yet implemented");
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
