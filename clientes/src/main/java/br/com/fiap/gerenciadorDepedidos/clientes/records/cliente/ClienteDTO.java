package br.com.fiap.gerenciadorDepedidos.clientes.records.cliente;

import java.util.List;

public record ClienteDTO(
        Long id,
        String nome,
        String cpf,
        List<TelefoneDTO> telefones,
        List<EnderecoDTO> enderecos
) {
}