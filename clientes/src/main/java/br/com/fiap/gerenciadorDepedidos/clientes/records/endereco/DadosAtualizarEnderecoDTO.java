package br.com.fiap.gerenciadorDepedidos.clientes.records.endereco;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizarEnderecoDTO(
        @NotBlank(message = "É obrigatório informar o cep do endereço")
        String cep,
        @NotBlank(message = "É obrigatório informar o logradouro do endereço")
        String logradouro,
        @NotBlank(message = "É obrigatório informar o número do endereço")
        String numero,
        String complemento,
        @NotBlank(message = "É obrigatório informar o bairro do endereço")
        String bairro,
        @NotBlank(message = "É obrigatório informar a cidade do endereço")
        String cidade,
        @NotBlank(message = "É obrigatório informar o uf do endereço")
        String uf
) {
}