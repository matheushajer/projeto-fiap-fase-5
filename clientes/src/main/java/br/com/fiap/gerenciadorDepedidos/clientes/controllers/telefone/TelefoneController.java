package br.com.fiap.gerenciadorDepedidos.clientes.controllers.telefone;

import br.com.fiap.gerenciadorDepedidos.clientes.records.telefone.DadosAtualizarTelefoneDTO;
import br.com.fiap.gerenciadorDepedidos.clientes.useCases.telefone.AtualizarTelefonePrincipalClienteUseCase;
import br.com.fiap.gerenciadorDepedidos.clientes.useCases.telefone.CriarTelefoneClienteUseCase;
import br.com.fiap.gerenciadorDepedidos.clientes.useCases.telefone.DeletarTelefoneClienteUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/telefones")
@RequiredArgsConstructor
public class TelefoneController {

    private final CriarTelefoneClienteUseCase criarTelefoneClienteUseCase;

    private final DeletarTelefoneClienteUseCase deletarTelefoneClienteUseCase;

    private final AtualizarTelefonePrincipalClienteUseCase atualizarTelefonePrincipalClienteUseCase;

    @PostMapping("{clientId}/adicionar-telefone")
    private ResponseEntity<String> criarTelefoneCliente(@PathVariable Long clientId, @RequestBody @Valid List<DadosAtualizarTelefoneDTO> atualizarTelefones) {
        return ResponseEntity.ok(criarTelefoneClienteUseCase.criarTelefoneCliente(clientId, atualizarTelefones));
    }

    @DeleteMapping("/{id}")
    public void deletarTelefone(@PathVariable Long id) {
        deletarTelefoneClienteUseCase.deleteTelefonePeloId(id);

    }

    @PutMapping(value = "{clientId}/atualizar-telefone-principal")
    public ResponseEntity<String> atualizarTelefonePrincipal(@PathVariable Long clientId, @RequestBody @Valid DadosAtualizarTelefoneDTO atualizarTelefone) {
        return ResponseEntity.ok(atualizarTelefonePrincipalClienteUseCase.atualizarTelefonePrincipal(clientId, atualizarTelefone));
    }
}
