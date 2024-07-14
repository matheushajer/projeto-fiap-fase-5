package br.com.fiap.gerenciadorDepedidos.entrega.controllers;

import br.com.fiap.gerenciadorDepedidos.entrega.records.DadosCriacaoEntregaDTO;
import br.com.fiap.gerenciadorDepedidos.entrega.records.DadosRetornoCriacaoEntregaDTO;
import br.com.fiap.gerenciadorDepedidos.entrega.useCases.CriarEntregaUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

    @Autowired
    CriarEntregaUseCase criarEntregaUseCase;

    @PostMapping("/criar-entrega")
    public ResponseEntity<DadosRetornoCriacaoEntregaDTO> criarPedidoDeEntrega(@RequestBody DadosCriacaoEntregaDTO dadosCriacaoEntregaDTO) throws IOException, InterruptedException {

        return ResponseEntity.ok(criarEntregaUseCase.criarPedidoDeEntrega(dadosCriacaoEntregaDTO));

    }

}
