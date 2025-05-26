package publicaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import publicaciones.model.Autor;
import publicaciones.model.Libro;

import java.util.List;
import java.util.Optional;


public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByAutor(Autor autor);
    ;
}
