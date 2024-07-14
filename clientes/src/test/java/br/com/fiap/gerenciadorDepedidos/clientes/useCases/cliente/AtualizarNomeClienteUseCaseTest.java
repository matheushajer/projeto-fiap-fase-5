package br.com.fiap.gerenciadorDepedidos.clientes.useCases.cliente;

import br.com.fiap.gerenciadorDepedidos.clientes.entities.ClienteEntity;
import br.com.fiap.gerenciadorDepedidos.clientes.records.cliente.DadosAtualizarNomeClienteDTO;
import br.com.fiap.gerenciadorDepedidos.clientes.records.cliente.DadosRetornoAtualizacaoNomeClienteDTO;
import br.com.fiap.gerenciadorDepedidos.clientes.repositories.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizarNomeClienteUseCaseTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private AtualizarNomeClienteUseCase atualizarNomeClienteUseCase;

    @Test
    void testAtualizarNomeClienteClientePresente() {
        Long clienteId = 1L;
        String novoNome = "Novo Nome";
        String nomeAntigo = "Antigo Nome";
        DadosAtualizarNomeClienteDTO dadosAtualizar = new DadosAtualizarNomeClienteDTO(novoNome);
        ClienteEntity clienteEntity = new ClienteEntity(clienteId, nomeAntigo);

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(clienteEntity));

        DadosRetornoAtualizacaoNomeClienteDTO retorno = atualizarNomeClienteUseCase.atualizarNomeCliente(clienteId, dadosAtualizar);

        assertEquals(novoNome, clienteEntity.getNome());
        assertEquals(nomeAntigo, retorno.nomeAntigo());
        assertEquals(novoNome, retorno.nomeAtualizado());
        verify(clienteRepository, times(1)).findById(clienteId);
        verify(clienteRepository, times(1)).save(clienteEntity);
    }

    @Test
    void testAtualizarNomeClienteClienteNaoEncontrado() {
        Long clienteId = 1L;
        DadosAtualizarNomeClienteDTO dadosAtualizar = new DadosAtualizarNomeClienteDTO("Novo Nome");

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> atualizarNomeClienteUseCase.atualizarNomeCliente(clienteId, dadosAtualizar));

        assertEquals("Cliente não encontrado!", exception.getMessage());
        verify(clienteRepository, times(1)).findById(clienteId);
        verify(clienteRepository, never()).save(any());
    }
}
