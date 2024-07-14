package br.com.fiap.gerenciadorDepedidos.produtos.adapters;

import br.com.fiap.gerenciadorDepedidos.produtos.entities.ProdutoEntity;
import br.com.fiap.gerenciadorDepedidos.produtos.entities.enuns.CategoriaProduto;
import br.com.fiap.gerenciadorDepedidos.produtos.records.DadosCriacaoProdutoDTO;
import br.com.fiap.gerenciadorDepedidos.produtos.records.DadosProdutoParaEntregaDTO;
import br.com.fiap.gerenciadorDepedidos.produtos.records.DadosProdutoParaPedidoDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class ProdutoAdapterTest {
    ProdutoAdapter produtoAdapter = new ProdutoAdapter();

    @Test
    void testConverterParaEntity() {

        ProdutoEntity result = produtoAdapter.converterParaEntity(
                new DadosCriacaoProdutoDTO("nome", "descricao", new BigDecimal(0), 0,
                        CategoriaProduto.AUDIO, 0, 0, 0, 0.0));

        Assertions.assertEquals(new ProdutoEntity("nome", "descricao", new BigDecimal(0),
                0, CategoriaProduto.AUDIO, 0, 0, 0, (double) 0), result);
    }

    @Test
    void testConverterParaDTO() {
        DadosCriacaoProdutoDTO result = produtoAdapter.converterParaDTO(
                new ProdutoEntity("nome", "descricao", new BigDecimal(0), 0,
                        CategoriaProduto.AUDIO, 0, 0, 0, 0.0));

        Assertions.assertEquals(new DadosCriacaoProdutoDTO("nome", "descricao", new BigDecimal(0),
                0, CategoriaProduto.AUDIO, 0, 0, 0, 0.0), result);
    }

    @Test
    void testConverterParaDadosPedidoDTO() {
        DadosProdutoParaPedidoDTO result = produtoAdapter.converterParaDadosPedidoDTO(
                new ProdutoEntity("nome", null, new BigDecimal(0), null, null, null, null, null, null));

        Assertions.assertEquals(new DadosProdutoParaPedidoDTO("nome", new BigDecimal(0)), result);
    }

    @Test
    void testConverterParaDadosEntregaDTO() {
        DadosProdutoParaEntregaDTO result = produtoAdapter.converterParaDadosEntregaDTO(
                new ProdutoEntity(null, null, null, null, null, 0, 0, 0, 0.0));

        Assertions.assertEquals(new DadosProdutoParaEntregaDTO(0, 0, 0, 0.0), result);
    }


}