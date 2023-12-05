package br.com.financialtransaction.models;

import java.time.LocalDateTime;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Transacoes {

    @ApiModelProperty(value = "identificador de transação", example = "1", required = true)
    @NotNull(message = "correlationId é obrigatório")
    @DecimalMin(value = "1", message = "Campo não pode estar zerado")
    private long correlationId;

    @ApiModelProperty(value = "data e horario da transação", example = "2023-09-09T14:15:05")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @NotNull(message = "datetime é obrigatório")
    private LocalDateTime datetime;

    @ApiModelProperty(value = "identificador da conta de origem", example = "938485762")
    @NotNull(message = "sourceAccount é obrigatório")
    @DecimalMin(value = "1", message = "Campo não pode estar zerado")
    public long sourceAccount;

    @ApiModelProperty(value = "identificador da conta de destino", example = "2147483649")
    @NotNull(message = "destinationAccount é obrigatório")
    @DecimalMin(value = "1", message = "Campo não pode estar zerado")
    private long destinationAccount;

    @ApiModelProperty(value = "valor da transação", example = "150.00")
    @NotNull(message = "amount é obrigatório")
    @DecimalMin(value = "1", message = "Campo não pode estar zerado")
    private double amount;
}
