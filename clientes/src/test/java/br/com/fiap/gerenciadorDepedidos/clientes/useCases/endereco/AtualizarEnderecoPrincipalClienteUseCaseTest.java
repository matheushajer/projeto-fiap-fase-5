package br.com.fiap.gerenciadorDepedidos.clientes.useCases.endereco;

import br.com.fiap.gerenciadorDepedidos.clientes.entities.EnderecoEntity;
import br.com.fiap.gerenciadorDepedidos.clientes.records.endereco.DadosAtualizarEnderecoDTO;
import br.com.fiap.gerenciadorDepedidos.clientes.repositories.EnderecoRepository;
import br.com.fiap.gerenciadorDepedidos.clientes.useCases.endereço.AtualizarEnderecoPrincipalClienteUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizarEnderecoPrincipalClienteUseCaseTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private AtualizarEnderecoPrincipalClienteUseCase atualizarEnderecoPrincipalClienteUseCase;

    @Test
    void atualizarEndercoPrincipal_DeveAtualizarEnderecoPrincipalQuandoEncontrado() {
        Long clienteId = 1L;
        DadosAtualizarEnderecoDTO atualizarEndereco = new DadosAtualizarEnderecoDTO("12345678", "Logradouro", "123", "Complemento", "Bairro", "Cidade", "UF");

        EnderecoEntity enderecoEntity = new EnderecoEntity();
        when(enderecoRepository.findById(any())).thenReturn(Optional.of(enderecoEntity));
        when(enderecoRepository.findByClienteEntityIdAndIsEnderecoPrincipalIsTrue(any())).thenReturn(enderecoEntity);

        String resultado = atualizarEnderecoPrincipalClienteUseCase.atualizarEndercoPrincipal(clienteId, atualizarEndereco);

        assertEquals("Endereco Pricipal Atualizado com Sucesso", resultado);
        verify(enderecoRepository, times(1)).findById(any());
        verify(enderecoRepository, times(1)).save(enderecoEntity);
        assertEquals(atualizarEndereco.cep(), enderecoEntity.getCep());
        assertEquals(atualizarEndereco.numero(), enderecoEntity.getNumero());
        assertEquals(atualizarEndereco.complemento(), enderecoEntity.getComplemento());
        assertEquals(atualizarEndereco.bairro(), enderecoEntity.getBairro());
        assertEquals(atualizarEndereco.cidade(), enderecoEntity.getCidade());
        assertEquals(atualizarEndereco.uf(), enderecoEntity.getUf());
    }

    @Test
    void atualizarEndercoPrincipal_DeveLancarExcecaoQuandoEnderecoNaoForEncontrado() {
        Long clienteId = 1L;
        DadosAtualizarEnderecoDTO atualizarEndereco = new DadosAtualizarEnderecoDTO("12345678", "Logradouro", "123", "Complemento", "Bairro", "Cidade", "UF");

        when(enderecoRepository.findById(any())).thenReturn(Optional.empty());
        when(enderecoRepository.findByClienteEntityIdAndIsEnderecoPrincipalIsTrue(any())).thenReturn(new EnderecoEntity());

        assertThrows(IllegalArgumentException.class, () -> atualizarEnderecoPrincipalClienteUseCase.atualizarEndercoPrincipal(clienteId, atualizarEndereco));

        verify(enderecoRepository, times(1)).findById(any());
        verify(enderecoRepository, never()).save(any());
    }

    @Test
    void findByClienteEntityIdAndIsEnderecoPrincipalIsTrue_DeveRetornarEnderecoPrincipalQuandoEncontrado() {
        Long clienteId = 1L;

        EnderecoEntity enderecoEntity = new EnderecoEntity();
        when(enderecoRepository.findByClienteEntityIdAndIsEnderecoPrincipalIsTrue(any())).thenReturn(enderecoEntity);

        EnderecoEntity resultado = atualizarEnderecoPrincipalClienteUseCase.findByClienteEntityIdAndIsEnderecoPrincipalIsTrue(clienteId);

        assertEquals(enderecoEntity, resultado);
        verify(enderecoRepository, times(1)).findByClienteEntityIdAndIsEnderecoPrincipalIsTrue(any());
    }
}
