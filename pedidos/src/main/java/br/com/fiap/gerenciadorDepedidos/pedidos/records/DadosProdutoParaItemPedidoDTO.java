package br.com.fiap.gerenciadorDepedidos.pedidos.records;

import java.math.BigDecimal;

public record DadosProdutoParaItemPedidoDTO(
        String nome,
        BigDecimal preco
) {
}
