package catalogo.listener;

import catalogo.dto.LibroCatalogoDto;
import catalogo.dto.PaperCatalogoDto;
import catalogo.service.CatalogoService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CatalogoListener {

    @Autowired
    private CatalogoService catalogoService;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "catalogo.cola")
    public void recibirMensaje(String mensaje) {
        try {
            JsonNode rootNode = objectMapper.readTree(mensaje);
            String tipo = rootNode.get("tipoPublicacion").asText();

            if ("LIBRO".equals(tipo)) {
                LibroCatalogoDto libroDto = objectMapper.treeToValue(rootNode, LibroCatalogoDto.class);
                catalogoService.registrarLibro(libroDto);
            } else if ("PAPER".equals(tipo)) {
                PaperCatalogoDto paperDto = objectMapper.treeToValue(rootNode, PaperCatalogoDto.class);
                catalogoService.registrarPaper(paperDto);
            } else {
                System.err.println("Tipo de publicación no reconocido: " + tipo);
            }
        } catch (Exception e) {
            System.err.println("Error al procesar mensaje: " + e.getMessage());
        }
    }
}
