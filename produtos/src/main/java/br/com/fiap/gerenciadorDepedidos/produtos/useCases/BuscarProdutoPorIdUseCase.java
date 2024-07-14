package br.com.fiap.gerenciadorDepedidos.produtos.useCases;

import br.com.fiap.gerenciadorDepedidos.produtos.entities.ProdutoEntity;
import br.com.fiap.gerenciadorDepedidos.produtos.repositories.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe para implementar o caso de uso para buscar
 * um produto por ID.
 */
@Service
public class BuscarProdutoPorIdUseCase {

    @Autowired
    ProdutoRepository produtoRepository;

    /**
     * Método para buscar um produto pelo ID.
     *
     * @param produto_id ID do produto a ser buscado.
     * @return ProdutoEntity Objeto com os dados encontrados pelo ID fornecido.
     * @throws EntityNotFoundException Exception lançada caso o ID não encontre registros no banco.
     */
    public ProdutoEntity buscarProdutoPorId(Long produto_id) {

        return produtoRepository.findById(produto_id).orElseThrow(
                () -> new EntityNotFoundException("Produto com ID " + produto_id + " não encontrado")
        );

    }

}
