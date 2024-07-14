package br.com.fiap.gerenciadorDepedidos.pedidos.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "tb_item_pedido")
public class ItemPedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private PedidoEntity pedidoEntity;

    private Long produtoId;
    private String nome;
    private BigDecimal preco;
    private Integer quantidade;

    // **************
    // Construtores
    // **************

    public ItemPedidoEntity() {
    }

    public ItemPedidoEntity(Long produtoId, String nome, BigDecimal preco, Integer quantidade, PedidoEntity pedidoEntity) {
        this.produtoId = produtoId;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.pedidoEntity = pedidoEntity;
    }

    public ItemPedidoEntity(Long produtoId, String nome, BigDecimal preco, Integer quantidade) {
        this.produtoId = produtoId;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // ******************
    // Métodos auxiliares
    // ******************

    /**
     * Multiplica o preco pela quantadade de um itemPedido.
     *
     * @return valorTotalItem BigDecimal, com o valor da soma.
     */
    public BigDecimal calcularValorTotalItem() {
        BigDecimal valorTotalItem = preco.multiply(BigDecimal.valueOf(quantidade));
        return valorTotalItem;
    }

}
