package br.com.fiap.gerenciadorDepedidos.clientes.controllers.endereco;

import br.com.fiap.gerenciadorDepedidos.clientes.records.endereco.DadosAtualizarEnderecoDTO;
import br.com.fiap.gerenciadorDepedidos.clientes.useCases.endereço.AtualizarEnderecoPrincipalClienteUseCase;
import br.com.fiap.gerenciadorDepedidos.clientes.useCases.endereço.CriarEnderecoClienteUseCase;
import br.com.fiap.gerenciadorDepedidos.clientes.useCases.endereço.DeletarEnderecoClienteUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
@RequiredArgsConstructor
public class EnderecoController {

    private final CriarEnderecoClienteUseCase criarEnderecoClienteUseCase;

    private final DeletarEnderecoClienteUseCase deletarEnderecoClienteUseCase;

    private final AtualizarEnderecoPrincipalClienteUseCase atualizarEnderecoPrincipalClienteUseCase;

    @PostMapping("{clientId}/adicionar-endereco")
    public ResponseEntity<String> criarEnderecoCliente(@PathVariable Long clientId, @RequestBody List<DadosAtualizarEnderecoDTO> atualizarEnderecos) {
        return ResponseEntity.ok(criarEnderecoClienteUseCase.criarEnderecoCliente(clientId, atualizarEnderecos));
    }

    @DeleteMapping("/{id}")
    public void deletarTelefone(@PathVariable Long id) {
        deletarEnderecoClienteUseCase.deleteEnderecoPeloId(id);
    }

    @PutMapping(value = "{clientId}/atualizar-endereco-principal")
    public ResponseEntity<String> atualizarEnderecoPrincipal(@PathVariable Long clientId, @RequestBody @Valid DadosAtualizarEnderecoDTO atualizarEndereco) {
        return ResponseEntity.ok(atualizarEnderecoPrincipalClienteUseCase.atualizarEndercoPrincipal(clientId, atualizarEndereco));
    }
}