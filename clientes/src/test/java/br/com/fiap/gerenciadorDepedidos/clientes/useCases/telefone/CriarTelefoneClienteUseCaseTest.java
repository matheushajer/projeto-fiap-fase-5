package br.com.fiap.gerenciadorDepedidos.clientes.useCases.telefone;

import br.com.fiap.gerenciadorDepedidos.clientes.entities.ClienteEntity;
import br.com.fiap.gerenciadorDepedidos.clientes.entities.TelefoneEntity;
import br.com.fiap.gerenciadorDepedidos.clientes.records.telefone.DadosAtualizarTelefoneDTO;
import br.com.fiap.gerenciadorDepedidos.clientes.repositories.ClienteRepository;
import br.com.fiap.gerenciadorDepedidos.clientes.repositories.TelefoneRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarTelefoneClienteUseCaseTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private TelefoneRepository telefoneRepository;

    @InjectMocks
    private CriarTelefoneClienteUseCase criarTelefoneClienteUseCase;

    @Test
    void criarTelefoneCliente_WhenValidInput_ShouldCreateTelefoneAndReturnSuccessMessage() {
        Long clienteId = 1L;
        List<DadosAtualizarTelefoneDTO> atualizarTelefones = new ArrayList<>();
        atualizarTelefones.add(new DadosAtualizarTelefoneDTO(55, 11, 123456789));

        ClienteEntity cliente = new ClienteEntity();
        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(cliente));

        String result = criarTelefoneClienteUseCase.criarTelefoneCliente(clienteId, atualizarTelefones);

        assertEquals("Telefone(s) Cadastrado(s) com Sucesso", result);
        verify(telefoneRepository, times(1)).save(any(TelefoneEntity.class));
    }
}

