package br.com.fiap.gerenciadorDepedidos.usuario.request;

import br.com.fiap.gerenciadorDepedidos.usuario.entidade.enumeration.UsuarioRoleEnum;

public record UsuarioRequest(String login, String password, UsuarioRoleEnum role) {
}
