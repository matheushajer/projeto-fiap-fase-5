package br.com.fiap.gerenciadorDepedidos.entrega.entites;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Classe entity para representar os dados de envio do produto.
 * Será usada para consumir a API do melhor envio.
 */
@Entity
@Table(name = "tb_produtos_entrega")
@Data
public class ProdutosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long produto_id;
    private Integer altura;
    private Integer largura;
    private Integer comprimento;
    private Double peso;
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "entrega_id")
    private EntregaEntity entregaEntity;

}
