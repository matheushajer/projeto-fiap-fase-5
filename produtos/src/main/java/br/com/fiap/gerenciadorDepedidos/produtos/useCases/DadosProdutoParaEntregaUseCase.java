package br.com.fiap.gerenciadorDepedidos.produtos.useCases;

import br.com.fiap.gerenciadorDepedidos.produtos.adapters.ProdutoAdapter;
import br.com.fiap.gerenciadorDepedidos.produtos.entities.ProdutoEntity;
import br.com.fiap.gerenciadorDepedidos.produtos.records.DadosProdutoParaEntregaDTO;
import br.com.fiap.gerenciadorDepedidos.produtos.repositories.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe implementa a logica para devolver os dados necessários
 * para o microservice de Entregas.
 * <p>
 * Apenas determinados dados do produto são usados pelo serviço.
 */
@Service
public class DadosProdutoParaEntregaUseCase {

    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    ProdutoAdapter produtoAdapter;

    /**
     * Método para buscar e retornar apenas os dados do ProdutoEntity usados
     * pelo microserviço de Entregas.
     *
     * @param produto_id ID do produto a ser buscado.
     * @return DadosProdutoParaEntregaDTO DTO com os dados necessarios para o serviço.
     * @throws EntityNotFoundException Exception lançada caso não seja encontrado um produto com o ID fornecido.
     */
    public DadosProdutoParaEntregaDTO buscarDadosProdutoParaEntrega(Long produto_id) {

        ProdutoEntity produtoEntity = produtoRepository.findById(produto_id).orElseThrow(
                () -> new EntityNotFoundException("Produto com ID " + produto_id + " não encontrado")
        );

        return produtoAdapter.converterParaDadosEntregaDTO(produtoEntity);

    }

}
