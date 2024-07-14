package br.com.fiap.gerenciadorDepedidos.entrega.services.apiMelhorEnvio;

import lombok.Data;

@Data
public class ProductData {

    private String id;
    private Integer width;
    private Integer height;
    private Integer length;
    private Double weight;
    private Integer quantity;

}
