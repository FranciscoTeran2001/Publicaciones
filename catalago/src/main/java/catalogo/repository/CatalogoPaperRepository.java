package catalogo.repository;

import catalogo.model.CatalogoPaper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogoPaperRepository extends JpaRepository<CatalogoPaper, Long> {
}