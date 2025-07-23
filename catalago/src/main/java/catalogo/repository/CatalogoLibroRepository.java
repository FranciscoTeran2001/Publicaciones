package catalogo.repository;

import catalogo.model.CatalogoLibro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogoLibroRepository extends JpaRepository<CatalogoLibro, Long> {
}