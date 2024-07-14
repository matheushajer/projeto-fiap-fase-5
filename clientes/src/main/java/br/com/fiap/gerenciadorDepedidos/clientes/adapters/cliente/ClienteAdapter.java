package br.com.fiap.gerenciadorDepedidos.clientes.adapters.cliente;

import br.com.fiap.gerenciadorDepedidos.clientes.adapters.endereco.EnderecoAdapter;
import br.com.fiap.gerenciadorDepedidos.clientes.adapters.telefone.TelefoneAdapter;
import br.com.fiap.gerenciadorDepedidos.clientes.entities.ClienteEntity;
import br.com.fiap.gerenciadorDepedidos.clientes.records.cliente.ClienteDTO;
import br.com.fiap.gerenciadorDepedidos.clientes.records.cliente.DadosCriacaoClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe para efetuar tratamento dos dados vindo das APIs
 * e dos dados retornados nas operações do cliente.
 */
@Service
public class ClienteAdapter {

    @Autowired
    TelefoneAdapter telefoneAdapter;
    @Autowired
    EnderecoAdapter enderecoAdapter;

    /**
     * Método para converter os dados vindo da API para um ClienteEntity.
     *
     * @param dadosCriacaoClienteDTO Objeto DadosCriacaoClienteDTO a ser convertido.
     * @return Objeto ClienteEntity resultante da conversão.
     */
    public ClienteEntity converterParaEntity(DadosCriacaoClienteDTO dadosCriacaoClienteDTO) {

        ClienteEntity clienteEntity = new ClienteEntity(
                dadosCriacaoClienteDTO.nome(),
                dadosCriacaoClienteDTO.cpf(),
                dadosCriacaoClienteDTO.email()
        );

        clienteEntity.setTelefoneEntity(telefoneAdapter.converterParaListaDeTelefones(
                dadosCriacaoClienteDTO.dadosCriacaoTelefoneDTO(), clienteEntity));

        clienteEntity.setEnderecoEntity(enderecoAdapter.converterParaListaDeEnderecos(
                dadosCriacaoClienteDTO.dadosCriacaoEnderecoDTO(), clienteEntity
        ));

        return clienteEntity;

    }

    /**
     * Método para converter os dados vindo da API para um ClienteEntity.
     *
     * @param clienteEntity Objeto ClienteEntity a ser convertido.
     * @return Objeto DadosCriacaoClienteDTO resultante da conversão.
     */
    public DadosCriacaoClienteDTO converterParaDTO(ClienteEntity clienteEntity) {

        return new DadosCriacaoClienteDTO(
                clienteEntity.getNome(),
                censurarCPF(clienteEntity.getCpf()),
                clienteEntity.getEmail(),
                telefoneAdapter.converterParaListaDeDTO(clienteEntity.getTelefoneEntity()),
                enderecoAdapter.converterParaListaDeDTO(clienteEntity.getEnderecoEntity())
        );

    }

    /**
     * Método auxiliar para censurar os dados do CPF.
     *
     * @param cpf CPF que será censurado.
     * @return CPF censurado, na seguinte formatação | ***-***-**6-76
     */
    public String censurarCPF(String cpf) {

        // Aplica a censura no CPF
        return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "***-***-$3-$4");
    }

    /**
     * Converter a entidade de cliente para o objeto
     *
     * @param clienteEntity entidade cliente
     * @return O objeto cliente mapeado
     */
    public ClienteDTO converterClienteEntityParaClienteDTO(ClienteEntity clienteEntity) {

        return new ClienteDTO(clienteEntity.getId(), clienteEntity.getNome(), censurarCPF(clienteEntity.getCpf()),
                telefoneAdapter.converterListaTelefoneEntityParaListaTelefoneDTO(clienteEntity.getTelefoneEntity()),
                enderecoAdapter.converterListaEnderecoEntityParaListaDeEnderecoDTO(clienteEntity.getEnderecoEntity()));
    }

    /**
     * Converter a lista de entidade de cliente para a lista do objeto
     *
     * @param clienteEntityList lista da entidade cliente
     *
     * @return O objeto cliente listado e mapeado
     */
    public List<ClienteDTO> converterClienteEntitylistParaClienteDTOList(List<ClienteEntity> clienteEntityList) {
        List<ClienteDTO> clienteDTOList = new ArrayList<>();
        clienteEntityList.forEach(entity ->  clienteDTOList.add(converterClienteEntityParaClienteDTO(entity)));
        return clienteDTOList;
    }
}