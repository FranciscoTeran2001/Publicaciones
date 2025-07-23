package publicaciones.dto;
import lombok.Data;

@Data
public class PaperCatalogoDto {
    private String tipoPublicacion = "PAPER";
    private Long idPublicacion;
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