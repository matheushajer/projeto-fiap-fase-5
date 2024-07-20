package br.com.fiap.gerenciadorDepedidos.produtos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static br.com.fiap.gerenciadorDepedidos.produtos.security.enumeration.UsuarioRoleEnum.ADMIN;
import static br.com.fiap.gerenciadorDepedidos.produtos.security.enumeration.UsuarioRoleEnum.USER;

@Configuration
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET,"/produtos/**").hasAnyRole(USER.getRole(),ADMIN.getRole())
                        .requestMatchers(HttpMethod.POST,"/produtos/criar-produto").hasAnyRole(ADMIN.getRole())
                        .requestMatchers(HttpMethod.PATCH,"/produtos/atualizar-produto/{id}").hasAnyRole(ADMIN.getRole())
                        .requestMatchers(HttpMethod.DELETE, "/produtos/{id}").hasAnyRole(ADMIN.getRole())
                        .requestMatchers("/user").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}