package catalogo.service;

import catalogo.dto.LibroCatalogoDto;
import catalogo.dto.PaperCatalogoDto;
import catalogo.model.CatalogoLibro;
import catalogo.model.CatalogoPaper;
import catalogo.repository.CatalogoLibroRepository;
import catalogo.repository.CatalogoPaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CatalogoService {

    @Autowired
    private CatalogoLibroRepository libroRepository;

    @Autowired
    private CatalogoPaperRepository paperRepository;

    public void registrarLibro(LibroCatalogoDto dto) {
        CatalogoLibro libro = new CatalogoLibro();
        libro.setTitulo(dto.getTitulo());
        libro.setAnioPublicacion(dto.getAnioPublicacion());
        libro.setResumen(dto.getResumen());
        libro.setEditorial(dto.getEditorial());
        libro.setIsbn(dto.getIsbn());
        libro.setGenero(dto.getGenero());
        libro.setNumeroPaginas(dto.getNumeroPaginas());
        libro.setAutorNombre(dto.getAutorNombre());
        libro.setAutorApellido(dto.getAutorApellido());
        libro.setFechaRegistro(LocalDateTime.now());
        libro.setTipoPublicacion("LIBRO");

        libroRepository.save(libro);
    }

    public void registrarPaper(PaperCatalogoDto dto) {
        CatalogoPaper paper = new CatalogoPaper();
        paper.setTitulo(dto.getTitulo());
        paper.setAnioPublicacion(dto.getAnioPublicacion());
        paper.setResumen(dto.getResumen());
        paper.setEditorial(dto.getEditorial());
        paper.setIsbn(dto.getIsbn());
        paper.setDoi(dto.getDoi());
        paper.setConferencia(dto.getConferencia());
        paper.setUrl(dto.getUrl());
        paper.setTipoPaper(dto.getTipoPaper());
        paper.setAutorNombre(dto.getAutorNombre());
        paper.setAutorApellido(dto.getAutorApellido());
        paper.setFechaRegistro(LocalDateTime.now());
        paper.setTipoPublicacion("PAPER");

        paperRepository.save(paper);
    }
}