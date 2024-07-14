package br.com.fiap.gerenciadorDepedidos.clientes.repositories;

import br.com.fiap.gerenciadorDepedidos.clientes.entities.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {
    EnderecoEntity findByClienteEntityIdAndIsEnderecoPrincipalIsTrue(Long clienteId);
}