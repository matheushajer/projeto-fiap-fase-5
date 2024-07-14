package br.com.fiap.gerenciadorDepedidos.produtos.useCases;

import br.com.fiap.gerenciadorDepedidos.produtos.entities.ProdutoEntity;
import br.com.fiap.gerenciadorDepedidos.produtos.records.DadosAtualizacaoProdutoDTO;
import br.com.fiap.gerenciadorDepedidos.produtos.repositories.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe para implementação do caso se uso de atualizar
 * dados de um produto.
 */
@Service
public class AtualizarDadosProdutoUseCase {

    @Autowired
    ProdutoRepository produtoRepository;

    /**
     * Método para efetuar a atualização dos dados de um produto.
     *
     * @param produto_id                 ID do produto que será atualizado.
     * @param dadosAtualizacaoProdutoDTO Objeto com os dados que serão atualizados.
     * @return ProdutoEntity com os dados atualizados.
     * @throws EntityNotFoundException Exception lançada, caso id não encontre nenhum produto.
     */
    public ProdutoEntity atualizarDadosProduto(Long produto_id, DadosAtualizacaoProdutoDTO dadosAtualizacaoProdutoDTO) {

        ProdutoEntity produtoEntity = produtoRepository.findById(produto_id).orElseThrow(
                () -> new EntityNotFoundException("ID produto fornecido não foi encontrado"));

        if (dadosAtualizacaoProdutoDTO.nome() != null && !dadosAtualizacaoProdutoDTO.nome().isEmpty()) {
            produtoEntity.setNome(dadosAtualizacaoProdutoDTO.nome());
        }

        if (dadosAtualizacaoProdutoDTO.descricao() != null && !dadosAtualizacaoProdutoDTO.descricao().isEmpty()) {
            produtoEntity.setDescricao(dadosAtualizacaoProdutoDTO.descricao());
        }

        if (dadosAtualizacaoProdutoDTO.preco() != null) {
            produtoEntity.setPreco(dadosAtualizacaoProdutoDTO.preco());
        }

        if (dadosAtualizacaoProdutoDTO.quantidade() != null) {
            produtoEntity.setQuantidade(dadosAtualizacaoProdutoDTO.quantidade());
        }

        if (dadosAtualizacaoProdutoDTO.categoria() != null) {
            produtoEntity.setCategoria(dadosAtualizacaoProdutoDTO.categoria());
        }

        if (dadosAtualizacaoProdutoDTO.altura() != null) {
            produtoEntity.setAltura(dadosAtualizacaoProdutoDTO.altura());
        }

        if (dadosAtualizacaoProdutoDTO.largura() != null) {
            produtoEntity.setLargura(dadosAtualizacaoProdutoDTO.largura());
        }

        if (dadosAtualizacaoProdutoDTO.comprimento() != null) {
            produtoEntity.setComprimento(dadosAtualizacaoProdutoDTO.comprimento());
        }

        if (dadosAtualizacaoProdutoDTO.peso() != null) {
            produtoEntity.setPeso(dadosAtualizacaoProdutoDTO.peso());
        }

        produtoRepository.save(produtoEntity);

        return produtoEntity;

    }

}
