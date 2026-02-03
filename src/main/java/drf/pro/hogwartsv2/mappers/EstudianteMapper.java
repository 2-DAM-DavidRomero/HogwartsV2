package drf.pro.hogwartsv2.mappers;

import drf.pro.hogwartsv2.dtos.request.create.EstudianteCreateDTO;
import drf.pro.hogwartsv2.dtos.request.update.EstudianteUpdateDTO;
import drf.pro.hogwartsv2.dtos.response.EstudianteDTO;
import drf.pro.hogwartsv2.models.Casa;
import drf.pro.hogwartsv2.models.Estudiante;
import drf.pro.hogwartsv2.repositories.CasaRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
@Data
public class EstudianteMapper {
    @Autowired
    private CasaRepository casaRepository;

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

    public Estudiante toEntity(EstudianteCreateDTO dto){

        if (dto == null) return null;

        Estudiante estudiante = new Estudiante();

        estudiante.setNombre(dto.getNombre());
        estudiante.setApellido(dto.getApellido());
        Casa casa = casaRepository.findById(dto.getIdCasa())
                .orElseThrow(() -> new RuntimeException("Casa no encontrada"));

        estudiante.setCasa(casa);

        estudiante.setFechaNacimiento(Date.valueOf(dto.getFechaNacimiento()));
        estudiante.setAnyoCurso(dto.getAnyoCurso());

        return estudiante;
    }

    public void updateEntityFromDto(EstudianteUpdateDTO dto, Estudiante estudiante) {
        if (dto == null || estudiante == null) return;

        estudiante.setAnyoCurso(dto.getAnyoCurso());
    }
}
