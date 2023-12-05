package br.com.financialtransaction.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import br.com.financialtransaction.models.Transacoes;
import br.com.financialtransaction.service.ExecutarTransacaoFinanceiraService;
import ch.qos.logback.core.util.COWArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ContaSaldoController.class})
@ExtendWith(SpringExtension.class)
class ContaSaldoControllerTest {
    @Autowired
    private ContaSaldoController contaSaldoController;

    @MockBean
    private ExecutarTransacaoFinanceiraService executarTransacaoFinanceiraService;

    /**
     * Method under test: {@link ContaSaldoController#getBalances(List)}
     */
    @Test
    void testGetBalances() throws Exception {
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/contas")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ArrayList<>()));
        MockMvcBuilders.standaloneSetup(contaSaldoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ContaSaldoController#getBalancesJson(List)}
     */
    @Test
    void testGetBalancesJson() {
        ArrayList<Transacoes> transacoesList = new ArrayList<>();
        contaSaldoController.getBalancesJson(transacoesList);
        assertTrue(transacoesList.isEmpty());
    }

    /**
     * Method under test: {@link ContaSaldoController#getBalancesJson(List)}
     */
    @Test
    void testGetBalancesJson2() {
        doNothing().when(executarTransacaoFinanceiraService).transferJson(anyLong(), anyLong(), anyLong(), anyDouble());

        ArrayList<Transacoes> transacoesList = new ArrayList<>();
        transacoesList.add(new Transacoes(1L, LocalDateTime.of(1, 1, 1, 1, 1), 3L, 3L, 10.0d));
        contaSaldoController.getBalancesJson(transacoesList);
        verify(executarTransacaoFinanceiraService).transferJson(anyLong(), anyLong(), anyLong(), anyDouble());
        assertEquals(1, transacoesList.size());
    }

    /**
     * Method under test: {@link ContaSaldoController#getBalancesJson(List)}
     */
    @Test
    void testGetBalancesJson3() {
        doNothing().when(executarTransacaoFinanceiraService).transferJson(anyLong(), anyLong(), anyLong(), anyDouble());

        ArrayList<Transacoes> transacoesList = new ArrayList<>();
        transacoesList.add(new Transacoes(1L, LocalDateTime.of(4, 4, 4, 4, 1), 3L, 3L, 10.0d));
        transacoesList.add(new Transacoes(1L, LocalDateTime.of(1, 1, 1, 1, 1), 3L, 3L, 10.0d));
        contaSaldoController.getBalancesJson(transacoesList);
        verify(executarTransacaoFinanceiraService, atLeast(1)).transferJson(anyLong(), anyLong(), anyLong(), anyDouble());
        assertEquals(2, transacoesList.size());
    }

    /**
     * Method under test: {@link ContaSaldoController#getBalances(List)}
     */
    @Test
    void testGetBalances2() throws Exception {
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/contas", "Uri Variables")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ArrayList<>()));
        MockMvcBuilders.standaloneSetup(contaSaldoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ContaSaldoController#getBalances(List)}
     */
    @Test
    void testGetBalances3() throws Exception {
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .get("/contas", (COWArrayList<Object>) mock(COWArrayList.class))
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ArrayList<>()));
        MockMvcBuilders.standaloneSetup(contaSaldoController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

