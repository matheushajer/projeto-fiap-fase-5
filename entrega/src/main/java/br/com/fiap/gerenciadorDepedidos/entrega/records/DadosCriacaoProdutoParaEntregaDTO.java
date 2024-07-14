package br.com.fiap.gerenciadorDepedidos.entrega.records;

import jakarta.validation.constraints.NotNull;

/**
 * Classe DTO para representar os dados vindos da API de criação
 * de um pedido de entrega, contendo os dados dos produtos.
 *
 * @param produto_id
 * @param quantidade
 */
public record DadosCriacaoProdutoParaEntregaDTO(

        @NotNull(message = "ID do produto é obrigatório")
        Long produto_id,

        @NotNull(message = "A quantidade do produto é obrigatório")
        Integer quantidade
) {
}
