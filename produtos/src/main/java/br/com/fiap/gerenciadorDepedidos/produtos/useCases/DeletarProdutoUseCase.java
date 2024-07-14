package br.com.fiap.gerenciadorDepedidos.produtos.useCases;

import br.com.fiap.gerenciadorDepedidos.produtos.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe para implementar o caso de uso de deletar um produto.
 */
@Service
@Transactional
public class DeletarProdutoUseCase {

    @Autowired
    ProdutoRepository produtoRepository;

    /**
     * Método para efetuar a deleção de um produto no banco.
     *
     * @param produto_id ID do produto a ser deletado.
     */
    public void deletarProduto(Long produto_id) {

        produtoRepository.deleteById(produto_id);

    }


}
