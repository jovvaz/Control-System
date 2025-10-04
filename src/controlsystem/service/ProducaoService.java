

package controlsystem.service;

import controlsystem.FichaTecnica;
import controlsystem.Produto;
import controlsystem.TipoMovimentacao; // Garanta que este import esteja correto
import controlsystem.FichaTecnicaRepository;
import controlsystem.ProdutoRepository;
import java.util.Map;

public class ProducaoService { 





    private final ProdutoRepository produtoRepository;
    private final FichaTecnicaRepository fichaTecnicaRepository;
    private final EstoqueService estoqueService;

    
    public ProducaoService(ProdutoRepository produtoRepository, FichaTecnicaRepository fichaTecnicaRepository, EstoqueService estoqueService) {
        this.produtoRepository = produtoRepository;
        this.fichaTecnicaRepository = fichaTecnicaRepository;
        this.estoqueService = estoqueService;
    }

    public void verificarViabilidadeProducao(String produtoAcabadoId, double quantidadeAProduzir) {
        System.out.println("\n--- VERIFICANDO VIABILIDADE DA PRODUÇÃO ---");

        // 1. Busca a "receita" (Ficha Técnica) do produto. Se não existir, lança um erro.
        FichaTecnica ficha = fichaTecnicaRepository.buscarPorIdDoProduto(produtoAcabadoId)
                .orElseThrow(() -> new IllegalArgumentException("Ficha técnica não encontrada para o produto ID: " + produtoAcabadoId));

        // 2. Itera sobre cada componente (matéria-prima) da receita.
        for (Map.Entry<Produto, Double> entry : ficha.getComponentes().entrySet()) {
            Produto materiaPrima = entry.getKey();
            double quantidadeNecessariaPorUnidade = entry.getValue();

            // 3. Calcula o total necessário daquela matéria-prima para a produção inteira.
            double quantidadeTotalNecessaria = quantidadeNecessariaPorUnidade * quantidadeAProduzir;

            System.out.println("Verificando componente: " + materiaPrima.getNome());
            System.out.println(" -> Necessário por unidade: " + quantidadeNecessariaPorUnidade);
            System.out.println(" -> Total necessário: " + quantidadeTotalNecessaria);

            // 4. Busca o estoque atual da matéria-prima. Lança um erro se a matéria-prima não estiver cadastrada.
            Produto materiaPrimaEmEstoque = produtoRepository.buscarPorId(materiaPrima.getId())
                    .orElseThrow(() -> new IllegalStateException("Componente " + materiaPrima.getNome() + " não cadastrado no estoque."));

            System.out.println(" -> Estoque atual: " + materiaPrimaEmEstoque.getQuantidadeEmEstoque());

            // 5. A REGRA DE NEGÓCIO PRINCIPAL: Verifica se o estoque é suficiente.
            if (materiaPrimaEmEstoque.getQuantidadeEmEstoque() < quantidadeTotalNecessaria) {
                // Se não for suficiente, lança um erro claro e interrompe o processo.
                throw new IllegalStateException("Estoque insuficiente para " + materiaPrima.getNome() +
                        ". Necessário: " + quantidadeTotalNecessaria + ", Disponível: " + materiaPrimaEmEstoque.getQuantidadeEmEstoque());
            }
        }

        // Se o loop terminar sem lançar nenhum erro, a produção é viável.
        System.out.println("--- VIABILIDADE CONFIRMADA: Todos os componentes estão disponíveis em estoque! ---");
    }

  
    public void executarOrdemDeProducao(String produtoAcabadoId, double quantidadeAProduzir) {
       
        verificarViabilidadeProducao(produtoAcabadoId, quantidadeAProduzir);

        System.out.println("\n--- EXECUTANDO ORDEM DE PRODUÇÃO ---");

        
        FichaTecnica ficha = fichaTecnicaRepository.buscarPorIdDoProduto(produtoAcabadoId).get();

       
        for (Map.Entry<Produto, Double> entry : ficha.getComponentes().entrySet()) {
            Produto materiaPrima = entry.getKey();
            double quantidadeNecessariaPorUnidade = entry.getValue();
            double quantidadeTotalConsumida = quantidadeNecessariaPorUnidade * quantidadeAProduzir;

            
            estoqueService.registrarSaida(materiaPrima.getId(), quantidadeTotalConsumida, TipoMovimentacao.SAIDA_PRODUCAO);
            System.out.println(" -> Baixa de estoque: " + quantidadeTotalConsumida + " unidades de " + materiaPrima.getNome());
        }

        
        estoqueService.registrarEntrada(produtoAcabadoId, quantidadeAProduzir, TipoMovimentacao.ENTRADA);
        System.out.println(" -> Alta de estoque: " + quantidadeAProduzir + " unidades de " + ficha.getProdutoAcabado().getNome());
        
        System.out.println("--- PRODUÇÃO FINALIZADA COM SUCESSO! ---");
    }
}