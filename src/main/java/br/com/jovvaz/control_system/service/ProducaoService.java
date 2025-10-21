package br.com.jovvaz.control_system.service;

import br.com.jovvaz.control_system.model.FichaTecnica;
import br.com.jovvaz.control_system.model.Produto;
import br.com.jovvaz.control_system.repository.FichaTecnicaRepository;
import br.com.jovvaz.control_system.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
public class ProducaoService {

    private final ProdutoRepository produtoRepository;
    private final FichaTecnicaRepository fichaTecnicaRepository;
    private final EstoqueService estoqueService;

    public ProducaoService(ProdutoRepository produtoRepository, FichaTecnicaRepository fichaTecnicaRepository, EstoqueService estoqueService) {
        this.produtoRepository = produtoRepository;
        this.fichaTecnicaRepository = fichaTecnicaRepository;
        this.estoqueService = estoqueService;
    }

    @Transactional(readOnly = true)
    public void verificarViabilidadeProducao(String produtoAcabadoId, double quantidadeAProduzir) {
        System.out.println("\n--- VERIFICANDO VIABILIDADE DA PRODUÇÃO ---");

        Optional<FichaTecnica> fichaTecnicaOpt = fichaTecnicaRepository.findByProdutoAcabadoId(produtoAcabadoId);
        FichaTecnica fichaTecnica = fichaTecnicaOpt
                .orElseThrow(() -> new IllegalArgumentException("Ficha técnica não encontrada para o produto ID: " + produtoAcabadoId));

        for (Map.Entry<Produto, Double> entry : fichaTecnica.getComponentes().entrySet()) {
            Produto materiaPrima = entry.getKey();
            double quantidadeNecessariaPorUnidade = entry.getValue();

            double quantidadeTotalNecessaria = quantidadeNecessariaPorUnidade * quantidadeAProduzir;

            System.out.println("Verificando componente: " + materiaPrima.getNome());
            System.out.println(" -> Necessário por unidade: " + quantidadeNecessariaPorUnidade);
            System.out.println(" -> Total necessário: " + quantidadeTotalNecessaria);

            Optional<Produto> materiaPrimaEstoqueOpt = produtoRepository.findById(materiaPrima.getId());
            Produto materiaPrimaEstoque = materiaPrimaEstoqueOpt
                    .orElseThrow(() -> new IllegalStateException("Componente '" + materiaPrima.getNome() + "' não cadastrado no estoque."));

            System.out.println(" -> Estoque atual: " + materiaPrimaEstoque.getQuantidadeEmEstoque());

            if (materiaPrimaEstoque.getQuantidadeEmEstoque() < quantidadeTotalNecessaria) {
                throw new IllegalStateException("Estoque insuficiente para " + materiaPrima.getNome() +
                        ". Necessário: " + quantidadeTotalNecessaria + ", Disponível: " + materiaPrimaEstoque.getQuantidadeEmEstoque());
            }
        }
        System.out.println("--- VIABILIDADE CONFIRMADA: Todos os componentes estão disponíveis em estoque! ---");
    }

    @Transactional
    public void executarOrdemDeProducao(String produtoAcabadoId, double quantidadeAProduzir) {

        verificarViabilidadeProducao(produtoAcabadoId, quantidadeAProduzir);

        System.out.println("\n--- EXECUTANDO ORDEM DE PRODUÇÃO ---");

        FichaTecnica ficha = fichaTecnicaRepository.findByProdutoAcabadoId(produtoAcabadoId)
                .orElseThrow(() -> new IllegalArgumentException("Ficha técnica não encontrada: " + produtoAcabadoId));

        for (Map.Entry<Produto, Double> entry : ficha.getComponentes().entrySet()) {
            Produto materiaPrima = entry.getKey();
            double quantidadeNecessariaPorUnidade = entry.getValue();
            double quantidadeTotalConsumida = quantidadeNecessariaPorUnidade * quantidadeAProduzir;

            estoqueService.darBaixa(materiaPrima.getId(), quantidadeTotalConsumida);
            System.out.println(" -> Baixa de estoque: " + quantidadeTotalConsumida + " unidades de " + materiaPrima.getNome());
        }

        estoqueService.darEntrada(produtoAcabadoId, quantidadeAProduzir);
        System.out.println(" -> Alta de estoque: " + quantidadeAProduzir + " unidades de " + ficha.getProdutoAcabado().getNome());

        System.out.println("--- PRODUÇÃO FINALIZADA COM SUCESSO! ---");
    }
}