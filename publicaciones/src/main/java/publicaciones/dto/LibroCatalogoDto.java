package publicaciones.dto;



import lombok.Data;

@Data
public class LibroCatalogoDto {
    private String tipoPublicacion = "LIBRO";
    private Long idPublicacion;
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
