package br.com.fiap.gerenciadorDepedidos.pedidos.entities;

import br.com.fiap.gerenciadorDepedidos.pedidos.entities.enuns.StatusPedido;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "tb_pedidos")
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long clienteId;

    @OneToMany(mappedBy = "pedidoEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ItemPedidoEntity> itensPedido;

    private BigDecimal valorPedido = BigDecimal.ZERO;
    private Integer prazoDeEntrega;
    private BigDecimal frete = BigDecimal.ZERO;
    private BigDecimal valorComFrete = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.CRIADO;

    private Integer codigoDeRastreio;
    private LocalDateTime dataCriacaoPedido = LocalDateTime.now();

    // **************
    // Construtores
    // **************

    public PedidoEntity() {
    }

    // ******************
    // Métodos auxiliares
    // ******************

    /**
     * Método para calcular o valor total do pedido.
     * Multiplica o preco pela quantidade de cada itemPedido e soma.
     *
     * @param itensPedido Lista de ItemPedidoEntity, com os dados a serem somados.
     */
    public void calcularValorTotalPedido(List<ItemPedidoEntity> itensPedido) {

        BigDecimal valorTotalPedido = BigDecimal.ZERO;

        for (ItemPedidoEntity item : itensPedido) {
            BigDecimal valorTotalItem = item.calcularValorTotalItem();
            valorTotalPedido = valorTotalPedido.add(valorTotalItem);
        }

        this.valorPedido = this.valorPedido.add(valorTotalPedido);

    }

    /**
     * Método para calcular o valor total do pedido com o frete.
     * Soma o valorPedido com o frete.
     */
    public void calcularValorComFrete() {

        this.valorComFrete = this.valorPedido.add(this.frete);

    }

}
