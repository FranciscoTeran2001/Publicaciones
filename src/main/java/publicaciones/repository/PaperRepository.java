package publicaciones.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import publicaciones.model.Autor;
import publicaciones.model.Paper;

import java.util.List;

public interface PaperRepository extends JpaRepository<Paper, Long> {
    List<Paper> findByAutor(Autor autor);
}
