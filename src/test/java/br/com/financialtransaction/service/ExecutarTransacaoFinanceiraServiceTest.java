package br.com.financialtransaction.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.financialtransaction.models.Saldos;
import br.com.financialtransaction.repository.ContasRepository;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ExecutarTransacaoFinanceiraService.class})
@ExtendWith(SpringExtension.class)
class ExecutarTransacaoFinanceiraServiceTest {
    @MockBean
    private ContasRepository contasRepository;

    @Autowired
    private ExecutarTransacaoFinanceiraService executarTransacaoFinanceiraService;

    /**
     * Method under test: {@link ExecutarTransacaoFinanceiraService#transfer(long, long, long, double)}
     */
    @Test
    void testTransfer() {
        when(contasRepository.save((Saldos) any())).thenReturn(new Saldos(1L, 10.0d));
        when(contasRepository.findById((Long) any())).thenReturn(Optional.of(new Saldos(1L, 10.0d)));
        assertEquals(
                "Transacao numero 1 foi efetivada com sucesso! Novos saldos: Conta Origem: 10.0 | Conta Destino: 10.0",
                executarTransacaoFinanceiraService.transfer(1L, 1L, 1L, 10.0d));
        verify(contasRepository, atLeast(1)).save((Saldos) any());
        verify(contasRepository, atLeast(1)).findById((Long) any());
    }

    /**
     * Method under test: {@link ExecutarTransacaoFinanceiraService#transfer(long, long, long, double)}
     */
    @Test
    void testTransfer2() {
        when(contasRepository.save((Saldos) any())).thenThrow(new IllegalArgumentException());
        when(contasRepository.findById((Long) any())).thenReturn(Optional.of(new Saldos(1L, 10.0d)));
        assertEquals("Transacao numero 1 foi cancelada pois não foi encontrada a conta",
                     executarTransacaoFinanceiraService.transfer(1L, 1L, 1L, 10.0d));
        verify(contasRepository).save((Saldos) any());
        verify(contasRepository, atLeast(1)).findById((Long) any());
    }

    /**
     * Method under test: {@link ExecutarTransacaoFinanceiraService#transfer(long, long, long, double)}
     */
    @Test
    void testTransfer3() {
        when(contasRepository.save((Saldos) any())).thenReturn(null);
        when(contasRepository.findById((Long) any())).thenReturn(Optional.of(new Saldos(1L, 10.0d)));
        assertEquals("Transacao numero 1 foi cancelada pois não foi encontrada a conta",
                     executarTransacaoFinanceiraService.transfer(1L, 1L, 1L, 10.0d));
        verify(contasRepository).save((Saldos) any());
        verify(contasRepository, atLeast(1)).findById((Long) any());
    }

    /**
     * Method under test: {@link ExecutarTransacaoFinanceiraService#transfer(long, long, long, double)}
     */
    @Test
    void testTransfer4() {
        when(contasRepository.save((Saldos) any())).thenReturn(new Saldos(1L, 10.0d));
        when(contasRepository.findById((Long) any())).thenReturn(Optional.of(new Saldos(1L, 0.5d)));
        assertEquals("Transacao numero 1 foi cancelada por falta de saldo",
                     executarTransacaoFinanceiraService.transfer(1L, 1L, 1L, 10.0d));
        verify(contasRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ExecutarTransacaoFinanceiraService#transfer(long, long, long, double)}
     */
    @Test
    void testTransfer5() {
        Saldos saldos = mock(Saldos.class);
        when(saldos.getSaldo()).thenThrow(new IllegalArgumentException());
        Optional<Saldos> ofResult = Optional.of(saldos);
        when(contasRepository.save((Saldos) any())).thenReturn(new Saldos(1L, 10.0d));
        when(contasRepository.findById((Long) any())).thenReturn(ofResult);
        assertEquals("Transacao numero 1 foi cancelada pois não foi encontrada a conta",
                     executarTransacaoFinanceiraService.transfer(1L, 1L, 1L, 10.0d));
        verify(contasRepository).findById((Long) any());
        verify(saldos).getSaldo();
    }

    /**
     * Method under test: {@link ExecutarTransacaoFinanceiraService#transfer(long, long, long, double)}
     */
    @Test
    void testTransfer6() {
        when(contasRepository.save((Saldos) any())).thenReturn(new Saldos(1L, 10.0d));
        when(contasRepository.findById((Long) any())).thenReturn(Optional.empty());
        new IllegalArgumentException();
        assertEquals("Transacao numero 1 foi cancelada pois não foi encontrada a conta",
                     executarTransacaoFinanceiraService.transfer(1L, 1L, 1L, 10.0d));
        verify(contasRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ExecutarTransacaoFinanceiraService#transferJson(long, long, long, double)}
     */
    @Test
    void testTransferJson() {
        executarTransacaoFinanceiraService.transferJson(1L, 938485762L, 2147483649L, 150.0d);
    }
    @Test
    void testTransferJson2() {
        executarTransacaoFinanceiraService.transferJson(1L, 938485762L, 2147483649L, 3550.0d);
    }
    @Test
    void testTransferJson3() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Integer.parseInt("Conta 9384857 não encontradaTransaction number 1 was cancelled due to insufficient balance");
        });

        String expectedMessage ="Conta 9384857 não encontradaTransaction number 1 was cancelled due to insufficient balance";
        executarTransacaoFinanceiraService.transferJson(1L, 9384857L, 2147483649L, 3550.0d);
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
    /**
     * Method under test: {@link ExecutarTransacaoFinanceiraService#getSaldo(long)}
     */
    @Test
    void testGetSaldo() {
        Saldos saldos = new Saldos(1L, 10.0d);

        when(contasRepository.findById((Long) any())).thenReturn(Optional.of(saldos));
        assertSame(saldos, executarTransacaoFinanceiraService.getSaldo(1L));
        verify(contasRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ExecutarTransacaoFinanceiraService#getSaldo(long)}
     */
    @Test
    void testGetSaldo2() {
        when(contasRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> executarTransacaoFinanceiraService.getSaldo(1L));
        verify(contasRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ExecutarTransacaoFinanceiraService#getSaldo(long)}
     */
    @Test
    void testGetSaldo3() {
        when(contasRepository.findById((Long) any())).thenThrow(new IllegalArgumentException());
        assertThrows(IllegalArgumentException.class, () -> executarTransacaoFinanceiraService.getSaldo(1L));
        verify(contasRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ExecutarTransacaoFinanceiraService#updateSaldo(Saldos)}
     */
    @Test
    void testUpdateSaldo() {
        Saldos saldos = new Saldos(1L, 10.0d);

        when(contasRepository.save((Saldos) any())).thenReturn(saldos);
        assertSame(saldos, executarTransacaoFinanceiraService.updateSaldo(new Saldos(1L, 10.0d)));
        verify(contasRepository).save((Saldos) any());
    }

    /**
     * Method under test: {@link ExecutarTransacaoFinanceiraService#updateSaldo(Saldos)}
     */
    @Test
    void testUpdateSaldo2() {
        when(contasRepository.save((Saldos) any())).thenThrow(new IllegalArgumentException());
        assertThrows(IllegalArgumentException.class,
                     () -> executarTransacaoFinanceiraService.updateSaldo(new Saldos(1L, 10.0d)));
        verify(contasRepository).save((Saldos) any());
    }

}

