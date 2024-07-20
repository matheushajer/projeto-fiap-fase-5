package br.com.fiap.gerenciadorDepedidos.usuario.repository;

import br.com.fiap.gerenciadorDepedidos.usuario.entidade.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    UserDetails findByLogin(String login);
}
