package br.com.fiap.gerenciadorDepedidos.produtos.repositories;

import br.com.fiap.gerenciadorDepedidos.produtos.entities.ProdutoEntity;
import br.com.fiap.gerenciadorDepedidos.produtos.entities.enuns.CategoriaProduto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    Page<ProdutoEntity> findByNomeContaining(String nome, Pageable pageable);

    Page<ProdutoEntity> findByCategoria(CategoriaProduto categoria, Pageable pageable);

}
