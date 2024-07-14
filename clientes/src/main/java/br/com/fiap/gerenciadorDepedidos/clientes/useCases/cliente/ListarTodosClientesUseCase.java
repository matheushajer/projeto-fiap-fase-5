package br.com.fiap.gerenciadorDepedidos.clientes.useCases.cliente;

import br.com.fiap.gerenciadorDepedidos.clientes.adapters.cliente.ClienteAdapter;
import br.com.fiap.gerenciadorDepedidos.clientes.entities.ClienteEntity;
import br.com.fiap.gerenciadorDepedidos.clientes.records.cliente.ClienteDTO;
import br.com.fiap.gerenciadorDepedidos.clientes.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListarTodosClientesUseCase {

    private final ClienteRepository clienteRepository;

    private final ClienteAdapter clienteAdapter;

    /**
     * Listando todos os clientes cadastrados
     *
     * @param page pageable spring-data-commons
     * @return todos os objeto mapeados
     */
    public Page<ClienteDTO> listarClientes(Pageable page) {
        Page<ClienteEntity> clientesPage = clienteRepository.findAll(page);

        List<ClienteDTO> clienteEntityList = clientesPage.getContent()
                .stream()
                .map(clienteAdapter::converterClienteEntityParaClienteDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(clienteEntityList, page, clientesPage.getTotalElements());
    }
}