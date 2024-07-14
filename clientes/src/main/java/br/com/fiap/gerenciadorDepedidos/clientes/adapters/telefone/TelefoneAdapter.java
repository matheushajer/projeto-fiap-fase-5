package br.com.fiap.gerenciadorDepedidos.clientes.adapters.telefone;

import br.com.fiap.gerenciadorDepedidos.clientes.entities.ClienteEntity;
import br.com.fiap.gerenciadorDepedidos.clientes.entities.TelefoneEntity;
import br.com.fiap.gerenciadorDepedidos.clientes.records.cliente.TelefoneDTO;
import br.com.fiap.gerenciadorDepedidos.clientes.records.telefone.DadosCriacaoTelefoneDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe para efetuar tratamento dos dados vindo das APIs
 * e dos dados retornados.
 */
@Service
public class TelefoneAdapter {

    /**
     * Método para converter os dados vindo da API para um TelefoneEntity.
     *
     * @param dadosCriacaoTelefoneDTO Objeto DadosCriacaoTelefoneDTO a ser convertido.
     * @param clienteEntity           Obejto ClienteEntity a ser vinculado.
     * @return Objeto TelefoneEntity resultante da conversão.
     */
    public TelefoneEntity converterParaEntity(DadosCriacaoTelefoneDTO dadosCriacaoTelefoneDTO,
                                              ClienteEntity clienteEntity) {

        return new TelefoneEntity(
                dadosCriacaoTelefoneDTO.ddi(),
                dadosCriacaoTelefoneDTO.ddd(),
                dadosCriacaoTelefoneDTO.numero(),
                dadosCriacaoTelefoneDTO.isTelefonePrincipal(),
                clienteEntity
        );

    }

    /**
     * Método para converter os dados retornados para a API, para um DadosCriacaoTelefoneDTO.
     *
     * @param telefoneEntity Objeto TelefoneEntity a ser convertido.
     * @return Objeto DadosCriacaoTelefoneDTO resultante da conversão.
     */
    public List<DadosCriacaoTelefoneDTO> converterParaListaDeDTO(List<TelefoneEntity> telefoneEntity) {

        List<DadosCriacaoTelefoneDTO> dadosCriacaoTelefoneDTOList = new ArrayList<>();

        telefoneEntity.forEach(telefone ->
                dadosCriacaoTelefoneDTOList.add(new DadosCriacaoTelefoneDTO(
                        telefone.getDdi(),
                        telefone.getDdd(),
                        telefone.getNumero(),
                        telefone.isTelefonePrincial()
                ))
        );

        return dadosCriacaoTelefoneDTOList;

    }

    /**
     * Método para converter uma lista de dadosCriacaoTelefoneDTO para uma lista de TelefoneEntity.
     *
     * @param dadosCriacaoTelefoneDTOList Lista de objetos com os dados a serem convertidos.
     * @param clienteEntity               Objeto com os dados do cliente para relacionamento.
     * @return telefoneEntityList Lista de objetos convertidos.
     */
    public List<TelefoneEntity> converterParaListaDeTelefones(
            List<DadosCriacaoTelefoneDTO> dadosCriacaoTelefoneDTOList, ClienteEntity clienteEntity) {

        List<TelefoneEntity> telefoneEntityList = new ArrayList<>();

        long telefonesPrincipais = dadosCriacaoTelefoneDTOList.stream().filter(DadosCriacaoTelefoneDTO::isTelefonePrincipal)
                .count();

        if (telefonesPrincipais > 1) {
            throw new IllegalArgumentException("Apenas um telefone deve estar configurado como principal!");
        }

        dadosCriacaoTelefoneDTOList.forEach(telefone ->
                telefoneEntityList.add(new TelefoneEntity(
                        telefone.ddi(),
                        telefone.ddd(),
                        telefone.numero(),
                        telefone.isTelefonePrincipal(),
                        clienteEntity
                ))
        );

        return telefoneEntityList;

    }

    /**
     * Método para converter a lista de entidade do telefone para listagem do objeto
     *
     * @param telefoneEntities lista de entidade do telefone
     *
     * @return Lista de objeto telefone mapeada
     */
    public List<TelefoneDTO> converterListaTelefoneEntityParaListaTelefoneDTO(List<TelefoneEntity> telefoneEntities) {
        List<TelefoneDTO> telefones = new ArrayList<>();

        telefoneEntities.forEach(telefone -> {
            TelefoneDTO telefoneDTO = new TelefoneDTO(telefone.getId(), telefone.getDdi(), telefone.getDdd(),
                    telefone.getNumero(), telefone.isTelefonePrincial());
            telefones.add(telefoneDTO);
        });

        return telefones;
    }

}
