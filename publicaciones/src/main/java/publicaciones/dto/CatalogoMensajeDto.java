package publicaciones.dto;

import lombok.Data;




@Data
public class CatalogoMensajeDto {
    private String tipoPublicacion; // "LIBRO" o "PAPER"
    private Long idPublicacion;    // 👈 Nuevo campo
    private String titulo;
    private int anioPublicacion;
    private String resumen;
    private String editorial;
    private String isbn;
    private String genero;         // Solo para libros
    private int numeroPaginas;     // Solo para libros
    private String doi;            // 👈 Nuevo campo (para papers)
    private String conferencia;    // 👈 Nuevo campo
    private String url;            // 👈 Nuevo campo
    private String tipoPaper;      // 👈 Nuevo campo
    private String autorNombre;
    private String autorApellido;
}