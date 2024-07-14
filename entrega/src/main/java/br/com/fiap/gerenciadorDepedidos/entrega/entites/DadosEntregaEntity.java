package br.com.fiap.gerenciadorDepedidos.entrega.entites;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

/**
 * Classe entity para representar os dados de entrega, que serão
 * enviado para o microservice de Pedidos.
 */
@Entity
@Table(name = "tb_dados_entrega")
@Data
public class DadosEntregaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modalidade;
    private String prazo;
    private BigDecimal preco;

    @OneToOne
    @JoinColumn(name = "entrega_id")
    private EntregaEntity entregaEntity;

}
