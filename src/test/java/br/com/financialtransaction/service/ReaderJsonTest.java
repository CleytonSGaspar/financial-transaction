package br.com.financialtransaction.service;

import br.com.financialtransaction.models.Saldos;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ReaderJsonTest {
    /**
     * Method under test: {@link ReaderJson#readOneFrom(String)}
     */
    @Test
    void testReadOneFrom() throws IOException {
        List<Saldos> saldos = ReaderJson.readListFrom("src/main/resources/ContaSaldo.json");
        assertEquals(180.00, saldos.get(0).getSaldo());
    }
}

