/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlsystem;


/**
 *
 * @author joaov
 */
public class MovimentacaoEstoque {
    private Produto produto;
    private double quantidade;
    private TipoMovimentacao tipo;
    private LocalDataTime dataHora;

    public MovimentacaoEstoque(Produto produto, double quantidade, TipoMovimentacao tipo, LocalDataTime dataHora) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.dataHora = dataHora;
    }

    public Produto getProduto() {
        return produto;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public LocalDataTime getDataHora() {
        return dataHora;
    }
    
}

