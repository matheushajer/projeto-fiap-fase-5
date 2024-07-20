package br.com.fiap.gerenciadorDepedidos.clientes.security.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UsuarioRoleEnum {
    ADMIN("ADMIN"),
    USER("USER");

    private final String role;
}