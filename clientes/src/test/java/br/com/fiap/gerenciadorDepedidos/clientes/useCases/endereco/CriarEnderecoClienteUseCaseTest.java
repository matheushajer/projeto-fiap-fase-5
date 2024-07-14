package br.com.fiap.gerenciadorDepedidos.clientes.useCases.endereco;

import br.com.fiap.gerenciadorDepedidos.clientes.entities.ClienteEntity;
import br.com.fiap.gerenciadorDepedidos.clientes.entities.EnderecoEntity;
import br.com.fiap.gerenciadorDepedidos.clientes.records.endereco.DadosAtualizarEnderecoDTO;
import br.com.fiap.gerenciadorDepedidos.clientes.repositories.ClienteRepository;
import br.com.fiap.gerenciadorDepedidos.clientes.repositories.EnderecoRepository;
import br.com.fiap.gerenciadorDepedidos.clientes.useCases.endereço.CriarEnderecoClienteUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarEnderecoClienteUseCaseTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private CriarEnderecoClienteUseCase criarEnderecoClienteUseCase;

    @Test
    void criarEnderecoCliente_DeveCriarEnderecoParaClienteExistente() {
        Long clienteId = 1L;
        List<DadosAtualizarEnderecoDTO> atualizarEnderecos = new ArrayList<>();
        atualizarEnderecos.add(new DadosAtualizarEnderecoDTO("12345678", "Logradouro", "123", "Complemento", "Bairro", "Cidade", "UF"));

        ClienteEntity clienteEntity = new ClienteEntity();
        when(clienteRepository.findById(any())).thenReturn(Optional.of(clienteEntity));

        String resultado = criarEnderecoClienteUseCase.criarEnderecoCliente(clienteId, atualizarEnderecos);

        assertEquals("Endereco Criado Com Sucesso", resultado);
        verify(clienteRepository, times(1)).findById(clienteId);
        verify(enderecoRepository, times(1)).save(any(EnderecoEntity.class));
    }

    @Test
    void criarEnderecoCliente_DeveLancarExcecaoQuandoClienteNaoForEncontrado() {
        Long clienteId = 1L;
        List<DadosAtualizarEnderecoDTO> atualizarEnderecos = new ArrayList<>();
        atualizarEnderecos.add(new DadosAtualizarEnderecoDTO("12345678", "Logradouro", "123", "Complemento", "Bairro", "Cidade", "UF"));

        when(clienteRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> criarEnderecoClienteUseCase.criarEnderecoCliente(clienteId, atualizarEnderecos));

        verify(clienteRepository, times(1)).findById(clienteId);
        verify(enderecoRepository, never()).save(any());
    }
}
