package publicaciones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import publicaciones.dto.LibroDto;
import publicaciones.dto.ResponseDto;
import publicaciones.model.Autor;
import publicaciones.model.Libro;
import publicaciones.repository.AutorRepository;
import publicaciones.repository.LibroRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibrosService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    public ResponseDto crearLibro(LibroDto dto) {
        Autor autor = autorRepository.findById(dto.getIdAutor())
                .orElseThrow(() -> new RuntimeException("No existe el autor con id: " + dto.getIdAutor()));

        Libro libro = new Libro();
        libro.setAutor(autor);
        libro.setTitulo(dto.getTitulo());
        libro.setGenero(dto.getGenero());
        libro.setIsbn(dto.getIsbn());
        libro.setNumeroPaginas(dto.getNumeroPaginas());
        libro.setEditorial(dto.getEditorial());
        libro.setAnioPublicacion(dto.getAnioPublicacion());
        libro.setResumen(dto.getResumen());
        return new ResponseDto(
                "Libro registrado exitosamente",
                libroRepository.save(libro)
        );
    }


    // Listar libros
    public List<ResponseDto> listarLibros() {
        return libroRepository.findAll().stream()
                .map(Libro-> new ResponseDto("Libro de " + Libro.getAutor().getNombre()+" "+ Libro.getAutor().getApellido(), Libro))
                .collect(Collectors.toList());
    }
    public ResponseDto autorPorId(Long id)
    {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el autor con id: " + id));
        return new ResponseDto("Autor con id"+ autor.getId(), autor);
    }
 // Listar libros por autor
    public List<ResponseDto> librosPorAutor(Long id)
    {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el autor con id: " + id));
        return libroRepository.findByAutor(autor).stream()
                .map(libro -> new ResponseDto("Libro de "+libro.getAutor().getNombre() + libro.getTitulo(), libro))
                .collect(Collectors.toList());
    }

    public ResponseDto libroPorId(Long id) {
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el libro con la id: " + id));
        return new ResponseDto("Libro con id " + libro.getId()+" "+ "Del autor: "+libro.getAutor().getNombre()+" "+libro.getAutor().getApellido(), libro);
    }

    // Actualizar libro
    public ResponseDto actualizarLibro(Long id, LibroDto dto) {
        Autor autor = autorRepository.findById(dto.getIdAutor())
                .orElseThrow(() -> new RuntimeException("No existe el autor con id: " + dto.getIdAutor()));
        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el libro con id: " + id));

        libro.setAutor(autor);
        libro.setTitulo(dto.getTitulo());
        libro.setGenero(dto.getGenero());
        libro.setIsbn(dto.getIsbn());
        libro.setNumeroPaginas(dto.getNumeroPaginas());
        libro.setEditorial(dto.getEditorial());
        libro.setAnioPublicacion(dto.getAnioPublicacion());
        libro.setResumen(dto.getResumen());

        return new ResponseDto(
                "Libro actualizado exitosamente",
                libroRepository.save(libro)
        );
    }

    // Eliminar libro
    public ResponseDto eliminarLibro(Long id) {

        Libro libro = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el libro con id: " + id));
        libroRepository.delete(libro);
        return new ResponseDto("Libro eliminado exitosamente" + id, null);
    }

}
