package br.com.fiap.gerenciadorDepedidos.usuario.request;

import br.com.fiap.gerenciadorDepedidos.usuario.entidade.enumeration.UsuarioRoleEnum;

import java.util.Set;

public record UsuarioRequest(String login, String password, Set<UsuarioRoleEnum> roles) {
}
