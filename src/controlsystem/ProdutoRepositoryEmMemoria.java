package controlsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class ProdutoRepositoryEmMemoria implements ProdutoRepository {

    
    private final Map<String, Produto> bancoDeDados = new HashMap<>();

    @Override
    public void salvar(Produto produto) {
     
        bancoDeDados.put(produto.getId(), produto);
    }

    @Override
    public Optional<Produto> buscarPorId(String id) {
       
        return Optional.ofNullable(bancoDeDados.get(id));
    }

    @Override
    public List<Produto> buscarTodos() {
       
        return new ArrayList<>(bancoDeDados.values());
    }

    @Override
    public void deletar(String id) {
        
        bancoDeDados.remove(id);
    }
}