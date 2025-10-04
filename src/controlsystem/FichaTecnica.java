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

    public void adicionarComponente(Produto materiaPrima, double quantidade) {
    // Validação: Garante que estamos adicionando apenas matérias-primas como componentes
    if (materiaPrima.getTipo() != TipoProduto.MATERIA_PRIMA) {
        throw new IllegalArgumentException("Um componente de uma Ficha Técnica deve ser uma MATERIA_PRIMA.");
    }

    // Ação: Adiciona o componente (chave) e a quantidade (valor) ao nosso "dicionário" Map
    this.componentes.put(materiaPrima, quantidade);
}
    
    
        }


