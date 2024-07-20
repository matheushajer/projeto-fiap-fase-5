package br.com.fiap.gerenciadorDepedidos.pedidos.security.service;

import br.com.fiap.gerenciadorDepedidos.pedidos.security.entidade.Usuario;
import br.com.fiap.gerenciadorDepedidos.pedidos.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository userRepository;

    public Usuario saveUser(Usuario usuario){
        return userRepository.save(usuario);
    }
}