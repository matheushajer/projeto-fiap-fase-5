package br.com.fiap.gerenciadorDepedidos.entrega.security.service;

import br.com.fiap.gerenciadorDepedidos.entrega.security.entidade.Usuario;
import br.com.fiap.gerenciadorDepedidos.entrega.security.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UsuarioRepository repository;

    public Usuario saveUser(Usuario usuario) {
        return repository.save(usuario);
    }
}