package br.com.fiap.gerenciadorDepedidos.pedidos.records;

import br.com.fiap.gerenciadorDepedidos.pedidos.entities.enuns.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Classe DTO para representar os dados retornados pela API, após
 * criação de um Pedido.
 *
 * @param id
 * @param cliente_id
 * @param itensPedido
 * @param valorDoPedido
 * @param statusPedido
 * @param dataCriacaoPedido
 */
public record DadosRetornoCriacaoPedidoDTO(

        Long id,
        Long cliente_id,
        List<ItemPedidoDTO> itensPedido,
        BigDecimal valorDoPedido,
        StatusPedido statusPedido,
        LocalDateTime dataCriacaoPedido

) {
}
