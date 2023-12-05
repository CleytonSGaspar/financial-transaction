package br.com.financialtransaction.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.financialtransaction.models.Transacoes;
import br.com.financialtransaction.service.ExecutarTransacaoFinanceiraService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class ContaSaldoController {
    @Autowired
    private ExecutarTransacaoFinanceiraService service = new ExecutarTransacaoFinanceiraService();

    /**
     * Retrieves the balance for a list of transactions.
     * 
     * @param transacoes The list of transactions.
     * @return The response entity containing the list of balances.
     */

    @GetMapping("/contas")
    @ApiOperation(value = "Get Balances", notes = "Retrieves the balance for a list of transactions.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieved the list of balances"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    public ResponseEntity<List<String>> getBalances(@RequestBody List<Transacoes> transacoes) {
        // Sort the transactions by datetime
        transacoes.sort(Comparator.comparing(Transacoes::getDatetime));

        // Create a new list to store the balances
        List<String> balances = transacoes.stream()
                .map(item -> service.transfer(item.getCorrelationId(), item.getSourceAccount(),
                        item.getDestinationAccount(), item.getAmount()))
                .collect(Collectors.toList());

        // Return the list of balances in the response entity
        return ResponseEntity.ok().body(balances);
    }

    /**
     * Sorts the list of transactions by datetime and transfers the amount from
     * source account to destination account using the service.
     * 
     * @param transacoes The list of transactions.
     */
    public void getBalancesJson(@RequestBody List<Transacoes> transacoes) {
        // Sort the transactions by datetime
        transacoes.sort(Comparator.comparing(Transacoes::getDatetime));

        // Transfer the amount from source account to destination account for each
        // transaction
        transacoes.forEach(item -> service.transferJson(
                item.getCorrelationId(),
                item.getSourceAccount(),
                item.getDestinationAccount(),
                item.getAmount()));
    }
}
