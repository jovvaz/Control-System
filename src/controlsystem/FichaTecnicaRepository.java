
package controlsystem;

import controlsystem.FichaTecnica;
import java.util.Optional;

public interface FichaTecnicaRepository {

    void salvar(FichaTecnica fichaTecnica);

    Optional<FichaTecnica> buscarPorIdDoProduto(String produtoId);

  
}