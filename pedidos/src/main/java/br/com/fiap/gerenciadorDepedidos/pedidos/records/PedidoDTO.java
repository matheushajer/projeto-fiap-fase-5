package br.com.fiap.gerenciadorDepedidos.pedidos.records;

import br.com.fiap.gerenciadorDepedidos.pedidos.entities.enuns.StatusPedido;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoDTO(
        @NotNull(message = "O ID do pedido é obrigatório.")
        Long id,

        @NotNull(message = "O ID do cliente é obrigatório.")
        Long clienteId,

        @NotNull(message = "Os itens do pedido são obrigatórios.")
        List<ItemPedidoDTO> itensPedido,

        @NotNull(message = "O valor do pedido é obrigatório.")
        @Min(value = 0, message = "O valor do pedido deve ser maior ou igual a zero.")
        BigDecimal valorPedido,

        @Min(value = 1, message = "O prazo de entrega deve ser pelo menos 1 dia.")
        Integer prazoDeEntrega,

        @NotNull(message = "O valor do frete é obrigatório.")
        @Min(value = 0, message = "O valor do frete deve ser maior ou igual a zero.")
        BigDecimal frete,

        @NotNull(message = "O valor total com frete é obrigatório.")
        BigDecimal valorComFrete,

        @NotNull(message = "O status do pedido é obrigatório.")
        StatusPedido status,

        @Min(value = 10000000, message = "O código de rastreio deve ser um número de 8 dígitos.")
        Integer codigoDeRastreio,

        @NotNull(message = "A data de criação do pedido é obrigatória.")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime dataCriacaoPedido
) {
}
