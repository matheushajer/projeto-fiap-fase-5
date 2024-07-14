package br.com.fiap.gerenciadorDepedidos.usuario.entidade.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UsuarioRoleEnum {
    ADMIN("admin"),
    USER("user");

    private final String role;
}
