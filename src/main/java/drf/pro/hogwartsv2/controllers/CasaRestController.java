package drf.pro.hogwartsv2.controllers;


import drf.pro.hogwartsv2.dtos.response.CasaDTO;
import drf.pro.hogwartsv2.services.CasaSerivice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/casa")
@RequiredArgsConstructor
public class CasaRestController {
    private final CasaSerivice casaSerivice;

    @GetMapping
    @Operation(summary = "Lista todas las casa con sus estudiantes")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de casas obtenida correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CasaDTO.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "No hay casas",
                    content = @Content
            )
    })
    public ResponseEntity<List<CasaDTO>> obtenerTodasLasCasa(){
        List<CasaDTO> casas = casaSerivice.obtenerTodasLasCasa();

        if (casas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(casas);
    }


}
