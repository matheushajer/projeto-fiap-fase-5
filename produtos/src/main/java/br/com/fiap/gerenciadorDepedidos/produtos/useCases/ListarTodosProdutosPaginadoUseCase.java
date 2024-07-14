package br.com.fiap.gerenciadorDepedidos.produtos.useCases;

import br.com.fiap.gerenciadorDepedidos.produtos.entities.ProdutoEntity;
import br.com.fiap.gerenciadorDepedidos.produtos.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Classe para implementar o caso de uso de listar todos os produtos
 * de forma paginada.
 */
@Service
public class ListarTodosProdutosPaginadoUseCase {

    @Autowired
    ProdutoRepository produtoRepository;

    /**
     * Método para listar todos os produtos cadastrados de
     * forma paginada.
     *
     * @param pageable Objeto parametro para a paginação.
     * @return Page<ProdutoEntity> Objeto com os dados a serem exibidos paginados.
     */
    public Page<ProdutoEntity> listarTodosProdutosPaginado(Pageable pageable) {

        return produtoRepository.findAll(pageable);

    }


}
