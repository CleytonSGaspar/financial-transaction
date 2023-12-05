package br.com.financialtransaction.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class TransacoesTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Transacoes#Transacoes(long, LocalDateTime, long, long, double)}
     *   <li>{@link Transacoes#setAmount(double)}
     *   <li>{@link Transacoes#setCorrelationId(long)}
     *   <li>{@link Transacoes#setDatetime(LocalDateTime)}
     *   <li>{@link Transacoes#setDestinationAccount(long)}
     *   <li>{@link Transacoes#setSourceAccount(long)}
     *   <li>{@link Transacoes#getAmount()}
     *   <li>{@link Transacoes#getCorrelationId()}
     *   <li>{@link Transacoes#getDatetime()}
     *   <li>{@link Transacoes#getDestinationAccount()}
     *   <li>{@link Transacoes#getSourceAccount()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Transacoes actualTransacoes = new Transacoes(1L, LocalDateTime.of(1, 1, 1, 1, 1), 3L, 3L, 10.0d);
        actualTransacoes.setAmount(10.0d);
        actualTransacoes.setCorrelationId(1L);
        LocalDateTime ofResult = LocalDateTime.of(1, 1, 1, 1, 1);
        actualTransacoes.setDatetime(ofResult);
        actualTransacoes.setDestinationAccount(3L);
        actualTransacoes.setSourceAccount(3L);
        assertEquals(10.0d, actualTransacoes.getAmount());
        assertEquals(1L, actualTransacoes.getCorrelationId());
        assertSame(ofResult, actualTransacoes.getDatetime());
        assertEquals(3L, actualTransacoes.getDestinationAccount());
        assertEquals(3L, actualTransacoes.getSourceAccount());
    }

    /**
     * Method under test: {@link Transacoes#Transacoes(long, LocalDateTime, long, long, double)}
     */
    @Test
    void testConstructor2() {
        Transacoes actualTransacoes = new Transacoes(1L, LocalDateTime.of(1, 1, 1, 1, 1), 3L, 3L, 10.0d);

        assertEquals(10.0d, actualTransacoes.getAmount());
        assertEquals(3L, actualTransacoes.getSourceAccount());
        assertEquals(3L, actualTransacoes.getDestinationAccount());
        assertEquals(1L, actualTransacoes.getCorrelationId());
        assertEquals("01:01", actualTransacoes.getDatetime().toLocalTime().toString());
    }
}

