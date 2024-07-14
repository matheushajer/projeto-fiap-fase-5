package br.com.fiap.gerenciadorDepedidos.clientes.useCases.cliente;

import br.com.fiap.gerenciadorDepedidos.clientes.adapters.cliente.ClienteAdapter;
import br.com.fiap.gerenciadorDepedidos.clientes.entities.ClienteEntity;
import br.com.fiap.gerenciadorDepedidos.clientes.records.cliente.DadosCriacaoClienteDTO;
import br.com.fiap.gerenciadorDepedidos.clientes.records.endereco.DadosCriacaoEnderecoDTO;
import br.com.fiap.gerenciadorDepedidos.clientes.records.telefone.DadosCriacaoTelefoneDTO;
import br.com.fiap.gerenciadorDepedidos.clientes.repositories.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarClienteUseCaseTest {
    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteAdapter clienteAdapter;

    @InjectMocks
    private CriarClienteUseCase criarClienteUseCase;

    @Test
    void criarCliente_DeveRetornarDadosCriadosQuandoClienteForCriadoComSucesso() {
        List<DadosCriacaoTelefoneDTO> telefones = new ArrayList<>();
        List<DadosCriacaoEnderecoDTO> enderecos = new ArrayList<>();
        DadosCriacaoClienteDTO dadosCriacaoClienteDTO = new DadosCriacaoClienteDTO("Nome", "12345678901", "email@example.com", telefones, enderecos);

        when(clienteRepository.existsByCpf(dadosCriacaoClienteDTO.cpf())).thenReturn(false);

        ClienteEntity clienteEntity = new ClienteEntity("Nome", "12345678901", "email@example.com", new ArrayList<>(), new ArrayList<>());
        when(clienteAdapter.converterParaEntity(dadosCriacaoClienteDTO)).thenReturn(clienteEntity);
        when(clienteAdapter.converterParaDTO(clienteEntity)).thenReturn(dadosCriacaoClienteDTO);

        DadosCriacaoClienteDTO resultado = criarClienteUseCase.criarCliente(dadosCriacaoClienteDTO);

        assertNotNull(resultado);
        assertEquals(dadosCriacaoClienteDTO, resultado);
        verify(clienteRepository, times(1)).existsByCpf(dadosCriacaoClienteDTO.cpf());
        verify(clienteRepository, times(1)).save(any(ClienteEntity.class));
        verify(clienteAdapter, times(1)).converterParaEntity(dadosCriacaoClienteDTO);
        verify(clienteAdapter, times(1)).converterParaDTO(clienteEntity);
    }

    @Test
    void criarCliente_DeveLancarExcecaoQuandoCPFEstaCadastrado() {
        List<DadosCriacaoTelefoneDTO> telefones = new ArrayList<>();
        List<DadosCriacaoEnderecoDTO> enderecos = new ArrayList<>();
        DadosCriacaoClienteDTO dadosCriacaoClienteDTO = new DadosCriacaoClienteDTO("Nome", "12345678901", "email@example.com", telefones, enderecos);

        when(clienteRepository.existsByCpf(dadosCriacaoClienteDTO.cpf())).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> criarClienteUseCase.criarCliente(dadosCriacaoClienteDTO));

        verify(clienteRepository, times(1)).existsByCpf(dadosCriacaoClienteDTO.cpf());
        verify(clienteRepository, never()).save(any(ClienteEntity.class));
        verify(clienteAdapter, never()).converterParaEntity(any(DadosCriacaoClienteDTO.class));
        verify(clienteAdapter, never()).converterParaDTO(any(ClienteEntity.class));
    }

}
