/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlsystem;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author joaov
 */
public class FichaTecnica {
    private Produto produtoAcabado;
    private Map<Produto, Double> componentes;
    
public FichaTecnica(Produto produtoAcabado){
        if (produtoAcabado.getTipo() != TipoProduto.PRODUTO_ACABADO) {
            throw new IllegalArgumentException ("A Ficha Técnica só pode ser criada para um PRODUTO_ACABADO.");
        }
        this.produtoAcabado = produtoAcabado;
        this.componentes = new HashMap<>(); 

            }

public void getComponente(Produto materiaPrima, double quantidade) {
    if (materiaPrima.getTipo() != TipoProduto.MATERIA_PRIMA) {
        throw new IllegalArgumentException("Um componente deve ser uma MATERIA_PRIMA.");
    }
    this.componentes.put(materiaPrima, quantidade);
}

   public Produto getProdutoAcabado() {
    return this.produtoAcabado;
}


public Map<Produto, Double> getComponentes() { 
    return this.componentes;
}

    void adicionarComponente(Produto tampo, int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
        }


