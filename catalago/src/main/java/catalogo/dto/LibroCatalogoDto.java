package catalogo.dto;

import lombok.Data;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroCatalogoDto {
    private String titulo;
    private int anioPublicacion;
    private String resumen;
    private String editorial;
    private String isbn;
    private String genero;
    private int numeroPaginas;
    private String autorNombre;
    private String autorApellido;
}
