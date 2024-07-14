package br.com.fiap.gerenciadorDepedidos.clientes.useCases.telefone;

import br.com.fiap.gerenciadorDepedidos.clientes.entities.ClienteEntity;
import br.com.fiap.gerenciadorDepedidos.clientes.entities.TelefoneEntity;
import br.com.fiap.gerenciadorDepedidos.clientes.records.telefone.DadosAtualizarTelefoneDTO;
import br.com.fiap.gerenciadorDepedidos.clientes.repositories.ClienteRepository;
import br.com.fiap.gerenciadorDepedidos.clientes.repositories.TelefoneRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CriarTelefoneClienteUseCase {

    private final ClienteRepository clienteRepository;

    private final TelefoneRepository telefoneRepository;

    /**
     * Adicionando telefone no cadastro do cliente
     *
     * @param clienteId          numero de identificacao do cliente cadastrado
     * @param atualizarTelefones objeto lista para persistir numeros de telefones
     * @return mensagem de sucesso caso a operacao seja finalizada
     */
    public String criarTelefoneCliente(Long clienteId, List<DadosAtualizarTelefoneDTO> atualizarTelefones) {
        ClienteEntity cliente = this.clienteRepository.findById(clienteId).orElseThrow(() ->
                new IllegalArgumentException("O Cliente Não Foi Encontrado"));

        if (!CollectionUtils.isEmpty(atualizarTelefones)) {
            atualizarTelefones.forEach(atualizarTelefone -> {
                validacoesParaSalvarTelefone(cliente, atualizarTelefone.numero());
                telefoneRepository.save(new
                        TelefoneEntity(atualizarTelefone.ddi(), atualizarTelefone.ddd(), atualizarTelefone.numero(), cliente));
            });
        }

        return "Telefone(s) Cadastrado(s) com Sucesso";
    }

    /**
     * Metodo de validacoes para salvar o telefone
     *
     * @param cliente        entidade cliente
     * @param numeroTelefone numero a ser persistido
     */
    private void validacoesParaSalvarTelefone(ClienteEntity cliente, int numeroTelefone) {
        if (!CollectionUtils.isEmpty(cliente.getTelefoneEntity())) {
            cliente.getTelefoneEntity().forEach(telefone -> {
                if (numeroTelefone == telefone.getNumero()) {
                    throwNumerojaCadastradoParaOCliente();
                }

            });
        }
    }

    /**
     * Excecao caso o numero jã estiver cadastrado na base de dados
     */
    private void throwNumerojaCadastradoParaOCliente() {
        throw new EntityNotFoundException("Numero de telefone ja cadastrado para o cliente");
    }
}
