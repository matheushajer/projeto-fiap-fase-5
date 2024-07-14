package br.com.fiap.gerenciadorDepedidos.pedidos.repositories;


import br.com.fiap.gerenciadorDepedidos.pedidos.entities.PedidoEntity;
import br.com.fiap.gerenciadorDepedidos.pedidos.entities.enuns.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório para gerenciar as operações de banco de dados para a entidade Pedido.
 */
@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {

    /**
     * Método personalizado para buscar pedidos por seu status.
     *
     * @param status O status do pedido para busca.
     * @return Uma lista de pedidos que correspondem ao status fornecido.
     */
    List<PedidoEntity> findByStatus(StatusPedido status);
}
