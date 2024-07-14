package br.com.fiap.gerenciadorDepedidos.clientes.useCases.telefone;

import br.com.fiap.gerenciadorDepedidos.clientes.entities.TelefoneEntity;
import br.com.fiap.gerenciadorDepedidos.clientes.records.telefone.DadosAtualizarTelefoneDTO;
import br.com.fiap.gerenciadorDepedidos.clientes.repositories.TelefoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AtualizarTelefonePrincipalClienteUseCase {

    private final TelefoneRepository telefoneRepository;


    /**
     * Metodo para atualizar o Telefone Principal do cliente
     *
     * @param clientId          identificador do cliente
     * @param atualizarTelefone objeto para salvar as informações de telefone
     * @return mensagem de sucesso caso a operação seja concluída
     */
    public String atualizarTelefonePrincipal(Long clientId, DadosAtualizarTelefoneDTO atualizarTelefone) {
        TelefoneEntity telefoneDataBase =
                this.telefoneRepository.findById(this.findByclienteEntityIdAndIsTelefonePrincialIsTrue(clientId).getId())
                        .orElseThrow(() -> new IllegalArgumentException("O Telefone Pricipal Não Foi Encontrado"));

        telefoneDataBase.setDdi(atualizarTelefone.ddi());
        telefoneDataBase.setDdd(atualizarTelefone.ddd());
        telefoneDataBase.setNumero(atualizarTelefone.numero());

        telefoneRepository.save(telefoneDataBase);

        return "Telefone Pricipal Atualizado com Sucesso";
    }

    /**
     * Pegar o telefone principal do cliente
     *
     * @param clienteId identificador do cliente
     * @return O telefone principal do cliente
     */
    TelefoneEntity findByclienteEntityIdAndIsTelefonePrincialIsTrue(Long clienteId) {
        Optional<TelefoneEntity> telefoneOptional = telefoneRepository.findByclienteEntityIdAndIsTelefonePrincialIsTrue(clienteId);
        if (telefoneOptional.isPresent()) {
            return telefoneOptional.get();
        } else {
            throw new IllegalArgumentException("Telefone principal não encontrado para o cliente com ID: " + clienteId);
        }
    }

}