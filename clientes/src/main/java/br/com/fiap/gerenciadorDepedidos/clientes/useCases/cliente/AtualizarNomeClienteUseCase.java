package br.com.fiap.gerenciadorDepedidos.clientes.useCases.cliente;

import br.com.fiap.gerenciadorDepedidos.clientes.entities.ClienteEntity;
import br.com.fiap.gerenciadorDepedidos.clientes.records.cliente.DadosAtualizarNomeClienteDTO;
import br.com.fiap.gerenciadorDepedidos.clientes.records.cliente.DadosRetornoAtualizacaoNomeClienteDTO;
import br.com.fiap.gerenciadorDepedidos.clientes.repositories.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Classe para representar o caso de uso de atualizar
 * o nome de um cliente.
 */
@Service
@Transactional
public class AtualizarNomeClienteUseCase {

    @Autowired
    ClienteRepository clienteRepository;

    /**
     * Método para efetuar a atualização do nome de um cliente já cadastrado.
     *
     * @param cliente_id                   ID do cliente a ter o nome atualizado.
     * @param dadosAtualizarNomeClienteDTO Objeto com os dados para atualização do nome do cliente.
     * @return DadosRetornoAtualizacaoNomeClienteDTO Objeto com os dados da atualização efetuada.
     * @throws EntityNotFoundException Exception retornada caso o cliente_id não esteja cadastrado no banco.
     */
    public DadosRetornoAtualizacaoNomeClienteDTO atualizarNomeCliente(Long cliente_id, DadosAtualizarNomeClienteDTO dadosAtualizarNomeClienteDTO) {

        Optional<ClienteEntity> clienteEntity = clienteRepository.findById(cliente_id);

        if (clienteEntity.isPresent()) {
            ClienteEntity cliente = clienteEntity.get();

            String nomeAntigoCliente = cliente.getNome();

            cliente.setNome(dadosAtualizarNomeClienteDTO.nomeAtualizado());

            clienteRepository.save(cliente);

            return new DadosRetornoAtualizacaoNomeClienteDTO(nomeAntigoCliente, dadosAtualizarNomeClienteDTO.nomeAtualizado());

        } else {
            throw new EntityNotFoundException("Cliente não encontrado!");
        }
    }
}