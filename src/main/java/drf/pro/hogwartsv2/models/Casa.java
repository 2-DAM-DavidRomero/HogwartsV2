package drf.pro.hogwartsv2.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Entity
@Table(name = "casa")
public class Casa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_casa")
    private long idCasa;

    private String nombre;

    private String fundador;

    private String fantasma;

    @OneToOne
    @JoinColumn(name = "id_jefe", nullable = false, unique = true)
    private Profesor jefe;

    @OneToMany(mappedBy = "casa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Estudiante> estudiantes;
}