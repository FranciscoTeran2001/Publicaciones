package publicaciones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import publicaciones.dto.PaperDto;
import publicaciones.dto.ResponseDto;
import publicaciones.model.Autor;
import publicaciones.model.Paper;
import publicaciones.repository.AutorRepository;
import publicaciones.repository.PaperRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaperService {

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private AutorRepository autorRepository;

    // Crear paper
    public ResponseDto crearPaper(PaperDto dto) {
        Autor autor = autorRepository.findById(dto.getIdAutor())
                .orElseThrow(() -> new RuntimeException("No existe el autor con id: " + dto.getIdAutor()));

        Paper paper = new Paper();
        paper.setAutor(autor);
        paper.setTitulo(dto.getTitulo());
        paper.setAnioPublicacion(dto.getAnioPublicacion());
        paper.setResumen(dto.getResumen());
        paper.setEditorial(dto.getEditorial());
        paper.setIsbn(dto.getIsbn());

        // Campos específicos de Paper
        paper.setDoi(dto.getDoi());
        paper.setConferencia(dto.getConferencia());
        paper.setUrl(dto.getUrl());
        paper.setTipoPaper(dto.getTipoPaper());
        paper.setTipoPublicacion(dto.getTipoPublicacion());

        return new ResponseDto("Paper registrado exitosamente", paperRepository.save(paper));
    }

    // Listar todos los papers
    public List<ResponseDto> listarPapers() {
        return paperRepository.findAll().stream()
                .map(paper -> new ResponseDto(
                        "Paper de " + paper.getAutor().getNombre() + " " + paper.getAutor().getApellido(),
                        paper
                ))
                .collect(Collectors.toList());
    }

    // Buscar paper por ID
    public ResponseDto paperPorId(Long id) {
        Paper paper = paperRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el paper con la id: " + id));
        return new ResponseDto("Paper con id " + paper.getId() +
                " del autor: " + paper.getAutor().getNombre() + " " + paper.getAutor().getApellido(), paper);
    }

    // Listar papers por autor
    public List<ResponseDto> papersPorAutor(Long idAutor) {
        Autor autor = autorRepository.findById(idAutor)
                .orElseThrow(() -> new RuntimeException("No existe el autor con id: " + idAutor));

        return paperRepository.findByAutor(autor).stream()
                .map(paper -> new ResponseDto("Paper de " + autor.getNombre(), paper))
                .collect(Collectors.toList());
    }

    // Actualizar paper
    public ResponseDto actualizarPaper(Long id, PaperDto dto) {
        Autor autor = autorRepository.findById(dto.getIdAutor())
                .orElseThrow(() -> new RuntimeException("No existe el autor con id: " + dto.getIdAutor()));

        Paper paper = paperRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el paper con id: " + id));

        paper.setAutor(autor);
        paper.setTitulo(dto.getTitulo());
        paper.setAnioPublicacion(dto.getAnioPublicacion());
        paper.setResumen(dto.getResumen());
        paper.setEditorial(dto.getEditorial());
        paper.setIsbn(dto.getIsbn());
        paper.setDoi(dto.getDoi());
        paper.setConferencia(dto.getConferencia());
        paper.setUrl(dto.getUrl());
        paper.setTipoPaper(dto.getTipoPaper());
        paper.setTipoPublicacion(dto.getTipoPublicacion());

        return new ResponseDto("Paper actualizado exitosamente", paperRepository.save(paper));
    }

    // Eliminar paper
    public ResponseDto eliminarPaper(Long id) {
        Paper paper = paperRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe el paper con id: " + id));

        paperRepository.delete(paper);
        return new ResponseDto("Paper eliminado exitosamente con id: " + id, null);
    }
}
