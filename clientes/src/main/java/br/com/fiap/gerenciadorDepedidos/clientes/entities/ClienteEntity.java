package br.com.fiap.gerenciadorDepedidos.clientes.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe para representar a Entidade Cliente
 */
@Entity
@Data
@Table(name = "tb_cliente")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private String email;

    @OneToMany(mappedBy = "clienteEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.JOIN)
    private List<TelefoneEntity> telefoneEntity;

    @OneToMany(mappedBy = "clienteEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.JOIN)
    private List<EnderecoEntity> enderecoEntity;

    // **************
    // Construtores
    // **************

    public ClienteEntity() {
    }

    public ClienteEntity(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public ClienteEntity(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public ClienteEntity(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public ClienteEntity(String nome, String cpf, String email, List<TelefoneEntity> telefoneEntity, List<EnderecoEntity> enderecoEntity) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefoneEntity = telefoneEntity;
        this.enderecoEntity = enderecoEntity;
    }
}
