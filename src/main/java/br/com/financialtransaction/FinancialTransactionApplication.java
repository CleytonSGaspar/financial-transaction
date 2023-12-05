package br.com.financialtransaction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.financialtransaction.controller.ContaSaldoController;
import br.com.financialtransaction.models.Transacoes;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class FinancialTransactionApplication {

	private static ContaSaldoController executor = new ContaSaldoController();

	public static void main(String[] args) {
		// Run the FinancialTransactionApplication
		SpringApplication.run(FinancialTransactionApplication.class, args);

		// Create a list of transactions
		List<Transacoes> transacoes = new ArrayList<Transacoes>();

		// Add transactions to the list
		transacoes.add(new Transacoes(1, LocalDateTime.of(2023, 9, 9, 14, 15, 0), 938485762L, 2147483649L, 100.0));
		transacoes.add(new Transacoes(2, LocalDateTime.of(2023, 9, 9, 14, 15, 5), 2147483649L, 210385733L, 149.0));
		transacoes.add(new Transacoes(3, LocalDateTime.of(2023, 9, 9, 14, 15, 29), 347586970L, 238596054L, 1100.0));
		transacoes.add(new Transacoes(4, LocalDateTime.of(2023, 9, 9, 14, 17, 00), 675869708L, 210385733L, 5300.0));
		transacoes.add(new Transacoes(5, LocalDateTime.of(2023, 9, 9, 14, 18, 00), 238596054L, 674038564L, 1489.0));
		transacoes.add(new Transacoes(6, LocalDateTime.of(2023, 9, 9, 14, 19, 00), 938485762L, 2147483649L, 44.0));
		transacoes.add(new Transacoes(7, LocalDateTime.of(2023, 9, 9, 14, 19, 01), 573659065L, 675869708L, 150.0));

		// Get balances in JSON format
		executor.getBalancesJson(transacoes);
	}
}