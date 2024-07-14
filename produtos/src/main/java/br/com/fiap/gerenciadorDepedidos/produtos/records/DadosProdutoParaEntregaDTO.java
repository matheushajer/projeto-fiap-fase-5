package br.com.fiap.gerenciadorDepedidos.produtos.records;

/**
 * Classe para representar os dados do produto usados
 * pelo microserviço de Entregas.
 *
 * @param altura
 * @param largura
 * @param comprimento
 * @param peso
 */
public record DadosProdutoParaEntregaDTO(
        Integer altura,
        Integer largura,
        Integer comprimento,
        Double peso
) {
}
