package br.com.fiap.gerenciadorDepedidos.pedidos.records;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosInsercaoEntregaDTO(

        @Min(value = 1, message = "O prazo de entrega deve ser pelo menos 1 dia.")
        Integer prazoDeEntrega,

        @NotNull(message = "O valor do frete é obrigatório.")
        @Min(value = 0, message = "O valor do frete deve ser maior ou igual a zero.")
        BigDecimal frete,

        @NotNull(message = "O código de rastreio é obrigatório.")
        Long codigoDeRastreio
) {
}
