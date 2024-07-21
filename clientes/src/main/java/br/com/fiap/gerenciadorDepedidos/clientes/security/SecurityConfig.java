package br.com.fiap.gerenciadorDepedidos.clientes.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static br.com.fiap.gerenciadorDepedidos.clientes.security.enumeration.UsuarioRoleEnum.ADMIN;
import static br.com.fiap.gerenciadorDepedidos.clientes.security.enumeration.UsuarioRoleEnum.USER;


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
                        .requestMatchers(HttpMethod.GET, "/clientes/**").hasAnyRole(ADMIN.getRole(), USER.getRole())
                        .requestMatchers(HttpMethod.POST, "/clientes/**").hasAnyRole(ADMIN.getRole())
                        .requestMatchers(HttpMethod.POST, "/enderecos/**").hasAnyRole(ADMIN.getRole())
                        .requestMatchers(HttpMethod.POST, "/telefones/**").hasAnyRole(ADMIN.getRole())
                        .requestMatchers(HttpMethod.PUT, "/enderecos/**").hasAnyRole(ADMIN.getRole())
                        .requestMatchers(HttpMethod.PUT, "/telefones/**").hasAnyRole(ADMIN.getRole())
                        .requestMatchers(HttpMethod.PATCH, "/clientes/**").hasAnyRole(ADMIN.getRole())
                        .requestMatchers(HttpMethod.DELETE, "/enderecos/**").hasAnyRole(ADMIN.getRole())
                        .requestMatchers(HttpMethod.DELETE, "/telefones/**").hasAnyRole(ADMIN.getRole())
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


