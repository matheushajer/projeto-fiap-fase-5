package br.com.fiap.gerenciadorDepedidos.pedidos.records;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DadosRemocaoItemPedidoDTO(
        @NotNull(message = "O ID do pedido é obrigatório.")
        Long pedidoId,

        @NotNull(message = "O ID do produto é obrigatório.")
        Long produtoId,

        @NotNull(message = "A quantidade a ser removida é obrigatória.")
        @Min(value = 1, message = "A quantidade mínima a ser removida deve ser pelo menos 1.")
        Integer quantidade
) {
}