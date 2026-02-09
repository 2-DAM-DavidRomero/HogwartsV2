package drf.pro.hogwartsv2.mappers;

import drf.pro.hogwartsv2.dtos.request.create.EstudianteCreateDTO;
import drf.pro.hogwartsv2.dtos.request.create.MascotaCreateDTO;
import drf.pro.hogwartsv2.dtos.request.update.EstudianteUpdateDTO;
import drf.pro.hogwartsv2.dtos.request.update.MascotaUpdateDTO;
import drf.pro.hogwartsv2.models.Casa;
import drf.pro.hogwartsv2.models.Estudiante;
import drf.pro.hogwartsv2.models.Mascota;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@Data
@RequiredArgsConstructor
public class MascotaMapper {

    public Mascota toEntity(MascotaCreateDTO dto){

        if (dto == null) return null;

        Mascota mascota = new Mascota();

        mascota.setNombre(dto.getNombre());
        mascota.setEspecie(dto.getEspecie());

        return mascota;
    }

    public void updateEntityFromDto(MascotaUpdateDTO dto, Mascota mascota) {
        if (dto == null ||  mascota == null) return;

        mascota.setNombre(dto.getNombre());
        mascota.setEspecie(dto.getEspecie());
    }
}
