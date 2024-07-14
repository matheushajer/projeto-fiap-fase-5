package br.com.fiap.gerenciadorDepedidos.entrega.repositories;

import br.com.fiap.gerenciadorDepedidos.entrega.entites.EntregaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntregaRepository extends JpaRepository<EntregaEntity, Long> {
}
