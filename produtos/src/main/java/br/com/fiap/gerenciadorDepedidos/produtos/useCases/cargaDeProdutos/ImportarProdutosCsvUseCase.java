package br.com.fiap.gerenciadorDepedidos.produtos.useCases.cargaDeProdutos;

import br.com.fiap.gerenciadorDepedidos.produtos.adapters.ProdutoAdapter;
import br.com.fiap.gerenciadorDepedidos.produtos.entities.ProdutoEntity;
import br.com.fiap.gerenciadorDepedidos.produtos.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Classe para implementar o caso de uso de importar
 * dados vindos de um arquivo CSV.
 */
@Service
public class ImportarProdutosCsvUseCase {

    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    ProdutoAdapter produtoAdapter;

    /**
     * Método para importar um arquivo CSV, com dados para criação de produtos em massa e
     * salvar as informações no banco.
     *
     * @param file Arquivo recebido pela API, em formato CSV.
     */
    public void importarProdutosCsv(MultipartFile file) {

        try {
            List<ProdutoEntity> produtos = produtoAdapter.processarCsv(file.getInputStream());
            produtoRepository.saveAll(produtos);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao processar o arquivo CSV");
        }
    }

}
