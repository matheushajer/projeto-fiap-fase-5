package br.com.fiap.gerenciadorDepedidos.entrega.useCases;

import br.com.fiap.gerenciadorDepedidos.entrega.adapters.EntregaAdapter;
import br.com.fiap.gerenciadorDepedidos.entrega.entites.EntregaEntity;
import br.com.fiap.gerenciadorDepedidos.entrega.http.PedidoClient;
import br.com.fiap.gerenciadorDepedidos.entrega.records.DadosCriacaoEntregaDTO;
import br.com.fiap.gerenciadorDepedidos.entrega.records.DadosRetornoCriacaoEntregaDTO;
import br.com.fiap.gerenciadorDepedidos.entrega.repositories.EntregaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Classe para representar o caso de uso de criar uma entrega.
 */
@Service
@Transactional
public class CriarEntregaUseCase {

    @Autowired
    EntregaRepository entregaRepository;
    @Autowired
    EntregaAdapter entregaAdapter;
    @Autowired
    PedidoClient pedidoClient;

    public DadosRetornoCriacaoEntregaDTO criarPedidoDeEntrega(DadosCriacaoEntregaDTO dadosCriacaoEntregaDTO) throws IOException, InterruptedException {

        EntregaEntity entregaEntity = entregaAdapter.converterParaEntity(dadosCriacaoEntregaDTO);

        entregaRepository.save(entregaEntity);

        return entregaAdapter.converterParaDadosRetornoCriacaoDTO(entregaEntity);

    }

}
