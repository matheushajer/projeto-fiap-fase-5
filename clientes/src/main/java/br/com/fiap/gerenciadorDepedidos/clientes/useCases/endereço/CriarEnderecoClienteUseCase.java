package br.com.fiap.gerenciadorDepedidos.clientes.useCases.endereço;

import br.com.fiap.gerenciadorDepedidos.clientes.entities.ClienteEntity;
import br.com.fiap.gerenciadorDepedidos.clientes.entities.EnderecoEntity;
import br.com.fiap.gerenciadorDepedidos.clientes.records.endereco.DadosAtualizarEnderecoDTO;
import br.com.fiap.gerenciadorDepedidos.clientes.repositories.ClienteRepository;
import br.com.fiap.gerenciadorDepedidos.clientes.repositories.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CriarEnderecoClienteUseCase {

    private final ClienteRepository clienteRepository;

    private final EnderecoRepository enderecoRepository;

    /**
     * Adicionando endereço no cadastro do cliente
     *
     * @param clienteId          numero de identificacao do cliente cadastrado
     * @param atualizarEnderecos objeto lista para persistir endereços
     * @return mensagem de sucesso caso a operacao seja finalizada
     */
    public String criarEnderecoCliente(Long clienteId, List<DadosAtualizarEnderecoDTO> atualizarEnderecos) {

        ClienteEntity cliente = clienteRepository.findById(clienteId).orElseThrow(() ->
                new IllegalArgumentException("O Cliente Não Foi Encontrado"));

        if (!CollectionUtils.isEmpty(atualizarEnderecos)) {
            atualizarEnderecos.forEach(atualizarEndereco -> {
                enderecoRepository.save(new EnderecoEntity(atualizarEndereco.cep(), atualizarEndereco.logradouro(),
                        atualizarEndereco.numero(), atualizarEndereco.complemento(), atualizarEndereco.bairro(),
                        atualizarEndereco.cidade(), atualizarEndereco.uf(), cliente));
            });
        }

        return "Endereco Criado Com Sucesso";
    }
}