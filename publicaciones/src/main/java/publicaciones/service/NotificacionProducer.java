package publicaciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import publicaciones.dto.LibroCatalogoDto;
import publicaciones.dto.NotificacionDTO;
import publicaciones.dto.PaperCatalogoDto;
import publicaciones.model.Libro;
import publicaciones.model.Paper;

@Service
public class NotificacionProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void enviarNotificacion(String mensaje, String tipo) {
        try {
            NotificacionDTO dto = new NotificacionDTO(mensaje, tipo);
            rabbitTemplate.convertAndSend("notificaciones.cola", objectMapper.writeValueAsString(dto));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enviarACatalogo(Libro libro) {
        try {
            LibroCatalogoDto dto = new LibroCatalogoDto();
            dto.setTipoPublicacion("LIBRO");
            dto.setIdPublicacion(libro.getId());
            dto.setTitulo(libro.getTitulo());
            dto.setAnioPublicacion(libro.getAnioPublicacion());
            dto.setResumen(libro.getResumen());
            dto.setEditorial(libro.getEditorial());
            dto.setIsbn(libro.getIsbn());
            dto.setGenero(libro.getGenero());
            dto.setNumeroPaginas(libro.getNumeroPaginas());
            dto.setAutorNombre(libro.getAutor().getNombre());
            dto.setAutorApellido(libro.getAutor().getApellido());

            rabbitTemplate.convertAndSend("catalogo.cola", objectMapper.writeValueAsString(dto));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enviarACatalogoPaper(Paper paper) {
        try {
            PaperCatalogoDto dto = new PaperCatalogoDto();
            dto.setTipoPublicacion("PAPER");
            dto.setIdPublicacion(paper.getId());
            dto.setTitulo(paper.getTitulo());
            dto.setAnioPublicacion(paper.getAnioPublicacion());
            dto.setResumen(paper.getResumen());
            dto.setEditorial(paper.getEditorial());
            dto.setIsbn(paper.getIsbn());
            dto.setDoi(paper.getDoi());
            dto.setConferencia(paper.getConferencia());
            dto.setUrl(paper.getUrl());
            dto.setTipoPaper(paper.getTipoPaper());
            dto.setAutorNombre(paper.getAutor().getNombre());
            dto.setAutorApellido(paper.getAutor().getApellido());

            rabbitTemplate.convertAndSend("catalogo.cola", objectMapper.writeValueAsString(dto));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}