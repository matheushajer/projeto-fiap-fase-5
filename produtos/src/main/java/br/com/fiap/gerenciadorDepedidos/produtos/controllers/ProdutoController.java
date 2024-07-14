package br.com.fiap.gerenciadorDepedidos.produtos.controllers;

import br.com.fiap.gerenciadorDepedidos.produtos.entities.ProdutoEntity;
import br.com.fiap.gerenciadorDepedidos.produtos.records.DadosAtualizacaoProdutoDTO;
import br.com.fiap.gerenciadorDepedidos.produtos.records.DadosCriacaoProdutoDTO;
import br.com.fiap.gerenciadorDepedidos.produtos.records.DadosProdutoParaEntregaDTO;
import br.com.fiap.gerenciadorDepedidos.produtos.records.DadosProdutoParaPedidoDTO;
import br.com.fiap.gerenciadorDepedidos.produtos.useCases.*;
import br.com.fiap.gerenciadorDepedidos.produtos.useCases.cargaDeProdutos.ImportarProdutosCsvUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Classe Controller das operações do Produto.
 */
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    CriarProdutoUseCase criarProdutoUseCase;
    @Autowired
    DeletarProdutoUseCase deletarProdutoUseCase;
    @Autowired
    AtualizarDadosProdutoUseCase atualizarDadosProdutoUseCase;
    @Autowired
    ListarTodosProdutosPaginadoUseCase listarTodosProdutosPaginado;
    @Autowired
    BuscarProdutoPorIdUseCase buscarProdutoPorIdUseCase;
    @Autowired
    ListarProdutosPeloNomeUseCase listarProdutosPeloNomeUseCase;
    @Autowired
    DadosProdutoParaPedidoUseCase dadosProdutoParaPedidoUseCase;
    @Autowired
    DadosProdutoParaEntregaUseCase dadosProdutoParaEntregaUseCase;
    @Autowired
    ListarProdutosPelaCategoriaUseCase listarProdutosPelaCategoriaUseCase;
    @Autowired
    ImportarProdutosCsvUseCase importarProdutosCsvUseCase;

    @GetMapping
    public Page<ProdutoEntity> listarTodosProdutosPaginado(Pageable pageable) {

        return listarTodosProdutosPaginado.listarTodosProdutosPaginado(pageable);

    }

    @GetMapping("/{produto_id}")
    public ResponseEntity<ProdutoEntity> buscarProdutoPorId(@PathVariable Long produto_id) {
        return ResponseEntity.ok(buscarProdutoPorIdUseCase.buscarProdutoPorId(produto_id));
    }

    @GetMapping("/listar-por-nome/{nomeProduto}")
    public Page<ProdutoEntity> listarProdutosPeloNome(@PathVariable String nomeProduto, Pageable pageable) {

        return listarProdutosPeloNomeUseCase.listarProdutosPeloNome(nomeProduto, pageable);

    }

    @GetMapping("/listar-por-categoria/{nomeCategoria}")
    public Page<ProdutoEntity> listarProdutosPelaCategoria(@PathVariable String nomeCategoria, Pageable pageable) {

        return listarProdutosPelaCategoriaUseCase.listarProdutosPelaCategoria(nomeCategoria, pageable);

    }

    @GetMapping("/dados-pedido/{produto_id}")
    public ResponseEntity<DadosProdutoParaPedidoDTO> dadosProdutoParaPedido(@PathVariable Long produto_id) {

        return ResponseEntity.ok(dadosProdutoParaPedidoUseCase.buscarDadosDoProdutoParaPedidoService(produto_id));

    }

    @GetMapping("/dados-entrega/{produto_id}")
    public ResponseEntity<DadosProdutoParaEntregaDTO> dadosProdutoParaEntrega(@PathVariable Long produto_id) {

        return ResponseEntity.ok(dadosProdutoParaEntregaUseCase.buscarDadosProdutoParaEntrega(produto_id));

    }

    @PostMapping("/criar-produto")
    public ResponseEntity<DadosCriacaoProdutoDTO> criarProduto(
            @RequestBody @Validated DadosCriacaoProdutoDTO dadosCriacaoProdutoDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(criarProdutoUseCase.criarProduto(dadosCriacaoProdutoDTO));

    }

    @DeleteMapping("/{produto_id}")
    public void deletarProduto(@PathVariable Long produto_id) {

        deletarProdutoUseCase.deletarProduto(produto_id);

    }

    @PatchMapping("/atualizar-produto/{produto_id}")
    public ResponseEntity<ProdutoEntity> atualizaDadosProduto(
            @PathVariable Long produto_id, @RequestBody @Validated DadosAtualizacaoProdutoDTO dadosAtualizacaoProdutoDTO) {

        return ResponseEntity.ok(atualizarDadosProdutoUseCase.atualizarDadosProduto(produto_id, dadosAtualizacaoProdutoDTO));

    }

    @PostMapping("/importar-csv")
    public ResponseEntity<String> importarCsv(@RequestParam("file") MultipartFile file) {
        importarProdutosCsvUseCase.importarProdutosCsv(file);
        return ResponseEntity.ok("CSV Importado com sucesso!");
    }

}
