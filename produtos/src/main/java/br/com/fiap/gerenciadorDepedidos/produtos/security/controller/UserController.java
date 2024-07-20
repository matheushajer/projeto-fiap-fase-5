package br.com.fiap.gerenciadorDepedidos.produtos.security.controller;

import br.com.fiap.gerenciadorDepedidos.produtos.security.entidade.Usuario;
import br.com.fiap.gerenciadorDepedidos.produtos.security.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario){
        return ResponseEntity.ok(service.saveUser(usuario));
    }
}