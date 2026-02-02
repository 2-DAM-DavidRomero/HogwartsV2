package drf.pro.hogwartsv2.dtos.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import drf.pro.hogwartsv2.models.Estudiante;
import drf.pro.hogwartsv2.models.Profesor;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@JsonPropertyOrder({ "id", "nombre", "fundador", "fantasma", "jefe", "estudiantes" })
@Data
public class CasaDTO {
    private Long id;

    private String nombre;

    private String fundador;

    private String fantasma;

    private String jefe;

    private List<EstudianteDTO> estudiantes;
}
