package publicaciones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import publicaciones.dto.AutorDto;
import publicaciones.dto.ResponseDto;
import publicaciones.model.Autor;
import publicaciones.repository.AutorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private NotificacionProducer producer;
    //crear
    public ResponseDto crearAutor(AutorDto dto) {
        Autor autor = new Autor();
        autor.setNombre(dto.getNombre());
        autor.setApellido(dto.getApellido());
        autor.setEmail(dto.getEmail());
        autor.setNacionalidad(dto.getNacionalidad());
        autor.setInstitucion(dto.getInstitucion());
        autor.setOrcid(dto.getOrcid());

        Autor save = autorRepository.save(autor);
        // Enviar notificación al crear un autor
        producer.enviarNotificacion("Autor"+ save.getNombre()+ " ha sido registrado","Autor");
        // Retornar la respuesta
        return new ResponseDto(
                "Autor registrado exitosamente",save);
    }
    public List<ResponseDto> listarAutores() {
        return autorRepository.findAll().stream()
                .map(autor -> new ResponseDto("Autor"+autor.getApellido(), autor))
                .collect(Collectors.toList());
    }

    public ResponseDto autorPorId(Long id)
    {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el autor con id: " + id));
        return new ResponseDto("Autor con id"+ autor.getId(), autor);
    }

    public ResponseDto actualizarAutor(Long id, AutorDto dto) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el autor con id: " + id));
        autor.setNombre(dto.getNombre());
        autor.setApellido(dto.getApellido());
        autor.setEmail(dto.getEmail());
        autor.setNacionalidad(dto.getNacionalidad());
        autor.setInstitucion(dto.getInstitucion());
        autor.setOrcid(dto.getOrcid());
        return new ResponseDto(
                "Autor actualizado exitosamente",
                autorRepository.save(autor));
    }

    public ResponseDto eliminarAutor(Long id) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el autor con id: " + id));
        autorRepository.delete(autor);
        return new ResponseDto("Autor eliminado exitosamente"+ id, null);
    }
}
