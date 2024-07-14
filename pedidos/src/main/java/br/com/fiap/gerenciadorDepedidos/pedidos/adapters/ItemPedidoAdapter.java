package br.com.fiap.gerenciadorDepedidos.pedidos.adapters;

import br.com.fiap.gerenciadorDepedidos.pedidos.entities.ItemPedidoEntity;
import br.com.fiap.gerenciadorDepedidos.pedidos.records.ItemPedidoDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemPedidoAdapter {

    // Converte uma lista de entidades de ItemPedido para uma lista de DTOs de ItemPedido.

    public List<ItemPedidoDTO> converterParaListaDeDTO(List<ItemPedidoEntity> itemPedidoEntity) {

        List<ItemPedidoDTO> itemPedidoDTO = new ArrayList<>();
        itemPedidoEntity.forEach(itemPedido ->
                itemPedidoDTO.add(new ItemPedidoDTO(
                        itemPedido.getProdutoId(),
                        itemPedido.getNome(),
                        itemPedido.getPreco(),
                        itemPedido.getQuantidade()
                ))
        );

        return itemPedidoDTO;
    }

    // Converte uma lista de entidades de ItemPedido para uma lista de DTOs de ItemPedido.

    public List<ItemPedidoDTO> converterListaItemPedidoEntityParaListaItemPedidoDTO(List<ItemPedidoEntity> itemPedidoEntities) {
        List<ItemPedidoDTO> itenspedidos = new ArrayList<>();

        itemPedidoEntities.forEach(itemPedido -> {
            ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO(itemPedido.getProdutoId(), itemPedido.getNome(), itemPedido.getPreco(), itemPedido.getQuantidade());
            itenspedidos.add(itemPedidoDTO);

        });
        return itenspedidos;
    }
}
