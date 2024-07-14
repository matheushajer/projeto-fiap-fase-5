package br.com.fiap.gerenciadorDepedidos.clientes.controllers.cliente;

import br.com.fiap.gerenciadorDepedidos.clientes.records.cliente.*;
import br.com.fiap.gerenciadorDepedidos.clientes.useCases.cliente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe Controller das operações do Restaurante
 */
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    CriarClienteUseCase criarClienteUseCase;
    @Autowired
    AtualizarNomeClienteUseCase atualizarNomeClienteUseCase;
    @Autowired
    private ListarTodosClientesUseCase listarTodosClientesUseCase;
    @Autowired
    private BuscarClientePorCPFUseCase buscarClientePorCPFUseCase;
    @Autowired
    BuscarCepClienteUseCase buscarCepClienteUseCase;

    @PostMapping("/criar-cliente")
    public ResponseEntity<DadosCriacaoClienteDTO> criarCiente(
            @RequestBody @Validated DadosCriacaoClienteDTO dadosCriacaoClienteDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(criarClienteUseCase.criarCliente(dadosCriacaoClienteDTO));

    }

    @PatchMapping("/{cliente_id}/atualizar-nome")
    public ResponseEntity<DadosRetornoAtualizacaoNomeClienteDTO> atualizacaoNomeCliente(
            @PathVariable Long cliente_id, @RequestBody @Validated DadosAtualizarNomeClienteDTO dadosAtualizarNomeClienteDTO) {

        return ResponseEntity.ok(atualizarNomeClienteUseCase.atualizarNomeCliente(cliente_id, dadosAtualizarNomeClienteDTO));

    }

    @GetMapping
    public Page<ClienteDTO> listarClientes(Pageable page) {
        return listarTodosClientesUseCase.listarClientes(page);
    }

    @GetMapping("/filtro")
    public ResponseEntity<List<ClienteDTO>> buscarClientePorCPF(ClienteCpfFiltroDTO filtro) {
        return ResponseEntity.ok(buscarClientePorCPFUseCase.buscarClientePorCPF(filtro));
    }

    @GetMapping("/buscar-cep/{cliente_id}")
    public ResponseEntity<String> buscarCepCliente(@PathVariable Long cliente_id) {

        return ResponseEntity.ok(buscarCepClienteUseCase.buscarCepCliente(cliente_id));

    }

}