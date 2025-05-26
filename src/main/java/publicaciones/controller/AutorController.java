package publicaciones.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import publicaciones.dto.AutorDto;
import publicaciones.dto.ResponseDto;
import publicaciones.service.AutorService;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {
    //inyeccion de dependencias
    @Autowired
    private AutorService autorService;

    //crear
    @PostMapping
    public ResponseDto crearAutor(@RequestBody AutorDto dto) {
        return autorService.crearAutor(dto);
    }

    @GetMapping
    public List<ResponseDto> obtenerAutores() {
        return autorService.listarAutores();
    }

    @GetMapping("/{id}")
    public ResponseDto buscarPorId(@PathVariable Long id)
    {
        return autorService.autorPorId(id);
    }

    @PutMapping("/{id}")
    public ResponseDto actualizarAutor(@PathVariable Long id, @RequestBody AutorDto dto) {
        return autorService.actualizarAutor(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseDto eliminarAutor(@PathVariable Long id) {
        return autorService.eliminarAutor(id);
    }

}
