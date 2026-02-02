package drf.pro.hogwartsv2.dtos.response;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class EstudianteDTO {
    private Long id;

    private String nombre;

    private String apellido;

    private int anyoCurso;

    private Date fechaNacimiento;

    private String mascota;

    private List<String> asignaturas;
}
