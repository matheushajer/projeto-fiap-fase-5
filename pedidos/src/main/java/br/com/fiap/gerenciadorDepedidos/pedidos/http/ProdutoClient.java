package br.com.fiap.gerenciadorDepedidos.pedidos.http;

import br.com.fiap.gerenciadorDepedidos.pedidos.records.DadosProdutoParaItemPedidoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * interface para efetuar a comunição entre os microserviços de
 * pedidos e produtos.
 */
@FeignClient("produtos")
public interface ProdutoClient {

    @RequestMapping(method = RequestMethod.GET, value = "/produtos/dados-pedido/{id}")
    DadosProdutoParaItemPedidoDTO buscarDadosParaItemPedido(@PathVariable Long id);

}
