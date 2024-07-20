package br.com.fiap.gerenciadorDepedidos.clientes.security.repository;

import br.com.fiap.gerenciadorDepedidos.clientes.security.entidade.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Usuario findByLogin(String login);
}