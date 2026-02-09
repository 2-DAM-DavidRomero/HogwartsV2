package drf.pro.hogwartsv2.mappers;

import drf.pro.hogwartsv2.dtos.request.create.EstudianteCreateDTO;
import drf.pro.hogwartsv2.dtos.request.update.EstudianteUpdateDTO;
import drf.pro.hogwartsv2.dtos.response.EstudianteDTO;
import drf.pro.hogwartsv2.models.Casa;
import drf.pro.hogwartsv2.models.Estudiante;
import drf.pro.hogwartsv2.repositories.CasaRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Data
@Component
@RequiredArgsConstructor
public class EstudianteMapper {
    @Autowired
    private final CasaRepository casaRepository;
    private final MascotaMapper mascotaMapper;

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

        estudiante.setMascota(mascotaMapper.toEntity(dto.getMascota()));

        return estudiante;
    }

    public void updateEntityFromDto(EstudianteUpdateDTO dto, Estudiante estudiante) {
        if (dto == null || estudiante == null) return;

        estudiante.setAnyoCurso(dto.getAnyoCurso());
        estudiante.setFechaNacimiento(Date.valueOf(dto.getFechaNacimiento()));
        if (dto.getMascota() == null){
            if (estudiante.getMascota() != null) {
                estudiante.getMascota().setEstudiante(null); // rompe la relación
                estudiante.setMascota(null);
            }
        } else {
            mascotaMapper.updateEntityFromDto(dto.getMascota(), estudiante.getMascota());
        }
    }
}
