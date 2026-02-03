package drf.pro.hogwartsv2.controllers;


import drf.pro.hogwartsv2.dtos.request.create.EstudianteCreateDTO;
import drf.pro.hogwartsv2.dtos.request.update.EstudianteUpdateDTO;
import drf.pro.hogwartsv2.dtos.response.EstudianteDTO;
import drf.pro.hogwartsv2.services.CasaSerivice;
import drf.pro.hogwartsv2.services.EstudianteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/estudiante")
@RequiredArgsConstructor
public class EstudianteRestController {

    private final EstudianteService estudianteService;

    @PostMapping
    public ResponseEntity<EstudianteDTO> crearUsuario(@Valid @RequestBody EstudianteCreateDTO dto) {
        EstudianteDTO usuarioCreado = estudianteService.crearUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado); // 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDTO> actualizarUsuario(@PathVariable Long id, @Valid @RequestBody EstudianteUpdateDTO dto) {
        EstudianteDTO usuarioActualizado = estudianteService.actualizarEstudiante(id, dto);
        return ResponseEntity.ok(usuarioActualizado); // 200 OK
    }

}
