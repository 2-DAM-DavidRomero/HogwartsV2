package drf.pro.hogwartsv2.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "estudiante_asignatura")
public class EstudianteAsignatura {

    @EmbeddedId
    private EstudianteAsignaturaID id;

    private int calificacion;

    @ManyToOne
    @MapsId("asignaturaId")
    @JoinColumn(name = "id_asignatura")
    private Asignatura asignatura;

    @ManyToOne
    @MapsId("estudianteId")
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;
}
