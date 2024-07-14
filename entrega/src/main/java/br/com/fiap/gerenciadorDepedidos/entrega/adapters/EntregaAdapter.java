package br.com.fiap.gerenciadorDepedidos.entrega.adapters;

import br.com.fiap.gerenciadorDepedidos.entrega.entites.DadosEntregaEntity;
import br.com.fiap.gerenciadorDepedidos.entrega.entites.EntregaEntity;
import br.com.fiap.gerenciadorDepedidos.entrega.entites.ProdutosEntity;
import br.com.fiap.gerenciadorDepedidos.entrega.http.ClienteClient;
import br.com.fiap.gerenciadorDepedidos.entrega.http.ProdutoClient;
import br.com.fiap.gerenciadorDepedidos.entrega.records.DadosCriacaoEntregaDTO;
import br.com.fiap.gerenciadorDepedidos.entrega.records.DadosCriacaoProdutoParaEntregaDTO;
import br.com.fiap.gerenciadorDepedidos.entrega.records.DadosProdutoParaEntregaDTO;
import br.com.fiap.gerenciadorDepedidos.entrega.records.DadosRetornoCriacaoEntregaDTO;
import br.com.fiap.gerenciadorDepedidos.entrega.services.apiMelhorEnvio.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe adapter para as manipulações de dados relacionadas
 * ao pedido de entrega.
 */
@Service
public class EntregaAdapter {

    @Autowired
    ProdutoClient produtoClient;
    @Autowired
    ClienteClient clienteClient;
    @Autowired
    ApiMelhorEnvioConsumer apiMelhorEnvioConsumer;

    public EntregaEntity converterParaEntity(DadosCriacaoEntregaDTO dadosCriacaoEntregaDTO) throws IOException, InterruptedException {

        String cepDestinatario = clienteClient.buscarCepCliente(dadosCriacaoEntregaDTO.cliente_id());

        List<ProdutosEntity> produtos = new ArrayList<>();

        // Laço abaixo, é responsavel pela iteração dentro da lista de produtos vinda do objeto
        // dadosCriacaoEntregaDTO, e cria um novo ProdutoEntity, consultando o microservice de Produtos.
        int index = 0;
        for (DadosCriacaoProdutoParaEntregaDTO item : dadosCriacaoEntregaDTO.produtos()) {

            ProdutosEntity produtosEntity = new ProdutosEntity();

            produtosEntity.setProduto_id(item.produto_id());
            produtosEntity.setQuantidade(item.quantidade());

            DadosProdutoParaEntregaDTO dadosProdutoParaEntregaDTO = produtoClient.dadosProdutoParaEntrega(produtosEntity.getProduto_id());

            produtosEntity.setAltura(dadosProdutoParaEntregaDTO.altura());
            produtosEntity.setLargura(dadosProdutoParaEntregaDTO.largura());
            produtosEntity.setComprimento(dadosProdutoParaEntregaDTO.comprimento());
            produtosEntity.setPeso(dadosProdutoParaEntregaDTO.peso());

            produtos.add(produtosEntity);

            index++;
        }

        // Trecho abaixo é responsavel pela criação da entidade entrega
        EntregaEntity entregaEntity = new EntregaEntity();

        entregaEntity.setPedido_id(dadosCriacaoEntregaDTO.pedido_id());
        entregaEntity.setCepDestinatario(cepDestinatario);
        entregaEntity.setProdutos(produtos);

        HttpResponse<String> response = apiMelhorEnvioConsumer
                .consultarDadosMelhorEnvio(criarBodyParaRequest(entregaEntity));

        entregaEntity.setDadosEntrega(filtrarResponse(response, entregaEntity));

        return entregaEntity;

    }

    private RequestBodyData criarBodyParaRequest(EntregaEntity entregaEntity) {

        RequestBodyData body = new RequestBodyData();
        FromToData remetente = new FromToData();
        FromToData destinatario = new FromToData();
        List<ProductData> productData = new ArrayList<>();

        remetente.setPostalCode(entregaEntity.getCepRemetente());
        destinatario.setPostalCode(entregaEntity.getCepDestinatario());

        int index = 0;
        for (ProdutosEntity item : entregaEntity.getProdutos()) {
            ProductData produto = getProductData(entregaEntity, index);

            productData.add(produto);

            index++;
        }

        body.setFrom(remetente);
        body.setTo(destinatario);
        body.setProducts(productData);

        return body;

    }

    private DadosEntregaEntity filtrarResponse(HttpResponse<String> response, EntregaEntity entregaEntity) {

        Gson gson = new Gson();

        DadosEntregaEntity dadosEntrega = new DadosEntregaEntity();

        if (response.statusCode() == 200) {
            // Pega o retorno da chamada para o melhor envio, e filtra para uma lista de objetos do tipo
            // HttpResponse<String>
            DadosFiltradosConsultaMelhorEnvio[] dados = gson.fromJson(response.body(), DadosFiltradosConsultaMelhorEnvio[].class);

            if (dados.length >= 2) {
                BigDecimal precoConvertido = new BigDecimal(dados[1].getPrice());

                dadosEntrega.setModalidade(dados[1].getName());
                dadosEntrega.setPreco(precoConvertido);
                dadosEntrega.setPrazo(String.valueOf(dados[1].getDelivery_time()));
                dadosEntrega.setEntregaEntity(entregaEntity);
            }

        }

        return dadosEntrega;

    }

    private static ProductData getProductData(EntregaEntity entregaEntity, int index) {

        ProductData produto = new ProductData();

        produto.setId(entregaEntity.getProdutos().get(index).getProduto_id().toString());
        produto.setWidth(entregaEntity.getProdutos().get(index).getAltura());
        produto.setHeight(entregaEntity.getProdutos().get(index).getLargura());
        produto.setLength(entregaEntity.getProdutos().get(index).getComprimento());
        produto.setWeight(entregaEntity.getProdutos().get(index).getPeso());
        produto.setQuantity(entregaEntity.getProdutos().get(index).getQuantidade());

        return produto;
    }

    public DadosRetornoCriacaoEntregaDTO converterParaDadosRetornoCriacaoDTO(EntregaEntity entregaEntity) {

        return new DadosRetornoCriacaoEntregaDTO(
                entregaEntity.getDadosEntrega().getPrazo(),
                entregaEntity.getDadosEntrega().getPreco(),
                entregaEntity.getId()
        );

    }

}
