package br.com.fiap.gerenciadorDepedidos.produtos.records;

import br.com.fiap.gerenciadorDepedidos.produtos.entities.enuns.CategoriaProduto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

/**
 * Classe para representar os dados vindos pela API,
 * para efetuar a atualização de um produto.
 *
 * @param nome
 * @param descricao
 * @param preco
 * @param quantidade
 * @param categoria
 * @param altura
 * @param largura
 * @param comprimento
 * @param peso
 */
public record DadosAtualizacaoProdutoDTO(

        @JsonProperty
        String nome,
        @JsonProperty
        @Size(max = 255)
        String descricao,
        @JsonProperty
        BigDecimal preco,
        @JsonProperty
        Integer quantidade,
        @JsonProperty
        CategoriaProduto categoria,
        @JsonProperty
        Integer altura,
        @JsonProperty
        Integer largura,
        @JsonProperty
        Integer comprimento,
        @JsonProperty
        Double peso
) {
}
