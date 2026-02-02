package drf.pro.hogwartsv2.mappers;

import drf.pro.hogwartsv2.dtos.response.CasaDTO;
import drf.pro.hogwartsv2.models.Casa;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CasaMapper {

    private final EstudianteMapper estudianteMapper;

    public CasaDTO toDto(Casa casa) {
        if (casa == null) return null;

        CasaDTO dto = new CasaDTO();

        dto.setId(casa.getIdCasa());
        dto.setNombre(casa.getNombre());
        dto.setFundador(casa.getFundador());
        dto.setFantasma(casa.getFantasma());
        dto.setJefe(casa.getJefe().getNombre());
        dto.setEstudiantes(
                casa.getEstudiantes()
                        .stream()
                        .map(estudianteMapper::toDto)
                        .toList()
        );
        return dto;
    }
}
