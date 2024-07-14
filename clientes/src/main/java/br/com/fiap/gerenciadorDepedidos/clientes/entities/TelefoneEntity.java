package br.com.fiap.gerenciadorDepedidos.clientes.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Classe para representar a Entidade Telefone
 */
@Data
@Entity
@Table(name = "tb_telefone")
public class TelefoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int ddi;
    private int ddd;
    private int numero;
    private boolean isTelefonePrincial;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity clienteEntity;

    // **************
    // Construtores
    // **************

    public TelefoneEntity() {
    }

    public TelefoneEntity(int ddi, int ddd, int numero, boolean telefonePrincipal, ClienteEntity clienteEntity) {
        this.ddi = ddi;
        this.ddd = ddd;
        this.numero = numero;
        this.isTelefonePrincial = telefonePrincipal;
        this.clienteEntity = clienteEntity;
    }

    public TelefoneEntity(int ddi, int ddd, int numero, ClienteEntity clienteEntity) {
        this.ddi = ddi;
        this.ddd = ddd;
        this.numero = numero;
        this.clienteEntity = clienteEntity;
    }

    public TelefoneEntity(int ddi, int ddd, int numero) {
        this.ddi = ddi;
        this.ddd = ddd;
        this.numero = numero;
    }

}
