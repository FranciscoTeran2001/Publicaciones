package catalogo.dto;

import lombok.Data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperCatalogoDto {
    private String titulo;
    private int anioPublicacion;
    private String resumen;
    private String editorial;
    private String isbn;
    private String doi;
    private String conferencia;
    private String url;
    private String tipoPaper;
    private String autorNombre;
    private String autorApellido;
}
