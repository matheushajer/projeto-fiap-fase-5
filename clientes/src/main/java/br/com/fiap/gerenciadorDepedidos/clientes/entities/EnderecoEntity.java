package br.com.fiap.gerenciadorDepedidos.clientes.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Classe para representar a Entidade Endereco
 */
@Entity
@Data
@Table(name = "tb_endereco")
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private boolean isEnderecoPrincipal;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "cliente_id")
    private ClienteEntity clienteEntity;

    // **************
    // Construtores
    // **************

    public EnderecoEntity() {
    }

    public EnderecoEntity(
            String cep,
            String logradouro,
            String numero,
            String complemento,
            String bairro,
            String cidade,
            String uf,
            boolean isEnderecoPrincipal,
            ClienteEntity clienteEntity) {

        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.isEnderecoPrincipal = isEnderecoPrincipal;
        this.clienteEntity = clienteEntity;
    }

    public EnderecoEntity(
            String cep,
            String logradouro,
            String numero,
            String complemento,
            String bairro,
            String cidade,
            String uf,
            ClienteEntity clienteEntity) {

        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.clienteEntity = clienteEntity;
    }

}
