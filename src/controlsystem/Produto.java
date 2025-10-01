/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlsystem;

/**
 *
 * @author joaov
 */
public class Produto {
    public String nome; 
    public String desc;
    private String id;
    private double quantEmEstoque;
    public String unidadeMedida;
    private TipoProduto tipo;

    public Produto(String nome, String desc, String id, int quantEmEstoque, String unidadeMedida, TipoProduto tipo) {
        this.nome = nome;
        this.desc = desc;
        this.id = id;
        this.quantEmEstoque = quantEmEstoque;
        this.unidadeMedida = unidadeMedida;
        this.tipo = tipo;
    }
    public Produto() {}
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getQuantEmEstoque() {
        return (float) quantEmEstoque;
    }

    public void setQuantEmEstoque(float quantEmEstoque) {
        this.quantEmEstoque = quantEmEstoque;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

   public TipoProduto getTipo() {
    return this.tipo; 
}

    public double getQuantidadeEmEstoque() {
     return this.quantEmEstoque; 
}
      
}

