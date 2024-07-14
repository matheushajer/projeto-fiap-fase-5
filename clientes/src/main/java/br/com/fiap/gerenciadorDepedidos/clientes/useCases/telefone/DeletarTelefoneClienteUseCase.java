package br.com.fiap.gerenciadorDepedidos.clientes.useCases.telefone;

import br.com.fiap.gerenciadorDepedidos.clientes.repositories.TelefoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeletarTelefoneClienteUseCase {

    private final TelefoneRepository telefoneRepository;


    /**
     * Método para efetuar a deleção de um telefone no banco.
     *
     * @param id ID do telefone a ser deletado.
     */
    public void deleteTelefonePeloId(Long id) {
        telefoneRepository.deleteById(id);
    }
}
