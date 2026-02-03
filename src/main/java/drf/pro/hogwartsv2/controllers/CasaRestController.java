package drf.pro.hogwartsv2.controllers;


import drf.pro.hogwartsv2.dtos.response.CasaDTO;
import drf.pro.hogwartsv2.services.CasaSerivice;
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
    public ResponseEntity<List<CasaDTO>> obtenerTodasLasCasa(){
        List<CasaDTO> casas = casaSerivice.obtenerTodasLasCasa();

        if (casas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(casas);
    }


}
