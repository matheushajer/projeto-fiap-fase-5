package br.com.fiap.gerenciadorDepedidos.pedidos.records;

import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Classe DTO para representar os dados da API para criação de um pedido.
 *
 * @param clienteId
 * @param itensPedido
 * @param dataCriacaoPedido
 */
public record DadosCriacaoPedidoDTO(

        @NotNull(message = "O ID do cliente é obrigatório.")
        Long clienteId,

        @NotNull(message = "A lista de itens do pedido é obrigatória.")
        List<ItemPedidoDTO> itensPedido,

        @NotNull(message = "A data de criação do pedido é obrigatória.")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        LocalDateTime dataCriacaoPedido
) {
}
