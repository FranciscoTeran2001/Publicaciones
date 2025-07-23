package publicaciones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperDto {

    private String titulo;
    private int anioPublicacion;
    private String resumen;
    private String editorial;
    private String isbn;

    // Campos específicos de Paper
    private String doi;
    private String conferencia;
    private String url;
    private String tipoPaper;
    private String tipoPublicacion;


    private Long idAutor;
}