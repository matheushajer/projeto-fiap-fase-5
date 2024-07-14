package br.com.fiap.gerenciadorDepedidos.clientes.records.cliente;

/**
 * Classe DTO para representar os dados retornados, após
 * atualizar o nome de um cliente.
 *
 * @param nomeAntigo
 * @param nomeAtualizado
 */
public record DadosRetornoAtualizacaoNomeClienteDTO(
        String nomeAntigo,
        String nomeAtualizado
) {
}
