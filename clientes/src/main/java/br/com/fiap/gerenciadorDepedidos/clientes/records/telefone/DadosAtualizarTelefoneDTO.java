package br.com.fiap.gerenciadorDepedidos.clientes.records.telefone;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarTelefoneDTO(
        @NotNull(message = "Obrigatório informar o ddi do telefone")
        int ddi,
        @NotNull(message = "Obrigatório informar o ddd do telefone")
        int ddd,
        @NotNull(message = "Obrigatório informar o número do telefone")
        int numero
) {
}