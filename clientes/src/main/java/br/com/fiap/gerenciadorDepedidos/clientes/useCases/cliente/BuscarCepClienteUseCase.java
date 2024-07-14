package br.com.fiap.gerenciadorDepedidos.clientes.useCases.cliente;

import br.com.fiap.gerenciadorDepedidos.clientes.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe para representar o caso de uso de
 * buscar o Cep de um cliente.
 */
@Service
@Transactional
public class BuscarCepClienteUseCase {

    @Autowired
    ClienteRepository clienteRepository;

    public String buscarCepCliente(Long cliente_id) {

        return clienteRepository.findCepPrincipalByClienteId(cliente_id);

    }

}
