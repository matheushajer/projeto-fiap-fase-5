package br.com.fiap.gerenciadorDepedidos.produtos.adapters;

import br.com.fiap.gerenciadorDepedidos.produtos.entities.ProdutoEntity;
import br.com.fiap.gerenciadorDepedidos.produtos.entities.enuns.CategoriaProduto;
import br.com.fiap.gerenciadorDepedidos.produtos.records.DadosCriacaoProdutoDTO;
import br.com.fiap.gerenciadorDepedidos.produtos.records.DadosProdutoParaEntregaDTO;
import br.com.fiap.gerenciadorDepedidos.produtos.records.DadosProdutoParaPedidoDTO;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe para efetuar tratamento dos dados vindo das APIs
 * e dos dados retornados.
 */
@Service
public class ProdutoAdapter {

    /**
     * Método para efetuara conversão de um objeto DadosCriacaoProdutoDTO para
     * um objeto ProdutoEntity.
     *
     * @param dadosCriacaoProdutoDTO Objeto com os dados a serem convertidos.
     * @return ProdutoEntity Objeto com os dados convertidos.
     */
    public ProdutoEntity converterParaEntity(DadosCriacaoProdutoDTO dadosCriacaoProdutoDTO) {

        return new ProdutoEntity(
                dadosCriacaoProdutoDTO.nome(),
                dadosCriacaoProdutoDTO.descricao(),
                dadosCriacaoProdutoDTO.preco(),
                dadosCriacaoProdutoDTO.quantidade(),
                dadosCriacaoProdutoDTO.categoria(),
                dadosCriacaoProdutoDTO.altura(),
                dadosCriacaoProdutoDTO.largura(),
                dadosCriacaoProdutoDTO.comprimento(),
                dadosCriacaoProdutoDTO.peso()
        );

    }

    /**
     * Método para converter um objeto do tipo ProdutoEntity para
     * um objeto do tipo DadosCriacaoProdutoDTO.
     *
     * @param produtoEntity Objeto com os dados a serem convertidos.
     * @return DadosCriacaoProdutoDTO Objeto com os dados convertidos.
     */
    public DadosCriacaoProdutoDTO converterParaDTO(ProdutoEntity produtoEntity) {

        return new DadosCriacaoProdutoDTO(
                produtoEntity.getNome(),
                produtoEntity.getDescricao(),
                produtoEntity.getPreco(),
                produtoEntity.getQuantidade(),
                produtoEntity.getCategoria(),
                produtoEntity.getAltura(),
                produtoEntity.getLargura(),
                produtoEntity.getComprimento(),
                produtoEntity.getPeso()
        );

    }

    /**
     * Método para converter os dados de um ProdutoEntity para um objeto
     * do tipo DadosProdutoParaPedidoDTO.
     *
     * @param produtoEntity Objeto com os dados do produto vindo do banco.
     * @return DadosProdutoParaPedidoDTO DTO com os dados necessarios para o microserviço de Pedidos.
     */
    public DadosProdutoParaPedidoDTO converterParaDadosPedidoDTO(ProdutoEntity produtoEntity) {

        return new DadosProdutoParaPedidoDTO(
                produtoEntity.getNome(),
                produtoEntity.getPreco()
        );

    }

    /**
     * Método para converter os dados de um ProdutoEntity para um objeto do tipo
     * DadosProdutoParaEntregaDTO.
     *
     * @param produtoEntity Objeto com os dados do produto vindo do banco.
     * @return DadosProdutoParaEntregaDTO DTO com os dados necessarios para o microserviço de Entregas.
     */
    public DadosProdutoParaEntregaDTO converterParaDadosEntregaDTO(ProdutoEntity produtoEntity) {

        return new DadosProdutoParaEntregaDTO(
                produtoEntity.getAltura(),
                produtoEntity.getLargura(),
                produtoEntity.getComprimento(),
                produtoEntity.getPeso()
        );

    }

    /**
     * Método para processar os dados vindos de um arquivo CSV, com os dados para criação de produtos.
     *
     * @param inputStream Objeto para representar o arquivo vindo da API.
     * @return List<ProdutoEntity> Lista de ProdutoEntity, com os dados processados do arquivo.
     * @throws IOException Exception lançado em casos de erro na leitura do arquivo.
     */
    public List<ProdutoEntity> processarCsv(InputStream inputStream) throws IOException {
        List<ProdutoEntity> produtos;

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {

            produtos = bufferedReader.lines()
                    .skip(1)
                    .map(this::parseLinhaProduto)
                    .collect(Collectors.toList());

        }
        return produtos;
    }

    /**
     * Método para processar aquivos CSV usados na rotina agendada.
     *
     * @param arquivoCsv Objeto arquivo com os dados para serem processdos.
     * @return List<ProdutoEntity> Lista de ProdutoEntity, com os dados processados do arquivo.
     * @throws IOException Exception lançado em casos de erro na leitura do arquivo.
     */
    public List<ProdutoEntity> processarCsvAgendado(File arquivoCsv) throws IOException {
        List<ProdutoEntity> produtos;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(arquivoCsv))) {

            produtos = bufferedReader.lines()
                    .skip(1)
                    .map(this::parseLinhaProduto)
                    .collect(Collectors.toList());

        }
        return produtos;
    }

    /**
     * Método auxiliar para efetuar o parse do arquivo CSV e criar um ProdutoEntity
     * em cada linha do arquivo.
     *
     * @param linha Linha do arquivo CSV, com os dados do produto.
     * @return ProdutoEntity Objeto com os dados vindos do CSV.
     */
    private ProdutoEntity parseLinhaProduto(String linha) {
        String[] campos = linha.split(",");

        String nome = campos[0].trim();
        String descricao = campos[1].trim();
        BigDecimal preco = new BigDecimal(campos[2].trim());
        int quantidade = Integer.parseInt(campos[3].trim());
        CategoriaProduto categoria = CategoriaProduto.fromString(campos[4].trim());
        int altura = Integer.parseInt(campos[5].trim());
        int largura = Integer.parseInt(campos[6].trim());
        int comprimento = Integer.parseInt(campos[7].trim());
        double peso = Double.parseDouble(campos[8].trim());

        return new ProdutoEntity(nome, descricao, preco, quantidade, categoria, altura, largura, comprimento, peso);
    }

}
