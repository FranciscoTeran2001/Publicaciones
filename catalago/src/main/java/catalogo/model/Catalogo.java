package catalogo.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Catalogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private int anioPublicacion;
    private String resumen;
    private String editorial;
    private String isbn;
    private LocalDateTime fechaRegistro;
    private String tipoPublicacion;
}

