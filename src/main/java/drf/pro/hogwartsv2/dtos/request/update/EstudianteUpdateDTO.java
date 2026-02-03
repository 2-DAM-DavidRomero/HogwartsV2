package drf.pro.hogwartsv2.dtos.request.update;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class EstudianteUpdateDTO {

    @Min(value = 1, message = "El año del curso debe ser mayor que 0")
    @Max(value = 7, message = "El año del curso debe ser menor que 7")
    private int anyoCurso;

}
