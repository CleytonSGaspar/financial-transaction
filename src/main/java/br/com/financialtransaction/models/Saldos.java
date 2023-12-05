package br.com.financialtransaction.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@Getter
@Setter
public class Saldos {
    @Id
    private long conta;
    private double saldo;
}
