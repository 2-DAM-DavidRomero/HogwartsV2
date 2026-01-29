package drf.pro.hogwartsv2.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name = "profesor")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profesor")
    private long idProfesor;

    private String nombre;

    private String apellido;

    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @OneToOne
    @JoinColumn(name = "id_asignatura")
    private Asignatura asignatura;

    @OneToOne(mappedBy = "jefe")
    private Casa casa;
}