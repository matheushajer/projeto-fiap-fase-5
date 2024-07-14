package br.com.fiap.gerenciadorDepedidos.clientes.records.cliente;

import jakarta.validation.constraints.NotBlank;

/**
 * Classe DTO para representar os dados vindos da API para
 * atualizar o nome de um cliente.
 *
 * @param nomeAtualizado
 */
public record DadosAtualizarNomeClienteDTO(
        @NotBlank(message = "O novo nome do cliente é obrigatório!")
        String nomeAtualizado
) {
}
