package br.com.fiap.gerenciadorDepedidos.pedidos.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class TokenUserService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token);

            String subject = decodedJWT.getSubject();
            String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
            System.out.println("Decoded Subject: " + subject);
            System.out.println("Decoded Roles: " + String.join(", ", roles));

            return subject;
        } catch (JWTVerificationException exception) {
            System.out.println("Token verification failed: " + exception.getMessage());
            return "";
        }
    }
}