package br.com.fiap.gerenciadorDepedidos.clientes.useCases.cliente;

import br.com.fiap.gerenciadorDepedidos.clientes.adapters.cliente.ClienteAdapter;
import br.com.fiap.gerenciadorDepedidos.clientes.entities.ClienteEntity;
import br.com.fiap.gerenciadorDepedidos.clientes.records.cliente.ClienteCpfFiltroDTO;
import br.com.fiap.gerenciadorDepedidos.clientes.records.cliente.ClienteDTO;
import br.com.fiap.gerenciadorDepedidos.clientes.repositories.ClienteRepository;
import br.com.fiap.gerenciadorDepedidos.clientes.specification.ClienteSpecification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscarClientePorCPFUseCaseTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteAdapter clienteAdapter;

    @InjectMocks
    private BuscarClientePorCPFUseCase buscarClientePorCPFUseCase;

    @Test
    void buscarClientePorCPF_DeveRetornarListaDeClientesQuandoEncontrados() {
        ClienteCpfFiltroDTO filtro = new ClienteCpfFiltroDTO("12345678901");

        List<ClienteEntity> clienteEntities = new ArrayList<>();
        clienteEntities.add(new ClienteEntity("Nome", "12345678901"));
        when(clienteRepository.findAll(any(ClienteSpecification.class))).thenReturn(clienteEntities);

        List<ClienteDTO> clientesDTO = new ArrayList<>();
        clientesDTO.add(new ClienteDTO(1L, "Nome", "12345678901", new ArrayList<>(), new ArrayList<>()));
        when(clienteAdapter.converterClienteEntitylistParaClienteDTOList(clienteEntities)).thenReturn(clientesDTO);

        List<ClienteDTO> resultado = buscarClientePorCPFUseCase.buscarClientePorCPF(filtro);

        assertEquals(clientesDTO, resultado);
        verify(clienteRepository, times(1)).findAll(any(ClienteSpecification.class));
        verify(clienteAdapter, times(1)).converterClienteEntitylistParaClienteDTOList(clienteEntities);
    }

    @Test
    void buscarClientePorCPF_DeveRetornarListaVaziaQuandoNenhumClienteForEncontrado() {
        ClienteCpfFiltroDTO filtro = new ClienteCpfFiltroDTO("12345678901");

        when(clienteAdapter.converterClienteEntitylistParaClienteDTOList(anyList())).thenReturn(new ArrayList<>());

        when(clienteRepository.findAll(any(ClienteSpecification.class))).thenReturn(new ArrayList<>());

        List<ClienteDTO> resultado = buscarClientePorCPFUseCase.buscarClientePorCPF(filtro);

        assertEquals(0, resultado.size());
        verify(clienteRepository, times(1)).findAll(any(ClienteSpecification.class));
        verify(clienteAdapter, times(1)).converterClienteEntitylistParaClienteDTOList(anyList());
    }

}
