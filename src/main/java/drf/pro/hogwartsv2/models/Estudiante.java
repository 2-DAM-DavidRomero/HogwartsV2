package drf.pro.hogwartsv2.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "estudiante")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudiante")
    private long idEstudiante;

    private String nombre;

    private String apellido;

    @Column(name = "anyo_curso")
    private int anyoCurso;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "id_casa")
    private Casa casa;

    @OneToOne(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private Mascota mascota;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EstudianteAsignatura> asignaturas;
}