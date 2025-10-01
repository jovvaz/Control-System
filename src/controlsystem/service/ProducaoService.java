// Arquivo: ProducaoService.java
// Pacote: controlsystem.service

package controlsystem.service;

import controlsystem.FichaTecnica;
import controlsystem.Produto;
import controlsystem.FichaTecnicaRepository;
import controlsystem.ProdutoRepository;
import java.util.Map;

public class ProducaoService {

    private final ProdutoRepository produtoRepository;
    private final FichaTecnicaRepository fichaTecnicaRepository;

   
    public ProducaoService(ProdutoRepository produtoRepository, FichaTecnicaRepository fichaTecnicaRepository) {
        this.produtoRepository = produtoRepository;
        this.fichaTecnicaRepository = fichaTecnicaRepository;
    }
    
    

public void verificarViabilidadeProducao(String produtoAcabadoId, double quantidadeAProduzir) {
    System.out.println("\n--- VERIFICANDO VIABILIDADE DA PRODUÇÃO ---");

   
    FichaTecnica ficha = fichaTecnicaRepository.buscarPorIdDoProduto(produtoAcabadoId)
            .orElseThrow(() -> new IllegalArgumentException("Ficha técnica não encontrada para o produto ID: " + produtoAcabadoId));

   
    for (Map.Entry<Produto, Double> entry : ficha.getComponentes().entrySet()) {
        Produto materiaPrima = entry.getKey();
        double quantidadeNecessariaPorUnidade = entry.getValue();

       
        double quantidadeTotalNecessaria = quantidadeNecessariaPorUnidade * quantidadeAProduzir;

        System.out.println("Verificando componente: " + materiaPrima.getNome());
        System.out.println(" -> Necessário por unidade: " + quantidadeNecessariaPorUnidade);
        System.out.println(" -> Total necessário: " + quantidadeTotalNecessaria);

    
        Produto materiaPrimaEmEstoque = produtoRepository.buscarPorId(materiaPrima.getId())
                .orElseThrow(() -> new IllegalStateException("Componente " + materiaPrima.getNome() + " não cadastrado no estoque."));

        System.out.println(" -> Estoque atual: " + materiaPrimaEmEstoque.getQuantidadeEmEstoque());

       
        if (materiaPrimaEmEstoque.getQuantidadeEmEstoque() < quantidadeTotalNecessaria) {
            // Se não for suficiente, lança um erro e para tudo.
            throw new IllegalStateException("Estoque insuficiente para " + materiaPrima.getNome() +
                    ". Necessário: " + quantidadeTotalNecessaria + ", Disponível: " + materiaPrimaEmEstoque.getQuantidadeEmEstoque());
        }
    }

    
    System.out.println("--- VIABILIDADE CONFIRMADA: Todos os componentes estão disponíveis em estoque! ---");
}

}