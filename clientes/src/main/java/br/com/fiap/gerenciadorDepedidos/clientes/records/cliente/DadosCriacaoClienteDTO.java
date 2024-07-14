package br.com.fiap.gerenciadorDepedidos.clientes.records.cliente;

import br.com.fiap.gerenciadorDepedidos.clientes.records.endereco.DadosCriacaoEnderecoDTO;
import br.com.fiap.gerenciadorDepedidos.clientes.records.telefone.DadosCriacaoTelefoneDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

/**
 * Classe DTO para representar os dados vindo da API para criação
 * de um Cliente
 *
 * @param nome
 * @param cpf
 * @param email
 * @param dadosCriacaoTelefoneDTO
 */
public record DadosCriacaoClienteDTO(
        @NotBlank(message = "É obrigatório informar o nome do cliente")
        String nome,
        @CPF(message = "Campo CPF informado inválido!")
        String cpf,
        @Email(message = "Email informado inválido!")
        String email,
        @Valid
        List<DadosCriacaoTelefoneDTO> dadosCriacaoTelefoneDTO,
        @Valid
        List<DadosCriacaoEnderecoDTO> dadosCriacaoEnderecoDTO
) {
}
