package br.com.fiap.gerenciadorDepedidos.entrega.services.apiMelhorEnvio;

import lombok.Data;

import java.util.List;

@Data
public class RequestBodyData {

    private FromToData from;
    private FromToData to;
    private List<ProductData> products;

}
