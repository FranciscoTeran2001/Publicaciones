package publicaciones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import publicaciones.dto.PaperDto;
import publicaciones.dto.ResponseDto;
import publicaciones.service.PaperService;


import java.util.List;

@RestController
@RequestMapping("/papers")
public class PaperController {

    @Autowired
    private PaperService papersService;

    // Crear paper
    @PostMapping
    public ResponseDto crearPaper(@RequestBody PaperDto dto) {
        return papersService.crearPaper(dto);
    }

    // Listar todos los papers
    @GetMapping
    public List<ResponseDto> obtenerPapers() {
        return papersService.listarPapers();
    }

    // Buscar paper por ID
    @GetMapping("/{id}")
    public ResponseDto buscarPorId(@PathVariable Long id) {
        return papersService.paperPorId(id);
    }

    // Listar papers por autor
    @GetMapping("/autor/{id}")
    public List<ResponseDto> papersPorAutor(@PathVariable Long id) {
        return papersService.papersPorAutor(id);
    }

    // Actualizar paper
    @PutMapping("/{id}")
    public ResponseDto actualizarPaper(@PathVariable Long id, @RequestBody PaperDto dto) {
        return papersService.actualizarPaper(id, dto);
    }

    // Eliminar paper
    @DeleteMapping("/{id}")
    public ResponseDto eliminarPaper(@PathVariable Long id) {
        return papersService.eliminarPaper(id);
    }
}
