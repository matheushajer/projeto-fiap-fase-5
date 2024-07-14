package br.com.fiap.gerenciadorDepedidos.produtos.services;

import br.com.fiap.gerenciadorDepedidos.produtos.useCases.cargaDeProdutos.ImportarProdutosCsvAgendadoUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * Classe Service para tratar a rotina de importação de produtos
 * agendada.
 */
@Service
public class ImportacaoCsvAgendadaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImportacaoCsvAgendadaService.class);

    @Autowired
    ImportarProdutosCsvAgendadoUseCase importarProdutosCsvAgendadoUseCase;

    String nomeArquivoCsv = "carga-de-produtos.csv";

    /**
     * Método temporizado para efetuar a leitura de um arquivo CSV dentro do diretório
     * Resources, a cada 2 horas, desde a iniciação da aplicação.
     */
    @Scheduled(initialDelay = 7200000, fixedRate = 7200000)
    public void importarArquivoCSV() {

        try {

            Resource resource = new ClassPathResource(nomeArquivoCsv);
            File arquivoCsv = resource.getFile();

            importarProdutosCsvAgendadoUseCase.importarProdutosCsvAgendado(arquivoCsv);

            LOGGER.info("Rotina de importação de arquivo CSV concluída com sucesso!");
        } catch (IOException e) {
            LOGGER.warn("Arquivo CSV não encontrado para importação agendada: {}", e.getMessage());
        }

    }

}
