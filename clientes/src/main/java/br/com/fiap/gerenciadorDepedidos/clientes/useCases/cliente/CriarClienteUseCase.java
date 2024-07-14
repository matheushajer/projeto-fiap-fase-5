package br.com.fiap.gerenciadorDepedidos.clientes.useCases.cliente;

import br.com.fiap.gerenciadorDepedidos.clientes.adapters.cliente.ClienteAdapter;
import br.com.fiap.gerenciadorDepedidos.clientes.entities.ClienteEntity;
import br.com.fiap.gerenciadorDepedidos.clientes.records.cliente.DadosCriacaoClienteDTO;
import br.com.fiap.gerenciadorDepedidos.clientes.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe para representar o caso de uso da criação de um restaurante
 */
@Service
@Transactional
public class CriarClienteUseCase {

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ClienteAdapter clienteAdapter;

    /**
     * Método para efetuar a criação de uma entity ClienteEntity e gravar no banco.
     *
     * @param dadosCriacaoClienteDTO Objeto DadosCriacaoClienteDTO com os dados de criação do ClienteEntity.
     * @return Objeto DadosCriacaoClienteDTO com os dados gravados.
     */
    public DadosCriacaoClienteDTO criarCliente(DadosCriacaoClienteDTO dadosCriacaoClienteDTO) {

        if(clienteRepository.existsByCpf(dadosCriacaoClienteDTO.cpf())){
            throw new IllegalArgumentException("CPF já cadastrado!");
        }

        ClienteEntity clienteEntity = clienteAdapter.converterParaEntity(dadosCriacaoClienteDTO);

        clienteRepository.save(clienteEntity);

        return clienteAdapter.converterParaDTO(clienteEntity);

    }

}
