package br.com.fiap.gerenciadorDepedidos.entrega.records;

import java.math.BigDecimal;

/**
 * Classe DTO para representar os dados que serão retornados para a
 * API de criação de um pedido de entrega
 *
 * @param prazoDeEntrega
 * @param frete
 * @param codigoDeRastreio
 */
public record DadosRetornoCriacaoEntregaDTO(

        String prazoDeEntrega,
        BigDecimal frete,
        Long codigoDeRastreio

) {

}
