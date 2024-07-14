package br.com.fiap.gerenciadorDepedidos.entrega.http;

import br.com.fiap.gerenciadorDepedidos.entrega.records.DadosProdutoParaEntregaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * interface para efetuar a comunição entre os microserviços de
 * entregas e produtos.
 */
@FeignClient("produtos")
public interface ProdutoClient {

    @RequestMapping(method = RequestMethod.GET, value = "/produtos/dados-entrega/{id}")
    DadosProdutoParaEntregaDTO dadosProdutoParaEntrega(@PathVariable Long id);

}
