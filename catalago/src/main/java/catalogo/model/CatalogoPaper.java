package catalogo.model;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CatalogoPaper extends Catalogo {
    private String doi;
    private String conferencia;
    private String url;
    private String tipoPaper;
    private String autorNombre;
    private String autorApellido;
}