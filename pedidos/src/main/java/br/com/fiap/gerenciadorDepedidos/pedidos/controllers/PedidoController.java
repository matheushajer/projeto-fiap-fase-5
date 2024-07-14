package br.com.fiap.gerenciadorDepedidos.pedidos.controllers;

import br.com.fiap.gerenciadorDepedidos.pedidos.records.DadosAtualizacaoStatusPedidoDTO;
import br.com.fiap.gerenciadorDepedidos.pedidos.records.DadosCriacaoPedidoDTO;
import br.com.fiap.gerenciadorDepedidos.pedidos.records.DadosInsercaoEntregaDTO;
import br.com.fiap.gerenciadorDepedidos.pedidos.records.DadosRetornoCriacaoPedidoDTO;
import br.com.fiap.gerenciadorDepedidos.pedidos.useCases.AtualizarStatusPedidoUseCase;
import br.com.fiap.gerenciadorDepedidos.pedidos.useCases.CriarPedidoUseCase;
import br.com.fiap.gerenciadorDepedidos.pedidos.useCases.InserirDadosEntregaUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Classe Controller para gerenciar as operações de pedidos.
 * Prove endpoints para criação, atualização e gerenciamento de itens de pedidos.
 */
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    CriarPedidoUseCase criarPedidoUseCase;
    @Autowired
    AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;
    @Autowired
    InserirDadosEntregaUseCase inserirDadosEntregaUseCase;

    /**
     * Endpoint para criar um novo pedido.
     * Recebe um DTO com os dados do pedido e retorna o pedido criado com status HTTP 201.
     */
    @PostMapping("/criar-pedido")
    public ResponseEntity<DadosRetornoCriacaoPedidoDTO> criarPedido(@RequestBody DadosCriacaoPedidoDTO dadosCriacaoPedido) {

        return ResponseEntity.ok(criarPedidoUseCase.criarPedido(dadosCriacaoPedido));

    }

    /**
     * Endpoint para atualizar o status de um pedido existente.
     * Recebe o ID do pedido e um DTO com o novo status, retornando o pedido atualizado.
     */
    @PatchMapping("/atualizar-status-pedido/{pedidoId}")
    public ResponseEntity<Object> atualizarStatusPedido(
            @PathVariable Long pedidoId,
            @RequestBody @Validated DadosAtualizacaoStatusPedidoDTO dadosAtualizacaoStatusPedidoDTO) {

        atualizarStatusPedidoUseCase.atualizarStatus(pedidoId, dadosAtualizacaoStatusPedidoDTO);

        return ResponseEntity.ok().build();

    }

    /**
     * Endpoint para inserir dados de entrega em um pedido existente.
     * Recebe o ID do pedido e um DTO com os dados de entrega a serem atualizados.
     */
    @PutMapping("/inserir-dados-entrega/{pedidoId}")
    public ResponseEntity<Void> inserirDadosEntrega(
            @PathVariable Long pedidoId, @RequestBody @Validated DadosInsercaoEntregaDTO dadosEntregaDTO) {

        inserirDadosEntregaUseCase.inserirDadosEntrega(pedidoId, dadosEntregaDTO);

        return ResponseEntity.ok().build();

    }

}
