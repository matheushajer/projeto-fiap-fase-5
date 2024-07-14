package br.com.fiap.gerenciadorDepedidos.clientes.useCases.telefone;

import br.com.fiap.gerenciadorDepedidos.clientes.entities.ClienteEntity;
import br.com.fiap.gerenciadorDepedidos.clientes.entities.TelefoneEntity;
import br.com.fiap.gerenciadorDepedidos.clientes.records.telefone.DadosAtualizarTelefoneDTO;
import br.com.fiap.gerenciadorDepedidos.clientes.repositories.TelefoneRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AtualizarTelefonePrincipalClienteUseCaseTest {
    @Mock
    private TelefoneRepository telefoneRepository;

    @InjectMocks
    private AtualizarTelefonePrincipalClienteUseCase atualizarTelefonePrincipalClienteUseCase;

    @Test
    void findByclienteEntityIdAndIsTelefonePrincialIsTrue_WhenPrincipalFound_ShouldReturnPrincipal() {
        Long clienteId = 1L;
        TelefoneEntity telefonePrincipal = new TelefoneEntity(1, 55, 11, new ClienteEntity());

        when(telefoneRepository.findByclienteEntityIdAndIsTelefonePrincialIsTrue(clienteId))
                .thenReturn(Optional.of(telefonePrincipal));

        TelefoneEntity result = atualizarTelefonePrincipalClienteUseCase.findByclienteEntityIdAndIsTelefonePrincialIsTrue(clienteId);

        assertEquals(telefonePrincipal, result);
    }

    @Test
    void findByclienteEntityIdAndIsTelefonePrincialIsTrue_WhenPrincipalNotFound_ShouldThrowIllegalArgumentException() {
        Long clienteId = 1L;

        when(telefoneRepository.findByclienteEntityIdAndIsTelefonePrincialIsTrue(clienteId))
                .thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class,
                () -> atualizarTelefonePrincipalClienteUseCase.findByclienteEntityIdAndIsTelefonePrincialIsTrue(clienteId),
                "Telefone principal não encontrado para o cliente com ID: " + clienteId);
    }

    @Test
    void atualizarTelefonePrincipal_WhenPrincipalNotFound_ShouldThrowIllegalArgumentException() {
        Long clienteId = 1L;
        DadosAtualizarTelefoneDTO atualizarTelefone = new DadosAtualizarTelefoneDTO(55, 11, 123456789);

        when(telefoneRepository.findByclienteEntityIdAndIsTelefonePrincialIsTrue(clienteId))
                .thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class,
                () -> atualizarTelefonePrincipalClienteUseCase.atualizarTelefonePrincipal(clienteId, atualizarTelefone),
                "O Telefone Pricipal Não Foi Encontrado");
    }
}
