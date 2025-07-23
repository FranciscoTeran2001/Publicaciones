package publicaciones.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="author")
@Getter
@Setter
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false,length=50)
    private String nombre;

    @Column(name = "lastname", nullable = false)
    private String apellido;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "orcid", nullable = false)
    private String orcid;

    @Column
    private String nacionalidad;
    @Column
    private String institucion;


    @OneToMany(mappedBy = "autor") //va el mismo nombre que se esta haciendo la relacion en libro
    @JsonIgnore
    private List<Libro> libros;

    @OneToMany(mappedBy = "autor") //va el mismo nombre que se esta haciendo la relacion en paper
    @JsonIgnore
    private List<Paper> papers;

}
