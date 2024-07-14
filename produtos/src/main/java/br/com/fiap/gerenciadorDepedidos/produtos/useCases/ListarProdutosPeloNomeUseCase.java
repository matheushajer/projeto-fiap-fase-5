package br.com.fiap.gerenciadorDepedidos.produtos.useCases;

import br.com.fiap.gerenciadorDepedidos.produtos.entities.ProdutoEntity;
import br.com.fiap.gerenciadorDepedidos.produtos.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Classe para implementar o caso de uso de listar todos
 * os produtos pelo nome.
 */
@Service
public class ListarProdutosPeloNomeUseCase {

    @Autowired
    ProdutoRepository produtoRepository;

    /**
     * Método para buscar todos os produtos que contenham determinado nome.
     *
     * @param nome     Nome usar para a busca.
     * @param pageable Objeto usado para paginação.
     * @return Page<ProdutoEntity> Objeto com os dados da busca paginados.
     */
    public Page<ProdutoEntity> listarProdutosPeloNome(String nome, Pageable pageable) {

        return produtoRepository.findByNomeContaining(nome, pageable);

    }


}
