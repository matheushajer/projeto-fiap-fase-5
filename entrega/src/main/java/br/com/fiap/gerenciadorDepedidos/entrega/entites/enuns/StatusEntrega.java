package br.com.fiap.gerenciadorDepedidos.entrega.entites.enuns;

public enum StatusEntrega {
    CRIADO,
    PREPARANDO_PACOTE,
    ENVIANDO_PARA_TRANSPORTADORA,
    MERCADORIA_RECEBIDA,
    MERCADORIA_EM_TRANSITO,
    ENTREGUE;

    public static StatusEntrega fromString(String statuspedido) {
        return StatusEntrega.valueOf(statuspedido.toUpperCase());
    }
}
