package br.com.fiap.gerenciadorDepedidos.entrega.security.repository;

import br.com.fiap.gerenciadorDepedidos.entrega.security.entidade.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
   Usuario findByLogin(String login);
}