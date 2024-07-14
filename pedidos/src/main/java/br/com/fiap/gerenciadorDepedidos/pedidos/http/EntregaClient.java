package br.com.fiap.gerenciadorDepedidos.pedidos.http;

import br.com.fiap.gerenciadorDepedidos.pedidos.records.DadosInsercaoEntregaDTO;
import br.com.fiap.gerenciadorDepedidos.pedidos.records.entregas.DadosSolicitacaoEntregaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * interface para efetuar a comunição entre os microserviços de
 * pedidos e entregas.
 */
@FeignClient("entregas")
public interface EntregaClient {

    @RequestMapping(method = RequestMethod.POST, value = "/entregas/criar-entrega")
    DadosInsercaoEntregaDTO buscarDadosEntrega(@RequestBody DadosSolicitacaoEntregaDTO dadosSolicitacaoEntregaDTO);

}
