package br.com.financialtransaction.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.financialtransaction.models.Saldos;
import br.com.financialtransaction.repository.ContasRepository;

@Service
public class ExecutarTransacaoFinanceiraService {

    @Autowired
    private ContasRepository repository;

    /**
     * Transfers a given amount of money from one account to another.
     *
     * @param correlationId The correlation ID of the transaction.
     * @param contaOrigem   The ID of the source account.
     * @param contaDestino  The ID of the destination account.
     * @param valor         The amount of money to transfer.
     * @return A string message representing the result of the transaction.
     */
    public String transfer(long correlationId, long contaOrigem, long contaDestino, double valor) {
        try {
            // Get the balance of the source account
            Saldos contaSaldoOrigem = getSaldo(contaOrigem);

            // Check if the source account has enough balance
            if (contaSaldoOrigem.getSaldo() < valor) {
                return String.format("Transacao numero %s foi cancelada por falta de saldo", correlationId);
            } else {
                // Get the balance of the destination account
                Saldos contaSaldoDestino = getSaldo(contaDestino);

                // Update the balances of the accounts
                contaSaldoOrigem.setSaldo(contaSaldoOrigem.getSaldo() - valor);
                contaSaldoDestino.setSaldo(contaSaldoDestino.getSaldo() + valor);

                // Update the balances in the database and get the updated balances
                double novoSaldoOrigem = updateSaldo(contaSaldoOrigem).getSaldo();
                double novoSaldoDestino = updateSaldo(contaSaldoDestino).getSaldo();

                // Return the success message with the updated balances
                return String.format("Transacao numero %s foi efetivada com "
                        + "sucesso! Novos saldos: Conta Origem: %s | Conta Destino: %s", correlationId,
                        novoSaldoOrigem, novoSaldoDestino);
            }
        } catch (Exception e) {
            return String.format("Transacao numero %s foi cancelada pois não foi encontrada a conta", correlationId);
        }
    }

    /**
     * Transfers a specified amount of money from one account to another.
     *
     * @param correlationId      the ID of the transaction
     * @param originAccount      the account from which the money will be
     *                           transferred
     * @param destinationAccount the account to which the money will be transferred
     * @param amount             the amount of money to transfer
     */
    public void transferJson(long correlationId, long originAccount, long destinationAccount, double amount) {
        // Get the balance of the origin account
        Saldos originAccountBalance = getBalanceJson(originAccount);

        // Check if the origin account has enough balance for the transfer
        if (originAccountBalance.getConta() < amount) {
            System.err.printf("Transaction number %s was cancelled due to insufficient balance \n", correlationId);
        } else {
            // Get the balance of the destination account
            Saldos destinationAccountBalance = getBalanceJson(destinationAccount);

            // Deduct the transfer amount from the origin account's balance
            originAccountBalance.setSaldo(originAccountBalance.getConta() - amount);

            // Add the transfer amount to the destination account's balance
            destinationAccountBalance.setSaldo(destinationAccountBalance.getConta() + amount);

            System.err.printf("Transaction number %s was successfully completed! "
                    + "New balances: Origin Account: %s | Destination Account: %s \n", correlationId,
                    originAccountBalance.getConta(), destinationAccountBalance.getConta());
        }
    }

    /**
     * Retrieves the balance for a given account ID.
     *
     * @param id The ID of the account.
     * @return The balance of the account.
     * @throws IllegalArgumentException If the account does not exist.
     */
    public Saldos getSaldo(long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Conta %s não encontrada", id)));
    }

    /**
     * Updates the saldo of a given account.
     * 
     * @param conta - The account to be updated
     * @return The updated account
     */
    public Saldos updateSaldo(Saldos conta) {
        return this.repository.save(conta);
    }

    /**
     * Retrieves the balance of an account in JSON format.
     *
     * @param id The ID of the account.
     * @return The balance of the account in JSON format.
     */
    public Saldos getBalanceJson(long id) {
        try {
            List<Saldos> list = ReaderJson.readListFrom("src/main/resources/ContaSaldo.json");
            return list.stream()
                    .filter(saldos -> saldos.getConta() == id)
                    .findFirst().get();
        } catch (IOException e) {
            // If the JSON file is not found, print an error message.
            System.err.printf("Conta %s não encontrada", id);
        }
        // If the balance is not found in the JSON file, retrieve it from the
        // repository.
        return this.repository.findById(id).get();
    }
}