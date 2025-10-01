/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controlsystem;

import java.util.List;
import java.util.Optional;


public interface ProdutoRepository {
    void salvar(Produto produto);
    
    Optional<Produto> buscarPorId(String id);
    
    List<Produto> buscarTodos();
    
    void deletar(String id);
}

