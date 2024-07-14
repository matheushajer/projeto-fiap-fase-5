package br.com.fiap.gerenciadorDepedidos.produtos.entities;

import br.com.fiap.gerenciadorDepedidos.produtos.entities.enuns.CategoriaProduto;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_produtos")
@Data
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer quantidade;

    @Enumerated(EnumType.STRING)
    private CategoriaProduto categoria;

    private Integer altura;
    private Integer largura;
    private Integer comprimento;
    private Double peso;

    // **************
    // Construtores
    // **************

    public ProdutoEntity() {
    }

    public ProdutoEntity(String nome, String descricao, BigDecimal preco, Integer quantidade,
                         CategoriaProduto categoria, Integer altura, Integer largura, Integer comprimento, Double peso) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.altura = altura;
        this.largura = largura;
        this.comprimento = comprimento;
        this.peso = peso;
    }
}
