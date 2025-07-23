package publicaciones.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Paper extends Publicacion {

    private String doi;
    private String conferencia;
    private String url;
    private String tipoPaper;
    private String tipoPublicacion;

    @ManyToOne
    @JoinColumn(name= "id_autor")
    @JsonIgnore
    private Autor autor;

}
