package drf.pro.hogwartsv2.dtos.request.update;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EstudianteUpdateDTO {

    @Min(value = 1, message = "El año del curso debe ser mayor que 0")
    @Max(value = 7, message = "El año del curso debe ser menor que 7")
    private int anyoCurso;

    @NotNull(message = "La fecha es obligatorio.")
    private LocalDate fechaNacimiento;

    @Valid
    private MascotaUpdateDTO mascota;
}
