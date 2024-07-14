package br.com.fiap.gerenciadorDepedidos.clientes.repositories;

import br.com.fiap.gerenciadorDepedidos.clientes.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long>, JpaSpecificationExecutor<ClienteEntity> {

    ClienteEntity findByCpf(String cpf);

    boolean existsByCpf(String cpf);

    @Query("SELECT e.cep FROM ClienteEntity c JOIN c.enderecoEntity e WHERE c.id = :clienteId AND e.isEnderecoPrincipal = true")
    String findCepPrincipalByClienteId(Long clienteId);

}
