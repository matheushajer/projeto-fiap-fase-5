package br.com.fiap.gerenciadorDepedidos.entrega.entites;

import br.com.fiap.gerenciadorDepedidos.entrega.entites.enuns.StatusEntrega;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

/**
 * Classe entity para representar a entrega.
 */
@Entity
@Table(name = "tb_entregas")
@Data
public class EntregaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pedido_id;
    private String cepRemetente = "06332450";
    private String cepDestinatario;

    @OneToMany(mappedBy = "entregaEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.JOIN)
    private List<ProdutosEntity> produtos;

    @OneToOne(mappedBy = "entregaEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.JOIN)
    private DadosEntregaEntity dadosEntrega;

    private StatusEntrega statusEntrega = StatusEntrega.CRIADO;

}
