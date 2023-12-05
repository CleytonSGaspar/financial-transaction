package br.com.financialtransaction.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SaldosTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Saldos#Saldos(long, double)}
     *   <li>{@link Saldos#setConta(long)}
     *   <li>{@link Saldos#setSaldo(double)}
     *   <li>{@link Saldos#getConta()}
     *   <li>{@link Saldos#getSaldo()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Saldos actualSaldos = new Saldos(1L, 10.0d);
        actualSaldos.setConta(1L);
        actualSaldos.setSaldo(10.0d);
        assertEquals(1L, actualSaldos.getConta());
        assertEquals(10.0d, actualSaldos.getSaldo());
    }

    /**
     * Method under test: {@link Saldos#Saldos(long, double)}
     */
    @Test
    void testConstructor2() {
        Saldos actualSaldos = new Saldos(1L, 10.0d);

        assertEquals(1L, actualSaldos.getConta());
        assertEquals(10.0d, actualSaldos.getSaldo());
    }
}

