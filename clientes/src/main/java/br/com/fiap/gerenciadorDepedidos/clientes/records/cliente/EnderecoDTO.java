package br.com.fiap.gerenciadorDepedidos.clientes.records.cliente;

public record EnderecoDTO(
        Long id,
        String cep,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String uf,
        boolean isEnderecoPrincipal
) {
}