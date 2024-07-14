package br.com.fiap.gerenciadorDepedidos.pedidos.adapters;


import br.com.fiap.gerenciadorDepedidos.pedidos.entities.ItemPedidoEntity;
import br.com.fiap.gerenciadorDepedidos.pedidos.entities.PedidoEntity;
import br.com.fiap.gerenciadorDepedidos.pedidos.records.*;
import br.com.fiap.gerenciadorDepedidos.pedidos.records.entregas.DadosProdutoSolicitacaoEntregaDTO;
import br.com.fiap.gerenciadorDepedidos.pedidos.records.entregas.DadosSolicitacaoEntregaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe adapter para conversões do PedidoEntity.
 */
@Service
public class PedidoAdapter {

    @Autowired
    ItemPedidoAdapter itemPedidoAdapter;

    /**
     * Método adapter para pegar os dados vindos da API e criar um PedidoEntity.
     *
     * @param dadosCriacaoPedidoDTO         Objeto com os dados vindos da API, para criação de um pedido.
     * @param dadosProdutoParaItemPedidoDTO Objeto vindo do serviço de Produtos, para criação de um pedido.
     * @return PedidoEntity Objeto PedidoEntity, com os dados tratados e convertidos.
     */
    public PedidoEntity converterParaEntity(
            DadosCriacaoPedidoDTO dadosCriacaoPedidoDTO, List<DadosProdutoParaItemPedidoDTO> dadosProdutoParaItemPedidoDTO) {

        PedidoEntity pedidoEntity = new PedidoEntity();
        List<ItemPedidoEntity> itensPedidoEntity = new ArrayList<>();

        pedidoEntity.setClienteId(dadosCriacaoPedidoDTO.clienteId());

        int index = 0;
        for (ItemPedidoDTO itemPedidoDTO : dadosCriacaoPedidoDTO.itensPedido()) {

            ItemPedidoEntity itemPedidoEntity = new ItemPedidoEntity();

            itemPedidoEntity.setPedidoEntity(pedidoEntity);
            itemPedidoEntity.setProdutoId(itemPedidoDTO.produtoId());
            itemPedidoEntity.setQuantidade(itemPedidoDTO.quantidade());


            itemPedidoEntity.setNome(dadosProdutoParaItemPedidoDTO.get(index).nome());
            itemPedidoEntity.setPreco(dadosProdutoParaItemPedidoDTO.get(index).preco());

            itensPedidoEntity.add(itemPedidoEntity);

            pedidoEntity.setItensPedido(itensPedidoEntity);

            index++;

        }

        pedidoEntity.calcularValorTotalPedido(itensPedidoEntity);

        return pedidoEntity;
    }

    /**
     * Método adapter para converter um PedidoEntity para um DadosRetornoCriacaoPedidoDTO.
     *
     * @param pedidoEntity Objeto com os dados a serem convertidos.
     * @return DadosRetornoCriacaoPedidoDTO Objeto com os dados tratados e convertidos.
     */
    public DadosRetornoCriacaoPedidoDTO converterParaDadosRetornoCriacaoPedidoDTO(PedidoEntity pedidoEntity) {
        return new DadosRetornoCriacaoPedidoDTO(
                pedidoEntity.getId(),
                pedidoEntity.getClienteId(),
                converterListItemPedidoEntityParaListItemPedidoDTO(pedidoEntity.getItensPedido()),
                pedidoEntity.getValorPedido(),
                pedidoEntity.getStatus(),
                pedidoEntity.getDataCriacaoPedido()
        );
    }

    /**
     * Método auxuliar para converter uma lista de ItemPedidoEntity para
     *
     * @param itemPedidoEntities
     * @return
     */
    private List<ItemPedidoDTO> converterListItemPedidoEntityParaListItemPedidoDTO
    (List<ItemPedidoEntity> itemPedidoEntities) {

        List<ItemPedidoDTO> itemPedidoDTOs = new ArrayList<>();

        itemPedidoEntities.forEach(item ->
                itemPedidoDTOs.add(new ItemPedidoDTO(
                        item.getProdutoId(),
                        item.getNome(),
                        item.getPreco(),
                        item.getQuantidade()
                ))
        );

        return itemPedidoDTOs;

    }

    public DadosSolicitacaoEntregaDTO extrairDadosSolicitacaoEntrega(PedidoEntity pedidoEntity) {

        List<DadosProdutoSolicitacaoEntregaDTO> dadosProduto = new ArrayList<>();

        int index = 0;
        for (ItemPedidoEntity itemPedido : pedidoEntity.getItensPedido()) {

            DadosProdutoSolicitacaoEntregaDTO dadoProduto = new DadosProdutoSolicitacaoEntregaDTO(
                    itemPedido.getProdutoId(),
                    itemPedido.getQuantidade()
            );

            dadosProduto.add(dadoProduto);

            index++;
        }

        return new DadosSolicitacaoEntregaDTO(pedidoEntity.getId(), pedidoEntity.getClienteId(), dadosProduto);

    }


    // Converte um PedidoEntity para um PedidoDTO, incluindo uma lista de ItemPedidoDTO.
    public PedidoDTO converterPedidoEntityParaPedidoDTO(PedidoEntity pedidoEntity) {
        return new PedidoDTO(
                pedidoEntity.getId(),
                pedidoEntity.getClienteId(),
                itemPedidoAdapter.converterParaListaDeDTO(pedidoEntity.getItensPedido()),
                pedidoEntity.getValorPedido(),
                pedidoEntity.getPrazoDeEntrega(),
                pedidoEntity.getFrete(),
                pedidoEntity.getValorComFrete(),
                pedidoEntity.getStatus(),
                pedidoEntity.getCodigoDeRastreio(),
                pedidoEntity.getDataCriacaoPedido()
        );
    }

}
