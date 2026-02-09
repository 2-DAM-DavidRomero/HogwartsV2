package drf.pro.hogwartsv2.dtos.request.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MascotaCreateDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String nombre;

    @NotBlank(message = "La especie no puede estar vacía")
    @Size(max = 100, message = "La especie no puede superar los 100 caracteres")
    private String especie;
}
