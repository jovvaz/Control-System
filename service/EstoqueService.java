/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.jovvaz.control_system.service;



import br.com.jovvaz.control_system.model.Produto;
import br.com.jovvaz.control_system.model.TipoMovimentacao;
import br.com.jovvaz.control_system.model.TipoProduto;
import br.com.jovvaz.control_system.repository.ProdutoRepository;

import java.util.Optional;
import java.util.UUID; 

public class EstoqueService {

    
    private final ProdutoRepository produtoRepository;

    public EstoqueService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

     
    public Produto darEntradaEstoque(String produtoId, double quantidade) {
       
        Optional<Produto> produtoOptional = produtoRepository.buscarPorId(produtoId);

        
        if (produtoOptional.isEmpty()) {
            
            throw new IllegalArgumentException("Produto não encontrado com o ID: " + produtoId);
        }

        
        Produto produto = produtoOptional.get();

        double novaQuantidade = produto.getQuantEmEstoque() + quantidade;
        produto.setQuantEmEstoque((float) novaQuantidade);

        
        produtoRepository.salvar(produto);

       
        return produto;
    }
   
      public Optional<Produto> consultarEstoque(String produtoId) {
     
        return produtoRepository.buscarPorId(produtoId);
    }

 
public Produto cadastrarNovoProduto(String nome, String descricao, TipoProduto tipo, String unidadeMedida) {

    String id = UUID.randomUUID().toString();

   
    Produto novoProduto = new Produto(id, nome, descricao, 0, unidadeMedida, tipo);

   
    this.produtoRepository.salvar(novoProduto);

    System.out.println("-> Produto cadastrado: " + novoProduto.getNome() + " | ID: " + novoProduto.getId());

    return novoProduto;
}

    void registrarSaida(String id, double quantidadeTotalConsumida, TipoMovimentacao tipoMovimentacao) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void registrarEntrada(String produtoAcabadoId, double quantidadeAProduzir, TipoMovimentacao tipoMovimentacao) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}