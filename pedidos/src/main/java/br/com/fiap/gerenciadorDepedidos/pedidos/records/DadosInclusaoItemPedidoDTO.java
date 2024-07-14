package br.com.fiap.gerenciadorDepedidos.pedidos.records;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosInclusaoItemPedidoDTO(
        @NotNull(message = "O ID do pedido é obrigatório.")
        Long pedidoId,

        @NotNull(message = "A lista de novos itens é obrigatória.")
        List<ItemPedidoDTO> novosItens
) {
}