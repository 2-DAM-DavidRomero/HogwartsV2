package drf.pro.hogwartsv2.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "asignatura")
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignatura")
    private long idAsignatura;

    private String nombre;

    private String aula;

    private boolean obligatoria;

    @OneToOne(mappedBy = "asignatura")
    private Profesor profesor;

    @OneToMany(mappedBy = "asignatura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EstudianteAsignatura> estudiantes;
}
