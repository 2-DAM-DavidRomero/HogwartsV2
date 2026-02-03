package drf.pro.hogwartsv2.dtos.request.create;

import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;

@Data
public class EstudianteCreateDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido completo no puede estar vacío")
    @Size(max = 100, message = "El apellido completo no puede superar los 100 caracteres")
    private String apellido;

    @Min(value = 1, message = "El idCasa debe ser mayor que 0")
    private Long idCasa;

    @Min(value = 1, message = "El año del curso debe ser mayor que 0")
    @Max(value = 7, message = "El año del curso debe ser menor que 7")
    private int anyoCurso;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;


}
