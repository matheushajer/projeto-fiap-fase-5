package br.com.fiap.gerenciadorDepedidos.entrega.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * interface para efetuar a comunição entre os microserviços de
 * entregas e produtos.
 */
@FeignClient("clientes")
public interface ClienteClient {

    @RequestMapping(method = RequestMethod.GET, value = "/clientes/buscar-cep/{id}")
    String buscarCepCliente(@PathVariable Long id);

}
