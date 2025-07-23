package catalogo.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CatalogoLibro extends Catalogo {
    private String genero;
    private int numeroPaginas;
    private String autorNombre;
    private String autorApellido;
}
