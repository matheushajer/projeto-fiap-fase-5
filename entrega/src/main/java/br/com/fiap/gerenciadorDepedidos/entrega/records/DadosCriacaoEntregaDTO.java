package br.com.fiap.gerenciadorDepedidos.entrega.records;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * Classe DTO para representar os dados que vem da API
 * para criar um pedido de entrega.
 *
 * @param pedido_id
 * @param cliente_id
 * @param produtos
 */
public record DadosCriacaoEntregaDTO(

        @NotNull(message = "ID do pedido é obrigatório")
        Long pedido_id,

        @NotNull(message = "ID do cliente é obrigatório")
        Long cliente_id,

        @Valid
        List<DadosCriacaoProdutoParaEntregaDTO> produtos

) {
}
