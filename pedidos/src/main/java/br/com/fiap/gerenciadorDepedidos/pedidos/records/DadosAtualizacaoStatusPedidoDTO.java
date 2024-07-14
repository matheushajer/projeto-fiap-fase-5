package br.com.fiap.gerenciadorDepedidos.pedidos.records;

import br.com.fiap.gerenciadorDepedidos.pedidos.entities.enuns.StatusPedido;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoStatusPedidoDTO(

        @NotNull(message = "O Status do pedido é obrigatório.")
        StatusPedido status

) {
}