package br.com.fiap.gerenciadorDepedidos.produtos.security.service;

import br.com.fiap.gerenciadorDepedidos.produtos.security.entidade.Usuario;
import br.com.fiap.gerenciadorDepedidos.produtos.security.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UsuarioRepository repository;

    public Usuario saveUser(Usuario usuario) {
        return repository.save(usuario);
    }
}