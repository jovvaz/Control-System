package controlsystem; // ou o nome do seu pacote

import controlsystem.Produto;
import controlsystem.TipoProduto;
import controlsystem.ProdutoRepository;
import controlsystem.ProdutoRepositoryEmMemoria;
import controlsystem.service.EstoqueService;
import controlsystem.service.ProducaoService;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {
       

System.out.println("### INICIANDO SIMULAÇÃO DE PRODUÇÃO ###");


ProdutoRepository produtoRepository = new ProdutoRepositoryEmMemoria();
FichaTecnicaRepository fichaTecnicaRepository = new FichaTecnicaRepositoryEmMemoria();
EstoqueService estoqueService = new EstoqueService(produtoRepository);
ProducaoService producaoService = new ProducaoService(produtoRepository, fichaTecnicaRepository);

// --- 2. CADASTRO DE ITENS E ESTOQUE INICIAL ---
System.out.println("\n--- Cadastrando produtos e matérias-primas ---");
Produto tampo = estoqueService.cadastrarNovoProduto("Tampo de Madeira", "Tampo 80x60cm", TipoProduto.MATERIA_PRIMA, "UN");
Produto pe = estoqueService.cadastrarNovoProduto("Pé de Mesa de Metal", "Pé de metal 75cm", TipoProduto.MATERIA_PRIMA, "UN");
Produto mesa = estoqueService.cadastrarNovoProduto("Mesa de Escritório", "Mesa de madeira com pés de metal", TipoProduto.PRODUTO_ACABADO, "UN");

System.out.println("\n--- Dando entrada no estoque de matérias-primas ---");
estoqueService.darEntradaEstoque(tampo.getId(), 12); // Temos 12 tampos
estoqueService.darEntradaEstoque(pe.getId(), 50);   // Temos 50 pés

// --- 3. CRIAÇÃO DA FICHA TÉCNICA (RECEITA) ---
System.out.println("\n--- Criando a Ficha Técnica da Mesa ---");
// Para fazer 1 mesa, precisamos de 1 tampo e 4 pés.
System.out.println("--- DEBUG --- O tipo do objeto 'mesa' é: " + mesa.getTipo());
FichaTecnica fichaMesa = new FichaTecnica(mesa);
fichaMesa.adicionarComponente(tampo, 1);
fichaMesa.adicionarComponente(pe, 4);
fichaTecnicaRepository.salvar(fichaMesa); // Salvamos a "receita"
System.out.println("Ficha técnica da " + mesa.getNome() + " salva com sucesso!");


// Queremos produzir 10 mesas. Precisamos de 10 tampos e 40 pés. Temos estoque!
try {
    producaoService.verificarViabilidadeProducao(mesa.getId(), 10);
} catch (Exception e) {
    System.out.println("ERRO INESPERADO NO CENÁRIO DE SUCESSO: " + e.getMessage());
}

// --- 5. TESTE DE VIABILIDADE (CENÁRIO DE FALHA) ---
// Queremos produzir 13 mesas. Precisamos de 13 tampos (temos 12) e 52 pés (temos 50). Vai falhar!
try {
    System.out.println("\n\n--- Tentando produzir 13 unidades (deve falhar) ---");
    producaoService.verificarViabilidadeProducao(mesa.getId(), 13);
} catch (IllegalStateException | IllegalArgumentException e) {
    
    System.out.println("--- PRODUÇÃO NÃO PODE SER INICIADA! ---");
    System.out.println("MOTIVO: " + e.getMessage());
}

System.out.println("\n### SIMULAÇÃO FINALIZADA ###");
        
}
}