package drf.pro.hogwartsv2.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
public class EstudianteAsignaturaID  implements Serializable {

    @Column(name = "id_estudiante")
    private long estudianteId;

    @Column(name = "id_asignatura")
    private long asignaturaId;
}