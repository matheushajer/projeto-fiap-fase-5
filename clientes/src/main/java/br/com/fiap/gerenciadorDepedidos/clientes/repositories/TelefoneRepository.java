package br.com.fiap.gerenciadorDepedidos.clientes.repositories;

import br.com.fiap.gerenciadorDepedidos.clientes.entities.TelefoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TelefoneRepository extends JpaRepository<TelefoneEntity, Long> {
    Optional<TelefoneEntity> findByclienteEntityIdAndIsTelefonePrincialIsTrue(Long clienteId);
}