package br.com.fiap.gerenciadorDepedidos.entrega.http;

import br.com.fiap.gerenciadorDepedidos.entrega.records.DadosRetornoCriacaoEntregaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("pedidos")
public interface PedidoClient {

    @RequestMapping(method = RequestMethod.PUT, value = "/pedidos/inserir-dados-entrega/{pedidoId}")
    void dadosEntregaParaPedido(@PathVariable Long pedidoId, @RequestBody DadosRetornoCriacaoEntregaDTO dadosRetornoCriacaoEntregaDTO);

}
