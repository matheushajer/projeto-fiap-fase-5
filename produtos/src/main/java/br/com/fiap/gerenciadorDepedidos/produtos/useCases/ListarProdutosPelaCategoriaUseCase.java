package br.com.fiap.gerenciadorDepedidos.produtos.useCases;

import br.com.fiap.gerenciadorDepedidos.produtos.entities.ProdutoEntity;
import br.com.fiap.gerenciadorDepedidos.produtos.entities.enuns.CategoriaProduto;
import br.com.fiap.gerenciadorDepedidos.produtos.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Classe para implementar o caso de uso de listar todos os produtos
 * pela categoria.
 */
@Service
public class ListarProdutosPelaCategoriaUseCase {

    @Autowired
    ProdutoRepository produtoRepository;

    public Page<ProdutoEntity> listarProdutosPelaCategoria(String nomeCategoria, Pageable pageable) {

        CategoriaProduto categoriaProduto = CategoriaProduto.fromString(nomeCategoria);

        return produtoRepository.findByCategoria(categoriaProduto, pageable);

    }

}
