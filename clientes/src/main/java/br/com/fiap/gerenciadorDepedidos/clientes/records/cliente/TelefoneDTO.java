package br.com.fiap.gerenciadorDepedidos.clientes.records.cliente;

public record TelefoneDTO(
        Long id,
        int ddi,
        int ddd,
        int numero,
        boolean isTelefonePrincipal
) {
}