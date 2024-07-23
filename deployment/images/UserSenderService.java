package br.com.fiap.gerenciadorDepedidos.usuario.service;

import br.com.fiap.gerenciadorDepedidos.usuario.entidade.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UserSenderService {

    private final WebClient webClient;

    private final String[] serviceUrls = {
            "http://produtos/user",
            "http://clientes/user",
            "http://pedidos/user",
            "http://entrega/user"
    };

    public UserSenderService(WebClient.Builder webClient) {
        this.webClient = webClient.build();
    }

    public void sendUser(Usuario data) {
        for (String url : serviceUrls) {
            sendRequest(url, data);
        }
    }

    private void sendRequest(String url, Usuario data) {
        webClient.post()
                .uri(url)
                .bodyValue(data)
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe(
                        success -> System.out.println("Data sent successfully to " + url),
                        error -> System.err.println("Failed to send data to " + url + ": " + error.getMessage())
                );
    }
}