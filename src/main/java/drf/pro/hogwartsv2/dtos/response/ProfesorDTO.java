package drf.pro.hogwartsv2.dtos.response;

import drf.pro.hogwartsv2.models.Asignatura;
import drf.pro.hogwartsv2.models.Casa;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;


@Data
public class ProfesorDTO {

    private String nombre;

    private String apellido;

    private Date fechaInicio;
}
