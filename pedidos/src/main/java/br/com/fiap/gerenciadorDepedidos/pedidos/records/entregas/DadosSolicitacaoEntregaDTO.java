package br.com.fiap.gerenciadorDepedidos.pedidos.records.entregas;

import java.util.List;

public record DadosSolicitacaoEntregaDTO(
        Long pedido_id,
        Long cliente_id,
        List<DadosProdutoSolicitacaoEntregaDTO> produtos
) {
}
