package br.com.fiap.gerenciadorDepedidos.produtos.security.repository;

import br.com.fiap.gerenciadorDepedidos.produtos.security.entidade.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
   Usuario findByLogin(String login);
}