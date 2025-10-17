package br.com.jovvaz.control_system.model;

import br.com.jovvaz.control_system.model.Produto;
import br.com.jovvaz.control_system.model.TipoProduto;
import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "fichas_tecnicas")
public class FichaTecnica {

    @Id
    private String id;


    @OneToOne
    @JoinColumn(name = "produto_acabado_id") // Cria a coluna de chave estrangeira.
    private Produto produtoAcabado;


    @ElementCollection
    @CollectionTable(name = "ficha_tecnica_componentes", joinColumns = @JoinColumn(name = "ficha_tecnica_id"))
    @MapKeyJoinColumn(name = "produto_componente_id")
    @Column(name = "quantidade")
    private Map<Produto, Double> componentes = new HashMap<>();


    public FichaTecnica() {
    }

    public FichaTecnica(Produto produtoAcabado) {
        if (produtoAcabado.getTipo() != TipoProduto.PRODUTO_ACABADO) {
            throw new IllegalArgumentException("A Ficha Técnica só pode ser criada para um PRODUTO_ACABADO.");
        }
        this.id = UUID.randomUUID().toString(); // Geramos um ID único ao criar
        this.produtoAcabado = produtoAcabado;
    }


    public String getId() {
        return id;
    }

    public Produto getProdutoAcabado() {
        return produtoAcabado;
    }

    public Map<Produto, Double> getComponentes() {
        return componentes;
    }


    public void adicionarComponente(Produto materiaPrima, double quantidade) {
        if (materiaPrima.getTipo() != TipoProduto.MATERIA_PRIMA) {
            throw new IllegalArgumentException("Um componente de uma Ficha Técnica deve ser uma MATERIA_PRIMA.");
        }
        this.componentes.put(materiaPrima, quantidade);
    }
}