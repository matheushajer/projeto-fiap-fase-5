package br.com.fiap.gerenciadorDepedidos.clientes.useCases.endereço;

import br.com.fiap.gerenciadorDepedidos.clientes.repositories.EnderecoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class DeletarEnderecoClienteUseCase {

    private final EnderecoRepository enderecoRepository;

    /**
     * Método para efetuar a deleção de um endereco no banco.
     *
     * @param id ID do endereco a ser deletado.
     */
    public void deleteEnderecoPeloId(Long id) {
        enderecoRepository.deleteById(id);
    }
}