
package br.com.jovvaz.control_system.repository;
import br.com.jovvaz.control_system.model.FichaTecnica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;



@Repository
public interface FichaTecnicaRepository extends JpaRepository<FichaTecnica, String> {
    Optional<FichaTecnica> findByProdutoAcabadoId(String produtoId);
}
