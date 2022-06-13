package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.request.ClienteRequest;
import com.example.demo.request.MovimentarContaRequest;
import com.example.demo.utils.JsonUtils;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@SqlGroup({
	@Sql(scripts = "/sql/delete_database.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD),
	@Sql(scripts = "/sql/create_database.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD),
	@Sql(scripts = "/sql/insert_data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
})
class BankingControllerTest {

	private static final String BASE_URI = "/banking";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private JsonUtils jsonUtils;

	protected ResultActions post(Object request, String endpoint) throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(BASE_URI + endpoint)
				.contentType(MediaType.APPLICATION_JSON).content(jsonUtils.getRequestJSON(request));
		return mockMvc.perform(builder.contentType(MediaType.APPLICATION_JSON));
	}

	private ResultActions findAll() throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(BASE_URI + "/clientes");
		return mockMvc.perform(builder.contentType(MediaType.APPLICATION_JSON));
	}

	private ResultActions finder(String endpoint) throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(BASE_URI + endpoint);
		return mockMvc.perform(builder.contentType(MediaType.APPLICATION_JSON));
	}

	private String calcularSaldoEsperado(BigDecimal valorSaque, BigDecimal taxaAdministrativa) {
		BigDecimal saldoInicial = new BigDecimal(1000);
		BigDecimal valorTaxaAdministrativa = valorSaque.multiply(taxaAdministrativa);
		BigDecimal saldoEsperado = saldoInicial.subtract(valorSaque).subtract(valorTaxaAdministrativa);
		return saldoEsperado.setScale(2, RoundingMode.CEILING).toString();
	}

	@Test
	void canary() {
		assertTrue(true);
	}

	@Test
	void testCadastrarCliente() {
		String nome = "Teste da Silva";
		String planoExclusive = "true";
		String saldo = "0.00";
		String numeroConta = "CC102938";
		String dataNascimento = "01/01/1988";
		ClienteRequest request = new ClienteRequest(nome, planoExclusive, saldo, numeroConta, dataNascimento);

		try {
			post(request, "/cadastrarCliente")
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.nome").value(nome))
			.andExpect(jsonPath("$.planoExclusive").value(planoExclusive))
			.andExpect(jsonPath("$.saldo").value(saldo.toString()))
			.andExpect(jsonPath("$.numeroConta").value(numeroConta))
			.andExpect(jsonPath("$.dataNascimento").value(dataNascimento.toString()));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testRetornarTodosClientes() {
		try {
			findAll()
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].numeroConta").value("CC000001"))
			.andExpect(jsonPath("$[0].nome").value("TESTE 1"))
			.andExpect(jsonPath("$[1].numeroConta").value("CC000002"))
			.andExpect(jsonPath("$[1].nome").value("TESTE 2"))
			.andExpect(jsonPath("$[2].numeroConta").value("CC000003"))
			.andExpect(jsonPath("$[2].nome").value("TESTE 3"))
			.andExpect(jsonPath("$[3].numeroConta").value("CC000004"))
			.andExpect(jsonPath("$[3].nome").value("TESTE 4"))
			.andExpect(jsonPath("$[4].numeroConta").value("CC000005"))
			.andExpect(jsonPath("$[4].nome").value("TESTE 5"))
			.andExpect(jsonPath("$[5].numeroConta").value("CC000006"))
			.andExpect(jsonPath("$[5].nome").value("TESTE 6"));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testSacarValorMenorQue100() {
		String numeroConta = "CC000001";
		BigDecimal valorSaque = new BigDecimal(50);
		String saldoEsperado = calcularSaldoEsperado(valorSaque, new BigDecimal(0));

		MovimentarContaRequest request = new MovimentarContaRequest(numeroConta, valorSaque, true);

		try {
			post(request, "/movimentar").andExpect(status().isAccepted())
					.andExpect(jsonPath("$.numeroConta").value(numeroConta))
					.andExpect(jsonPath("$.saldo").value(saldoEsperado));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testSacarValorEntre100E300() {
		String numeroConta = "CC000002";
		BigDecimal valorSaque = new BigDecimal(150);
		String saldoEsperado = calcularSaldoEsperado(valorSaque, new BigDecimal(0.004));

		MovimentarContaRequest request = new MovimentarContaRequest(numeroConta, valorSaque, true);

		try {
			post(request, "/movimentar").andExpect(status().isAccepted())
					.andExpect(jsonPath("$.numeroConta").value(numeroConta))
					.andExpect(jsonPath("$.saldo").value(saldoEsperado));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testSacarValorMaiorQue300() {
		String numeroConta = "CC000003";
		BigDecimal valorSaque = new BigDecimal(350);
		String saldoEsperado = calcularSaldoEsperado(valorSaque, new BigDecimal(0.01));

		MovimentarContaRequest request = new MovimentarContaRequest(numeroConta, valorSaque, true);

		try {
			post(request, "/movimentar").andExpect(status().isAccepted())
					.andExpect(jsonPath("$.numeroConta").value(numeroConta))
					.andExpect(jsonPath("$.saldo").value(saldoEsperado));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testSacarValorPlanoExclusive() {
		String numeroConta = "CC000004";
		BigDecimal valorSaque = new BigDecimal(400);

		MovimentarContaRequest request = new MovimentarContaRequest("CC000004", valorSaque, true);

		try {
			post(request, "/movimentar").andExpect(status().isAccepted())
					.andExpect(jsonPath("$.numeroConta").value(numeroConta))
					.andExpect(jsonPath("$.saldo").value("600.00"));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testDepositar() {
		String numeroConta = "CC000005";
		BigDecimal valorDeposito = new BigDecimal(100);

		MovimentarContaRequest request = new MovimentarContaRequest(numeroConta, valorDeposito, false);

		try {
			post(request, "/movimentar").andExpect(status().isAccepted())
					.andExpect(jsonPath("$.numeroConta").value(numeroConta))
					.andExpect(jsonPath("$.saldo").value(valorDeposito.setScale(2, RoundingMode.CEILING).toString()));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testConsultarHistoricoTransacoes() {
		String numeroConta = "CC000006";

		try {
			finder("/historico/" + numeroConta).andExpect(status().isOk())
					.andExpect(jsonPath("$.numeroConta").value(numeroConta))
					.andExpect(jsonPath("$.historicoTransacoes.length()").value(8));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
