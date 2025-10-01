/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlsystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author joaov
 */
public class FichaTecnicaRepositoryEmMemoria implements FichaTecnicaRepository {

  
    private final Map<String, FichaTecnica> bancoDeDados = new HashMap<>();

    @Override
    public void salvar(FichaTecnica fichaTecnica) {
        bancoDeDados.put(fichaTecnica.getProdutoAcabado().getId(), fichaTecnica);
    }

    @Override
    public Optional<FichaTecnica> buscarPorIdDoProduto(String produtoId) {
        return Optional.ofNullable(bancoDeDados.get(produtoId));
    }
}