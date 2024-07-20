package br.com.fiap.gerenciadorDepedidos.produtos.security.entidade;

import br.com.fiap.gerenciadorDepedidos.produtos.security.enumeration.UsuarioRoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Table(name = "tb_usuario")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    private String id;
    private String login;
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<UsuarioRoleEnum> roles;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (UsuarioRoleEnum role : this.roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));  // Adicione "ROLE_" prefixo para compatibilidade
        }
        return authorities;
    }
}