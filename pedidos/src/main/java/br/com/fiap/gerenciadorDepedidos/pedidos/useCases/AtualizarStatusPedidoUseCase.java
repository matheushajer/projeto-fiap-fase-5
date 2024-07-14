package br.com.fiap.gerenciadorDepedidos.pedidos.useCases;

import br.com.fiap.gerenciadorDepedidos.pedidos.entities.PedidoEntity;
import br.com.fiap.gerenciadorDepedidos.pedidos.records.DadosAtualizacaoStatusPedidoDTO;
import br.com.fiap.gerenciadorDepedidos.pedidos.repositories.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtualizarStatusPedidoUseCase {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Transactional
    public void atualizarStatus(Long pedido_id, DadosAtualizacaoStatusPedidoDTO dadosAtualizacaoStatusPedidoDTO) {

        PedidoEntity pedido = pedidoRepository.findById(pedido_id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado com ID: " + pedido_id));

        pedido.setStatus(dadosAtualizacaoStatusPedidoDTO.status());

        pedidoRepository.save(pedido);

    }
}