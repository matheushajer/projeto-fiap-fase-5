package br.com.fiap.gerenciadorDepedidos.clientes.useCases.endereço;

import br.com.fiap.gerenciadorDepedidos.clientes.entities.EnderecoEntity;
import br.com.fiap.gerenciadorDepedidos.clientes.records.endereco.DadosAtualizarEnderecoDTO;
import br.com.fiap.gerenciadorDepedidos.clientes.repositories.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtualizarEnderecoPrincipalClienteUseCase {

    private final EnderecoRepository enderecoRepository;

    /**
     * Metodo para atualizar o Endereco Principal do cliente
     *
     * @param clienteId         identificador do cliente
     * @param atualizarEndereco objeto para salvar as informações de endereco
     * @return mensagem de sucesso caso a operação seja concluída
     */
    public String atualizarEndercoPrincipal(Long clienteId, DadosAtualizarEnderecoDTO atualizarEndereco) {
        EnderecoEntity enderecoDatabase =
                this.enderecoRepository.findById(this.findByClienteEntityIdAndIsEnderecoPrincipalIsTrue(clienteId).getId())
                        .orElseThrow(() -> new IllegalArgumentException("O endereco não foi encontrado"));

        enderecoDatabase.setCep(atualizarEndereco.cep());
        enderecoDatabase.setNumero(atualizarEndereco.numero());
        enderecoDatabase.setComplemento(atualizarEndereco.complemento());
        enderecoDatabase.setBairro(atualizarEndereco.bairro());
        enderecoDatabase.setCidade(atualizarEndereco.cidade());
        enderecoDatabase.setUf(atualizarEndereco.uf());

        this.enderecoRepository.save(enderecoDatabase);

        return "Endereco Pricipal Atualizado com Sucesso";

    }

    /**
     * Pegar o endereco principal do cliente
     *
     * @param clienteId identificador do cliente
     * @return O endereco principal do cliente
     */
    public EnderecoEntity findByClienteEntityIdAndIsEnderecoPrincipalIsTrue(Long clienteId) {
        return this.enderecoRepository.findByClienteEntityIdAndIsEnderecoPrincipalIsTrue(clienteId);
    }
}