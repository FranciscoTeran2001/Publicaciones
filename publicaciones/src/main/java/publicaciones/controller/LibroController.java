package publicaciones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import publicaciones.dto.LibroDto;
import publicaciones.dto.ResponseDto;
import publicaciones.service.LibrosService;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibrosService librosService;

    @PostMapping
    public ResponseDto crearLibro(@RequestBody LibroDto dto) {
        return librosService.crearLibro(dto);
    }
    // Listar libros
    @GetMapping
    public List<ResponseDto> obtenerLibros() {
        return librosService.listarLibros();
    }

   @GetMapping("autor/{id}")
    public List<ResponseDto> librosPorAutor(@PathVariable Long id) {
        return librosService.librosPorAutor(id);
    }

    @GetMapping("/{id}")
    public ResponseDto buscarPorId(@PathVariable Long id)
    {
        return librosService.libroPorId(id);
    }

    @PutMapping("/{id}")
    public ResponseDto actualizarAutor(@PathVariable Long id, @RequestBody LibroDto dto) {
        return librosService.actualizarLibro(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseDto eliminarAutor(@PathVariable Long id) {
        return librosService.eliminarLibro(id);
    }


}
