package br.com.fiap.gerenciadorDepedidos.pedidos.repositories;

import br.com.fiap.gerenciadorDepedidos.pedidos.entities.ItemPedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedidoEntity, Long> {
    ItemPedidoEntity findByPedidoEntity_IdAndId(Long pedidoId, Long id);
}
