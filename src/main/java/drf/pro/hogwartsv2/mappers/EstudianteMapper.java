package drf.pro.hogwartsv2.mappers;

import drf.pro.hogwartsv2.dtos.response.EstudianteDTO;
import drf.pro.hogwartsv2.models.Estudiante;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class EstudianteMapper {

    public EstudianteDTO toDto(Estudiante estudiante) {
        if (estudiante == null) return null;

        EstudianteDTO dto = new EstudianteDTO();

        dto.setId(estudiante.getIdEstudiante());
        dto.setNombre(estudiante.getNombre());
        dto.setApellido(estudiante.getApellido());
        dto.setFechaNacimiento(estudiante.getFechaNacimiento());
        dto.setAnyoCurso(estudiante.getAnyoCurso());

        dto.setMascota(
                estudiante.getMascota() != null ? estudiante.getMascota().getNombre() : null
        );

        if (estudiante.getAsignaturas() != null) {
            List<String> asignaturas = estudiante.getAsignaturas()
                    .stream()
                    .map(ea -> ea.getAsignatura().getNombre())
                    .toList();
            dto.setAsignaturas(asignaturas);
        }

        return dto;
    }
}
