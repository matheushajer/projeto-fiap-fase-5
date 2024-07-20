package br.com.fiap.gerenciadorDepedidos.clientes.security.service;

import br.com.fiap.gerenciadorDepedidos.clientes.security.entidade.Usuario;
import br.com.fiap.gerenciadorDepedidos.clientes.security.repository.UsuarioRepository;
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