package br.com.fiap.gerenciadorDepedidos.pedidos.entities.enuns;

/**
 * Enumerador que define os possíveis estados de um pedido.
 */
public enum StatusPedido {
    CRIADO,              // O pedido foi criado e está aguardando processamento.
    PROCESSANDO,         // O pedido está sendo processado.
    AGUARDANDO_PAGAMENTO,// O pedido está aguardando o pagamento ser confirmado.
    PAGO,                // O pagamento foi confirmado.
    PEDIDO_ENVIADO,      // O pedido foi enviado para entrega.
    ENTREGUE,            // O pedido foi entregue ao cliente.
    CANCELADO;           // O pedido foi cancelado.

    /**
     * Converte uma string para um status de pedido correspondente.
     *
     * @param categoria A string que representa o status do pedido.
     * @return StatusPedido correspondente à string fornecida.
     */
    public static StatusPedido fromString(String categoria) {
        return StatusPedido.valueOf(categoria.toUpperCase());
    }

}
