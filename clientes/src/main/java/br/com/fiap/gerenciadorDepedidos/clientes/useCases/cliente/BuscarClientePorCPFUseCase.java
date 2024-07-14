package br.com.fiap.gerenciadorDepedidos.clientes.useCases.cliente;

import br.com.fiap.gerenciadorDepedidos.clientes.adapters.cliente.ClienteAdapter;
import br.com.fiap.gerenciadorDepedidos.clientes.entities.ClienteEntity;
import br.com.fiap.gerenciadorDepedidos.clientes.records.cliente.ClienteCpfFiltroDTO;
import br.com.fiap.gerenciadorDepedidos.clientes.records.cliente.ClienteDTO;
import br.com.fiap.gerenciadorDepedidos.clientes.repositories.ClienteRepository;
import br.com.fiap.gerenciadorDepedidos.clientes.specification.ClienteSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuscarClientePorCPFUseCase {

    private final ClienteRepository clienteRepository;

    private final ClienteAdapter clienteAdapter;

    /**
     * Método para buscar as informações de cliente pelo cpf
     *
     * @param filter objeto para o filtro
     *
     * @return A lista de objetos de acordo com o filtro
     */
    public List<ClienteDTO> buscarClientePorCPF(ClienteCpfFiltroDTO filter) {
        List<ClienteEntity> clienteEntities = clienteRepository.findAll(new ClienteSpecification(filter));
        return clienteAdapter.converterClienteEntitylistParaClienteDTOList(clienteEntities);
    }
}
