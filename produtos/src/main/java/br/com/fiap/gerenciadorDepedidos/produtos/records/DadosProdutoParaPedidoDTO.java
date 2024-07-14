package br.com.fiap.gerenciadorDepedidos.produtos.records;

import java.math.BigDecimal;

/**
 * Classe DTO para representar os dados do produto enviados
 * para o microserviço de Pedidos.
 *
 * @param nome
 * @param preco
 */
public record DadosProdutoParaPedidoDTO(
        String nome,
        BigDecimal preco
) {
}
