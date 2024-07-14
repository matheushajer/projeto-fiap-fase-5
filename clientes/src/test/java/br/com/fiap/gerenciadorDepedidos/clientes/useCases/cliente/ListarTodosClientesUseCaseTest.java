package br.com.fiap.gerenciadorDepedidos.clientes.useCases.cliente;

import br.com.fiap.gerenciadorDepedidos.clientes.adapters.cliente.ClienteAdapter;
import br.com.fiap.gerenciadorDepedidos.clientes.entities.ClienteEntity;
import br.com.fiap.gerenciadorDepedidos.clientes.records.cliente.ClienteDTO;
import br.com.fiap.gerenciadorDepedidos.clientes.repositories.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListarTodosClientesUseCaseTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteAdapter clienteAdapter;

    @InjectMocks
    private ListarTodosClientesUseCase listarTodosClientesUseCase;

    @Test
    void listarClientes_DeveRetornarListaDeClientesQuandoClientesEstiveremDisponiveis() {
        Pageable pageable = Pageable.ofSize(10).withPage(0);

        List<ClienteEntity> clienteEntityList = new ArrayList<>();
        clienteEntityList.add(new ClienteEntity("Nome1", "12345678901"));
        clienteEntityList.add(new ClienteEntity("Nome2", "23456789012"));
        Page<ClienteEntity> clientesPage = new PageImpl<>(clienteEntityList);
        when(clienteRepository.findAll(pageable)).thenReturn(clientesPage);

        List<ClienteDTO> clienteDTOList = new ArrayList<>();
        clienteDTOList.add(new ClienteDTO(1L, "Nome1", "12345678901", new ArrayList<>(), new ArrayList<>()));
        clienteDTOList.add(new ClienteDTO(2L, "Nome2", "23456789012", new ArrayList<>(), new ArrayList<>()));
        when(clienteAdapter.converterClienteEntityParaClienteDTO(any())).thenReturn(clienteDTOList.get(0), clienteDTOList.get(1));

        Page<ClienteDTO> resultado = listarTodosClientesUseCase.listarClientes(pageable);

        assertEquals(clientesPage.getContent().size(), resultado.getContent().size());
        for (int i = 0; i < clientesPage.getContent().size(); i++) {
            assertEquals(clientesPage.getContent().get(i).getNome(), resultado.getContent().get(i).nome());
            assertEquals(clientesPage.getContent().get(i).getCpf(), resultado.getContent().get(i).cpf());
        }
        assertEquals(clientesPage.getTotalElements(), resultado.getTotalElements());
    }
}
