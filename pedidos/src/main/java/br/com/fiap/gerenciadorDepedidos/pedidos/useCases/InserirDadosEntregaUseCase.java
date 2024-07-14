package br.com.fiap.gerenciadorDepedidos.pedidos.useCases;


import br.com.fiap.gerenciadorDepedidos.pedidos.entities.PedidoEntity;
import br.com.fiap.gerenciadorDepedidos.pedidos.entities.enuns.StatusPedido;
import br.com.fiap.gerenciadorDepedidos.pedidos.records.DadosInsercaoEntregaDTO;
import br.com.fiap.gerenciadorDepedidos.pedidos.repositories.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Use case para inserir dados de entrega em um pedido e atualizar seu status para AGUARDANDO_PAGAMENTO.
 */
@Service
@Transactional
public class InserirDadosEntregaUseCase {

    @Autowired
    private PedidoRepository pedidoRepository;

    public void inserirDadosEntrega(Long pedido_id, DadosInsercaoEntregaDTO dadosEntregaDTO) {

        PedidoEntity pedidoEntity = pedidoRepository.findById(pedido_id).orElseThrow(
                () -> new EntityNotFoundException("Pedido com ID " + pedido_id + " não encontrado"));

        pedidoEntity.setPrazoDeEntrega(dadosEntregaDTO.prazoDeEntrega());
        pedidoEntity.setFrete(dadosEntregaDTO.frete());
        pedidoEntity.setCodigoDeRastreio(Math.toIntExact(dadosEntregaDTO.codigoDeRastreio()));

        pedidoEntity.calcularValorComFrete();

        pedidoEntity.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);

        pedidoRepository.save(pedidoEntity);

    }

}