package drf.pro.hogwartsv2.controllers;


import drf.pro.hogwartsv2.dtos.request.create.EstudianteCreateDTO;
import drf.pro.hogwartsv2.dtos.request.update.EstudianteUpdateDTO;
import drf.pro.hogwartsv2.dtos.response.CasaDTO;
import drf.pro.hogwartsv2.dtos.response.EstudianteDTO;
import drf.pro.hogwartsv2.services.CasaSerivice;
import drf.pro.hogwartsv2.services.EstudianteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Crear un nuevo estudiante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Estudiante creado correctamente",
                    content = @Content(schema = @Schema(implementation = EstudianteDTO.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Errores de validación en los campos enviados",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    // Un solo ejemplo con un par de campos basta para entender el formato
                                    example = "{\"anyoCurso\": \"Debe ser menor que 7\", \"fechaNacimiento\": \"Es obligatoria\"}"
                            )
                    )
            )
    })
    public ResponseEntity<EstudianteDTO> crearUsuario(@Valid @RequestBody EstudianteCreateDTO dto) {
        EstudianteDTO usuarioCreado = estudianteService.crearUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado); // 201 Created
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar los datos de un estudiante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estudiante actualizado correctamente",
                    content = @Content(schema = @Schema(implementation = EstudianteDTO.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "El estudiante no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "Usuario no encontrado con id: 1000"
                            )
                    )
            )
    })
    public ResponseEntity<EstudianteDTO> actualizarUsuario(@PathVariable Long id, @Valid @RequestBody EstudianteUpdateDTO dto) {
        EstudianteDTO usuarioActualizado = estudianteService.actualizarEstudiante(id, dto);
        return ResponseEntity.ok(usuarioActualizado); // 200 OK
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un estudiante por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Estudiante eliminado correctamente",
                    content = @Content(schema = @Schema(implementation = EstudianteDTO.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "El estudiante no existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "Usuario no encontrado con id: 1000"
                            )
                    )
            )
    })
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        estudianteService.eliminarUsuario(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }


}
