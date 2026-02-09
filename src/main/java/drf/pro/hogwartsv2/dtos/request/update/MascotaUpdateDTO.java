package drf.pro.hogwartsv2.dtos.request.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MascotaUpdateDTO {
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 50, message = "El nombre de la mascota no puede superar los 50 caracteres")
    private String nombre;

    @NotBlank(message = "La especie no puede estar vacío")
    @Size(max = 50, message = "La especie de la mascota no puede superar los 50 caracteres")
    private String especie;
}