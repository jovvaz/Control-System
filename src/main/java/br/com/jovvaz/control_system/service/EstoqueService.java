package br.com.jovvaz.control_system.service;

import br.com.jovvaz.control_system.model.Produto;
import br.com.jovvaz.control_system.model.TipoProduto;
import br.com.jovvaz.control_system.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EstoqueService {

    private final ProdutoRepository produtoRepository;

    public EstoqueService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto criarProduto(String id, String nome, String desc, TipoProduto tipo, String unidadeMedida) {
        Produto novoProduto = new Produto(id, nome, desc, tipo, unidadeMedida);
        return produtoRepository.save(novoProduto);
    }

    public Produto darEntrada(String produtoId, double quantidade) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com o ID: " + produtoId));

        produto.darEntrada(quantidade);
        return produtoRepository.save(produto);
    }

    public Produto darBaixa(String produtoId, double quantidade) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado com o ID: " + produtoId));

        produto.darBaixa(quantidade);
        return produtoRepository.save(produto);
    }

    public Optional<Produto> buscarPorId(String id) {
        return produtoRepository.findById(id);
    }

    public List<Produto> buscarTodos() {
        return produtoRepository.findAll();
    }
}