package br.com.fiap.gerenciadorDepedidos.produtos.records;

import br.com.fiap.gerenciadorDepedidos.produtos.entities.enuns.CategoriaProduto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

/**
 * Classe para representar os dados vindos pela API,
 * para efetuar a criação de um produto.
 */
public record DadosCriacaoProdutoDTO(

        @NotBlank(message = "O nome do produto é obrigatório")
        String nome,
        @NotBlank(message = "O produto deve ter uma descrição inicial")
        @Size(max = 255)
        String descricao,
        @NotNull(message = "O preço do produto é obrigatório!")
        BigDecimal preco,
        @NotNull(message = "Obrigatório informar a quantidade do produto sendo cadastrado")
        Integer quantidade,
        @NotNull(message = "Obrigatório informar a categoria do produto")
        CategoriaProduto categoria,
        @NotNull(message = "Obrigatório informar a altura do produto sendo cadastrado")
        Integer altura,
        @NotNull(message = "Obrigatório informar a largura do produto sendo cadastrado")
        Integer largura,
        @NotNull(message = "Obrigatório informar o comprimento do produto sendo cadastrado")
        Integer comprimento,
        @NotNull(message = "Obrigatório informar o peso do produto sendo cadastrado")
        Double peso
) {
}
