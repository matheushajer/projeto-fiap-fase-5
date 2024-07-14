package br.com.fiap.gerenciadorDepedidos.produtos.entities.enuns;

public enum CategoriaProduto {
    AUDIO,
    BRINQUEDOS,
    DECORACAO,
    ELETRODOMESTICOS,
    INFORMATICA,
    LIVROS,
    GAMES,
    FERRAMENTAS;

    public static CategoriaProduto fromString(String categoria) {
        return CategoriaProduto.valueOf(categoria.toUpperCase());
    }
}
