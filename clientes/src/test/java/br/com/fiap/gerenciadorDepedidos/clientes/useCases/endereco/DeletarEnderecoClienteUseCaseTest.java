package br.com.fiap.gerenciadorDepedidos.clientes.useCases.endereco;

import br.com.fiap.gerenciadorDepedidos.clientes.repositories.EnderecoRepository;
import br.com.fiap.gerenciadorDepedidos.clientes.useCases.endereço.DeletarEnderecoClienteUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeletarEnderecoClienteUseCaseTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private DeletarEnderecoClienteUseCase deletarEnderecoClienteUseCase;

    @Test
    void deleteEnderecoPeloId_DeveChamarDeleteByIdDoRepositorio() {
        Long enderecoId = 1L;

        deletarEnderecoClienteUseCase.deleteEnderecoPeloId(enderecoId);

        verify(enderecoRepository, times(1)).deleteById(enderecoId);
    }
}
